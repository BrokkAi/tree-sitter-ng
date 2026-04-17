package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code cpp} from tree-sitter {@code node-types.json}.
 */
public enum CppNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    ASSEMBLY_CODE("assembly_code"),
    BASE("base"),
    BODY("body"),
    CAPTURES("captures"),
    CLOBBERS("clobbers"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    CONSTRAINT("constraint"),
    DECLARATOR("declarator"),
    DEFAULT_TYPE("default_type"),
    DEFAULT_VALUE("default_value"),
    DELIMITER("delimiter"),
    DESIGNATOR("designator"),
    DIRECTIVE("directive"),
    END("end"),
    FIELD("field"),
    FILTER("filter"),
    FUNCTION("function"),
    GOTO_LABELS("goto_labels"),
    INDICES("indices"),
    INITIALIZER("initializer"),
    INPUT_OPERANDS("input_operands"),
    LABEL("label"),
    LEFT("left"),
    LENGTH("length"),
    MEMBER("member"),
    MESSAGE("message"),
    NAME("name"),
    OPERAND("operand"),
    OPERATOR("operator"),
    OUTPUT_OPERANDS("output_operands"),
    PARAMETERS("parameters"),
    PATH("path"),
    PATTERN("pattern"),
    PLACEMENT("placement"),
    PREFIX("prefix"),
    REGISTER("register"),
    REQUIREMENTS("requirements"),
    RIGHT("right"),
    SCOPE("scope"),
    SIZE("size"),
    START("start"),
    SYMBOL("symbol"),
    TEMPLATE_PARAMETERS("template_parameters"),
    TYPE("type"),
    UPDATE("update"),
    VALUE("value");

    private final @Nullable String name;

    CppNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static CppNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        CppNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, CppNodeField> LOOKUP = initLookup();

    private static Map<String, CppNodeField> initLookup() {
        HashMap<String, CppNodeField> m = new HashMap<>();
        for (CppNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
