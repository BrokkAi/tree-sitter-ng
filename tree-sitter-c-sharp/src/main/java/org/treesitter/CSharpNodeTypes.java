package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code c-sharp} from tree-sitter {@code node-types.json}.
 */
public final class CSharpNodeTypes {
    private CSharpNodeTypes() {}

    public static final String ACCESSOR_DECLARATION = "accessor_declaration";
    public static final String ACCESSOR_LIST = "accessor_list";
    public static final String ALIAS_QUALIFIED_NAME = "alias_qualified_name";
    public static final String AND_PATTERN = "and_pattern";
    public static final String ANONYMOUS_METHOD_EXPRESSION = "anonymous_method_expression";
    public static final String ANONYMOUS_OBJECT_CREATION_EXPRESSION = "anonymous_object_creation_expression";
    public static final String ARGUMENT = "argument";
    public static final String ARGUMENT_LIST = "argument_list";
    public static final String ARRAY_CREATION_EXPRESSION = "array_creation_expression";
    public static final String ARRAY_RANK_SPECIFIER = "array_rank_specifier";
    public static final String ARRAY_TYPE = "array_type";
    public static final String ARROW_EXPRESSION_CLAUSE = "arrow_expression_clause";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String AS_EXPRESSION = "as_expression";
    public static final String ATTRIBUTE = "attribute";
    public static final String ATTRIBUTE_ARGUMENT = "attribute_argument";
    public static final String ATTRIBUTE_ARGUMENT_LIST = "attribute_argument_list";
    public static final String ATTRIBUTE_LIST = "attribute_list";
    public static final String ATTRIBUTE_TARGET_SPECIFIER = "attribute_target_specifier";
    public static final String AWAIT_EXPRESSION = "await_expression";
    public static final String BASE_LIST = "base_list";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BLOCK = "block";
    public static final String BOOLEAN_LITERAL = "boolean_literal";
    public static final String BRACKETED_ARGUMENT_LIST = "bracketed_argument_list";
    public static final String BRACKETED_PARAMETER_LIST = "bracketed_parameter_list";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CALLING_CONVENTION = "calling_convention";
    public static final String CAST_EXPRESSION = "cast_expression";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CATCH_DECLARATION = "catch_declaration";
    public static final String CATCH_FILTER_CLAUSE = "catch_filter_clause";
    public static final String CHARACTER_LITERAL = "character_literal";
    public static final String CHARACTER_LITERAL_CONTENT = "character_literal_content";
    public static final String CHECKED_EXPRESSION = "checked_expression";
    public static final String CHECKED_STATEMENT = "checked_statement";
    public static final String CLASS_DECLARATION = "class_declaration";
    public static final String COMMENT = "comment";
    public static final String COMPILATION_UNIT = "compilation_unit";
    public static final String CONDITIONAL_ACCESS_EXPRESSION = "conditional_access_expression";
    public static final String CONDITIONAL_EXPRESSION = "conditional_expression";
    public static final String CONSTANT_PATTERN = "constant_pattern";
    public static final String CONSTRUCTOR_CONSTRAINT = "constructor_constraint";
    public static final String CONSTRUCTOR_DECLARATION = "constructor_declaration";
    public static final String CONSTRUCTOR_INITIALIZER = "constructor_initializer";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String CONVERSION_OPERATOR_DECLARATION = "conversion_operator_declaration";
    public static final String DECLARATION = "declaration";
    public static final String DECLARATION_EXPRESSION = "declaration_expression";
    public static final String DECLARATION_LIST = "declaration_list";
    public static final String DECLARATION_PATTERN = "declaration_pattern";
    public static final String DEFAULT_EXPRESSION = "default_expression";
    public static final String DELEGATE_DECLARATION = "delegate_declaration";
    public static final String DESTRUCTOR_DECLARATION = "destructor_declaration";
    public static final String DISCARD = "discard";
    public static final String DO_STATEMENT = "do_statement";
    public static final String ELEMENT_ACCESS_EXPRESSION = "element_access_expression";
    public static final String ELEMENT_BINDING_EXPRESSION = "element_binding_expression";
    public static final String EMPTY_STATEMENT = "empty_statement";
    public static final String ENUM_DECLARATION = "enum_declaration";
    public static final String ENUM_MEMBER_DECLARATION = "enum_member_declaration";
    public static final String ENUM_MEMBER_DECLARATION_LIST = "enum_member_declaration_list";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EVENT_DECLARATION = "event_declaration";
    public static final String EVENT_FIELD_DECLARATION = "event_field_declaration";
    public static final String EXPLICIT_INTERFACE_SPECIFIER = "explicit_interface_specifier";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String EXTERN_ALIAS_DIRECTIVE = "extern_alias_directive";
    public static final String FIELD_DECLARATION = "field_declaration";
    public static final String FILE_SCOPED_NAMESPACE_DECLARATION = "file_scoped_namespace_declaration";
    public static final String FINALLY_CLAUSE = "finally_clause";
    public static final String FIXED_STATEMENT = "fixed_statement";
    public static final String FOREACH_STATEMENT = "foreach_statement";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FROM_CLAUSE = "from_clause";
    public static final String FUNCTION_POINTER_PARAMETER = "function_pointer_parameter";
    public static final String FUNCTION_POINTER_TYPE = "function_pointer_type";
    public static final String GENERIC_NAME = "generic_name";
    public static final String GLOBAL_ATTRIBUTE = "global_attribute";
    public static final String GLOBAL_STATEMENT = "global_statement";
    public static final String GOTO_STATEMENT = "goto_statement";
    public static final String GROUP_CLAUSE = "group_clause";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_STATEMENT = "if_statement";
    public static final String IMPLICIT_ARRAY_CREATION_EXPRESSION = "implicit_array_creation_expression";
    public static final String IMPLICIT_OBJECT_CREATION_EXPRESSION = "implicit_object_creation_expression";
    public static final String IMPLICIT_PARAMETER = "implicit_parameter";
    public static final String IMPLICIT_STACKALLOC_EXPRESSION = "implicit_stackalloc_expression";
    public static final String IMPLICIT_TYPE = "implicit_type";
    public static final String INDEXER_DECLARATION = "indexer_declaration";
    public static final String INITIALIZER_EXPRESSION = "initializer_expression";
    public static final String INTEGER_LITERAL = "integer_literal";
    public static final String INTERFACE_DECLARATION = "interface_declaration";
    public static final String INTERPOLATED_STRING_EXPRESSION = "interpolated_string_expression";
    public static final String INTERPOLATION = "interpolation";
    public static final String INTERPOLATION_ALIGNMENT_CLAUSE = "interpolation_alignment_clause";
    public static final String INTERPOLATION_BRACE = "interpolation_brace";
    public static final String INTERPOLATION_FORMAT_CLAUSE = "interpolation_format_clause";
    public static final String INTERPOLATION_QUOTE = "interpolation_quote";
    public static final String INTERPOLATION_START = "interpolation_start";
    public static final String INVOCATION_EXPRESSION = "invocation_expression";
    public static final String IS_EXPRESSION = "is_expression";
    public static final String IS_PATTERN_EXPRESSION = "is_pattern_expression";
    public static final String JOIN_CLAUSE = "join_clause";
    public static final String JOIN_INTO_CLAUSE = "join_into_clause";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LAMBDA_EXPRESSION = "lambda_expression";
    public static final String LET_CLAUSE = "let_clause";
    public static final String LIST_PATTERN = "list_pattern";
    public static final String LITERAL = "literal";
    public static final String LOCAL_DECLARATION_STATEMENT = "local_declaration_statement";
    public static final String LOCAL_FUNCTION_STATEMENT = "local_function_statement";
    public static final String LOCK_STATEMENT = "lock_statement";
    public static final String LVALUE_EXPRESSION = "lvalue_expression";
    public static final String MAKEREF_EXPRESSION = "makeref_expression";
    public static final String MEMBER_ACCESS_EXPRESSION = "member_access_expression";
    public static final String MEMBER_BINDING_EXPRESSION = "member_binding_expression";
    public static final String METHOD_DECLARATION = "method_declaration";
    public static final String MODIFIER = "modifier";
    public static final String NAMESPACE_DECLARATION = "namespace_declaration";
    public static final String NEGATED_PATTERN = "negated_pattern";
    public static final String NON_LVALUE_EXPRESSION = "non_lvalue_expression";
    public static final String NULLABLE_TYPE = "nullable_type";
    public static final String NULL_LITERAL = "null_literal";
    public static final String OBJECT_CREATION_EXPRESSION = "object_creation_expression";
    public static final String OPERATOR_DECLARATION = "operator_declaration";
    public static final String ORDER_BY_CLAUSE = "order_by_clause";
    public static final String OR_PATTERN = "or_pattern";
    public static final String PARAMETER = "parameter";
    public static final String PARAMETER_LIST = "parameter_list";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PARENTHESIZED_PATTERN = "parenthesized_pattern";
    public static final String PARENTHESIZED_VARIABLE_DESIGNATION = "parenthesized_variable_designation";
    public static final String PATTERN = "pattern";
    public static final String POINTER_TYPE = "pointer_type";
    public static final String POSITIONAL_PATTERN_CLAUSE = "positional_pattern_clause";
    public static final String POSTFIX_UNARY_EXPRESSION = "postfix_unary_expression";
    public static final String PREDEFINED_TYPE = "predefined_type";
    public static final String PREFIX_UNARY_EXPRESSION = "prefix_unary_expression";
    public static final String PREPROC_ARG = "preproc_arg";
    public static final String PREPROC_DEFINE = "preproc_define";
    public static final String PREPROC_ELIF = "preproc_elif";
    public static final String PREPROC_ELSE = "preproc_else";
    public static final String PREPROC_ENDREGION = "preproc_endregion";
    public static final String PREPROC_ERROR = "preproc_error";
    public static final String PREPROC_IF = "preproc_if";
    public static final String PREPROC_LINE = "preproc_line";
    public static final String PREPROC_NULLABLE = "preproc_nullable";
    public static final String PREPROC_PRAGMA = "preproc_pragma";
    public static final String PREPROC_REGION = "preproc_region";
    public static final String PREPROC_UNDEF = "preproc_undef";
    public static final String PREPROC_WARNING = "preproc_warning";
    public static final String PRIMARY_CONSTRUCTOR_BASE_TYPE = "primary_constructor_base_type";
    public static final String PROPERTY_DECLARATION = "property_declaration";
    public static final String PROPERTY_PATTERN_CLAUSE = "property_pattern_clause";
    public static final String QUALIFIED_NAME = "qualified_name";
    public static final String QUERY_EXPRESSION = "query_expression";
    public static final String RANGE_EXPRESSION = "range_expression";
    public static final String RAW_STRING_CONTENT = "raw_string_content";
    public static final String RAW_STRING_END = "raw_string_end";
    public static final String RAW_STRING_LITERAL = "raw_string_literal";
    public static final String RAW_STRING_START = "raw_string_start";
    public static final String REAL_LITERAL = "real_literal";
    public static final String RECORD_DECLARATION = "record_declaration";
    public static final String RECURSIVE_PATTERN = "recursive_pattern";
    public static final String REFTYPE_EXPRESSION = "reftype_expression";
    public static final String REFVALUE_EXPRESSION = "refvalue_expression";
    public static final String REF_EXPRESSION = "ref_expression";
    public static final String REF_TYPE = "ref_type";
    public static final String RELATIONAL_PATTERN = "relational_pattern";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SCOPED_TYPE = "scoped_type";
    public static final String SELECT_CLAUSE = "select_clause";
    public static final String SHEBANG_DIRECTIVE = "shebang_directive";
    public static final String SIZEOF_EXPRESSION = "sizeof_expression";
    public static final String STACKALLOC_EXPRESSION = "stackalloc_expression";
    public static final String STATEMENT = "statement";
    public static final String STRING_CONTENT = "string_content";
    public static final String STRING_LITERAL = "string_literal";
    public static final String STRING_LITERAL_CONTENT = "string_literal_content";
    public static final String STRING_LITERAL_ENCODING = "string_literal_encoding";
    public static final String STRUCT_DECLARATION = "struct_declaration";
    public static final String SUBPATTERN = "subpattern";
    public static final String SWITCH_BODY = "switch_body";
    public static final String SWITCH_EXPRESSION = "switch_expression";
    public static final String SWITCH_EXPRESSION_ARM = "switch_expression_arm";
    public static final String SWITCH_SECTION = "switch_section";
    public static final String SWITCH_STATEMENT = "switch_statement";
    public static final String THROW_EXPRESSION = "throw_expression";
    public static final String THROW_STATEMENT = "throw_statement";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String TUPLE_ELEMENT = "tuple_element";
    public static final String TUPLE_EXPRESSION = "tuple_expression";
    public static final String TUPLE_PATTERN = "tuple_pattern";
    public static final String TUPLE_TYPE = "tuple_type";
    public static final String TYPE = "type";
    public static final String TYPEOF_EXPRESSION = "typeof_expression";
    public static final String TYPE_ARGUMENT_LIST = "type_argument_list";
    public static final String TYPE_DECLARATION = "type_declaration";
    public static final String TYPE_PARAMETER = "type_parameter";
    public static final String TYPE_PARAMETER_CONSTRAINT = "type_parameter_constraint";
    public static final String TYPE_PARAMETER_CONSTRAINTS_CLAUSE = "type_parameter_constraints_clause";
    public static final String TYPE_PARAMETER_LIST = "type_parameter_list";
    public static final String TYPE_PATTERN = "type_pattern";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNSAFE_STATEMENT = "unsafe_statement";
    public static final String USING_DIRECTIVE = "using_directive";
    public static final String USING_STATEMENT = "using_statement";
    public static final String VARIABLE_DECLARATION = "variable_declaration";
    public static final String VARIABLE_DECLARATOR = "variable_declarator";
    public static final String VAR_PATTERN = "var_pattern";
    public static final String VERBATIM_STRING_LITERAL = "verbatim_string_literal";
    public static final String WHEN_CLAUSE = "when_clause";
    public static final String WHERE_CLAUSE = "where_clause";
    public static final String WHILE_STATEMENT = "while_statement";
    public static final String WITH_EXPRESSION = "with_expression";
    public static final String WITH_INITIALIZER = "with_initializer";
    public static final String YIELD_STATEMENT = "yield_statement";

