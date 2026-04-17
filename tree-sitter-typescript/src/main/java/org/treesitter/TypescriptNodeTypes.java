package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code typescript} from tree-sitter {@code node-types.json}.
 */
public final class TypescriptNodeTypes {
    private TypescriptNodeTypes() {}

    public static final String ABSTRACT_CLASS_DECLARATION = "abstract_class_declaration";
    public static final String ABSTRACT_METHOD_SIGNATURE = "abstract_method_signature";
    public static final String ACCESSIBILITY_MODIFIER = "accessibility_modifier";
    public static final String ADDING_TYPE_ANNOTATION = "adding_type_annotation";
    public static final String AMBIENT_DECLARATION = "ambient_declaration";
    public static final String ARGUMENTS = "arguments";
    public static final String ARRAY = "array";
    public static final String ARRAY_PATTERN = "array_pattern";
    public static final String ARRAY_TYPE = "array_type";
    public static final String ARROW_FUNCTION = "arrow_function";
    public static final String ASSERTS = "asserts";
    public static final String ASSERTS_ANNOTATION = "asserts_annotation";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String ASSIGNMENT_PATTERN = "assignment_pattern";
    public static final String AS_EXPRESSION = "as_expression";
    public static final String AUGMENTED_ASSIGNMENT_EXPRESSION = "augmented_assignment_expression";
    public static final String AWAIT_EXPRESSION = "await_expression";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CALL_SIGNATURE = "call_signature";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CLASS_ = "class";
    public static final String CLASS_BODY = "class_body";
    public static final String CLASS_DECLARATION = "class_declaration";
    public static final String CLASS_HERITAGE = "class_heritage";
    public static final String CLASS_STATIC_BLOCK = "class_static_block";
    public static final String COMMENT = "comment";
    public static final String COMPUTED_PROPERTY_NAME = "computed_property_name";
    public static final String CONDITIONAL_TYPE = "conditional_type";
    public static final String CONSTRAINT = "constraint";
    public static final String CONSTRUCTOR_TYPE = "constructor_type";
    public static final String CONSTRUCT_SIGNATURE = "construct_signature";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String DEBUGGER_STATEMENT = "debugger_statement";
    public static final String DECLARATION = "declaration";
    public static final String DECORATOR = "decorator";
    public static final String DEFAULT_TYPE = "default_type";
    public static final String DO_STATEMENT = "do_statement";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String EMPTY_STATEMENT = "empty_statement";
    public static final String ENUM_ASSIGNMENT = "enum_assignment";
    public static final String ENUM_BODY = "enum_body";
    public static final String ENUM_DECLARATION = "enum_declaration";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXISTENTIAL_TYPE = "existential_type";
    public static final String EXPORT_CLAUSE = "export_clause";
    public static final String EXPORT_SPECIFIER = "export_specifier";
    public static final String EXPORT_STATEMENT = "export_statement";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String EXTENDS_CLAUSE = "extends_clause";
    public static final String EXTENDS_TYPE_CLAUSE = "extends_type_clause";
    public static final String FALSE = "false";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FLOW_MAYBE_TYPE = "flow_maybe_type";
    public static final String FORMAL_PARAMETERS = "formal_parameters";
    public static final String FOR_IN_STATEMENT = "for_in_statement";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_DECLARATION = "function_declaration";
    public static final String FUNCTION_EXPRESSION = "function_expression";
    public static final String FUNCTION_SIGNATURE = "function_signature";
    public static final String FUNCTION_TYPE = "function_type";
    public static final String GENERATOR_FUNCTION = "generator_function";
    public static final String GENERATOR_FUNCTION_DECLARATION = "generator_function_declaration";
    public static final String GENERIC_TYPE = "generic_type";
    public static final String HASH_BANG_LINE = "hash_bang_line";
    public static final String HTML_COMMENT = "html_comment";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IMPLEMENTS_CLAUSE = "implements_clause";
    public static final String IMPORT_ = "import";
    public static final String IMPORT_ALIAS = "import_alias";
    public static final String IMPORT_ATTRIBUTE = "import_attribute";
    public static final String IMPORT_CLAUSE = "import_clause";
    public static final String IMPORT_REQUIRE_CLAUSE = "import_require_clause";
    public static final String IMPORT_SPECIFIER = "import_specifier";
    public static final String IMPORT_STATEMENT = "import_statement";
    public static final String INDEX_SIGNATURE = "index_signature";
    public static final String INDEX_TYPE_QUERY = "index_type_query";
    public static final String INFER_TYPE = "infer_type";
    public static final String INSTANTIATION_EXPRESSION = "instantiation_expression";
    public static final String INTERFACE_BODY = "interface_body";
    public static final String INTERFACE_DECLARATION = "interface_declaration";
    public static final String INTERNAL_MODULE = "internal_module";
    public static final String INTERSECTION_TYPE = "intersection_type";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LEXICAL_DECLARATION = "lexical_declaration";
    public static final String LITERAL_TYPE = "literal_type";
    public static final String LOOKUP_TYPE = "lookup_type";
    public static final String MAPPED_TYPE_CLAUSE = "mapped_type_clause";
    public static final String MEMBER_EXPRESSION = "member_expression";
    public static final String META_PROPERTY = "meta_property";
    public static final String METHOD_DEFINITION = "method_definition";
    public static final String METHOD_SIGNATURE = "method_signature";
    public static final String MODULE = "module";
    public static final String NAMED_IMPORTS = "named_imports";
    public static final String NAMESPACE_EXPORT = "namespace_export";
    public static final String NAMESPACE_IMPORT = "namespace_import";
    public static final String NESTED_IDENTIFIER = "nested_identifier";
    public static final String NESTED_TYPE_IDENTIFIER = "nested_type_identifier";
    public static final String NEW_EXPRESSION = "new_expression";
    public static final String NON_NULL_EXPRESSION = "non_null_expression";
    public static final String NULL = "null";
    public static final String NUMBER = "number";
    public static final String OBJECT = "object";
    public static final String OBJECT_ASSIGNMENT_PATTERN = "object_assignment_pattern";
    public static final String OBJECT_PATTERN = "object_pattern";
    public static final String OBJECT_TYPE = "object_type";
    public static final String OMITTING_TYPE_ANNOTATION = "omitting_type_annotation";
    public static final String OPTING_TYPE_ANNOTATION = "opting_type_annotation";
    public static final String OPTIONAL_CHAIN = "optional_chain";
    public static final String OPTIONAL_PARAMETER = "optional_parameter";
    public static final String OPTIONAL_TYPE = "optional_type";
    public static final String OVERRIDE_MODIFIER = "override_modifier";
    public static final String PAIR = "pair";
    public static final String PAIR_PATTERN = "pair_pattern";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PARENTHESIZED_TYPE = "parenthesized_type";
    public static final String PATTERN = "pattern";
    public static final String PREDEFINED_TYPE = "predefined_type";
    public static final String PRIMARY_EXPRESSION = "primary_expression";
    public static final String PRIMARY_TYPE = "primary_type";
    public static final String PRIVATE_PROPERTY_IDENTIFIER = "private_property_identifier";
    public static final String PROGRAM = "program";
    public static final String PROPERTY_IDENTIFIER = "property_identifier";
    public static final String PROPERTY_SIGNATURE = "property_signature";
    public static final String PUBLIC_FIELD_DEFINITION = "public_field_definition";
    public static final String READONLY_TYPE = "readonly_type";
    public static final String REGEX = "regex";
    public static final String REGEX_FLAGS = "regex_flags";
    public static final String REGEX_PATTERN = "regex_pattern";
    public static final String REQUIRED_PARAMETER = "required_parameter";
    public static final String REST_PATTERN = "rest_pattern";
    public static final String REST_TYPE = "rest_type";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SATISFIES_EXPRESSION = "satisfies_expression";
    public static final String SEQUENCE_EXPRESSION = "sequence_expression";
    public static final String SHORTHAND_PROPERTY_IDENTIFIER = "shorthand_property_identifier";
    public static final String SHORTHAND_PROPERTY_IDENTIFIER_PATTERN = "shorthand_property_identifier_pattern";
    public static final String SPREAD_ELEMENT = "spread_element";
    public static final String STATEMENT = "statement";
    public static final String STATEMENT_BLOCK = "statement_block";
    public static final String STATEMENT_IDENTIFIER = "statement_identifier";
    public static final String STRING = "string";
    public static final String STRING_FRAGMENT = "string_fragment";
    public static final String SUBSCRIPT_EXPRESSION = "subscript_expression";
    public static final String SUPER_ = "super";
    public static final String SWITCH_BODY = "switch_body";
    public static final String SWITCH_CASE = "switch_case";
    public static final String SWITCH_DEFAULT = "switch_default";
    public static final String SWITCH_STATEMENT = "switch_statement";
    public static final String TEMPLATE_LITERAL_TYPE = "template_literal_type";
    public static final String TEMPLATE_STRING = "template_string";
    public static final String TEMPLATE_SUBSTITUTION = "template_substitution";
    public static final String TEMPLATE_TYPE = "template_type";
    public static final String TERNARY_EXPRESSION = "ternary_expression";
    public static final String THIS_ = "this";
    public static final String THIS_TYPE = "this_type";
    public static final String THROW_STATEMENT = "throw_statement";
    public static final String TRUE = "true";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String TUPLE_TYPE = "tuple_type";
    public static final String TYPE = "type";
    public static final String TYPE_ALIAS_DECLARATION = "type_alias_declaration";
    public static final String TYPE_ANNOTATION = "type_annotation";
    public static final String TYPE_ARGUMENTS = "type_arguments";
    public static final String TYPE_ASSERTION = "type_assertion";
    public static final String TYPE_IDENTIFIER = "type_identifier";
    public static final String TYPE_PARAMETER = "type_parameter";
    public static final String TYPE_PARAMETERS = "type_parameters";
    public static final String TYPE_PREDICATE = "type_predicate";
    public static final String TYPE_PREDICATE_ANNOTATION = "type_predicate_annotation";
    public static final String TYPE_QUERY = "type_query";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNDEFINED = "undefined";
    public static final String UNION_TYPE = "union_type";
    public static final String UPDATE_EXPRESSION = "update_expression";
    public static final String VARIABLE_DECLARATION = "variable_declaration";
    public static final String VARIABLE_DECLARATOR = "variable_declarator";
    public static final String WHILE_STATEMENT = "while_statement";
    public static final String WITH_STATEMENT = "with_statement";
    public static final String YIELD_EXPRESSION = "yield_expression";

