package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code scala} from tree-sitter {@code node-types.json}.
 */
public final class ScalaNodeTypes {
    private ScalaNodeTypes() {}

    public static final String ACCESS_MODIFIER = "access_modifier";
    public static final String ACCESS_QUALIFIER = "access_qualifier";
    public static final String ALTERNATIVE_PATTERN = "alternative_pattern";
    public static final String ANNOTATED_TYPE = "annotated_type";
    public static final String ANNOTATION = "annotation";
    public static final String ARGUMENTS = "arguments";
    public static final String ARROW_RENAMED_IDENTIFIER = "arrow_renamed_identifier";
    public static final String ASCRIPTION_EXPRESSION = "ascription_expression";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String AS_RENAMED_IDENTIFIER = "as_renamed_identifier";
    public static final String BINDING = "binding";
    public static final String BINDINGS = "bindings";
    public static final String BLOCK = "block";
    public static final String BLOCK_COMMENT = "block_comment";
    public static final String BOOLEAN_LITERAL = "boolean_literal";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CAPTURE_PATTERN = "capture_pattern";
    public static final String CASE_BLOCK = "case_block";
    public static final String CASE_CLASS_PATTERN = "case_class_pattern";
    public static final String CASE_CLAUSE = "case_clause";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CHARACTER_LITERAL = "character_literal";
    public static final String CLASS_DEFINITION = "class_definition";
    public static final String CLASS_PARAMETER = "class_parameter";
    public static final String CLASS_PARAMETERS = "class_parameters";
    public static final String COLON_ARGUMENT = "colon_argument";
    public static final String COMMENT = "comment";
    public static final String COMPILATION_UNIT = "compilation_unit";
    public static final String COMPOUND_TYPE = "compound_type";
    public static final String CONTEXT_BOUND = "context_bound";
    public static final String CONTRAVARIANT_TYPE_PARAMETER = "contravariant_type_parameter";
    public static final String COVARIANT_TYPE_PARAMETER = "covariant_type_parameter";
    public static final String DEFINITION = "_definition";
    public static final String DERIVES_CLAUSE = "derives_clause";
    public static final String DO_WHILE_EXPRESSION = "do_while_expression";
    public static final String ENUMERATOR = "enumerator";
    public static final String ENUMERATORS = "enumerators";
    public static final String ENUM_BODY = "enum_body";
    public static final String ENUM_CASE_DEFINITIONS = "enum_case_definitions";
    public static final String ENUM_DEFINITION = "enum_definition";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPORT_DECLARATION = "export_declaration";
    public static final String EXPRESSION = "expression";
    public static final String EXTENDS_CLAUSE = "extends_clause";
    public static final String EXTENSION_DEFINITION = "extension_definition";
    public static final String FIELD_EXPRESSION = "field_expression";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FLOATING_POINT_LITERAL = "floating_point_literal";
    public static final String FOR_EXPRESSION = "for_expression";
    public static final String FULL_ENUM_CASE = "full_enum_case";
    public static final String FUNCTION_DECLARATION = "function_declaration";
    public static final String FUNCTION_DEFINITION = "function_definition";
    public static final String FUNCTION_TYPE = "function_type";
    public static final String GENERIC_FUNCTION = "generic_function";
    public static final String GENERIC_TYPE = "generic_type";
    public static final String GIVEN_CONDITIONAL = "given_conditional";
    public static final String GIVEN_DEFINITION = "given_definition";
    public static final String GIVEN_PATTERN = "given_pattern";
    public static final String GUARD = "guard";
    public static final String IDENTIFIER = "identifier";
    public static final String IDENTIFIERS = "identifiers";
    public static final String IF_EXPRESSION = "if_expression";
    public static final String IMPORT_DECLARATION = "import_declaration";
    public static final String INDENTED_BLOCK = "indented_block";
    public static final String INDENTED_CASES = "indented_cases";
    public static final String INFIX_EXPRESSION = "infix_expression";
    public static final String INFIX_MODIFIER = "infix_modifier";
    public static final String INFIX_PATTERN = "infix_pattern";
    public static final String INFIX_TYPE = "infix_type";
    public static final String INLINE_MODIFIER = "inline_modifier";
    public static final String INSTANCE_EXPRESSION = "instance_expression";
    public static final String INTEGER_LITERAL = "integer_literal";
    public static final String INTERPOLATED_STRING = "interpolated_string";
    public static final String INTERPOLATED_STRING_EXPRESSION = "interpolated_string_expression";
    public static final String INTERPOLATION = "interpolation";
    public static final String LAMBDA_EXPRESSION = "lambda_expression";
    public static final String LAZY_PARAMETER_TYPE = "lazy_parameter_type";
    public static final String LITERAL_TYPE = "literal_type";
    public static final String LOWER_BOUND = "lower_bound";
    public static final String MACRO_BODY = "macro_body";
    public static final String MATCH_EXPRESSION = "match_expression";
    public static final String MATCH_TYPE = "match_type";
    public static final String MODIFIERS = "modifiers";
    public static final String NAMED_PATTERN = "named_pattern";
    public static final String NAMED_TUPLE_PATTERN = "named_tuple_pattern";
    public static final String NAMED_TUPLE_TYPE = "named_tuple_type";
    public static final String NAMESPACE_SELECTORS = "namespace_selectors";
    public static final String NAMESPACE_WILDCARD = "namespace_wildcard";
    public static final String NAME_AND_TYPE = "name_and_type";
    public static final String NULL_LITERAL = "null_literal";
    public static final String OBJECT_DEFINITION = "object_definition";
    public static final String OPAQUE_MODIFIER = "opaque_modifier";
    public static final String OPEN_MODIFIER = "open_modifier";
    public static final String OPERATOR_IDENTIFIER = "operator_identifier";
    public static final String PACKAGE_CLAUSE = "package_clause";
    public static final String PACKAGE_IDENTIFIER = "package_identifier";
    public static final String PACKAGE_OBJECT = "package_object";
    public static final String PARAMETER = "parameter";
    public static final String PARAMETERS = "parameters";
    public static final String PARAMETER_TYPES = "parameter_types";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PATTERN = "_pattern";
    public static final String POSTFIX_EXPRESSION = "postfix_expression";
    public static final String PREFIX_EXPRESSION = "prefix_expression";
    public static final String PROJECTED_TYPE = "projected_type";
    public static final String QUOTE_EXPRESSION = "quote_expression";
    public static final String REFINEMENT = "refinement";
    public static final String REPEATED_PARAMETER_TYPE = "repeated_parameter_type";
    public static final String REPEAT_PATTERN = "repeat_pattern";
    public static final String RETURN_EXPRESSION = "return_expression";
    public static final String SELF_TYPE = "self_type";
    public static final String SIMPLE_ENUM_CASE = "simple_enum_case";
    public static final String SINGLETON_TYPE = "singleton_type";
    public static final String SPLICE_EXPRESSION = "splice_expression";
    public static final String STABLE_IDENTIFIER = "stable_identifier";
    public static final String STABLE_TYPE_IDENTIFIER = "stable_type_identifier";
    public static final String STRING = "string";
    public static final String STRUCTURAL_TYPE = "structural_type";
    public static final String TEMPLATE_BODY = "template_body";
    public static final String THROW_EXPRESSION = "throw_expression";
    public static final String TRAIT_DEFINITION = "trait_definition";
    public static final String TRANSPARENT_MODIFIER = "transparent_modifier";
    public static final String TRY_EXPRESSION = "try_expression";
    public static final String TUPLE_EXPRESSION = "tuple_expression";
    public static final String TUPLE_PATTERN = "tuple_pattern";
    public static final String TUPLE_TYPE = "tuple_type";
    public static final String TYPED_PATTERN = "typed_pattern";
    public static final String TYPE_ARGUMENTS = "type_arguments";
    public static final String TYPE_CASE_CLAUSE = "type_case_clause";
    public static final String TYPE_DEFINITION = "type_definition";
    public static final String TYPE_LAMBDA = "type_lambda";
    public static final String TYPE_PARAMETERS = "type_parameters";
    public static final String UNIT = "unit";
    public static final String UPPER_BOUND = "upper_bound";
    public static final String USING_DIRECTIVE = "using_directive";
    public static final String USING_DIRECTIVE_KEY = "using_directive_key";
    public static final String USING_DIRECTIVE_VALUE = "using_directive_value";
    public static final String VAL_DECLARATION = "val_declaration";
    public static final String VAL_DEFINITION = "val_definition";
    public static final String VAR_DECLARATION = "var_declaration";
    public static final String VAR_DEFINITION = "var_definition";
    public static final String VIEW_BOUND = "view_bound";
    public static final String WHILE_EXPRESSION = "while_expression";
    public static final String WILDCARD = "wildcard";
    public static final String WITH_TEMPLATE_BODY = "with_template_body";

