package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code julia} from tree-sitter {@code node-types.json}.
 */
public final class JuliaNodeTypes {
    private JuliaNodeTypes() {}

    public static final String ABSTRACT_DEFINITION = "abstract_definition";
    public static final String ADJOINT_EXPRESSION = "adjoint_expression";
    public static final String ARGUMENT_LIST = "argument_list";
    public static final String ARROW_FUNCTION_EXPRESSION = "arrow_function_expression";
    public static final String ASSIGNMENT = "assignment";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BLOCK = "block";
    public static final String BLOCK_COMMENT = "block_comment";
    public static final String BOOLEAN_LITERAL = "boolean_literal";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String BROADCAST_CALL_EXPRESSION = "broadcast_call_expression";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CHARACTER_LITERAL = "character_literal";
    public static final String COMMAND_LITERAL = "command_literal";
    public static final String COMPOUND_ASSIGNMENT_EXPRESSION = "compound_assignment_expression";
    public static final String COMPOUND_STATEMENT = "compound_statement";
    public static final String COMPREHENSION_EXPRESSION = "comprehension_expression";
    public static final String CONST_STATEMENT = "const_statement";
    public static final String CONTENT = "content";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String CURLY_EXPRESSION = "curly_expression";
    public static final String DEFINITION = "_definition";
    public static final String DO_CLAUSE = "do_clause";
    public static final String ELSEIF_CLAUSE = "elseif_clause";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPORT_STATEMENT = "export_statement";
    public static final String EXPRESSION = "_expression";
    public static final String FIELD_EXPRESSION = "field_expression";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FLOAT_LITERAL = "float_literal";
    public static final String FOR_BINDING = "for_binding";
    public static final String FOR_CLAUSE = "for_clause";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_DEFINITION = "function_definition";
    public static final String GENERATOR = "generator";
    public static final String GLOBAL_STATEMENT = "global_statement";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_CLAUSE = "if_clause";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IMPORT_ALIAS = "import_alias";
    public static final String IMPORT_PATH = "import_path";
    public static final String IMPORT_STATEMENT = "import_statement";
    public static final String INDEX_EXPRESSION = "index_expression";
    public static final String INTEGER_LITERAL = "integer_literal";
    public static final String INTERPOLATION_EXPRESSION = "interpolation_expression";
    public static final String JUXTAPOSITION_EXPRESSION = "juxtaposition_expression";
    public static final String LET_STATEMENT = "let_statement";
    public static final String LINE_COMMENT = "line_comment";
    public static final String LOCAL_STATEMENT = "local_statement";
    public static final String MACROCALL_EXPRESSION = "macrocall_expression";
    public static final String MACRO_ARGUMENT_LIST = "macro_argument_list";
    public static final String MACRO_DEFINITION = "macro_definition";
    public static final String MACRO_IDENTIFIER = "macro_identifier";
    public static final String MATRIX_EXPRESSION = "matrix_expression";
    public static final String MATRIX_ROW = "matrix_row";
    public static final String MODULE_DEFINITION = "module_definition";
    public static final String OPEN_TUPLE = "open_tuple";
    public static final String OPERATOR = "operator";
    public static final String PARAMETRIZED_TYPE_EXPRESSION = "parametrized_type_expression";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PREFIXED_COMMAND_LITERAL = "prefixed_command_literal";
    public static final String PREFIXED_STRING_LITERAL = "prefixed_string_literal";
    public static final String PRIMITIVE_DEFINITION = "primitive_definition";
    public static final String PUBLIC_STATEMENT = "public_statement";
    public static final String QUOTE_EXPRESSION = "quote_expression";
    public static final String QUOTE_STATEMENT = "quote_statement";
    public static final String RANGE_EXPRESSION = "range_expression";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SELECTED_IMPORT = "selected_import";
    public static final String SIGNATURE = "signature";
    public static final String SOURCE_FILE = "source_file";
    public static final String SPLAT_EXPRESSION = "splat_expression";
    public static final String STATEMENT = "_statement";
    public static final String STRING_INTERPOLATION = "string_interpolation";
    public static final String STRING_LITERAL = "string_literal";
    public static final String STRUCT_DEFINITION = "struct_definition";
    public static final String TERNARY_EXPRESSION = "ternary_expression";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String TUPLE_EXPRESSION = "tuple_expression";
    public static final String TYPED_EXPRESSION = "typed_expression";
    public static final String TYPE_HEAD = "type_head";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNARY_TYPED_EXPRESSION = "unary_typed_expression";
    public static final String USING_STATEMENT = "using_statement";
    public static final String VECTOR_EXPRESSION = "vector_expression";
    public static final String WHERE_EXPRESSION = "where_expression";
    public static final String WHILE_STATEMENT = "while_statement";

    public static final Set<String> DEFINITION_SET = Set.of(
            ABSTRACT_DEFINITION,
            FUNCTION_DEFINITION,
            MACRO_DEFINITION,
            MODULE_DEFINITION,
            PRIMITIVE_DEFINITION,
            STRUCT_DEFINITION);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ADJOINT_EXPRESSION,
            ARROW_FUNCTION_EXPRESSION,
            BINARY_EXPRESSION,
            BOOLEAN_LITERAL,
            BROADCAST_CALL_EXPRESSION,
            CALL_EXPRESSION,
            CHARACTER_LITERAL,
            COMMAND_LITERAL,
            COMPOUND_ASSIGNMENT_EXPRESSION,
            COMPREHENSION_EXPRESSION,
            CURLY_EXPRESSION,
            DEFINITION,
            FIELD_EXPRESSION,
            FLOAT_LITERAL,
            IDENTIFIER,
            INDEX_EXPRESSION,
            INTEGER_LITERAL,
            INTERPOLATION_EXPRESSION,
            JUXTAPOSITION_EXPRESSION,
            MACROCALL_EXPRESSION,
            MATRIX_EXPRESSION,
            OPERATOR,
            PARAMETRIZED_TYPE_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            PREFIXED_COMMAND_LITERAL,
            PREFIXED_STRING_LITERAL,
            QUOTE_EXPRESSION,
            RANGE_EXPRESSION,
            SPLAT_EXPRESSION,
            STATEMENT,
            STRING_LITERAL,
            TERNARY_EXPRESSION,
            TUPLE_EXPRESSION,
            TYPED_EXPRESSION,
            UNARY_EXPRESSION,
            UNARY_TYPED_EXPRESSION,
            VECTOR_EXPRESSION,
            WHERE_EXPRESSION);
    public static final Set<String> STATEMENT_SET = Set.of(
            BREAK_STATEMENT,
            COMPOUND_STATEMENT,
            CONST_STATEMENT,
            CONTINUE_STATEMENT,
            EXPORT_STATEMENT,
            FOR_STATEMENT,
            GLOBAL_STATEMENT,
            IF_STATEMENT,
            IMPORT_STATEMENT,
            LET_STATEMENT,
            LOCAL_STATEMENT,
            PUBLIC_STATEMENT,
            QUOTE_STATEMENT,
            RETURN_STATEMENT,
            TRY_STATEMENT,
            USING_STATEMENT,
            WHILE_STATEMENT);
}
