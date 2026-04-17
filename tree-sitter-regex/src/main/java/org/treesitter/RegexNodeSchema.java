package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code regex} from tree-sitter {@code node-types.json}.
 */
public final class RegexNodeSchema {
    private RegexNodeSchema() {}

    public static Set<RegexNodeField> fields(@Nullable RegexNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<RegexNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<RegexNodeType> allowedTypes(@Nullable RegexNodeType owner, @Nullable RegexNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<RegexNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable RegexNodeType owner, @Nullable RegexNodeField field) {
        if (owner == null || field == null) return false;
        Map<RegexNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable RegexNodeType owner, @Nullable RegexNodeField field) {
        if (owner == null || field == null) return false;
        Map<RegexNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<RegexNodeType> allowedChildTypes(@Nullable RegexNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable RegexNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable RegexNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<RegexNodeType, Map<RegexNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<RegexNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<RegexNodeType, Map<RegexNodeField, FieldInfo>> initFields() {
        EnumMap<RegexNodeType, Map<RegexNodeField, FieldInfo>> out = new EnumMap<>(RegexNodeType.class);
        return out;
    }

    private static EnumMap<RegexNodeType, ChildInfo> initChildren() {
        EnumMap<RegexNodeType, ChildInfo> out = new EnumMap<>(RegexNodeType.class);
        out.put(RegexNodeType.ALTERNATION, new ChildInfo(false, true, Set.of(RegexNodeType.TERM)));
        out.put(RegexNodeType.ANONYMOUS_CAPTURING_GROUP, new ChildInfo(true, false, Set.of(RegexNodeType.PATTERN)));
        out.put(RegexNodeType.BACKREFERENCE_ESCAPE, new ChildInfo(true, false, Set.of(RegexNodeType.GROUP_NAME)));
        out.put(
                RegexNodeType.CHARACTER_CLASS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RegexNodeType.CHARACTER_CLASS_ESCAPE,
                                RegexNodeType.CLASS_CHARACTER,
                                RegexNodeType.CLASS_RANGE,
                                RegexNodeType.CONTROL_ESCAPE,
                                RegexNodeType.CONTROL_LETTER_ESCAPE,
                                RegexNodeType.IDENTITY_ESCAPE,
                                RegexNodeType.POSIX_CHARACTER_CLASS)));
        out.put(
                RegexNodeType.CHARACTER_CLASS_ESCAPE,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                RegexNodeType.UNICODE_CHARACTER_ESCAPE,
                                RegexNodeType.UNICODE_PROPERTY_VALUE_EXPRESSION)));
        out.put(
                RegexNodeType.CLASS_RANGE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RegexNodeType.CHARACTER_CLASS_ESCAPE,
                                RegexNodeType.CLASS_CHARACTER,
                                RegexNodeType.CONTROL_ESCAPE)));
        out.put(
                RegexNodeType.COUNT_QUANTIFIER,
                new ChildInfo(true, true, Set.of(RegexNodeType.DECIMAL_DIGITS, RegexNodeType.LAZY)));
        out.put(
                RegexNodeType.INLINE_FLAGS_GROUP,
                new ChildInfo(true, true, Set.of(RegexNodeType.FLAGS, RegexNodeType.PATTERN)));
        out.put(RegexNodeType.LOOKAROUND_ASSERTION, new ChildInfo(true, false, Set.of(RegexNodeType.PATTERN)));
        out.put(
                RegexNodeType.NAMED_CAPTURING_GROUP,
                new ChildInfo(true, true, Set.of(RegexNodeType.GROUP_NAME, RegexNodeType.PATTERN)));
        out.put(RegexNodeType.NAMED_GROUP_BACKREFERENCE, new ChildInfo(true, false, Set.of(RegexNodeType.GROUP_NAME)));
        out.put(RegexNodeType.NON_CAPTURING_GROUP, new ChildInfo(true, false, Set.of(RegexNodeType.PATTERN)));
        out.put(RegexNodeType.ONE_OR_MORE, new ChildInfo(false, false, Set.of(RegexNodeType.LAZY)));
        out.put(RegexNodeType.OPTIONAL, new ChildInfo(false, false, Set.of(RegexNodeType.LAZY)));
        out.put(
                RegexNodeType.PATTERN,
                new ChildInfo(true, false, Set.of(RegexNodeType.ALTERNATION, RegexNodeType.TERM)));
        out.put(
                RegexNodeType.POSIX_CHARACTER_CLASS,
                new ChildInfo(true, false, Set.of(RegexNodeType.POSIX_CLASS_NAME)));
        out.put(
                RegexNodeType.TERM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RegexNodeType.ANONYMOUS_CAPTURING_GROUP,
                                RegexNodeType.ANY_CHARACTER,
                                RegexNodeType.BACKREFERENCE_ESCAPE,
                                RegexNodeType.BOUNDARY_ASSERTION,
                                RegexNodeType.CHARACTER_CLASS,
                                RegexNodeType.CHARACTER_CLASS_ESCAPE,
                                RegexNodeType.CONTROL_ESCAPE,
                                RegexNodeType.CONTROL_LETTER_ESCAPE,
                                RegexNodeType.COUNT_QUANTIFIER,
                                RegexNodeType.DECIMAL_ESCAPE,
                                RegexNodeType.END_ASSERTION,
                                RegexNodeType.IDENTITY_ESCAPE,
                                RegexNodeType.INLINE_FLAGS_GROUP,
                                RegexNodeType.LOOKAROUND_ASSERTION,
                                RegexNodeType.NAMED_CAPTURING_GROUP,
                                RegexNodeType.NAMED_GROUP_BACKREFERENCE,
                                RegexNodeType.NON_BOUNDARY_ASSERTION,
                                RegexNodeType.NON_CAPTURING_GROUP,
                                RegexNodeType.ONE_OR_MORE,
                                RegexNodeType.OPTIONAL,
                                RegexNodeType.PATTERN_CHARACTER,
                                RegexNodeType.POSIX_CHARACTER_CLASS,
                                RegexNodeType.START_ASSERTION,
                                RegexNodeType.ZERO_OR_MORE)));
        out.put(
                RegexNodeType.UNICODE_PROPERTY_VALUE_EXPRESSION,
                new ChildInfo(
                        true, true, Set.of(RegexNodeType.UNICODE_PROPERTY_NAME, RegexNodeType.UNICODE_PROPERTY_VALUE)));
        out.put(RegexNodeType.ZERO_OR_MORE, new ChildInfo(false, false, Set.of(RegexNodeType.LAZY)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<RegexNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<RegexNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<RegexNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<RegexNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
