package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code java} from tree-sitter {@code node-types.json}.
 */
public final class JavaNodeSchema {
    private JavaNodeSchema() {}

    public static Set<JavaNodeField> fields(@Nullable JavaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<JavaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<JavaNodeType> allowedTypes(@Nullable JavaNodeType owner, @Nullable JavaNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<JavaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable JavaNodeType owner, @Nullable JavaNodeField field) {
        if (owner == null || field == null) return false;
        Map<JavaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable JavaNodeType owner, @Nullable JavaNodeField field) {
        if (owner == null || field == null) return false;
        Map<JavaNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<JavaNodeType> allowedChildTypes(@Nullable JavaNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable JavaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable JavaNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<JavaNodeType, Map<JavaNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<JavaNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<JavaNodeType, Map<JavaNodeField, FieldInfo>> initFields() {
        EnumMap<JavaNodeType, Map<JavaNodeField, FieldInfo>> out = new EnumMap<>(JavaNodeType.class);
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(JavaNodeType.ANNOTATION_ARGUMENT_LIST)));
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.ANNOTATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.ANNOTATION_TYPE_BODY)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            out.put(JavaNodeType.ANNOTATION_TYPE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(false, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            m.put(
                    JavaNodeField.VALUE,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    JavaNodeType.ANNOTATION,
                                    JavaNodeType.ELEMENT_VALUE_ARRAY_INITIALIZER,
                                    JavaNodeType.EXPRESSION,
                                    JavaNodeType.MARKER_ANNOTATION)));
            out.put(JavaNodeType.ANNOTATION_TYPE_ELEMENT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ARRAY, new FieldInfo(true, false, Set.of(JavaNodeType.PRIMARY_EXPRESSION)));
            m.put(JavaNodeField.INDEX, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.ARRAY_ACCESS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(
                    JavaNodeField.DIMENSIONS,
                    new FieldInfo(true, true, Set.of(JavaNodeType.DIMENSIONS, JavaNodeType.DIMENSIONS_EXPR)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.SIMPLE_TYPE)));
            m.put(JavaNodeField.VALUE, new FieldInfo(false, false, Set.of(JavaNodeType.ARRAY_INITIALIZER)));
            out.put(JavaNodeType.ARRAY_CREATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(true, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(JavaNodeField.ELEMENT, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            out.put(JavaNodeType.ARRAY_TYPE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(
                    JavaNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(JavaNodeType.ARRAY_ACCESS, JavaNodeType.FIELD_ACCESS, JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(JavaNodeField.RIGHT, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.LEFT, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            m.put(JavaNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(JavaNodeField.RIGHT, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.TYPE, new FieldInfo(true, true, Set.of(JavaNodeType.TYPE)));
            m.put(JavaNodeField.VALUE, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.CAST_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.BLOCK)));
            out.put(JavaNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(false, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.UNDERSCORE_PATTERN)));
            out.put(JavaNodeType.CATCH_FORMAL_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.CLASS_BODY)));
            m.put(JavaNodeField.INTERFACES, new FieldInfo(false, false, Set.of(JavaNodeType.SUPER_INTERFACES)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.PERMITS_, new FieldInfo(false, false, Set.of(JavaNodeType.PERMITS_)));
            m.put(JavaNodeField.SUPERCLASS, new FieldInfo(false, false, Set.of(JavaNodeType.SUPERCLASS)));
            m.put(JavaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_PARAMETERS)));
            out.put(JavaNodeType.CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.BLOCK)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            out.put(JavaNodeType.COMPACT_CONSTRUCTOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DECLARATOR, new FieldInfo(true, true, Set.of(JavaNodeType.VARIABLE_DECLARATOR)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            out.put(JavaNodeType.CONSTANT_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.CONSTRUCTOR_BODY)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(JavaNodeType.FORMAL_PARAMETERS)));
            m.put(JavaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_PARAMETERS)));
            out.put(JavaNodeType.CONSTRUCTOR_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.STATEMENT)));
            m.put(JavaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JavaNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(JavaNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.KEY, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(
                    JavaNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavaNodeType.ANNOTATION,
                                    JavaNodeType.ELEMENT_VALUE_ARRAY_INITIALIZER,
                                    JavaNodeType.EXPRESSION,
                                    JavaNodeType.MARKER_ANNOTATION)));
            out.put(JavaNodeType.ELEMENT_VALUE_PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.STATEMENT)));
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(false, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.UNDERSCORE_PATTERN)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            m.put(JavaNodeField.VALUE, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.ENHANCED_FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(JavaNodeType.ARGUMENT_LIST)));
            m.put(JavaNodeField.BODY, new FieldInfo(false, false, Set.of(JavaNodeType.CLASS_BODY)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            out.put(JavaNodeType.ENUM_CONSTANT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.ENUM_BODY)));
            m.put(JavaNodeField.INTERFACES, new FieldInfo(false, false, Set.of(JavaNodeType.SUPER_INTERFACES)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            out.put(JavaNodeType.ENUM_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(JavaNodeType.ARGUMENT_LIST)));
            m.put(
                    JavaNodeField.CONSTRUCTOR,
                    new FieldInfo(true, false, Set.of(JavaNodeType.SUPER_, JavaNodeType.THIS_)));
            m.put(JavaNodeField.OBJECT, new FieldInfo(false, false, Set.of(JavaNodeType.PRIMARY_EXPRESSION)));
            m.put(JavaNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_ARGUMENTS)));
            out.put(JavaNodeType.EXPLICIT_CONSTRUCTOR_INVOCATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(
                    JavaNodeField.MODULES,
                    new FieldInfo(false, true, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            m.put(
                    JavaNodeField.PACKAGE_,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.EXPORTS_MODULE_DIRECTIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.FIELD, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.THIS_)));
            m.put(
                    JavaNodeField.OBJECT,
                    new FieldInfo(true, false, Set.of(JavaNodeType.PRIMARY_EXPRESSION, JavaNodeType.SUPER_)));
            out.put(JavaNodeType.FIELD_ACCESS, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DECLARATOR, new FieldInfo(true, true, Set.of(JavaNodeType.VARIABLE_DECLARATOR)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            out.put(JavaNodeType.FIELD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(false, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.UNDERSCORE_PATTERN)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            out.put(JavaNodeType.FORMAL_PARAMETER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.STATEMENT)));
            m.put(JavaNodeField.CONDITION, new FieldInfo(false, false, Set.of(JavaNodeType.EXPRESSION)));
            m.put(
                    JavaNodeField.INIT,
                    new FieldInfo(
                            false, true, Set.of(JavaNodeType.EXPRESSION, JavaNodeType.LOCAL_VARIABLE_DECLARATION)));
            m.put(JavaNodeField.UPDATE, new FieldInfo(false, true, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(JavaNodeType.STATEMENT)));
            m.put(JavaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JavaNodeType.PARENTHESIZED_EXPRESSION)));
            m.put(JavaNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(JavaNodeType.STATEMENT)));
            out.put(JavaNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.LEFT, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            m.put(JavaNodeField.NAME, new FieldInfo(false, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.PATTERN, new FieldInfo(false, false, Set.of(JavaNodeType.RECORD_PATTERN)));
            m.put(JavaNodeField.RIGHT, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE)));
            out.put(JavaNodeType.INSTANCEOF_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.INTERFACE_BODY)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.PERMITS_, new FieldInfo(false, false, Set.of(JavaNodeType.PERMITS_)));
            m.put(JavaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_PARAMETERS)));
            out.put(JavaNodeType.INTERFACE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.BLOCK, JavaNodeType.EXPRESSION)));
            m.put(
                    JavaNodeField.PARAMETERS,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavaNodeType.FORMAL_PARAMETERS,
                                    JavaNodeType.IDENTIFIER,
                                    JavaNodeType.INFERRED_PARAMETERS)));
            out.put(JavaNodeType.LAMBDA_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DECLARATOR, new FieldInfo(true, true, Set.of(JavaNodeType.VARIABLE_DECLARATOR)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            out.put(JavaNodeType.LOCAL_VARIABLE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.MARKER_ANNOTATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(false, false, Set.of(JavaNodeType.BLOCK)));
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(false, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(JavaNodeType.FORMAL_PARAMETERS)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            m.put(JavaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_PARAMETERS)));
            out.put(JavaNodeType.METHOD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(JavaNodeType.ARGUMENT_LIST)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(
                    JavaNodeField.OBJECT,
                    new FieldInfo(false, false, Set.of(JavaNodeType.PRIMARY_EXPRESSION, JavaNodeType.SUPER_)));
            m.put(JavaNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_ARGUMENTS)));
            out.put(JavaNodeType.METHOD_INVOCATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.MODULE_BODY)));
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.MODULE_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ARGUMENTS, new FieldInfo(true, false, Set.of(JavaNodeType.ARGUMENT_LIST)));
            m.put(JavaNodeField.TYPE, new FieldInfo(true, false, Set.of(JavaNodeType.SIMPLE_TYPE)));
            m.put(JavaNodeField.TYPE_ARGUMENTS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_ARGUMENTS)));
            out.put(JavaNodeType.OBJECT_CREATION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(
                    JavaNodeField.MODULES,
                    new FieldInfo(false, true, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            m.put(
                    JavaNodeField.PACKAGE_,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.OPENS_MODULE_DIRECTIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(
                    JavaNodeField.PROVIDED,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            m.put(
                    JavaNodeField.PROVIDER,
                    new FieldInfo(false, true, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.PROVIDES_MODULE_DIRECTIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.CLASS_BODY)));
            m.put(JavaNodeField.INTERFACES, new FieldInfo(false, false, Set.of(JavaNodeType.SUPER_INTERFACES)));
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(JavaNodeField.PARAMETERS, new FieldInfo(true, false, Set.of(JavaNodeType.FORMAL_PARAMETERS)));
            m.put(JavaNodeField.TYPE_PARAMETERS, new FieldInfo(false, false, Set.of(JavaNodeType.TYPE_PARAMETERS)));
            out.put(JavaNodeType.RECORD_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.MODIFIERS, new FieldInfo(false, true, Set.of(JavaNodeType.REQUIRES_MODIFIER)));
            m.put(
                    JavaNodeField.MODULE,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.REQUIRES_MODULE_DIRECTIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(false, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(false, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.UNDERSCORE_PATTERN)));
            m.put(JavaNodeField.TYPE, new FieldInfo(false, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
            m.put(JavaNodeField.VALUE, new FieldInfo(false, false, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.RESOURCE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.NAME, new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER)));
            m.put(
                    JavaNodeField.SCOPE,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.SCOPED_IDENTIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.SWITCH_BLOCK)));
            m.put(JavaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JavaNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(JavaNodeType.SWITCH_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.BLOCK)));
            out.put(JavaNodeType.SYNCHRONIZED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.TEMPLATE_ARGUMENT, new FieldInfo(true, false, Set.of(JavaNodeType.STRING_LITERAL)));
            m.put(
                    JavaNodeField.TEMPLATE_PROCESSOR,
                    new FieldInfo(true, false, Set.of(JavaNodeType.PRIMARY_EXPRESSION)));
            out.put(JavaNodeType.TEMPLATE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            m.put(JavaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            m.put(JavaNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.TERNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.BLOCK)));
            out.put(JavaNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.BLOCK)));
            m.put(JavaNodeField.RESOURCES, new FieldInfo(true, false, Set.of(JavaNodeType.RESOURCE_SPECIFICATION)));
            out.put(JavaNodeType.TRY_WITH_RESOURCES_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.OPERAND, new FieldInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
            m.put(JavaNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(JavaNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(
                    JavaNodeField.TYPE,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
            out.put(JavaNodeType.USES_MODULE_DIRECTIVE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.DIMENSIONS, new FieldInfo(false, false, Set.of(JavaNodeType.DIMENSIONS)));
            m.put(
                    JavaNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.UNDERSCORE_PATTERN)));
            m.put(
                    JavaNodeField.VALUE,
                    new FieldInfo(false, false, Set.of(JavaNodeType.ARRAY_INITIALIZER, JavaNodeType.EXPRESSION)));
            out.put(JavaNodeType.VARIABLE_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavaNodeField, FieldInfo> m = new EnumMap<>(JavaNodeField.class);
            m.put(JavaNodeField.BODY, new FieldInfo(true, false, Set.of(JavaNodeType.STATEMENT)));
            m.put(JavaNodeField.CONDITION, new FieldInfo(true, false, Set.of(JavaNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(JavaNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<JavaNodeType, ChildInfo> initChildren() {
        EnumMap<JavaNodeType, ChildInfo> out = new EnumMap<>(JavaNodeType.class);
        out.put(
                JavaNodeType.ANNOTATED_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.UNANNOTATED_TYPE)));
        out.put(
                JavaNodeType.ANNOTATION_ARGUMENT_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.ELEMENT_VALUE_ARRAY_INITIALIZER,
                                JavaNodeType.ELEMENT_VALUE_PAIR,
                                JavaNodeType.EXPRESSION,
                                JavaNodeType.MARKER_ANNOTATION)));
        out.put(
                JavaNodeType.ANNOTATION_TYPE_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION_TYPE_DECLARATION,
                                JavaNodeType.ANNOTATION_TYPE_ELEMENT_DECLARATION,
                                JavaNodeType.CLASS_DECLARATION,
                                JavaNodeType.CONSTANT_DECLARATION,
                                JavaNodeType.ENUM_DECLARATION,
                                JavaNodeType.INTERFACE_DECLARATION)));
        out.put(JavaNodeType.ANNOTATION_TYPE_DECLARATION, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(
                JavaNodeType.ANNOTATION_TYPE_ELEMENT_DECLARATION,
                new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.ARGUMENT_LIST, new ChildInfo(false, true, Set.of(JavaNodeType.EXPRESSION)));
        out.put(
                JavaNodeType.ARRAY_CREATION_EXPRESSION,
                new ChildInfo(false, true, Set.of(JavaNodeType.ANNOTATION, JavaNodeType.MARKER_ANNOTATION)));
        out.put(
                JavaNodeType.ARRAY_INITIALIZER,
                new ChildInfo(false, true, Set.of(JavaNodeType.ARRAY_INITIALIZER, JavaNodeType.EXPRESSION)));
        out.put(JavaNodeType.ASSERT_STATEMENT, new ChildInfo(true, true, Set.of(JavaNodeType.EXPRESSION)));
        out.put(JavaNodeType.BLOCK, new ChildInfo(false, true, Set.of(JavaNodeType.STATEMENT)));
        out.put(JavaNodeType.BREAK_STATEMENT, new ChildInfo(false, false, Set.of(JavaNodeType.IDENTIFIER)));
        out.put(JavaNodeType.CATCH_CLAUSE, new ChildInfo(true, false, Set.of(JavaNodeType.CATCH_FORMAL_PARAMETER)));
        out.put(
                JavaNodeType.CATCH_FORMAL_PARAMETER,
                new ChildInfo(true, true, Set.of(JavaNodeType.CATCH_TYPE, JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.CATCH_TYPE, new ChildInfo(true, true, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
        out.put(
                JavaNodeType.CLASS_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION_TYPE_DECLARATION,
                                JavaNodeType.BLOCK,
                                JavaNodeType.CLASS_DECLARATION,
                                JavaNodeType.COMPACT_CONSTRUCTOR_DECLARATION,
                                JavaNodeType.CONSTRUCTOR_DECLARATION,
                                JavaNodeType.ENUM_DECLARATION,
                                JavaNodeType.FIELD_DECLARATION,
                                JavaNodeType.INTERFACE_DECLARATION,
                                JavaNodeType.METHOD_DECLARATION,
                                JavaNodeType.RECORD_DECLARATION,
                                JavaNodeType.STATIC_INITIALIZER)));
        out.put(JavaNodeType.CLASS_DECLARATION, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.CLASS_LITERAL, new ChildInfo(true, false, Set.of(JavaNodeType.UNANNOTATED_TYPE)));
        out.put(
                JavaNodeType.COMPACT_CONSTRUCTOR_DECLARATION,
                new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.CONSTANT_DECLARATION, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(
                JavaNodeType.CONSTRUCTOR_BODY,
                new ChildInfo(
                        false, true, Set.of(JavaNodeType.EXPLICIT_CONSTRUCTOR_INVOCATION, JavaNodeType.STATEMENT)));
        out.put(
                JavaNodeType.CONSTRUCTOR_DECLARATION,
                new ChildInfo(false, true, Set.of(JavaNodeType.MODIFIERS, JavaNodeType.THROWS_)));
        out.put(JavaNodeType.CONTINUE_STATEMENT, new ChildInfo(false, false, Set.of(JavaNodeType.IDENTIFIER)));
        out.put(
                JavaNodeType.DIMENSIONS,
                new ChildInfo(false, true, Set.of(JavaNodeType.ANNOTATION, JavaNodeType.MARKER_ANNOTATION)));
        out.put(
                JavaNodeType.DIMENSIONS_EXPR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JavaNodeType.ANNOTATION, JavaNodeType.EXPRESSION, JavaNodeType.MARKER_ANNOTATION)));
        out.put(
                JavaNodeType.ELEMENT_VALUE_ARRAY_INITIALIZER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.ELEMENT_VALUE_ARRAY_INITIALIZER,
                                JavaNodeType.EXPRESSION,
                                JavaNodeType.MARKER_ANNOTATION)));
        out.put(JavaNodeType.ENHANCED_FOR_STATEMENT, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(
                JavaNodeType.ENUM_BODY,
                new ChildInfo(false, true, Set.of(JavaNodeType.ENUM_BODY_DECLARATIONS, JavaNodeType.ENUM_CONSTANT)));
        out.put(
                JavaNodeType.ENUM_BODY_DECLARATIONS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION_TYPE_DECLARATION,
                                JavaNodeType.BLOCK,
                                JavaNodeType.CLASS_DECLARATION,
                                JavaNodeType.COMPACT_CONSTRUCTOR_DECLARATION,
                                JavaNodeType.CONSTRUCTOR_DECLARATION,
                                JavaNodeType.ENUM_DECLARATION,
                                JavaNodeType.FIELD_DECLARATION,
                                JavaNodeType.INTERFACE_DECLARATION,
                                JavaNodeType.METHOD_DECLARATION,
                                JavaNodeType.RECORD_DECLARATION,
                                JavaNodeType.STATIC_INITIALIZER)));
        out.put(JavaNodeType.ENUM_CONSTANT, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.ENUM_DECLARATION, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.EXPRESSION_STATEMENT, new ChildInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
        out.put(JavaNodeType.EXTENDS_INTERFACES, new ChildInfo(true, false, Set.of(JavaNodeType.TYPE_LIST)));
        out.put(JavaNodeType.FIELD_ACCESS, new ChildInfo(false, false, Set.of(JavaNodeType.SUPER_)));
        out.put(JavaNodeType.FIELD_DECLARATION, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.FINALLY_CLAUSE, new ChildInfo(true, false, Set.of(JavaNodeType.BLOCK)));
        out.put(JavaNodeType.FORMAL_PARAMETER, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(
                JavaNodeType.FORMAL_PARAMETERS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.FORMAL_PARAMETER,
                                JavaNodeType.RECEIVER_PARAMETER,
                                JavaNodeType.SPREAD_PARAMETER)));
        out.put(
                JavaNodeType.GENERIC_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.SCOPED_TYPE_IDENTIFIER,
                                JavaNodeType.TYPE_ARGUMENTS,
                                JavaNodeType.TYPE_IDENTIFIER)));
        out.put(JavaNodeType.GUARD, new ChildInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
        out.put(
                JavaNodeType.IMPORT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JavaNodeType.ASTERISK, JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
        out.put(JavaNodeType.INFERRED_PARAMETERS, new ChildInfo(true, true, Set.of(JavaNodeType.IDENTIFIER)));
        out.put(
                JavaNodeType.INTERFACE_BODY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION_TYPE_DECLARATION,
                                JavaNodeType.CLASS_DECLARATION,
                                JavaNodeType.CONSTANT_DECLARATION,
                                JavaNodeType.ENUM_DECLARATION,
                                JavaNodeType.INTERFACE_DECLARATION,
                                JavaNodeType.METHOD_DECLARATION,
                                JavaNodeType.RECORD_DECLARATION)));
        out.put(
                JavaNodeType.INTERFACE_DECLARATION,
                new ChildInfo(false, true, Set.of(JavaNodeType.EXTENDS_INTERFACES, JavaNodeType.MODIFIERS)));
        out.put(
                JavaNodeType.LABELED_STATEMENT,
                new ChildInfo(true, true, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.STATEMENT)));
        out.put(JavaNodeType.LOCAL_VARIABLE_DECLARATION, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(
                JavaNodeType.METHOD_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.MODIFIERS,
                                JavaNodeType.THROWS_)));
        out.put(JavaNodeType.METHOD_INVOCATION, new ChildInfo(false, false, Set.of(JavaNodeType.SUPER_)));
        out.put(
                JavaNodeType.METHOD_REFERENCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.PRIMARY_EXPRESSION,
                                JavaNodeType.SUPER_,
                                JavaNodeType.TYPE,
                                JavaNodeType.TYPE_ARGUMENTS)));
        out.put(
                JavaNodeType.MODIFIERS,
                new ChildInfo(false, true, Set.of(JavaNodeType.ANNOTATION, JavaNodeType.MARKER_ANNOTATION)));
        out.put(JavaNodeType.MODULE_BODY, new ChildInfo(false, true, Set.of(JavaNodeType.MODULE_DIRECTIVE)));
        out.put(
                JavaNodeType.MODULE_DECLARATION,
                new ChildInfo(false, true, Set.of(JavaNodeType.ANNOTATION, JavaNodeType.MARKER_ANNOTATION)));
        out.put(
                JavaNodeType.OBJECT_CREATION_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.CLASS_BODY,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.PRIMARY_EXPRESSION)));
        out.put(
                JavaNodeType.PACKAGE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.IDENTIFIER,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.SCOPED_IDENTIFIER)));
        out.put(JavaNodeType.PARENTHESIZED_EXPRESSION, new ChildInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
        out.put(
                JavaNodeType.PATTERN,
                new ChildInfo(true, false, Set.of(JavaNodeType.RECORD_PATTERN, JavaNodeType.TYPE_PATTERN)));
        out.put(JavaNodeType.PERMITS_, new ChildInfo(true, false, Set.of(JavaNodeType.TYPE_LIST)));
        out.put(
                JavaNodeType.PROGRAM,
                new ChildInfo(false, true, Set.of(JavaNodeType.METHOD_DECLARATION, JavaNodeType.STATEMENT)));
        out.put(
                JavaNodeType.PROVIDES_MODULE_DIRECTIVE,
                new ChildInfo(true, false, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.SCOPED_IDENTIFIER)));
        out.put(
                JavaNodeType.RECEIVER_PARAMETER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.IDENTIFIER,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.THIS_,
                                JavaNodeType.UNANNOTATED_TYPE)));
        out.put(JavaNodeType.RECORD_DECLARATION, new ChildInfo(false, false, Set.of(JavaNodeType.MODIFIERS)));
        out.put(
                JavaNodeType.RECORD_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(JavaNodeType.GENERIC_TYPE, JavaNodeType.IDENTIFIER, JavaNodeType.RECORD_PATTERN_BODY)));
        out.put(
                JavaNodeType.RECORD_PATTERN_BODY,
                new ChildInfo(false, true, Set.of(JavaNodeType.RECORD_PATTERN, JavaNodeType.RECORD_PATTERN_COMPONENT)));
        out.put(
                JavaNodeType.RECORD_PATTERN_COMPONENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.IDENTIFIER,
                                JavaNodeType.UNANNOTATED_TYPE,
                                JavaNodeType.UNDERSCORE_PATTERN)));
        out.put(
                JavaNodeType.RESOURCE,
                new ChildInfo(
                        false,
                        false,
                        Set.of(JavaNodeType.FIELD_ACCESS, JavaNodeType.IDENTIFIER, JavaNodeType.MODIFIERS)));
        out.put(JavaNodeType.RESOURCE_SPECIFICATION, new ChildInfo(true, true, Set.of(JavaNodeType.RESOURCE)));
        out.put(JavaNodeType.RETURN_STATEMENT, new ChildInfo(false, false, Set.of(JavaNodeType.EXPRESSION)));
        out.put(
                JavaNodeType.SCOPED_TYPE_IDENTIFIER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.GENERIC_TYPE,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.SCOPED_TYPE_IDENTIFIER,
                                JavaNodeType.TYPE_IDENTIFIER)));
        out.put(
                JavaNodeType.SPREAD_PARAMETER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.MODIFIERS,
                                JavaNodeType.UNANNOTATED_TYPE,
                                JavaNodeType.VARIABLE_DECLARATOR)));
        out.put(JavaNodeType.STATIC_INITIALIZER, new ChildInfo(true, false, Set.of(JavaNodeType.BLOCK)));
        out.put(JavaNodeType.STRING_INTERPOLATION, new ChildInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
        out.put(
                JavaNodeType.STRING_LITERAL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ESCAPE_SEQUENCE,
                                JavaNodeType.MULTILINE_STRING_FRAGMENT,
                                JavaNodeType.STRING_FRAGMENT,
                                JavaNodeType.STRING_INTERPOLATION)));
        out.put(JavaNodeType.SUPERCLASS, new ChildInfo(true, false, Set.of(JavaNodeType.TYPE)));
        out.put(JavaNodeType.SUPER_INTERFACES, new ChildInfo(true, false, Set.of(JavaNodeType.TYPE_LIST)));
        out.put(
                JavaNodeType.SWITCH_BLOCK,
                new ChildInfo(
                        false, true, Set.of(JavaNodeType.SWITCH_BLOCK_STATEMENT_GROUP, JavaNodeType.SWITCH_RULE)));
        out.put(
                JavaNodeType.SWITCH_BLOCK_STATEMENT_GROUP,
                new ChildInfo(true, true, Set.of(JavaNodeType.STATEMENT, JavaNodeType.SWITCH_LABEL)));
        out.put(
                JavaNodeType.SWITCH_LABEL,
                new ChildInfo(false, true, Set.of(JavaNodeType.EXPRESSION, JavaNodeType.GUARD, JavaNodeType.PATTERN)));
        out.put(
                JavaNodeType.SWITCH_RULE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.BLOCK,
                                JavaNodeType.EXPRESSION_STATEMENT,
                                JavaNodeType.SWITCH_LABEL,
                                JavaNodeType.THROW_STATEMENT)));
        out.put(
                JavaNodeType.SYNCHRONIZED_STATEMENT,
                new ChildInfo(true, false, Set.of(JavaNodeType.PARENTHESIZED_EXPRESSION)));
        out.put(JavaNodeType.THROWS_, new ChildInfo(true, true, Set.of(JavaNodeType.TYPE)));
        out.put(JavaNodeType.THROW_STATEMENT, new ChildInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
        out.put(
                JavaNodeType.TRY_STATEMENT,
                new ChildInfo(true, true, Set.of(JavaNodeType.CATCH_CLAUSE, JavaNodeType.FINALLY_CLAUSE)));
        out.put(
                JavaNodeType.TRY_WITH_RESOURCES_STATEMENT,
                new ChildInfo(false, true, Set.of(JavaNodeType.CATCH_CLAUSE, JavaNodeType.FINALLY_CLAUSE)));
        out.put(
                JavaNodeType.TYPE_ARGUMENTS,
                new ChildInfo(false, true, Set.of(JavaNodeType.TYPE, JavaNodeType.WILDCARD)));
        out.put(JavaNodeType.TYPE_BOUND, new ChildInfo(true, true, Set.of(JavaNodeType.TYPE)));
        out.put(JavaNodeType.TYPE_LIST, new ChildInfo(true, true, Set.of(JavaNodeType.TYPE)));
        out.put(
                JavaNodeType.TYPE_PARAMETER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.TYPE_BOUND,
                                JavaNodeType.TYPE_IDENTIFIER)));
        out.put(JavaNodeType.TYPE_PARAMETERS, new ChildInfo(true, true, Set.of(JavaNodeType.TYPE_PARAMETER)));
        out.put(
                JavaNodeType.TYPE_PATTERN,
                new ChildInfo(true, true, Set.of(JavaNodeType.IDENTIFIER, JavaNodeType.UNANNOTATED_TYPE)));
        out.put(JavaNodeType.UPDATE_EXPRESSION, new ChildInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
        out.put(
                JavaNodeType.WILDCARD,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavaNodeType.ANNOTATION,
                                JavaNodeType.MARKER_ANNOTATION,
                                JavaNodeType.SUPER_,
                                JavaNodeType.TYPE)));
        out.put(JavaNodeType.YIELD_STATEMENT, new ChildInfo(true, false, Set.of(JavaNodeType.EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<JavaNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<JavaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<JavaNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<JavaNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
