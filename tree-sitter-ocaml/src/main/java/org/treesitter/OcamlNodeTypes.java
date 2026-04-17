package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code ocaml} from tree-sitter {@code node-types.json}.
 */
public final class OcamlNodeTypes {
    private OcamlNodeTypes() {}

    public static final String ABSTRACT_TYPE = "abstract_type";
    public static final String ADD_OPERATOR = "add_operator";
    public static final String ALIASED_TYPE = "aliased_type";
    public static final String ALIAS_PATTERN = "alias_pattern";
    public static final String AND_OPERATOR = "and_operator";
    public static final String APPLICATION_EXPRESSION = "application_expression";
    public static final String ARRAY_BINDING_PATTERN = "array_binding_pattern";
    public static final String ARRAY_EXPRESSION = "array_expression";
    public static final String ARRAY_GET_EXPRESSION = "array_get_expression";
    public static final String ARRAY_PATTERN = "array_pattern";
    public static final String ASSERT_EXPRESSION = "assert_expression";
    public static final String ASSIGN_OPERATOR = "assign_operator";
    public static final String ATTRIBUTE = "attribute";
    public static final String ATTRIBUTE_ID = "attribute_id";
    public static final String ATTRIBUTE_PAYLOAD = "attribute_payload";
    public static final String BIGARRAY_GET_EXPRESSION = "bigarray_get_expression";
    public static final String BINDING_PATTERN = "_binding_pattern";
    public static final String BOOLEAN_ = "boolean";
    public static final String CHARACTER = "character";
    public static final String CHARACTER_CONTENT = "character_content";
    public static final String CLASS_APPLICATION = "class_application";
    public static final String CLASS_BINDING = "class_binding";
    public static final String CLASS_BODY_TYPE = "class_body_type";
    public static final String CLASS_DEFINITION = "class_definition";
    public static final String CLASS_EXPRESSION = "_class_expression";
    public static final String CLASS_FIELD = "_class_field";
    public static final String CLASS_FIELD_SPECIFICATION = "_class_field_specification";
    public static final String CLASS_FUNCTION = "class_function";
    public static final String CLASS_FUNCTION_TYPE = "class_function_type";
    public static final String CLASS_INITIALIZER = "class_initializer";
    public static final String CLASS_NAME = "class_name";
    public static final String CLASS_PATH = "class_path";
    public static final String CLASS_TYPE = "_class_type";
    public static final String CLASS_TYPE_BINDING = "class_type_binding";
    public static final String CLASS_TYPE_DEFINITION = "class_type_definition";
    public static final String CLASS_TYPE_NAME = "class_type_name";
    public static final String CLASS_TYPE_PATH = "class_type_path";
    public static final String COERCION_EXPRESSION = "coercion_expression";
    public static final String COMMENT = "comment";
    public static final String COMPILATION_UNIT = "compilation_unit";
    public static final String CONCAT_OPERATOR = "concat_operator";
    public static final String CONSTANT = "_constant";
    public static final String CONSTRAIN_MODULE = "constrain_module";
    public static final String CONSTRAIN_MODULE_TYPE = "constrain_module_type";
    public static final String CONSTRAIN_TYPE = "constrain_type";
    public static final String CONSTRUCTED_TYPE = "constructed_type";
    public static final String CONSTRUCTOR_DECLARATION = "constructor_declaration";
    public static final String CONSTRUCTOR_NAME = "constructor_name";
    public static final String CONSTRUCTOR_PATH = "constructor_path";
    public static final String CONSTRUCTOR_PATTERN = "constructor_pattern";
    public static final String CONS_EXPRESSION = "cons_expression";
    public static final String CONS_PATTERN = "cons_pattern";
    public static final String CONVERSION_SPECIFICATION = "conversion_specification";
    public static final String DIRECTIVE = "directive";
    public static final String DO_CLAUSE = "do_clause";
    public static final String EFFECT_PATTERN = "_effect_pattern";
    public static final String EFFECT_PATTERN_2 = "effect_pattern";
    public static final String ELSE_CLAUSE = "else_clause";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String EXCEPTION_DEFINITION = "exception_definition";
    public static final String EXCEPTION_PATTERN = "exception_pattern";
    public static final String EXPRESSION = "_expression";
    public static final String EXPRESSION_ITEM = "expression_item";
    public static final String EXTENDED_MODULE_PATH = "extended_module_path";
    public static final String EXTENSION = "extension";
    public static final String EXTERNAL = "external";
    public static final String FIELD_DECLARATION = "field_declaration";
    public static final String FIELD_EXPRESSION = "field_expression";
    public static final String FIELD_GET_EXPRESSION = "field_get_expression";
    public static final String FIELD_NAME = "field_name";
    public static final String FIELD_PATH = "field_path";
    public static final String FIELD_PATTERN = "field_pattern";
    public static final String FLOATING_ATTRIBUTE = "floating_attribute";
    public static final String FOR_EXPRESSION = "for_expression";
    public static final String FUNCTION_EXPRESSION = "function_expression";
    public static final String FUNCTION_TYPE = "function_type";
    public static final String FUNCTOR = "functor";
    public static final String FUNCTOR_TYPE = "functor_type";
    public static final String FUN_EXPRESSION = "fun_expression";
    public static final String GUARD = "guard";
    public static final String HASH_EXPRESSION = "hash_expression";
    public static final String HASH_OPERATOR = "hash_operator";
    public static final String HASH_TYPE = "hash_type";
    public static final String IF_EXPRESSION = "if_expression";
    public static final String INCLUDE_MODULE = "include_module";
    public static final String INCLUDE_MODULE_TYPE = "include_module_type";
    public static final String INDEXING_OPERATOR = "indexing_operator";
    public static final String INDEXING_OPERATOR_PATH = "indexing_operator_path";
    public static final String INFIX_EXPRESSION = "infix_expression";
    public static final String INFIX_OPERATOR = "_infix_operator";
    public static final String INHERITANCE_DEFINITION = "inheritance_definition";
    public static final String INHERITANCE_SPECIFICATION = "inheritance_specification";
    public static final String INSTANCE_VARIABLE_DEFINITION = "instance_variable_definition";
    public static final String INSTANCE_VARIABLE_EXPRESSION = "instance_variable_expression";
    public static final String INSTANCE_VARIABLE_NAME = "instance_variable_name";
    public static final String INSTANCE_VARIABLE_SPECIFICATION = "instance_variable_specification";
    public static final String INSTANTIATED_CLASS = "instantiated_class";
    public static final String INSTANTIATED_CLASS_TYPE = "instantiated_class_type";
    public static final String ITEM_ATTRIBUTE = "item_attribute";
    public static final String ITEM_EXTENSION = "item_extension";
    public static final String LABELED_ARGUMENT = "labeled_argument";
    public static final String LABEL_NAME = "label_name";
    public static final String LAZY_EXPRESSION = "lazy_expression";
    public static final String LAZY_PATTERN = "lazy_pattern";
    public static final String LET_AND_OPERATOR = "let_and_operator";
    public static final String LET_BINDING = "let_binding";
    public static final String LET_CLASS_EXPRESSION = "let_class_expression";
    public static final String LET_EXCEPTION_EXPRESSION = "let_exception_expression";
    public static final String LET_EXPRESSION = "let_expression";
    public static final String LET_MODULE_EXPRESSION = "let_module_expression";
    public static final String LET_OPEN_CLASS_EXPRESSION = "let_open_class_expression";
    public static final String LET_OPEN_CLASS_TYPE = "let_open_class_type";
    public static final String LET_OPEN_EXPRESSION = "let_open_expression";
    public static final String LET_OPERATOR = "let_operator";
    public static final String LINE_NUMBER_DIRECTIVE = "line_number_directive";
    public static final String LIST_BINDING_PATTERN = "list_binding_pattern";
    public static final String LIST_EXPRESSION = "list_expression";
    public static final String LIST_PATTERN = "list_pattern";
    public static final String LOCAL_OPEN_EXPRESSION = "local_open_expression";
    public static final String LOCAL_OPEN_PATTERN = "local_open_pattern";
    public static final String LOCAL_OPEN_TYPE = "local_open_type";
    public static final String MATCH_CASE = "match_case";
    public static final String MATCH_EXPRESSION = "match_expression";
    public static final String MATCH_OPERATOR = "match_operator";
    public static final String METHOD_DEFINITION = "method_definition";
    public static final String METHOD_INVOCATION = "method_invocation";
    public static final String METHOD_NAME = "method_name";
    public static final String METHOD_SPECIFICATION = "method_specification";
    public static final String METHOD_TYPE = "method_type";
    public static final String MODULE_APPLICATION = "module_application";
    public static final String MODULE_BINDING = "module_binding";
    public static final String MODULE_DEFINITION = "module_definition";
    public static final String MODULE_EXPRESSION = "_module_expression";
    public static final String MODULE_NAME = "module_name";
    public static final String MODULE_PARAMETER = "module_parameter";
    public static final String MODULE_PATH = "module_path";
    public static final String MODULE_TYPE = "_module_type";
    public static final String MODULE_TYPE_CONSTRAINT = "module_type_constraint";
    public static final String MODULE_TYPE_DEFINITION = "module_type_definition";
    public static final String MODULE_TYPE_NAME = "module_type_name";
    public static final String MODULE_TYPE_OF = "module_type_of";
    public static final String MODULE_TYPE_PATH = "module_type_path";
    public static final String MULT_OPERATOR = "mult_operator";
    public static final String NEW_EXPRESSION = "new_expression";
    public static final String NUMBER = "number";
    public static final String OBJECT_COPY_EXPRESSION = "object_copy_expression";
    public static final String OBJECT_EXPRESSION = "object_expression";
    public static final String OBJECT_TYPE = "object_type";
    public static final String OCAMLYACC_VALUE = "ocamlyacc_value";
    public static final String OPEN_MODULE = "open_module";
    public static final String OR_OPERATOR = "or_operator";
    public static final String OR_PATTERN = "or_pattern";
    public static final String PACKAGE_EXPRESSION = "package_expression";
    public static final String PACKAGE_PATTERN = "package_pattern";
    public static final String PACKAGE_TYPE = "package_type";
    public static final String PACKED_MODULE = "packed_module";
    public static final String PARAMETER = "_parameter";
    public static final String PARAMETER_2 = "parameter";
    public static final String PARENTHESIZED_CLASS_EXPRESSION = "parenthesized_class_expression";
    public static final String PARENTHESIZED_EXPRESSION = "parenthesized_expression";
    public static final String PARENTHESIZED_MODULE_EXPRESSION = "parenthesized_module_expression";
    public static final String PARENTHESIZED_MODULE_TYPE = "parenthesized_module_type";
    public static final String PARENTHESIZED_OPERATOR = "parenthesized_operator";
    public static final String PARENTHESIZED_PATTERN = "parenthesized_pattern";
    public static final String PARENTHESIZED_TYPE = "parenthesized_type";
    public static final String PATTERN = "_pattern";
    public static final String POLYMORPHIC_TYPE = "_polymorphic_type";
    public static final String POLYMORPHIC_TYPE_2 = "polymorphic_type";
    public static final String POLYMORPHIC_VARIANT_PATTERN = "polymorphic_variant_pattern";
    public static final String POLYMORPHIC_VARIANT_TYPE = "polymorphic_variant_type";
    public static final String POW_OPERATOR = "pow_operator";
    public static final String PREFIX_EXPRESSION = "prefix_expression";
    public static final String PREFIX_OPERATOR = "prefix_operator";
    public static final String PRETTY_PRINTING_INDICATION = "pretty_printing_indication";
    public static final String PRODUCT_EXPRESSION = "product_expression";
    public static final String QUOTED_EXTENSION = "quoted_extension";
    public static final String QUOTED_ITEM_EXTENSION = "quoted_item_extension";
    public static final String QUOTED_STRING = "quoted_string";
    public static final String QUOTED_STRING_CONTENT = "quoted_string_content";
    public static final String RANGE_PATTERN = "range_pattern";
    public static final String RECORD_BINDING_PATTERN = "record_binding_pattern";
    public static final String RECORD_DECLARATION = "record_declaration";
    public static final String RECORD_EXPRESSION = "record_expression";
    public static final String RECORD_PATTERN = "record_pattern";
    public static final String REFUTATION_CASE = "refutation_case";
    public static final String REL_OPERATOR = "rel_operator";
    public static final String SEQUENCE_EXPRESSION = "_sequence_expression";
    public static final String SEQUENCE_EXPRESSION_2 = "sequence_expression";
    public static final String SET_EXPRESSION = "set_expression";
    public static final String SHEBANG = "shebang";
    public static final String SIGNATURE = "signature";
    public static final String SIGNATURE_ITEM = "_signature_item";
    public static final String SIGNED_CONSTANT = "_signed_constant";
    public static final String SIGNED_NUMBER = "signed_number";
    public static final String SIGN_EXPRESSION = "sign_expression";
    public static final String SIGN_OPERATOR = "sign_operator";
    public static final String SIMPLE_CLASS_EXPRESSION = "_simple_class_expression";
    public static final String SIMPLE_CLASS_TYPE = "_simple_class_type";
    public static final String SIMPLE_EXPRESSION = "_simple_expression";
    public static final String SIMPLE_MODULE_EXPRESSION = "_simple_module_expression";
    public static final String SIMPLE_PATTERN = "_simple_pattern";
    public static final String SIMPLE_TYPE = "_simple_type";
    public static final String STRING = "string";
    public static final String STRING_CONTENT = "string_content";
    public static final String STRING_GET_EXPRESSION = "string_get_expression";
    public static final String STRUCTURE = "structure";
    public static final String STRUCTURE_ITEM = "_structure_item";
    public static final String TAG = "tag";
    public static final String TAG_PATTERN = "tag_pattern";
    public static final String TAG_SPECIFICATION = "tag_specification";
    public static final String THEN_CLAUSE = "then_clause";
    public static final String TOPLEVEL_DIRECTIVE = "toplevel_directive";
    public static final String TRY_EXPRESSION = "try_expression";
    public static final String TUPLE_PATTERN = "tuple_pattern";
    public static final String TUPLE_TYPE = "_tuple_type";
    public static final String TUPLE_TYPE_2 = "tuple_type";
    public static final String TYPE = "_type";
    public static final String TYPED_CLASS_EXPRESSION = "typed_class_expression";
    public static final String TYPED_EXPRESSION = "typed_expression";
    public static final String TYPED_LABEL = "typed_label";
    public static final String TYPED_MODULE_EXPRESSION = "typed_module_expression";
    public static final String TYPED_PATTERN = "typed_pattern";
    public static final String TYPE_BINDING = "type_binding";
    public static final String TYPE_CONSTRAINT = "type_constraint";
    public static final String TYPE_CONSTRUCTOR = "type_constructor";
    public static final String TYPE_CONSTRUCTOR_PATH = "type_constructor_path";
    public static final String TYPE_DEFINITION = "type_definition";
    public static final String TYPE_PARAMETER_CONSTRAINT = "type_parameter_constraint";
    public static final String TYPE_VARIABLE = "type_variable";
    public static final String UNIT = "unit";
    public static final String VALUE_DEFINITION = "value_definition";
    public static final String VALUE_NAME = "value_name";
    public static final String VALUE_PATH = "value_path";
    public static final String VALUE_PATTERN = "value_pattern";
    public static final String VALUE_SPECIFICATION = "value_specification";
    public static final String VARIANT_DECLARATION = "variant_declaration";
    public static final String WHILE_EXPRESSION = "while_expression";

