package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code javascript} from tree-sitter {@code node-types.json}.
 */
public final class JavascriptNodeSchema {
    private JavascriptNodeSchema() {}

    public static Set<JavascriptNodeField> fields(@Nullable JavascriptNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<JavascriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<JavascriptNodeType> allowedTypes(
            @Nullable JavascriptNodeType owner, @Nullable JavascriptNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<JavascriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable JavascriptNodeType owner, @Nullable JavascriptNodeField field) {
        if (owner == null || field == null) return false;
        Map<JavascriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable JavascriptNodeType owner, @Nullable JavascriptNodeField field) {
        if (owner == null || field == null) return false;
        Map<JavascriptNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<JavascriptNodeType> allowedChildTypes(@Nullable JavascriptNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable JavascriptNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable JavascriptNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<JavascriptNodeType, Map<JavascriptNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<JavascriptNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<JavascriptNodeType, Map<JavascriptNodeField, FieldInfo>> initFields() {
        EnumMap<JavascriptNodeType, Map<JavascriptNodeField, FieldInfo>> out = new EnumMap<>(JavascriptNodeType.class);
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.BODY,
                    new FieldInfo(
                            true, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(JavascriptNodeField.PARAMETER, new FieldInfo(false, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            m.put(
                    JavascriptNodeField.PARAMETERS,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.FORMAL_PARAMETERS)));
            out.put(JavascriptNodeType.ARROW_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.ARRAY_PATTERN,
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.MEMBER_EXPRESSION,
                                    JavascriptNodeType.OBJECT_PATTERN,
                                    JavascriptNodeType.PARENTHESIZED_EXPRESSION,
                                    JavascriptNodeType.SUBSCRIPT_EXPRESSION,
                                    JavascriptNodeType.UNDEFINED)));
            m.put(JavascriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.LEFT, new FieldInfo(true, false, Set.of(JavascriptNodeType.PATTERN)));
            m.put(JavascriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.ASSIGNMENT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.MEMBER_EXPRESSION,
                                    JavascriptNodeType.PARENTHESIZED_EXPRESSION,
                                    JavascriptNodeType.SUBSCRIPT_EXPRESSION)));
            m.put(JavascriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(JavascriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.AUGMENTED_ASSIGNMENT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.PRIVATE_PROPERTY_IDENTIFIER)));
            m.put(JavascriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(JavascriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.BINARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.LABEL,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.STATEMENT_IDENTIFIER)));
            out.put(JavascriptNodeType.BREAK_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.ARGUMENTS,
                    new FieldInfo(
                            true, false, Set.of(JavascriptNodeType.ARGUMENTS, JavascriptNodeType.TEMPLATE_STRING)));
            m.put(
                    JavascriptNodeField.FUNCTION,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.IMPORT_)));
            m.put(
                    JavascriptNodeField.OPTIONAL_CHAIN,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.OPTIONAL_CHAIN)));
            out.put(JavascriptNodeType.CALL_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(
                    JavascriptNodeField.PARAMETER,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    JavascriptNodeType.ARRAY_PATTERN,
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.OBJECT_PATTERN)));
            out.put(JavascriptNodeType.CATCH_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.CLASS_BODY)));
            m.put(JavascriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(JavascriptNodeType.DECORATOR)));
            m.put(JavascriptNodeField.NAME, new FieldInfo(false, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            out.put(JavascriptNodeType.CLASS_, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.MEMBER,
                    new FieldInfo(
                            false,
                            true,
                            Set.of(
                                    JavascriptNodeType.CLASS_STATIC_BLOCK,
                                    JavascriptNodeType.FIELD_DEFINITION,
                                    JavascriptNodeType.METHOD_DEFINITION)));
            out.put(JavascriptNodeType.CLASS_BODY, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.CLASS_BODY)));
            m.put(JavascriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(JavascriptNodeType.DECORATOR)));
            m.put(JavascriptNodeField.NAME, new FieldInfo(true, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            out.put(JavascriptNodeType.CLASS_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            out.put(JavascriptNodeType.CLASS_STATIC_BLOCK, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.LABEL,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.STATEMENT_IDENTIFIER)));
            out.put(JavascriptNodeType.CONTINUE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
            m.put(
                    JavascriptNodeField.CONDITION,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(JavascriptNodeType.DO_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.ALIAS,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.IDENTIFIER, JavascriptNodeType.STRING)));
            m.put(
                    JavascriptNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.IDENTIFIER, JavascriptNodeType.STRING)));
            out.put(JavascriptNodeType.EXPORT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.DECLARATION, new FieldInfo(false, false, Set.of(JavascriptNodeType.DECLARATION)));
            m.put(JavascriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(JavascriptNodeType.DECORATOR)));
            m.put(JavascriptNodeField.SOURCE, new FieldInfo(false, false, Set.of(JavascriptNodeType.STRING)));
            m.put(JavascriptNodeField.VALUE, new FieldInfo(false, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.EXPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(JavascriptNodeType.DECORATOR)));
            m.put(
                    JavascriptNodeField.PROPERTY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.COMPUTED_PROPERTY_NAME,
                                    JavascriptNodeType.NUMBER,
                                    JavascriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.STRING)));
            m.put(JavascriptNodeField.VALUE, new FieldInfo(false, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.FIELD_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            out.put(JavascriptNodeType.FINALLY_CLAUSE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
            m.put(JavascriptNodeField.KIND, new FieldInfo(false, true, Collections.emptySet()));
            m.put(
                    JavascriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.ARRAY_PATTERN,
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.MEMBER_EXPRESSION,
                                    JavascriptNodeType.OBJECT_PATTERN,
                                    JavascriptNodeType.PARENTHESIZED_EXPRESSION,
                                    JavascriptNodeType.SUBSCRIPT_EXPRESSION,
                                    JavascriptNodeType.UNDEFINED)));
            m.put(JavascriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            m.put(
                    JavascriptNodeField.RIGHT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
            m.put(JavascriptNodeField.VALUE, new FieldInfo(false, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.FOR_IN_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
            m.put(
                    JavascriptNodeField.CONDITION,
                    new FieldInfo(
                            true,
                            true,
                            Set.of(
                                    JavascriptNodeType.EMPTY_STATEMENT,
                                    JavascriptNodeType.EXPRESSION,
                                    JavascriptNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    JavascriptNodeField.INCREMENT,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
            m.put(
                    JavascriptNodeField.INITIALIZER,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.EMPTY_STATEMENT,
                                    JavascriptNodeType.EXPRESSION,
                                    JavascriptNodeType.LEXICAL_DECLARATION,
                                    JavascriptNodeType.SEQUENCE_EXPRESSION,
                                    JavascriptNodeType.VARIABLE_DECLARATION)));
            out.put(JavascriptNodeType.FOR_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(JavascriptNodeField.NAME, new FieldInfo(true, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            m.put(
                    JavascriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.FORMAL_PARAMETERS)));
            out.put(JavascriptNodeType.FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(JavascriptNodeField.NAME, new FieldInfo(false, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            m.put(
                    JavascriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.FORMAL_PARAMETERS)));
            out.put(JavascriptNodeType.FUNCTION_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(JavascriptNodeField.NAME, new FieldInfo(false, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            m.put(
                    JavascriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.FORMAL_PARAMETERS)));
            out.put(JavascriptNodeType.GENERATOR_FUNCTION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(JavascriptNodeField.NAME, new FieldInfo(true, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            m.put(
                    JavascriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.FORMAL_PARAMETERS)));
            out.put(JavascriptNodeType.GENERATOR_FUNCTION_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.ALTERNATIVE, new FieldInfo(false, false, Set.of(JavascriptNodeType.ELSE_CLAUSE)));
            m.put(
                    JavascriptNodeField.CONDITION,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.PARENTHESIZED_EXPRESSION)));
            m.put(JavascriptNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
            out.put(JavascriptNodeType.IF_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.ALIAS, new FieldInfo(false, false, Set.of(JavascriptNodeType.IDENTIFIER)));
            m.put(
                    JavascriptNodeField.NAME,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.IDENTIFIER, JavascriptNodeType.STRING)));
            out.put(JavascriptNodeType.IMPORT_SPECIFIER, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.SOURCE, new FieldInfo(true, false, Set.of(JavascriptNodeType.STRING)));
            out.put(JavascriptNodeType.IMPORT_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.JSX_NAMESPACE_NAME,
                                    JavascriptNodeType.MEMBER_EXPRESSION)));
            out.put(JavascriptNodeType.JSX_CLOSING_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.CLOSE_TAG,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.JSX_CLOSING_ELEMENT)));
            m.put(
                    JavascriptNodeField.OPEN_TAG,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.JSX_OPENING_ELEMENT)));
            out.put(JavascriptNodeType.JSX_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.ATTRIBUTE,
                    new FieldInfo(
                            false, true, Set.of(JavascriptNodeType.JSX_ATTRIBUTE, JavascriptNodeType.JSX_EXPRESSION)));
            m.put(
                    JavascriptNodeField.NAME,
                    new FieldInfo(
                            false,
                            false,
                            Set.of(
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.JSX_NAMESPACE_NAME,
                                    JavascriptNodeType.MEMBER_EXPRESSION)));
            out.put(JavascriptNodeType.JSX_OPENING_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.ATTRIBUTE,
                    new FieldInfo(
                            false, true, Set.of(JavascriptNodeType.JSX_ATTRIBUTE, JavascriptNodeType.JSX_EXPRESSION)));
            m.put(
                    JavascriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.JSX_NAMESPACE_NAME,
                                    JavascriptNodeType.MEMBER_EXPRESSION)));
            out.put(JavascriptNodeType.JSX_SELF_CLOSING_ELEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
            m.put(
                    JavascriptNodeField.LABEL,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_IDENTIFIER)));
            out.put(JavascriptNodeType.LABELED_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.KIND, new FieldInfo(true, false, Collections.emptySet()));
            out.put(JavascriptNodeType.LEXICAL_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.OBJECT,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.IMPORT_)));
            m.put(
                    JavascriptNodeField.OPTIONAL_CHAIN,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.OPTIONAL_CHAIN)));
            m.put(
                    JavascriptNodeField.PROPERTY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.PROPERTY_IDENTIFIER)));
            out.put(JavascriptNodeType.MEMBER_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(JavascriptNodeField.DECORATOR, new FieldInfo(false, true, Set.of(JavascriptNodeType.DECORATOR)));
            m.put(
                    JavascriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.COMPUTED_PROPERTY_NAME,
                                    JavascriptNodeType.NUMBER,
                                    JavascriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.STRING)));
            m.put(
                    JavascriptNodeField.PARAMETERS,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.FORMAL_PARAMETERS)));
            out.put(JavascriptNodeType.METHOD_DEFINITION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.ARGUMENTS, new FieldInfo(false, false, Set.of(JavascriptNodeType.ARGUMENTS)));
            m.put(
                    JavascriptNodeField.CONSTRUCTOR,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(JavascriptNodeType.NEW_EXPRESSION, JavascriptNodeType.PRIMARY_EXPRESSION)));
            out.put(JavascriptNodeType.NEW_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.LEFT,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.ARRAY_PATTERN,
                                    JavascriptNodeType.OBJECT_PATTERN,
                                    JavascriptNodeType.SHORTHAND_PROPERTY_IDENTIFIER_PATTERN)));
            m.put(JavascriptNodeField.RIGHT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.OBJECT_ASSIGNMENT_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.KEY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.COMPUTED_PROPERTY_NAME,
                                    JavascriptNodeType.NUMBER,
                                    JavascriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.STRING)));
            m.put(JavascriptNodeField.VALUE, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.PAIR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.KEY,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.COMPUTED_PROPERTY_NAME,
                                    JavascriptNodeType.NUMBER,
                                    JavascriptNodeType.PRIVATE_PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.PROPERTY_IDENTIFIER,
                                    JavascriptNodeType.STRING)));
            m.put(
                    JavascriptNodeField.VALUE,
                    new FieldInfo(
                            true, false, Set.of(JavascriptNodeType.ASSIGNMENT_PATTERN, JavascriptNodeType.PATTERN)));
            out.put(JavascriptNodeType.PAIR_PATTERN, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.FLAGS, new FieldInfo(false, false, Set.of(JavascriptNodeType.REGEX_FLAGS)));
            m.put(JavascriptNodeField.PATTERN, new FieldInfo(true, false, Set.of(JavascriptNodeType.REGEX_PATTERN)));
            out.put(JavascriptNodeType.REGEX, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.INDEX,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
            m.put(JavascriptNodeField.OBJECT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            m.put(
                    JavascriptNodeField.OPTIONAL_CHAIN,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.OPTIONAL_CHAIN)));
            out.put(JavascriptNodeType.SUBSCRIPT_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(false, true, Set.of(JavascriptNodeType.STATEMENT)));
            m.put(
                    JavascriptNodeField.VALUE,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
            out.put(JavascriptNodeType.SWITCH_CASE, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(false, true, Set.of(JavascriptNodeType.STATEMENT)));
            out.put(JavascriptNodeType.SWITCH_DEFAULT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.SWITCH_BODY)));
            m.put(
                    JavascriptNodeField.VALUE,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(JavascriptNodeType.SWITCH_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.ALTERNATIVE, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            m.put(JavascriptNodeField.CONDITION, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            m.put(JavascriptNodeField.CONSEQUENCE, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.TERNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT_BLOCK)));
            m.put(
                    JavascriptNodeField.FINALIZER,
                    new FieldInfo(false, false, Set.of(JavascriptNodeType.FINALLY_CLAUSE)));
            m.put(JavascriptNodeField.HANDLER, new FieldInfo(false, false, Set.of(JavascriptNodeType.CATCH_CLAUSE)));
            out.put(JavascriptNodeType.TRY_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            m.put(JavascriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(JavascriptNodeType.UNARY_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.ARGUMENT, new FieldInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
            m.put(JavascriptNodeField.OPERATOR, new FieldInfo(true, false, Collections.emptySet()));
            out.put(JavascriptNodeType.UPDATE_EXPRESSION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.KIND, new FieldInfo(true, true, Collections.emptySet()));
            out.put(JavascriptNodeType.USING_DECLARATION, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(
                    JavascriptNodeField.NAME,
                    new FieldInfo(
                            true,
                            false,
                            Set.of(
                                    JavascriptNodeType.ARRAY_PATTERN,
                                    JavascriptNodeType.IDENTIFIER,
                                    JavascriptNodeType.OBJECT_PATTERN)));
            m.put(JavascriptNodeField.VALUE, new FieldInfo(false, false, Set.of(JavascriptNodeType.EXPRESSION)));
            out.put(JavascriptNodeType.VARIABLE_DECLARATOR, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
            m.put(
                    JavascriptNodeField.CONDITION,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(JavascriptNodeType.WHILE_STATEMENT, Collections.unmodifiableMap(m));
        }
        {
            EnumMap<JavascriptNodeField, FieldInfo> m = new EnumMap<>(JavascriptNodeField.class);
            m.put(JavascriptNodeField.BODY, new FieldInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
            m.put(
                    JavascriptNodeField.OBJECT,
                    new FieldInfo(true, false, Set.of(JavascriptNodeType.PARENTHESIZED_EXPRESSION)));
            out.put(JavascriptNodeType.WITH_STATEMENT, Collections.unmodifiableMap(m));
        }
        return out;
    }

    private static EnumMap<JavascriptNodeType, ChildInfo> initChildren() {
        EnumMap<JavascriptNodeType, ChildInfo> out = new EnumMap<>(JavascriptNodeType.class);
        out.put(
                JavascriptNodeType.ARGUMENTS,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SPREAD_ELEMENT)));
        out.put(
                JavascriptNodeType.ARRAY,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SPREAD_ELEMENT)));
        out.put(
                JavascriptNodeType.ARRAY_PATTERN,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.ASSIGNMENT_PATTERN, JavascriptNodeType.PATTERN)));
        out.put(JavascriptNodeType.AWAIT_EXPRESSION, new ChildInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
        out.put(JavascriptNodeType.CLASS_, new ChildInfo(false, false, Set.of(JavascriptNodeType.CLASS_HERITAGE)));
        out.put(
                JavascriptNodeType.CLASS_DECLARATION,
                new ChildInfo(false, false, Set.of(JavascriptNodeType.CLASS_HERITAGE)));
        out.put(JavascriptNodeType.CLASS_HERITAGE, new ChildInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
        out.put(
                JavascriptNodeType.COMPUTED_PROPERTY_NAME,
                new ChildInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
        out.put(
                JavascriptNodeType.DECORATOR,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JavascriptNodeType.CALL_EXPRESSION,
                                JavascriptNodeType.IDENTIFIER,
                                JavascriptNodeType.MEMBER_EXPRESSION)));
        out.put(JavascriptNodeType.ELSE_CLAUSE, new ChildInfo(true, false, Set.of(JavascriptNodeType.STATEMENT)));
        out.put(
                JavascriptNodeType.EXPORT_CLAUSE,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.EXPORT_SPECIFIER)));
        out.put(
                JavascriptNodeType.EXPORT_STATEMENT,
                new ChildInfo(
                        false, false, Set.of(JavascriptNodeType.EXPORT_CLAUSE, JavascriptNodeType.NAMESPACE_EXPORT)));
        out.put(
                JavascriptNodeType.EXPRESSION_STATEMENT,
                new ChildInfo(
                        true, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                JavascriptNodeType.FORMAL_PARAMETERS,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.ASSIGNMENT_PATTERN, JavascriptNodeType.PATTERN)));
        out.put(JavascriptNodeType.IMPORT_ATTRIBUTE, new ChildInfo(true, false, Set.of(JavascriptNodeType.OBJECT)));
        out.put(
                JavascriptNodeType.IMPORT_CLAUSE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavascriptNodeType.IDENTIFIER,
                                JavascriptNodeType.NAMED_IMPORTS,
                                JavascriptNodeType.NAMESPACE_IMPORT)));
        out.put(
                JavascriptNodeType.IMPORT_STATEMENT,
                new ChildInfo(
                        false, true, Set.of(JavascriptNodeType.IMPORT_ATTRIBUTE, JavascriptNodeType.IMPORT_CLAUSE)));
        out.put(
                JavascriptNodeType.JSX_ATTRIBUTE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                JavascriptNodeType.JSX_ELEMENT,
                                JavascriptNodeType.JSX_EXPRESSION,
                                JavascriptNodeType.JSX_NAMESPACE_NAME,
                                JavascriptNodeType.JSX_SELF_CLOSING_ELEMENT,
                                JavascriptNodeType.PROPERTY_IDENTIFIER,
                                JavascriptNodeType.STRING)));
        out.put(
                JavascriptNodeType.JSX_ELEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavascriptNodeType.HTML_CHARACTER_REFERENCE,
                                JavascriptNodeType.JSX_ELEMENT,
                                JavascriptNodeType.JSX_EXPRESSION,
                                JavascriptNodeType.JSX_SELF_CLOSING_ELEMENT,
                                JavascriptNodeType.JSX_TEXT)));
        out.put(
                JavascriptNodeType.JSX_EXPRESSION,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                JavascriptNodeType.EXPRESSION,
                                JavascriptNodeType.SEQUENCE_EXPRESSION,
                                JavascriptNodeType.SPREAD_ELEMENT)));
        out.put(
                JavascriptNodeType.JSX_NAMESPACE_NAME,
                new ChildInfo(true, true, Set.of(JavascriptNodeType.IDENTIFIER)));
        out.put(
                JavascriptNodeType.LEXICAL_DECLARATION,
                new ChildInfo(true, true, Set.of(JavascriptNodeType.VARIABLE_DECLARATOR)));
        out.put(
                JavascriptNodeType.NAMED_IMPORTS,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.IMPORT_SPECIFIER)));
        out.put(
                JavascriptNodeType.NAMESPACE_EXPORT,
                new ChildInfo(false, false, Set.of(JavascriptNodeType.IDENTIFIER, JavascriptNodeType.STRING)));
        out.put(JavascriptNodeType.NAMESPACE_IMPORT, new ChildInfo(true, false, Set.of(JavascriptNodeType.IDENTIFIER)));
        out.put(
                JavascriptNodeType.OBJECT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavascriptNodeType.METHOD_DEFINITION,
                                JavascriptNodeType.PAIR,
                                JavascriptNodeType.SHORTHAND_PROPERTY_IDENTIFIER,
                                JavascriptNodeType.SPREAD_ELEMENT)));
        out.put(
                JavascriptNodeType.OBJECT_PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavascriptNodeType.OBJECT_ASSIGNMENT_PATTERN,
                                JavascriptNodeType.PAIR_PATTERN,
                                JavascriptNodeType.REST_PATTERN,
                                JavascriptNodeType.SHORTHAND_PROPERTY_IDENTIFIER_PATTERN)));
        out.put(
                JavascriptNodeType.PARENTHESIZED_EXPRESSION,
                new ChildInfo(
                        true, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                JavascriptNodeType.PROGRAM,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.HASH_BANG_LINE, JavascriptNodeType.STATEMENT)));
        out.put(
                JavascriptNodeType.REST_PATTERN,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                JavascriptNodeType.ARRAY_PATTERN,
                                JavascriptNodeType.IDENTIFIER,
                                JavascriptNodeType.MEMBER_EXPRESSION,
                                JavascriptNodeType.OBJECT_PATTERN,
                                JavascriptNodeType.SUBSCRIPT_EXPRESSION,
                                JavascriptNodeType.UNDEFINED)));
        out.put(
                JavascriptNodeType.RETURN_STATEMENT,
                new ChildInfo(
                        false, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                JavascriptNodeType.SEQUENCE_EXPRESSION,
                new ChildInfo(true, true, Set.of(JavascriptNodeType.EXPRESSION)));
        out.put(JavascriptNodeType.SPREAD_ELEMENT, new ChildInfo(true, false, Set.of(JavascriptNodeType.EXPRESSION)));
        out.put(JavascriptNodeType.STATEMENT_BLOCK, new ChildInfo(false, true, Set.of(JavascriptNodeType.STATEMENT)));
        out.put(
                JavascriptNodeType.STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavascriptNodeType.ESCAPE_SEQUENCE,
                                JavascriptNodeType.HTML_CHARACTER_REFERENCE,
                                JavascriptNodeType.STRING_FRAGMENT)));
        out.put(
                JavascriptNodeType.SWITCH_BODY,
                new ChildInfo(false, true, Set.of(JavascriptNodeType.SWITCH_CASE, JavascriptNodeType.SWITCH_DEFAULT)));
        out.put(
                JavascriptNodeType.TEMPLATE_STRING,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                JavascriptNodeType.ESCAPE_SEQUENCE,
                                JavascriptNodeType.STRING_FRAGMENT,
                                JavascriptNodeType.TEMPLATE_SUBSTITUTION)));
        out.put(
                JavascriptNodeType.TEMPLATE_SUBSTITUTION,
                new ChildInfo(
                        true, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                JavascriptNodeType.THROW_STATEMENT,
                new ChildInfo(
                        true, false, Set.of(JavascriptNodeType.EXPRESSION, JavascriptNodeType.SEQUENCE_EXPRESSION)));
        out.put(
                JavascriptNodeType.USING_DECLARATION,
                new ChildInfo(true, true, Set.of(JavascriptNodeType.VARIABLE_DECLARATOR)));
        out.put(
                JavascriptNodeType.VARIABLE_DECLARATION,
                new ChildInfo(true, true, Set.of(JavascriptNodeType.VARIABLE_DECLARATOR)));
        out.put(
                JavascriptNodeType.YIELD_EXPRESSION,
                new ChildInfo(false, false, Set.of(JavascriptNodeType.EXPRESSION)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<JavascriptNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<JavascriptNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<JavascriptNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<JavascriptNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
