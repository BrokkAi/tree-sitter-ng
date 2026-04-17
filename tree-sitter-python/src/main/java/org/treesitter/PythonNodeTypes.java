package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code python} from tree-sitter {@code node-types.json}.
 */
public final class PythonNodeTypes {
    private PythonNodeTypes() {}

    public static final String ALIASED_IMPORT = "aliased_import";
    public static final String ARGUMENT_LIST = "argument_list";
    public static final String ASSERT_STATEMENT = "assert_statement";
    public static final String ASSIGNMENT = "assignment";
    public static final String AS_PATTERN = "as_pattern";
    public static final String ATTRIBUTE = "attribute";
    public static final String AUGMENTED_ASSIGNMENT = "augmented_assignment";
    public static final String AWAIT = "await";
    public static final String BINARY_OPERATOR = "binary_operator";
    public static final String BLOCK = "block";
    public static final String BOOLEAN_OPERATOR = "boolean_operator";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CALL = "call";
    public static final String CASE_CLAUSE = "case_clause";
    public static final String CASE_PATTERN = "case_pattern";
    public static final String CHEVRON = "chevron";
    public static final String CLASS_DEFINITION = "class_definition";
    public static final String CLASS_PATTERN = "class_pattern";
    public static final String COMMENT = "comment";
    public static final String COMPARISON_OPERATOR = "comparison_operator";
    public static final String COMPLEX_PATTERN = "complex_pattern";
    public static final String COMPOUND_STATEMENT = "_compound_statement";
    public static final String CONCATENATED_STRING = "concatenated_string";
    public static final String CONDITIONAL_EXPRESSION = "conditional_expression";
    public static final String CONSTRAINED_TYPE = "constrained_type";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String DECORATED_DEFINITION = "decorated_definition";
    public static final String DECORATOR = "decorator";
    public static final String DEFAULT_PARAMETER = "default_parameter";
    public static final String DELETE_STATEMENT = "delete_statement";
    public static final String DICTIONARY = "dictionary";
    public static final String DICTIONARY_COMPREHENSION = "dictionary_comprehension";
    public static final String DICTIONARY_SPLAT = "dictionary_splat";
    public static final String DICTIONARY_SPLAT_PATTERN = "dictionary_splat_pattern";
    public static final String DICT_PATTERN = "dict_pattern";
    public static final String DOTTED_NAME = "dotted_name";
    public static final String ELIF_CLAUSE = "elif_clause";
    public static final String ELLIPSIS = "ellipsis";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String ESCAPE_INTERPOLATION = "escape_interpolation";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXCEPT_CLAUSE = "except_clause";
    public static final String EXEC_STATEMENT = "exec_statement";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_LIST = "expression_list";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String FALSE = "false";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FLOAT_ = "float";
    public static final String FORMAT_EXPRESSION = "format_expression";
    public static final String FORMAT_SPECIFIER = "format_specifier";
    public static final String FOR_IN_CLAUSE = "for_in_clause";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_DEFINITION = "function_definition";
    public static final String FUTURE_IMPORT_STATEMENT = "future_import_statement";
    public static final String GENERATOR_EXPRESSION = "generator_expression";
    public static final String GENERIC_TYPE = "generic_type";
    public static final String GLOBAL_STATEMENT = "global_statement";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_CLAUSE = "if_clause";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IMPORT_FROM_STATEMENT = "import_from_statement";
    public static final String IMPORT_PREFIX = "import_prefix";
    public static final String IMPORT_STATEMENT = "import_statement";
    public static final String INTEGER = "integer";
    public static final String INTERPOLATION = "interpolation";
    public static final String KEYWORD_ARGUMENT = "keyword_argument";
    public static final String KEYWORD_PATTERN = "keyword_pattern";
    public static final String KEYWORD_SEPARATOR = "keyword_separator";
    public static final String LAMBDA = "lambda";
    public static final String LAMBDA_PARAMETERS = "lambda_parameters";
    public static final String LINE_CONTINUATION = "line_continuation";
    public static final String LIST = "list";
    public static final String LIST_COMPREHENSION = "list_comprehension";
    public static final String LIST_PATTERN = "list_pattern";
    public static final String LIST_SPLAT = "list_splat";
    public static final String LIST_SPLAT_PATTERN = "list_splat_pattern";
    public static final String MATCH_STATEMENT = "match_statement";
    public static final String MEMBER_TYPE = "member_type";
    public static final String MODULE = "module";
    public static final String NAMED_EXPRESSION = "named_expression";
    public static final String NONE = "none";
    public static final String NONLOCAL_STATEMENT = "nonlocal_statement";
    public static final String NOT_OPERATOR = "not_operator";
    public static final String PAIR = "pair";
    public static final String PARAMETER = "parameter";
    public static final String PARAMETERS = "parameters";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PARENTHESIZED_LIST_SPLAT = "parenthesized_list_splat";
    public static final String PASS_STATEMENT = "pass_statement";
    public static final String PATTERN = "pattern";
    public static final String PATTERN_LIST = "pattern_list";
    public static final String POSITIONAL_SEPARATOR = "positional_separator";
    public static final String PRIMARY_EXPRESSION = "primary_expression";
    public static final String PRINT_STATEMENT = "print_statement";
    public static final String RAISE_STATEMENT = "raise_statement";
    public static final String RELATIVE_IMPORT = "relative_import";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SET = "set";
    public static final String SET_COMPREHENSION = "set_comprehension";
    public static final String SIMPLE_STATEMENT = "_simple_statement";
    public static final String SLICE = "slice";
    public static final String SPLAT_PATTERN = "splat_pattern";
    public static final String SPLAT_TYPE = "splat_type";
    public static final String STRING = "string";
    public static final String STRING_CONTENT = "string_content";
    public static final String STRING_END = "string_end";
    public static final String STRING_START = "string_start";
    public static final String SUBSCRIPT = "subscript";
    public static final String TRUE = "true";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String TUPLE = "tuple";
    public static final String TUPLE_PATTERN = "tuple_pattern";
    public static final String TYPE = "type";
    public static final String TYPED_DEFAULT_PARAMETER = "typed_default_parameter";
    public static final String TYPED_PARAMETER = "typed_parameter";
    public static final String TYPE_ALIAS_STATEMENT = "type_alias_statement";
    public static final String TYPE_CONVERSION = "type_conversion";
    public static final String TYPE_PARAMETER = "type_parameter";
    public static final String UNARY_OPERATOR = "unary_operator";
    public static final String UNION_PATTERN = "union_pattern";
    public static final String UNION_TYPE = "union_type";
    public static final String WHILE_STATEMENT = "while_statement";
    public static final String WILDCARD_IMPORT = "wildcard_import";
    public static final String WITH_CLAUSE = "with_clause";
    public static final String WITH_ITEM = "with_item";
    public static final String WITH_STATEMENT = "with_statement";
    public static final String YIELD_ = "yield";

