package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code kotlin} from tree-sitter {@code node-types.json}.
 */
public enum KotlinNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ADDITIVE_EXPRESSION("additive_expression"),
    ANNOTATED_LAMBDA("annotated_lambda"),
    ANNOTATION("annotation"),
    ANONYMOUS_FUNCTION("anonymous_function"),
    ANONYMOUS_INITIALIZER("anonymous_initializer"),
    ASSIGNMENT("assignment"),
    AS_EXPRESSION("as_expression"),
    BINDING_PATTERN_KIND("binding_pattern_kind"),
    BIN_LITERAL("bin_literal"),
    BOOLEAN_LITERAL("boolean_literal"),
    CALLABLE_REFERENCE("callable_reference"),
    CALL_EXPRESSION("call_expression"),
    CALL_SUFFIX("call_suffix"),
    CATCH_BLOCK("catch_block"),
    CHARACTER_ESCAPE_SEQ("character_escape_seq"),
    CHARACTER_LITERAL("character_literal"),
    CHECK_EXPRESSION("check_expression"),
    CLASS_BODY("class_body"),
    CLASS_DECLARATION("class_declaration"),
    CLASS_MODIFIER("class_modifier"),
    CLASS_PARAMETER("class_parameter"),
    COLLECTION_LITERAL("collection_literal"),
    COMPANION_OBJECT("companion_object"),
    COMPARISON_EXPRESSION("comparison_expression"),
    CONJUNCTION_EXPRESSION("conjunction_expression"),
    CONSTRUCTOR_DELEGATION_CALL("constructor_delegation_call"),
    CONSTRUCTOR_INVOCATION("constructor_invocation"),
    CONTROL_STRUCTURE_BODY("control_structure_body"),
    DELEGATION_SPECIFIER("delegation_specifier"),
    DIRECTLY_ASSIGNABLE_EXPRESSION("directly_assignable_expression"),
    DISJUNCTION_EXPRESSION("disjunction_expression"),
    DO_WHILE_STATEMENT("do_while_statement"),
    ELVIS_EXPRESSION("elvis_expression"),
    ENUM_CLASS_BODY("enum_class_body"),
    ENUM_ENTRY("enum_entry"),
    EQUALITY_EXPRESSION("equality_expression"),
    EXPLICIT_DELEGATION("explicit_delegation"),
    FILE_ANNOTATION("file_annotation"),
    FINALLY_BLOCK("finally_block"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_BODY("function_body"),
    FUNCTION_DECLARATION("function_declaration"),
    FUNCTION_MODIFIER("function_modifier"),
    FUNCTION_TYPE("function_type"),
    FUNCTION_TYPE_PARAMETERS("function_type_parameters"),
    FUNCTION_VALUE_PARAMETERS("function_value_parameters"),
    GETTER("getter"),
    HEX_LITERAL("hex_literal"),
    IDENTIFIER("identifier"),
    IF_EXPRESSION("if_expression"),
    IMPORT_ALIAS("import_alias"),
    IMPORT_HEADER("import_header"),
    IMPORT_LIST("import_list"),
    INDEXING_EXPRESSION("indexing_expression"),
    INDEXING_SUFFIX("indexing_suffix"),
    INFIX_EXPRESSION("infix_expression"),
    INHERITANCE_MODIFIER("inheritance_modifier"),
    INTEGER_LITERAL("integer_literal"),
    INTERPOLATED_EXPRESSION("interpolated_expression"),
    INTERPOLATED_IDENTIFIER("interpolated_identifier"),
    JUMP_EXPRESSION("jump_expression"),
    LABEL("label"),
    LAMBDA_LITERAL("lambda_literal"),
    LAMBDA_PARAMETERS("lambda_parameters"),
    LINE_COMMENT("line_comment"),
    LONG_LITERAL("long_literal"),
    MEMBER_MODIFIER("member_modifier"),
    MODIFIERS("modifiers"),
    MULTILINE_COMMENT("multiline_comment"),
    MULTIPLICATIVE_EXPRESSION("multiplicative_expression"),
    MULTI_VARIABLE_DECLARATION("multi_variable_declaration"),
    NAVIGATION_EXPRESSION("navigation_expression"),
    NAVIGATION_SUFFIX("navigation_suffix"),
    NOT_NULLABLE_TYPE("not_nullable_type"),
    NULLABLE_TYPE("nullable_type"),
    OBJECT_DECLARATION("object_declaration"),
    OBJECT_LITERAL("object_literal"),
    PACKAGE_HEADER("package_header"),
    PARAMETER("parameter"),
    PARAMETER_MODIFIER("parameter_modifier"),
    PARAMETER_MODIFIERS("parameter_modifiers"),
    PARAMETER_WITH_OPTIONAL_TYPE("parameter_with_optional_type"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PARENTHESIZED_TYPE("parenthesized_type"),
    PARENTHESIZED_USER_TYPE("parenthesized_user_type"),
    PLATFORM_MODIFIER("platform_modifier"),
    POSTFIX_EXPRESSION("postfix_expression"),
    PREFIX_EXPRESSION("prefix_expression"),
    PRIMARY_CONSTRUCTOR("primary_constructor"),
    PROPERTY_DECLARATION("property_declaration"),
    PROPERTY_DELEGATE("property_delegate"),
    PROPERTY_MODIFIER("property_modifier"),
    RANGE_EXPRESSION("range_expression"),
    RANGE_TEST("range_test"),
    REAL_LITERAL("real_literal"),
    REIFICATION_MODIFIER("reification_modifier"),
    SECONDARY_CONSTRUCTOR("secondary_constructor"),
    SETTER("setter"),
    SHEBANG_LINE("shebang_line"),
    SIMPLE_IDENTIFIER("simple_identifier"),
    SOURCE_FILE("source_file"),
    SPREAD_EXPRESSION("spread_expression"),
    STATEMENTS("statements"),
    STRING_CONTENT("string_content"),
    STRING_LITERAL("string_literal"),
    SUPER_EXPRESSION("super_expression"),
    THIS_EXPRESSION("this_expression"),
    TRY_EXPRESSION("try_expression"),
    TYPE_ALIAS("type_alias"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_CONSTRAINT("type_constraint"),
    TYPE_CONSTRAINTS("type_constraints"),
    TYPE_IDENTIFIER("type_identifier"),
    TYPE_MODIFIERS("type_modifiers"),
    TYPE_PARAMETER("type_parameter"),
    TYPE_PARAMETERS("type_parameters"),
    TYPE_PARAMETER_MODIFIERS("type_parameter_modifiers"),
    TYPE_PROJECTION("type_projection"),
    TYPE_PROJECTION_MODIFIERS("type_projection_modifiers"),
    TYPE_TEST("type_test"),
    UNSIGNED_LITERAL("unsigned_literal"),
    USER_TYPE("user_type"),
    USE_SITE_TARGET("use_site_target"),
    VALUE_ARGUMENT("value_argument"),
    VALUE_ARGUMENTS("value_arguments"),
    VARIABLE_DECLARATION("variable_declaration"),
    VARIANCE_MODIFIER("variance_modifier"),
    VISIBILITY_MODIFIER("visibility_modifier"),
    WHEN_CONDITION("when_condition"),
    WHEN_ENTRY("when_entry"),
    WHEN_EXPRESSION("when_expression"),
    WHEN_SUBJECT("when_subject"),
    WHILE_STATEMENT("while_statement"),
    WILDCARD_IMPORT("wildcard_import");

    private final @Nullable String type;

    KotlinNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static KotlinNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static KotlinNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        KotlinNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, KotlinNodeType> LOOKUP = initLookup();

    private static Map<String, KotlinNodeType> initLookup() {
        HashMap<String, KotlinNodeType> m = new HashMap<>();
        for (KotlinNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
