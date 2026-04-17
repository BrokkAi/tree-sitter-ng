package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code ruby} from tree-sitter {@code node-types.json}.
 */
public final class RubyNodeSchema {
    private RubyNodeSchema() {}

    public static Set<RubyNodeField> fields(@Nullable RubyNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<RubyNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<RubyNodeType> allowedTypes(@Nullable RubyNodeType owner, @Nullable RubyNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<RubyNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable RubyNodeType owner, @Nullable RubyNodeField field) {
        if (owner == null || field == null) return false;
        Map<RubyNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable RubyNodeType owner, @Nullable RubyNodeField field) {
        if (owner == null || field == null) return false;
        Map<RubyNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<RubyNodeType> allowedChildTypes(@Nullable RubyNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable RubyNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable RubyNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<RubyNodeType, Map<RubyNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<RubyNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<RubyNodeType, Map<RubyNodeField, FieldInfo>> initFields() {
        EnumMap<RubyNodeType, Map<RubyNodeField, FieldInfo>> out = new EnumMap<>(RubyNodeType.class);
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.ALIAS, new FieldInfo(true, false, Set.of(RubyNodeType.METHOD_NAME)));
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.METHOD_NAME)));
            out.put(RubyNodeType.ALIAS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.ALTERNATIVES, new FieldInfo(true, true, Set.of(RubyNodeType.PATTERN_EXPR_BASIC)));
            out.put(RubyNodeType.ALTERNATIVE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.CLASS_, new FieldInfo(false, false, Set.of(RubyNodeType.PATTERN_CONSTANT)));
            out.put(RubyNodeType.ARRAY_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.LEFT,
                    new FieldInfo(true, false, Set.of(RubyNodeType.LEFT_ASSIGNMENT_LIST, RubyNodeType.LHS)));
            m.put(
                    RubyNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RubyNodeType.EXPRESSION,
                                    RubyNodeType.RESCUE_MODIFIER,
                                    RubyNodeType.RIGHT_ASSIGNMENT_LIST,
                                    RubyNodeType.SPLAT_ARGUMENT)));
            out.put(RubyNodeType.ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.IDENTIFIER)));
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.PATTERN_EXPR)));
            out.put(RubyNodeType.AS_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.LEFT,
                    new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION, RubyNodeType.SIMPLE_NUMERIC)));
            m.put(RubyNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(RubyNodeField.RIGHT, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.BINARY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.BLOCK_BODY)));
            m.put(RubyNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(RubyNodeType.BLOCK_PARAMETERS)));
            out.put(RubyNodeType.BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(false, false, Set.of(RubyNodeType.IDENTIFIER)));
            out.put(RubyNodeType.BLOCK_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.LOCALS, new FieldInfo(false, true, Set.of(RubyNodeType.IDENTIFIER)));
            out.put(RubyNodeType.BLOCK_PARAMETERS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(RubyNodeType.ARGUMENT_LIST)));
            m.put(RubyNodeField.BLOCK, new FieldInfo(false, false, Set.of(RubyNodeType.BLOCK, RubyNodeType.DO_BLOCK)));
            m.put(
                    RubyNodeField.METHOD,
                    new FieldInfo(false, false, Set.of(RubyNodeType.OPERATOR, RubyNodeType.VARIABLE)));
            m.put(RubyNodeField.OPERATOR, new FieldInfo(false, false, Set.of(RubyNodeType.CALL_OPERATOR)));
            m.put(RubyNodeField.RECEIVER, new FieldInfo(false, false, Set.of(RubyNodeType.PRIMARY)));
            out.put(RubyNodeType.CALL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.VALUE, new FieldInfo(false, false, Set.of(RubyNodeType.STATEMENT)));
            out.put(RubyNodeType.CASE_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.CLAUSES, new FieldInfo(true, true, Set.of(RubyNodeType.IN_CLAUSE)));
            m.put(RubyNodeField.ELSE_, new FieldInfo(false, false, Set.of(RubyNodeType.ELSE_)));
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            out.put(RubyNodeType.CASE_MATCH, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.BODY_STATEMENT)));
            m.put(
                    RubyNodeField.NAME,
                    new FieldInfo(true, false, Set.of(RubyNodeType.CONSTANT, RubyNodeType.SCOPE_RESOLUTION)));
            m.put(RubyNodeField.SUPERCLASS, new FieldInfo(false, false, Set.of(RubyNodeType.SUPERCLASS)));
            out.put(RubyNodeType.CLASS_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(RubyNodeType.ARG)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.ARG)));
            m.put(RubyNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(RubyNodeType.ARG)));
            out.put(RubyNodeType.CONDITIONAL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.BODY_STATEMENT)));
            m.put(RubyNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(RubyNodeType.BLOCK_PARAMETERS)));
            out.put(RubyNodeType.DO_BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BLOCK, new FieldInfo(false, false, Set.of(RubyNodeType.BLOCK, RubyNodeType.DO_BLOCK)));
            m.put(RubyNodeField.OBJECT, new FieldInfo(true, false, Set.of(RubyNodeType.PRIMARY)));
            out.put(RubyNodeType.ELEMENT_REFERENCE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.ALTERNATIVE,
                    new FieldInfo(false, false, Set.of(RubyNodeType.ELSE_, RubyNodeType.ELSIF)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.CONSEQUENCE, new FieldInfo(false, false, Set.of(RubyNodeType.THEN)));
            out.put(RubyNodeType.ELSIF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.EXPRESSION_REFERENCE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.CLASS_, new FieldInfo(false, false, Set.of(RubyNodeType.PATTERN_CONSTANT)));
            out.put(RubyNodeType.FIND_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.DO_)));
            m.put(
                    RubyNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(RubyNodeType.LEFT_ASSIGNMENT_LIST, RubyNodeType.LHS)));
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.IN)));
            out.put(RubyNodeType.FOR_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.CLASS_, new FieldInfo(false, false, Set.of(RubyNodeType.PATTERN_CONSTANT)));
            out.put(RubyNodeType.HASH_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(false, false, Set.of(RubyNodeType.IDENTIFIER)));
            out.put(RubyNodeType.HASH_SPLAT_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.ALTERNATIVE,
                    new FieldInfo(false, false, Set.of(RubyNodeType.ELSE_, RubyNodeType.ELSIF)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.CONSEQUENCE, new FieldInfo(false, false, Set.of(RubyNodeType.THEN)));
            out.put(RubyNodeType.IF_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.IF_GUARD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.IF_MODIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.THEN)));
            m.put(
                    RubyNodeField.GUARD,
                    new FieldInfo(false, false, Set.of(RubyNodeType.IF_GUARD, RubyNodeType.UNLESS_GUARD)));
            m.put(RubyNodeField.PATTERN, new FieldInfo(true, false, Set.of(RubyNodeType.PATTERN_TOP_EXPR_BODY)));
            out.put(RubyNodeType.IN_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.IDENTIFIER)));
            m.put(RubyNodeField.VALUE, new FieldInfo(false, false, Set.of(RubyNodeType.ARG)));
            out.put(RubyNodeType.KEYWORD_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.KEY,
                    new FieldInfo(true, false, Set.of(RubyNodeType.HASH_KEY_SYMBOL, RubyNodeType.STRING)));
            m.put(RubyNodeField.VALUE, new FieldInfo(false, false, Set.of(RubyNodeType.PATTERN_EXPR)));
            out.put(RubyNodeType.KEYWORD_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.BLOCK, RubyNodeType.DO_BLOCK)));
            m.put(RubyNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(RubyNodeType.LAMBDA_PARAMETERS)));
            out.put(RubyNodeType.LAMBDA, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.PATTERN, new FieldInfo(true, false, Set.of(RubyNodeType.PATTERN_TOP_EXPR_BODY)));
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.ARG)));
            out.put(RubyNodeType.MATCH_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.BODY,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(RubyNodeType.ARG, RubyNodeType.BODY_STATEMENT, RubyNodeType.RESCUE_MODIFIER)));
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.METHOD_NAME)));
            m.put(RubyNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(RubyNodeType.METHOD_PARAMETERS)));
            out.put(RubyNodeType.METHOD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.BODY_STATEMENT)));
            m.put(
                    RubyNodeField.NAME,
                    new FieldInfo(true, false, Set.of(RubyNodeType.CONSTANT, RubyNodeType.SCOPE_RESOLUTION)));
            out.put(RubyNodeType.MODULE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.LEFT, new FieldInfo(true, false, Set.of(RubyNodeType.LHS)));
            m.put(RubyNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    RubyNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION, RubyNodeType.RESCUE_MODIFIER)));
            out.put(RubyNodeType.OPERATOR_ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.IDENTIFIER)));
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.ARG)));
            out.put(RubyNodeType.OPTIONAL_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.KEY,
                    new FieldInfo(
                            true, false, Set.of(RubyNodeType.ARG, RubyNodeType.HASH_KEY_SYMBOL, RubyNodeType.STRING)));
            m.put(RubyNodeField.VALUE, new FieldInfo(false, false, Set.of(RubyNodeType.ARG)));
            out.put(RubyNodeType.PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.BEGIN,
                    new FieldInfo(false, false, Set.of(RubyNodeType.ARG, RubyNodeType.PATTERN_PRIMITIVE)));
            m.put(
                    RubyNodeField.END,
                    new FieldInfo(false, false, Set.of(RubyNodeType.ARG, RubyNodeType.PATTERN_PRIMITIVE)));
            m.put(RubyNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(RubyNodeType.RANGE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.THEN)));
            m.put(RubyNodeField.EXCEPTIONS, new FieldInfo(false, false, Set.of(RubyNodeType.EXCEPTIONS)));
            m.put(RubyNodeField.VARIABLE, new FieldInfo(false, false, Set.of(RubyNodeType.EXCEPTION_VARIABLE)));
            out.put(RubyNodeType.RESCUE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.ARG, RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.HANDLER, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.RESCUE_MODIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.CONSTANT)));
            m.put(
                    RubyNodeField.SCOPE,
                    new FieldInfo(false, false, Set.of(RubyNodeType.PATTERN_CONSTANT, RubyNodeType.PRIMARY)));
            out.put(RubyNodeType.SCOPE_RESOLUTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.IDENTIFIER)));
            out.put(RubyNodeType.SETTER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.BODY_STATEMENT)));
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.ARG)));
            out.put(RubyNodeType.SINGLETON_CLASS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.BODY,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(RubyNodeType.ARG, RubyNodeType.BODY_STATEMENT, RubyNodeType.RESCUE_MODIFIER)));
            m.put(RubyNodeField.NAME, new FieldInfo(true, false, Set.of(RubyNodeType.METHOD_NAME)));
            m.put(RubyNodeField.OBJECT, new FieldInfo(true, false, Set.of(RubyNodeType.ARG, RubyNodeType.VARIABLE)));
            m.put(RubyNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(RubyNodeType.METHOD_PARAMETERS)));
            out.put(RubyNodeType.SINGLETON_METHOD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.NAME, new FieldInfo(false, false, Set.of(RubyNodeType.IDENTIFIER)));
            out.put(RubyNodeType.SPLAT_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.PATTERN, new FieldInfo(true, false, Set.of(RubyNodeType.PATTERN_TOP_EXPR_BODY)));
            m.put(RubyNodeField.VALUE, new FieldInfo(true, false, Set.of(RubyNodeType.ARG)));
            out.put(RubyNodeType.TEST_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.OPERAND,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    RubyNodeType.EXPRESSION,
                                    RubyNodeType.PARENTHESIZED_STATEMENTS,
                                    RubyNodeType.SIMPLE_NUMERIC)));
            m.put(RubyNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(RubyNodeType.UNARY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.ALTERNATIVE,
                    new FieldInfo(false, false, Set.of(RubyNodeType.ELSE_, RubyNodeType.ELSIF)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.CONSEQUENCE, new FieldInfo(false, false, Set.of(RubyNodeType.THEN)));
            out.put(RubyNodeType.UNLESS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.UNLESS_GUARD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.UNLESS_MODIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.DO_)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            out.put(RubyNodeType.UNTIL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.UNTIL_MODIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(
                    RubyNodeField.NAME,
                    new FieldInfo(true, false, Set.of(RubyNodeType.IDENTIFIER, RubyNodeType.NONLOCAL_VARIABLE)));
            out.put(RubyNodeType.VARIABLE_REFERENCE_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(false, false, Set.of(RubyNodeType.THEN)));
            m.put(RubyNodeField.PATTERN, new FieldInfo(true, true, Set.of(RubyNodeType.PATTERN)));
            out.put(RubyNodeType.WHEN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.DO_)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            out.put(RubyNodeType.WHILE_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<RubyNodeField, FieldInfo> m = new EnumMap<>(RubyNodeField.class);
            m.put(RubyNodeField.BODY, new FieldInfo(true, false, Set.of(RubyNodeType.STATEMENT)));
            m.put(RubyNodeField.CONDITION, new FieldInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
            out.put(RubyNodeType.WHILE_MODIFIER, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<RubyNodeType, ChildInfo> initChildren() {
        EnumMap<RubyNodeType, ChildInfo> out = new EnumMap<>(RubyNodeType.class);
        out.put(
                RubyNodeType.ARGUMENT_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.BLOCK_ARGUMENT,
                                RubyNodeType.EXPRESSION,
                                RubyNodeType.FORWARD_ARGUMENT,
                                RubyNodeType.HASH_SPLAT_ARGUMENT,
                                RubyNodeType.PAIR,
                                RubyNodeType.SPLAT_ARGUMENT)));
        out.put(
                RubyNodeType.ARRAY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.BLOCK_ARGUMENT,
                                RubyNodeType.EXPRESSION,
                                RubyNodeType.FORWARD_ARGUMENT,
                                RubyNodeType.HASH_SPLAT_ARGUMENT,
                                RubyNodeType.PAIR,
                                RubyNodeType.SPLAT_ARGUMENT)));
        out.put(
                RubyNodeType.ARRAY_PATTERN,
                new ChildInfo(false, true, Set.of(RubyNodeType.PATTERN_EXPR, RubyNodeType.SPLAT_PARAMETER)));
        out.put(
                RubyNodeType.BARE_STRING,
                new ChildInfo(
                        true,
                        true,
                        Set.of(RubyNodeType.ESCAPE_SEQUENCE, RubyNodeType.INTERPOLATION, RubyNodeType.STRING_CONTENT)));
        out.put(
                RubyNodeType.BARE_SYMBOL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(RubyNodeType.ESCAPE_SEQUENCE, RubyNodeType.INTERPOLATION, RubyNodeType.STRING_CONTENT)));
        out.put(
                RubyNodeType.BEGIN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.ELSE_,
                                RubyNodeType.EMPTY_STATEMENT,
                                RubyNodeType.ENSURE,
                                RubyNodeType.RESCUE,
                                RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.BEGIN_BLOCK,
                new ChildInfo(false, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(RubyNodeType.BLOCK_ARGUMENT, new ChildInfo(false, false, Set.of(RubyNodeType.ARG)));
        out.put(
                RubyNodeType.BLOCK_BODY,
                new ChildInfo(true, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.BLOCK_PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.BLOCK_PARAMETER,
                                RubyNodeType.DESTRUCTURED_PARAMETER,
                                RubyNodeType.FORWARD_PARAMETER,
                                RubyNodeType.HASH_SPLAT_NIL,
                                RubyNodeType.HASH_SPLAT_PARAMETER,
                                RubyNodeType.IDENTIFIER,
                                RubyNodeType.KEYWORD_PARAMETER,
                                RubyNodeType.OPTIONAL_PARAMETER,
                                RubyNodeType.SPLAT_PARAMETER)));
        out.put(
                RubyNodeType.BODY_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RubyNodeType.ELSE_,
                                RubyNodeType.EMPTY_STATEMENT,
                                RubyNodeType.ENSURE,
                                RubyNodeType.RESCUE,
                                RubyNodeType.STATEMENT)));
        out.put(RubyNodeType.BREAK_, new ChildInfo(false, false, Set.of(RubyNodeType.ARGUMENT_LIST)));
        out.put(RubyNodeType.CASE_, new ChildInfo(false, true, Set.of(RubyNodeType.ELSE_, RubyNodeType.WHEN)));
        out.put(RubyNodeType.CHAINED_STRING, new ChildInfo(true, true, Set.of(RubyNodeType.STRING)));
        out.put(
                RubyNodeType.COMPLEX,
                new ChildInfo(true, false, Set.of(RubyNodeType.FLOAT_, RubyNodeType.INTEGER, RubyNodeType.RATIONAL)));
        out.put(
                RubyNodeType.DELIMITED_SYMBOL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(RubyNodeType.ESCAPE_SEQUENCE, RubyNodeType.INTERPOLATION, RubyNodeType.STRING_CONTENT)));
        out.put(
                RubyNodeType.DESTRUCTURED_LEFT_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RubyNodeType.DESTRUCTURED_LEFT_ASSIGNMENT,
                                RubyNodeType.LHS,
                                RubyNodeType.REST_ASSIGNMENT)));
        out.put(
                RubyNodeType.DESTRUCTURED_PARAMETER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.BLOCK_PARAMETER,
                                RubyNodeType.DESTRUCTURED_PARAMETER,
                                RubyNodeType.FORWARD_PARAMETER,
                                RubyNodeType.HASH_SPLAT_NIL,
                                RubyNodeType.HASH_SPLAT_PARAMETER,
                                RubyNodeType.IDENTIFIER,
                                RubyNodeType.KEYWORD_PARAMETER,
                                RubyNodeType.OPTIONAL_PARAMETER,
                                RubyNodeType.SPLAT_PARAMETER)));
        out.put(
                RubyNodeType.DO_,
                new ChildInfo(false, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.ELEMENT_REFERENCE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.BLOCK_ARGUMENT,
                                RubyNodeType.EXPRESSION,
                                RubyNodeType.FORWARD_ARGUMENT,
                                RubyNodeType.HASH_SPLAT_ARGUMENT,
                                RubyNodeType.PAIR,
                                RubyNodeType.SPLAT_ARGUMENT)));
        out.put(
                RubyNodeType.ELSE_,
                new ChildInfo(false, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.END_BLOCK,
                new ChildInfo(false, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.ENSURE,
                new ChildInfo(false, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.EXCEPTIONS,
                new ChildInfo(true, true, Set.of(RubyNodeType.ARG, RubyNodeType.SPLAT_ARGUMENT)));
        out.put(RubyNodeType.EXCEPTION_VARIABLE, new ChildInfo(true, false, Set.of(RubyNodeType.LHS)));
        out.put(
                RubyNodeType.FIND_PATTERN,
                new ChildInfo(true, true, Set.of(RubyNodeType.PATTERN_EXPR, RubyNodeType.SPLAT_PARAMETER)));
        out.put(
                RubyNodeType.HASH,
                new ChildInfo(false, true, Set.of(RubyNodeType.HASH_SPLAT_ARGUMENT, RubyNodeType.PAIR)));
        out.put(
                RubyNodeType.HASH_PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.HASH_SPLAT_NIL,
                                RubyNodeType.HASH_SPLAT_PARAMETER,
                                RubyNodeType.KEYWORD_PATTERN)));
        out.put(RubyNodeType.HASH_SPLAT_ARGUMENT, new ChildInfo(false, false, Set.of(RubyNodeType.ARG)));
        out.put(
                RubyNodeType.HEREDOC_BODY,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RubyNodeType.ESCAPE_SEQUENCE,
                                RubyNodeType.HEREDOC_CONTENT,
                                RubyNodeType.HEREDOC_END,
                                RubyNodeType.INTERPOLATION)));
        out.put(RubyNodeType.IN, new ChildInfo(true, false, Set.of(RubyNodeType.ARG)));
        out.put(
                RubyNodeType.INTERPOLATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.NONLOCAL_VARIABLE, RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.LAMBDA_PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.BLOCK_PARAMETER,
                                RubyNodeType.DESTRUCTURED_PARAMETER,
                                RubyNodeType.FORWARD_PARAMETER,
                                RubyNodeType.HASH_SPLAT_NIL,
                                RubyNodeType.HASH_SPLAT_PARAMETER,
                                RubyNodeType.IDENTIFIER,
                                RubyNodeType.KEYWORD_PARAMETER,
                                RubyNodeType.OPTIONAL_PARAMETER,
                                RubyNodeType.SPLAT_PARAMETER)));
        out.put(
                RubyNodeType.LEFT_ASSIGNMENT_LIST,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                RubyNodeType.DESTRUCTURED_LEFT_ASSIGNMENT,
                                RubyNodeType.LHS,
                                RubyNodeType.REST_ASSIGNMENT)));
        out.put(
                RubyNodeType.METHOD_PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                RubyNodeType.BLOCK_PARAMETER,
                                RubyNodeType.DESTRUCTURED_PARAMETER,
                                RubyNodeType.FORWARD_PARAMETER,
                                RubyNodeType.HASH_SPLAT_NIL,
                                RubyNodeType.HASH_SPLAT_PARAMETER,
                                RubyNodeType.IDENTIFIER,
                                RubyNodeType.KEYWORD_PARAMETER,
                                RubyNodeType.OPTIONAL_PARAMETER,
                                RubyNodeType.SPLAT_PARAMETER)));
        out.put(RubyNodeType.NEXT, new ChildInfo(false, false, Set.of(RubyNodeType.ARGUMENT_LIST)));
        out.put(RubyNodeType.PARENTHESIZED_PATTERN, new ChildInfo(true, false, Set.of(RubyNodeType.PATTERN_EXPR)));
        out.put(
                RubyNodeType.PARENTHESIZED_STATEMENTS,
                new ChildInfo(false, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(
                RubyNodeType.PATTERN,
                new ChildInfo(true, false, Set.of(RubyNodeType.ARG, RubyNodeType.SPLAT_ARGUMENT)));
        out.put(
                RubyNodeType.PROGRAM,
                new ChildInfo(
                        false,
                        true,
                        Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT, RubyNodeType.UNINTERPRETED)));
        out.put(RubyNodeType.RATIONAL, new ChildInfo(true, false, Set.of(RubyNodeType.FLOAT_, RubyNodeType.INTEGER)));
        out.put(RubyNodeType.REDO, new ChildInfo(false, false, Set.of(RubyNodeType.ARGUMENT_LIST)));
        out.put(
                RubyNodeType.REGEX,
                new ChildInfo(
                        false,
                        true,
                        Set.of(RubyNodeType.ESCAPE_SEQUENCE, RubyNodeType.INTERPOLATION, RubyNodeType.STRING_CONTENT)));
        out.put(RubyNodeType.REST_ASSIGNMENT, new ChildInfo(false, false, Set.of(RubyNodeType.LHS)));
        out.put(RubyNodeType.RETRY, new ChildInfo(false, false, Set.of(RubyNodeType.ARGUMENT_LIST)));
        out.put(RubyNodeType.RETURN_, new ChildInfo(false, false, Set.of(RubyNodeType.ARGUMENT_LIST)));
        out.put(
                RubyNodeType.RIGHT_ASSIGNMENT_LIST,
                new ChildInfo(true, true, Set.of(RubyNodeType.ARG, RubyNodeType.SPLAT_ARGUMENT)));
        out.put(RubyNodeType.SPLAT_ARGUMENT, new ChildInfo(false, false, Set.of(RubyNodeType.ARG)));
        out.put(
                RubyNodeType.STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(RubyNodeType.ESCAPE_SEQUENCE, RubyNodeType.INTERPOLATION, RubyNodeType.STRING_CONTENT)));
        out.put(RubyNodeType.STRING_ARRAY, new ChildInfo(false, true, Set.of(RubyNodeType.BARE_STRING)));
        out.put(
                RubyNodeType.SUBSHELL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(RubyNodeType.ESCAPE_SEQUENCE, RubyNodeType.INTERPOLATION, RubyNodeType.STRING_CONTENT)));
        out.put(RubyNodeType.SUPERCLASS, new ChildInfo(true, false, Set.of(RubyNodeType.EXPRESSION)));
        out.put(RubyNodeType.SYMBOL_ARRAY, new ChildInfo(false, true, Set.of(RubyNodeType.BARE_SYMBOL)));
        out.put(
                RubyNodeType.THEN,
                new ChildInfo(false, true, Set.of(RubyNodeType.EMPTY_STATEMENT, RubyNodeType.STATEMENT)));
        out.put(RubyNodeType.UNDEF, new ChildInfo(true, true, Set.of(RubyNodeType.METHOD_NAME)));
        out.put(RubyNodeType.YIELD_, new ChildInfo(false, false, Set.of(RubyNodeType.ARGUMENT_LIST)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<RubyNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<RubyNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<RubyNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<RubyNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
