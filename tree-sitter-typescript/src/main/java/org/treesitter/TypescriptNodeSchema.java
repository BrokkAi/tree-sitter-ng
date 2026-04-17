package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code typescript} from tree-sitter {@code node-types.json}.
 */
public final class TypescriptNodeSchema {
    private TypescriptNodeSchema() {}

    public static Set<TypescriptNodeField> fields(@Nullable TypescriptNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<TypescriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<TypescriptNodeType> allowedTypes(
            @Nullable TypescriptNodeType owner, @Nullable TypescriptNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<TypescriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable TypescriptNodeType owner, @Nullable TypescriptNodeField field) {
        if (owner == null || field == null) return false;
        Map<TypescriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable TypescriptNodeType owner, @Nullable TypescriptNodeField field) {
        if (owner == null || field == null) return false;
        Map<TypescriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<TypescriptNodeType> allowedChildTypes(@Nullable TypescriptNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable TypescriptNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable TypescriptNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<TypescriptNodeType, Map<TypescriptNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<TypescriptNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<TypescriptNodeType, Map<TypescriptNodeField, FieldInfo>> initFields() {
        EnumMap<TypescriptNodeType, Map<TypescriptNodeField, FieldInfo>> out = new EnumMap<>(TypescriptNodeType.class);
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.CLASS_BODY)));
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.ABSTRACT_CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.ABSTRACT_METHOD_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.BODY,
                    new FieldInfo(
                            true, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(TypescriptNodeField.PARAMETER, new FieldInfo(false, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.ARROW_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.ARRAY_PATTERN,
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.MEMBER_EXPRESSION,
                                    TypescriptNodeType.NON_NULL_EXPRESSION,
                                    TypescriptNodeType.OBJECT_PATTERN,
                                    TypescriptNodeType.PARENTHESIZED_EXPRESSION,
                                    TypescriptNodeType.SUBSCRIPT_EXPRESSION,
                                    TypescriptNodeType.UNDEFINED)));
            m.put(TypescriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.LEFT, new FieldInfo(true, false, Set.of(TypescriptNodeType.PATTERN)));
            m.put(TypescriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.ASSIGNMENT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.MEMBER_EXPRESSION,
                                    TypescriptNodeType.NON_NULL_EXPRESSION,
                                    TypescriptNodeType.PARENTHESIZED_EXPRESSION,
                                    TypescriptNodeType.SUBSCRIPT_EXPRESSION)));
            m.put(TypescriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(TypescriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.AUGMENTED_ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER)));
            m.put(TypescriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(TypescriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.LABEL,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.STATEMENT_IDENTIFIER)));
            out.put(TypescriptNodeType.BREAK_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.ARGUMENTS,
                    new FieldInfo(
                            true, false, Set.of(TypescriptNodeType.ARGUMENTS, TypescriptNodeType.TEMPLATE_STRING)));
            m.put(
                    TypescriptNodeField.FUNCTION,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.IMPORT_)));
            m.put(
                    TypescriptNodeField.TYPE_ARGUMENTS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ARGUMENTS)));
            out.put(TypescriptNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.CALL_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(
                    TypescriptNodeField.PARAMETER,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ARRAY_PATTERN,
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.OBJECT_PATTERN)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            out.put(TypescriptNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.CLASS_BODY)));
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.CLASS_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            out.put(TypescriptNodeType.CLASS_BODY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.CLASS_BODY)));
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            out.put(TypescriptNodeType.CLASS_STATIC_BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            m.put(TypescriptNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            m.put(TypescriptNodeField.LEFT, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            m.put(TypescriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            out.put(TypescriptNodeType.CONDITIONAL_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.CONSTRUCTOR_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.CONSTRUCT_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.LABEL,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.STATEMENT_IDENTIFIER)));
            out.put(TypescriptNodeType.CONTINUE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
            m.put(
                    TypescriptNodeField.CONDITION,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TypescriptNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.ENUM_ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            out.put(TypescriptNodeType.ENUM_BODY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.ENUM_BODY)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            out.put(TypescriptNodeType.ENUM_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.ALIAS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.STRING)));
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.STRING)));
            out.put(TypescriptNodeType.EXPORT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.DECLARATION, new FieldInfo(false, false, Set.of(TypescriptNodeType.DECLARATION)));
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            m.put(TypescriptNodeField.SOURCE, new FieldInfo(false, false, Set.of(TypescriptNodeType.STRING)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.EXPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.TYPE_ARGUMENTS,
                    new FieldInfo(false, true, Set.of(TypescriptNodeType.TYPE_ARGUMENTS)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(true, true, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.EXTENDS_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.TYPE,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    TypescriptNodeType.GENERIC_TYPE,
                                    TypescriptNodeType.NESTED_TYPE_IDENTIFIER,
                                    TypescriptNodeType.TYPE_IDENTIFIER)));
            out.put(TypescriptNodeType.EXTENDS_TYPE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            out.put(TypescriptNodeType.FINALLY_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
            m.put(TypescriptNodeField.KIND, new FieldInfo(false, false, Collections.emptySet()));
            m.put(
                    TypescriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.ARRAY_PATTERN,
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.MEMBER_EXPRESSION,
                                    TypescriptNodeType.NON_NULL_EXPRESSION,
                                    TypescriptNodeType.OBJECT_PATTERN,
                                    TypescriptNodeType.PARENTHESIZED_EXPRESSION,
                                    TypescriptNodeType.SUBSCRIPT_EXPRESSION,
                                    TypescriptNodeType.UNDEFINED)));
            m.put(TypescriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    TypescriptNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SEQUENCE_EXPRESSION)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.FOR_IN_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
            m.put(
                    TypescriptNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    TypescriptNodeType.EMPTY_STATEMENT,
                                    TypescriptNodeType.EXPRESSION,
                                    TypescriptNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    TypescriptNodeField.INCREMENT,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    TypescriptNodeField.INITIALIZER,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.EMPTY_STATEMENT,
                                    TypescriptNodeType.EXPRESSION,
                                    TypescriptNodeType.LEXICAL_DECLARATION,
                                    TypescriptNodeType.SEQUENCE_EXPRESSION,
                                    TypescriptNodeType.VARIABLE_DECLARATION)));
            out.put(TypescriptNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(false, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.FUNCTION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.FUNCTION_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS,
                                    TypescriptNodeType.TYPE,
                                    TypescriptNodeType.TYPE_PREDICATE)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.FUNCTION_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(false, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.GENERATOR_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.GENERATOR_FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TypescriptNodeType.NESTED_TYPE_IDENTIFIER, TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(
                    TypescriptNodeField.TYPE_ARGUMENTS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_ARGUMENTS)));
            out.put(TypescriptNodeType.GENERIC_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(TypescriptNodeType.ELSE_CLAUSE)));
            m.put(
                    TypescriptNodeField.CONDITION,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.PARENTHESIZED_EXPRESSION)));
            m.put(TypescriptNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
            out.put(TypescriptNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.SOURCE, new FieldInfo(true, false, Set.of(TypescriptNodeType.STRING)));
            out.put(TypescriptNodeType.IMPORT_REQUIRE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.ALIAS, new FieldInfo(false, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.STRING)));
            out.put(TypescriptNodeType.IMPORT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.SOURCE, new FieldInfo(false, false, Set.of(TypescriptNodeType.STRING)));
            out.put(TypescriptNodeType.IMPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.INDEX_TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(false, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(TypescriptNodeField.SIGN, new FieldInfo(false, false, Collections.emptySet()));
            m.put(
                    TypescriptNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.ADDING_TYPE_ANNOTATION,
                                    TypescriptNodeType.OMITTING_TYPE_ANNOTATION,
                                    TypescriptNodeType.OPTING_TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION)));
            out.put(TypescriptNodeType.INDEX_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.FUNCTION,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.IMPORT_,
                                    TypescriptNodeType.MEMBER_EXPRESSION,
                                    TypescriptNodeType.SUBSCRIPT_EXPRESSION)));
            m.put(
                    TypescriptNodeField.TYPE_ARGUMENTS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_ARGUMENTS)));
            out.put(TypescriptNodeType.INSTANTIATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.INTERFACE_BODY)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.INTERFACE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(false, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.NESTED_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            out.put(TypescriptNodeType.INTERNAL_MODULE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
            m.put(
                    TypescriptNodeField.LABEL,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_IDENTIFIER)));
            out.put(TypescriptNodeType.LABELED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.KIND, new FieldInfo(true, false, Collections.emptySet()));
            out.put(TypescriptNodeType.LEXICAL_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.ALIAS, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            out.put(TypescriptNodeType.MAPPED_TYPE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.OBJECT,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.IMPORT_)));
            m.put(
                    TypescriptNodeField.OPTIONAL_CHAIN,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.OPTIONAL_CHAIN)));
            m.put(
                    TypescriptNodeField.PROPERTY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER)));
            out.put(TypescriptNodeType.MEMBER_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.METHOD_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(
                    TypescriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.FORMAL_PARAMETERS)));
            m.put(
                    TypescriptNodeField.RETURN_TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    TypescriptNodeType.ASSERTS_ANNOTATION,
                                    TypescriptNodeType.TYPE_ANNOTATION,
                                    TypescriptNodeType.TYPE_PREDICATE_ANNOTATION)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            out.put(TypescriptNodeType.METHOD_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(false, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.NESTED_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            out.put(TypescriptNodeType.MODULE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.OBJECT,
                    new FieldInfo(
                            true, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.MEMBER_EXPRESSION)));
            m.put(
                    TypescriptNodeField.PROPERTY,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.PROPERTY_IDENTIFIER)));
            out.put(TypescriptNodeType.NESTED_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.MODULE,
                    new FieldInfo(
                            true, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.NESTED_IDENTIFIER)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            out.put(TypescriptNodeType.NESTED_TYPE_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(TypescriptNodeType.ARGUMENTS)));
            m.put(
                    TypescriptNodeField.CONSTRUCTOR,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.PRIMARY_EXPRESSION)));
            m.put(
                    TypescriptNodeField.TYPE_ARGUMENTS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ARGUMENTS)));
            out.put(TypescriptNodeType.NEW_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.ARRAY_PATTERN,
                                    TypescriptNodeType.OBJECT_PATTERN,
                                    TypescriptNodeType.SHORTHAND_PROPERTY_IDENTIFIER_PATTERN)));
            m.put(TypescriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.OBJECT_ASSIGNMENT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(false, false, Set.of(TypescriptNodeType.IDENTIFIER)));
            m.put(
                    TypescriptNodeField.PATTERN,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.PATTERN, TypescriptNodeType.THIS_)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.OPTIONAL_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.KEY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.KEY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(
                    TypescriptNodeField.VALUE,
                    new FieldInfo(
                            true, false, Set.of(TypescriptNodeType.ASSIGNMENT_PATTERN, TypescriptNodeType.PATTERN)));
            out.put(TypescriptNodeType.PAIR_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            out.put(TypescriptNodeType.PARENTHESIZED_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            out.put(TypescriptNodeType.PROPERTY_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.PROPERTY_IDENTIFIER,
                                    TypescriptNodeType.STRING)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.PUBLIC_FIELD_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.FLAGS, new FieldInfo(false, false, Set.of(TypescriptNodeType.REGEX_FLAGS)));
            m.put(TypescriptNodeField.PATTERN, new FieldInfo(true, false, Set.of(TypescriptNodeType.REGEX_PATTERN)));
            out.put(TypescriptNodeType.REGEX, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(TypescriptNodeType.DECORATOR)));
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            false, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.REST_PATTERN)));
            m.put(
                    TypescriptNodeField.PATTERN,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.PATTERN, TypescriptNodeType.THIS_)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.REQUIRED_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.INDEX,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.EXPRESSION,
                                    TypescriptNodeType.NUMBER,
                                    TypescriptNodeType.PREDEFINED_TYPE,
                                    TypescriptNodeType.SEQUENCE_EXPRESSION,
                                    TypescriptNodeType.STRING)));
            m.put(TypescriptNodeField.OBJECT, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            m.put(
                    TypescriptNodeField.OPTIONAL_CHAIN,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.OPTIONAL_CHAIN)));
            out.put(TypescriptNodeType.SUBSCRIPT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(false, true, Set.of(TypescriptNodeType.STATEMENT)));
            m.put(
                    TypescriptNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SEQUENCE_EXPRESSION)));
            out.put(TypescriptNodeType.SWITCH_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(false, true, Set.of(TypescriptNodeType.STATEMENT)));
            out.put(TypescriptNodeType.SWITCH_DEFAULT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.SWITCH_BODY)));
            m.put(
                    TypescriptNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TypescriptNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            m.put(TypescriptNodeField.CONDITION, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            m.put(TypescriptNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.TERNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT_BLOCK)));
            m.put(
                    TypescriptNodeField.FINALIZER,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.FINALLY_CLAUSE)));
            m.put(TypescriptNodeField.HANDLER, new FieldInfo(false, false, Set.of(TypescriptNodeType.CATCH_CLAUSE)));
            out.put(TypescriptNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(
                    TypescriptNodeField.TYPE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_PARAMETERS)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            out.put(TypescriptNodeType.TYPE_ALIAS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.CONSTRAINT, new FieldInfo(false, false, Set.of(TypescriptNodeType.CONSTRAINT)));
            m.put(TypescriptNodeField.NAME, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE_IDENTIFIER)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(false, false, Set.of(TypescriptNodeType.DEFAULT_TYPE)));
            out.put(TypescriptNodeType.TYPE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.THIS_)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
            out.put(TypescriptNodeType.TYPE_PREDICATE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.ARGUMENT,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.NUMBER)));
            m.put(TypescriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(TypescriptNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
            m.put(TypescriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(TypescriptNodeType.UPDATE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(
                    TypescriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    TypescriptNodeType.ARRAY_PATTERN,
                                    TypescriptNodeType.IDENTIFIER,
                                    TypescriptNodeType.OBJECT_PATTERN)));
            m.put(TypescriptNodeField.TYPE, new FieldInfo(false, false, Set.of(TypescriptNodeType.TYPE_ANNOTATION)));
            m.put(TypescriptNodeField.VALUE, new FieldInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
            out.put(TypescriptNodeType.VARIABLE_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
            m.put(
                    TypescriptNodeField.CONDITION,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TypescriptNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<TypescriptNodeField, FieldInfo> m = new EnumMap<>(TypescriptNodeField.class);
            m.put(TypescriptNodeField.BODY, new FieldInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
            m.put(
                    TypescriptNodeField.OBJECT,
                    new FieldInfo(true, false, Set.of(TypescriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(TypescriptNodeType.WITH_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<TypescriptNodeType, ChildInfo> initChildren() {
        EnumMap<TypescriptNodeType, ChildInfo> out = new EnumMap<>(TypescriptNodeType.class);
        out.put(
                TypescriptNodeType.ABSTRACT_CLASS_DECLARATION,
                new ChildInfo(false, false, Set.of(TypescriptNodeType.CLASS_HERITAGE)));
        out.put(
                TypescriptNodeType.ABSTRACT_METHOD_SIGNATURE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.ACCESSIBILITY_MODIFIER, TypescriptNodeType.OVERRIDE_MODIFIER)));
        out.put(TypescriptNodeType.ADDING_TYPE_ANNOTATION, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.AMBIENT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                TypescriptNodeType.DECLARATION,
                                TypescriptNodeType.PROPERTY_IDENTIFIER,
                                TypescriptNodeType.STATEMENT_BLOCK,
                                TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.ARGUMENTS,
                new ChildInfo(false, true, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SPREAD_ELEMENT)));
        out.put(
                TypescriptNodeType.ARRAY,
                new ChildInfo(false, true, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SPREAD_ELEMENT)));
        out.put(
                TypescriptNodeType.ARRAY_PATTERN,
                new ChildInfo(false, true, Set.of(TypescriptNodeType.ASSIGNMENT_PATTERN, TypescriptNodeType.PATTERN)));
        out.put(TypescriptNodeType.ARRAY_TYPE, new ChildInfo(true, false, Set.of(TypescriptNodeType.PRIMARY_TYPE)));
        out.put(
                TypescriptNodeType.ASSERTS,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TypescriptNodeType.IDENTIFIER,
                                TypescriptNodeType.THIS_,
                                TypescriptNodeType.TYPE_PREDICATE)));
        out.put(TypescriptNodeType.ASSERTS_ANNOTATION, new ChildInfo(true, false, Set.of(TypescriptNodeType.ASSERTS)));
        out.put(
                TypescriptNodeType.AS_EXPRESSION,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.TYPE)));
        out.put(TypescriptNodeType.AWAIT_EXPRESSION, new ChildInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
        out.put(TypescriptNodeType.CLASS_, new ChildInfo(false, false, Set.of(TypescriptNodeType.CLASS_HERITAGE)));
        out.put(
                TypescriptNodeType.CLASS_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.ABSTRACT_METHOD_SIGNATURE,
                                TypescriptNodeType.CLASS_STATIC_BLOCK,
                                TypescriptNodeType.INDEX_SIGNATURE,
                                TypescriptNodeType.METHOD_DEFINITION,
                                TypescriptNodeType.METHOD_SIGNATURE,
                                TypescriptNodeType.PUBLIC_FIELD_DEFINITION)));
        out.put(
                TypescriptNodeType.CLASS_DECLARATION,
                new ChildInfo(false, false, Set.of(TypescriptNodeType.CLASS_HERITAGE)));
        out.put(
                TypescriptNodeType.CLASS_HERITAGE,
                new ChildInfo(
                        true, true, Set.of(TypescriptNodeType.EXTENDS_CLAUSE, TypescriptNodeType.IMPLEMENTS_CLAUSE)));
        out.put(
                TypescriptNodeType.COMPUTED_PROPERTY_NAME,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
        out.put(TypescriptNodeType.CONSTRAINT, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.DECORATOR,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TypescriptNodeType.CALL_EXPRESSION,
                                TypescriptNodeType.IDENTIFIER,
                                TypescriptNodeType.MEMBER_EXPRESSION,
                                TypescriptNodeType.PARENTHESIZED_EXPRESSION)));
        out.put(TypescriptNodeType.DEFAULT_TYPE, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(TypescriptNodeType.ELSE_CLAUSE, new ChildInfo(true, false, Set.of(TypescriptNodeType.STATEMENT)));
        out.put(TypescriptNodeType.ENUM_BODY, new ChildInfo(false, true, Set.of(TypescriptNodeType.ENUM_ASSIGNMENT)));
        out.put(
                TypescriptNodeType.EXPORT_CLAUSE,
                new ChildInfo(false, true, Set.of(TypescriptNodeType.EXPORT_SPECIFIER)));
        out.put(
                TypescriptNodeType.EXPORT_STATEMENT,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                TypescriptNodeType.EXPORT_CLAUSE,
                                TypescriptNodeType.EXPRESSION,
                                TypescriptNodeType.IDENTIFIER,
                                TypescriptNodeType.NAMESPACE_EXPORT)));
        out.put(
                TypescriptNodeType.EXPRESSION_STATEMENT,
                new ChildInfo(
                        true, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                TypescriptNodeType.FLOW_MAYBE_TYPE,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.PRIMARY_TYPE)));
        out.put(
                TypescriptNodeType.FORMAL_PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.OPTIONAL_PARAMETER, TypescriptNodeType.REQUIRED_PARAMETER)));
        out.put(TypescriptNodeType.IMPLEMENTS_CLAUSE, new ChildInfo(true, true, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.IMPORT_ALIAS,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.NESTED_IDENTIFIER)));
        out.put(TypescriptNodeType.IMPORT_ATTRIBUTE, new ChildInfo(true, false, Set.of(TypescriptNodeType.OBJECT)));
        out.put(
                TypescriptNodeType.IMPORT_CLAUSE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                TypescriptNodeType.IDENTIFIER,
                                TypescriptNodeType.NAMED_IMPORTS,
                                TypescriptNodeType.NAMESPACE_IMPORT)));
        out.put(
                TypescriptNodeType.IMPORT_REQUIRE_CLAUSE,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER)));
        out.put(
                TypescriptNodeType.IMPORT_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.IMPORT_ATTRIBUTE,
                                TypescriptNodeType.IMPORT_CLAUSE,
                                TypescriptNodeType.IMPORT_REQUIRE_CLAUSE)));
        out.put(
                TypescriptNodeType.INDEX_SIGNATURE,
                new ChildInfo(false, false, Set.of(TypescriptNodeType.MAPPED_TYPE_CLAUSE)));
        out.put(
                TypescriptNodeType.INDEX_TYPE_QUERY,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.PRIMARY_TYPE)));
        out.put(
                TypescriptNodeType.INFER_TYPE,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.TYPE, TypescriptNodeType.TYPE_IDENTIFIER)));
        out.put(
                TypescriptNodeType.INSTANTIATION_EXPRESSION,
                new ChildInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
        out.put(
                TypescriptNodeType.INTERFACE_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.CALL_SIGNATURE,
                                TypescriptNodeType.CONSTRUCT_SIGNATURE,
                                TypescriptNodeType.EXPORT_STATEMENT,
                                TypescriptNodeType.INDEX_SIGNATURE,
                                TypescriptNodeType.METHOD_SIGNATURE,
                                TypescriptNodeType.PROPERTY_SIGNATURE)));
        out.put(
                TypescriptNodeType.INTERFACE_DECLARATION,
                new ChildInfo(false, false, Set.of(TypescriptNodeType.EXTENDS_TYPE_CLAUSE)));
        out.put(TypescriptNodeType.INTERSECTION_TYPE, new ChildInfo(true, true, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.LEXICAL_DECLARATION,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.VARIABLE_DECLARATOR)));
        out.put(
                TypescriptNodeType.LITERAL_TYPE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TypescriptNodeType.FALSE,
                                TypescriptNodeType.NULL,
                                TypescriptNodeType.NUMBER,
                                TypescriptNodeType.STRING,
                                TypescriptNodeType.TRUE,
                                TypescriptNodeType.UNARY_EXPRESSION,
                                TypescriptNodeType.UNDEFINED)));
        out.put(TypescriptNodeType.LOOKUP_TYPE, new ChildInfo(true, true, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.METHOD_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.ACCESSIBILITY_MODIFIER, TypescriptNodeType.OVERRIDE_MODIFIER)));
        out.put(
                TypescriptNodeType.METHOD_SIGNATURE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.ACCESSIBILITY_MODIFIER, TypescriptNodeType.OVERRIDE_MODIFIER)));
        out.put(
                TypescriptNodeType.NAMED_IMPORTS,
                new ChildInfo(false, true, Set.of(TypescriptNodeType.IMPORT_SPECIFIER)));
        out.put(
                TypescriptNodeType.NAMESPACE_EXPORT,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER, TypescriptNodeType.STRING)));
        out.put(TypescriptNodeType.NAMESPACE_IMPORT, new ChildInfo(true, false, Set.of(TypescriptNodeType.IDENTIFIER)));
        out.put(
                TypescriptNodeType.NON_NULL_EXPRESSION,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
        out.put(
                TypescriptNodeType.OBJECT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.METHOD_DEFINITION,
                                TypescriptNodeType.PAIR,
                                TypescriptNodeType.SHORTHAND_PROPERTY_IDENTIFIER,
                                TypescriptNodeType.SPREAD_ELEMENT)));
        out.put(
                TypescriptNodeType.OBJECT_PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.OBJECT_ASSIGNMENT_PATTERN,
                                TypescriptNodeType.PAIR_PATTERN,
                                TypescriptNodeType.REST_PATTERN,
                                TypescriptNodeType.SHORTHAND_PROPERTY_IDENTIFIER_PATTERN)));
        out.put(
                TypescriptNodeType.OBJECT_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.CALL_SIGNATURE,
                                TypescriptNodeType.CONSTRUCT_SIGNATURE,
                                TypescriptNodeType.EXPORT_STATEMENT,
                                TypescriptNodeType.INDEX_SIGNATURE,
                                TypescriptNodeType.METHOD_SIGNATURE,
                                TypescriptNodeType.PROPERTY_SIGNATURE)));
        out.put(
                TypescriptNodeType.OMITTING_TYPE_ANNOTATION,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(TypescriptNodeType.OPTING_TYPE_ANNOTATION, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.OPTIONAL_PARAMETER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.ACCESSIBILITY_MODIFIER, TypescriptNodeType.OVERRIDE_MODIFIER)));
        out.put(TypescriptNodeType.OPTIONAL_TYPE, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TypescriptNodeType.CALL_EXPRESSION,
                                TypescriptNodeType.EXPRESSION,
                                TypescriptNodeType.IDENTIFIER,
                                TypescriptNodeType.MEMBER_EXPRESSION,
                                TypescriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(TypescriptNodeType.PARENTHESIZED_TYPE, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.PROGRAM,
                new ChildInfo(false, true, Set.of(TypescriptNodeType.HASH_BANG_LINE, TypescriptNodeType.STATEMENT)));
        out.put(
                TypescriptNodeType.PROPERTY_SIGNATURE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.ACCESSIBILITY_MODIFIER, TypescriptNodeType.OVERRIDE_MODIFIER)));
        out.put(
                TypescriptNodeType.PUBLIC_FIELD_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.ACCESSIBILITY_MODIFIER, TypescriptNodeType.OVERRIDE_MODIFIER)));
        out.put(TypescriptNodeType.READONLY_TYPE, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.REQUIRED_PARAMETER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(TypescriptNodeType.ACCESSIBILITY_MODIFIER, TypescriptNodeType.OVERRIDE_MODIFIER)));
        out.put(
                TypescriptNodeType.REST_PATTERN,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TypescriptNodeType.ARRAY_PATTERN,
                                TypescriptNodeType.IDENTIFIER,
                                TypescriptNodeType.MEMBER_EXPRESSION,
                                TypescriptNodeType.NON_NULL_EXPRESSION,
                                TypescriptNodeType.OBJECT_PATTERN,
                                TypescriptNodeType.SUBSCRIPT_EXPRESSION,
                                TypescriptNodeType.UNDEFINED)));
        out.put(TypescriptNodeType.REST_TYPE, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.RETURN_STATEMENT,
                new ChildInfo(
                        false, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                TypescriptNodeType.SATISFIES_EXPRESSION,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.SEQUENCE_EXPRESSION,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.EXPRESSION)));
        out.put(TypescriptNodeType.SPREAD_ELEMENT, new ChildInfo(true, false, Set.of(TypescriptNodeType.EXPRESSION)));
        out.put(TypescriptNodeType.STATEMENT_BLOCK, new ChildInfo(false, true, Set.of(TypescriptNodeType.STATEMENT)));
        out.put(
                TypescriptNodeType.STRING,
                new ChildInfo(
                        false, true, Set.of(TypescriptNodeType.ESCAPE_SEQUENCE, TypescriptNodeType.STRING_FRAGMENT)));
        out.put(
                TypescriptNodeType.SWITCH_BODY,
                new ChildInfo(false, true, Set.of(TypescriptNodeType.SWITCH_CASE, TypescriptNodeType.SWITCH_DEFAULT)));
        out.put(
                TypescriptNodeType.TEMPLATE_LITERAL_TYPE,
                new ChildInfo(
                        false, true, Set.of(TypescriptNodeType.STRING_FRAGMENT, TypescriptNodeType.TEMPLATE_TYPE)));
        out.put(
                TypescriptNodeType.TEMPLATE_STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.ESCAPE_SEQUENCE,
                                TypescriptNodeType.STRING_FRAGMENT,
                                TypescriptNodeType.TEMPLATE_SUBSTITUTION)));
        out.put(
                TypescriptNodeType.TEMPLATE_SUBSTITUTION,
                new ChildInfo(
                        true, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                TypescriptNodeType.TEMPLATE_TYPE,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.INFER_TYPE, TypescriptNodeType.PRIMARY_TYPE)));
        out.put(
                TypescriptNodeType.THROW_STATEMENT,
                new ChildInfo(
                        true, false, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                TypescriptNodeType.TUPLE_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                TypescriptNodeType.OPTIONAL_PARAMETER,
                                TypescriptNodeType.OPTIONAL_TYPE,
                                TypescriptNodeType.REQUIRED_PARAMETER,
                                TypescriptNodeType.REST_TYPE,
                                TypescriptNodeType.TYPE)));
        out.put(TypescriptNodeType.TYPE_ANNOTATION, new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE)));
        out.put(TypescriptNodeType.TYPE_ARGUMENTS, new ChildInfo(true, true, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.TYPE_ASSERTION,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.EXPRESSION, TypescriptNodeType.TYPE_ARGUMENTS)));
        out.put(
                TypescriptNodeType.TYPE_PARAMETERS,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.TYPE_PARAMETER)));
        out.put(
                TypescriptNodeType.TYPE_PREDICATE_ANNOTATION,
                new ChildInfo(true, false, Set.of(TypescriptNodeType.TYPE_PREDICATE)));
        out.put(
                TypescriptNodeType.TYPE_QUERY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                TypescriptNodeType.CALL_EXPRESSION,
                                TypescriptNodeType.IDENTIFIER,
                                TypescriptNodeType.INSTANTIATION_EXPRESSION,
                                TypescriptNodeType.MEMBER_EXPRESSION,
                                TypescriptNodeType.SUBSCRIPT_EXPRESSION,
                                TypescriptNodeType.THIS_)));
        out.put(TypescriptNodeType.UNION_TYPE, new ChildInfo(true, true, Set.of(TypescriptNodeType.TYPE)));
        out.put(
                TypescriptNodeType.VARIABLE_DECLARATION,
                new ChildInfo(true, true, Set.of(TypescriptNodeType.VARIABLE_DECLARATOR)));
        out.put(
                TypescriptNodeType.YIELD_EXPRESSION,
                new ChildInfo(false, false, Set.of(TypescriptNodeType.EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<TypescriptNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<TypescriptNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<TypescriptNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<TypescriptNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