    public static final Set<String> DECLARATION_SET = Set.of(
            CLASS_DECLARATION,
            CONSTRUCTOR_DECLARATION,
            CONVERSION_OPERATOR_DECLARATION,
            DELEGATE_DECLARATION,
            DESTRUCTOR_DECLARATION,
            ENUM_DECLARATION,
            EVENT_DECLARATION,
            EVENT_FIELD_DECLARATION,
            FIELD_DECLARATION,
            INDEXER_DECLARATION,
            INTERFACE_DECLARATION,
            METHOD_DECLARATION,
            NAMESPACE_DECLARATION,
            OPERATOR_DECLARATION,
            PREPROC_IF,
            PROPERTY_DECLARATION,
            RECORD_DECLARATION,
            STRUCT_DECLARATION,
            USING_DIRECTIVE);
    public static final Set<String> EXPRESSION_SET = Set.of(LVALUE_EXPRESSION, NON_LVALUE_EXPRESSION);
    public static final Set<String> LITERAL_SET = Set.of(
            BOOLEAN_LITERAL,
            CHARACTER_LITERAL,
            INTEGER_LITERAL,
            NULL_LITERAL,
            RAW_STRING_LITERAL,
            REAL_LITERAL,
            STRING_LITERAL,
            VERBATIM_STRING_LITERAL);
    public static final Set<String> LVALUE_EXPRESSION_SET = Set.of(
            ELEMENT_ACCESS_EXPRESSION,
            ELEMENT_BINDING_EXPRESSION,
            GENERIC_NAME,
            IDENTIFIER,
            MEMBER_ACCESS_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            PREFIX_UNARY_EXPRESSION,
            TUPLE_EXPRESSION);
    public static final Set<String> NON_LVALUE_EXPRESSION_SET = Set.of(
            ANONYMOUS_METHOD_EXPRESSION,
            ANONYMOUS_OBJECT_CREATION_EXPRESSION,
            ARRAY_CREATION_EXPRESSION,
            ASSIGNMENT_EXPRESSION,
            AS_EXPRESSION,
            AWAIT_EXPRESSION,
            BINARY_EXPRESSION,
            CAST_EXPRESSION,
            CHECKED_EXPRESSION,
            CONDITIONAL_ACCESS_EXPRESSION,
            CONDITIONAL_EXPRESSION,
            DEFAULT_EXPRESSION,
            IMPLICIT_ARRAY_CREATION_EXPRESSION,
            IMPLICIT_OBJECT_CREATION_EXPRESSION,
            IMPLICIT_STACKALLOC_EXPRESSION,
            INITIALIZER_EXPRESSION,
            INTERPOLATED_STRING_EXPRESSION,
            INVOCATION_EXPRESSION,
            IS_EXPRESSION,
            IS_PATTERN_EXPRESSION,
            LAMBDA_EXPRESSION,
            LITERAL,
            MAKEREF_EXPRESSION,
            OBJECT_CREATION_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            POSTFIX_UNARY_EXPRESSION,
            PREFIX_UNARY_EXPRESSION,
            PREPROC_IF,
            QUERY_EXPRESSION,
            RANGE_EXPRESSION,
            REFTYPE_EXPRESSION,
            REFVALUE_EXPRESSION,
            REF_EXPRESSION,
            SIZEOF_EXPRESSION,
            STACKALLOC_EXPRESSION,
            SWITCH_EXPRESSION,
            THROW_EXPRESSION,
            TYPEOF_EXPRESSION,
            WITH_EXPRESSION);
    public static final Set<String> PATTERN_SET = Set.of(
            AND_PATTERN,
            CONSTANT_PATTERN,
            DECLARATION_PATTERN,
            DISCARD,
            LIST_PATTERN,
            NEGATED_PATTERN,
            OR_PATTERN,
            PARENTHESIZED_PATTERN,
            RECURSIVE_PATTERN,
            RELATIONAL_PATTERN,
            TYPE_PATTERN,
            VAR_PATTERN);
    public static final Set<String> STATEMENT_SET = Set.of(
            BLOCK,
            BREAK_STATEMENT,
            CHECKED_STATEMENT,
            CONTINUE_STATEMENT,
            DO_STATEMENT,
            EMPTY_STATEMENT,
            EXPRESSION_STATEMENT,
            FIXED_STATEMENT,
            FOREACH_STATEMENT,
            FOR_STATEMENT,
            GOTO_STATEMENT,
            IF_STATEMENT,
            LABELED_STATEMENT,
            LOCAL_DECLARATION_STATEMENT,
            LOCAL_FUNCTION_STATEMENT,
            LOCK_STATEMENT,
            PREPROC_IF,
            RETURN_STATEMENT,
            SWITCH_STATEMENT,
            THROW_STATEMENT,
            TRY_STATEMENT,
            UNSAFE_STATEMENT,
            USING_STATEMENT,
            WHILE_STATEMENT,
            YIELD_STATEMENT);
    public static final Set<String> TYPE_DECLARATION_SET = Set.of(
            CLASS_DECLARATION,
            DELEGATE_DECLARATION,
            ENUM_DECLARATION,
            INTERFACE_DECLARATION,
            RECORD_DECLARATION,
            STRUCT_DECLARATION);
    public static final Set<String> TYPE_SET = Set.of(
            ALIAS_QUALIFIED_NAME,
            ARRAY_TYPE,
            FUNCTION_POINTER_TYPE,
            GENERIC_NAME,
            IDENTIFIER,
            IMPLICIT_TYPE,
            NULLABLE_TYPE,
            POINTER_TYPE,
            PREDEFINED_TYPE,
            QUALIFIED_NAME,
            REF_TYPE,
            SCOPED_TYPE,
            TUPLE_TYPE);
}
