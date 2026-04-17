package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code python} from tree-sitter {@code node-types.json}.
 */
public final class PythonNodeSchema {
    private PythonNodeSchema() {}

    public static Set<PythonNodeField> fields(@Nullable PythonNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<PythonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<PythonNodeType> allowedTypes(@Nullable PythonNodeType owner, @Nullable PythonNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<PythonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable PythonNodeType owner, @Nullable PythonNodeField field) {
        if (owner == null || field == null) return false;
        Map<PythonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable PythonNodeType owner, @Nullable PythonNodeField field) {
        if (owner == null || field == null) return false;
        Map<PythonNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<PythonNodeType> allowedChildTypes(@Nullable PythonNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable PythonNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable PythonNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<PythonNodeType, Map<PythonNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<PythonNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<PythonNodeType, Map<PythonNodeField, FieldInfo>> initFields() {
        EnumMap<PythonNodeType, Map<PythonNodeField, FieldInfo>> out = new EnumMap<>(PythonNodeType.class);
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ALIAS, new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
            m.put(PythonNodeField.NAME, new FieldInfo(true, false, Set.of(PythonNodeType.DOTTED_NAME)));
            out.put(PythonNodeType.ALIASED_IMPORT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.LEFT,
                    new FieldInfo(true, false, Set.of(PythonNodeType.PATTERN, PythonNodeType.PATTERN_LIST)));
            m.put(
                    PythonNodeField.RIGHT,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    PythonNodeType.ASSIGNMENT,
                                    PythonNodeType.AUGMENTED_ASSIGNMENT,
                                    PythonNodeType.EXPRESSION,
                                    PythonNodeType.EXPRESSION_LIST,
                                    PythonNodeType.PATTERN_LIST,
                                    PythonNodeType.YIELD_)));
            m.put(PythonNodeField.TYPE, new FieldInfo(false, false, Set.of(PythonNodeType.TYPE)));
            out.put(PythonNodeType.ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ALIAS, new FieldInfo(false, false, Collections.emptySet()));
            out.put(PythonNodeType.AS_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ATTRIBUTE, new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
            m.put(PythonNodeField.OBJECT, new FieldInfo(true, false, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
            out.put(PythonNodeType.ATTRIBUTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.LEFT,
                    new FieldInfo(true, false, Set.of(PythonNodeType.PATTERN, PythonNodeType.PATTERN_LIST)));
            m.put(PythonNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    PythonNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PythonNodeType.ASSIGNMENT,
                                    PythonNodeType.AUGMENTED_ASSIGNMENT,
                                    PythonNodeType.EXPRESSION,
                                    PythonNodeType.EXPRESSION_LIST,
                                    PythonNodeType.PATTERN_LIST,
                                    PythonNodeType.YIELD_)));
            out.put(PythonNodeType.AUGMENTED_ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.LEFT, new FieldInfo(true, false, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
            m.put(PythonNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(PythonNodeField.RIGHT, new FieldInfo(true, false, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
            out.put(PythonNodeType.BINARY_OPERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ALTERNATIVE, new FieldInfo(false, true, Set.of(PythonNodeType.CASE_CLAUSE)));
            out.put(PythonNodeType.BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.LEFT, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            m.put(PythonNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(PythonNodeField.RIGHT, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.BOOLEAN_OPERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.ARGUMENTS,
                    new FieldInfo(
                            true, false, Set.of(PythonNodeType.ARGUMENT_LIST, PythonNodeType.GENERATOR_EXPRESSION)));
            m.put(PythonNodeField.FUNCTION, new FieldInfo(true, false, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
            out.put(PythonNodeType.CALL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            m.put(PythonNodeField.GUARD, new FieldInfo(false, false, Set.of(PythonNodeType.IF_CLAUSE)));
            out.put(PythonNodeType.CASE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            m.put(PythonNodeField.NAME, new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
            m.put(PythonNodeField.SUPERCLASSES, new FieldInfo(false, false, Set.of(PythonNodeType.ARGUMENT_LIST)));
            m.put(PythonNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(PythonNodeType.TYPE_PARAMETER)));
            out.put(PythonNodeType.CLASS_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.OPERATORS, new FieldInfo(true, true, Collections.emptySet()));
            out.put(PythonNodeType.COMPARISON_OPERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.DEFINITION,
                    new FieldInfo(
                            true, false, Set.of(PythonNodeType.CLASS_DEFINITION, PythonNodeType.FUNCTION_DEFINITION)));
            out.put(PythonNodeType.DECORATED_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.NAME,
                    new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER, PythonNodeType.TUPLE_PATTERN)));
            m.put(PythonNodeField.VALUE, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.DEFAULT_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.PAIR)));
            out.put(PythonNodeType.DICTIONARY_COMPREHENSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.KEY,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    PythonNodeType.CLASS_PATTERN,
                                    PythonNodeType.COMPLEX_PATTERN,
                                    PythonNodeType.CONCATENATED_STRING,
                                    PythonNodeType.DICT_PATTERN,
                                    PythonNodeType.DOTTED_NAME,
                                    PythonNodeType.FALSE,
                                    PythonNodeType.FLOAT_,
                                    PythonNodeType.INTEGER,
                                    PythonNodeType.LIST_PATTERN,
                                    PythonNodeType.NONE,
                                    PythonNodeType.SPLAT_PATTERN,
                                    PythonNodeType.STRING,
                                    PythonNodeType.TRUE,
                                    PythonNodeType.TUPLE_PATTERN,
                                    PythonNodeType.UNION_PATTERN)));
            m.put(PythonNodeField.VALUE, new FieldInfo(false, true, Set.of(PythonNodeType.CASE_PATTERN)));
            out.put(PythonNodeType.DICT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.CONDITION, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            m.put(PythonNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            out.put(PythonNodeType.ELIF_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            out.put(PythonNodeType.ELSE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ALIAS, new FieldInfo(false, false, Set.of(PythonNodeType.EXPRESSION)));
            m.put(PythonNodeField.VALUE, new FieldInfo(false, true, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.EXCEPT_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.CODE,
                    new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER, PythonNodeType.STRING)));
            out.put(PythonNodeType.EXEC_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.EXPRESSION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PythonNodeType.EXPRESSION,
                                    PythonNodeType.EXPRESSION_LIST,
                                    PythonNodeType.PATTERN_LIST,
                                    PythonNodeType.YIELD_)));
            m.put(
                    PythonNodeField.FORMAT_SPECIFIER,
                    new FieldInfo(false, false, Set.of(PythonNodeType.FORMAT_SPECIFIER)));
            m.put(PythonNodeField.TYPE_CONVERSION, new FieldInfo(false, false, Set.of(PythonNodeType.TYPE_CONVERSION)));
            out.put(PythonNodeType.FORMAT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.LEFT,
                    new FieldInfo(true, false, Set.of(PythonNodeType.PATTERN, PythonNodeType.PATTERN_LIST)));
            m.put(PythonNodeField.RIGHT, new FieldInfo(true, true, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.FOR_IN_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(PythonNodeType.ELSE_CLAUSE)));
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            m.put(
                    PythonNodeField.LEFT,
                    new FieldInfo(true, false, Set.of(PythonNodeType.PATTERN, PythonNodeType.PATTERN_LIST)));
            m.put(
                    PythonNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION, PythonNodeType.EXPRESSION_LIST)));
            out.put(PythonNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            m.put(PythonNodeField.NAME, new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
            m.put(PythonNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(PythonNodeType.PARAMETERS)));
            m.put(PythonNodeField.RETURN_TYPE, new FieldInfo(false, false, Set.of(PythonNodeType.TYPE)));
            m.put(PythonNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(PythonNodeType.TYPE_PARAMETER)));
            out.put(PythonNodeType.FUNCTION_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.NAME,
                    new FieldInfo(true, true, Set.of(PythonNodeType.ALIASED_IMPORT, PythonNodeType.DOTTED_NAME)));
            out.put(PythonNodeType.FUTURE_IMPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.GENERATOR_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.ALTERNATIVE,
                    new FieldInfo(false, true, Set.of(PythonNodeType.ELIF_CLAUSE, PythonNodeType.ELSE_CLAUSE)));
            m.put(PythonNodeField.CONDITION, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            m.put(PythonNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            out.put(PythonNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.MODULE_NAME,
                    new FieldInfo(true, false, Set.of(PythonNodeType.DOTTED_NAME, PythonNodeType.RELATIVE_IMPORT)));
            m.put(
                    PythonNodeField.NAME,
                    new FieldInfo(false, true, Set.of(PythonNodeType.ALIASED_IMPORT, PythonNodeType.DOTTED_NAME)));
            out.put(PythonNodeType.IMPORT_FROM_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.NAME,
                    new FieldInfo(true, true, Set.of(PythonNodeType.ALIASED_IMPORT, PythonNodeType.DOTTED_NAME)));
            out.put(PythonNodeType.IMPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.EXPRESSION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PythonNodeType.EXPRESSION,
                                    PythonNodeType.EXPRESSION_LIST,
                                    PythonNodeType.PATTERN_LIST,
                                    PythonNodeType.YIELD_)));
            m.put(
                    PythonNodeField.FORMAT_SPECIFIER,
                    new FieldInfo(false, false, Set.of(PythonNodeType.FORMAT_SPECIFIER)));
            m.put(PythonNodeField.TYPE_CONVERSION, new FieldInfo(false, false, Set.of(PythonNodeType.TYPE_CONVERSION)));
            out.put(PythonNodeType.INTERPOLATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.NAME, new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
            m.put(PythonNodeField.VALUE, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.KEYWORD_ARGUMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            m.put(PythonNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(PythonNodeType.LAMBDA_PARAMETERS)));
            out.put(PythonNodeType.LAMBDA, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.LIST_COMPREHENSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            m.put(PythonNodeField.SUBJECT, new FieldInfo(true, true, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.MATCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.NAME, new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
            m.put(PythonNodeField.VALUE, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.NAMED_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.NOT_OPERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.KEY, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            m.put(PythonNodeField.VALUE, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ARGUMENT, new FieldInfo(false, true, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.PRINT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.CAUSE, new FieldInfo(false, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.RAISE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.SET_COMPREHENSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(
                    PythonNodeField.SUBSCRIPT,
                    new FieldInfo(true, true, Set.of(PythonNodeType.EXPRESSION, PythonNodeType.SLICE)));
            m.put(PythonNodeField.VALUE, new FieldInfo(true, false, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
            out.put(PythonNodeType.SUBSCRIPT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            out.put(PythonNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.NAME, new FieldInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
            m.put(PythonNodeField.TYPE, new FieldInfo(true, false, Set.of(PythonNodeType.TYPE)));
            m.put(PythonNodeField.VALUE, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.TYPED_DEFAULT_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.TYPE, new FieldInfo(true, false, Set.of(PythonNodeType.TYPE)));
            out.put(PythonNodeType.TYPED_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.LEFT, new FieldInfo(true, false, Set.of(PythonNodeType.TYPE)));
            m.put(PythonNodeField.RIGHT, new FieldInfo(true, false, Set.of(PythonNodeType.TYPE)));
            out.put(PythonNodeType.TYPE_ALIAS_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
            m.put(PythonNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(PythonNodeType.UNARY_OPERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(PythonNodeType.ELSE_CLAUSE)));
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            m.put(PythonNodeField.CONDITION, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.VALUE, new FieldInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
            out.put(PythonNodeType.WITH_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PythonNodeField, FieldInfo> m = new EnumMap<>(PythonNodeField.class);
            m.put(PythonNodeField.BODY, new FieldInfo(true, false, Set.of(PythonNodeType.BLOCK)));
            out.put(PythonNodeType.WITH_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<PythonNodeType, ChildInfo> initChildren() {
        EnumMap<PythonNodeType, ChildInfo> out = new EnumMap<>(PythonNodeType.class);
        out.put(
                PythonNodeType.ARGUMENT_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PythonNodeType.DICTIONARY_SPLAT,
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.KEYWORD_ARGUMENT,
                                PythonNodeType.LIST_SPLAT,
                                PythonNodeType.PARENTHESIZED_EXPRESSION)));
        out.put(PythonNodeType.ASSERT_STATEMENT, new ChildInfo(true, true, Set.of(PythonNodeType.EXPRESSION)));
        out.put(
                PythonNodeType.AS_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(PythonNodeType.CASE_PATTERN, PythonNodeType.EXPRESSION, PythonNodeType.IDENTIFIER)));
        out.put(PythonNodeType.AWAIT, new ChildInfo(true, false, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
        out.put(
                PythonNodeType.BLOCK,
                new ChildInfo(false, true, Set.of(PythonNodeType.COMPOUND_STATEMENT, PythonNodeType.SIMPLE_STATEMENT)));
        out.put(PythonNodeType.CASE_CLAUSE, new ChildInfo(true, true, Set.of(PythonNodeType.CASE_PATTERN)));
        out.put(
                PythonNodeType.CASE_PATTERN,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                PythonNodeType.AS_PATTERN,
                                PythonNodeType.CLASS_PATTERN,
                                PythonNodeType.COMPLEX_PATTERN,
                                PythonNodeType.CONCATENATED_STRING,
                                PythonNodeType.DICT_PATTERN,
                                PythonNodeType.DOTTED_NAME,
                                PythonNodeType.FALSE,
                                PythonNodeType.FLOAT_,
                                PythonNodeType.INTEGER,
                                PythonNodeType.KEYWORD_PATTERN,
                                PythonNodeType.LIST_PATTERN,
                                PythonNodeType.NONE,
                                PythonNodeType.SPLAT_PATTERN,
                                PythonNodeType.STRING,
                                PythonNodeType.TRUE,
                                PythonNodeType.TUPLE_PATTERN,
                                PythonNodeType.UNION_PATTERN)));
        out.put(PythonNodeType.CHEVRON, new ChildInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
        out.put(
                PythonNodeType.CLASS_PATTERN,
                new ChildInfo(true, true, Set.of(PythonNodeType.CASE_PATTERN, PythonNodeType.DOTTED_NAME)));
        out.put(
                PythonNodeType.COMPARISON_OPERATOR,
                new ChildInfo(true, true, Set.of(PythonNodeType.PRIMARY_EXPRESSION)));
        out.put(
                PythonNodeType.COMPLEX_PATTERN,
                new ChildInfo(true, true, Set.of(PythonNodeType.FLOAT_, PythonNodeType.INTEGER)));
        out.put(PythonNodeType.CONCATENATED_STRING, new ChildInfo(true, true, Set.of(PythonNodeType.STRING)));
        out.put(PythonNodeType.CONDITIONAL_EXPRESSION, new ChildInfo(true, true, Set.of(PythonNodeType.EXPRESSION)));
        out.put(PythonNodeType.CONSTRAINED_TYPE, new ChildInfo(true, true, Set.of(PythonNodeType.TYPE)));
        out.put(PythonNodeType.DECORATED_DEFINITION, new ChildInfo(true, true, Set.of(PythonNodeType.DECORATOR)));
        out.put(PythonNodeType.DECORATOR, new ChildInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
        out.put(
                PythonNodeType.DELETE_STATEMENT,
                new ChildInfo(true, false, Set.of(PythonNodeType.EXPRESSION, PythonNodeType.EXPRESSION_LIST)));
        out.put(
                PythonNodeType.DICTIONARY,
                new ChildInfo(false, true, Set.of(PythonNodeType.DICTIONARY_SPLAT, PythonNodeType.PAIR)));
        out.put(
                PythonNodeType.DICTIONARY_COMPREHENSION,
                new ChildInfo(true, true, Set.of(PythonNodeType.FOR_IN_CLAUSE, PythonNodeType.IF_CLAUSE)));
        out.put(PythonNodeType.DICTIONARY_SPLAT, new ChildInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
        out.put(
                PythonNodeType.DICTIONARY_SPLAT_PATTERN,
                new ChildInfo(
                        true,
                        false,
                        Set.of(PythonNodeType.ATTRIBUTE, PythonNodeType.IDENTIFIER, PythonNodeType.SUBSCRIPT)));
        out.put(PythonNodeType.DICT_PATTERN, new ChildInfo(false, true, Set.of(PythonNodeType.SPLAT_PATTERN)));
        out.put(PythonNodeType.DOTTED_NAME, new ChildInfo(true, true, Set.of(PythonNodeType.IDENTIFIER)));
        out.put(PythonNodeType.EXCEPT_CLAUSE, new ChildInfo(true, false, Set.of(PythonNodeType.BLOCK)));
        out.put(PythonNodeType.EXEC_STATEMENT, new ChildInfo(false, true, Set.of(PythonNodeType.EXPRESSION)));
        out.put(PythonNodeType.EXPRESSION_LIST, new ChildInfo(true, true, Set.of(PythonNodeType.EXPRESSION)));
        out.put(
                PythonNodeType.EXPRESSION_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PythonNodeType.ASSIGNMENT,
                                PythonNodeType.AUGMENTED_ASSIGNMENT,
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.YIELD_)));
        out.put(PythonNodeType.FINALLY_CLAUSE, new ChildInfo(true, false, Set.of(PythonNodeType.BLOCK)));
        out.put(PythonNodeType.FORMAT_SPECIFIER, new ChildInfo(false, true, Set.of(PythonNodeType.FORMAT_EXPRESSION)));
        out.put(
                PythonNodeType.GENERATOR_EXPRESSION,
                new ChildInfo(true, true, Set.of(PythonNodeType.FOR_IN_CLAUSE, PythonNodeType.IF_CLAUSE)));
        out.put(
                PythonNodeType.GENERIC_TYPE,
                new ChildInfo(true, true, Set.of(PythonNodeType.IDENTIFIER, PythonNodeType.TYPE_PARAMETER)));
        out.put(PythonNodeType.GLOBAL_STATEMENT, new ChildInfo(true, true, Set.of(PythonNodeType.IDENTIFIER)));
        out.put(PythonNodeType.IF_CLAUSE, new ChildInfo(true, false, Set.of(PythonNodeType.EXPRESSION)));
        out.put(
                PythonNodeType.IMPORT_FROM_STATEMENT,
                new ChildInfo(false, false, Set.of(PythonNodeType.WILDCARD_IMPORT)));
        out.put(
                PythonNodeType.KEYWORD_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PythonNodeType.CLASS_PATTERN,
                                PythonNodeType.COMPLEX_PATTERN,
                                PythonNodeType.CONCATENATED_STRING,
                                PythonNodeType.DICT_PATTERN,
                                PythonNodeType.DOTTED_NAME,
                                PythonNodeType.FALSE,
                                PythonNodeType.FLOAT_,
                                PythonNodeType.IDENTIFIER,
                                PythonNodeType.INTEGER,
                                PythonNodeType.LIST_PATTERN,
                                PythonNodeType.NONE,
                                PythonNodeType.SPLAT_PATTERN,
                                PythonNodeType.STRING,
                                PythonNodeType.TRUE,
                                PythonNodeType.TUPLE_PATTERN,
                                PythonNodeType.UNION_PATTERN)));
        out.put(PythonNodeType.LAMBDA_PARAMETERS, new ChildInfo(true, true, Set.of(PythonNodeType.PARAMETER)));
        out.put(
                PythonNodeType.LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.LIST_SPLAT,
                                PythonNodeType.PARENTHESIZED_LIST_SPLAT,
                                PythonNodeType.YIELD_)));
        out.put(
                PythonNodeType.LIST_COMPREHENSION,
                new ChildInfo(true, true, Set.of(PythonNodeType.FOR_IN_CLAUSE, PythonNodeType.IF_CLAUSE)));
        out.put(
                PythonNodeType.LIST_PATTERN,
                new ChildInfo(false, true, Set.of(PythonNodeType.CASE_PATTERN, PythonNodeType.PATTERN)));
        out.put(
                PythonNodeType.LIST_SPLAT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                PythonNodeType.ATTRIBUTE,
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.IDENTIFIER,
                                PythonNodeType.SUBSCRIPT)));
        out.put(
                PythonNodeType.LIST_SPLAT_PATTERN,
                new ChildInfo(
                        true,
                        false,
                        Set.of(PythonNodeType.ATTRIBUTE, PythonNodeType.IDENTIFIER, PythonNodeType.SUBSCRIPT)));
        out.put(
                PythonNodeType.MEMBER_TYPE,
                new ChildInfo(true, true, Set.of(PythonNodeType.IDENTIFIER, PythonNodeType.TYPE)));
        out.put(
                PythonNodeType.MODULE,
                new ChildInfo(false, true, Set.of(PythonNodeType.COMPOUND_STATEMENT, PythonNodeType.SIMPLE_STATEMENT)));
        out.put(PythonNodeType.NONLOCAL_STATEMENT, new ChildInfo(true, true, Set.of(PythonNodeType.IDENTIFIER)));
        out.put(PythonNodeType.PARAMETERS, new ChildInfo(false, true, Set.of(PythonNodeType.PARAMETER)));
        out.put(
                PythonNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.LIST_SPLAT,
                                PythonNodeType.PARENTHESIZED_EXPRESSION,
                                PythonNodeType.YIELD_)));
        out.put(
                PythonNodeType.PARENTHESIZED_LIST_SPLAT,
                new ChildInfo(true, false, Set.of(PythonNodeType.LIST_SPLAT, PythonNodeType.PARENTHESIZED_EXPRESSION)));
        out.put(PythonNodeType.PATTERN_LIST, new ChildInfo(true, true, Set.of(PythonNodeType.PATTERN)));
        out.put(PythonNodeType.PRINT_STATEMENT, new ChildInfo(false, false, Set.of(PythonNodeType.CHEVRON)));
        out.put(
                PythonNodeType.RAISE_STATEMENT,
                new ChildInfo(false, false, Set.of(PythonNodeType.EXPRESSION, PythonNodeType.EXPRESSION_LIST)));
        out.put(
                PythonNodeType.RELATIVE_IMPORT,
                new ChildInfo(true, true, Set.of(PythonNodeType.DOTTED_NAME, PythonNodeType.IMPORT_PREFIX)));
        out.put(
                PythonNodeType.RETURN_STATEMENT,
                new ChildInfo(false, false, Set.of(PythonNodeType.EXPRESSION, PythonNodeType.EXPRESSION_LIST)));
        out.put(
                PythonNodeType.SET,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.LIST_SPLAT,
                                PythonNodeType.PARENTHESIZED_LIST_SPLAT,
                                PythonNodeType.YIELD_)));
        out.put(
                PythonNodeType.SET_COMPREHENSION,
                new ChildInfo(true, true, Set.of(PythonNodeType.FOR_IN_CLAUSE, PythonNodeType.IF_CLAUSE)));
        out.put(PythonNodeType.SLICE, new ChildInfo(false, true, Set.of(PythonNodeType.EXPRESSION)));
        out.put(PythonNodeType.SPLAT_PATTERN, new ChildInfo(false, false, Set.of(PythonNodeType.IDENTIFIER)));
        out.put(PythonNodeType.SPLAT_TYPE, new ChildInfo(true, false, Set.of(PythonNodeType.IDENTIFIER)));
        out.put(
                PythonNodeType.STRING,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PythonNodeType.INTERPOLATION,
                                PythonNodeType.STRING_CONTENT,
                                PythonNodeType.STRING_END,
                                PythonNodeType.STRING_START)));
        out.put(
                PythonNodeType.STRING_CONTENT,
                new ChildInfo(
                        false, true, Set.of(PythonNodeType.ESCAPE_INTERPOLATION, PythonNodeType.ESCAPE_SEQUENCE)));
        out.put(
                PythonNodeType.TRY_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PythonNodeType.ELSE_CLAUSE,
                                PythonNodeType.EXCEPT_CLAUSE,
                                PythonNodeType.FINALLY_CLAUSE)));
        out.put(
                PythonNodeType.TUPLE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.LIST_SPLAT,
                                PythonNodeType.PARENTHESIZED_LIST_SPLAT,
                                PythonNodeType.YIELD_)));
        out.put(
                PythonNodeType.TUPLE_PATTERN,
                new ChildInfo(false, true, Set.of(PythonNodeType.CASE_PATTERN, PythonNodeType.PATTERN)));
        out.put(
                PythonNodeType.TYPE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                PythonNodeType.CONSTRAINED_TYPE,
                                PythonNodeType.EXPRESSION,
                                PythonNodeType.GENERIC_TYPE,
                                PythonNodeType.MEMBER_TYPE,
                                PythonNodeType.SPLAT_TYPE,
                                PythonNodeType.UNION_TYPE)));
        out.put(
                PythonNodeType.TYPED_PARAMETER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                PythonNodeType.DICTIONARY_SPLAT_PATTERN,
                                PythonNodeType.IDENTIFIER,
                                PythonNodeType.LIST_SPLAT_PATTERN)));
        out.put(PythonNodeType.TYPE_PARAMETER, new ChildInfo(true, true, Set.of(PythonNodeType.TYPE)));
        out.put(
                PythonNodeType.UNION_PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PythonNodeType.CLASS_PATTERN,
                                PythonNodeType.COMPLEX_PATTERN,
                                PythonNodeType.CONCATENATED_STRING,
                                PythonNodeType.DICT_PATTERN,
                                PythonNodeType.DOTTED_NAME,
                                PythonNodeType.FALSE,
                                PythonNodeType.FLOAT_,
                                PythonNodeType.INTEGER,
                                PythonNodeType.LIST_PATTERN,
                                PythonNodeType.NONE,
                                PythonNodeType.SPLAT_PATTERN,
                                PythonNodeType.STRING,
                                PythonNodeType.TRUE,
                                PythonNodeType.TUPLE_PATTERN,
                                PythonNodeType.UNION_PATTERN)));
        out.put(PythonNodeType.UNION_TYPE, new ChildInfo(true, true, Set.of(PythonNodeType.TYPE)));
        out.put(PythonNodeType.WITH_CLAUSE, new ChildInfo(true, true, Set.of(PythonNodeType.WITH_ITEM)));
        out.put(PythonNodeType.WITH_STATEMENT, new ChildInfo(true, false, Set.of(PythonNodeType.WITH_CLAUSE)));
        out.put(
                PythonNodeType.YIELD_,
                new ChildInfo(false, false, Set.of(PythonNodeType.EXPRESSION, PythonNodeType.EXPRESSION_LIST)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<PythonNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<PythonNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<PythonNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<PythonNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
