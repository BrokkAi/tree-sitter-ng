package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code css} from tree-sitter {@code node-types.json}.
 */
public enum CssNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    CssNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static CssNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        CssNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, CssNodeField> LOOKUP = initLookup();

    private static Map<String, CssNodeField> initLookup() {
        HashMap<String, CssNodeField> m = new HashMap<>();
        for (CssNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
