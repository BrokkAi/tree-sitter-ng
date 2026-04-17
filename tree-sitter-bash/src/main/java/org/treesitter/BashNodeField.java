package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code bash} from tree-sitter {@code node-types.json}.
 */
public enum BashNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    BODY("body"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    DESCRIPTOR("descriptor"),
    DESTINATION("destination"),
    FALLTHROUGH("fallthrough"),
    INDEX("index"),
    INITIALIZER("initializer"),
    LEFT("left"),
    NAME("name"),
    OPERATOR("operator"),
    REDIRECT("redirect"),
    RIGHT("right"),
    TERMINATION("termination"),
    UPDATE("update"),
    VALUE("value"),
    VARIABLE("variable");

    private final @Nullable String name;

    BashNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static BashNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        BashNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, BashNodeField> LOOKUP = initLookup();

    private static Map<String, BashNodeField> initLookup() {
        HashMap<String, BashNodeField> m = new HashMap<>();
        for (BashNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
