package org.treesitter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TSTreeCursorTest {
    public static final String JSON_SRC = "[1, null]";
    private TSTree tree;
    private TSParser parser;

    @SuppressWarnings("NullAway.Init")
    private TSTreeCursor rootCursor;

    @BeforeEach
    void beforeEach() {
        parser = new TSParser();
        TSLanguage json = new TreeSitterJson();
        parser.setLanguage(json);
        tree = Objects.requireNonNull(parser.parseString(null, JSON_SRC));
        TSNode rootNode = Objects.requireNonNull(tree.getRootNode());
        rootCursor = new TSTreeCursor(rootNode);
    }

    @Test
    void reset() {
        rootCursor.gotoFirstChild();
        rootCursor.reset();
        TSNode node = rootCursor.currentNode();
        assertNotNull(node);
        assertEquals("document", node.getType());
    }

    @Test
    void currentNode() {
        TSNode node = rootCursor.currentNode();
        assertNotNull(node);
        assertEquals("document", node.getType());
    }

    @Test
    void currentFieldName() {
        assertNull(rootCursor.currentFieldName());
    }

    @Test
    void currentFieldId() {
        assertEquals(0, rootCursor.currentFieldId());
    }

    @Test
    void gotoParent() {
        assertFalse(rootCursor.gotoParent());
    }

    @Test
    void gotoNextSibling() {
        assertFalse(rootCursor.gotoNextSibling());
    }

    @Test
    void gotoPreviousSibling() {
        assertFalse(rootCursor.gotoPreviousSibling());
    }

    @Test
    void gotoFirstChild() {
        assertTrue(rootCursor.gotoFirstChild());
        TSNode node = rootCursor.currentNode();
        assertNotNull(node);
        assertEquals("array", node.getType());
    }

    @Test
    void gotoFirstChildForByte() {
        assertEquals(0, rootCursor.gotoFirstChildForByte(0));
        TSNode node = rootCursor.currentNode();
        assertNotNull(node);
        assertEquals("array", node.getType());
    }

    @Test
    void gotoFirstChildForPoint() {
        assertEquals(0, rootCursor.gotoFirstChildForPoint(new TSPoint(0, 0)));
        TSNode node = rootCursor.currentNode();
        assertNotNull(node);
        assertEquals("array", node.getType());
    }

    @Test
    void copy() {
        rootCursor.gotoFirstChild();
        TSTreeCursor copy = rootCursor.copy();
        TSNode node = copy.currentNode();
        assertNotNull(node);
        assertEquals("array", node.getType());
    }
}
