package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code julia} from tree-sitter {@code node-types.json}.
 */
public enum JuliaNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALTERNATIVE("alternative"),
    CONDITION("condition"),
    NAME("name"),
    PREFIX("prefix"),
    SUFFIX("suffix"),
    VALUE("value");

    private final @Nullable String name;

    JuliaNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static JuliaNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        JuliaNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, JuliaNodeField> LOOKUP = initLookup();

    private static Map<String, JuliaNodeField> initLookup() {
        HashMap<String, JuliaNodeField> m = new HashMap<>();
        for (JuliaNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
