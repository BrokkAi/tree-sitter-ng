package org.treesitter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TSQueryMetadataTest {
    public static final String JSON_SRC = "[1, 2]";
    private TSLanguage json;
    private TSQueryCursor cursor;

    @SuppressWarnings("NullAway.Init")
    private TSNode rootNode;

    @BeforeEach
    void beforeEach() {
        TSParser parser = new TSParser();
        json = new TreeSitterJson();
        parser.setLanguage(json);
        TSTree tree = Objects.requireNonNull(parser.parseString(null, JSON_SRC));
        rootNode = Objects.requireNonNull(tree.getRootNode());
        cursor = new TSQueryCursor();
    }

    @Test
    void testSetMetadataDirective() {
        // ((number) @n (#set! role "foo"))
        TSQuery query = new TSQuery(json, "((number) @n (#set! role \"foo\"))");
        cursor.exec(query, rootNode, JSON_SRC);
        TSQueryMatch match = new TSQueryMatch();

        int count = 0;
        while (cursor.nextMatch(match)) {
            count++;
            Map<String, String> metadata = match.getMetadata();
            assertEquals("foo", metadata.get("role"), "Metadata 'role' should be 'foo'");
        }
        assertEquals(2, count, "Should have matched two numbers");
    }

    @Test
    void testIsPredicateSuccess() {
        // ((number) @n (#set! role "foo") (#is? role "foo"))
        TSQuery query = new TSQuery(json, "((number) @n (#set! role \"foo\") (#is? role \"foo\"))");
        cursor.exec(query, rootNode, JSON_SRC);
        TSQueryMatch match = new TSQueryMatch();

        int count = 0;
        while (cursor.nextMatch(match)) {
            count++;
            assertEquals("foo", match.getMetadata().get("role"));
        }
        assertEquals(2, count, "Both matches should satisfy #is? role 'foo'");
    }

    @Test
    void testIsPredicateFailure() {
        // ((number) @n (#set! role "foo") (#is? role "bar"))
        TSQuery query = new TSQuery(json, "((number) @n (#set! role \"foo\") (#is? role \"bar\"))");
        cursor.exec(query, rootNode, JSON_SRC);
        TSQueryMatch match = new TSQueryMatch();

        int count = 0;
        while (cursor.nextMatch(match)) {
            count++;
        }
        assertEquals(0, count, "No matches should be returned because role is 'foo', not 'bar'");
    }

    @Test
    void testMetadataWithNextCapture() {
        // ((number) @n (#set! role "foo") (#is? role "foo"))
        TSQuery query = new TSQuery(json, "((number) @n (#set! role \"foo\") (#is? role \"foo\"))");
        cursor.exec(query, rootNode, JSON_SRC);
        TSQueryMatch match = new TSQueryMatch();

        int count = 0;
        while (cursor.nextCapture(match)) {
            count++;
            assertEquals("foo", match.getMetadata().get("role"), "Metadata should be preserved in nextCapture");
        }
        assertEquals(2, count, "Both captures should satisfy #is? role 'foo'");
    }

    @Test
    void testMetadataIsolationBetweenMatches() {
        // We have [1, 2].
        // Pattern 0 matches '1' and sets role=first
        // Pattern 1 matches '2' and sets nothing
        String queryString =
                "((number) @n1 (#eq? @n1 \"1\") (#set! role \"first\")) " + "((number) @n2 (#eq? @n2 \"2\"))";

        TSQuery query = new TSQuery(json, queryString);
        cursor.exec(query, rootNode, JSON_SRC);

        // First match (the number 1)
        TSQueryMatch match = new TSQueryMatch();
        assertTrue(cursor.nextMatch(match), "First match not found");
        Map<String, String> metadata1 = match.getMetadata();
        assertNotNull(metadata1);
        assertEquals("first", metadata1.get("role"), "First match should have metadata");

        // Second match (the number 2)
        // Reset the cursor/query state implicitly by getting the next match into a NEW object
        TSQueryMatch match2 = new TSQueryMatch();
        assertTrue(cursor.nextMatch(match2), "Second match not found");
        Map<String, String> metadata2 = match2.getMetadata();
        assertTrue(
                metadata2 == null || !metadata2.containsKey("role"), "Second match metadata should be cleared/empty");
    }
}
