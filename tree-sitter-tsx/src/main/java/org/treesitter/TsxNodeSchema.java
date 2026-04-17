package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code tsx} from tree-sitter {@code node-types.json}.
 */
public final class TsxNodeSchema {
    private TsxNodeSchema() {}

    public static Set<TsxNodeField> fields(@Nullable TsxNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<TsxNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<TsxNodeType> allowedTypes(@Nullable TsxNodeType owner, @Nullable TsxNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<TsxNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable TsxNodeType owner, @Nullable TsxNodeField field) {
        if (owner == null || field == null) return false;
        Map<TsxNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable TsxNodeType owner, @Nullable TsxNodeField field) {
        if (owner == null || field == null) return false;
        Map<TsxNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<TsxNodeType> allowedChildTypes(@Nullable TsxNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable TsxNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable TsxNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<TsxNodeType, Map<TsxNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<TsxNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<TsxNodeType, Map<TsxNodeField, FieldInfo>> initFields() {
        EnumMap<TsxNodeType, Map<TsxNodeField, FieldInfo>> out = new EnumMap<>(TsxNodeType.class);
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.CLASS_BODY)));
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.ABSTRACT_CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.ABSTRACT_METHOD_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.BODY,
                    new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.STATEMENT_BLOCK)));
            m.put(TsxNodeField.PARAMETER, new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.ARROW_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.ARRAY_PATTERN,
                                    TsxNodeType.IDENTIFIER,
                                    TsxNodeType.MEMBER_EXPRESSION,
                                    TsxNodeType.NON_NULL_EXPRESSION,
                                    TsxNodeType.OBJECT_PATTERN,
                                    TsxNodeType.PARENTHESIZED_EXPRESSION,
                                    TsxNodeType.SUBSCRIPT_EXPRESSION,
                                    TsxNodeType.UNDEFINED)));
            m.put(TsxNodeField.RIGHT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.LEFT, new FieldInfo(true, false, Set.of(TsxNodeType.PATTERN)));
            m.put(TsxNodeField.RIGHT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.ASSIGNMENT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.IDENTIFIER,
                                    TsxNodeType.MEMBER_EXPRESSION,
                                    TsxNodeType.NON_NULL_EXPRESSION,
                                    TsxNodeType.PARENTHESIZED_EXPRESSION,
                                    TsxNodeType.SUBSCRIPT_EXPRESSION)));
            m.put(TsxNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(TsxNodeField.RIGHT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.AUGMENTED_ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.LEFT,
                    new FieldInfo(
                            true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER)));
            m.put(TsxNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(TsxNodeField.RIGHT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.LABEL, new FieldInfo(false, false, Set.of(TsxNodeType.STATEMENT_IDENTIFIER)));
            out.put(TsxNodeType.BREAK_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.ARGUMENTS,
                    new FieldInfo(true, false, Set.of(TsxNodeType.ARGUMENTS, TsxNodeType.TEMPLATE_STRING)));
            m.put(
                    TsxNodeField.FUNCTION,
                    new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.IMPORT_)));
            m.put(TsxNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ARGUMENTS)));
            out.put(TsxNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.CALL_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(
                    TsxNodeField.PARAMETER,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(TsxNodeType.ARRAY_PATTERN, TsxNodeType.IDENTIFIER, TsxNodeType.OBJECT_PATTERN)));
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            out.put(TsxNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.CLASS_BODY)));
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            m.put(TsxNodeField.NAME, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.CLASS_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            out.put(TsxNodeType.CLASS_BODY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.CLASS_BODY)));
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            out.put(TsxNodeType.CLASS_STATIC_BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            m.put(TsxNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            m.put(TsxNodeField.LEFT, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            m.put(TsxNodeField.RIGHT, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            out.put(TsxNodeType.CONDITIONAL_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(TsxNodeField.TYPE, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.CONSTRUCTOR_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.CONSTRUCT_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.LABEL, new FieldInfo(false, false, Set.of(TsxNodeType.STATEMENT_IDENTIFIER)));
            out.put(TsxNodeType.CONTINUE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
            m.put(TsxNodeField.CONDITION, new FieldInfo(true, false, Set.of(TsxNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TsxNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.VALUE, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.ENUM_ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            out.put(TsxNodeType.ENUM_BODY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.ENUM_BODY)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER)));
            out.put(TsxNodeType.ENUM_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ALIAS, new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.STRING)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.STRING)));
            out.put(TsxNodeType.EXPORT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.DECLARATION, new FieldInfo(false, false, Set.of(TsxNodeType.DECLARATION)));
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            m.put(TsxNodeField.SOURCE, new FieldInfo(false, false, Set.of(TsxNodeType.STRING)));
            m.put(TsxNodeField.VALUE, new FieldInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.EXPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.TYPE_ARGUMENTS, new FieldInfo(false, true, Set.of(TsxNodeType.TYPE_ARGUMENTS)));
            m.put(TsxNodeField.VALUE, new FieldInfo(true, true, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.EXTENDS_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.TYPE,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    TsxNodeType.GENERIC_TYPE,
                                    TsxNodeType.NESTED_TYPE_IDENTIFIER,
                                    TsxNodeType.TYPE_IDENTIFIER)));
            out.put(TsxNodeType.EXTENDS_TYPE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            out.put(TsxNodeType.FINALLY_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
            m.put(TsxNodeField.KIND, new FieldInfo(false, false, Collections.emptySet()));
            m.put(
                    TsxNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.ARRAY_PATTERN,
                                    TsxNodeType.IDENTIFIER,
                                    TsxNodeType.MEMBER_EXPRESSION,
                                    TsxNodeType.NON_NULL_EXPRESSION,
                                    TsxNodeType.OBJECT_PATTERN,
                                    TsxNodeType.PARENTHESIZED_EXPRESSION,
                                    TsxNodeType.SUBSCRIPT_EXPRESSION,
                                    TsxNodeType.UNDEFINED)));
            m.put(TsxNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    TsxNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION)));
            m.put(TsxNodeField.VALUE, new FieldInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.FOR_IN_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
            m.put(
                    TsxNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    TsxNodeType.EMPTY_STATEMENT,
                                    TsxNodeType.EXPRESSION,
                                    TsxNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    TsxNodeField.INCREMENT,
                    new FieldInfo(false, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    TsxNodeField.INITIALIZER,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.EMPTY_STATEMENT,
                                    TsxNodeType.EXPRESSION,
                                    TsxNodeType.LEXICAL_DECLARATION,
                                    TsxNodeType.SEQUENCE_EXPRESSION,
                                    TsxNodeType.VARIABLE_DECLARATION)));
            out.put(TsxNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(TsxNodeField.NAME, new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.FUNCTION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.FUNCTION_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            true, false, Set.of(TsxNodeType.ASSERTS, TsxNodeType.TYPE, TsxNodeType.TYPE_PREDICATE)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.FUNCTION_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(TsxNodeField.NAME, new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.GENERATOR_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.GENERATOR_FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true, false, Set.of(TsxNodeType.NESTED_TYPE_IDENTIFIER, TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_ARGUMENTS)));
            out.put(TsxNodeType.GENERIC_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(TsxNodeType.ELSE_CLAUSE)));
            m.put(TsxNodeField.CONDITION, new FieldInfo(true, false, Set.of(TsxNodeType.PARENTHESIZED_EXPRESSION)));
            m.put(TsxNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
            out.put(TsxNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.SOURCE, new FieldInfo(true, false, Set.of(TsxNodeType.STRING)));
            out.put(TsxNodeType.IMPORT_REQUIRE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ALIAS, new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.STRING)));
            out.put(TsxNodeType.IMPORT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.SOURCE, new FieldInfo(false, false, Set.of(TsxNodeType.STRING)));
            out.put(TsxNodeType.IMPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.INDEX_TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE)));
            m.put(TsxNodeField.NAME, new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.SIGN, new FieldInfo(false, false, Collections.emptySet()));
            m.put(
                    TsxNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.ADDING_TYPE_ANNOTATION,
                                    TsxNodeType.OMITTING_TYPE_ANNOTATION,
                                    TsxNodeType.OPTING_TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION)));
            out.put(TsxNodeType.INDEX_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.FUNCTION,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.IDENTIFIER,
                                    TsxNodeType.IMPORT_,
                                    TsxNodeType.MEMBER_EXPRESSION,
                                    TsxNodeType.SUBSCRIPT_EXPRESSION)));
            m.put(TsxNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_ARGUMENTS)));
            out.put(TsxNodeType.INSTANTIATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.INTERFACE_BODY)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.INTERFACE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(false, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.NESTED_IDENTIFIER, TsxNodeType.STRING)));
            out.put(TsxNodeType.INTERNAL_MODULE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.IDENTIFIER,
                                    TsxNodeType.JSX_NAMESPACE_NAME,
                                    TsxNodeType.MEMBER_EXPRESSION)));
            out.put(TsxNodeType.JSX_CLOSING_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.CLOSE_TAG, new FieldInfo(true, false, Set.of(TsxNodeType.JSX_CLOSING_ELEMENT)));
            m.put(TsxNodeField.OPEN_TAG, new FieldInfo(true, false, Set.of(TsxNodeType.JSX_OPENING_ELEMENT)));
            out.put(TsxNodeType.JSX_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.ATTRIBUTE,
                    new FieldInfo(false, true, Set.of(TsxNodeType.JSX_ATTRIBUTE, TsxNodeType.JSX_EXPRESSION)));
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.IDENTIFIER,
                                    TsxNodeType.JSX_NAMESPACE_NAME,
                                    TsxNodeType.MEMBER_EXPRESSION)));
            m.put(TsxNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ARGUMENTS)));
            out.put(TsxNodeType.JSX_OPENING_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.ATTRIBUTE,
                    new FieldInfo(false, true, Set.of(TsxNodeType.JSX_ATTRIBUTE, TsxNodeType.JSX_EXPRESSION)));
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.IDENTIFIER,
                                    TsxNodeType.JSX_NAMESPACE_NAME,
                                    TsxNodeType.MEMBER_EXPRESSION)));
            m.put(TsxNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ARGUMENTS)));
            out.put(TsxNodeType.JSX_SELF_CLOSING_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
            m.put(TsxNodeField.LABEL, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_IDENTIFIER)));
            out.put(TsxNodeType.LABELED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.KIND, new FieldInfo(true, false, Collections.emptySet()));
            out.put(TsxNodeType.LEXICAL_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ALIAS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.TYPE, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            out.put(TsxNodeType.MAPPED_TYPE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.OBJECT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.IMPORT_)));
            m.put(TsxNodeField.OPTIONAL_CHAIN, new FieldInfo(false, false, Set.of(TsxNodeType.OPTIONAL_CHAIN)));
            m.put(
                    TsxNodeField.PROPERTY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER, TsxNodeType.PROPERTY_IDENTIFIER)));
            out.put(TsxNodeType.MEMBER_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.METHOD_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(TsxNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TsxNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TsxNodeType.ASSERTS_ANNOTATION,
                                    TsxNodeType.TYPE_ANNOTATION,
                                    TsxNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            out.put(TsxNodeType.METHOD_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(false, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.NESTED_IDENTIFIER, TsxNodeType.STRING)));
            out.put(TsxNodeType.MODULE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.OBJECT,
                    new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.MEMBER_EXPRESSION)));
            m.put(TsxNodeField.PROPERTY, new FieldInfo(true, false, Set.of(TsxNodeType.PROPERTY_IDENTIFIER)));
            out.put(TsxNodeType.NESTED_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.MODULE,
                    new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.NESTED_IDENTIFIER)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            out.put(TsxNodeType.NESTED_TYPE_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(TsxNodeType.ARGUMENTS)));
            m.put(TsxNodeField.CONSTRUCTOR, new FieldInfo(true, false, Set.of(TsxNodeType.PRIMARY_EXPRESSION)));
            m.put(TsxNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ARGUMENTS)));
            out.put(TsxNodeType.NEW_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.ARRAY_PATTERN,
                                    TsxNodeType.OBJECT_PATTERN,
                                    TsxNodeType.SHORTHAND_PROPERTY_IDENTIFIER_PATTERN)));
            m.put(TsxNodeField.RIGHT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.OBJECT_ASSIGNMENT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            m.put(TsxNodeField.NAME, new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER)));
            m.put(TsxNodeField.PATTERN, new FieldInfo(false, false, Set.of(TsxNodeType.PATTERN, TsxNodeType.THIS_)));
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            m.put(TsxNodeField.VALUE, new FieldInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.OPTIONAL_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.KEY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.VALUE, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.KEY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(
                    TsxNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(TsxNodeType.ASSIGNMENT_PATTERN, TsxNodeType.PATTERN)));
            out.put(TsxNodeType.PAIR_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            out.put(TsxNodeType.PARENTHESIZED_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            out.put(TsxNodeType.PROPERTY_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.COMPUTED_PROPERTY_NAME,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TsxNodeType.PROPERTY_IDENTIFIER,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            m.put(TsxNodeField.VALUE, new FieldInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.PUBLIC_FIELD_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.FLAGS, new FieldInfo(false, false, Set.of(TsxNodeType.REGEX_FLAGS)));
            m.put(TsxNodeField.PATTERN, new FieldInfo(true, false, Set.of(TsxNodeType.REGEX_PATTERN)));
            out.put(TsxNodeType.REGEX, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TsxNodeType.DECORATOR)));
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(false, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.REST_PATTERN)));
            m.put(TsxNodeField.PATTERN, new FieldInfo(false, false, Set.of(TsxNodeType.PATTERN, TsxNodeType.THIS_)));
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            m.put(TsxNodeField.VALUE, new FieldInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.REQUIRED_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.INDEX,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TsxNodeType.EXPRESSION,
                                    TsxNodeType.NUMBER,
                                    TsxNodeType.PREDEFINED_TYPE,
                                    TsxNodeType.SEQUENCE_EXPRESSION,
                                    TsxNodeType.STRING)));
            m.put(TsxNodeField.OBJECT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            m.put(TsxNodeField.OPTIONAL_CHAIN, new FieldInfo(false, false, Set.of(TsxNodeType.OPTIONAL_CHAIN)));
            out.put(TsxNodeType.SUBSCRIPT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(false, true, Set.of(TsxNodeType.STATEMENT)));
            m.put(
                    TsxNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION)));
            out.put(TsxNodeType.SWITCH_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(false, true, Set.of(TsxNodeType.STATEMENT)));
            out.put(TsxNodeType.SWITCH_DEFAULT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.SWITCH_BODY)));
            m.put(TsxNodeField.VALUE, new FieldInfo(true, false, Set.of(TsxNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TsxNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            m.put(TsxNodeField.CONDITION, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            m.put(TsxNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.TERNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT_BLOCK)));
            m.put(TsxNodeField.FINALIZER, new FieldInfo(false, false, Set.of(TsxNodeType.FINALLY_CLAUSE)));
            m.put(TsxNodeField.HANDLER, new FieldInfo(false, false, Set.of(TsxNodeType.CATCH_CLAUSE)));
            out.put(TsxNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_PARAMETERS)));
            m.put(TsxNodeField.VALUE, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            out.put(TsxNodeType.TYPE_ALIAS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.CONSTRAINT, new FieldInfo(false, false, Set.of(TsxNodeType.CONSTRAINT)));
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE_IDENTIFIER)));
            m.put(TsxNodeField.VALUE, new FieldInfo(false, false, Set.of(TsxNodeType.DEFAULT_TYPE)));
            out.put(TsxNodeType.TYPE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.NAME, new FieldInfo(true, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.THIS_)));
            m.put(TsxNodeField.TYPE, new FieldInfo(true, false, Set.of(TsxNodeType.TYPE)));
            out.put(TsxNodeType.TYPE_PREDICATE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.ARGUMENT,
                    new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.NUMBER)));
            m.put(TsxNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(TsxNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
            m.put(TsxNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(TsxNodeType.UPDATE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(
                    TsxNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TsxNodeType.ARRAY_PATTERN, TsxNodeType.IDENTIFIER, TsxNodeType.OBJECT_PATTERN)));
            m.put(TsxNodeField.TYPE, new FieldInfo(false, false, Set.of(TsxNodeType.TYPE_ANNOTATION)));
            m.put(TsxNodeField.VALUE, new FieldInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
            out.put(TsxNodeType.VARIABLE_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
            m.put(TsxNodeField.CONDITION, new FieldInfo(true, false, Set.of(TsxNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TsxNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TsxNodeField, FieldInfo> m = new EnumMap<>(TsxNodeField.class);
            m.put(TsxNodeField.BODY, new FieldInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
            m.put(TsxNodeField.OBJECT, new FieldInfo(true, false, Set.of(TsxNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TsxNodeType.WITH_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<TsxNodeType, ChildInfo> initChildren() {
        EnumMap<TsxNodeType, ChildInfo> out = new EnumMap<>(TsxNodeType.class);
        out.put(
                TsxNodeType.ABSTRACT_CLASS_DECLARATION,
                new ChildInfo(false, false, Set.of(TsxNodeType.CLASS_HERITAGE)));
        out.put(
                TsxNodeType.ABSTRACT_METHOD_SIGNATURE,
                new ChildInfo(false, true, Set.of(TsxNodeType.ACCESSIBILITY_MODIFIER, TsxNodeType.OVERRIDE_MODIFIER)));
        out.put(TsxNodeType.ADDING_TYPE_ANNOTATION, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.AMBIENT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                TsxNodeType.DECLARATION,
                                TsxNodeType.PROPERTY_IDENTIFIER,
                                TsxNodeType.STATEMENT_BLOCK,
                                TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.ARGUMENTS,
                new ChildInfo(false, true, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SPREAD_ELEMENT)));
        out.put(
                TsxNodeType.ARRAY,
                new ChildInfo(false, true, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SPREAD_ELEMENT)));
        out.put(
                TsxNodeType.ARRAY_PATTERN,
                new ChildInfo(false, true, Set.of(TsxNodeType.ASSIGNMENT_PATTERN, TsxNodeType.PATTERN)));
        out.put(TsxNodeType.ARRAY_TYPE, new ChildInfo(true, false, Set.of(TsxNodeType.PRIMARY_TYPE)));
        out.put(
                TsxNodeType.ASSERTS,
                new ChildInfo(
                        true, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.THIS_, TsxNodeType.TYPE_PREDICATE)));
        out.put(TsxNodeType.ASSERTS_ANNOTATION, new ChildInfo(true, false, Set.of(TsxNodeType.ASSERTS)));
        out.put(TsxNodeType.AS_EXPRESSION, new ChildInfo(true, true, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.TYPE)));
        out.put(TsxNodeType.AWAIT_EXPRESSION, new ChildInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
        out.put(TsxNodeType.CLASS_, new ChildInfo(false, false, Set.of(TsxNodeType.CLASS_HERITAGE)));
        out.put(
                TsxNodeType.CLASS_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.ABSTRACT_METHOD_SIGNATURE,
                                TsxNodeType.CLASS_STATIC_BLOCK,
                                TsxNodeType.INDEX_SIGNATURE,
                                TsxNodeType.METHOD_DEFINITION,
                                TsxNodeType.METHOD_SIGNATURE,
                                TsxNodeType.PUBLIC_FIELD_DEFINITION)));
        out.put(TsxNodeType.CLASS_DECLARATION, new ChildInfo(false, false, Set.of(TsxNodeType.CLASS_HERITAGE)));
        out.put(
                TsxNodeType.CLASS_HERITAGE,
                new ChildInfo(true, true, Set.of(TsxNodeType.EXTENDS_CLAUSE, TsxNodeType.IMPLEMENTS_CLAUSE)));
        out.put(TsxNodeType.COMPUTED_PROPERTY_NAME, new ChildInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
        out.put(TsxNodeType.CONSTRAINT, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.DECORATOR,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TsxNodeType.CALL_EXPRESSION,
                                TsxNodeType.IDENTIFIER,
                                TsxNodeType.MEMBER_EXPRESSION,
                                TsxNodeType.PARENTHESIZED_EXPRESSION)));
        out.put(TsxNodeType.DEFAULT_TYPE, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(TsxNodeType.ELSE_CLAUSE, new ChildInfo(true, false, Set.of(TsxNodeType.STATEMENT)));
        out.put(TsxNodeType.ENUM_BODY, new ChildInfo(false, true, Set.of(TsxNodeType.ENUM_ASSIGNMENT)));
        out.put(TsxNodeType.EXPORT_CLAUSE, new ChildInfo(false, true, Set.of(TsxNodeType.EXPORT_SPECIFIER)));
        out.put(
                TsxNodeType.EXPORT_STATEMENT,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                TsxNodeType.EXPORT_CLAUSE,
                                TsxNodeType.EXPRESSION,
                                TsxNodeType.IDENTIFIER,
                                TsxNodeType.NAMESPACE_EXPORT)));
        out.put(
                TsxNodeType.EXPRESSION_STATEMENT,
                new ChildInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION)));
        out.put(TsxNodeType.FLOW_MAYBE_TYPE, new ChildInfo(true, false, Set.of(TsxNodeType.PRIMARY_TYPE)));
        out.put(
                TsxNodeType.FORMAL_PARAMETERS,
                new ChildInfo(false, true, Set.of(TsxNodeType.OPTIONAL_PARAMETER, TsxNodeType.REQUIRED_PARAMETER)));
        out.put(TsxNodeType.IMPLEMENTS_CLAUSE, new ChildInfo(true, true, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.IMPORT_ALIAS,
                new ChildInfo(true, true, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.NESTED_IDENTIFIER)));
        out.put(TsxNodeType.IMPORT_ATTRIBUTE, new ChildInfo(true, false, Set.of(TsxNodeType.OBJECT)));
        out.put(
                TsxNodeType.IMPORT_CLAUSE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.NAMED_IMPORTS, TsxNodeType.NAMESPACE_IMPORT)));
        out.put(TsxNodeType.IMPORT_REQUIRE_CLAUSE, new ChildInfo(true, false, Set.of(TsxNodeType.IDENTIFIER)));
        out.put(
                TsxNodeType.IMPORT_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.IMPORT_ATTRIBUTE,
                                TsxNodeType.IMPORT_CLAUSE,
                                TsxNodeType.IMPORT_REQUIRE_CLAUSE)));
        out.put(TsxNodeType.INDEX_SIGNATURE, new ChildInfo(false, false, Set.of(TsxNodeType.MAPPED_TYPE_CLAUSE)));
        out.put(TsxNodeType.INDEX_TYPE_QUERY, new ChildInfo(true, false, Set.of(TsxNodeType.PRIMARY_TYPE)));
        out.put(
                TsxNodeType.INFER_TYPE,
                new ChildInfo(true, true, Set.of(TsxNodeType.TYPE, TsxNodeType.TYPE_IDENTIFIER)));
        out.put(TsxNodeType.INSTANTIATION_EXPRESSION, new ChildInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
        out.put(
                TsxNodeType.INTERFACE_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.CALL_SIGNATURE,
                                TsxNodeType.CONSTRUCT_SIGNATURE,
                                TsxNodeType.EXPORT_STATEMENT,
                                TsxNodeType.INDEX_SIGNATURE,
                                TsxNodeType.METHOD_SIGNATURE,
                                TsxNodeType.PROPERTY_SIGNATURE)));
        out.put(
                TsxNodeType.INTERFACE_DECLARATION,
                new ChildInfo(false, false, Set.of(TsxNodeType.EXTENDS_TYPE_CLAUSE)));
        out.put(TsxNodeType.INTERSECTION_TYPE, new ChildInfo(true, true, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.JSX_ATTRIBUTE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                TsxNodeType.JSX_ELEMENT,
                                TsxNodeType.JSX_EXPRESSION,
                                TsxNodeType.JSX_NAMESPACE_NAME,
                                TsxNodeType.JSX_SELF_CLOSING_ELEMENT,
                                TsxNodeType.PROPERTY_IDENTIFIER,
                                TsxNodeType.STRING)));
        out.put(
                TsxNodeType.JSX_ELEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.HTML_CHARACTER_REFERENCE,
                                TsxNodeType.JSX_ELEMENT,
                                TsxNodeType.JSX_EXPRESSION,
                                TsxNodeType.JSX_SELF_CLOSING_ELEMENT,
                                TsxNodeType.JSX_TEXT)));
        out.put(
                TsxNodeType.JSX_EXPRESSION,
                new ChildInfo(
                        false,
                        false,
                        Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION, TsxNodeType.SPREAD_ELEMENT)));
        out.put(TsxNodeType.JSX_NAMESPACE_NAME, new ChildInfo(true, true, Set.of(TsxNodeType.IDENTIFIER)));
        out.put(TsxNodeType.LEXICAL_DECLARATION, new ChildInfo(true, true, Set.of(TsxNodeType.VARIABLE_DECLARATOR)));
        out.put(
                TsxNodeType.LITERAL_TYPE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TsxNodeType.FALSE,
                                TsxNodeType.NULL,
                                TsxNodeType.NUMBER,
                                TsxNodeType.STRING,
                                TsxNodeType.TRUE,
                                TsxNodeType.UNARY_EXPRESSION,
                                TsxNodeType.UNDEFINED)));
        out.put(TsxNodeType.LOOKUP_TYPE, new ChildInfo(true, true, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.METHOD_DEFINITION,
                new ChildInfo(false, true, Set.of(TsxNodeType.ACCESSIBILITY_MODIFIER, TsxNodeType.OVERRIDE_MODIFIER)));
        out.put(
                TsxNodeType.METHOD_SIGNATURE,
                new ChildInfo(false, true, Set.of(TsxNodeType.ACCESSIBILITY_MODIFIER, TsxNodeType.OVERRIDE_MODIFIER)));
        out.put(TsxNodeType.NAMED_IMPORTS, new ChildInfo(false, true, Set.of(TsxNodeType.IMPORT_SPECIFIER)));
        out.put(
                TsxNodeType.NAMESPACE_EXPORT,
                new ChildInfo(true, false, Set.of(TsxNodeType.IDENTIFIER, TsxNodeType.STRING)));
        out.put(TsxNodeType.NAMESPACE_IMPORT, new ChildInfo(true, false, Set.of(TsxNodeType.IDENTIFIER)));
        out.put(TsxNodeType.NON_NULL_EXPRESSION, new ChildInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
        out.put(
                TsxNodeType.OBJECT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.METHOD_DEFINITION,
                                TsxNodeType.PAIR,
                                TsxNodeType.SHORTHAND_PROPERTY_IDENTIFIER,
                                TsxNodeType.SPREAD_ELEMENT)));
        out.put(
                TsxNodeType.OBJECT_PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.OBJECT_ASSIGNMENT_PATTERN,
                                TsxNodeType.PAIR_PATTERN,
                                TsxNodeType.REST_PATTERN,
                                TsxNodeType.SHORTHAND_PROPERTY_IDENTIFIER_PATTERN)));
        out.put(
                TsxNodeType.OBJECT_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.CALL_SIGNATURE,
                                TsxNodeType.CONSTRUCT_SIGNATURE,
                                TsxNodeType.EXPORT_STATEMENT,
                                TsxNodeType.INDEX_SIGNATURE,
                                TsxNodeType.METHOD_SIGNATURE,
                                TsxNodeType.PROPERTY_SIGNATURE)));
        out.put(TsxNodeType.OMITTING_TYPE_ANNOTATION, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(TsxNodeType.OPTING_TYPE_ANNOTATION, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.OPTIONAL_PARAMETER,
                new ChildInfo(false, true, Set.of(TsxNodeType.ACCESSIBILITY_MODIFIER, TsxNodeType.OVERRIDE_MODIFIER)));
        out.put(TsxNodeType.OPTIONAL_TYPE, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TsxNodeType.CALL_EXPRESSION,
                                TsxNodeType.EXPRESSION,
                                TsxNodeType.IDENTIFIER,
                                TsxNodeType.MEMBER_EXPRESSION,
                                TsxNodeType.SEQUENCE_EXPRESSION)));
        out.put(TsxNodeType.PARENTHESIZED_TYPE, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.PROGRAM,
                new ChildInfo(false, true, Set.of(TsxNodeType.HASH_BANG_LINE, TsxNodeType.STATEMENT)));
        out.put(
                TsxNodeType.PROPERTY_SIGNATURE,
                new ChildInfo(false, true, Set.of(TsxNodeType.ACCESSIBILITY_MODIFIER, TsxNodeType.OVERRIDE_MODIFIER)));
        out.put(
                TsxNodeType.PUBLIC_FIELD_DEFINITION,
                new ChildInfo(false, true, Set.of(TsxNodeType.ACCESSIBILITY_MODIFIER, TsxNodeType.OVERRIDE_MODIFIER)));
        out.put(TsxNodeType.READONLY_TYPE, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.REQUIRED_PARAMETER,
                new ChildInfo(false, true, Set.of(TsxNodeType.ACCESSIBILITY_MODIFIER, TsxNodeType.OVERRIDE_MODIFIER)));
        out.put(
                TsxNodeType.REST_PATTERN,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TsxNodeType.ARRAY_PATTERN,
                                TsxNodeType.IDENTIFIER,
                                TsxNodeType.MEMBER_EXPRESSION,
                                TsxNodeType.NON_NULL_EXPRESSION,
                                TsxNodeType.OBJECT_PATTERN,
                                TsxNodeType.SUBSCRIPT_EXPRESSION,
                                TsxNodeType.UNDEFINED)));
        out.put(TsxNodeType.REST_TYPE, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(
                TsxNodeType.RETURN_STATEMENT,
                new ChildInfo(false, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                TsxNodeType.SATISFIES_EXPRESSION,
                new ChildInfo(true, true, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.TYPE)));
        out.put(TsxNodeType.SEQUENCE_EXPRESSION, new ChildInfo(true, true, Set.of(TsxNodeType.EXPRESSION)));
        out.put(TsxNodeType.SPREAD_ELEMENT, new ChildInfo(true, false, Set.of(TsxNodeType.EXPRESSION)));
        out.put(TsxNodeType.STATEMENT_BLOCK, new ChildInfo(false, true, Set.of(TsxNodeType.STATEMENT)));
        out.put(
                TsxNodeType.STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.ESCAPE_SEQUENCE,
                                TsxNodeType.HTML_CHARACTER_REFERENCE,
                                TsxNodeType.STRING_FRAGMENT)));
        out.put(
                TsxNodeType.SWITCH_BODY,
                new ChildInfo(false, true, Set.of(TsxNodeType.SWITCH_CASE, TsxNodeType.SWITCH_DEFAULT)));
        out.put(
                TsxNodeType.TEMPLATE_LITERAL_TYPE,
                new ChildInfo(false, true, Set.of(TsxNodeType.STRING_FRAGMENT, TsxNodeType.TEMPLATE_TYPE)));
        out.put(
                TsxNodeType.TEMPLATE_STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.ESCAPE_SEQUENCE,
                                TsxNodeType.STRING_FRAGMENT,
                                TsxNodeType.TEMPLATE_SUBSTITUTION)));
        out.put(
                TsxNodeType.TEMPLATE_SUBSTITUTION,
                new ChildInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                TsxNodeType.TEMPLATE_TYPE,
                new ChildInfo(true, false, Set.of(TsxNodeType.INFER_TYPE, TsxNodeType.PRIMARY_TYPE)));
        out.put(
                TsxNodeType.THROW_STATEMENT,
                new ChildInfo(true, false, Set.of(TsxNodeType.EXPRESSION, TsxNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                TsxNodeType.TUPLE_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TsxNodeType.OPTIONAL_PARAMETER,
                                TsxNodeType.OPTIONAL_TYPE,
                                TsxNodeType.REQUIRED_PARAMETER,
                                TsxNodeType.REST_TYPE,
                                TsxNodeType.TYPE)));
        out.put(TsxNodeType.TYPE_ANNOTATION, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE)));
        out.put(TsxNodeType.TYPE_ARGUMENTS, new ChildInfo(true, true, Set.of(TsxNodeType.TYPE)));
        out.put(TsxNodeType.TYPE_PARAMETERS, new ChildInfo(true, true, Set.of(TsxNodeType.TYPE_PARAMETER)));
        out.put(TsxNodeType.TYPE_PREDICATE_ANNOTATION, new ChildInfo(true, false, Set.of(TsxNodeType.TYPE_PREDICATE)));
        out.put(
                TsxNodeType.TYPE_QUERY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TsxNodeType.CALL_EXPRESSION,
                                TsxNodeType.IDENTIFIER,
                                TsxNodeType.INSTANTIATION_EXPRESSION,
                                TsxNodeType.MEMBER_EXPRESSION,
                                TsxNodeType.SUBSCRIPT_EXPRESSION,
                                TsxNodeType.THIS_)));
        out.put(TsxNodeType.UNION_TYPE, new ChildInfo(true, true, Set.of(TsxNodeType.TYPE)));
        out.put(TsxNodeType.VARIABLE_DECLARATION, new ChildInfo(true, true, Set.of(TsxNodeType.VARIABLE_DECLARATOR)));
        out.put(TsxNodeType.YIELD_EXPRESSION, new ChildInfo(false, false, Set.of(TsxNodeType.EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<TsxNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<TsxNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<TsxNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<TsxNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
