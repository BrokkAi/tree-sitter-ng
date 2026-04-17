package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code c-sharp} from tree-sitter {@code node-types.json}.
 */
public final class CSharpNodeSchema {
    private CSharpNodeSchema() {}

    public static Set<CSharpNodeField> fields(@Nullable CSharpNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<CSharpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<CSharpNodeType> allowedTypes(@Nullable CSharpNodeType owner, @Nullable CSharpNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<CSharpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable CSharpNodeType owner, @Nullable CSharpNodeField field) {
        if (owner == null || field == null) return false;
        Map<CSharpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable CSharpNodeType owner, @Nullable CSharpNodeField field) {
        if (owner == null || field == null) return false;
        Map<CSharpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<CSharpNodeType> allowedChildTypes(@Nullable CSharpNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable CSharpNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable CSharpNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<CSharpNodeType, Map<CSharpNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<CSharpNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<CSharpNodeType, Map<CSharpNodeField, FieldInfo>> initFields() {
        EnumMap<CSharpNodeType, Map<CSharpNodeField, FieldInfo>> out = new EnumMap<>(CSharpNodeType.class);
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.BLOCK)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.ACCESSOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ALIAS, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(
                    CSharpNodeField.NAME,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.GENERIC_NAME, CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.ALIAS_QUALIFIED_NAME, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.LEFT, new FieldInfo(true, false, Set.of(CSharpNodeType.PATTERN)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CSharpNodeField.RIGHT, new FieldInfo(true, false, Set.of(CSharpNodeType.PATTERN)));
            out.put(CSharpNodeType.AND_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            out.put(CSharpNodeType.ANONYMOUS_METHOD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.ARGUMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.ARRAY_TYPE)));
            out.put(CSharpNodeType.ARRAY_CREATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.RANK, new FieldInfo(true, false, Set.of(CSharpNodeType.ARRAY_RANK_SPECIFIER)));
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.ARRAY_TYPE,
                                    CSharpNodeType.FUNCTION_POINTER_TYPE,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.NULLABLE_TYPE,
                                    CSharpNodeType.POINTER_TYPE,
                                    CSharpNodeType.PREDEFINED_TYPE,
                                    CSharpNodeType.QUALIFIED_NAME,
                                    CSharpNodeType.TUPLE_TYPE)));
            out.put(CSharpNodeType.ARRAY_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.LEFT, new FieldInfo(true, false, Set.of(CSharpNodeType.LVALUE_EXPRESSION)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CSharpNodeField.RIGHT, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.LEFT, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CSharpNodeField.RIGHT, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.AS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.QUALIFIED_NAME)));
            out.put(CSharpNodeType.ATTRIBUTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.BINARY_EXPRESSION,
                                    CSharpNodeType.BOOLEAN_LITERAL,
                                    CSharpNodeType.CHARACTER_LITERAL,
                                    CSharpNodeType.EXPRESSION,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.INTEGER_LITERAL,
                                    CSharpNodeType.PARENTHESIZED_EXPRESSION,
                                    CSharpNodeType.UNARY_EXPRESSION)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    CSharpNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.BINARY_EXPRESSION,
                                    CSharpNodeType.BOOLEAN_LITERAL,
                                    CSharpNodeType.CHARACTER_LITERAL,
                                    CSharpNodeType.EXPRESSION,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.INTEGER_LITERAL,
                                    CSharpNodeType.PARENTHESIZED_EXPRESSION,
                                    CSharpNodeType.UNARY_EXPRESSION)));
            out.put(CSharpNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, true, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(false, true, Set.of(CSharpNodeType.ARRAY_TYPE, CSharpNodeType.NULLABLE_TYPE)));
            out.put(CSharpNodeType.BRACKETED_PARAMETER_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            m.put(CSharpNodeField.VALUE, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.CAST_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.BLOCK)));
            out.put(CSharpNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.CATCH_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.DECLARATION_LIST)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.CONDITION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.CONDITIONAL_ACCESS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(CSharpNodeField.CONDITION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(CSharpNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.CONDITIONAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.BLOCK)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            out.put(CSharpNodeType.CONSTRUCTOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.BLOCK)));
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.CONVERSION_OPERATOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.DECLARATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.DECLARATION_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.DEFAULT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            m.put(
                    CSharpNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE_PARAMETER_LIST)));
            out.put(CSharpNodeType.DELEGATE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.BLOCK)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            out.put(CSharpNodeType.DESTRUCTOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.STATEMENT)));
            m.put(CSharpNodeField.CONDITION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.EXPRESSION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(
                    CSharpNodeField.SUBSCRIPT,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.BRACKETED_ARGUMENT_LIST)));
            out.put(CSharpNodeType.ELEMENT_ACCESS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.ENUM_MEMBER_DECLARATION_LIST)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.ENUM_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.VALUE, new FieldInfo(false, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.ENUM_MEMBER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ACCESSORS, new FieldInfo(false, false, Set.of(CSharpNodeType.ACCESSOR_LIST)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.EVENT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.EXTERN_ALIAS_DIRECTIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.QUALIFIED_NAME)));
            out.put(CSharpNodeType.FILE_SCOPED_NAMESPACE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.STATEMENT)));
            m.put(
                    CSharpNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.EXPRESSION,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.TUPLE_PATTERN)));
            m.put(CSharpNodeField.RIGHT, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.FOREACH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.STATEMENT)));
            m.put(CSharpNodeField.CONDITION, new FieldInfo(false, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(
                    CSharpNodeField.INITIALIZER,
                    new FieldInfo(false, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.VARIABLE_DECLARATION)));
            m.put(CSharpNodeField.UPDATE, new FieldInfo(false, true, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.FROM_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.ARRAY_TYPE,
                                    CSharpNodeType.FUNCTION_POINTER_TYPE,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.IMPLICIT_TYPE,
                                    CSharpNodeType.NULLABLE_TYPE,
                                    CSharpNodeType.POINTER_TYPE,
                                    CSharpNodeType.PREDEFINED_TYPE,
                                    CSharpNodeType.QUALIFIED_NAME,
                                    CSharpNodeType.TUPLE_TYPE)));
            out.put(CSharpNodeType.FUNCTION_POINTER_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.RETURNS, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.FUNCTION_POINTER_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(CSharpNodeType.STATEMENT)));
            m.put(CSharpNodeField.CONDITION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(CSharpNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(CSharpNodeType.STATEMENT)));
            out.put(CSharpNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ACCESSORS, new FieldInfo(false, false, Set.of(CSharpNodeType.ACCESSOR_LIST)));
            m.put(
                    CSharpNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.BRACKETED_PARAMETER_LIST)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            m.put(CSharpNodeField.VALUE, new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE)));
            out.put(CSharpNodeType.INDEXER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.DECLARATION_LIST)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(
                    CSharpNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE_PARAMETER_LIST)));
            out.put(CSharpNodeType.INTERFACE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(CSharpNodeType.ARGUMENT_LIST)));
            m.put(CSharpNodeField.FUNCTION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.INVOCATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.LEFT, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CSharpNodeField.RIGHT, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.IS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.EXPRESSION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            m.put(CSharpNodeField.PATTERN, new FieldInfo(true, false, Set.of(CSharpNodeType.PATTERN)));
            out.put(CSharpNodeType.IS_PATTERN_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.JOIN_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.BLOCK, CSharpNodeType.EXPRESSION)));
            m.put(
                    CSharpNodeField.PARAMETERS,
                    new FieldInfo(
                            true, false, Set.of(CSharpNodeType.IMPLICIT_PARAMETER, CSharpNodeType.PARAMETER_LIST)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.LAMBDA_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.BLOCK)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            m.put(
                    CSharpNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE_PARAMETER_LIST)));
            out.put(CSharpNodeType.LOCAL_FUNCTION_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.EXPRESSION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.EXPRESSION,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.PREDEFINED_TYPE,
                                    CSharpNodeType.QUALIFIED_NAME)));
            m.put(
                    CSharpNodeField.NAME,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.GENERIC_NAME, CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.MEMBER_ACCESS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.NAME,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.GENERIC_NAME, CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.MEMBER_BINDING_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.BLOCK)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            m.put(CSharpNodeField.RETURNS, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            m.put(
                    CSharpNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE_PARAMETER_LIST)));
            out.put(CSharpNodeType.METHOD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.DECLARATION_LIST)));
            m.put(
                    CSharpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.QUALIFIED_NAME)));
            out.put(CSharpNodeType.NAMESPACE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.ARRAY_TYPE,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.PREDEFINED_TYPE,
                                    CSharpNodeType.QUALIFIED_NAME,
                                    CSharpNodeType.TUPLE_TYPE)));
            out.put(CSharpNodeType.NULLABLE_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(CSharpNodeType.ARGUMENT_LIST)));
            m.put(
                    CSharpNodeField.INITIALIZER,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.INITIALIZER_EXPRESSION)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.OBJECT_CREATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.BLOCK)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CSharpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CSharpNodeType.PARAMETER_LIST)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.OPERATOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.LEFT, new FieldInfo(true, false, Set.of(CSharpNodeType.PATTERN)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CSharpNodeField.RIGHT, new FieldInfo(true, false, Set.of(CSharpNodeType.PATTERN)));
            out.put(CSharpNodeType.OR_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, true, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(false, true, Set.of(CSharpNodeType.ARRAY_TYPE, CSharpNodeType.NULLABLE_TYPE)));
            out.put(CSharpNodeType.PARAMETER_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, true, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.PARENTHESIZED_VARIABLE_DESIGNATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.FUNCTION_POINTER_TYPE,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.NULLABLE_TYPE,
                                    CSharpNodeType.POINTER_TYPE,
                                    CSharpNodeType.PREDEFINED_TYPE,
                                    CSharpNodeType.QUALIFIED_NAME,
                                    CSharpNodeType.TUPLE_TYPE)));
            out.put(CSharpNodeType.POINTER_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.ALTERNATIVE,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.PREPROC_ELIF, CSharpNodeType.PREPROC_ELSE)));
            m.put(
                    CSharpNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.BINARY_EXPRESSION,
                                    CSharpNodeType.BOOLEAN_LITERAL,
                                    CSharpNodeType.CHARACTER_LITERAL,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.INTEGER_LITERAL,
                                    CSharpNodeType.PARENTHESIZED_EXPRESSION,
                                    CSharpNodeType.UNARY_EXPRESSION)));
            out.put(CSharpNodeType.PREPROC_ELIF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.CONTENT, new FieldInfo(false, false, Set.of(CSharpNodeType.PREPROC_ARG)));
            out.put(CSharpNodeType.PREPROC_ENDREGION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.ALTERNATIVE,
                    new FieldInfo(false, false, Set.of(CSharpNodeType.PREPROC_ELIF, CSharpNodeType.PREPROC_ELSE)));
            m.put(
                    CSharpNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.BINARY_EXPRESSION,
                                    CSharpNodeType.BOOLEAN_LITERAL,
                                    CSharpNodeType.CHARACTER_LITERAL,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.INTEGER_LITERAL,
                                    CSharpNodeType.PARENTHESIZED_EXPRESSION,
                                    CSharpNodeType.UNARY_EXPRESSION)));
            out.put(CSharpNodeType.PREPROC_IF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.CONTENT, new FieldInfo(false, false, Set.of(CSharpNodeType.PREPROC_ARG)));
            out.put(CSharpNodeType.PREPROC_REGION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.QUALIFIED_NAME)));
            out.put(CSharpNodeType.PRIMARY_CONSTRUCTOR_BASE_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.ACCESSORS, new FieldInfo(false, false, Set.of(CSharpNodeType.ACCESSOR_LIST)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            m.put(
                    CSharpNodeField.VALUE,
                    new FieldInfo(
                            false, false, Set.of(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.PROPERTY_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.NAME,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.GENERIC_NAME, CSharpNodeType.IDENTIFIER)));
            m.put(
                    CSharpNodeField.QUALIFIER,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.QUALIFIED_NAME)));
            out.put(CSharpNodeType.QUALIFIED_NAME, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(false, false, Set.of(CSharpNodeType.DECLARATION_LIST)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.RECORD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.RECURSIVE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            m.put(CSharpNodeField.VALUE, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.REFVALUE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.REF_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                    CSharpNodeType.GENERIC_NAME,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.QUALIFIED_NAME,
                                    CSharpNodeType.REF_TYPE)));
            out.put(CSharpNodeType.SCOPED_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.SIZEOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.ARRAY_TYPE)));
            out.put(CSharpNodeType.STACKALLOC_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.DECLARATION_LIST)));
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.STRUCT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.SWITCH_BODY)));
            m.put(
                    CSharpNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.TUPLE_EXPRESSION)));
            out.put(CSharpNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.BLOCK)));
            out.put(CSharpNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.TUPLE_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, true, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.TUPLE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.TYPEOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.TYPE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(false, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.TYPE_PARAMETER_CONSTRAINT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.TYPE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(
                    CSharpNodeField.ARGUMENT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CSharpNodeType.BINARY_EXPRESSION,
                                    CSharpNodeType.BOOLEAN_LITERAL,
                                    CSharpNodeType.CHARACTER_LITERAL,
                                    CSharpNodeType.IDENTIFIER,
                                    CSharpNodeType.INTEGER_LITERAL,
                                    CSharpNodeType.PARENTHESIZED_EXPRESSION,
                                    CSharpNodeType.UNARY_EXPRESSION)));
            m.put(CSharpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CSharpNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.USING_DIRECTIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.STATEMENT)));
            out.put(CSharpNodeType.USING_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.TYPE, new FieldInfo(true, false, Set.of(CSharpNodeType.TYPE)));
            out.put(CSharpNodeType.VARIABLE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.VARIABLE_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.NAME, new FieldInfo(false, false, Set.of(CSharpNodeType.IDENTIFIER)));
            out.put(CSharpNodeType.VAR_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CSharpNodeField, FieldInfo> m = new EnumMap<>(CSharpNodeField.class);
            m.put(CSharpNodeField.BODY, new FieldInfo(true, false, Set.of(CSharpNodeType.STATEMENT)));
            m.put(CSharpNodeField.CONDITION, new FieldInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
            out.put(CSharpNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<CSharpNodeType, ChildInfo> initChildren() {
        EnumMap<CSharpNodeType, ChildInfo> out = new EnumMap<>(CSharpNodeType.class);
        out.put(
                CSharpNodeType.ACCESSOR_DECLARATION,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_LIST, CSharpNodeType.MODIFIER)));
        out.put(CSharpNodeType.ACCESSOR_LIST, new ChildInfo(false, true, Set.of(CSharpNodeType.ACCESSOR_DECLARATION)));
        out.put(
                CSharpNodeType.ANONYMOUS_METHOD_EXPRESSION,
                new ChildInfo(true, true, Set.of(CSharpNodeType.BLOCK, CSharpNodeType.MODIFIER)));
        out.put(
                CSharpNodeType.ANONYMOUS_OBJECT_CREATION_EXPRESSION,
                new ChildInfo(false, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.IDENTIFIER)));
        out.put(
                CSharpNodeType.ARGUMENT,
                new ChildInfo(true, false, Set.of(CSharpNodeType.DECLARATION_EXPRESSION, CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.ARGUMENT_LIST, new ChildInfo(false, true, Set.of(CSharpNodeType.ARGUMENT)));
        out.put(
                CSharpNodeType.ARRAY_CREATION_EXPRESSION,
                new ChildInfo(false, false, Set.of(CSharpNodeType.INITIALIZER_EXPRESSION)));
        out.put(CSharpNodeType.ARRAY_RANK_SPECIFIER, new ChildInfo(false, true, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.ARROW_EXPRESSION_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.ATTRIBUTE, new ChildInfo(false, false, Set.of(CSharpNodeType.ATTRIBUTE_ARGUMENT_LIST)));
        out.put(
                CSharpNodeType.ATTRIBUTE_ARGUMENT,
                new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.IDENTIFIER)));
        out.put(
                CSharpNodeType.ATTRIBUTE_ARGUMENT_LIST,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_ARGUMENT)));
        out.put(
                CSharpNodeType.ATTRIBUTE_LIST,
                new ChildInfo(true, true, Set.of(CSharpNodeType.ATTRIBUTE, CSharpNodeType.ATTRIBUTE_TARGET_SPECIFIER)));
        out.put(CSharpNodeType.AWAIT_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.BASE_LIST,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.ARGUMENT_LIST,
                                CSharpNodeType.PRIMARY_CONSTRUCTOR_BASE_TYPE,
                                CSharpNodeType.TYPE)));
        out.put(CSharpNodeType.BLOCK, new ChildInfo(false, true, Set.of(CSharpNodeType.STATEMENT)));
        out.put(CSharpNodeType.BRACKETED_ARGUMENT_LIST, new ChildInfo(true, true, Set.of(CSharpNodeType.ARGUMENT)));
        out.put(
                CSharpNodeType.BRACKETED_PARAMETER_LIST,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_LIST, CSharpNodeType.PARAMETER)));
        out.put(CSharpNodeType.CALLING_CONVENTION, new ChildInfo(false, true, Set.of(CSharpNodeType.IDENTIFIER)));
        out.put(
                CSharpNodeType.CATCH_CLAUSE,
                new ChildInfo(
                        false, true, Set.of(CSharpNodeType.CATCH_DECLARATION, CSharpNodeType.CATCH_FILTER_CLAUSE)));
        out.put(CSharpNodeType.CATCH_FILTER_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.CHARACTER_LITERAL,
                new ChildInfo(
                        true, false, Set.of(CSharpNodeType.CHARACTER_LITERAL_CONTENT, CSharpNodeType.ESCAPE_SEQUENCE)));
        out.put(CSharpNodeType.CHECKED_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.CHECKED_STATEMENT, new ChildInfo(true, false, Set.of(CSharpNodeType.BLOCK)));
        out.put(
                CSharpNodeType.CLASS_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.BASE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.PARAMETER_LIST,
                                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE,
                                CSharpNodeType.TYPE_PARAMETER_LIST)));
        out.put(
                CSharpNodeType.COMPILATION_UNIT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.EXTERN_ALIAS_DIRECTIVE,
                                CSharpNodeType.FILE_SCOPED_NAMESPACE_DECLARATION,
                                CSharpNodeType.GLOBAL_ATTRIBUTE,
                                CSharpNodeType.GLOBAL_STATEMENT,
                                CSharpNodeType.NAMESPACE_DECLARATION,
                                CSharpNodeType.PREPROC_IF,
                                CSharpNodeType.SHEBANG_DIRECTIVE,
                                CSharpNodeType.TYPE_DECLARATION,
                                CSharpNodeType.USING_DIRECTIVE)));
        out.put(
                CSharpNodeType.CONDITIONAL_ACCESS_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(CSharpNodeType.ELEMENT_BINDING_EXPRESSION, CSharpNodeType.MEMBER_BINDING_EXPRESSION)));
        out.put(
                CSharpNodeType.CONSTANT_PATTERN,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CSharpNodeType.BINARY_EXPRESSION,
                                CSharpNodeType.CAST_EXPRESSION,
                                CSharpNodeType.DEFAULT_EXPRESSION,
                                CSharpNodeType.GENERIC_NAME,
                                CSharpNodeType.IDENTIFIER,
                                CSharpNodeType.INTERPOLATED_STRING_EXPRESSION,
                                CSharpNodeType.INVOCATION_EXPRESSION,
                                CSharpNodeType.LITERAL,
                                CSharpNodeType.MEMBER_ACCESS_EXPRESSION,
                                CSharpNodeType.PARENTHESIZED_EXPRESSION,
                                CSharpNodeType.POSTFIX_UNARY_EXPRESSION,
                                CSharpNodeType.PREFIX_UNARY_EXPRESSION,
                                CSharpNodeType.SIZEOF_EXPRESSION,
                                CSharpNodeType.TUPLE_EXPRESSION,
                                CSharpNodeType.TYPEOF_EXPRESSION)));
        out.put(
                CSharpNodeType.CONSTRUCTOR_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.CONSTRUCTOR_INITIALIZER,
                                CSharpNodeType.MODIFIER)));
        out.put(
                CSharpNodeType.CONSTRUCTOR_INITIALIZER,
                new ChildInfo(true, false, Set.of(CSharpNodeType.ARGUMENT_LIST)));
        out.put(
                CSharpNodeType.CONVERSION_OPERATOR_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.EXPLICIT_INTERFACE_SPECIFIER,
                                CSharpNodeType.MODIFIER)));
        out.put(CSharpNodeType.DECLARATION_LIST, new ChildInfo(false, true, Set.of(CSharpNodeType.DECLARATION)));
        out.put(
                CSharpNodeType.DECLARATION_PATTERN,
                new ChildInfo(
                        false,
                        false,
                        Set.of(CSharpNodeType.DISCARD, CSharpNodeType.PARENTHESIZED_VARIABLE_DESIGNATION)));
        out.put(
                CSharpNodeType.DELEGATE_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE)));
        out.put(
                CSharpNodeType.DESTRUCTOR_DECLARATION,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_LIST)));
        out.put(CSharpNodeType.ELEMENT_BINDING_EXPRESSION, new ChildInfo(true, true, Set.of(CSharpNodeType.ARGUMENT)));
        out.put(
                CSharpNodeType.ENUM_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CSharpNodeType.ATTRIBUTE_LIST, CSharpNodeType.BASE_LIST, CSharpNodeType.MODIFIER)));
        out.put(
                CSharpNodeType.ENUM_MEMBER_DECLARATION,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_LIST)));
        out.put(
                CSharpNodeType.ENUM_MEMBER_DECLARATION_LIST,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ENUM_MEMBER_DECLARATION, CSharpNodeType.PREPROC_IF)));
        out.put(
                CSharpNodeType.EVENT_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.EXPLICIT_INTERFACE_SPECIFIER,
                                CSharpNodeType.MODIFIER)));
        out.put(
                CSharpNodeType.EVENT_FIELD_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.VARIABLE_DECLARATION)));
        out.put(
                CSharpNodeType.EXPLICIT_INTERFACE_SPECIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CSharpNodeType.ALIAS_QUALIFIED_NAME,
                                CSharpNodeType.GENERIC_NAME,
                                CSharpNodeType.IDENTIFIER,
                                CSharpNodeType.QUALIFIED_NAME)));
        out.put(
                CSharpNodeType.EXPRESSION_STATEMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CSharpNodeType.ASSIGNMENT_EXPRESSION,
                                CSharpNodeType.AWAIT_EXPRESSION,
                                CSharpNodeType.INVOCATION_EXPRESSION,
                                CSharpNodeType.OBJECT_CREATION_EXPRESSION,
                                CSharpNodeType.PARENTHESIZED_EXPRESSION,
                                CSharpNodeType.POSTFIX_UNARY_EXPRESSION,
                                CSharpNodeType.PREFIX_UNARY_EXPRESSION)));
        out.put(
                CSharpNodeType.FIELD_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.VARIABLE_DECLARATION)));
        out.put(CSharpNodeType.FINALLY_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.BLOCK)));
        out.put(
                CSharpNodeType.FIXED_STATEMENT,
                new ChildInfo(true, true, Set.of(CSharpNodeType.STATEMENT, CSharpNodeType.VARIABLE_DECLARATION)));
        out.put(CSharpNodeType.FROM_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.FUNCTION_POINTER_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CSharpNodeType.CALLING_CONVENTION, CSharpNodeType.FUNCTION_POINTER_PARAMETER)));
        out.put(
                CSharpNodeType.GENERIC_NAME,
                new ChildInfo(true, true, Set.of(CSharpNodeType.IDENTIFIER, CSharpNodeType.TYPE_ARGUMENT_LIST)));
        out.put(CSharpNodeType.GLOBAL_ATTRIBUTE, new ChildInfo(true, true, Set.of(CSharpNodeType.ATTRIBUTE)));
        out.put(CSharpNodeType.GLOBAL_STATEMENT, new ChildInfo(true, false, Set.of(CSharpNodeType.STATEMENT)));
        out.put(CSharpNodeType.GOTO_STATEMENT, new ChildInfo(false, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.GROUP_CLAUSE, new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.IMPLICIT_ARRAY_CREATION_EXPRESSION,
                new ChildInfo(true, false, Set.of(CSharpNodeType.INITIALIZER_EXPRESSION)));
        out.put(
                CSharpNodeType.IMPLICIT_OBJECT_CREATION_EXPRESSION,
                new ChildInfo(true, true, Set.of(CSharpNodeType.ARGUMENT_LIST, CSharpNodeType.INITIALIZER_EXPRESSION)));
        out.put(
                CSharpNodeType.IMPLICIT_STACKALLOC_EXPRESSION,
                new ChildInfo(true, false, Set.of(CSharpNodeType.INITIALIZER_EXPRESSION)));
        out.put(
                CSharpNodeType.INDEXER_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.EXPLICIT_INTERFACE_SPECIFIER,
                                CSharpNodeType.MODIFIER)));
        out.put(CSharpNodeType.INITIALIZER_EXPRESSION, new ChildInfo(false, true, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.INTERFACE_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.BASE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE)));
        out.put(
                CSharpNodeType.INTERPOLATED_STRING_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.ESCAPE_SEQUENCE,
                                CSharpNodeType.INTERPOLATION,
                                CSharpNodeType.INTERPOLATION_QUOTE,
                                CSharpNodeType.INTERPOLATION_START,
                                CSharpNodeType.STRING_CONTENT)));
        out.put(
                CSharpNodeType.INTERPOLATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.EXPRESSION,
                                CSharpNodeType.INTERPOLATION_ALIGNMENT_CLAUSE,
                                CSharpNodeType.INTERPOLATION_BRACE,
                                CSharpNodeType.INTERPOLATION_FORMAT_CLAUSE)));
        out.put(
                CSharpNodeType.INTERPOLATION_ALIGNMENT_CLAUSE,
                new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.JOIN_CLAUSE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.IDENTIFIER, CSharpNodeType.JOIN_INTO_CLAUSE)));
        out.put(CSharpNodeType.JOIN_INTO_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.IDENTIFIER)));
        out.put(
                CSharpNodeType.LABELED_STATEMENT,
                new ChildInfo(true, true, Set.of(CSharpNodeType.IDENTIFIER, CSharpNodeType.STATEMENT)));
        out.put(
                CSharpNodeType.LAMBDA_EXPRESSION,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_LIST, CSharpNodeType.MODIFIER)));
        out.put(
                CSharpNodeType.LET_CLAUSE,
                new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.IDENTIFIER)));
        out.put(CSharpNodeType.LIST_PATTERN, new ChildInfo(false, true, Set.of(CSharpNodeType.PATTERN)));
        out.put(
                CSharpNodeType.LOCAL_DECLARATION_STATEMENT,
                new ChildInfo(true, true, Set.of(CSharpNodeType.MODIFIER, CSharpNodeType.VARIABLE_DECLARATION)));
        out.put(
                CSharpNodeType.LOCAL_FUNCTION_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE)));
        out.put(
                CSharpNodeType.LOCK_STATEMENT,
                new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.STATEMENT)));
        out.put(CSharpNodeType.MAKEREF_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.METHOD_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.EXPLICIT_INTERFACE_SPECIFIER,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE)));
        out.put(CSharpNodeType.NEGATED_PATTERN, new ChildInfo(true, false, Set.of(CSharpNodeType.PATTERN)));
        out.put(
                CSharpNodeType.OPERATOR_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.EXPLICIT_INTERFACE_SPECIFIER,
                                CSharpNodeType.MODIFIER)));
        out.put(CSharpNodeType.ORDER_BY_CLAUSE, new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.PARAMETER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CSharpNodeType.ATTRIBUTE_LIST, CSharpNodeType.EXPRESSION, CSharpNodeType.MODIFIER)));
        out.put(
                CSharpNodeType.PARAMETER_LIST,
                new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_LIST, CSharpNodeType.PARAMETER)));
        out.put(
                CSharpNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CSharpNodeType.BOOLEAN_LITERAL,
                                CSharpNodeType.CHARACTER_LITERAL,
                                CSharpNodeType.INTEGER_LITERAL,
                                CSharpNodeType.LVALUE_EXPRESSION,
                                CSharpNodeType.NON_LVALUE_EXPRESSION,
                                CSharpNodeType.UNARY_EXPRESSION)));
        out.put(CSharpNodeType.PARENTHESIZED_PATTERN, new ChildInfo(true, false, Set.of(CSharpNodeType.PATTERN)));
        out.put(
                CSharpNodeType.PARENTHESIZED_VARIABLE_DESIGNATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CSharpNodeType.DISCARD, CSharpNodeType.PARENTHESIZED_VARIABLE_DESIGNATION)));
        out.put(
                CSharpNodeType.POSITIONAL_PATTERN_CLAUSE,
                new ChildInfo(false, true, Set.of(CSharpNodeType.SUBPATTERN)));
        out.put(CSharpNodeType.POSTFIX_UNARY_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.PREFIX_UNARY_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.PREPROC_DEFINE, new ChildInfo(true, false, Set.of(CSharpNodeType.PREPROC_ARG)));
        out.put(
                CSharpNodeType.PREPROC_ELIF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.DECLARATION,
                                CSharpNodeType.ENUM_MEMBER_DECLARATION,
                                CSharpNodeType.EXPRESSION,
                                CSharpNodeType.EXTERN_ALIAS_DIRECTIVE,
                                CSharpNodeType.FILE_SCOPED_NAMESPACE_DECLARATION,
                                CSharpNodeType.GLOBAL_ATTRIBUTE,
                                CSharpNodeType.STATEMENT,
                                CSharpNodeType.TYPE_DECLARATION)));
        out.put(
                CSharpNodeType.PREPROC_ELSE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.DECLARATION,
                                CSharpNodeType.ENUM_MEMBER_DECLARATION,
                                CSharpNodeType.EXPRESSION,
                                CSharpNodeType.EXTERN_ALIAS_DIRECTIVE,
                                CSharpNodeType.FILE_SCOPED_NAMESPACE_DECLARATION,
                                CSharpNodeType.GLOBAL_ATTRIBUTE,
                                CSharpNodeType.STATEMENT,
                                CSharpNodeType.TYPE_DECLARATION)));
        out.put(CSharpNodeType.PREPROC_ERROR, new ChildInfo(true, false, Set.of(CSharpNodeType.PREPROC_ARG)));
        out.put(
                CSharpNodeType.PREPROC_IF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.DECLARATION,
                                CSharpNodeType.ENUM_MEMBER_DECLARATION,
                                CSharpNodeType.EXPRESSION,
                                CSharpNodeType.EXTERN_ALIAS_DIRECTIVE,
                                CSharpNodeType.FILE_SCOPED_NAMESPACE_DECLARATION,
                                CSharpNodeType.GLOBAL_ATTRIBUTE,
                                CSharpNodeType.STATEMENT,
                                CSharpNodeType.TYPE_DECLARATION)));
        out.put(
                CSharpNodeType.PREPROC_LINE,
                new ChildInfo(false, true, Set.of(CSharpNodeType.INTEGER_LITERAL, CSharpNodeType.STRING_LITERAL)));
        out.put(
                CSharpNodeType.PREPROC_PRAGMA,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.IDENTIFIER,
                                CSharpNodeType.INTEGER_LITERAL,
                                CSharpNodeType.STRING_LITERAL)));
        out.put(CSharpNodeType.PREPROC_UNDEF, new ChildInfo(true, false, Set.of(CSharpNodeType.PREPROC_ARG)));
        out.put(CSharpNodeType.PREPROC_WARNING, new ChildInfo(true, false, Set.of(CSharpNodeType.PREPROC_ARG)));
        out.put(
                CSharpNodeType.PRIMARY_CONSTRUCTOR_BASE_TYPE,
                new ChildInfo(true, false, Set.of(CSharpNodeType.ARGUMENT_LIST)));
        out.put(
                CSharpNodeType.PROPERTY_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.EXPLICIT_INTERFACE_SPECIFIER,
                                CSharpNodeType.MODIFIER)));
        out.put(CSharpNodeType.PROPERTY_PATTERN_CLAUSE, new ChildInfo(false, true, Set.of(CSharpNodeType.SUBPATTERN)));
        out.put(
                CSharpNodeType.QUERY_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.FROM_CLAUSE,
                                CSharpNodeType.GROUP_CLAUSE,
                                CSharpNodeType.IDENTIFIER,
                                CSharpNodeType.JOIN_CLAUSE,
                                CSharpNodeType.LET_CLAUSE,
                                CSharpNodeType.ORDER_BY_CLAUSE,
                                CSharpNodeType.SELECT_CLAUSE,
                                CSharpNodeType.WHERE_CLAUSE)));
        out.put(CSharpNodeType.RANGE_EXPRESSION, new ChildInfo(false, true, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.RAW_STRING_LITERAL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.RAW_STRING_CONTENT,
                                CSharpNodeType.RAW_STRING_END,
                                CSharpNodeType.RAW_STRING_START)));
        out.put(
                CSharpNodeType.RECORD_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.BASE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.PARAMETER_LIST,
                                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE,
                                CSharpNodeType.TYPE_PARAMETER_LIST)));
        out.put(
                CSharpNodeType.RECURSIVE_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CSharpNodeType.DISCARD,
                                CSharpNodeType.PARENTHESIZED_VARIABLE_DESIGNATION,
                                CSharpNodeType.POSITIONAL_PATTERN_CLAUSE,
                                CSharpNodeType.PROPERTY_PATTERN_CLAUSE)));
        out.put(CSharpNodeType.REFTYPE_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.REF_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.RELATIONAL_PATTERN, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.RETURN_STATEMENT, new ChildInfo(false, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.SELECT_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.STACKALLOC_EXPRESSION,
                new ChildInfo(false, false, Set.of(CSharpNodeType.INITIALIZER_EXPRESSION)));
        out.put(
                CSharpNodeType.STRING_LITERAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ESCAPE_SEQUENCE,
                                CSharpNodeType.STRING_LITERAL_CONTENT,
                                CSharpNodeType.STRING_LITERAL_ENCODING)));
        out.put(
                CSharpNodeType.STRUCT_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.ATTRIBUTE_LIST,
                                CSharpNodeType.BASE_LIST,
                                CSharpNodeType.MODIFIER,
                                CSharpNodeType.PARAMETER_LIST,
                                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE,
                                CSharpNodeType.TYPE_PARAMETER_LIST)));
        out.put(
                CSharpNodeType.SUBPATTERN,
                new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.PATTERN)));
        out.put(CSharpNodeType.SWITCH_BODY, new ChildInfo(false, true, Set.of(CSharpNodeType.SWITCH_SECTION)));
        out.put(
                CSharpNodeType.SWITCH_EXPRESSION,
                new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.SWITCH_EXPRESSION_ARM)));
        out.put(
                CSharpNodeType.SWITCH_EXPRESSION_ARM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.PATTERN, CSharpNodeType.WHEN_CLAUSE)));
        out.put(
                CSharpNodeType.SWITCH_SECTION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.EXPRESSION,
                                CSharpNodeType.PATTERN,
                                CSharpNodeType.STATEMENT,
                                CSharpNodeType.WHEN_CLAUSE)));
        out.put(CSharpNodeType.THROW_EXPRESSION, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.THROW_STATEMENT, new ChildInfo(false, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.TRY_STATEMENT,
                new ChildInfo(false, true, Set.of(CSharpNodeType.CATCH_CLAUSE, CSharpNodeType.FINALLY_CLAUSE)));
        out.put(CSharpNodeType.TUPLE_EXPRESSION, new ChildInfo(true, true, Set.of(CSharpNodeType.ARGUMENT)));
        out.put(
                CSharpNodeType.TUPLE_PATTERN,
                new ChildInfo(false, true, Set.of(CSharpNodeType.DISCARD, CSharpNodeType.TUPLE_PATTERN)));
        out.put(CSharpNodeType.TUPLE_TYPE, new ChildInfo(true, true, Set.of(CSharpNodeType.TUPLE_ELEMENT)));
        out.put(CSharpNodeType.TYPE_ARGUMENT_LIST, new ChildInfo(false, true, Set.of(CSharpNodeType.TYPE)));
        out.put(CSharpNodeType.TYPE_PARAMETER, new ChildInfo(false, true, Set.of(CSharpNodeType.ATTRIBUTE_LIST)));
        out.put(
                CSharpNodeType.TYPE_PARAMETER_CONSTRAINT,
                new ChildInfo(false, false, Set.of(CSharpNodeType.CONSTRUCTOR_CONSTRAINT)));
        out.put(
                CSharpNodeType.TYPE_PARAMETER_CONSTRAINTS_CLAUSE,
                new ChildInfo(true, true, Set.of(CSharpNodeType.IDENTIFIER, CSharpNodeType.TYPE_PARAMETER_CONSTRAINT)));
        out.put(CSharpNodeType.TYPE_PARAMETER_LIST, new ChildInfo(true, true, Set.of(CSharpNodeType.TYPE_PARAMETER)));
        out.put(CSharpNodeType.UNSAFE_STATEMENT, new ChildInfo(true, false, Set.of(CSharpNodeType.BLOCK)));
        out.put(CSharpNodeType.USING_DIRECTIVE, new ChildInfo(true, false, Set.of(CSharpNodeType.TYPE)));
        out.put(
                CSharpNodeType.USING_STATEMENT,
                new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.VARIABLE_DECLARATION)));
        out.put(
                CSharpNodeType.VARIABLE_DECLARATION,
                new ChildInfo(true, true, Set.of(CSharpNodeType.VARIABLE_DECLARATOR)));
        out.put(
                CSharpNodeType.VARIABLE_DECLARATOR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CSharpNodeType.BRACKETED_ARGUMENT_LIST,
                                CSharpNodeType.EXPRESSION,
                                CSharpNodeType.TUPLE_PATTERN)));
        out.put(
                CSharpNodeType.VAR_PATTERN,
                new ChildInfo(
                        false,
                        false,
                        Set.of(CSharpNodeType.DISCARD, CSharpNodeType.PARENTHESIZED_VARIABLE_DESIGNATION)));
        out.put(CSharpNodeType.WHEN_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(CSharpNodeType.WHERE_CLAUSE, new ChildInfo(true, false, Set.of(CSharpNodeType.EXPRESSION)));
        out.put(
                CSharpNodeType.WITH_EXPRESSION,
                new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.WITH_INITIALIZER)));
        out.put(
                CSharpNodeType.WITH_INITIALIZER,
                new ChildInfo(true, true, Set.of(CSharpNodeType.EXPRESSION, CSharpNodeType.IDENTIFIER)));
        out.put(CSharpNodeType.YIELD_STATEMENT, new ChildInfo(false, false, Set.of(CSharpNodeType.EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<CSharpNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<CSharpNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<CSharpNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<CSharpNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
