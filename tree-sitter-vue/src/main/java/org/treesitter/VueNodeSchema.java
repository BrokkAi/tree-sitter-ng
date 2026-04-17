package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code vue} from tree-sitter {@code node-types.json}.
 */
public final class VueNodeSchema {
    private VueNodeSchema() {}

    public static Set<VueNodeField> fields(@Nullable VueNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<VueNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<VueNodeType> allowedTypes(@Nullable VueNodeType owner, @Nullable VueNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<VueNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable VueNodeType owner, @Nullable VueNodeField field) {
        if (owner == null || field == null) return false;
        Map<VueNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable VueNodeType owner, @Nullable VueNodeField field) {
        if (owner == null || field == null) return false;
        Map<VueNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<VueNodeType> allowedChildTypes(@Nullable VueNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable VueNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable VueNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<VueNodeType, Map<VueNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<VueNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<VueNodeType, Map<VueNodeField, FieldInfo>> initFields() {
        EnumMap<VueNodeType, Map<VueNodeField, FieldInfo>> out = new EnumMap<>(VueNodeType.class);
        return out;
    }

    private static EnumMap<VueNodeType, ChildInfo> initChildren() {
        EnumMap<VueNodeType, ChildInfo> out = new EnumMap<>(VueNodeType.class);
        out.put(
                VueNodeType.ATTRIBUTE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VueNodeType.ATTRIBUTE_NAME,
                                VueNodeType.ATTRIBUTE_VALUE,
                                VueNodeType.QUOTED_ATTRIBUTE_VALUE)));
        out.put(
                VueNodeType.DIRECTIVE_ATTRIBUTE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VueNodeType.ATTRIBUTE_VALUE,
                                VueNodeType.DIRECTIVE_MODIFIERS,
                                VueNodeType.DIRECTIVE_NAME,
                                VueNodeType.DIRECTIVE_VALUE,
                                VueNodeType.DYNAMIC_DIRECTIVE_VALUE,
                                VueNodeType.QUOTED_ATTRIBUTE_VALUE)));
        out.put(VueNodeType.DIRECTIVE_MODIFIERS, new ChildInfo(true, true, Set.of(VueNodeType.DIRECTIVE_MODIFIER)));
        out.put(
                VueNodeType.DOCUMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VueNodeType.DOCTYPE,
                                VueNodeType.ELEMENT,
                                VueNodeType.ENTITY,
                                VueNodeType.ERRONEOUS_END_TAG,
                                VueNodeType.INTERPOLATION,
                                VueNodeType.SCRIPT_ELEMENT,
                                VueNodeType.STYLE_ELEMENT,
                                VueNodeType.TEMPLATE_ELEMENT,
                                VueNodeType.TEXT)));
        out.put(
                VueNodeType.DYNAMIC_DIRECTIVE_VALUE,
                new ChildInfo(false, false, Set.of(VueNodeType.DYNAMIC_DIRECTIVE_INNER_VALUE)));
        out.put(
                VueNodeType.ELEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VueNodeType.DOCTYPE,
                                VueNodeType.ELEMENT,
                                VueNodeType.END_TAG,
                                VueNodeType.ENTITY,
                                VueNodeType.ERRONEOUS_END_TAG,
                                VueNodeType.INTERPOLATION,
                                VueNodeType.SCRIPT_ELEMENT,
                                VueNodeType.SELF_CLOSING_TAG,
                                VueNodeType.START_TAG,
                                VueNodeType.STYLE_ELEMENT,
                                VueNodeType.TEMPLATE_ELEMENT,
                                VueNodeType.TEXT)));
        out.put(VueNodeType.END_TAG, new ChildInfo(true, false, Set.of(VueNodeType.TAG_NAME)));
        out.put(VueNodeType.ERRONEOUS_END_TAG, new ChildInfo(true, false, Set.of(VueNodeType.ERRONEOUS_END_TAG_NAME)));
        out.put(VueNodeType.INTERPOLATION, new ChildInfo(false, false, Set.of(VueNodeType.RAW_TEXT)));
        out.put(VueNodeType.QUOTED_ATTRIBUTE_VALUE, new ChildInfo(false, false, Set.of(VueNodeType.ATTRIBUTE_VALUE)));
        out.put(
                VueNodeType.SCRIPT_ELEMENT,
                new ChildInfo(true, true, Set.of(VueNodeType.END_TAG, VueNodeType.RAW_TEXT, VueNodeType.START_TAG)));
        out.put(
                VueNodeType.SELF_CLOSING_TAG,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VueNodeType.ATTRIBUTE, VueNodeType.DIRECTIVE_ATTRIBUTE, VueNodeType.TAG_NAME)));
        out.put(
                VueNodeType.START_TAG,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VueNodeType.ATTRIBUTE, VueNodeType.DIRECTIVE_ATTRIBUTE, VueNodeType.TAG_NAME)));
        out.put(
                VueNodeType.STYLE_ELEMENT,
                new ChildInfo(true, true, Set.of(VueNodeType.END_TAG, VueNodeType.RAW_TEXT, VueNodeType.START_TAG)));
        out.put(
                VueNodeType.TEMPLATE_ELEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VueNodeType.DOCTYPE,
                                VueNodeType.ELEMENT,
                                VueNodeType.END_TAG,
                                VueNodeType.ENTITY,
                                VueNodeType.ERRONEOUS_END_TAG,
                                VueNodeType.INTERPOLATION,
                                VueNodeType.SCRIPT_ELEMENT,
                                VueNodeType.START_TAG,
                                VueNodeType.STYLE_ELEMENT,
                                VueNodeType.TEMPLATE_ELEMENT,
                                VueNodeType.TEXT)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<VueNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<VueNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<VueNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<VueNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
