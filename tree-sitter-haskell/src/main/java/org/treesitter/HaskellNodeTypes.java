package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code haskell} from tree-sitter {@code node-types.json}.
 */
public final class HaskellNodeTypes {
    private HaskellNodeTypes() {}

    public static final String ABSTRACT_FAMILY = "abstract_family";
    public static final String ALL_NAMES = "all_names";
    public static final String ALTERNATIVE = "alternative";
    public static final String ALTERNATIVES = "alternatives";
    public static final String ANNOTATED = "annotated";
    public static final String APPLY = "apply";
    public static final String ARITHMETIC_SEQUENCE = "arithmetic_sequence";
    public static final String AS = "as";
    public static final String ASSOCIATED_TYPE = "associated_type";
    public static final String BIND = "bind";
    public static final String BINDING_LIST = "binding_list";
    public static final String BOOLEAN_ = "boolean";
    public static final String CALLING_CONVENTION = "calling_convention";
    public static final String CASE_ = "case";
    public static final String CHAR_ = "char";
    public static final String CHILDREN = "children";
    public static final String CLASS_ = "class";
    public static final String CLASS_DECL = "class_decl";
    public static final String CLASS_DECLARATIONS = "class_declarations";
    public static final String COMMENT = "comment";
    public static final String CONDITIONAL = "conditional";
    public static final String CONSTRAINT = "constraint";
    public static final String CONSTRAINTS = "constraints";
    public static final String CONSTRUCTOR = "constructor";
    public static final String CONSTRUCTOR_OPERATOR = "constructor_operator";
    public static final String CONSTRUCTOR_SYNONYM = "constructor_synonym";
    public static final String CONSTRUCTOR_SYNONYMS = "constructor_synonyms";
    public static final String CONTEXT = "context";
    public static final String CPP = "cpp";
    public static final String DATA_CONSTRUCTOR = "data_constructor";
    public static final String DATA_CONSTRUCTORS = "data_constructors";
    public static final String DATA_FAMILY = "data_family";
    public static final String DATA_INSTANCE = "data_instance";
    public static final String DATA_TYPE = "data_type";
    public static final String DECL = "decl";
    public static final String DECLARATION = "declaration";
    public static final String DECLARATIONS = "declarations";
    public static final String DEFAULT_SIGNATURE = "default_signature";
    public static final String DEFAULT_TYPES = "default_types";
    public static final String DERIVING = "deriving";
    public static final String DERIVING_INSTANCE = "deriving_instance";
    public static final String DERIVING_STRATEGY = "deriving_strategy";
    public static final String DO_ = "do";
    public static final String DO_MODULE = "do_module";
    public static final String EMPTY_LIST = "empty_list";
    public static final String ENTITY = "entity";
    public static final String EQUATION = "equation";
    public static final String EQUATIONS = "equations";
    public static final String EXP = "exp";
    public static final String EXPLICIT_TYPE = "explicit_type";
    public static final String EXPORT = "export";
    public static final String EXPORTS = "exports";
    public static final String EXPRESSION = "expression";
    public static final String FIELD = "field";
    public static final String FIELDS = "fields";
    public static final String FIELD_NAME = "field_name";
    public static final String FIELD_PATH = "field_path";
    public static final String FIELD_PATTERN = "field_pattern";
    public static final String FIELD_UPDATE = "field_update";
    public static final String FIXITY = "fixity";
    public static final String FLOAT_ = "float";
    public static final String FORALL = "forall";
    public static final String FORALL_REQUIRED = "forall_required";
    public static final String FOREIGN_EXPORT = "foreign_export";
    public static final String FOREIGN_IMPORT = "foreign_import";
    public static final String FUNCTION = "function";
    public static final String FUNCTION_HEAD_PARENS = "function_head_parens";
    public static final String FUNDEP = "fundep";
    public static final String FUNDEPS = "fundeps";
    public static final String GADT_CONSTRUCTOR = "gadt_constructor";
    public static final String GADT_CONSTRUCTORS = "gadt_constructors";
    public static final String GENERATOR = "generator";
    public static final String GROUP = "group";
    public static final String GUARD = "guard";
    public static final String GUARDS = "guards";
    public static final String HADDOCK = "haddock";
    public static final String HASKELL = "haskell";
    public static final String HEADER = "header";
    public static final String IMPLICIT_PARAMETER = "implicit_parameter";
    public static final String IMPLICIT_VARIABLE = "implicit_variable";
    public static final String IMPORTS = "imports";
    public static final String IMPORT_ = "import";
    public static final String IMPORT_LIST = "import_list";
    public static final String IMPORT_NAME = "import_name";
    public static final String IMPORT_PACKAGE = "import_package";
    public static final String INFERRED = "inferred";
    public static final String INFIX = "infix";
    public static final String INFIX_ID = "infix_id";
    public static final String INSTANCE = "instance";
    public static final String INSTANCE_DECL = "instance_decl";
    public static final String INSTANCE_DECLARATIONS = "instance_declarations";
    public static final String INTEGER = "integer";
    public static final String INVISIBLE = "invisible";
    public static final String IRREFUTABLE = "irrefutable";
    public static final String KIND_APPLICATION = "kind_application";
    public static final String KIND_SIGNATURE = "kind_signature";
    public static final String LABEL = "label";
    public static final String LAMBDA = "lambda";
    public static final String LAMBDA_CASE = "lambda_case";
    public static final String LAMBDA_CASES = "lambda_cases";
    public static final String LAZY_FIELD = "lazy_field";
    public static final String LEFT_SECTION = "left_section";
    public static final String LET = "let";
    public static final String LET_IN = "let_in";
    public static final String LINEAR_FUNCTION = "linear_function";
    public static final String LIST = "list";
    public static final String LIST_COMPREHENSION = "list_comprehension";
    public static final String LITERAL = "literal";
    public static final String LOCAL_BINDS = "local_binds";
    public static final String MATCH = "match";
    public static final String MODIFIER = "modifier";
    public static final String MODULE = "module";
    public static final String MODULE_EXPORT = "module_export";
    public static final String MODULE_ID = "module_id";
    public static final String MULTI_WAY_IF = "multi_way_if";
    public static final String NAME = "name";
    public static final String NAMESPACE = "namespace";
    public static final String NEGATION = "negation";
    public static final String NEWTYPE = "newtype";
    public static final String NEWTYPE_CONSTRUCTOR = "newtype_constructor";
    public static final String OPERATOR = "operator";
    public static final String PARENS = "parens";
    public static final String PATTERN = "pattern";
    public static final String PATTERNS = "patterns";
    public static final String PATTERN_GUARD = "pattern_guard";
    public static final String PATTERN_SYNONYM = "pattern_synonym";
    public static final String PRAGMA = "pragma";
    public static final String PREFIX = "prefix";
    public static final String PREFIX_ID = "prefix_id";
    public static final String PREFIX_LIST = "prefix_list";
    public static final String PREFIX_TUPLE = "prefix_tuple";
    public static final String PREFIX_UNBOXED_SUM = "prefix_unboxed_sum";
    public static final String PREFIX_UNBOXED_TUPLE = "prefix_unboxed_tuple";
    public static final String PROJECTION = "projection";
    public static final String PROJECTION_SELECTOR = "projection_selector";
    public static final String PROMOTED = "promoted";
    public static final String QUALIFIED = "qualified";
    public static final String QUALIFIER = "qualifier";
    public static final String QUALIFIERS = "qualifiers";
    public static final String QUANTIFIED_TYPE = "quantified_type";
    public static final String QUANTIFIED_VARIABLES = "quantified_variables";
    public static final String QUASIQUOTE = "quasiquote";
    public static final String QUASIQUOTE_BODY = "quasiquote_body";
    public static final String QUOTE = "quote";
    public static final String QUOTED_DECLS = "quoted_decls";
    public static final String QUOTED_EXPRESSION = "quoted_expression";
    public static final String QUOTED_PATTERN = "quoted_pattern";
    public static final String QUOTED_TYPE = "quoted_type";
    public static final String QUOTER = "quoter";
    public static final String REC = "rec";
    public static final String RECORD_ = "record";
    public static final String RIGHT_SECTION = "right_section";
    public static final String ROLE_ANNOTATION = "role_annotation";
    public static final String SAFETY = "safety";
    public static final String SIGNATURE = "signature";
    public static final String SPECIAL = "special";
    public static final String SPLICE = "splice";
    public static final String STAR = "star";
    public static final String STATEMENT = "statement";
    public static final String STRICT = "strict";
    public static final String STRICT_FIELD = "strict_field";
    public static final String STRING = "string";
    public static final String TH_QUOTED_NAME = "th_quoted_name";
    public static final String TOP_SPLICE = "top_splice";
    public static final String TRANSFORM = "transform";
    public static final String TUPLE = "tuple";
    public static final String TYPE = "type";
    public static final String TYPED_QUOTE = "typed_quote";
    public static final String TYPE_APPLICATION = "type_application";
    public static final String TYPE_BINDER = "type_binder";
    public static final String TYPE_FAMILY = "type_family";
    public static final String TYPE_FAMILY_INJECTIVITY = "type_family_injectivity";
    public static final String TYPE_FAMILY_RESULT = "type_family_result";
    public static final String TYPE_INSTANCE = "type_instance";
    public static final String TYPE_PARAM = "type_param";
    public static final String TYPE_PARAMS = "type_params";
    public static final String TYPE_PATTERNS = "type_patterns";
    public static final String TYPE_ROLE = "type_role";
    public static final String TYPE_SYNOMYM = "type_synomym";
    public static final String UNBOXED_SUM = "unboxed_sum";
    public static final String UNBOXED_TUPLE = "unboxed_tuple";
    public static final String UNBOXED_UNIT = "unboxed_unit";
    public static final String UNIT = "unit";
    public static final String VARIABLE = "variable";
    public static final String VIA = "via";
    public static final String VIEW_PATTERN = "view_pattern";
    public static final String WILDCARD = "wildcard";

