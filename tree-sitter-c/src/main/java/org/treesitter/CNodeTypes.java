package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code c} from tree-sitter {@code node-types.json}.
 */
public final class CNodeTypes {
    private CNodeTypes() {}

    public static final String ABSTRACT_ARRAY_DECLARATOR = "abstract_array_declarator";
    public static final String ABSTRACT_DECLARATOR = "_abstract_declarator";
    public static final String ABSTRACT_FUNCTION_DECLARATOR = "abstract_function_declarator";
    public static final String ABSTRACT_PARENTHESIZED_DECLARATOR = "abstract_parenthesized_declarator";
    public static final String ABSTRACT_POINTER_DECLARATOR = "abstract_pointer_declarator";
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
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BITFIELD_CLAUSE = "bitfield_clause";
    public static final String BREAK_STATEMENT = "break_statement";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CASE_STATEMENT = "case_statement";
    public static final String CAST_EXPRESSION = "cast_expression";
    public static final String CHARACTER = "character";
    public static final String CHAR_LITERAL = "char_literal";
    public static final String COMMA_EXPRESSION = "comma_expression";
    public static final String COMMENT = "comment";
    public static final String COMPOUND_LITERAL_EXPRESSION = "compound_literal_expression";
    public static final String COMPOUND_STATEMENT = "compound_statement";
    public static final String CONCATENATED_STRING = "concatenated_string";
    public static final String CONDITIONAL_EXPRESSION = "conditional_expression";
    public static final String CONTINUE_STATEMENT = "continue_statement";
    public static final String DECLARATION = "declaration";
    public static final String DECLARATION_LIST = "declaration_list";
    public static final String DECLARATOR = "_declarator";
    public static final String DO_STATEMENT = "do_statement";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String ENUMERATOR = "enumerator";
    public static final String ENUMERATOR_LIST = "enumerator_list";
    public static final String ENUM_SPECIFIER = "enum_specifier";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
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
    public static final String FOR_STATEMENT = "for_statement";
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
    public static final String LABELED_STATEMENT = "labeled_statement";
    public static final String LINKAGE_SPECIFICATION = "linkage_specification";
    public static final String MACRO_TYPE_SPECIFIER = "macro_type_specifier";
    public static final String MS_BASED_MODIFIER = "ms_based_modifier";
    public static final String MS_CALL_MODIFIER = "ms_call_modifier";
    public static final String MS_DECLSPEC_MODIFIER = "ms_declspec_modifier";
    public static final String MS_POINTER_MODIFIER = "ms_pointer_modifier";
    public static final String MS_RESTRICT_MODIFIER = "ms_restrict_modifier";
    public static final String MS_SIGNED_PTR_MODIFIER = "ms_signed_ptr_modifier";
    public static final String MS_UNALIGNED_PTR_MODIFIER = "ms_unaligned_ptr_modifier";
    public static final String MS_UNSIGNED_PTR_MODIFIER = "ms_unsigned_ptr_modifier";
    public static final String NULL = "null";
    public static final String NUMBER_LITERAL = "number_literal";
    public static final String OFFSETOF_EXPRESSION = "offsetof_expression";
    public static final String PARAMETER_DECLARATION = "parameter_declaration";
    public static final String PARAMETER_LIST = "parameter_list";
    public static final String PARENTHESIZED_DECLARATOR = "parenthesized_declarator";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String POINTER_DECLARATOR = "pointer_declarator";
    public static final String POINTER_EXPRESSION = "pointer_expression";
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
    public static final String RETURN_STATEMENT = "return_statement";
    public static final String SEH_EXCEPT_CLAUSE = "seh_except_clause";
    public static final String SEH_FINALLY_CLAUSE = "seh_finally_clause";
    public static final String SEH_LEAVE_STATEMENT = "seh_leave_statement";
    public static final String SEH_TRY_STATEMENT = "seh_try_statement";
    public static final String SIZED_TYPE_SPECIFIER = "sized_type_specifier";
    public static final String SIZEOF_EXPRESSION = "sizeof_expression";
    public static final String STATEMENT = "statement";
    public static final String STATEMENT_IDENTIFIER = "statement_identifier";
    public static final String STORAGE_CLASS_SPECIFIER = "storage_class_specifier";
    public static final String STRING_CONTENT = "string_content";
    public static final String STRING_LITERAL = "string_literal";
    public static final String STRUCT_SPECIFIER = "struct_specifier";
    public static final String SUBSCRIPT_DESIGNATOR = "subscript_designator";
    public static final String SUBSCRIPT_EXPRESSION = "subscript_expression";
    public static final String SUBSCRIPT_RANGE_DESIGNATOR = "subscript_range_designator";
    public static final String SWITCH_STATEMENT = "switch_statement";
    public static final String SYSTEM_LIB_STRING = "system_lib_string";
    public static final String TRANSLATION_UNIT = "translation_unit";
    public static final String TRUE = "true";
    public static final String TYPE_DECLARATOR = "_type_declarator";
    public static final String TYPE_DEFINITION = "type_definition";
    public static final String TYPE_DESCRIPTOR = "type_descriptor";
    public static final String TYPE_IDENTIFIER = "type_identifier";
    public static final String TYPE_QUALIFIER = "type_qualifier";
    public static final String TYPE_SPECIFIER = "type_specifier";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNION_SPECIFIER = "union_specifier";
    public static final String UPDATE_EXPRESSION = "update_expression";
    public static final String VARIADIC_PARAMETER = "variadic_parameter";
    public static final String WHILE_STATEMENT = "while_statement";

