package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code python} from tree-sitter {@code node-types.json}.
 */
public enum PythonNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ALIASED_IMPORT("aliased_import"),
    ARGUMENT_LIST("argument_list"),
    ASSERT_STATEMENT("assert_statement"),
    ASSIGNMENT("assignment"),
    AS_PATTERN("as_pattern"),
    ATTRIBUTE("attribute"),
    AUGMENTED_ASSIGNMENT("augmented_assignment"),
    AWAIT("await"),
    BINARY_OPERATOR("binary_operator"),
    BLOCK("block"),
    BOOLEAN_OPERATOR("boolean_operator"),
    BREAK_STATEMENT("break_statement"),
    CALL("call"),
    CASE_CLAUSE("case_clause"),
    CASE_PATTERN("case_pattern"),
    CHEVRON("chevron"),
    CLASS_DEFINITION("class_definition"),
    CLASS_PATTERN("class_pattern"),
    COMMENT("comment"),
    COMPARISON_OPERATOR("comparison_operator"),
    COMPLEX_PATTERN("complex_pattern"),
    COMPOUND_STATEMENT("_compound_statement"),
    CONCATENATED_STRING("concatenated_string"),
    CONDITIONAL_EXPRESSION("conditional_expression"),
    CONSTRAINED_TYPE("constrained_type"),
    CONTINUE_STATEMENT("continue_statement"),
    DECORATED_DEFINITION("decorated_definition"),
    DECORATOR("decorator"),
    DEFAULT_PARAMETER("default_parameter"),
    DELETE_STATEMENT("delete_statement"),
    DICTIONARY("dictionary"),
    DICTIONARY_COMPREHENSION("dictionary_comprehension"),
    DICTIONARY_SPLAT("dictionary_splat"),
    DICTIONARY_SPLAT_PATTERN("dictionary_splat_pattern"),
    DICT_PATTERN("dict_pattern"),
    DOTTED_NAME("dotted_name"),
    ELIF_CLAUSE("elif_clause"),
    ELLIPSIS("ellipsis"),
    ELSE_CLAUSE("else_clause"),
    ESCAPE_INTERPOLATION("escape_interpolation"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXCEPT_CLAUSE("except_clause"),
    EXEC_STATEMENT("exec_statement"),
    EXPRESSION("expression"),
    EXPRESSION_LIST("expression_list"),
    EXPRESSION_STATEMENT("expression_statement"),
    FALSE("false"),
    FINALLY_CLAUSE("finally_clause"),
    FLOAT_("float"),
    FORMAT_EXPRESSION("format_expression"),
    FORMAT_SPECIFIER("format_specifier"),
    FOR_IN_CLAUSE("for_in_clause"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DEFINITION("function_definition"),
    FUTURE_IMPORT_STATEMENT("future_import_statement"),
    GENERATOR_EXPRESSION("generator_expression"),
    GENERIC_TYPE("generic_type"),
    GLOBAL_STATEMENT("global_statement"),
    IDENTIFIER("identifier"),
    IF_CLAUSE("if_clause"),
    IF_STATEMENT("if_statement"),
    IMPORT_FROM_STATEMENT("import_from_statement"),
    IMPORT_PREFIX("import_prefix"),
    IMPORT_STATEMENT("import_statement"),
    INTEGER("integer"),
    INTERPOLATION("interpolation"),
    KEYWORD_ARGUMENT("keyword_argument"),
    KEYWORD_PATTERN("keyword_pattern"),
    KEYWORD_SEPARATOR("keyword_separator"),
    LAMBDA("lambda"),
    LAMBDA_PARAMETERS("lambda_parameters"),
    LINE_CONTINUATION("line_continuation"),
    LIST("list"),
    LIST_COMPREHENSION("list_comprehension"),
    LIST_PATTERN("list_pattern"),
    LIST_SPLAT("list_splat"),
    LIST_SPLAT_PATTERN("list_splat_pattern"),
    MATCH_STATEMENT("match_statement"),
    MEMBER_TYPE("member_type"),
    MODULE("module"),
    NAMED_EXPRESSION("named_expression"),
    NONE("none"),
    NONLOCAL_STATEMENT("nonlocal_statement"),
    NOT_OPERATOR("not_operator"),
    PAIR("pair"),
    PARAMETER("parameter"),
    PARAMETERS("parameters"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PARENTHESIZED_LIST_SPLAT("parenthesized_list_splat"),
    PASS_STATEMENT("pass_statement"),
    PATTERN("pattern"),
    PATTERN_LIST("pattern_list"),
    POSITIONAL_SEPARATOR("positional_separator"),
    PRIMARY_EXPRESSION("primary_expression"),
    PRINT_STATEMENT("print_statement"),
    RAISE_STATEMENT("raise_statement"),
    RELATIVE_IMPORT("relative_import"),
    RETURN_STATEMENT("return_statement"),
    SET("set"),
    SET_COMPREHENSION("set_comprehension"),
    SIMPLE_STATEMENT("_simple_statement"),
    SLICE("slice"),
    SPLAT_PATTERN("splat_pattern"),
    SPLAT_TYPE("splat_type"),
    STRING("string"),
    STRING_CONTENT("string_content"),
    STRING_END("string_end"),
    STRING_START("string_start"),
    SUBSCRIPT("subscript"),
    TRUE("true"),
    TRY_STATEMENT("try_statement"),
    TUPLE("tuple"),
    TUPLE_PATTERN("tuple_pattern"),
    TYPE("type"),
    TYPED_DEFAULT_PARAMETER("typed_default_parameter"),
    TYPED_PARAMETER("typed_parameter"),
    TYPE_ALIAS_STATEMENT("type_alias_statement"),
    TYPE_CONVERSION("type_conversion"),
    TYPE_PARAMETER("type_parameter"),
    UNARY_OPERATOR("unary_operator"),
    UNION_PATTERN("union_pattern"),
    UNION_TYPE("union_type"),
    WHILE_STATEMENT("while_statement"),
    WILDCARD_IMPORT("wildcard_import"),
    WITH_CLAUSE("with_clause"),
    WITH_ITEM("with_item"),
    WITH_STATEMENT("with_statement"),
    YIELD_("yield");

    public static final Set<PythonNodeType> COMPOUND_STATEMENT_SET = Set.of(
            CLASS_DEFINITION,
            DECORATED_DEFINITION,
            FOR_STATEMENT,
            FUNCTION_DEFINITION,
            IF_STATEMENT,
            MATCH_STATEMENT,
            TRY_STATEMENT,
            WHILE_STATEMENT,
            WITH_STATEMENT);
    public static final Set<PythonNodeType> EXPRESSION_SET = Set.of(
            AS_PATTERN,
            BOOLEAN_OPERATOR,
            COMPARISON_OPERATOR,
            CONDITIONAL_EXPRESSION,
            LAMBDA,
            NAMED_EXPRESSION,
            NOT_OPERATOR,
            PRIMARY_EXPRESSION);
    public static final Set<PythonNodeType> PARAMETER_SET = Set.of(
            DEFAULT_PARAMETER,
            DICTIONARY_SPLAT_PATTERN,
            IDENTIFIER,
            KEYWORD_SEPARATOR,
            LIST_SPLAT_PATTERN,
            POSITIONAL_SEPARATOR,
            TUPLE_PATTERN,
            TYPED_DEFAULT_PARAMETER,
            TYPED_PARAMETER);
    public static final Set<PythonNodeType> PATTERN_SET =
            Set.of(ATTRIBUTE, IDENTIFIER, LIST_PATTERN, LIST_SPLAT_PATTERN, SUBSCRIPT, TUPLE_PATTERN);
    public static final Set<PythonNodeType> PRIMARY_EXPRESSION_SET = Set.of(
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
    public static final Set<PythonNodeType> SIMPLE_STATEMENT_SET = Set.of(
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

    private final @Nullable String type;

    PythonNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static PythonNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static PythonNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        PythonNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, PythonNodeType> LOOKUP = initLookup();

    private static Map<String, PythonNodeType> initLookup() {
        HashMap<String, PythonNodeType> m = new HashMap<>();
        for (PythonNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
