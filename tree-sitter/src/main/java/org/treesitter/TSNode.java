package org.treesitter;

import static org.treesitter.TSParser.*;

import java.util.Objects;
import org.jspecify.annotations.Nullable;

public class TSNode {
    private int context0;
    private int context1;
    private int context2;
    private int context3;
    private long idPtr;
    private long treePtr;

    @SuppressWarnings("NullAway.Init")
    private TSTree tree;

    @SuppressWarnings("NullAway.Init")
    protected long getTreePtr() {
        return treePtr;
    }

    /**
     * Get the tree that this node belongs to.
     * @return The tree.
     */
    public TSTree getTree() {
        return tree;
    }

    protected void setTree(TSTree tree) {
        this.tree = tree;
    }

    /**
     * Get the node's number of children.
     *
     * @return The number of children.
     */
    public int getChildCount() {
        return ts_node_child_count(this);
    }

    /**
     * Get the node's number of *named* children.<br>
     *
     * See also {@link #isNamed()}.
     *
     * @return The number of named children.
     */
    public int getNamedChildCount() {
        return ts_node_named_child_count(this);
    }

    /**
     * Get the node's *named* child at the given index.<br>
     *
     * See also {@link #isNamed()}.
     *
     * @param index The index of the named child to get.
     *
     * @return The named child at the given index, or <code>null</code> if the index is out of bounds.
     */
    public @Nullable TSNode getNamedChild(int index) {
        TSNode ret = ts_node_named_child(this, index);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }
    /**
     * Get the node's type as a string.
     *
     * @return The node's type.
     */
    public String getType() {
        return ts_node_type(this);
    }

    /**
     * Get the node's type as a numerical id.
     *
     * @return The node's type id.
     */
    public int getSymbol() {
        return ts_node_symbol(this);
    }

    /**
     * Check if the node is *named*. Named nodes correspond to named rules in the
     * grammar, whereas *anonymous* nodes correspond to string literals in the
     * grammar.
     *
     * @return True if the node is a named node.
     */
    public boolean isNamed() {
        return ts_node_is_named(this);
    }

    /**
     * Check if the node is *missing*. Missing nodes are inserted by the parser in
     * order to recover from certain kinds of syntax errors.
     *
     * @return True if the node is a missing node.
     */
    public boolean isMissing() {
        return ts_node_is_missing(this);
    }

    /**
     * Check if the node is *extra*. Extra nodes represent things like comments,
     * which are not required the grammar, but can appear anywhere.
     *
     * @return True if the node is an extra node.
     */
    public boolean isExtra() {
        return ts_node_is_extra(this);
    }

    /**
     * Check if a syntax node has been edited.
     *
     * @return True if the node has been edited.
     */
    public boolean hasChanges() {
        return ts_node_has_changes(this);
    }

    /**
     * Check if the node is a syntax error or contains any syntax errors.
     *
     * @return True if the node is a syntax error.
     */
    public boolean hasError() {
        return ts_node_has_error(this);
    }

    /**
     * Check if the node is a syntax error.
     *
     * @return True node is a syntax error.
     */
    public boolean isError() {
        return TSParser.ts_node_is_error(this);
    }

    /**
     * Get this node's parser state.
     *
     * @return nodes's parser state.
     */
    public int getParserState() {
        return TSParser.ts_node_parse_state(this);
    }

    /**
     * Get this node's next parser state.
     *
     * @return nodes's next parser state.
     */
    public int getNextParserState() {
        return TSParser.ts_node_next_parse_state(this);
    }

    /**
     * Get the node's start byte.
     *
     * @return The node's start byte.
     */
    public int getStartByte() {
        return ts_node_start_byte(this);
    }

    /**
     * Get the node's end byte.
     *
     * @return The node's end byte.
     */
    public int getEndByte() {
        return ts_node_end_byte(this);
    }

    /**
     * Get the node's start position in terms of rows and columns.
     *
     * @return The node's start position.
     */
    public TSPoint getStartPoint() {
        return ts_node_start_point(this);
    }

