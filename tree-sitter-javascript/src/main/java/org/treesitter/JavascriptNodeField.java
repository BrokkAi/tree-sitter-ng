package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code javascript} from tree-sitter {@code node-types.json}.
 */
public enum JavascriptNodeField {
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
    CONSTRUCTOR("constructor"),
    DECLARATION("declaration"),
    DECORATOR("decorator"),
    FINALIZER("finalizer"),
    FLAGS("flags"),
    FUNCTION("function"),
    HANDLER("handler"),
    INCREMENT("increment"),
    INDEX("index"),
    INITIALIZER("initializer"),
    KEY("key"),
    KIND("kind"),
    LABEL("label"),
    LEFT("left"),
    MEMBER("member"),
    NAME("name"),
    OBJECT("object"),
    OPEN_TAG("open_tag"),
    OPERATOR("operator"),
    OPTIONAL_CHAIN("optional_chain"),
    PARAMETER("parameter"),
    PARAMETERS("parameters"),
    PATTERN("pattern"),
    PROPERTY("property"),
    RIGHT("right"),
    SOURCE("source"),
    VALUE("value");

    private final @Nullable String name;

    JavascriptNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static JavascriptNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        JavascriptNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, JavascriptNodeField> LOOKUP = initLookup();

    private static Map<String, JavascriptNodeField> initLookup() {
        HashMap<String, JavascriptNodeField> m = new HashMap<>();
        for (JavascriptNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
