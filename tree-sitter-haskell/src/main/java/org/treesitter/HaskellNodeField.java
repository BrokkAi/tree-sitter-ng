package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code haskell} from tree-sitter {@code node-types.json}.
 */
public enum HaskellNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ALTERNATIVES("alternatives"),
    ARGUMENT("argument"),
    ARROW("arrow"),
    ASSOCIATIVITY("associativity"),
    BIND("bind"),
    BINDS("binds"),
    BODY("body"),
    CALLING_CONVENTION("calling_convention"),
    CHILDREN("children"),
    CLASSES("classes"),
    CLASSIFIER("classifier"),
    CLOSED_FAMILY("closed_family"),
    CONSTRAINT("constraint"),
    CONSTRUCTOR("constructor"),
    CONSTRUCTORS("constructors"),
    CONTEXT("context"),
    DECL("decl"),
    DECLARATION("declaration"),
    DECLARATIONS("declarations"),
    DERIVING("deriving"),
    DETERMINED("determined"),
    ELEMENT("element"),
    ELSE_("else"),
    ENTITY("entity"),
    EQUATION("equation"),
    EXPORT("export"),
    EXPORTS("exports"),
    EXPRESSION("expression"),
    FIELD("field"),
    FIELDS("fields"),
    FORALL("forall"),
    FROM("from"),
    FUNCTION("function"),
    FUNDEP("fundep"),
    FUNDEPS("fundeps"),
    GUARD("guard"),
    GUARDS("guards"),
    ID("id"),
    IF_("if"),
    IMPLICIT("implicit"),
    IMPORTS("imports"),
    IMPORT_("import"),
    KEY("key"),
    KIND("kind"),
    LEFT_OPERAND("left_operand"),
    MATCH("match"),
    MATCHED("matched"),
    MINUS("minus"),
    MODULE("module"),
    MULTIPLICITY("multiplicity"),
    NAME("name"),
    NAMES("names"),
    NAMESPACE("namespace"),
    NUMBER("number"),
    OPERATOR("operator"),
    PACKAGE_("package"),
    PARAMETER("parameter"),
    PARENS("parens"),
    PATTERN("pattern"),
    PATTERNS("patterns"),
    PRECEDENCE("precedence"),
    QUALIFIER("qualifier"),
    QUALIFIERS("qualifiers"),
    QUANTIFIER("quantifier"),
    QUOTER("quoter"),
    RESULT("result"),
    RIGHT_OPERAND("right_operand"),
    ROLE("role"),
    SAFETY("safety"),
    SIGNATURE("signature"),
    STATEMENT("statement"),
    STEP("step"),
    STRATEGY("strategy"),
    SUBFIELD("subfield"),
    SYNONYM("synonym"),
    THEN("then"),
    TO("to"),
    TRANSFORMATION("transformation"),
    TYPE("type"),
    VARIABLE("variable"),
    VARIABLES("variables"),
    VIA("via");

    private final @Nullable String name;

    HaskellNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static HaskellNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        HaskellNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, HaskellNodeField> LOOKUP = initLookup();

    private static Map<String, HaskellNodeField> initLookup() {
        HashMap<String, HaskellNodeField> m = new HashMap<>();
        for (HaskellNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
