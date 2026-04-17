package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code rust} from tree-sitter {@code node-types.json}.
 */
public final class RustNodeSchema {
    private RustNodeSchema() {}

    public static Set<RustNodeField> fields(@Nullable RustNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<RustNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<RustNodeType> allowedTypes(@Nullable RustNodeType owner, @Nullable RustNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<RustNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable RustNodeType owner, @Nullable RustNodeField field) {
        if (owner == null || field == null) return false;
        Map<RustNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable RustNodeType owner, @Nullable RustNodeField field) {
        if (owner == null || field == null) return false;
        Map<RustNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<RustNodeType> allowedChildTypes(@Nullable RustNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable RustNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable RustNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<RustNodeType, Map<RustNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<RustNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<RustNodeType, Map<RustNodeField, FieldInfo>> initFields() {
        EnumMap<RustNodeType, Map<RustNodeField, FieldInfo>> out = new EnumMap<>(RustNodeType.class);
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.TRAIT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.BOUNDED_TYPE,
                                    RustNodeType.FUNCTION_TYPE,
                                    RustNodeType.GENERIC_TYPE,
                                    RustNodeType.REMOVED_TRAIT_BOUND,
                                    RustNodeType.SCOPED_TYPE_IDENTIFIER,
                                    RustNodeType.TUPLE_TYPE,
                                    RustNodeType.TYPE_IDENTIFIER)));
            out.put(RustNodeType.ABSTRACT_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.LENGTH, new FieldInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.ARRAY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ELEMENT, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.LENGTH, new FieldInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.ARRAY_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.LEFT, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            m.put(RustNodeField.RIGHT, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BOUNDS, new FieldInfo(false, false, Set.of(RustNodeType.TRAIT_BOUNDS)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.ASSOCIATED_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(RustNodeType.TOKEN_TREE)));
            m.put(RustNodeField.VALUE, new FieldInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.ATTRIBUTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.LEFT, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            m.put(RustNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(RustNodeField.RIGHT, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.DOC, new FieldInfo(false, false, Set.of(RustNodeType.DOC_COMMENT)));
            m.put(RustNodeField.INNER, new FieldInfo(false, false, Set.of(RustNodeType.INNER_DOC_COMMENT_MARKER)));
            m.put(RustNodeField.OUTER, new FieldInfo(false, false, Set.of(RustNodeType.OUTER_DOC_COMMENT_MARKER)));
            out.put(RustNodeType.BLOCK_COMMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(RustNodeType.ARGUMENTS)));
            m.put(
                    RustNodeField.FUNCTION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.ARRAY_EXPRESSION,
                                    RustNodeType.ASSIGNMENT_EXPRESSION,
                                    RustNodeType.ASYNC_BLOCK,
                                    RustNodeType.AWAIT_EXPRESSION,
                                    RustNodeType.BINARY_EXPRESSION,
                                    RustNodeType.BLOCK,
                                    RustNodeType.BREAK_EXPRESSION,
                                    RustNodeType.CALL_EXPRESSION,
                                    RustNodeType.CLOSURE_EXPRESSION,
                                    RustNodeType.COMPOUND_ASSIGNMENT_EXPR,
                                    RustNodeType.CONST_BLOCK,
                                    RustNodeType.CONTINUE_EXPRESSION,
                                    RustNodeType.FIELD_EXPRESSION,
                                    RustNodeType.FOR_EXPRESSION,
                                    RustNodeType.GENERIC_FUNCTION,
                                    RustNodeType.GEN_BLOCK,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.IF_EXPRESSION,
                                    RustNodeType.INDEX_EXPRESSION,
                                    RustNodeType.LITERAL,
                                    RustNodeType.LOOP_EXPRESSION,
                                    RustNodeType.MACRO_INVOCATION,
                                    RustNodeType.MATCH_EXPRESSION,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.PARENTHESIZED_EXPRESSION,
                                    RustNodeType.REFERENCE_EXPRESSION,
                                    RustNodeType.RETURN_EXPRESSION,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SELF,
                                    RustNodeType.STRUCT_EXPRESSION,
                                    RustNodeType.TRY_BLOCK,
                                    RustNodeType.TRY_EXPRESSION,
                                    RustNodeType.TUPLE_EXPRESSION,
                                    RustNodeType.TYPE_CAST_EXPRESSION,
                                    RustNodeType.UNARY_EXPRESSION,
                                    RustNodeType.UNIT_EXPRESSION,
                                    RustNodeType.UNSAFE_BLOCK,
                                    RustNodeType.WHILE_EXPRESSION,
                                    RustNodeType.YIELD_EXPRESSION)));
            out.put(RustNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            m.put(RustNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(RustNodeType.CLOSURE_PARAMETERS)));
            m.put(RustNodeField.RETURN_TYPE, new FieldInfo(false, false, Set.of(RustNodeType.TYPE)));
            out.put(RustNodeType.CLOSURE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.LEFT, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            m.put(RustNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(RustNodeField.RIGHT, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.COMPOUND_ASSIGNMENT_EXPR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.BLOCK)));
            out.put(RustNodeType.CONST_BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.VALUE, new FieldInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.CONST_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(
                    RustNodeField.VALUE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    RustNodeType.BLOCK,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.LITERAL,
                                    RustNodeType.NEGATIVE_LITERAL)));
            out.put(RustNodeType.CONST_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.TRAIT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.FUNCTION_TYPE,
                                    RustNodeType.GENERIC_TYPE,
                                    RustNodeType.HIGHER_RANKED_TRAIT_BOUND,
                                    RustNodeType.SCOPED_TYPE_IDENTIFIER,
                                    RustNodeType.TUPLE_TYPE,
                                    RustNodeType.TYPE_IDENTIFIER)));
            out.put(RustNodeType.DYNAMIC_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.ENUM_VARIANT_LIST)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.ENUM_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.BODY,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(RustNodeType.FIELD_DECLARATION_LIST, RustNodeType.ORDERED_FIELD_DECLARATION_LIST)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            m.put(RustNodeField.VALUE, new FieldInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.ENUM_VARIANT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ALIAS, new FieldInfo(false, false, Set.of(RustNodeType.IDENTIFIER)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            out.put(RustNodeType.EXTERN_CRATE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.FIELD_IDENTIFIER)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            out.put(RustNodeType.FIELD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.FIELD,
                    new FieldInfo(true, false, Set.of(RustNodeType.FIELD_IDENTIFIER, RustNodeType.INTEGER_LITERAL)));
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.FIELD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.FIELD,
                    new FieldInfo(true, false, Set.of(RustNodeType.FIELD_IDENTIFIER, RustNodeType.INTEGER_LITERAL)));
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.FIELD_INITIALIZER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(RustNodeType.FIELD_IDENTIFIER, RustNodeType.SHORTHAND_FIELD_IDENTIFIER)));
            m.put(RustNodeField.PATTERN, new FieldInfo(false, false, Set.of(RustNodeType.PATTERN)));
            out.put(RustNodeType.FIELD_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(false, false, Set.of(RustNodeType.DECLARATION_LIST)));
            out.put(RustNodeType.FOREIGN_MOD_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.BLOCK)));
            m.put(RustNodeField.PATTERN, new FieldInfo(true, false, Set.of(RustNodeType.PATTERN)));
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.FOR_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.BLOCK)));
            m.put(
                    RustNodeField.NAME,
                    new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER, RustNodeType.METAVARIABLE)));
            m.put(RustNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(RustNodeType.PARAMETERS)));
            m.put(RustNodeField.RETURN_TYPE, new FieldInfo(false, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.FUNCTION_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.NAME,
                    new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER, RustNodeType.METAVARIABLE)));
            m.put(RustNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(RustNodeType.PARAMETERS)));
            m.put(RustNodeField.RETURN_TYPE, new FieldInfo(false, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.FUNCTION_SIGNATURE_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(RustNodeType.PARAMETERS)));
            m.put(RustNodeField.RETURN_TYPE, new FieldInfo(false, false, Set.of(RustNodeType.TYPE)));
            m.put(
                    RustNodeField.TRAIT,
                    new FieldInfo(
                            false, false, Set.of(RustNodeType.SCOPED_TYPE_IDENTIFIER, RustNodeType.TYPE_IDENTIFIER)));
            out.put(RustNodeType.FUNCTION_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.FUNCTION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.FIELD_EXPRESSION,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.SCOPED_IDENTIFIER)));
            m.put(RustNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_ARGUMENTS)));
            out.put(RustNodeType.GENERIC_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_ARGUMENTS)));
            out.put(RustNodeType.GENERIC_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SCOPED_TYPE_IDENTIFIER,
                                    RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_ARGUMENTS)));
            out.put(RustNodeType.GENERIC_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.TYPE,
                    new FieldInfo(true, false, Set.of(RustNodeType.SCOPED_IDENTIFIER, RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE_ARGUMENTS, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_ARGUMENTS)));
            out.put(RustNodeType.GENERIC_TYPE_WITH_TURBOFISH, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.HIGHER_RANKED_TRAIT_BOUND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(RustNodeType.ELSE_CLAUSE)));
            m.put(
                    RustNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(RustNodeType.EXPRESSION, RustNodeType.LET_CHAIN, RustNodeType.LET_CONDITION)));
            m.put(RustNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(RustNodeType.BLOCK)));
            out.put(RustNodeType.IF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(false, false, Set.of(RustNodeType.DECLARATION_LIST)));
            m.put(
                    RustNodeField.TRAIT,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    RustNodeType.GENERIC_TYPE,
                                    RustNodeType.SCOPED_TYPE_IDENTIFIER,
                                    RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.IMPL_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.PATTERN, new FieldInfo(true, false, Set.of(RustNodeType.PATTERN)));
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.LET_CONDITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(RustNodeType.BLOCK)));
            m.put(RustNodeField.PATTERN, new FieldInfo(true, false, Set.of(RustNodeType.PATTERN)));
            m.put(RustNodeField.TYPE, new FieldInfo(false, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.VALUE, new FieldInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.LET_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BOUNDS, new FieldInfo(false, false, Set.of(RustNodeType.TRAIT_BOUNDS)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.LIFETIME)));
            out.put(RustNodeType.LIFETIME_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.DOC, new FieldInfo(false, false, Set.of(RustNodeType.DOC_COMMENT)));
            m.put(RustNodeField.INNER, new FieldInfo(false, false, Set.of(RustNodeType.INNER_DOC_COMMENT_MARKER)));
            m.put(RustNodeField.OUTER, new FieldInfo(false, false, Set.of(RustNodeType.OUTER_DOC_COMMENT_MARKER)));
            out.put(RustNodeType.LINE_COMMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.BLOCK)));
            out.put(RustNodeType.LOOP_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            out.put(RustNodeType.MACRO_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.MACRO,
                    new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER, RustNodeType.SCOPED_IDENTIFIER)));
            out.put(RustNodeType.MACRO_INVOCATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.LEFT, new FieldInfo(true, false, Set.of(RustNodeType.TOKEN_TREE_PATTERN)));
            m.put(RustNodeField.RIGHT, new FieldInfo(true, false, Set.of(RustNodeType.TOKEN_TREE)));
            out.put(RustNodeType.MACRO_RULE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.PATTERN, new FieldInfo(true, false, Set.of(RustNodeType.MATCH_PATTERN)));
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.MATCH_ARM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.MATCH_BLOCK)));
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.MATCH_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.CONDITION,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(RustNodeType.EXPRESSION, RustNodeType.LET_CHAIN, RustNodeType.LET_CONDITION)));
            out.put(RustNodeType.MATCH_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(false, false, Set.of(RustNodeType.DECLARATION_LIST)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            out.put(RustNodeType.MOD_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.TYPE, new FieldInfo(false, true, Set.of(RustNodeType.TYPE)));
            out.put(RustNodeType.ORDERED_FIELD_DECLARATION_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.PATTERN, new FieldInfo(true, false, Set.of(RustNodeType.PATTERN, RustNodeType.SELF)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            out.put(RustNodeType.PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            out.put(RustNodeType.POINTER_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ALIAS, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            out.put(RustNodeType.QUALIFIED_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.LEFT,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    RustNodeType.CRATE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.LITERAL_PATTERN,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SELF,
                                    RustNodeType.SUPER_)));
            m.put(
                    RustNodeField.RIGHT,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    RustNodeType.CRATE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.LITERAL_PATTERN,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SELF,
                                    RustNodeType.SUPER_)));
            out.put(RustNodeType.RANGE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.REFERENCE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            out.put(RustNodeType.REFERENCE_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER, RustNodeType.SUPER_)));
            m.put(
                    RustNodeField.PATH,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    RustNodeType.BRACKETED_TYPE,
                                    RustNodeType.CRATE,
                                    RustNodeType.GENERIC_TYPE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SELF,
                                    RustNodeType.SUPER_)));
            out.put(RustNodeType.SCOPED_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(
                    RustNodeField.PATH,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    RustNodeType.BRACKETED_TYPE,
                                    RustNodeType.CRATE,
                                    RustNodeType.GENERIC_TYPE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SELF,
                                    RustNodeType.SUPER_)));
            out.put(RustNodeType.SCOPED_TYPE_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.LIST, new FieldInfo(true, false, Set.of(RustNodeType.USE_LIST)));
            m.put(
                    RustNodeField.PATH,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    RustNodeType.CRATE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SELF,
                                    RustNodeType.SUPER_)));
            out.put(RustNodeType.SCOPED_USE_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.VALUE, new FieldInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.STATIC_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.FIELD_INITIALIZER_LIST)));
            m.put(
                    RustNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.GENERIC_TYPE_WITH_TURBOFISH,
                                    RustNodeType.SCOPED_TYPE_IDENTIFIER,
                                    RustNodeType.TYPE_IDENTIFIER)));
            out.put(RustNodeType.STRUCT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.BODY,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(RustNodeType.FIELD_DECLARATION_LIST, RustNodeType.ORDERED_FIELD_DECLARATION_LIST)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.STRUCT_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.TYPE,
                    new FieldInfo(
                            true, false, Set.of(RustNodeType.SCOPED_TYPE_IDENTIFIER, RustNodeType.TYPE_IDENTIFIER)));
            out.put(RustNodeType.STRUCT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.METAVARIABLE)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.FRAGMENT_SPECIFIER)));
            out.put(RustNodeType.TOKEN_BINDING_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.DECLARATION_LIST)));
            m.put(RustNodeField.BOUNDS, new FieldInfo(false, false, Set.of(RustNodeType.TRAIT_BOUNDS)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.TRAIT_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.GENERIC_TYPE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.SCOPED_IDENTIFIER)));
            out.put(RustNodeType.TUPLE_STRUCT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_ARGUMENTS)));
            out.put(RustNodeType.TYPE_BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.VALUE, new FieldInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
            out.put(RustNodeType.TYPE_CAST_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE, new FieldInfo(true, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.TYPE_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BOUNDS, new FieldInfo(false, false, Set.of(RustNodeType.TRAIT_BOUNDS)));
            m.put(RustNodeField.DEFAULT_TYPE, new FieldInfo(false, false, Set.of(RustNodeType.TYPE)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            out.put(RustNodeType.TYPE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.FIELD_DECLARATION_LIST)));
            m.put(RustNodeField.NAME, new FieldInfo(true, false, Set.of(RustNodeType.TYPE_IDENTIFIER)));
            m.put(RustNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
            out.put(RustNodeType.UNION_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.ALIAS, new FieldInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
            m.put(
                    RustNodeField.PATH,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.CRATE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SELF,
                                    RustNodeType.SUPER_)));
            out.put(RustNodeType.USE_AS_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(
                    RustNodeField.ARGUMENT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.CRATE,
                                    RustNodeType.IDENTIFIER,
                                    RustNodeType.METAVARIABLE,
                                    RustNodeType.SCOPED_IDENTIFIER,
                                    RustNodeType.SCOPED_USE_LIST,
                                    RustNodeType.SELF,
                                    RustNodeType.SUPER_,
                                    RustNodeType.USE_AS_CLAUSE,
                                    RustNodeType.USE_LIST,
                                    RustNodeType.USE_WILDCARD)));
            out.put(RustNodeType.USE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.PATTERN, new FieldInfo(false, false, Set.of(RustNodeType.PATTERN)));
            out.put(RustNodeType.VARIADIC_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BOUNDS, new FieldInfo(true, false, Set.of(RustNodeType.TRAIT_BOUNDS)));
            m.put(
                    RustNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RustNodeType.ARRAY_TYPE,
                                    RustNodeType.GENERIC_TYPE,
                                    RustNodeType.HIGHER_RANKED_TRAIT_BOUND,
                                    RustNodeType.LIFETIME,
                                    RustNodeType.POINTER_TYPE,
                                    RustNodeType.PRIMITIVE_TYPE,
                                    RustNodeType.REFERENCE_TYPE,
                                    RustNodeType.SCOPED_TYPE_IDENTIFIER,
                                    RustNodeType.TUPLE_TYPE,
                                    RustNodeType.TYPE_IDENTIFIER)));
            out.put(RustNodeType.WHERE_PREDICATE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RustNodeField, FieldInfo> m = new EnumMap<>(RustNodeField.class);
            m.put(RustNodeField.BODY, new FieldInfo(true, false, Set.of(RustNodeType.BLOCK)));
            m.put(
                    RustNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(RustNodeType.EXPRESSION, RustNodeType.LET_CHAIN, RustNodeType.LET_CONDITION)));
            out.put(RustNodeType.WHILE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<RustNodeType, ChildInfo> initChildren() {
        EnumMap<RustNodeType, ChildInfo> out = new EnumMap<>(RustNodeType.class);
        out.put(RustNodeType.ABSTRACT_TYPE, new ChildInfo(false, false, Set.of(RustNodeType.TYPE_PARAMETERS)));
        out.put(
                RustNodeType.ARGUMENTS,
                new ChildInfo(false, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.EXPRESSION)));
        out.put(
                RustNodeType.ARRAY_EXPRESSION,
                new ChildInfo(false, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.EXPRESSION)));
        out.put(RustNodeType.ASSOCIATED_TYPE, new ChildInfo(false, false, Set.of(RustNodeType.WHERE_CLAUSE)));
        out.put(RustNodeType.ASYNC_BLOCK, new ChildInfo(true, false, Set.of(RustNodeType.BLOCK)));
        out.put(
                RustNodeType.ATTRIBUTE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.SCOPED_IDENTIFIER,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_)));
        out.put(RustNodeType.ATTRIBUTE_ITEM, new ChildInfo(true, false, Set.of(RustNodeType.ATTRIBUTE)));
        out.put(RustNodeType.AWAIT_EXPRESSION, new ChildInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
        out.put(RustNodeType.BASE_FIELD_INITIALIZER, new ChildInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
        out.put(
                RustNodeType.BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.DECLARATION_STATEMENT,
                                RustNodeType.EXPRESSION,
                                RustNodeType.EXPRESSION_STATEMENT,
                                RustNodeType.LABEL)));
        out.put(
                RustNodeType.BOUNDED_TYPE,
                new ChildInfo(true, true, Set.of(RustNodeType.LIFETIME, RustNodeType.TYPE, RustNodeType.USE_BOUNDS)));
        out.put(
                RustNodeType.BRACKETED_TYPE,
                new ChildInfo(true, false, Set.of(RustNodeType.QUALIFIED_TYPE, RustNodeType.TYPE)));
        out.put(
                RustNodeType.BREAK_EXPRESSION,
                new ChildInfo(false, true, Set.of(RustNodeType.EXPRESSION, RustNodeType.LABEL)));
        out.put(RustNodeType.CAPTURED_PATTERN, new ChildInfo(true, true, Set.of(RustNodeType.PATTERN)));
        out.put(
                RustNodeType.CLOSURE_PARAMETERS,
                new ChildInfo(false, true, Set.of(RustNodeType.PARAMETER, RustNodeType.PATTERN)));
        out.put(RustNodeType.CONST_ITEM, new ChildInfo(false, false, Set.of(RustNodeType.VISIBILITY_MODIFIER)));
        out.put(RustNodeType.CONTINUE_EXPRESSION, new ChildInfo(false, false, Set.of(RustNodeType.LABEL)));
        out.put(RustNodeType.DECLARATION_LIST, new ChildInfo(false, true, Set.of(RustNodeType.DECLARATION_STATEMENT)));
        out.put(
                RustNodeType.ELSE_CLAUSE,
                new ChildInfo(true, false, Set.of(RustNodeType.BLOCK, RustNodeType.IF_EXPRESSION)));
        out.put(
                RustNodeType.ENUM_ITEM,
                new ChildInfo(false, true, Set.of(RustNodeType.VISIBILITY_MODIFIER, RustNodeType.WHERE_CLAUSE)));
        out.put(RustNodeType.ENUM_VARIANT, new ChildInfo(false, false, Set.of(RustNodeType.VISIBILITY_MODIFIER)));
        out.put(
                RustNodeType.ENUM_VARIANT_LIST,
                new ChildInfo(false, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.ENUM_VARIANT)));
        out.put(RustNodeType.EXPRESSION_STATEMENT, new ChildInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
        out.put(
                RustNodeType.EXTERN_CRATE_DECLARATION,
                new ChildInfo(true, true, Set.of(RustNodeType.CRATE, RustNodeType.VISIBILITY_MODIFIER)));
        out.put(RustNodeType.EXTERN_MODIFIER, new ChildInfo(false, false, Set.of(RustNodeType.STRING_LITERAL)));
        out.put(RustNodeType.FIELD_DECLARATION, new ChildInfo(false, false, Set.of(RustNodeType.VISIBILITY_MODIFIER)));
        out.put(
                RustNodeType.FIELD_DECLARATION_LIST,
                new ChildInfo(false, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.FIELD_DECLARATION)));
        out.put(RustNodeType.FIELD_INITIALIZER, new ChildInfo(false, true, Set.of(RustNodeType.ATTRIBUTE_ITEM)));
        out.put(
                RustNodeType.FIELD_INITIALIZER_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.BASE_FIELD_INITIALIZER,
                                RustNodeType.FIELD_INITIALIZER,
                                RustNodeType.SHORTHAND_FIELD_INITIALIZER)));
        out.put(RustNodeType.FIELD_PATTERN, new ChildInfo(false, false, Set.of(RustNodeType.MUTABLE_SPECIFIER)));
        out.put(
                RustNodeType.FOREIGN_MOD_ITEM,
                new ChildInfo(true, true, Set.of(RustNodeType.EXTERN_MODIFIER, RustNodeType.VISIBILITY_MODIFIER)));
        out.put(RustNodeType.FOR_EXPRESSION, new ChildInfo(false, false, Set.of(RustNodeType.LABEL)));
        out.put(RustNodeType.FOR_LIFETIMES, new ChildInfo(true, true, Set.of(RustNodeType.LIFETIME)));
        out.put(
                RustNodeType.FUNCTION_ITEM,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.FUNCTION_MODIFIERS,
                                RustNodeType.VISIBILITY_MODIFIER,
                                RustNodeType.WHERE_CLAUSE)));
        out.put(RustNodeType.FUNCTION_MODIFIERS, new ChildInfo(false, true, Set.of(RustNodeType.EXTERN_MODIFIER)));
        out.put(
                RustNodeType.FUNCTION_SIGNATURE_ITEM,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.FUNCTION_MODIFIERS,
                                RustNodeType.VISIBILITY_MODIFIER,
                                RustNodeType.WHERE_CLAUSE)));
        out.put(
                RustNodeType.FUNCTION_TYPE,
                new ChildInfo(false, true, Set.of(RustNodeType.FOR_LIFETIMES, RustNodeType.FUNCTION_MODIFIERS)));
        out.put(
                RustNodeType.GENERIC_PATTERN,
                new ChildInfo(true, false, Set.of(RustNodeType.IDENTIFIER, RustNodeType.SCOPED_IDENTIFIER)));
        out.put(RustNodeType.GEN_BLOCK, new ChildInfo(true, false, Set.of(RustNodeType.BLOCK)));
        out.put(RustNodeType.IMPL_ITEM, new ChildInfo(false, false, Set.of(RustNodeType.WHERE_CLAUSE)));
        out.put(RustNodeType.INDEX_EXPRESSION, new ChildInfo(true, true, Set.of(RustNodeType.EXPRESSION)));
        out.put(RustNodeType.INNER_ATTRIBUTE_ITEM, new ChildInfo(true, false, Set.of(RustNodeType.ATTRIBUTE)));
        out.put(RustNodeType.LABEL, new ChildInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
        out.put(
                RustNodeType.LET_CHAIN,
                new ChildInfo(true, true, Set.of(RustNodeType.EXPRESSION, RustNodeType.LET_CONDITION)));
        out.put(RustNodeType.LET_DECLARATION, new ChildInfo(false, false, Set.of(RustNodeType.MUTABLE_SPECIFIER)));
        out.put(RustNodeType.LIFETIME, new ChildInfo(true, false, Set.of(RustNodeType.IDENTIFIER)));
        out.put(RustNodeType.LOOP_EXPRESSION, new ChildInfo(false, false, Set.of(RustNodeType.LABEL)));
        out.put(RustNodeType.MACRO_DEFINITION, new ChildInfo(false, true, Set.of(RustNodeType.MACRO_RULE)));
        out.put(RustNodeType.MACRO_INVOCATION, new ChildInfo(true, false, Set.of(RustNodeType.TOKEN_TREE)));
        out.put(
                RustNodeType.MATCH_ARM,
                new ChildInfo(false, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.INNER_ATTRIBUTE_ITEM)));
        out.put(RustNodeType.MATCH_BLOCK, new ChildInfo(false, true, Set.of(RustNodeType.MATCH_ARM)));
        out.put(RustNodeType.MATCH_PATTERN, new ChildInfo(true, false, Set.of(RustNodeType.PATTERN)));
        out.put(RustNodeType.MOD_ITEM, new ChildInfo(false, false, Set.of(RustNodeType.VISIBILITY_MODIFIER)));
        out.put(
                RustNodeType.MUT_PATTERN,
                new ChildInfo(true, true, Set.of(RustNodeType.MUTABLE_SPECIFIER, RustNodeType.PATTERN)));
        out.put(
                RustNodeType.NEGATIVE_LITERAL,
                new ChildInfo(true, false, Set.of(RustNodeType.FLOAT_LITERAL, RustNodeType.INTEGER_LITERAL)));
        out.put(
                RustNodeType.ORDERED_FIELD_DECLARATION_LIST,
                new ChildInfo(false, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.VISIBILITY_MODIFIER)));
        out.put(RustNodeType.OR_PATTERN, new ChildInfo(true, true, Set.of(RustNodeType.PATTERN)));
        out.put(RustNodeType.PARAMETER, new ChildInfo(false, false, Set.of(RustNodeType.MUTABLE_SPECIFIER)));
        out.put(
                RustNodeType.PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.ATTRIBUTE_ITEM,
                                RustNodeType.PARAMETER,
                                RustNodeType.SELF_PARAMETER,
                                RustNodeType.TYPE,
                                RustNodeType.VARIADIC_PARAMETER)));
        out.put(RustNodeType.PARENTHESIZED_EXPRESSION, new ChildInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
        out.put(RustNodeType.POINTER_TYPE, new ChildInfo(false, false, Set.of(RustNodeType.MUTABLE_SPECIFIER)));
        out.put(RustNodeType.RANGE_EXPRESSION, new ChildInfo(false, true, Set.of(RustNodeType.EXPRESSION)));
        out.put(RustNodeType.RAW_STRING_LITERAL, new ChildInfo(true, false, Set.of(RustNodeType.STRING_CONTENT)));
        out.put(RustNodeType.REFERENCE_EXPRESSION, new ChildInfo(false, false, Set.of(RustNodeType.MUTABLE_SPECIFIER)));
        out.put(
                RustNodeType.REFERENCE_PATTERN,
                new ChildInfo(true, true, Set.of(RustNodeType.MUTABLE_SPECIFIER, RustNodeType.PATTERN)));
        out.put(
                RustNodeType.REFERENCE_TYPE,
                new ChildInfo(false, true, Set.of(RustNodeType.LIFETIME, RustNodeType.MUTABLE_SPECIFIER)));
        out.put(RustNodeType.REF_PATTERN, new ChildInfo(true, false, Set.of(RustNodeType.PATTERN)));
        out.put(RustNodeType.REMOVED_TRAIT_BOUND, new ChildInfo(true, false, Set.of(RustNodeType.TYPE)));
        out.put(RustNodeType.RETURN_EXPRESSION, new ChildInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
        out.put(
                RustNodeType.SELF_PARAMETER,
                new ChildInfo(
                        true, true, Set.of(RustNodeType.LIFETIME, RustNodeType.MUTABLE_SPECIFIER, RustNodeType.SELF)));
        out.put(
                RustNodeType.SHORTHAND_FIELD_INITIALIZER,
                new ChildInfo(true, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.IDENTIFIER)));
        out.put(RustNodeType.SLICE_PATTERN, new ChildInfo(false, true, Set.of(RustNodeType.PATTERN)));
        out.put(
                RustNodeType.SOURCE_FILE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.DECLARATION_STATEMENT,
                                RustNodeType.EXPRESSION_STATEMENT,
                                RustNodeType.SHEBANG)));
        out.put(
                RustNodeType.STATIC_ITEM,
                new ChildInfo(false, true, Set.of(RustNodeType.MUTABLE_SPECIFIER, RustNodeType.VISIBILITY_MODIFIER)));
        out.put(
                RustNodeType.STRING_LITERAL,
                new ChildInfo(false, true, Set.of(RustNodeType.ESCAPE_SEQUENCE, RustNodeType.STRING_CONTENT)));
        out.put(
                RustNodeType.STRUCT_ITEM,
                new ChildInfo(false, true, Set.of(RustNodeType.VISIBILITY_MODIFIER, RustNodeType.WHERE_CLAUSE)));
        out.put(
                RustNodeType.STRUCT_PATTERN,
                new ChildInfo(false, true, Set.of(RustNodeType.FIELD_PATTERN, RustNodeType.REMAINING_FIELD_PATTERN)));
        out.put(
                RustNodeType.TOKEN_REPETITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.LITERAL,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.MUTABLE_SPECIFIER,
                                RustNodeType.PRIMITIVE_TYPE,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_,
                                RustNodeType.TOKEN_REPETITION,
                                RustNodeType.TOKEN_TREE)));
        out.put(
                RustNodeType.TOKEN_REPETITION_PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.LITERAL,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.MUTABLE_SPECIFIER,
                                RustNodeType.PRIMITIVE_TYPE,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_,
                                RustNodeType.TOKEN_BINDING_PATTERN,
                                RustNodeType.TOKEN_REPETITION_PATTERN,
                                RustNodeType.TOKEN_TREE_PATTERN)));
        out.put(
                RustNodeType.TOKEN_TREE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.LITERAL,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.MUTABLE_SPECIFIER,
                                RustNodeType.PRIMITIVE_TYPE,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_,
                                RustNodeType.TOKEN_REPETITION,
                                RustNodeType.TOKEN_TREE)));
        out.put(
                RustNodeType.TOKEN_TREE_PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.LITERAL,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.MUTABLE_SPECIFIER,
                                RustNodeType.PRIMITIVE_TYPE,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_,
                                RustNodeType.TOKEN_BINDING_PATTERN,
                                RustNodeType.TOKEN_REPETITION_PATTERN,
                                RustNodeType.TOKEN_TREE_PATTERN)));
        out.put(
                RustNodeType.TRAIT_BOUNDS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(RustNodeType.HIGHER_RANKED_TRAIT_BOUND, RustNodeType.LIFETIME, RustNodeType.TYPE)));
        out.put(
                RustNodeType.TRAIT_ITEM,
                new ChildInfo(false, true, Set.of(RustNodeType.VISIBILITY_MODIFIER, RustNodeType.WHERE_CLAUSE)));
        out.put(RustNodeType.TRY_BLOCK, new ChildInfo(true, false, Set.of(RustNodeType.BLOCK)));
        out.put(RustNodeType.TRY_EXPRESSION, new ChildInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
        out.put(
                RustNodeType.TUPLE_EXPRESSION,
                new ChildInfo(true, true, Set.of(RustNodeType.ATTRIBUTE_ITEM, RustNodeType.EXPRESSION)));
        out.put(
                RustNodeType.TUPLE_PATTERN,
                new ChildInfo(false, true, Set.of(RustNodeType.CLOSURE_EXPRESSION, RustNodeType.PATTERN)));
        out.put(RustNodeType.TUPLE_STRUCT_PATTERN, new ChildInfo(false, true, Set.of(RustNodeType.PATTERN)));
        out.put(RustNodeType.TUPLE_TYPE, new ChildInfo(true, true, Set.of(RustNodeType.TYPE)));
        out.put(
                RustNodeType.TYPE_ARGUMENTS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RustNodeType.BLOCK,
                                RustNodeType.LIFETIME,
                                RustNodeType.LITERAL,
                                RustNodeType.TRAIT_BOUNDS,
                                RustNodeType.TYPE,
                                RustNodeType.TYPE_BINDING)));
        out.put(
                RustNodeType.TYPE_ITEM,
                new ChildInfo(false, true, Set.of(RustNodeType.VISIBILITY_MODIFIER, RustNodeType.WHERE_CLAUSE)));
        out.put(
                RustNodeType.TYPE_PARAMETERS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RustNodeType.ATTRIBUTE_ITEM,
                                RustNodeType.CONST_PARAMETER,
                                RustNodeType.LIFETIME_PARAMETER,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.TYPE_PARAMETER)));
        out.put(RustNodeType.UNARY_EXPRESSION, new ChildInfo(true, false, Set.of(RustNodeType.EXPRESSION)));
        out.put(
                RustNodeType.UNION_ITEM,
                new ChildInfo(false, true, Set.of(RustNodeType.VISIBILITY_MODIFIER, RustNodeType.WHERE_CLAUSE)));
        out.put(RustNodeType.UNSAFE_BLOCK, new ChildInfo(true, false, Set.of(RustNodeType.BLOCK)));
        out.put(
                RustNodeType.USE_BOUNDS,
                new ChildInfo(false, true, Set.of(RustNodeType.LIFETIME, RustNodeType.TYPE_IDENTIFIER)));
        out.put(RustNodeType.USE_DECLARATION, new ChildInfo(false, false, Set.of(RustNodeType.VISIBILITY_MODIFIER)));
        out.put(
                RustNodeType.USE_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.SCOPED_IDENTIFIER,
                                RustNodeType.SCOPED_USE_LIST,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_,
                                RustNodeType.USE_AS_CLAUSE,
                                RustNodeType.USE_LIST,
                                RustNodeType.USE_WILDCARD)));
        out.put(
                RustNodeType.USE_WILDCARD,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.SCOPED_IDENTIFIER,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_)));
        out.put(RustNodeType.VARIADIC_PARAMETER, new ChildInfo(false, false, Set.of(RustNodeType.MUTABLE_SPECIFIER)));
        out.put(
                RustNodeType.VISIBILITY_MODIFIER,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                RustNodeType.CRATE,
                                RustNodeType.IDENTIFIER,
                                RustNodeType.METAVARIABLE,
                                RustNodeType.SCOPED_IDENTIFIER,
                                RustNodeType.SELF,
                                RustNodeType.SUPER_)));
        out.put(RustNodeType.WHERE_CLAUSE, new ChildInfo(false, true, Set.of(RustNodeType.WHERE_PREDICATE)));
        out.put(RustNodeType.WHILE_EXPRESSION, new ChildInfo(false, false, Set.of(RustNodeType.LABEL)));
        out.put(RustNodeType.YIELD_EXPRESSION, new ChildInfo(false, false, Set.of(RustNodeType.EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<RustNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<RustNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<RustNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<RustNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
