package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code go} from tree-sitter {@code node-types.json}.
 */
public final class GoNodeTypes {
    private GoNodeTypes() {}

    public static final String ARGUMENT_LIST = "argument_list";
    public static final String ARRAY_TYPE = "array_type";
    public static final String ASSIGNMENT_STATEMENT = "assignment_statement";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BLANK_IDENTIFIER = "blank_identifier";
    public static final String BLOCK = "block";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CHANNEL_TYPE = "channel_type";
    public static final String COMMENT = "comment";
    public static final String COMMUNICATION_CASE = "communication_case";
    public static final String COMPOSITE_LITERAL = "composite_literal";
    public static final String CONST_DECLARATION = "const_declaration";
    public static final String CONST_SPEC = "const_spec";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String DEC_STATEMENT = "dec_statement";
    public static final String DEFAULT_CASE = "default_case";
    public static final String DEFER_STATEMENT = "defer_statement";
    public static final String DOT = "dot";
    public static final String EMPTY_STATEMENT = "empty_statement";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPRESSION = "_expression";
    public static final String EXPRESSION_CASE = "expression_case";
    public static final String EXPRESSION_LIST = "expression_list";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String EXPRESSION_SWITCH_STATEMENT = "expression_switch_statement";
    public static final String FALLTHROUGH_STATEMENT = "fallthrough_statement";
    public static final String FALSE = "false";
    public static final String FIELD_DECLARATION = "field_declaration";
    public static final String FIELD_DECLARATION_LIST = "field_declaration_list";
    public static final String FIELD_IDENTIFIER = "field_identifier";
    public static final String FLOAT_LITERAL = "float_literal";
    public static final String FOR_CLAUSE = "for_clause";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_DECLARATION = "function_declaration";
    public static final String FUNCTION_TYPE = "function_type";
    public static final String FUNC_LITERAL = "func_literal";
    public static final String GENERIC_TYPE = "generic_type";
    public static final String GOTO_STATEMENT = "goto_statement";
    public static final String GO_STATEMENT = "go_statement";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IMAGINARY_LITERAL = "imaginary_literal";
    public static final String IMPLICIT_LENGTH_ARRAY_TYPE = "implicit_length_array_type";
    public static final String IMPORT_DECLARATION = "import_declaration";
    public static final String IMPORT_SPEC = "import_spec";
    public static final String IMPORT_SPEC_LIST = "import_spec_list";
    public static final String INC_STATEMENT = "inc_statement";
    public static final String INDEX_EXPRESSION = "index_expression";
    public static final String INTERFACE_TYPE = "interface_type";
    public static final String INTERPRETED_STRING_LITERAL = "interpreted_string_literal";
    public static final String INTERPRETED_STRING_LITERAL_CONTENT = "interpreted_string_literal_content";
    public static final String INT_LITERAL = "int_literal";
    public static final String IOTA = "iota";
    public static final String KEYED_ELEMENT = "keyed_element";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LABEL_NAME = "label_name";
    public static final String LITERAL_ELEMENT = "literal_element";
    public static final String LITERAL_VALUE = "literal_value";
    public static final String MAP_TYPE = "map_type";
    public static final String METHOD_DECLARATION = "method_declaration";
    public static final String METHOD_ELEM = "method_elem";
    public static final String NEGATED_TYPE = "negated_type";
    public static final String NIL = "nil";
    public static final String PACKAGE_CLAUSE = "package_clause";
    public static final String PACKAGE_IDENTIFIER = "package_identifier";
    public static final String PARAMETER_DECLARATION = "parameter_declaration";
    public static final String PARAMETER_LIST = "parameter_list";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PARENTHESIZED_TYPE = "parenthesized_type";
    public static final String POINTER_TYPE = "pointer_type";
    public static final String QUALIFIED_TYPE = "qualified_type";
    public static final String RANGE_CLAUSE = "range_clause";
    public static final String RAW_STRING_LITERAL = "raw_string_literal";
    public static final String RAW_STRING_LITERAL_CONTENT = "raw_string_literal_content";
    public static final String RECEIVE_STATEMENT = "receive_statement";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String RUNE_LITERAL = "rune_literal";
    public static final String SELECTOR_EXPRESSION = "selector_expression";
    public static final String SELECT_STATEMENT = "select_statement";
    public static final String SEND_STATEMENT = "send_statement";
    public static final String SHORT_VAR_DECLARATION = "short_var_declaration";
    public static final String SIMPLE_STATEMENT = "_simple_statement";
    public static final String SIMPLE_TYPE = "_simple_type";
    public static final String SLICE_EXPRESSION = "slice_expression";
    public static final String SLICE_TYPE = "slice_type";
    public static final String SOURCE_FILE = "source_file";
    public static final String STATEMENT = "_statement";
    public static final String STATEMENT_LIST = "statement_list";
    public static final String STRUCT_TYPE = "struct_type";
    public static final String TRUE = "true";
    public static final String TYPE = "_type";
    public static final String TYPE_ALIAS = "type_alias";
    public static final String TYPE_ARGUMENTS = "type_arguments";
    public static final String TYPE_ASSERTION_EXPRESSION = "type_assertion_expression";
    public static final String TYPE_CASE = "type_case";
    public static final String TYPE_CONSTRAINT = "type_constraint";
    public static final String TYPE_CONVERSION_EXPRESSION = "type_conversion_expression";
    public static final String TYPE_DECLARATION = "type_declaration";
    public static final String TYPE_ELEM = "type_elem";
    public static final String TYPE_IDENTIFIER = "type_identifier";
    public static final String TYPE_INSTANTIATION_EXPRESSION = "type_instantiation_expression";
    public static final String TYPE_PARAMETER_DECLARATION = "type_parameter_declaration";
    public static final String TYPE_PARAMETER_LIST = "type_parameter_list";
    public static final String TYPE_SPEC = "type_spec";
    public static final String TYPE_SWITCH_STATEMENT = "type_switch_statement";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String VARIADIC_ARGUMENT = "variadic_argument";
    public static final String VARIADIC_PARAMETER_DECLARATION = "variadic_parameter_declaration";
    public static final String VAR_DECLARATION = "var_declaration";
    public static final String VAR_SPEC = "var_spec";
    public static final String VAR_SPEC_LIST = "var_spec_list";

    public static final Set<String> EXPRESSION_SET = Set.of(
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
    public static final Set<String> SIMPLE_STATEMENT_SET = Set.of(
            ASSIGNMENT_STATEMENT,
            DEC_STATEMENT,
            EXPRESSION_STATEMENT,
            INC_STATEMENT,
            SEND_STATEMENT,
            SHORT_VAR_DECLARATION);
    public static final Set<String> SIMPLE_TYPE_SET = Set.of(
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
    public static final Set<String> STATEMENT_SET = Set.of(
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
    public static final Set<String> TYPE_SET = Set.of(PARENTHESIZED_TYPE, SIMPLE_TYPE);
}