    public static final Set<String> ABSTRACT_DECLARATOR_SET = Set.of(
            ABSTRACT_ARRAY_DECLARATOR,
            ABSTRACT_FUNCTION_DECLARATOR,
            ABSTRACT_PARENTHESIZED_DECLARATOR,
            ABSTRACT_POINTER_DECLARATOR);
    public static final Set<String> DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FUNCTION_DECLARATOR,
            IDENTIFIER,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR);
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
            EXTENSION_EXPRESSION,
            FALSE,
            FIELD_EXPRESSION,
            GENERIC_EXPRESSION,
            GNU_ASM_EXPRESSION,
            IDENTIFIER,
            NULL,
            NUMBER_LITERAL,
            OFFSETOF_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            POINTER_EXPRESSION,
            SIZEOF_EXPRESSION,
            STRING_LITERAL,
            SUBSCRIPT_EXPRESSION,
            TRUE,
            UNARY_EXPRESSION,
            UPDATE_EXPRESSION);
    public static final Set<String> FIELD_DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FIELD_IDENTIFIER,
            FUNCTION_DECLARATOR,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR);
    public static final Set<String> STATEMENT_SET = Set.of(
            ATTRIBUTED_STATEMENT,
            BREAK_STATEMENT,
            CASE_STATEMENT,
            COMPOUND_STATEMENT,
            CONTINUE_STATEMENT,
            DO_STATEMENT,
            EXPRESSION_STATEMENT,
            FOR_STATEMENT,
            GOTO_STATEMENT,
            IF_STATEMENT,
            LABELED_STATEMENT,
            RETURN_STATEMENT,
            SEH_LEAVE_STATEMENT,
            SEH_TRY_STATEMENT,
            SWITCH_STATEMENT,
            WHILE_STATEMENT);
    public static final Set<String> TYPE_DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FUNCTION_DECLARATOR,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR,
            PRIMITIVE_TYPE,
            TYPE_IDENTIFIER);
    public static final Set<String> TYPE_SPECIFIER_SET = Set.of(
            ENUM_SPECIFIER,
            MACRO_TYPE_SPECIFIER,
            PRIMITIVE_TYPE,
            SIZED_TYPE_SPECIFIER,
            STRUCT_SPECIFIER,
            TYPE_IDENTIFIER,
            UNION_SPECIFIER);
}
