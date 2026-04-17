package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code zig} from tree-sitter {@code node-types.json}.
 */
public enum ZigNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    BODY("body"),
    CONDITION("condition"),
    ERROR("error"),
    FUNCTION("function"),
    INDEX("index"),
    LEFT("left"),
    MEMBER("member"),
    NAME("name"),
    OBJECT("object"),
    OK("ok"),
    OPERATOR("operator"),
    RIGHT("right"),
    SENTINEL("sentinel"),
    TYPE("type");

    private final @Nullable String name;

    ZigNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static ZigNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        ZigNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, ZigNodeField> LOOKUP = initLookup();

    private static Map<String, ZigNodeField> initLookup() {
        HashMap<String, ZigNodeField> m = new HashMap<>();
        for (ZigNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
