package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code ruby} from tree-sitter {@code node-types.json}.
 */
public enum RubyNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE_PATTERN("alternative_pattern"),
    ARG("_arg"),
    ARGUMENT_LIST("argument_list"),
    ARRAY("array"),
    ARRAY_PATTERN("array_pattern"),
    ASSIGNMENT("assignment"),
    AS_PATTERN("as_pattern"),
    BARE_STRING("bare_string"),
    BARE_SYMBOL("bare_symbol"),
    BEGIN("begin"),
    BEGIN_BLOCK("begin_block"),
    BINARY("binary"),
    BLOCK("block"),
    BLOCK_ARGUMENT("block_argument"),
    BLOCK_BODY("block_body"),
    BLOCK_PARAMETER("block_parameter"),
    BLOCK_PARAMETERS("block_parameters"),
    BODY_STATEMENT("body_statement"),
    BREAK_("break"),
    CALL("call"),
    CALL_OPERATOR("_call_operator"),
    CASE_("case"),
    CASE_MATCH("case_match"),
    CHAINED_STRING("chained_string"),
    CHARACTER("character"),
    CLASS_("class"),
    CLASS_VARIABLE("class_variable"),
    COMMENT("comment"),
    COMPLEX("complex"),
    CONDITIONAL("conditional"),
    CONSTANT("constant"),
    DELIMITED_SYMBOL("delimited_symbol"),
    DESTRUCTURED_LEFT_ASSIGNMENT("destructured_left_assignment"),
    DESTRUCTURED_PARAMETER("destructured_parameter"),
    DO_("do"),
    DO_BLOCK("do_block"),
    ELEMENT_REFERENCE("element_reference"),
    ELSE_("else"),
    ELSIF("elsif"),
    EMPTY_STATEMENT("empty_statement"),
    ENCODING("encoding"),
    END_BLOCK("end_block"),
    ENSURE("ensure"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXCEPTIONS("exceptions"),
    EXCEPTION_VARIABLE("exception_variable"),
    EXPRESSION("_expression"),
    EXPRESSION_REFERENCE_PATTERN("expression_reference_pattern"),
    FALSE("false"),
    FILE("file"),
    FIND_PATTERN("find_pattern"),
    FLOAT_("float"),
    FORWARD_ARGUMENT("forward_argument"),
    FORWARD_PARAMETER("forward_parameter"),
    FOR_("for"),
    GLOBAL_VARIABLE("global_variable"),
    HASH("hash"),
    HASH_KEY_SYMBOL("hash_key_symbol"),
    HASH_PATTERN("hash_pattern"),
    HASH_SPLAT_ARGUMENT("hash_splat_argument"),
    HASH_SPLAT_NIL("hash_splat_nil"),
    HASH_SPLAT_PARAMETER("hash_splat_parameter"),
    HEREDOC_BEGINNING("heredoc_beginning"),
    HEREDOC_BODY("heredoc_body"),
    HEREDOC_CONTENT("heredoc_content"),
    HEREDOC_END("heredoc_end"),
    IDENTIFIER("identifier"),
    IF_("if"),
    IF_GUARD("if_guard"),
    IF_MODIFIER("if_modifier"),
    IN("in"),
    INSTANCE_VARIABLE("instance_variable"),
    INTEGER("integer"),
    INTERPOLATION("interpolation"),
    IN_CLAUSE("in_clause"),
    KEYWORD_PARAMETER("keyword_parameter"),
    KEYWORD_PATTERN("keyword_pattern"),
    LAMBDA("lambda"),
    LAMBDA_PARAMETERS("lambda_parameters"),
    LEFT_ASSIGNMENT_LIST("left_assignment_list"),
    LHS("_lhs"),
    LINE("line"),
    MATCH_PATTERN("match_pattern"),
    METHOD("method"),
    METHOD_NAME("_method_name"),
    METHOD_PARAMETERS("method_parameters"),
    MODULE("module"),
    NEXT("next"),
    NIL("nil"),
    NONLOCAL_VARIABLE("_nonlocal_variable"),
    OPERATOR("operator"),
    OPERATOR_ASSIGNMENT("operator_assignment"),
    OPTIONAL_PARAMETER("optional_parameter"),
    PAIR("pair"),
    PARENTHESIZED_PATTERN("parenthesized_pattern"),
    PARENTHESIZED_STATEMENTS("parenthesized_statements"),
    PATTERN("pattern"),
    PATTERN_CONSTANT("_pattern_constant"),
    PATTERN_EXPR("_pattern_expr"),
    PATTERN_EXPR_BASIC("_pattern_expr_basic"),
    PATTERN_PRIMITIVE("_pattern_primitive"),
    PATTERN_TOP_EXPR_BODY("_pattern_top_expr_body"),
    PRIMARY("_primary"),
    PROGRAM("program"),
    RANGE("range"),
    RATIONAL("rational"),
    REDO("redo"),
    REGEX("regex"),
    RESCUE("rescue"),
    RESCUE_MODIFIER("rescue_modifier"),
    REST_ASSIGNMENT("rest_assignment"),
    RETRY("retry"),
    RETURN_("return"),
    RIGHT_ASSIGNMENT_LIST("right_assignment_list"),
    SCOPE_RESOLUTION("scope_resolution"),
    SELF("self"),
    SETTER("setter"),
    SIMPLE_NUMERIC("_simple_numeric"),
    SIMPLE_SYMBOL("simple_symbol"),
    SINGLETON_CLASS("singleton_class"),
    SINGLETON_METHOD("singleton_method"),
    SPLAT_ARGUMENT("splat_argument"),
    SPLAT_PARAMETER("splat_parameter"),
    STATEMENT("_statement"),
    STRING("string"),
    STRING_ARRAY("string_array"),
    STRING_CONTENT("string_content"),
    SUBSHELL("subshell"),
    SUPERCLASS("superclass"),
    SUPER_("super"),
    SYMBOL_ARRAY("symbol_array"),
    TEST_PATTERN("test_pattern"),
    THEN("then"),
    TRUE("true"),
    UNARY("unary"),
    UNDEF("undef"),
    UNINTERPRETED("uninterpreted"),
    UNLESS("unless"),
    UNLESS_GUARD("unless_guard"),
    UNLESS_MODIFIER("unless_modifier"),
    UNTIL("until"),
    UNTIL_MODIFIER("until_modifier"),
    VARIABLE("_variable"),
    VARIABLE_REFERENCE_PATTERN("variable_reference_pattern"),
    WHEN("when"),
    WHILE_("while"),
    WHILE_MODIFIER("while_modifier"),
    YIELD_("yield");

    public static final Set<RubyNodeType> ARG_SET =
            Set.of(ASSIGNMENT, BINARY, CONDITIONAL, OPERATOR_ASSIGNMENT, PRIMARY, RANGE, UNARY);
    public static final Set<RubyNodeType> EXPRESSION_SET = Set.of(
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
    public static final Set<RubyNodeType> LHS_SET =
            Set.of(CALL, ELEMENT_REFERENCE, FALSE, NIL, SCOPE_RESOLUTION, TRUE, VARIABLE);
    public static final Set<RubyNodeType> METHOD_NAME_SET =
            Set.of(CONSTANT, DELIMITED_SYMBOL, IDENTIFIER, NONLOCAL_VARIABLE, OPERATOR, SETTER, SIMPLE_SYMBOL);
    public static final Set<RubyNodeType> NONLOCAL_VARIABLE_SET =
            Set.of(CLASS_VARIABLE, GLOBAL_VARIABLE, INSTANCE_VARIABLE);
    public static final Set<RubyNodeType> PATTERN_CONSTANT_SET = Set.of(CONSTANT, SCOPE_RESOLUTION);
    public static final Set<RubyNodeType> PATTERN_EXPR_BASIC_SET = Set.of(
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
    public static final Set<RubyNodeType> PATTERN_EXPR_SET =
            Set.of(ALTERNATIVE_PATTERN, AS_PATTERN, PATTERN_EXPR_BASIC);
    public static final Set<RubyNodeType> PATTERN_PRIMITIVE_SET = Set.of(
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
    public static final Set<RubyNodeType> PATTERN_TOP_EXPR_BODY_SET =
            Set.of(ARRAY_PATTERN, FIND_PATTERN, HASH_PATTERN, PATTERN_EXPR);
    public static final Set<RubyNodeType> PRIMARY_SET = Set.of(
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
    public static final Set<RubyNodeType> SIMPLE_NUMERIC_SET = Set.of(COMPLEX, FLOAT_, INTEGER, RATIONAL);
    public static final Set<RubyNodeType> STATEMENT_SET = Set.of(
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
    public static final Set<RubyNodeType> VARIABLE_SET = Set.of(CONSTANT, IDENTIFIER, NONLOCAL_VARIABLE, SELF, SUPER_);

    private final @Nullable String type;

    RubyNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static RubyNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static RubyNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        RubyNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, RubyNodeType> LOOKUP = initLookup();

    private static Map<String, RubyNodeType> initLookup() {
        HashMap<String, RubyNodeType> m = new HashMap<>();
        for (RubyNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
