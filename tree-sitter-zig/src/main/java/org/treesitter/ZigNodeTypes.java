package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code zig} from tree-sitter {@code node-types.json}.
 */
public final class ZigNodeTypes {
    private ZigNodeTypes() {}

    public static final String ADDRESS_SPACE = "address_space";
    public static final String ANONYMOUS_STRUCT_INITIALIZER = "anonymous_struct_initializer";
    public static final String ANYFRAME_TYPE = "anyframe_type";
    public static final String ARGUMENTS = "arguments";
    public static final String ARRAY_TYPE = "array_type";
    public static final String ASM_CLOBBERS = "asm_clobbers";
    public static final String ASM_EXPRESSION = "asm_expression";
    public static final String ASM_INPUT = "asm_input";
    public static final String ASM_INPUT_ITEM = "asm_input_item";
    public static final String ASM_OUTPUT = "asm_output";
    public static final String ASM_OUTPUT_ITEM = "asm_output_item";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String ASYNC_EXPRESSION = "async_expression";
    public static final String AWAIT_EXPRESSION = "await_expression";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BLOCK = "block";
    public static final String BLOCK_EXPRESSION = "block_expression";
    public static final String BLOCK_LABEL = "block_label";
    public static final String BOOLEAN_ = "boolean";
    public static final String BREAK_EXPRESSION = "break_expression";
    public static final String BREAK_LABEL = "break_label";
    public static final String BUILTIN_FUNCTION = "builtin_function";
    public static final String BUILTIN_IDENTIFIER = "builtin_identifier";
    public static final String BUILTIN_TYPE = "builtin_type";
    public static final String BYTE_ALIGNMENT = "byte_alignment";
    public static final String CALLING_CONVENTION = "calling_convention";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CATCH_EXPRESSION = "catch_expression";
    public static final String CHARACTER = "character";
    public static final String CHARACTER_CONTENT = "character_content";
    public static final String COMMENT = "comment";
    public static final String COMPTIME_DECLARATION = "comptime_declaration";
    public static final String COMPTIME_EXPRESSION = "comptime_expression";
    public static final String COMPTIME_STATEMENT = "comptime_statement";
    public static final String COMPTIME_TYPE_EXPRESSION = "comptime_type_expression";
    public static final String CONTAINER_FIELD = "container_field";
    public static final String CONTINUE_EXPRESSION = "continue_expression";
    public static final String DEFER_STATEMENT = "defer_statement";
    public static final String DEREFERENCE_EXPRESSION = "dereference_expression";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String ENUM_DECLARATION = "enum_declaration";
    public static final String ERRDEFER_STATEMENT = "errdefer_statement";
    public static final String ERROR_SET_DECLARATION = "error_set_declaration";
    public static final String ERROR_TYPE = "error_type";
    public static final String ERROR_UNION_TYPE = "error_union_type";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String FIELD_EXPRESSION = "field_expression";
    public static final String FIELD_INITIALIZER = "field_initializer";
    public static final String FLOAT_ = "float";
    public static final String FOR_EXPRESSION = "for_expression";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_DECLARATION = "function_declaration";
    public static final String FUNCTION_SIGNATURE = "function_signature";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_EXPRESSION = "if_expression";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IF_TYPE_EXPRESSION = "if_type_expression";
    public static final String INDEX_EXPRESSION = "index_expression";
    public static final String INITIALIZER_LIST = "initializer_list";
    public static final String INTEGER = "integer";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LABELED_TYPE_EXPRESSION = "labeled_type_expression";
    public static final String LINK_SECTION = "link_section";
    public static final String MULTILINE_STRING = "multiline_string";
    public static final String NOSUSPEND_EXPRESSION = "nosuspend_expression";
    public static final String NOSUSPEND_STATEMENT = "nosuspend_statement";
    public static final String NULLABLE_TYPE = "nullable_type";
    public static final String NULL_COERCION_EXPRESSION = "null_coercion_expression";
    public static final String OPAQUE_DECLARATION = "opaque_declaration";
    public static final String PARAMETER = "parameter";
    public static final String PARAMETERS = "parameters";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PAYLOAD = "payload";
    public static final String POINTER_TYPE = "pointer_type";
    public static final String PRIMARY_TYPE_EXPRESSION = "primary_type_expression";
    public static final String RANGE_EXPRESSION = "range_expression";
    public static final String RESUME_EXPRESSION = "resume_expression";
    public static final String RETURN_EXPRESSION = "return_expression";
    public static final String SLICE_TYPE = "slice_type";
    public static final String SOURCE_FILE = "source_file";
    public static final String STATEMENT = "statement";
    public static final String STRING = "string";
    public static final String STRING_CONTENT = "string_content";
    public static final String STRUCT_DECLARATION = "struct_declaration";
    public static final String STRUCT_INITIALIZER = "struct_initializer";
    public static final String SUSPEND_STATEMENT = "suspend_statement";
    public static final String SWITCH_CASE = "switch_case";
    public static final String SWITCH_EXPRESSION = "switch_expression";
    public static final String TEST_DECLARATION = "test_declaration";
    public static final String TRY_EXPRESSION = "try_expression";
    public static final String TYPE_EXPRESSION = "type_expression";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNION_DECLARATION = "union_declaration";
    public static final String USING_NAMESPACE_DECLARATION = "using_namespace_declaration";
    public static final String VARIABLE_DECLARATION = "variable_declaration";
    public static final String WHILE_EXPRESSION = "while_expression";
    public static final String WHILE_STATEMENT = "while_statement";