    public static final Set<String> DECLARATION_SET = Set.of(
            ABSTRACT_CLASS_DECLARATION,
            AMBIENT_DECLARATION,
            CLASS_DECLARATION,
            ENUM_DECLARATION,
            FUNCTION_DECLARATION,
            FUNCTION_SIGNATURE,
            GENERATOR_FUNCTION_DECLARATION,
            IMPORT_ALIAS,
            INTERFACE_DECLARATION,
            INTERNAL_MODULE,
            LEXICAL_DECLARATION,
            MODULE,
            TYPE_ALIAS_DECLARATION,
            VARIABLE_DECLARATION);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ASSIGNMENT_EXPRESSION,
            AS_EXPRESSION,
            AUGMENTED_ASSIGNMENT_EXPRESSION,
            AWAIT_EXPRESSION,
            BINARY_EXPRESSION,
            INSTANTIATION_EXPRESSION,
            INTERNAL_MODULE,
            NEW_EXPRESSION,
            PRIMARY_EXPRESSION,
            SATISFIES_EXPRESSION,
            TERNARY_EXPRESSION,
            TYPE_ASSERTION,
            UNARY_EXPRESSION,
            UPDATE_EXPRESSION,
            YIELD_EXPRESSION);
    public static final Set<String> PATTERN_SET = Set.of(
            ARRAY_PATTERN,
            IDENTIFIER,
            MEMBER_EXPRESSION,
            NON_NULL_EXPRESSION,
            OBJECT_PATTERN,
            REST_PATTERN,
            SUBSCRIPT_EXPRESSION,
            UNDEFINED);
    public static final Set<String> PRIMARY_EXPRESSION_SET = Set.of(
            ARRAY,
            ARROW_FUNCTION,
            CALL_EXPRESSION,
            CLASS_,
            FALSE,
            FUNCTION_EXPRESSION,
            GENERATOR_FUNCTION,
            IDENTIFIER,
            MEMBER_EXPRESSION,
            META_PROPERTY,
            NON_NULL_EXPRESSION,
            NULL,
            NUMBER,
            OBJECT,
            PARENTHESIZED_EXPRESSION,
            REGEX,
            STRING,
            SUBSCRIPT_EXPRESSION,
            SUPER_,
            TEMPLATE_STRING,
            THIS_,
            TRUE,
            UNDEFINED);
    public static final Set<String> PRIMARY_TYPE_SET = Set.of(
            ARRAY_TYPE,
            CONDITIONAL_TYPE,
            EXISTENTIAL_TYPE,
            FLOW_MAYBE_TYPE,
            GENERIC_TYPE,
            INDEX_TYPE_QUERY,
            INTERSECTION_TYPE,
            LITERAL_TYPE,
            LOOKUP_TYPE,
            NESTED_TYPE_IDENTIFIER,
            OBJECT_TYPE,
            PARENTHESIZED_TYPE,
            PREDEFINED_TYPE,
            TEMPLATE_LITERAL_TYPE,
            THIS_TYPE,
            TUPLE_TYPE,
            TYPE_IDENTIFIER,
            TYPE_QUERY,
            UNION_TYPE);
    public static final Set<String> STATEMENT_SET = Set.of(
            BREAK_STATEMENT,
            CONTINUE_STATEMENT,
            DEBUGGER_STATEMENT,
            DECLARATION,
            DO_STATEMENT,
            EMPTY_STATEMENT,
            EXPORT_STATEMENT,
            EXPRESSION_STATEMENT,
            FOR_IN_STATEMENT,
            FOR_STATEMENT,
            IF_STATEMENT,
            IMPORT_STATEMENT,
            LABELED_STATEMENT,
            RETURN_STATEMENT,
            STATEMENT_BLOCK,
            SWITCH_STATEMENT,
            THROW_STATEMENT,
            TRY_STATEMENT,
            WHILE_STATEMENT,
            WITH_STATEMENT);
    public static final Set<String> TYPE_SET = Set.of(
            CALL_EXPRESSION,
            CONSTRUCTOR_TYPE,
            FUNCTION_TYPE,
            INFER_TYPE,
            MEMBER_EXPRESSION,
            PRIMARY_TYPE,
            READONLY_TYPE);
}
