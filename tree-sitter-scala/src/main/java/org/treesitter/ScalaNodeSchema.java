package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code scala} from tree-sitter {@code node-types.json}.
 */
public final class ScalaNodeSchema {
    private ScalaNodeSchema() {}

    public static Set<ScalaNodeField> fields(@Nullable ScalaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<ScalaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<ScalaNodeType> allowedTypes(@Nullable ScalaNodeType owner, @Nullable ScalaNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<ScalaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable ScalaNodeType owner, @Nullable ScalaNodeField field) {
        if (owner == null || field == null) return false;
        Map<ScalaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable ScalaNodeType owner, @Nullable ScalaNodeField field) {
        if (owner == null || field == null) return false;
        Map<ScalaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<ScalaNodeType> allowedChildTypes(@Nullable ScalaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable ScalaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable ScalaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<ScalaNodeType, Map<ScalaNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<ScalaNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<ScalaNodeType, Map<ScalaNodeField, FieldInfo>> initFields() {
        EnumMap<ScalaNodeType, Map<ScalaNodeField, FieldInfo>> out = new EnumMap<>(ScalaNodeType.class);
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.ARGUMENTS, new FieldInfo(false, true, Set.of(ScalaNodeType.ARGUMENTS)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.ANNOTATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.ALIAS,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            out.put(ScalaNodeType.ARROW_RENAMED_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.BLOCK,
                                    ScalaNodeType.BOOLEAN_LITERAL,
                                    ScalaNodeType.CALL_EXPRESSION,
                                    ScalaNodeType.CASE_BLOCK,
                                    ScalaNodeType.CHARACTER_LITERAL,
                                    ScalaNodeType.FIELD_EXPRESSION,
                                    ScalaNodeType.FLOATING_POINT_LITERAL,
                                    ScalaNodeType.GENERIC_FUNCTION,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.INSTANCE_EXPRESSION,
                                    ScalaNodeType.INTEGER_LITERAL,
                                    ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                    ScalaNodeType.NULL_LITERAL,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                    ScalaNodeType.PREFIX_EXPRESSION,
                                    ScalaNodeType.QUOTE_EXPRESSION,
                                    ScalaNodeType.SPLICE_EXPRESSION,
                                    ScalaNodeType.STRING,
                                    ScalaNodeType.TUPLE_EXPRESSION,
                                    ScalaNodeType.UNIT,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.RIGHT, new FieldInfo(true, false, Set.of(ScalaNodeType.EXPRESSION)));
            out.put(ScalaNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.ALIAS,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            out.put(ScalaNodeType.AS_RENAMED_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(false, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LAZY_PARAMETER_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.ARGUMENTS,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ARGUMENTS,
                                    ScalaNodeType.BLOCK,
                                    ScalaNodeType.CASE_BLOCK,
                                    ScalaNodeType.COLON_ARGUMENT)));
            m.put(
                    ScalaNodeField.FUNCTION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.BLOCK,
                                    ScalaNodeType.BOOLEAN_LITERAL,
                                    ScalaNodeType.CALL_EXPRESSION,
                                    ScalaNodeType.CASE_BLOCK,
                                    ScalaNodeType.CHARACTER_LITERAL,
                                    ScalaNodeType.FIELD_EXPRESSION,
                                    ScalaNodeType.FLOATING_POINT_LITERAL,
                                    ScalaNodeType.GENERIC_FUNCTION,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.INFIX_EXPRESSION,
                                    ScalaNodeType.INSTANCE_EXPRESSION,
                                    ScalaNodeType.INTEGER_LITERAL,
                                    ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                    ScalaNodeType.NULL_LITERAL,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                    ScalaNodeType.POSTFIX_EXPRESSION,
                                    ScalaNodeType.PREFIX_EXPRESSION,
                                    ScalaNodeType.QUOTE_EXPRESSION,
                                    ScalaNodeType.SPLICE_EXPRESSION,
                                    ScalaNodeType.STRING,
                                    ScalaNodeType.TUPLE_EXPRESSION,
                                    ScalaNodeType.UNIT,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.PATTERN, new FieldInfo(true, false, Set.of(ScalaNodeType.PATTERN)));
            out.put(ScalaNodeType.CAPTURE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.PATTERN,
                    new FieldInfo(false, true, Set.of(ScalaNodeType.NAMED_PATTERN, ScalaNodeType.PATTERN)));
            m.put(ScalaNodeField.TYPE, new FieldInfo(true, false, Set.of(ScalaNodeType.STABLE_TYPE_IDENTIFIER)));
            out.put(ScalaNodeType.CASE_CLASS_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(false, true, Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION)));
            m.put(ScalaNodeField.PATTERN, new FieldInfo(true, false, Set.of(ScalaNodeType.PATTERN)));
            out.put(ScalaNodeType.CASE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(false, false, Set.of(ScalaNodeType.EXPRESSION)));
            m.put(ScalaNodeField.PATTERN, new FieldInfo(false, false, Set.of(ScalaNodeType.PATTERN)));
            out.put(ScalaNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(false, true, Set.of(ScalaNodeType.TEMPLATE_BODY)));
            m.put(ScalaNodeField.CLASS_PARAMETERS, new FieldInfo(false, true, Set.of(ScalaNodeType.CLASS_PARAMETERS)));
            m.put(ScalaNodeField.DERIVE, new FieldInfo(false, false, Set.of(ScalaNodeType.DERIVES_CLAUSE)));
            m.put(ScalaNodeField.EXTEND, new FieldInfo(false, false, Set.of(ScalaNodeType.EXTENDS_CLAUSE)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.CLASS_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.DEFAULT_VALUE, new FieldInfo(false, false, Set.of(ScalaNodeType.EXPRESSION)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LAZY_PARAMETER_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.CLASS_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.LAMBDA_START,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ScalaNodeType.BINDINGS,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.COLON_ARGUMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BASE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.EXTRA,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.COMPOUND_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(false, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.CONTEXT_BOUND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BOUND,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ScalaNodeType.CONTEXT_BOUND,
                                    ScalaNodeType.LOWER_BOUND,
                                    ScalaNodeType.UPPER_BOUND,
                                    ScalaNodeType.VIEW_BOUND)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.CONTRAVARIANT_TYPE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BOUND,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ScalaNodeType.CONTEXT_BOUND,
                                    ScalaNodeType.LOWER_BOUND,
                                    ScalaNodeType.UPPER_BOUND,
                                    ScalaNodeType.VIEW_BOUND)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.COVARIANT_TYPE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.TYPE, new FieldInfo(true, true, Set.of(ScalaNodeType.STABLE_TYPE_IDENTIFIER)));
            out.put(ScalaNodeType.DERIVES_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(true, false, Set.of(ScalaNodeType.EXPRESSION)));
            m.put(ScalaNodeField.CONDITION, new FieldInfo(true, false, Set.of(ScalaNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(ScalaNodeType.DO_WHILE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(true, false, Set.of(ScalaNodeType.ENUM_BODY)));
            m.put(ScalaNodeField.CLASS_PARAMETERS, new FieldInfo(false, true, Set.of(ScalaNodeType.CLASS_PARAMETERS)));
            m.put(ScalaNodeField.DERIVE, new FieldInfo(false, false, Set.of(ScalaNodeType.DERIVES_CLAUSE)));
            m.put(ScalaNodeField.EXTEND, new FieldInfo(false, false, Set.of(ScalaNodeType.EXTENDS_CLAUSE)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.ENUM_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.PATH,
                    new FieldInfo(true, true, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            out.put(ScalaNodeType.EXPORT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.ARGUMENTS, new FieldInfo(false, true, Set.of(ScalaNodeType.ARGUMENTS)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.ARGUMENTS,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.EXTENDS_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(true, true, Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION)));
            m.put(ScalaNodeField.PARAMETERS, new FieldInfo(false, true, Set.of(ScalaNodeType.PARAMETERS)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.EXTENSION_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.FIELD,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.BLOCK,
                                    ScalaNodeType.BOOLEAN_LITERAL,
                                    ScalaNodeType.CALL_EXPRESSION,
                                    ScalaNodeType.CASE_BLOCK,
                                    ScalaNodeType.CHARACTER_LITERAL,
                                    ScalaNodeType.FIELD_EXPRESSION,
                                    ScalaNodeType.FLOATING_POINT_LITERAL,
                                    ScalaNodeType.GENERIC_FUNCTION,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.INSTANCE_EXPRESSION,
                                    ScalaNodeType.INTEGER_LITERAL,
                                    ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                    ScalaNodeType.NULL_LITERAL,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                    ScalaNodeType.QUOTE_EXPRESSION,
                                    ScalaNodeType.SPLICE_EXPRESSION,
                                    ScalaNodeType.STRING,
                                    ScalaNodeType.TUPLE_EXPRESSION,
                                    ScalaNodeType.UNIT,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.FIELD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            m.put(ScalaNodeField.ENUMERATORS, new FieldInfo(true, true, Set.of(ScalaNodeType.ENUMERATORS)));
            out.put(ScalaNodeType.FOR_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.CLASS_PARAMETERS, new FieldInfo(true, true, Set.of(ScalaNodeType.CLASS_PARAMETERS)));
            m.put(ScalaNodeField.EXTEND, new FieldInfo(false, false, Set.of(ScalaNodeType.EXTENDS_CLAUSE)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.FULL_ENUM_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.PARAMETERS,
                    new FieldInfo(false, true, Set.of(ScalaNodeType.PARAMETERS, ScalaNodeType.TYPE_PARAMETERS)));
            m.put(
                    ScalaNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.PARAMETERS,
                    new FieldInfo(false, true, Set.of(ScalaNodeType.PARAMETERS, ScalaNodeType.TYPE_PARAMETERS)));
            m.put(
                    ScalaNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.FUNCTION_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.PARAMETER_TYPES, new FieldInfo(false, false, Set.of(ScalaNodeType.PARAMETER_TYPES)));
            m.put(
                    ScalaNodeField.RETURN_TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.FUNCTION_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.FUNCTION, new FieldInfo(true, false, Set.of(ScalaNodeType.EXPRESSION)));
            m.put(ScalaNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(ScalaNodeType.TYPE_ARGUMENTS)));
            out.put(ScalaNodeType.GENERIC_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(ScalaNodeType.TYPE_ARGUMENTS)));
            out.put(ScalaNodeType.GENERIC_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(ScalaNodeType.ARGUMENTS)));
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES,
                                    ScalaNodeType.WITH_TEMPLATE_BODY)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(false, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(ScalaNodeField.PARAMETERS, new FieldInfo(false, true, Set.of(ScalaNodeType.PARAMETERS)));
            m.put(
                    ScalaNodeField.RETURN_TYPE,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.ARGUMENTS,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD,
                                    ScalaNodeType.WITH_TEMPLATE_BODY)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.GIVEN_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.GIVEN_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.BLOCK,
                                    ScalaNodeType.BOOLEAN_LITERAL,
                                    ScalaNodeType.CALL_EXPRESSION,
                                    ScalaNodeType.CASE_BLOCK,
                                    ScalaNodeType.CHARACTER_LITERAL,
                                    ScalaNodeType.FIELD_EXPRESSION,
                                    ScalaNodeType.FLOATING_POINT_LITERAL,
                                    ScalaNodeType.GENERIC_FUNCTION,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.INFIX_EXPRESSION,
                                    ScalaNodeType.INSTANCE_EXPRESSION,
                                    ScalaNodeType.INTEGER_LITERAL,
                                    ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                    ScalaNodeType.NULL_LITERAL,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                    ScalaNodeType.POSTFIX_EXPRESSION,
                                    ScalaNodeType.PREFIX_EXPRESSION,
                                    ScalaNodeType.QUOTE_EXPRESSION,
                                    ScalaNodeType.SPLICE_EXPRESSION,
                                    ScalaNodeType.STRING,
                                    ScalaNodeType.TUPLE_EXPRESSION,
                                    ScalaNodeType.UNIT,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.GUARD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            m.put(
                    ScalaNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            m.put(
                    ScalaNodeField.CONSEQUENCE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            out.put(ScalaNodeType.IF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.PATH,
                    new FieldInfo(true, true, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            out.put(ScalaNodeType.IMPORT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.BLOCK,
                                    ScalaNodeType.BOOLEAN_LITERAL,
                                    ScalaNodeType.CALL_EXPRESSION,
                                    ScalaNodeType.CASE_BLOCK,
                                    ScalaNodeType.CHARACTER_LITERAL,
                                    ScalaNodeType.FIELD_EXPRESSION,
                                    ScalaNodeType.FLOATING_POINT_LITERAL,
                                    ScalaNodeType.GENERIC_FUNCTION,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.INFIX_EXPRESSION,
                                    ScalaNodeType.INSTANCE_EXPRESSION,
                                    ScalaNodeType.INTEGER_LITERAL,
                                    ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                    ScalaNodeType.NULL_LITERAL,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                    ScalaNodeType.PREFIX_EXPRESSION,
                                    ScalaNodeType.QUOTE_EXPRESSION,
                                    ScalaNodeType.SPLICE_EXPRESSION,
                                    ScalaNodeType.STRING,
                                    ScalaNodeType.TUPLE_EXPRESSION,
                                    ScalaNodeType.UNIT,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.OPERATOR,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.BLOCK,
                                    ScalaNodeType.BOOLEAN_LITERAL,
                                    ScalaNodeType.CALL_EXPRESSION,
                                    ScalaNodeType.CASE_BLOCK,
                                    ScalaNodeType.CHARACTER_LITERAL,
                                    ScalaNodeType.COLON_ARGUMENT,
                                    ScalaNodeType.FIELD_EXPRESSION,
                                    ScalaNodeType.FLOATING_POINT_LITERAL,
                                    ScalaNodeType.GENERIC_FUNCTION,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.INSTANCE_EXPRESSION,
                                    ScalaNodeType.INTEGER_LITERAL,
                                    ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                    ScalaNodeType.NULL_LITERAL,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                    ScalaNodeType.PREFIX_EXPRESSION,
                                    ScalaNodeType.QUOTE_EXPRESSION,
                                    ScalaNodeType.SPLICE_EXPRESSION,
                                    ScalaNodeType.STRING,
                                    ScalaNodeType.TUPLE_EXPRESSION,
                                    ScalaNodeType.UNIT,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.INFIX_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.LEFT, new FieldInfo(true, false, Set.of(ScalaNodeType.PATTERN)));
            m.put(
                    ScalaNodeField.OPERATOR,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(ScalaNodeField.RIGHT, new FieldInfo(true, false, Set.of(ScalaNodeType.PATTERN)));
            out.put(ScalaNodeType.INFIX_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.OPERATOR,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.INFIX_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(ScalaNodeType.ARGUMENTS)));
            out.put(ScalaNodeType.INSTANCE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.INTERPOLATOR, new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER)));
            out.put(ScalaNodeType.INTERPOLATED_STRING_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.PARAMETERS,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.BINDINGS,
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.LAMBDA_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.LAZY_PARAMETER_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.LOWER_BOUND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.CASE_BLOCK, ScalaNodeType.INDENTED_CASES)));
            m.put(ScalaNodeField.VALUE, new FieldInfo(true, false, Set.of(ScalaNodeType.EXPRESSION)));
            out.put(ScalaNodeType.MATCH_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LAZY_PARAMETER_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.NAME_AND_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(false, true, Set.of(ScalaNodeType.TEMPLATE_BODY)));
            m.put(ScalaNodeField.DERIVE, new FieldInfo(false, false, Set.of(ScalaNodeType.DERIVES_CLAUSE)));
            m.put(ScalaNodeField.EXTEND, new FieldInfo(false, false, Set.of(ScalaNodeType.EXTENDS_CLAUSE)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            out.put(ScalaNodeType.OBJECT_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(false, false, Set.of(ScalaNodeType.TEMPLATE_BODY)));
            m.put(ScalaNodeField.NAME, new FieldInfo(true, false, Set.of(ScalaNodeType.PACKAGE_IDENTIFIER)));
            out.put(ScalaNodeType.PACKAGE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(false, true, Set.of(ScalaNodeType.TEMPLATE_BODY)));
            m.put(ScalaNodeField.DERIVE, new FieldInfo(false, false, Set.of(ScalaNodeType.DERIVES_CLAUSE)));
            m.put(ScalaNodeField.EXTEND, new FieldInfo(false, false, Set.of(ScalaNodeType.EXTENDS_CLAUSE)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            out.put(ScalaNodeType.PACKAGE_OBJECT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.DEFAULT_VALUE, new FieldInfo(false, false, Set.of(ScalaNodeType.EXPRESSION)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LAZY_PARAMETER_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.SELECTOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.PROJECTED_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.REPEATED_PARAMETER_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.PATTERN, new FieldInfo(true, false, Set.of(ScalaNodeType.PATTERN)));
            out.put(ScalaNodeType.REPEAT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.EXTEND, new FieldInfo(false, false, Set.of(ScalaNodeType.EXTENDS_CLAUSE)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            out.put(ScalaNodeType.SIMPLE_ENUM_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.BODY, new FieldInfo(false, true, Set.of(ScalaNodeType.TEMPLATE_BODY)));
            m.put(ScalaNodeField.CLASS_PARAMETERS, new FieldInfo(false, true, Set.of(ScalaNodeType.CLASS_PARAMETERS)));
            m.put(ScalaNodeField.DERIVE, new FieldInfo(false, false, Set.of(ScalaNodeType.DERIVES_CLAUSE)));
            m.put(ScalaNodeField.EXTEND, new FieldInfo(false, false, Set.of(ScalaNodeType.EXTENDS_CLAUSE)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.TRAIT_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            out.put(ScalaNodeType.TRY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(ScalaNodeField.PATTERN, new FieldInfo(true, false, Set.of(ScalaNodeType.PATTERN)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.TYPED_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.RETURN_TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.TYPE_CASE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BOUND,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(ScalaNodeType.CONTEXT_BOUND, ScalaNodeType.LOWER_BOUND, ScalaNodeType.UPPER_BOUND)));
            m.put(ScalaNodeField.NAME, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.TYPE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BOUND,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ScalaNodeType.CONTEXT_BOUND,
                                    ScalaNodeType.LOWER_BOUND,
                                    ScalaNodeType.UPPER_BOUND,
                                    ScalaNodeType.VIEW_BOUND)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.RETURN_TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, true, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.TYPE_LAMBDA, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BOUND,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ScalaNodeType.CONTEXT_BOUND,
                                    ScalaNodeType.LOWER_BOUND,
                                    ScalaNodeType.UPPER_BOUND,
                                    ScalaNodeType.VIEW_BOUND)));
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    ScalaNodeType.IDENTIFIER,
                                    ScalaNodeType.OPERATOR_IDENTIFIER,
                                    ScalaNodeType.WILDCARD)));
            m.put(ScalaNodeField.TYPE_PARAMETERS, new FieldInfo(false, true, Set.of(ScalaNodeType.TYPE_PARAMETERS)));
            out.put(ScalaNodeType.TYPE_PARAMETERS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.UPPER_BOUND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, true, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.VAL_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIERS, ScalaNodeType.PATTERN)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            out.put(ScalaNodeType.VAL_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.NAME,
                    new FieldInfo(true, true, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.VAR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(ScalaNodeType.IDENTIFIERS, ScalaNodeType.PATTERN)));
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            m.put(
                    ScalaNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            out.put(ScalaNodeType.VAR_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.ANNOTATED_TYPE,
                                    ScalaNodeType.COMPOUND_TYPE,
                                    ScalaNodeType.FUNCTION_TYPE,
                                    ScalaNodeType.GENERIC_TYPE,
                                    ScalaNodeType.INFIX_TYPE,
                                    ScalaNodeType.LITERAL_TYPE,
                                    ScalaNodeType.MATCH_TYPE,
                                    ScalaNodeType.NAMED_TUPLE_TYPE,
                                    ScalaNodeType.PROJECTED_TYPE,
                                    ScalaNodeType.SINGLETON_TYPE,
                                    ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                    ScalaNodeType.STRUCTURAL_TYPE,
                                    ScalaNodeType.TUPLE_TYPE,
                                    ScalaNodeType.TYPE_LAMBDA,
                                    ScalaNodeType.WILDCARD)));
            out.put(ScalaNodeType.VIEW_BOUND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<ScalaNodeField, FieldInfo> m = new EnumMap<>(ScalaNodeField.class);
            m.put(
                    ScalaNodeField.BODY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            m.put(
                    ScalaNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    ScalaNodeType.EXPRESSION,
                                    ScalaNodeType.INDENTED_BLOCK,
                                    ScalaNodeType.INDENTED_CASES)));
            out.put(ScalaNodeType.WHILE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<ScalaNodeType, ChildInfo> initChildren() {
        EnumMap<ScalaNodeType, ChildInfo> out = new EnumMap<>(ScalaNodeType.class);
        out.put(ScalaNodeType.ACCESS_MODIFIER, new ChildInfo(false, false, Set.of(ScalaNodeType.ACCESS_QUALIFIER)));
        out.put(
                ScalaNodeType.ACCESS_QUALIFIER,
                new ChildInfo(true, false, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
        out.put(ScalaNodeType.ALTERNATIVE_PATTERN, new ChildInfo(true, true, Set.of(ScalaNodeType.PATTERN)));
        out.put(
                ScalaNodeType.ANNOTATED_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATION,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.WILDCARD)));
        out.put(ScalaNodeType.ARGUMENTS, new ChildInfo(false, true, Set.of(ScalaNodeType.EXPRESSION)));
        out.put(
                ScalaNodeType.ASCRIPTION_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.ANNOTATION,
                                ScalaNodeType.BLOCK,
                                ScalaNodeType.BOOLEAN_LITERAL,
                                ScalaNodeType.CALL_EXPRESSION,
                                ScalaNodeType.CASE_BLOCK,
                                ScalaNodeType.CHARACTER_LITERAL,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FIELD_EXPRESSION,
                                ScalaNodeType.FLOATING_POINT_LITERAL,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_FUNCTION,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.INFIX_EXPRESSION,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.INSTANCE_EXPRESSION,
                                ScalaNodeType.INTEGER_LITERAL,
                                ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                ScalaNodeType.LAZY_PARAMETER_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.NULL_LITERAL,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                ScalaNodeType.POSTFIX_EXPRESSION,
                                ScalaNodeType.PREFIX_EXPRESSION,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.QUOTE_EXPRESSION,
                                ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.SPLICE_EXPRESSION,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRING,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_EXPRESSION,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.UNIT,
                                ScalaNodeType.WILDCARD)));
        out.put(ScalaNodeType.BINDING, new ChildInfo(false, false, Set.of(ScalaNodeType.WILDCARD)));
        out.put(ScalaNodeType.BINDINGS, new ChildInfo(false, true, Set.of(ScalaNodeType.BINDING)));
        out.put(
                ScalaNodeType.BLOCK,
                new ChildInfo(false, true, Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION)));
        out.put(ScalaNodeType.CASE_BLOCK, new ChildInfo(false, true, Set.of(ScalaNodeType.CASE_CLAUSE)));
        out.put(ScalaNodeType.CASE_CLAUSE, new ChildInfo(false, false, Set.of(ScalaNodeType.GUARD)));
        out.put(
                ScalaNodeType.CATCH_CLAUSE,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                ScalaNodeType.EXPRESSION,
                                ScalaNodeType.GUARD,
                                ScalaNodeType.INDENTED_BLOCK,
                                ScalaNodeType.INDENTED_CASES)));
        out.put(
                ScalaNodeType.CLASS_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.ACCESS_MODIFIER, ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.CLASS_PARAMETER,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(ScalaNodeType.CLASS_PARAMETERS, new ChildInfo(false, true, Set.of(ScalaNodeType.CLASS_PARAMETER)));
        out.put(
                ScalaNodeType.COLON_ARGUMENT,
                new ChildInfo(true, false, Set.of(ScalaNodeType.INDENTED_BLOCK, ScalaNodeType.INDENTED_CASES)));
        out.put(ScalaNodeType.COMMENT, new ChildInfo(false, false, Set.of(ScalaNodeType.USING_DIRECTIVE)));
        out.put(
                ScalaNodeType.COMPILATION_UNIT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.COMMENT, ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION)));
        out.put(ScalaNodeType.COMPOUND_TYPE, new ChildInfo(false, false, Set.of(ScalaNodeType.REFINEMENT)));
        out.put(
                ScalaNodeType.ENUMERATOR,
                new ChildInfo(
                        true, true, Set.of(ScalaNodeType.EXPRESSION, ScalaNodeType.GUARD, ScalaNodeType.PATTERN)));
        out.put(ScalaNodeType.ENUMERATORS, new ChildInfo(true, true, Set.of(ScalaNodeType.ENUMERATOR)));
        out.put(
                ScalaNodeType.ENUM_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.DEFINITION,
                                ScalaNodeType.ENUM_CASE_DEFINITIONS,
                                ScalaNodeType.EXPRESSION)));
        out.put(
                ScalaNodeType.ENUM_CASE_DEFINITIONS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATION,
                                ScalaNodeType.FULL_ENUM_CASE,
                                ScalaNodeType.SIMPLE_ENUM_CASE)));
        out.put(
                ScalaNodeType.ENUM_DEFINITION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ACCESS_MODIFIER, ScalaNodeType.ANNOTATION)));
        out.put(
                ScalaNodeType.EXPORT_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.AS_RENAMED_IDENTIFIER,
                                ScalaNodeType.NAMESPACE_SELECTORS,
                                ScalaNodeType.NAMESPACE_WILDCARD)));
        out.put(ScalaNodeType.EXTENDS_CLAUSE, new ChildInfo(false, false, Set.of(ScalaNodeType.ARGUMENTS)));
        out.put(
                ScalaNodeType.FINALLY_CLAUSE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(ScalaNodeType.EXPRESSION, ScalaNodeType.INDENTED_BLOCK, ScalaNodeType.INDENTED_CASES)));
        out.put(
                ScalaNodeType.FUNCTION_DECLARATION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.FUNCTION_DEFINITION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.GIVEN_CONDITIONAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LAZY_PARAMETER_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PARAMETER,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.GIVEN_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.GIVEN_CONDITIONAL, ScalaNodeType.MODIFIERS)));
        out.put(ScalaNodeType.IDENTIFIERS, new ChildInfo(true, true, Set.of(ScalaNodeType.IDENTIFIER)));
        out.put(ScalaNodeType.IF_EXPRESSION, new ChildInfo(false, false, Set.of(ScalaNodeType.INLINE_MODIFIER)));
        out.put(
                ScalaNodeType.IMPORT_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.AS_RENAMED_IDENTIFIER,
                                ScalaNodeType.NAMESPACE_SELECTORS,
                                ScalaNodeType.NAMESPACE_WILDCARD)));
        out.put(
                ScalaNodeType.INDENTED_BLOCK,
                new ChildInfo(false, true, Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION)));
        out.put(ScalaNodeType.INDENTED_CASES, new ChildInfo(true, true, Set.of(ScalaNodeType.CASE_CLAUSE)));
        out.put(
                ScalaNodeType.INSTANCE_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TEMPLATE_BODY,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.INTERPOLATED_STRING,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ESCAPE_SEQUENCE, ScalaNodeType.INTERPOLATION)));
        out.put(
                ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                new ChildInfo(true, false, Set.of(ScalaNodeType.INTERPOLATED_STRING)));
        out.put(
                ScalaNodeType.INTERPOLATION,
                new ChildInfo(true, false, Set.of(ScalaNodeType.BLOCK, ScalaNodeType.IDENTIFIER)));
        out.put(
                ScalaNodeType.LAMBDA_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(ScalaNodeType.EXPRESSION, ScalaNodeType.INDENTED_BLOCK, ScalaNodeType.INDENTED_CASES)));
        out.put(
                ScalaNodeType.LITERAL_TYPE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                ScalaNodeType.BOOLEAN_LITERAL,
                                ScalaNodeType.CHARACTER_LITERAL,
                                ScalaNodeType.FLOATING_POINT_LITERAL,
                                ScalaNodeType.INTEGER_LITERAL,
                                ScalaNodeType.STRING)));
        out.put(
                ScalaNodeType.MACRO_BODY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                ScalaNodeType.BLOCK,
                                ScalaNodeType.BOOLEAN_LITERAL,
                                ScalaNodeType.CALL_EXPRESSION,
                                ScalaNodeType.CASE_BLOCK,
                                ScalaNodeType.CHARACTER_LITERAL,
                                ScalaNodeType.FIELD_EXPRESSION,
                                ScalaNodeType.FLOATING_POINT_LITERAL,
                                ScalaNodeType.GENERIC_FUNCTION,
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.INFIX_EXPRESSION,
                                ScalaNodeType.INSTANCE_EXPRESSION,
                                ScalaNodeType.INTEGER_LITERAL,
                                ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                ScalaNodeType.NULL_LITERAL,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                ScalaNodeType.PREFIX_EXPRESSION,
                                ScalaNodeType.QUOTE_EXPRESSION,
                                ScalaNodeType.SPLICE_EXPRESSION,
                                ScalaNodeType.STRING,
                                ScalaNodeType.TUPLE_EXPRESSION,
                                ScalaNodeType.UNIT,
                                ScalaNodeType.WILDCARD)));
        out.put(ScalaNodeType.MATCH_EXPRESSION, new ChildInfo(false, false, Set.of(ScalaNodeType.INLINE_MODIFIER)));
        out.put(
                ScalaNodeType.MATCH_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_CASE_CLAUSE,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.MODIFIERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ACCESS_MODIFIER,
                                ScalaNodeType.INFIX_MODIFIER,
                                ScalaNodeType.INLINE_MODIFIER,
                                ScalaNodeType.OPEN_MODIFIER,
                                ScalaNodeType.TRANSPARENT_MODIFIER)));
        out.put(ScalaNodeType.NAMED_PATTERN, new ChildInfo(true, true, Set.of(ScalaNodeType.PATTERN)));
        out.put(ScalaNodeType.NAMED_TUPLE_PATTERN, new ChildInfo(true, true, Set.of(ScalaNodeType.NAMED_PATTERN)));
        out.put(ScalaNodeType.NAMED_TUPLE_TYPE, new ChildInfo(true, true, Set.of(ScalaNodeType.NAME_AND_TYPE)));
        out.put(
                ScalaNodeType.NAMESPACE_SELECTORS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.ARROW_RENAMED_IDENTIFIER,
                                ScalaNodeType.AS_RENAMED_IDENTIFIER,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.NAMESPACE_WILDCARD,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.OBJECT_DEFINITION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.PACKAGE_IDENTIFIER,
                new ChildInfo(true, true, Set.of(ScalaNodeType.IDENTIFIER, ScalaNodeType.OPERATOR_IDENTIFIER)));
        out.put(
                ScalaNodeType.PARAMETER,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.INLINE_MODIFIER)));
        out.put(
                ScalaNodeType.PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LAZY_PARAMETER_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PARAMETER,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.PARAMETER_TYPES,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LAZY_PARAMETER_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.REPEATED_PARAMETER_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.WILDCARD)));
        out.put(ScalaNodeType.PARENTHESIZED_EXPRESSION, new ChildInfo(true, false, Set.of(ScalaNodeType.EXPRESSION)));
        out.put(
                ScalaNodeType.POSTFIX_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.BLOCK,
                                ScalaNodeType.BOOLEAN_LITERAL,
                                ScalaNodeType.CALL_EXPRESSION,
                                ScalaNodeType.CASE_BLOCK,
                                ScalaNodeType.CHARACTER_LITERAL,
                                ScalaNodeType.FIELD_EXPRESSION,
                                ScalaNodeType.FLOATING_POINT_LITERAL,
                                ScalaNodeType.GENERIC_FUNCTION,
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.INFIX_EXPRESSION,
                                ScalaNodeType.INSTANCE_EXPRESSION,
                                ScalaNodeType.INTEGER_LITERAL,
                                ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                ScalaNodeType.NULL_LITERAL,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                ScalaNodeType.PREFIX_EXPRESSION,
                                ScalaNodeType.QUOTE_EXPRESSION,
                                ScalaNodeType.SPLICE_EXPRESSION,
                                ScalaNodeType.STRING,
                                ScalaNodeType.TUPLE_EXPRESSION,
                                ScalaNodeType.UNIT,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.PREFIX_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                ScalaNodeType.BLOCK,
                                ScalaNodeType.BOOLEAN_LITERAL,
                                ScalaNodeType.CALL_EXPRESSION,
                                ScalaNodeType.CASE_BLOCK,
                                ScalaNodeType.CHARACTER_LITERAL,
                                ScalaNodeType.FIELD_EXPRESSION,
                                ScalaNodeType.FLOATING_POINT_LITERAL,
                                ScalaNodeType.GENERIC_FUNCTION,
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.INSTANCE_EXPRESSION,
                                ScalaNodeType.INTEGER_LITERAL,
                                ScalaNodeType.INTERPOLATED_STRING_EXPRESSION,
                                ScalaNodeType.NULL_LITERAL,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.PARENTHESIZED_EXPRESSION,
                                ScalaNodeType.QUOTE_EXPRESSION,
                                ScalaNodeType.SPLICE_EXPRESSION,
                                ScalaNodeType.STRING,
                                ScalaNodeType.TUPLE_EXPRESSION,
                                ScalaNodeType.UNIT,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.QUOTE_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.DEFINITION,
                                ScalaNodeType.EXPRESSION,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA)));
        out.put(
                ScalaNodeType.REFINEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION, ScalaNodeType.SELF_TYPE)));
        out.put(ScalaNodeType.RETURN_EXPRESSION, new ChildInfo(false, false, Set.of(ScalaNodeType.EXPRESSION)));
        out.put(
                ScalaNodeType.SELF_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.SINGLETON_TYPE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.STABLE_IDENTIFIER)));
        out.put(
                ScalaNodeType.SPLICE_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.DEFINITION,
                                ScalaNodeType.EXPRESSION,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA)));
        out.put(
                ScalaNodeType.STABLE_IDENTIFIER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.STABLE_IDENTIFIER)));
        out.put(
                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.IDENTIFIER,
                                ScalaNodeType.OPERATOR_IDENTIFIER,
                                ScalaNodeType.STABLE_IDENTIFIER)));
        out.put(ScalaNodeType.STRING, new ChildInfo(false, true, Set.of(ScalaNodeType.ESCAPE_SEQUENCE)));
        out.put(
                ScalaNodeType.STRUCTURAL_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION, ScalaNodeType.SELF_TYPE)));
        out.put(
                ScalaNodeType.TEMPLATE_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION, ScalaNodeType.SELF_TYPE)));
        out.put(ScalaNodeType.THROW_EXPRESSION, new ChildInfo(true, false, Set.of(ScalaNodeType.EXPRESSION)));
        out.put(
                ScalaNodeType.TRAIT_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.ACCESS_MODIFIER, ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.TRY_EXPRESSION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.CATCH_CLAUSE, ScalaNodeType.FINALLY_CLAUSE)));
        out.put(ScalaNodeType.TUPLE_EXPRESSION, new ChildInfo(true, true, Set.of(ScalaNodeType.EXPRESSION)));
        out.put(ScalaNodeType.TUPLE_PATTERN, new ChildInfo(true, true, Set.of(ScalaNodeType.PATTERN)));
        out.put(
                ScalaNodeType.TUPLE_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.TYPE_ARGUMENTS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.FUNCTION_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.MATCH_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.STRUCTURAL_TYPE,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.TYPE_LAMBDA,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.TYPE_CASE_CLAUSE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                ScalaNodeType.ANNOTATED_TYPE,
                                ScalaNodeType.COMPOUND_TYPE,
                                ScalaNodeType.GENERIC_TYPE,
                                ScalaNodeType.INFIX_TYPE,
                                ScalaNodeType.LITERAL_TYPE,
                                ScalaNodeType.NAMED_TUPLE_TYPE,
                                ScalaNodeType.PROJECTED_TYPE,
                                ScalaNodeType.SINGLETON_TYPE,
                                ScalaNodeType.STABLE_TYPE_IDENTIFIER,
                                ScalaNodeType.TUPLE_TYPE,
                                ScalaNodeType.WILDCARD)));
        out.put(
                ScalaNodeType.TYPE_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS, ScalaNodeType.OPAQUE_MODIFIER)));
        out.put(
                ScalaNodeType.TYPE_PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                ScalaNodeType.ANNOTATION,
                                ScalaNodeType.CONTRAVARIANT_TYPE_PARAMETER,
                                ScalaNodeType.COVARIANT_TYPE_PARAMETER,
                                ScalaNodeType.TYPE_LAMBDA)));
        out.put(
                ScalaNodeType.USING_DIRECTIVE,
                new ChildInfo(
                        true, true, Set.of(ScalaNodeType.USING_DIRECTIVE_KEY, ScalaNodeType.USING_DIRECTIVE_VALUE)));
        out.put(
                ScalaNodeType.VAL_DECLARATION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.VAL_DEFINITION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.VAR_DECLARATION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.VAR_DEFINITION,
                new ChildInfo(false, true, Set.of(ScalaNodeType.ANNOTATION, ScalaNodeType.MODIFIERS)));
        out.put(
                ScalaNodeType.WITH_TEMPLATE_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(ScalaNodeType.DEFINITION, ScalaNodeType.EXPRESSION, ScalaNodeType.SELF_TYPE)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<ScalaNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<ScalaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<ScalaNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<ScalaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
