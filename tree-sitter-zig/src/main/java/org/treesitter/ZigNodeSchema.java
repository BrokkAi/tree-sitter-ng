package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code zig} from tree-sitter {@code node-types.json}.
 */
public final class ZigNodeSchema {
    private ZigNodeSchema() {}

    public static Set<ZigNodeField> fields(@Nullable ZigNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<ZigNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<ZigNodeType> allowedTypes(@Nullable ZigNodeType owner, @Nullable ZigNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<ZigNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable ZigNodeType owner, @Nullable ZigNodeField field) {
        if (owner == null || field == null) return false;
        Map<ZigNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable ZigNodeType owner, @Nullable ZigNodeField field) {
        if (owner == null || field == null) return false;
        Map<ZigNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<ZigNodeType> allowedChildTypes(@Nullable ZigNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable ZigNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable ZigNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<ZigNodeType, Map<ZigNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<ZigNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<ZigNodeType, Map<ZigNodeField, FieldInfo>> initFields() {
        EnumMap<ZigNodeType, Map<ZigNodeField, FieldInfo>> out = new EnumMap<>(ZigNodeType.class);
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.LEFT, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(ZigNodeField.RIGHT, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.LEFT, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(ZigNodeField.RIGHT, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(ZigNodeType.ARGUMENTS)));
            m.put(ZigNodeField.FUNCTION, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(
                    ZigNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ZigNodeType.COMPTIME_TYPE_EXPRESSION,
                                    ZigNodeType.IF_TYPE_EXPRESSION,
                                    ZigNodeType.PRIMARY_TYPE_EXPRESSION)));
            m.put(
                    ZigNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ZigNodeType.COMPTIME_TYPE_EXPRESSION,
                                    ZigNodeType.IF_TYPE_EXPRESSION,
                                    ZigNodeType.PRIMARY_TYPE_EXPRESSION)));
            out.put(ZigNodeType.CONTAINER_FIELD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(ZigNodeType.STATEMENT)));
            out.put(ZigNodeType.ELSE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.ERROR, new FieldInfo(false, false, Set.of(ZigNodeType.TYPE_EXPRESSION)));
            m.put(ZigNodeField.OK, new FieldInfo(true, false, Set.of(ZigNodeType.TYPE_EXPRESSION)));
            out.put(ZigNodeType.ERROR_UNION_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.MEMBER, new FieldInfo(true, false, Set.of(ZigNodeType.IDENTIFIER)));
            m.put(ZigNodeField.OBJECT, new FieldInfo(false, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.FIELD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(
                    ZigNodeField.BODY,
                    new FieldInfo(true, false, Set.of(ZigNodeType.BLOCK_EXPRESSION, ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.BODY, new FieldInfo(false, false, Set.of(ZigNodeType.BLOCK)));
            m.put(ZigNodeField.NAME, new FieldInfo(false, false, Set.of(ZigNodeType.IDENTIFIER)));
            m.put(
                    ZigNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ZigNodeType.COMPTIME_TYPE_EXPRESSION,
                                    ZigNodeType.IF_TYPE_EXPRESSION,
                                    ZigNodeType.TYPE_EXPRESSION)));
            out.put(ZigNodeType.FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.NAME, new FieldInfo(false, false, Set.of(ZigNodeType.IDENTIFIER)));
            m.put(
                    ZigNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ZigNodeType.COMPTIME_TYPE_EXPRESSION,
                                    ZigNodeType.IF_TYPE_EXPRESSION,
                                    ZigNodeType.TYPE_EXPRESSION)));
            out.put(ZigNodeType.FUNCTION_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.CONDITION, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.IF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(
                    ZigNodeField.BODY,
                    new FieldInfo(true, false, Set.of(ZigNodeType.BLOCK_EXPRESSION, ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.CONDITION, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.CONDITION, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.IF_TYPE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.INDEX, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.OBJECT, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.SENTINEL, new FieldInfo(false, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.INDEX_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.NAME, new FieldInfo(false, false, Set.of(ZigNodeType.IDENTIFIER)));
            m.put(
                    ZigNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ZigNodeType.COMPTIME_TYPE_EXPRESSION,
                                    ZigNodeType.IF_TYPE_EXPRESSION,
                                    ZigNodeType.TYPE_EXPRESSION)));
            out.put(ZigNodeType.PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.LEFT, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.RIGHT, new FieldInfo(false, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.RANGE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.SENTINEL, new FieldInfo(false, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.SLICE_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(ZigNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(
                    ZigNodeField.TYPE,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ZigNodeType.COMPTIME_TYPE_EXPRESSION,
                                    ZigNodeType.IF_TYPE_EXPRESSION,
                                    ZigNodeType.TYPE_EXPRESSION)));
            out.put(ZigNodeType.VARIABLE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(ZigNodeField.CONDITION, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.WHILE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ZigNodeField, FieldInfo> m = new EnumMap<>(ZigNodeField.class);
            m.put(
                    ZigNodeField.BODY,
                    new FieldInfo(true, false, Set.of(ZigNodeType.BLOCK_EXPRESSION, ZigNodeType.EXPRESSION)));
            m.put(ZigNodeField.CONDITION, new FieldInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
            out.put(ZigNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<ZigNodeType, ChildInfo> initChildren() {
        EnumMap<ZigNodeType, ChildInfo> out = new EnumMap<>(ZigNodeType.class);
        out.put(ZigNodeType.ADDRESS_SPACE, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.ANONYMOUS_STRUCT_INITIALIZER,
                new ChildInfo(true, false, Set.of(ZigNodeType.INITIALIZER_LIST)));
        out.put(ZigNodeType.ANYFRAME_TYPE, new ChildInfo(true, false, Set.of(ZigNodeType.TYPE_EXPRESSION)));
        out.put(ZigNodeType.ARGUMENTS, new ChildInfo(false, true, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.ARRAY_TYPE, new ChildInfo(true, true, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.ASM_CLOBBERS,
                new ChildInfo(false, true, Set.of(ZigNodeType.MULTILINE_STRING, ZigNodeType.STRING)));
        out.put(
                ZigNodeType.ASM_EXPRESSION,
                new ChildInfo(true, true, Set.of(ZigNodeType.ASM_OUTPUT, ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.ASM_INPUT,
                new ChildInfo(false, true, Set.of(ZigNodeType.ASM_CLOBBERS, ZigNodeType.ASM_INPUT_ITEM)));
        out.put(
                ZigNodeType.ASM_INPUT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.EXPRESSION,
                                ZigNodeType.IDENTIFIER,
                                ZigNodeType.MULTILINE_STRING,
                                ZigNodeType.STRING)));
        out.put(
                ZigNodeType.ASM_OUTPUT,
                new ChildInfo(false, true, Set.of(ZigNodeType.ASM_INPUT, ZigNodeType.ASM_OUTPUT_ITEM)));
        out.put(
                ZigNodeType.ASM_OUTPUT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.IDENTIFIER,
                                ZigNodeType.MULTILINE_STRING,
                                ZigNodeType.STRING,
                                ZigNodeType.TYPE_EXPRESSION)));
        out.put(ZigNodeType.ASYNC_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.AWAIT_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.BLOCK, new ChildInfo(false, true, Set.of(ZigNodeType.STATEMENT)));
        out.put(
                ZigNodeType.BLOCK_EXPRESSION,
                new ChildInfo(true, true, Set.of(ZigNodeType.BLOCK, ZigNodeType.BLOCK_LABEL)));
        out.put(ZigNodeType.BLOCK_LABEL, new ChildInfo(true, false, Set.of(ZigNodeType.IDENTIFIER)));
        out.put(
                ZigNodeType.BREAK_EXPRESSION,
                new ChildInfo(false, true, Set.of(ZigNodeType.BREAK_LABEL, ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.BREAK_LABEL, new ChildInfo(true, false, Set.of(ZigNodeType.IDENTIFIER)));
        out.put(
                ZigNodeType.BUILTIN_FUNCTION,
                new ChildInfo(true, true, Set.of(ZigNodeType.ARGUMENTS, ZigNodeType.BUILTIN_IDENTIFIER)));
        out.put(ZigNodeType.BYTE_ALIGNMENT, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.CALLING_CONVENTION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.CATCH_EXPRESSION,
                new ChildInfo(true, true, Set.of(ZigNodeType.EXPRESSION, ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.CHARACTER,
                new ChildInfo(true, false, Set.of(ZigNodeType.CHARACTER_CONTENT, ZigNodeType.ESCAPE_SEQUENCE)));
        out.put(ZigNodeType.COMPTIME_DECLARATION, new ChildInfo(true, false, Set.of(ZigNodeType.BLOCK)));
        out.put(ZigNodeType.COMPTIME_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.COMPTIME_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.BLOCK,
                                ZigNodeType.BLOCK_LABEL,
                                ZigNodeType.EXPRESSION_STATEMENT,
                                ZigNodeType.VARIABLE_DECLARATION)));
        out.put(ZigNodeType.COMPTIME_TYPE_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.TYPE_EXPRESSION)));
        out.put(
                ZigNodeType.CONTAINER_FIELD,
                new ChildInfo(false, true, Set.of(ZigNodeType.BYTE_ALIGNMENT, ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.CONTINUE_EXPRESSION,
                new ChildInfo(false, true, Set.of(ZigNodeType.BREAK_LABEL, ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.DEFER_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(ZigNodeType.BLOCK, ZigNodeType.BLOCK_LABEL, ZigNodeType.EXPRESSION_STATEMENT)));
        out.put(ZigNodeType.DEREFERENCE_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.ELSE_CLAUSE, new ChildInfo(false, false, Set.of(ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.ENUM_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.COMPTIME_DECLARATION,
                                ZigNodeType.CONTAINER_FIELD,
                                ZigNodeType.EXPRESSION,
                                ZigNodeType.FUNCTION_DECLARATION,
                                ZigNodeType.TEST_DECLARATION,
                                ZigNodeType.USING_NAMESPACE_DECLARATION,
                                ZigNodeType.VARIABLE_DECLARATION)));
        out.put(
                ZigNodeType.ERRDEFER_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.BLOCK,
                                ZigNodeType.BLOCK_LABEL,
                                ZigNodeType.EXPRESSION_STATEMENT,
                                ZigNodeType.PAYLOAD)));
        out.put(ZigNodeType.ERROR_SET_DECLARATION, new ChildInfo(false, true, Set.of(ZigNodeType.IDENTIFIER)));
        out.put(ZigNodeType.ERROR_TYPE, new ChildInfo(true, false, Set.of(ZigNodeType.IDENTIFIER)));
        out.put(ZigNodeType.EXPRESSION_STATEMENT, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.FIELD_INITIALIZER,
                new ChildInfo(true, true, Set.of(ZigNodeType.EXPRESSION, ZigNodeType.IDENTIFIER)));
        out.put(
                ZigNodeType.FOR_EXPRESSION,
                new ChildInfo(
                        true, true, Set.of(ZigNodeType.BLOCK_LABEL, ZigNodeType.EXPRESSION, ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.FOR_STATEMENT,
                new ChildInfo(
                        true, true, Set.of(ZigNodeType.ELSE_CLAUSE, ZigNodeType.EXPRESSION, ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.FUNCTION_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.ADDRESS_SPACE,
                                ZigNodeType.BYTE_ALIGNMENT,
                                ZigNodeType.CALLING_CONVENTION,
                                ZigNodeType.LINK_SECTION,
                                ZigNodeType.PARAMETERS,
                                ZigNodeType.STRING)));
        out.put(
                ZigNodeType.FUNCTION_SIGNATURE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.ADDRESS_SPACE,
                                ZigNodeType.BYTE_ALIGNMENT,
                                ZigNodeType.CALLING_CONVENTION,
                                ZigNodeType.LINK_SECTION,
                                ZigNodeType.PARAMETERS)));
        out.put(ZigNodeType.IDENTIFIER, new ChildInfo(false, false, Set.of(ZigNodeType.STRING)));
        out.put(
                ZigNodeType.IF_EXPRESSION,
                new ChildInfo(true, true, Set.of(ZigNodeType.EXPRESSION, ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.IF_STATEMENT,
                new ChildInfo(false, true, Set.of(ZigNodeType.ELSE_CLAUSE, ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.IF_TYPE_EXPRESSION,
                new ChildInfo(true, true, Set.of(ZigNodeType.PAYLOAD, ZigNodeType.TYPE_EXPRESSION)));
        out.put(
                ZigNodeType.INITIALIZER_LIST,
                new ChildInfo(false, true, Set.of(ZigNodeType.EXPRESSION, ZigNodeType.FIELD_INITIALIZER)));
        out.put(
                ZigNodeType.LABELED_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.BLOCK,
                                ZigNodeType.BLOCK_LABEL,
                                ZigNodeType.FOR_STATEMENT,
                                ZigNodeType.WHILE_STATEMENT)));
        out.put(
                ZigNodeType.LABELED_TYPE_EXPRESSION,
                new ChildInfo(true, true, Set.of(ZigNodeType.BLOCK, ZigNodeType.BLOCK_LABEL)));
        out.put(ZigNodeType.LINK_SECTION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.NOSUSPEND_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.NOSUSPEND_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(ZigNodeType.BLOCK, ZigNodeType.BLOCK_LABEL, ZigNodeType.EXPRESSION_STATEMENT)));
        out.put(
                ZigNodeType.NULLABLE_TYPE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                ZigNodeType.COMPTIME_TYPE_EXPRESSION,
                                ZigNodeType.IF_TYPE_EXPRESSION,
                                ZigNodeType.TYPE_EXPRESSION)));
        out.put(ZigNodeType.NULL_COERCION_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.OPAQUE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.COMPTIME_DECLARATION,
                                ZigNodeType.CONTAINER_FIELD,
                                ZigNodeType.FUNCTION_DECLARATION,
                                ZigNodeType.TEST_DECLARATION,
                                ZigNodeType.USING_NAMESPACE_DECLARATION,
                                ZigNodeType.VARIABLE_DECLARATION)));
        out.put(ZigNodeType.PARAMETERS, new ChildInfo(false, true, Set.of(ZigNodeType.PARAMETER)));
        out.put(ZigNodeType.PARENTHESIZED_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.PAYLOAD, new ChildInfo(true, true, Set.of(ZigNodeType.IDENTIFIER)));
        out.put(
                ZigNodeType.POINTER_TYPE,
                new ChildInfo(true, true, Set.of(ZigNodeType.ADDRESS_SPACE, ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.RESUME_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(ZigNodeType.RETURN_EXPRESSION, new ChildInfo(false, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.SLICE_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(ZigNodeType.ADDRESS_SPACE, ZigNodeType.BYTE_ALIGNMENT, ZigNodeType.TYPE_EXPRESSION)));
        out.put(
                ZigNodeType.SOURCE_FILE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ZigNodeType.COMPTIME_DECLARATION,
                                ZigNodeType.CONTAINER_FIELD,
                                ZigNodeType.FUNCTION_DECLARATION,
                                ZigNodeType.TEST_DECLARATION,
                                ZigNodeType.USING_NAMESPACE_DECLARATION,
                                ZigNodeType.VARIABLE_DECLARATION)));
        out.put(
                ZigNodeType.STRING,
                new ChildInfo(false, true, Set.of(ZigNodeType.ESCAPE_SEQUENCE, ZigNodeType.STRING_CONTENT)));
        out.put(
                ZigNodeType.STRUCT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.COMPTIME_DECLARATION,
                                ZigNodeType.CONTAINER_FIELD,
                                ZigNodeType.EXPRESSION,
                                ZigNodeType.FUNCTION_DECLARATION,
                                ZigNodeType.TEST_DECLARATION,
                                ZigNodeType.USING_NAMESPACE_DECLARATION,
                                ZigNodeType.VARIABLE_DECLARATION)));
        out.put(
                ZigNodeType.STRUCT_INITIALIZER,
                new ChildInfo(true, true, Set.of(ZigNodeType.INITIALIZER_LIST, ZigNodeType.PRIMARY_TYPE_EXPRESSION)));
        out.put(
                ZigNodeType.SUSPEND_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(ZigNodeType.BLOCK, ZigNodeType.BLOCK_LABEL, ZigNodeType.EXPRESSION_STATEMENT)));
        out.put(
                ZigNodeType.SWITCH_CASE,
                new ChildInfo(true, true, Set.of(ZigNodeType.EXPRESSION, ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.SWITCH_EXPRESSION,
                new ChildInfo(
                        true, true, Set.of(ZigNodeType.BLOCK_LABEL, ZigNodeType.EXPRESSION, ZigNodeType.SWITCH_CASE)));
        out.put(
                ZigNodeType.TEST_DECLARATION,
                new ChildInfo(true, true, Set.of(ZigNodeType.BLOCK, ZigNodeType.IDENTIFIER, ZigNodeType.STRING)));
        out.put(ZigNodeType.TRY_EXPRESSION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.UNION_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.COMPTIME_DECLARATION,
                                ZigNodeType.CONTAINER_FIELD,
                                ZigNodeType.EXPRESSION,
                                ZigNodeType.FUNCTION_DECLARATION,
                                ZigNodeType.TEST_DECLARATION,
                                ZigNodeType.USING_NAMESPACE_DECLARATION,
                                ZigNodeType.VARIABLE_DECLARATION)));
        out.put(ZigNodeType.USING_NAMESPACE_DECLARATION, new ChildInfo(true, false, Set.of(ZigNodeType.EXPRESSION)));
        out.put(
                ZigNodeType.VARIABLE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ZigNodeType.ADDRESS_SPACE,
                                ZigNodeType.BYTE_ALIGNMENT,
                                ZigNodeType.EXPRESSION,
                                ZigNodeType.IDENTIFIER,
                                ZigNodeType.LINK_SECTION,
                                ZigNodeType.STRING)));
        out.put(
                ZigNodeType.WHILE_EXPRESSION,
                new ChildInfo(
                        true, true, Set.of(ZigNodeType.BLOCK_LABEL, ZigNodeType.EXPRESSION, ZigNodeType.PAYLOAD)));
        out.put(
                ZigNodeType.WHILE_STATEMENT,
                new ChildInfo(
                        false, true, Set.of(ZigNodeType.ELSE_CLAUSE, ZigNodeType.EXPRESSION, ZigNodeType.PAYLOAD)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<ZigNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<ZigNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<ZigNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<ZigNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
