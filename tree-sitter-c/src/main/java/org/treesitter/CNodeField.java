package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code c} from tree-sitter {@code node-types.json}.
 */
public enum CNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    ASSEMBLY_CODE("assembly_code"),
    BODY("body"),
    CLOBBERS("clobbers"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    CONSTRAINT("constraint"),
    DECLARATOR("declarator"),
    DESIGNATOR("designator"),
    DIRECTIVE("directive"),
    END("end"),
    FIELD("field"),
    FILTER("filter"),
    FUNCTION("function"),
    GOTO_LABELS("goto_labels"),
    INDEX("index"),
    INITIALIZER("initializer"),
    INPUT_OPERANDS("input_operands"),
    LABEL("label"),
    LEFT("left"),
    MEMBER("member"),
    NAME("name"),
    OPERAND("operand"),
    OPERATOR("operator"),
    OUTPUT_OPERANDS("output_operands"),
    PARAMETERS("parameters"),
    PATH("path"),
    PREFIX("prefix"),
    REGISTER("register"),
    RIGHT("right"),
    SIZE("size"),
    START("start"),
    SYMBOL("symbol"),
    TYPE("type"),
    UNDERLYING_TYPE("underlying_type"),
    UPDATE("update"),
    VALUE("value");

    private final @Nullable String name;

    CNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static CNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        CNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, CNodeField> LOOKUP = initLookup();

    private static Map<String, CNodeField> initLookup() {
        HashMap<String, CNodeField> m = new HashMap<>();
        for (CNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
