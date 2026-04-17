package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code html} from tree-sitter {@code node-types.json}.
 */
public final class HtmlNodeSchema {
    private HtmlNodeSchema() {}

    public static Set<HtmlNodeField> fields(@Nullable HtmlNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<HtmlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<HtmlNodeType> allowedTypes(@Nullable HtmlNodeType owner, @Nullable HtmlNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<HtmlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable HtmlNodeType owner, @Nullable HtmlNodeField field) {
        if (owner == null || field == null) return false;
        Map<HtmlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable HtmlNodeType owner, @Nullable HtmlNodeField field) {
        if (owner == null || field == null) return false;
        Map<HtmlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<HtmlNodeType> allowedChildTypes(@Nullable HtmlNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable HtmlNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable HtmlNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<HtmlNodeType, Map<HtmlNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<HtmlNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<HtmlNodeType, Map<HtmlNodeField, FieldInfo>> initFields() {
        EnumMap<HtmlNodeType, Map<HtmlNodeField, FieldInfo>> out = new EnumMap<>(HtmlNodeType.class);
        return out;
    }

    private static EnumMap<HtmlNodeType, ChildInfo> initChildren() {
        EnumMap<HtmlNodeType, ChildInfo> out = new EnumMap<>(HtmlNodeType.class);
        out.put(
                HtmlNodeType.ATTRIBUTE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                HtmlNodeType.ATTRIBUTE_NAME,
                                HtmlNodeType.ATTRIBUTE_VALUE,
                                HtmlNodeType.QUOTED_ATTRIBUTE_VALUE)));
        out.put(
                HtmlNodeType.DOCUMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                HtmlNodeType.DOCTYPE,
                                HtmlNodeType.ELEMENT,
                                HtmlNodeType.ENTITY,
                                HtmlNodeType.ERRONEOUS_END_TAG,
                                HtmlNodeType.SCRIPT_ELEMENT,
                                HtmlNodeType.STYLE_ELEMENT,
                                HtmlNodeType.TEXT)));
        out.put(
                HtmlNodeType.ELEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                HtmlNodeType.DOCTYPE,
                                HtmlNodeType.ELEMENT,
                                HtmlNodeType.END_TAG,
                                HtmlNodeType.ENTITY,
                                HtmlNodeType.ERRONEOUS_END_TAG,
                                HtmlNodeType.SCRIPT_ELEMENT,
                                HtmlNodeType.SELF_CLOSING_TAG,
                                HtmlNodeType.START_TAG,
                                HtmlNodeType.STYLE_ELEMENT,
                                HtmlNodeType.TEXT)));
        out.put(HtmlNodeType.END_TAG, new ChildInfo(true, false, Set.of(HtmlNodeType.TAG_NAME)));
        out.put(
                HtmlNodeType.ERRONEOUS_END_TAG,
                new ChildInfo(true, false, Set.of(HtmlNodeType.ERRONEOUS_END_TAG_NAME)));
        out.put(HtmlNodeType.QUOTED_ATTRIBUTE_VALUE, new ChildInfo(false, false, Set.of(HtmlNodeType.ATTRIBUTE_VALUE)));
        out.put(
                HtmlNodeType.SCRIPT_ELEMENT,
                new ChildInfo(true, true, Set.of(HtmlNodeType.END_TAG, HtmlNodeType.RAW_TEXT, HtmlNodeType.START_TAG)));
        out.put(
                HtmlNodeType.SELF_CLOSING_TAG,
                new ChildInfo(true, true, Set.of(HtmlNodeType.ATTRIBUTE, HtmlNodeType.TAG_NAME)));
        out.put(
                HtmlNodeType.START_TAG,
                new ChildInfo(true, true, Set.of(HtmlNodeType.ATTRIBUTE, HtmlNodeType.TAG_NAME)));
        out.put(
                HtmlNodeType.STYLE_ELEMENT,
                new ChildInfo(true, true, Set.of(HtmlNodeType.END_TAG, HtmlNodeType.RAW_TEXT, HtmlNodeType.START_TAG)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<HtmlNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<HtmlNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<HtmlNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<HtmlNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
