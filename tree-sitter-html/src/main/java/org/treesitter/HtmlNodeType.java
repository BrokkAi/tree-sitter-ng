package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code html} from tree-sitter {@code node-types.json}.
 */
public enum HtmlNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ATTRIBUTE("attribute"),
    ATTRIBUTE_NAME("attribute_name"),
    ATTRIBUTE_VALUE("attribute_value"),
    COMMENT("comment"),
    DOCTYPE("doctype"),
    DOCUMENT("document"),
    ELEMENT("element"),
    END_TAG("end_tag"),
    ENTITY("entity"),
    ERRONEOUS_END_TAG("erroneous_end_tag"),
    ERRONEOUS_END_TAG_NAME("erroneous_end_tag_name"),
    QUOTED_ATTRIBUTE_VALUE("quoted_attribute_value"),
    RAW_TEXT("raw_text"),
    SCRIPT_ELEMENT("script_element"),
    SELF_CLOSING_TAG("self_closing_tag"),
    START_TAG("start_tag"),
    STYLE_ELEMENT("style_element"),
    TAG_NAME("tag_name"),
    TEXT("text");

    private final @Nullable String type;

    HtmlNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static HtmlNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static HtmlNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        HtmlNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, HtmlNodeType> LOOKUP = initLookup();

    private static Map<String, HtmlNodeType> initLookup() {
        HashMap<String, HtmlNodeType> m = new HashMap<>();
        for (HtmlNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
