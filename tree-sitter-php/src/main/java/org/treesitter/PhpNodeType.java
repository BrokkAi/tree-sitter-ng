package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code php} from tree-sitter {@code node-types.json}.
 */
public enum PhpNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ABSTRACT_MODIFIER("abstract_modifier"),
    ANONYMOUS_CLASS("anonymous_class"),
    ANONYMOUS_FUNCTION("anonymous_function"),
    ANONYMOUS_FUNCTION_USE_CLAUSE("anonymous_function_use_clause"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    ARRAY_CREATION_EXPRESSION("array_creation_expression"),
    ARRAY_ELEMENT_INITIALIZER("array_element_initializer"),
    ARROW_FUNCTION("arrow_function"),
    ASSIGNMENT_EXPRESSION("assignment_expression"),
    ATTRIBUTE("attribute"),
    ATTRIBUTE_GROUP("attribute_group"),
    ATTRIBUTE_LIST("attribute_list"),
    AUGMENTED_ASSIGNMENT_EXPRESSION("augmented_assignment_expression"),
    BASE_CLAUSE("base_clause"),
    BINARY_EXPRESSION("binary_expression"),
    BOOLEAN_("boolean"),
    BOTTOM_TYPE("bottom_type"),
    BREAK_STATEMENT("break_statement"),
    BY_REF("by_ref"),
    CASE_STATEMENT("case_statement"),
    CAST_EXPRESSION("cast_expression"),
    CAST_TYPE("cast_type"),
    CATCH_CLAUSE("catch_clause"),
    CLASS_CONSTANT_ACCESS_EXPRESSION("class_constant_access_expression"),
    CLASS_DECLARATION("class_declaration"),
    CLASS_INTERFACE_CLAUSE("class_interface_clause"),
    CLONE_EXPRESSION("clone_expression"),
    COLON_BLOCK("colon_block"),
    COMMENT("comment"),
    COMPOUND_STATEMENT("compound_statement"),
    CONDITIONAL_EXPRESSION("conditional_expression"),
    CONST_DECLARATION("const_declaration"),
    CONST_ELEMENT("const_element"),
    CONTINUE_STATEMENT("continue_statement"),
    DECLARATION_LIST("declaration_list"),
    DECLARE_DIRECTIVE("declare_directive"),
    DECLARE_STATEMENT("declare_statement"),
    DEFAULT_STATEMENT("default_statement"),
    DISJUNCTIVE_NORMAL_FORM_TYPE("disjunctive_normal_form_type"),
    DO_STATEMENT("do_statement"),
    DYNAMIC_VARIABLE_NAME("dynamic_variable_name"),
    ECHO_STATEMENT("echo_statement"),
    ELSE_CLAUSE("else_clause"),
    ELSE_IF_CLAUSE("else_if_clause"),
    EMPTY_STATEMENT("empty_statement"),
    ENCAPSED_STRING("encapsed_string"),
    ENUM_CASE("enum_case"),
    ENUM_DECLARATION("enum_declaration"),
    ENUM_DECLARATION_LIST("enum_declaration_list"),
    ERROR_SUPPRESSION_EXPRESSION("error_suppression_expression"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXIT_STATEMENT("exit_statement"),
    EXPRESSION("expression"),
    EXPRESSION_STATEMENT("expression_statement"),
    FINALLY_CLAUSE("finally_clause"),
    FINAL_MODIFIER("final_modifier"),
    FLOAT_("float"),
    FOREACH_STATEMENT("foreach_statement"),
    FORMAL_PARAMETERS("formal_parameters"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_CALL_EXPRESSION("function_call_expression"),
    FUNCTION_DEFINITION("function_definition"),
    FUNCTION_STATIC_DECLARATION("function_static_declaration"),
    GLOBAL_DECLARATION("global_declaration"),
    GOTO_STATEMENT("goto_statement"),
    HEREDOC("heredoc"),
    HEREDOC_BODY("heredoc_body"),
    HEREDOC_END("heredoc_end"),
    HEREDOC_START("heredoc_start"),
    IF_STATEMENT("if_statement"),
    INCLUDE_EXPRESSION("include_expression"),
    INCLUDE_ONCE_EXPRESSION("include_once_expression"),
    INTEGER("integer"),
    INTERFACE_DECLARATION("interface_declaration"),
    INTERSECTION_TYPE("intersection_type"),
    LIST_LITERAL("list_literal"),
    LITERAL("literal"),
    MATCH_BLOCK("match_block"),
    MATCH_CONDITIONAL_EXPRESSION("match_conditional_expression"),
    MATCH_CONDITION_LIST("match_condition_list"),
    MATCH_DEFAULT_EXPRESSION("match_default_expression"),
    MATCH_EXPRESSION("match_expression"),
    MEMBER_ACCESS_EXPRESSION("member_access_expression"),
    MEMBER_CALL_EXPRESSION("member_call_expression"),
    METHOD_DECLARATION("method_declaration"),
    NAME("name"),
    NAMED_LABEL_STATEMENT("named_label_statement"),
    NAMED_TYPE("named_type"),
    NAMESPACE_DEFINITION("namespace_definition"),
    NAMESPACE_NAME("namespace_name"),
    NAMESPACE_USE_CLAUSE("namespace_use_clause"),
    NAMESPACE_USE_DECLARATION("namespace_use_declaration"),
    NAMESPACE_USE_GROUP("namespace_use_group"),
    NOWDOC("nowdoc"),
    NOWDOC_BODY("nowdoc_body"),
    NOWDOC_STRING("nowdoc_string"),
    NULL("null"),
    NULLSAFE_MEMBER_ACCESS_EXPRESSION("nullsafe_member_access_expression"),
    NULLSAFE_MEMBER_CALL_EXPRESSION("nullsafe_member_call_expression"),
    OBJECT_CREATION_EXPRESSION("object_creation_expression"),
    OPERATION("operation"),
    OPTIONAL_TYPE("optional_type"),
    PAIR("pair"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PHP_END_TAG("php_end_tag"),
    PHP_TAG("php_tag"),
    PRIMARY_EXPRESSION("primary_expression"),
    PRIMITIVE_TYPE("primitive_type"),
    PRINT_INTRINSIC("print_intrinsic"),
    PROGRAM("program"),
    PROPERTY_DECLARATION("property_declaration"),
    PROPERTY_ELEMENT("property_element"),
    PROPERTY_HOOK("property_hook"),
    PROPERTY_HOOK_LIST("property_hook_list"),
    PROPERTY_PROMOTION_PARAMETER("property_promotion_parameter"),
    QUALIFIED_NAME("qualified_name"),
    READONLY_MODIFIER("readonly_modifier"),
    REFERENCE_ASSIGNMENT_EXPRESSION("reference_assignment_expression"),
    REFERENCE_MODIFIER("reference_modifier"),
    RELATIVE_NAME("relative_name"),
    RELATIVE_SCOPE("relative_scope"),
    REQUIRE_EXPRESSION("require_expression"),
    REQUIRE_ONCE_EXPRESSION("require_once_expression"),
    RETURN_STATEMENT("return_statement"),
    SCOPED_CALL_EXPRESSION("scoped_call_expression"),
    SCOPED_PROPERTY_ACCESS_EXPRESSION("scoped_property_access_expression"),
    SEQUENCE_EXPRESSION("sequence_expression"),
    SHELL_COMMAND_EXPRESSION("shell_command_expression"),
    SIMPLE_PARAMETER("simple_parameter"),
    STATEMENT("statement"),
    STATIC_MODIFIER("static_modifier"),
    STATIC_VARIABLE_DECLARATION("static_variable_declaration"),
    STRING("string"),
    STRING_CONTENT("string_content"),
    SUBSCRIPT_EXPRESSION("subscript_expression"),
    SWITCH_BLOCK("switch_block"),
    SWITCH_STATEMENT("switch_statement"),
    TEXT("text"),
    TEXT_INTERPOLATION("text_interpolation"),
    THROW_EXPRESSION("throw_expression"),
    TRAIT_DECLARATION("trait_declaration"),
    TRY_STATEMENT("try_statement"),
    TYPE("type"),
    TYPE_LIST("type_list"),
    UNARY_OP_EXPRESSION("unary_op_expression"),
    UNION_TYPE("union_type"),
    UNSET_STATEMENT("unset_statement"),
    UPDATE_EXPRESSION("update_expression"),
    USE_AS_CLAUSE("use_as_clause"),
    USE_DECLARATION("use_declaration"),
    USE_INSTEAD_OF_CLAUSE("use_instead_of_clause"),
    USE_LIST("use_list"),
    VARIABLE_NAME("variable_name"),
    VARIADIC_PARAMETER("variadic_parameter"),
    VARIADIC_PLACEHOLDER("variadic_placeholder"),
    VARIADIC_UNPACKING("variadic_unpacking"),
    VAR_MODIFIER("var_modifier"),
    VISIBILITY_MODIFIER("visibility_modifier"),
    WHILE_STATEMENT("while_statement"),
    YIELD_EXPRESSION("yield_expression");

    public static final Set<PhpNodeType> EXPRESSION_SET = Set.of(
            ASSIGNMENT_EXPRESSION,
            AUGMENTED_ASSIGNMENT_EXPRESSION,
            BINARY_EXPRESSION,
            CAST_EXPRESSION,
            CLONE_EXPRESSION,
            CONDITIONAL_EXPRESSION,
            ERROR_SUPPRESSION_EXPRESSION,
            INCLUDE_EXPRESSION,
            INCLUDE_ONCE_EXPRESSION,
            MATCH_EXPRESSION,
            PRIMARY_EXPRESSION,
            REFERENCE_ASSIGNMENT_EXPRESSION,
            REQUIRE_EXPRESSION,
            REQUIRE_ONCE_EXPRESSION,
            UNARY_OP_EXPRESSION,
            YIELD_EXPRESSION);
    public static final Set<PhpNodeType> LITERAL_SET =
            Set.of(BOOLEAN_, ENCAPSED_STRING, FLOAT_, HEREDOC, INTEGER, NOWDOC, NULL, STRING);
    public static final Set<PhpNodeType> PRIMARY_EXPRESSION_SET = Set.of(
            ANONYMOUS_FUNCTION,
            ARRAY_CREATION_EXPRESSION,
            ARROW_FUNCTION,
            CAST_EXPRESSION,
            CLASS_CONSTANT_ACCESS_EXPRESSION,
            DYNAMIC_VARIABLE_NAME,
            FUNCTION_CALL_EXPRESSION,
            LITERAL,
            MEMBER_ACCESS_EXPRESSION,
            MEMBER_CALL_EXPRESSION,
            NAME,
            NULLSAFE_MEMBER_ACCESS_EXPRESSION,
            NULLSAFE_MEMBER_CALL_EXPRESSION,
            OBJECT_CREATION_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            PRINT_INTRINSIC,
            QUALIFIED_NAME,
            RELATIVE_NAME,
            SCOPED_CALL_EXPRESSION,
            SCOPED_PROPERTY_ACCESS_EXPRESSION,
            SHELL_COMMAND_EXPRESSION,
            SUBSCRIPT_EXPRESSION,
            THROW_EXPRESSION,
            UPDATE_EXPRESSION,
            VARIABLE_NAME);
    public static final Set<PhpNodeType> STATEMENT_SET = Set.of(
            BREAK_STATEMENT,
            CLASS_DECLARATION,
            COMPOUND_STATEMENT,
            CONST_DECLARATION,
            CONTINUE_STATEMENT,
            DECLARE_STATEMENT,
            DO_STATEMENT,
            ECHO_STATEMENT,
            EMPTY_STATEMENT,
            ENUM_DECLARATION,
            EXIT_STATEMENT,
            EXPRESSION_STATEMENT,
            FOREACH_STATEMENT,
            FOR_STATEMENT,
            FUNCTION_DEFINITION,
            FUNCTION_STATIC_DECLARATION,
            GLOBAL_DECLARATION,
            GOTO_STATEMENT,
            IF_STATEMENT,
            INTERFACE_DECLARATION,
            NAMED_LABEL_STATEMENT,
            NAMESPACE_DEFINITION,
            NAMESPACE_USE_DECLARATION,
            RETURN_STATEMENT,
            SWITCH_STATEMENT,
            TRAIT_DECLARATION,
            TRY_STATEMENT,
            UNSET_STATEMENT,
            WHILE_STATEMENT);
    public static final Set<PhpNodeType> TYPE_SET = Set.of(
            DISJUNCTIVE_NORMAL_FORM_TYPE, INTERSECTION_TYPE, NAMED_TYPE, OPTIONAL_TYPE, PRIMITIVE_TYPE, UNION_TYPE);

    private final @Nullable String type;

    PhpNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static PhpNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static PhpNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        PhpNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, PhpNodeType> LOOKUP = initLookup();

    private static Map<String, PhpNodeType> initLookup() {
        HashMap<String, PhpNodeType> m = new HashMap<>();
        for (PhpNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
