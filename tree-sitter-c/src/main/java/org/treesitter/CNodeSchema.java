package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code c} from tree-sitter {@code node-types.json}.
 */
public final class CNodeSchema {
    private CNodeSchema() {}

    public static Set<CNodeField> fields(@Nullable CNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<CNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<CNodeType> allowedTypes(@Nullable CNodeType owner, @Nullable CNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<CNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable CNodeType owner, @Nullable CNodeField field) {
        if (owner == null || field == null) return false;
        Map<CNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable CNodeType owner, @Nullable CNodeField field) {
        if (owner == null || field == null) return false;
        Map<CNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<CNodeType> allowedChildTypes(@Nullable CNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable CNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable CNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<CNodeType, Map<CNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<CNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<CNodeType, Map<CNodeField, FieldInfo>> initFields() {
        EnumMap<CNodeType, Map<CNodeField, FieldInfo>> out = new EnumMap<>(CNodeType.class);
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CNodeType.ABSTRACT_DECLARATOR)));
            m.put(CNodeField.SIZE, new FieldInfo(false, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.ABSTRACT_ARRAY_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CNodeType.ABSTRACT_DECLARATOR)));
            m.put(CNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CNodeType.PARAMETER_LIST)));
            out.put(CNodeType.ABSTRACT_FUNCTION_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CNodeType.ABSTRACT_DECLARATOR)));
            out.put(CNodeType.ABSTRACT_POINTER_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_DESCRIPTOR)));
            out.put(CNodeType.ALIGNOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CNodeType.DECLARATOR, CNodeType.FIELD_DECLARATOR, CNodeType.TYPE_DECLARATOR)));
            m.put(CNodeField.SIZE, new FieldInfo(false, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.ARRAY_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CNodeType.CALL_EXPRESSION,
                                    CNodeType.FIELD_EXPRESSION,
                                    CNodeType.IDENTIFIER,
                                    CNodeType.PARENTHESIZED_EXPRESSION,
                                    CNodeType.POINTER_EXPRESSION,
                                    CNodeType.SUBSCRIPT_EXPRESSION)));
            m.put(CNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CNodeField.RIGHT, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.NAME, new FieldInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
            m.put(CNodeField.PREFIX, new FieldInfo(false, false, Set.of(CNodeType.IDENTIFIER)));
            out.put(CNodeType.ATTRIBUTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.LEFT, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION, CNodeType.PREPROC_DEFINED)));
            m.put(CNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    CNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION, CNodeType.PREPROC_DEFINED)));
            out.put(CNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(CNodeType.ARGUMENT_LIST)));
            m.put(CNodeField.FUNCTION, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.VALUE, new FieldInfo(false, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.CASE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_DESCRIPTOR)));
            m.put(CNodeField.VALUE, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.CAST_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.LEFT, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(
                    CNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(CNodeType.COMMA_EXPRESSION, CNodeType.EXPRESSION)));
            out.put(CNodeType.COMMA_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_DESCRIPTOR)));
            m.put(CNodeField.VALUE, new FieldInfo(true, false, Set.of(CNodeType.INITIALIZER_LIST)));
            out.put(CNodeType.COMPOUND_LITERAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(CNodeField.CONDITION, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(
                    CNodeField.CONSEQUENCE,
                    new FieldInfo(false, false, Set.of(CNodeType.COMMA_EXPRESSION, CNodeType.EXPRESSION)));
            out.put(CNodeType.CONDITIONAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CNodeType.ARRAY_DECLARATOR,
                                    CNodeType.ATTRIBUTED_DECLARATOR,
                                    CNodeType.FUNCTION_DECLARATOR,
                                    CNodeType.GNU_ASM_EXPRESSION,
                                    CNodeType.IDENTIFIER,
                                    CNodeType.INIT_DECLARATOR,
                                    CNodeType.MS_CALL_MODIFIER,
                                    CNodeType.PARENTHESIZED_DECLARATOR,
                                    CNodeType.POINTER_DECLARATOR)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_SPECIFIER)));
            out.put(CNodeType.DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.STATEMENT)));
            m.put(CNodeField.CONDITION, new FieldInfo(true, false, Set.of(CNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(CNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.NAME, new FieldInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
            m.put(CNodeField.VALUE, new FieldInfo(false, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.ENUMERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(false, false, Set.of(CNodeType.ENUMERATOR_LIST)));
            m.put(CNodeField.NAME, new FieldInfo(false, false, Set.of(CNodeType.TYPE_IDENTIFIER)));
            m.put(CNodeField.UNDERLYING_TYPE, new FieldInfo(false, false, Set.of(CNodeType.PRIMITIVE_TYPE)));
            out.put(CNodeType.ENUM_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.DECLARATOR, new FieldInfo(false, true, Set.of(CNodeType.FIELD_DECLARATOR)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_SPECIFIER)));
            out.put(CNodeType.FIELD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(CNodeField.FIELD, new FieldInfo(true, false, Set.of(CNodeType.FIELD_IDENTIFIER)));
            m.put(CNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CNodeType.FIELD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.STATEMENT)));
            m.put(
                    CNodeField.CONDITION,
                    new FieldInfo(false, false, Set.of(CNodeType.COMMA_EXPRESSION, CNodeType.EXPRESSION)));
            m.put(
                    CNodeField.INITIALIZER,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CNodeType.COMMA_EXPRESSION, CNodeType.DECLARATION, CNodeType.EXPRESSION)));
            m.put(
                    CNodeField.UPDATE,
                    new FieldInfo(false, false, Set.of(CNodeType.COMMA_EXPRESSION, CNodeType.EXPRESSION)));
            out.put(CNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CNodeType.DECLARATOR, CNodeType.FIELD_DECLARATOR, CNodeType.TYPE_DECLARATOR)));
            m.put(CNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CNodeType.PARAMETER_LIST)));
            out.put(CNodeType.FUNCTION_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.COMPOUND_STATEMENT)));
            m.put(CNodeField.DECLARATOR, new FieldInfo(true, false, Set.of(CNodeType.DECLARATOR)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_SPECIFIER)));
            out.put(CNodeType.FUNCTION_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.REGISTER,
                    new FieldInfo(false, true, Set.of(CNodeType.CONCATENATED_STRING, CNodeType.STRING_LITERAL)));
            out.put(CNodeType.GNU_ASM_CLOBBER_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.ASSEMBLY_CODE,
                    new FieldInfo(true, false, Set.of(CNodeType.CONCATENATED_STRING, CNodeType.STRING_LITERAL)));
            m.put(CNodeField.CLOBBERS, new FieldInfo(false, false, Set.of(CNodeType.GNU_ASM_CLOBBER_LIST)));
            m.put(CNodeField.GOTO_LABELS, new FieldInfo(false, false, Set.of(CNodeType.GNU_ASM_GOTO_LIST)));
            m.put(CNodeField.INPUT_OPERANDS, new FieldInfo(false, false, Set.of(CNodeType.GNU_ASM_INPUT_OPERAND_LIST)));
            m.put(
                    CNodeField.OUTPUT_OPERANDS,
                    new FieldInfo(false, false, Set.of(CNodeType.GNU_ASM_OUTPUT_OPERAND_LIST)));
            out.put(CNodeType.GNU_ASM_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.LABEL, new FieldInfo(false, true, Set.of(CNodeType.IDENTIFIER)));
            out.put(CNodeType.GNU_ASM_GOTO_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.CONSTRAINT, new FieldInfo(true, false, Set.of(CNodeType.STRING_LITERAL)));
            m.put(CNodeField.SYMBOL, new FieldInfo(false, false, Set.of(CNodeType.IDENTIFIER)));
            m.put(CNodeField.VALUE, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.GNU_ASM_INPUT_OPERAND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.OPERAND, new FieldInfo(false, true, Set.of(CNodeType.GNU_ASM_INPUT_OPERAND)));
            out.put(CNodeType.GNU_ASM_INPUT_OPERAND_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.CONSTRAINT, new FieldInfo(true, false, Set.of(CNodeType.STRING_LITERAL)));
            m.put(CNodeField.SYMBOL, new FieldInfo(false, false, Set.of(CNodeType.IDENTIFIER)));
            m.put(CNodeField.VALUE, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.GNU_ASM_OUTPUT_OPERAND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.OPERAND, new FieldInfo(false, true, Set.of(CNodeType.GNU_ASM_OUTPUT_OPERAND)));
            out.put(CNodeType.GNU_ASM_OUTPUT_OPERAND_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.LABEL, new FieldInfo(true, false, Set.of(CNodeType.STATEMENT_IDENTIFIER)));
            out.put(CNodeType.GOTO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(CNodeType.ELSE_CLAUSE)));
            m.put(CNodeField.CONDITION, new FieldInfo(true, false, Set.of(CNodeType.PARENTHESIZED_EXPRESSION)));
            m.put(CNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(CNodeType.STATEMENT)));
            out.put(CNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.DESIGNATOR,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CNodeType.FIELD_DESIGNATOR,
                                    CNodeType.FIELD_IDENTIFIER,
                                    CNodeType.SUBSCRIPT_DESIGNATOR,
                                    CNodeType.SUBSCRIPT_RANGE_DESIGNATOR)));
            m.put(
                    CNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION, CNodeType.INITIALIZER_LIST)));
            out.put(CNodeType.INITIALIZER_PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.DECLARATOR, new FieldInfo(true, false, Set.of(CNodeType.DECLARATOR)));
            m.put(
                    CNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION, CNodeType.INITIALIZER_LIST)));
            out.put(CNodeType.INIT_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.LABEL, new FieldInfo(true, false, Set.of(CNodeType.STATEMENT_IDENTIFIER)));
            out.put(CNodeType.LABELED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.BODY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CNodeType.DECLARATION, CNodeType.DECLARATION_LIST, CNodeType.FUNCTION_DEFINITION)));
            m.put(CNodeField.VALUE, new FieldInfo(true, false, Set.of(CNodeType.STRING_LITERAL)));
            out.put(CNodeType.LINKAGE_SPECIFICATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.NAME, new FieldInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_DESCRIPTOR)));
            out.put(CNodeType.MACRO_TYPE_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.MEMBER, new FieldInfo(true, false, Set.of(CNodeType.FIELD_IDENTIFIER)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_DESCRIPTOR)));
            out.put(CNodeType.OFFSETOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.DECLARATOR,
                    new FieldInfo(false, false, Set.of(CNodeType.ABSTRACT_DECLARATOR, CNodeType.DECLARATOR)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_SPECIFIER)));
            out.put(CNodeType.PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CNodeType.DECLARATOR, CNodeType.FIELD_DECLARATOR, CNodeType.TYPE_DECLARATOR)));
            out.put(CNodeType.POINTER_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(CNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CNodeType.POINTER_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ARGUMENT, new FieldInfo(false, false, Set.of(CNodeType.PREPROC_ARG)));
            m.put(CNodeField.DIRECTIVE, new FieldInfo(true, false, Set.of(CNodeType.PREPROC_DIRECTIVE)));
            out.put(CNodeType.PREPROC_CALL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.NAME, new FieldInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
            m.put(CNodeField.VALUE, new FieldInfo(false, false, Set.of(CNodeType.PREPROC_ARG)));
            out.put(CNodeType.PREPROC_DEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CNodeType.PREPROC_ELIF, CNodeType.PREPROC_ELIFDEF, CNodeType.PREPROC_ELSE)));
            m.put(
                    CNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CNodeType.BINARY_EXPRESSION,
                                    CNodeType.CALL_EXPRESSION,
                                    CNodeType.CHAR_LITERAL,
                                    CNodeType.IDENTIFIER,
                                    CNodeType.NUMBER_LITERAL,
                                    CNodeType.PARENTHESIZED_EXPRESSION,
                                    CNodeType.PREPROC_DEFINED,
                                    CNodeType.UNARY_EXPRESSION)));
            out.put(CNodeType.PREPROC_ELIF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CNodeType.PREPROC_ELIF, CNodeType.PREPROC_ELIFDEF, CNodeType.PREPROC_ELSE)));
            m.put(CNodeField.NAME, new FieldInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
            out.put(CNodeType.PREPROC_ELIFDEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.NAME, new FieldInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
            m.put(CNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CNodeType.PREPROC_PARAMS)));
            m.put(CNodeField.VALUE, new FieldInfo(false, false, Set.of(CNodeType.PREPROC_ARG)));
            out.put(CNodeType.PREPROC_FUNCTION_DEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CNodeType.PREPROC_ELIF, CNodeType.PREPROC_ELIFDEF, CNodeType.PREPROC_ELSE)));
            m.put(
                    CNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CNodeType.BINARY_EXPRESSION,
                                    CNodeType.CALL_EXPRESSION,
                                    CNodeType.CHAR_LITERAL,
                                    CNodeType.IDENTIFIER,
                                    CNodeType.NUMBER_LITERAL,
                                    CNodeType.PARENTHESIZED_EXPRESSION,
                                    CNodeType.PREPROC_DEFINED,
                                    CNodeType.UNARY_EXPRESSION)));
            out.put(CNodeType.PREPROC_IF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CNodeType.PREPROC_ELIF, CNodeType.PREPROC_ELIFDEF, CNodeType.PREPROC_ELSE)));
            m.put(CNodeField.NAME, new FieldInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
            out.put(CNodeType.PREPROC_IFDEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.PATH,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CNodeType.CALL_EXPRESSION,
                                    CNodeType.IDENTIFIER,
                                    CNodeType.STRING_LITERAL,
                                    CNodeType.SYSTEM_LIB_STRING)));
            out.put(CNodeType.PREPROC_INCLUDE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.COMPOUND_STATEMENT)));
            m.put(CNodeField.FILTER, new FieldInfo(true, false, Set.of(CNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(CNodeType.SEH_EXCEPT_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.COMPOUND_STATEMENT)));
            out.put(CNodeType.SEH_FINALLY_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.COMPOUND_STATEMENT)));
            out.put(CNodeType.SEH_TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.TYPE,
                    new FieldInfo(false, false, Set.of(CNodeType.PRIMITIVE_TYPE, CNodeType.TYPE_IDENTIFIER)));
            out.put(CNodeType.SIZED_TYPE_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.TYPE, new FieldInfo(false, false, Set.of(CNodeType.TYPE_DESCRIPTOR)));
            m.put(CNodeField.VALUE, new FieldInfo(false, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.SIZEOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(false, false, Set.of(CNodeType.FIELD_DECLARATION_LIST)));
            m.put(CNodeField.NAME, new FieldInfo(false, false, Set.of(CNodeType.TYPE_IDENTIFIER)));
            out.put(CNodeType.STRUCT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(CNodeField.INDEX, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.SUBSCRIPT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.END, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(CNodeField.START, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            out.put(CNodeType.SUBSCRIPT_RANGE_DESIGNATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.COMPOUND_STATEMENT)));
            m.put(CNodeField.CONDITION, new FieldInfo(true, false, Set.of(CNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(CNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.DECLARATOR, new FieldInfo(true, true, Set.of(CNodeType.TYPE_DECLARATOR)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_SPECIFIER)));
            out.put(CNodeType.TYPE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CNodeType.ABSTRACT_DECLARATOR)));
            m.put(CNodeField.TYPE, new FieldInfo(true, false, Set.of(CNodeType.TYPE_SPECIFIER)));
            out.put(CNodeType.TYPE_DESCRIPTOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(
                    CNodeField.ARGUMENT,
                    new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION, CNodeType.PREPROC_DEFINED)));
            m.put(CNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(false, false, Set.of(CNodeType.FIELD_DECLARATION_LIST)));
            m.put(CNodeField.NAME, new FieldInfo(false, false, Set.of(CNodeType.TYPE_IDENTIFIER)));
            out.put(CNodeType.UNION_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CNodeType.EXPRESSION)));
            m.put(CNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CNodeType.UPDATE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CNodeField, FieldInfo> m = new EnumMap<>(CNodeField.class);
            m.put(CNodeField.BODY, new FieldInfo(true, false, Set.of(CNodeType.STATEMENT)));
            m.put(CNodeField.CONDITION, new FieldInfo(true, false, Set.of(CNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(CNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<CNodeType, ChildInfo> initChildren() {
        EnumMap<CNodeType, ChildInfo> out = new EnumMap<>(CNodeType.class);
        out.put(CNodeType.ABSTRACT_ARRAY_DECLARATOR, new ChildInfo(false, true, Set.of(CNodeType.TYPE_QUALIFIER)));
        out.put(
                CNodeType.ABSTRACT_PARENTHESIZED_DECLARATOR,
                new ChildInfo(true, true, Set.of(CNodeType.ABSTRACT_DECLARATOR, CNodeType.MS_CALL_MODIFIER)));
        out.put(
                CNodeType.ABSTRACT_POINTER_DECLARATOR,
                new ChildInfo(false, true, Set.of(CNodeType.MS_POINTER_MODIFIER, CNodeType.TYPE_QUALIFIER)));
        out.put(
                CNodeType.ALIGNAS_QUALIFIER,
                new ChildInfo(true, false, Set.of(CNodeType.EXPRESSION, CNodeType.TYPE_DESCRIPTOR)));
        out.put(
                CNodeType.ARGUMENT_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CNodeType.COMPOUND_STATEMENT, CNodeType.EXPRESSION, CNodeType.PREPROC_DEFINED)));
        out.put(CNodeType.ARRAY_DECLARATOR, new ChildInfo(false, true, Set.of(CNodeType.TYPE_QUALIFIER)));
        out.put(CNodeType.ATTRIBUTE, new ChildInfo(false, false, Set.of(CNodeType.ARGUMENT_LIST)));
        out.put(
                CNodeType.ATTRIBUTED_DECLARATOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTE_DECLARATION,
                                CNodeType.DECLARATOR,
                                CNodeType.FIELD_DECLARATOR,
                                CNodeType.TYPE_DECLARATOR)));
        out.put(
                CNodeType.ATTRIBUTED_STATEMENT,
                new ChildInfo(true, true, Set.of(CNodeType.ATTRIBUTE_DECLARATION, CNodeType.STATEMENT)));
        out.put(CNodeType.ATTRIBUTE_DECLARATION, new ChildInfo(true, true, Set.of(CNodeType.ATTRIBUTE)));
        out.put(CNodeType.ATTRIBUTE_SPECIFIER, new ChildInfo(true, false, Set.of(CNodeType.ARGUMENT_LIST)));
        out.put(CNodeType.BITFIELD_CLAUSE, new ChildInfo(true, false, Set.of(CNodeType.EXPRESSION)));
        out.put(
                CNodeType.CASE_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTED_STATEMENT,
                                CNodeType.BREAK_STATEMENT,
                                CNodeType.COMPOUND_STATEMENT,
                                CNodeType.CONTINUE_STATEMENT,
                                CNodeType.DECLARATION,
                                CNodeType.DO_STATEMENT,
                                CNodeType.EXPRESSION_STATEMENT,
                                CNodeType.FOR_STATEMENT,
                                CNodeType.GOTO_STATEMENT,
                                CNodeType.IF_STATEMENT,
                                CNodeType.LABELED_STATEMENT,
                                CNodeType.RETURN_STATEMENT,
                                CNodeType.SEH_LEAVE_STATEMENT,
                                CNodeType.SEH_TRY_STATEMENT,
                                CNodeType.SWITCH_STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.WHILE_STATEMENT)));
        out.put(
                CNodeType.CHAR_LITERAL,
                new ChildInfo(true, true, Set.of(CNodeType.CHARACTER, CNodeType.ESCAPE_SEQUENCE)));
        out.put(
                CNodeType.COMPOUND_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.DECLARATION,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER)));
        out.put(
                CNodeType.CONCATENATED_STRING,
                new ChildInfo(true, true, Set.of(CNodeType.IDENTIFIER, CNodeType.STRING_LITERAL)));
        out.put(
                CNodeType.DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTE_DECLARATION,
                                CNodeType.ATTRIBUTE_SPECIFIER,
                                CNodeType.MS_DECLSPEC_MODIFIER,
                                CNodeType.STORAGE_CLASS_SPECIFIER,
                                CNodeType.TYPE_QUALIFIER)));
        out.put(
                CNodeType.DECLARATION_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.DECLARATION,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER)));
        out.put(CNodeType.ELSE_CLAUSE, new ChildInfo(true, false, Set.of(CNodeType.STATEMENT)));
        out.put(
                CNodeType.ENUMERATOR_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ENUMERATOR,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF)));
        out.put(CNodeType.ENUM_SPECIFIER, new ChildInfo(false, false, Set.of(CNodeType.ATTRIBUTE_SPECIFIER)));
        out.put(
                CNodeType.EXPRESSION_STATEMENT,
                new ChildInfo(false, false, Set.of(CNodeType.COMMA_EXPRESSION, CNodeType.EXPRESSION)));
        out.put(CNodeType.EXTENSION_EXPRESSION, new ChildInfo(true, false, Set.of(CNodeType.EXPRESSION)));
        out.put(
                CNodeType.FIELD_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTE_DECLARATION,
                                CNodeType.ATTRIBUTE_SPECIFIER,
                                CNodeType.BITFIELD_CLAUSE,
                                CNodeType.MS_DECLSPEC_MODIFIER,
                                CNodeType.STORAGE_CLASS_SPECIFIER,
                                CNodeType.TYPE_QUALIFIER)));
        out.put(
                CNodeType.FIELD_DECLARATION_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.FIELD_DECLARATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF)));
        out.put(CNodeType.FIELD_DESIGNATOR, new ChildInfo(true, false, Set.of(CNodeType.FIELD_IDENTIFIER)));
        out.put(
                CNodeType.FUNCTION_DECLARATOR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTE_SPECIFIER,
                                CNodeType.CALL_EXPRESSION,
                                CNodeType.GNU_ASM_EXPRESSION,
                                CNodeType.IDENTIFIER)));
        out.put(
                CNodeType.FUNCTION_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTE_DECLARATION,
                                CNodeType.ATTRIBUTE_SPECIFIER,
                                CNodeType.DECLARATION,
                                CNodeType.MS_CALL_MODIFIER,
                                CNodeType.MS_DECLSPEC_MODIFIER,
                                CNodeType.STORAGE_CLASS_SPECIFIER,
                                CNodeType.TYPE_QUALIFIER)));
        out.put(
                CNodeType.GENERIC_EXPRESSION,
                new ChildInfo(true, true, Set.of(CNodeType.EXPRESSION, CNodeType.TYPE_DESCRIPTOR)));
        out.put(CNodeType.GNU_ASM_EXPRESSION, new ChildInfo(false, true, Set.of(CNodeType.GNU_ASM_QUALIFIER)));
        out.put(
                CNodeType.INITIALIZER_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CNodeType.EXPRESSION, CNodeType.INITIALIZER_LIST, CNodeType.INITIALIZER_PAIR)));
        out.put(
                CNodeType.LABELED_STATEMENT,
                new ChildInfo(true, false, Set.of(CNodeType.DECLARATION, CNodeType.STATEMENT)));
        out.put(CNodeType.MS_BASED_MODIFIER, new ChildInfo(true, false, Set.of(CNodeType.ARGUMENT_LIST)));
        out.put(CNodeType.MS_DECLSPEC_MODIFIER, new ChildInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
        out.put(
                CNodeType.MS_POINTER_MODIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CNodeType.MS_RESTRICT_MODIFIER,
                                CNodeType.MS_SIGNED_PTR_MODIFIER,
                                CNodeType.MS_UNALIGNED_PTR_MODIFIER,
                                CNodeType.MS_UNSIGNED_PTR_MODIFIER)));
        out.put(
                CNodeType.PARAMETER_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTE_DECLARATION,
                                CNodeType.ATTRIBUTE_SPECIFIER,
                                CNodeType.MS_DECLSPEC_MODIFIER,
                                CNodeType.STORAGE_CLASS_SPECIFIER,
                                CNodeType.TYPE_QUALIFIER)));
        out.put(
                CNodeType.PARAMETER_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.COMPOUND_STATEMENT,
                                CNodeType.IDENTIFIER,
                                CNodeType.PARAMETER_DECLARATION,
                                CNodeType.VARIADIC_PARAMETER)));
        out.put(
                CNodeType.PARENTHESIZED_DECLARATOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CNodeType.DECLARATOR,
                                CNodeType.FIELD_DECLARATOR,
                                CNodeType.MS_CALL_MODIFIER,
                                CNodeType.TYPE_DECLARATOR)));
        out.put(
                CNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CNodeType.COMMA_EXPRESSION,
                                CNodeType.COMPOUND_STATEMENT,
                                CNodeType.EXPRESSION,
                                CNodeType.PREPROC_DEFINED)));
        out.put(
                CNodeType.POINTER_DECLARATOR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CNodeType.MS_BASED_MODIFIER, CNodeType.MS_POINTER_MODIFIER, CNodeType.TYPE_QUALIFIER)));
        out.put(CNodeType.PREPROC_DEFINED, new ChildInfo(true, false, Set.of(CNodeType.IDENTIFIER)));
        out.put(
                CNodeType.PREPROC_ELIF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.DECLARATION,
                                CNodeType.ENUMERATOR,
                                CNodeType.FIELD_DECLARATION,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER)));
        out.put(
                CNodeType.PREPROC_ELIFDEF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.DECLARATION,
                                CNodeType.ENUMERATOR,
                                CNodeType.FIELD_DECLARATION,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER)));
        out.put(
                CNodeType.PREPROC_ELSE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.DECLARATION,
                                CNodeType.ENUMERATOR,
                                CNodeType.FIELD_DECLARATION,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER)));
        out.put(
                CNodeType.PREPROC_IF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.DECLARATION,
                                CNodeType.ENUMERATOR,
                                CNodeType.FIELD_DECLARATION,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER)));
        out.put(
                CNodeType.PREPROC_IFDEF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.DECLARATION,
                                CNodeType.ENUMERATOR,
                                CNodeType.FIELD_DECLARATION,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER)));
        out.put(CNodeType.PREPROC_PARAMS, new ChildInfo(false, true, Set.of(CNodeType.IDENTIFIER)));
        out.put(
                CNodeType.RETURN_STATEMENT,
                new ChildInfo(false, false, Set.of(CNodeType.COMMA_EXPRESSION, CNodeType.EXPRESSION)));
        out.put(
                CNodeType.SEH_TRY_STATEMENT,
                new ChildInfo(true, false, Set.of(CNodeType.SEH_EXCEPT_CLAUSE, CNodeType.SEH_FINALLY_CLAUSE)));
        out.put(CNodeType.SIZED_TYPE_SPECIFIER, new ChildInfo(false, true, Set.of(CNodeType.TYPE_QUALIFIER)));
        out.put(
                CNodeType.STRING_LITERAL,
                new ChildInfo(false, true, Set.of(CNodeType.ESCAPE_SEQUENCE, CNodeType.STRING_CONTENT)));
        out.put(
                CNodeType.STRUCT_SPECIFIER,
                new ChildInfo(false, true, Set.of(CNodeType.ATTRIBUTE_SPECIFIER, CNodeType.MS_DECLSPEC_MODIFIER)));
        out.put(CNodeType.SUBSCRIPT_DESIGNATOR, new ChildInfo(true, false, Set.of(CNodeType.EXPRESSION)));
        out.put(
                CNodeType.TRANSLATION_UNIT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CNodeType.ATTRIBUTED_STATEMENT,
                                CNodeType.BREAK_STATEMENT,
                                CNodeType.CASE_STATEMENT,
                                CNodeType.COMPOUND_STATEMENT,
                                CNodeType.CONTINUE_STATEMENT,
                                CNodeType.DECLARATION,
                                CNodeType.DO_STATEMENT,
                                CNodeType.EXPRESSION_STATEMENT,
                                CNodeType.FOR_STATEMENT,
                                CNodeType.FUNCTION_DEFINITION,
                                CNodeType.GOTO_STATEMENT,
                                CNodeType.IF_STATEMENT,
                                CNodeType.LABELED_STATEMENT,
                                CNodeType.LINKAGE_SPECIFICATION,
                                CNodeType.PREPROC_CALL,
                                CNodeType.PREPROC_DEF,
                                CNodeType.PREPROC_FUNCTION_DEF,
                                CNodeType.PREPROC_IF,
                                CNodeType.PREPROC_IFDEF,
                                CNodeType.PREPROC_INCLUDE,
                                CNodeType.RETURN_STATEMENT,
                                CNodeType.SWITCH_STATEMENT,
                                CNodeType.TYPE_DEFINITION,
                                CNodeType.TYPE_SPECIFIER,
                                CNodeType.WHILE_STATEMENT)));
        out.put(
                CNodeType.TYPE_DEFINITION,
                new ChildInfo(false, true, Set.of(CNodeType.ATTRIBUTE_SPECIFIER, CNodeType.TYPE_QUALIFIER)));
        out.put(CNodeType.TYPE_DESCRIPTOR, new ChildInfo(false, true, Set.of(CNodeType.TYPE_QUALIFIER)));
        out.put(CNodeType.TYPE_QUALIFIER, new ChildInfo(false, false, Set.of(CNodeType.ALIGNAS_QUALIFIER)));
        out.put(
                CNodeType.UNION_SPECIFIER,
                new ChildInfo(false, true, Set.of(CNodeType.ATTRIBUTE_SPECIFIER, CNodeType.MS_DECLSPEC_MODIFIER)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<CNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<CNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<CNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<CNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
