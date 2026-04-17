package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code c-sharp} from tree-sitter {@code node-types.json}.
 */
public enum CSharpNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ACCESSORS("accessors"),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    BODY("body"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    CONTENT("content"),
    EXPRESSION("expression"),
    FUNCTION("function"),
    INITIALIZER("initializer"),
    LEFT("left"),
    NAME("name"),
    OPERATOR("operator"),
    PARAMETERS("parameters"),
    PATTERN("pattern"),
    QUALIFIER("qualifier"),
    RANK("rank"),
    RETURNS("returns"),
    RIGHT("right"),
    SUBSCRIPT("subscript"),
    TYPE("type"),
    TYPE_PARAMETERS("type_parameters"),
    UPDATE("update"),
    VALUE("value");

    private final @Nullable String name;

    CSharpNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static CSharpNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        CSharpNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, CSharpNodeField> LOOKUP = initLookup();

    private static Map<String, CSharpNodeField> initLookup() {
        HashMap<String, CSharpNodeField> m = new HashMap<>();
        for (CSharpNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
