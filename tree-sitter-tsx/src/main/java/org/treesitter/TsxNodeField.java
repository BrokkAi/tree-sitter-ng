package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code tsx} from tree-sitter {@code node-types.json}.
 */
public enum TsxNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null),
    ALIAS("alias"),
    ALTERNATIVE("alternative"),
    ARGUMENT("argument"),
    ARGUMENTS("arguments"),
    ATTRIBUTE("attribute"),
    BODY("body"),
    CLOSE_TAG("close_tag"),
    CONDITION("condition"),
    CONSEQUENCE("consequence"),
    CONSTRAINT("constraint"),
    CONSTRUCTOR("constructor"),
    DECLARATION("declaration"),
    DECORATOR("decorator"),
    FINALIZER("finalizer"),
    FLAGS("flags"),
    FUNCTION("function"),
    HANDLER("handler"),
    INCREMENT("increment"),
    INDEX("index"),
    INDEX_TYPE("index_type"),
    INITIALIZER("initializer"),
    KEY("key"),
    KIND("kind"),
    LABEL("label"),
    LEFT("left"),
    MODULE("module"),
    NAME("name"),
    OBJECT("object"),
    OPEN_TAG("open_tag"),
    OPERATOR("operator"),
    OPTIONAL_CHAIN("optional_chain"),
    PARAMETER("parameter"),
    PARAMETERS("parameters"),
    PATTERN("pattern"),
    PROPERTY("property"),
    RETURN_TYPE("return_type"),
    RIGHT("right"),
    SIGN("sign"),
    SOURCE("source"),
    TYPE("type"),
    TYPE_ARGUMENTS("type_arguments"),
    TYPE_PARAMETERS("type_parameters"),
    VALUE("value");

    private final @Nullable String name;

    TsxNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static TsxNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        TsxNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, TsxNodeField> LOOKUP = initLookup();

    private static Map<String, TsxNodeField> initLookup() {
        HashMap<String, TsxNodeField> m = new HashMap<>();
        for (TsxNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
