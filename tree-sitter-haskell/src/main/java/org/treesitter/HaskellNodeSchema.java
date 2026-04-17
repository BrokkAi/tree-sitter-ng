package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code haskell} from tree-sitter {@code node-types.json}.
 */
public final class HaskellNodeSchema {
    private HaskellNodeSchema() {}

    public static Set<HaskellNodeField> fields(@Nullable HaskellNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<HaskellNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<HaskellNodeType> allowedTypes(@Nullable HaskellNodeType owner, @Nullable HaskellNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<HaskellNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable HaskellNodeType owner, @Nullable HaskellNodeField field) {
        if (owner == null || field == null) return false;
        Map<HaskellNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable HaskellNodeType owner, @Nullable HaskellNodeField field) {
        if (owner == null || field == null) return false;
        Map<HaskellNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<HaskellNodeType> allowedChildTypes(@Nullable HaskellNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable HaskellNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable HaskellNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<HaskellNodeType, Map<HaskellNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<HaskellNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<HaskellNodeType, Map<HaskellNodeField, FieldInfo>> initFields() {
        EnumMap<HaskellNodeType, Map<HaskellNodeField, FieldInfo>> out = new EnumMap<>(HaskellNodeType.class);
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.BINDS, new FieldInfo(false, false, Set.of(HaskellNodeType.LOCAL_BINDS)));
            m.put(HaskellNodeField.MATCH, new FieldInfo(true, true, Set.of(HaskellNodeType.MATCH)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERNS)));
            out.put(HaskellNodeType.ALTERNATIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ALTERNATIVE, new FieldInfo(false, true, Set.of(HaskellNodeType.ALTERNATIVE)));
            out.put(HaskellNodeType.ALTERNATIVES, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.KIND, new FieldInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.ANNOTATED, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ARGUMENT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.EXPLICIT_TYPE,
                                    HaskellNodeType.EXPRESSION,
                                    HaskellNodeType.KIND_APPLICATION,
                                    HaskellNodeType.PATTERN,
                                    HaskellNodeType.TYPE,
                                    HaskellNodeType.TYPE_APPLICATION,
                                    HaskellNodeType.TYPE_BINDER)));
            m.put(
                    HaskellNodeField.CONSTRUCTOR,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRAINT, HaskellNodeType.TYPE)));
            m.put(
                    HaskellNodeField.FUNCTION,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.PATTERN)));
            out.put(HaskellNodeType.APPLY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.FROM,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.STEP,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.TO,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.ARITHMETIC_SEQUENCE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.BIND, new FieldInfo(true, false, Set.of(HaskellNodeType.VARIABLE)));
            m.put(HaskellNodeField.PATTERN, new FieldInfo(true, false, Set.of(HaskellNodeType.PATTERN)));
            out.put(HaskellNodeType.AS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.NAMESPACE, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.NAME, HaskellNodeType.QUALIFIED)));
            out.put(HaskellNodeType.ASSOCIATED_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ARROW, new FieldInfo(false, false, Collections.emptySet()));
            m.put(HaskellNodeField.BINDS, new FieldInfo(false, false, Set.of(HaskellNodeType.LOCAL_BINDS)));
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(HaskellNodeField.IMPLICIT, new FieldInfo(false, false, Set.of(HaskellNodeType.IMPLICIT_VARIABLE)));
            m.put(HaskellNodeField.MATCH, new FieldInfo(false, true, Set.of(HaskellNodeType.MATCH)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PREFIX_ID, HaskellNodeType.VARIABLE)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.BIND, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(HaskellNodeType.CONSTRUCTOR, HaskellNodeType.PREFIX_ID, HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.BINDING_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ALTERNATIVES, new FieldInfo(false, false, Set.of(HaskellNodeType.ALTERNATIVES)));
            out.put(HaskellNodeType.CASE_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ELEMENT,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    HaskellNodeType.ALL_NAMES,
                                    HaskellNodeType.ASSOCIATED_TYPE,
                                    HaskellNodeType.CONSTRUCTOR,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.QUALIFIED,
                                    HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.CHILDREN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONTEXT)));
            m.put(
                    HaskellNodeField.DECLARATIONS,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.CLASS_DECLARATIONS)));
            m.put(HaskellNodeField.FUNDEPS, new FieldInfo(false, false, Set.of(HaskellNodeType.FUNDEPS)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.UNIT)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS)));
            out.put(HaskellNodeType.CLASS_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.DECLARATION, new FieldInfo(false, true, Set.of(HaskellNodeType.CLASS_DECL)));
            out.put(HaskellNodeType.CLASS_DECLARATIONS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ELSE_,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.IF_,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.THEN,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.CONDITIONAL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.BINDS, new FieldInfo(false, false, Set.of(HaskellNodeType.LOCAL_BINDS)));
            m.put(HaskellNodeField.IMPLICIT, new FieldInfo(false, false, Set.of(HaskellNodeType.IMPLICIT_VARIABLE)));
            m.put(HaskellNodeField.MATCH, new FieldInfo(true, true, Set.of(HaskellNodeType.MATCH)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PREFIX_ID, HaskellNodeType.VARIABLE)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.CONSTRUCTOR_SYNONYM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ARROW, new FieldInfo(true, false, Collections.emptySet()));
            m.put(HaskellNodeField.CONSTRAINT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRAINTS)));
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(true, false, Set.of(HaskellNodeType.CONSTRAINT)));
            m.put(HaskellNodeField.TYPE, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.CONTEXT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CONSTRUCTOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.INFIX,
                                    HaskellNodeType.PREFIX,
                                    HaskellNodeType.RECORD_,
                                    HaskellNodeType.SPECIAL)));
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONTEXT)));
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            out.put(HaskellNodeType.DATA_CONSTRUCTOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONSTRUCTOR, new FieldInfo(true, true, Set.of(HaskellNodeType.DATA_CONSTRUCTOR)));
            out.put(HaskellNodeType.DATA_CONSTRUCTORS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.KIND, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.UNIT)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS)));
            out.put(HaskellNodeType.DATA_FAMILY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CONSTRUCTORS,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.DATA_CONSTRUCTORS, HaskellNodeType.GADT_CONSTRUCTORS)));
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONTEXT)));
            m.put(HaskellNodeField.DERIVING, new FieldInfo(false, true, Set.of(HaskellNodeType.DERIVING)));
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            m.put(HaskellNodeField.KIND, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.QUALIFIED,
                                    HaskellNodeType.UNIT)));
            m.put(
                    HaskellNodeField.PATTERNS,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS, HaskellNodeType.TYPE_PATTERNS)));
            out.put(HaskellNodeType.DATA_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.SIGNATURE, new FieldInfo(true, false, Set.of(HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.DEFAULT_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(false, true, Set.of(HaskellNodeType.QUANTIFIED_TYPE, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.DEFAULT_TYPES, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CLASSES, new FieldInfo(true, false, Set.of(HaskellNodeType.CONSTRAINT)));
            m.put(HaskellNodeField.STRATEGY, new FieldInfo(false, false, Set.of(HaskellNodeType.DERIVING_STRATEGY)));
            m.put(HaskellNodeField.VIA, new FieldInfo(false, false, Set.of(HaskellNodeType.VIA)));
            out.put(HaskellNodeType.DERIVING, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONTEXT)));
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.NAME, HaskellNodeType.PREFIX_ID, HaskellNodeType.QUALIFIED)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PATTERNS)));
            m.put(HaskellNodeField.STRATEGY, new FieldInfo(false, false, Set.of(HaskellNodeType.DERIVING_STRATEGY)));
            m.put(HaskellNodeField.VIA, new FieldInfo(false, false, Set.of(HaskellNodeType.VIA)));
            out.put(HaskellNodeType.DERIVING_INSTANCE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.STATEMENT, new FieldInfo(false, true, Set.of(HaskellNodeType.STATEMENT)));
            out.put(HaskellNodeType.DO_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ID, new FieldInfo(true, false, Collections.emptySet()));
            m.put(HaskellNodeField.MODULE, new FieldInfo(true, false, Set.of(HaskellNodeType.MODULE)));
            out.put(HaskellNodeType.DO_MODULE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CONSTRUCTORS,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRUCTOR_SYNONYMS)));
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.NAME, HaskellNodeType.PREFIX_ID, HaskellNodeType.QUALIFIED)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PATTERNS)));
            m.put(HaskellNodeField.SYNONYM, new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERN)));
            out.put(HaskellNodeType.EQUATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.EQUATION, new FieldInfo(false, true, Set.of(HaskellNodeType.EQUATION)));
            out.put(HaskellNodeType.EQUATIONS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.TYPE)));
            out.put(HaskellNodeType.EXPLICIT_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CHILDREN, new FieldInfo(false, false, Set.of(HaskellNodeType.CHILDREN)));
            m.put(HaskellNodeField.NAMESPACE, new FieldInfo(false, false, Set.of(HaskellNodeType.NAMESPACE)));
            m.put(HaskellNodeField.OPERATOR, new FieldInfo(false, false, Set.of(HaskellNodeType.PREFIX_ID)));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.NAME, HaskellNodeType.QUALIFIED)));
            m.put(
                    HaskellNodeField.VARIABLE,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.QUALIFIED, HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.EXPORT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.EXPORT, new FieldInfo(false, true, Set.of(HaskellNodeType.EXPORT)));
            out.put(HaskellNodeType.EXPORTS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.NAME, new FieldInfo(false, true, Set.of(HaskellNodeType.FIELD_NAME)));
            m.put(
                    HaskellNodeField.PARAMETER,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.LAZY_FIELD,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.STRICT_FIELD)));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.LAZY_FIELD,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.STRICT_FIELD)));
            out.put(HaskellNodeType.FIELD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.FIELD, new FieldInfo(false, true, Set.of(HaskellNodeType.FIELD)));
            out.put(HaskellNodeType.FIELDS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.FIELD,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.FIELD_NAME, HaskellNodeType.QUALIFIED)));
            m.put(HaskellNodeField.SUBFIELD, new FieldInfo(true, true, Set.of(HaskellNodeType.FIELD_NAME)));
            out.put(HaskellNodeType.FIELD_PATH, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.FIELD,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FIELD_NAME, HaskellNodeType.QUALIFIED)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE, HaskellNodeType.VIEW_PATTERN)));
            out.put(HaskellNodeType.FIELD_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.FIELD,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.FIELD_NAME, HaskellNodeType.FIELD_PATH, HaskellNodeType.QUALIFIED)));
            out.put(HaskellNodeType.FIELD_UPDATE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ASSOCIATIVITY, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    HaskellNodeField.OPERATOR,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    HaskellNodeType.CONSTRUCTOR_OPERATOR,
                                    HaskellNodeType.INFIX_ID,
                                    HaskellNodeType.OPERATOR)));
            m.put(HaskellNodeField.PRECEDENCE, new FieldInfo(false, false, Set.of(HaskellNodeType.INTEGER)));
            out.put(HaskellNodeType.FIXITY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONSTRAINT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRAINTS)));
            m.put(HaskellNodeField.QUANTIFIER, new FieldInfo(true, false, Collections.emptySet()));
            m.put(HaskellNodeField.TYPE, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.VARIABLES,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_VARIABLES)));
            out.put(HaskellNodeType.FORALL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.QUANTIFIER, new FieldInfo(true, false, Collections.emptySet()));
            m.put(HaskellNodeField.TYPE, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.VARIABLES,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_VARIABLES)));
            out.put(HaskellNodeType.FORALL_REQUIRED, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CALLING_CONVENTION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.CALLING_CONVENTION)));
            m.put(HaskellNodeField.ENTITY, new FieldInfo(false, false, Set.of(HaskellNodeType.ENTITY)));
            m.put(HaskellNodeField.SIGNATURE, new FieldInfo(true, false, Set.of(HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.FOREIGN_EXPORT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CALLING_CONVENTION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.CALLING_CONVENTION)));
            m.put(HaskellNodeField.ENTITY, new FieldInfo(false, false, Set.of(HaskellNodeType.ENTITY)));
            m.put(HaskellNodeField.SAFETY, new FieldInfo(false, false, Set.of(HaskellNodeType.SAFETY)));
            m.put(HaskellNodeField.SIGNATURE, new FieldInfo(true, false, Set.of(HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.FOREIGN_IMPORT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ARROW, new FieldInfo(false, false, Collections.emptySet()));
            m.put(HaskellNodeField.BINDS, new FieldInfo(false, false, Set.of(HaskellNodeType.LOCAL_BINDS)));
            m.put(HaskellNodeField.MATCH, new FieldInfo(false, true, Set.of(HaskellNodeType.MATCH)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PREFIX_ID, HaskellNodeType.VARIABLE)));
            m.put(
                    HaskellNodeField.PARAMETER,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.LAZY_FIELD,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.STRICT_FIELD)));
            m.put(HaskellNodeField.PARENS, new FieldInfo(false, false, Set.of(HaskellNodeType.FUNCTION_HEAD_PARENS)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERNS)));
            m.put(HaskellNodeField.RESULT, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PREFIX_ID, HaskellNodeType.VARIABLE)));
            m.put(HaskellNodeField.PARENS, new FieldInfo(false, false, Set.of(HaskellNodeType.FUNCTION_HEAD_PARENS)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERNS)));
            out.put(HaskellNodeType.FUNCTION_HEAD_PARENS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.DETERMINED, new FieldInfo(true, true, Set.of(HaskellNodeType.VARIABLE)));
            m.put(HaskellNodeField.MATCHED, new FieldInfo(true, true, Set.of(HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.FUNDEP, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.FUNDEP, new FieldInfo(true, true, Set.of(HaskellNodeType.FUNDEP)));
            out.put(HaskellNodeType.FUNDEPS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONTEXT)));
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRUCTOR, HaskellNodeType.PREFIX_ID)));
            m.put(HaskellNodeField.NAMES, new FieldInfo(false, false, Set.of(HaskellNodeType.BINDING_LIST)));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.PREFIX, HaskellNodeType.RECORD_)));
            out.put(HaskellNodeType.GADT_CONSTRUCTOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONSTRUCTOR, new FieldInfo(false, true, Set.of(HaskellNodeType.GADT_CONSTRUCTOR)));
            out.put(HaskellNodeType.GADT_CONSTRUCTORS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ARROW, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.GENERATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CLASSIFIER,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.KEY,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.GROUP, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.GUARD, new FieldInfo(true, true, Set.of(HaskellNodeType.GUARD)));
            out.put(HaskellNodeType.GUARDS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.DECLARATIONS, new FieldInfo(false, false, Set.of(HaskellNodeType.DECLARATIONS)));
            m.put(HaskellNodeField.IMPORTS, new FieldInfo(false, false, Set.of(HaskellNodeType.IMPORTS)));
            out.put(HaskellNodeType.HASKELL, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.EXPORTS, new FieldInfo(false, false, Set.of(HaskellNodeType.EXPORTS)));
            m.put(HaskellNodeField.MODULE, new FieldInfo(true, false, Set.of(HaskellNodeType.MODULE)));
            out.put(HaskellNodeType.HEADER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.NAME, new FieldInfo(true, false, Set.of(HaskellNodeType.IMPLICIT_VARIABLE)));
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.IMPLICIT_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.IMPORT_, new FieldInfo(true, true, Set.of(HaskellNodeType.IMPORT_)));
            out.put(HaskellNodeType.IMPORTS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ALIAS, new FieldInfo(false, false, Set.of(HaskellNodeType.MODULE)));
            m.put(HaskellNodeField.MODULE, new FieldInfo(true, false, Set.of(HaskellNodeType.MODULE)));
            m.put(HaskellNodeField.NAMES, new FieldInfo(false, false, Set.of(HaskellNodeType.IMPORT_LIST)));
            m.put(HaskellNodeField.PACKAGE_, new FieldInfo(false, false, Set.of(HaskellNodeType.IMPORT_PACKAGE)));
            out.put(HaskellNodeType.IMPORT_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.NAME, new FieldInfo(false, true, Set.of(HaskellNodeType.IMPORT_NAME)));
            out.put(HaskellNodeType.IMPORT_LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CHILDREN, new FieldInfo(false, false, Set.of(HaskellNodeType.CHILDREN)));
            m.put(HaskellNodeField.NAMESPACE, new FieldInfo(false, false, Set.of(HaskellNodeType.NAMESPACE)));
            m.put(HaskellNodeField.OPERATOR, new FieldInfo(false, false, Set.of(HaskellNodeType.PREFIX_ID)));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.NAME, HaskellNodeType.QUALIFIED)));
            m.put(
                    HaskellNodeField.VARIABLE,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.QUALIFIED, HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.IMPORT_NAME, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.LEFT_OPERAND,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.EXPRESSION,
                                    HaskellNodeType.LAZY_FIELD,
                                    HaskellNodeType.PATTERN,
                                    HaskellNodeType.STRICT_FIELD,
                                    HaskellNodeType.TYPE,
                                    HaskellNodeType.TYPE_PARAM)));
            m.put(
                    HaskellNodeField.OPERATOR,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    HaskellNodeType.CONSTRUCTOR_OPERATOR,
                                    HaskellNodeType.INFIX_ID,
                                    HaskellNodeType.OPERATOR,
                                    HaskellNodeType.PROMOTED,
                                    HaskellNodeType.QUALIFIED)));
            m.put(
                    HaskellNodeField.RIGHT_OPERAND,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.EXPRESSION,
                                    HaskellNodeType.LAZY_FIELD,
                                    HaskellNodeType.PATTERN,
                                    HaskellNodeType.STRICT_FIELD,
                                    HaskellNodeType.TYPE,
                                    HaskellNodeType.TYPE_PARAM)));
            out.put(HaskellNodeType.INFIX, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONTEXT)));
            m.put(
                    HaskellNodeField.DECLARATIONS,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.INSTANCE_DECLARATIONS)));
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.NAME, HaskellNodeType.PREFIX_ID, HaskellNodeType.QUALIFIED)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PATTERNS)));
            out.put(HaskellNodeType.INSTANCE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.DECLARATION, new FieldInfo(false, true, Set.of(HaskellNodeType.INSTANCE_DECL)));
            out.put(HaskellNodeType.INSTANCE_DECLARATIONS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.BIND, new FieldInfo(true, false, Set.of(HaskellNodeType.TYPE_PARAM)));
            out.put(HaskellNodeType.INVISIBLE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.PATTERN, new FieldInfo(true, false, Set.of(HaskellNodeType.PATTERN)));
            out.put(HaskellNodeType.IRREFUTABLE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.TYPE)));
            out.put(HaskellNodeType.KIND_APPLICATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.KIND, new FieldInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.UNIT)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS)));
            out.put(HaskellNodeType.KIND_SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(true, false, Set.of(HaskellNodeType.PATTERNS)));
            out.put(HaskellNodeType.LAMBDA, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ALTERNATIVES, new FieldInfo(false, false, Set.of(HaskellNodeType.ALTERNATIVES)));
            out.put(HaskellNodeType.LAMBDA_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ALTERNATIVES, new FieldInfo(false, false, Set.of(HaskellNodeType.ALTERNATIVES)));
            out.put(HaskellNodeType.LAMBDA_CASES, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.TYPE)));
            out.put(HaskellNodeType.LAZY_FIELD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.LEFT_OPERAND, new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION)));
            m.put(
                    HaskellNodeField.OPERATOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.CONSTRUCTOR_OPERATOR,
                                    HaskellNodeType.INFIX_ID,
                                    HaskellNodeType.OPERATOR,
                                    HaskellNodeType.QUALIFIED)));
            out.put(HaskellNodeType.LEFT_SECTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.BINDS, new FieldInfo(false, false, Set.of(HaskellNodeType.LOCAL_BINDS)));
            out.put(HaskellNodeType.LET, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.BINDS, new FieldInfo(false, false, Set.of(HaskellNodeType.LOCAL_BINDS)));
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.LET_IN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ARROW, new FieldInfo(true, false, Collections.emptySet()));
            m.put(HaskellNodeField.MULTIPLICITY, new FieldInfo(false, false, Set.of(HaskellNodeType.MODIFIER)));
            m.put(
                    HaskellNodeField.PARAMETER,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.LAZY_FIELD,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.STRICT_FIELD)));
            m.put(HaskellNodeField.RESULT, new FieldInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.LINEAR_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ELEMENT,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    HaskellNodeType.EXPRESSION,
                                    HaskellNodeType.PATTERN,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.SIGNATURE,
                                    HaskellNodeType.VIEW_PATTERN)));
            out.put(HaskellNodeType.LIST, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(HaskellNodeField.QUALIFIERS, new FieldInfo(true, true, Set.of(HaskellNodeType.QUALIFIERS)));
            out.put(HaskellNodeType.LIST_COMPREHENSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.DECL,
                    new FieldInfo(false, true, Set.of(HaskellNodeType.DECL, HaskellNodeType.FIXITY)));
            out.put(HaskellNodeType.LOCAL_BINDS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(HaskellNodeField.GUARDS, new FieldInfo(false, false, Set.of(HaskellNodeType.GUARDS)));
            out.put(HaskellNodeType.MATCH, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.MODULE, new FieldInfo(true, false, Set.of(HaskellNodeType.MODULE)));
            out.put(HaskellNodeType.MODULE_EXPORT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.MATCH, new FieldInfo(false, true, Set.of(HaskellNodeType.MATCH)));
            out.put(HaskellNodeType.MULTI_WAY_IF, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.EXPRESSION, new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION)));
            m.put(HaskellNodeField.MINUS, new FieldInfo(false, false, Collections.emptySet()));
            m.put(
                    HaskellNodeField.NUMBER,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FLOAT_, HaskellNodeType.INTEGER)));
            out.put(HaskellNodeType.NEGATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CONSTRUCTOR,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.NEWTYPE_CONSTRUCTOR)));
            m.put(
                    HaskellNodeField.CONSTRUCTORS,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.GADT_CONSTRUCTORS)));
            m.put(HaskellNodeField.CONTEXT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONTEXT)));
            m.put(HaskellNodeField.DERIVING, new FieldInfo(false, true, Set.of(HaskellNodeType.DERIVING)));
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            m.put(HaskellNodeField.KIND, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.QUALIFIED,
                                    HaskellNodeType.UNIT)));
            m.put(
                    HaskellNodeField.PATTERNS,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS, HaskellNodeType.TYPE_PATTERNS)));
            out.put(HaskellNodeType.NEWTYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.FIELD,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.FIELD, HaskellNodeType.RECORD_)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.CONSTRUCTOR, HaskellNodeType.PREFIX_ID)));
            out.put(HaskellNodeType.NEWTYPE_CONSTRUCTOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(HaskellNodeField.KIND, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.QUALIFIED,
                                    HaskellNodeType.UNIT)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE, HaskellNodeType.VIEW_PATTERN)));
            m.put(
                    HaskellNodeField.PATTERNS,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS, HaskellNodeType.TYPE_PATTERNS)));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.PARENS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ARROW, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.PATTERN_GUARD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.FIELD,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(HaskellNodeType.LAZY_FIELD, HaskellNodeType.STRICT_FIELD, HaskellNodeType.TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRUCTOR, HaskellNodeType.PREFIX_ID)));
            m.put(HaskellNodeField.TYPE, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.PREFIX, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.EXPRESSION, new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION)));
            m.put(HaskellNodeField.FIELD, new FieldInfo(true, false, Set.of(HaskellNodeType.FIELD_NAME)));
            out.put(HaskellNodeType.PROJECTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.FIELD, new FieldInfo(true, true, Set.of(HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.PROJECTION_SELECTOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ID,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.CONSTRUCTOR,
                                    HaskellNodeType.CONSTRUCTOR_OPERATOR,
                                    HaskellNodeType.FIELD_NAME,
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.OPERATOR,
                                    HaskellNodeType.VARIABLE)));
            m.put(HaskellNodeField.MODULE, new FieldInfo(true, false, Set.of(HaskellNodeType.MODULE)));
            out.put(HaskellNodeType.QUALIFIED, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.QUALIFIER, new FieldInfo(true, true, Set.of(HaskellNodeType.QUALIFIER)));
            out.put(HaskellNodeType.QUALIFIERS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.BODY, new FieldInfo(false, false, Set.of(HaskellNodeType.QUASIQUOTE_BODY)));
            m.put(HaskellNodeField.QUOTER, new FieldInfo(true, false, Set.of(HaskellNodeType.QUOTER)));
            out.put(HaskellNodeType.QUASIQUOTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.QUOTER, new FieldInfo(false, false, Collections.emptySet()));
            out.put(HaskellNodeType.QUOTE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.DECLARATION, new FieldInfo(false, true, Set.of(HaskellNodeType.DECLARATION)));
            out.put(HaskellNodeType.QUOTED_DECLS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.STATEMENT, new FieldInfo(false, true, Set.of(HaskellNodeType.STATEMENT)));
            out.put(HaskellNodeType.REC, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ARROW, new FieldInfo(false, true, Collections.emptySet()));
            m.put(HaskellNodeField.CONSTRUCTOR, new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERN)));
            m.put(HaskellNodeField.EXPRESSION, new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION)));
            m.put(
                    HaskellNodeField.FIELD,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    HaskellNodeType.FIELD,
                                    HaskellNodeType.FIELD_PATTERN,
                                    HaskellNodeType.FIELD_UPDATE)));
            m.put(HaskellNodeField.FIELDS, new FieldInfo(false, false, Set.of(HaskellNodeType.FIELDS)));
            m.put(HaskellNodeField.NAME, new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRUCTOR)));
            m.put(HaskellNodeField.TYPE, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.RECORD_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.RIGHT_OPERAND, new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION)));
            out.put(HaskellNodeType.RIGHT_SECTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.ROLE, new FieldInfo(true, true, Set.of(HaskellNodeType.TYPE_ROLE)));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(HaskellNodeType.NAME, HaskellNodeType.PREFIX_ID, HaskellNodeType.QUALIFIED)));
            out.put(HaskellNodeType.ROLE_ANNOTATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.CONSTRAINT, new FieldInfo(false, false, Set.of(HaskellNodeType.CONSTRAINTS)));
            m.put(HaskellNodeField.EXPRESSION, new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION)));
            m.put(HaskellNodeField.KIND, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.PREFIX_ID, HaskellNodeType.VARIABLE)));
            m.put(HaskellNodeField.NAMES, new FieldInfo(false, false, Set.of(HaskellNodeType.BINDING_LIST)));
            m.put(HaskellNodeField.PATTERN, new FieldInfo(false, false, Set.of(HaskellNodeType.PATTERN)));
            m.put(
                    HaskellNodeField.SYNONYM,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.BINDING_LIST,
                                    HaskellNodeType.CONSTRUCTOR,
                                    HaskellNodeType.PREFIX_ID)));
            m.put(HaskellNodeField.TYPE, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.SIGNATURE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    HaskellNodeType.CONSTRUCTOR,
                                    HaskellNodeType.IMPLICIT_VARIABLE,
                                    HaskellNodeType.LABEL,
                                    HaskellNodeType.LITERAL,
                                    HaskellNodeType.PARENS,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.QUALIFIED,
                                    HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.SPLICE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.PATTERN, new FieldInfo(true, false, Set.of(HaskellNodeType.PATTERN)));
            out.put(HaskellNodeType.STRICT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.TYPE)));
            out.put(HaskellNodeType.STRICT_FIELD, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.CONSTRUCTOR,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.QUALIFIED,
                                    HaskellNodeType.VARIABLE)));
            m.put(HaskellNodeField.TYPE, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE)));
            out.put(HaskellNodeType.TH_QUOTED_NAME, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.KEY,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.TRANSFORMATION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.TRANSFORM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ELEMENT,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    HaskellNodeType.EXPRESSION,
                                    HaskellNodeType.PATTERN,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.SIGNATURE,
                                    HaskellNodeType.VIEW_PATTERN)));
            out.put(HaskellNodeType.TUPLE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.TYPE)));
            out.put(HaskellNodeType.TYPE_APPLICATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.TYPE)));
            out.put(HaskellNodeType.TYPE_BINDER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.CLOSED_FAMILY,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.ABSTRACT_FAMILY, HaskellNodeType.EQUATIONS)));
            m.put(HaskellNodeField.KIND, new FieldInfo(false, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.UNIT)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS)));
            out.put(HaskellNodeType.TYPE_FAMILY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.DETERMINED, new FieldInfo(true, true, Set.of(HaskellNodeType.VARIABLE)));
            m.put(HaskellNodeField.RESULT, new FieldInfo(true, false, Set.of(HaskellNodeType.VARIABLE)));
            out.put(HaskellNodeType.TYPE_FAMILY_INJECTIVITY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.RESULT, new FieldInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.TYPE_FAMILY_RESULT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.FORALL,
                    new FieldInfo(false, false, Set.of(HaskellNodeType.FORALL, HaskellNodeType.FORALL_REQUIRED)));
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(HaskellNodeType.NAME, HaskellNodeType.PREFIX_ID, HaskellNodeType.QUALIFIED)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PATTERNS)));
            out.put(HaskellNodeType.TYPE_INSTANCE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    HaskellNodeType.NAME,
                                    HaskellNodeType.PREFIX_ID,
                                    HaskellNodeType.PREFIX_LIST,
                                    HaskellNodeType.UNIT)));
            m.put(HaskellNodeField.PATTERNS, new FieldInfo(false, false, Set.of(HaskellNodeType.TYPE_PARAMS)));
            m.put(
                    HaskellNodeField.TYPE,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE, HaskellNodeType.SIGNATURE)));
            out.put(HaskellNodeType.TYPE_SYNOMYM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ELEMENT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    HaskellNodeType.EXPRESSION,
                                    HaskellNodeType.PATTERN,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.SIGNATURE,
                                    HaskellNodeType.VIEW_PATTERN)));
            out.put(HaskellNodeType.UNBOXED_SUM, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.ELEMENT,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    HaskellNodeType.EXPRESSION,
                                    HaskellNodeType.PATTERN,
                                    HaskellNodeType.QUANTIFIED_TYPE,
                                    HaskellNodeType.SIGNATURE,
                                    HaskellNodeType.VIEW_PATTERN)));
            out.put(HaskellNodeType.UNBOXED_TUPLE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(HaskellNodeField.TYPE, new FieldInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE)));
            out.put(HaskellNodeType.VIA, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<HaskellNodeField, FieldInfo> m = new EnumMap<>(HaskellNodeField.class);
            m.put(
                    HaskellNodeField.EXPRESSION,
                    new FieldInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
            m.put(
                    HaskellNodeField.PATTERN,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE, HaskellNodeType.VIEW_PATTERN)));
            out.put(HaskellNodeType.VIEW_PATTERN, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<HaskellNodeType, ChildInfo> initChildren() {
        EnumMap<HaskellNodeType, ChildInfo> out = new EnumMap<>(HaskellNodeType.class);
        out.put(HaskellNodeType.ANNOTATED, new ChildInfo(true, false, Set.of(HaskellNodeType.TYPE_PARAM)));
        out.put(
                HaskellNodeType.BOOLEAN_,
                new ChildInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
        out.put(
                HaskellNodeType.CASE_,
                new ChildInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
        out.put(
                HaskellNodeType.CLASS_,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        out.put(
                HaskellNodeType.CONSTRUCTOR_SYNONYMS,
                new ChildInfo(false, true, Set.of(HaskellNodeType.CONSTRUCTOR_SYNONYM)));
        out.put(
                HaskellNodeType.DATA_FAMILY,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        out.put(
                HaskellNodeType.DATA_INSTANCE,
                new ChildInfo(true, false, Set.of(HaskellNodeType.DATA_TYPE, HaskellNodeType.NEWTYPE)));
        out.put(
                HaskellNodeType.DATA_TYPE,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        out.put(
                HaskellNodeType.DECLARATIONS,
                new ChildInfo(true, true, Set.of(HaskellNodeType.DECLARATION, HaskellNodeType.IMPORT_)));
        out.put(
                HaskellNodeType.DERIVING_INSTANCE,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        out.put(HaskellNodeType.DO_, new ChildInfo(false, false, Set.of(HaskellNodeType.DO_MODULE)));
        out.put(HaskellNodeType.ENTITY, new ChildInfo(true, false, Set.of(HaskellNodeType.STRING)));
        out.put(
                HaskellNodeType.EQUATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS, HaskellNodeType.QUANTIFIED_TYPE)));
        out.put(
                HaskellNodeType.EXP,
                new ChildInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
        out.put(HaskellNodeType.EXPORTS, new ChildInfo(false, true, Set.of(HaskellNodeType.MODULE_EXPORT)));
        out.put(HaskellNodeType.FIELD, new ChildInfo(false, false, Set.of(HaskellNodeType.TYPE)));
        out.put(HaskellNodeType.FIELD_NAME, new ChildInfo(true, false, Set.of(HaskellNodeType.VARIABLE)));
        out.put(HaskellNodeType.FIELD_PATTERN, new ChildInfo(false, false, Set.of(HaskellNodeType.WILDCARD)));
        out.put(HaskellNodeType.FIELD_UPDATE, new ChildInfo(false, false, Set.of(HaskellNodeType.WILDCARD)));
        out.put(HaskellNodeType.FUNCTION, new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX)));
        out.put(HaskellNodeType.FUNCTION_HEAD_PARENS, new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX)));
        out.put(HaskellNodeType.HASKELL, new ChildInfo(false, false, Set.of(HaskellNodeType.HEADER)));
        out.put(
                HaskellNodeType.INFERRED,
                new ChildInfo(true, false, Set.of(HaskellNodeType.ANNOTATED, HaskellNodeType.TYPE_PARAM)));
        out.put(
                HaskellNodeType.INFIX_ID,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                HaskellNodeType.CONSTRUCTOR,
                                HaskellNodeType.NAME,
                                HaskellNodeType.QUALIFIED,
                                HaskellNodeType.VARIABLE)));
        out.put(
                HaskellNodeType.INSTANCE,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        out.put(
                HaskellNodeType.KIND_SIGNATURE,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        out.put(
                HaskellNodeType.LITERAL,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                HaskellNodeType.CHAR_,
                                HaskellNodeType.FLOAT_,
                                HaskellNodeType.INTEGER,
                                HaskellNodeType.STRING)));
        out.put(HaskellNodeType.MODIFIER, new ChildInfo(true, false, Set.of(HaskellNodeType.TYPE)));
        out.put(HaskellNodeType.MODULE, new ChildInfo(true, true, Set.of(HaskellNodeType.MODULE_ID)));
        out.put(
                HaskellNodeType.NEWTYPE,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        out.put(
                HaskellNodeType.PARENS,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                HaskellNodeType.ANNOTATED,
                                HaskellNodeType.CONSTRAINTS,
                                HaskellNodeType.INFIX,
                                HaskellNodeType.TYPE_PARAM)));
        out.put(
                HaskellNodeType.PATTERNS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(HaskellNodeType.EXPLICIT_TYPE, HaskellNodeType.PATTERN, HaskellNodeType.TYPE_BINDER)));
        out.put(
                HaskellNodeType.PATTERN_SYNONYM,
                new ChildInfo(true, false, Set.of(HaskellNodeType.EQUATION, HaskellNodeType.SIGNATURE)));
        out.put(
                HaskellNodeType.PREFIX_ID,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                HaskellNodeType.CONSTRUCTOR_OPERATOR,
                                HaskellNodeType.OPERATOR,
                                HaskellNodeType.QUALIFIED)));
        out.put(
                HaskellNodeType.PROMOTED,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                HaskellNodeType.CONSTRUCTOR,
                                HaskellNodeType.CONSTRUCTOR_OPERATOR,
                                HaskellNodeType.EMPTY_LIST,
                                HaskellNodeType.INFIX_ID,
                                HaskellNodeType.LIST,
                                HaskellNodeType.OPERATOR,
                                HaskellNodeType.PREFIX_ID,
                                HaskellNodeType.PREFIX_TUPLE,
                                HaskellNodeType.QUALIFIED,
                                HaskellNodeType.TUPLE,
                                HaskellNodeType.UNIT)));
        out.put(
                HaskellNodeType.QUANTIFIED_VARIABLES,
                new ChildInfo(true, true, Set.of(HaskellNodeType.INFERRED, HaskellNodeType.TYPE_PARAM)));
        out.put(
                HaskellNodeType.QUOTE,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                HaskellNodeType.QUOTED_DECLS,
                                HaskellNodeType.QUOTED_EXPRESSION,
                                HaskellNodeType.QUOTED_PATTERN,
                                HaskellNodeType.QUOTED_TYPE)));
        out.put(
                HaskellNodeType.QUOTED_EXPRESSION,
                new ChildInfo(true, false, Set.of(HaskellNodeType.EXPRESSION, HaskellNodeType.SIGNATURE)));
        out.put(
                HaskellNodeType.QUOTED_PATTERN,
                new ChildInfo(true, false, Set.of(HaskellNodeType.PATTERN, HaskellNodeType.SIGNATURE)));
        out.put(
                HaskellNodeType.QUOTED_TYPE,
                new ChildInfo(true, false, Set.of(HaskellNodeType.QUANTIFIED_TYPE, HaskellNodeType.SIGNATURE)));
        out.put(
                HaskellNodeType.QUOTER,
                new ChildInfo(true, false, Set.of(HaskellNodeType.QUALIFIED, HaskellNodeType.VARIABLE)));
        out.put(
                HaskellNodeType.RIGHT_SECTION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                HaskellNodeType.CONSTRUCTOR_OPERATOR,
                                HaskellNodeType.INFIX_ID,
                                HaskellNodeType.OPERATOR,
                                HaskellNodeType.QUALIFIED)));
        out.put(
                HaskellNodeType.SPECIAL,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                HaskellNodeType.EMPTY_LIST,
                                HaskellNodeType.TUPLE,
                                HaskellNodeType.UNBOXED_SUM,
                                HaskellNodeType.UNBOXED_TUPLE,
                                HaskellNodeType.UNBOXED_UNIT,
                                HaskellNodeType.UNIT)));
        out.put(HaskellNodeType.TOP_SPLICE, new ChildInfo(true, false, Set.of(HaskellNodeType.EXPRESSION)));
        out.put(HaskellNodeType.TUPLE, new ChildInfo(false, true, Set.of(HaskellNodeType.CONSTRAINTS)));
        out.put(HaskellNodeType.TYPED_QUOTE, new ChildInfo(false, false, Set.of(HaskellNodeType.QUOTED_EXPRESSION)));
        out.put(
                HaskellNodeType.TYPE_FAMILY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                HaskellNodeType.INFIX,
                                HaskellNodeType.PARENS,
                                HaskellNodeType.TYPE_FAMILY_INJECTIVITY,
                                HaskellNodeType.TYPE_FAMILY_RESULT)));
        out.put(
                HaskellNodeType.TYPE_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS, HaskellNodeType.QUANTIFIED_TYPE)));
        out.put(HaskellNodeType.TYPE_PARAMS, new ChildInfo(true, true, Set.of(HaskellNodeType.TYPE_PARAM)));
        out.put(
                HaskellNodeType.TYPE_PATTERNS,
                new ChildInfo(true, true, Set.of(HaskellNodeType.KIND_APPLICATION, HaskellNodeType.TYPE)));
        out.put(
                HaskellNodeType.TYPE_SYNOMYM,
                new ChildInfo(false, false, Set.of(HaskellNodeType.INFIX, HaskellNodeType.PARENS)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<HaskellNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<HaskellNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<HaskellNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<HaskellNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
