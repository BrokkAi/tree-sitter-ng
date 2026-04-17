package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code bash} from tree-sitter {@code node-types.json}.
 */
public enum BashNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ANSI_C_STRING("ansi_c_string"),
    ARITHMETIC_EXPANSION("arithmetic_expansion"),
    ARRAY("array"),
    BINARY_EXPRESSION("binary_expression"),
    BRACE_EXPRESSION("brace_expression"),
    CASE_ITEM("case_item"),
    CASE_STATEMENT("case_statement"),
    COMMAND("command"),
    COMMAND_NAME("command_name"),
    COMMAND_SUBSTITUTION("command_substitution"),
    COMMENT("comment"),
    COMPOUND_STATEMENT("compound_statement"),
    CONCATENATION("concatenation"),
    C_STYLE_FOR_STATEMENT("c_style_for_statement"),
    DECLARATION_COMMAND("declaration_command"),
    DO_GROUP("do_group"),
    ELIF_CLAUSE("elif_clause"),
    ELSE_CLAUSE("else_clause"),
    EXPANSION("expansion"),
    EXPRESSION("_expression"),
    EXTGLOB_PATTERN("extglob_pattern"),
    FILE_DESCRIPTOR("file_descriptor"),
    FILE_REDIRECT("file_redirect"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DEFINITION("function_definition"),
    HEREDOC_BODY("heredoc_body"),
    HEREDOC_CONTENT("heredoc_content"),
    HEREDOC_END("heredoc_end"),
    HEREDOC_REDIRECT("heredoc_redirect"),
    HEREDOC_START("heredoc_start"),
    HERESTRING_REDIRECT("herestring_redirect"),
    IF_STATEMENT("if_statement"),
    LIST("list"),
    NEGATED_COMMAND("negated_command"),
    NUMBER("number"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PIPELINE("pipeline"),
    POSTFIX_EXPRESSION("postfix_expression"),
    PRIMARY_EXPRESSION("_primary_expression"),
    PROCESS_SUBSTITUTION("process_substitution"),
    PROGRAM("program"),
    RAW_STRING("raw_string"),
    REDIRECTED_STATEMENT("redirected_statement"),
    REGEX("regex"),
    SIMPLE_EXPANSION("simple_expansion"),
    SPECIAL_VARIABLE_NAME("special_variable_name"),
    STATEMENT("_statement"),
    STRING("string"),
    STRING_CONTENT("string_content"),
    SUBSCRIPT("subscript"),
    SUBSHELL("subshell"),
    TERNARY_EXPRESSION("ternary_expression"),
    TEST_COMMAND("test_command"),
    TEST_OPERATOR("test_operator"),
    TRANSLATED_STRING("translated_string"),
    UNARY_EXPRESSION("unary_expression"),
    UNSET_COMMAND("unset_command"),
    VARIABLE_ASSIGNMENT("variable_assignment"),
    VARIABLE_ASSIGNMENTS("variable_assignments"),
    VARIABLE_NAME("variable_name"),
    WHILE_STATEMENT("while_statement"),
    WORD("word");

    public static final Set<BashNodeType> EXPRESSION_SET = Set.of(
            BINARY_EXPRESSION,
            CONCATENATION,
            PARENTHESIZED_EXPRESSION,
            POSTFIX_EXPRESSION,
            PRIMARY_EXPRESSION,
            TERNARY_EXPRESSION,
            UNARY_EXPRESSION,
            WORD);
    public static final Set<BashNodeType> PRIMARY_EXPRESSION_SET = Set.of(
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
    public static final Set<BashNodeType> STATEMENT_SET = Set.of(
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

    private final @Nullable String type;

    BashNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static BashNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static BashNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        BashNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, BashNodeType> LOOKUP = initLookup();

    private static Map<String, BashNodeType> initLookup() {
        HashMap<String, BashNodeType> m = new HashMap<>();
        for (BashNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
