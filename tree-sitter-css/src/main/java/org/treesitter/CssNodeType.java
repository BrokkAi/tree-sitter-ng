package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code css} from tree-sitter {@code node-types.json}.
 */
public enum CssNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ADJACENT_SIBLING_SELECTOR("adjacent_sibling_selector"),
    ARGUMENTS("arguments"),
    ATTRIBUTE_NAME("attribute_name"),
    ATTRIBUTE_SELECTOR("attribute_selector"),
    AT_KEYWORD("at_keyword"),
    AT_RULE("at_rule"),
    BINARY_EXPRESSION("binary_expression"),
    BINARY_QUERY("binary_query"),
    BLOCK("block"),
    CALL_EXPRESSION("call_expression"),
    CHARSET_STATEMENT("charset_statement"),
    CHILD_SELECTOR("child_selector"),
    CLASS_NAME("class_name"),
    CLASS_SELECTOR("class_selector"),
    COLOR_VALUE("color_value"),
    COMMENT("comment"),
    DECLARATION("declaration"),
    DESCENDANT_SELECTOR("descendant_selector"),
    ESCAPE_SEQUENCE("escape_sequence"),
    FEATURE_NAME("feature_name"),
    FEATURE_QUERY("feature_query"),
    FLOAT_VALUE("float_value"),
    FROM("from"),
    FUNCTION_NAME("function_name"),
    GRID_VALUE("grid_value"),
    IDENTIFIER("identifier"),
    ID_NAME("id_name"),
    ID_SELECTOR("id_selector"),
    IMPORTANT("important"),
    IMPORTANT_VALUE("important_value"),
    IMPORT_STATEMENT("import_statement"),
    INTEGER_VALUE("integer_value"),
    JS_COMMENT("js_comment"),
    KEYFRAMES_NAME("keyframes_name"),
    KEYFRAMES_STATEMENT("keyframes_statement"),
    KEYFRAME_BLOCK("keyframe_block"),
    KEYFRAME_BLOCK_LIST("keyframe_block_list"),
    KEYWORD_QUERY("keyword_query"),
    MEDIA_STATEMENT("media_statement"),
    NAMESPACE_NAME("namespace_name"),
    NAMESPACE_SELECTOR("namespace_selector"),
    NAMESPACE_STATEMENT("namespace_statement"),
    NESTING_SELECTOR("nesting_selector"),
    PARENTHESIZED_QUERY("parenthesized_query"),
    PARENTHESIZED_VALUE("parenthesized_value"),
    PLAIN_VALUE("plain_value"),
    POSTCSS_STATEMENT("postcss_statement"),
    PROPERTY_NAME("property_name"),
    PSEUDO_CLASS_SELECTOR("pseudo_class_selector"),
    PSEUDO_ELEMENT_SELECTOR("pseudo_element_selector"),
    RULE_SET("rule_set"),
    SCOPE_STATEMENT("scope_statement"),
    SELECTORS("selectors"),
    SELECTOR_QUERY("selector_query"),
    SIBLING_SELECTOR("sibling_selector"),
    STRING_CONTENT("string_content"),
    STRING_VALUE("string_value"),
    STYLESHEET("stylesheet"),
    SUPPORTS_STATEMENT("supports_statement"),
    TAG_NAME("tag_name"),
    TO("to"),
    UNARY_QUERY("unary_query"),
    UNIT("unit"),
    UNIVERSAL_SELECTOR("universal_selector");

    private final @Nullable String type;

    CssNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static CssNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static CssNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        CssNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, CssNodeType> LOOKUP = initLookup();

    private static Map<String, CssNodeType> initLookup() {
        HashMap<String, CssNodeType> m = new HashMap<>();
        for (CssNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
