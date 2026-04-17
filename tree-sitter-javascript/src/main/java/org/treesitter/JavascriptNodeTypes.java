package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code javascript} from tree-sitter {@code node-types.json}.
 */
public final class JavascriptNodeTypes {
    private JavascriptNodeTypes() {}

    public static final String ARGUMENTS = "arguments";
    public static final String ARRAY = "array";
    public static final String ARRAY_PATTERN = "array_pattern";
    public static final String ARROW_FUNCTION = "arrow_function";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String ASSIGNMENT_PATTERN = "assignment_pattern";
    public static final String AUGMENTED_ASSIGNMENT_EXPRESSION = "augmented_assignment_expression";
    public static final String AWAIT_EXPRESSION = "await_expression";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CLASS_ = "class";
    public static final String CLASS_BODY = "class_body";
    public static final String CLASS_DECLARATION = "class_declaration";
    public static final String CLASS_HERITAGE = "class_heritage";
    public static final String CLASS_STATIC_BLOCK = "class_static_block";
    public static final String COMMENT = "comment";
    public static final String COMPUTED_PROPERTY_NAME = "computed_property_name";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String DEBUGGER_STATEMENT = "debugger_statement";
    public static final String DECLARATION = "declaration";
    public static final String DECORATOR = "decorator";
    public static final String DO_STATEMENT = "do_statement";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String EMPTY_STATEMENT = "empty_statement";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPORT_CLAUSE = "export_clause";
    public static final String EXPORT_SPECIFIER = "export_specifier";
    public static final String EXPORT_STATEMENT = "export_statement";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String FALSE = "false";
    public static final String FIELD_DEFINITION = "field_definition";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FORMAL_PARAMETERS = "formal_parameters";
    public static final String FOR_IN_STATEMENT = "for_in_statement";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FUNCTION_DECLARATION = "function_declaration";
    public static final String FUNCTION_EXPRESSION = "function_expression";
    public static final String GENERATOR_FUNCTION = "generator_function";
    public static final String GENERATOR_FUNCTION_DECLARATION = "generator_function_declaration";
    public static final String HASH_BANG_LINE = "hash_bang_line";
    public static final String HTML_CHARACTER_REFERENCE = "html_character_reference";
    public static final String HTML_COMMENT = "html_comment";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IMPORT_ = "import";
    public static final String IMPORT_ATTRIBUTE = "import_attribute";
    public static final String IMPORT_CLAUSE = "import_clause";
    public static final String IMPORT_SPECIFIER = "import_specifier";
    public static final String IMPORT_STATEMENT = "import_statement";
    public static final String JSX_ATTRIBUTE = "jsx_attribute";
    public static final String JSX_CLOSING_ELEMENT = "jsx_closing_element";
    public static final String JSX_ELEMENT = "jsx_element";
    public static final String JSX_EXPRESSION = "jsx_expression";
    public static final String JSX_NAMESPACE_NAME = "jsx_namespace_name";
    public static final String JSX_OPENING_ELEMENT = "jsx_opening_element";
    public static final String JSX_SELF_CLOSING_ELEMENT = "jsx_self_closing_element";
    public static final String JSX_TEXT = "jsx_text";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LEXICAL_DECLARATION = "lexical_declaration";
    public static final String MEMBER_EXPRESSION = "member_expression";
    public static final String META_PROPERTY = "meta_property";
    public static final String METHOD_DEFINITION = "method_definition";
    public static final String NAMED_IMPORTS = "named_imports";
    public static final String NAMESPACE_EXPORT = "namespace_export";
    public static final String NAMESPACE_IMPORT = "namespace_import";
    public static final String NEW_EXPRESSION = "new_expression";
    public static final String NULL = "null";
    public static final String NUMBER = "number";
    public static final String OBJECT = "object";
    public static final String OBJECT_ASSIGNMENT_PATTERN = "object_assignment_pattern";
    public static final String OBJECT_PATTERN = "object_pattern";
    public static final String OPTIONAL_CHAIN = "optional_chain";
    public static final String PAIR = "pair";
    public static final String PAIR_PATTERN = "pair_pattern";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PATTERN = "pattern";
    public static final String PRIMARY_EXPRESSION = "primary_expression";
    public static final String PRIVATE_PROPERTY_IDENTIFIER = "private_property_identifier";
    public static final String PROGRAM = "program";
    public static final String PROPERTY_IDENTIFIER = "property_identifier";
    public static final String REGEX = "regex";
    public static final String REGEX_FLAGS = "regex_flags";
    public static final String REGEX_PATTERN = "regex_pattern";
    public static final String REST_PATTERN = "rest_pattern";
    public static final String RETURN_STATEMENT = "return_statement";
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
    public static final String TEMPLATE_STRING = "template_string";
    public static final String TEMPLATE_SUBSTITUTION = "template_substitution";
    public static final String TERNARY_EXPRESSION = "ternary_expression";
    public static final String THIS_ = "this";
    public static final String THROW_STATEMENT = "throw_statement";
    public static final String TRUE = "true";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNDEFINED = "undefined";
    public static final String UPDATE_EXPRESSION = "update_expression";
    public static final String USING_DECLARATION = "using_declaration";
    public static final String VARIABLE_DECLARATION = "variable_declaration";
    public static final String VARIABLE_DECLARATOR = "variable_declarator";
    public static final String WHILE_STATEMENT = "while_statement";
    public static final String WITH_STATEMENT = "with_statement";
    public static final String YIELD_EXPRESSION = "yield_expression";

    public static final Set<String> DECLARATION_SET = Set.of(
            CLASS_DECLARATION,
            FUNCTION_DECLARATION,
            GENERATOR_FUNCTION_DECLARATION,
            LEXICAL_DECLARATION,
            USING_DECLARATION,
            VARIABLE_DECLARATION);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ASSIGNMENT_EXPRESSION,
            AUGMENTED_ASSIGNMENT_EXPRESSION,
            AWAIT_EXPRESSION,
            BINARY_EXPRESSION,
            JSX_ELEMENT,
            JSX_SELF_CLOSING_ELEMENT,
            NEW_EXPRESSION,
            PRIMARY_EXPRESSION,
            TERNARY_EXPRESSION,
            UNARY_EXPRESSION,
            UPDATE_EXPRESSION,
            YIELD_EXPRESSION);
    public static final Set<String> PATTERN_SET = Set.of(
            ARRAY_PATTERN,
            IDENTIFIER,
            MEMBER_EXPRESSION,
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
}
