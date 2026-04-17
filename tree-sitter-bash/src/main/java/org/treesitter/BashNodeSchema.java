package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code bash} from tree-sitter {@code node-types.json}.
 */
public final class BashNodeSchema {
    private BashNodeSchema() {}

    public static Set<BashNodeField> fields(@Nullable BashNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<BashNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<BashNodeType> allowedTypes(@Nullable BashNodeType owner, @Nullable BashNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<BashNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable BashNodeType owner, @Nullable BashNodeField field) {
        if (owner == null || field == null) return false;
        Map<BashNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable BashNodeType owner, @Nullable BashNodeField field) {
        if (owner == null || field == null) return false;
        Map<BashNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<BashNodeType> allowedChildTypes(@Nullable BashNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable BashNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable BashNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<BashNodeType, Map<BashNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<BashNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<BashNodeType, Map<BashNodeField, FieldInfo>> initFields() {
        EnumMap<BashNodeType, Map<BashNodeField, FieldInfo>> out = new EnumMap<>(BashNodeType.class);
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.LEFT,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.EXPRESSION,
                                    BashNodeType.NUMBER,
                                    BashNodeType.RAW_STRING,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.SUBSCRIPT,
                                    BashNodeType.VARIABLE_NAME)));
            m.put(BashNodeField.OPERATOR, new FieldInfo(true, false, Set.of(BashNodeType.TEST_OPERATOR)));
            m.put(
                    BashNodeField.RIGHT,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.EXPRESSION,
                                    BashNodeType.EXTGLOB_PATTERN,
                                    BashNodeType.NUMBER,
                                    BashNodeType.RAW_STRING,
                                    BashNodeType.REGEX,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.SUBSCRIPT,
                                    BashNodeType.VARIABLE_NAME)));
            out.put(BashNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.FALLTHROUGH, new FieldInfo(false, false, Collections.emptySet()));
            m.put(BashNodeField.TERMINATION, new FieldInfo(false, false, Collections.emptySet()));
            m.put(
                    BashNodeField.VALUE,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    BashNodeType.CONCATENATION,
                                    BashNodeType.EXTGLOB_PATTERN,
                                    BashNodeType.PRIMARY_EXPRESSION)));
            out.put(BashNodeType.CASE_ITEM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION)));
            out.put(BashNodeType.CASE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.ARGUMENT,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION, BashNodeType.REGEX)));
            m.put(BashNodeField.NAME, new FieldInfo(true, false, Set.of(BashNodeType.COMMAND_NAME)));
            m.put(
                    BashNodeField.REDIRECT,
                    new FieldInfo(false, true, Set.of(BashNodeType.FILE_REDIRECT, BashNodeType.HERESTRING_REDIRECT)));
            out.put(BashNodeType.COMMAND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.REDIRECT, new FieldInfo(false, false, Set.of(BashNodeType.FILE_REDIRECT)));
            out.put(BashNodeType.COMMAND_SUBSTITUTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.BODY,
                    new FieldInfo(true, false, Set.of(BashNodeType.COMPOUND_STATEMENT, BashNodeType.DO_GROUP)));
            m.put(
                    BashNodeField.CONDITION,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    BashNodeType.BINARY_EXPRESSION,
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.NUMBER,
                                    BashNodeType.PARENTHESIZED_EXPRESSION,
                                    BashNodeType.POSTFIX_EXPRESSION,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.UNARY_EXPRESSION,
                                    BashNodeType.VARIABLE_ASSIGNMENT,
                                    BashNodeType.WORD)));
            m.put(
                    BashNodeField.INITIALIZER,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    BashNodeType.BINARY_EXPRESSION,
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.NUMBER,
                                    BashNodeType.PARENTHESIZED_EXPRESSION,
                                    BashNodeType.POSTFIX_EXPRESSION,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.UNARY_EXPRESSION,
                                    BashNodeType.VARIABLE_ASSIGNMENT,
                                    BashNodeType.WORD)));
            m.put(
                    BashNodeField.UPDATE,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    BashNodeType.BINARY_EXPRESSION,
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.NUMBER,
                                    BashNodeType.PARENTHESIZED_EXPRESSION,
                                    BashNodeType.POSTFIX_EXPRESSION,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.UNARY_EXPRESSION,
                                    BashNodeType.VARIABLE_ASSIGNMENT,
                                    BashNodeType.WORD)));
            out.put(BashNodeType.C_STYLE_FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.OPERATOR, new FieldInfo(false, true, Collections.emptySet()));
            out.put(BashNodeType.EXPANSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.DESCRIPTOR, new FieldInfo(false, false, Set.of(BashNodeType.FILE_DESCRIPTOR)));
            m.put(
                    BashNodeField.DESTINATION,
                    new FieldInfo(false, true, Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION)));
            out.put(BashNodeType.FILE_REDIRECT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.BODY, new FieldInfo(true, false, Set.of(BashNodeType.DO_GROUP)));
            m.put(
                    BashNodeField.VALUE,
                    new FieldInfo(false, true, Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION)));
            m.put(BashNodeField.VARIABLE, new FieldInfo(true, false, Set.of(BashNodeType.VARIABLE_NAME)));
            out.put(BashNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.BODY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    BashNodeType.COMPOUND_STATEMENT,
                                    BashNodeType.IF_STATEMENT,
                                    BashNodeType.SUBSHELL,
                                    BashNodeType.TEST_COMMAND)));
            m.put(BashNodeField.NAME, new FieldInfo(true, false, Set.of(BashNodeType.WORD)));
            m.put(
                    BashNodeField.REDIRECT,
                    new FieldInfo(false, false, Set.of(BashNodeType.FILE_REDIRECT, BashNodeType.HERESTRING_REDIRECT)));
            out.put(BashNodeType.FUNCTION_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.ARGUMENT,
                    new FieldInfo(false, true, Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION)));
            m.put(BashNodeField.DESCRIPTOR, new FieldInfo(false, false, Set.of(BashNodeType.FILE_DESCRIPTOR)));
            m.put(BashNodeField.OPERATOR, new FieldInfo(false, false, Collections.emptySet()));
            m.put(
                    BashNodeField.REDIRECT,
                    new FieldInfo(false, true, Set.of(BashNodeType.FILE_REDIRECT, BashNodeType.HERESTRING_REDIRECT)));
            m.put(BashNodeField.RIGHT, new FieldInfo(false, false, Set.of(BashNodeType.STATEMENT)));
            out.put(BashNodeType.HEREDOC_REDIRECT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.DESCRIPTOR, new FieldInfo(false, false, Set.of(BashNodeType.FILE_DESCRIPTOR)));
            out.put(BashNodeType.HERESTRING_REDIRECT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.CONDITION, new FieldInfo(true, true, Set.of(BashNodeType.STATEMENT)));
            out.put(BashNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(BashNodeType.POSTFIX_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.BODY, new FieldInfo(false, false, Set.of(BashNodeType.STATEMENT)));
            m.put(
                    BashNodeField.REDIRECT,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    BashNodeType.FILE_REDIRECT,
                                    BashNodeType.HEREDOC_REDIRECT,
                                    BashNodeType.HERESTRING_REDIRECT)));
            out.put(BashNodeType.REDIRECTED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.INDEX,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    BashNodeType.BINARY_EXPRESSION,
                                    BashNodeType.COMPOUND_STATEMENT,
                                    BashNodeType.CONCATENATION,
                                    BashNodeType.PRIMARY_EXPRESSION,
                                    BashNodeType.SUBSHELL,
                                    BashNodeType.UNARY_EXPRESSION)));
            m.put(BashNodeField.NAME, new FieldInfo(true, false, Set.of(BashNodeType.VARIABLE_NAME)));
            out.put(BashNodeType.SUBSCRIPT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.ALTERNATIVE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.EXPRESSION,
                                    BashNodeType.NUMBER,
                                    BashNodeType.RAW_STRING,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.SUBSCRIPT,
                                    BashNodeType.VARIABLE_NAME)));
            m.put(
                    BashNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.EXPRESSION,
                                    BashNodeType.NUMBER,
                                    BashNodeType.RAW_STRING,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.SUBSCRIPT,
                                    BashNodeType.VARIABLE_NAME)));
            m.put(
                    BashNodeField.CONSEQUENCE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    BashNodeType.COMMAND_SUBSTITUTION,
                                    BashNodeType.EXPANSION,
                                    BashNodeType.EXPRESSION,
                                    BashNodeType.NUMBER,
                                    BashNodeType.RAW_STRING,
                                    BashNodeType.SIMPLE_EXPANSION,
                                    BashNodeType.STRING,
                                    BashNodeType.SUBSCRIPT,
                                    BashNodeType.VARIABLE_NAME)));
            out.put(BashNodeType.TERNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.OPERATOR, new FieldInfo(true, false, Set.of(BashNodeType.TEST_OPERATOR)));
            out.put(BashNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(
                    BashNodeField.NAME,
                    new FieldInfo(true, false, Set.of(BashNodeType.SUBSCRIPT, BashNodeType.VARIABLE_NAME)));
            m.put(
                    BashNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    BashNodeType.ARRAY,
                                    BashNodeType.BINARY_EXPRESSION,
                                    BashNodeType.CONCATENATION,
                                    BashNodeType.PARENTHESIZED_EXPRESSION,
                                    BashNodeType.POSTFIX_EXPRESSION,
                                    BashNodeType.PRIMARY_EXPRESSION,
                                    BashNodeType.UNARY_EXPRESSION,
                                    BashNodeType.VARIABLE_ASSIGNMENT)));
            out.put(BashNodeType.VARIABLE_ASSIGNMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<BashNodeField, FieldInfo> m = new EnumMap<>(BashNodeField.class);
            m.put(BashNodeField.BODY, new FieldInfo(true, false, Set.of(BashNodeType.DO_GROUP)));
            m.put(BashNodeField.CONDITION, new FieldInfo(true, true, Set.of(BashNodeType.STATEMENT)));
            out.put(BashNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<BashNodeType, ChildInfo> initChildren() {
        EnumMap<BashNodeType, ChildInfo> out = new EnumMap<>(BashNodeType.class);
        out.put(
                BashNodeType.ARITHMETIC_EXPANSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                BashNodeType.BINARY_EXPRESSION,
                                BashNodeType.COMMAND_SUBSTITUTION,
                                BashNodeType.EXPANSION,
                                BashNodeType.NUMBER,
                                BashNodeType.PARENTHESIZED_EXPRESSION,
                                BashNodeType.POSTFIX_EXPRESSION,
                                BashNodeType.RAW_STRING,
                                BashNodeType.SIMPLE_EXPANSION,
                                BashNodeType.STRING,
                                BashNodeType.SUBSCRIPT,
                                BashNodeType.TERNARY_EXPRESSION,
                                BashNodeType.UNARY_EXPRESSION,
                                BashNodeType.VARIABLE_NAME)));
        out.put(
                BashNodeType.ARRAY,
                new ChildInfo(false, true, Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION)));
        out.put(
                BashNodeType.BINARY_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                BashNodeType.BINARY_EXPRESSION,
                                BashNodeType.EXPANSION,
                                BashNodeType.NUMBER,
                                BashNodeType.VARIABLE_NAME)));
        out.put(BashNodeType.BRACE_EXPRESSION, new ChildInfo(true, true, Set.of(BashNodeType.NUMBER)));
        out.put(BashNodeType.CASE_ITEM, new ChildInfo(false, true, Set.of(BashNodeType.STATEMENT)));
        out.put(BashNodeType.CASE_STATEMENT, new ChildInfo(false, true, Set.of(BashNodeType.CASE_ITEM)));
        out.put(
                BashNodeType.COMMAND,
                new ChildInfo(false, true, Set.of(BashNodeType.SUBSHELL, BashNodeType.VARIABLE_ASSIGNMENT)));
        out.put(
                BashNodeType.COMMAND_NAME,
                new ChildInfo(true, false, Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION)));
        out.put(BashNodeType.COMMAND_SUBSTITUTION, new ChildInfo(false, true, Set.of(BashNodeType.STATEMENT)));
        out.put(
                BashNodeType.COMPOUND_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                BashNodeType.BINARY_EXPRESSION,
                                BashNodeType.COMMAND_SUBSTITUTION,
                                BashNodeType.EXPANSION,
                                BashNodeType.NUMBER,
                                BashNodeType.PARENTHESIZED_EXPRESSION,
                                BashNodeType.POSTFIX_EXPRESSION,
                                BashNodeType.RAW_STRING,
                                BashNodeType.SIMPLE_EXPANSION,
                                BashNodeType.STATEMENT,
                                BashNodeType.STRING,
                                BashNodeType.SUBSCRIPT,
                                BashNodeType.TERNARY_EXPRESSION,
                                BashNodeType.UNARY_EXPRESSION,
                                BashNodeType.VARIABLE_NAME)));
        out.put(
                BashNodeType.CONCATENATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(BashNodeType.ARRAY, BashNodeType.PRIMARY_EXPRESSION, BashNodeType.VARIABLE_NAME)));
        out.put(
                BashNodeType.DECLARATION_COMMAND,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                BashNodeType.CONCATENATION,
                                BashNodeType.PRIMARY_EXPRESSION,
                                BashNodeType.VARIABLE_ASSIGNMENT,
                                BashNodeType.VARIABLE_NAME)));
        out.put(BashNodeType.DO_GROUP, new ChildInfo(false, true, Set.of(BashNodeType.STATEMENT)));
        out.put(BashNodeType.ELIF_CLAUSE, new ChildInfo(true, true, Set.of(BashNodeType.STATEMENT)));
        out.put(BashNodeType.ELSE_CLAUSE, new ChildInfo(false, true, Set.of(BashNodeType.STATEMENT)));
        out.put(
                BashNodeType.EXPANSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                BashNodeType.ARRAY,
                                BashNodeType.BINARY_EXPRESSION,
                                BashNodeType.CONCATENATION,
                                BashNodeType.PARENTHESIZED_EXPRESSION,
                                BashNodeType.PRIMARY_EXPRESSION,
                                BashNodeType.REGEX,
                                BashNodeType.SPECIAL_VARIABLE_NAME,
                                BashNodeType.SUBSCRIPT,
                                BashNodeType.VARIABLE_NAME)));
        out.put(
                BashNodeType.HEREDOC_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                BashNodeType.COMMAND_SUBSTITUTION,
                                BashNodeType.EXPANSION,
                                BashNodeType.HEREDOC_CONTENT,
                                BashNodeType.SIMPLE_EXPANSION)));
        out.put(
                BashNodeType.HEREDOC_REDIRECT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                BashNodeType.HEREDOC_BODY,
                                BashNodeType.HEREDOC_END,
                                BashNodeType.HEREDOC_START,
                                BashNodeType.PIPELINE)));
        out.put(
                BashNodeType.HERESTRING_REDIRECT,
                new ChildInfo(true, false, Set.of(BashNodeType.CONCATENATION, BashNodeType.PRIMARY_EXPRESSION)));
        out.put(
                BashNodeType.IF_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(BashNodeType.ELIF_CLAUSE, BashNodeType.ELSE_CLAUSE, BashNodeType.STATEMENT)));
        out.put(BashNodeType.LIST, new ChildInfo(true, true, Set.of(BashNodeType.STATEMENT)));
        out.put(
                BashNodeType.NEGATED_COMMAND,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                BashNodeType.COMMAND,
                                BashNodeType.SUBSHELL,
                                BashNodeType.TEST_COMMAND,
                                BashNodeType.VARIABLE_ASSIGNMENT)));
        out.put(
                BashNodeType.NUMBER,
                new ChildInfo(false, false, Set.of(BashNodeType.COMMAND_SUBSTITUTION, BashNodeType.EXPANSION)));
        out.put(
                BashNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                BashNodeType.COMMAND_SUBSTITUTION,
                                BashNodeType.EXPANSION,
                                BashNodeType.EXPRESSION,
                                BashNodeType.NUMBER,
                                BashNodeType.RAW_STRING,
                                BashNodeType.SIMPLE_EXPANSION,
                                BashNodeType.STRING,
                                BashNodeType.SUBSCRIPT,
                                BashNodeType.VARIABLE_ASSIGNMENT,
                                BashNodeType.VARIABLE_NAME)));
        out.put(BashNodeType.PIPELINE, new ChildInfo(true, true, Set.of(BashNodeType.STATEMENT)));
        out.put(
                BashNodeType.POSTFIX_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                BashNodeType.COMMAND_SUBSTITUTION,
                                BashNodeType.EXPANSION,
                                BashNodeType.EXPRESSION,
                                BashNodeType.NUMBER,
                                BashNodeType.RAW_STRING,
                                BashNodeType.SIMPLE_EXPANSION,
                                BashNodeType.STRING,
                                BashNodeType.SUBSCRIPT,
                                BashNodeType.VARIABLE_NAME)));
        out.put(BashNodeType.PROCESS_SUBSTITUTION, new ChildInfo(true, true, Set.of(BashNodeType.STATEMENT)));
        out.put(BashNodeType.PROGRAM, new ChildInfo(false, true, Set.of(BashNodeType.STATEMENT)));
        out.put(
                BashNodeType.REDIRECTED_STATEMENT,
                new ChildInfo(false, false, Set.of(BashNodeType.HERESTRING_REDIRECT)));
        out.put(
                BashNodeType.SIMPLE_EXPANSION,
                new ChildInfo(true, false, Set.of(BashNodeType.SPECIAL_VARIABLE_NAME, BashNodeType.VARIABLE_NAME)));
        out.put(
                BashNodeType.STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                BashNodeType.ARITHMETIC_EXPANSION,
                                BashNodeType.COMMAND_SUBSTITUTION,
                                BashNodeType.EXPANSION,
                                BashNodeType.SIMPLE_EXPANSION,
                                BashNodeType.STRING_CONTENT)));
        out.put(BashNodeType.SUBSHELL, new ChildInfo(true, true, Set.of(BashNodeType.STATEMENT)));
        out.put(
                BashNodeType.TEST_COMMAND,
                new ChildInfo(false, false, Set.of(BashNodeType.EXPRESSION, BashNodeType.REDIRECTED_STATEMENT)));
        out.put(BashNodeType.TRANSLATED_STRING, new ChildInfo(true, false, Set.of(BashNodeType.STRING)));
        out.put(
                BashNodeType.UNARY_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                BashNodeType.COMMAND_SUBSTITUTION,
                                BashNodeType.EXPANSION,
                                BashNodeType.EXPRESSION,
                                BashNodeType.NUMBER,
                                BashNodeType.RAW_STRING,
                                BashNodeType.SIMPLE_EXPANSION,
                                BashNodeType.STRING,
                                BashNodeType.SUBSCRIPT,
                                BashNodeType.VARIABLE_NAME)));
        out.put(
                BashNodeType.UNSET_COMMAND,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                BashNodeType.CONCATENATION,
                                BashNodeType.PRIMARY_EXPRESSION,
                                BashNodeType.VARIABLE_NAME)));
        out.put(BashNodeType.VARIABLE_ASSIGNMENTS, new ChildInfo(true, true, Set.of(BashNodeType.VARIABLE_ASSIGNMENT)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<BashNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<BashNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<BashNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<BashNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
