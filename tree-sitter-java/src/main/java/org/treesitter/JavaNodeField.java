package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code java} from tree-sitter {@code node-types.json}.
 */
public enum JavaNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALTERNATIVE("alternative"),
    ARGUMENTS("arguments"),
    ARRAY("array"),
    BODY("body"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    CONSTRUCTOR("constructor"),
    DECLARATOR("declarator"),
    DIMENSIONS("dimensions"),
    ELEMENT("element"),
    FIELD("field"),
    INDEX("index"),
    INIT("init"),
    INTERFACES("interfaces"),
    KEY("key"),
    LEFT("left"),
    MODIFIERS("modifiers"),
    MODULE("module"),
    MODULES("modules"),
    NAME("name"),
    OBJECT("object"),
    OPERAND("operand"),
    OPERATOR("operator"),
    PACKAGE_("package"),
    PARAMETERS("parameters"),
    PATTERN("pattern"),
    PERMITS_("permits"),
    PROVIDED("provided"),
    PROVIDER("provider"),
    RESOURCES("resources"),
    RIGHT("right"),
    SCOPE("scope"),
    SUPERCLASS("superclass"),
    TEMPLATE_ARGUMENT("template_argument"),
    TEMPLATE_PROCESSOR("template_processor"),
    TYPE("type"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_PARAMETERS("type_parameters"),
    UPDATE("update"),
    VALUE("value");

    private final @Nullable String name;

    JavaNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static JavaNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        JavaNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, JavaNodeField> LOOKUP = initLookup();

    private static Map<String, JavaNodeField> initLookup() {
        HashMap<String, JavaNodeField> m = new HashMap<>();
        for (JavaNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
