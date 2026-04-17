package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code json} from tree-sitter {@code node-types.json}.
 */
public final class JsonNodeSchema {
    private JsonNodeSchema() {}

    public static Set<JsonNodeField> fields(@Nullable JsonNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<JsonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<JsonNodeType> allowedTypes(@Nullable JsonNodeType owner, @Nullable JsonNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<JsonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable JsonNodeType owner, @Nullable JsonNodeField field) {
        if (owner == null || field == null) return false;
        Map<JsonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable JsonNodeType owner, @Nullable JsonNodeField field) {
        if (owner == null || field == null) return false;
        Map<JsonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<JsonNodeType> allowedChildTypes(@Nullable JsonNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable JsonNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable JsonNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<JsonNodeType, Map<JsonNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<JsonNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<JsonNodeType, Map<JsonNodeField, FieldInfo>> initFields() {
        EnumMap<JsonNodeType, Map<JsonNodeField, FieldInfo>> out = new EnumMap<>(JsonNodeType.class);
        {
            EnumMap<JsonNodeField, FieldInfo> m = new EnumMap<>(JsonNodeField.class);
            m.put(JsonNodeField.KEY, new FieldInfo(true, false, Set.of(JsonNodeType.STRING)));
            m.put(JsonNodeField.VALUE, new FieldInfo(true, false, Set.of(JsonNodeType.VALUE)));
            out.put(JsonNodeType.PAIR, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<JsonNodeType, ChildInfo> initChildren() {
        EnumMap<JsonNodeType, ChildInfo> out = new EnumMap<>(JsonNodeType.class);
        out.put(JsonNodeType.ARRAY, new ChildInfo(false, true, Set.of(JsonNodeType.VALUE)));
        out.put(JsonNodeType.DOCUMENT, new ChildInfo(false, true, Set.of(JsonNodeType.VALUE)));
        out.put(JsonNodeType.OBJECT, new ChildInfo(false, true, Set.of(JsonNodeType.PAIR)));
        out.put(
                JsonNodeType.STRING,
                new ChildInfo(false, true, Set.of(JsonNodeType.ESCAPE_SEQUENCE, JsonNodeType.STRING_CONTENT)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<JsonNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<JsonNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<JsonNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<JsonNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
