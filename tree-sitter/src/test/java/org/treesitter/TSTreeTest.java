package org.treesitter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TSTreeTest {
    public static final String JSON_SRC = "[1, null]";
    private static TSTree tree;
    private static final TSLanguage json = new TreeSitterJson();
    private static final TSParser parser = new TSParser();

    @BeforeAll
    static void beforeAll() {
        parser.setLanguage(json);
        tree = Objects.requireNonNull(parser.parseString(null, JSON_SRC));
    }

    @Test
    void copy() {
        tree.copy();
        assertNotEquals(tree.getPtr(), tree.copy().getPtr());
    }

    @Test
    void getRootNode() {
        TSNode rootNode = Objects.requireNonNull(tree.getRootNode());
        assertEquals("document", rootNode.getType());
    }

    @Test
    void getRootNodeWithOffset() {
        TSNode rootNode = Objects.requireNonNull(tree.getRootNodeWithOffset(0, new TSPoint(0, 0)));
        assertEquals("document", rootNode.getType());
    }

    @Test
    void getLanguage() {
        assertEquals(json.getPtr(), tree.getLanguage().getPtr());
    }

    @Test
    void getIncludedRanges() {
        TSRange[] ranges = tree.getIncludedRanges();
        assertTrue(ranges.length > 0);
        TSRange range = tree.getIncludedRanges()[0];
        assertEquals(0, range.getStartByte());
        assertEquals(-1, range.getEndByte());
        assertEquals(0, range.getStartPoint().getRow());
        assertEquals(0, range.getStartPoint().getColumn());
        assertEquals(-1, range.getEndPoint().getRow());
        assertEquals(-1, range.getEndPoint().getColumn());
    }

    @Test
    void edit() {
        assertNotNull(tree);
        final AtomicBoolean edited = new AtomicBoolean(false);
        parser.reset();
        byte[] buf = new byte[1024];
        String newJsonSrc = "[1, null, 4]";
        TSReader reader = (buf1, offset, position) -> {
            ByteBuffer byteBuffer = ByteBuffer.wrap(buf1);
            if (edited.get()) {
                if (offset >= newJsonSrc.length()) {
                    return 0;
                }

                byteBuffer.put(newJsonSrc.getBytes(StandardCharsets.UTF_8));
                return newJsonSrc.length();
            } else {
                if (offset >= JSON_SRC.length()) {
                    return 0;
                }
                byteBuffer.put(JSON_SRC.getBytes(StandardCharsets.UTF_8));
                return JSON_SRC.length();
            }
        };
        tree = Objects.requireNonNull(parser.parse(buf, null, reader, TSInputEncoding.TSInputEncodingUTF8));
        TSNode root = Objects.requireNonNull(tree.getRootNode());
        assertEquals(1, root.getChildCount());
        assertEquals(2, Objects.requireNonNull(root.getNamedChild(0)).getNamedChildCount());
        int editStart = 0;
        int editEnd = 1;
        tree.edit(new TSInputEdit(
                editStart,
                editStart,
                editEnd,
                new TSPoint(0, editStart),
                new TSPoint(0, editStart),
                new TSPoint(0, editEnd)));
        edited.set(true);
        tree = Objects.requireNonNull(parser.parse(buf, tree, reader, TSInputEncoding.TSInputEncodingUTF8));
        TSNode root2 = Objects.requireNonNull(tree.getRootNode());
        assertEquals(1, root2.getChildCount());
        assertEquals(3, Objects.requireNonNull(root2.getNamedChild(0)).getNamedChildCount());
    }

    @Test
    void getChangedRanges() {
        assertNotNull(tree);
        final AtomicBoolean edited = new AtomicBoolean(false);
        parser.reset();
        byte[] buf = new byte[1024];
        String newJsonSrc = "[1, null, 4]";
        TSReader reader = new TSReader() {
            @Override
            public int read(byte[] buf, int offset, TSPoint position) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
                if (edited.get()) {
                    if (offset >= newJsonSrc.length()) {
                        return 0;
                    }

                    byteBuffer.put(newJsonSrc.getBytes(StandardCharsets.UTF_8));
                    return newJsonSrc.length();
                } else {
                    if (offset >= JSON_SRC.length()) {
                        return 0;
                    }
                    ByteBuffer byteBuffer1 = ByteBuffer.wrap(buf);
                    byteBuffer1.put(JSON_SRC.getBytes(StandardCharsets.UTF_8));
                    return JSON_SRC.length();
                }
            }
        };
        tree = Objects.requireNonNull(parser.parse(buf, null, reader, TSInputEncoding.TSInputEncodingUTF8));
        TSNode root = Objects.requireNonNull(tree.getRootNode());
        assertEquals(1, root.getChildCount());
        assertEquals(2, Objects.requireNonNull(root.getNamedChild(0)).getNamedChildCount());
        int editStart = 0;
        int editEnd = 1;
        tree.edit(new TSInputEdit(
                editStart,
                editStart,
                editEnd,
                new TSPoint(0, editStart),
                new TSPoint(0, editStart),
                new TSPoint(0, editEnd)));
        edited.set(true);
        TSTree newTree = Objects.requireNonNull(parser.parse(buf, tree, reader, TSInputEncoding.TSInputEncodingUTF8));
        TSRange[] ranges = TSTree.getChangedRanges(tree, newTree);
        assertTrue(ranges.length > 0);
        assertEquals(12, ranges[0].getEndByte());
    }

    @Test
    void printDotGraphs() throws IOException {
        assertNotNull(tree);
        File dotFile = File.createTempFile("tree", ".dot");
        tree.printDotGraphs(dotFile);
        assertTrue(dotFile.length() > 0);
    }

    @Test
    void testNullRootThrowsException() {
        // The TSTree API now guarantees that a root node exists for an open tree.
        // If the tree is closed, getRootNode() must throw IllegalStateException.
        TSTree localTree = parser.parseString(null, "{}");
        assertNotNull(localTree);
        localTree.close();
        assertThrows(IllegalStateException.class, localTree::getRootNode);

        // Additionally, the constructor throws IllegalStateException if the
        // native library returns a tree with a null root. While hard to
        // trigger with a healthy native library, the check is present
        // in the constructor.
    }
}
