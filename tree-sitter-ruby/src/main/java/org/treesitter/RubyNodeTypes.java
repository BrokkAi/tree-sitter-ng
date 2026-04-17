package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code ruby} from tree-sitter {@code node-types.json}.
 */
public final class RubyNodeTypes {
    private RubyNodeTypes() {}

    public static final String ALIAS = "alias";
    public static final String ALTERNATIVE_PATTERN = "alternative_pattern";
    public static final String ARG = "_arg";
    public static final String ARGUMENT_LIST = "argument_list";
    public static final String ARRAY = "array";
    public static final String ARRAY_PATTERN = "array_pattern";
    public static final String ASSIGNMENT = "assignment";
    public static final String AS_PATTERN = "as_pattern";
    public static final String BARE_STRING = "bare_string";
    public static final String BARE_SYMBOL = "bare_symbol";
    public static final String BEGIN = "begin";
    public static final String BEGIN_BLOCK = "begin_block";
    public static final String BINARY = "binary";
    public static final String BLOCK = "block";
    public static final String BLOCK_ARGUMENT = "block_argument";
    public static final String BLOCK_BODY = "block_body";
    public static final String BLOCK_PARAMETER = "block_parameter";
    public static final String BLOCK_PARAMETERS = "block_parameters";
    public static final String BODY_STATEMENT = "body_statement";
    public static final String BREAK_ = "break";
    public static final String CALL = "call";
    public static final String CALL_OPERATOR = "_call_operator";
    public static final String CASE_ = "case";
    public static final String CASE_MATCH = "case_match";
    public static final String CHAINED_STRING = "chained_string";
    public static final String CHARACTER = "character";
    public static final String CLASS_ = "class";
    public static final String CLASS_VARIABLE = "class_variable";
    public static final String COMMENT = "comment";
    public static final String COMPLEX = "complex";
    public static final String CONDITIONAL = "conditional";
    public static final String CONSTANT = "constant";
    public static final String DELIMITED_SYMBOL = "delimited_symbol";
    public static final String DESTRUCTURED_LEFT_ASSIGNMENT = "destructured_left_assignment";
    public static final String DESTRUCTURED_PARAMETER = "destructured_parameter";
    public static final String DO_ = "do";
    public static final String DO_BLOCK = "do_block";
    public static final String ELEMENT_REFERENCE = "element_reference";
    public static final String ELSE_ = "else";
    public static final String ELSIF = "elsif";
    public static final String EMPTY_STATEMENT = "empty_statement";
    public static final String ENCODING = "encoding";
    public static final String END_BLOCK = "end_block";
    public static final String ENSURE = "ensure";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXCEPTIONS = "exceptions";
    public static final String EXCEPTION_VARIABLE = "exception_variable";
    public static final String EXPRESSION = "_expression";
    public static final String EXPRESSION_REFERENCE_PATTERN = "expression_reference_pattern";
    public static final String FALSE = "false";
    public static final String FILE = "file";
    public static final String FIND_PATTERN = "find_pattern";
    public static final String FLOAT_ = "float";
    public static final String FORWARD_ARGUMENT = "forward_argument";
    public static final String FORWARD_PARAMETER = "forward_parameter";
    public static final String FOR_ = "for";
    public static final String GLOBAL_VARIABLE = "global_variable";
    public static final String HASH = "hash";
    public static final String HASH_KEY_SYMBOL = "hash_key_symbol";
    public static final String HASH_PATTERN = "hash_pattern";
    public static final String HASH_SPLAT_ARGUMENT = "hash_splat_argument";
    public static final String HASH_SPLAT_NIL = "hash_splat_nil";
    public static final String HASH_SPLAT_PARAMETER = "hash_splat_parameter";
    public static final String HEREDOC_BEGINNING = "heredoc_beginning";
    public static final String HEREDOC_BODY = "heredoc_body";
    public static final String HEREDOC_CONTENT = "heredoc_content";
    public static final String HEREDOC_END = "heredoc_end";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_ = "if";
    public static final String IF_GUARD = "if_guard";
    public static final String IF_MODIFIER = "if_modifier";
    public static final String IN = "in";
    public static final String INSTANCE_VARIABLE = "instance_variable";
    public static final String INTEGER = "integer";
    public static final String INTERPOLATION = "interpolation";
    public static final String IN_CLAUSE = "in_clause";
    public static final String KEYWORD_PARAMETER = "keyword_parameter";
    public static final String KEYWORD_PATTERN = "keyword_pattern";
    public static final String LAMBDA = "lambda";
    public static final String LAMBDA_PARAMETERS = "lambda_parameters";
    public static final String LEFT_ASSIGNMENT_LIST = "left_assignment_list";
    public static final String LHS = "_lhs";
    public static final String LINE = "line";
    public static final String MATCH_PATTERN = "match_pattern";
    public static final String METHOD = "method";
    public static final String METHOD_NAME = "_method_name";
    public static final String METHOD_PARAMETERS = "method_parameters";
    public static final String MODULE = "module";
    public static final String NEXT = "next";
    public static final String NIL = "nil";
    public static final String NONLOCAL_VARIABLE = "_nonlocal_variable";
    public static final String OPERATOR = "operator";
    public static final String OPERATOR_ASSIGNMENT = "operator_assignment";
    public static final String OPTIONAL_PARAMETER = "optional_parameter";
    public static final String PAIR = "pair";
    public static final String PARENTHESIZED_PATTERN = "parenthesized_pattern";
    public static final String PARENTHESIZED_STATEMENTS = "parenthesized_statements";
    public static final String PATTERN = "pattern";
    public static final String PATTERN_CONSTANT = "_pattern_constant";
    public static final String PATTERN_EXPR = "_pattern_expr";
    public static final String PATTERN_EXPR_BASIC = "_pattern_expr_basic";
    public static final String PATTERN_PRIMITIVE = "_pattern_primitive";
    public static final String PATTERN_TOP_EXPR_BODY = "_pattern_top_expr_body";
    public static final String PRIMARY = "_primary";
    public static final String PROGRAM = "program";
    public static final String RANGE = "range";
    public static final String RATIONAL = "rational";
    public static final String REDO = "redo";
    public static final String REGEX = "regex";
    public static final String RESCUE = "rescue";
    public static final String RESCUE_MODIFIER = "rescue_modifier";
    public static final String REST_ASSIGNMENT = "rest_assignment";
    public static final String RETRY = "retry";
    public static final String RETURN_ = "return";
    public static final String RIGHT_ASSIGNMENT_LIST = "right_assignment_list";
    public static final String SCOPE_RESOLUTION = "scope_resolution";
    public static final String SELF = "self";
    public static final String SETTER = "setter";
    public static final String SIMPLE_NUMERIC = "_simple_numeric";
    public static final String SIMPLE_SYMBOL = "simple_symbol";
    public static final String SINGLETON_CLASS = "singleton_class";
    public static final String SINGLETON_METHOD = "singleton_method";
    public static final String SPLAT_ARGUMENT = "splat_argument";
    public static final String SPLAT_PARAMETER = "splat_parameter";
    public static final String STATEMENT = "_statement";
    public static final String STRING = "string";
    public static final String STRING_ARRAY = "string_array";
    public static final String STRING_CONTENT = "string_content";
    public static final String SUBSHELL = "subshell";
    public static final String SUPERCLASS = "superclass";
    public static final String SUPER_ = "super";
    public static final String SYMBOL_ARRAY = "symbol_array";
    public static final String TEST_PATTERN = "test_pattern";
    public static final String THEN = "then";
    public static final String TRUE = "true";
    public static final String UNARY = "unary";
    public static final String UNDEF = "undef";
    public static final String UNINTERPRETED = "uninterpreted";
    public static final String UNLESS = "unless";
    public static final String UNLESS_GUARD = "unless_guard";
    public static final String UNLESS_MODIFIER = "unless_modifier";
    public static final String UNTIL = "until";
    public static final String UNTIL_MODIFIER = "until_modifier";
    public static final String VARIABLE = "_variable";
    public static final String VARIABLE_REFERENCE_PATTERN = "variable_reference_pattern";
    public static final String WHEN = "when";
    public static final String WHILE_ = "while";
    public static final String WHILE_MODIFIER = "while_modifier";
    public static final String YIELD_ = "yield";

