package org.treesitter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TSParserTest {

    public static final String JSON_SRC = "[1, null]";
    private static TSLanguage json = new TreeSitterJson();
    private static TSParser parser = new TSParser();

    @BeforeAll
    static void beforeAll() {
        parser.setLanguage(json);
    }

    @Test
    public void parseString() {
        TSTree tree = Objects.requireNonNull(parser.parseString(null, JSON_SRC));
        assertNotNull(tree);
    }

    @Test
    public void parseStringOrThrow() {
        TSTree tree = parser.parseStringOrThrow(null, JSON_SRC);
        assertNotNull(tree);
    }

    @Test
    public void parseEncoding() {
        parser.reset();
        TSTree tree =
                Objects.requireNonNull(parser.parseStringEncoding(null, JSON_SRC, TSInputEncoding.TSInputEncodingUTF8));
        assertNotNull(tree);
    }

    @Test
    public void parse() {
        parser.reset();
        byte[] buffer = new byte[1024];
        String input = JSON_SRC;
        TSReader reader = (buf, offset, position) -> {
            byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
            if (offset >= bytes.length) {
                return 0;
            }
            ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
            byteBuffer.put(bytes);
            return bytes.length;
        };
        TSTree tree = Objects.requireNonNull(parser.parse(buffer, null, reader, TSInputEncoding.TSInputEncodingUTF8));
        assertNotNull(tree);
        TSNode rootNode = Objects.requireNonNull(tree.getRootNode());
        TSNode arrayNode = Objects.requireNonNull(rootNode.getNamedChild(0));
        TSNode numberNode = Objects.requireNonNull(arrayNode.getNamedChild(0));
        assertEquals("document", rootNode.getType());
        assertEquals("array", arrayNode.getType());
        assertEquals("number", numberNode.getType());
    }

    @Test
    public void parseWithOptions() {
        parser.reset();
        byte[] buffer = new byte[1024];
        String input = "{\n" + "  \"id\": 12345,\n"
                + "  \"name\": \"Alice\",\n"
                + "  \"email\": \"alice@example.com\",\n"
                + "  \"age\": 30,\n"
                + "  \"isActive\": true,\n"
                + "  \"roles\": [\"admin\", \"editor\"],\n"
                + "  \"profile\": {\n"
                + "    \"bio\": \"Loves programming and cats.\",\n"
                + "    \"location\": \"San Francisco\",\n"
                + "    \"website\": \"https://alice.dev\"\n"
                + "  }\n"
                + "}";
        TSReader reader = (buf, offset, position) -> {
            byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
            if (offset >= bytes.length) {
                return 0;
            }
            ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
            byteBuffer.put(bytes);
            return bytes.length;
        };
        TSTree tree = Objects.requireNonNull(
                parser.parseWithOptions(buffer, null, reader, TSInputEncoding.TSInputEncodingUTF8, (parserState) -> {
                    assertTrue(parserState.getCurrentByteOffset() > 0);
                    return parserState.hasError();
                }));
        assertNotNull(tree);
        TSNode rootNode = Objects.requireNonNull(tree.getRootNode());
        TSNode arrayNode = Objects.requireNonNull(rootNode.getNamedChild(0));
        TSNode numberNode = Objects.requireNonNull(arrayNode.getNamedChild(0));
        assertEquals("document", rootNode.getType());
        assertEquals("object", arrayNode.getType());
        assertEquals("pair", numberNode.getType());
    }

    @Test
    public void parseRange() {
        TSParser parser = new TSParser();
        TSLanguage json = new TreeSitterJson();
        parser.setLanguage(json);
        assertNotNull(Objects.requireNonNull(parser.parseString(null, JSON_SRC)));
        parser.setLanguage(json);
        assertTrue(parser.setIncludedRanges(new TSRange[] {new TSRange(new TSPoint(0, 0), new TSPoint(0, 9), 0, 9)}));
        TSRange[] ranges = parser.getIncludedRanges();
        assertEquals(1, ranges.length);
        assertEquals(0, ranges[0].getStartByte());
        assertEquals(9, ranges[0].getEndByte());
        assertEquals(0, ranges[0].getStartPoint().getRow());
        assertEquals(0, ranges[0].getStartPoint().getColumn());
        assertEquals(0, ranges[0].getEndPoint().getRow());
        assertEquals(9, ranges[0].getEndPoint().getColumn());
    }

    @Test
    void reset() {
        TSParser parser = new TSParser();
        parser.reset();
    }

    @Test
    void setLogger() {
        TSParser parser = new TSParser();
        TSLanguage json = new TreeSitterJson();
        parser.setLanguage(json);
        TSLogger logger = (type, message) -> {
            assertFalse(message.isEmpty());
            assertNotNull(type);
        };
        parser.setLogger(logger);
        assertNotNull(Objects.requireNonNull(parser.parseString(null, JSON_SRC)));
    }

    @Test
    void getLogger() {
        TSParser parser = new TSParser();
        TSLanguage json = new TreeSitterJson();
        parser.setLanguage(json);
        TSLogger logger = (type, message) -> System.out.format("%s %s\n", type, message);
        parser.setLogger(logger);
        assertEquals(logger, parser.getLogger());
    }

    @Test
    void getLanguage() {
        TSParser parser = new TSParser();
        TSLanguage json = new TreeSitterJson();
        parser.setLanguage(json);
        TSLanguage language = Objects.requireNonNull(parser.getLanguage());
        assertNotNull(language);
        assertEquals(language.getPtr(), json.getPtr());
    }

    @Test
    void printDotGraphs() throws IOException {
        TSParser parser = new TSParser();
        TSLanguage json = new TreeSitterJson();
        parser.setLanguage(json);
        File dotFile = File.createTempFile("json", ".dot");
        parser.printDotGraphs(dotFile);
        assertNotNull(Objects.requireNonNull(parser.parseString(null, JSON_SRC)));
        assertTrue(dotFile.length() > 0);
        parser.printDotGraphs(null);
        parser.reset();
        assertNotNull(Objects.requireNonNull(parser.parseString(null, JSON_SRC)));
    }

    @Test
    void emojiInUtf16() {
        // the 🌏 as in utf16
        // ref https://www.fileformat.info/info/unicode/char/1f30e/index.htm
        String emoji = "\uD83C\uDF0E";
        parser.reset();
        TSTree tree = Objects.requireNonNull(parser.parseString(null, emoji));
        TSNode node = Objects.requireNonNull(tree.getRootNode());
        byte[] bytes = emoji.getBytes(StandardCharsets.UTF_8);
        assertEquals(4, bytes.length);
        assertEquals(node.getEndByte(), bytes.length);
    }

    @Test
    void emojiInSourceCode() {
        // 🌏 emoji
        String emoji = "\uD83C\uDF10";
        parser.reset();
        TSTree tree = Objects.requireNonNull(parser.parseString(null, emoji));
        TSNode node = Objects.requireNonNull(tree.getRootNode());
        byte[] bytes = emoji.getBytes(StandardCharsets.UTF_8);
        assertEquals(4, bytes.length);
        assertEquals(node.getEndByte(), bytes.length);
    }

    @Test
    void nodeText() {
        // 🌏 emoji
        String emoji = "\uD83C\uDF10";
        parser.reset();
        TSTree tree = Objects.requireNonNull(parser.parseString(null, emoji));
        TSNode node = Objects.requireNonNull(tree.getRootNode());
        byte[] bytes = emoji.getBytes(StandardCharsets.UTF_8);
        int startByte = node.getStartByte();
        int endByte = node.getEndByte();
        byte[] nodeBytes = Arrays.copyOfRange(bytes, startByte, endByte);
        String s = new String(nodeBytes, StandardCharsets.UTF_8);
        assertEquals(4, bytes.length);
        assertEquals(node.getEndByte(), bytes.length);
        assertEquals(s, emoji);
    }

    @Test
    void cursor() {
        TSParser parser = new TSParser();
        TSLanguage lang = new TreeSitterJson();
        parser.setLanguage(lang);
        TSTree tree = Objects.requireNonNull(parser.parseString(null, "c"));
        TSNode rootNode = Objects.requireNonNull(tree.getRootNode());
        TSTreeCursor cursor = new TSTreeCursor(rootNode);
        TSNode currentNode = cursor.currentNode();
        assertNotNull(currentNode);
        assertNotNull(currentNode.getType());
        assertNotNull(currentNode.getTree());
    }
}
