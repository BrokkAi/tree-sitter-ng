package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code php} from tree-sitter {@code node-types.json}.
 */
public final class PhpNodeSchema {
    private PhpNodeSchema() {}

    public static Set<PhpNodeField> fields(@Nullable PhpNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<PhpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<PhpNodeType> allowedTypes(@Nullable PhpNodeType owner, @Nullable PhpNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<PhpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable PhpNodeType owner, @Nullable PhpNodeField field) {
        if (owner == null || field == null) return false;
        Map<PhpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable PhpNodeType owner, @Nullable PhpNodeField field) {
        if (owner == null || field == null) return false;
        Map<PhpNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<PhpNodeType> allowedChildTypes(@Nullable PhpNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable PhpNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable PhpNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<PhpNodeType, Map<PhpNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<PhpNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<PhpNodeType, Map<PhpNodeField, FieldInfo>> initFields() {
        EnumMap<PhpNodeType, Map<PhpNodeField, FieldInfo>> out = new EnumMap<>(PhpNodeType.class);
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.DECLARATION_LIST)));
            out.put(PhpNodeType.ANONYMOUS_CLASS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.COMPOUND_STATEMENT)));
            m.put(PhpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(PhpNodeType.FORMAL_PARAMETERS)));
            m.put(PhpNodeField.REFERENCE_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.REFERENCE_MODIFIER)));
            m.put(
                    PhpNodeField.RETURN_TYPE,
                    new FieldInfo(false, false, Set.of(PhpNodeType.BOTTOM_TYPE, PhpNodeType.TYPE)));
            m.put(PhpNodeField.STATIC_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.STATIC_MODIFIER)));
            out.put(PhpNodeType.ANONYMOUS_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.NAME, new FieldInfo(false, false, Set.of(PhpNodeType.NAME)));
            m.put(PhpNodeField.REFERENCE_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.REFERENCE_MODIFIER)));
            out.put(PhpNodeType.ARGUMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(PhpNodeType.FORMAL_PARAMETERS)));
            m.put(PhpNodeField.REFERENCE_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.REFERENCE_MODIFIER)));
            m.put(
                    PhpNodeField.RETURN_TYPE,
                    new FieldInfo(false, false, Set.of(PhpNodeType.BOTTOM_TYPE, PhpNodeType.TYPE)));
            m.put(PhpNodeField.STATIC_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.STATIC_MODIFIER)));
            out.put(PhpNodeType.ARROW_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.LIST_LITERAL,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.RIGHT, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(PhpNodeType.ARGUMENTS)));
            out.put(PhpNodeType.ATTRIBUTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(PhpNodeField.RIGHT, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.AUGMENTED_ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.LEFT, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    PhpNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.EXPRESSION,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.VALUE, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.CASE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.TYPE, new FieldInfo(true, false, Set.of(PhpNodeType.CAST_TYPE)));
            m.put(
                    PhpNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.CLONE_EXPRESSION,
                                    PhpNodeType.ERROR_SUPPRESSION_EXPRESSION,
                                    PhpNodeType.INCLUDE_EXPRESSION,
                                    PhpNodeType.INCLUDE_ONCE_EXPRESSION,
                                    PhpNodeType.PRIMARY_EXPRESSION,
                                    PhpNodeType.UNARY_OP_EXPRESSION)));
            out.put(PhpNodeType.CAST_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.COMPOUND_STATEMENT)));
            m.put(PhpNodeField.NAME, new FieldInfo(false, false, Set.of(PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.TYPE, new FieldInfo(true, false, Set.of(PhpNodeType.TYPE_LIST)));
            out.put(PhpNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.DECLARATION_LIST)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.NAME)));
            out.put(PhpNodeType.CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.BODY, new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.CONDITION, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.CONDITIONAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.TYPE, new FieldInfo(false, false, Set.of(PhpNodeType.TYPE)));
            out.put(PhpNodeType.CONST_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.STATEMENT)));
            m.put(PhpNodeField.CONDITION, new FieldInfo(true, false, Set.of(PhpNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(PhpNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.BODY,
                    new FieldInfo(true, false, Set.of(PhpNodeType.COLON_BLOCK, PhpNodeType.STATEMENT)));
            out.put(PhpNodeType.ELSE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.BODY,
                    new FieldInfo(true, false, Set.of(PhpNodeType.COLON_BLOCK, PhpNodeType.STATEMENT)));
            m.put(PhpNodeField.CONDITION, new FieldInfo(true, false, Set.of(PhpNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(PhpNodeType.ELSE_IF_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.NAME)));
            m.put(PhpNodeField.VALUE, new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.ENUM_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.ENUM_DECLARATION_LIST)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.NAME)));
            out.put(PhpNodeType.ENUM_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.COMPOUND_STATEMENT)));
            out.put(PhpNodeType.FINALLY_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(PhpNodeType.COLON_BLOCK, PhpNodeType.STATEMENT)));
            out.put(PhpNodeType.FOREACH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(false, true, Set.of(PhpNodeType.STATEMENT)));
            m.put(
                    PhpNodeField.CONDITION,
                    new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION, PhpNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    PhpNodeField.INITIALIZE,
                    new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION, PhpNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    PhpNodeField.UPDATE,
                    new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION, PhpNodeType.SEQUENCE_EXPRESSION)));
            out.put(PhpNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(PhpNodeType.ARGUMENTS)));
            m.put(
                    PhpNodeField.FUNCTION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.ENCAPSED_STRING,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.HEREDOC,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NOWDOC,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.STRING,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.FUNCTION_CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.COMPOUND_STATEMENT)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.NAME)));
            m.put(PhpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(PhpNodeType.FORMAL_PARAMETERS)));
            m.put(
                    PhpNodeField.RETURN_TYPE,
                    new FieldInfo(false, false, Set.of(PhpNodeType.BOTTOM_TYPE, PhpNodeType.TYPE)));
            out.put(PhpNodeType.FUNCTION_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.END_TAG, new FieldInfo(true, false, Set.of(PhpNodeType.HEREDOC_END)));
            m.put(PhpNodeField.IDENTIFIER, new FieldInfo(true, false, Set.of(PhpNodeType.HEREDOC_START)));
            m.put(PhpNodeField.VALUE, new FieldInfo(false, false, Set.of(PhpNodeType.HEREDOC_BODY)));
            out.put(PhpNodeType.HEREDOC, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.ALTERNATIVE,
                    new FieldInfo(false, true, Set.of(PhpNodeType.ELSE_CLAUSE, PhpNodeType.ELSE_IF_CLAUSE)));
            m.put(
                    PhpNodeField.BODY,
                    new FieldInfo(true, false, Set.of(PhpNodeType.COLON_BLOCK, PhpNodeType.STATEMENT)));
            m.put(PhpNodeField.CONDITION, new FieldInfo(true, false, Set.of(PhpNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(PhpNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.DECLARATION_LIST)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.NAME)));
            out.put(PhpNodeType.INTERFACE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.CONDITIONAL_EXPRESSIONS,
                    new FieldInfo(true, false, Set.of(PhpNodeType.MATCH_CONDITION_LIST)));
            m.put(PhpNodeField.RETURN_EXPRESSION, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.MATCH_CONDITIONAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.RETURN_EXPRESSION, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.MATCH_DEFAULT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.MATCH_BLOCK)));
            m.put(PhpNodeField.CONDITION, new FieldInfo(true, false, Set.of(PhpNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(PhpNodeType.MATCH_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(
                    PhpNodeField.OBJECT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.ENCAPSED_STRING,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.HEREDOC,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NOWDOC,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.STRING,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.MEMBER_ACCESS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(PhpNodeType.ARGUMENTS)));
            m.put(
                    PhpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(
                    PhpNodeField.OBJECT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.ENCAPSED_STRING,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.HEREDOC,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NOWDOC,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.STRING,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.MEMBER_CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(false, false, Set.of(PhpNodeType.COMPOUND_STATEMENT)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.NAME)));
            m.put(PhpNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(PhpNodeType.FORMAL_PARAMETERS)));
            m.put(
                    PhpNodeField.RETURN_TYPE,
                    new FieldInfo(false, false, Set.of(PhpNodeType.BOTTOM_TYPE, PhpNodeType.TYPE)));
            out.put(PhpNodeType.METHOD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(false, false, Set.of(PhpNodeType.COMPOUND_STATEMENT)));
            m.put(PhpNodeField.NAME, new FieldInfo(false, false, Set.of(PhpNodeType.NAMESPACE_NAME)));
            out.put(PhpNodeType.NAMESPACE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ALIAS, new FieldInfo(false, false, Set.of(PhpNodeType.NAME)));
            m.put(PhpNodeField.TYPE, new FieldInfo(false, false, Collections.emptySet()));
            out.put(PhpNodeType.NAMESPACE_USE_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(false, false, Set.of(PhpNodeType.NAMESPACE_USE_GROUP)));
            m.put(PhpNodeField.TYPE, new FieldInfo(false, false, Collections.emptySet()));
            out.put(PhpNodeType.NAMESPACE_USE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.END_TAG, new FieldInfo(true, false, Set.of(PhpNodeType.HEREDOC_END)));
            m.put(PhpNodeField.IDENTIFIER, new FieldInfo(true, false, Set.of(PhpNodeType.HEREDOC_START)));
            m.put(PhpNodeField.VALUE, new FieldInfo(false, false, Set.of(PhpNodeType.NOWDOC_BODY)));
            out.put(PhpNodeType.NOWDOC, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(
                    PhpNodeField.OBJECT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.ENCAPSED_STRING,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.HEREDOC,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NOWDOC,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.STRING,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(PhpNodeType.ARGUMENTS)));
            m.put(
                    PhpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(
                    PhpNodeField.OBJECT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.ENCAPSED_STRING,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.HEREDOC,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NOWDOC,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.STRING,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.TYPE, new FieldInfo(false, false, Set.of(PhpNodeType.TYPE)));
            out.put(PhpNodeType.PROPERTY_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.DEFAULT_VALUE, new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.PROPERTY_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(
                    PhpNodeField.BODY,
                    new FieldInfo(false, false, Set.of(PhpNodeType.COMPOUND_STATEMENT, PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.FINAL_, new FieldInfo(false, false, Set.of(PhpNodeType.FINAL_MODIFIER)));
            m.put(PhpNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(PhpNodeType.FORMAL_PARAMETERS)));
            m.put(PhpNodeField.REFERENCE_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.REFERENCE_MODIFIER)));
            out.put(PhpNodeType.PROPERTY_HOOK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.DEFAULT_VALUE, new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.BY_REF, PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.READONLY, new FieldInfo(false, false, Set.of(PhpNodeType.READONLY_MODIFIER)));
            m.put(PhpNodeField.TYPE, new FieldInfo(false, false, Set.of(PhpNodeType.TYPE)));
            m.put(PhpNodeField.VISIBILITY, new FieldInfo(true, false, Set.of(PhpNodeType.VISIBILITY_MODIFIER)));
            out.put(PhpNodeType.PROPERTY_PROMOTION_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.PREFIX, new FieldInfo(true, true, Set.of(PhpNodeType.NAMESPACE_NAME)));
            out.put(PhpNodeType.QUALIFIED_NAME, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.LIST_LITERAL,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.RIGHT, new FieldInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.REFERENCE_ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.PREFIX, new FieldInfo(true, true, Set.of(PhpNodeType.NAMESPACE_NAME)));
            out.put(PhpNodeType.RELATIVE_NAME, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(PhpNodeType.ARGUMENTS)));
            m.put(
                    PhpNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(
                    PhpNodeField.SCOPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.ENCAPSED_STRING,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.HEREDOC,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NOWDOC,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.RELATIVE_SCOPE,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.STRING,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.SCOPED_CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.NAME,
                    new FieldInfo(true, false, Set.of(PhpNodeType.DYNAMIC_VARIABLE_NAME, PhpNodeType.VARIABLE_NAME)));
            m.put(
                    PhpNodeField.SCOPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.ENCAPSED_STRING,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.HEREDOC,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NAME,
                                    PhpNodeType.NOWDOC,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                    PhpNodeType.PARENTHESIZED_EXPRESSION,
                                    PhpNodeType.QUALIFIED_NAME,
                                    PhpNodeType.RELATIVE_NAME,
                                    PhpNodeType.RELATIVE_SCOPE,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.STRING,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            out.put(PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.DEFAULT_VALUE, new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.REFERENCE_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.REFERENCE_MODIFIER)));
            m.put(PhpNodeField.TYPE, new FieldInfo(false, false, Set.of(PhpNodeType.TYPE)));
            out.put(PhpNodeType.SIMPLE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.VALUE, new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
            out.put(PhpNodeType.STATIC_VARIABLE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.SWITCH_BLOCK)));
            m.put(PhpNodeField.CONDITION, new FieldInfo(true, false, Set.of(PhpNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(PhpNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.DECLARATION_LIST)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.NAME)));
            out.put(PhpNodeType.TRAIT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.BODY, new FieldInfo(true, false, Set.of(PhpNodeType.COMPOUND_STATEMENT)));
            out.put(PhpNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ARGUMENT, new FieldInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
            m.put(PhpNodeField.OPERATOR, new FieldInfo(false, false, Collections.emptySet()));
            out.put(PhpNodeType.UNARY_OP_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.ARGUMENT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    PhpNodeType.CAST_EXPRESSION,
                                    PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                    PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                    PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                    PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_CALL_EXPRESSION,
                                    PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                    PhpNodeType.SUBSCRIPT_EXPRESSION,
                                    PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(PhpNodeType.UPDATE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(PhpNodeField.ATTRIBUTES, new FieldInfo(false, false, Set.of(PhpNodeType.ATTRIBUTE_LIST)));
            m.put(PhpNodeField.NAME, new FieldInfo(true, false, Set.of(PhpNodeType.VARIABLE_NAME)));
            m.put(PhpNodeField.REFERENCE_MODIFIER, new FieldInfo(false, false, Set.of(PhpNodeType.REFERENCE_MODIFIER)));
            m.put(PhpNodeField.TYPE, new FieldInfo(false, false, Set.of(PhpNodeType.TYPE)));
            out.put(PhpNodeType.VARIADIC_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<PhpNodeField, FieldInfo> m = new EnumMap<>(PhpNodeField.class);
            m.put(
                    PhpNodeField.BODY,
                    new FieldInfo(true, false, Set.of(PhpNodeType.COLON_BLOCK, PhpNodeType.STATEMENT)));
            m.put(PhpNodeField.CONDITION, new FieldInfo(true, false, Set.of(PhpNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(PhpNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<PhpNodeType, ChildInfo> initChildren() {
        EnumMap<PhpNodeType, ChildInfo> out = new EnumMap<>(PhpNodeType.class);
        out.put(
                PhpNodeType.ANONYMOUS_CLASS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.ABSTRACT_MODIFIER,
                                PhpNodeType.ARGUMENTS,
                                PhpNodeType.BASE_CLAUSE,
                                PhpNodeType.CLASS_INTERFACE_CLAUSE,
                                PhpNodeType.FINAL_MODIFIER,
                                PhpNodeType.READONLY_MODIFIER,
                                PhpNodeType.STATIC_MODIFIER,
                                PhpNodeType.VAR_MODIFIER,
                                PhpNodeType.VISIBILITY_MODIFIER)));
        out.put(
                PhpNodeType.ANONYMOUS_FUNCTION,
                new ChildInfo(false, false, Set.of(PhpNodeType.ANONYMOUS_FUNCTION_USE_CLAUSE)));
        out.put(
                PhpNodeType.ANONYMOUS_FUNCTION_USE_CLAUSE,
                new ChildInfo(true, true, Set.of(PhpNodeType.BY_REF, PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.ARGUMENT,
                new ChildInfo(
                        true, false, Set.of(PhpNodeType.EXPRESSION, PhpNodeType.NAME, PhpNodeType.VARIADIC_UNPACKING)));
        out.put(
                PhpNodeType.ARGUMENTS,
                new ChildInfo(false, true, Set.of(PhpNodeType.ARGUMENT, PhpNodeType.VARIADIC_PLACEHOLDER)));
        out.put(
                PhpNodeType.ARRAY_CREATION_EXPRESSION,
                new ChildInfo(false, true, Set.of(PhpNodeType.ARRAY_ELEMENT_INITIALIZER)));
        out.put(
                PhpNodeType.ARRAY_ELEMENT_INITIALIZER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(PhpNodeType.BY_REF, PhpNodeType.EXPRESSION, PhpNodeType.VARIADIC_UNPACKING)));
        out.put(
                PhpNodeType.ATTRIBUTE,
                new ChildInfo(
                        true, false, Set.of(PhpNodeType.NAME, PhpNodeType.QUALIFIED_NAME, PhpNodeType.RELATIVE_NAME)));
        out.put(PhpNodeType.ATTRIBUTE_GROUP, new ChildInfo(true, true, Set.of(PhpNodeType.ATTRIBUTE)));
        out.put(PhpNodeType.ATTRIBUTE_LIST, new ChildInfo(true, true, Set.of(PhpNodeType.ATTRIBUTE_GROUP)));
        out.put(
                PhpNodeType.BASE_CLAUSE,
                new ChildInfo(
                        true, true, Set.of(PhpNodeType.NAME, PhpNodeType.QUALIFIED_NAME, PhpNodeType.RELATIVE_NAME)));
        out.put(PhpNodeType.BREAK_STATEMENT, new ChildInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(
                PhpNodeType.BY_REF,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                PhpNodeType.CAST_EXPRESSION,
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.MEMBER_CALL_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(PhpNodeType.CASE_STATEMENT, new ChildInfo(false, true, Set.of(PhpNodeType.STATEMENT)));
        out.put(
                PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                PhpNodeType.CAST_EXPRESSION,
                                PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.ENCAPSED_STRING,
                                PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                PhpNodeType.HEREDOC,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.MEMBER_CALL_EXPRESSION,
                                PhpNodeType.NAME,
                                PhpNodeType.NOWDOC,
                                PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                PhpNodeType.PARENTHESIZED_EXPRESSION,
                                PhpNodeType.QUALIFIED_NAME,
                                PhpNodeType.RELATIVE_NAME,
                                PhpNodeType.RELATIVE_SCOPE,
                                PhpNodeType.SCOPED_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                PhpNodeType.STRING,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.CLASS_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.ABSTRACT_MODIFIER,
                                PhpNodeType.BASE_CLAUSE,
                                PhpNodeType.CLASS_INTERFACE_CLAUSE,
                                PhpNodeType.FINAL_MODIFIER,
                                PhpNodeType.READONLY_MODIFIER,
                                PhpNodeType.STATIC_MODIFIER,
                                PhpNodeType.VAR_MODIFIER,
                                PhpNodeType.VISIBILITY_MODIFIER)));
        out.put(
                PhpNodeType.CLASS_INTERFACE_CLAUSE,
                new ChildInfo(
                        true, true, Set.of(PhpNodeType.NAME, PhpNodeType.QUALIFIED_NAME, PhpNodeType.RELATIVE_NAME)));
        out.put(PhpNodeType.CLONE_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.PRIMARY_EXPRESSION)));
        out.put(PhpNodeType.COLON_BLOCK, new ChildInfo(false, true, Set.of(PhpNodeType.STATEMENT)));
        out.put(PhpNodeType.COMPOUND_STATEMENT, new ChildInfo(false, true, Set.of(PhpNodeType.STATEMENT)));
        out.put(
                PhpNodeType.CONST_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.ABSTRACT_MODIFIER,
                                PhpNodeType.CONST_ELEMENT,
                                PhpNodeType.FINAL_MODIFIER,
                                PhpNodeType.READONLY_MODIFIER,
                                PhpNodeType.STATIC_MODIFIER,
                                PhpNodeType.VAR_MODIFIER,
                                PhpNodeType.VISIBILITY_MODIFIER)));
        out.put(PhpNodeType.CONST_ELEMENT, new ChildInfo(true, true, Set.of(PhpNodeType.EXPRESSION, PhpNodeType.NAME)));
        out.put(PhpNodeType.CONTINUE_STATEMENT, new ChildInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(
                PhpNodeType.DECLARATION_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.CONST_DECLARATION,
                                PhpNodeType.METHOD_DECLARATION,
                                PhpNodeType.PROPERTY_DECLARATION,
                                PhpNodeType.USE_DECLARATION)));
        out.put(PhpNodeType.DECLARE_DIRECTIVE, new ChildInfo(true, false, Set.of(PhpNodeType.LITERAL)));
        out.put(
                PhpNodeType.DECLARE_STATEMENT,
                new ChildInfo(true, true, Set.of(PhpNodeType.DECLARE_DIRECTIVE, PhpNodeType.STATEMENT)));
        out.put(PhpNodeType.DEFAULT_STATEMENT, new ChildInfo(false, true, Set.of(PhpNodeType.STATEMENT)));
        out.put(
                PhpNodeType.DISJUNCTIVE_NORMAL_FORM_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.INTERSECTION_TYPE,
                                PhpNodeType.NAMED_TYPE,
                                PhpNodeType.OPTIONAL_TYPE,
                                PhpNodeType.PRIMITIVE_TYPE)));
        out.put(
                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                new ChildInfo(
                        true,
                        false,
                        Set.of(PhpNodeType.DYNAMIC_VARIABLE_NAME, PhpNodeType.EXPRESSION, PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.ECHO_STATEMENT,
                new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION, PhpNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                PhpNodeType.ENCAPSED_STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.ESCAPE_SEQUENCE,
                                PhpNodeType.EXPRESSION,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.STRING_CONTENT,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.ENUM_DECLARATION,
                new ChildInfo(false, true, Set.of(PhpNodeType.CLASS_INTERFACE_CLAUSE, PhpNodeType.PRIMITIVE_TYPE)));
        out.put(
                PhpNodeType.ENUM_DECLARATION_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.CONST_DECLARATION,
                                PhpNodeType.ENUM_CASE,
                                PhpNodeType.METHOD_DECLARATION,
                                PhpNodeType.USE_DECLARATION)));
        out.put(PhpNodeType.ERROR_SUPPRESSION_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.EXIT_STATEMENT, new ChildInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.EXPRESSION_STATEMENT, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(
                PhpNodeType.FOREACH_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.BY_REF,
                                PhpNodeType.EXPRESSION,
                                PhpNodeType.LIST_LITERAL,
                                PhpNodeType.PAIR)));
        out.put(
                PhpNodeType.FORMAL_PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.PROPERTY_PROMOTION_PARAMETER,
                                PhpNodeType.SIMPLE_PARAMETER,
                                PhpNodeType.VARIADIC_PARAMETER)));
        out.put(PhpNodeType.FUNCTION_DEFINITION, new ChildInfo(false, false, Set.of(PhpNodeType.REFERENCE_MODIFIER)));
        out.put(
                PhpNodeType.FUNCTION_STATIC_DECLARATION,
                new ChildInfo(true, true, Set.of(PhpNodeType.STATIC_VARIABLE_DECLARATION)));
        out.put(
                PhpNodeType.GLOBAL_DECLARATION,
                new ChildInfo(true, true, Set.of(PhpNodeType.DYNAMIC_VARIABLE_NAME, PhpNodeType.VARIABLE_NAME)));
        out.put(PhpNodeType.GOTO_STATEMENT, new ChildInfo(true, false, Set.of(PhpNodeType.NAME)));
        out.put(
                PhpNodeType.HEREDOC_BODY,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.ESCAPE_SEQUENCE,
                                PhpNodeType.EXPRESSION,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.STRING_CONTENT,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(PhpNodeType.INCLUDE_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.INCLUDE_ONCE_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.INTERFACE_DECLARATION, new ChildInfo(false, false, Set.of(PhpNodeType.BASE_CLAUSE)));
        out.put(
                PhpNodeType.INTERSECTION_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(PhpNodeType.NAMED_TYPE, PhpNodeType.OPTIONAL_TYPE, PhpNodeType.PRIMITIVE_TYPE)));
        out.put(
                PhpNodeType.LIST_LITERAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.BY_REF,
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.EXPRESSION,
                                PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                PhpNodeType.LIST_LITERAL,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.MEMBER_CALL_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.MATCH_BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(PhpNodeType.MATCH_CONDITIONAL_EXPRESSION, PhpNodeType.MATCH_DEFAULT_EXPRESSION)));
        out.put(PhpNodeType.MATCH_CONDITION_LIST, new ChildInfo(true, true, Set.of(PhpNodeType.EXPRESSION)));
        out.put(
                PhpNodeType.METHOD_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.ABSTRACT_MODIFIER,
                                PhpNodeType.FINAL_MODIFIER,
                                PhpNodeType.READONLY_MODIFIER,
                                PhpNodeType.REFERENCE_MODIFIER,
                                PhpNodeType.STATIC_MODIFIER,
                                PhpNodeType.VAR_MODIFIER,
                                PhpNodeType.VISIBILITY_MODIFIER)));
        out.put(PhpNodeType.NAMED_LABEL_STATEMENT, new ChildInfo(true, false, Set.of(PhpNodeType.NAME)));
        out.put(
                PhpNodeType.NAMED_TYPE,
                new ChildInfo(
                        true, false, Set.of(PhpNodeType.NAME, PhpNodeType.QUALIFIED_NAME, PhpNodeType.RELATIVE_NAME)));
        out.put(PhpNodeType.NAMESPACE_NAME, new ChildInfo(true, true, Set.of(PhpNodeType.NAME)));
        out.put(
                PhpNodeType.NAMESPACE_USE_CLAUSE,
                new ChildInfo(true, false, Set.of(PhpNodeType.NAME, PhpNodeType.QUALIFIED_NAME)));
        out.put(
                PhpNodeType.NAMESPACE_USE_DECLARATION,
                new ChildInfo(true, true, Set.of(PhpNodeType.NAMESPACE_NAME, PhpNodeType.NAMESPACE_USE_CLAUSE)));
        out.put(PhpNodeType.NAMESPACE_USE_GROUP, new ChildInfo(true, true, Set.of(PhpNodeType.NAMESPACE_USE_CLAUSE)));
        out.put(PhpNodeType.NOWDOC_BODY, new ChildInfo(true, true, Set.of(PhpNodeType.NOWDOC_STRING)));
        out.put(
                PhpNodeType.OBJECT_CREATION_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.ANONYMOUS_CLASS,
                                PhpNodeType.ARGUMENTS,
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.NAME,
                                PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.PARENTHESIZED_EXPRESSION,
                                PhpNodeType.QUALIFIED_NAME,
                                PhpNodeType.RELATIVE_NAME,
                                PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.OPTIONAL_TYPE,
                new ChildInfo(true, false, Set.of(PhpNodeType.NAMED_TYPE, PhpNodeType.PRIMITIVE_TYPE)));
        out.put(
                PhpNodeType.PAIR,
                new ChildInfo(
                        true, true, Set.of(PhpNodeType.BY_REF, PhpNodeType.EXPRESSION, PhpNodeType.LIST_LITERAL)));
        out.put(PhpNodeType.PARENTHESIZED_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.PRINT_INTRINSIC, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(
                PhpNodeType.PROGRAM,
                new ChildInfo(false, true, Set.of(PhpNodeType.PHP_TAG, PhpNodeType.STATEMENT, PhpNodeType.TEXT)));
        out.put(
                PhpNodeType.PROPERTY_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.ABSTRACT_MODIFIER,
                                PhpNodeType.FINAL_MODIFIER,
                                PhpNodeType.PROPERTY_ELEMENT,
                                PhpNodeType.PROPERTY_HOOK_LIST,
                                PhpNodeType.READONLY_MODIFIER,
                                PhpNodeType.STATIC_MODIFIER,
                                PhpNodeType.VAR_MODIFIER,
                                PhpNodeType.VISIBILITY_MODIFIER)));
        out.put(PhpNodeType.PROPERTY_HOOK, new ChildInfo(true, false, Set.of(PhpNodeType.NAME)));
        out.put(PhpNodeType.PROPERTY_HOOK_LIST, new ChildInfo(false, true, Set.of(PhpNodeType.PROPERTY_HOOK)));
        out.put(
                PhpNodeType.PROPERTY_PROMOTION_PARAMETER,
                new ChildInfo(false, false, Set.of(PhpNodeType.PROPERTY_HOOK_LIST)));
        out.put(PhpNodeType.QUALIFIED_NAME, new ChildInfo(true, false, Set.of(PhpNodeType.NAME)));
        out.put(PhpNodeType.RELATIVE_NAME, new ChildInfo(true, false, Set.of(PhpNodeType.NAME)));
        out.put(PhpNodeType.REQUIRE_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.REQUIRE_ONCE_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.RETURN_STATEMENT, new ChildInfo(false, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(
                PhpNodeType.SEQUENCE_EXPRESSION,
                new ChildInfo(true, true, Set.of(PhpNodeType.EXPRESSION, PhpNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                PhpNodeType.SHELL_COMMAND_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.ESCAPE_SEQUENCE,
                                PhpNodeType.EXPRESSION,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.STRING_CONTENT,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.STRING,
                new ChildInfo(false, true, Set.of(PhpNodeType.ESCAPE_SEQUENCE, PhpNodeType.STRING_CONTENT)));
        out.put(
                PhpNodeType.SUBSCRIPT_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.ARRAY_CREATION_EXPRESSION,
                                PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.ENCAPSED_STRING,
                                PhpNodeType.EXPRESSION,
                                PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                PhpNodeType.HEREDOC,
                                PhpNodeType.INTEGER,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.MEMBER_CALL_EXPRESSION,
                                PhpNodeType.NAME,
                                PhpNodeType.NOWDOC,
                                PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                PhpNodeType.OBJECT_CREATION_EXPRESSION,
                                PhpNodeType.PARENTHESIZED_EXPRESSION,
                                PhpNodeType.QUALIFIED_NAME,
                                PhpNodeType.RELATIVE_NAME,
                                PhpNodeType.SCOPED_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                PhpNodeType.STRING,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.SWITCH_BLOCK,
                new ChildInfo(false, true, Set.of(PhpNodeType.CASE_STATEMENT, PhpNodeType.DEFAULT_STATEMENT)));
        out.put(
                PhpNodeType.TEXT_INTERPOLATION,
                new ChildInfo(true, true, Set.of(PhpNodeType.PHP_END_TAG, PhpNodeType.PHP_TAG, PhpNodeType.TEXT)));
        out.put(PhpNodeType.THROW_EXPRESSION, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(
                PhpNodeType.TRY_STATEMENT,
                new ChildInfo(true, true, Set.of(PhpNodeType.CATCH_CLAUSE, PhpNodeType.FINALLY_CLAUSE)));
        out.put(PhpNodeType.TYPE_LIST, new ChildInfo(true, true, Set.of(PhpNodeType.NAMED_TYPE)));
        out.put(PhpNodeType.UNARY_OP_EXPRESSION, new ChildInfo(false, false, Set.of(PhpNodeType.INTEGER)));
        out.put(
                PhpNodeType.UNION_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(PhpNodeType.NAMED_TYPE, PhpNodeType.OPTIONAL_TYPE, PhpNodeType.PRIMITIVE_TYPE)));
        out.put(
                PhpNodeType.UNSET_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.CAST_EXPRESSION,
                                PhpNodeType.DYNAMIC_VARIABLE_NAME,
                                PhpNodeType.FUNCTION_CALL_EXPRESSION,
                                PhpNodeType.MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.MEMBER_CALL_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_ACCESS_EXPRESSION,
                                PhpNodeType.NULLSAFE_MEMBER_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_CALL_EXPRESSION,
                                PhpNodeType.SCOPED_PROPERTY_ACCESS_EXPRESSION,
                                PhpNodeType.SUBSCRIPT_EXPRESSION,
                                PhpNodeType.VARIABLE_NAME)));
        out.put(
                PhpNodeType.USE_AS_CLAUSE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION,
                                PhpNodeType.NAME,
                                PhpNodeType.VISIBILITY_MODIFIER)));
        out.put(
                PhpNodeType.USE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                PhpNodeType.NAME,
                                PhpNodeType.QUALIFIED_NAME,
                                PhpNodeType.RELATIVE_NAME,
                                PhpNodeType.USE_LIST)));
        out.put(
                PhpNodeType.USE_INSTEAD_OF_CLAUSE,
                new ChildInfo(true, true, Set.of(PhpNodeType.CLASS_CONSTANT_ACCESS_EXPRESSION, PhpNodeType.NAME)));
        out.put(
                PhpNodeType.USE_LIST,
                new ChildInfo(false, true, Set.of(PhpNodeType.USE_AS_CLAUSE, PhpNodeType.USE_INSTEAD_OF_CLAUSE)));
        out.put(PhpNodeType.VARIABLE_NAME, new ChildInfo(true, false, Set.of(PhpNodeType.NAME)));
        out.put(PhpNodeType.VARIADIC_UNPACKING, new ChildInfo(true, false, Set.of(PhpNodeType.EXPRESSION)));
        out.put(PhpNodeType.VISIBILITY_MODIFIER, new ChildInfo(false, false, Set.of(PhpNodeType.OPERATION)));
        out.put(
                PhpNodeType.YIELD_EXPRESSION,
                new ChildInfo(false, false, Set.of(PhpNodeType.ARRAY_ELEMENT_INITIALIZER, PhpNodeType.EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<PhpNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<PhpNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<PhpNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<PhpNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
