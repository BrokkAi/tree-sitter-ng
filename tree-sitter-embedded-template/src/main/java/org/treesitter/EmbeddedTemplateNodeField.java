package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code embedded-template} from tree-sitter {@code node-types.json}.
 */
public enum EmbeddedTemplateNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    EmbeddedTemplateNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static EmbeddedTemplateNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        EmbeddedTemplateNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, EmbeddedTemplateNodeField> LOOKUP = initLookup();

    private static Map<String, EmbeddedTemplateNodeField> initLookup() {
        HashMap<String, EmbeddedTemplateNodeField> m = new HashMap<>();
        for (EmbeddedTemplateNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
