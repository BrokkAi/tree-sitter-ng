package org.treesitter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/**
 * Lightweight schema utilities for {@code verilog} from tree-sitter {@code node-types.json}.
 */
public final class VerilogNodeSchema {
    private VerilogNodeSchema() {}

    public static Set<VerilogNodeField> fields(@Nullable VerilogNodeType owner) {
        if (owner == null) return Collections.emptySet();
        Map<VerilogNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        return m.keySet();
    }

    public static Set<VerilogNodeType> allowedTypes(@Nullable VerilogNodeType owner, @Nullable VerilogNodeField field) {
        if (owner == null || field == null) return Collections.emptySet();
        Map<VerilogNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return Collections.emptySet();
        FieldInfo info = m.get(field);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean isRequired(@Nullable VerilogNodeType owner, @Nullable VerilogNodeField field) {
        if (owner == null || field == null) return false;
        Map<VerilogNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.required;
    }

    public static boolean isMultiple(@Nullable VerilogNodeType owner, @Nullable VerilogNodeField field) {
        if (owner == null || field == null) return false;
        Map<VerilogNodeField, FieldInfo> m = FIELDS.get(owner);
        if (m == null) return false;
        FieldInfo info = m.get(field);
        return info != null && info.multiple;
    }

    public static Set<VerilogNodeType> allowedChildTypes(@Nullable VerilogNodeType owner) {
        if (owner == null) return Collections.emptySet();
        ChildInfo info = CHILDREN.get(owner);
        if (info == null) return Collections.emptySet();
        return info.allowedTypes;
    }

    public static boolean childrenRequired(@Nullable VerilogNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.required;
    }

    public static boolean childrenMultiple(@Nullable VerilogNodeType owner) {
        if (owner == null) return false;
        ChildInfo info = CHILDREN.get(owner);
        return info != null && info.multiple;
    }

    private static final EnumMap<VerilogNodeType, Map<VerilogNodeField, FieldInfo>> FIELDS = initFields();
    private static final EnumMap<VerilogNodeType, ChildInfo> CHILDREN = initChildren();

    // Some grammars have no fields/children schema; keep constructors 'used' under -Werror.
    @SuppressWarnings("unused")
    private static final FieldInfo UNUSED_FIELD_INFO = new FieldInfo(false, false, Collections.emptySet());

    @SuppressWarnings("unused")
    private static final ChildInfo UNUSED_CHILD_INFO = new ChildInfo(false, false, Collections.emptySet());

    private static EnumMap<VerilogNodeType, Map<VerilogNodeField, FieldInfo>> initFields() {
        EnumMap<VerilogNodeType, Map<VerilogNodeField, FieldInfo>> out = new EnumMap<>(VerilogNodeType.class);
        return out;
    }

    private static EnumMap<VerilogNodeType, ChildInfo> initChildren() {
        EnumMap<VerilogNodeType, ChildInfo> out = new EnumMap<>(VerilogNodeType.class);
        out.put(
                VerilogNodeType.ACTION_BLOCK,
                new ChildInfo(true, true, Set.of(VerilogNodeType.STATEMENT, VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.ALWAYS_CONSTRUCT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ALWAYS_KEYWORD, VerilogNodeType.STATEMENT)));
        out.put(
                VerilogNodeType.ANONYMOUS_PROGRAM,
                new ChildInfo(false, true, Set.of(VerilogNodeType.ANONYMOUS_PROGRAM_ITEM)));
        out.put(
                VerilogNodeType.ANONYMOUS_PROGRAM_ITEM,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                VerilogNodeType.CLASS_CONSTRUCTOR_DECLARATION,
                                VerilogNodeType.CLASS_DECLARATION,
                                VerilogNodeType.COVERGROUP_DECLARATION,
                                VerilogNodeType.FUNCTION_DECLARATION,
                                VerilogNodeType.TASK_DECLARATION)));
        out.put(
                VerilogNodeType.ANSI_PORT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.INTERFACE_PORT_HEADER,
                                VerilogNodeType.NET_PORT_HEADER1,
                                VerilogNodeType.PORT_DIRECTION,
                                VerilogNodeType.PORT_IDENTIFIER,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION,
                                VerilogNodeType.VARIABLE_PORT_HEADER)));
        out.put(
                VerilogNodeType.ARRAY_MANIPULATION_CALL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ARRAY_METHOD_NAME,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT)));
        out.put(
                VerilogNodeType.ARRAY_METHOD_NAME,
                new ChildInfo(false, false, Set.of(VerilogNodeType.METHOD_IDENTIFIER)));
        out.put(VerilogNodeType.ARRAY_RANGE_EXPRESSION, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.ASSERTION_VARIABLE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIST_OF_VARIABLE_DECL_ASSIGNMENTS)));
        out.put(
                VerilogNodeType.ASSERT_PROPERTY_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ACTION_BLOCK, VerilogNodeType.PROPERTY_SPEC)));
        out.put(
                VerilogNodeType.ASSIGNMENT_PATTERN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSIGNMENT_PATTERN_KEY,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.ASSIGNMENT_PATTERN_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSIGNMENT_PATTERN,
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.TYPE_REFERENCE)));
        out.put(
                VerilogNodeType.ASSIGNMENT_PATTERN_KEY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.GENERATE_BLOCK_IDENTIFIER,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.INTEGER_VECTOR_TYPE,
                                VerilogNodeType.NON_INTEGER_TYPE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PARAMETER_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.ASSIGNMENT_PATTERN_NET_LVALUE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.NET_LVALUE)));
        out.put(
                VerilogNodeType.ASSIGNMENT_PATTERN_VARIABLE_LVALUE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.VARIABLE_LVALUE)));
        out.put(VerilogNodeType.ASSOCIATIVE_DIMENSION, new ChildInfo(false, false, Set.of(VerilogNodeType.DATA_TYPE)));
        out.put(
                VerilogNodeType.ASSUME_PROPERTY_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ACTION_BLOCK, VerilogNodeType.PROPERTY_SPEC)));
        out.put(VerilogNodeType.ATTRIBUTE_INSTANCE, new ChildInfo(true, true, Set.of(VerilogNodeType.ATTR_SPEC)));
        out.put(
                VerilogNodeType.ATTR_SPEC,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.BEGIN_KEYWORDS,
                new ChildInfo(true, false, Set.of(VerilogNodeType.DOUBLE_QUOTED_STRING)));
        out.put(
                VerilogNodeType.BIND_DIRECTIVE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BIND_TARGET_INSTANCE,
                                VerilogNodeType.BIND_TARGET_INSTANCE_LIST,
                                VerilogNodeType.BIND_TARGET_SCOPE,
                                VerilogNodeType.CHECKER_INSTANTIATION,
                                VerilogNodeType.INTERFACE_INSTANTIATION,
                                VerilogNodeType.MODULE_INSTANTIATION,
                                VerilogNodeType.PROGRAM_INSTANTIATION)));
        out.put(
                VerilogNodeType.BIND_TARGET_INSTANCE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.BIND_TARGET_INSTANCE_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.BIND_TARGET_INSTANCE)));
        out.put(
                VerilogNodeType.BIND_TARGET_SCOPE,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.BINS_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.COVER_POINT_IDENTIFIER,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.BINS_OR_EMPTY,
                new ChildInfo(
                        false, true, Set.of(VerilogNodeType.ATTRIBUTE_INSTANCE, VerilogNodeType.BINS_OR_OPTIONS)));
        out.put(
                VerilogNodeType.BINS_OR_OPTIONS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BINS_KEYWORD,
                                VerilogNodeType.COVERAGE_OPTION,
                                VerilogNodeType.COVERGROUP_RANGE_LIST,
                                VerilogNodeType.COVER_POINT_IDENTIFIER,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.TRANS_LIST)));
        out.put(
                VerilogNodeType.BINS_SELECTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BINS_KEYWORD,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SELECT_EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.BINS_SELECTION_OR_OPTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.BINS_SELECTION,
                                VerilogNodeType.COVERAGE_OPTION)));
        out.put(VerilogNodeType.BIT_SELECT1, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.BLOCKING_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DELAY_OR_EVENT_CONTROL,
                                VerilogNodeType.DYNAMIC_ARRAY_NEW,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.NONRANGE_VARIABLE_LVALUE,
                                VerilogNodeType.OPERATOR_ASSIGNMENT,
                                VerilogNodeType.VARIABLE_LVALUE)));
        out.put(
                VerilogNodeType.BLOCK_EVENT_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.BLOCK_EVENT_EXPRESSION, VerilogNodeType.HIERARCHICAL_BTF_IDENTIFIER)));
        out.put(
                VerilogNodeType.BLOCK_ITEM_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.DATA_DECLARATION,
                                VerilogNodeType.LET_DECLARATION,
                                VerilogNodeType.LOCAL_PARAMETER_DECLARATION,
                                VerilogNodeType.OVERLOAD_DECLARATION,
                                VerilogNodeType.PARAMETER_DECLARATION)));
        out.put(VerilogNodeType.CASE_EXPRESSION, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.CASE_GENERATE_CONSTRUCT,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.CASE_GENERATE_ITEM, VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CASE_GENERATE_ITEM,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION, VerilogNodeType.GENERATE_BLOCK)));
        out.put(
                VerilogNodeType.CASE_INSIDE_ITEM,
                new ChildInfo(true, true, Set.of(VerilogNodeType.OPEN_RANGE_LIST, VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.CASE_ITEM,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.CASE_ITEM_EXPRESSION, VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(VerilogNodeType.CASE_ITEM_EXPRESSION, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.CASE_PATTERN_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.PATTERN,
                                VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.CASE_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CASE_EXPRESSION,
                                VerilogNodeType.CASE_INSIDE_ITEM,
                                VerilogNodeType.CASE_ITEM,
                                VerilogNodeType.CASE_KEYWORD,
                                VerilogNodeType.CASE_PATTERN_ITEM,
                                VerilogNodeType.UNIQUE_PRIORITY)));
        out.put(
                VerilogNodeType.CAST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CASTING_TYPE, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.CASTING_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONSTANT_PRIMARY,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.GENERATE_BLOCK_IDENTIFIER,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.INTEGER_VECTOR_TYPE,
                                VerilogNodeType.NON_INTEGER_TYPE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PARAMETER_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CHECKER_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ALWAYS_CONSTRUCT,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CASE_GENERATE_CONSTRUCT,
                                VerilogNodeType.CHECKER_IDENTIFIER,
                                VerilogNodeType.CHECKER_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.CHECKER_PORT_LIST,
                                VerilogNodeType.CONCURRENT_ASSERTION_ITEM,
                                VerilogNodeType.CONTINUOUS_ASSIGN,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERTION_ITEM,
                                VerilogNodeType.ELABORATION_SYSTEM_TASK,
                                VerilogNodeType.FINAL_CONSTRUCT,
                                VerilogNodeType.GENERATE_REGION,
                                VerilogNodeType.IF_GENERATE_CONSTRUCT,
                                VerilogNodeType.INITIAL_CONSTRUCT,
                                VerilogNodeType.LOOP_GENERATE_CONSTRUCT)));
        out.put(
                VerilogNodeType.CHECKER_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CHECKER_INSTANTIATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CHECKER_IDENTIFIER,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.FORMAL_PORT_IDENTIFIER,
                                VerilogNodeType.NAME_OF_INSTANCE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PROPERTY_EXPR,
                                VerilogNodeType.SEQUENCE_EXPR)));
        out.put(
                VerilogNodeType.CHECKER_OR_GENERATE_ITEM_DECLARATION,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                VerilogNodeType.CHECKER_DECLARATION,
                                VerilogNodeType.CLOCKING_DECLARATION,
                                VerilogNodeType.CLOCKING_IDENTIFIER,
                                VerilogNodeType.COVERGROUP_DECLARATION,
                                VerilogNodeType.DATA_DECLARATION,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.FUNCTION_DECLARATION,
                                VerilogNodeType.GENVAR_DECLARATION,
                                VerilogNodeType.LET_DECLARATION,
                                VerilogNodeType.PROPERTY_DECLARATION,
                                VerilogNodeType.SEQUENCE_DECLARATION)));
        out.put(
                VerilogNodeType.CHECKER_PORT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CHECKER_PORT_DIRECTION,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.FORMAL_PORT_IDENTIFIER,
                                VerilogNodeType.PROPERTY_EXPR,
                                VerilogNodeType.PROPERTY_FORMAL_TYPE1,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.SEQUENCE_EXPR,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.CHECKER_PORT_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CHECKER_PORT_ITEM)));
        out.put(
                VerilogNodeType.CLASS_CONSTRUCTOR_DECLARATION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.BLOCK_ITEM_DECLARATION,
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.FUNCTION_STATEMENT_OR_NULL,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT,
                                VerilogNodeType.TF_PORT_LIST)));
        out.put(
                VerilogNodeType.CLASS_CONSTRUCTOR_PROTOTYPE,
                new ChildInfo(false, false, Set.of(VerilogNodeType.TF_PORT_LIST)));
        out.put(
                VerilogNodeType.CLASS_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_IDENTIFIER,
                                VerilogNodeType.CLASS_ITEM,
                                VerilogNodeType.CLASS_TYPE,
                                VerilogNodeType.INTERFACE_CLASS_TYPE,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT,
                                VerilogNodeType.PARAMETER_PORT_LIST)));
        out.put(
                VerilogNodeType.CLASS_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CLASS_ITEM,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.BEGIN_KEYWORDS,
                                VerilogNodeType.CLASS_DECLARATION,
                                VerilogNodeType.CLASS_METHOD,
                                VerilogNodeType.CLASS_PROPERTY,
                                VerilogNodeType.CONSTRAINT_DECLARATION,
                                VerilogNodeType.CONSTRAINT_PROTOTYPE,
                                VerilogNodeType.COVERGROUP_DECLARATION,
                                VerilogNodeType.DEFAULT_NETTYPE_COMPILER_DIRECTIVE,
                                VerilogNodeType.ID_DIRECTIVE,
                                VerilogNodeType.INCLUDE_COMPILER_DIRECTIVE,
                                VerilogNodeType.LINE_COMPILER_DIRECTIVE,
                                VerilogNodeType.LOCAL_PARAMETER_DECLARATION,
                                VerilogNodeType.PARAMETER_DECLARATION,
                                VerilogNodeType.TEXT_MACRO_DEFINITION,
                                VerilogNodeType.TEXT_MACRO_USAGE,
                                VerilogNodeType.TIMESCALE_COMPILER_DIRECTIVE,
                                VerilogNodeType.UNCONNECTED_DRIVE,
                                VerilogNodeType.ZERO_DIRECTIVE)));
        out.put(
                VerilogNodeType.CLASS_METHOD,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_CONSTRUCTOR_DECLARATION,
                                VerilogNodeType.CLASS_CONSTRUCTOR_PROTOTYPE,
                                VerilogNodeType.CLASS_ITEM_QUALIFIER,
                                VerilogNodeType.FUNCTION_DECLARATION,
                                VerilogNodeType.FUNCTION_PROTOTYPE,
                                VerilogNodeType.METHOD_QUALIFIER,
                                VerilogNodeType.TASK_DECLARATION,
                                VerilogNodeType.TASK_PROTOTYPE)));
        out.put(
                VerilogNodeType.CLASS_NEW,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT)));
        out.put(
                VerilogNodeType.CLASS_PROPERTY,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_ITEM_QUALIFIER,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONST_IDENTIFIER,
                                VerilogNodeType.DATA_DECLARATION,
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.RANDOM_QUALIFIER)));
        out.put(
                VerilogNodeType.CLASS_QUALIFIER,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CLASS_SCOPE, VerilogNodeType.IMPLICIT_CLASS_HANDLE)));
        out.put(VerilogNodeType.CLASS_SCOPE, new ChildInfo(true, false, Set.of(VerilogNodeType.CLASS_TYPE)));
        out.put(
                VerilogNodeType.CLASS_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_IDENTIFIER,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PARAMETER_VALUE_ASSIGNMENT)));
        out.put(
                VerilogNodeType.CLOCKING_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLOCKING_EVENT,
                                VerilogNodeType.CLOCKING_IDENTIFIER,
                                VerilogNodeType.CLOCKING_ITEM)));
        out.put(
                VerilogNodeType.CLOCKING_DECL_ASSIGN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(VerilogNodeType.CLOCKING_DIRECTION, new ChildInfo(false, true, Set.of(VerilogNodeType.CLOCKING_SKEW)));
        out.put(
                VerilogNodeType.CLOCKING_DRIVE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLOCKVAR_EXPRESSION,
                                VerilogNodeType.CYCLE_DELAY,
                                VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.CLOCKING_EVENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CLOCKING_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.CLOCKING_IDENTIFIER,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CLOCKING_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CLOCKING_DIRECTION,
                                VerilogNodeType.DEFAULT_SKEW,
                                VerilogNodeType.LET_DECLARATION,
                                VerilogNodeType.LIST_OF_CLOCKING_DECL_ASSIGN,
                                VerilogNodeType.PROPERTY_DECLARATION,
                                VerilogNodeType.SEQUENCE_DECLARATION)));
        out.put(
                VerilogNodeType.CLOCKING_SKEW,
                new ChildInfo(true, true, Set.of(VerilogNodeType.DELAY_CONTROL, VerilogNodeType.EDGE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CLOCKVAR,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CLOCKVAR_EXPRESSION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CLOCKVAR, VerilogNodeType.SELECT1)));
        out.put(
                VerilogNodeType.CMOS_SWITCH_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.INPUT_TERMINAL,
                                VerilogNodeType.NAME_OF_INSTANCE,
                                VerilogNodeType.NCONTROL_TERMINAL,
                                VerilogNodeType.OUTPUT_TERMINAL,
                                VerilogNodeType.PCONTROL_TERMINAL)));
        out.put(
                VerilogNodeType.COMBINATIONAL_BODY,
                new ChildInfo(true, true, Set.of(VerilogNodeType.COMBINATIONAL_ENTRY)));
        out.put(
                VerilogNodeType.COMBINATIONAL_ENTRY,
                new ChildInfo(true, true, Set.of(VerilogNodeType.LEVEL_INPUT_LIST, VerilogNodeType.OUTPUT_SYMBOL)));
        out.put(VerilogNodeType.CONCATENATION, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.CONCURRENT_ASSERTION_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSERT_PROPERTY_STATEMENT,
                                VerilogNodeType.ASSUME_PROPERTY_STATEMENT,
                                VerilogNodeType.CHECKER_INSTANTIATION,
                                VerilogNodeType.COVER_PROPERTY_STATEMENT,
                                VerilogNodeType.COVER_SEQUENCE_STATEMENT,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.RESTRICT_PROPERTY_STATEMENT,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CONDITIONAL_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.COND_PREDICATE,
                                VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.CONDITIONAL_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.COND_PREDICATE,
                                VerilogNodeType.STATEMENT_OR_NULL,
                                VerilogNodeType.UNIQUE_PRIORITY)));
        out.put(
                VerilogNodeType.COND_PATTERN,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.PATTERN)));
        out.put(
                VerilogNodeType.COND_PREDICATE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.COND_PATTERN, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.CONSECUTIVE_REPETITION,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CYCLE_DELAY_CONST_RANGE_EXPRESSION)));
        out.put(
                VerilogNodeType.CONSTANT_BIT_SELECT1,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CONSTANT_CONCATENATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CONSTANT_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONSTANT_PRIMARY,
                                VerilogNodeType.UNARY_OPERATOR)));
        out.put(
                VerilogNodeType.CONSTANT_INDEXED_RANGE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CONSTANT_MULTIPLE_CONCATENATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.CONSTANT_CONCATENATION, VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CONSTANT_PARAM_EXPRESSION,
                new ChildInfo(
                        false,
                        false,
                        Set.of(VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION, VerilogNodeType.DATA_TYPE)));
        out.put(
                VerilogNodeType.CONSTANT_PRIMARY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_CONCATENATION,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONSTANT_INDEXED_RANGE,
                                VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION,
                                VerilogNodeType.CONSTANT_MULTIPLE_CONCATENATION,
                                VerilogNodeType.CONSTANT_RANGE,
                                VerilogNodeType.CONSTANT_SELECT1,
                                VerilogNodeType.GENERATE_BLOCK_IDENTIFIER,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PARAMETER_IDENTIFIER,
                                VerilogNodeType.PRIMARY_LITERAL,
                                VerilogNodeType.TYPE_REFERENCE)));
        out.put(VerilogNodeType.CONSTANT_RANGE, new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CONSTANT_SELECT1,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONSTANT_INDEXED_RANGE,
                                VerilogNodeType.CONSTANT_RANGE)));
        out.put(
                VerilogNodeType.CONSTRAINT_BLOCK,
                new ChildInfo(false, true, Set.of(VerilogNodeType.CONSTRAINT_BLOCK_ITEM)));
        out.put(
                VerilogNodeType.CONSTRAINT_BLOCK_ITEM,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.CONSTRAINT_EXPRESSION, VerilogNodeType.SOLVE_BEFORE_LIST)));
        out.put(
                VerilogNodeType.CONSTRAINT_DECLARATION,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.CONSTRAINT_BLOCK, VerilogNodeType.CONSTRAINT_IDENTIFIER)));
        out.put(
                VerilogNodeType.CONSTRAINT_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTRAINT_PRIMARY,
                                VerilogNodeType.CONSTRAINT_SET,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.LOOP_VARIABLES1,
                                VerilogNodeType.PS_OR_HIERARCHICAL_ARRAY_IDENTIFIER,
                                VerilogNodeType.UNIQUENESS_CONSTRAINT)));
        out.put(
                VerilogNodeType.CONSTRAINT_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.CONSTRAINT_IDENTIFIER,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CONSTRAINT_PRIMARY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.IMPLICIT_CLASS_HANDLE,
                                VerilogNodeType.SELECT1,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CONSTRAINT_PROTOTYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.CONSTRAINT_IDENTIFIER, VerilogNodeType.CONSTRAINT_PROTOTYPE_QUALIFIER)));
        out.put(
                VerilogNodeType.CONSTRAINT_SET,
                new ChildInfo(false, true, Set.of(VerilogNodeType.CONSTRAINT_EXPRESSION)));
        out.put(
                VerilogNodeType.CONST_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.CONST_IDENTIFIER,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CONTINUOUS_ASSIGN,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DELAY3,
                                VerilogNodeType.DELAY_CONTROL,
                                VerilogNodeType.DRIVE_STRENGTH,
                                VerilogNodeType.LIST_OF_NET_ASSIGNMENTS,
                                VerilogNodeType.LIST_OF_VARIABLE_ASSIGNMENTS)));
        out.put(
                VerilogNodeType.CONTROLLED_REFERENCE_EVENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONTROLLED_REFERENCE_EVENT,
                                VerilogNodeType.SPECIFY_INPUT_TERMINAL_DESCRIPTOR,
                                VerilogNodeType.SPECIFY_OUTPUT_TERMINAL_DESCRIPTOR,
                                VerilogNodeType.TIMING_CHECK_CONDITION,
                                VerilogNodeType.TIMING_CHECK_EVENT_CONTROL)));
        out.put(
                VerilogNodeType.COVERAGE_EVENT,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                VerilogNodeType.BLOCK_EVENT_EXPRESSION,
                                VerilogNodeType.CLOCKING_EVENT,
                                VerilogNodeType.TF_PORT_LIST)));
        out.put(
                VerilogNodeType.COVERAGE_OPTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.MEMBER_IDENTIFIER)));
        out.put(
                VerilogNodeType.COVERAGE_SPEC_OR_OPTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.COVERAGE_OPTION,
                                VerilogNodeType.COVER_CROSS,
                                VerilogNodeType.COVER_POINT)));
        out.put(
                VerilogNodeType.COVERGROUP_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.COVERAGE_EVENT,
                                VerilogNodeType.COVERAGE_SPEC_OR_OPTION,
                                VerilogNodeType.COVERGROUP_IDENTIFIER,
                                VerilogNodeType.TF_PORT_LIST)));
        out.put(
                VerilogNodeType.COVERGROUP_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.COVERGROUP_RANGE_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.COVERGROUP_VALUE_RANGE)));
        out.put(VerilogNodeType.COVERGROUP_VALUE_RANGE, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.COVER_CROSS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CROSS_BODY,
                                VerilogNodeType.CROSS_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.LIST_OF_CROSS_ITEMS)));
        out.put(
                VerilogNodeType.COVER_POINT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BINS_OR_EMPTY,
                                VerilogNodeType.COVER_POINT_IDENTIFIER,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.COVER_POINT_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.COVER_PROPERTY_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PROPERTY_SPEC, VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.COVER_SEQUENCE_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLOCKING_EVENT,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.SEQUENCE_EXPR,
                                VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(VerilogNodeType.CROSS_BODY, new ChildInfo(false, true, Set.of(VerilogNodeType.CROSS_BODY_ITEM)));
        out.put(
                VerilogNodeType.CROSS_BODY_ITEM,
                new ChildInfo(
                        true,
                        false,
                        Set.of(VerilogNodeType.BINS_SELECTION_OR_OPTION, VerilogNodeType.FUNCTION_DECLARATION)));
        out.put(
                VerilogNodeType.CROSS_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CYCLE_DELAY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.INTEGRAL_NUMBER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.CYCLE_DELAY_CONST_RANGE_EXPRESSION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.CYCLE_DELAY_RANGE,
                new ChildInfo(
                        false,
                        false,
                        Set.of(VerilogNodeType.CONSTANT_PRIMARY, VerilogNodeType.CYCLE_DELAY_CONST_RANGE_EXPRESSION)));
        out.put(
                VerilogNodeType.DATA_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.LIST_OF_VARIABLE_DECL_ASSIGNMENTS,
                                VerilogNodeType.NET_TYPE_DECLARATION,
                                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                                VerilogNodeType.TYPE_DECLARATION)));
        out.put(VerilogNodeType.DATA_EVENT, new ChildInfo(true, false, Set.of(VerilogNodeType.TIMING_CHECK_EVENT)));
        out.put(VerilogNodeType.DATA_SOURCE_EXPRESSION, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.DATA_TYPE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CLASS_TYPE,
                                VerilogNodeType.COVERGROUP_IDENTIFIER,
                                VerilogNodeType.ENUM_BASE_TYPE,
                                VerilogNodeType.ENUM_NAME_DECLARATION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.INTEGER_VECTOR_TYPE,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.MODPORT_IDENTIFIER,
                                VerilogNodeType.NON_INTEGER_TYPE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PACKED_DIMENSION,
                                VerilogNodeType.PARAMETER_VALUE_ASSIGNMENT,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.STRUCT_UNION,
                                VerilogNodeType.STRUCT_UNION_MEMBER,
                                VerilogNodeType.TYPE_REFERENCE)));
        out.put(
                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                new ChildInfo(true, false, Set.of(VerilogNodeType.DATA_TYPE, VerilogNodeType.IMPLICIT_DATA_TYPE1)));
        out.put(VerilogNodeType.DATA_TYPE_OR_VOID, new ChildInfo(false, false, Set.of(VerilogNodeType.DATA_TYPE)));
        out.put(VerilogNodeType.DECIMAL_NUMBER, new ChildInfo(false, false, Set.of(VerilogNodeType.UNSIGNED_NUMBER)));
        out.put(
                VerilogNodeType.DEFAULT_NETTYPE_COMPILER_DIRECTIVE,
                new ChildInfo(true, false, Set.of(VerilogNodeType.DEFAULT_NETTYPE_VALUE)));
        out.put(VerilogNodeType.DEFAULT_SKEW, new ChildInfo(true, true, Set.of(VerilogNodeType.CLOCKING_SKEW)));
        out.put(
                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERTION_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERT_STATEMENT,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSUME_STATEMENT,
                                VerilogNodeType.DEFERRED_IMMEDIATE_COVER_STATEMENT,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERT_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ACTION_BLOCK, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.DEFERRED_IMMEDIATE_ASSUME_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ACTION_BLOCK, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.DEFERRED_IMMEDIATE_COVER_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.DEFPARAM_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.DELAY2,
                new ChildInfo(true, true, Set.of(VerilogNodeType.DELAY_VALUE, VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.DELAY3,
                new ChildInfo(true, true, Set.of(VerilogNodeType.DELAY_VALUE, VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.DELAYED_DATA,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION, VerilogNodeType.TERMINAL_IDENTIFIER)));
        out.put(
                VerilogNodeType.DELAYED_REFERENCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION, VerilogNodeType.TERMINAL_IDENTIFIER)));
        out.put(
                VerilogNodeType.DELAY_CONTROL,
                new ChildInfo(true, false, Set.of(VerilogNodeType.DELAY_VALUE, VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.DELAY_OR_EVENT_CONTROL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DELAY_CONTROL,
                                VerilogNodeType.EVENT_CONTROL,
                                VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.DELAY_VALUE,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                VerilogNodeType.PS_IDENTIFIER,
                                VerilogNodeType.REAL_NUMBER,
                                VerilogNodeType.TIME_LITERAL,
                                VerilogNodeType.UNSIGNED_NUMBER)));
        out.put(
                VerilogNodeType.DISABLE_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.DIST_ITEM,
                new ChildInfo(true, true, Set.of(VerilogNodeType.DIST_WEIGHT, VerilogNodeType.VALUE_RANGE)));
        out.put(VerilogNodeType.DIST_LIST, new ChildInfo(true, true, Set.of(VerilogNodeType.DIST_ITEM)));
        out.put(VerilogNodeType.DIST_WEIGHT, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.DPI_FUNCTION_PROTO,
                new ChildInfo(true, false, Set.of(VerilogNodeType.FUNCTION_PROTOTYPE)));
        out.put(
                VerilogNodeType.DPI_IMPORT_EXPORT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.C_IDENTIFIER,
                                VerilogNodeType.DPI_FUNCTION_IMPORT_PROPERTY,
                                VerilogNodeType.DPI_FUNCTION_PROTO,
                                VerilogNodeType.DPI_SPEC_STRING,
                                VerilogNodeType.DPI_TASK_IMPORT_PROPERTY,
                                VerilogNodeType.DPI_TASK_PROTO,
                                VerilogNodeType.FUNCTION_IDENTIFIER,
                                VerilogNodeType.TASK_IDENTIFIER)));
        out.put(VerilogNodeType.DPI_TASK_PROTO, new ChildInfo(true, false, Set.of(VerilogNodeType.TASK_PROTOTYPE)));
        out.put(
                VerilogNodeType.DRIVE_STRENGTH,
                new ChildInfo(true, true, Set.of(VerilogNodeType.STRENGTH0, VerilogNodeType.STRENGTH1)));
        out.put(VerilogNodeType.DYNAMIC_ARRAY_NEW, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.EDGE_CONTROL_SPECIFIER,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EDGE_DESCRIPTOR)));
        out.put(
                VerilogNodeType.EDGE_INDICATOR,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EDGE_SYMBOL, VerilogNodeType.LEVEL_SYMBOL)));
        out.put(
                VerilogNodeType.EDGE_INPUT_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EDGE_INDICATOR, VerilogNodeType.LEVEL_SYMBOL)));
        out.put(
                VerilogNodeType.EDGE_SENSITIVE_PATH_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.FULL_EDGE_SENSITIVE_PATH_DESCRIPTION,
                                VerilogNodeType.PARALLEL_EDGE_SENSITIVE_PATH_DESCRIPTION,
                                VerilogNodeType.PATH_DELAY_VALUE)));
        out.put(
                VerilogNodeType.ELABORATION_SYSTEM_TASK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.FINISH_NUMBER,
                                VerilogNodeType.LIST_OF_ARGUMENTS,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT)));
        out.put(
                VerilogNodeType.ENABLE_GATE_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ENABLE_TERMINAL,
                                VerilogNodeType.INPUT_TERMINAL,
                                VerilogNodeType.NAME_OF_INSTANCE,
                                VerilogNodeType.OUTPUT_TERMINAL)));
        out.put(VerilogNodeType.ENABLE_TERMINAL, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.END_EDGE_OFFSET,
                new ChildInfo(true, false, Set.of(VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.ENUM_BASE_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.INTEGER_VECTOR_TYPE,
                                VerilogNodeType.PACKED_DIMENSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.ENUM_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.ENUM_NAME_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.ENUM_IDENTIFIER,
                                VerilogNodeType.INTEGRAL_NUMBER)));
        out.put(VerilogNodeType.ERROR_LIMIT_VALUE, new ChildInfo(true, false, Set.of(VerilogNodeType.LIMIT_VALUE)));
        out.put(
                VerilogNodeType.EVENT_BASED_FLAG,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.EVENT_CONTROL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.EVENT_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.EDGE_IDENTIFIER,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.EVENT_TRIGGER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.DELAY_OR_EVENT_CONTROL,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.EXPECT_PROPERTY_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ACTION_BLOCK, VerilogNodeType.PROPERTY_SPEC)));
        out.put(
                VerilogNodeType.EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CONDITIONAL_EXPRESSION,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.INC_OR_DEC_EXPRESSION,
                                VerilogNodeType.INSIDE_EXPRESSION,
                                VerilogNodeType.OPERATOR_ASSIGNMENT,
                                VerilogNodeType.PRIMARY,
                                VerilogNodeType.TAGGED_UNION_EXPRESSION,
                                VerilogNodeType.UNARY_OPERATOR)));
        out.put(
                VerilogNodeType.EXPRESSION_OR_DIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.DIST_LIST, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.EXTERN_CONSTRAINT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTRAINT_BLOCK,
                                VerilogNodeType.CONSTRAINT_IDENTIFIER)));
        out.put(
                VerilogNodeType.EXTERN_TF_DECLARATION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.FUNCTION_PROTOTYPE, VerilogNodeType.TASK_PROTOTYPE)));
        out.put(
                VerilogNodeType.FINAL_CONSTRUCT,
                new ChildInfo(true, false, Set.of(VerilogNodeType.FUNCTION_STATEMENT)));
        out.put(
                VerilogNodeType.FORMAL_ARGUMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.DEFAULT_TEXT, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.FORMAL_PORT_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.FOR_INITIALIZATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.FOR_VARIABLE_DECLARATION,
                                VerilogNodeType.LIST_OF_VARIABLE_ASSIGNMENTS)));
        out.put(
                VerilogNodeType.FOR_STEP,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.FUNCTION_SUBROUTINE_CALL,
                                VerilogNodeType.INC_OR_DEC_EXPRESSION,
                                VerilogNodeType.OPERATOR_ASSIGNMENT)));
        out.put(
                VerilogNodeType.FOR_VARIABLE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.FULLSKEW_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.EVENT_BASED_FLAG,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.REMAIN_ACTIVE_FLAG,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.FULL_EDGE_SENSITIVE_PATH_DESCRIPTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_SOURCE_EXPRESSION,
                                VerilogNodeType.EDGE_IDENTIFIER,
                                VerilogNodeType.LIST_OF_PATH_INPUTS,
                                VerilogNodeType.LIST_OF_PATH_OUTPUTS,
                                VerilogNodeType.POLARITY_OPERATOR)));
        out.put(
                VerilogNodeType.FULL_PATH_DESCRIPTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.LIST_OF_PATH_INPUTS,
                                VerilogNodeType.LIST_OF_PATH_OUTPUTS,
                                VerilogNodeType.POLARITY_OPERATOR)));
        out.put(
                VerilogNodeType.FUNCTION_BODY_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BLOCK_ITEM_DECLARATION,
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.FUNCTION_DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.FUNCTION_IDENTIFIER,
                                VerilogNodeType.FUNCTION_STATEMENT_OR_NULL,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.TF_ITEM_DECLARATION,
                                VerilogNodeType.TF_PORT_LIST)));
        out.put(
                VerilogNodeType.FUNCTION_DATA_TYPE_OR_IMPLICIT1,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.DATA_TYPE_OR_VOID, VerilogNodeType.IMPLICIT_DATA_TYPE1)));
        out.put(
                VerilogNodeType.FUNCTION_DECLARATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.FUNCTION_BODY_DECLARATION, VerilogNodeType.LIFETIME)));
        out.put(
                VerilogNodeType.FUNCTION_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.FUNCTION_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.FUNCTION_PROTOTYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE_OR_VOID,
                                VerilogNodeType.FUNCTION_IDENTIFIER,
                                VerilogNodeType.TF_PORT_LIST)));
        out.put(VerilogNodeType.FUNCTION_STATEMENT, new ChildInfo(true, false, Set.of(VerilogNodeType.STATEMENT)));
        out.put(
                VerilogNodeType.FUNCTION_STATEMENT_OR_NULL,
                new ChildInfo(
                        false, true, Set.of(VerilogNodeType.ATTRIBUTE_INSTANCE, VerilogNodeType.FUNCTION_STATEMENT)));
        out.put(
                VerilogNodeType.FUNCTION_SUBROUTINE_CALL,
                new ChildInfo(true, false, Set.of(VerilogNodeType.SUBROUTINE_CALL)));
        out.put(
                VerilogNodeType.GATE_INSTANTIATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CMOS_SWITCHTYPE,
                                VerilogNodeType.CMOS_SWITCH_INSTANCE,
                                VerilogNodeType.DELAY2,
                                VerilogNodeType.DELAY3,
                                VerilogNodeType.DRIVE_STRENGTH,
                                VerilogNodeType.ENABLE_GATETYPE,
                                VerilogNodeType.ENABLE_GATE_INSTANCE,
                                VerilogNodeType.MOS_SWITCHTYPE,
                                VerilogNodeType.MOS_SWITCH_INSTANCE,
                                VerilogNodeType.N_INPUT_GATETYPE,
                                VerilogNodeType.N_INPUT_GATE_INSTANCE,
                                VerilogNodeType.N_OUTPUT_GATETYPE,
                                VerilogNodeType.N_OUTPUT_GATE_INSTANCE,
                                VerilogNodeType.PASS_ENABLE_SWITCH_INSTANCE,
                                VerilogNodeType.PASS_EN_SWITCHTYPE,
                                VerilogNodeType.PASS_SWITCHTYPE,
                                VerilogNodeType.PASS_SWITCH_INSTANCE,
                                VerilogNodeType.PULLDOWN_STRENGTH,
                                VerilogNodeType.PULLUP_STRENGTH,
                                VerilogNodeType.PULL_GATE_INSTANCE)));
        out.put(
                VerilogNodeType.GENERATE_BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ALWAYS_CONSTRUCT,
                                VerilogNodeType.CASE_GENERATE_CONSTRUCT,
                                VerilogNodeType.CHECKER_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.CONCURRENT_ASSERTION_ITEM,
                                VerilogNodeType.CONTINUOUS_ASSIGN,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERTION_ITEM,
                                VerilogNodeType.ELABORATION_SYSTEM_TASK,
                                VerilogNodeType.FINAL_CONSTRUCT,
                                VerilogNodeType.GENERATE_BLOCK_IDENTIFIER,
                                VerilogNodeType.GENERATE_REGION,
                                VerilogNodeType.IF_GENERATE_CONSTRUCT,
                                VerilogNodeType.INITIAL_CONSTRUCT,
                                VerilogNodeType.INTERFACE_OR_GENERATE_ITEM,
                                VerilogNodeType.LOOP_GENERATE_CONSTRUCT,
                                VerilogNodeType.MODULE_OR_GENERATE_ITEM)));
        out.put(
                VerilogNodeType.GENERATE_BLOCK_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.GENERATE_BLOCK_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.GENERATE_REGION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ALWAYS_CONSTRUCT,
                                VerilogNodeType.CASE_GENERATE_CONSTRUCT,
                                VerilogNodeType.CHECKER_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.CONCURRENT_ASSERTION_ITEM,
                                VerilogNodeType.CONTINUOUS_ASSIGN,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERTION_ITEM,
                                VerilogNodeType.ELABORATION_SYSTEM_TASK,
                                VerilogNodeType.FINAL_CONSTRUCT,
                                VerilogNodeType.GENERATE_REGION,
                                VerilogNodeType.IF_GENERATE_CONSTRUCT,
                                VerilogNodeType.INITIAL_CONSTRUCT,
                                VerilogNodeType.INTERFACE_OR_GENERATE_ITEM,
                                VerilogNodeType.LOOP_GENERATE_CONSTRUCT,
                                VerilogNodeType.MODULE_OR_GENERATE_ITEM)));
        out.put(
                VerilogNodeType.GENVAR_DECLARATION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.LIST_OF_GENVAR_IDENTIFIERS)));
        out.put(
                VerilogNodeType.GENVAR_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.GENVAR_INITIALIZATION,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION, VerilogNodeType.GENVAR_IDENTIFIER)));
        out.put(
                VerilogNodeType.GENVAR_ITERATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSIGNMENT_OPERATOR,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.GENVAR_IDENTIFIER,
                                VerilogNodeType.INC_OR_DEC_OPERATOR)));
        out.put(
                VerilogNodeType.GOTO_REPETITION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CYCLE_DELAY_CONST_RANGE_EXPRESSION)));
        out.put(
                VerilogNodeType.HIERARCHICAL_BTF_IDENTIFIER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.METHOD_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.HIERARCHICAL_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.LIST_OF_PORT_CONNECTIONS, VerilogNodeType.NAME_OF_INSTANCE)));
        out.put(
                VerilogNodeType.HOLD_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.IDENTIFIER_LIST,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.ID_DIRECTIVE,
                new ChildInfo(true, false, Set.of(VerilogNodeType.TEXT_MACRO_IDENTIFIER)));
        out.put(
                VerilogNodeType.IF_GENERATE_CONSTRUCT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION, VerilogNodeType.GENERATE_BLOCK)));
        out.put(
                VerilogNodeType.IMPLICIT_DATA_TYPE1,
                new ChildInfo(false, true, Set.of(VerilogNodeType.PACKED_DIMENSION)));
        out.put(
                VerilogNodeType.INCLUDE_COMPILER_DIRECTIVE,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.DOUBLE_QUOTED_STRING,
                                VerilogNodeType.INCLUDE_COMPILER_DIRECTIVE_STANDARD)));
        out.put(
                VerilogNodeType.INC_OR_DEC_EXPRESSION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.INC_OR_DEC_OPERATOR,
                                VerilogNodeType.VARIABLE_LVALUE)));
        out.put(
                VerilogNodeType.INDEXED_RANGE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_EXPRESSION, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.INDEX_VARIABLE_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INDEX_VARIABLE_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.INITIAL_CONSTRUCT,
                new ChildInfo(true, false, Set.of(VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.INOUT_DECLARATION,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.LIST_OF_PORT_IDENTIFIERS, VerilogNodeType.NET_PORT_TYPE1)));
        out.put(
                VerilogNodeType.INOUT_PORT_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INOUT_PORT_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(VerilogNodeType.INOUT_TERMINAL, new ChildInfo(true, false, Set.of(VerilogNodeType.NET_LVALUE)));
        out.put(
                VerilogNodeType.INPUT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIST_OF_PORT_IDENTIFIERS,
                                VerilogNodeType.LIST_OF_VARIABLE_IDENTIFIERS,
                                VerilogNodeType.NET_PORT_TYPE1)));
        out.put(
                VerilogNodeType.INPUT_IDENTIFIER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.INOUT_PORT_IDENTIFIER,
                                VerilogNodeType.INPUT_PORT_IDENTIFIER,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.INPUT_PORT_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INPUT_PORT_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(VerilogNodeType.INPUT_TERMINAL, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.INSIDE_EXPRESSION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.OPEN_RANGE_LIST)));
        out.put(
                VerilogNodeType.INSTANCE_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.INTEGRAL_NUMBER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.BINARY_NUMBER,
                                VerilogNodeType.DECIMAL_NUMBER,
                                VerilogNodeType.HEX_NUMBER,
                                VerilogNodeType.OCTAL_NUMBER)));
        out.put(
                VerilogNodeType.INTERFACE_ANSI_HEADER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.LIST_OF_PORT_DECLARATIONS,
                                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                                VerilogNodeType.PARAMETER_PORT_LIST)));
        out.put(
                VerilogNodeType.INTERFACE_CLASS_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_IDENTIFIER,
                                VerilogNodeType.INTERFACE_CLASS_ITEM,
                                VerilogNodeType.INTERFACE_CLASS_TYPE,
                                VerilogNodeType.PARAMETER_PORT_LIST)));
        out.put(
                VerilogNodeType.INTERFACE_CLASS_ITEM,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.INTERFACE_CLASS_METHOD,
                                VerilogNodeType.LOCAL_PARAMETER_DECLARATION,
                                VerilogNodeType.PARAMETER_DECLARATION,
                                VerilogNodeType.TYPE_DECLARATION)));
        out.put(
                VerilogNodeType.INTERFACE_CLASS_METHOD,
                new ChildInfo(true, false, Set.of(VerilogNodeType.FUNCTION_PROTOTYPE, VerilogNodeType.TASK_PROTOTYPE)));
        out.put(
                VerilogNodeType.INTERFACE_CLASS_TYPE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_IDENTIFIER,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PARAMETER_VALUE_ASSIGNMENT)));
        out.put(
                VerilogNodeType.INTERFACE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.GENERATE_REGION,
                                VerilogNodeType.INTERFACE_ANSI_HEADER,
                                VerilogNodeType.INTERFACE_DECLARATION,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.INTERFACE_ITEM,
                                VerilogNodeType.INTERFACE_NONANSI_HEADER,
                                VerilogNodeType.INTERFACE_OR_GENERATE_ITEM,
                                VerilogNodeType.MODPORT_DECLARATION,
                                VerilogNodeType.PROGRAM_DECLARATION,
                                VerilogNodeType.TIMEUNITS_DECLARATION)));
        out.put(
                VerilogNodeType.INTERFACE_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.INTERFACE_INSTANCE_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.INTERFACE_INSTANTIATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.HIERARCHICAL_INSTANCE,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.PARAMETER_VALUE_ASSIGNMENT)));
        out.put(
                VerilogNodeType.INTERFACE_ITEM,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.GENERATE_REGION,
                                VerilogNodeType.INTERFACE_DECLARATION,
                                VerilogNodeType.INTERFACE_OR_GENERATE_ITEM,
                                VerilogNodeType.MODPORT_DECLARATION,
                                VerilogNodeType.PORT_DECLARATION,
                                VerilogNodeType.PROGRAM_DECLARATION,
                                VerilogNodeType.TIMEUNITS_DECLARATION)));
        out.put(
                VerilogNodeType.INTERFACE_NONANSI_HEADER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.LIST_OF_PORTS,
                                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                                VerilogNodeType.PARAMETER_PORT_LIST)));
        out.put(
                VerilogNodeType.INTERFACE_OR_GENERATE_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ALWAYS_CONSTRUCT,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.BIND_DIRECTIVE,
                                VerilogNodeType.CASE_GENERATE_CONSTRUCT,
                                VerilogNodeType.CLOCKING_DECLARATION,
                                VerilogNodeType.CLOCKING_IDENTIFIER,
                                VerilogNodeType.CONCURRENT_ASSERTION_ITEM,
                                VerilogNodeType.CONTINUOUS_ASSIGN,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERTION_ITEM,
                                VerilogNodeType.ELABORATION_SYSTEM_TASK,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.EXTERN_TF_DECLARATION,
                                VerilogNodeType.FINAL_CONSTRUCT,
                                VerilogNodeType.GENVAR_DECLARATION,
                                VerilogNodeType.IF_GENERATE_CONSTRUCT,
                                VerilogNodeType.INITIAL_CONSTRUCT,
                                VerilogNodeType.INTERFACE_INSTANTIATION,
                                VerilogNodeType.LOOP_GENERATE_CONSTRUCT,
                                VerilogNodeType.NET_ALIAS,
                                VerilogNodeType.PACKAGE_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.PROGRAM_INSTANTIATION)));
        out.put(
                VerilogNodeType.INTERFACE_PORT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.LIST_OF_INTERFACE_IDENTIFIERS,
                                VerilogNodeType.MODPORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.INTERFACE_PORT_HEADER,
                new ChildInfo(
                        false, true, Set.of(VerilogNodeType.INTERFACE_IDENTIFIER, VerilogNodeType.MODPORT_IDENTIFIER)));
        out.put(VerilogNodeType.JUMP_STATEMENT, new ChildInfo(false, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(VerilogNodeType.LET_ACTUAL_ARG, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.LET_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.LET_PORT_LIST,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.LET_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.LET_LIST_OF_ARGUMENTS,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.LET_FORMAL_TYPE1,
                new ChildInfo(false, false, Set.of(VerilogNodeType.DATA_TYPE_OR_IMPLICIT1)));
        out.put(
                VerilogNodeType.LET_LIST_OF_ARGUMENTS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.LET_ACTUAL_ARG,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.LET_PORT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.FORMAL_PORT_IDENTIFIER,
                                VerilogNodeType.LET_FORMAL_TYPE1,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(VerilogNodeType.LET_PORT_LIST, new ChildInfo(true, true, Set.of(VerilogNodeType.LET_PORT_ITEM)));
        out.put(VerilogNodeType.LEVEL_INPUT_LIST, new ChildInfo(true, true, Set.of(VerilogNodeType.LEVEL_SYMBOL)));
        out.put(
                VerilogNodeType.LIMIT_VALUE,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.LINE_COMPILER_DIRECTIVE,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.DOUBLE_QUOTED_STRING, VerilogNodeType.UNSIGNED_NUMBER)));
        out.put(
                VerilogNodeType.LIST_OF_ACTUAL_ARGUMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.LIST_OF_ARGUMENTS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.LIST_OF_CLOCKING_DECL_ASSIGN,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CLOCKING_DECL_ASSIGN)));
        out.put(
                VerilogNodeType.LIST_OF_CROSS_ITEMS,
                new ChildInfo(false, true, Set.of(VerilogNodeType.COVER_POINT_IDENTIFIER)));
        out.put(
                VerilogNodeType.LIST_OF_DEFPARAM_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.DEFPARAM_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_FORMAL_ARGUMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.FORMAL_ARGUMENT)));
        out.put(
                VerilogNodeType.LIST_OF_GENVAR_IDENTIFIERS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.GENVAR_IDENTIFIER)));
        out.put(
                VerilogNodeType.LIST_OF_INTERFACE_IDENTIFIERS,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.INTERFACE_IDENTIFIER, VerilogNodeType.UNPACKED_DIMENSION)));
        out.put(
                VerilogNodeType.LIST_OF_NET_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.NET_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_NET_DECL_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.NET_DECL_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_PARAMETER_ASSIGNMENTS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.NAMED_PARAMETER_ASSIGNMENT,
                                VerilogNodeType.ORDERED_PARAMETER_ASSIGNMENT_2)));
        out.put(
                VerilogNodeType.LIST_OF_PARAM_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PARAM_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_PATH_DELAY_EXPRESSIONS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PATH_DELAY_EXPRESSION)));
        out.put(
                VerilogNodeType.LIST_OF_PATH_INPUTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.SPECIFY_INPUT_TERMINAL_DESCRIPTOR)));
        out.put(
                VerilogNodeType.LIST_OF_PATH_OUTPUTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.SPECIFY_OUTPUT_TERMINAL_DESCRIPTOR)));
        out.put(
                VerilogNodeType.LIST_OF_PORTS,
                new ChildInfo(false, true, Set.of(VerilogNodeType.LINE_COMPILER_DIRECTIVE, VerilogNodeType.PORT)));
        out.put(
                VerilogNodeType.LIST_OF_PORT_CONNECTIONS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.NAMED_PORT_CONNECTION, VerilogNodeType.ORDERED_PORT_CONNECTION)));
        out.put(
                VerilogNodeType.LIST_OF_PORT_DECLARATIONS,
                new ChildInfo(
                        false,
                        true,
                        Set.of(VerilogNodeType.ANSI_PORT_DECLARATION, VerilogNodeType.ATTRIBUTE_INSTANCE)));
        out.put(
                VerilogNodeType.LIST_OF_PORT_IDENTIFIERS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PORT_IDENTIFIER, VerilogNodeType.UNPACKED_DIMENSION)));
        out.put(
                VerilogNodeType.LIST_OF_SPECPARAM_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.SPECPARAM_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_TF_VARIABLE_IDENTIFIERS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.PORT_IDENTIFIER,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.LIST_OF_TYPE_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.TYPE_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_UDP_PORT_IDENTIFIERS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.LIST_OF_VARIABLE_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.VARIABLE_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_VARIABLE_DECL_ASSIGNMENTS,
                new ChildInfo(true, true, Set.of(VerilogNodeType.VARIABLE_DECL_ASSIGNMENT)));
        out.put(
                VerilogNodeType.LIST_OF_VARIABLE_IDENTIFIERS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.LIST_OF_VARIABLE_PORT_IDENTIFIERS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.PORT_IDENTIFIER,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.LOCAL_PARAMETER_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIST_OF_PARAM_ASSIGNMENTS,
                                VerilogNodeType.LIST_OF_TYPE_ASSIGNMENTS)));
        out.put(
                VerilogNodeType.LOOP_GENERATE_CONSTRUCT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.GENERATE_BLOCK,
                                VerilogNodeType.GENVAR_INITIALIZATION,
                                VerilogNodeType.GENVAR_ITERATION)));
        out.put(
                VerilogNodeType.LOOP_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.FOR_INITIALIZATION,
                                VerilogNodeType.FOR_STEP,
                                VerilogNodeType.LOOP_VARIABLES1,
                                VerilogNodeType.PS_OR_HIERARCHICAL_ARRAY_IDENTIFIER,
                                VerilogNodeType.STATEMENT,
                                VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.LOOP_VARIABLES1,
                new ChildInfo(true, true, Set.of(VerilogNodeType.INDEX_VARIABLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.MEMBER_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.METHOD_CALL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.IMPLICIT_CLASS_HANDLE,
                                VerilogNodeType.METHOD_CALL_BODY,
                                VerilogNodeType.PRIMARY)));
        out.put(
                VerilogNodeType.METHOD_CALL_BODY,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ARRAY_MANIPULATION_CALL,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT,
                                VerilogNodeType.METHOD_IDENTIFIER,
                                VerilogNodeType.RANDOMIZE_CALL)));
        out.put(
                VerilogNodeType.METHOD_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.METHOD_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.METHOD_QUALIFIER,
                new ChildInfo(false, false, Set.of(VerilogNodeType.CLASS_ITEM_QUALIFIER)));
        out.put(VerilogNodeType.MINTYPMAX_EXPRESSION, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.MODPORT_CLOCKING_DECLARATION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CLOCKING_IDENTIFIER)));
        out.put(VerilogNodeType.MODPORT_DECLARATION, new ChildInfo(true, true, Set.of(VerilogNodeType.MODPORT_ITEM)));
        out.put(
                VerilogNodeType.MODPORT_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.MODPORT_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.MODPORT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.MODPORT_IDENTIFIER, VerilogNodeType.MODPORT_PORTS_DECLARATION)));
        out.put(
                VerilogNodeType.MODPORT_PORTS_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.MODPORT_CLOCKING_DECLARATION,
                                VerilogNodeType.MODPORT_SIMPLE_PORTS_DECLARATION,
                                VerilogNodeType.MODPORT_TF_PORTS_DECLARATION)));
        out.put(
                VerilogNodeType.MODPORT_SIMPLE_PORT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.MODPORT_SIMPLE_PORTS_DECLARATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.MODPORT_SIMPLE_PORT, VerilogNodeType.PORT_DIRECTION)));
        out.put(
                VerilogNodeType.MODPORT_TF_PORTS_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.FUNCTION_PROTOTYPE,
                                VerilogNodeType.IMPORT_EXPORT,
                                VerilogNodeType.TASK_PROTOTYPE,
                                VerilogNodeType.TF_IDENTIFIER)));
        out.put(
                VerilogNodeType.MODULE_ANSI_HEADER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.LIST_OF_PORT_DECLARATIONS,
                                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                                VerilogNodeType.PARAMETER_PORT_LIST)));
        out.put(
                VerilogNodeType.MODULE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.BEGIN_KEYWORDS,
                                VerilogNodeType.DEFAULT_NETTYPE_COMPILER_DIRECTIVE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.GENERATE_REGION,
                                VerilogNodeType.ID_DIRECTIVE,
                                VerilogNodeType.INCLUDE_COMPILER_DIRECTIVE,
                                VerilogNodeType.INTERFACE_DECLARATION,
                                VerilogNodeType.LINE_COMPILER_DIRECTIVE,
                                VerilogNodeType.MODULE_ANSI_HEADER,
                                VerilogNodeType.MODULE_DECLARATION,
                                VerilogNodeType.MODULE_HEADER,
                                VerilogNodeType.MODULE_NONANSI_HEADER,
                                VerilogNodeType.MODULE_OR_GENERATE_ITEM,
                                VerilogNodeType.PORT_DECLARATION,
                                VerilogNodeType.PROGRAM_DECLARATION,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.SPECIFY_BLOCK,
                                VerilogNodeType.SPECPARAM_DECLARATION,
                                VerilogNodeType.TEXT_MACRO_DEFINITION,
                                VerilogNodeType.TEXT_MACRO_USAGE,
                                VerilogNodeType.TIMESCALE_COMPILER_DIRECTIVE,
                                VerilogNodeType.TIMEUNITS_DECLARATION,
                                VerilogNodeType.UNCONNECTED_DRIVE,
                                VerilogNodeType.ZERO_DIRECTIVE)));
        out.put(
                VerilogNodeType.MODULE_HEADER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.MODULE_KEYWORD,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.MODULE_INSTANTIATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.HIERARCHICAL_INSTANCE,
                                VerilogNodeType.PARAMETER_VALUE_ASSIGNMENT,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.MODULE_NONANSI_HEADER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.LIST_OF_PORTS,
                                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                                VerilogNodeType.PARAMETER_PORT_LIST)));
        out.put(
                VerilogNodeType.MODULE_OR_GENERATE_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ALWAYS_CONSTRUCT,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.BIND_DIRECTIVE,
                                VerilogNodeType.CASE_GENERATE_CONSTRUCT,
                                VerilogNodeType.CLOCKING_DECLARATION,
                                VerilogNodeType.CLOCKING_IDENTIFIER,
                                VerilogNodeType.CONCURRENT_ASSERTION_ITEM,
                                VerilogNodeType.CONTINUOUS_ASSIGN,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERTION_ITEM,
                                VerilogNodeType.ELABORATION_SYSTEM_TASK,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.FINAL_CONSTRUCT,
                                VerilogNodeType.GATE_INSTANTIATION,
                                VerilogNodeType.GENVAR_DECLARATION,
                                VerilogNodeType.IF_GENERATE_CONSTRUCT,
                                VerilogNodeType.INITIAL_CONSTRUCT,
                                VerilogNodeType.INTERFACE_INSTANTIATION,
                                VerilogNodeType.LOOP_GENERATE_CONSTRUCT,
                                VerilogNodeType.MODULE_INSTANTIATION,
                                VerilogNodeType.NET_ALIAS,
                                VerilogNodeType.PACKAGE_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.PARAMETER_OVERRIDE,
                                VerilogNodeType.PROGRAM_INSTANTIATION,
                                VerilogNodeType.UDP_INSTANTIATION)));
        out.put(
                VerilogNodeType.MODULE_PATH_CONCATENATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.MODULE_PATH_EXPRESSION)));
        out.put(
                VerilogNodeType.MODULE_PATH_EXPRESSION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.MODULE_PATH_PRIMARY)));
        out.put(
                VerilogNodeType.MODULE_PATH_MINTYPMAX_EXPRESSION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.MODULE_PATH_EXPRESSION)));
        out.put(
                VerilogNodeType.MODULE_PATH_MULTIPLE_CONCATENATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.CONSTANT_EXPRESSION, VerilogNodeType.MODULE_PATH_CONCATENATION)));
        out.put(
                VerilogNodeType.MODULE_PATH_PRIMARY,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.FUNCTION_SUBROUTINE_CALL,
                                VerilogNodeType.INTEGRAL_NUMBER,
                                VerilogNodeType.MODULE_PATH_CONCATENATION,
                                VerilogNodeType.MODULE_PATH_MINTYPMAX_EXPRESSION,
                                VerilogNodeType.MODULE_PATH_MULTIPLE_CONCATENATION,
                                VerilogNodeType.REAL_NUMBER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.MOS_SWITCH_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ENABLE_TERMINAL,
                                VerilogNodeType.INPUT_TERMINAL,
                                VerilogNodeType.NAME_OF_INSTANCE,
                                VerilogNodeType.OUTPUT_TERMINAL)));
        out.put(
                VerilogNodeType.MULTIPLE_CONCATENATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONCATENATION, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.NAMED_PARAMETER_ASSIGNMENT,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.PARAMETER_IDENTIFIER, VerilogNodeType.PARAM_EXPRESSION)));
        out.put(
                VerilogNodeType.NAMED_PORT_CONNECTION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.NAME_OF_INSTANCE,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.INSTANCE_IDENTIFIER, VerilogNodeType.UNPACKED_DIMENSION)));
        out.put(VerilogNodeType.NCONTROL_TERMINAL, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(VerilogNodeType.NET_ALIAS, new ChildInfo(true, true, Set.of(VerilogNodeType.NET_LVALUE)));
        out.put(
                VerilogNodeType.NET_ASSIGNMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.NET_LVALUE)));
        out.put(
                VerilogNodeType.NET_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CHARGE_STRENGTH,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.DELAY3,
                                VerilogNodeType.DELAY_CONTROL,
                                VerilogNodeType.DELAY_VALUE,
                                VerilogNodeType.DRIVE_STRENGTH,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.IMPLICIT_DATA_TYPE1,
                                VerilogNodeType.LIST_OF_NET_DECL_ASSIGNMENTS,
                                VerilogNodeType.NET_TYPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UNPACKED_DIMENSION)));
        out.put(
                VerilogNodeType.NET_DECL_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UNPACKED_DIMENSION)));
        out.put(
                VerilogNodeType.NET_LVALUE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ASSIGNMENT_PATTERN_NET_LVALUE,
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.CONSTANT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.NET_LVALUE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.TYPE_REFERENCE)));
        out.put(
                VerilogNodeType.NET_PORT_HEADER1,
                new ChildInfo(true, true, Set.of(VerilogNodeType.NET_PORT_TYPE1, VerilogNodeType.PORT_DIRECTION)));
        out.put(
                VerilogNodeType.NET_PORT_TYPE1,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.IMPLICIT_DATA_TYPE1,
                                VerilogNodeType.NET_TYPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.NET_TYPE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.TF_IDENTIFIER)));
        out.put(VerilogNodeType.NEXT_STATE, new ChildInfo(false, false, Set.of(VerilogNodeType.OUTPUT_SYMBOL)));
        out.put(
                VerilogNodeType.NOCHANGE_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.END_EDGE_OFFSET,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.START_EDGE_OFFSET)));
        out.put(
                VerilogNodeType.NONBLOCKING_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DELAY_OR_EVENT_CONTROL,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.VARIABLE_LVALUE)));
        out.put(
                VerilogNodeType.NONRANGE_SELECT1,
                new ChildInfo(true, true, Set.of(VerilogNodeType.BIT_SELECT1, VerilogNodeType.MEMBER_IDENTIFIER)));
        out.put(
                VerilogNodeType.NONRANGE_VARIABLE_LVALUE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.IMPLICIT_CLASS_HANDLE,
                                VerilogNodeType.NONRANGE_SELECT1,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.NON_CONSECUTIVE_REPETITION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CYCLE_DELAY_CONST_RANGE_EXPRESSION)));
        out.put(
                VerilogNodeType.NON_PORT_PROGRAM_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CASE_GENERATE_CONSTRUCT,
                                VerilogNodeType.CLOCKING_DECLARATION,
                                VerilogNodeType.CLOCKING_IDENTIFIER,
                                VerilogNodeType.CONCURRENT_ASSERTION_ITEM,
                                VerilogNodeType.CONTINUOUS_ASSIGN,
                                VerilogNodeType.ELABORATION_SYSTEM_TASK,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.FINAL_CONSTRUCT,
                                VerilogNodeType.GENERATE_REGION,
                                VerilogNodeType.GENVAR_DECLARATION,
                                VerilogNodeType.IF_GENERATE_CONSTRUCT,
                                VerilogNodeType.INITIAL_CONSTRUCT,
                                VerilogNodeType.LOOP_GENERATE_CONSTRUCT,
                                VerilogNodeType.PACKAGE_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.TIMEUNITS_DECLARATION)));
        out.put(
                VerilogNodeType.NOTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.N_INPUT_GATE_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.INPUT_TERMINAL,
                                VerilogNodeType.NAME_OF_INSTANCE,
                                VerilogNodeType.OUTPUT_TERMINAL)));
        out.put(
                VerilogNodeType.N_OUTPUT_GATE_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.INPUT_TERMINAL,
                                VerilogNodeType.NAME_OF_INSTANCE,
                                VerilogNodeType.OUTPUT_TERMINAL)));
        out.put(VerilogNodeType.OPEN_RANGE_LIST, new ChildInfo(true, true, Set.of(VerilogNodeType.OPEN_VALUE_RANGE)));
        out.put(VerilogNodeType.OPEN_VALUE_RANGE, new ChildInfo(true, false, Set.of(VerilogNodeType.VALUE_RANGE)));
        out.put(
                VerilogNodeType.OPERATOR_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSIGNMENT_OPERATOR,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.VARIABLE_LVALUE)));
        out.put(
                VerilogNodeType.ORDERED_PARAMETER_ASSIGNMENT,
                new ChildInfo(false, false, Set.of(VerilogNodeType.DATA_TYPE, VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.ORDERED_PARAMETER_ASSIGNMENT_2,
                new ChildInfo(true, false, Set.of(VerilogNodeType.ORDERED_PARAMETER_ASSIGNMENT)));
        out.put(
                VerilogNodeType.ORDERED_PORT_CONNECTION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ATTRIBUTE_INSTANCE, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.OUTPUT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIST_OF_PORT_IDENTIFIERS,
                                VerilogNodeType.LIST_OF_VARIABLE_PORT_IDENTIFIERS,
                                VerilogNodeType.NET_PORT_TYPE1)));
        out.put(
                VerilogNodeType.OUTPUT_IDENTIFIER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.INOUT_PORT_IDENTIFIER,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.OUTPUT_PORT_IDENTIFIER,
                                VerilogNodeType.PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.OUTPUT_PORT_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.OUTPUT_PORT_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(VerilogNodeType.OUTPUT_TERMINAL, new ChildInfo(true, false, Set.of(VerilogNodeType.NET_LVALUE)));
        out.put(
                VerilogNodeType.OVERLOAD_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.FUNCTION_IDENTIFIER,
                                VerilogNodeType.OVERLOAD_OPERATOR,
                                VerilogNodeType.OVERLOAD_PROTO_FORMALS)));
        out.put(VerilogNodeType.OVERLOAD_PROTO_FORMALS, new ChildInfo(true, true, Set.of(VerilogNodeType.DATA_TYPE)));
        out.put(
                VerilogNodeType.PACKAGE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ANONYMOUS_PROGRAM,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.PACKAGE_EXPORT_DECLARATION,
                                VerilogNodeType.PACKAGE_IDENTIFIER,
                                VerilogNodeType.PACKAGE_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.TIMEUNITS_DECLARATION)));
        out.put(
                VerilogNodeType.PACKAGE_EXPORT_DECLARATION,
                new ChildInfo(false, true, Set.of(VerilogNodeType.PACKAGE_IMPORT_ITEM)));
        out.put(
                VerilogNodeType.PACKAGE_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PACKAGE_IMPORT_ITEM)));
        out.put(
                VerilogNodeType.PACKAGE_IMPORT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.PACKAGE_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PACKAGE_OR_GENERATE_ITEM_DECLARATION,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                VerilogNodeType.CHECKER_DECLARATION,
                                VerilogNodeType.CLASS_CONSTRUCTOR_DECLARATION,
                                VerilogNodeType.CLASS_DECLARATION,
                                VerilogNodeType.COVERGROUP_DECLARATION,
                                VerilogNodeType.DATA_DECLARATION,
                                VerilogNodeType.DPI_IMPORT_EXPORT,
                                VerilogNodeType.EXTERN_CONSTRAINT_DECLARATION,
                                VerilogNodeType.FUNCTION_DECLARATION,
                                VerilogNodeType.INTERFACE_CLASS_DECLARATION,
                                VerilogNodeType.LET_DECLARATION,
                                VerilogNodeType.LOCAL_PARAMETER_DECLARATION,
                                VerilogNodeType.NET_DECLARATION,
                                VerilogNodeType.OVERLOAD_DECLARATION,
                                VerilogNodeType.PARAMETER_DECLARATION,
                                VerilogNodeType.PROPERTY_DECLARATION,
                                VerilogNodeType.SEQUENCE_DECLARATION,
                                VerilogNodeType.TASK_DECLARATION)));
        out.put(VerilogNodeType.PACKAGE_SCOPE, new ChildInfo(false, false, Set.of(VerilogNodeType.PACKAGE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PACKED_DIMENSION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CONSTANT_RANGE, VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.PARALLEL_EDGE_SENSITIVE_PATH_DESCRIPTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_SOURCE_EXPRESSION,
                                VerilogNodeType.EDGE_IDENTIFIER,
                                VerilogNodeType.POLARITY_OPERATOR,
                                VerilogNodeType.SPECIFY_INPUT_TERMINAL_DESCRIPTOR,
                                VerilogNodeType.SPECIFY_OUTPUT_TERMINAL_DESCRIPTOR)));
        out.put(
                VerilogNodeType.PARALLEL_PATH_DESCRIPTION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.POLARITY_OPERATOR,
                                VerilogNodeType.SPECIFY_INPUT_TERMINAL_DESCRIPTOR,
                                VerilogNodeType.SPECIFY_OUTPUT_TERMINAL_DESCRIPTOR)));
        out.put(
                VerilogNodeType.PARAMETER_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIST_OF_PARAM_ASSIGNMENTS,
                                VerilogNodeType.LIST_OF_TYPE_ASSIGNMENTS)));
        out.put(
                VerilogNodeType.PARAMETER_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PARAMETER_OVERRIDE,
                new ChildInfo(true, false, Set.of(VerilogNodeType.LIST_OF_DEFPARAM_ASSIGNMENTS)));
        out.put(
                VerilogNodeType.PARAMETER_PORT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.LIST_OF_PARAM_ASSIGNMENTS,
                                VerilogNodeType.LIST_OF_TYPE_ASSIGNMENTS,
                                VerilogNodeType.LOCAL_PARAMETER_DECLARATION,
                                VerilogNodeType.PARAMETER_DECLARATION)));
        out.put(
                VerilogNodeType.PARAMETER_PORT_LIST,
                new ChildInfo(
                        false,
                        true,
                        Set.of(VerilogNodeType.LIST_OF_PARAM_ASSIGNMENTS, VerilogNodeType.PARAMETER_PORT_DECLARATION)));
        out.put(
                VerilogNodeType.PARAMETER_VALUE_ASSIGNMENT,
                new ChildInfo(false, false, Set.of(VerilogNodeType.LIST_OF_PARAMETER_ASSIGNMENTS)));
        out.put(
                VerilogNodeType.PARAM_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_PARAM_EXPRESSION,
                                VerilogNodeType.PARAMETER_IDENTIFIER,
                                VerilogNodeType.UNPACKED_DIMENSION)));
        out.put(
                VerilogNodeType.PARAM_EXPRESSION,
                new ChildInfo(false, false, Set.of(VerilogNodeType.DATA_TYPE, VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.PAR_BLOCK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BLOCK_ITEM_DECLARATION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.JOIN_KEYWORD,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.PASS_ENABLE_SWITCH_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ENABLE_TERMINAL,
                                VerilogNodeType.INOUT_TERMINAL,
                                VerilogNodeType.NAME_OF_INSTANCE)));
        out.put(
                VerilogNodeType.PASS_SWITCH_INSTANCE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.INOUT_TERMINAL, VerilogNodeType.NAME_OF_INSTANCE)));
        out.put(
                VerilogNodeType.PATH_DECLARATION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.EDGE_SENSITIVE_PATH_DECLARATION,
                                VerilogNodeType.SIMPLE_PATH_DECLARATION,
                                VerilogNodeType.STATE_DEPENDENT_PATH_DECLARATION)));
        out.put(
                VerilogNodeType.PATH_DELAY_EXPRESSION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.PATH_DELAY_VALUE,
                new ChildInfo(true, false, Set.of(VerilogNodeType.LIST_OF_PATH_DELAY_EXPRESSIONS)));
        out.put(
                VerilogNodeType.PATTERN,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.MEMBER_IDENTIFIER,
                                VerilogNodeType.PATTERN,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(VerilogNodeType.PCONTROL_TERMINAL, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.PERIOD_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONTROLLED_REFERENCE_EVENT,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.PORT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PORT_IDENTIFIER, VerilogNodeType.PORT_REFERENCE)));
        out.put(
                VerilogNodeType.PORT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.INOUT_DECLARATION,
                                VerilogNodeType.INPUT_DECLARATION,
                                VerilogNodeType.INTERFACE_PORT_DECLARATION,
                                VerilogNodeType.OUTPUT_DECLARATION,
                                VerilogNodeType.REF_DECLARATION)));
        out.put(
                VerilogNodeType.PORT_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PORT_REFERENCE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTANT_SELECT1, VerilogNodeType.PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.PRIMARY,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ASSIGNMENT_PATTERN_EXPRESSION,
                                VerilogNodeType.CAST,
                                VerilogNodeType.CLASS_QUALIFIER,
                                VerilogNodeType.CONCATENATION,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.EMPTY_UNPACKED_ARRAY_CONCATENATION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.FUNCTION_SUBROUTINE_CALL,
                                VerilogNodeType.LET_EXPRESSION,
                                VerilogNodeType.MINTYPMAX_EXPRESSION,
                                VerilogNodeType.MULTIPLE_CONCATENATION,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PRIMARY_LITERAL,
                                VerilogNodeType.RANGE_EXPRESSION,
                                VerilogNodeType.SELECT1,
                                VerilogNodeType.SEQUENCE_METHOD_CALL,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.STREAMING_CONCATENATION)));
        out.put(
                VerilogNodeType.PRIMARY_LITERAL,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.INTEGRAL_NUMBER,
                                VerilogNodeType.REAL_NUMBER,
                                VerilogNodeType.SIMPLE_TEXT_MACRO_USAGE,
                                VerilogNodeType.STRING_LITERAL,
                                VerilogNodeType.TIME_LITERAL,
                                VerilogNodeType.UNBASED_UNSIZED_LITERAL)));
        out.put(
                VerilogNodeType.PROCEDURAL_CONTINUOUS_ASSIGNMENT,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.NET_ASSIGNMENT,
                                VerilogNodeType.NET_LVALUE,
                                VerilogNodeType.VARIABLE_ASSIGNMENT,
                                VerilogNodeType.VARIABLE_LVALUE)));
        out.put(
                VerilogNodeType.PROCEDURAL_TIMING_CONTROL_STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CYCLE_DELAY,
                                VerilogNodeType.DELAY_CONTROL,
                                VerilogNodeType.EVENT_CONTROL,
                                VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.PROGRAM_ANSI_HEADER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.LIST_OF_PORT_DECLARATIONS,
                                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                                VerilogNodeType.PARAMETER_PORT_LIST,
                                VerilogNodeType.PROGRAM_IDENTIFIER)));
        out.put(
                VerilogNodeType.PROGRAM_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.NON_PORT_PROGRAM_ITEM,
                                VerilogNodeType.PROGRAM_ANSI_HEADER,
                                VerilogNodeType.PROGRAM_IDENTIFIER,
                                VerilogNodeType.PROGRAM_ITEM,
                                VerilogNodeType.PROGRAM_NONANSI_HEADER,
                                VerilogNodeType.TIMEUNITS_DECLARATION)));
        out.put(
                VerilogNodeType.PROGRAM_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PROGRAM_INSTANTIATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.HIERARCHICAL_INSTANCE,
                                VerilogNodeType.PARAMETER_VALUE_ASSIGNMENT,
                                VerilogNodeType.PROGRAM_IDENTIFIER)));
        out.put(
                VerilogNodeType.PROGRAM_ITEM,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.NON_PORT_PROGRAM_ITEM, VerilogNodeType.PORT_DECLARATION)));
        out.put(
                VerilogNodeType.PROGRAM_NONANSI_HEADER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.LIFETIME,
                                VerilogNodeType.LIST_OF_PORTS,
                                VerilogNodeType.PACKAGE_IMPORT_DECLARATION,
                                VerilogNodeType.PARAMETER_PORT_LIST,
                                VerilogNodeType.PROGRAM_IDENTIFIER)));
        out.put(
                VerilogNodeType.PROPERTY_CASE_ITEM,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION_OR_DIST, VerilogNodeType.PROPERTY_EXPR)));
        out.put(
                VerilogNodeType.PROPERTY_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSERTION_VARIABLE_DECLARATION,
                                VerilogNodeType.PROPERTY_IDENTIFIER,
                                VerilogNodeType.PROPERTY_PORT_LIST,
                                VerilogNodeType.PROPERTY_SPEC)));
        out.put(
                VerilogNodeType.PROPERTY_EXPR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLOCKING_EVENT,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONSTANT_RANGE,
                                VerilogNodeType.CYCLE_DELAY_CONST_RANGE_EXPRESSION,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.PROPERTY_CASE_ITEM,
                                VerilogNodeType.PROPERTY_EXPR,
                                VerilogNodeType.SEQUENCE_EXPR)));
        out.put(
                VerilogNodeType.PROPERTY_FORMAL_TYPE1,
                new ChildInfo(false, false, Set.of(VerilogNodeType.SEQUENCE_FORMAL_TYPE1)));
        out.put(
                VerilogNodeType.PROPERTY_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PROPERTY_PORT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.FORMAL_PORT_IDENTIFIER,
                                VerilogNodeType.PROPERTY_EXPR,
                                VerilogNodeType.PROPERTY_FORMAL_TYPE1,
                                VerilogNodeType.PROPERTY_LVAR_PORT_DIRECTION,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.SEQUENCE_EXPR,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.PROPERTY_PORT_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.PROPERTY_PORT_ITEM)));
        out.put(
                VerilogNodeType.PROPERTY_SPEC,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLOCKING_EVENT,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.PROPERTY_EXPR)));
        out.put(
                VerilogNodeType.PS_IDENTIFIER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PS_OR_HIERARCHICAL_ARRAY_IDENTIFIER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.IMPLICIT_CLASS_HANDLE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.PULLDOWN_STRENGTH,
                new ChildInfo(true, true, Set.of(VerilogNodeType.STRENGTH0, VerilogNodeType.STRENGTH1)));
        out.put(
                VerilogNodeType.PULLUP_STRENGTH,
                new ChildInfo(true, true, Set.of(VerilogNodeType.STRENGTH0, VerilogNodeType.STRENGTH1)));
        out.put(
                VerilogNodeType.PULL_GATE_INSTANCE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.NAME_OF_INSTANCE, VerilogNodeType.OUTPUT_TERMINAL)));
        out.put(
                VerilogNodeType.PULSESTYLE_DECLARATION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.LIST_OF_PATH_OUTPUTS)));
        out.put(
                VerilogNodeType.PULSE_CONTROL_SPECPARAM,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.ERROR_LIMIT_VALUE, VerilogNodeType.REJECT_LIMIT_VALUE)));
        out.put(
                VerilogNodeType.QUEUE_DIMENSION,
                new ChildInfo(false, false, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.RANDCASE_ITEM,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(VerilogNodeType.RANDCASE_STATEMENT, new ChildInfo(true, true, Set.of(VerilogNodeType.RANDCASE_ITEM)));
        out.put(
                VerilogNodeType.RANDOMIZE_CALL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CONSTRAINT_BLOCK,
                                VerilogNodeType.IDENTIFIER_LIST,
                                VerilogNodeType.VARIABLE_IDENTIFIER_LIST)));
        out.put(
                VerilogNodeType.RANGE_EXPRESSION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.CONSTANT_RANGE,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.INDEXED_RANGE)));
        out.put(VerilogNodeType.REAL_NUMBER, new ChildInfo(false, false, Set.of(VerilogNodeType.FIXED_POINT_NUMBER)));
        out.put(
                VerilogNodeType.RECOVERY_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.RECREM_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.DELAYED_DATA,
                                VerilogNodeType.DELAYED_REFERENCE,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.TIMECHECK_CONDITION,
                                VerilogNodeType.TIMESTAMP_CONDITION,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.REFERENCE_EVENT,
                new ChildInfo(true, false, Set.of(VerilogNodeType.TIMING_CHECK_EVENT)));
        out.put(
                VerilogNodeType.REF_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIST_OF_VARIABLE_IDENTIFIERS)));
        out.put(VerilogNodeType.REJECT_LIMIT_VALUE, new ChildInfo(true, false, Set.of(VerilogNodeType.LIMIT_VALUE)));
        out.put(
                VerilogNodeType.REMAIN_ACTIVE_FLAG,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.REMOVAL_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(VerilogNodeType.REPEAT_RANGE, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.RESTRICT_PROPERTY_STATEMENT,
                new ChildInfo(true, false, Set.of(VerilogNodeType.PROPERTY_SPEC)));
        out.put(
                VerilogNodeType.SCALAR_TIMING_CHECK_CONDITION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.SCALAR_CONSTANT)));
        out.put(
                VerilogNodeType.SELECT1,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BIT_SELECT1,
                                VerilogNodeType.CONSTANT_RANGE,
                                VerilogNodeType.INDEXED_RANGE,
                                VerilogNodeType.MEMBER_IDENTIFIER)));
        out.put(
                VerilogNodeType.SELECT_CONDITION,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.BINS_EXPRESSION, VerilogNodeType.COVERGROUP_RANGE_LIST)));
        out.put(
                VerilogNodeType.SELECT_EXPRESSION,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CROSS_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SELECT_CONDITION,
                                VerilogNodeType.SELECT_EXPRESSION)));
        out.put(
                VerilogNodeType.SEQUENCE_ABBREV,
                new ChildInfo(true, false, Set.of(VerilogNodeType.CONSECUTIVE_REPETITION)));
        out.put(
                VerilogNodeType.SEQUENCE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSERTION_VARIABLE_DECLARATION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SEQUENCE_EXPR,
                                VerilogNodeType.SEQUENCE_PORT_LIST,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.SEQUENCE_EXPR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLOCKING_EVENT,
                                VerilogNodeType.CONSECUTIVE_REPETITION,
                                VerilogNodeType.CYCLE_DELAY_RANGE,
                                VerilogNodeType.EXPRESSION_OR_DIST,
                                VerilogNodeType.GOTO_REPETITION,
                                VerilogNodeType.INC_OR_DEC_EXPRESSION,
                                VerilogNodeType.NON_CONSECUTIVE_REPETITION,
                                VerilogNodeType.OPERATOR_ASSIGNMENT,
                                VerilogNodeType.SEQUENCE_ABBREV,
                                VerilogNodeType.SEQUENCE_EXPR,
                                VerilogNodeType.SEQUENCE_INSTANCE,
                                VerilogNodeType.SUBROUTINE_CALL)));
        out.put(
                VerilogNodeType.SEQUENCE_FORMAL_TYPE1,
                new ChildInfo(false, false, Set.of(VerilogNodeType.DATA_TYPE_OR_IMPLICIT1)));
        out.put(
                VerilogNodeType.SEQUENCE_INSTANCE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SEQUENCE_LIST_OF_ARGUMENTS,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.SEQUENCE_LIST_OF_ARGUMENTS,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.SEQUENCE_EXPR,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.SEQUENCE_METHOD_CALL,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.METHOD_IDENTIFIER, VerilogNodeType.SEQUENCE_INSTANCE)));
        out.put(
                VerilogNodeType.SEQUENCE_PORT_ITEM,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.EVENT_EXPRESSION,
                                VerilogNodeType.FORMAL_PORT_IDENTIFIER,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.SEQUENCE_EXPR,
                                VerilogNodeType.SEQUENCE_FORMAL_TYPE1,
                                VerilogNodeType.SEQUENCE_LVAR_PORT_DIRECTION,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.SEQUENCE_PORT_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.SEQUENCE_PORT_ITEM)));
        out.put(
                VerilogNodeType.SEQUENTIAL_BODY,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.SEQUENTIAL_ENTRY, VerilogNodeType.UDP_INITIAL_STATEMENT)));
        out.put(
                VerilogNodeType.SEQUENTIAL_ENTRY,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.EDGE_INPUT_LIST,
                                VerilogNodeType.LEVEL_INPUT_LIST,
                                VerilogNodeType.LEVEL_SYMBOL,
                                VerilogNodeType.NEXT_STATE)));
        out.put(
                VerilogNodeType.SEQ_BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.BLOCK_ITEM_DECLARATION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.SETUPHOLD_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.DELAYED_DATA,
                                VerilogNodeType.DELAYED_REFERENCE,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.TIMECHECK_CONDITION,
                                VerilogNodeType.TIMESTAMP_CONDITION,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.SETUP_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.SHOWCANCELLED_DECLARATION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.LIST_OF_PATH_OUTPUTS)));
        out.put(
                VerilogNodeType.SIMPLE_IMMEDIATE_ASSERT_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ACTION_BLOCK, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.SIMPLE_IMMEDIATE_ASSUME_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ACTION_BLOCK, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.SIMPLE_IMMEDIATE_COVER_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.SIMPLE_PATH_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.FULL_PATH_DESCRIPTION,
                                VerilogNodeType.PARALLEL_PATH_DESCRIPTION,
                                VerilogNodeType.PATH_DELAY_VALUE)));
        out.put(
                VerilogNodeType.SIMPLE_TEXT_MACRO_USAGE,
                new ChildInfo(true, false, Set.of(VerilogNodeType.TEXT_MACRO_IDENTIFIER)));
        out.put(
                VerilogNodeType.SKEW_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.SLICE_SIZE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.GENERATE_BLOCK_IDENTIFIER,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.INTEGER_VECTOR_TYPE,
                                VerilogNodeType.NON_INTEGER_TYPE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.PARAMETER_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.SOLVE_BEFORE_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.CONSTRAINT_PRIMARY)));
        out.put(
                VerilogNodeType.SOURCE_FILE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ANONYMOUS_PROGRAM,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.BEGIN_KEYWORDS,
                                VerilogNodeType.BIND_DIRECTIVE,
                                VerilogNodeType.DEFAULT_NETTYPE_COMPILER_DIRECTIVE,
                                VerilogNodeType.ID_DIRECTIVE,
                                VerilogNodeType.INCLUDE_COMPILER_DIRECTIVE,
                                VerilogNodeType.INTERFACE_DECLARATION,
                                VerilogNodeType.LINE_COMPILER_DIRECTIVE,
                                VerilogNodeType.MODULE_DECLARATION,
                                VerilogNodeType.PACKAGE_DECLARATION,
                                VerilogNodeType.PACKAGE_EXPORT_DECLARATION,
                                VerilogNodeType.PACKAGE_OR_GENERATE_ITEM_DECLARATION,
                                VerilogNodeType.PROGRAM_DECLARATION,
                                VerilogNodeType.TEXT_MACRO_DEFINITION,
                                VerilogNodeType.TEXT_MACRO_USAGE,
                                VerilogNodeType.TIMESCALE_COMPILER_DIRECTIVE,
                                VerilogNodeType.TIMEUNITS_DECLARATION,
                                VerilogNodeType.UDP_DECLARATION,
                                VerilogNodeType.UNCONNECTED_DRIVE,
                                VerilogNodeType.ZERO_DIRECTIVE)));
        out.put(
                VerilogNodeType.SPECIFY_BLOCK,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.FULLSKEW_TIMING_CHECK,
                                VerilogNodeType.HOLD_TIMING_CHECK,
                                VerilogNodeType.NOCHANGE_TIMING_CHECK,
                                VerilogNodeType.PATH_DECLARATION,
                                VerilogNodeType.PERIOD_TIMING_CHECK,
                                VerilogNodeType.PULSESTYLE_DECLARATION,
                                VerilogNodeType.RECOVERY_TIMING_CHECK,
                                VerilogNodeType.RECREM_TIMING_CHECK,
                                VerilogNodeType.REMOVAL_TIMING_CHECK,
                                VerilogNodeType.SETUPHOLD_TIMING_CHECK,
                                VerilogNodeType.SETUP_TIMING_CHECK,
                                VerilogNodeType.SHOWCANCELLED_DECLARATION,
                                VerilogNodeType.SKEW_TIMING_CHECK,
                                VerilogNodeType.SPECPARAM_DECLARATION,
                                VerilogNodeType.TIMESKEW_TIMING_CHECK,
                                VerilogNodeType.WIDTH_TIMING_CHECK)));
        out.put(
                VerilogNodeType.SPECIFY_INPUT_TERMINAL_DESCRIPTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONSTANT_INDEXED_RANGE,
                                VerilogNodeType.CONSTANT_RANGE,
                                VerilogNodeType.INPUT_IDENTIFIER)));
        out.put(
                VerilogNodeType.SPECIFY_OUTPUT_TERMINAL_DESCRIPTOR,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.CONSTANT_INDEXED_RANGE,
                                VerilogNodeType.CONSTANT_RANGE,
                                VerilogNodeType.OUTPUT_IDENTIFIER)));
        out.put(
                VerilogNodeType.SPECPARAM_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONSTANT_MINTYPMAX_EXPRESSION,
                                VerilogNodeType.PULSE_CONTROL_SPECPARAM,
                                VerilogNodeType.SPECPARAM_IDENTIFIER)));
        out.put(
                VerilogNodeType.SPECPARAM_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.LIST_OF_SPECPARAM_ASSIGNMENTS, VerilogNodeType.PACKED_DIMENSION)));
        out.put(
                VerilogNodeType.SPECPARAM_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.START_EDGE_OFFSET,
                new ChildInfo(true, false, Set.of(VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.STATEMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.STATEMENT_ITEM)));
        out.put(
                VerilogNodeType.STATEMENT_ITEM,
                new ChildInfo(
                        false,
                        false,
                        Set.of(
                                VerilogNodeType.ASSERT_PROPERTY_STATEMENT,
                                VerilogNodeType.ASSUME_PROPERTY_STATEMENT,
                                VerilogNodeType.BLOCKING_ASSIGNMENT,
                                VerilogNodeType.CASE_STATEMENT,
                                VerilogNodeType.CHECKER_INSTANTIATION,
                                VerilogNodeType.CLOCKING_DRIVE,
                                VerilogNodeType.CONDITIONAL_STATEMENT,
                                VerilogNodeType.COVER_PROPERTY_STATEMENT,
                                VerilogNodeType.COVER_SEQUENCE_STATEMENT,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSERT_STATEMENT,
                                VerilogNodeType.DEFERRED_IMMEDIATE_ASSUME_STATEMENT,
                                VerilogNodeType.DEFERRED_IMMEDIATE_COVER_STATEMENT,
                                VerilogNodeType.DISABLE_STATEMENT,
                                VerilogNodeType.EVENT_TRIGGER,
                                VerilogNodeType.EXPECT_PROPERTY_STATEMENT,
                                VerilogNodeType.INC_OR_DEC_EXPRESSION,
                                VerilogNodeType.JUMP_STATEMENT,
                                VerilogNodeType.LOOP_STATEMENT,
                                VerilogNodeType.NONBLOCKING_ASSIGNMENT,
                                VerilogNodeType.PAR_BLOCK,
                                VerilogNodeType.PROCEDURAL_CONTINUOUS_ASSIGNMENT,
                                VerilogNodeType.PROCEDURAL_TIMING_CONTROL_STATEMENT,
                                VerilogNodeType.RANDCASE_STATEMENT,
                                VerilogNodeType.RESTRICT_PROPERTY_STATEMENT,
                                VerilogNodeType.SEQ_BLOCK,
                                VerilogNodeType.SIMPLE_IMMEDIATE_ASSERT_STATEMENT,
                                VerilogNodeType.SIMPLE_IMMEDIATE_ASSUME_STATEMENT,
                                VerilogNodeType.SIMPLE_IMMEDIATE_COVER_STATEMENT,
                                VerilogNodeType.SYSTEM_TF_CALL,
                                VerilogNodeType.WAIT_STATEMENT)));
        out.put(
                VerilogNodeType.STATEMENT_OR_NULL,
                new ChildInfo(false, true, Set.of(VerilogNodeType.ATTRIBUTE_INSTANCE, VerilogNodeType.STATEMENT)));
        out.put(
                VerilogNodeType.STATE_DEPENDENT_PATH_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.EDGE_SENSITIVE_PATH_DECLARATION,
                                VerilogNodeType.MODULE_PATH_EXPRESSION,
                                VerilogNodeType.SIMPLE_PATH_DECLARATION)));
        out.put(
                VerilogNodeType.STREAMING_CONCATENATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.SLICE_SIZE,
                                VerilogNodeType.STREAM_CONCATENATION,
                                VerilogNodeType.STREAM_OPERATOR)));
        out.put(
                VerilogNodeType.STREAM_CONCATENATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.STREAM_EXPRESSION)));
        out.put(
                VerilogNodeType.STREAM_EXPRESSION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.ARRAY_RANGE_EXPRESSION, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.STRUCT_UNION_MEMBER,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.DATA_TYPE_OR_VOID,
                                VerilogNodeType.LIST_OF_VARIABLE_DECL_ASSIGNMENTS,
                                VerilogNodeType.RANDOM_QUALIFIER)));
        out.put(
                VerilogNodeType.SUBROUTINE_CALL,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.METHOD_CALL,
                                VerilogNodeType.RANDOMIZE_CALL,
                                VerilogNodeType.SYSTEM_TF_CALL,
                                VerilogNodeType.TF_CALL)));
        out.put(
                VerilogNodeType.SYSTEM_TF_CALL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CLOCKING_EVENT,
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT,
                                VerilogNodeType.SYSTEM_TF_IDENTIFIER)));
        out.put(
                VerilogNodeType.TAGGED_UNION_EXPRESSION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.MEMBER_IDENTIFIER)));
        out.put(
                VerilogNodeType.TASK_BODY_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.BLOCK_ITEM_DECLARATION,
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.INTERFACE_IDENTIFIER,
                                VerilogNodeType.STATEMENT_OR_NULL,
                                VerilogNodeType.TASK_IDENTIFIER,
                                VerilogNodeType.TF_ITEM_DECLARATION,
                                VerilogNodeType.TF_PORT_LIST)));
        out.put(
                VerilogNodeType.TASK_DECLARATION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.LIFETIME, VerilogNodeType.TASK_BODY_DECLARATION)));
        out.put(
                VerilogNodeType.TASK_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.TASK_IDENTIFIER)));
        out.put(
                VerilogNodeType.TASK_PROTOTYPE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.TASK_IDENTIFIER, VerilogNodeType.TF_PORT_LIST)));
        out.put(
                VerilogNodeType.TERMINAL_IDENTIFIER,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.TERMINAL_IDENTIFIER)));
        out.put(
                VerilogNodeType.TEXT_MACRO_DEFINITION,
                new ChildInfo(true, true, Set.of(VerilogNodeType.MACRO_TEXT, VerilogNodeType.TEXT_MACRO_NAME)));
        out.put(
                VerilogNodeType.TEXT_MACRO_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.TEXT_MACRO_NAME,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.LIST_OF_FORMAL_ARGUMENTS, VerilogNodeType.TEXT_MACRO_IDENTIFIER)));
        out.put(
                VerilogNodeType.TEXT_MACRO_USAGE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.LIST_OF_ACTUAL_ARGUMENTS, VerilogNodeType.TEXT_MACRO_IDENTIFIER)));
        out.put(
                VerilogNodeType.TF_CALL,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.LIST_OF_ARGUMENTS_PARENT,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.TF_IDENTIFIER,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.TF_ITEM_DECLARATION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(VerilogNodeType.BLOCK_ITEM_DECLARATION, VerilogNodeType.TF_PORT_DECLARATION)));
        out.put(
                VerilogNodeType.TF_PORT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.LIST_OF_TF_VARIABLE_IDENTIFIERS,
                                VerilogNodeType.TF_PORT_DIRECTION)));
        out.put(VerilogNodeType.TF_PORT_DIRECTION, new ChildInfo(false, false, Set.of(VerilogNodeType.PORT_DIRECTION)));
        out.put(
                VerilogNodeType.TF_PORT_ITEM1,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.PORT_IDENTIFIER,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.TF_PORT_DIRECTION,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(VerilogNodeType.TF_PORT_LIST, new ChildInfo(true, true, Set.of(VerilogNodeType.TF_PORT_ITEM1)));
        out.put(VerilogNodeType.THRESHOLD, new ChildInfo(true, false, Set.of(VerilogNodeType.CONSTANT_EXPRESSION)));
        out.put(
                VerilogNodeType.TIMECHECK_CONDITION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(
                VerilogNodeType.TIMESCALE_COMPILER_DIRECTIVE,
                new ChildInfo(true, true, Set.of(VerilogNodeType.TIME_LITERAL)));
        out.put(
                VerilogNodeType.TIMESKEW_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_EVENT,
                                VerilogNodeType.EVENT_BASED_FLAG,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.REFERENCE_EVENT,
                                VerilogNodeType.REMAIN_ACTIVE_FLAG,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        out.put(
                VerilogNodeType.TIMESTAMP_CONDITION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.MINTYPMAX_EXPRESSION)));
        out.put(VerilogNodeType.TIMEUNITS_DECLARATION, new ChildInfo(true, true, Set.of(VerilogNodeType.TIME_LITERAL)));
        out.put(
                VerilogNodeType.TIME_LITERAL,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.FIXED_POINT_NUMBER,
                                VerilogNodeType.TIME_UNIT,
                                VerilogNodeType.UNSIGNED_NUMBER)));
        out.put(
                VerilogNodeType.TIMING_CHECK_CONDITION,
                new ChildInfo(true, false, Set.of(VerilogNodeType.SCALAR_TIMING_CHECK_CONDITION)));
        out.put(
                VerilogNodeType.TIMING_CHECK_EVENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.SPECIFY_INPUT_TERMINAL_DESCRIPTOR,
                                VerilogNodeType.SPECIFY_OUTPUT_TERMINAL_DESCRIPTOR,
                                VerilogNodeType.TIMING_CHECK_CONDITION,
                                VerilogNodeType.TIMING_CHECK_EVENT_CONTROL)));
        out.put(
                VerilogNodeType.TIMING_CHECK_EVENT_CONTROL,
                new ChildInfo(false, false, Set.of(VerilogNodeType.EDGE_CONTROL_SPECIFIER)));
        out.put(VerilogNodeType.TIMING_CHECK_LIMIT, new ChildInfo(true, false, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(VerilogNodeType.TRANS_ITEM, new ChildInfo(true, false, Set.of(VerilogNodeType.COVERGROUP_RANGE_LIST)));
        out.put(VerilogNodeType.TRANS_LIST, new ChildInfo(true, true, Set.of(VerilogNodeType.TRANS_SET)));
        out.put(
                VerilogNodeType.TRANS_RANGE_LIST,
                new ChildInfo(true, true, Set.of(VerilogNodeType.REPEAT_RANGE, VerilogNodeType.TRANS_ITEM)));
        out.put(VerilogNodeType.TRANS_SET, new ChildInfo(true, true, Set.of(VerilogNodeType.TRANS_RANGE_LIST)));
        out.put(
                VerilogNodeType.TYPE_ASSIGNMENT,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.TYPE_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.INTERFACE_INSTANCE_IDENTIFIER,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.TYPE_REFERENCE,
                new ChildInfo(true, false, Set.of(VerilogNodeType.DATA_TYPE, VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.UDP_ANSI_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UDP_DECLARATION_PORT_LIST)));
        out.put(
                VerilogNodeType.UDP_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.COMBINATIONAL_BODY,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SEQUENTIAL_BODY,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UDP_ANSI_DECLARATION,
                                VerilogNodeType.UDP_NONANSI_DECLARATION,
                                VerilogNodeType.UDP_PORT_DECLARATION)));
        out.put(
                VerilogNodeType.UDP_DECLARATION_PORT_LIST,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.UDP_INPUT_DECLARATION, VerilogNodeType.UDP_OUTPUT_DECLARATION)));
        out.put(
                VerilogNodeType.UDP_INITIAL_STATEMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.INIT_VAL, VerilogNodeType.OUTPUT_PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.UDP_INPUT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.ATTRIBUTE_INSTANCE, VerilogNodeType.LIST_OF_UDP_PORT_IDENTIFIERS)));
        out.put(
                VerilogNodeType.UDP_INSTANCE,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.INPUT_TERMINAL,
                                VerilogNodeType.NAME_OF_INSTANCE,
                                VerilogNodeType.OUTPUT_TERMINAL)));
        out.put(
                VerilogNodeType.UDP_INSTANTIATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.DELAY2,
                                VerilogNodeType.DRIVE_STRENGTH,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UDP_INSTANCE)));
        out.put(
                VerilogNodeType.UDP_NONANSI_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UDP_PORT_LIST)));
        out.put(
                VerilogNodeType.UDP_OUTPUT_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.CONSTANT_EXPRESSION,
                                VerilogNodeType.PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.UDP_PORT_DECLARATION,
                new ChildInfo(
                        true,
                        false,
                        Set.of(
                                VerilogNodeType.UDP_INPUT_DECLARATION,
                                VerilogNodeType.UDP_OUTPUT_DECLARATION,
                                VerilogNodeType.UDP_REG_DECLARATION)));
        out.put(
                VerilogNodeType.UDP_PORT_LIST,
                new ChildInfo(
                        true,
                        true,
                        Set.of(VerilogNodeType.INPUT_PORT_IDENTIFIER, VerilogNodeType.OUTPUT_PORT_IDENTIFIER)));
        out.put(
                VerilogNodeType.UDP_REG_DECLARATION,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.ATTRIBUTE_INSTANCE,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.UNIQUENESS_CONSTRAINT,
                new ChildInfo(true, false, Set.of(VerilogNodeType.OPEN_RANGE_LIST)));
        out.put(
                VerilogNodeType.UNPACKED_DIMENSION,
                new ChildInfo(
                        true, false, Set.of(VerilogNodeType.CONSTANT_EXPRESSION, VerilogNodeType.CONSTANT_RANGE)));
        out.put(VerilogNodeType.VALUE_RANGE, new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION)));
        out.put(
                VerilogNodeType.VARIABLE_ASSIGNMENT,
                new ChildInfo(true, true, Set.of(VerilogNodeType.EXPRESSION, VerilogNodeType.VARIABLE_LVALUE)));
        out.put(
                VerilogNodeType.VARIABLE_DECL_ASSIGNMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ASSOCIATIVE_DIMENSION,
                                VerilogNodeType.CLASS_NEW,
                                VerilogNodeType.DYNAMIC_ARRAY_NEW,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.QUEUE_DIMENSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.UNPACKED_DIMENSION,
                                VerilogNodeType.UNSIZED_DIMENSION)));
        out.put(
                VerilogNodeType.VARIABLE_IDENTIFIER_LIST,
                new ChildInfo(
                        true, true, Set.of(VerilogNodeType.ESCAPED_IDENTIFIER, VerilogNodeType.SIMPLE_IDENTIFIER)));
        out.put(
                VerilogNodeType.VARIABLE_LVALUE,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ASSIGNMENT_PATTERN_VARIABLE_LVALUE,
                                VerilogNodeType.CLASS_SCOPE,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.IMPLICIT_CLASS_HANDLE,
                                VerilogNodeType.INTEGER_ATOM_TYPE,
                                VerilogNodeType.PACKAGE_SCOPE,
                                VerilogNodeType.SELECT1,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.STREAMING_CONCATENATION,
                                VerilogNodeType.TYPE_REFERENCE,
                                VerilogNodeType.VARIABLE_LVALUE)));
        out.put(
                VerilogNodeType.VARIABLE_PORT_HEADER,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.DATA_TYPE,
                                VerilogNodeType.DATA_TYPE_OR_IMPLICIT1,
                                VerilogNodeType.PORT_DIRECTION)));
        out.put(
                VerilogNodeType.WAIT_STATEMENT,
                new ChildInfo(
                        false,
                        true,
                        Set.of(
                                VerilogNodeType.ACTION_BLOCK,
                                VerilogNodeType.CONSTANT_BIT_SELECT1,
                                VerilogNodeType.ESCAPED_IDENTIFIER,
                                VerilogNodeType.EXPRESSION,
                                VerilogNodeType.SIMPLE_IDENTIFIER,
                                VerilogNodeType.STATEMENT_OR_NULL)));
        out.put(
                VerilogNodeType.WIDTH_TIMING_CHECK,
                new ChildInfo(
                        true,
                        true,
                        Set.of(
                                VerilogNodeType.CONTROLLED_REFERENCE_EVENT,
                                VerilogNodeType.NOTIFIER,
                                VerilogNodeType.THRESHOLD,
                                VerilogNodeType.TIMING_CHECK_LIMIT)));
        return out;
    }

    private static final class FieldInfo {
        final boolean required;
        final boolean multiple;
        final Set<VerilogNodeType> allowedTypes;

        FieldInfo(boolean required, boolean multiple, Set<VerilogNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }

    private static final class ChildInfo {
        final boolean required;
        final boolean multiple;
        final Set<VerilogNodeType> allowedTypes;

        ChildInfo(boolean required, boolean multiple, Set<VerilogNodeType> allowedTypes) {
            this.required = required;
            this.multiple = multiple;
            this.allowedTypes = allowedTypes;
        }
    }
}
