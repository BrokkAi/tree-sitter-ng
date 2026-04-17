package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code json} from tree-sitter {@code node-types.json}.
 */
public enum JsonNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ARRAY("array"),
    COMMENT("comment"),
    DOCUMENT("document"),
    ESCAPE_SEQUENCE("escape_sequence"),
    FALSE("false"),
    NULL("null"),
    NUMBER("number"),
    OBJECT("object"),
    PAIR("pair"),
    STRING("string"),
    STRING_CONTENT("string_content"),
    TRUE("true"),
    VALUE("_value");

    public static final Set<JsonNodeType> VALUE_SET = Set.of(ARRAY, FALSE, NULL, NUMBER, OBJECT, STRING, TRUE);

    private final @Nullable String type;

    JsonNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static JsonNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static JsonNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        JsonNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, JsonNodeType> LOOKUP = initLookup();

    private static Map<String, JsonNodeType> initLookup() {
        HashMap<String, JsonNodeType> m = new HashMap<>();
        for (JsonNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