    public static final Set<String> BINDING_PATTERN_SET = Set.of(
            ALIAS_PATTERN,
            ARRAY_PATTERN,
            CONSTRUCTOR_PATH,
            CONSTRUCTOR_PATTERN,
            CONS_PATTERN,
            EXTENSION,
            LAZY_PATTERN,
            LIST_PATTERN,
            LOCAL_OPEN_PATTERN,
            OR_PATTERN,
            PACKAGE_PATTERN,
            PARENTHESIZED_OPERATOR,
            PARENTHESIZED_PATTERN,
            POLYMORPHIC_VARIANT_PATTERN,
            QUOTED_EXTENSION,
            RANGE_PATTERN,
            RECORD_PATTERN,
            SIGNED_CONSTANT,
            TAG,
            TAG_PATTERN,
            TUPLE_PATTERN,
            TYPED_PATTERN,
            VALUE_NAME);
    public static final Set<String> CLASS_EXPRESSION_SET = Set.of(
            CLASS_APPLICATION,
            CLASS_FUNCTION,
            LET_CLASS_EXPRESSION,
            LET_OPEN_CLASS_EXPRESSION,
            SIMPLE_CLASS_EXPRESSION);
    public static final Set<String> CLASS_FIELD_SET = Set.of(
            CLASS_INITIALIZER,
            INHERITANCE_DEFINITION,
            INSTANCE_VARIABLE_DEFINITION,
            ITEM_EXTENSION,
            METHOD_DEFINITION,
            QUOTED_ITEM_EXTENSION,
            TYPE_PARAMETER_CONSTRAINT);
    public static final Set<String> CLASS_FIELD_SPECIFICATION_SET = Set.of(
            INHERITANCE_SPECIFICATION,
            INSTANCE_VARIABLE_SPECIFICATION,
            ITEM_EXTENSION,
            METHOD_SPECIFICATION,
            QUOTED_ITEM_EXTENSION,
            TYPE_PARAMETER_CONSTRAINT);
    public static final Set<String> CLASS_TYPE_SET = Set.of(CLASS_FUNCTION_TYPE, SIMPLE_CLASS_TYPE);
    public static final Set<String> CONSTANT_SET = Set.of(BOOLEAN_, CHARACTER, NUMBER, QUOTED_STRING, STRING, UNIT);
    public static final Set<String> EFFECT_PATTERN_SET =
            Set.of(CONSTRUCTOR_PATTERN, LAZY_PATTERN, SIMPLE_PATTERN, TAG_PATTERN);
    public static final Set<String> EXPRESSION_SET = Set.of(
            APPLICATION_EXPRESSION,
            ASSERT_EXPRESSION,
            CONS_EXPRESSION,
            FOR_EXPRESSION,
            FUNCTION_EXPRESSION,
            FUN_EXPRESSION,
            IF_EXPRESSION,
            INFIX_EXPRESSION,
            LAZY_EXPRESSION,
            LET_EXCEPTION_EXPRESSION,
            LET_EXPRESSION,
            LET_MODULE_EXPRESSION,
            LET_OPEN_EXPRESSION,
            MATCH_EXPRESSION,
            PRODUCT_EXPRESSION,
            SET_EXPRESSION,
            SIGN_EXPRESSION,
            SIMPLE_EXPRESSION,
            TRY_EXPRESSION,
            WHILE_EXPRESSION);
    public static final Set<String> INFIX_OPERATOR_SET = Set.of(
            ADD_OPERATOR,
            AND_OPERATOR,
            ASSIGN_OPERATOR,
            CONCAT_OPERATOR,
            MULT_OPERATOR,
            OR_OPERATOR,
            POW_OPERATOR,
            REL_OPERATOR);
    public static final Set<String> MODULE_EXPRESSION_SET =
            Set.of(FUNCTOR, MODULE_APPLICATION, MODULE_PATH, SIMPLE_MODULE_EXPRESSION, STRUCTURE);
    public static final Set<String> MODULE_TYPE_SET = Set.of(
            EXTENSION,
            FUNCTOR_TYPE,
            MODULE_TYPE_CONSTRAINT,
            MODULE_TYPE_OF,
            MODULE_TYPE_PATH,
            PARENTHESIZED_MODULE_TYPE,
            QUOTED_EXTENSION,
            SIGNATURE);
    public static final Set<String> PARAMETER_SET = Set.of(ABSTRACT_TYPE, PARAMETER_2);
    public static final Set<String> PATTERN_SET = Set.of(
            ALIAS_PATTERN,
            CONS_PATTERN,
            EFFECT_PATTERN,
            EFFECT_PATTERN_2,
            EXCEPTION_PATTERN,
            OR_PATTERN,
            RANGE_PATTERN,
            TUPLE_PATTERN);
    public static final Set<String> POLYMORPHIC_TYPE_SET = Set.of(POLYMORPHIC_TYPE_2, TYPE);
    public static final Set<String> SEQUENCE_EXPRESSION_SET = Set.of(EXPRESSION, SEQUENCE_EXPRESSION_2);
    public static final Set<String> SIGNATURE_ITEM_SET = Set.of(
            CLASS_DEFINITION,
            CLASS_TYPE_DEFINITION,
            EXCEPTION_DEFINITION,
            EXTERNAL,
            FLOATING_ATTRIBUTE,
            INCLUDE_MODULE_TYPE,
            ITEM_EXTENSION,
            MODULE_DEFINITION,
            MODULE_TYPE_DEFINITION,
            OPEN_MODULE,
            QUOTED_ITEM_EXTENSION,
            TYPE_DEFINITION,
            VALUE_SPECIFICATION);
    public static final Set<String> SIGNED_CONSTANT_SET = Set.of(CONSTANT, SIGNED_NUMBER);
    public static final Set<String> SIMPLE_CLASS_EXPRESSION_SET = Set.of(
            CLASS_PATH,
            EXTENSION,
            INSTANTIATED_CLASS,
            OBJECT_EXPRESSION,
            PARENTHESIZED_CLASS_EXPRESSION,
            QUOTED_EXTENSION,
            TYPED_CLASS_EXPRESSION);
    public static final Set<String> SIMPLE_CLASS_TYPE_SET = Set.of(
            CLASS_BODY_TYPE,
            CLASS_TYPE_PATH,
            EXTENSION,
            INSTANTIATED_CLASS_TYPE,
            LET_OPEN_CLASS_TYPE,
            QUOTED_EXTENSION);
    public static final Set<String> SIMPLE_EXPRESSION_SET = Set.of(
            ARRAY_EXPRESSION,
            ARRAY_GET_EXPRESSION,
            BIGARRAY_GET_EXPRESSION,
            COERCION_EXPRESSION,
            CONSTANT,
            CONSTRUCTOR_PATH,
            EXTENSION,
            FIELD_GET_EXPRESSION,
            HASH_EXPRESSION,
            LIST_EXPRESSION,
            LOCAL_OPEN_EXPRESSION,
            METHOD_INVOCATION,
            NEW_EXPRESSION,
            OBJECT_COPY_EXPRESSION,
            OBJECT_EXPRESSION,
            OCAMLYACC_VALUE,
            PACKAGE_EXPRESSION,
            PARENTHESIZED_EXPRESSION,
            PREFIX_EXPRESSION,
            QUOTED_EXTENSION,
            RECORD_EXPRESSION,
            STRING_GET_EXPRESSION,
            TAG,
            TYPED_EXPRESSION,
            VALUE_PATH);
    public static final Set<String> SIMPLE_MODULE_EXPRESSION_SET = Set.of(
            EXTENSION, PACKED_MODULE, PARENTHESIZED_MODULE_EXPRESSION, QUOTED_EXTENSION, TYPED_MODULE_EXPRESSION);
    public static final Set<String> SIMPLE_PATTERN_SET = Set.of(
            ARRAY_PATTERN,
            CONSTRUCTOR_PATH,
            EXTENSION,
            LIST_PATTERN,
            LOCAL_OPEN_PATTERN,
            PACKAGE_PATTERN,
            PARENTHESIZED_OPERATOR,
            PARENTHESIZED_PATTERN,
            POLYMORPHIC_VARIANT_PATTERN,
            QUOTED_EXTENSION,
            RECORD_PATTERN,
            SIGNED_CONSTANT,
            TAG,
            TYPED_PATTERN,
            VALUE_PATTERN);
    public static final Set<String> SIMPLE_TYPE_SET = Set.of(
            CONSTRUCTED_TYPE,
            EXTENSION,
            HASH_TYPE,
            LOCAL_OPEN_TYPE,
            OBJECT_TYPE,
            PACKAGE_TYPE,
            PARENTHESIZED_TYPE,
            POLYMORPHIC_VARIANT_TYPE,
            QUOTED_EXTENSION,
            TYPE_CONSTRUCTOR_PATH,
            TYPE_VARIABLE);
    public static final Set<String> STRUCTURE_ITEM_SET = Set.of(
            CLASS_DEFINITION,
            CLASS_TYPE_DEFINITION,
            EXCEPTION_DEFINITION,
            EXTERNAL,
            FLOATING_ATTRIBUTE,
            INCLUDE_MODULE,
            ITEM_EXTENSION,
            MODULE_DEFINITION,
            MODULE_TYPE_DEFINITION,
            OPEN_MODULE,
            QUOTED_ITEM_EXTENSION,
            TYPE_DEFINITION,
            VALUE_DEFINITION);
    public static final Set<String> TUPLE_TYPE_SET = Set.of(SIMPLE_TYPE, TUPLE_TYPE_2);
    public static final Set<String> TYPE_SET = Set.of(ALIASED_TYPE, FUNCTION_TYPE, TUPLE_TYPE);
}
