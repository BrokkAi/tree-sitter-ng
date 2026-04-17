package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code agda} from tree-sitter {@code node-types.json}.
 */
public enum AgdaNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ABSTRACT_("abstract"),
    ATOM("atom"),
    ATTRIBUTE("attribute"),
    ATTRIBUTES("attributes"),
    BID("bid"),
    CATCHALL_PRAGMA("catchall_pragma"),
    COMMENT("comment"),
    DATA("data"),
    DATA_NAME("data_name"),
    DATA_SIGNATURE("data_signature"),
    DO_("do"),
    DO_WHERE("do_where"),
    EXPR("expr"),
    FIELDS("fields"),
    FIELD_ASSIGNMENT("field_assignment"),
    FIELD_NAME("field_name"),
    FORALL("forall"),
    FUNCTION("function"),
    FUNCTION_NAME("function_name"),
    GENERALIZE("generalize"),
    HOLE_NAME("hole_name"),
    HOLE_NAMES("hole_names"),
    ID("id"),
    IMPORT_("import"),
    IMPORT_DIRECTIVE("import_directive"),
    INFIX("infix"),
    INSTANCE("instance"),
    INTEGER("integer"),
    LAMBDA("lambda"),
    LAMBDA_CLAUSE("lambda_clause"),
    LAMBDA_CLAUSE_ABSURD("lambda_clause_absurd"),
    LET("let"),
    LHS("lhs"),
    LITERAL("literal"),
    MACRO("macro"),
    MODULE("module"),
    MODULE_APPLICATION("module_application"),
    MODULE_ASSIGNMENT("module_assignment"),
    MODULE_MACRO("module_macro"),
    MODULE_NAME("module_name"),
    MUTUAL("mutual"),
    OPEN("open"),
    PATTERN("pattern"),
    POSTULATE("postulate"),
    PRAGMA("pragma"),
    PRIMITIVE("primitive"),
    PRIVATE_("private"),
    PROPN("PropN"),
    QID("qid"),
    RECORD_("record"),
    RECORD_ASSIGNMENTS("record_assignments"),
    RECORD_CONSTRUCTOR("record_constructor"),
    RECORD_CONSTRUCTOR_INSTANCE("record_constructor_instance"),
    RECORD_DECLARATIONS_BLOCK("record_declarations_block"),
    RECORD_ETA("record_eta"),
    RECORD_INDUCTION("record_induction"),
    RECORD_NAME("record_name"),
    RECORD_SIGNATURE("record_signature"),
    RENAMING("renaming"),
    REWRITE_EQUATIONS("rewrite_equations"),
    RHS("rhs"),
    SETN("SetN"),
    SIGNATURE("signature"),
    SOURCE_FILE("source_file"),
    STMT("stmt"),
    SYNTAX("syntax"),
    TYPED_BINDING("typed_binding"),
    TYPE_SIGNATURE("type_signature"),
    UNQUOTE_DECL("unquote_decl"),
    UNTYPED_BINDING("untyped_binding"),
    WHERE("where"),
    WITH_EXPRESSIONS("with_expressions");

    private final @Nullable String type;

    AgdaNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static AgdaNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static AgdaNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        AgdaNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, AgdaNodeType> LOOKUP = initLookup();

    private static Map<String, AgdaNodeType> initLookup() {
        HashMap<String, AgdaNodeType> m = new HashMap<>();
        for (AgdaNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
