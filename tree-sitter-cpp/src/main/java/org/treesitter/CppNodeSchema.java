package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code cpp} from tree-sitter {@code node-types.json}.
 */
public final class CppNodeSchema {
    private CppNodeSchema() {}

    public static Set<CppNodeField> fields(@Nullable CppNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<CppNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<CppNodeType> allowedTypes(@Nullable CppNodeType owner, @Nullable CppNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<CppNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable CppNodeType owner, @Nullable CppNodeField field) {
        if (owner == null || field == null) return false;
        Map<CppNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable CppNodeType owner, @Nullable CppNodeField field) {
        if (owner == null || field == null) return false;
        Map<CppNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<CppNodeType> allowedChildTypes(@Nullable CppNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable CppNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable CppNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<CppNodeType, Map<CppNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<CppNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<CppNodeType, Map<CppNodeField, FieldInfo>> initFields() {
        EnumMap<CppNodeType, Map<CppNodeField, FieldInfo>> out = new EnumMap<>(CppNodeType.class);
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CppNodeType.ABSTRACT_DECLARATOR)));
            m.put(CppNodeField.SIZE, new FieldInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.ABSTRACT_ARRAY_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CppNodeType.ABSTRACT_DECLARATOR)));
            m.put(CppNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CppNodeType.PARAMETER_LIST)));
            out.put(CppNodeType.ABSTRACT_FUNCTION_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CppNodeType.ABSTRACT_DECLARATOR)));
            out.put(CppNodeType.ABSTRACT_POINTER_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_IDENTIFIER)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_DESCRIPTOR)));
            out.put(CppNodeType.ALIAS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_DESCRIPTOR)));
            out.put(CppNodeType.ALIGNOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CppNodeType.DECLARATOR, CppNodeType.FIELD_DECLARATOR, CppNodeType.TYPE_DECLARATOR)));
            m.put(CppNodeField.SIZE, new FieldInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.ARRAY_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LEFT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    CppNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
            out.put(CppNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            m.put(CppNodeField.PREFIX, new FieldInfo(false, false, Set.of(CppNodeType.IDENTIFIER)));
            out.put(CppNodeType.ATTRIBUTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.LEFT,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.PREPROC_DEFINED)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    CppNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.PREPROC_DEFINED)));
            out.put(CppNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(CppNodeType.ARGUMENT_LIST)));
            m.put(
                    CppNodeField.FUNCTION,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.PRIMITIVE_TYPE)));
            out.put(CppNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.VALUE, new FieldInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.CASE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_DESCRIPTOR)));
            m.put(CppNodeField.VALUE, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.CAST_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.COMPOUND_STATEMENT)));
            m.put(CppNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CppNodeType.PARAMETER_LIST)));
            out.put(CppNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(false, false, Set.of(CppNodeType.FIELD_DECLARATION_LIST)));
            m.put(
                    CppNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.CLASS_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LEFT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(
                    CppNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.EXPRESSION)));
            out.put(CppNodeType.COMMA_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CppNodeType.PRIMITIVE_TYPE,
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_DESCRIPTOR,
                                    CppNodeType.TYPE_IDENTIFIER)));
            m.put(CppNodeField.VALUE, new FieldInfo(true, false, Set.of(CppNodeType.INITIALIZER_LIST)));
            out.put(CppNodeType.COMPOUND_LITERAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            out.put(CppNodeType.CONCEPT_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.CONDITION, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(
                    CppNodeField.CONSEQUENCE,
                    new FieldInfo(false, false, Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.EXPRESSION)));
            out.put(CppNodeType.CONDITIONAL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.INITIALIZER, new FieldInfo(false, false, Set.of(CppNodeType.INIT_STATEMENT)));
            m.put(
                    CppNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.DECLARATION, CppNodeType.EXPRESSION)));
            out.put(CppNodeType.CONDITION_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.LEFT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.CONSTRAINT_CONJUNCTION,
                                    CppNodeType.CONSTRAINT_DISJUNCTION,
                                    CppNodeType.EXPRESSION,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    CppNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.CONSTRAINT_CONJUNCTION,
                                    CppNodeType.CONSTRAINT_DISJUNCTION,
                                    CppNodeType.EXPRESSION,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.CONSTRAINT_CONJUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.LEFT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.CONSTRAINT_CONJUNCTION,
                                    CppNodeType.CONSTRAINT_DISJUNCTION,
                                    CppNodeType.EXPRESSION,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    CppNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.CONSTRAINT_CONJUNCTION,
                                    CppNodeType.CONSTRAINT_DISJUNCTION,
                                    CppNodeType.EXPRESSION,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.CONSTRAINT_DISJUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CppNodeType.CO_AWAIT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.DECLARATOR,
                                    CppNodeType.GNU_ASM_EXPRESSION,
                                    CppNodeType.INIT_DECLARATOR,
                                    CppNodeType.OPERATOR_CAST)));
            m.put(CppNodeField.DEFAULT_VALUE, new FieldInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.TYPE, new FieldInfo(false, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            m.put(
                    CppNodeField.VALUE,
                    new FieldInfo(false, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
            out.put(CppNodeType.DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.STATEMENT)));
            m.put(CppNodeField.CONDITION, new FieldInfo(true, false, Set.of(CppNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(CppNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            m.put(CppNodeField.VALUE, new FieldInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.ENUMERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.BASE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    CppNodeType.PRIMITIVE_TYPE,
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.SIZED_TYPE_SPECIFIER,
                                    CppNodeType.TYPE_IDENTIFIER)));
            m.put(CppNodeField.BODY, new FieldInfo(false, false, Set.of(CppNodeType.ENUMERATOR_LIST)));
            m.put(
                    CppNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.ENUM_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(false, true, Set.of(CppNodeType.FIELD_DECLARATOR)));
            m.put(
                    CppNodeField.DEFAULT_VALUE,
                    new FieldInfo(false, true, Set.of(CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.FIELD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(
                    CppNodeField.FIELD,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CppNodeType.DEPENDENT_NAME,
                                    CppNodeType.DESTRUCTOR_NAME,
                                    CppNodeType.FIELD_IDENTIFIER,
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.TEMPLATE_METHOD)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CppNodeType.FIELD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LEFT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(CppNodeField.RIGHT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.FOLD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.STATEMENT)));
            m.put(CppNodeField.DECLARATOR, new FieldInfo(true, false, Set.of(CppNodeType.DECLARATOR)));
            m.put(CppNodeField.INITIALIZER, new FieldInfo(false, false, Set.of(CppNodeType.INIT_STATEMENT)));
            m.put(
                    CppNodeField.RIGHT,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.FOR_RANGE_LOOP, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.STATEMENT)));
            m.put(
                    CppNodeField.CONDITION,
                    new FieldInfo(false, false, Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.EXPRESSION)));
            m.put(
                    CppNodeField.INITIALIZER,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.DECLARATION, CppNodeType.EXPRESSION)));
            m.put(
                    CppNodeField.UPDATE,
                    new FieldInfo(false, false, Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.EXPRESSION)));
            out.put(CppNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CppNodeType.DECLARATOR, CppNodeType.FIELD_DECLARATOR, CppNodeType.TYPE_DECLARATOR)));
            m.put(CppNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CppNodeType.PARAMETER_LIST)));
            out.put(CppNodeType.FUNCTION_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.BODY,
                    new FieldInfo(false, false, Set.of(CppNodeType.COMPOUND_STATEMENT, CppNodeType.TRY_STATEMENT)));
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CppNodeType.DECLARATOR, CppNodeType.FIELD_DECLARATOR, CppNodeType.OPERATOR_CAST)));
            m.put(CppNodeField.TYPE, new FieldInfo(false, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.FUNCTION_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.REGISTER,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    CppNodeType.CONCATENATED_STRING,
                                    CppNodeType.RAW_STRING_LITERAL,
                                    CppNodeType.STRING_LITERAL)));
            out.put(CppNodeType.GNU_ASM_CLOBBER_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.ASSEMBLY_CODE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CppNodeType.CONCATENATED_STRING,
                                    CppNodeType.RAW_STRING_LITERAL,
                                    CppNodeType.STRING_LITERAL)));
            m.put(CppNodeField.CLOBBERS, new FieldInfo(false, false, Set.of(CppNodeType.GNU_ASM_CLOBBER_LIST)));
            m.put(CppNodeField.GOTO_LABELS, new FieldInfo(false, false, Set.of(CppNodeType.GNU_ASM_GOTO_LIST)));
            m.put(
                    CppNodeField.INPUT_OPERANDS,
                    new FieldInfo(false, false, Set.of(CppNodeType.GNU_ASM_INPUT_OPERAND_LIST)));
            m.put(
                    CppNodeField.OUTPUT_OPERANDS,
                    new FieldInfo(false, false, Set.of(CppNodeType.GNU_ASM_OUTPUT_OPERAND_LIST)));
            out.put(CppNodeType.GNU_ASM_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LABEL, new FieldInfo(false, true, Set.of(CppNodeType.IDENTIFIER)));
            out.put(CppNodeType.GNU_ASM_GOTO_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.CONSTRAINT, new FieldInfo(true, false, Set.of(CppNodeType.STRING_LITERAL)));
            m.put(CppNodeField.SYMBOL, new FieldInfo(false, false, Set.of(CppNodeType.IDENTIFIER)));
            m.put(CppNodeField.VALUE, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.GNU_ASM_INPUT_OPERAND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.OPERAND, new FieldInfo(false, true, Set.of(CppNodeType.GNU_ASM_INPUT_OPERAND)));
            out.put(CppNodeType.GNU_ASM_INPUT_OPERAND_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.CONSTRAINT, new FieldInfo(true, false, Set.of(CppNodeType.STRING_LITERAL)));
            m.put(CppNodeField.SYMBOL, new FieldInfo(false, false, Set.of(CppNodeType.IDENTIFIER)));
            m.put(CppNodeField.VALUE, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.GNU_ASM_OUTPUT_OPERAND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.OPERAND, new FieldInfo(false, true, Set.of(CppNodeType.GNU_ASM_OUTPUT_OPERAND)));
            out.put(CppNodeType.GNU_ASM_OUTPUT_OPERAND_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LABEL, new FieldInfo(true, false, Set.of(CppNodeType.STATEMENT_IDENTIFIER)));
            out.put(CppNodeType.GOTO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(CppNodeType.ELSE_CLAUSE)));
            m.put(CppNodeField.CONDITION, new FieldInfo(true, false, Set.of(CppNodeType.CONDITION_CLAUSE)));
            m.put(CppNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(CppNodeType.STATEMENT)));
            out.put(CppNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DESIGNATOR,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.FIELD_DESIGNATOR,
                                    CppNodeType.FIELD_IDENTIFIER,
                                    CppNodeType.SUBSCRIPT_DESIGNATOR,
                                    CppNodeType.SUBSCRIPT_RANGE_DESIGNATOR)));
            m.put(
                    CppNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
            out.put(CppNodeType.INITIALIZER_PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(true, false, Set.of(CppNodeType.DECLARATOR)));
            m.put(
                    CppNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CppNodeType.ARGUMENT_LIST, CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
            out.put(CppNodeType.INIT_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LABEL, new FieldInfo(true, false, Set.of(CppNodeType.STATEMENT_IDENTIFIER)));
            out.put(CppNodeType.LABELED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LEFT, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            m.put(CppNodeField.RIGHT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.LAMBDA_CAPTURE_INITIALIZER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.COMPOUND_STATEMENT)));
            m.put(CppNodeField.CAPTURES, new FieldInfo(true, false, Set.of(CppNodeType.LAMBDA_CAPTURE_SPECIFIER)));
            m.put(CppNodeField.CONSTRAINT, new FieldInfo(false, false, Set.of(CppNodeType.REQUIRES_CLAUSE)));
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(false, false, Set.of(CppNodeType.ABSTRACT_FUNCTION_DECLARATOR)));
            m.put(
                    CppNodeField.TEMPLATE_PARAMETERS,
                    new FieldInfo(false, false, Set.of(CppNodeType.TEMPLATE_PARAMETER_LIST)));
            out.put(CppNodeType.LAMBDA_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.BODY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CppNodeType.DECLARATION,
                                    CppNodeType.DECLARATION_LIST,
                                    CppNodeType.FUNCTION_DEFINITION)));
            m.put(CppNodeField.VALUE, new FieldInfo(true, false, Set.of(CppNodeType.STRING_LITERAL)));
            out.put(CppNodeType.LINKAGE_SPECIFICATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.NAMESPACE_IDENTIFIER)));
            out.put(CppNodeType.NAMESPACE_ALIAS_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.DECLARATION_LIST)));
            m.put(
                    CppNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CppNodeType.NAMESPACE_IDENTIFIER, CppNodeType.NESTED_NAMESPACE_SPECIFIER)));
            out.put(CppNodeType.NAMESPACE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.LENGTH, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.NEW_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.ARGUMENTS,
                    new FieldInfo(false, false, Set.of(CppNodeType.ARGUMENT_LIST, CppNodeType.INITIALIZER_LIST)));
            m.put(CppNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CppNodeType.NEW_DECLARATOR)));
            m.put(CppNodeField.PLACEMENT, new FieldInfo(false, false, Set.of(CppNodeType.ARGUMENT_LIST)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.NEW_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.MEMBER, new FieldInfo(true, false, Set.of(CppNodeType.FIELD_IDENTIFIER)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_DESCRIPTOR)));
            out.put(CppNodeType.OFFSETOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(true, false, Set.of(CppNodeType.ABSTRACT_DECLARATOR)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.OPERATOR_CAST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(
                            false, false, Set.of(CppNodeType.ABSTRACT_REFERENCE_DECLARATOR, CppNodeType.DECLARATOR)));
            m.put(CppNodeField.DEFAULT_VALUE, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.OPTIONAL_PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DEFAULT_TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            m.put(CppNodeField.NAME, new FieldInfo(false, false, Set.of(CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.OPTIONAL_TYPE_PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(false, false, Set.of(CppNodeType.ABSTRACT_DECLARATOR, CppNodeType.DECLARATOR)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.TYPE_DESCRIPTOR)));
            out.put(CppNodeType.PARAMETER_PACK_EXPANSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.CONSTRAINT, new FieldInfo(false, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.PLACEHOLDER_TYPE_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(CppNodeType.DECLARATOR, CppNodeType.FIELD_DECLARATOR, CppNodeType.TYPE_DECLARATOR)));
            out.put(CppNodeType.POINTER_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CppNodeType.POINTER_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_DECLARATOR)));
            out.put(CppNodeType.POINTER_TYPE_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENT, new FieldInfo(false, false, Set.of(CppNodeType.PREPROC_ARG)));
            m.put(CppNodeField.DIRECTIVE, new FieldInfo(true, false, Set.of(CppNodeType.PREPROC_DIRECTIVE)));
            out.put(CppNodeType.PREPROC_CALL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            m.put(CppNodeField.VALUE, new FieldInfo(false, false, Set.of(CppNodeType.PREPROC_ARG)));
            out.put(CppNodeType.PREPROC_DEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CppNodeType.PREPROC_ELIF, CppNodeType.PREPROC_ELIFDEF, CppNodeType.PREPROC_ELSE)));
            m.put(
                    CppNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CppNodeType.BINARY_EXPRESSION,
                                    CppNodeType.CALL_EXPRESSION,
                                    CppNodeType.CHAR_LITERAL,
                                    CppNodeType.IDENTIFIER,
                                    CppNodeType.NUMBER_LITERAL,
                                    CppNodeType.PARENTHESIZED_EXPRESSION,
                                    CppNodeType.PREPROC_DEFINED,
                                    CppNodeType.UNARY_EXPRESSION)));
            out.put(CppNodeType.PREPROC_ELIF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CppNodeType.PREPROC_ELIF, CppNodeType.PREPROC_ELIFDEF, CppNodeType.PREPROC_ELSE)));
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            out.put(CppNodeType.PREPROC_ELIFDEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            m.put(CppNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CppNodeType.PREPROC_PARAMS)));
            m.put(CppNodeField.VALUE, new FieldInfo(false, false, Set.of(CppNodeType.PREPROC_ARG)));
            out.put(CppNodeType.PREPROC_FUNCTION_DEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CppNodeType.PREPROC_ELIF, CppNodeType.PREPROC_ELIFDEF, CppNodeType.PREPROC_ELSE)));
            m.put(
                    CppNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CppNodeType.BINARY_EXPRESSION,
                                    CppNodeType.CALL_EXPRESSION,
                                    CppNodeType.CHAR_LITERAL,
                                    CppNodeType.IDENTIFIER,
                                    CppNodeType.NUMBER_LITERAL,
                                    CppNodeType.PARENTHESIZED_EXPRESSION,
                                    CppNodeType.PREPROC_DEFINED,
                                    CppNodeType.UNARY_EXPRESSION)));
            out.put(CppNodeType.PREPROC_IF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.ALTERNATIVE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(CppNodeType.PREPROC_ELIF, CppNodeType.PREPROC_ELIFDEF, CppNodeType.PREPROC_ELSE)));
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            out.put(CppNodeType.PREPROC_IFDEF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.PATH,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    CppNodeType.CALL_EXPRESSION,
                                    CppNodeType.IDENTIFIER,
                                    CppNodeType.STRING_LITERAL,
                                    CppNodeType.SYSTEM_LIB_STRING)));
            out.put(CppNodeType.PREPROC_INCLUDE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.NAME,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.DEPENDENT_NAME,
                                    CppNodeType.DESTRUCTOR_NAME,
                                    CppNodeType.FIELD_IDENTIFIER,
                                    CppNodeType.IDENTIFIER,
                                    CppNodeType.OPERATOR_CAST,
                                    CppNodeType.OPERATOR_NAME,
                                    CppNodeType.POINTER_TYPE_DECLARATOR,
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.TEMPLATE_FUNCTION,
                                    CppNodeType.TEMPLATE_METHOD,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            m.put(
                    CppNodeField.SCOPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    CppNodeType.DECLTYPE,
                                    CppNodeType.DEPENDENT_NAME,
                                    CppNodeType.NAMESPACE_IDENTIFIER,
                                    CppNodeType.TEMPLATE_TYPE)));
            out.put(CppNodeType.QUALIFIED_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DELIMITER, new FieldInfo(false, false, Set.of(CppNodeType.RAW_STRING_DELIMITER)));
            out.put(CppNodeType.RAW_STRING_LITERAL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.CONSTRAINT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    CppNodeType.CONSTRAINT_CONJUNCTION,
                                    CppNodeType.CONSTRAINT_DISJUNCTION,
                                    CppNodeType.EXPRESSION,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.REQUIRES_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.PARAMETERS, new FieldInfo(false, false, Set.of(CppNodeType.PARAMETER_LIST)));
            m.put(CppNodeField.REQUIREMENTS, new FieldInfo(true, false, Set.of(CppNodeType.REQUIREMENT_SEQ)));
            out.put(CppNodeType.REQUIRES_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.COMPOUND_STATEMENT)));
            m.put(CppNodeField.FILTER, new FieldInfo(true, false, Set.of(CppNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(CppNodeType.SEH_EXCEPT_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.COMPOUND_STATEMENT)));
            out.put(CppNodeType.SEH_FINALLY_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.COMPOUND_STATEMENT)));
            out.put(CppNodeType.SEH_TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.TYPE,
                    new FieldInfo(false, false, Set.of(CppNodeType.PRIMITIVE_TYPE, CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.SIZED_TYPE_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.TYPE, new FieldInfo(false, false, Set.of(CppNodeType.TYPE_DESCRIPTOR)));
            m.put(CppNodeField.VALUE, new FieldInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.SIZEOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.CONDITION, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(
                    CppNodeField.MESSAGE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    CppNodeType.CONCATENATED_STRING,
                                    CppNodeType.RAW_STRING_LITERAL,
                                    CppNodeType.STRING_LITERAL)));
            out.put(CppNodeType.STATIC_ASSERT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(false, false, Set.of(CppNodeType.FIELD_DECLARATION_LIST)));
            m.put(
                    CppNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.STRUCT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.INDICES, new FieldInfo(true, false, Set.of(CppNodeType.SUBSCRIPT_ARGUMENT_LIST)));
            out.put(CppNodeType.SUBSCRIPT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.END, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.START, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            out.put(CppNodeType.SUBSCRIPT_RANGE_DESIGNATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.COMPOUND_STATEMENT)));
            m.put(CppNodeField.CONDITION, new FieldInfo(true, false, Set.of(CppNodeType.CONDITION_CLAUSE)));
            out.put(CppNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CppNodeType.TEMPLATE_PARAMETER_LIST)));
            out.put(CppNodeType.TEMPLATE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(CppNodeType.TEMPLATE_ARGUMENT_LIST)));
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
            out.put(CppNodeType.TEMPLATE_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(true, false, Set.of(CppNodeType.DECLARATOR)));
            m.put(CppNodeField.TYPE, new FieldInfo(false, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.TEMPLATE_INSTANTIATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(CppNodeType.TEMPLATE_ARGUMENT_LIST)));
            m.put(
                    CppNodeField.NAME,
                    new FieldInfo(true, false, Set.of(CppNodeType.FIELD_IDENTIFIER, CppNodeType.OPERATOR_NAME)));
            out.put(CppNodeType.TEMPLATE_METHOD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(CppNodeType.TEMPLATE_PARAMETER_LIST)));
            out.put(CppNodeType.TEMPLATE_TEMPLATE_PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(CppNodeType.TEMPLATE_ARGUMENT_LIST)));
            m.put(CppNodeField.NAME, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.TEMPLATE_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.COMPOUND_STATEMENT)));
            out.put(CppNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(true, true, Set.of(CppNodeType.TYPE_DECLARATOR)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.TYPE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.DECLARATOR, new FieldInfo(false, false, Set.of(CppNodeType.ABSTRACT_DECLARATOR)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.TYPE_DESCRIPTOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.ARGUMENT,
                    new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.PREPROC_DEFINED)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CppNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(false, false, Set.of(CppNodeType.FIELD_DECLARATION_LIST)));
            m.put(
                    CppNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    CppNodeType.QUALIFIED_IDENTIFIER,
                                    CppNodeType.TEMPLATE_TYPE,
                                    CppNodeType.TYPE_IDENTIFIER)));
            out.put(CppNodeType.UNION_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
            m.put(CppNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(CppNodeType.UPDATE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(
                    CppNodeField.DECLARATOR,
                    new FieldInfo(
                            true, false, Set.of(CppNodeType.REFERENCE_DECLARATOR, CppNodeType.VARIADIC_DECLARATOR)));
            m.put(CppNodeField.TYPE, new FieldInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
            out.put(CppNodeType.VARIADIC_PARAMETER_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<CppNodeField, FieldInfo> m = new EnumMap<>(CppNodeField.class);
            m.put(CppNodeField.BODY, new FieldInfo(true, false, Set.of(CppNodeType.STATEMENT)));
            m.put(CppNodeField.CONDITION, new FieldInfo(true, false, Set.of(CppNodeType.CONDITION_CLAUSE)));
            out.put(CppNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<CppNodeType, ChildInfo> initChildren() {
        EnumMap<CppNodeType, ChildInfo> out = new EnumMap<>(CppNodeType.class);
        out.put(CppNodeType.ABSTRACT_ARRAY_DECLARATOR, new ChildInfo(false, true, Set.of(CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.ABSTRACT_FUNCTION_DECLARATOR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.GNU_ASM_EXPRESSION,
                                CppNodeType.NOEXCEPT,
                                CppNodeType.REF_QUALIFIER,
                                CppNodeType.REQUIRES_CLAUSE,
                                CppNodeType.THROW_SPECIFIER,
                                CppNodeType.TRAILING_RETURN_TYPE,
                                CppNodeType.TYPE_QUALIFIER,
                                CppNodeType.VIRTUAL_SPECIFIER)));
        out.put(
                CppNodeType.ABSTRACT_PARENTHESIZED_DECLARATOR,
                new ChildInfo(true, true, Set.of(CppNodeType.ABSTRACT_DECLARATOR, CppNodeType.MS_CALL_MODIFIER)));
        out.put(
                CppNodeType.ABSTRACT_POINTER_DECLARATOR,
                new ChildInfo(false, true, Set.of(CppNodeType.MS_POINTER_MODIFIER, CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.ABSTRACT_REFERENCE_DECLARATOR,
                new ChildInfo(false, false, Set.of(CppNodeType.ABSTRACT_DECLARATOR)));
        out.put(CppNodeType.ALIAS_DECLARATION, new ChildInfo(false, true, Set.of(CppNodeType.ATTRIBUTE_DECLARATION)));
        out.put(
                CppNodeType.ALIGNAS_QUALIFIER,
                new ChildInfo(true, false, Set.of(CppNodeType.EXPRESSION, CppNodeType.TYPE_DESCRIPTOR)));
        out.put(
                CppNodeType.ARGUMENT_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.COMPOUND_STATEMENT,
                                CppNodeType.EXPRESSION,
                                CppNodeType.INITIALIZER_LIST,
                                CppNodeType.PREPROC_DEFINED)));
        out.put(CppNodeType.ARRAY_DECLARATOR, new ChildInfo(false, true, Set.of(CppNodeType.TYPE_QUALIFIER)));
        out.put(CppNodeType.ATTRIBUTE, new ChildInfo(false, false, Set.of(CppNodeType.ARGUMENT_LIST)));
        out.put(
                CppNodeType.ATTRIBUTED_DECLARATOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.DECLARATOR,
                                CppNodeType.FIELD_DECLARATOR,
                                CppNodeType.TYPE_DECLARATOR)));
        out.put(
                CppNodeType.ATTRIBUTED_STATEMENT,
                new ChildInfo(true, true, Set.of(CppNodeType.ATTRIBUTE_DECLARATION, CppNodeType.STATEMENT)));
        out.put(CppNodeType.ATTRIBUTE_DECLARATION, new ChildInfo(true, true, Set.of(CppNodeType.ATTRIBUTE)));
        out.put(CppNodeType.ATTRIBUTE_SPECIFIER, new ChildInfo(true, false, Set.of(CppNodeType.ARGUMENT_LIST)));
        out.put(
                CppNodeType.BASE_CLASS_CLAUSE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CppNodeType.ACCESS_SPECIFIER,
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.QUALIFIED_IDENTIFIER,
                                CppNodeType.TEMPLATE_TYPE,
                                CppNodeType.TYPE_IDENTIFIER)));
        out.put(CppNodeType.BITFIELD_CLAUSE, new ChildInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(
                CppNodeType.CASE_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTED_STATEMENT,
                                CppNodeType.BREAK_STATEMENT,
                                CppNodeType.COMPOUND_STATEMENT,
                                CppNodeType.CONTINUE_STATEMENT,
                                CppNodeType.CO_RETURN_STATEMENT,
                                CppNodeType.CO_YIELD_STATEMENT,
                                CppNodeType.DECLARATION,
                                CppNodeType.DO_STATEMENT,
                                CppNodeType.EXPRESSION_STATEMENT,
                                CppNodeType.FOR_RANGE_LOOP,
                                CppNodeType.FOR_STATEMENT,
                                CppNodeType.GOTO_STATEMENT,
                                CppNodeType.IF_STATEMENT,
                                CppNodeType.LABELED_STATEMENT,
                                CppNodeType.RETURN_STATEMENT,
                                CppNodeType.SEH_LEAVE_STATEMENT,
                                CppNodeType.SEH_TRY_STATEMENT,
                                CppNodeType.SWITCH_STATEMENT,
                                CppNodeType.THROW_STATEMENT,
                                CppNodeType.TRY_STATEMENT,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.WHILE_STATEMENT)));
        out.put(
                CppNodeType.CHAR_LITERAL,
                new ChildInfo(true, true, Set.of(CppNodeType.CHARACTER, CppNodeType.ESCAPE_SEQUENCE)));
        out.put(
                CppNodeType.CLASS_SPECIFIER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ALIGNAS_QUALIFIER,
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.BASE_CLASS_CLAUSE,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.VIRTUAL_SPECIFIER)));
        out.put(
                CppNodeType.COMPOUND_REQUIREMENT,
                new ChildInfo(true, true, Set.of(CppNodeType.EXPRESSION, CppNodeType.TRAILING_RETURN_TYPE)));
        out.put(
                CppNodeType.COMPOUND_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION)));
        out.put(
                CppNodeType.CONCATENATED_STRING,
                new ChildInfo(
                        true,
                        true,
                        Set.of(CppNodeType.IDENTIFIER, CppNodeType.RAW_STRING_LITERAL, CppNodeType.STRING_LITERAL)));
        out.put(CppNodeType.CONCEPT_DEFINITION, new ChildInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(CppNodeType.CO_RETURN_STATEMENT, new ChildInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(CppNodeType.CO_YIELD_STATEMENT, new ChildInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(
                CppNodeType.DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.EXPLICIT_FUNCTION_SPECIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.DECLARATION_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION)));
        out.put(CppNodeType.DECLTYPE, new ChildInfo(true, false, Set.of(CppNodeType.AUTO, CppNodeType.EXPRESSION)));
        out.put(CppNodeType.DELETE_EXPRESSION, new ChildInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(
                CppNodeType.DEPENDENT_NAME,
                new ChildInfo(
                        true,
                        false,
                        Set.of(CppNodeType.TEMPLATE_FUNCTION, CppNodeType.TEMPLATE_METHOD, CppNodeType.TEMPLATE_TYPE)));
        out.put(CppNodeType.DEPENDENT_TYPE, new ChildInfo(true, false, Set.of(CppNodeType.TYPE_SPECIFIER)));
        out.put(CppNodeType.DESTRUCTOR_NAME, new ChildInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
        out.put(CppNodeType.ELSE_CLAUSE, new ChildInfo(true, false, Set.of(CppNodeType.STATEMENT)));
        out.put(
                CppNodeType.ENUMERATOR_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ENUMERATOR,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF)));
        out.put(CppNodeType.ENUM_SPECIFIER, new ChildInfo(false, false, Set.of(CppNodeType.ATTRIBUTE_SPECIFIER)));
        out.put(CppNodeType.EXPLICIT_FUNCTION_SPECIFIER, new ChildInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(
                CppNodeType.EXPRESSION_STATEMENT,
                new ChildInfo(false, false, Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.EXPRESSION)));
        out.put(CppNodeType.EXTENSION_EXPRESSION, new ChildInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(
                CppNodeType.FIELD_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.BITFIELD_CLAUSE,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.FIELD_DECLARATION_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ACCESS_SPECIFIER,
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.DECLARATION,
                                CppNodeType.FIELD_DECLARATION,
                                CppNodeType.FRIEND_DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.USING_DECLARATION)));
        out.put(CppNodeType.FIELD_DESIGNATOR, new ChildInfo(true, false, Set.of(CppNodeType.FIELD_IDENTIFIER)));
        out.put(
                CppNodeType.FIELD_INITIALIZER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CppNodeType.ARGUMENT_LIST,
                                CppNodeType.FIELD_IDENTIFIER,
                                CppNodeType.INITIALIZER_LIST,
                                CppNodeType.QUALIFIED_IDENTIFIER,
                                CppNodeType.TEMPLATE_METHOD)));
        out.put(CppNodeType.FIELD_INITIALIZER_LIST, new ChildInfo(true, true, Set.of(CppNodeType.FIELD_INITIALIZER)));
        out.put(
                CppNodeType.FOR_RANGE_LOOP,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.FRIEND_DECLARATION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CppNodeType.DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.QUALIFIED_IDENTIFIER,
                                CppNodeType.TEMPLATE_TYPE,
                                CppNodeType.TYPE_IDENTIFIER)));
        out.put(
                CppNodeType.FUNCTION_DECLARATOR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.GNU_ASM_EXPRESSION,
                                CppNodeType.NOEXCEPT,
                                CppNodeType.REF_QUALIFIER,
                                CppNodeType.REQUIRES_CLAUSE,
                                CppNodeType.THROW_SPECIFIER,
                                CppNodeType.TRAILING_RETURN_TYPE,
                                CppNodeType.TYPE_QUALIFIER,
                                CppNodeType.VIRTUAL_SPECIFIER)));
        out.put(
                CppNodeType.FUNCTION_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.DEFAULT_METHOD_CLAUSE,
                                CppNodeType.DELETE_METHOD_CLAUSE,
                                CppNodeType.EXPLICIT_FUNCTION_SPECIFIER,
                                CppNodeType.FIELD_INITIALIZER_LIST,
                                CppNodeType.MS_CALL_MODIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.PURE_VIRTUAL_CLAUSE,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TRY_STATEMENT,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.GENERIC_EXPRESSION,
                new ChildInfo(true, true, Set.of(CppNodeType.EXPRESSION, CppNodeType.TYPE_DESCRIPTOR)));
        out.put(CppNodeType.GNU_ASM_EXPRESSION, new ChildInfo(false, true, Set.of(CppNodeType.GNU_ASM_QUALIFIER)));
        out.put(
                CppNodeType.INITIALIZER_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST, CppNodeType.INITIALIZER_PAIR)));
        out.put(
                CppNodeType.INIT_STATEMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.DECLARATION,
                                CppNodeType.EXPRESSION_STATEMENT,
                                CppNodeType.TYPE_DEFINITION)));
        out.put(
                CppNodeType.LABELED_STATEMENT,
                new ChildInfo(true, false, Set.of(CppNodeType.DECLARATION, CppNodeType.STATEMENT)));
        out.put(
                CppNodeType.LAMBDA_CAPTURE_SPECIFIER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.IDENTIFIER,
                                CppNodeType.LAMBDA_CAPTURE_INITIALIZER,
                                CppNodeType.LAMBDA_DEFAULT_CAPTURE,
                                CppNodeType.PARAMETER_PACK_EXPANSION,
                                CppNodeType.QUALIFIED_IDENTIFIER,
                                CppNodeType.THIS_)));
        out.put(CppNodeType.MS_BASED_MODIFIER, new ChildInfo(true, false, Set.of(CppNodeType.ARGUMENT_LIST)));
        out.put(CppNodeType.MS_DECLSPEC_MODIFIER, new ChildInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
        out.put(
                CppNodeType.MS_POINTER_MODIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CppNodeType.MS_RESTRICT_MODIFIER,
                                CppNodeType.MS_SIGNED_PTR_MODIFIER,
                                CppNodeType.MS_UNALIGNED_PTR_MODIFIER,
                                CppNodeType.MS_UNSIGNED_PTR_MODIFIER)));
        out.put(
                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                new ChildInfo(
                        true, false, Set.of(CppNodeType.NAMESPACE_IDENTIFIER, CppNodeType.NESTED_NAMESPACE_SPECIFIER)));
        out.put(
                CppNodeType.NAMESPACE_DEFINITION,
                new ChildInfo(false, false, Set.of(CppNodeType.ATTRIBUTE_DECLARATION)));
        out.put(
                CppNodeType.NESTED_NAMESPACE_SPECIFIER,
                new ChildInfo(
                        false, true, Set.of(CppNodeType.NAMESPACE_IDENTIFIER, CppNodeType.NESTED_NAMESPACE_SPECIFIER)));
        out.put(CppNodeType.NEW_DECLARATOR, new ChildInfo(false, false, Set.of(CppNodeType.NEW_DECLARATOR)));
        out.put(CppNodeType.NOEXCEPT, new ChildInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(
                CppNodeType.OPERATOR_CAST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(CppNodeType.OPERATOR_NAME, new ChildInfo(false, false, Set.of(CppNodeType.IDENTIFIER)));
        out.put(
                CppNodeType.OPTIONAL_PARAMETER_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.PARAMETER_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.PARAMETER_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.OPTIONAL_PARAMETER_DECLARATION,
                                CppNodeType.PARAMETER_DECLARATION,
                                CppNodeType.VARIADIC_PARAMETER_DECLARATION)));
        out.put(
                CppNodeType.PARENTHESIZED_DECLARATOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CppNodeType.DECLARATOR,
                                CppNodeType.FIELD_DECLARATOR,
                                CppNodeType.MS_CALL_MODIFIER,
                                CppNodeType.TYPE_DECLARATOR)));
        out.put(
                CppNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CppNodeType.COMMA_EXPRESSION,
                                CppNodeType.COMPOUND_STATEMENT,
                                CppNodeType.EXPRESSION,
                                CppNodeType.PREPROC_DEFINED)));
        out.put(
                CppNodeType.PLACEHOLDER_TYPE_SPECIFIER,
                new ChildInfo(true, false, Set.of(CppNodeType.AUTO, CppNodeType.DECLTYPE)));
        out.put(
                CppNodeType.POINTER_DECLARATOR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.MS_BASED_MODIFIER,
                                CppNodeType.MS_POINTER_MODIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.POINTER_TYPE_DECLARATOR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.MS_BASED_MODIFIER,
                                CppNodeType.MS_POINTER_MODIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(CppNodeType.PREPROC_DEFINED, new ChildInfo(true, false, Set.of(CppNodeType.IDENTIFIER)));
        out.put(
                CppNodeType.PREPROC_ELIF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ACCESS_SPECIFIER,
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.ENUMERATOR,
                                CppNodeType.FIELD_DECLARATION,
                                CppNodeType.FRIEND_DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION)));
        out.put(
                CppNodeType.PREPROC_ELIFDEF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ACCESS_SPECIFIER,
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.ENUMERATOR,
                                CppNodeType.FIELD_DECLARATION,
                                CppNodeType.FRIEND_DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION)));
        out.put(
                CppNodeType.PREPROC_ELSE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ACCESS_SPECIFIER,
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.ENUMERATOR,
                                CppNodeType.FIELD_DECLARATION,
                                CppNodeType.FRIEND_DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION)));
        out.put(
                CppNodeType.PREPROC_IF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ACCESS_SPECIFIER,
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.ENUMERATOR,
                                CppNodeType.FIELD_DECLARATION,
                                CppNodeType.FRIEND_DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION)));
        out.put(
                CppNodeType.PREPROC_IFDEF,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ACCESS_SPECIFIER,
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.ENUMERATOR,
                                CppNodeType.FIELD_DECLARATION,
                                CppNodeType.FRIEND_DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION)));
        out.put(CppNodeType.PREPROC_PARAMS, new ChildInfo(false, true, Set.of(CppNodeType.IDENTIFIER)));
        out.put(
                CppNodeType.RAW_STRING_LITERAL,
                new ChildInfo(true, true, Set.of(CppNodeType.RAW_STRING_CONTENT, CppNodeType.RAW_STRING_DELIMITER)));
        out.put(
                CppNodeType.REFERENCE_DECLARATOR,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CppNodeType.DECLARATOR,
                                CppNodeType.FIELD_DECLARATOR,
                                CppNodeType.TYPE_DECLARATOR,
                                CppNodeType.VARIADIC_DECLARATOR)));
        out.put(
                CppNodeType.REQUIREMENT_SEQ,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.COMPOUND_REQUIREMENT,
                                CppNodeType.SIMPLE_REQUIREMENT,
                                CppNodeType.TYPE_REQUIREMENT)));
        out.put(
                CppNodeType.RETURN_STATEMENT,
                new ChildInfo(
                        false,
                        false,
                        Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
        out.put(
                CppNodeType.SEH_TRY_STATEMENT,
                new ChildInfo(true, false, Set.of(CppNodeType.SEH_EXCEPT_CLAUSE, CppNodeType.SEH_FINALLY_CLAUSE)));
        out.put(
                CppNodeType.SIMPLE_REQUIREMENT,
                new ChildInfo(false, false, Set.of(CppNodeType.COMMA_EXPRESSION, CppNodeType.EXPRESSION)));
        out.put(CppNodeType.SIZED_TYPE_SPECIFIER, new ChildInfo(false, true, Set.of(CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.STRING_LITERAL,
                new ChildInfo(false, true, Set.of(CppNodeType.ESCAPE_SEQUENCE, CppNodeType.STRING_CONTENT)));
        out.put(CppNodeType.STRUCTURED_BINDING_DECLARATOR, new ChildInfo(true, true, Set.of(CppNodeType.IDENTIFIER)));
        out.put(
                CppNodeType.STRUCT_SPECIFIER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ALIGNAS_QUALIFIER,
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.BASE_CLASS_CLAUSE,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.VIRTUAL_SPECIFIER)));
        out.put(
                CppNodeType.SUBSCRIPT_ARGUMENT_LIST,
                new ChildInfo(false, true, Set.of(CppNodeType.EXPRESSION, CppNodeType.INITIALIZER_LIST)));
        out.put(CppNodeType.SUBSCRIPT_DESIGNATOR, new ChildInfo(true, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(
                CppNodeType.TEMPLATE_ARGUMENT_LIST,
                new ChildInfo(false, true, Set.of(CppNodeType.EXPRESSION, CppNodeType.TYPE_DESCRIPTOR)));
        out.put(
                CppNodeType.TEMPLATE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.DECLARATION,
                                CppNodeType.FRIEND_DECLARATION,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.REQUIRES_CLAUSE,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TYPE_SPECIFIER)));
        out.put(
                CppNodeType.TEMPLATE_INSTANTIATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.TEMPLATE_PARAMETER_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.OPTIONAL_PARAMETER_DECLARATION,
                                CppNodeType.OPTIONAL_TYPE_PARAMETER_DECLARATION,
                                CppNodeType.PARAMETER_DECLARATION,
                                CppNodeType.TEMPLATE_TEMPLATE_PARAMETER_DECLARATION,
                                CppNodeType.TYPE_PARAMETER_DECLARATION,
                                CppNodeType.VARIADIC_PARAMETER_DECLARATION,
                                CppNodeType.VARIADIC_TYPE_PARAMETER_DECLARATION)));
        out.put(
                CppNodeType.TEMPLATE_TEMPLATE_PARAMETER_DECLARATION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CppNodeType.OPTIONAL_TYPE_PARAMETER_DECLARATION,
                                CppNodeType.TYPE_PARAMETER_DECLARATION,
                                CppNodeType.VARIADIC_TYPE_PARAMETER_DECLARATION)));
        out.put(CppNodeType.THROW_SPECIFIER, new ChildInfo(false, true, Set.of(CppNodeType.TYPE_DESCRIPTOR)));
        out.put(CppNodeType.THROW_STATEMENT, new ChildInfo(false, false, Set.of(CppNodeType.EXPRESSION)));
        out.put(CppNodeType.TRAILING_RETURN_TYPE, new ChildInfo(true, false, Set.of(CppNodeType.TYPE_DESCRIPTOR)));
        out.put(
                CppNodeType.TRANSLATION_UNIT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ALIAS_DECLARATION,
                                CppNodeType.ATTRIBUTED_STATEMENT,
                                CppNodeType.BREAK_STATEMENT,
                                CppNodeType.CASE_STATEMENT,
                                CppNodeType.COMPOUND_STATEMENT,
                                CppNodeType.CONCEPT_DEFINITION,
                                CppNodeType.CONTINUE_STATEMENT,
                                CppNodeType.CO_RETURN_STATEMENT,
                                CppNodeType.CO_YIELD_STATEMENT,
                                CppNodeType.DECLARATION,
                                CppNodeType.DO_STATEMENT,
                                CppNodeType.EXPRESSION_STATEMENT,
                                CppNodeType.FOR_RANGE_LOOP,
                                CppNodeType.FOR_STATEMENT,
                                CppNodeType.FUNCTION_DEFINITION,
                                CppNodeType.GOTO_STATEMENT,
                                CppNodeType.IF_STATEMENT,
                                CppNodeType.LABELED_STATEMENT,
                                CppNodeType.LINKAGE_SPECIFICATION,
                                CppNodeType.NAMESPACE_ALIAS_DEFINITION,
                                CppNodeType.NAMESPACE_DEFINITION,
                                CppNodeType.PREPROC_CALL,
                                CppNodeType.PREPROC_DEF,
                                CppNodeType.PREPROC_FUNCTION_DEF,
                                CppNodeType.PREPROC_IF,
                                CppNodeType.PREPROC_IFDEF,
                                CppNodeType.PREPROC_INCLUDE,
                                CppNodeType.RETURN_STATEMENT,
                                CppNodeType.STATIC_ASSERT_DECLARATION,
                                CppNodeType.SWITCH_STATEMENT,
                                CppNodeType.TEMPLATE_DECLARATION,
                                CppNodeType.TEMPLATE_INSTANTIATION,
                                CppNodeType.THROW_STATEMENT,
                                CppNodeType.TRY_STATEMENT,
                                CppNodeType.TYPE_DEFINITION,
                                CppNodeType.TYPE_SPECIFIER,
                                CppNodeType.USING_DECLARATION,
                                CppNodeType.WHILE_STATEMENT)));
        out.put(
                CppNodeType.TRY_STATEMENT,
                new ChildInfo(true, true, Set.of(CppNodeType.CATCH_CLAUSE, CppNodeType.FIELD_INITIALIZER_LIST)));
        out.put(
                CppNodeType.TYPE_DEFINITION,
                new ChildInfo(false, true, Set.of(CppNodeType.ATTRIBUTE_SPECIFIER, CppNodeType.TYPE_QUALIFIER)));
        out.put(CppNodeType.TYPE_DESCRIPTOR, new ChildInfo(false, true, Set.of(CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.TYPE_PARAMETER_DECLARATION,
                new ChildInfo(false, false, Set.of(CppNodeType.TYPE_IDENTIFIER)));
        out.put(CppNodeType.TYPE_QUALIFIER, new ChildInfo(false, false, Set.of(CppNodeType.ALIGNAS_QUALIFIER)));
        out.put(
                CppNodeType.TYPE_REQUIREMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CppNodeType.QUALIFIED_IDENTIFIER,
                                CppNodeType.TEMPLATE_TYPE,
                                CppNodeType.TYPE_IDENTIFIER)));
        out.put(
                CppNodeType.UNION_SPECIFIER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ALIGNAS_QUALIFIER,
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.BASE_CLASS_CLAUSE,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.VIRTUAL_SPECIFIER)));
        out.put(
                CppNodeType.USER_DEFINED_LITERAL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CppNodeType.CHAR_LITERAL,
                                CppNodeType.CONCATENATED_STRING,
                                CppNodeType.LITERAL_SUFFIX,
                                CppNodeType.NUMBER_LITERAL,
                                CppNodeType.RAW_STRING_LITERAL,
                                CppNodeType.STRING_LITERAL)));
        out.put(
                CppNodeType.USING_DECLARATION,
                new ChildInfo(true, false, Set.of(CppNodeType.IDENTIFIER, CppNodeType.QUALIFIED_IDENTIFIER)));
        out.put(CppNodeType.VARIADIC_DECLARATOR, new ChildInfo(false, false, Set.of(CppNodeType.IDENTIFIER)));
        out.put(
                CppNodeType.VARIADIC_PARAMETER_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CppNodeType.ATTRIBUTE_DECLARATION,
                                CppNodeType.ATTRIBUTE_SPECIFIER,
                                CppNodeType.MS_DECLSPEC_MODIFIER,
                                CppNodeType.STORAGE_CLASS_SPECIFIER,
                                CppNodeType.TYPE_QUALIFIER)));
        out.put(
                CppNodeType.VARIADIC_TYPE_PARAMETER_DECLARATION,
                new ChildInfo(false, false, Set.of(CppNodeType.TYPE_IDENTIFIER)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<CppNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<CppNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<CppNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<CppNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
