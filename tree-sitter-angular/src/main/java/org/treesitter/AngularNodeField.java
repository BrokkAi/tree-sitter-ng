package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code angular} from tree-sitter {@code node-types.json}.
 */
public enum AngularNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ALTERNATIVE_CONDITION("alternative_condition"),
    ARGUMENTS("arguments"),
    BODY("body"),
    CALL("call"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    DECLARATION("declaration"),
    DEFAULT_("default"),
    EMPTY("empty"),
    ERROR("error"),
    FLAGS("flags"),
    FUNCTION("function"),
    KEY("key"),
    LEFT("left"),
    LOADING("loading"),
    MINIMUM("minimum"),
    NAME("name"),
    NAMED("named"),
    OBJECT("object"),
    OPERATOR("operator"),
    PATTERN("pattern"),
    PIPES("pipes"),
    PLACEHOLDER("placeholder"),
    PROPERTY("property"),
    REFERENCE("reference"),
    RIGHT("right"),
    TRACK("track"),
    TRIGGER("trigger"),
    UNIT("unit"),
    VALUE("value");

    private final @Nullable String name;

    AngularNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static AngularNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        AngularNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, AngularNodeField> LOOKUP = initLookup();

    private static Map<String, AngularNodeField> initLookup() {
        HashMap<String, AngularNodeField> m = new HashMap<>();
        for (AngularNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
