package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code angular} from tree-sitter {@code node-types.json}.
 */
public enum AngularNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ANIMATION_BINDING("animation_binding"),
    ARGUMENTS("arguments"),
    ARRAY("array"),
    ASSIGNMENT_EXPRESSION("assignment_expression"),
    ATTRIBUTE("attribute"),
    ATTRIBUTE_NAME("attribute_name"),
    ATTRIBUTE_VALUE("attribute_value"),
    BINARY_EXPRESSION("binary_expression"),
    BINDING_NAME("binding_name"),
    BRACKET_EXPRESSION("bracket_expression"),
    CALL_EXPRESSION("call_expression"),
    CASE_STATEMENT("case_statement"),
    CLASS_BINDING("class_binding"),
    CLASS_NAME("class_name"),
    COALESCING_OPERATOR("coalescing_operator"),
    COMMENT("comment"),
    CONCATENATION_EXPRESSION("concatenation_expression"),
    CONDITIONAL_EXPRESSION("conditional_expression"),
    CONDITIONAL_OPERATOR("conditional_operator"),
    CONTROL_KEYWORD("control_keyword"),
    DEFAULT_STATEMENT("default_statement"),
    DEFER_STATEMENT("defer_statement"),
    DEFER_TRIGGER("defer_trigger"),
    DEFER_TRIGGER_CONDITION("defer_trigger_condition"),
    DOCTYPE("doctype"),
    DOCUMENT("document"),
    ELEMENT("element"),
    ELSE_IF_STATEMENT("else_if_statement"),
    ELSE_STATEMENT("else_statement"),
    EMPTY_STATEMENT("empty_statement"),
    END_TAG("end_tag"),
    ENTITY("entity"),
    ERRONEOUS_END_TAG("erroneous_end_tag"),
    ERRONEOUS_END_TAG_NAME("erroneous_end_tag_name"),
    ERROR_STATEMENT("error_statement"),
    EVENT_BINDING("event_binding"),
    EXPRESSION("expression"),
    FOR_DECLARATION("for_declaration"),
    FOR_REFERENCE("for_reference"),
    FOR_STATEMENT("for_statement"),
    GROUP("group"),
    ICU_CASE("icu_case"),
    ICU_CATEGORY("icu_category"),
    ICU_CLAUSE("icu_clause"),
    ICU_EXPRESSION("icu_expression"),
    IDENTIFIER("identifier"),
    IF_CONDITION("if_condition"),
    IF_REFERENCE("if_reference"),
    IF_STATEMENT("if_statement"),
    INTERPOLATION("interpolation"),
    LET_STATEMENT("let_statement"),
    LOADING_CONDITION("loading_condition"),
    LOADING_STATEMENT("loading_statement"),
    MEMBER_EXPRESSION("member_expression"),
    NULLISH_COALESCING_EXPRESSION("nullish_coalescing_expression"),
    NUMBER("number"),
    OBJECT("object"),
    PAIR("pair"),
    PIPE_ARGUMENTS("pipe_arguments"),
    PIPE_CALL("pipe_call"),
    PIPE_OPERATOR("pipe_operator"),
    PIPE_SEQUENCE("pipe_sequence"),
    PLACEHOLDER_MINIMUM("placeholder_minimum"),
    PLACEHOLDER_STATEMENT("placeholder_statement"),
    PREFETCH_KEYWORD("prefetch_keyword"),
    PROPERTY_BINDING("property_binding"),
    QUOTED_ATTRIBUTE_VALUE("quoted_attribute_value"),
    RAW_TEXT("raw_text"),
    REGULAR_EXPRESSION("regular_expression"),
    REGULAR_EXPRESSION_FLAGS("regular_expression_flags"),
    REGULAR_EXPRESSION_PATTERN("regular_expression_pattern"),
    SCRIPT_ELEMENT("script_element"),
    SELF_CLOSING_TAG("self_closing_tag"),
    SPECIAL_KEYWORD("special_keyword"),
    SPREAD("spread"),
    START_TAG("start_tag"),
    STATEMENT_BLOCK("statement_block"),
    STRING("string"),
    STRUCTURAL_ASSIGNMENT("structural_assignment"),
    STRUCTURAL_DECLARATION("structural_declaration"),
    STRUCTURAL_DIRECTIVE("structural_directive"),
    STRUCTURAL_EXPRESSION("structural_expression"),
    STYLE_ELEMENT("style_element"),
    STYLE_UNIT("style_unit"),
    SWITCH_BODY("switch_body"),
    SWITCH_STATEMENT("switch_statement"),
    TAG_NAME("tag_name"),
    TEMPLATE_CHARS("template_chars"),
    TEMPLATE_STRING("template_string"),
    TEMPLATE_SUBSTITUTION("template_substitution"),
    TERNARY_EXPRESSION("ternary_expression"),
    TERNARY_OPERATOR("ternary_operator"),
    TEXT("text"),
    TIMED_EXPRESSION("timed_expression"),
    TWO_WAY_BINDING("two_way_binding"),
    UNARY_EXPRESSION("unary_expression"),
    UNARY_OPERATOR("unary_operator"),
    UNIT("unit");

    private final @Nullable String type;

    AngularNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static AngularNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static AngularNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        AngularNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, AngularNodeType> LOOKUP = initLookup();

    private static Map<String, AngularNodeType> initLookup() {
        HashMap<String, AngularNodeType> m = new HashMap<>();
        for (AngularNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
