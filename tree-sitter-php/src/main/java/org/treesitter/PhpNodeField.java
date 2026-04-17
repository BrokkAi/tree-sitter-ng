package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code php} from tree-sitter {@code node-types.json}.
 */
public enum PhpNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    ATTRIBUTES("attributes"),
    BODY("body"),
    CONDITION("condition"),
    CONDITIONAL_EXPRESSIONS("conditional_expressions"),
    DEFAULT_VALUE("default_value"),
    END_TAG("end_tag"),
    FINAL_("final"),
    FUNCTION("function"),
    IDENTIFIER("identifier"),
    INITIALIZE("initialize"),
    LEFT("left"),
    NAME("name"),
    OBJECT("object"),
    OPERATOR("operator"),
    PARAMETERS("parameters"),
    PREFIX("prefix"),
    READONLY("readonly"),
    REFERENCE_MODIFIER("reference_modifier"),
    RETURN_EXPRESSION("return_expression"),
    RETURN_TYPE("return_type"),
    RIGHT("right"),
    SCOPE("scope"),
    STATIC_MODIFIER("static_modifier"),
    TYPE("type"),
    UPDATE("update"),
    VALUE("value"),
    VISIBILITY("visibility");

    private final @Nullable String name;

    PhpNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static PhpNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        PhpNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, PhpNodeField> LOOKUP = initLookup();

    private static Map<String, PhpNodeField> initLookup() {
        HashMap<String, PhpNodeField> m = new HashMap<>();
        for (PhpNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
