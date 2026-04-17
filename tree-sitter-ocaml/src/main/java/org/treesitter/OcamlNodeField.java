package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code ocaml} from tree-sitter {@code node-types.json}.
 */
public enum OcamlNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ARGUMENT("argument"),
    BODY("body"),
    CLASS_("class"),
    CONDITION("condition"),
    CONTINUATION("continuation"),
    EFFECT("effect"),
    FROM("from"),
    FUNCTION("function"),
    FUNCTOR("functor"),
    LEFT("left"),
    NAME("name"),
    OPERATOR("operator"),
    PATTERN("pattern"),
    RIGHT("right"),
    TO("to");

    private final @Nullable String name;

    OcamlNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static OcamlNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        OcamlNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, OcamlNodeField> LOOKUP = initLookup();

    private static Map<String, OcamlNodeField> initLookup() {
        HashMap<String, OcamlNodeField> m = new HashMap<>();
        for (OcamlNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
