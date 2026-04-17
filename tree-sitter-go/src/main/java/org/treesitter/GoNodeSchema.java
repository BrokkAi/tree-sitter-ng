package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code go} from tree-sitter {@code node-types.json}.
 */
public final class GoNodeSchema {
    private GoNodeSchema() {}

    public static Set<GoNodeField> fields(@Nullable GoNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<GoNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<GoNodeType> allowedTypes(@Nullable GoNodeType owner, @Nullable GoNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<GoNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable GoNodeType owner, @Nullable GoNodeField field) {
        if (owner == null || field == null) return false;
        Map<GoNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable GoNodeType owner, @Nullable GoNodeField field) {
        if (owner == null || field == null) return false;
        Map<GoNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<GoNodeType> allowedChildTypes(@Nullable GoNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable GoNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable GoNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<GoNodeType, Map<GoNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<GoNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<GoNodeType, Map<GoNodeField, FieldInfo>> initFields() {
        EnumMap<GoNodeType, Map<GoNodeField, FieldInfo>> out = new EnumMap<>(GoNodeType.class);
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.ELEMENT, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            m.put(GoNodeField.LENGTH, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.ARRAY_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.LEFT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            m.put(GoNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(GoNodeField.RIGHT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            out.put(GoNodeType.ASSIGNMENT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.LEFT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(GoNodeField.RIGHT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(GoNodeType.ARGUMENT_LIST)));
            m.put(GoNodeField.FUNCTION, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(GoNodeType.TYPE_ARGUMENTS)));
            out.put(GoNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.VALUE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.CHANNEL_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(
                    GoNodeField.COMMUNICATION,
                    new FieldInfo(true, false, Set.of(GoNodeType.RECEIVE_STATEMENT, GoNodeType.SEND_STATEMENT)));
            out.put(GoNodeType.COMMUNICATION_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.BODY, new FieldInfo(true, false, Set.of(GoNodeType.LITERAL_VALUE)));
            m.put(
                    GoNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    GoNodeType.ARRAY_TYPE,
                                    GoNodeType.GENERIC_TYPE,
                                    GoNodeType.IMPLICIT_LENGTH_ARRAY_TYPE,
                                    GoNodeType.MAP_TYPE,
                                    GoNodeType.QUALIFIED_TYPE,
                                    GoNodeType.SLICE_TYPE,
                                    GoNodeType.STRUCT_TYPE,
                                    GoNodeType.TYPE_IDENTIFIER)));
            out.put(GoNodeType.COMPOSITE_LITERAL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(true, true, Set.of(GoNodeType.IDENTIFIER)));
            m.put(GoNodeField.TYPE, new FieldInfo(false, false, Set.of(GoNodeType.TYPE)));
            m.put(GoNodeField.VALUE, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            out.put(GoNodeType.CONST_SPEC, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.VALUE, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            out.put(GoNodeType.EXPRESSION_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.INITIALIZER, new FieldInfo(false, false, Set.of(GoNodeType.SIMPLE_STATEMENT)));
            m.put(GoNodeField.VALUE, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.EXPRESSION_SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(false, true, Set.of(GoNodeType.FIELD_IDENTIFIER)));
            m.put(
                    GoNodeField.TAG,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(GoNodeType.INTERPRETED_STRING_LITERAL, GoNodeType.RAW_STRING_LITERAL)));
            m.put(
                    GoNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    GoNodeType.GENERIC_TYPE,
                                    GoNodeType.QUALIFIED_TYPE,
                                    GoNodeType.TYPE,
                                    GoNodeType.TYPE_IDENTIFIER)));
            out.put(GoNodeType.FIELD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.CONDITION, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.INITIALIZER, new FieldInfo(false, false, Set.of(GoNodeType.SIMPLE_STATEMENT)));
            m.put(GoNodeField.UPDATE, new FieldInfo(false, false, Set.of(GoNodeType.SIMPLE_STATEMENT)));
            out.put(GoNodeType.FOR_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.BODY, new FieldInfo(true, false, Set.of(GoNodeType.BLOCK)));
            out.put(GoNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.BODY, new FieldInfo(false, false, Set.of(GoNodeType.BLOCK)));
            m.put(GoNodeField.NAME, new FieldInfo(true, false, Set.of(GoNodeType.IDENTIFIER)));
            m.put(GoNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(GoNodeType.PARAMETER_LIST)));
            m.put(
                    GoNodeField.RESULT,
                    new FieldInfo(false, false, Set.of(GoNodeType.PARAMETER_LIST, GoNodeType.SIMPLE_TYPE)));
            m.put(GoNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(GoNodeType.TYPE_PARAMETER_LIST)));
            out.put(GoNodeType.FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(GoNodeType.PARAMETER_LIST)));
            m.put(
                    GoNodeField.RESULT,
                    new FieldInfo(false, false, Set.of(GoNodeType.PARAMETER_LIST, GoNodeType.SIMPLE_TYPE)));
            out.put(GoNodeType.FUNCTION_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.BODY, new FieldInfo(true, false, Set.of(GoNodeType.BLOCK)));
            m.put(GoNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(GoNodeType.PARAMETER_LIST)));
            m.put(
                    GoNodeField.RESULT,
                    new FieldInfo(false, false, Set.of(GoNodeType.PARAMETER_LIST, GoNodeType.SIMPLE_TYPE)));
            out.put(GoNodeType.FUNC_LITERAL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(
                    GoNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(GoNodeType.NEGATED_TYPE, GoNodeType.QUALIFIED_TYPE, GoNodeType.TYPE_IDENTIFIER)));
            m.put(GoNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(GoNodeType.TYPE_ARGUMENTS)));
            out.put(GoNodeType.GENERIC_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(
                    GoNodeField.ALTERNATIVE,
                    new FieldInfo(false, false, Set.of(GoNodeType.BLOCK, GoNodeType.IF_STATEMENT)));
            m.put(GoNodeField.CONDITION, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(GoNodeType.BLOCK)));
            m.put(GoNodeField.INITIALIZER, new FieldInfo(false, false, Set.of(GoNodeType.SIMPLE_STATEMENT)));
            out.put(GoNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.ELEMENT, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.IMPLICIT_LENGTH_ARRAY_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(
                    GoNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(GoNodeType.BLANK_IDENTIFIER, GoNodeType.DOT, GoNodeType.PACKAGE_IDENTIFIER)));
            m.put(
                    GoNodeField.PATH,
                    new FieldInfo(
                            true, false, Set.of(GoNodeType.INTERPRETED_STRING_LITERAL, GoNodeType.RAW_STRING_LITERAL)));
            out.put(GoNodeType.IMPORT_SPEC, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.INDEX, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.OPERAND, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.INDEX_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.KEY, new FieldInfo(true, false, Set.of(GoNodeType.LITERAL_ELEMENT)));
            m.put(GoNodeField.VALUE, new FieldInfo(true, false, Set.of(GoNodeType.LITERAL_ELEMENT)));
            out.put(GoNodeType.KEYED_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.LABEL, new FieldInfo(true, false, Set.of(GoNodeType.LABEL_NAME)));
            out.put(GoNodeType.LABELED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.KEY, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            m.put(GoNodeField.VALUE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.MAP_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.BODY, new FieldInfo(false, false, Set.of(GoNodeType.BLOCK)));
            m.put(GoNodeField.NAME, new FieldInfo(true, false, Set.of(GoNodeType.FIELD_IDENTIFIER)));
            m.put(GoNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(GoNodeType.PARAMETER_LIST)));
            m.put(GoNodeField.RECEIVER, new FieldInfo(true, false, Set.of(GoNodeType.PARAMETER_LIST)));
            m.put(
                    GoNodeField.RESULT,
                    new FieldInfo(false, false, Set.of(GoNodeType.PARAMETER_LIST, GoNodeType.SIMPLE_TYPE)));
            out.put(GoNodeType.METHOD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(true, false, Set.of(GoNodeType.FIELD_IDENTIFIER)));
            m.put(GoNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(GoNodeType.PARAMETER_LIST)));
            m.put(
                    GoNodeField.RESULT,
                    new FieldInfo(false, false, Set.of(GoNodeType.PARAMETER_LIST, GoNodeType.SIMPLE_TYPE)));
            out.put(GoNodeType.METHOD_ELEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(false, true, Set.of(GoNodeType.IDENTIFIER)));
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(true, false, Set.of(GoNodeType.TYPE_IDENTIFIER)));
            m.put(GoNodeField.PACKAGE_, new FieldInfo(true, false, Set.of(GoNodeType.PACKAGE_IDENTIFIER)));
            out.put(GoNodeType.QUALIFIED_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.LEFT, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            m.put(GoNodeField.RIGHT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.RANGE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.LEFT, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            m.put(GoNodeField.RIGHT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.RECEIVE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.FIELD, new FieldInfo(true, false, Set.of(GoNodeType.FIELD_IDENTIFIER)));
            m.put(GoNodeField.OPERAND, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.SELECTOR_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.CHANNEL, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.VALUE, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.SEND_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.LEFT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            m.put(GoNodeField.RIGHT, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            out.put(GoNodeType.SHORT_VAR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.CAPACITY, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.END, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.OPERAND, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.START, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.SLICE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.ELEMENT, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.SLICE_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(true, false, Set.of(GoNodeType.TYPE_IDENTIFIER)));
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            m.put(GoNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(GoNodeType.TYPE_PARAMETER_LIST)));
            out.put(GoNodeType.TYPE_ALIAS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.OPERAND, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.TYPE_ASSERTION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.TYPE, new FieldInfo(true, true, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.TYPE_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.OPERAND, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.TYPE_CONVERSION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.TYPE_INSTANTIATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(true, true, Set.of(GoNodeType.IDENTIFIER)));
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE_CONSTRAINT)));
            out.put(GoNodeType.TYPE_PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(true, false, Set.of(GoNodeType.TYPE_IDENTIFIER)));
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            m.put(GoNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(GoNodeType.TYPE_PARAMETER_LIST)));
            out.put(GoNodeType.TYPE_SPEC, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.ALIAS, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            m.put(GoNodeField.INITIALIZER, new FieldInfo(false, false, Set.of(GoNodeType.SIMPLE_STATEMENT)));
            m.put(GoNodeField.VALUE, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            out.put(GoNodeType.TYPE_SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.OPERAND, new FieldInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
            m.put(GoNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(GoNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(false, false, Set.of(GoNodeType.IDENTIFIER)));
            m.put(GoNodeField.TYPE, new FieldInfo(true, false, Set.of(GoNodeType.TYPE)));
            out.put(GoNodeType.VARIADIC_PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<GoNodeField, FieldInfo> m = new EnumMap<>(GoNodeField.class);
            m.put(GoNodeField.NAME, new FieldInfo(true, true, Set.of(GoNodeType.IDENTIFIER)));
            m.put(GoNodeField.TYPE, new FieldInfo(false, false, Set.of(GoNodeType.TYPE)));
            m.put(GoNodeField.VALUE, new FieldInfo(false, false, Set.of(GoNodeType.EXPRESSION_LIST)));
            out.put(GoNodeType.VAR_SPEC, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<GoNodeType, ChildInfo> initChildren() {
        EnumMap<GoNodeType, ChildInfo> out = new EnumMap<>(GoNodeType.class);
        out.put(
                GoNodeType.ARGUMENT_LIST,
                new ChildInfo(
                        false, true, Set.of(GoNodeType.EXPRESSION, GoNodeType.TYPE, GoNodeType.VARIADIC_ARGUMENT)));
        out.put(GoNodeType.BLOCK, new ChildInfo(false, false, Set.of(GoNodeType.STATEMENT_LIST)));
        out.put(GoNodeType.BREAK_STATEMENT, new ChildInfo(false, false, Set.of(GoNodeType.LABEL_NAME)));
        out.put(GoNodeType.COMMUNICATION_CASE, new ChildInfo(false, false, Set.of(GoNodeType.STATEMENT_LIST)));
        out.put(GoNodeType.CONST_DECLARATION, new ChildInfo(false, true, Set.of(GoNodeType.CONST_SPEC)));
        out.put(GoNodeType.CONTINUE_STATEMENT, new ChildInfo(false, false, Set.of(GoNodeType.LABEL_NAME)));
        out.put(GoNodeType.DEC_STATEMENT, new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
        out.put(GoNodeType.DEFAULT_CASE, new ChildInfo(false, false, Set.of(GoNodeType.STATEMENT_LIST)));
        out.put(GoNodeType.DEFER_STATEMENT, new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
        out.put(GoNodeType.EXPRESSION_CASE, new ChildInfo(false, false, Set.of(GoNodeType.STATEMENT_LIST)));
        out.put(GoNodeType.EXPRESSION_LIST, new ChildInfo(true, true, Set.of(GoNodeType.EXPRESSION)));
        out.put(GoNodeType.EXPRESSION_STATEMENT, new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
        out.put(
                GoNodeType.EXPRESSION_SWITCH_STATEMENT,
                new ChildInfo(false, true, Set.of(GoNodeType.DEFAULT_CASE, GoNodeType.EXPRESSION_CASE)));
        out.put(GoNodeType.FIELD_DECLARATION_LIST, new ChildInfo(false, true, Set.of(GoNodeType.FIELD_DECLARATION)));
        out.put(
                GoNodeType.FOR_STATEMENT,
                new ChildInfo(
                        false, false, Set.of(GoNodeType.EXPRESSION, GoNodeType.FOR_CLAUSE, GoNodeType.RANGE_CLAUSE)));
        out.put(GoNodeType.GOTO_STATEMENT, new ChildInfo(true, false, Set.of(GoNodeType.LABEL_NAME)));
        out.put(GoNodeType.GO_STATEMENT, new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
        out.put(
                GoNodeType.IMPORT_DECLARATION,
                new ChildInfo(true, false, Set.of(GoNodeType.IMPORT_SPEC, GoNodeType.IMPORT_SPEC_LIST)));
        out.put(GoNodeType.IMPORT_SPEC_LIST, new ChildInfo(false, true, Set.of(GoNodeType.IMPORT_SPEC)));
        out.put(GoNodeType.INC_STATEMENT, new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
        out.put(
                GoNodeType.INTERFACE_TYPE,
                new ChildInfo(false, true, Set.of(GoNodeType.METHOD_ELEM, GoNodeType.TYPE_ELEM)));
        out.put(
                GoNodeType.INTERPRETED_STRING_LITERAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(GoNodeType.ESCAPE_SEQUENCE, GoNodeType.INTERPRETED_STRING_LITERAL_CONTENT)));
        out.put(GoNodeType.LABELED_STATEMENT, new ChildInfo(false, false, Set.of(GoNodeType.STATEMENT)));
        out.put(
                GoNodeType.LITERAL_ELEMENT,
                new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION, GoNodeType.LITERAL_VALUE)));
        out.put(
                GoNodeType.LITERAL_VALUE,
                new ChildInfo(false, true, Set.of(GoNodeType.KEYED_ELEMENT, GoNodeType.LITERAL_ELEMENT)));
        out.put(GoNodeType.NEGATED_TYPE, new ChildInfo(true, false, Set.of(GoNodeType.TYPE)));
        out.put(GoNodeType.PACKAGE_CLAUSE, new ChildInfo(true, false, Set.of(GoNodeType.PACKAGE_IDENTIFIER)));
        out.put(
                GoNodeType.PARAMETER_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(GoNodeType.PARAMETER_DECLARATION, GoNodeType.VARIADIC_PARAMETER_DECLARATION)));
        out.put(GoNodeType.PARENTHESIZED_EXPRESSION, new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
        out.put(GoNodeType.PARENTHESIZED_TYPE, new ChildInfo(true, false, Set.of(GoNodeType.TYPE)));
        out.put(GoNodeType.POINTER_TYPE, new ChildInfo(true, false, Set.of(GoNodeType.TYPE)));
        out.put(
                GoNodeType.RAW_STRING_LITERAL,
                new ChildInfo(true, false, Set.of(GoNodeType.RAW_STRING_LITERAL_CONTENT)));
        out.put(GoNodeType.RETURN_STATEMENT, new ChildInfo(false, false, Set.of(GoNodeType.EXPRESSION_LIST)));
        out.put(
                GoNodeType.SELECT_STATEMENT,
                new ChildInfo(false, true, Set.of(GoNodeType.COMMUNICATION_CASE, GoNodeType.DEFAULT_CASE)));
        out.put(
                GoNodeType.SOURCE_FILE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                GoNodeType.FUNCTION_DECLARATION,
                                GoNodeType.IMPORT_DECLARATION,
                                GoNodeType.METHOD_DECLARATION,
                                GoNodeType.PACKAGE_CLAUSE,
                                GoNodeType.STATEMENT)));
        out.put(GoNodeType.STATEMENT_LIST, new ChildInfo(true, true, Set.of(GoNodeType.STATEMENT)));
        out.put(GoNodeType.STRUCT_TYPE, new ChildInfo(true, false, Set.of(GoNodeType.FIELD_DECLARATION_LIST)));
        out.put(GoNodeType.TYPE_ARGUMENTS, new ChildInfo(true, true, Set.of(GoNodeType.TYPE_ELEM)));
        out.put(GoNodeType.TYPE_CASE, new ChildInfo(false, false, Set.of(GoNodeType.STATEMENT_LIST)));
        out.put(GoNodeType.TYPE_CONSTRAINT, new ChildInfo(true, true, Set.of(GoNodeType.TYPE)));
        out.put(
                GoNodeType.TYPE_DECLARATION,
                new ChildInfo(false, true, Set.of(GoNodeType.TYPE_ALIAS, GoNodeType.TYPE_SPEC)));
        out.put(GoNodeType.TYPE_ELEM, new ChildInfo(true, true, Set.of(GoNodeType.TYPE)));
        out.put(GoNodeType.TYPE_INSTANTIATION_EXPRESSION, new ChildInfo(true, true, Set.of(GoNodeType.TYPE)));
        out.put(
                GoNodeType.TYPE_PARAMETER_LIST,
                new ChildInfo(true, true, Set.of(GoNodeType.TYPE_PARAMETER_DECLARATION)));
        out.put(
                GoNodeType.TYPE_SWITCH_STATEMENT,
                new ChildInfo(false, true, Set.of(GoNodeType.DEFAULT_CASE, GoNodeType.TYPE_CASE)));
        out.put(GoNodeType.VARIADIC_ARGUMENT, new ChildInfo(true, false, Set.of(GoNodeType.EXPRESSION)));
        out.put(
                GoNodeType.VAR_DECLARATION,
                new ChildInfo(true, false, Set.of(GoNodeType.VAR_SPEC, GoNodeType.VAR_SPEC_LIST)));
        out.put(GoNodeType.VAR_SPEC_LIST, new ChildInfo(false, true, Set.of(GoNodeType.VAR_SPEC)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<GoNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<GoNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<GoNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<GoNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
