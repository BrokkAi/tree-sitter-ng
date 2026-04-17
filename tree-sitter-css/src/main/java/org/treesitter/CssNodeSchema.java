package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code css} from tree-sitter {@code node-types.json}.
 */
public final class CssNodeSchema {
    private CssNodeSchema() {}

    public static Set<CssNodeField> fields(@Nullable CssNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<CssNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<CssNodeType> allowedTypes(@Nullable CssNodeType owner, @Nullable CssNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<CssNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable CssNodeType owner, @Nullable CssNodeField field) {
        if (owner == null || field == null) return false;
        Map<CssNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable CssNodeType owner, @Nullable CssNodeField field) {
        if (owner == null || field == null) return false;
        Map<CssNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<CssNodeType> allowedChildTypes(@Nullable CssNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable CssNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable CssNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<CssNodeType, Map<CssNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<CssNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<CssNodeType, Map<CssNodeField, FieldInfo>> initFields() {
        EnumMap<CssNodeType, Map<CssNodeField, FieldInfo>> out = new EnumMap<>(CssNodeType.class);
        return out;
    }

    private static EnumMap<CssNodeType, ChildInfo> initChildren() {
        EnumMap<CssNodeType, ChildInfo> out = new EnumMap<>(CssNodeType.class);
        out.put(
                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.ARGUMENTS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.ATTRIBUTE_NAME,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.ATTRIBUTE_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_NAME,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.AT_RULE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.AT_KEYWORD,
                                CssNodeType.BINARY_QUERY,
                                CssNodeType.BLOCK,
                                CssNodeType.FEATURE_QUERY,
                                CssNodeType.KEYWORD_QUERY,
                                CssNodeType.PARENTHESIZED_QUERY,
                                CssNodeType.SELECTOR_QUERY,
                                CssNodeType.UNARY_QUERY)));
        out.put(
                CssNodeType.BINARY_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.STRING_VALUE)));
        out.put(
                CssNodeType.BINARY_QUERY,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_QUERY,
                                CssNodeType.FEATURE_QUERY,
                                CssNodeType.KEYWORD_QUERY,
                                CssNodeType.PARENTHESIZED_QUERY,
                                CssNodeType.SELECTOR_QUERY,
                                CssNodeType.UNARY_QUERY)));
        out.put(
                CssNodeType.BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CssNodeType.AT_RULE,
                                CssNodeType.CHARSET_STATEMENT,
                                CssNodeType.DECLARATION,
                                CssNodeType.IMPORT_STATEMENT,
                                CssNodeType.KEYFRAMES_STATEMENT,
                                CssNodeType.MEDIA_STATEMENT,
                                CssNodeType.NAMESPACE_STATEMENT,
                                CssNodeType.POSTCSS_STATEMENT,
                                CssNodeType.RULE_SET,
                                CssNodeType.SCOPE_STATEMENT,
                                CssNodeType.SUPPORTS_STATEMENT)));
        out.put(
                CssNodeType.CALL_EXPRESSION,
                new ChildInfo(true, true, Set.of(CssNodeType.ARGUMENTS, CssNodeType.FUNCTION_NAME)));
        out.put(
                CssNodeType.CHARSET_STATEMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.STRING_VALUE)));
        out.put(
                CssNodeType.CHILD_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.CLASS_NAME,
                new ChildInfo(false, true, Set.of(CssNodeType.ESCAPE_SEQUENCE, CssNodeType.IDENTIFIER)));
        out.put(
                CssNodeType.CLASS_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_NAME,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.PROPERTY_NAME,
                                CssNodeType.STRING_VALUE)));
        out.put(
                CssNodeType.DESCENDANT_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.FEATURE_QUERY,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FEATURE_NAME,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.STRING_VALUE)));
        out.put(CssNodeType.FLOAT_VALUE, new ChildInfo(false, false, Set.of(CssNodeType.UNIT)));
        out.put(
                CssNodeType.GRID_VALUE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.STRING_VALUE)));
        out.put(
                CssNodeType.ID_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_NAME,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.IMPORT_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.BINARY_QUERY,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FEATURE_QUERY,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.KEYWORD_QUERY,
                                CssNodeType.PARENTHESIZED_QUERY,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.SELECTOR_QUERY,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.UNARY_QUERY)));
        out.put(CssNodeType.INTEGER_VALUE, new ChildInfo(false, false, Set.of(CssNodeType.UNIT)));
        out.put(
                CssNodeType.KEYFRAMES_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(CssNodeType.AT_KEYWORD, CssNodeType.KEYFRAMES_NAME, CssNodeType.KEYFRAME_BLOCK_LIST)));
        out.put(
                CssNodeType.KEYFRAME_BLOCK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(CssNodeType.BLOCK, CssNodeType.FROM, CssNodeType.INTEGER_VALUE, CssNodeType.TO)));
        out.put(CssNodeType.KEYFRAME_BLOCK_LIST, new ChildInfo(false, true, Set.of(CssNodeType.KEYFRAME_BLOCK)));
        out.put(
                CssNodeType.MEDIA_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_QUERY,
                                CssNodeType.BLOCK,
                                CssNodeType.FEATURE_QUERY,
                                CssNodeType.KEYWORD_QUERY,
                                CssNodeType.PARENTHESIZED_QUERY,
                                CssNodeType.SELECTOR_QUERY,
                                CssNodeType.UNARY_QUERY)));
        out.put(
                CssNodeType.NAMESPACE_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.NAMESPACE_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(CssNodeType.CALL_EXPRESSION, CssNodeType.NAMESPACE_NAME, CssNodeType.STRING_VALUE)));
        out.put(
                CssNodeType.PARENTHESIZED_QUERY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CssNodeType.BINARY_QUERY,
                                CssNodeType.FEATURE_QUERY,
                                CssNodeType.KEYWORD_QUERY,
                                CssNodeType.PARENTHESIZED_QUERY,
                                CssNodeType.SELECTOR_QUERY,
                                CssNodeType.UNARY_QUERY)));
        out.put(
                CssNodeType.PARENTHESIZED_VALUE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.STRING_VALUE)));
        out.put(
                CssNodeType.POSTCSS_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.AT_KEYWORD,
                                CssNodeType.BINARY_EXPRESSION,
                                CssNodeType.CALL_EXPRESSION,
                                CssNodeType.COLOR_VALUE,
                                CssNodeType.FLOAT_VALUE,
                                CssNodeType.GRID_VALUE,
                                CssNodeType.IMPORTANT,
                                CssNodeType.IMPORTANT_VALUE,
                                CssNodeType.INTEGER_VALUE,
                                CssNodeType.PARENTHESIZED_VALUE,
                                CssNodeType.PLAIN_VALUE,
                                CssNodeType.STRING_VALUE)));
        out.put(
                CssNodeType.PSEUDO_CLASS_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ARGUMENTS,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_NAME,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ARGUMENTS,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(CssNodeType.RULE_SET, new ChildInfo(true, true, Set.of(CssNodeType.BLOCK, CssNodeType.SELECTORS)));
        out.put(
                CssNodeType.SCOPE_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.BLOCK,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.SELECTORS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.SELECTOR_QUERY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.SIBLING_SELECTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.ADJACENT_SIBLING_SELECTOR,
                                CssNodeType.ATTRIBUTE_SELECTOR,
                                CssNodeType.CHILD_SELECTOR,
                                CssNodeType.CLASS_SELECTOR,
                                CssNodeType.DESCENDANT_SELECTOR,
                                CssNodeType.ID_SELECTOR,
                                CssNodeType.NAMESPACE_SELECTOR,
                                CssNodeType.NESTING_SELECTOR,
                                CssNodeType.PSEUDO_CLASS_SELECTOR,
                                CssNodeType.PSEUDO_ELEMENT_SELECTOR,
                                CssNodeType.SIBLING_SELECTOR,
                                CssNodeType.STRING_VALUE,
                                CssNodeType.TAG_NAME,
                                CssNodeType.UNIVERSAL_SELECTOR)));
        out.put(
                CssNodeType.STRING_VALUE,
                new ChildInfo(false, true, Set.of(CssNodeType.ESCAPE_SEQUENCE, CssNodeType.STRING_CONTENT)));
        out.put(
                CssNodeType.STYLESHEET,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                CssNodeType.AT_RULE,
                                CssNodeType.CHARSET_STATEMENT,
                                CssNodeType.DECLARATION,
                                CssNodeType.IMPORT_STATEMENT,
                                CssNodeType.KEYFRAMES_STATEMENT,
                                CssNodeType.MEDIA_STATEMENT,
                                CssNodeType.NAMESPACE_STATEMENT,
                                CssNodeType.RULE_SET,
                                CssNodeType.SCOPE_STATEMENT,
                                CssNodeType.SUPPORTS_STATEMENT)));
        out.put(
                CssNodeType.SUPPORTS_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                CssNodeType.BINARY_QUERY,
                                CssNodeType.BLOCK,
                                CssNodeType.FEATURE_QUERY,
                                CssNodeType.KEYWORD_QUERY,
                                CssNodeType.PARENTHESIZED_QUERY,
                                CssNodeType.SELECTOR_QUERY,
                                CssNodeType.UNARY_QUERY)));
        out.put(
                CssNodeType.UNARY_QUERY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                CssNodeType.BINARY_QUERY,
                                CssNodeType.FEATURE_QUERY,
                                CssNodeType.KEYWORD_QUERY,
                                CssNodeType.PARENTHESIZED_QUERY,
                                CssNodeType.SELECTOR_QUERY,
                                CssNodeType.UNARY_QUERY)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<CssNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<CssNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<CssNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<CssNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
