package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code bash} from tree-sitter {@code node-types.json}.
 */
public final class BashNodeTypes {
    private BashNodeTypes() {}

    public static final String ANSI_C_STRING = "ansi_c_string";
    public static final String ARITHMETIC_EXPANSION = "arithmetic_expansion";
    public static final String ARRAY = "array";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BRACE_EXPRESSION = "brace_expression";
    public static final String CASE_ITEM = "case_item";
    public static final String CASE_STATEMENT = "case_statement";
    public static final String COMMAND = "command";
    public static final String COMMAND_NAME = "command_name";
    public static final String COMMAND_SUBSTITUTION = "command_substitution";
    public static final String COMMENT = "comment";
    public static final String COMPOUND_STATEMENT = "compound_statement";
    public static final String CONCATENATION = "concatenation";
    public static final String C_STYLE_FOR_STATEMENT = "c_style_for_statement";
    public static final String DECLARATION_COMMAND = "declaration_command";
    public static final String DO_GROUP = "do_group";
    public static final String ELIF_CLAUSE = "elif_clause";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String EXPANSION = "expansion";
    public static final String EXPRESSION = "_expression";
    public static final String EXTGLOB_PATTERN = "extglob_pattern";
    public static final String FILE_DESCRIPTOR = "file_descriptor";
    public static final String FILE_REDIRECT = "file_redirect";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_DEFINITION = "function_definition";
    public static final String HEREDOC_BODY = "heredoc_body";
    public static final String HEREDOC_CONTENT = "heredoc_content";
    public static final String HEREDOC_END = "heredoc_end";
    public static final String HEREDOC_REDIRECT = "heredoc_redirect";
    public static final String HEREDOC_START = "heredoc_start";
    public static final String HERESTRING_REDIRECT = "herestring_redirect";
    public static final String IF_STATEMENT = "if_statement";
    public static final String LIST = "list";
    public static final String NEGATED_COMMAND = "negated_command";
    public static final String NUMBER = "number";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PIPELINE = "pipeline";
    public static final String POSTFIX_EXPRESSION = "postfix_expression";
    public static final String PRIMARY_EXPRESSION = "_primary_expression";
    public static final String PROCESS_SUBSTITUTION = "process_substitution";
    public static final String PROGRAM = "program";
    public static final String RAW_STRING = "raw_string";
    public static final String REDIRECTED_STATEMENT = "redirected_statement";
    public static final String REGEX = "regex";
    public static final String SIMPLE_EXPANSION = "simple_expansion";
    public static final String SPECIAL_VARIABLE_NAME = "special_variable_name";
    public static final String STATEMENT = "_statement";
    public static final String STRING = "string";
    public static final String STRING_CONTENT = "string_content";
    public static final String SUBSCRIPT = "subscript";
    public static final String SUBSHELL = "subshell";
    public static final String TERNARY_EXPRESSION = "ternary_expression";
    public static final String TEST_COMMAND = "test_command";
    public static final String TEST_OPERATOR = "test_operator";
    public static final String TRANSLATED_STRING = "translated_string";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNSET_COMMAND = "unset_command";
    public static final String VARIABLE_ASSIGNMENT = "variable_assignment";
    public static final String VARIABLE_ASSIGNMENTS = "variable_assignments";
    public static final String VARIABLE_NAME = "variable_name";
    public static final String WHILE_STATEMENT = "while_statement";
    public static final String WORD = "word";

    public static final Set<String> EXPRESSION_SET = Set.of(
            BINARY_EXPRESSION,
            CONCATENATION,
            PARENTHESIZED_EXPRESSION,
            POSTFIX_EXPRESSION,
            PRIMARY_EXPRESSION,
            TERNARY_EXPRESSION,
            UNARY_EXPRESSION,
            WORD);
    public static final Set<String> PRIMARY_EXPRESSION_SET = Set.of(
            ANSI_C_STRING,
            ARITHMETIC_EXPANSION,
            BRACE_EXPRESSION,
            COMMAND_SUBSTITUTION,
            EXPANSION,
            NUMBER,
            PROCESS_SUBSTITUTION,
            RAW_STRING,
            SIMPLE_EXPANSION,
            STRING,
            TRANSLATED_STRING,
            WORD);
    public static final Set<String> STATEMENT_SET = Set.of(
            CASE_STATEMENT,
            COMMAND,
            COMPOUND_STATEMENT,
            C_STYLE_FOR_STATEMENT,
            DECLARATION_COMMAND,
            FOR_STATEMENT,
            FUNCTION_DEFINITION,
            IF_STATEMENT,
            LIST,
            NEGATED_COMMAND,
            PIPELINE,
            REDIRECTED_STATEMENT,
            SUBSHELL,
            TEST_COMMAND,
            UNSET_COMMAND,
            VARIABLE_ASSIGNMENT,
            VARIABLE_ASSIGNMENTS,
            WHILE_STATEMENT);
}
