package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code ruby} from tree-sitter {@code node-types.json}.
 */
public enum RubyNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ALTERNATIVES("alternatives"),
    ARGUMENTS("arguments"),
    BEGIN("begin"),
    BLOCK("block"),
    BODY("body"),
    CLASS_("class"),
    CLAUSES("clauses"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    ELSE_("else"),
    END("end"),
    EXCEPTIONS("exceptions"),
    GUARD("guard"),
    HANDLER("handler"),
    KEY("key"),
    LEFT("left"),
    LOCALS("locals"),
    METHOD("method"),
    NAME("name"),
    OBJECT("object"),
    OPERAND("operand"),
    OPERATOR("operator"),
    PARAMETERS("parameters"),
    PATTERN("pattern"),
    RECEIVER("receiver"),
    RIGHT("right"),
    SCOPE("scope"),
    SUPERCLASS("superclass"),
    VALUE("value"),
    VARIABLE("variable");

    private final @Nullable String name;

    RubyNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static RubyNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        RubyNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, RubyNodeField> LOOKUP = initLookup();

    private static Map<String, RubyNodeField> initLookup() {
        HashMap<String, RubyNodeField> m = new HashMap<>();
        for (RubyNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
