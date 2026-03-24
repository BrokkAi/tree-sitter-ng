package org.treesitter;

import static org.junit.jupiter.api.Assertions.*;

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
        assertNotNull(tree);
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
        TSNode child = Objects.requireNonNull(rootNode.getChild(0));
        TSNode descendant = Objects.requireNonNull(
                Objects.requireNonNull(rootNode.getChild(0)).getChild(0));
        assertEquals(
                child.toString(),
                Objects.requireNonNull(rootNode.getChildWithDescendant(descendant))
                        .toString());
        assertNotNull(rootNode.getChildWithDescendant(descendant));
    }

    @Test
    void traversingEquals() {
        TSNode child = Objects.requireNonNull(rootNode.getChild(0));
        assertNotEquals(child, rootNode);
        assertEquals(child.getParent(), rootNode);
    }
}
