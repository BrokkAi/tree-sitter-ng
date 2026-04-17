package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code julia} from tree-sitter {@code node-types.json}.
 */
public final class JuliaNodeSchema {
    private JuliaNodeSchema() {}

    public static Set<JuliaNodeField> fields(@Nullable JuliaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<JuliaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<JuliaNodeType> allowedTypes(@Nullable JuliaNodeType owner, @Nullable JuliaNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<JuliaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable JuliaNodeType owner, @Nullable JuliaNodeField field) {
        if (owner == null || field == null) return false;
        Map<JuliaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable JuliaNodeType owner, @Nullable JuliaNodeField field) {
        if (owner == null || field == null) return false;
        Map<JuliaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<JuliaNodeType> allowedChildTypes(@Nullable JuliaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable JuliaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable JuliaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<JuliaNodeType, Map<JuliaNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<JuliaNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<JuliaNodeType, Map<JuliaNodeField, FieldInfo>> initFields() {
        EnumMap<JuliaNodeType, Map<JuliaNodeField, FieldInfo>> out = new EnumMap<>(JuliaNodeType.class);
        {
            EnumMap<JuliaNodeField, FieldInfo> m = new EnumMap<>(JuliaNodeField.class);
            m.put(JuliaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JuliaNodeType.EXPRESSION)));
            out.put(JuliaNodeType.ELSEIF_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JuliaNodeField, FieldInfo> m = new EnumMap<>(JuliaNodeField.class);
            m.put(
                    JuliaNodeField.VALUE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    JuliaNodeType.ADJOINT_EXPRESSION,
                                    JuliaNodeType.BOOLEAN_LITERAL,
                                    JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                    JuliaNodeType.CALL_EXPRESSION,
                                    JuliaNodeType.CHARACTER_LITERAL,
                                    JuliaNodeType.COMMAND_LITERAL,
                                    JuliaNodeType.COMPREHENSION_EXPRESSION,
                                    JuliaNodeType.CURLY_EXPRESSION,
                                    JuliaNodeType.FIELD_EXPRESSION,
                                    JuliaNodeType.IDENTIFIER,
                                    JuliaNodeType.INDEX_EXPRESSION,
                                    JuliaNodeType.INTERPOLATION_EXPRESSION,
                                    JuliaNodeType.MACROCALL_EXPRESSION,
                                    JuliaNodeType.MATRIX_EXPRESSION,
                                    JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                    JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                    JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                    JuliaNodeType.PREFIXED_STRING_LITERAL,
                                    JuliaNodeType.QUOTE_EXPRESSION,
                                    JuliaNodeType.STRING_LITERAL,
                                    JuliaNodeType.TUPLE_EXPRESSION,
                                    JuliaNodeType.VECTOR_EXPRESSION)));
            out.put(JuliaNodeType.FIELD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JuliaNodeField, FieldInfo> m = new EnumMap<>(JuliaNodeField.class);
            m.put(
                    JuliaNodeField.ALTERNATIVE,
                    new FieldInfo(false, true, Set.of(JuliaNodeType.ELSEIF_CLAUSE, JuliaNodeType.ELSE_CLAUSE)));
            m.put(JuliaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JuliaNodeType.EXPRESSION)));
            out.put(JuliaNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JuliaNodeField, FieldInfo> m = new EnumMap<>(JuliaNodeField.class);
            m.put(
                    JuliaNodeField.NAME,
                    new FieldInfo(
                            true, false, Set.of(JuliaNodeType.IDENTIFIER, JuliaNodeType.INTERPOLATION_EXPRESSION)));
            out.put(JuliaNodeType.MODULE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JuliaNodeField, FieldInfo> m = new EnumMap<>(JuliaNodeField.class);
            m.put(JuliaNodeField.PREFIX, new FieldInfo(true, false, Set.of(JuliaNodeType.IDENTIFIER)));
            m.put(JuliaNodeField.SUFFIX, new FieldInfo(false, false, Set.of(JuliaNodeType.IDENTIFIER)));
            out.put(JuliaNodeType.PREFIXED_COMMAND_LITERAL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JuliaNodeField, FieldInfo> m = new EnumMap<>(JuliaNodeField.class);
            m.put(JuliaNodeField.PREFIX, new FieldInfo(true, false, Set.of(JuliaNodeType.IDENTIFIER)));
            m.put(JuliaNodeField.SUFFIX, new FieldInfo(false, false, Set.of(JuliaNodeType.IDENTIFIER)));
            out.put(JuliaNodeType.PREFIXED_STRING_LITERAL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JuliaNodeField, FieldInfo> m = new EnumMap<>(JuliaNodeField.class);
            m.put(JuliaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JuliaNodeType.EXPRESSION)));
            out.put(JuliaNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<JuliaNodeType, ChildInfo> initChildren() {
        EnumMap<JuliaNodeType, ChildInfo> out = new EnumMap<>(JuliaNodeType.class);
        out.put(JuliaNodeType.ABSTRACT_DEFINITION, new ChildInfo(true, false, Set.of(JuliaNodeType.TYPE_HEAD)));
        out.put(
                JuliaNodeType.ADJOINT_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.ARGUMENT_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.GENERATOR)));
        out.put(
                JuliaNodeType.ARROW_FUNCTION_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JuliaNodeType.ARGUMENT_LIST, JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(JuliaNodeType.BINARY_EXPRESSION, new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.BLOCK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(
                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.ARGUMENT_LIST,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.DO_CLAUSE,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.CALL_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.ARGUMENT_LIST,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.DO_CLAUSE,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.OPERATOR,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.CATCH_CLAUSE,
                new ChildInfo(false, true, Set.of(JuliaNodeType.BLOCK, JuliaNodeType.IDENTIFIER)));
        out.put(
                JuliaNodeType.COMMAND_LITERAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JuliaNodeType.CONTENT,
                                JuliaNodeType.ESCAPE_SEQUENCE,
                                JuliaNodeType.STRING_INTERPOLATION)));
        out.put(
                JuliaNodeType.COMPOUND_ASSIGNMENT_EXPRESSION,
                new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(JuliaNodeType.COMPOUND_STATEMENT, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        out.put(
                JuliaNodeType.COMPREHENSION_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ASSIGNMENT,
                                JuliaNodeType.EXPRESSION,
                                JuliaNodeType.FOR_CLAUSE,
                                JuliaNodeType.IF_CLAUSE)));
        out.put(
                JuliaNodeType.CONST_STATEMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(
                JuliaNodeType.CURLY_EXPRESSION,
                new ChildInfo(false, true, Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.DO_CLAUSE,
                new ChildInfo(
                        false, true, Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.BLOCK, JuliaNodeType.EXPRESSION)));
        out.put(JuliaNodeType.ELSEIF_CLAUSE, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        out.put(JuliaNodeType.ELSE_CLAUSE, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        out.put(
                JuliaNodeType.EXPORT_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.OPERATOR)));
        out.put(
                JuliaNodeType.FIELD_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(JuliaNodeType.FINALLY_CLAUSE, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        out.put(JuliaNodeType.FOR_BINDING, new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(JuliaNodeType.FOR_CLAUSE, new ChildInfo(true, true, Set.of(JuliaNodeType.FOR_BINDING)));
        out.put(
                JuliaNodeType.FOR_STATEMENT,
                new ChildInfo(true, true, Set.of(JuliaNodeType.BLOCK, JuliaNodeType.FOR_BINDING)));
        out.put(
                JuliaNodeType.FUNCTION_DEFINITION,
                new ChildInfo(true, true, Set.of(JuliaNodeType.BLOCK, JuliaNodeType.SIGNATURE)));
        out.put(
                JuliaNodeType.GENERATOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ASSIGNMENT,
                                JuliaNodeType.EXPRESSION,
                                JuliaNodeType.FOR_CLAUSE,
                                JuliaNodeType.IF_CLAUSE)));
        out.put(
                JuliaNodeType.GLOBAL_STATEMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(JuliaNodeType.IF_CLAUSE, new ChildInfo(true, false, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(JuliaNodeType.IF_STATEMENT, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        out.put(
                JuliaNodeType.IMPORT_ALIAS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.IMPORT_PATH,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.OPERATOR)));
        out.put(
                JuliaNodeType.IMPORT_PATH,
                new ChildInfo(true, true, Set.of(JuliaNodeType.IDENTIFIER, JuliaNodeType.INTERPOLATION_EXPRESSION)));
        out.put(
                JuliaNodeType.IMPORT_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.IMPORT_ALIAS,
                                JuliaNodeType.IMPORT_PATH,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.OPERATOR,
                                JuliaNodeType.SELECTED_IMPORT)));
        out.put(
                JuliaNodeType.INDEX_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.INTERPOLATION_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FLOAT_LITERAL,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INTEGER_LITERAL,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.JUXTAPOSITION_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.FLOAT_LITERAL,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTEGER_LITERAL,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.LET_STATEMENT,
                new ChildInfo(
                        false, true, Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.BLOCK, JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.LOCAL_STATEMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(
                JuliaNodeType.MACROCALL_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ARGUMENT_LIST,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.DO_CLAUSE,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.MACRO_ARGUMENT_LIST,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.MACRO_ARGUMENT_LIST,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(
                JuliaNodeType.MACRO_DEFINITION,
                new ChildInfo(true, true, Set.of(JuliaNodeType.BLOCK, JuliaNodeType.SIGNATURE)));
        out.put(
                JuliaNodeType.MACRO_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(JuliaNodeType.FIELD_EXPRESSION, JuliaNodeType.IDENTIFIER, JuliaNodeType.OPERATOR)));
        out.put(JuliaNodeType.MATRIX_EXPRESSION, new ChildInfo(true, true, Set.of(JuliaNodeType.MATRIX_ROW)));
        out.put(
                JuliaNodeType.MATRIX_ROW,
                new ChildInfo(true, true, Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION)));
        out.put(JuliaNodeType.MODULE_DEFINITION, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        out.put(JuliaNodeType.OPEN_TUPLE, new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.GENERATOR)));
        out.put(
                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                new ChildInfo(false, true, Set.of(JuliaNodeType.CONTENT, JuliaNodeType.ESCAPE_SEQUENCE)));
        out.put(
                JuliaNodeType.PREFIXED_STRING_LITERAL,
                new ChildInfo(false, true, Set.of(JuliaNodeType.CONTENT, JuliaNodeType.ESCAPE_SEQUENCE)));
        out.put(
                JuliaNodeType.PRIMITIVE_DEFINITION,
                new ChildInfo(true, true, Set.of(JuliaNodeType.INTEGER_LITERAL, JuliaNodeType.TYPE_HEAD)));
        out.put(
                JuliaNodeType.PUBLIC_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.OPERATOR)));
        out.put(
                JuliaNodeType.QUOTE_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FLOAT_LITERAL,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INTEGER_LITERAL,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.OPERATOR,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(JuliaNodeType.QUOTE_STATEMENT, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        out.put(JuliaNodeType.RANGE_EXPRESSION, new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.RETURN_STATEMENT,
                new ChildInfo(
                        false,
                        false,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(
                JuliaNodeType.SELECTED_IMPORT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.IMPORT_ALIAS,
                                JuliaNodeType.IMPORT_PATH,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.OPERATOR)));
        out.put(
                JuliaNodeType.SIGNATURE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JuliaNodeType.ARGUMENT_LIST,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.TYPED_EXPRESSION,
                                JuliaNodeType.WHERE_EXPRESSION)));
        out.put(
                JuliaNodeType.SOURCE_FILE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.OPEN_TUPLE)));
        out.put(JuliaNodeType.SPLAT_EXPRESSION, new ChildInfo(true, false, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.STRING_INTERPOLATION,
                new ChildInfo(true, false, Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.STRING_LITERAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JuliaNodeType.CONTENT,
                                JuliaNodeType.ESCAPE_SEQUENCE,
                                JuliaNodeType.STRING_INTERPOLATION)));
        out.put(
                JuliaNodeType.STRUCT_DEFINITION,
                new ChildInfo(true, true, Set.of(JuliaNodeType.BLOCK, JuliaNodeType.TYPE_HEAD)));
        out.put(
                JuliaNodeType.TERNARY_EXPRESSION,
                new ChildInfo(true, true, Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.TRY_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.BLOCK,
                                JuliaNodeType.CATCH_CLAUSE,
                                JuliaNodeType.ELSE_CLAUSE,
                                JuliaNodeType.FINALLY_CLAUSE)));
        out.put(
                JuliaNodeType.TUPLE_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION, JuliaNodeType.GENERATOR)));
        out.put(JuliaNodeType.TYPED_EXPRESSION, new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.TYPE_HEAD,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.BINARY_EXPRESSION,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(JuliaNodeType.UNARY_EXPRESSION, new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(
                JuliaNodeType.UNARY_TYPED_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JuliaNodeType.ADJOINT_EXPRESSION,
                                JuliaNodeType.BOOLEAN_LITERAL,
                                JuliaNodeType.BROADCAST_CALL_EXPRESSION,
                                JuliaNodeType.CALL_EXPRESSION,
                                JuliaNodeType.CHARACTER_LITERAL,
                                JuliaNodeType.COMMAND_LITERAL,
                                JuliaNodeType.COMPREHENSION_EXPRESSION,
                                JuliaNodeType.CURLY_EXPRESSION,
                                JuliaNodeType.FIELD_EXPRESSION,
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.INDEX_EXPRESSION,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACROCALL_EXPRESSION,
                                JuliaNodeType.MATRIX_EXPRESSION,
                                JuliaNodeType.PARAMETRIZED_TYPE_EXPRESSION,
                                JuliaNodeType.PARENTHESIZED_EXPRESSION,
                                JuliaNodeType.PREFIXED_COMMAND_LITERAL,
                                JuliaNodeType.PREFIXED_STRING_LITERAL,
                                JuliaNodeType.QUOTE_EXPRESSION,
                                JuliaNodeType.STRING_LITERAL,
                                JuliaNodeType.TUPLE_EXPRESSION,
                                JuliaNodeType.VECTOR_EXPRESSION)));
        out.put(
                JuliaNodeType.USING_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JuliaNodeType.IDENTIFIER,
                                JuliaNodeType.IMPORT_ALIAS,
                                JuliaNodeType.IMPORT_PATH,
                                JuliaNodeType.INTERPOLATION_EXPRESSION,
                                JuliaNodeType.MACRO_IDENTIFIER,
                                JuliaNodeType.OPERATOR,
                                JuliaNodeType.SELECTED_IMPORT)));
        out.put(
                JuliaNodeType.VECTOR_EXPRESSION,
                new ChildInfo(false, true, Set.of(JuliaNodeType.ASSIGNMENT, JuliaNodeType.EXPRESSION)));
        out.put(JuliaNodeType.WHERE_EXPRESSION, new ChildInfo(true, true, Set.of(JuliaNodeType.EXPRESSION)));
        out.put(JuliaNodeType.WHILE_STATEMENT, new ChildInfo(false, false, Set.of(JuliaNodeType.BLOCK)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<JuliaNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<JuliaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<JuliaNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<JuliaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
