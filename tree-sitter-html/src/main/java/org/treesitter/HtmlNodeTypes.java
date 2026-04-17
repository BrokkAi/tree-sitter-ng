package org.treesitter;

/**
 * Node type constants for {@code html} from tree-sitter {@code node-types.json}.
 */
public final class HtmlNodeTypes {
    private HtmlNodeTypes() {}

    public static final String ATTRIBUTE = "attribute";
    public static final String ATTRIBUTE_NAME = "attribute_name";
    public static final String ATTRIBUTE_VALUE = "attribute_value";
    public static final String COMMENT = "comment";
    public static final String DOCTYPE = "doctype";
    public static final String DOCUMENT = "document";
    public static final String ELEMENT = "element";
    public static final String END_TAG = "end_tag";
    public static final String ENTITY = "entity";
    public static final String ERRONEOUS_END_TAG = "erroneous_end_tag";
    public static final String ERRONEOUS_END_TAG_NAME = "erroneous_end_tag_name";
    public static final String QUOTED_ATTRIBUTE_VALUE = "quoted_attribute_value";
    public static final String RAW_TEXT = "raw_text";
    public static final String SCRIPT_ELEMENT = "script_element";
    public static final String SELF_CLOSING_TAG = "self_closing_tag";
    public static final String START_TAG = "start_tag";
    public static final String STYLE_ELEMENT = "style_element";
    public static final String TAG_NAME = "tag_name";
    public static final String TEXT = "text";
}