    public static final Set<String> EXPRESSION_SET = Set.of(
            ASM_EXPRESSION,
            ASSIGNMENT_EXPRESSION,
            ASYNC_EXPRESSION,
            AWAIT_EXPRESSION,
            BINARY_EXPRESSION,
            BLOCK,
            BREAK_EXPRESSION,
            CATCH_EXPRESSION,
            COMPTIME_EXPRESSION,
            CONTINUE_EXPRESSION,
            FOR_EXPRESSION,
            IF_EXPRESSION,
            NOSUSPEND_EXPRESSION,
            RESUME_EXPRESSION,
            RETURN_EXPRESSION,
            TRY_EXPRESSION,
            TYPE_EXPRESSION,
            UNARY_EXPRESSION,
            WHILE_EXPRESSION);
    public static final Set<String> PRIMARY_TYPE_EXPRESSION_SET = Set.of(
            ANYFRAME_TYPE,
            ARRAY_TYPE,
            BOOLEAN_,
            BUILTIN_FUNCTION,
            BUILTIN_TYPE,
            CALL_EXPRESSION,
            CHARACTER,
            DEREFERENCE_EXPRESSION,
            ENUM_DECLARATION,
            ERROR_TYPE,
            ERROR_UNION_TYPE,
            FIELD_EXPRESSION,
            FLOAT_,
            FUNCTION_SIGNATURE,
            IDENTIFIER,
            INDEX_EXPRESSION,
            INTEGER,
            MULTILINE_STRING,
            NULLABLE_TYPE,
            NULL_COERCION_EXPRESSION,
            OPAQUE_DECLARATION,
            POINTER_TYPE,
            RANGE_EXPRESSION,
            SLICE_TYPE,
            STRING,
            STRUCT_DECLARATION,
            SWITCH_EXPRESSION,
            UNION_DECLARATION);
    public static final Set<String> STATEMENT_SET = Set.of(
            COMPTIME_STATEMENT,
            DEFER_STATEMENT,
            ERRDEFER_STATEMENT,
            EXPRESSION_STATEMENT,
            FOR_STATEMENT,
            IF_STATEMENT,
            LABELED_STATEMENT,
            NOSUSPEND_STATEMENT,
            SUSPEND_STATEMENT,
            SWITCH_EXPRESSION,
            VARIABLE_DECLARATION,
            WHILE_STATEMENT);
    public static final Set<String> TYPE_EXPRESSION_SET = Set.of(
            ANONYMOUS_STRUCT_INITIALIZER,
            ERROR_SET_DECLARATION,
            LABELED_TYPE_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            PRIMARY_TYPE_EXPRESSION,
            STRUCT_INITIALIZER);
}
