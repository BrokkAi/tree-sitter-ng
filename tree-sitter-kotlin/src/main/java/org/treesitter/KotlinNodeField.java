package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code kotlin} from tree-sitter {@code node-types.json}.
 */
public enum KotlinNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    KotlinNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static KotlinNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        KotlinNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, KotlinNodeField> LOOKUP = initLookup();

    private static Map<String, KotlinNodeField> initLookup() {
        HashMap<String, KotlinNodeField> m = new HashMap<>();
        for (KotlinNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
