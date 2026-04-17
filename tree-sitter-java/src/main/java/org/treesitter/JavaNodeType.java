package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code java} from tree-sitter {@code node-types.json}.
 */
public enum JavaNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ANNOTATED_TYPE("annotated_type"),
    ANNOTATION("annotation"),
    ANNOTATION_ARGUMENT_LIST("annotation_argument_list"),
    ANNOTATION_TYPE_BODY("annotation_type_body"),
    ANNOTATION_TYPE_DECLARATION("annotation_type_declaration"),
    ANNOTATION_TYPE_ELEMENT_DECLARATION("annotation_type_element_declaration"),
    ARGUMENT_LIST("argument_list"),
    ARRAY_ACCESS("array_access"),
    ARRAY_CREATION_EXPRESSION("array_creation_expression"),
    ARRAY_INITIALIZER("array_initializer"),
    ARRAY_TYPE("array_type"),
    ASSERT_STATEMENT("assert_statement"),
    ASSIGNMENT_EXPRESSION("assignment_expression"),
    ASTERISK("asterisk"),
    BINARY_EXPRESSION("binary_expression"),
    BINARY_INTEGER_LITERAL("binary_integer_literal"),
    BLOCK("block"),
    BLOCK_COMMENT("block_comment"),
    BOOLEAN_TYPE("boolean_type"),
    BREAK_STATEMENT("break_statement"),
    CAST_EXPRESSION("cast_expression"),
    CATCH_CLAUSE("catch_clause"),
    CATCH_FORMAL_PARAMETER("catch_formal_parameter"),
    CATCH_TYPE("catch_type"),
    CHARACTER_LITERAL("character_literal"),
    CLASS_BODY("class_body"),
    CLASS_DECLARATION("class_declaration"),
    CLASS_LITERAL("class_literal"),
    COMPACT_CONSTRUCTOR_DECLARATION("compact_constructor_declaration"),
    CONSTANT_DECLARATION("constant_declaration"),
    CONSTRUCTOR_BODY("constructor_body"),
    CONSTRUCTOR_DECLARATION("constructor_declaration"),
    CONTINUE_STATEMENT("continue_statement"),
    DECIMAL_FLOATING_POINT_LITERAL("decimal_floating_point_literal"),
    DECIMAL_INTEGER_LITERAL("decimal_integer_literal"),
    DECLARATION("declaration"),
    DIMENSIONS("dimensions"),
    DIMENSIONS_EXPR("dimensions_expr"),
    DO_STATEMENT("do_statement"),
    ELEMENT_VALUE_ARRAY_INITIALIZER("element_value_array_initializer"),
    ELEMENT_VALUE_PAIR("element_value_pair"),
    ENHANCED_FOR_STATEMENT("enhanced_for_statement"),
    ENUM_BODY("enum_body"),
    ENUM_BODY_DECLARATIONS("enum_body_declarations"),
    ENUM_CONSTANT("enum_constant"),
    ENUM_DECLARATION("enum_declaration"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXPLICIT_CONSTRUCTOR_INVOCATION("explicit_constructor_invocation"),
    EXPORTS_MODULE_DIRECTIVE("exports_module_directive"),
    EXPRESSION("expression"),
    EXPRESSION_STATEMENT("expression_statement"),
    EXTENDS_INTERFACES("extends_interfaces"),
    FALSE("false"),
    FIELD_ACCESS("field_access"),
    FIELD_DECLARATION("field_declaration"),
    FINALLY_CLAUSE("finally_clause"),
    FLOATING_POINT_TYPE("floating_point_type"),
    FORMAL_PARAMETER("formal_parameter"),
    FORMAL_PARAMETERS("formal_parameters"),
    FOR_STATEMENT("for_statement"),
    GENERIC_TYPE("generic_type"),
    GUARD("guard"),
    HEX_FLOATING_POINT_LITERAL("hex_floating_point_literal"),
    HEX_INTEGER_LITERAL("hex_integer_literal"),
    IDENTIFIER("identifier"),
    IF_STATEMENT("if_statement"),
    IMPORT_DECLARATION("import_declaration"),
    INFERRED_PARAMETERS("inferred_parameters"),
    INSTANCEOF_EXPRESSION("instanceof_expression"),
    INTEGRAL_TYPE("integral_type"),
    INTERFACE_BODY("interface_body"),
    INTERFACE_DECLARATION("interface_declaration"),
    LABELED_STATEMENT("labeled_statement"),
    LAMBDA_EXPRESSION("lambda_expression"),
    LINE_COMMENT("line_comment"),
    LITERAL("_literal"),
    LOCAL_VARIABLE_DECLARATION("local_variable_declaration"),
    MARKER_ANNOTATION("marker_annotation"),
    METHOD_DECLARATION("method_declaration"),
    METHOD_INVOCATION("method_invocation"),
    METHOD_REFERENCE("method_reference"),
    MODIFIERS("modifiers"),
    MODULE_BODY("module_body"),
    MODULE_DECLARATION("module_declaration"),
    MODULE_DIRECTIVE("module_directive"),
    MULTILINE_STRING_FRAGMENT("multiline_string_fragment"),
    NULL_LITERAL("null_literal"),
    OBJECT_CREATION_EXPRESSION("object_creation_expression"),
    OCTAL_INTEGER_LITERAL("octal_integer_literal"),
    OPENS_MODULE_DIRECTIVE("opens_module_directive"),
    PACKAGE_DECLARATION("package_declaration"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PATTERN("pattern"),
    PERMITS_("permits"),
    PRIMARY_EXPRESSION("primary_expression"),
    PROGRAM("program"),
    PROVIDES_MODULE_DIRECTIVE("provides_module_directive"),
    RECEIVER_PARAMETER("receiver_parameter"),
    RECORD_DECLARATION("record_declaration"),
    RECORD_PATTERN("record_pattern"),
    RECORD_PATTERN_BODY("record_pattern_body"),
    RECORD_PATTERN_COMPONENT("record_pattern_component"),
    REQUIRES_MODIFIER("requires_modifier"),
    REQUIRES_MODULE_DIRECTIVE("requires_module_directive"),
    RESOURCE("resource"),
    RESOURCE_SPECIFICATION("resource_specification"),
    RETURN_STATEMENT("return_statement"),
    SCOPED_IDENTIFIER("scoped_identifier"),
    SCOPED_TYPE_IDENTIFIER("scoped_type_identifier"),
    SIMPLE_TYPE("_simple_type"),
    SPREAD_PARAMETER("spread_parameter"),
    STATEMENT("statement"),
    STATIC_INITIALIZER("static_initializer"),
    STRING_FRAGMENT("string_fragment"),
    STRING_INTERPOLATION("string_interpolation"),
    STRING_LITERAL("string_literal"),
    SUPERCLASS("superclass"),
    SUPER_("super"),
    SUPER_INTERFACES("super_interfaces"),
    SWITCH_BLOCK("switch_block"),
    SWITCH_BLOCK_STATEMENT_GROUP("switch_block_statement_group"),
    SWITCH_EXPRESSION("switch_expression"),
    SWITCH_LABEL("switch_label"),
    SWITCH_RULE("switch_rule"),
    SYNCHRONIZED_STATEMENT("synchronized_statement"),
    TEMPLATE_EXPRESSION("template_expression"),
    TERNARY_EXPRESSION("ternary_expression"),
    THIS_("this"),
    THROWS_("throws"),
    THROW_STATEMENT("throw_statement"),
    TRUE("true"),
    TRY_STATEMENT("try_statement"),
    TRY_WITH_RESOURCES_STATEMENT("try_with_resources_statement"),
    TYPE("_type"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_BOUND("type_bound"),
    TYPE_IDENTIFIER("type_identifier"),
    TYPE_LIST("type_list"),
    TYPE_PARAMETER("type_parameter"),
    TYPE_PARAMETERS("type_parameters"),
    TYPE_PATTERN("type_pattern"),
    UNANNOTATED_TYPE("_unannotated_type"),
    UNARY_EXPRESSION("unary_expression"),
    UNDERSCORE_PATTERN("underscore_pattern"),
    UPDATE_EXPRESSION("update_expression"),
    USES_MODULE_DIRECTIVE("uses_module_directive"),
    VARIABLE_DECLARATOR("variable_declarator"),
    VOID_TYPE("void_type"),
    WHILE_STATEMENT("while_statement"),
    WILDCARD("wildcard"),
    YIELD_STATEMENT("yield_statement");

    public static final Set<JavaNodeType> DECLARATION_SET = Set.of(
            ANNOTATION_TYPE_DECLARATION,
            CLASS_DECLARATION,
            ENUM_DECLARATION,
            IMPORT_DECLARATION,
            INTERFACE_DECLARATION,
            MODULE_DECLARATION,
            PACKAGE_DECLARATION,
            RECORD_DECLARATION);
    public static final Set<JavaNodeType> EXPRESSION_SET = Set.of(
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
    public static final Set<JavaNodeType> LITERAL_SET = Set.of(
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
    public static final Set<JavaNodeType> MODULE_DIRECTIVE_SET = Set.of(
            EXPORTS_MODULE_DIRECTIVE,
            OPENS_MODULE_DIRECTIVE,
            PROVIDES_MODULE_DIRECTIVE,
            REQUIRES_MODULE_DIRECTIVE,
            USES_MODULE_DIRECTIVE);
    public static final Set<JavaNodeType> PRIMARY_EXPRESSION_SET = Set.of(
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
    public static final Set<JavaNodeType> SIMPLE_TYPE_SET = Set.of(
            BOOLEAN_TYPE,
            FLOATING_POINT_TYPE,
            GENERIC_TYPE,
            INTEGRAL_TYPE,
            SCOPED_TYPE_IDENTIFIER,
            TYPE_IDENTIFIER,
            VOID_TYPE);
    public static final Set<JavaNodeType> STATEMENT_SET = Set.of(
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
    public static final Set<JavaNodeType> TYPE_SET = Set.of(ANNOTATED_TYPE, UNANNOTATED_TYPE);
    public static final Set<JavaNodeType> UNANNOTATED_TYPE_SET = Set.of(ARRAY_TYPE, SIMPLE_TYPE);

    private final @Nullable String type;

    JavaNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static JavaNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static JavaNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        JavaNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, JavaNodeType> LOOKUP = initLookup();

    private static Map<String, JavaNodeType> initLookup() {
        HashMap<String, JavaNodeType> m = new HashMap<>();
        for (JavaNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
