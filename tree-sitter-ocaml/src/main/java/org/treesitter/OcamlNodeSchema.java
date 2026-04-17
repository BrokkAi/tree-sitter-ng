package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code ocaml} from tree-sitter {@code node-types.json}.
 */
public final class OcamlNodeSchema {
    private OcamlNodeSchema() {}

    public static Set<OcamlNodeField> fields(@Nullable OcamlNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<OcamlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<OcamlNodeType> allowedTypes(@Nullable OcamlNodeType owner, @Nullable OcamlNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<OcamlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable OcamlNodeType owner, @Nullable OcamlNodeField field) {
        if (owner == null || field == null) return false;
        Map<OcamlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable OcamlNodeType owner, @Nullable OcamlNodeField field) {
        if (owner == null || field == null) return false;
        Map<OcamlNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<OcamlNodeType> allowedChildTypes(@Nullable OcamlNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable OcamlNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable OcamlNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<OcamlNodeType, Map<OcamlNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<OcamlNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<OcamlNodeType, Map<OcamlNodeField, FieldInfo>> initFields() {
        EnumMap<OcamlNodeType, Map<OcamlNodeField, FieldInfo>> out = new EnumMap<>(OcamlNodeType.class);
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(
                    OcamlNodeField.ARGUMENT,
                    new FieldInfo(true, true, Set.of(OcamlNodeType.LABELED_ARGUMENT, OcamlNodeType.SIMPLE_EXPRESSION)));
            m.put(OcamlNodeField.FUNCTION, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_EXPRESSION)));
            out.put(OcamlNodeType.APPLICATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(
                    OcamlNodeField.ARGUMENT,
                    new FieldInfo(true, true, Set.of(OcamlNodeType.LABELED_ARGUMENT, OcamlNodeType.SIMPLE_EXPRESSION)));
            m.put(OcamlNodeField.CLASS_, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_CLASS_EXPRESSION)));
            out.put(OcamlNodeType.CLASS_APPLICATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(false, false, Set.of(OcamlNodeType.CLASS_EXPRESSION)));
            m.put(OcamlNodeField.NAME, new FieldInfo(true, false, Set.of(OcamlNodeType.CLASS_NAME)));
            out.put(OcamlNodeType.CLASS_BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.CLASS_EXPRESSION)));
            out.put(OcamlNodeType.CLASS_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_CLASS_TYPE)));
            m.put(OcamlNodeField.NAME, new FieldInfo(true, false, Set.of(OcamlNodeType.CLASS_TYPE_NAME)));
            out.put(OcamlNodeType.CLASS_TYPE_BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.PATTERN, new FieldInfo(false, false, Set.of(OcamlNodeType.BINDING_PATTERN)));
            out.put(OcamlNodeType.CONSTRUCTOR_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.LEFT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            m.put(OcamlNodeField.RIGHT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            out.put(OcamlNodeType.CONS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.CONTINUATION, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_PATTERN)));
            m.put(OcamlNodeField.EFFECT, new FieldInfo(true, false, Set.of(OcamlNodeType.EFFECT_PATTERN)));
            out.put(OcamlNodeType.EFFECT_PATTERN_2, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(false, false, Set.of(OcamlNodeType.EXPRESSION)));
            m.put(OcamlNodeField.NAME, new FieldInfo(true, false, Set.of(OcamlNodeType.FIELD_PATH)));
            out.put(OcamlNodeType.FIELD_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.PATTERN, new FieldInfo(false, false, Set.of(OcamlNodeType.BINDING_PATTERN)));
            out.put(OcamlNodeType.FIELD_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.FROM, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    OcamlNodeField.NAME,
                    new FieldInfo(
                            true, false, Set.of(OcamlNodeType.PARENTHESIZED_OPERATOR, OcamlNodeType.VALUE_PATTERN)));
            m.put(OcamlNodeField.TO, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.FOR_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.MODULE_EXPRESSION)));
            out.put(OcamlNodeType.FUNCTOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.FUN_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.LEFT, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_EXPRESSION)));
            m.put(OcamlNodeField.OPERATOR, new FieldInfo(true, false, Set.of(OcamlNodeType.HASH_OPERATOR)));
            m.put(OcamlNodeField.RIGHT, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_EXPRESSION)));
            out.put(OcamlNodeType.HASH_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.CONDITION, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.IF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.LEFT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            m.put(
                    OcamlNodeField.OPERATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    OcamlNodeType.ADD_OPERATOR,
                                    OcamlNodeType.AND_OPERATOR,
                                    OcamlNodeType.ASSIGN_OPERATOR,
                                    OcamlNodeType.CONCAT_OPERATOR,
                                    OcamlNodeType.MULT_OPERATOR,
                                    OcamlNodeType.OR_OPERATOR,
                                    OcamlNodeType.POW_OPERATOR,
                                    OcamlNodeType.REL_OPERATOR)));
            m.put(OcamlNodeField.RIGHT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            out.put(OcamlNodeType.INFIX_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(false, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            m.put(OcamlNodeField.NAME, new FieldInfo(true, false, Set.of(OcamlNodeType.INSTANCE_VARIABLE_NAME)));
            out.put(OcamlNodeType.INSTANCE_VARIABLE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(false, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            m.put(OcamlNodeField.PATTERN, new FieldInfo(true, false, Set.of(OcamlNodeType.BINDING_PATTERN)));
            out.put(OcamlNodeType.LET_BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.CLASS_EXPRESSION)));
            out.put(OcamlNodeType.LET_CLASS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.LET_EXCEPTION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.LET_MODULE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.CLASS_EXPRESSION)));
            out.put(OcamlNodeType.LET_OPEN_CLASS_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_CLASS_TYPE)));
            out.put(OcamlNodeType.LET_OPEN_CLASS_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.LET_OPEN_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(
                    OcamlNodeField.BODY,
                    new FieldInfo(
                            true, false, Set.of(OcamlNodeType.REFUTATION_CASE, OcamlNodeType.SEQUENCE_EXPRESSION)));
            m.put(OcamlNodeField.PATTERN, new FieldInfo(true, false, Set.of(OcamlNodeType.PATTERN)));
            out.put(OcamlNodeType.MATCH_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(false, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            m.put(OcamlNodeField.NAME, new FieldInfo(true, false, Set.of(OcamlNodeType.METHOD_NAME)));
            out.put(OcamlNodeType.METHOD_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.ARGUMENT, new FieldInfo(false, false, Set.of(OcamlNodeType.SIMPLE_MODULE_EXPRESSION)));
            m.put(OcamlNodeField.FUNCTOR, new FieldInfo(true, false, Set.of(OcamlNodeType.MODULE_EXPRESSION)));
            out.put(OcamlNodeType.MODULE_APPLICATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(false, false, Set.of(OcamlNodeType.MODULE_EXPRESSION)));
            m.put(OcamlNodeField.NAME, new FieldInfo(true, false, Set.of(OcamlNodeType.MODULE_NAME)));
            out.put(OcamlNodeType.MODULE_BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.NAME, new FieldInfo(false, false, Set.of(OcamlNodeType.MODULE_NAME)));
            out.put(OcamlNodeType.MODULE_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(false, false, Set.of(OcamlNodeType.MODULE_TYPE)));
            m.put(OcamlNodeField.NAME, new FieldInfo(true, false, Set.of(OcamlNodeType.MODULE_TYPE_NAME)));
            out.put(OcamlNodeType.MODULE_TYPE_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(
                    OcamlNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(OcamlNodeType.PATTERN, OcamlNodeType.SIMPLE_PATTERN)));
            out.put(OcamlNodeType.PARAMETER_2, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.OPERATOR, new FieldInfo(true, false, Set.of(OcamlNodeType.PREFIX_OPERATOR)));
            m.put(OcamlNodeField.RIGHT, new FieldInfo(true, false, Set.of(OcamlNodeType.SIMPLE_EXPRESSION)));
            out.put(OcamlNodeType.PREFIX_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.LEFT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            m.put(OcamlNodeField.RIGHT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            out.put(OcamlNodeType.PRODUCT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.LEFT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            m.put(OcamlNodeField.RIGHT, new FieldInfo(false, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.SEQUENCE_EXPRESSION_2, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.BODY, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            out.put(OcamlNodeType.SET_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.OPERATOR, new FieldInfo(true, false, Set.of(OcamlNodeType.SIGN_OPERATOR)));
            m.put(OcamlNodeField.RIGHT, new FieldInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
            out.put(OcamlNodeType.SIGN_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.PATTERN, new FieldInfo(false, false, Set.of(OcamlNodeType.BINDING_PATTERN)));
            out.put(OcamlNodeType.TAG_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.PATTERN, new FieldInfo(false, false, Set.of(OcamlNodeType.BINDING_PATTERN)));
            out.put(OcamlNodeType.TYPED_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(
                    OcamlNodeField.BODY,
                    new FieldInfo(
                            false, false, Set.of(OcamlNodeType.RECORD_DECLARATION, OcamlNodeType.VARIANT_DECLARATION)));
            m.put(
                    OcamlNodeField.NAME,
                    new FieldInfo(
                            true, false, Set.of(OcamlNodeType.TYPE_CONSTRUCTOR, OcamlNodeType.TYPE_CONSTRUCTOR_PATH)));
            out.put(OcamlNodeType.TYPE_BINDING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<OcamlNodeField, FieldInfo> m = new EnumMap<>(OcamlNodeField.class);
            m.put(OcamlNodeField.CONDITION, new FieldInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
            out.put(OcamlNodeType.WHILE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<OcamlNodeType, ChildInfo> initChildren() {
        EnumMap<OcamlNodeType, ChildInfo> out = new EnumMap<>(OcamlNodeType.class);
        out.put(OcamlNodeType.ABSTRACT_TYPE, new ChildInfo(false, true, Set.of(OcamlNodeType.TYPE_CONSTRUCTOR)));
        out.put(
                OcamlNodeType.ALIASED_TYPE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.TYPE, OcamlNodeType.TYPE_VARIABLE)));
        out.put(
                OcamlNodeType.ALIAS_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN, OcamlNodeType.VALUE_PATTERN)));
        out.put(OcamlNodeType.ARRAY_BINDING_PATTERN, new ChildInfo(false, true, Set.of(OcamlNodeType.BINDING_PATTERN)));
        out.put(OcamlNodeType.ARRAY_EXPRESSION, new ChildInfo(false, true, Set.of(OcamlNodeType.EXPRESSION)));
        out.put(
                OcamlNodeType.ARRAY_GET_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.INDEXING_OPERATOR_PATH,
                                OcamlNodeType.SEQUENCE_EXPRESSION,
                                OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(
                OcamlNodeType.ARRAY_PATTERN,
                new ChildInfo(false, true, Set.of(OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN)));
        out.put(
                OcamlNodeType.ASSERT_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(
                OcamlNodeType.ATTRIBUTE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.ATTRIBUTE_PAYLOAD)));
        out.put(
                OcamlNodeType.ATTRIBUTE_PAYLOAD,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.EXPRESSION_ITEM,
                                OcamlNodeType.GUARD,
                                OcamlNodeType.PATTERN,
                                OcamlNodeType.SIGNATURE_ITEM,
                                OcamlNodeType.STRUCTURE_ITEM,
                                OcamlNodeType.TOPLEVEL_DIRECTIVE,
                                OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.BIGARRAY_GET_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.INDEXING_OPERATOR_PATH,
                                OcamlNodeType.SEQUENCE_EXPRESSION,
                                OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(OcamlNodeType.CHARACTER, new ChildInfo(true, false, Set.of(OcamlNodeType.CHARACTER_CONTENT)));
        out.put(OcamlNodeType.CHARACTER_CONTENT, new ChildInfo(false, false, Set.of(OcamlNodeType.ESCAPE_SEQUENCE)));
        out.put(
                OcamlNodeType.CLASS_BINDING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.CLASS_TYPE,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.PARAMETER,
                                OcamlNodeType.TYPE_VARIABLE)));
        out.put(
                OcamlNodeType.CLASS_BODY_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.CLASS_FIELD_SPECIFICATION,
                                OcamlNodeType.FLOATING_ATTRIBUTE,
                                OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.CLASS_DEFINITION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.CLASS_BINDING)));
        out.put(OcamlNodeType.CLASS_FUNCTION, new ChildInfo(true, true, Set.of(OcamlNodeType.PARAMETER)));
        out.put(
                OcamlNodeType.CLASS_FUNCTION_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.CLASS_TYPE, OcamlNodeType.LABEL_NAME, OcamlNodeType.TUPLE_TYPE)));
        out.put(
                OcamlNodeType.CLASS_INITIALIZER,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                OcamlNodeType.CLASS_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.CLASS_NAME, OcamlNodeType.MODULE_PATH)));
        out.put(
                OcamlNodeType.CLASS_TYPE_BINDING,
                new ChildInfo(false, true, Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.TYPE_VARIABLE)));
        out.put(
                OcamlNodeType.CLASS_TYPE_DEFINITION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.CLASS_TYPE_BINDING)));
        out.put(
                OcamlNodeType.CLASS_TYPE_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.CLASS_TYPE_NAME, OcamlNodeType.EXTENDED_MODULE_PATH)));
        out.put(
                OcamlNodeType.COERCION_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.COMPILATION_UNIT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.EXPRESSION_ITEM,
                                OcamlNodeType.SHEBANG,
                                OcamlNodeType.STRUCTURE_ITEM,
                                OcamlNodeType.TOPLEVEL_DIRECTIVE)));
        out.put(
                OcamlNodeType.CONSTRAIN_MODULE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.EXTENDED_MODULE_PATH, OcamlNodeType.MODULE_PATH)));
        out.put(OcamlNodeType.CONSTRAIN_MODULE_TYPE, new ChildInfo(true, true, Set.of(OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.CONSTRAIN_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.TYPE,
                                OcamlNodeType.TYPE_CONSTRAINT,
                                OcamlNodeType.TYPE_CONSTRUCTOR_PATH,
                                OcamlNodeType.TYPE_VARIABLE)));
        out.put(
                OcamlNodeType.CONSTRUCTED_TYPE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.SIMPLE_TYPE, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.CONSTRUCTOR_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.CONSTRUCTOR_NAME,
                                OcamlNodeType.CONSTRUCTOR_PATH,
                                OcamlNodeType.RECORD_DECLARATION,
                                OcamlNodeType.SIMPLE_TYPE)));
        out.put(
                OcamlNodeType.CONSTRUCTOR_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.CONSTRUCTOR_NAME, OcamlNodeType.MODULE_PATH)));
        out.put(
                OcamlNodeType.CONSTRUCTOR_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.ABSTRACT_TYPE, OcamlNodeType.CONSTRUCTOR_PATH, OcamlNodeType.PATTERN)));
        out.put(
                OcamlNodeType.CONS_PATTERN,
                new ChildInfo(true, true, Set.of(OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN)));
        out.put(OcamlNodeType.DO_CLAUSE, new ChildInfo(false, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(OcamlNodeType.ELSE_CLAUSE, new ChildInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
        out.put(
                OcamlNodeType.EXCEPTION_DEFINITION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.CONSTRUCTOR_DECLARATION,
                                OcamlNodeType.ITEM_ATTRIBUTE)));
        out.put(
                OcamlNodeType.EXCEPTION_PATTERN,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.PATTERN)));
        out.put(
                OcamlNodeType.EXPRESSION_ITEM,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                OcamlNodeType.EXTENDED_MODULE_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.EXTENDED_MODULE_PATH, OcamlNodeType.MODULE_NAME)));
        out.put(
                OcamlNodeType.EXTENSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.ATTRIBUTE_PAYLOAD)));
        out.put(
                OcamlNodeType.EXTERNAL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.PARENTHESIZED_OPERATOR,
                                OcamlNodeType.POLYMORPHIC_TYPE,
                                OcamlNodeType.STRING,
                                OcamlNodeType.VALUE_NAME)));
        out.put(
                OcamlNodeType.FIELD_DECLARATION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_NAME, OcamlNodeType.POLYMORPHIC_TYPE)));
        out.put(OcamlNodeType.FIELD_EXPRESSION, new ChildInfo(false, false, Set.of(OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.FIELD_GET_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_PATH, OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(
                OcamlNodeType.FIELD_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_NAME, OcamlNodeType.MODULE_PATH)));
        out.put(
                OcamlNodeType.FIELD_PATTERN,
                new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_PATH, OcamlNodeType.PATTERN, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.FLOATING_ATTRIBUTE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.ATTRIBUTE_PAYLOAD)));
        out.put(
                OcamlNodeType.FOR_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.DO_CLAUSE)));
        out.put(
                OcamlNodeType.FUNCTION_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.MATCH_CASE)));
        out.put(
                OcamlNodeType.FUNCTION_TYPE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.TYPE, OcamlNodeType.TYPED_LABEL)));
        out.put(OcamlNodeType.FUNCTOR, new ChildInfo(true, true, Set.of(OcamlNodeType.MODULE_PARAMETER)));
        out.put(
                OcamlNodeType.FUNCTOR_TYPE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.MODULE_PARAMETER, OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.FUN_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.PARAMETER, OcamlNodeType.SIMPLE_TYPE)));
        out.put(OcamlNodeType.GUARD, new ChildInfo(true, false, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                OcamlNodeType.HASH_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.CLASS_TYPE_PATH, OcamlNodeType.SIMPLE_TYPE, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.IF_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.ELSE_CLAUSE, OcamlNodeType.THEN_CLAUSE)));
        out.put(
                OcamlNodeType.INCLUDE_MODULE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.MODULE_EXPRESSION)));
        out.put(
                OcamlNodeType.INCLUDE_MODULE_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.INDEXING_OPERATOR_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.INDEXING_OPERATOR, OcamlNodeType.MODULE_PATH)));
        out.put(
                OcamlNodeType.INHERITANCE_DEFINITION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.CLASS_EXPRESSION,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.PARENTHESIZED_OPERATOR,
                                OcamlNodeType.VALUE_PATTERN)));
        out.put(
                OcamlNodeType.INHERITANCE_SPECIFICATION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.SIMPLE_CLASS_TYPE)));
        out.put(
                OcamlNodeType.INSTANCE_VARIABLE_DEFINITION,
                new ChildInfo(false, true, Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.INSTANCE_VARIABLE_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.EXPRESSION, OcamlNodeType.INSTANCE_VARIABLE_NAME)));
        out.put(
                OcamlNodeType.INSTANCE_VARIABLE_SPECIFICATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.INSTANCE_VARIABLE_NAME,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.INSTANTIATED_CLASS,
                new ChildInfo(true, true, Set.of(OcamlNodeType.CLASS_PATH, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.INSTANTIATED_CLASS_TYPE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.CLASS_TYPE_PATH, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.ITEM_ATTRIBUTE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.ATTRIBUTE_PAYLOAD)));
        out.put(
                OcamlNodeType.ITEM_EXTENSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.ATTRIBUTE_PAYLOAD,
                                OcamlNodeType.ITEM_ATTRIBUTE)));
        out.put(
                OcamlNodeType.LABELED_ARGUMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.LABEL_NAME, OcamlNodeType.SIMPLE_EXPRESSION, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.LAZY_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(
                OcamlNodeType.LAZY_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN)));
        out.put(
                OcamlNodeType.LET_BINDING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.PARAMETER, OcamlNodeType.POLYMORPHIC_TYPE)));
        out.put(OcamlNodeType.LET_CLASS_EXPRESSION, new ChildInfo(true, false, Set.of(OcamlNodeType.VALUE_DEFINITION)));
        out.put(
                OcamlNodeType.LET_EXCEPTION_EXPRESSION,
                new ChildInfo(true, false, Set.of(OcamlNodeType.EXCEPTION_DEFINITION)));
        out.put(
                OcamlNodeType.LET_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION, OcamlNodeType.VALUE_DEFINITION)));
        out.put(
                OcamlNodeType.LET_MODULE_EXPRESSION,
                new ChildInfo(true, false, Set.of(OcamlNodeType.MODULE_DEFINITION)));
        out.put(OcamlNodeType.LET_OPEN_CLASS_EXPRESSION, new ChildInfo(true, false, Set.of(OcamlNodeType.OPEN_MODULE)));
        out.put(OcamlNodeType.LET_OPEN_CLASS_TYPE, new ChildInfo(true, false, Set.of(OcamlNodeType.OPEN_MODULE)));
        out.put(OcamlNodeType.LET_OPEN_EXPRESSION, new ChildInfo(true, false, Set.of(OcamlNodeType.OPEN_MODULE)));
        out.put(OcamlNodeType.LIST_BINDING_PATTERN, new ChildInfo(false, true, Set.of(OcamlNodeType.BINDING_PATTERN)));
        out.put(OcamlNodeType.LIST_EXPRESSION, new ChildInfo(false, true, Set.of(OcamlNodeType.EXPRESSION)));
        out.put(
                OcamlNodeType.LIST_PATTERN,
                new ChildInfo(false, true, Set.of(OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN)));
        out.put(
                OcamlNodeType.LOCAL_OPEN_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ARRAY_EXPRESSION,
                                OcamlNodeType.LIST_EXPRESSION,
                                OcamlNodeType.MODULE_PATH,
                                OcamlNodeType.OBJECT_COPY_EXPRESSION,
                                OcamlNodeType.PACKAGE_EXPRESSION,
                                OcamlNodeType.RECORD_EXPRESSION,
                                OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                OcamlNodeType.LOCAL_OPEN_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ARRAY_BINDING_PATTERN,
                                OcamlNodeType.BINDING_PATTERN,
                                OcamlNodeType.LIST_BINDING_PATTERN,
                                OcamlNodeType.MODULE_PATH,
                                OcamlNodeType.PATTERN,
                                OcamlNodeType.RECORD_BINDING_PATTERN)));
        out.put(
                OcamlNodeType.LOCAL_OPEN_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.EXTENDED_MODULE_PATH,
                                OcamlNodeType.PACKAGE_TYPE,
                                OcamlNodeType.POLYMORPHIC_VARIANT_TYPE,
                                OcamlNodeType.TYPE)));
        out.put(OcamlNodeType.MATCH_CASE, new ChildInfo(false, false, Set.of(OcamlNodeType.GUARD)));
        out.put(
                OcamlNodeType.MATCH_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.MATCH_CASE,
                                OcamlNodeType.MATCH_OPERATOR,
                                OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                OcamlNodeType.METHOD_DEFINITION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.PARAMETER, OcamlNodeType.POLYMORPHIC_TYPE)));
        out.put(
                OcamlNodeType.METHOD_INVOCATION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.METHOD_NAME, OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(
                OcamlNodeType.METHOD_SPECIFICATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.METHOD_NAME,
                                OcamlNodeType.POLYMORPHIC_TYPE)));
        out.put(
                OcamlNodeType.METHOD_TYPE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.METHOD_NAME, OcamlNodeType.POLYMORPHIC_TYPE)));
        out.put(
                OcamlNodeType.MODULE_BINDING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.MODULE_PARAMETER,
                                OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.MODULE_DEFINITION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.MODULE_BINDING)));
        out.put(OcamlNodeType.MODULE_PARAMETER, new ChildInfo(false, false, Set.of(OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.MODULE_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.MODULE_NAME, OcamlNodeType.MODULE_PATH)));
        out.put(
                OcamlNodeType.MODULE_TYPE_CONSTRAINT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.CONSTRAIN_MODULE,
                                OcamlNodeType.CONSTRAIN_MODULE_TYPE,
                                OcamlNodeType.CONSTRAIN_TYPE,
                                OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.MODULE_TYPE_DEFINITION,
                new ChildInfo(false, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.ITEM_ATTRIBUTE)));
        out.put(OcamlNodeType.MODULE_TYPE_OF, new ChildInfo(true, false, Set.of(OcamlNodeType.MODULE_EXPRESSION)));
        out.put(
                OcamlNodeType.MODULE_TYPE_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.EXTENDED_MODULE_PATH, OcamlNodeType.MODULE_TYPE_NAME)));
        out.put(
                OcamlNodeType.NEW_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.CLASS_PATH)));
        out.put(
                OcamlNodeType.OBJECT_COPY_EXPRESSION,
                new ChildInfo(false, true, Set.of(OcamlNodeType.INSTANCE_VARIABLE_EXPRESSION)));
        out.put(
                OcamlNodeType.OBJECT_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.CLASS_FIELD,
                                OcamlNodeType.FLOATING_ATTRIBUTE,
                                OcamlNodeType.PATTERN,
                                OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.OBJECT_TYPE,
                new ChildInfo(false, true, Set.of(OcamlNodeType.METHOD_TYPE, OcamlNodeType.SIMPLE_TYPE)));
        out.put(
                OcamlNodeType.OPEN_MODULE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.MODULE_EXPRESSION)));
        out.put(
                OcamlNodeType.OR_PATTERN,
                new ChildInfo(true, true, Set.of(OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN)));
        out.put(
                OcamlNodeType.PACKAGE_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.MODULE_EXPRESSION,
                                OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.PACKAGE_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.MODULE_NAME, OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.PACKAGE_TYPE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.PACKED_MODULE,
                new ChildInfo(true, true, Set.of(OcamlNodeType.EXPRESSION, OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.PARAMETER_2,
                new ChildInfo(
                        false,
                        true,
                        Set.of(OcamlNodeType.LABEL_NAME, OcamlNodeType.SEQUENCE_EXPRESSION, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.PARENTHESIZED_CLASS_EXPRESSION,
                new ChildInfo(true, false, Set.of(OcamlNodeType.CLASS_EXPRESSION)));
        out.put(
                OcamlNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                OcamlNodeType.PARENTHESIZED_MODULE_EXPRESSION,
                new ChildInfo(true, false, Set.of(OcamlNodeType.MODULE_EXPRESSION)));
        out.put(OcamlNodeType.PARENTHESIZED_MODULE_TYPE, new ChildInfo(true, false, Set.of(OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.PARENTHESIZED_OPERATOR,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                OcamlNodeType.HASH_OPERATOR,
                                OcamlNodeType.INDEXING_OPERATOR,
                                OcamlNodeType.INFIX_OPERATOR,
                                OcamlNodeType.LET_AND_OPERATOR,
                                OcamlNodeType.LET_OPERATOR,
                                OcamlNodeType.MATCH_OPERATOR,
                                OcamlNodeType.PREFIX_OPERATOR)));
        out.put(
                OcamlNodeType.PARENTHESIZED_PATTERN,
                new ChildInfo(true, false, Set.of(OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN)));
        out.put(OcamlNodeType.PARENTHESIZED_TYPE, new ChildInfo(true, false, Set.of(OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.POLYMORPHIC_TYPE_2,
                new ChildInfo(
                        true,
                        true,
                        Set.of(OcamlNodeType.ABSTRACT_TYPE, OcamlNodeType.TYPE, OcamlNodeType.TYPE_VARIABLE)));
        out.put(
                OcamlNodeType.POLYMORPHIC_VARIANT_PATTERN,
                new ChildInfo(true, false, Set.of(OcamlNodeType.TYPE_CONSTRUCTOR_PATH)));
        out.put(
                OcamlNodeType.POLYMORPHIC_VARIANT_TYPE,
                new ChildInfo(
                        false, true, Set.of(OcamlNodeType.TAG, OcamlNodeType.TAG_SPECIFICATION, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.QUOTED_EXTENSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.QUOTED_STRING_CONTENT)));
        out.put(
                OcamlNodeType.QUOTED_ITEM_EXTENSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.QUOTED_STRING_CONTENT)));
        out.put(OcamlNodeType.QUOTED_STRING, new ChildInfo(false, false, Set.of(OcamlNodeType.QUOTED_STRING_CONTENT)));
        out.put(
                OcamlNodeType.QUOTED_STRING_CONTENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(OcamlNodeType.CONVERSION_SPECIFICATION, OcamlNodeType.PRETTY_PRINTING_INDICATION)));
        out.put(OcamlNodeType.RANGE_PATTERN, new ChildInfo(true, true, Set.of(OcamlNodeType.SIGNED_CONSTANT)));
        out.put(OcamlNodeType.RECORD_BINDING_PATTERN, new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_PATTERN)));
        out.put(OcamlNodeType.RECORD_DECLARATION, new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_DECLARATION)));
        out.put(
                OcamlNodeType.RECORD_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_EXPRESSION, OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(OcamlNodeType.RECORD_PATTERN, new ChildInfo(true, true, Set.of(OcamlNodeType.FIELD_PATTERN)));
        out.put(OcamlNodeType.SEQUENCE_EXPRESSION_2, new ChildInfo(false, false, Set.of(OcamlNodeType.ATTRIBUTE_ID)));
        out.put(
                OcamlNodeType.SET_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                OcamlNodeType.ARRAY_GET_EXPRESSION,
                                OcamlNodeType.BIGARRAY_GET_EXPRESSION,
                                OcamlNodeType.FIELD_GET_EXPRESSION,
                                OcamlNodeType.INSTANCE_VARIABLE_NAME,
                                OcamlNodeType.STRING_GET_EXPRESSION)));
        out.put(OcamlNodeType.SIGNATURE, new ChildInfo(false, true, Set.of(OcamlNodeType.SIGNATURE_ITEM)));
        out.put(OcamlNodeType.STRING, new ChildInfo(false, false, Set.of(OcamlNodeType.STRING_CONTENT)));
        out.put(
                OcamlNodeType.STRING_CONTENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.CONVERSION_SPECIFICATION,
                                OcamlNodeType.ESCAPE_SEQUENCE,
                                OcamlNodeType.PRETTY_PRINTING_INDICATION)));
        out.put(
                OcamlNodeType.STRING_GET_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.INDEXING_OPERATOR_PATH,
                                OcamlNodeType.SEQUENCE_EXPRESSION,
                                OcamlNodeType.SIMPLE_EXPRESSION)));
        out.put(
                OcamlNodeType.STRUCTURE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.EXPRESSION_ITEM,
                                OcamlNodeType.STRUCTURE_ITEM,
                                OcamlNodeType.TOPLEVEL_DIRECTIVE)));
        out.put(OcamlNodeType.TAG_PATTERN, new ChildInfo(true, true, Set.of(OcamlNodeType.PATTERN, OcamlNodeType.TAG)));
        out.put(
                OcamlNodeType.TAG_SPECIFICATION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.TAG, OcamlNodeType.TYPE)));
        out.put(OcamlNodeType.THEN_CLAUSE, new ChildInfo(true, false, Set.of(OcamlNodeType.EXPRESSION)));
        out.put(
                OcamlNodeType.TOPLEVEL_DIRECTIVE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.CONSTANT,
                                OcamlNodeType.DIRECTIVE,
                                OcamlNodeType.MODULE_PATH,
                                OcamlNodeType.VALUE_PATH)));
        out.put(
                OcamlNodeType.TRY_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.MATCH_CASE,
                                OcamlNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                OcamlNodeType.TUPLE_PATTERN,
                new ChildInfo(true, true, Set.of(OcamlNodeType.BINDING_PATTERN, OcamlNodeType.PATTERN)));
        out.put(OcamlNodeType.TUPLE_TYPE_2, new ChildInfo(true, true, Set.of(OcamlNodeType.TUPLE_TYPE)));
        out.put(
                OcamlNodeType.TYPED_CLASS_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.CLASS_EXPRESSION, OcamlNodeType.CLASS_TYPE)));
        out.put(
                OcamlNodeType.TYPED_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.SEQUENCE_EXPRESSION, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.TYPED_LABEL,
                new ChildInfo(true, true, Set.of(OcamlNodeType.LABEL_NAME, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.TYPED_MODULE_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.MODULE_EXPRESSION, OcamlNodeType.MODULE_TYPE)));
        out.put(
                OcamlNodeType.TYPED_PATTERN,
                new ChildInfo(true, true, Set.of(OcamlNodeType.PATTERN, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.TYPE_BINDING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.TYPE,
                                OcamlNodeType.TYPE_CONSTRAINT,
                                OcamlNodeType.TYPE_VARIABLE)));
        out.put(OcamlNodeType.TYPE_CONSTRAINT, new ChildInfo(true, true, Set.of(OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.TYPE_CONSTRUCTOR_PATH,
                new ChildInfo(true, true, Set.of(OcamlNodeType.EXTENDED_MODULE_PATH, OcamlNodeType.TYPE_CONSTRUCTOR)));
        out.put(
                OcamlNodeType.TYPE_DEFINITION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.TYPE_BINDING)));
        out.put(
                OcamlNodeType.TYPE_PARAMETER_CONSTRAINT,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ITEM_ATTRIBUTE, OcamlNodeType.TYPE)));
        out.put(
                OcamlNodeType.VALUE_DEFINITION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.LET_AND_OPERATOR,
                                OcamlNodeType.LET_BINDING,
                                OcamlNodeType.LET_OPERATOR)));
        out.put(
                OcamlNodeType.VALUE_PATH,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.MODULE_PATH,
                                OcamlNodeType.PARENTHESIZED_OPERATOR,
                                OcamlNodeType.VALUE_NAME)));
        out.put(
                OcamlNodeType.VALUE_SPECIFICATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                OcamlNodeType.ATTRIBUTE_ID,
                                OcamlNodeType.ITEM_ATTRIBUTE,
                                OcamlNodeType.PARENTHESIZED_OPERATOR,
                                OcamlNodeType.POLYMORPHIC_TYPE,
                                OcamlNodeType.VALUE_NAME)));
        out.put(
                OcamlNodeType.VARIANT_DECLARATION,
                new ChildInfo(false, true, Set.of(OcamlNodeType.CONSTRUCTOR_DECLARATION)));
        out.put(
                OcamlNodeType.WHILE_EXPRESSION,
                new ChildInfo(true, true, Set.of(OcamlNodeType.ATTRIBUTE_ID, OcamlNodeType.DO_CLAUSE)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<OcamlNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<OcamlNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<OcamlNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<OcamlNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
