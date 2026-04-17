package org.treesitter;

import java.util.Set;

/**
 * Node type constants for {@code json} from tree-sitter {@code node-types.json}.
 */
public final class JsonNodeTypes {
    private JsonNodeTypes() {}

    public static final String ARRAY = "array";
    public static final String COMMENT = "comment";
    public static final String DOCUMENT = "document";
    public static final String ESCAPE_SEQUENCE = "escape_sequence";
    public static final String FALSE = "false";
    public static final String NULL = "null";
    public static final String NUMBER = "number";
    public static final String OBJECT = "object";
    public static final String PAIR = "pair";
    public static final String STRING = "string";
    public static final String STRING_CONTENT = "string_content";
    public static final String TRUE = "true";
    public static final String VALUE = "_value";

    public static final Set<String> VALUE_SET = Set.of(ARRAY, FALSE, NULL, NUMBER, OBJECT, STRING, TRUE);
}
