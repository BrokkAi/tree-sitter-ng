package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code agda} from tree-sitter {@code node-types.json}.
 */
public enum AgdaNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    AgdaNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static AgdaNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        AgdaNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, AgdaNodeField> LOOKUP = initLookup();

    private static Map<String, AgdaNodeField> initLookup() {
        HashMap<String, AgdaNodeField> m = new HashMap<>();
        for (AgdaNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
