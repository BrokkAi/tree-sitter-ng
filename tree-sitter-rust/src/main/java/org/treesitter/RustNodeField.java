package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code rust} from tree-sitter {@code node-types.json}.
 */
public enum RustNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    BODY("body"),
    BOUNDS("bounds"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    DEFAULT_TYPE("default_type"),
    DOC("doc"),
    ELEMENT("element"),
    FIELD("field"),
    FUNCTION("function"),
    INNER("inner"),
    LEFT("left"),
    LENGTH("length"),
    LIST("list"),
    MACRO("macro"),
    NAME("name"),
    OPERATOR("operator"),
    OUTER("outer"),
    PARAMETERS("parameters"),
    PATH("path"),
    PATTERN("pattern"),
    RETURN_TYPE("return_type"),
    RIGHT("right"),
    TRAIT("trait"),
    TYPE("type"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_PARAMETERS("type_parameters"),
    VALUE("value");

    private final @Nullable String name;

    RustNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static RustNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        RustNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, RustNodeField> LOOKUP = initLookup();

    private static Map<String, RustNodeField> initLookup() {
        HashMap<String, RustNodeField> m = new HashMap<>();
        for (RustNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
