package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node types for {@code regex} from tree-sitter {@code node-types.json}.
 */
public enum RegexNodeType {
    /** Represents a null TSNode reference or a TSNode with a null type. */
    __NULL__(null),
    ALTERNATION("alternation"),
    ANONYMOUS_CAPTURING_GROUP("anonymous_capturing_group"),
    ANY_CHARACTER("any_character"),
    BACKREFERENCE_ESCAPE("backreference_escape"),
    BOUNDARY_ASSERTION("boundary_assertion"),
    CHARACTER_CLASS("character_class"),
    CHARACTER_CLASS_ESCAPE("character_class_escape"),
    CLASS_CHARACTER("class_character"),
    CLASS_RANGE("class_range"),
    CONTROL_ESCAPE("control_escape"),
    CONTROL_LETTER_ESCAPE("control_letter_escape"),
    COUNT_QUANTIFIER("count_quantifier"),
    DECIMAL_DIGITS("decimal_digits"),
    DECIMAL_ESCAPE("decimal_escape"),
    END_ASSERTION("end_assertion"),
    FLAGS("flags"),
    GROUP_NAME("group_name"),
    IDENTITY_ESCAPE("identity_escape"),
    INLINE_FLAGS_GROUP("inline_flags_group"),
    LAZY("lazy"),
    LOOKAROUND_ASSERTION("lookaround_assertion"),
    NAMED_CAPTURING_GROUP("named_capturing_group"),
    NAMED_GROUP_BACKREFERENCE("named_group_backreference"),
    NON_BOUNDARY_ASSERTION("non_boundary_assertion"),
    NON_CAPTURING_GROUP("non_capturing_group"),
    ONE_OR_MORE("one_or_more"),
    OPTIONAL("optional"),
    PATTERN("pattern"),
    PATTERN_CHARACTER("pattern_character"),
    POSIX_CHARACTER_CLASS("posix_character_class"),
    POSIX_CLASS_NAME("posix_class_name"),
    START_ASSERTION("start_assertion"),
    TERM("term"),
    UNICODE_CHARACTER_ESCAPE("unicode_character_escape"),
    UNICODE_PROPERTY_NAME("unicode_property_name"),
    UNICODE_PROPERTY_VALUE("unicode_property_value"),
    UNICODE_PROPERTY_VALUE_EXPRESSION("unicode_property_value_expression"),
    ZERO_OR_MORE("zero_or_more");

    private final @Nullable String type;

    RegexNodeType(@Nullable String type) {
        this.type = type;
    }

    public @Nullable String getType() {
        return type;
    }

    public static RegexNodeType from(@Nullable TSNode node) {
        if (node == null) return __NULL__;
        return fromType(node.getType());
    }

    public static RegexNodeType fromType(@Nullable String type) {
        if (type == null) return __NULL__;
        RegexNodeType t = LOOKUP.get(type);
        return t == null ? __NULL__ : t;
    }

    private static final Map<String, RegexNodeType> LOOKUP = initLookup();

    private static Map<String, RegexNodeType> initLookup() {
        HashMap<String, RegexNodeType> m = new HashMap<>();
        for (RegexNodeType t : values()) {
            if (t.type != null) m.put(t.type, t);
        }
        return Collections.unmodifiableMap(m);
    }
}
