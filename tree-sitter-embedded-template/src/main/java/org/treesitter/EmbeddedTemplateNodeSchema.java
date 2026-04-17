package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code embedded-template} from tree-sitter {@code node-types.json}.
 */
public final class EmbeddedTemplateNodeSchema {
    private EmbeddedTemplateNodeSchema() {}

    public static Set<EmbeddedTemplateNodeField> fields(@Nullable EmbeddedTemplateNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<EmbeddedTemplateNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<EmbeddedTemplateNodeType> allowedTypes(
            @Nullable EmbeddedTemplateNodeType owner, @Nullable EmbeddedTemplateNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<EmbeddedTemplateNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(
            @Nullable EmbeddedTemplateNodeType owner, @Nullable EmbeddedTemplateNodeField field) {
        if (owner == null || field == null) return false;
        Map<EmbeddedTemplateNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(
            @Nullable EmbeddedTemplateNodeType owner, @Nullable EmbeddedTemplateNodeField field) {
        if (owner == null || field == null) return false;
        Map<EmbeddedTemplateNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<EmbeddedTemplateNodeType> allowedChildTypes(@Nullable EmbeddedTemplateNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable EmbeddedTemplateNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable EmbeddedTemplateNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<EmbeddedTemplateNodeType, Map<EmbeddedTemplateNodeField, FieldInfo>> FIELDS =
            initFields();
    private static final EnumMap<EmbeddedTemplateNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<EmbeddedTemplateNodeType, Map<EmbeddedTemplateNodeField, FieldInfo>> initFields() {
        EnumMap<EmbeddedTemplateNodeType, Map<EmbeddedTemplateNodeField, FieldInfo>> out =
                new EnumMap<>(EmbeddedTemplateNodeType.class);
        return out;
    }

    private static EnumMap<EmbeddedTemplateNodeType, ChildInfo> initChildren() {
        EnumMap<EmbeddedTemplateNodeType, ChildInfo> out = new EnumMap<>(EmbeddedTemplateNodeType.class);
        out.put(
                EmbeddedTemplateNodeType.COMMENT_DIRECTIVE,
                new ChildInfo(false, false, Set.of(EmbeddedTemplateNodeType.COMMENT)));
        out.put(EmbeddedTemplateNodeType.DIRECTIVE, new ChildInfo(false, false, Set.of(EmbeddedTemplateNodeType.CODE)));
        out.put(
                EmbeddedTemplateNodeType.GRAPHQL_DIRECTIVE,
                new ChildInfo(false, false, Set.of(EmbeddedTemplateNodeType.CODE)));
        out.put(
                EmbeddedTemplateNodeType.OUTPUT_DIRECTIVE,
                new ChildInfo(false, false, Set.of(EmbeddedTemplateNodeType.CODE)));
        out.put(
                EmbeddedTemplateNodeType.TEMPLATE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                EmbeddedTemplateNodeType.COMMENT_DIRECTIVE,
                                EmbeddedTemplateNodeType.CONTENT,
                                EmbeddedTemplateNodeType.DIRECTIVE,
                                EmbeddedTemplateNodeType.GRAPHQL_DIRECTIVE,
                                EmbeddedTemplateNodeType.OUTPUT_DIRECTIVE)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<EmbeddedTemplateNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<EmbeddedTemplateNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<EmbeddedTemplateNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<EmbeddedTemplateNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
