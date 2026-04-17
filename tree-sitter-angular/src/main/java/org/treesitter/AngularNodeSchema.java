package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code angular} from tree-sitter {@code node-types.json}.
 */
public final class AngularNodeSchema {
    private AngularNodeSchema() {}

    public static Set<AngularNodeField> fields(@Nullable AngularNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<AngularNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<AngularNodeType> allowedTypes(@Nullable AngularNodeType owner, @Nullable AngularNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<AngularNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable AngularNodeType owner, @Nullable AngularNodeField field) {
        if (owner == null || field == null) return false;
        Map<AngularNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable AngularNodeType owner, @Nullable AngularNodeField field) {
        if (owner == null || field == null) return false;
        Map<AngularNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<AngularNodeType> allowedChildTypes(@Nullable AngularNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable AngularNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable AngularNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<AngularNodeType, Map<AngularNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<AngularNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<AngularNodeType, Map<AngularNodeField, FieldInfo>> initFields() {
        EnumMap<AngularNodeType, Map<AngularNodeField, FieldInfo>> out = new EnumMap<>(AngularNodeType.class);
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.TRIGGER,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    AngularNodeType.ASSIGNMENT_EXPRESSION,
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            out.put(AngularNodeType.ANIMATION_BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.NAME,
                    new FieldInfo(true, false, Set.of(AngularNodeType.IDENTIFIER, AngularNodeType.MEMBER_EXPRESSION)));
            m.put(
                    AngularNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            out.put(AngularNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.LEFT, new FieldInfo(true, false, Set.of(AngularNodeType.EXPRESSION)));
            m.put(AngularNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    AngularNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(AngularNodeType.BINARY_EXPRESSION, AngularNodeType.EXPRESSION)));
            out.put(AngularNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.OBJECT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.ARRAY,
                                    AngularNodeType.BRACKET_EXPRESSION,
                                    AngularNodeType.CALL_EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.IDENTIFIER,
                                    AngularNodeType.MEMBER_EXPRESSION,
                                    AngularNodeType.NUMBER,
                                    AngularNodeType.OBJECT,
                                    AngularNodeType.STRING,
                                    AngularNodeType.TEMPLATE_STRING)));
            m.put(
                    AngularNodeField.PROPERTY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            out.put(AngularNodeType.BRACKET_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(AngularNodeType.ARGUMENTS)));
            m.put(AngularNodeField.FUNCTION, new FieldInfo(true, false, Set.of(AngularNodeType.IDENTIFIER)));
            out.put(AngularNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            m.put(
                    AngularNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            out.put(AngularNodeType.CASE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.ARRAY,
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.BRACKET_EXPRESSION,
                                    AngularNodeType.CALL_EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.IDENTIFIER,
                                    AngularNodeType.MEMBER_EXPRESSION,
                                    AngularNodeType.NUMBER,
                                    AngularNodeType.OBJECT,
                                    AngularNodeType.STRING,
                                    AngularNodeType.TEMPLATE_STRING,
                                    AngularNodeType.UNARY_EXPRESSION)));
            m.put(
                    AngularNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.ARRAY,
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.BRACKET_EXPRESSION,
                                    AngularNodeType.CALL_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.IDENTIFIER,
                                    AngularNodeType.MEMBER_EXPRESSION,
                                    AngularNodeType.NUMBER,
                                    AngularNodeType.OBJECT,
                                    AngularNodeType.STRING,
                                    AngularNodeType.TEMPLATE_STRING,
                                    AngularNodeType.UNARY_EXPRESSION)));
            out.put(AngularNodeType.CONDITIONAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            out.put(AngularNodeType.DEFAULT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            out.put(AngularNodeType.DEFER_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.CONDITION,
                    new FieldInfo(true, true, Set.of(AngularNodeType.DEFER_TRIGGER_CONDITION)));
            out.put(AngularNodeType.DEFER_TRIGGER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.TRIGGER,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.ARRAY,
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.BRACKET_EXPRESSION,
                                    AngularNodeType.CALL_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.IDENTIFIER,
                                    AngularNodeType.MEMBER_EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.NUMBER,
                                    AngularNodeType.OBJECT,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.STRING,
                                    AngularNodeType.TEMPLATE_STRING,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            out.put(AngularNodeType.DEFER_TRIGGER_CONDITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ALTERNATIVE, new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_STATEMENT)));
            m.put(
                    AngularNodeField.ALTERNATIVE_CONDITION,
                    new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_IF_STATEMENT)));
            m.put(AngularNodeField.EMPTY, new FieldInfo(false, true, Set.of(AngularNodeType.EMPTY_STATEMENT)));
            m.put(AngularNodeField.ERROR, new FieldInfo(false, true, Set.of(AngularNodeType.ERROR_STATEMENT)));
            m.put(AngularNodeField.LOADING, new FieldInfo(false, true, Set.of(AngularNodeType.LOADING_STATEMENT)));
            m.put(
                    AngularNodeField.PLACEHOLDER,
                    new FieldInfo(false, true, Set.of(AngularNodeType.PLACEHOLDER_STATEMENT)));
            out.put(AngularNodeType.DOCUMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ALTERNATIVE, new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_STATEMENT)));
            m.put(
                    AngularNodeField.ALTERNATIVE_CONDITION,
                    new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_IF_STATEMENT)));
            m.put(AngularNodeField.EMPTY, new FieldInfo(false, true, Set.of(AngularNodeType.EMPTY_STATEMENT)));
            m.put(AngularNodeField.ERROR, new FieldInfo(false, true, Set.of(AngularNodeType.ERROR_STATEMENT)));
            m.put(AngularNodeField.LOADING, new FieldInfo(false, true, Set.of(AngularNodeType.LOADING_STATEMENT)));
            m.put(
                    AngularNodeField.PLACEHOLDER,
                    new FieldInfo(false, true, Set.of(AngularNodeType.PLACEHOLDER_STATEMENT)));
            out.put(AngularNodeType.ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.CONDITION, new FieldInfo(true, false, Set.of(AngularNodeType.IF_CONDITION)));
            m.put(AngularNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            m.put(AngularNodeField.REFERENCE, new FieldInfo(false, false, Set.of(AngularNodeType.IF_REFERENCE)));
            out.put(AngularNodeType.ELSE_IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            out.put(AngularNodeType.EMPTY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            out.put(AngularNodeType.ERROR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.PIPES, new FieldInfo(false, false, Set.of(AngularNodeType.PIPE_SEQUENCE)));
            out.put(AngularNodeType.EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.NAME, new FieldInfo(true, false, Set.of(AngularNodeType.IDENTIFIER)));
            m.put(AngularNodeField.TRACK, new FieldInfo(true, false, Set.of(AngularNodeType.EXPRESSION)));
            m.put(AngularNodeField.VALUE, new FieldInfo(true, false, Set.of(AngularNodeType.EXPRESSION)));
            out.put(AngularNodeType.FOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ALIAS, new FieldInfo(true, true, Set.of(AngularNodeType.ASSIGNMENT_EXPRESSION)));
            out.put(AngularNodeType.FOR_REFERENCE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            m.put(AngularNodeField.DECLARATION, new FieldInfo(true, false, Set.of(AngularNodeType.FOR_DECLARATION)));
            m.put(AngularNodeField.REFERENCE, new FieldInfo(false, false, Set.of(AngularNodeType.FOR_REFERENCE)));
            out.put(AngularNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ALTERNATIVE, new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_STATEMENT)));
            m.put(
                    AngularNodeField.ALTERNATIVE_CONDITION,
                    new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_IF_STATEMENT)));
            m.put(AngularNodeField.EMPTY, new FieldInfo(false, true, Set.of(AngularNodeType.EMPTY_STATEMENT)));
            m.put(AngularNodeField.ERROR, new FieldInfo(false, true, Set.of(AngularNodeType.ERROR_STATEMENT)));
            m.put(AngularNodeField.LOADING, new FieldInfo(false, true, Set.of(AngularNodeType.LOADING_STATEMENT)));
            m.put(
                    AngularNodeField.PLACEHOLDER,
                    new FieldInfo(false, true, Set.of(AngularNodeType.PLACEHOLDER_STATEMENT)));
            out.put(AngularNodeType.ICU_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.CONDITION, new FieldInfo(true, false, Set.of(AngularNodeType.IF_CONDITION)));
            m.put(AngularNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            m.put(AngularNodeField.REFERENCE, new FieldInfo(false, false, Set.of(AngularNodeType.IF_REFERENCE)));
            out.put(AngularNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.CONDITION, new FieldInfo(true, true, Set.of(AngularNodeType.TIMED_EXPRESSION)));
            out.put(AngularNodeType.LOADING_CONDITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            out.put(AngularNodeType.LOADING_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.CALL, new FieldInfo(false, false, Set.of(AngularNodeType.CALL_EXPRESSION)));
            m.put(
                    AngularNodeField.OBJECT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.ARRAY,
                                    AngularNodeType.BRACKET_EXPRESSION,
                                    AngularNodeType.CALL_EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.IDENTIFIER,
                                    AngularNodeType.MEMBER_EXPRESSION,
                                    AngularNodeType.NUMBER,
                                    AngularNodeType.OBJECT,
                                    AngularNodeType.STRING,
                                    AngularNodeType.TEMPLATE_STRING)));
            m.put(AngularNodeField.PROPERTY, new FieldInfo(false, false, Set.of(AngularNodeType.IDENTIFIER)));
            m.put(AngularNodeField.UNIT, new FieldInfo(false, false, Set.of(AngularNodeType.STYLE_UNIT)));
            out.put(AngularNodeType.MEMBER_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            m.put(
                    AngularNodeField.DEFAULT_,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.ARRAY,
                                    AngularNodeType.BRACKET_EXPRESSION,
                                    AngularNodeType.CALL_EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.IDENTIFIER,
                                    AngularNodeType.MEMBER_EXPRESSION,
                                    AngularNodeType.NUMBER,
                                    AngularNodeType.OBJECT,
                                    AngularNodeType.STRING,
                                    AngularNodeType.TEMPLATE_STRING)));
            out.put(AngularNodeType.NULLISH_COALESCING_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.KEY,
                    new FieldInfo(true, false, Set.of(AngularNodeType.IDENTIFIER, AngularNodeType.STRING)));
            m.put(
                    AngularNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            out.put(AngularNodeType.PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(AngularNodeType.PIPE_ARGUMENTS)));
            m.put(AngularNodeField.NAME, new FieldInfo(true, false, Set.of(AngularNodeType.IDENTIFIER)));
            out.put(AngularNodeType.PIPE_CALL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.MINIMUM, new FieldInfo(true, false, Set.of(AngularNodeType.TIMED_EXPRESSION)));
            out.put(AngularNodeType.PLACEHOLDER_MINIMUM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.STATEMENT_BLOCK)));
            out.put(AngularNodeType.PLACEHOLDER_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.FLAGS,
                    new FieldInfo(false, false, Set.of(AngularNodeType.REGULAR_EXPRESSION_FLAGS)));
            m.put(
                    AngularNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(AngularNodeType.REGULAR_EXPRESSION_PATTERN)));
            out.put(AngularNodeType.REGULAR_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ALTERNATIVE, new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_STATEMENT)));
            m.put(
                    AngularNodeField.ALTERNATIVE_CONDITION,
                    new FieldInfo(false, true, Set.of(AngularNodeType.ELSE_IF_STATEMENT)));
            m.put(AngularNodeField.EMPTY, new FieldInfo(false, true, Set.of(AngularNodeType.EMPTY_STATEMENT)));
            m.put(AngularNodeField.ERROR, new FieldInfo(false, true, Set.of(AngularNodeType.ERROR_STATEMENT)));
            m.put(AngularNodeField.LOADING, new FieldInfo(false, true, Set.of(AngularNodeType.LOADING_STATEMENT)));
            m.put(
                    AngularNodeField.PLACEHOLDER,
                    new FieldInfo(false, true, Set.of(AngularNodeType.PLACEHOLDER_STATEMENT)));
            out.put(AngularNodeType.STATEMENT_BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ALIAS, new FieldInfo(false, false, Set.of(AngularNodeType.IDENTIFIER)));
            m.put(AngularNodeField.NAME, new FieldInfo(true, false, Set.of(AngularNodeType.IDENTIFIER)));
            m.put(AngularNodeField.OPERATOR, new FieldInfo(false, false, Set.of(AngularNodeType.IDENTIFIER)));
            m.put(
                    AngularNodeField.VALUE,
                    new FieldInfo(false, false, Set.of(AngularNodeType.EXPRESSION, AngularNodeType.IDENTIFIER)));
            out.put(AngularNodeType.STRUCTURAL_ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.ALIAS, new FieldInfo(false, false, Set.of(AngularNodeType.IDENTIFIER)));
            m.put(AngularNodeField.NAMED, new FieldInfo(false, false, Set.of(AngularNodeType.IDENTIFIER)));
            out.put(AngularNodeType.STRUCTURAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.BODY, new FieldInfo(true, false, Set.of(AngularNodeType.SWITCH_BODY)));
            m.put(AngularNodeField.VALUE, new FieldInfo(true, false, Set.of(AngularNodeType.EXPRESSION)));
            out.put(AngularNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(
                    AngularNodeField.ALTERNATIVE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            m.put(
                    AngularNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    AngularNodeType.BINARY_EXPRESSION,
                                    AngularNodeType.CONDITIONAL_EXPRESSION,
                                    AngularNodeType.EXPRESSION,
                                    AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                    AngularNodeType.REGULAR_EXPRESSION,
                                    AngularNodeType.TERNARY_EXPRESSION,
                                    AngularNodeType.UNARY_EXPRESSION)));
            m.put(
                    AngularNodeField.CONSEQUENCE,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    AngularNodeType.ARRAY,
                                    AngularNodeType.BRACKET_EXPRESSION,
                                    AngularNodeType.CALL_EXPRESSION,
                                    AngularNodeType.GROUP,
                                    AngularNodeType.IDENTIFIER,
                                    AngularNodeType.MEMBER_EXPRESSION,
                                    AngularNodeType.NUMBER,
                                    AngularNodeType.OBJECT,
                                    AngularNodeType.STRING,
                                    AngularNodeType.TEMPLATE_STRING)));
            out.put(AngularNodeType.TERNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.VALUE, new FieldInfo(true, false, Set.of(AngularNodeType.NUMBER)));
            out.put(AngularNodeType.TIMED_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<AngularNodeField, FieldInfo> m = new EnumMap<>(AngularNodeField.class);
            m.put(AngularNodeField.OPERATOR, new FieldInfo(true, false, Set.of(AngularNodeType.UNARY_OPERATOR)));
            m.put(AngularNodeField.VALUE, new FieldInfo(true, false, Set.of(AngularNodeType.EXPRESSION)));
            out.put(AngularNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<AngularNodeType, ChildInfo> initChildren() {
        EnumMap<AngularNodeType, ChildInfo> out = new EnumMap<>(AngularNodeType.class);
        out.put(AngularNodeType.ANIMATION_BINDING, new ChildInfo(true, false, Set.of(AngularNodeType.BINDING_NAME)));
        out.put(
                AngularNodeType.ARGUMENTS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.ARRAY,
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.BRACKET_EXPRESSION,
                                AngularNodeType.CALL_EXPRESSION,
                                AngularNodeType.GROUP,
                                AngularNodeType.IDENTIFIER,
                                AngularNodeType.MEMBER_EXPRESSION,
                                AngularNodeType.NUMBER,
                                AngularNodeType.OBJECT,
                                AngularNodeType.STRING,
                                AngularNodeType.TEMPLATE_STRING,
                                AngularNodeType.UNARY_EXPRESSION,
                                AngularNodeType.UNIT)));
        out.put(
                AngularNodeType.ARRAY,
                new ChildInfo(true, true, Set.of(AngularNodeType.EXPRESSION, AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.ATTRIBUTE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.ANIMATION_BINDING,
                                AngularNodeType.ATTRIBUTE_NAME,
                                AngularNodeType.ATTRIBUTE_VALUE,
                                AngularNodeType.EVENT_BINDING,
                                AngularNodeType.PROPERTY_BINDING,
                                AngularNodeType.QUOTED_ATTRIBUTE_VALUE,
                                AngularNodeType.STRUCTURAL_DIRECTIVE,
                                AngularNodeType.TWO_WAY_BINDING)));
        out.put(
                AngularNodeType.BINDING_NAME,
                new ChildInfo(true, false, Set.of(AngularNodeType.IDENTIFIER, AngularNodeType.MEMBER_EXPRESSION)));
        out.put(AngularNodeType.CASE_STATEMENT, new ChildInfo(true, false, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.CLASS_BINDING,
                new ChildInfo(true, true, Set.of(AngularNodeType.CLASS_NAME, AngularNodeType.IDENTIFIER)));
        out.put(
                AngularNodeType.CONCATENATION_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.ARRAY,
                                AngularNodeType.BRACKET_EXPRESSION,
                                AngularNodeType.CALL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.GROUP,
                                AngularNodeType.IDENTIFIER,
                                AngularNodeType.MEMBER_EXPRESSION,
                                AngularNodeType.NUMBER,
                                AngularNodeType.OBJECT,
                                AngularNodeType.STRING,
                                AngularNodeType.TEMPLATE_STRING)));
        out.put(
                AngularNodeType.CONDITIONAL_EXPRESSION,
                new ChildInfo(true, false, Set.of(AngularNodeType.CONDITIONAL_OPERATOR)));
        out.put(AngularNodeType.DEFAULT_STATEMENT, new ChildInfo(true, false, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.DEFER_STATEMENT,
                new ChildInfo(true, true, Set.of(AngularNodeType.CONTROL_KEYWORD, AngularNodeType.DEFER_TRIGGER)));
        out.put(
                AngularNodeType.DEFER_TRIGGER_CONDITION,
                new ChildInfo(true, true, Set.of(AngularNodeType.PREFETCH_KEYWORD, AngularNodeType.SPECIAL_KEYWORD)));
        out.put(
                AngularNodeType.DOCUMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AngularNodeType.DEFER_STATEMENT,
                                AngularNodeType.DOCTYPE,
                                AngularNodeType.ELEMENT,
                                AngularNodeType.ENTITY,
                                AngularNodeType.ERRONEOUS_END_TAG,
                                AngularNodeType.FOR_STATEMENT,
                                AngularNodeType.ICU_EXPRESSION,
                                AngularNodeType.IF_STATEMENT,
                                AngularNodeType.INTERPOLATION,
                                AngularNodeType.LET_STATEMENT,
                                AngularNodeType.SCRIPT_ELEMENT,
                                AngularNodeType.STYLE_ELEMENT,
                                AngularNodeType.SWITCH_STATEMENT,
                                AngularNodeType.TEXT)));
        out.put(
                AngularNodeType.ELEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.DEFER_STATEMENT,
                                AngularNodeType.DOCTYPE,
                                AngularNodeType.ELEMENT,
                                AngularNodeType.END_TAG,
                                AngularNodeType.ENTITY,
                                AngularNodeType.ERRONEOUS_END_TAG,
                                AngularNodeType.FOR_STATEMENT,
                                AngularNodeType.ICU_EXPRESSION,
                                AngularNodeType.IF_STATEMENT,
                                AngularNodeType.INTERPOLATION,
                                AngularNodeType.LET_STATEMENT,
                                AngularNodeType.SCRIPT_ELEMENT,
                                AngularNodeType.SELF_CLOSING_TAG,
                                AngularNodeType.START_TAG,
                                AngularNodeType.STYLE_ELEMENT,
                                AngularNodeType.SWITCH_STATEMENT,
                                AngularNodeType.TEXT)));
        out.put(AngularNodeType.ELSE_IF_STATEMENT, new ChildInfo(true, true, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.ELSE_STATEMENT,
                new ChildInfo(true, true, Set.of(AngularNodeType.CONTROL_KEYWORD, AngularNodeType.STATEMENT_BLOCK)));
        out.put(AngularNodeType.EMPTY_STATEMENT, new ChildInfo(true, false, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(AngularNodeType.END_TAG, new ChildInfo(true, false, Set.of(AngularNodeType.TAG_NAME)));
        out.put(
                AngularNodeType.ERRONEOUS_END_TAG,
                new ChildInfo(true, false, Set.of(AngularNodeType.ERRONEOUS_END_TAG_NAME)));
        out.put(AngularNodeType.ERROR_STATEMENT, new ChildInfo(true, false, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.EVENT_BINDING,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.ASSIGNMENT_EXPRESSION,
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.BINDING_NAME,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                AngularNodeType.ARRAY,
                                AngularNodeType.BRACKET_EXPRESSION,
                                AngularNodeType.CALL_EXPRESSION,
                                AngularNodeType.GROUP,
                                AngularNodeType.IDENTIFIER,
                                AngularNodeType.MEMBER_EXPRESSION,
                                AngularNodeType.NUMBER,
                                AngularNodeType.OBJECT,
                                AngularNodeType.STRING,
                                AngularNodeType.TEMPLATE_STRING)));
        out.put(AngularNodeType.FOR_DECLARATION, new ChildInfo(true, true, Set.of(AngularNodeType.SPECIAL_KEYWORD)));
        out.put(AngularNodeType.FOR_REFERENCE, new ChildInfo(true, false, Set.of(AngularNodeType.SPECIAL_KEYWORD)));
        out.put(AngularNodeType.FOR_STATEMENT, new ChildInfo(true, false, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.GROUP,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.ICU_CASE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.DEFER_STATEMENT,
                                AngularNodeType.DOCTYPE,
                                AngularNodeType.ELEMENT,
                                AngularNodeType.ENTITY,
                                AngularNodeType.ERRONEOUS_END_TAG,
                                AngularNodeType.FOR_STATEMENT,
                                AngularNodeType.ICU_CATEGORY,
                                AngularNodeType.ICU_EXPRESSION,
                                AngularNodeType.IF_STATEMENT,
                                AngularNodeType.INTERPOLATION,
                                AngularNodeType.LET_STATEMENT,
                                AngularNodeType.SCRIPT_ELEMENT,
                                AngularNodeType.STYLE_ELEMENT,
                                AngularNodeType.SWITCH_STATEMENT,
                                AngularNodeType.TEXT)));
        out.put(
                AngularNodeType.ICU_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.CONCATENATION_EXPRESSION,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.ICU_CASE,
                                AngularNodeType.ICU_CLAUSE,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.IF_CONDITION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.IF_REFERENCE,
                new ChildInfo(true, true, Set.of(AngularNodeType.IDENTIFIER, AngularNodeType.SPECIAL_KEYWORD)));
        out.put(AngularNodeType.IF_STATEMENT, new ChildInfo(true, false, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.INTERPOLATION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.CONCATENATION_EXPRESSION,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.LET_STATEMENT,
                new ChildInfo(
                        true, true, Set.of(AngularNodeType.ASSIGNMENT_EXPRESSION, AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.LOADING_STATEMENT,
                new ChildInfo(true, true, Set.of(AngularNodeType.CONTROL_KEYWORD, AngularNodeType.LOADING_CONDITION)));
        out.put(
                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                new ChildInfo(true, false, Set.of(AngularNodeType.COALESCING_OPERATOR)));
        out.put(
                AngularNodeType.OBJECT,
                new ChildInfo(
                        false, true, Set.of(AngularNodeType.IDENTIFIER, AngularNodeType.PAIR, AngularNodeType.SPREAD)));
        out.put(
                AngularNodeType.PIPE_ARGUMENTS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AngularNodeType.ARRAY,
                                AngularNodeType.BRACKET_EXPRESSION,
                                AngularNodeType.CALL_EXPRESSION,
                                AngularNodeType.GROUP,
                                AngularNodeType.IDENTIFIER,
                                AngularNodeType.MEMBER_EXPRESSION,
                                AngularNodeType.NUMBER,
                                AngularNodeType.OBJECT,
                                AngularNodeType.STRING,
                                AngularNodeType.TEMPLATE_STRING)));
        out.put(
                AngularNodeType.PIPE_SEQUENCE,
                new ChildInfo(true, true, Set.of(AngularNodeType.PIPE_CALL, AngularNodeType.PIPE_OPERATOR)));
        out.put(
                AngularNodeType.PLACEHOLDER_STATEMENT,
                new ChildInfo(
                        true, true, Set.of(AngularNodeType.CONTROL_KEYWORD, AngularNodeType.PLACEHOLDER_MINIMUM)));
        out.put(
                AngularNodeType.PROPERTY_BINDING,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.ASSIGNMENT_EXPRESSION,
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.BINDING_NAME,
                                AngularNodeType.CLASS_BINDING,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.QUOTED_ATTRIBUTE_VALUE,
                new ChildInfo(false, false, Set.of(AngularNodeType.ATTRIBUTE_VALUE)));
        out.put(
                AngularNodeType.REGULAR_EXPRESSION,
                new ChildInfo(false, false, Set.of(AngularNodeType.CALL_EXPRESSION)));
        out.put(
                AngularNodeType.SCRIPT_ELEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(AngularNodeType.END_TAG, AngularNodeType.RAW_TEXT, AngularNodeType.START_TAG)));
        out.put(
                AngularNodeType.SELF_CLOSING_TAG,
                new ChildInfo(true, true, Set.of(AngularNodeType.ATTRIBUTE, AngularNodeType.TAG_NAME)));
        out.put(AngularNodeType.SPREAD, new ChildInfo(true, false, Set.of(AngularNodeType.IDENTIFIER)));
        out.put(
                AngularNodeType.START_TAG,
                new ChildInfo(true, true, Set.of(AngularNodeType.ATTRIBUTE, AngularNodeType.TAG_NAME)));
        out.put(
                AngularNodeType.STATEMENT_BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AngularNodeType.DEFER_STATEMENT,
                                AngularNodeType.DOCTYPE,
                                AngularNodeType.ELEMENT,
                                AngularNodeType.ENTITY,
                                AngularNodeType.ERRONEOUS_END_TAG,
                                AngularNodeType.FOR_STATEMENT,
                                AngularNodeType.ICU_EXPRESSION,
                                AngularNodeType.IF_STATEMENT,
                                AngularNodeType.INTERPOLATION,
                                AngularNodeType.LET_STATEMENT,
                                AngularNodeType.SCRIPT_ELEMENT,
                                AngularNodeType.STYLE_ELEMENT,
                                AngularNodeType.SWITCH_STATEMENT,
                                AngularNodeType.TEXT)));
        out.put(
                AngularNodeType.STRUCTURAL_ASSIGNMENT,
                new ChildInfo(false, true, Set.of(AngularNodeType.SPECIAL_KEYWORD)));
        out.put(
                AngularNodeType.STRUCTURAL_DECLARATION,
                new ChildInfo(
                        true, true, Set.of(AngularNodeType.SPECIAL_KEYWORD, AngularNodeType.STRUCTURAL_ASSIGNMENT)));
        out.put(
                AngularNodeType.STRUCTURAL_DIRECTIVE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.IDENTIFIER,
                                AngularNodeType.STRUCTURAL_DECLARATION,
                                AngularNodeType.STRUCTURAL_EXPRESSION)));
        out.put(
                AngularNodeType.STRUCTURAL_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.IDENTIFIER,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.SPECIAL_KEYWORD,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.STYLE_ELEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(AngularNodeType.END_TAG, AngularNodeType.RAW_TEXT, AngularNodeType.START_TAG)));
        out.put(
                AngularNodeType.SWITCH_BODY,
                new ChildInfo(true, true, Set.of(AngularNodeType.CASE_STATEMENT, AngularNodeType.DEFAULT_STATEMENT)));
        out.put(AngularNodeType.SWITCH_STATEMENT, new ChildInfo(true, false, Set.of(AngularNodeType.CONTROL_KEYWORD)));
        out.put(
                AngularNodeType.TEMPLATE_STRING,
                new ChildInfo(
                        false, true, Set.of(AngularNodeType.TEMPLATE_CHARS, AngularNodeType.TEMPLATE_SUBSTITUTION)));
        out.put(
                AngularNodeType.TEMPLATE_SUBSTITUTION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        out.put(
                AngularNodeType.TERNARY_EXPRESSION,
                new ChildInfo(true, true, Set.of(AngularNodeType.TERNARY_OPERATOR)));
        out.put(
                AngularNodeType.TIMED_EXPRESSION,
                new ChildInfo(true, true, Set.of(AngularNodeType.SPECIAL_KEYWORD, AngularNodeType.UNIT)));
        out.put(
                AngularNodeType.TWO_WAY_BINDING,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AngularNodeType.ASSIGNMENT_EXPRESSION,
                                AngularNodeType.BINARY_EXPRESSION,
                                AngularNodeType.BINDING_NAME,
                                AngularNodeType.CONDITIONAL_EXPRESSION,
                                AngularNodeType.EXPRESSION,
                                AngularNodeType.NULLISH_COALESCING_EXPRESSION,
                                AngularNodeType.REGULAR_EXPRESSION,
                                AngularNodeType.TERNARY_EXPRESSION,
                                AngularNodeType.UNARY_EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<AngularNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<AngularNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<AngularNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<AngularNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