    public static final Set<String> CLASS_DECL_SET =
            Set.of(DATA_FAMILY, DECL, DEFAULT_SIGNATURE, FIXITY, TYPE_FAMILY, TYPE_INSTANCE);
    public static final Set<String> CONSTRAINTS_SET =
            Set.of(CONSTRAINT, CONTEXT, FORALL, IMPLICIT_PARAMETER, SIGNATURE);
    public static final Set<String> CONSTRAINT_SET = Set.of(
            APPLY,
            INFIX,
            LITERAL,
            NAME,
            PARENS,
            PREFIX_ID,
            PREFIX_TUPLE,
            PREFIX_UNBOXED_SUM,
            PREFIX_UNBOXED_TUPLE,
            PROMOTED,
            QUALIFIED,
            QUASIQUOTE,
            SPLICE,
            TUPLE,
            UNBOXED_UNIT,
            UNIT,
            VARIABLE,
            WILDCARD);
    public static final Set<String> DECLARATION_SET = Set.of(
            CLASS_,
            DATA_FAMILY,
            DATA_INSTANCE,
            DATA_TYPE,
            DECL,
            DEFAULT_TYPES,
            DERIVING_INSTANCE,
            FIXITY,
            FOREIGN_EXPORT,
            FOREIGN_IMPORT,
            INSTANCE,
            KIND_SIGNATURE,
            NEWTYPE,
            PATTERN_SYNONYM,
            ROLE_ANNOTATION,
            TOP_SPLICE,
            TYPE_FAMILY,
            TYPE_INSTANCE,
            TYPE_SYNOMYM);
    public static final Set<String> DECL_SET = Set.of(BIND, FUNCTION, SIGNATURE);
    public static final Set<String> EXPRESSION_SET = Set.of(
            APPLY,
            ARITHMETIC_SEQUENCE,
            CASE_,
            CONDITIONAL,
            CONSTRUCTOR,
            DO_,
            IMPLICIT_VARIABLE,
            INFIX,
            LABEL,
            LAMBDA,
            LAMBDA_CASE,
            LAMBDA_CASES,
            LEFT_SECTION,
            LET_IN,
            LIST,
            LIST_COMPREHENSION,
            LITERAL,
            MULTI_WAY_IF,
            NEGATION,
            PARENS,
            PREFIX_ID,
            PREFIX_TUPLE,
            PREFIX_UNBOXED_SUM,
            PREFIX_UNBOXED_TUPLE,
            PROJECTION,
            PROJECTION_SELECTOR,
            QUALIFIED,
            QUASIQUOTE,
            QUOTE,
            RECORD_,
            RIGHT_SECTION,
            SPLICE,
            TH_QUOTED_NAME,
            TUPLE,
            TYPED_QUOTE,
            UNBOXED_SUM,
            UNBOXED_TUPLE,
            UNBOXED_UNIT,
            UNIT,
            VARIABLE);
    public static final Set<String> GUARD_SET = Set.of(BOOLEAN_, LET, PATTERN_GUARD);
    public static final Set<String> INSTANCE_DECL_SET = Set.of(DATA_INSTANCE, DECL, TYPE_INSTANCE);
    public static final Set<String> PATTERN_SET = Set.of(
            APPLY,
            AS,
            CONSTRUCTOR,
            INFIX,
            IRREFUTABLE,
            LIST,
            LITERAL,
            NEGATION,
            PARENS,
            PREFIX_ID,
            PREFIX_TUPLE,
            PREFIX_UNBOXED_SUM,
            PREFIX_UNBOXED_TUPLE,
            QUALIFIED,
            QUASIQUOTE,
            RECORD_,
            SPLICE,
            STRICT,
            TUPLE,
            UNBOXED_SUM,
            UNBOXED_TUPLE,
            UNBOXED_UNIT,
            UNIT,
            VARIABLE,
            WILDCARD);
    public static final Set<String> QUALIFIER_SET = Set.of(BOOLEAN_, GENERATOR, GROUP, LET, TRANSFORM);
    public static final Set<String> QUANTIFIED_TYPE_SET =
            Set.of(CONTEXT, FORALL, FORALL_REQUIRED, FUNCTION, IMPLICIT_PARAMETER, LINEAR_FUNCTION, TYPE);
    public static final Set<String> STATEMENT_SET = Set.of(BIND, EXP, LET, REC);
    public static final Set<String> TYPE_PARAM_SET = Set.of(INVISIBLE, PARENS, VARIABLE, WILDCARD);
    public static final Set<String> TYPE_SET = Set.of(
            APPLY,
            INFIX,
            LIST,
            LITERAL,
            NAME,
            PARENS,
            PREFIX_ID,
            PREFIX_LIST,
            PREFIX_TUPLE,
            PREFIX_UNBOXED_SUM,
            PREFIX_UNBOXED_TUPLE,
            PROMOTED,
            QUALIFIED,
            QUASIQUOTE,
            SPLICE,
            STAR,
            TUPLE,
            UNBOXED_SUM,
            UNBOXED_TUPLE,
            UNBOXED_UNIT,
            UNIT,
            VARIABLE,
            WILDCARD);
}
