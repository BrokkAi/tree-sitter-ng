package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code agda} from tree-sitter {@code node-types.json}.
 */
public final class AgdaNodeSchema {
    private AgdaNodeSchema() {}

    public static Set<AgdaNodeField> fields(@Nullable AgdaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<AgdaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<AgdaNodeType> allowedTypes(@Nullable AgdaNodeType owner, @Nullable AgdaNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<AgdaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable AgdaNodeType owner, @Nullable AgdaNodeField field) {
        if (owner == null || field == null) return false;
        Map<AgdaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable AgdaNodeType owner, @Nullable AgdaNodeField field) {
        if (owner == null || field == null) return false;
        Map<AgdaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<AgdaNodeType> allowedChildTypes(@Nullable AgdaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable AgdaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable AgdaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<AgdaNodeType, Map<AgdaNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<AgdaNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<AgdaNodeType, Map<AgdaNodeField, FieldInfo>> initFields() {
        EnumMap<AgdaNodeType, Map<AgdaNodeField, FieldInfo>> out = new EnumMap<>(AgdaNodeType.class);
        return out;
    }

    private static EnumMap<AgdaNodeType, ChildInfo> initChildren() {
        EnumMap<AgdaNodeType, ChildInfo> out = new EnumMap<>(AgdaNodeType.class);
        out.put(
                AgdaNodeType.ABSTRACT_,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.ATOM,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.ID,
                                AgdaNodeType.LITERAL,
                                AgdaNodeType.PROPN,
                                AgdaNodeType.QID,
                                AgdaNodeType.RECORD_ASSIGNMENTS,
                                AgdaNodeType.SETN)));
        out.put(
                AgdaNodeType.ATTRIBUTE,
                new ChildInfo(true, false, Set.of(AgdaNodeType.EXPR, AgdaNodeType.LITERAL, AgdaNodeType.QID)));
        out.put(AgdaNodeType.ATTRIBUTES, new ChildInfo(true, true, Set.of(AgdaNodeType.ATTRIBUTE)));
        out.put(
                AgdaNodeType.DATA,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_NAME,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNQUOTE_DECL,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(
                AgdaNodeType.DATA_SIGNATURE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.DATA_NAME,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(AgdaNodeType.DO_, new ChildInfo(true, true, Set.of(AgdaNodeType.DO_WHERE, AgdaNodeType.STMT)));
        out.put(
                AgdaNodeType.DO_WHERE,
                new ChildInfo(false, true, Set.of(AgdaNodeType.LAMBDA_CLAUSE, AgdaNodeType.LAMBDA_CLAUSE_ABSURD)));
        out.put(
                AgdaNodeType.EXPR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.ATTRIBUTES,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.ID,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET,
                                AgdaNodeType.TYPED_BINDING)));
        out.put(AgdaNodeType.FIELDS, new ChildInfo(false, true, Set.of(AgdaNodeType.SIGNATURE)));
        out.put(
                AgdaNodeType.FIELD_ASSIGNMENT,
                new ChildInfo(true, true, Set.of(AgdaNodeType.EXPR, AgdaNodeType.FIELD_NAME)));
        out.put(
                AgdaNodeType.FORALL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(AgdaNodeType.EXPR, AgdaNodeType.TYPED_BINDING, AgdaNodeType.UNTYPED_BINDING)));
        out.put(
                AgdaNodeType.FUNCTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(AgdaNodeType.ATTRIBUTES, AgdaNodeType.LHS, AgdaNodeType.RHS, AgdaNodeType.WHERE)));
        out.put(
                AgdaNodeType.FUNCTION_NAME,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.ID,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET)));
        out.put(AgdaNodeType.GENERALIZE, new ChildInfo(false, true, Set.of(AgdaNodeType.SIGNATURE)));
        out.put(AgdaNodeType.HOLE_NAME, new ChildInfo(true, true, Set.of(AgdaNodeType.BID, AgdaNodeType.ID)));
        out.put(AgdaNodeType.HOLE_NAMES, new ChildInfo(true, true, Set.of(AgdaNodeType.HOLE_NAME)));
        out.put(AgdaNodeType.IMPORT_, new ChildInfo(true, false, Set.of(AgdaNodeType.MODULE_NAME)));
        out.put(
                AgdaNodeType.IMPORT_DIRECTIVE,
                new ChildInfo(false, true, Set.of(AgdaNodeType.ID, AgdaNodeType.RENAMING)));
        out.put(AgdaNodeType.INFIX, new ChildInfo(true, true, Set.of(AgdaNodeType.BID, AgdaNodeType.INTEGER)));
        out.put(
                AgdaNodeType.INSTANCE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.LAMBDA,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.EXPR,
                                AgdaNodeType.LAMBDA_CLAUSE,
                                AgdaNodeType.LAMBDA_CLAUSE_ABSURD,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(
                AgdaNodeType.LAMBDA_CLAUSE,
                new ChildInfo(true, true, Set.of(AgdaNodeType.ATOM, AgdaNodeType.CATCHALL_PRAGMA, AgdaNodeType.EXPR)));
        out.put(
                AgdaNodeType.LAMBDA_CLAUSE_ABSURD,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.CATCHALL_PRAGMA,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.ID,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET)));
        out.put(
                AgdaNodeType.LET,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.LHS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.FUNCTION_NAME,
                                AgdaNodeType.ID,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET,
                                AgdaNodeType.REWRITE_EQUATIONS,
                                AgdaNodeType.WITH_EXPRESSIONS)));
        out.put(
                AgdaNodeType.MACRO,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.MODULE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MODULE_NAME,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNQUOTE_DECL,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(
                AgdaNodeType.MODULE_APPLICATION,
                new ChildInfo(true, true, Set.of(AgdaNodeType.ATOM, AgdaNodeType.MODULE_NAME)));
        out.put(
                AgdaNodeType.MODULE_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(AgdaNodeType.ATOM, AgdaNodeType.IMPORT_DIRECTIVE, AgdaNodeType.MODULE_NAME)));
        out.put(
                AgdaNodeType.MODULE_MACRO,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.IMPORT_DIRECTIVE,
                                AgdaNodeType.MODULE_APPLICATION,
                                AgdaNodeType.MODULE_NAME,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(AgdaNodeType.MODULE_NAME, new ChildInfo(false, false, Set.of(AgdaNodeType.QID)));
        out.put(
                AgdaNodeType.MUTUAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.OPEN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.IMPORT_DIRECTIVE,
                                AgdaNodeType.MODULE_NAME)));
        out.put(
                AgdaNodeType.PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.EXPR,
                                AgdaNodeType.ID,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(
                AgdaNodeType.POSTULATE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(AgdaNodeType.PRIMITIVE, new ChildInfo(false, true, Set.of(AgdaNodeType.TYPE_SIGNATURE)));
        out.put(
                AgdaNodeType.PRIVATE_,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.RECORD_,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.EXPR,
                                AgdaNodeType.RECORD_DECLARATIONS_BLOCK,
                                AgdaNodeType.RECORD_NAME,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(
                AgdaNodeType.RECORD_ASSIGNMENTS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FIELD_ASSIGNMENT,
                                AgdaNodeType.ID,
                                AgdaNodeType.LITERAL,
                                AgdaNodeType.MODULE_ASSIGNMENT,
                                AgdaNodeType.PROPN,
                                AgdaNodeType.QID,
                                AgdaNodeType.RECORD_ASSIGNMENTS,
                                AgdaNodeType.SETN)));
        out.put(AgdaNodeType.RECORD_CONSTRUCTOR, new ChildInfo(true, false, Set.of(AgdaNodeType.ID)));
        out.put(
                AgdaNodeType.RECORD_CONSTRUCTOR_INSTANCE,
                new ChildInfo(true, true, Set.of(AgdaNodeType.RECORD_CONSTRUCTOR)));
        out.put(
                AgdaNodeType.RECORD_DECLARATIONS_BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_CONSTRUCTOR,
                                AgdaNodeType.RECORD_CONSTRUCTOR_INSTANCE,
                                AgdaNodeType.RECORD_ETA,
                                AgdaNodeType.RECORD_INDUCTION,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.RECORD_NAME,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.ID,
                                AgdaNodeType.LITERAL,
                                AgdaNodeType.PROPN,
                                AgdaNodeType.QID,
                                AgdaNodeType.RECORD_ASSIGNMENTS,
                                AgdaNodeType.SETN)));
        out.put(
                AgdaNodeType.RECORD_SIGNATURE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.EXPR,
                                AgdaNodeType.RECORD_NAME,
                                AgdaNodeType.TYPED_BINDING,
                                AgdaNodeType.UNTYPED_BINDING)));
        out.put(AgdaNodeType.RENAMING, new ChildInfo(true, true, Set.of(AgdaNodeType.ID)));
        out.put(
                AgdaNodeType.REWRITE_EQUATIONS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.ID,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET)));
        out.put(AgdaNodeType.RHS, new ChildInfo(true, false, Set.of(AgdaNodeType.EXPR)));
        out.put(AgdaNodeType.SETN, new ChildInfo(false, false, Set.of(AgdaNodeType.ATOM)));
        out.put(
                AgdaNodeType.SIGNATURE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                AgdaNodeType.ATTRIBUTE,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FIELD_NAME,
                                AgdaNodeType.SIGNATURE)));
        out.put(
                AgdaNodeType.SOURCE_FILE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.STMT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.ATTRIBUTES,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.ID,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET,
                                AgdaNodeType.TYPED_BINDING)));
        out.put(AgdaNodeType.SYNTAX, new ChildInfo(true, true, Set.of(AgdaNodeType.HOLE_NAMES, AgdaNodeType.ID)));
        out.put(
                AgdaNodeType.TYPED_BINDING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.ATOM,
                                AgdaNodeType.ATTRIBUTES,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.ID,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.QID,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(
                AgdaNodeType.TYPE_SIGNATURE,
                new ChildInfo(true, true, Set.of(AgdaNodeType.EXPR, AgdaNodeType.FIELD_NAME)));
        out.put(AgdaNodeType.UNQUOTE_DECL, new ChildInfo(true, true, Set.of(AgdaNodeType.EXPR, AgdaNodeType.ID)));
        out.put(
                AgdaNodeType.UNTYPED_BINDING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ATOM,
                                AgdaNodeType.ATTRIBUTES,
                                AgdaNodeType.BID,
                                AgdaNodeType.DO_,
                                AgdaNodeType.EXPR,
                                AgdaNodeType.FORALL,
                                AgdaNodeType.ID,
                                AgdaNodeType.LAMBDA,
                                AgdaNodeType.LET,
                                AgdaNodeType.QID)));
        out.put(
                AgdaNodeType.WHERE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                AgdaNodeType.ABSTRACT_,
                                AgdaNodeType.BID,
                                AgdaNodeType.DATA,
                                AgdaNodeType.DATA_SIGNATURE,
                                AgdaNodeType.FIELDS,
                                AgdaNodeType.FUNCTION,
                                AgdaNodeType.GENERALIZE,
                                AgdaNodeType.IMPORT_,
                                AgdaNodeType.INFIX,
                                AgdaNodeType.INSTANCE,
                                AgdaNodeType.MACRO,
                                AgdaNodeType.MODULE,
                                AgdaNodeType.MODULE_MACRO,
                                AgdaNodeType.MUTUAL,
                                AgdaNodeType.OPEN,
                                AgdaNodeType.PATTERN,
                                AgdaNodeType.POSTULATE,
                                AgdaNodeType.PRAGMA,
                                AgdaNodeType.PRIMITIVE,
                                AgdaNodeType.PRIVATE_,
                                AgdaNodeType.RECORD_,
                                AgdaNodeType.RECORD_SIGNATURE,
                                AgdaNodeType.SYNTAX,
                                AgdaNodeType.UNQUOTE_DECL)));
        out.put(AgdaNodeType.WITH_EXPRESSIONS, new ChildInfo(true, false, Set.of(AgdaNodeType.EXPR)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<AgdaNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<AgdaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<AgdaNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<AgdaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
