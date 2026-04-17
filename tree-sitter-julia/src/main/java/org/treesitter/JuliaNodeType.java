package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code julia} from tree-sitter {@code node-types.json}.
 */
public enum JuliaNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ABSTRACT_DEFINITION("abstract_definition"),
    ADJOINT_EXPRESSION("adjoint_expression"),
    ARGUMENT_LIST("argument_list"),
    ARROW_FUNCTION_EXPRESSION("arrow_function_expression"),
    ASSIGNMENT("assignment"),
    BINARY_EXPRESSION("binary_expression"),
    BLOCK("block"),
    BLOCK_COMMENT("block_comment"),
    BOOLEAN_LITERAL("boolean_literal"),
    BREAK_STATEMENT("break_statement"),
    BROADCAST_CALL_EXPRESSION("broadcast_call_expression"),
    CALL_EXPRESSION("call_expression"),
    CATCH_CLAUSE("catch_clause"),
    CHARACTER_LITERAL("character_literal"),
    COMMAND_LITERAL("command_literal"),
    COMPOUND_ASSIGNMENT_EXPRESSION("compound_assignment_expression"),
    COMPOUND_STATEMENT("compound_statement"),
    COMPREHENSION_EXPRESSION("comprehension_expression"),
    CONST_STATEMENT("const_statement"),
    CONTENT("content"),
    CONTINUE_STATEMENT("continue_statement"),
    CURLY_EXPRESSION("curly_expression"),
    DEFINITION("_definition"),
    DO_CLAUSE("do_clause"),
    ELSEIF_CLAUSE("elseif_clause"),
    ELSE_CLAUSE("else_clause"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXPORT_STATEMENT("export_statement"),
    EXPRESSION("_expression"),
    FIELD_EXPRESSION("field_expression"),
    FINALLY_CLAUSE("finally_clause"),
    FLOAT_LITERAL("float_literal"),
    FOR_BINDING("for_binding"),
    FOR_CLAUSE("for_clause"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DEFINITION("function_definition"),
    GENERATOR("generator"),
    GLOBAL_STATEMENT("global_statement"),
    IDENTIFIER("identifier"),
    IF_CLAUSE("if_clause"),
    IF_STATEMENT("if_statement"),
    IMPORT_ALIAS("import_alias"),
    IMPORT_PATH("import_path"),
    IMPORT_STATEMENT("import_statement"),
    INDEX_EXPRESSION("index_expression"),
    INTEGER_LITERAL("integer_literal"),
    INTERPOLATION_EXPRESSION("interpolation_expression"),
    JUXTAPOSITION_EXPRESSION("juxtaposition_expression"),
    LET_STATEMENT("let_statement"),
    LINE_COMMENT("line_comment"),
    LOCAL_STATEMENT("local_statement"),
    MACROCALL_EXPRESSION("macrocall_expression"),
    MACRO_ARGUMENT_LIST("macro_argument_list"),
    MACRO_DEFINITION("macro_definition"),
    MACRO_IDENTIFIER("macro_identifier"),
    MATRIX_EXPRESSION("matrix_expression"),
    MATRIX_ROW("matrix_row"),
    MODULE_DEFINITION("module_definition"),
    OPEN_TUPLE("open_tuple"),
    OPERATOR("operator"),
    PARAMETRIZED_TYPE_EXPRESSION("parametrized_type_expression"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PREFIXED_COMMAND_LITERAL("prefixed_command_literal"),
    PREFIXED_STRING_LITERAL("prefixed_string_literal"),
    PRIMITIVE_DEFINITION("primitive_definition"),
    PUBLIC_STATEMENT("public_statement"),
    QUOTE_EXPRESSION("quote_expression"),
    QUOTE_STATEMENT("quote_statement"),
    RANGE_EXPRESSION("range_expression"),
    RETURN_STATEMENT("return_statement"),
    SELECTED_IMPORT("selected_import"),
    SIGNATURE("signature"),
    SOURCE_FILE("source_file"),
    SPLAT_EXPRESSION("splat_expression"),
    STATEMENT("_statement"),
    STRING_INTERPOLATION("string_interpolation"),
    STRING_LITERAL("string_literal"),
    STRUCT_DEFINITION("struct_definition"),
    TERNARY_EXPRESSION("ternary_expression"),
    TRY_STATEMENT("try_statement"),
    TUPLE_EXPRESSION("tuple_expression"),
    TYPED_EXPRESSION("typed_expression"),
    TYPE_HEAD("type_head"),
    UNARY_EXPRESSION("unary_expression"),
    UNARY_TYPED_EXPRESSION("unary_typed_expression"),
    USING_STATEMENT("using_statement"),
    VECTOR_EXPRESSION("vector_expression"),
    WHERE_EXPRESSION("where_expression"),
    WHILE_STATEMENT("while_statement");

    public static final Set<JuliaNodeType> DEFINITION_SET = Set.of(
            ABSTRACT_DEFINITION,
            FUNCTION_DEFINITION,
            MACRO_DEFINITION,
            MODULE_DEFINITION,
            PRIMITIVE_DEFINITION,
            STRUCT_DEFINITION);
    public static final Set<JuliaNodeType> EXPRESSION_SET = Set.of(
            ADJOINT_EXPRESSION,
            ARROW_FUNCTION_EXPRESSION,
            BINARY_EXPRESSION,
            BOOLEAN_LITERAL,
            BROADCAST_CALL_EXPRESSION,
            CALL_EXPRESSION,
            CHARACTER_LITERAL,
            COMMAND_LITERAL,
            COMPOUND_ASSIGNMENT_EXPRESSION,
            COMPREHENSION_EXPRESSION,
            CURLY_EXPRESSION,
            DEFINITION,
            FIELD_EXPRESSION,
            FLOAT_LITERAL,
            IDENTIFIER,
            INDEX_EXPRESSION,
            INTEGER_LITERAL,
            INTERPOLATION_EXPRESSION,
            JUXTAPOSITION_EXPRESSION,
            MACROCALL_EXPRESSION,
            MATRIX_EXPRESSION,
            OPERATOR,
            PARAMETRIZED_TYPE_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            PREFIXED_COMMAND_LITERAL,
            PREFIXED_STRING_LITERAL,
            QUOTE_EXPRESSION,
            RANGE_EXPRESSION,
            SPLAT_EXPRESSION,
            STATEMENT,
            STRING_LITERAL,
            TERNARY_EXPRESSION,
            TUPLE_EXPRESSION,
            TYPED_EXPRESSION,
            UNARY_EXPRESSION,
            UNARY_TYPED_EXPRESSION,
            VECTOR_EXPRESSION,
            WHERE_EXPRESSION);
    public static final Set<JuliaNodeType> STATEMENT_SET = Set.of(
            BREAK_STATEMENT,
            COMPOUND_STATEMENT,
            CONST_STATEMENT,
            CONTINUE_STATEMENT,
            EXPORT_STATEMENT,
            FOR_STATEMENT,
            GLOBAL_STATEMENT,
            IF_STATEMENT,
            IMPORT_STATEMENT,
            LET_STATEMENT,
            LOCAL_STATEMENT,
            PUBLIC_STATEMENT,
            QUOTE_STATEMENT,
            RETURN_STATEMENT,
            TRY_STATEMENT,
            USING_STATEMENT,
            WHILE_STATEMENT);

    private final @Nullable String type;

    JuliaNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static JuliaNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static JuliaNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        JuliaNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, JuliaNodeType> LOOKUP = initLookup();

    private static Map<String, JuliaNodeType> initLookup() {
        HashMap<String, JuliaNodeType> m = new HashMap<>();
        for (JuliaNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
