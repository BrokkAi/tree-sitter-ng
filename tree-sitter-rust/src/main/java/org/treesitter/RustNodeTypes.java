package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code rust} from tree-sitter {@code node-types.json}.
 */
public final class RustNodeTypes {
    private RustNodeTypes() {}

    public static final String ABSTRACT_TYPE = "abstract_type";
    public static final String ARGUMENTS = "arguments";
    public static final String ARRAY_EXPRESSION = "array_expression";
    public static final String ARRAY_TYPE = "array_type";
    public static final String ASSIGNMENT_EXPRESSION = "assignment_expression";
    public static final String ASSOCIATED_TYPE = "associated_type";
    public static final String ASYNC_BLOCK = "async_block";
    public static final String ATTRIBUTE = "attribute";
    public static final String ATTRIBUTE_ITEM = "attribute_item";
    public static final String AWAIT_EXPRESSION = "await_expression";
    public static final String BASE_FIELD_INITIALIZER = "base_field_initializer";
    public static final String BINARY_EXPRESSION = "binary_expression";
    public static final String BLOCK = "block";
    public static final String BLOCK_COMMENT = "block_comment";
    public static final String BOOLEAN_LITERAL = "boolean_literal";
    public static final String BOUNDED_TYPE = "bounded_type";
    public static final String BRACKETED_TYPE = "bracketed_type";
    public static final String BREAK_EXPRESSION = "break_expression";
    public static final String CALL_EXPRESSION = "call_expression";
    public static final String CAPTURED_PATTERN = "captured_pattern";
    public static final String CHAR_LITERAL = "char_literal";
    public static final String CLOSURE_EXPRESSION = "closure_expression";
    public static final String CLOSURE_PARAMETERS = "closure_parameters";
    public static final String COMPOUND_ASSIGNMENT_EXPR = "compound_assignment_expr";
    public static final String CONST_BLOCK = "const_block";
    public static final String CONST_ITEM = "const_item";
    public static final String CONST_PARAMETER = "const_parameter";
    public static final String CONTINUE_EXPRESSION = "continue_expression";
    public static final String CRATE = "crate";
    public static final String DECLARATION_LIST = "declaration_list";
    public static final String DECLARATION_STATEMENT = "_declaration_statement";
    public static final String DOC_COMMENT = "doc_comment";
    public static final String DYNAMIC_TYPE = "dynamic_type";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String EMPTY_STATEMENT = "empty_statement";
    public static final String ENUM_ITEM = "enum_item";
    public static final String ENUM_VARIANT = "enum_variant";
    public static final String ENUM_VARIANT_LIST = "enum_variant_list";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXPRESSION = "_expression";
    public static final String EXPRESSION_STATEMENT = "expression_statement";
    public static final String EXTERN_CRATE_DECLARATION = "extern_crate_declaration";
    public static final String EXTERN_MODIFIER = "extern_modifier";
    public static final String FIELD_DECLARATION = "field_declaration";
    public static final String FIELD_DECLARATION_LIST = "field_declaration_list";
    public static final String FIELD_EXPRESSION = "field_expression";
    public static final String FIELD_IDENTIFIER = "field_identifier";
    public static final String FIELD_INITIALIZER = "field_initializer";
    public static final String FIELD_INITIALIZER_LIST = "field_initializer_list";
    public static final String FIELD_PATTERN = "field_pattern";
    public static final String FLOAT_LITERAL = "float_literal";
    public static final String FOREIGN_MOD_ITEM = "foreign_mod_item";
    public static final String FOR_EXPRESSION = "for_expression";
    public static final String FOR_LIFETIMES = "for_lifetimes";
    public static final String FRAGMENT_SPECIFIER = "fragment_specifier";
    public static final String FUNCTION_ITEM = "function_item";
    public static final String FUNCTION_MODIFIERS = "function_modifiers";
    public static final String FUNCTION_SIGNATURE_ITEM = "function_signature_item";
    public static final String FUNCTION_TYPE = "function_type";
    public static final String GENERIC_FUNCTION = "generic_function";
    public static final String GENERIC_PATTERN = "generic_pattern";
    public static final String GENERIC_TYPE = "generic_type";
    public static final String GENERIC_TYPE_WITH_TURBOFISH = "generic_type_with_turbofish";
    public static final String GEN_BLOCK = "gen_block";
    public static final String HIGHER_RANKED_TRAIT_BOUND = "higher_ranked_trait_bound";
    public static final String IDENTIFIER = "identifier";
    public static final String IF_EXPRESSION = "if_expression";
    public static final String IMPL_ITEM = "impl_item";
    public static final String INDEX_EXPRESSION = "index_expression";
    public static final String INNER_ATTRIBUTE_ITEM = "inner_attribute_item";
    public static final String INNER_DOC_COMMENT_MARKER = "inner_doc_comment_marker";
    public static final String INTEGER_LITERAL = "integer_literal";
    public static final String LABEL = "label";
    public static final String LET_CHAIN = "let_chain";
    public static final String LET_CONDITION = "let_condition";
    public static final String LET_DECLARATION = "let_declaration";
    public static final String LIFETIME = "lifetime";
    public static final String LIFETIME_PARAMETER = "lifetime_parameter";
    public static final String LINE_COMMENT = "line_comment";
    public static final String LITERAL = "_literal";
    public static final String LITERAL_PATTERN = "_literal_pattern";
    public static final String LOOP_EXPRESSION = "loop_expression";
    public static final String MACRO_DEFINITION = "macro_definition";
    public static final String MACRO_INVOCATION = "macro_invocation";
    public static final String MACRO_RULE = "macro_rule";
    public static final String MATCH_ARM = "match_arm";
    public static final String MATCH_BLOCK = "match_block";
    public static final String MATCH_EXPRESSION = "match_expression";
    public static final String MATCH_PATTERN = "match_pattern";
    public static final String METAVARIABLE = "metavariable";
    public static final String MOD_ITEM = "mod_item";
    public static final String MUTABLE_SPECIFIER = "mutable_specifier";
    public static final String MUT_PATTERN = "mut_pattern";
    public static final String NEGATIVE_LITERAL = "negative_literal";
    public static final String NEVER_TYPE = "never_type";
    public static final String ORDERED_FIELD_DECLARATION_LIST = "ordered_field_declaration_list";
    public static final String OR_PATTERN = "or_pattern";
    public static final String OUTER_DOC_COMMENT_MARKER = "outer_doc_comment_marker";
    public static final String PARAMETER = "parameter";
    public static final String PARAMETERS = "parameters";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PATTERN = "_pattern";
    public static final String POINTER_TYPE = "pointer_type";
    public static final String PRIMITIVE_TYPE = "primitive_type";
    public static final String QUALIFIED_TYPE = "qualified_type";
    public static final String RANGE_EXPRESSION = "range_expression";
    public static final String RANGE_PATTERN = "range_pattern";
    public static final String RAW_STRING_LITERAL = "raw_string_literal";
    public static final String REFERENCE_EXPRESSION = "reference_expression";
    public static final String REFERENCE_PATTERN = "reference_pattern";
    public static final String REFERENCE_TYPE = "reference_type";
    public static final String REF_PATTERN = "ref_pattern";
    public static final String REMAINING_FIELD_PATTERN = "remaining_field_pattern";
    public static final String REMOVED_TRAIT_BOUND = "removed_trait_bound";
    public static final String RETURN_EXPRESSION = "return_expression";
    public static final String SCOPED_IDENTIFIER = "scoped_identifier";
    public static final String SCOPED_TYPE_IDENTIFIER = "scoped_type_identifier";
    public static final String SCOPED_USE_LIST = "scoped_use_list";
    public static final String SELF = "self";
    public static final String SELF_PARAMETER = "self_parameter";
    public static final String SHEBANG = "shebang";
    public static final String SHORTHAND_FIELD_IDENTIFIER = "shorthand_field_identifier";
    public static final String SHORTHAND_FIELD_INITIALIZER = "shorthand_field_initializer";
    public static final String SLICE_PATTERN = "slice_pattern";
    public static final String SOURCE_FILE = "source_file";
    public static final String STATIC_ITEM = "static_item";
    public static final String STRING_CONTENT = "string_content";
    public static final String STRING_LITERAL = "string_literal";
    public static final String STRUCT_EXPRESSION = "struct_expression";
    public static final String STRUCT_ITEM = "struct_item";
    public static final String STRUCT_PATTERN = "struct_pattern";
    public static final String SUPER_ = "super";
    public static final String TOKEN_BINDING_PATTERN = "token_binding_pattern";
    public static final String TOKEN_REPETITION = "token_repetition";
    public static final String TOKEN_REPETITION_PATTERN = "token_repetition_pattern";
    public static final String TOKEN_TREE = "token_tree";
    public static final String TOKEN_TREE_PATTERN = "token_tree_pattern";
    public static final String TRAIT_BOUNDS = "trait_bounds";
    public static final String TRAIT_ITEM = "trait_item";
    public static final String TRY_BLOCK = "try_block";
    public static final String TRY_EXPRESSION = "try_expression";
    public static final String TUPLE_EXPRESSION = "tuple_expression";
    public static final String TUPLE_PATTERN = "tuple_pattern";
    public static final String TUPLE_STRUCT_PATTERN = "tuple_struct_pattern";
    public static final String TUPLE_TYPE = "tuple_type";
    public static final String TYPE = "_type";
    public static final String TYPE_ARGUMENTS = "type_arguments";
    public static final String TYPE_BINDING = "type_binding";
    public static final String TYPE_CAST_EXPRESSION = "type_cast_expression";
    public static final String TYPE_IDENTIFIER = "type_identifier";
    public static final String TYPE_ITEM = "type_item";
    public static final String TYPE_PARAMETER = "type_parameter";
    public static final String TYPE_PARAMETERS = "type_parameters";
    public static final String UNARY_EXPRESSION = "unary_expression";
    public static final String UNION_ITEM = "union_item";
    public static final String UNIT_EXPRESSION = "unit_expression";
    public static final String UNIT_TYPE = "unit_type";
    public static final String UNSAFE_BLOCK = "unsafe_block";
    public static final String USE_AS_CLAUSE = "use_as_clause";
    public static final String USE_BOUNDS = "use_bounds";
    public static final String USE_DECLARATION = "use_declaration";
    public static final String USE_LIST = "use_list";
    public static final String USE_WILDCARD = "use_wildcard";
    public static final String VARIADIC_PARAMETER = "variadic_parameter";
    public static final String VISIBILITY_MODIFIER = "visibility_modifier";
    public static final String WHERE_CLAUSE = "where_clause";
    public static final String WHERE_PREDICATE = "where_predicate";
    public static final String WHILE_EXPRESSION = "while_expression";
    public static final String YIELD_EXPRESSION = "yield_expression";

