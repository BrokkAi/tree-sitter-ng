package org.treesitter.build

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

class GenTask extends DefaultTask {

    static final class ParsedNodeType {
        final String type
        final boolean named
        final List<ParsedNodeType> subtypes
        final Map<String, ParsedFieldInfo> fields
        final ParsedChildrenInfo children

        ParsedNodeType(
                String type,
                boolean named,
                List<ParsedNodeType> subtypes,
                Map<String, ParsedFieldInfo> fields,
                ParsedChildrenInfo children
        ) {
            this.type = type
            this.named = named
            this.subtypes = subtypes
            this.fields = fields
            this.children = children
        }
    }

    static final class ParsedFieldInfo {
        final boolean required
        final boolean multiple
        final List<String> allowedTypes

        ParsedFieldInfo(boolean required, boolean multiple, List<String> allowedTypes) {
            this.required = required
            this.multiple = multiple
            this.allowedTypes = allowedTypes
        }
    }

    static final class ParsedChildrenInfo {
        final boolean required
        final boolean multiple
        final List<String> allowedTypes

        ParsedChildrenInfo(boolean required, boolean multiple, List<String> allowedTypes) {
            this.required = required
            this.multiple = multiple
            this.allowedTypes = allowedTypes
        }
    }

    static String subProjectName(String libShortName) {
        return "tree-sitter-$libShortName"
    }

    static String capitalizedLibName(String libShortName) {
        return libShortName.split("-").collect { it.capitalize() }.join("")
    }

    static String libIdentifierName(String libShortName) {
        return libShortName.replace("-", "_")
    }

    static File findNodeTypesJson(File downloadSrcRoot, String libShortName = null) {
        if (downloadSrcRoot == null || !downloadSrcRoot.exists() || !downloadSrcRoot.isDirectory()) {
            return null
        }

        def matches = []
        downloadSrcRoot.eachFileRecurse { File f ->
            if (f.isFile() && f.name == "node-types.json") {
                matches.add(f)
            }
        }
        if (matches.isEmpty()) {
            return null
        }
        if (matches.size() == 1) {
            return (File) matches[0]
        }

        if (libShortName != null && !libShortName.trim().isEmpty()) {
            def slug = libShortName.trim().replace('\\', '/')
            def best = matches.findAll { File f ->
                f.path.replace('\\', '/').endsWith("/${slug}/src/node-types.json")
            }
            if (best.size() == 1) {
                return (File) best[0]
            }
        }

        def preferred = matches.findAll { File f ->
            f.parentFile?.name == "src"
        }
        if (preferred.size() == 1) {
            return (File) preferred[0]
        }
        if (preferred.size() > 1) {
            def preferredEnds = preferred.findAll { File f ->
                f.path.replace('\\', '/').endsWith("/src/node-types.json")
            }
            if (preferredEnds.size() == 1) {
                return (File) preferredEnds[0]
            }
        }

        def list = matches.collect { it.absolutePath }.sort().join("\n  - ")
        throw new GradleException("Multiple node-types.json files found under ${downloadSrcRoot.absolutePath}:\n  - ${list}\nPlease disambiguate by adjusting the upstream source layout or generator heuristics.")
    }

    static List<ParsedNodeType> parseNodeTypes(File jsonFile) {
        def slurper = new JsonSlurper()
        def parsed = slurper.parse(jsonFile)
        if (!(parsed instanceof List)) {
            throw new GradleException("Expected node-types.json top-level array at ${jsonFile.absolutePath}")
        }
        return ((List) parsed).collect { obj ->
            if (!(obj instanceof Map)) {
                return new ParsedNodeType(null, false, [], [:], null)
            }
            def type = obj.get("type")
            def named = obj.get("named") == true
            def subtypesRaw = obj.get("subtypes")
            def subtypes = []
            if (subtypesRaw instanceof List) {
                subtypes = ((List) subtypesRaw).collect { st ->
                    if (st instanceof Map) {
                        return new ParsedNodeType(
                                st.get("type") instanceof String ? (String) st.get("type") : null,
                                st.get("named") == true,
                                [],
                                [:],
                                null
                        )
                    }
                    return new ParsedNodeType(null, false, [], [:], null)
                }
            }

            def fields = parseFields(obj.get("fields"))
            def children = parseChildren(obj.get("children"))

            return new ParsedNodeType(
                    type instanceof String ? (String) type : null,
                    named,
                    (List<ParsedNodeType>) subtypes,
                    fields,
                    children
            )
        }
    }

