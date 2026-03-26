package org.treesitter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TSNodeTest {
    public static final String JSON_SRC = "[1, null]";
    private TSTree tree;
    private TSLanguage json;
    private TSParser parser;

    @SuppressWarnings("NullAway.Init")
    private TSNode rootNode;

    @SuppressWarnings("NullAway.Init")
    private TSNode arrayNode;

    @SuppressWarnings("NullAway.Init")
    private TSNode numberNode;

    @BeforeEach
    void beforeEach() {
        parser = new TSParser();
        json = new TreeSitterJson();
        parser.setLanguage(json);
        tree = Objects.requireNonNull(parser.parseString(null, JSON_SRC));
        rootNode = Objects.requireNonNull(tree.getRootNode());
        arrayNode = Objects.requireNonNull(rootNode.getNamedChild(0));
        numberNode = Objects.requireNonNull(arrayNode.getNamedChild(0));
    }

    @Test
    void getChildCount() {
        assertEquals(1, rootNode.getChildCount());
    }

    @Test
    void getNamedChildCount() {
        assertEquals(2, arrayNode.getNamedChildCount());
    }

    @Test
    void getNamedChild() {
        assertEquals(
                "number", Objects.requireNonNull(arrayNode.getNamedChild(0)).getType());
    }

    @Test
    void getChildren() {
        List<TSNode> children = rootNode.getChildren();
        int count = rootNode.getChildCount();
        assertEquals(count, children.size());
        for (int i = 0; i < count; i++) {
            assertEquals(rootNode.getChild(i), children.get(i));
        }
        // Verify iteration
        int i = 0;
        for (TSNode child : children) {
            assertEquals(rootNode.getChild(i++), child);
        }
        assertEquals(count, i);

        assertThrows(IndexOutOfBoundsException.class, () -> children.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> children.get(children.size()));
    }

    @Test
    void getNamedChildren() {
        List<TSNode> namedChildren = arrayNode.getNamedChildren();
        int count = arrayNode.getNamedChildCount();
        assertEquals(count, namedChildren.size());
        for (int i = 0; i < count; i++) {
            assertEquals(arrayNode.getNamedChild(i), namedChildren.get(i));
        }
        // Verify iteration
        int i = 0;
        for (TSNode namedChild : namedChildren) {
            assertEquals(arrayNode.getNamedChild(i++), namedChild);
        }
        assertEquals(count, i);

        assertThrows(IndexOutOfBoundsException.class, () -> namedChildren.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> namedChildren.get(namedChildren.size()));
    }

    @Test
    void getType() {
        assertEquals(15, rootNode.getSymbol());
    }

    @Test
    void getSymbol() {
        assertEquals("array", arrayNode.getType());
    }

    @Test
    void isNamed() {
        assertTrue(arrayNode.isNamed());
    }

    @Test
    void isMissing() {
        assertFalse(rootNode.isMissing());
    }

    @Test
    void isExtra() {
        assertFalse(rootNode.isExtra());
    }

    @Test
    void hasChanges() {
        assertFalse(rootNode.hasChanges());
    }

    @Test
    void hasError() {
        assertFalse(rootNode.hasError());
    }

    @Test
    void isError() {
        assertFalse(rootNode.isError());
    }

    @Test
    void getParserState() {
        assertEquals(rootNode.getParserState(), 0);
    }

    @Test
    void nextParserState() {
        assertEquals(rootNode.getNextParserState(), 0);
    }

    @Test
    void getStartByte() {
        assertEquals(0, rootNode.getStartByte());
    }

    @Test
    void getEndByte() {
        assertEquals(9, rootNode.getEndByte());
    }

    @Test
    void getStartPoint() {
        assertEquals(0, rootNode.getStartPoint().getRow());
        assertEquals(0, rootNode.getStartPoint().getRow());
    }

    @Test
    void getEndPoint() {
        assertEquals(0, rootNode.getEndPoint().getRow());
        assertEquals(9, rootNode.getEndPoint().getColumn());
    }

    @Test
    void getParent() {
        assertNull(rootNode.getParent());
    }

    @Test
    void getChild() {
        assertEquals("array", Objects.requireNonNull(rootNode.getChild(0)).getType());
    }

    @Test
    void getFieldNameForChild() {
        assertNull(rootNode.getFieldNameForChild(0));
    }

    @Test
    void getNextNamedSibling() {
        assertNull(rootNode.getNextNamedSibling());
    }

    @Test
    void getPrevNamedSibling() {
        assertNull(rootNode.getPrevNamedSibling());
    }

    @Test
    void getNextSibling() {
        assertEquals(",", Objects.requireNonNull(numberNode.getNextSibling()).getType());
    }

    @Test
    void getPrevSibling() {
        assertEquals("[", Objects.requireNonNull(numberNode.getPrevSibling()).getType());
    }

    @Test
    void getChildByFieldName() {
        parser.reset();
        tree = Objects.requireNonNull(parser.parseString(null, "{\"foo\": 42}"));
        TSNode root = Objects.requireNonNull(tree.getRootNode());
        TSNode child = Objects.requireNonNull(
                        Objects.requireNonNull(root.getNamedChild(0)).getNamedChild(0))
                .getChildByFieldName("key");
        assertNotNull(child);
        assertEquals("string", child.getType());
    }

    @Test
    void getChildByFieldId() {
        parser.reset();
        tree = Objects.requireNonNull(parser.parseString(null, "{\"foo\": 42}"));
        TSNode root = Objects.requireNonNull(tree.getRootNode());
        TSNode child = Objects.requireNonNull(
                        Objects.requireNonNull(root.getNamedChild(0)).getNamedChild(0))
                .getChildByFieldId(1);
        assertNotNull(child);
        assertEquals("string", child.getType());
    }

    @Test
    void getFirstChildForByte() {
        TSNode child = Objects.requireNonNull(arrayNode.getFirstChildForByte(0));
        assertEquals("[", child.getType());
    }

    @Test
    void getFirstNamedChildForByte() {
        TSNode child = arrayNode.getFirstNamedChildForByte(0);
        assertNotNull(child);
        assertEquals("number", child.getType());
    }

    @Test
    void getDescendantForByteRange() {
        TSNode descendant = Objects.requireNonNull(arrayNode.getDescendantForByteRange(0, 1));
        assertEquals("[", descendant.getType());
    }

    @Test
    void getDescendantForPointRange() {
        TSNode descendant = arrayNode.getDescendantForPointRange(new TSPoint(0, 0), new TSPoint(0, 1));
        assertNotNull(descendant);
        assertEquals("[", descendant.getType());
    }

    @Test
    void getNamedDescendantForByteRange() {
        TSNode descendant = arrayNode.getNamedDescendantForByteRange(1, 2);
        assertNotNull(descendant);
        assertEquals("number", descendant.getType());
    }

    @Test
    void getNamedDescendantForPointRange() {
        TSNode descendant = arrayNode.getNamedDescendantForPointRange(new TSPoint(0, 1), new TSPoint(0, 2));
        assertNotNull(descendant);
        assertEquals("number", descendant.getType());
    }

    @Test
    void edit() {
        int editStart = 0;
        int editEnd = 1;
        rootNode.edit(new TSInputEdit(
                editStart,
                editStart,
                editEnd,
                new TSPoint(0, editStart),
                new TSPoint(0, editStart),
                new TSPoint(0, editEnd)));
    }

    @Test
    void eq() {
        assertFalse(TSNode.eq(rootNode, numberNode));
    }

    @Test
    void getGrammarType() {
        assertEquals("document", rootNode.getGrammarType());
    }

    @Test
    void getGrammarSymbol() {
        assertEquals(15, rootNode.getGrammarSymbol());
    }

    @Test
    void getFieldNameForNamedChild() {
        assertNull(rootNode.getFieldNameForNamedChild(0));
    }

    @Test
    void getChildWithDescendant() {
        TSNode child = rootNode.getChild(0);
        assertNotNull(child);
        TSNode firstChild = child.getChild(0);
        assertNotNull(firstChild);
        TSNode descendant = firstChild;
        TSNode childWithDescendant = rootNode.getChildWithDescendant(descendant);
        assertNotNull(childWithDescendant);
        assertEquals(child.toString(), childWithDescendant.toString());
    }

    @Test
    void traversingEquals() {
        TSNode child = Objects.requireNonNull(rootNode.getChild(0));
        assertFalse(TSNode.eq(child, rootNode));
        assertTrue(TSNode.eq(child.getParent(), rootNode));
    }

    @Test
    void eqDifferentTrees() {
        // Create a second tree from the same source
        TSTree tree2 = Objects.requireNonNull(parser.parseString(null, JSON_SRC));
        TSNode rootNode2 = Objects.requireNonNull(tree2.getRootNode());

        // Even if they are logically the same (root of the same source),
        // they belong to different native trees and different Java TSTree wrappers.
        assertFalse(
                TSNode.eq(rootNode, rootNode2), "Nodes from different TSTree instances should not be equal via eq()");
        assertFalse(
                rootNode.equals(rootNode2), "Nodes from different TSTree instances should not be equal via equals()");

        // Same tree node should be equal to itself
        assertTrue(TSNode.eq(rootNode, rootNode), "Node should be eq() to itself");
        assertEquals(rootNode, rootNode, "Node should be equals() to itself");
    }

    @Test
    @SuppressWarnings("NullAway")
    void testNullableBoundaryConditions() {
        // 1. Parent of root
        assertNull(rootNode.getParent(), "rootNode.getParent() must be null");

        // 2. Siblings of root (single root node)
        assertNull(rootNode.getNextSibling(), "rootNode.getNextSibling() must be null");
        assertNull(rootNode.getPrevSibling(), "rootNode.getPrevSibling() must be null");
        assertNull(rootNode.getNextNamedSibling(), "rootNode.getNextNamedSibling() must be null");
        assertNull(rootNode.getPrevNamedSibling(), "rootNode.getPrevNamedSibling() must be null");

        // 3. Out of bounds children indices
        // Using a high index to ensure it's out of bounds for the array node
        assertNull(arrayNode.getChild(50), "arrayNode.getChild(50) must be null");
        assertNull(arrayNode.getNamedChild(50), "arrayNode.getNamedChild(50) must be null");

        // 4. Descendant logic - root is not a child of its own children
        assertNull(
                numberNode.getChildWithDescendant(rootNode),
                "numberNode.getChildWithDescendant(rootNode) must be null");

        // 5. Fields - Check methods that return @Nullable String or TSNode
        // array nodes in JSON usually don't have fields for their elements
        assertNull(arrayNode.getChildByFieldName("non_existent_field"), "getChildByFieldName should be null");
        assertNull(arrayNode.getFieldNameForChild(0), "getFieldNameForChild(0) should be null");

        // 6. Byte/Point offsets out of range
        int tooFar = 10000;
        // Tree-sitter might return the root or a large-spanning node for out-of-bounds descendant ranges
        // depending on the internal tree structure, but many child-seekers return null.
        // We test the ones that are guaranteed to be null or check their actual behavior.
        assertNull(rootNode.getFirstChildForByte(tooFar), "getFirstChildForByte(tooFar) must be null");
        assertNull(rootNode.getFirstNamedChildForByte(tooFar), "getFirstNamedChildForByte(tooFar) must be null");

        // 7. Verify literal null vs "Null Node" object wrapper
        // If the return value was an object (not literal null), this would fail
        TSNode actualNull = rootNode.getParent();
        assertNull(actualNull, "Returned value must be literal null");

        // Verify that trying to use the result throws NPE (proving it's a real null and not a wrapper)
        assertThrows(
                NullPointerException.class,
                () -> actualNull.getType(),
                "Confirmed null should throw NPE on access, verifying it is not a 'null node' object wrapper");
    }
}