    public static final Set<String> DECLARATION_STATEMENT_SET = Set.of(
            ASSOCIATED_TYPE,
            ATTRIBUTE_ITEM,
            CONST_ITEM,
            EMPTY_STATEMENT,
            ENUM_ITEM,
            EXTERN_CRATE_DECLARATION,
            FOREIGN_MOD_ITEM,
            FUNCTION_ITEM,
            FUNCTION_SIGNATURE_ITEM,
            IMPL_ITEM,
            INNER_ATTRIBUTE_ITEM,
            LET_DECLARATION,
            MACRO_DEFINITION,
            MACRO_INVOCATION,
            MOD_ITEM,
            STATIC_ITEM,
            STRUCT_ITEM,
            TRAIT_ITEM,
            TYPE_ITEM,
            UNION_ITEM,
            USE_DECLARATION);
    public static final Set<String> EXPRESSION_SET = Set.of(
            ARRAY_EXPRESSION,
            ASSIGNMENT_EXPRESSION,
            ASYNC_BLOCK,
            AWAIT_EXPRESSION,
            BINARY_EXPRESSION,
            BLOCK,
            BREAK_EXPRESSION,
            CALL_EXPRESSION,
            CLOSURE_EXPRESSION,
            COMPOUND_ASSIGNMENT_EXPR,
            CONST_BLOCK,
            CONTINUE_EXPRESSION,
            FIELD_EXPRESSION,
            FOR_EXPRESSION,
            GENERIC_FUNCTION,
            GEN_BLOCK,
            IDENTIFIER,
            IF_EXPRESSION,
            INDEX_EXPRESSION,
            LITERAL,
            LOOP_EXPRESSION,
            MACRO_INVOCATION,
            MATCH_EXPRESSION,
            METAVARIABLE,
            PARENTHESIZED_EXPRESSION,
            RANGE_EXPRESSION,
            REFERENCE_EXPRESSION,
            RETURN_EXPRESSION,
            SCOPED_IDENTIFIER,
            SELF,
            STRUCT_EXPRESSION,
            TRY_BLOCK,
            TRY_EXPRESSION,
            TUPLE_EXPRESSION,
            TYPE_CAST_EXPRESSION,
            UNARY_EXPRESSION,
            UNIT_EXPRESSION,
            UNSAFE_BLOCK,
            WHILE_EXPRESSION,
            YIELD_EXPRESSION);
    public static final Set<String> LITERAL_PATTERN_SET = Set.of(
            BOOLEAN_LITERAL,
            CHAR_LITERAL,
            FLOAT_LITERAL,
            INTEGER_LITERAL,
            NEGATIVE_LITERAL,
            RAW_STRING_LITERAL,
            STRING_LITERAL);
    public static final Set<String> LITERAL_SET =
            Set.of(BOOLEAN_LITERAL, CHAR_LITERAL, FLOAT_LITERAL, INTEGER_LITERAL, RAW_STRING_LITERAL, STRING_LITERAL);
    public static final Set<String> PATTERN_SET = Set.of(
            CAPTURED_PATTERN,
            CONST_BLOCK,
            GENERIC_PATTERN,
            IDENTIFIER,
            LITERAL_PATTERN,
            MACRO_INVOCATION,
            MUT_PATTERN,
            OR_PATTERN,
            RANGE_PATTERN,
            REFERENCE_PATTERN,
            REF_PATTERN,
            REMAINING_FIELD_PATTERN,
            SCOPED_IDENTIFIER,
            SLICE_PATTERN,
            STRUCT_PATTERN,
            TUPLE_PATTERN,
            TUPLE_STRUCT_PATTERN);
    public static final Set<String> TYPE_SET = Set.of(
            ABSTRACT_TYPE,
            ARRAY_TYPE,
            BOUNDED_TYPE,
            DYNAMIC_TYPE,
            FUNCTION_TYPE,
            GENERIC_TYPE,
            MACRO_INVOCATION,
            METAVARIABLE,
            NEVER_TYPE,
            POINTER_TYPE,
            PRIMITIVE_TYPE,
            REFERENCE_TYPE,
            REMOVED_TRAIT_BOUND,
            SCOPED_TYPE_IDENTIFIER,
            TUPLE_TYPE,
            TYPE_IDENTIFIER,
            UNIT_TYPE);
}
