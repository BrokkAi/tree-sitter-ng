package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code python} from tree-sitter {@code node-types.json}.
 */
public enum PythonNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    ATTRIBUTE("attribute"),
    BODY("body"),
    CAUSE("cause"),
    CODE("code"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    DEFINITION("definition"),
    EXPRESSION("expression"),
    FORMAT_SPECIFIER("format_specifier"),
    FUNCTION("function"),
    GUARD("guard"),
    KEY("key"),
    LEFT("left"),
    MODULE_NAME("module_name"),
    NAME("name"),
    OBJECT("object"),
    OPERATOR("operator"),
    OPERATORS("operators"),
    PARAMETERS("parameters"),
    RETURN_TYPE("return_type"),
    RIGHT("right"),
    SUBJECT("subject"),
    SUBSCRIPT("subscript"),
    SUPERCLASSES("superclasses"),
    TYPE("type"),
    TYPE_CONVERSION("type_conversion"),
    TYPE_PARAMETERS("type_parameters"),
    VALUE("value");

    private final @Nullable String name;

    PythonNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static PythonNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        PythonNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, PythonNodeField> LOOKUP = initLookup();

    private static Map<String, PythonNodeField> initLookup() {
        HashMap<String, PythonNodeField> m = new HashMap<>();
        for (PythonNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
