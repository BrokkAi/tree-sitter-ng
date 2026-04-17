package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code go} from tree-sitter {@code node-types.json}.
 */
public enum GoNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ARGUMENTS("arguments"),
    BODY("body"),
    CAPACITY("capacity"),
    CHANNEL("channel"),
    COMMUNICATION("communication"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    ELEMENT("element"),
    END("end"),
    FIELD("field"),
    FUNCTION("function"),
    INDEX("index"),
    INITIALIZER("initializer"),
    KEY("key"),
    LABEL("label"),
    LEFT("left"),
    LENGTH("length"),
    NAME("name"),
    OPERAND("operand"),
    OPERATOR("operator"),
    PACKAGE_("package"),
    PARAMETERS("parameters"),
    PATH("path"),
    RECEIVER("receiver"),
    RESULT("result"),
    RIGHT("right"),
    START("start"),
    TAG("tag"),
    TYPE("type"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_PARAMETERS("type_parameters"),
    UPDATE("update"),
    VALUE("value");

    private final @Nullable String name;

    GoNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static GoNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        GoNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, GoNodeField> LOOKUP = initLookup();

    private static Map<String, GoNodeField> initLookup() {
        HashMap<String, GoNodeField> m = new HashMap<>();
        for (GoNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
