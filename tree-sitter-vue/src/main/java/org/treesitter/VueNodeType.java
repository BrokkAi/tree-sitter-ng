package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code vue} from tree-sitter {@code node-types.json}.
 */
public enum VueNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ATTRIBUTE("attribute"),
    ATTRIBUTE_NAME("attribute_name"),
    ATTRIBUTE_VALUE("attribute_value"),
    COMMENT("comment"),
    DIRECTIVE_ATTRIBUTE("directive_attribute"),
    DIRECTIVE_MODIFIER("directive_modifier"),
    DIRECTIVE_MODIFIERS("directive_modifiers"),
    DIRECTIVE_NAME("directive_name"),
    DIRECTIVE_VALUE("directive_value"),
    DOCTYPE("doctype"),
    DOCUMENT("document"),
    DYNAMIC_DIRECTIVE_INNER_VALUE("dynamic_directive_inner_value"),
    DYNAMIC_DIRECTIVE_VALUE("dynamic_directive_value"),
    ELEMENT("element"),
    END_TAG("end_tag"),
    ENTITY("entity"),
    ERRONEOUS_END_TAG("erroneous_end_tag"),
    ERRONEOUS_END_TAG_NAME("erroneous_end_tag_name"),
    INTERPOLATION("interpolation"),
    QUOTED_ATTRIBUTE_VALUE("quoted_attribute_value"),
    RAW_TEXT("raw_text"),
    SCRIPT_ELEMENT("script_element"),
    SELF_CLOSING_TAG("self_closing_tag"),
    START_TAG("start_tag"),
    STYLE_ELEMENT("style_element"),
    TAG_NAME("tag_name"),
    TEMPLATE_ELEMENT("template_element"),
    TEXT("text");

    private final @Nullable String type;

    VueNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static VueNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static VueNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        VueNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, VueNodeType> LOOKUP = initLookup();

    private static Map<String, VueNodeType> initLookup() {
        HashMap<String, VueNodeType> m = new HashMap<>();
        for (VueNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
