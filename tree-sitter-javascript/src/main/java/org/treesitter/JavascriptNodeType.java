package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code javascript} from tree-sitter {@code node-types.json}.
 */
public enum JavascriptNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ARGUMENTS("arguments"),
    ARRAY("array"),
    ARRAY_PATTERN("array_pattern"),
    ARROW_FUNCTION("arrow_function"),
    ASSIGNMENT_EXPRESSION("assignment_expression"),
    ASSIGNMENT_PATTERN("assignment_pattern"),
    AUGMENTED_ASSIGNMENT_EXPRESSION("augmented_assignment_expression"),
    AWAIT_EXPRESSION("await_expression"),
    BINARY_EXPRESSION("binary_expression"),
    BREAK_STATEMENT("break_statement"),
    CALL_EXPRESSION("call_expression"),
    CATCH_CLAUSE("catch_clause"),
    CLASS_("class"),
    CLASS_BODY("class_body"),
    CLASS_DECLARATION("class_declaration"),
    CLASS_HERITAGE("class_heritage"),
    CLASS_STATIC_BLOCK("class_static_block"),
    COMMENT("comment"),
    COMPUTED_PROPERTY_NAME("computed_property_name"),
    CONTINUE_STATEMENT("continue_statement"),
    DEBUGGER_STATEMENT("debugger_statement"),
    DECLARATION("declaration"),
    DECORATOR("decorator"),
    DO_STATEMENT("do_statement"),
    ELSE_CLAUSE("else_clause"),
    EMPTY_STATEMENT("empty_statement"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXPORT_CLAUSE("export_clause"),
    EXPORT_SPECIFIER("export_specifier"),
    EXPORT_STATEMENT("export_statement"),
    EXPRESSION("expression"),
    EXPRESSION_STATEMENT("expression_statement"),
    FALSE("false"),
    FIELD_DEFINITION("field_definition"),
    FINALLY_CLAUSE("finally_clause"),
    FORMAL_PARAMETERS("formal_parameters"),
    FOR_IN_STATEMENT("for_in_statement"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DECLARATION("function_declaration"),
    FUNCTION_EXPRESSION("function_expression"),
    GENERATOR_FUNCTION("generator_function"),
    GENERATOR_FUNCTION_DECLARATION("generator_function_declaration"),
    HASH_BANG_LINE("hash_bang_line"),
    HTML_CHARACTER_REFERENCE("html_character_reference"),
    HTML_COMMENT("html_comment"),
    IDENTIFIER("identifier"),
    IF_STATEMENT("if_statement"),
    IMPORT_("import"),
    IMPORT_ATTRIBUTE("import_attribute"),
    IMPORT_CLAUSE("import_clause"),
    IMPORT_SPECIFIER("import_specifier"),
    IMPORT_STATEMENT("import_statement"),
    JSX_ATTRIBUTE("jsx_attribute"),
    JSX_CLOSING_ELEMENT("jsx_closing_element"),
    JSX_ELEMENT("jsx_element"),
    JSX_EXPRESSION("jsx_expression"),
    JSX_NAMESPACE_NAME("jsx_namespace_name"),
    JSX_OPENING_ELEMENT("jsx_opening_element"),
    JSX_SELF_CLOSING_ELEMENT("jsx_self_closing_element"),
    JSX_TEXT("jsx_text"),
    LABELED_STATEMENT("labeled_statement"),
    LEXICAL_DECLARATION("lexical_declaration"),
    MEMBER_EXPRESSION("member_expression"),
    META_PROPERTY("meta_property"),
    METHOD_DEFINITION("method_definition"),
    NAMED_IMPORTS("named_imports"),
    NAMESPACE_EXPORT("namespace_export"),
    NAMESPACE_IMPORT("namespace_import"),
    NEW_EXPRESSION("new_expression"),
    NULL("null"),
    NUMBER("number"),
    OBJECT("object"),
    OBJECT_ASSIGNMENT_PATTERN("object_assignment_pattern"),
    OBJECT_PATTERN("object_pattern"),
    OPTIONAL_CHAIN("optional_chain"),
    PAIR("pair"),
    PAIR_PATTERN("pair_pattern"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PATTERN("pattern"),
    PRIMARY_EXPRESSION("primary_expression"),
    PRIVATE_PROPERTY_IDENTIFIER("private_property_identifier"),
    PROGRAM("program"),
    PROPERTY_IDENTIFIER("property_identifier"),
    REGEX("regex"),
    REGEX_FLAGS("regex_flags"),
    REGEX_PATTERN("regex_pattern"),
    REST_PATTERN("rest_pattern"),
    RETURN_STATEMENT("return_statement"),
    SEQUENCE_EXPRESSION("sequence_expression"),
    SHORTHAND_PROPERTY_IDENTIFIER("shorthand_property_identifier"),
    SHORTHAND_PROPERTY_IDENTIFIER_PATTERN("shorthand_property_identifier_pattern"),
    SPREAD_ELEMENT("spread_element"),
    STATEMENT("statement"),
    STATEMENT_BLOCK("statement_block"),
    STATEMENT_IDENTIFIER("statement_identifier"),
    STRING("string"),
    STRING_FRAGMENT("string_fragment"),
    SUBSCRIPT_EXPRESSION("subscript_expression"),
    SUPER_("super"),
    SWITCH_BODY("switch_body"),
    SWITCH_CASE("switch_case"),
    SWITCH_DEFAULT("switch_default"),
    SWITCH_STATEMENT("switch_statement"),
    TEMPLATE_STRING("template_string"),
    TEMPLATE_SUBSTITUTION("template_substitution"),
    TERNARY_EXPRESSION("ternary_expression"),
    THIS_("this"),
    THROW_STATEMENT("throw_statement"),
    TRUE("true"),
    TRY_STATEMENT("try_statement"),
    UNARY_EXPRESSION("unary_expression"),
    UNDEFINED("undefined"),
    UPDATE_EXPRESSION("update_expression"),
    USING_DECLARATION("using_declaration"),
    VARIABLE_DECLARATION("variable_declaration"),
    VARIABLE_DECLARATOR("variable_declarator"),
    WHILE_STATEMENT("while_statement"),
    WITH_STATEMENT("with_statement"),
    YIELD_EXPRESSION("yield_expression");

    public static final Set<JavascriptNodeType> DECLARATION_SET = Set.of(
            CLASS_DECLARATION,
            FUNCTION_DECLARATION,
            GENERATOR_FUNCTION_DECLARATION,
            LEXICAL_DECLARATION,
            USING_DECLARATION,
            VARIABLE_DECLARATION);
    public static final Set<JavascriptNodeType> EXPRESSION_SET = Set.of(
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
    public static final Set<JavascriptNodeType> PATTERN_SET = Set.of(
            ARRAY_PATTERN,
            IDENTIFIER,
            MEMBER_EXPRESSION,
            OBJECT_PATTERN,
            REST_PATTERN,
            SUBSCRIPT_EXPRESSION,
            UNDEFINED);
    public static final Set<JavascriptNodeType> PRIMARY_EXPRESSION_SET = Set.of(
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
    public static final Set<JavascriptNodeType> STATEMENT_SET = Set.of(
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

    private final @Nullable String type;

    JavascriptNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static JavascriptNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static JavascriptNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        JavascriptNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, JavascriptNodeType> LOOKUP = initLookup();

    private static Map<String, JavascriptNodeType> initLookup() {
        HashMap<String, JavascriptNodeType> m = new HashMap<>();
        for (JavascriptNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
