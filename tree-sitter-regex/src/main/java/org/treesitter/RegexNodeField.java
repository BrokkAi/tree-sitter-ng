package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code regex} from tree-sitter {@code node-types.json}.
 */
public enum RegexNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    RegexNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static RegexNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        RegexNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, RegexNodeField> LOOKUP = initLookup();

    private static Map<String, RegexNodeField> initLookup() {
        HashMap<String, RegexNodeField> m = new HashMap<>();
        for (RegexNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
