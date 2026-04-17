package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code java} from tree-sitter {@code node-types.json}.
 */
public final class JavaNodeTypes {
    private JavaNodeTypes() {}

    public static final String ANNOTATED_TYPE = "annotated_type";
    public static final String ANNOTATION = "annotation";
    public static final String ANNOTATION_ARGUMENT_LIST = "annotation_argument_list";
    public static final String ANNOTATION_TYPE_BODY = "annotation_type_body";
    public static final String ANNOTATION_TYPE_DECLARATION = "annotation_type_declaration";
    public static final String ANNOTATION_TYPE_ELEMENT_DECLARATION = "annotation_type_element_declaration";
    public static final String ARGUMENT_LIST = "argument_list";
    public static final String ARRAY_ACCESS = "array_access";
    public static final String ARRAY_CREATION_EXPRESSION = "array_creation_expression";
    public static final String ARRAY_INITIALIZER = "array_initializer";
    public static final String ARRAY_TYPE = "array_type";
    public static final String ASSERT_STATEMENT = "assert_statement";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String ASTERISK = "asterisk";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BINARY_INTEGER_LITERAL = "binary_integer_literal";
    public static final String BLOCK = "block";
    public static final String BLOCK_COMMENT = "block_comment";
    public static final String BOOLEAN_TYPE = "boolean_type";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CAST_EXPRESSION = "cast_expression";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CATCH_FORMAL_PARAMETER = "catch_formal_parameter";
    public static final String CATCH_TYPE = "catch_type";
    public static final String CHARACTER_LITERAL = "character_literal";
    public static final String CLASS_BODY = "class_body";
    public static final String CLASS_DECLARATION = "class_declaration";
    public static final String CLASS_LITERAL = "class_literal";
    public static final String COMPACT_CONSTRUCTOR_DECLARATION = "compact_constructor_declaration";
    public static final String CONSTANT_DECLARATION = "constant_declaration";
    public static final String CONSTRUCTOR_BODY = "constructor_body";
    public static final String CONSTRUCTOR_DECLARATION = "constructor_declaration";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String DECIMAL_FLOATING_POINT_LITERAL = "decimal_floating_point_literal";
    public static final String DECIMAL_INTEGER_LITERAL = "decimal_integer_literal";
    public static final String DECLARATION = "declaration";
    public static final String DIMENSIONS = "dimensions";
    public static final String DIMENSIONS_EXPR = "dimensions_expr";
    public static final String DO_STATEMENT = "do_statement";
    public static final String ELEMENT_VALUE_ARRAY_INITIALIZER = "element_value_array_initializer";
    public static final String ELEMENT_VALUE_PAIR = "element_value_pair";
    public static final String ENHANCED_FOR_STATEMENT = "enhanced_for_statement";
    public static final String ENUM_BODY = "enum_body";
    public static final String ENUM_BODY_DECLARATIONS = "enum_body_declarations";
    public static final String ENUM_CONSTANT = "enum_constant";
    public static final String ENUM_DECLARATION = "enum_declaration";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPLICIT_CONSTRUCTOR_INVOCATION = "explicit_constructor_invocation";
    public static final String EXPORTS_MODULE_DIRECTIVE = "exports_module_directive";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String EXTENDS_INTERFACES = "extends_interfaces";
    public static final String FALSE = "false";
    public static final String FIELD_ACCESS = "field_access";
    public static final String FIELD_DECLARATION = "field_declaration";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FLOATING_POINT_TYPE = "floating_point_type";
    public static final String FORMAL_PARAMETER = "formal_parameter";
    public static final String FORMAL_PARAMETERS = "formal_parameters";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String GENERIC_TYPE = "generic_type";
    public static final String GUARD = "guard";
    public static final String HEX_FLOATING_POINT_LITERAL = "hex_floating_point_literal";
    public static final String HEX_INTEGER_LITERAL = "hex_integer_literal";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IMPORT_DECLARATION = "import_declaration";
    public static final String INFERRED_PARAMETERS = "inferred_parameters";
    public static final String INSTANCEOF_EXPRESSION = "instanceof_expression";
    public static final String INTEGRAL_TYPE = "integral_type";
    public static final String INTERFACE_BODY = "interface_body";
    public static final String INTERFACE_DECLARATION = "interface_declaration";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LAMBDA_EXPRESSION = "lambda_expression";
    public static final String LINE_COMMENT = "line_comment";
    public static final String LITERAL = "_literal";
    public static final String LOCAL_VARIABLE_DECLARATION = "local_variable_declaration";
    public static final String MARKER_ANNOTATION = "marker_annotation";
    public static final String METHOD_DECLARATION = "method_declaration";
    public static final String METHOD_INVOCATION = "method_invocation";
    public static final String METHOD_REFERENCE = "method_reference";
    public static final String MODIFIERS = "modifiers";
    public static final String MODULE_BODY = "module_body";
    public static final String MODULE_DECLARATION = "module_declaration";
    public static final String MODULE_DIRECTIVE = "module_directive";
    public static final String MULTILINE_STRING_FRAGMENT = "multiline_string_fragment";
    public static final String NULL_LITERAL = "null_literal";
    public static final String OBJECT_CREATION_EXPRESSION = "object_creation_expression";
    public static final String OCTAL_INTEGER_LITERAL = "octal_integer_literal";
    public static final String OPENS_MODULE_DIRECTIVE = "opens_module_directive";
    public static final String PACKAGE_DECLARATION = "package_declaration";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PATTERN = "pattern";
    public static final String PERMITS_ = "permits";
    public static final String PRIMARY_EXPRESSION = "primary_expression";
    public static final String PROGRAM = "program";
    public static final String PROVIDES_MODULE_DIRECTIVE = "provides_module_directive";
    public static final String RECEIVER_PARAMETER = "receiver_parameter";
    public static final String RECORD_DECLARATION = "record_declaration";
    public static final String RECORD_PATTERN = "record_pattern";
    public static final String RECORD_PATTERN_BODY = "record_pattern_body";
    public static final String RECORD_PATTERN_COMPONENT = "record_pattern_component";
    public static final String REQUIRES_MODIFIER = "requires_modifier";
    public static final String REQUIRES_MODULE_DIRECTIVE = "requires_module_directive";
    public static final String RESOURCE = "resource";
    public static final String RESOURCE_SPECIFICATION = "resource_specification";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SCOPED_IDENTIFIER = "scoped_identifier";
    public static final String SCOPED_TYPE_IDENTIFIER = "scoped_type_identifier";
    public static final String SIMPLE_TYPE = "_simple_type";
    public static final String SPREAD_PARAMETER = "spread_parameter";
    public static final String STATEMENT = "statement";
    public static final String STATIC_INITIALIZER = "static_initializer";
    public static final String STRING_FRAGMENT = "string_fragment";
    public static final String STRING_INTERPOLATION = "string_interpolation";
    public static final String STRING_LITERAL = "string_literal";
    public static final String SUPERCLASS = "superclass";
    public static final String SUPER_ = "super";
    public static final String SUPER_INTERFACES = "super_interfaces";
    public static final String SWITCH_BLOCK = "switch_block";
    public static final String SWITCH_BLOCK_STATEMENT_GROUP = "switch_block_statement_group";
    public static final String SWITCH_EXPRESSION = "switch_expression";
    public static final String SWITCH_LABEL = "switch_label";
    public static final String SWITCH_RULE = "switch_rule";
    public static final String SYNCHRONIZED_STATEMENT = "synchronized_statement";
    public static final String TEMPLATE_EXPRESSION = "template_expression";
    public static final String TERNARY_EXPRESSION = "ternary_expression";
    public static final String THIS_ = "this";
    public static final String THROWS_ = "throws";
    public static final String THROW_STATEMENT = "throw_statement";
    public static final String TRUE = "true";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String TRY_WITH_RESOURCES_STATEMENT = "try_with_resources_statement";
    public static final String TYPE = "_type";
    public static final String TYPE_ARGUMENTS = "type_arguments";
    public static final String TYPE_BOUND = "type_bound";
    public static final String TYPE_IDENTIFIER = "type_identifier";
    public static final String TYPE_LIST = "type_list";
    public static final String TYPE_PARAMETER = "type_parameter";
    public static final String TYPE_PARAMETERS = "type_parameters";
    public static final String TYPE_PATTERN = "type_pattern";
    public static final String UNANNOTATED_TYPE = "_unannotated_type";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNDERSCORE_PATTERN = "underscore_pattern";
    public static final String UPDATE_EXPRESSION = "update_expression";
    public static final String USES_MODULE_DIRECTIVE = "uses_module_directive";
    public static final String VARIABLE_DECLARATOR = "variable_declarator";
    public static final String VOID_TYPE = "void_type";
    public static final String WHILE_STATEMENT = "while_statement";
    public static final String WILDCARD = "wildcard";
    public static final String YIELD_STATEMENT = "yield_statement";

