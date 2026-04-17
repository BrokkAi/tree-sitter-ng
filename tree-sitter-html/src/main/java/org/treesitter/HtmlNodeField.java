package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code html} from tree-sitter {@code node-types.json}.
 */
public enum HtmlNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    HtmlNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static HtmlNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        HtmlNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, HtmlNodeField> LOOKUP = initLookup();

    private static Map<String, HtmlNodeField> initLookup() {
        HashMap<String, HtmlNodeField> m = new HashMap<>();
        for (HtmlNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
