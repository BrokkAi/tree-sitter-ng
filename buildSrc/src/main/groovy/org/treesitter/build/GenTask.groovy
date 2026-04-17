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

        ParsedNodeType(String type, boolean named, List<ParsedNodeType> subtypes) {
            this.type = type
            this.named = named
            this.subtypes = subtypes
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
                return new ParsedNodeType(null, false, [])
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
                                []
                        )
                    }
                    return new ParsedNodeType(null, false, [])
                }
            }
            return new ParsedNodeType(type instanceof String ? (String) type : null, named, (List<ParsedNodeType>) subtypes)
        }
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

    static void writeNodeTypesClass(String libShortName, File outDir, List<ParsedNodeType> nodes) {
        def capitalized = capitalizedLibName(libShortName)
        def className = "${capitalized}NodeTypes"
        def pkgDir = new File(outDir, "org/treesitter")
        def outFile = new File(pkgDir, "${className}.java")

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

            def setName = constByType.values().contains(setBase) ? "${setBase}_SET" : setBase
            setDecls.add([name: setName, members: subtypeConsts])
        }
        setDecls = setDecls.unique { it.name }.sort { a, b -> a.name <=> b.name }

        def content = new StringBuilder()
        content.append("package org.treesitter;\n\n")
        if (!setDecls.isEmpty()) {
            content.append("import java.util.Set;\n\n")
        }
        content.append("/**\n")
        content.append(" * Node type constants for {@code ${libShortName}} from tree-sitter {@code node-types.json}.\n")
        content.append(" */\n")
        content.append("public final class ${className} {\n")
        content.append("    private ${className}() {}\n\n")

        constByType.entrySet().toList().sort { a, b -> a.value <=> b.value }.each { e ->
            content.append("    public static final String ${e.value} = \"${e.key}\";\n")
        }
        if (!setDecls.isEmpty()) {
            content.append("\n")
            setDecls.each { s ->
                content.append("    public static final Set<String> ${s.name} = Set.of(${s.members.join(', ')});\n")
            }
        }
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
