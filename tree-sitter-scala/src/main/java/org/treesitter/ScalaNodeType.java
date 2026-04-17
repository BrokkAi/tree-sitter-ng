package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code scala} from tree-sitter {@code node-types.json}.
 */
public enum ScalaNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ACCESS_MODIFIER("access_modifier"),
    ACCESS_QUALIFIER("access_qualifier"),
    ALTERNATIVE_PATTERN("alternative_pattern"),
    ANNOTATED_TYPE("annotated_type"),
    ANNOTATION("annotation"),
    ARGUMENTS("arguments"),
    ARROW_RENAMED_IDENTIFIER("arrow_renamed_identifier"),
    ASCRIPTION_EXPRESSION("ascription_expression"),
    ASSIGNMENT_EXPRESSION("assignment_expression"),
    AS_RENAMED_IDENTIFIER("as_renamed_identifier"),
    BINDING("binding"),
    BINDINGS("bindings"),
    BLOCK("block"),
    BLOCK_COMMENT("block_comment"),
    BOOLEAN_LITERAL("boolean_literal"),
    CALL_EXPRESSION("call_expression"),
    CAPTURE_PATTERN("capture_pattern"),
    CASE_BLOCK("case_block"),
    CASE_CLASS_PATTERN("case_class_pattern"),
    CASE_CLAUSE("case_clause"),
    CATCH_CLAUSE("catch_clause"),
    CHARACTER_LITERAL("character_literal"),
    CLASS_DEFINITION("class_definition"),
    CLASS_PARAMETER("class_parameter"),
    CLASS_PARAMETERS("class_parameters"),
    COLON_ARGUMENT("colon_argument"),
    COMMENT("comment"),
    COMPILATION_UNIT("compilation_unit"),
    COMPOUND_TYPE("compound_type"),
    CONTEXT_BOUND("context_bound"),
    CONTRAVARIANT_TYPE_PARAMETER("contravariant_type_parameter"),
    COVARIANT_TYPE_PARAMETER("covariant_type_parameter"),
    DEFINITION("_definition"),
    DERIVES_CLAUSE("derives_clause"),
    DO_WHILE_EXPRESSION("do_while_expression"),
    ENUMERATOR("enumerator"),
    ENUMERATORS("enumerators"),
    ENUM_BODY("enum_body"),
    ENUM_CASE_DEFINITIONS("enum_case_definitions"),
    ENUM_DEFINITION("enum_definition"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXPORT_DECLARATION("export_declaration"),
    EXPRESSION("expression"),
    EXTENDS_CLAUSE("extends_clause"),
    EXTENSION_DEFINITION("extension_definition"),
    FIELD_EXPRESSION("field_expression"),
    FINALLY_CLAUSE("finally_clause"),
    FLOATING_POINT_LITERAL("floating_point_literal"),
    FOR_EXPRESSION("for_expression"),
    FULL_ENUM_CASE("full_enum_case"),
    FUNCTION_DECLARATION("function_declaration"),
    FUNCTION_DEFINITION("function_definition"),
    FUNCTION_TYPE("function_type"),
    GENERIC_FUNCTION("generic_function"),
    GENERIC_TYPE("generic_type"),
    GIVEN_CONDITIONAL("given_conditional"),
    GIVEN_DEFINITION("given_definition"),
    GIVEN_PATTERN("given_pattern"),
    GUARD("guard"),
    IDENTIFIER("identifier"),
    IDENTIFIERS("identifiers"),
    IF_EXPRESSION("if_expression"),
    IMPORT_DECLARATION("import_declaration"),
    INDENTED_BLOCK("indented_block"),
    INDENTED_CASES("indented_cases"),
    INFIX_EXPRESSION("infix_expression"),
    INFIX_MODIFIER("infix_modifier"),
    INFIX_PATTERN("infix_pattern"),
    INFIX_TYPE("infix_type"),
    INLINE_MODIFIER("inline_modifier"),
    INSTANCE_EXPRESSION("instance_expression"),
    INTEGER_LITERAL("integer_literal"),
    INTERPOLATED_STRING("interpolated_string"),
    INTERPOLATED_STRING_EXPRESSION("interpolated_string_expression"),
    INTERPOLATION("interpolation"),
    LAMBDA_EXPRESSION("lambda_expression"),
    LAZY_PARAMETER_TYPE("lazy_parameter_type"),
    LITERAL_TYPE("literal_type"),
    LOWER_BOUND("lower_bound"),
    MACRO_BODY("macro_body"),
    MATCH_EXPRESSION("match_expression"),
    MATCH_TYPE("match_type"),
    MODIFIERS("modifiers"),
    NAMED_PATTERN("named_pattern"),
    NAMED_TUPLE_PATTERN("named_tuple_pattern"),
    NAMED_TUPLE_TYPE("named_tuple_type"),
    NAMESPACE_SELECTORS("namespace_selectors"),
    NAMESPACE_WILDCARD("namespace_wildcard"),
    NAME_AND_TYPE("name_and_type"),
    NULL_LITERAL("null_literal"),
    OBJECT_DEFINITION("object_definition"),
    OPAQUE_MODIFIER("opaque_modifier"),
    OPEN_MODIFIER("open_modifier"),
    OPERATOR_IDENTIFIER("operator_identifier"),
    PACKAGE_CLAUSE("package_clause"),
    PACKAGE_IDENTIFIER("package_identifier"),
    PACKAGE_OBJECT("package_object"),
    PARAMETER("parameter"),
    PARAMETERS("parameters"),
    PARAMETER_TYPES("parameter_types"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PATTERN("_pattern"),
    POSTFIX_EXPRESSION("postfix_expression"),
    PREFIX_EXPRESSION("prefix_expression"),
    PROJECTED_TYPE("projected_type"),
    QUOTE_EXPRESSION("quote_expression"),
    REFINEMENT("refinement"),
    REPEATED_PARAMETER_TYPE("repeated_parameter_type"),
    REPEAT_PATTERN("repeat_pattern"),
    RETURN_EXPRESSION("return_expression"),
    SELF_TYPE("self_type"),
    SIMPLE_ENUM_CASE("simple_enum_case"),
    SINGLETON_TYPE("singleton_type"),
    SPLICE_EXPRESSION("splice_expression"),
    STABLE_IDENTIFIER("stable_identifier"),
    STABLE_TYPE_IDENTIFIER("stable_type_identifier"),
    STRING("string"),
    STRUCTURAL_TYPE("structural_type"),
    TEMPLATE_BODY("template_body"),
    THROW_EXPRESSION("throw_expression"),
    TRAIT_DEFINITION("trait_definition"),
    TRANSPARENT_MODIFIER("transparent_modifier"),
    TRY_EXPRESSION("try_expression"),
    TUPLE_EXPRESSION("tuple_expression"),
    TUPLE_PATTERN("tuple_pattern"),
    TUPLE_TYPE("tuple_type"),
    TYPED_PATTERN("typed_pattern"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_CASE_CLAUSE("type_case_clause"),
    TYPE_DEFINITION("type_definition"),
    TYPE_LAMBDA("type_lambda"),
    TYPE_PARAMETERS("type_parameters"),
    UNIT("unit"),
    UPPER_BOUND("upper_bound"),
    USING_DIRECTIVE("using_directive"),
    USING_DIRECTIVE_KEY("using_directive_key"),
    USING_DIRECTIVE_VALUE("using_directive_value"),
    VAL_DECLARATION("val_declaration"),
    VAL_DEFINITION("val_definition"),
    VAR_DECLARATION("var_declaration"),
    VAR_DEFINITION("var_definition"),
    VIEW_BOUND("view_bound"),
    WHILE_EXPRESSION("while_expression"),
    WILDCARD("wildcard"),
    WITH_TEMPLATE_BODY("with_template_body");

    public static final Set<ScalaNodeType> DEFINITION_SET = Set.of(
            CLASS_DEFINITION,
            ENUM_DEFINITION,
            EXPORT_DECLARATION,
            EXTENSION_DEFINITION,
            FUNCTION_DECLARATION,
            FUNCTION_DEFINITION,
            GIVEN_DEFINITION,
            IMPORT_DECLARATION,
            OBJECT_DEFINITION,
            PACKAGE_CLAUSE,
            PACKAGE_OBJECT,
            TRAIT_DEFINITION,
            TYPE_DEFINITION,
            VAL_DECLARATION,
            VAL_DEFINITION,
            VAR_DECLARATION,
            VAR_DEFINITION);
    public static final Set<ScalaNodeType> EXPRESSION_SET = Set.of(
            ASCRIPTION_EXPRESSION,
            ASSIGNMENT_EXPRESSION,
            BLOCK,
            BOOLEAN_LITERAL,
            CALL_EXPRESSION,
            CASE_BLOCK,
            CHARACTER_LITERAL,
            DO_WHILE_EXPRESSION,
            FIELD_EXPRESSION,
            FLOATING_POINT_LITERAL,
            FOR_EXPRESSION,
            GENERIC_FUNCTION,
            IDENTIFIER,
            IF_EXPRESSION,
            INFIX_EXPRESSION,
            INSTANCE_EXPRESSION,
            INTEGER_LITERAL,
            INTERPOLATED_STRING_EXPRESSION,
            LAMBDA_EXPRESSION,
            MACRO_BODY,
            MATCH_EXPRESSION,
            NULL_LITERAL,
            OPERATOR_IDENTIFIER,
            PARENTHESIZED_EXPRESSION,
            POSTFIX_EXPRESSION,
            PREFIX_EXPRESSION,
            QUOTE_EXPRESSION,
            RETURN_EXPRESSION,
            SPLICE_EXPRESSION,
            STRING,
            THROW_EXPRESSION,
            TRY_EXPRESSION,
            TUPLE_EXPRESSION,
            UNIT,
            WHILE_EXPRESSION,
            WILDCARD);
    public static final Set<ScalaNodeType> PATTERN_SET = Set.of(
            ALTERNATIVE_PATTERN,
            BOOLEAN_LITERAL,
            CAPTURE_PATTERN,
            CASE_CLASS_PATTERN,
            CHARACTER_LITERAL,
            FLOATING_POINT_LITERAL,
            GIVEN_PATTERN,
            IDENTIFIER,
            INFIX_PATTERN,
            INTEGER_LITERAL,
            INTERPOLATED_STRING_EXPRESSION,
            NAMED_TUPLE_PATTERN,
            NULL_LITERAL,
            OPERATOR_IDENTIFIER,
            QUOTE_EXPRESSION,
            REPEAT_PATTERN,
            STABLE_IDENTIFIER,
            STRING,
            TUPLE_PATTERN,
            TYPED_PATTERN,
            WILDCARD);

    private final @Nullable String type;

    ScalaNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static ScalaNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static ScalaNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        ScalaNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, ScalaNodeType> LOOKUP = initLookup();

    private static Map<String, ScalaNodeType> initLookup() {
        HashMap<String, ScalaNodeType> m = new HashMap<>();
        for (ScalaNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