    private static Map<String, ParsedFieldInfo> parseFields(Object fieldsRaw) {
        if (!(fieldsRaw instanceof Map)) return [:]
        def out = new LinkedHashMap<String, ParsedFieldInfo>()
        ((Map) fieldsRaw).each { k, v ->
            if (!(k instanceof String)) return
            if (!(v instanceof Map)) return
            def fieldName = ((String) k).trim()
            if (fieldName.isEmpty()) return
            out.put(fieldName, parseFieldInfo((Map) v))
        }
        return out
    }

    private static ParsedFieldInfo parseFieldInfo(Map infoRaw) {
        def required = infoRaw.get("required") == true
        def multiple = infoRaw.get("multiple") == true
        def allowedTypes = parseAllowedTypes(infoRaw.get("types"))
        return new ParsedFieldInfo(required, multiple, allowedTypes)
    }

    private static ParsedChildrenInfo parseChildren(Object childrenRaw) {
        if (!(childrenRaw instanceof Map)) return null
        def m = (Map) childrenRaw
        def required = m.get("required") == true
        def multiple = m.get("multiple") == true
        def allowedTypes = parseAllowedTypes(m.get("types"))
        return new ParsedChildrenInfo(required, multiple, allowedTypes)
    }

    private static List<String> parseAllowedTypes(Object typesRaw) {
        if (!(typesRaw instanceof List)) return []
        def out = []
        ((List) typesRaw).each { t ->
            if (!(t instanceof Map)) return
            def named = ((Map) t).get("named") == true
            def type = ((Map) t).get("type")
            if (!named) return
            if (!(type instanceof String)) return
            def s = ((String) type).trim()
            if (s.isEmpty()) return
            out.add(s)
        }
        return out.unique().sort()
    }

    private static final Set<String> JAVA_KEYWORDS = [
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
            "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
            "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile", "while",
            // contextual/reserved in newer Java
            "var", "yield", "record", "sealed", "permits", "non-sealed"
    ] as Set<String>

