package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code zig} from tree-sitter {@code node-types.json}.
 */
public enum ZigNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ADDRESS_SPACE("address_space"),
    ANONYMOUS_STRUCT_INITIALIZER("anonymous_struct_initializer"),
    ANYFRAME_TYPE("anyframe_type"),
    ARGUMENTS("arguments"),
    ARRAY_TYPE("array_type"),
    ASM_CLOBBERS("asm_clobbers"),
    ASM_EXPRESSION("asm_expression"),
    ASM_INPUT("asm_input"),
    ASM_INPUT_ITEM("asm_input_item"),
    ASM_OUTPUT("asm_output"),
    ASM_OUTPUT_ITEM("asm_output_item"),
    ASSIGNMENT_EXPRESSION("assignment_expression"),
    ASYNC_EXPRESSION("async_expression"),
    AWAIT_EXPRESSION("await_expression"),
    BINARY_EXPRESSION("binary_expression"),
    BLOCK("block"),
    BLOCK_EXPRESSION("block_expression"),
    BLOCK_LABEL("block_label"),
    BOOLEAN_("boolean"),
    BREAK_EXPRESSION("break_expression"),
    BREAK_LABEL("break_label"),
    BUILTIN_FUNCTION("builtin_function"),
    BUILTIN_IDENTIFIER("builtin_identifier"),
    BUILTIN_TYPE("builtin_type"),
    BYTE_ALIGNMENT("byte_alignment"),
    CALLING_CONVENTION("calling_convention"),
    CALL_EXPRESSION("call_expression"),
    CATCH_EXPRESSION("catch_expression"),
    CHARACTER("character"),
    CHARACTER_CONTENT("character_content"),
    COMMENT("comment"),
    COMPTIME_DECLARATION("comptime_declaration"),
    COMPTIME_EXPRESSION("comptime_expression"),
    COMPTIME_STATEMENT("comptime_statement"),
    COMPTIME_TYPE_EXPRESSION("comptime_type_expression"),
    CONTAINER_FIELD("container_field"),
    CONTINUE_EXPRESSION("continue_expression"),
    DEFER_STATEMENT("defer_statement"),
    DEREFERENCE_EXPRESSION("dereference_expression"),
    ELSE_CLAUSE("else_clause"),
    ENUM_DECLARATION("enum_declaration"),
    ERRDEFER_STATEMENT("errdefer_statement"),
    ERROR_SET_DECLARATION("error_set_declaration"),
    ERROR_TYPE("error_type"),
    ERROR_UNION_TYPE("error_union_type"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXPRESSION("expression"),
    EXPRESSION_STATEMENT("expression_statement"),
    FIELD_EXPRESSION("field_expression"),
    FIELD_INITIALIZER("field_initializer"),
    FLOAT_("float"),
    FOR_EXPRESSION("for_expression"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DECLARATION("function_declaration"),
    FUNCTION_SIGNATURE("function_signature"),
    IDENTIFIER("identifier"),
    IF_EXPRESSION("if_expression"),
    IF_STATEMENT("if_statement"),
    IF_TYPE_EXPRESSION("if_type_expression"),
    INDEX_EXPRESSION("index_expression"),
    INITIALIZER_LIST("initializer_list"),
    INTEGER("integer"),
    LABELED_STATEMENT("labeled_statement"),
    LABELED_TYPE_EXPRESSION("labeled_type_expression"),
    LINK_SECTION("link_section"),
    MULTILINE_STRING("multiline_string"),
    NOSUSPEND_EXPRESSION("nosuspend_expression"),
    NOSUSPEND_STATEMENT("nosuspend_statement"),
    NULLABLE_TYPE("nullable_type"),
    NULL_COERCION_EXPRESSION("null_coercion_expression"),
    OPAQUE_DECLARATION("opaque_declaration"),
    PARAMETER("parameter"),
    PARAMETERS("parameters"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PAYLOAD("payload"),
    POINTER_TYPE("pointer_type"),
    PRIMARY_TYPE_EXPRESSION("primary_type_expression"),
    RANGE_EXPRESSION("range_expression"),
    RESUME_EXPRESSION("resume_expression"),
    RETURN_EXPRESSION("return_expression"),
    SLICE_TYPE("slice_type"),
    SOURCE_FILE("source_file"),
    STATEMENT("statement"),
    STRING("string"),
    STRING_CONTENT("string_content"),
    STRUCT_DECLARATION("struct_declaration"),
    STRUCT_INITIALIZER("struct_initializer"),
    SUSPEND_STATEMENT("suspend_statement"),
    SWITCH_CASE("switch_case"),
    SWITCH_EXPRESSION("switch_expression"),
    TEST_DECLARATION("test_declaration"),
    TRY_EXPRESSION("try_expression"),
    TYPE_EXPRESSION("type_expression"),
    UNARY_EXPRESSION("unary_expression"),
    UNION_DECLARATION("union_declaration"),
    USING_NAMESPACE_DECLARATION("using_namespace_declaration"),
    VARIABLE_DECLARATION("variable_declaration"),
    WHILE_EXPRESSION("while_expression"),
    WHILE_STATEMENT("while_statement");

    public static final Set<ZigNodeType> EXPRESSION_SET = Set.of(
            ASM_EXPRESSION,
            ASSIGNMENT_EXPRESSION,
            ASYNC_EXPRESSION,
            AWAIT_EXPRESSION,
            BINARY_EXPRESSION,
            BLOCK,
            BREAK_EXPRESSION,
            CATCH_EXPRESSION,
            COMPTIME_EXPRESSION,
            CONTINUE_EXPRESSION,
            FOR_EXPRESSION,
            IF_EXPRESSION,
            NOSUSPEND_EXPRESSION,
            RESUME_EXPRESSION,
            RETURN_EXPRESSION,
            TRY_EXPRESSION,
            TYPE_EXPRESSION,
            UNARY_EXPRESSION,
            WHILE_EXPRESSION);
    public static final Set<ZigNodeType> PRIMARY_TYPE_EXPRESSION_SET = Set.of(
            ANYFRAME_TYPE,
            ARRAY_TYPE,
            BOOLEAN_,
            BUILTIN_FUNCTION,
            BUILTIN_TYPE,
            CALL_EXPRESSION,
            CHARACTER,
            DEREFERENCE_EXPRESSION,
            ENUM_DECLARATION,
            ERROR_TYPE,
            ERROR_UNION_TYPE,
            FIELD_EXPRESSION,
            FLOAT_,
            FUNCTION_SIGNATURE,
            IDENTIFIER,
            INDEX_EXPRESSION,
            INTEGER,
            MULTILINE_STRING,
            NULLABLE_TYPE,
            NULL_COERCION_EXPRESSION,
            OPAQUE_DECLARATION,
            POINTER_TYPE,
            RANGE_EXPRESSION,
            SLICE_TYPE,
            STRING,
            STRUCT_DECLARATION,
            SWITCH_EXPRESSION,
            UNION_DECLARATION);
    public static final Set<ZigNodeType> STATEMENT_SET = Set.of(
            COMPTIME_STATEMENT,
            DEFER_STATEMENT,
            ERRDEFER_STATEMENT,
            EXPRESSION_STATEMENT,
            FOR_STATEMENT,
            IF_STATEMENT,
            LABELED_STATEMENT,
            NOSUSPEND_STATEMENT,
            SUSPEND_STATEMENT,
            SWITCH_EXPRESSION,
            VARIABLE_DECLARATION,
            WHILE_STATEMENT);
    public static final Set<ZigNodeType> TYPE_EXPRESSION_SET = Set.of(
            ANONYMOUS_STRUCT_INITIALIZER,
            ERROR_SET_DECLARATION,
            LABELED_TYPE_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            PRIMARY_TYPE_EXPRESSION,
            STRUCT_INITIALIZER);

    private final @Nullable String type;

    ZigNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static ZigNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static ZigNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        ZigNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, ZigNodeType> LOOKUP = initLookup();

    private static Map<String, ZigNodeType> initLookup() {
        HashMap<String, ZigNodeType> m = new HashMap<>();
        for (ZigNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
