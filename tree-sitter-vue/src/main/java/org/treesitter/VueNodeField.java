package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code vue} from tree-sitter {@code node-types.json}.
 */
public enum VueNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    VueNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static VueNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        VueNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, VueNodeField> LOOKUP = initLookup();

    private static Map<String, VueNodeField> initLookup() {
        HashMap<String, VueNodeField> m = new HashMap<>();
        for (VueNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
