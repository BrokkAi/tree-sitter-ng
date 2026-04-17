package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code scala} from tree-sitter {@code node-types.json}.
 */
public enum ScalaNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ARGUMENTS("arguments"),
    BASE("base"),
    BODY("body"),
    BOUND("bound"),
    CLASS_PARAMETERS("class_parameters"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    DEFAULT_VALUE("default_value"),
    DERIVE("derive"),
    ENUMERATORS("enumerators"),
    EXTEND("extend"),
    EXTRA("extra"),
    FIELD("field"),
    FUNCTION("function"),
    INTERPOLATOR("interpolator"),
    LAMBDA_START("lambda_start"),
    LEFT("left"),
    NAME("name"),
    OPERATOR("operator"),
    PARAMETERS("parameters"),
    PARAMETER_TYPES("parameter_types"),
    PATH("path"),
    PATTERN("pattern"),
    RETURN_TYPE("return_type"),
    RIGHT("right"),
    SELECTOR("selector"),
    TYPE("type"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_PARAMETERS("type_parameters"),
    VALUE("value");

    private final @Nullable String name;

    ScalaNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static ScalaNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        ScalaNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, ScalaNodeField> LOOKUP = initLookup();

    private static Map<String, ScalaNodeField> initLookup() {
        HashMap<String, ScalaNodeField> m = new HashMap<>();
        for (ScalaNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