    public static final Set<String> DECLARATION_SET = Set.of(
            ANNOTATION_TYPE_DECLARATION,
            CLASS_DECLARATION,
            ENUM_DECLARATION,
            IMPORT_DECLARATION,
            INTERFACE_DECLARATION,
            MODULE_DECLARATION,
            PACKAGE_DECLARATION,
            RECORD_DECLARATION);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ASSIGNMENT_EXPRESSION,
            BINARY_EXPRESSION,
            CAST_EXPRESSION,
            INSTANCEOF_EXPRESSION,
            LAMBDA_EXPRESSION,
            PRIMARY_EXPRESSION,
            SWITCH_EXPRESSION,
            TERNARY_EXPRESSION,
            UNARY_EXPRESSION,
            UPDATE_EXPRESSION);
    public static final Set<String> LITERAL_SET = Set.of(
            BINARY_INTEGER_LITERAL,
            CHARACTER_LITERAL,
            DECIMAL_FLOATING_POINT_LITERAL,
            DECIMAL_INTEGER_LITERAL,
            FALSE,
            HEX_FLOATING_POINT_LITERAL,
            HEX_INTEGER_LITERAL,
            NULL_LITERAL,
            OCTAL_INTEGER_LITERAL,
            STRING_LITERAL,
            TRUE);
    public static final Set<String> MODULE_DIRECTIVE_SET = Set.of(
            EXPORTS_MODULE_DIRECTIVE,
            OPENS_MODULE_DIRECTIVE,
            PROVIDES_MODULE_DIRECTIVE,
            REQUIRES_MODULE_DIRECTIVE,
            USES_MODULE_DIRECTIVE);
    public static final Set<String> PRIMARY_EXPRESSION_SET = Set.of(
            ARRAY_ACCESS,
            ARRAY_CREATION_EXPRESSION,
            CLASS_LITERAL,
            FIELD_ACCESS,
            IDENTIFIER,
            LITERAL,
            METHOD_INVOCATION,
            METHOD_REFERENCE,
            OBJECT_CREATION_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            TEMPLATE_EXPRESSION,
            THIS_);
    public static final Set<String> SIMPLE_TYPE_SET = Set.of(
            BOOLEAN_TYPE,
            FLOATING_POINT_TYPE,
            GENERIC_TYPE,
            INTEGRAL_TYPE,
            SCOPED_TYPE_IDENTIFIER,
            TYPE_IDENTIFIER,
            VOID_TYPE);
    public static final Set<String> STATEMENT_SET = Set.of(
            ASSERT_STATEMENT,
            BLOCK,
            BREAK_STATEMENT,
            CONTINUE_STATEMENT,
            DECLARATION,
            DO_STATEMENT,
            ENHANCED_FOR_STATEMENT,
            EXPRESSION_STATEMENT,
            FOR_STATEMENT,
            IF_STATEMENT,
            LABELED_STATEMENT,
            LOCAL_VARIABLE_DECLARATION,
            RETURN_STATEMENT,
            SWITCH_EXPRESSION,
            SYNCHRONIZED_STATEMENT,
            THROW_STATEMENT,
            TRY_STATEMENT,
            TRY_WITH_RESOURCES_STATEMENT,
            WHILE_STATEMENT,
            YIELD_STATEMENT);
    public static final Set<String> TYPE_SET = Set.of(ANNOTATED_TYPE, UNANNOTATED_TYPE);
    public static final Set<String> UNANNOTATED_TYPE_SET = Set.of(ARRAY_TYPE, SIMPLE_TYPE);
}
