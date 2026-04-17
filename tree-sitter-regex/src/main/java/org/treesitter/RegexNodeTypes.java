package org.treesitter;

/**
 * Node type constants for {@code regex} from tree-sitter {@code node-types.json}.
 */
public final class RegexNodeTypes {
    private RegexNodeTypes() {}

    public static final String ALTERNATION = "alternation";
    public static final String ANONYMOUS_CAPTURING_GROUP = "anonymous_capturing_group";
    public static final String ANY_CHARACTER = "any_character";
    public static final String BACKREFERENCE_ESCAPE = "backreference_escape";
    public static final String BOUNDARY_ASSERTION = "boundary_assertion";
    public static final String CHARACTER_CLASS = "character_class";
    public static final String CHARACTER_CLASS_ESCAPE = "character_class_escape";
    public static final String CLASS_CHARACTER = "class_character";
    public static final String CLASS_RANGE = "class_range";
    public static final String CONTROL_ESCAPE = "control_escape";
    public static final String CONTROL_LETTER_ESCAPE = "control_letter_escape";
    public static final String COUNT_QUANTIFIER = "count_quantifier";
    public static final String DECIMAL_DIGITS = "decimal_digits";
    public static final String DECIMAL_ESCAPE = "decimal_escape";
    public static final String END_ASSERTION = "end_assertion";
    public static final String FLAGS = "flags";
    public static final String GROUP_NAME = "group_name";
    public static final String IDENTITY_ESCAPE = "identity_escape";
    public static final String INLINE_FLAGS_GROUP = "inline_flags_group";
    public static final String LAZY = "lazy";
    public static final String LOOKAROUND_ASSERTION = "lookaround_assertion";
    public static final String NAMED_CAPTURING_GROUP = "named_capturing_group";
    public static final String NAMED_GROUP_BACKREFERENCE = "named_group_backreference";
    public static final String NON_BOUNDARY_ASSERTION = "non_boundary_assertion";
    public static final String NON_CAPTURING_GROUP = "non_capturing_group";
    public static final String ONE_OR_MORE = "one_or_more";
    public static final String OPTIONAL = "optional";
    public static final String PATTERN = "pattern";
    public static final String PATTERN_CHARACTER = "pattern_character";
    public static final String POSIX_CHARACTER_CLASS = "posix_character_class";
    public static final String POSIX_CLASS_NAME = "posix_class_name";
    public static final String START_ASSERTION = "start_assertion";
    public static final String TERM = "term";
    public static final String UNICODE_CHARACTER_ESCAPE = "unicode_character_escape";
    public static final String UNICODE_PROPERTY_NAME = "unicode_property_name";
    public static final String UNICODE_PROPERTY_VALUE = "unicode_property_value";
    public static final String UNICODE_PROPERTY_VALUE_EXPRESSION = "unicode_property_value_expression";
    public static final String ZERO_OR_MORE = "zero_or_more";
}