    public static final Set<String> DEFINITION_SET = Set.of(
            CLASS_DEFINITION,
            ENUM_DEFINITION,
            EXPORT_DECLARATION,
            EXTENSION_DEFINITION,
            FUNCTION_DECLARATION,
            FUNCTION_DEFINITION,
            GIVEN_DEFINITION,
            IMPORT_DECLARATION,
            OBJECT_DEFINITION,
            PACKAGE_CLAUSE,
            PACKAGE_OBJECT,
            TRAIT_DEFINITION,
            TYPE_DEFINITION,
            VAL_DECLARATION,
            VAL_DEFINITION,
            VAR_DECLARATION,
            VAR_DEFINITION);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ASCRIPTION_EXPRESSION,
            ASSIGNMENT_EXPRESSION,
            BLOCK,
            BOOLEAN_LITERAL,
            CALL_EXPRESSION,
            CASE_BLOCK,
            CHARACTER_LITERAL,
            DO_WHILE_EXPRESSION,
            FIELD_EXPRESSION,
            FLOATING_POINT_LITERAL,
            FOR_EXPRESSION,
            GENERIC_FUNCTION,
            IDENTIFIER,
            IF_EXPRESSION,
            INFIX_EXPRESSION,
            INSTANCE_EXPRESSION,
            INTEGER_LITERAL,
            INTERPOLATED_STRING_EXPRESSION,
            LAMBDA_EXPRESSION,
            MACRO_BODY,
            MATCH_EXPRESSION,
            NULL_LITERAL,
            OPERATOR_IDENTIFIER,
            PARENTHESIZED_EXPRESSION,
            POSTFIX_EXPRESSION,
            PREFIX_EXPRESSION,
            QUOTE_EXPRESSION,
            RETURN_EXPRESSION,
            SPLICE_EXPRESSION,
            STRING,
            THROW_EXPRESSION,
            TRY_EXPRESSION,
            TUPLE_EXPRESSION,
            UNIT,
            WHILE_EXPRESSION,
            WILDCARD);
    public static final Set<String> PATTERN_SET = Set.of(
            ALTERNATIVE_PATTERN,
            BOOLEAN_LITERAL,
            CAPTURE_PATTERN,
            CASE_CLASS_PATTERN,
            CHARACTER_LITERAL,
            FLOATING_POINT_LITERAL,
            GIVEN_PATTERN,
            IDENTIFIER,
            INFIX_PATTERN,
            INTEGER_LITERAL,
            INTERPOLATED_STRING_EXPRESSION,
            NAMED_TUPLE_PATTERN,
            NULL_LITERAL,
            OPERATOR_IDENTIFIER,
            QUOTE_EXPRESSION,
            REPEAT_PATTERN,
            STABLE_IDENTIFIER,
            STRING,
            TUPLE_PATTERN,
            TYPED_PATTERN,
            WILDCARD);
}
