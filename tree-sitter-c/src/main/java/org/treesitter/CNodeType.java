package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code c} from tree-sitter {@code node-types.json}.
 */
public enum CNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ABSTRACT_ARRAY_DECLARATOR("abstract_array_declarator"),
    ABSTRACT_DECLARATOR("_abstract_declarator"),
    ABSTRACT_FUNCTION_DECLARATOR("abstract_function_declarator"),
    ABSTRACT_PARENTHESIZED_DECLARATOR("abstract_parenthesized_declarator"),
    ABSTRACT_POINTER_DECLARATOR("abstract_pointer_declarator"),
    ALIGNAS_QUALIFIER("alignas_qualifier"),
    ALIGNOF_EXPRESSION("alignof_expression"),
    ARGUMENT_LIST("argument_list"),
    ARRAY_DECLARATOR("array_declarator"),
    ASSIGNMENT_EXPRESSION("assignment_expression"),
    ATTRIBUTE("attribute"),
    ATTRIBUTED_DECLARATOR("attributed_declarator"),
    ATTRIBUTED_STATEMENT("attributed_statement"),
    ATTRIBUTE_DECLARATION("attribute_declaration"),
    ATTRIBUTE_SPECIFIER("attribute_specifier"),
    BINARY_EXPRESSION("binary_expression"),
    BITFIELD_CLAUSE("bitfield_clause"),
    BREAK_STATEMENT("break_statement"),
    CALL_EXPRESSION("call_expression"),
    CASE_STATEMENT("case_statement"),
    CAST_EXPRESSION("cast_expression"),
    CHARACTER("character"),
    CHAR_LITERAL("char_literal"),
    COMMA_EXPRESSION("comma_expression"),
    COMMENT("comment"),
    COMPOUND_LITERAL_EXPRESSION("compound_literal_expression"),
    COMPOUND_STATEMENT("compound_statement"),
    CONCATENATED_STRING("concatenated_string"),
    CONDITIONAL_EXPRESSION("conditional_expression"),
    CONTINUE_STATEMENT("continue_statement"),
    DECLARATION("declaration"),
    DECLARATION_LIST("declaration_list"),
    DECLARATOR("_declarator"),
    DO_STATEMENT("do_statement"),
    ELSE_CLAUSE("else_clause"),
    ENUMERATOR("enumerator"),
    ENUMERATOR_LIST("enumerator_list"),
    ENUM_SPECIFIER("enum_specifier"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXPRESSION("expression"),
    EXPRESSION_STATEMENT("expression_statement"),
    EXTENSION_EXPRESSION("extension_expression"),
    FALSE("false"),
    FIELD_DECLARATION("field_declaration"),
    FIELD_DECLARATION_LIST("field_declaration_list"),
    FIELD_DECLARATOR("_field_declarator"),
    FIELD_DESIGNATOR("field_designator"),
    FIELD_EXPRESSION("field_expression"),
    FIELD_IDENTIFIER("field_identifier"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DECLARATOR("function_declarator"),
    FUNCTION_DEFINITION("function_definition"),
    GENERIC_EXPRESSION("generic_expression"),
    GNU_ASM_CLOBBER_LIST("gnu_asm_clobber_list"),
    GNU_ASM_EXPRESSION("gnu_asm_expression"),
    GNU_ASM_GOTO_LIST("gnu_asm_goto_list"),
    GNU_ASM_INPUT_OPERAND("gnu_asm_input_operand"),
    GNU_ASM_INPUT_OPERAND_LIST("gnu_asm_input_operand_list"),
    GNU_ASM_OUTPUT_OPERAND("gnu_asm_output_operand"),
    GNU_ASM_OUTPUT_OPERAND_LIST("gnu_asm_output_operand_list"),
    GNU_ASM_QUALIFIER("gnu_asm_qualifier"),
    GOTO_STATEMENT("goto_statement"),
    IDENTIFIER("identifier"),
    IF_STATEMENT("if_statement"),
    INITIALIZER_LIST("initializer_list"),
    INITIALIZER_PAIR("initializer_pair"),
    INIT_DECLARATOR("init_declarator"),
    LABELED_STATEMENT("labeled_statement"),
    LINKAGE_SPECIFICATION("linkage_specification"),
    MACRO_TYPE_SPECIFIER("macro_type_specifier"),
    MS_BASED_MODIFIER("ms_based_modifier"),
    MS_CALL_MODIFIER("ms_call_modifier"),
    MS_DECLSPEC_MODIFIER("ms_declspec_modifier"),
    MS_POINTER_MODIFIER("ms_pointer_modifier"),
    MS_RESTRICT_MODIFIER("ms_restrict_modifier"),
    MS_SIGNED_PTR_MODIFIER("ms_signed_ptr_modifier"),
    MS_UNALIGNED_PTR_MODIFIER("ms_unaligned_ptr_modifier"),
    MS_UNSIGNED_PTR_MODIFIER("ms_unsigned_ptr_modifier"),
    NULL("null"),
    NUMBER_LITERAL("number_literal"),
    OFFSETOF_EXPRESSION("offsetof_expression"),
    PARAMETER_DECLARATION("parameter_declaration"),
    PARAMETER_LIST("parameter_list"),
    PARENTHESIZED_DECLARATOR("parenthesized_declarator"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    POINTER_DECLARATOR("pointer_declarator"),
    POINTER_EXPRESSION("pointer_expression"),
    PREPROC_ARG("preproc_arg"),
    PREPROC_CALL("preproc_call"),
    PREPROC_DEF("preproc_def"),
    PREPROC_DEFINED("preproc_defined"),
    PREPROC_DIRECTIVE("preproc_directive"),
    PREPROC_ELIF("preproc_elif"),
    PREPROC_ELIFDEF("preproc_elifdef"),
    PREPROC_ELSE("preproc_else"),
    PREPROC_FUNCTION_DEF("preproc_function_def"),
    PREPROC_IF("preproc_if"),
    PREPROC_IFDEF("preproc_ifdef"),
    PREPROC_INCLUDE("preproc_include"),
    PREPROC_PARAMS("preproc_params"),
    PRIMITIVE_TYPE("primitive_type"),
    RETURN_STATEMENT("return_statement"),
    SEH_EXCEPT_CLAUSE("seh_except_clause"),
    SEH_FINALLY_CLAUSE("seh_finally_clause"),
    SEH_LEAVE_STATEMENT("seh_leave_statement"),
    SEH_TRY_STATEMENT("seh_try_statement"),
    SIZED_TYPE_SPECIFIER("sized_type_specifier"),
    SIZEOF_EXPRESSION("sizeof_expression"),
    STATEMENT("statement"),
    STATEMENT_IDENTIFIER("statement_identifier"),
    STORAGE_CLASS_SPECIFIER("storage_class_specifier"),
    STRING_CONTENT("string_content"),
    STRING_LITERAL("string_literal"),
    STRUCT_SPECIFIER("struct_specifier"),
    SUBSCRIPT_DESIGNATOR("subscript_designator"),
    SUBSCRIPT_EXPRESSION("subscript_expression"),
    SUBSCRIPT_RANGE_DESIGNATOR("subscript_range_designator"),
    SWITCH_STATEMENT("switch_statement"),
    SYSTEM_LIB_STRING("system_lib_string"),
    TRANSLATION_UNIT("translation_unit"),
    TRUE("true"),
    TYPE_DECLARATOR("_type_declarator"),
    TYPE_DEFINITION("type_definition"),
    TYPE_DESCRIPTOR("type_descriptor"),
    TYPE_IDENTIFIER("type_identifier"),
    TYPE_QUALIFIER("type_qualifier"),
    TYPE_SPECIFIER("type_specifier"),
    UNARY_EXPRESSION("unary_expression"),
    UNION_SPECIFIER("union_specifier"),
    UPDATE_EXPRESSION("update_expression"),
    VARIADIC_PARAMETER("variadic_parameter"),
    WHILE_STATEMENT("while_statement");

    public static final Set<CNodeType> ABSTRACT_DECLARATOR_SET = Set.of(
            ABSTRACT_ARRAY_DECLARATOR,
            ABSTRACT_FUNCTION_DECLARATOR,
            ABSTRACT_PARENTHESIZED_DECLARATOR,
            ABSTRACT_POINTER_DECLARATOR);
    public static final Set<CNodeType> DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FUNCTION_DECLARATOR,
            IDENTIFIER,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR);
    public static final Set<CNodeType> EXPRESSION_SET = Set.of(
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
    public static final Set<CNodeType> FIELD_DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FIELD_IDENTIFIER,
            FUNCTION_DECLARATOR,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR);
    public static final Set<CNodeType> STATEMENT_SET = Set.of(
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
    public static final Set<CNodeType> TYPE_DECLARATOR_SET = Set.of(
            ARRAY_DECLARATOR,
            ATTRIBUTED_DECLARATOR,
            FUNCTION_DECLARATOR,
            PARENTHESIZED_DECLARATOR,
            POINTER_DECLARATOR,
            PRIMITIVE_TYPE,
            TYPE_IDENTIFIER);
    public static final Set<CNodeType> TYPE_SPECIFIER_SET = Set.of(
            ENUM_SPECIFIER,
            MACRO_TYPE_SPECIFIER,
            PRIMITIVE_TYPE,
            SIZED_TYPE_SPECIFIER,
            STRUCT_SPECIFIER,
            TYPE_IDENTIFIER,
            UNION_SPECIFIER);

    private final @Nullable String type;

    CNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static CNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static CNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        CNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, CNodeType> LOOKUP = initLookup();

    private static Map<String, CNodeType> initLookup() {
        HashMap<String, CNodeType> m = new HashMap<>();
        for (CNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
