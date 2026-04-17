package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code cpp} from tree-sitter {@code node-types.json}.
 */
public final class CppNodeTypes {
    private CppNodeTypes() {}

    public static final String ABSTRACT_ARRAY_DECLARATOR = "abstract_array_declarator";
    public static final String ABSTRACT_DECLARATOR = "_abstract_declarator";
    public static final String ABSTRACT_FUNCTION_DECLARATOR = "abstract_function_declarator";
    public static final String ABSTRACT_PARENTHESIZED_DECLARATOR = "abstract_parenthesized_declarator";
    public static final String ABSTRACT_POINTER_DECLARATOR = "abstract_pointer_declarator";
    public static final String ABSTRACT_REFERENCE_DECLARATOR = "abstract_reference_declarator";
    public static final String ACCESS_SPECIFIER = "access_specifier";
    public static final String ALIAS_DECLARATION = "alias_declaration";
    public static final String ALIGNAS_QUALIFIER = "alignas_qualifier";
    public static final String ALIGNOF_EXPRESSION = "alignof_expression";
    public static final String ARGUMENT_LIST = "argument_list";
    public static final String ARRAY_DECLARATOR = "array_declarator";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String ATTRIBUTE = "attribute";
    public static final String ATTRIBUTED_DECLARATOR = "attributed_declarator";
    public static final String ATTRIBUTED_STATEMENT = "attributed_statement";
    public static final String ATTRIBUTE_DECLARATION = "attribute_declaration";
    public static final String ATTRIBUTE_SPECIFIER = "attribute_specifier";
    public static final String AUTO = "auto";
    public static final String BASE_CLASS_CLAUSE = "base_class_clause";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BITFIELD_CLAUSE = "bitfield_clause";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CASE_STATEMENT = "case_statement";
    public static final String CAST_EXPRESSION = "cast_expression";
    public static final String CATCH_CLAUSE = "catch_clause";
    public static final String CHARACTER = "character";
    public static final String CHAR_LITERAL = "char_literal";
    public static final String CLASS_SPECIFIER = "class_specifier";
    public static final String COMMA_EXPRESSION = "comma_expression";
    public static final String COMMENT = "comment";
    public static final String COMPOUND_LITERAL_EXPRESSION = "compound_literal_expression";
    public static final String COMPOUND_REQUIREMENT = "compound_requirement";
    public static final String COMPOUND_STATEMENT = "compound_statement";
    public static final String CONCATENATED_STRING = "concatenated_string";
    public static final String CONCEPT_DEFINITION = "concept_definition";
    public static final String CONDITIONAL_EXPRESSION = "conditional_expression";
    public static final String CONDITION_CLAUSE = "condition_clause";
    public static final String CONSTRAINT_CONJUNCTION = "constraint_conjunction";
    public static final String CONSTRAINT_DISJUNCTION = "constraint_disjunction";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String CO_AWAIT_EXPRESSION = "co_await_expression";
    public static final String CO_RETURN_STATEMENT = "co_return_statement";
    public static final String CO_YIELD_STATEMENT = "co_yield_statement";
    public static final String DECLARATION = "declaration";
    public static final String DECLARATION_LIST = "declaration_list";
    public static final String DECLARATOR = "_declarator";
    public static final String DECLTYPE = "decltype";
    public static final String DEFAULT_METHOD_CLAUSE = "default_method_clause";
    public static final String DELETE_EXPRESSION = "delete_expression";
    public static final String DELETE_METHOD_CLAUSE = "delete_method_clause";
    public static final String DEPENDENT_NAME = "dependent_name";
    public static final String DEPENDENT_TYPE = "dependent_type";
    public static final String DESTRUCTOR_NAME = "destructor_name";
    public static final String DO_STATEMENT = "do_statement";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String ENUMERATOR = "enumerator";
    public static final String ENUMERATOR_LIST = "enumerator_list";
    public static final String ENUM_SPECIFIER = "enum_specifier";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPLICIT_FUNCTION_SPECIFIER = "explicit_function_specifier";
    public static final String EXPRESSION = "expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String EXTENSION_EXPRESSION = "extension_expression";
    public static final String FALSE = "false";
    public static final String FIELD_DECLARATION = "field_declaration";
    public static final String FIELD_DECLARATION_LIST = "field_declaration_list";
    public static final String FIELD_DECLARATOR = "_field_declarator";
    public static final String FIELD_DESIGNATOR = "field_designator";
    public static final String FIELD_EXPRESSION = "field_expression";
    public static final String FIELD_IDENTIFIER = "field_identifier";
    public static final String FIELD_INITIALIZER = "field_initializer";
    public static final String FIELD_INITIALIZER_LIST = "field_initializer_list";
    public static final String FOLD_EXPRESSION = "fold_expression";
    public static final String FOR_RANGE_LOOP = "for_range_loop";
    public static final String FOR_STATEMENT = "for_statement";
    public static final String FRIEND_DECLARATION = "friend_declaration";
    public static final String FUNCTION_DECLARATOR = "function_declarator";
    public static final String FUNCTION_DEFINITION = "function_definition";
    public static final String GENERIC_EXPRESSION = "generic_expression";
    public static final String GNU_ASM_CLOBBER_LIST = "gnu_asm_clobber_list";
    public static final String GNU_ASM_EXPRESSION = "gnu_asm_expression";
    public static final String GNU_ASM_GOTO_LIST = "gnu_asm_goto_list";
    public static final String GNU_ASM_INPUT_OPERAND = "gnu_asm_input_operand";
    public static final String GNU_ASM_INPUT_OPERAND_LIST = "gnu_asm_input_operand_list";
    public static final String GNU_ASM_OUTPUT_OPERAND = "gnu_asm_output_operand";
    public static final String GNU_ASM_OUTPUT_OPERAND_LIST = "gnu_asm_output_operand_list";
    public static final String GNU_ASM_QUALIFIER = "gnu_asm_qualifier";
    public static final String GOTO_STATEMENT = "goto_statement";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_STATEMENT = "if_statement";
    public static final String INITIALIZER_LIST = "initializer_list";
    public static final String INITIALIZER_PAIR = "initializer_pair";
    public static final String INIT_DECLARATOR = "init_declarator";
    public static final String INIT_STATEMENT = "init_statement";
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LAMBDA_CAPTURE_INITIALIZER = "lambda_capture_initializer";
    public static final String LAMBDA_CAPTURE_SPECIFIER = "lambda_capture_specifier";
    public static final String LAMBDA_DEFAULT_CAPTURE = "lambda_default_capture";
    public static final String LAMBDA_EXPRESSION = "lambda_expression";
    public static final String LINKAGE_SPECIFICATION = "linkage_specification";
    public static final String LITERAL_SUFFIX = "literal_suffix";
    public static final String MS_BASED_MODIFIER = "ms_based_modifier";
    public static final String MS_CALL_MODIFIER = "ms_call_modifier";
    public static final String MS_DECLSPEC_MODIFIER = "ms_declspec_modifier";
    public static final String MS_POINTER_MODIFIER = "ms_pointer_modifier";
    public static final String MS_RESTRICT_MODIFIER = "ms_restrict_modifier";
    public static final String MS_SIGNED_PTR_MODIFIER = "ms_signed_ptr_modifier";
    public static final String MS_UNALIGNED_PTR_MODIFIER = "ms_unaligned_ptr_modifier";
    public static final String MS_UNSIGNED_PTR_MODIFIER = "ms_unsigned_ptr_modifier";
    public static final String NAMESPACE_ALIAS_DEFINITION = "namespace_alias_definition";
    public static final String NAMESPACE_DEFINITION = "namespace_definition";
    public static final String NAMESPACE_IDENTIFIER = "namespace_identifier";
    public static final String NESTED_NAMESPACE_SPECIFIER = "nested_namespace_specifier";
    public static final String NEW_DECLARATOR = "new_declarator";
    public static final String NEW_EXPRESSION = "new_expression";
    public static final String NOEXCEPT = "noexcept";
    public static final String NULL = "null";
    public static final String NUMBER_LITERAL = "number_literal";
    public static final String OFFSETOF_EXPRESSION = "offsetof_expression";
    public static final String OPERATOR_CAST = "operator_cast";
    public static final String OPERATOR_NAME = "operator_name";
    public static final String OPTIONAL_PARAMETER_DECLARATION = "optional_parameter_declaration";
    public static final String OPTIONAL_TYPE_PARAMETER_DECLARATION = "optional_type_parameter_declaration";
    public static final String PARAMETER_DECLARATION = "parameter_declaration";
    public static final String PARAMETER_LIST = "parameter_list";
    public static final String PARAMETER_PACK_EXPANSION = "parameter_pack_expansion";
    public static final String PARENTHESIZED_DECLARATOR = "parenthesized_declarator";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PLACEHOLDER_TYPE_SPECIFIER = "placeholder_type_specifier";
    public static final String POINTER_DECLARATOR = "pointer_declarator";
    public static final String POINTER_EXPRESSION = "pointer_expression";
    public static final String POINTER_TYPE_DECLARATOR = "pointer_type_declarator";
    public static final String PREPROC_ARG = "preproc_arg";
    public static final String PREPROC_CALL = "preproc_call";
    public static final String PREPROC_DEF = "preproc_def";
    public static final String PREPROC_DEFINED = "preproc_defined";
    public static final String PREPROC_DIRECTIVE = "preproc_directive";
    public static final String PREPROC_ELIF = "preproc_elif";
    public static final String PREPROC_ELIFDEF = "preproc_elifdef";
    public static final String PREPROC_ELSE = "preproc_else";
    public static final String PREPROC_FUNCTION_DEF = "preproc_function_def";
    public static final String PREPROC_IF = "preproc_if";
    public static final String PREPROC_IFDEF = "preproc_ifdef";
    public static final String PREPROC_INCLUDE = "preproc_include";
    public static final String PREPROC_PARAMS = "preproc_params";
    public static final String PRIMITIVE_TYPE = "primitive_type";
    public static final String PURE_VIRTUAL_CLAUSE = "pure_virtual_clause";
    public static final String QUALIFIED_IDENTIFIER = "qualified_identifier";
    public static final String RAW_STRING_CONTENT = "raw_string_content";
    public static final String RAW_STRING_DELIMITER = "raw_string_delimiter";
    public static final String RAW_STRING_LITERAL = "raw_string_literal";
    public static final String REFERENCE_DECLARATOR = "reference_declarator";
    public static final String REF_QUALIFIER = "ref_qualifier";
    public static final String REQUIREMENT_SEQ = "requirement_seq";
    public static final String REQUIRES_CLAUSE = "requires_clause";
    public static final String REQUIRES_EXPRESSION = "requires_expression";
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SEH_EXCEPT_CLAUSE = "seh_except_clause";
    public static final String SEH_FINALLY_CLAUSE = "seh_finally_clause";
    public static final String SEH_LEAVE_STATEMENT = "seh_leave_statement";
    public static final String SEH_TRY_STATEMENT = "seh_try_statement";
    public static final String SIMPLE_REQUIREMENT = "simple_requirement";
    public static final String SIZED_TYPE_SPECIFIER = "sized_type_specifier";
    public static final String SIZEOF_EXPRESSION = "sizeof_expression";
    public static final String STATEMENT = "statement";
    public static final String STATEMENT_IDENTIFIER = "statement_identifier";
    public static final String STATIC_ASSERT_DECLARATION = "static_assert_declaration";
    public static final String STORAGE_CLASS_SPECIFIER = "storage_class_specifier";
    public static final String STRING_CONTENT = "string_content";
    public static final String STRING_LITERAL = "string_literal";
    public static final String STRUCTURED_BINDING_DECLARATOR = "structured_binding_declarator";
    public static final String STRUCT_SPECIFIER = "struct_specifier";
    public static final String SUBSCRIPT_ARGUMENT_LIST = "subscript_argument_list";
    public static final String SUBSCRIPT_DESIGNATOR = "subscript_designator";
    public static final String SUBSCRIPT_EXPRESSION = "subscript_expression";
    public static final String SUBSCRIPT_RANGE_DESIGNATOR = "subscript_range_designator";
    public static final String SWITCH_STATEMENT = "switch_statement";
    public static final String SYSTEM_LIB_STRING = "system_lib_string";
    public static final String TEMPLATE_ARGUMENT_LIST = "template_argument_list";
    public static final String TEMPLATE_DECLARATION = "template_declaration";
    public static final String TEMPLATE_FUNCTION = "template_function";
    public static final String TEMPLATE_INSTANTIATION = "template_instantiation";
    public static final String TEMPLATE_METHOD = "template_method";
    public static final String TEMPLATE_PARAMETER_LIST = "template_parameter_list";
    public static final String TEMPLATE_TEMPLATE_PARAMETER_DECLARATION = "template_template_parameter_declaration";
    public static final String TEMPLATE_TYPE = "template_type";
    public static final String THIS_ = "this";
    public static final String THROW_SPECIFIER = "throw_specifier";
    public static final String THROW_STATEMENT = "throw_statement";
    public static final String TRAILING_RETURN_TYPE = "trailing_return_type";
    public static final String TRANSLATION_UNIT = "translation_unit";
    public static final String TRUE = "true";
    public static final String TRY_STATEMENT = "try_statement";
    public static final String TYPE_DECLARATOR = "_type_declarator";
    public static final String TYPE_DEFINITION = "type_definition";
    public static final String TYPE_DESCRIPTOR = "type_descriptor";
    public static final String TYPE_IDENTIFIER = "type_identifier";
    public static final String TYPE_PARAMETER_DECLARATION = "type_parameter_declaration";
    public static final String TYPE_QUALIFIER = "type_qualifier";
    public static final String TYPE_REQUIREMENT = "type_requirement";
    public static final String TYPE_SPECIFIER = "type_specifier";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNION_SPECIFIER = "union_specifier";
    public static final String UPDATE_EXPRESSION = "update_expression";
    public static final String USER_DEFINED_LITERAL = "user_defined_literal";
    public static final String USING_DECLARATION = "using_declaration";
    public static final String VARIADIC_DECLARATOR = "variadic_declarator";
    public static final String VARIADIC_PARAMETER_DECLARATION = "variadic_parameter_declaration";
    public static final String VARIADIC_TYPE_PARAMETER_DECLARATION = "variadic_type_parameter_declaration";
    public static final String VIRTUAL_SPECIFIER = "virtual_specifier";
    public static final String WHILE_STATEMENT = "while_statement";

