package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code php} from tree-sitter {@code node-types.json}.
 */
public final class PhpNodeTypes {
    private PhpNodeTypes() {}

    public static final String ABSTRACT_MODIFIER = "abstract_modifier";
    public static final String ANONYMOUS_CLASS = "anonymous_class";
    public static final String ANONYMOUS_FUNCTION = "anonymous_function";
    public static final String ANONYMOUS_FUNCTION_USE_CLAUSE = "anonymous_function_use_clause";
    public static final String ARGUMENT = "argument";
    public static final String ARGUMENTS = "arguments";
    public static final String ARRAY_CREATION_EXPRESSION = "array_creation_expression";
    public static final String ARRAY_ELEMENT_INITIALIZER = "array_element_initializer";
    public static final String ARROW_FUNCTION = "arrow_function";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String ATTRIBUTE = "attribute";
    public static final String ATTRIBUTE_GROUP = "attribute_group";
    public static final String ATTRIBUTE_LIST = "attribute_list";
    public static final String AUGMENTED_ASSIGNMENT_EXPRESSION = "augmented_assignment_expression";
    public static final String BASE_CLAUSE = "base_clause";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BOOLEAN_ = "boolean";
    public static final String BOTTOM_TYPE = "bottom_type";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String BY_REF = "by_ref";
    public static final String CASE_STATEMENT = "case_statement";
    public static final String CAST_EXPRESSION = "cast_expression";
    public static final String CAST_TYPE = "cast_type";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CLASS_CONSTANT_ACCESS_EXPRESSION = "class_constant_access_expression";
    public static final String CLASS_DECLARATION = "class_declaration";
    public static final String CLASS_INTERFACE_CLAUSE = "class_interface_clause";
    public static final String CLONE_EXPRESSION = "clone_expression";
    public static final String COLON_BLOCK = "colon_block";
    public static final String COMMENT = "comment";
    public static final String COMPOUND_STATEMENT = "compound_statement";
    public static final String CONDITIONAL_EXPRESSION = "conditional_expression";
    public static final String CONST_DECLARATION = "const_declaration";
    public static final String CONST_ELEMENT = "const_element";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String DECLARATION_LIST = "declaration_list";
    public static final String DECLARE_DIRECTIVE = "declare_directive";
    public static final String DECLARE_STATEMENT = "declare_statement";
    public static final String DEFAULT_STATEMENT = "default_statement";
    public static final String DISJUNCTIVE_NORMAL_FORM_TYPE = "disjunctive_normal_form_type";
    public static final String DO_STATEMENT = "do_statement";
    public static final String DYNAMIC_VARIABLE_NAME = "dynamic_variable_name";
    public static final String ECHO_STATEMENT = "echo_statement";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String ELSE_IF_CLAUSE = "else_if_clause";
    public static final String EMPTY_STATEMENT = "empty_statement";
    public static final String ENCAPSED_STRING = "encapsed_string";
    public static final String ENUM_CASE = "enum_case";
    public static final String ENUM_DECLARATION = "enum_declaration";
    public static final String ENUM_DECLARATION_LIST = "enum_declaration_list";
    public static final String ERROR_SUPPRESSION_EXPRESSION = "error_suppression_expression";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXIT_STATEMENT = "exit_statement";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FINAL_MODIFIER = "final_modifier";
    public static final String FLOAT_ = "float";
    public static final String FOREACH_STATEMENT = "foreach_statement";
    public static final String FORMAL_PARAMETERS = "formal_parameters";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_CALL_EXPRESSION = "function_call_expression";
    public static final String FUNCTION_DEFINITION = "function_definition";
    public static final String FUNCTION_STATIC_DECLARATION = "function_static_declaration";
    public static final String GLOBAL_DECLARATION = "global_declaration";
    public static final String GOTO_STATEMENT = "goto_statement";
    public static final String HEREDOC = "heredoc";
    public static final String HEREDOC_BODY = "heredoc_body";
    public static final String HEREDOC_END = "heredoc_end";
    public static final String HEREDOC_START = "heredoc_start";
    public static final String IF_STATEMENT = "if_statement";
    public static final String INCLUDE_EXPRESSION = "include_expression";
    public static final String INCLUDE_ONCE_EXPRESSION = "include_once_expression";
    public static final String INTEGER = "integer";
    public static final String INTERFACE_DECLARATION = "interface_declaration";
    public static final String INTERSECTION_TYPE = "intersection_type";
    public static final String LIST_LITERAL = "list_literal";
    public static final String LITERAL = "literal";
    public static final String MATCH_BLOCK = "match_block";
    public static final String MATCH_CONDITIONAL_EXPRESSION = "match_conditional_expression";
    public static final String MATCH_CONDITION_LIST = "match_condition_list";
    public static final String MATCH_DEFAULT_EXPRESSION = "match_default_expression";
    public static final String MATCH_EXPRESSION = "match_expression";
    public static final String MEMBER_ACCESS_EXPRESSION = "member_access_expression";
    public static final String MEMBER_CALL_EXPRESSION = "member_call_expression";
    public static final String METHOD_DECLARATION = "method_declaration";
    public static final String NAME = "name";
    public static final String NAMED_LABEL_STATEMENT = "named_label_statement";
    public static final String NAMED_TYPE = "named_type";
    public static final String NAMESPACE_DEFINITION = "namespace_definition";
    public static final String NAMESPACE_NAME = "namespace_name";
    public static final String NAMESPACE_USE_CLAUSE = "namespace_use_clause";
    public static final String NAMESPACE_USE_DECLARATION = "namespace_use_declaration";
    public static final String NAMESPACE_USE_GROUP = "namespace_use_group";
    public static final String NOWDOC = "nowdoc";
    public static final String NOWDOC_BODY = "nowdoc_body";
    public static final String NOWDOC_STRING = "nowdoc_string";
    public static final String NULL = "null";
    public static final String NULLSAFE_MEMBER_ACCESS_EXPRESSION = "nullsafe_member_access_expression";
    public static final String NULLSAFE_MEMBER_CALL_EXPRESSION = "nullsafe_member_call_expression";
    public static final String OBJECT_CREATION_EXPRESSION = "object_creation_expression";
    public static final String OPERATION = "operation";
    public static final String OPTIONAL_TYPE = "optional_type";
    public static final String PAIR = "pair";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PHP_END_TAG = "php_end_tag";
    public static final String PHP_TAG = "php_tag";
    public static final String PRIMARY_EXPRESSION = "primary_expression";
    public static final String PRIMITIVE_TYPE = "primitive_type";
    public static final String PRINT_INTRINSIC = "print_intrinsic";
    public static final String PROGRAM = "program";
    public static final String PROPERTY_DECLARATION = "property_declaration";
    public static final String PROPERTY_ELEMENT = "property_element";
    public static final String PROPERTY_HOOK = "property_hook";
    public static final String PROPERTY_HOOK_LIST = "property_hook_list";
    public static final String PROPERTY_PROMOTION_PARAMETER = "property_promotion_parameter";
    public static final String QUALIFIED_NAME = "qualified_name";
    public static final String READONLY_MODIFIER = "readonly_modifier";
    public static final String REFERENCE_ASSIGNMENT_EXPRESSION = "reference_assignment_expression";
    public static final String REFERENCE_MODIFIER = "reference_modifier";
    public static final String RELATIVE_NAME = "relative_name";
    public static final String RELATIVE_SCOPE = "relative_scope";
    public static final String REQUIRE_EXPRESSION = "require_expression";
    public static final String REQUIRE_ONCE_EXPRESSION = "require_once_expression";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SCOPED_CALL_EXPRESSION = "scoped_call_expression";
    public static final String SCOPED_PROPERTY_ACCESS_EXPRESSION = "scoped_property_access_expression";
    public static final String SEQUENCE_EXPRESSION = "sequence_expression";
    public static final String SHELL_COMMAND_EXPRESSION = "shell_command_expression";
    public static final String SIMPLE_PARAMETER = "simple_parameter";
    public static final String STATEMENT = "statement";
    public static final String STATIC_MODIFIER = "static_modifier";
    public static final String STATIC_VARIABLE_DECLARATION = "static_variable_declaration";
    public static final String STRING = "string";
    public static final String STRING_CONTENT = "string_content";
    public static final String SUBSCRIPT_EXPRESSION = "subscript_expression";
    public static final String SWITCH_BLOCK = "switch_block";
    public static final String SWITCH_STATEMENT = "switch_statement";
    public static final String TEXT = "text";
    public static final String TEXT_INTERPOLATION = "text_interpolation";
    public static final String THROW_EXPRESSION = "throw_expression";
    public static final String TRAIT_DECLARATION = "trait_declaration";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String TYPE = "type";
    public static final String TYPE_LIST = "type_list";
    public static final String UNARY_OP_EXPRESSION = "unary_op_expression";
    public static final String UNION_TYPE = "union_type";
    public static final String UNSET_STATEMENT = "unset_statement";
    public static final String UPDATE_EXPRESSION = "update_expression";
    public static final String USE_AS_CLAUSE = "use_as_clause";
    public static final String USE_DECLARATION = "use_declaration";
    public static final String USE_INSTEAD_OF_CLAUSE = "use_instead_of_clause";
    public static final String USE_LIST = "use_list";
    public static final String VARIABLE_NAME = "variable_name";
    public static final String VARIADIC_PARAMETER = "variadic_parameter";
    public static final String VARIADIC_PLACEHOLDER = "variadic_placeholder";
    public static final String VARIADIC_UNPACKING = "variadic_unpacking";
    public static final String VAR_MODIFIER = "var_modifier";
    public static final String VISIBILITY_MODIFIER = "visibility_modifier";
    public static final String WHILE_STATEMENT = "while_statement";
    public static final String YIELD_EXPRESSION = "yield_expression";

    public static final Set<String> EXPRESSION_SET = Set.of(
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
    public static final Set<String> LITERAL_SET =
            Set.of(BOOLEAN_, ENCAPSED_STRING, FLOAT_, HEREDOC, INTEGER, NOWDOC, NULL, STRING);
    public static final Set<String> PRIMARY_EXPRESSION_SET = Set.of(
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
    public static final Set<String> STATEMENT_SET = Set.of(
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
    public static final Set<String> TYPE_SET = Set.of(
            DISJUNCTIVE_NORMAL_FORM_TYPE, INTERSECTION_TYPE, NAMED_TYPE, OPTIONAL_TYPE, PRIMITIVE_TYPE, UNION_TYPE);
}