    static String constantNameForNodeType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return null
        }
        def s = type.trim()
        s = s.replaceAll(/[^A-Za-z0-9]+/, "_")
        s = s.replaceAll(/_+/, "_")
        s = s.replaceAll(/^_+|_+$/, "")
        s = s.toUpperCase(Locale.ROOT)
        if (s.isEmpty()) {
            return null
        }
        if (s.charAt(0).isDigit()) {
            s = "_" + s
        }
        if (JAVA_KEYWORDS.contains(s.toLowerCase(Locale.ROOT))) {
            s = s + "_"
        }
        return s
    }

    private static Map<String, String> namedTypeConstMap(List<ParsedNodeType> nodes) {
        def namedNodes = nodes.findAll { it.named && it.type instanceof String && it.type != null }
        def constByType = new LinkedHashMap<String, String>()
        def usedConstNames = new HashSet<String>()
        namedNodes.collect { (String) it.type }.unique().sort().each { String type ->
            def base = constantNameForNodeType(type)
            if (base == null) return
            def name = base
            def i = 2
            while (usedConstNames.contains(name)) {
                name = "${base}_${i}"
                i++
            }
            usedConstNames.add(name)
            constByType.put(type, name)
        }
        return constByType
    }

    private static Map<String, String> fieldNameConstMap(List<ParsedNodeType> nodes) {
        def fieldNames = new LinkedHashSet<String>()
        nodes.each { n ->
            if (n.fields instanceof Map) {
                n.fields.keySet().each { fn ->
                    if (fn instanceof String && !((String) fn).trim().isEmpty()) fieldNames.add(((String) fn).trim())
                }
            }
        }
        def constByFieldName = new LinkedHashMap<String, String>()
        def usedConstNames = new HashSet<String>()
        fieldNames.toList().unique().sort().each { String fieldName ->
            def base = constantNameForNodeType(fieldName)
            if (base == null) return
            def name = base
            def i = 2
            while (usedConstNames.contains(name)) {
                name = "${base}_${i}"
                i++
            }
            usedConstNames.add(name)
            constByFieldName.put(fieldName, name)
        }
        return constByFieldName
    }

    static void writeNodeTypesClass(String libShortName, File outDir, List<ParsedNodeType> nodes) {
        def capitalized = capitalizedLibName(libShortName)
        def enumName = "${capitalized}NodeType"
        def pkgDir = new File(outDir, "org/treesitter")
        def outFile = new File(pkgDir, "${enumName}.java")

        def constByType = namedTypeConstMap(nodes)

        def setDecls = []
        nodes.each { n ->
            if (!(n.subtypes instanceof List) || n.subtypes.isEmpty()) return
            def superType = n.type instanceof String ? (String) n.type : null
            if (superType == null) return
            def setBase = constantNameForNodeType(superType)
            if (setBase == null) return

            def subtypeConsts = []
            n.subtypes.each { st ->
                if (!st.named) return
                def t = st.type
                if (!(t instanceof String) || t == null) return
                def cn = constByType.get(t)
                if (cn != null) subtypeConsts.add(cn)
            }
            subtypeConsts = subtypeConsts.unique().sort()
            if (subtypeConsts.isEmpty()) return

            // In an enum, constants share the same namespace as static fields, so always suffix sets.
            def setName = "${setBase}_SET"
            setDecls.add([name: setName, members: subtypeConsts])
        }
        setDecls = setDecls.unique { it.name }.sort { a, b -> a.name <=> b.name }

        def content = new StringBuilder()
        content.append("package org.treesitter;\n\n")
        content.append("import java.util.Collections;\n")
        content.append("import java.util.HashMap;\n")
        content.append("import java.util.Map;\n")
        if (!setDecls.isEmpty()) {
            content.append("import java.util.Set;\n")
        }
        content.append("import org.jspecify.annotations.Nullable;\n\n")
        content.append("/**\n")
        content.append(" * Node types for {@code ${libShortName}} from tree-sitter {@code node-types.json}.\n")
        content.append(" */\n")
        content.append("public enum ${enumName} {\n")
        content.append("    /** Represents a null TSNode reference or a TSNode with a null type. */\n")
        content.append("    __NULL__(null),\n")

        def entries = constByType.entrySet().toList().sort { a, b -> a.value <=> b.value }
        entries.eachWithIndex { e, idx ->
            def suffix = (idx == entries.size() - 1) ? ";" : ","
            content.append("    ${e.value}(\"${e.key}\")${suffix}\n")
        }
        if (!setDecls.isEmpty()) {
            content.append("\n")
            setDecls.each { s ->
                content.append("    public static final Set<${enumName}> ${s.name} = Set.of(${s.members.join(', ')});\n")
            }
        }
        content.append("\n")
        content.append("    private final @Nullable String type;\n\n")
        content.append("    ${enumName}(@Nullable String type) {\n")
        content.append("        this.type = type;\n")
        content.append("    }\n\n")
        content.append("    public @Nullable String getType() {\n")
        content.append("        return type;\n")
        content.append("    }\n\n")
        content.append("    public static ${enumName} from(@Nullable TSNode node) {\n")
        content.append("        if (node == null) return __NULL__;\n")
        content.append("        return fromType(node.getType());\n")
        content.append("    }\n\n")
        content.append("    public static ${enumName} fromType(@Nullable String type) {\n")
        content.append("        if (type == null) return __NULL__;\n")
        content.append("        ${enumName} t = LOOKUP.get(type);\n")
        content.append("        return t == null ? __NULL__ : t;\n")
        content.append("    }\n\n")
        content.append("    private static final Map<String, ${enumName}> LOOKUP = initLookup();\n\n")
        content.append("    private static Map<String, ${enumName}> initLookup() {\n")
        content.append("        HashMap<String, ${enumName}> m = new HashMap<>();\n")
        content.append("        for (${enumName} t : values()) {\n")
        content.append("            if (t.type != null) m.put(t.type, t);\n")
        content.append("        }\n")
        content.append("        return Collections.unmodifiableMap(m);\n")
        content.append("    }\n")
        content.append("}\n")

        pkgDir.mkdirs()
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content.toString()) }
        }
    }

    static void writeNodeFieldsClass(String libShortName, File outDir, List<ParsedNodeType> nodes) {
        def capitalized = capitalizedLibName(libShortName)
        def enumName = "${capitalized}NodeField"
        def pkgDir = new File(outDir, "org/treesitter")
        def outFile = new File(pkgDir, "${enumName}.java")

        def constByFieldName = fieldNameConstMap(nodes)

        def content = new StringBuilder()
        content.append("package org.treesitter;\n\n")
        content.append("import java.util.Collections;\n")
        content.append("import java.util.HashMap;\n")
        content.append("import java.util.Map;\n")
        content.append("import org.jspecify.annotations.Nullable;\n\n")
        content.append("/**\n")
        content.append(" * Node field names for {@code ${libShortName}} from tree-sitter {@code node-types.json}.\n")
        content.append(" */\n")
        content.append("public enum ${enumName} {\n")
        content.append("    /** Represents a null field reference or a null field name. */\n")
        content.append("    __NULL__(null)")
        if (!constByFieldName.isEmpty()) {
            content.append(",\n")
        } else {
            content.append(";\n")
        }

        def entries = constByFieldName.entrySet().toList().sort { a, b -> a.value <=> b.value }
        entries.eachWithIndex { e, idx ->
            def suffix = (idx == entries.size() - 1) ? ";" : ","
            content.append("    ${e.value}(\"${e.key}\")${suffix}\n")
        }

        content.append("\n")
        content.append("    private final @Nullable String name;\n\n")
        content.append("    ${enumName}(@Nullable String name) {\n")
        content.append("        this.name = name;\n")
        content.append("    }\n\n")
        content.append("    public @Nullable String getName() {\n")
        content.append("        return name;\n")
        content.append("    }\n\n")
        content.append("    public static ${enumName} fromName(@Nullable String name) {\n")
        content.append("        if (name == null) return __NULL__;\n")
        content.append("        ${enumName} f = LOOKUP.get(name);\n")
        content.append("        return f == null ? __NULL__ : f;\n")
        content.append("    }\n\n")
        content.append("    private static final Map<String, ${enumName}> LOOKUP = initLookup();\n\n")
        content.append("    private static Map<String, ${enumName}> initLookup() {\n")
        content.append("        HashMap<String, ${enumName}> m = new HashMap<>();\n")
        content.append("        for (${enumName} f : values()) {\n")
        content.append("            if (f.name != null) m.put(f.name, f);\n")
        content.append("        }\n")
        content.append("        return Collections.unmodifiableMap(m);\n")
        content.append("    }\n")
        content.append("}\n")

        pkgDir.mkdirs()
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content.toString()) }
        }
    }

    static void writeNodeSchemaClass(String libShortName, File outDir, List<ParsedNodeType> nodes) {
        def capitalized = capitalizedLibName(libShortName)
        def nodeTypeName = "${capitalized}NodeType"
        def fieldEnumName = "${capitalized}NodeField"
        def className = "${capitalized}NodeSchema"
        def pkgDir = new File(outDir, "org/treesitter")
        def outFile = new File(pkgDir, "${className}.java")

        def constByType = namedTypeConstMap(nodes)
        def constByFieldName = fieldNameConstMap(nodes)

        def ownerToFields = new LinkedHashMap<String, List<Map>>()
        def ownerToChildren = new LinkedHashMap<String, Map>()

        nodes.each { n ->
            if (!n.named) return
            def rawType = n.type instanceof String ? (String) n.type : null
            if (rawType == null) return
            def ownerConst = constByType.get(rawType)
            if (ownerConst == null) return

            if (n.fields instanceof Map && !n.fields.isEmpty()) {
                def list = []
                n.fields.each { fn, info ->
                    if (!(fn instanceof String)) return
                    def fieldName = ((String) fn).trim()
                    if (fieldName.isEmpty()) return
                    def fieldConst = constByFieldName.get(fieldName)
                    if (fieldConst == null) return
                    if (!(info instanceof ParsedFieldInfo)) return

                    def allowedConsts = []
                    info.allowedTypes.each { at ->
                        def c = constByType.get(at)
                        if (c != null) allowedConsts.add("${nodeTypeName}.${c}")
                    }
                    allowedConsts = allowedConsts.unique().sort()

                    list.add([
                            fieldConst: fieldConst,
                            required  : info.required == true,
                            multiple  : info.multiple == true,
                            allowed   : allowedConsts
                    ])
                }
                if (!list.isEmpty()) {
                    list = list.sort { a, b -> a.fieldConst <=> b.fieldConst }
                    ownerToFields.put(ownerConst, list)
                }
            }

            if (n.children != null) {
                def allowedConsts = []
                n.children.allowedTypes.each { at ->
                    def c = constByType.get(at)
                    if (c != null) allowedConsts.add("${nodeTypeName}.${c}")
                }
                allowedConsts = allowedConsts.unique().sort()
                ownerToChildren.put(ownerConst, [
                        required: n.children.required == true,
                        multiple: n.children.multiple == true,
                        allowed : allowedConsts
                ])
            }
        }

        def content = new StringBuilder()
        content.append("package org.treesitter;\n\n")
        content.append("import java.util.Collections;\n")
        content.append("import java.util.EnumMap;\n")
        content.append("import java.util.Map;\n")
        content.append("import java.util.Set;\n")
        content.append("import org.jspecify.annotations.Nullable;\n\n")
        content.append("/**\n")
        content.append(" * Lightweight schema utilities for {@code ${libShortName}} from tree-sitter {@code node-types.json}.\n")
        content.append(" */\n")
        content.append("public final class ${className} {\n")
        content.append("    private ${className}() {}\n\n")
        content.append("    public static Set<${fieldEnumName}> fields(@Nullable ${nodeTypeName} owner) {\n")
        content.append("        if (owner == null) return Collections.emptySet();\n")
        content.append("        Map<${fieldEnumName}, FieldInfo> m = FIELDS.get(owner);\n")
        content.append("        if (m == null) return Collections.emptySet();\n")
        content.append("        return m.keySet();\n")
        content.append("    }\n\n")
        content.append("    public static Set<${nodeTypeName}> allowedTypes(@Nullable ${nodeTypeName} owner, @Nullable ${fieldEnumName} field) {\n")
        content.append("        if (owner == null || field == null) return Collections.emptySet();\n")
        content.append("        Map<${fieldEnumName}, FieldInfo> m = FIELDS.get(owner);\n")
        content.append("        if (m == null) return Collections.emptySet();\n")
        content.append("        FieldInfo info = m.get(field);\n")
        content.append("        if (info == null) return Collections.emptySet();\n")
        content.append("        return info.allowedTypes;\n")
        content.append("    }\n\n")
        content.append("    public static boolean isRequired(@Nullable ${nodeTypeName} owner, @Nullable ${fieldEnumName} field) {\n")
        content.append("        if (owner == null || field == null) return false;\n")
        content.append("        Map<${fieldEnumName}, FieldInfo> m = FIELDS.get(owner);\n")
        content.append("        if (m == null) return false;\n")
        content.append("        FieldInfo info = m.get(field);\n")
        content.append("        return info != null && info.required;\n")
        content.append("    }\n\n")
        content.append("    public static boolean isMultiple(@Nullable ${nodeTypeName} owner, @Nullable ${fieldEnumName} field) {\n")
        content.append("        if (owner == null || field == null) return false;\n")
        content.append("        Map<${fieldEnumName}, FieldInfo> m = FIELDS.get(owner);\n")
        content.append("        if (m == null) return false;\n")
        content.append("        FieldInfo info = m.get(field);\n")
        content.append("        return info != null && info.multiple;\n")
        content.append("    }\n\n")
        content.append("    public static Set<${nodeTypeName}> allowedChildTypes(@Nullable ${nodeTypeName} owner) {\n")
        content.append("        if (owner == null) return Collections.emptySet();\n")
        content.append("        ChildInfo info = CHILDREN.get(owner);\n")
        content.append("        if (info == null) return Collections.emptySet();\n")
        content.append("        return info.allowedTypes;\n")
        content.append("    }\n\n")
        content.append("    public static boolean childrenRequired(@Nullable ${nodeTypeName} owner) {\n")
        content.append("        if (owner == null) return false;\n")
        content.append("        ChildInfo info = CHILDREN.get(owner);\n")
        content.append("        return info != null && info.required;\n")
        content.append("    }\n\n")
        content.append("    public static boolean childrenMultiple(@Nullable ${nodeTypeName} owner) {\n")
        content.append("        if (owner == null) return false;\n")
        content.append("        ChildInfo info = CHILDREN.get(owner);\n")
        content.append("        return info != null && info.multiple;\n")
        content.append("    }\n\n")
        content.append("    private static final EnumMap<${nodeTypeName}, Map<${fieldEnumName}, FieldInfo>> FIELDS = initFields();\n")
        content.append("    private static final EnumMap<${nodeTypeName}, ChildInfo> CHILDREN = initChildren();\n\n")
        content.append("    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.\n")
        content.append("    @SuppressWarnings(\"unused\")\n")
        content.append("    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());\n")
        content.append("    @SuppressWarnings(\"unused\")\n")
        content.append("    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());\n\n")
        content.append("    private static EnumMap<${nodeTypeName}, Map<${fieldEnumName}, FieldInfo>> initFields() {\n")
        content.append("        EnumMap<${nodeTypeName}, Map<${fieldEnumName}, FieldInfo>> out = new EnumMap<>(${nodeTypeName}.class);\n")
        ownerToFields.entrySet().toList().sort { a, b -> a.key <=> b.key }.each { e ->
            content.append("        {\n")
            content.append("            EnumMap<${fieldEnumName}, FieldInfo> m = new EnumMap<>(${fieldEnumName}.class);\n")
            e.value.each { fi ->
                def allowed = (fi.allowed instanceof List && !fi.allowed.isEmpty()) ? "Set.of(${fi.allowed.join(', ')})" : "Collections.emptySet()"
                content.append("            m.put(${fieldEnumName}.${fi.fieldConst}, new FieldInfo(${fi.required}, ${fi.multiple}, ${allowed}));\n")
            }
            content.append("            out.put(${nodeTypeName}.${e.key}, Collections.unmodifiableMap(m));\n")
            content.append("        }\n")
        }
        content.append("        return out;\n")
        content.append("    }\n\n")
        content.append("    private static EnumMap<${nodeTypeName}, ChildInfo> initChildren() {\n")
        content.append("        EnumMap<${nodeTypeName}, ChildInfo> out = new EnumMap<>(${nodeTypeName}.class);\n")
        ownerToChildren.entrySet().toList().sort { a, b -> a.key <=> b.key }.each { e ->
            def allowed = (e.value.allowed instanceof List && !e.value.allowed.isEmpty()) ? "Set.of(${e.value.allowed.join(', ')})" : "Collections.emptySet()"
            content.append("        out.put(${nodeTypeName}.${e.key}, new ChildInfo(${e.value.required}, ${e.value.multiple}, ${allowed}));\n")
        }
        content.append("        return out;\n")
        content.append("    }\n\n")
        content.append("    private static final class FieldInfo {\n")
        content.append("        final boolean required;\n")
        content.append("        final boolean multiple;\n")
        content.append("        final Set<${nodeTypeName}> allowedTypes;\n\n")
        content.append("        FieldInfo(boolean required, boolean multiple, Set<${nodeTypeName}> allowedTypes) {\n")
        content.append("            this.required = required;\n")
        content.append("            this.multiple = multiple;\n")
        content.append("            this.allowedTypes = allowedTypes;\n")
        content.append("        }\n")
        content.append("    }\n\n")
        content.append("    private static final class ChildInfo {\n")
        content.append("        final boolean required;\n")
        content.append("        final boolean multiple;\n")
        content.append("        final Set<${nodeTypeName}> allowedTypes;\n\n")
        content.append("        ChildInfo(boolean required, boolean multiple, Set<${nodeTypeName}> allowedTypes) {\n")
        content.append("            this.required = required;\n")
        content.append("            this.multiple = multiple;\n")
        content.append("            this.allowedTypes = allowedTypes;\n")
        content.append("        }\n")
        content.append("    }\n")
        content.append("}\n")

        pkgDir.mkdirs()
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content.toString()) }
        }
    }

    static void genJavaFile(File projectDir, String libShortName) {
        def capitalized = capitalizedLibName(libShortName)
        def className = "TreeSitter$capitalized"
        def idName = libIdentifierName(libShortName)
        def classFile = new File(projectDir, "src/main/java/org/treesitter/${className}.java")
        def content = """
package org.treesitter;

import org.treesitter.utils.NativeUtils;

/**
 * Tree-sitter language binding for {@code $libShortName}.
 * <p>
 * This class provides the native language definition for use with {@link TSParser}.
 */
public class $className extends TSLanguage {

    static {
        NativeUtils.loadLib("lib/tree-sitter-$libShortName");
    }
    private native static long tree_sitter_$idName();

    /**
     * Create a new instance of the {@code $libShortName} language.
     */
    public $className() {
        super(tree_sitter_$idName());
    }

    /**
     * Create a new instance from an existing native pointer.
     *
     * @param ptr the native pointer to the language
     */
    private $className(long ptr) {
        super(ptr);
    }

    @Override
    public TSLanguage copy() {
        return new $className(copyPtr());
    }
}
"""
        classFile.getParentFile().mkdirs()
        try (OutputStream outputStream = new FileOutputStream(classFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content) }
        }
    }

    static void genPackageInfo(File projectDir) {
        def packageInfoFile = new File(projectDir, "src/main/java/org/treesitter/package-info.java")
        def content = """
@NullMarked
package org.treesitter;

import org.jspecify.annotations.NullMarked;
"""
        packageInfoFile.getParentFile().mkdirs()
        try (OutputStream outputStream = new FileOutputStream(packageInfoFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content.trim() + System.lineSeparator()) }
        }
    }

    static void genJavaTestFile(File projectDir, String libShortName) {
        def capitalized = capitalizedLibName(libShortName)
        def classFile = new File(projectDir, "src/test/java/org/treesitter/TreeSitter${capitalized}Test.java")
        def content = """
package org.treesitter;

import org.junit.jupiter.api.Test;
import org.treesitter.tests.CorpusTest;

import java.io.IOException;

class TreeSitter${capitalized}Test {
    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitter$capitalized(), "$libShortName");
    }
}
"""
        classFile.getParentFile().mkdirs()
        try (OutputStream outputStream = new FileOutputStream(classFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content) }
        }
    }

    static void genProperties(File projectDir, String version) {
        def content = """upstreamVersion=${version}"""
        try (OutputStream outputStream = new FileOutputStream(new File(projectDir, "gradle.properties"))) {
            outputStream.withPrintWriter { it.write(content) }
        }
    }


    static void genBuildGradle(File projectDir, String url) {
        def gradleFile = new File(projectDir, "build.gradle")
        def content = """
tasks.named('downloadSource') {
    url = "$url"
}
"""
        try (OutputStream outputStream = new FileOutputStream(gradleFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content) }
        }
    }

    static String jniMethodName(String idName) {
        return idName.replace("_", "_1")
    }

    static void genJniCFile(File projectDir, String libShortName) {
        def capitalized = capitalizedLibName(libShortName)
        def cFile = new File(projectDir, "src/main/c/org_treesitter_TreeSitter${capitalized}.c")
        def idName = libIdentifierName(libShortName)
        def jniMethodName = jniMethodName(idName)
        def content = """
#include <jni.h>
void *tree_sitter_$idName();
/*
 * Class:     org_treesitter_TreeSitter$capitalized
 * Method:    tree_sitter_$idName
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_treesitter_TreeSitter${capitalized}_tree_1sitter_1$jniMethodName
  (JNIEnv *env, jclass clz){
   return (jlong) tree_sitter_$idName();
}
"""
        cFile.getParentFile().mkdirs()
        try (OutputStream outputStream = new FileOutputStream(cFile)) {
            outputStream.withPrintWriter { writer -> writer.write(content) }
        }
    }

    static void updateSettingsGradle(Project project, String libShortName) {
        def projectLine = "include 'tree-sitter-$libShortName'"
        def settingsFile = project.rootProject.file("settings.gradle")
        def shouldUpdate = true

        try (InputStream inputStream = new FileInputStream(settingsFile)) {
            inputStream.withReader { reader ->
                reader.eachLine { line ->
                    {
                        if (line == projectLine) {
                            shouldUpdate = false
                        }
                    }
                }
            }
        }
        if (shouldUpdate) {
            try (OutputStream outputStream = new FileOutputStream(settingsFile, true)) {
                outputStream.withPrintWriter { writer -> writer.println(projectLine + System.lineSeparator()) }
            }
        }
    }

    static void genAll(Project project, String libShortName, String version, String url) {
        def subProjectName = subProjectName(libShortName)
        def projectDir = project.rootProject.layout.projectDirectory.dir(subProjectName).asFile
        if (projectDir.exists()) {
            throw new GradleException("Can't generate sub project. $projectDir existed!")
        }
        project.rootProject.mkdir(projectDir)

        genProperties(projectDir, version)
        genPackageInfo(projectDir)
        genJavaFile(projectDir, libShortName)
        genJniCFile(projectDir, libShortName)
        genBuildGradle(projectDir, url)
        genJavaTestFile(projectDir, libShortName)
        updateSettingsGradle(project, libShortName)
    }

    private String url
    private String libShortName
    private String version

    @Option(option = "parser-zip", description = "Parser zip url")
    void setUrl(String url) {
        this.url = url
    }

    @Option(option = "parser-name", description = "Parser name. e.g., json")
    void setShortName(String libShortName) {
        this.libShortName = libShortName
    }


    @Option(option = "parser-version", description = "Parser version. e.g., 1.0.0, master")
    void setVersion(String version) {
        this.version = version
    }

    @TaskAction
    void gen() {
        if (url == null || libShortName == null || version == null) {
            throw new GradleException("Require options missing! \nExample:\n  ./gradlew gen --parser-name=bash --parser-version=0.1.1 --parser-zip=https://exmaple.org/bash.zip")
        }
        genAll(project, libShortName, version, url)
    }

}