    /**
     * Get the node's end position in terms of rows and columns.
     *
     * @return The node's end position.
     */
    public TSPoint getEndPoint() {
        return ts_node_end_point(this);
    }

    /**
     * Get the node's immediate parent. <br>
     * Prefer {@link #getChildWithDescendant(TSNode)} for iterating over the node's ancestors.
     *
     * @return The node's parent.
     */
    public @Nullable TSNode getParent() {
        TSNode ret = ts_node_parent(this);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node that contains `descendant`.<br>
     *
     * Note that this can return `descendant` itself.
     *
     * @param descendant the descendant to search.
     * @return child that contains `descendant`.
     */
    public @Nullable TSNode getChildWithDescendant(TSNode descendant) {
        TSNode ret = ts_node_child_with_descendant(this, descendant);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's child at the given index, where zero represents the first
     * child.
     *
     * @param index The index of the child to get.
     *
     * @return The node's child at the given index, or <code>null</code> if the index is out of bounds.
     */
    public @Nullable TSNode getChild(int index) {
        TSNode ret = ts_node_child(this, index);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the field name for node's child at the given index, where zero represents
     * the first child. Returns <code>null</code>, if no field is found.
     *
     * @param index The index of the child to get.
     *
     * @return The field name for the node's child at the given index.
     */
    public @Nullable String getFieldNameForChild(int index) {
        return ts_node_field_name_for_child(this, index);
    }

    /**
     * Get the field name for node's named child at the given index, where zero
     * represents the first named child. Returns null, if no field is found.
     *
     * @param namedChildIndex Index of the child
     * @return The field name for the node's named child at the given index.
     */
    public @Nullable String getFieldNameForNamedChild(int namedChildIndex) {
        return TSParser.ts_node_field_name_for_named_child(this, namedChildIndex);
    }

    /**
     * Get the node's next *named* sibling.
     *
     * @return The node's next *named* sibling.
     */
    public @Nullable TSNode getNextNamedSibling() {
        TSNode ret = ts_node_next_named_sibling(this);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's previous *named* sibling.
     *
     * @return The node's previous *named* sibling.
     */
    public @Nullable TSNode getPrevNamedSibling() {
        TSNode ret = ts_node_prev_named_sibling(this);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's next sibling.
     *
     * @return The node's next sibling.
     */
    public @Nullable TSNode getNextSibling() {
        TSNode ret = ts_node_next_sibling(this);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's previous sibling.
     *
     * @return the node's previous sibling.
     */
    public @Nullable TSNode getPrevSibling() {
        TSNode ret = ts_node_prev_sibling(this);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's child with the given field name.
     *
     * @param fieldName The field name of the child to get.
     *
     * @return The node's child with the given field name.
     */
    public @Nullable TSNode getChildByFieldName(String fieldName) {
        TSNode ret = ts_node_child_by_field_name(this, fieldName);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's child with the given numerical field id.<br>
     *
     * You can convert a field name to an id using the
     * {@link TSLanguage#fieldIdForName(String) fieldIdForName()} function.
     *
     * @param fieldId The field id of the child to get.
     *
     * @return The node's child with the given field id.
     */
    public @Nullable TSNode getChildByFieldId(int fieldId) {
        TSNode ret = ts_node_child_by_field_id(this, fieldId);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's first child that contains or starts after the given byte offset.
     *
     * @param startByte The byte offset to search.
     *
     * @return The node's first child that beyond the given byte offset.
     */
    public @Nullable TSNode getFirstChildForByte(int startByte) {
        TSNode ret = ts_node_first_child_for_byte(this, startByte);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the node's first named child that contains or starts after the given byte offset.
     *
     * @param startByte The byte offset to search.
     *
     * @return The node's first named child that beyond the given byte offset.
     */
    public @Nullable TSNode getFirstNamedChildForByte(int startByte) {
        TSNode ret = ts_node_first_named_child_for_byte(this, startByte);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the smallest node within this node that spans the given range of bytes.
     *
     * @param startByte The start byte offset to search.
     * @param endByte The end byte offset to search.
     *
     * @return The smallest node within this node that spans the given range of bytes.
     */
    public @Nullable TSNode getDescendantForByteRange(int startByte, int endByte) {
        TSNode ret = ts_node_descendant_for_byte_range(this, startByte, endByte);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the smallest node within this node that spans the given (row, column) positions.
     *
     * @param startPoint the start point to search.
     * @param endPoint the end point to search.
     *
     * @return The smallest node within this node that spans the given range of positions.
     */
    public @Nullable TSNode getDescendantForPointRange(TSPoint startPoint, TSPoint endPoint) {
        TSNode ret = ts_node_descendant_for_point_range(this, startPoint, endPoint);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the smallest named node within this node that spans the given range of
     * bytes.
     *
     * @param startByte the start byte to search.
     * @param endByte the end byte to search.
     *
     * @return The smallest named node within this node that spans the given range of bytes.
     */
    public @Nullable TSNode getNamedDescendantForByteRange(int startByte, int endByte) {
        TSNode ret = ts_node_named_descendant_for_byte_range(this, startByte, endByte);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Get the smallest named node within this node that spans the given range of
     * (row, column) positions.
     * @param startPoint the start point to search.
     * @param endPoint the end point to search.
     *
     * @return The smallest named node within this node that spans the given range of positions.
     */
    public @Nullable TSNode getNamedDescendantForPointRange(TSPoint startPoint, TSPoint endPoint) {
        TSNode ret = ts_node_named_descendant_for_point_range(this, startPoint, endPoint);
        if (ret != null) {
            ret.setTree(tree);
        }
        return ret;
    }

    /**
     * Edit the node to keep it in-sync with source code that has been edited.<br>
     *
     * This function is only rarely needed. When you edit a syntax tree with the
     * {@link TSTree#edit(TSInputEdit) TStree#edit()} function, all the nodes that you retrieve from the tree
     * afterward will already reflect the edit. You only need to use this function
     * when you have a {@link TSNode} instance that you want to keep and continue to use
     * after an edit.
     *
     * @param inputEdit the edit to apply to the node.
     */
    public void edit(TSInputEdit inputEdit) {
        TSNode tsNode = ts_node_edit(this, inputEdit);
        context0 = tsNode.context0;
        context1 = tsNode.context1;
        context2 = tsNode.context2;
        context3 = tsNode.context3;
        idPtr = tsNode.idPtr;
        treePtr = tsNode.treePtr;
    }

    /**
     * Check if two nodes are identical.
     *
     * @param a The first node to compare.
     * @param b The second node to compare.
     *
     * @return Whether the two nodes are identical.
     */
    public static boolean eq(@Nullable TSNode a, @Nullable TSNode b) {
        if (Objects.equals(a, b)) return true;
        if (a == null || b == null) return false;
        return ts_node_eq(a, b);
    }

    /**
     * Get an S-expression representing the node as a string.<br>
     */
    @Override
    public String toString() {
        return TSParser.ts_node_string(this);
    }

    /**
     * Get the node's type as it appears in the grammar ignoring aliases as a string.
     *
     * @return Node grammar type
     */
    public String getGrammarType() {
        return TSParser.ts_node_grammar_type(this);
    }

    /**
     * Get the node's type as a numerical id as it appears in the grammar ignoring
     * aliases. This should be used in {@link TSLanguage#nextState(int, int)} instead of {@link TSNode#getSymbol()}
     *
     * @return Node grammar symbol
     */
    public int getGrammarSymbol() {
        return TSParser.ts_node_grammar_symbol(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPtr, treePtr);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TSNode)) return false;
        TSNode other = (TSNode) obj;
        return idPtr == other.idPtr && treePtr == other.treePtr;
    }
}
