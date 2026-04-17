package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code json} from tree-sitter {@code node-types.json}.
 */
public enum JsonNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    KEY("key"),
    VALUE("value");

    private final @Nullable String name;

    JsonNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static JsonNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        JsonNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, JsonNodeField> LOOKUP = initLookup();

    private static Map<String, JsonNodeField> initLookup() {
        HashMap<String, JsonNodeField> m = new HashMap<>();
        for (JsonNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