    public static final Set<String> ARG_SET =
            Set.of(ASSIGNMENT, BINARY, CONDITIONAL, OPERATOR_ASSIGNMENT, PRIMARY, RANGE, UNARY);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ARG,
            ASSIGNMENT,
            BINARY,
            BREAK_,
            CALL,
            MATCH_PATTERN,
            NEXT,
            OPERATOR_ASSIGNMENT,
            RETURN_,
            TEST_PATTERN,
            UNARY,
            YIELD_);
    public static final Set<String> LHS_SET =
            Set.of(CALL, ELEMENT_REFERENCE, FALSE, NIL, SCOPE_RESOLUTION, TRUE, VARIABLE);
    public static final Set<String> METHOD_NAME_SET =
            Set.of(CONSTANT, DELIMITED_SYMBOL, IDENTIFIER, NONLOCAL_VARIABLE, OPERATOR, SETTER, SIMPLE_SYMBOL);
    public static final Set<String> NONLOCAL_VARIABLE_SET = Set.of(CLASS_VARIABLE, GLOBAL_VARIABLE, INSTANCE_VARIABLE);
    public static final Set<String> PATTERN_CONSTANT_SET = Set.of(CONSTANT, SCOPE_RESOLUTION);
    public static final Set<String> PATTERN_EXPR_BASIC_SET = Set.of(
            ARRAY_PATTERN,
            EXPRESSION_REFERENCE_PATTERN,
            FIND_PATTERN,
            HASH_PATTERN,
            IDENTIFIER,
            PARENTHESIZED_PATTERN,
            PATTERN_CONSTANT,
            PATTERN_PRIMITIVE,
            RANGE,
            VARIABLE_REFERENCE_PATTERN);
    public static final Set<String> PATTERN_EXPR_SET = Set.of(ALTERNATIVE_PATTERN, AS_PATTERN, PATTERN_EXPR_BASIC);
    public static final Set<String> PATTERN_PRIMITIVE_SET = Set.of(
            DELIMITED_SYMBOL,
            ENCODING,
            FALSE,
            FILE,
            HEREDOC_BEGINNING,
            LAMBDA,
            LINE,
            NIL,
            REGEX,
            SELF,
            SIMPLE_NUMERIC,
            SIMPLE_SYMBOL,
            STRING,
            STRING_ARRAY,
            SUBSHELL,
            SYMBOL_ARRAY,
            TRUE,
            UNARY);
    public static final Set<String> PATTERN_TOP_EXPR_BODY_SET =
            Set.of(ARRAY_PATTERN, FIND_PATTERN, HASH_PATTERN, PATTERN_EXPR);
    public static final Set<String> PRIMARY_SET = Set.of(
            ARRAY,
            BEGIN,
            BREAK_,
            CALL,
            CASE_,
            CASE_MATCH,
            CHAINED_STRING,
            CHARACTER,
            CLASS_,
            DELIMITED_SYMBOL,
            FOR_,
            HASH,
            HEREDOC_BEGINNING,
            IF_,
            LAMBDA,
            LHS,
            METHOD,
            MODULE,
            NEXT,
            PARENTHESIZED_STATEMENTS,
            REDO,
            REGEX,
            RETRY,
            RETURN_,
            SIMPLE_NUMERIC,
            SIMPLE_SYMBOL,
            SINGLETON_CLASS,
            SINGLETON_METHOD,
            STRING,
            STRING_ARRAY,
            SUBSHELL,
            SYMBOL_ARRAY,
            UNARY,
            UNLESS,
            UNTIL,
            WHILE_,
            YIELD_);
    public static final Set<String> SIMPLE_NUMERIC_SET = Set.of(COMPLEX, FLOAT_, INTEGER, RATIONAL);
    public static final Set<String> STATEMENT_SET = Set.of(
            ALIAS,
            BEGIN_BLOCK,
            END_BLOCK,
            EXPRESSION,
            IF_MODIFIER,
            RESCUE_MODIFIER,
            UNDEF,
            UNLESS_MODIFIER,
            UNTIL_MODIFIER,
            WHILE_MODIFIER);
    public static final Set<String> VARIABLE_SET = Set.of(CONSTANT, IDENTIFIER, NONLOCAL_VARIABLE, SELF, SUPER_);
}
