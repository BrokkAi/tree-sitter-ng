package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code go} from tree-sitter {@code node-types.json}.
 */
public enum GoNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ARGUMENT_LIST("argument_list"),
    ARRAY_TYPE("array_type"),
    ASSIGNMENT_STATEMENT("assignment_statement"),
    BINARY_EXPRESSION("binary_expression"),
    BLANK_IDENTIFIER("blank_identifier"),
    BLOCK("block"),
    BREAK_STATEMENT("break_statement"),
    CALL_EXPRESSION("call_expression"),
    CHANNEL_TYPE("channel_type"),
    COMMENT("comment"),
    COMMUNICATION_CASE("communication_case"),
    COMPOSITE_LITERAL("composite_literal"),
    CONST_DECLARATION("const_declaration"),
    CONST_SPEC("const_spec"),
    CONTINUE_STATEMENT("continue_statement"),
    DEC_STATEMENT("dec_statement"),
    DEFAULT_CASE("default_case"),
    DEFER_STATEMENT("defer_statement"),
    DOT("dot"),
    EMPTY_STATEMENT("empty_statement"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXPRESSION("_expression"),
    EXPRESSION_CASE("expression_case"),
    EXPRESSION_LIST("expression_list"),
    EXPRESSION_STATEMENT("expression_statement"),
    EXPRESSION_SWITCH_STATEMENT("expression_switch_statement"),
    FALLTHROUGH_STATEMENT("fallthrough_statement"),
    FALSE("false"),
    FIELD_DECLARATION("field_declaration"),
    FIELD_DECLARATION_LIST("field_declaration_list"),
    FIELD_IDENTIFIER("field_identifier"),
    FLOAT_LITERAL("float_literal"),
    FOR_CLAUSE("for_clause"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DECLARATION("function_declaration"),
    FUNCTION_TYPE("function_type"),
    FUNC_LITERAL("func_literal"),
    GENERIC_TYPE("generic_type"),
    GOTO_STATEMENT("goto_statement"),
    GO_STATEMENT("go_statement"),
    IDENTIFIER("identifier"),
    IF_STATEMENT("if_statement"),
    IMAGINARY_LITERAL("imaginary_literal"),
    IMPLICIT_LENGTH_ARRAY_TYPE("implicit_length_array_type"),
    IMPORT_DECLARATION("import_declaration"),
    IMPORT_SPEC("import_spec"),
    IMPORT_SPEC_LIST("import_spec_list"),
    INC_STATEMENT("inc_statement"),
    INDEX_EXPRESSION("index_expression"),
    INTERFACE_TYPE("interface_type"),
    INTERPRETED_STRING_LITERAL("interpreted_string_literal"),
    INTERPRETED_STRING_LITERAL_CONTENT("interpreted_string_literal_content"),
    INT_LITERAL("int_literal"),
    IOTA("iota"),
    KEYED_ELEMENT("keyed_element"),
    LABELED_STATEMENT("labeled_statement"),
    LABEL_NAME("label_name"),
    LITERAL_ELEMENT("literal_element"),
    LITERAL_VALUE("literal_value"),
    MAP_TYPE("map_type"),
    METHOD_DECLARATION("method_declaration"),
    METHOD_ELEM("method_elem"),
    NEGATED_TYPE("negated_type"),
    NIL("nil"),
    PACKAGE_CLAUSE("package_clause"),
    PACKAGE_IDENTIFIER("package_identifier"),
    PARAMETER_DECLARATION("parameter_declaration"),
    PARAMETER_LIST("parameter_list"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PARENTHESIZED_TYPE("parenthesized_type"),
    POINTER_TYPE("pointer_type"),
    QUALIFIED_TYPE("qualified_type"),
    RANGE_CLAUSE("range_clause"),
    RAW_STRING_LITERAL("raw_string_literal"),
    RAW_STRING_LITERAL_CONTENT("raw_string_literal_content"),
    RECEIVE_STATEMENT("receive_statement"),
    RETURN_STATEMENT("return_statement"),
    RUNE_LITERAL("rune_literal"),
    SELECTOR_EXPRESSION("selector_expression"),
    SELECT_STATEMENT("select_statement"),
    SEND_STATEMENT("send_statement"),
    SHORT_VAR_DECLARATION("short_var_declaration"),
    SIMPLE_STATEMENT("_simple_statement"),
    SIMPLE_TYPE("_simple_type"),
    SLICE_EXPRESSION("slice_expression"),
    SLICE_TYPE("slice_type"),
    SOURCE_FILE("source_file"),
    STATEMENT("_statement"),
    STATEMENT_LIST("statement_list"),
    STRUCT_TYPE("struct_type"),
    TRUE("true"),
    TYPE("_type"),
    TYPE_ALIAS("type_alias"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_ASSERTION_EXPRESSION("type_assertion_expression"),
    TYPE_CASE("type_case"),
    TYPE_CONSTRAINT("type_constraint"),
    TYPE_CONVERSION_EXPRESSION("type_conversion_expression"),
    TYPE_DECLARATION("type_declaration"),
    TYPE_ELEM("type_elem"),
    TYPE_IDENTIFIER("type_identifier"),
    TYPE_INSTANTIATION_EXPRESSION("type_instantiation_expression"),
    TYPE_PARAMETER_DECLARATION("type_parameter_declaration"),
    TYPE_PARAMETER_LIST("type_parameter_list"),
    TYPE_SPEC("type_spec"),
    TYPE_SWITCH_STATEMENT("type_switch_statement"),
    UNARY_EXPRESSION("unary_expression"),
    VARIADIC_ARGUMENT("variadic_argument"),
    VARIADIC_PARAMETER_DECLARATION("variadic_parameter_declaration"),
    VAR_DECLARATION("var_declaration"),
    VAR_SPEC("var_spec"),
    VAR_SPEC_LIST("var_spec_list");

    public static final Set<GoNodeType> EXPRESSION_SET = Set.of(
            BINARY_EXPRESSION,
            CALL_EXPRESSION,
            COMPOSITE_LITERAL,
            FALSE,
            FLOAT_LITERAL,
            FUNC_LITERAL,
            IDENTIFIER,
            IMAGINARY_LITERAL,
            INDEX_EXPRESSION,
            INTERPRETED_STRING_LITERAL,
            INT_LITERAL,
            IOTA,
            NIL,
            PARENTHESIZED_EXPRESSION,
            RAW_STRING_LITERAL,
            RUNE_LITERAL,
            SELECTOR_EXPRESSION,
            SLICE_EXPRESSION,
            TRUE,
            TYPE_ASSERTION_EXPRESSION,
            TYPE_CONVERSION_EXPRESSION,
            TYPE_INSTANTIATION_EXPRESSION,
            UNARY_EXPRESSION);
    public static final Set<GoNodeType> SIMPLE_STATEMENT_SET = Set.of(
            ASSIGNMENT_STATEMENT,
            DEC_STATEMENT,
            EXPRESSION_STATEMENT,
            INC_STATEMENT,
            SEND_STATEMENT,
            SHORT_VAR_DECLARATION);
    public static final Set<GoNodeType> SIMPLE_TYPE_SET = Set.of(
            ARRAY_TYPE,
            CHANNEL_TYPE,
            FUNCTION_TYPE,
            GENERIC_TYPE,
            INTERFACE_TYPE,
            MAP_TYPE,
            NEGATED_TYPE,
            POINTER_TYPE,
            QUALIFIED_TYPE,
            SLICE_TYPE,
            STRUCT_TYPE,
            TYPE_IDENTIFIER);
    public static final Set<GoNodeType> STATEMENT_SET = Set.of(
            BLOCK,
            BREAK_STATEMENT,
            CONST_DECLARATION,
            CONTINUE_STATEMENT,
            DEFER_STATEMENT,
            EMPTY_STATEMENT,
            EXPRESSION_SWITCH_STATEMENT,
            FALLTHROUGH_STATEMENT,
            FOR_STATEMENT,
            GOTO_STATEMENT,
            GO_STATEMENT,
            IF_STATEMENT,
            LABELED_STATEMENT,
            RETURN_STATEMENT,
            SELECT_STATEMENT,
            SIMPLE_STATEMENT,
            TYPE_DECLARATION,
            TYPE_SWITCH_STATEMENT,
            VAR_DECLARATION);
    public static final Set<GoNodeType> TYPE_SET = Set.of(PARENTHESIZED_TYPE, SIMPLE_TYPE);

    private final @Nullable String type;

    GoNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static GoNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static GoNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        GoNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, GoNodeType> LOOKUP = initLookup();

    private static Map<String, GoNodeType> initLookup() {
        HashMap<String, GoNodeType> m = new HashMap<>();
        for (GoNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