    public static final Set<String> ABSTRACT_DECLARATOR_SET = Set.of(
            ABSTRACT_ARRAY_DECLARATOR,
            ABSTRACT_FUNCTION_DECLARATOR,
            ABSTRACT_PARENTHESIZED_DECLARATOR,
            ABSTRACT_POINTER_DECLARATOR,
            ABSTRACT_REFERENCE_DECLARATOR);
    public static final Set<String> DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            DESTRUCTOR_NAME,
            FUNCTION_DECLARATOR,
            IDENTIFIER,
            OPERATOR_NAME,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR,
            QUALIFIED_IDENTIFIER,
            REFERENCE_DECLARATOR,
            STRUCTURED_BINDING_DECLARATOR,
            TEMPLATE_FUNCTION);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ALIGNOF_EXPRESSION,
            ASSIGNMENT_EXPRESSION,
            BINARY_EXPRESSION,
            CALL_EXPRESSION,
            CAST_EXPRESSION,
            CHAR_LITERAL,
            COMPOUND_LITERAL_EXPRESSION,
            CONCATENATED_STRING,
            CONDITIONAL_EXPRESSION,
            CO_AWAIT_EXPRESSION,
            DELETE_EXPRESSION,
            EXTENSION_EXPRESSION,
            FALSE,
            FIELD_EXPRESSION,
            FOLD_EXPRESSION,
            GENERIC_EXPRESSION,
            GNU_ASM_EXPRESSION,
            IDENTIFIER,
            LAMBDA_EXPRESSION,
            NEW_EXPRESSION,
            NULL,
            NUMBER_LITERAL,
            OFFSETOF_EXPRESSION,
            PARAMETER_PACK_EXPANSION,
            PARENTHESIZED_EXPRESSION,
            POINTER_EXPRESSION,
            QUALIFIED_IDENTIFIER,
            RAW_STRING_LITERAL,
            REQUIRES_CLAUSE,
            REQUIRES_EXPRESSION,
            SIZEOF_EXPRESSION,
            STRING_LITERAL,
            SUBSCRIPT_EXPRESSION,
            TEMPLATE_FUNCTION,
            THIS_,
            TRUE,
            UNARY_EXPRESSION,
            UPDATE_EXPRESSION,
            USER_DEFINED_LITERAL);
    public static final Set<String> FIELD_DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FIELD_IDENTIFIER,
            FUNCTION_DECLARATOR,
            OPERATOR_NAME,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR,
            REFERENCE_DECLARATOR,
            TEMPLATE_METHOD);
    public static final Set<String> STATEMENT_SET = Set.of(
            ATTRIBUTED_STATEMENT,
            BREAK_STATEMENT,
            CASE_STATEMENT,
            COMPOUND_STATEMENT,
            CONTINUE_STATEMENT,
            CO_RETURN_STATEMENT,
            CO_YIELD_STATEMENT,
            DO_STATEMENT,
            EXPRESSION_STATEMENT,
            FOR_RANGE_LOOP,
            FOR_STATEMENT,
            GOTO_STATEMENT,
            IF_STATEMENT,
            LABELED_STATEMENT,
            RETURN_STATEMENT,
            SEH_LEAVE_STATEMENT,
            SEH_TRY_STATEMENT,
            SWITCH_STATEMENT,
            THROW_STATEMENT,
            TRY_STATEMENT,
            WHILE_STATEMENT);
    public static final Set<String> TYPE_DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FUNCTION_DECLARATOR,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR,
            PRIMITIVE_TYPE,
            REFERENCE_DECLARATOR,
            TYPE_IDENTIFIER);
    public static final Set<String> TYPE_SPECIFIER_SET = Set.of(
            CLASS_SPECIFIER,
            DECLTYPE,
            DEPENDENT_TYPE,
            ENUM_SPECIFIER,
            PLACEHOLDER_TYPE_SPECIFIER,
            PRIMITIVE_TYPE,
            QUALIFIED_IDENTIFIER,
            SIZED_TYPE_SPECIFIER,
            STRUCT_SPECIFIER,
            TEMPLATE_TYPE,
            TYPE_IDENTIFIER,
            UNION_SPECIFIER);
}