    public static final Set<String> COMPOUND_STATEMENT_SET = Set.of(
            CLASS_DEFINITION,
            DECORATED_DEFINITION,
            FOR_STATEMENT,
            FUNCTION_DEFINITION,
            IF_STATEMENT,
            MATCH_STATEMENT,
            TRY_STATEMENT,
            WHILE_STATEMENT,
            WITH_STATEMENT);
    public static final Set<String> EXPRESSION_SET = Set.of(
            AS_PATTERN,
            BOOLEAN_OPERATOR,
            COMPARISON_OPERATOR,
            CONDITIONAL_EXPRESSION,
            LAMBDA,
            NAMED_EXPRESSION,
            NOT_OPERATOR,
            PRIMARY_EXPRESSION);
    public static final Set<String> PARAMETER_SET = Set.of(
            DEFAULT_PARAMETER,
            DICTIONARY_SPLAT_PATTERN,
            IDENTIFIER,
            KEYWORD_SEPARATOR,
            LIST_SPLAT_PATTERN,
            POSITIONAL_SEPARATOR,
            TUPLE_PATTERN,
            TYPED_DEFAULT_PARAMETER,
            TYPED_PARAMETER);
    public static final Set<String> PATTERN_SET =
            Set.of(ATTRIBUTE, IDENTIFIER, LIST_PATTERN, LIST_SPLAT_PATTERN, SUBSCRIPT, TUPLE_PATTERN);
    public static final Set<String> PRIMARY_EXPRESSION_SET = Set.of(
            ATTRIBUTE,
            AWAIT,
            BINARY_OPERATOR,
            CALL,
            CONCATENATED_STRING,
            DICTIONARY,
            DICTIONARY_COMPREHENSION,
            ELLIPSIS,
            FALSE,
            FLOAT_,
            GENERATOR_EXPRESSION,
            IDENTIFIER,
            INTEGER,
            LIST,
            LIST_COMPREHENSION,
            LIST_SPLAT,
            NONE,
            PARENTHESIZED_EXPRESSION,
            SET,
            SET_COMPREHENSION,
            STRING,
            SUBSCRIPT,
            TRUE,
            TUPLE,
            UNARY_OPERATOR);
    public static final Set<String> SIMPLE_STATEMENT_SET = Set.of(
            ASSERT_STATEMENT,
            BREAK_STATEMENT,
            CONTINUE_STATEMENT,
            DELETE_STATEMENT,
            EXEC_STATEMENT,
            EXPRESSION_STATEMENT,
            FUTURE_IMPORT_STATEMENT,
            GLOBAL_STATEMENT,
            IMPORT_FROM_STATEMENT,
            IMPORT_STATEMENT,
            NONLOCAL_STATEMENT,
            PASS_STATEMENT,
            PRINT_STATEMENT,
            RAISE_STATEMENT,
            RETURN_STATEMENT,
            TYPE_ALIAS_STATEMENT);
}
