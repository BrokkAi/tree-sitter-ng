package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code embedded-template} from tree-sitter {@code node-types.json}.
 */
public enum EmbeddedTemplateNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    CODE("code"),
    COMMENT("comment"),
    COMMENT_DIRECTIVE("comment_directive"),
    CONTENT("content"),
    DIRECTIVE("directive"),
    GRAPHQL_DIRECTIVE("graphql_directive"),
    OUTPUT_DIRECTIVE("output_directive"),
    TEMPLATE("template");

    private final @Nullable String type;

    EmbeddedTemplateNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static EmbeddedTemplateNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static EmbeddedTemplateNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        EmbeddedTemplateNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, EmbeddedTemplateNodeType> LOOKUP = initLookup();

    private static Map<String, EmbeddedTemplateNodeType> initLookup() {
        HashMap<String, EmbeddedTemplateNodeType> m = new HashMap<>();
        for (EmbeddedTemplateNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
