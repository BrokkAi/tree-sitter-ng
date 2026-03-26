package org.treesitter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for AutoCloseable implementation in Tree-sitter Java bindings.
 * <p>
 * Note on TSTree: TSTree is usually created by parser.parse().
 * We test idempotency via manual instantiation if we can't easily get a valid tree without a language.
 * TSTree constructor is package-private, so we test via parser if we had a language.
 * Since we can't easily create a valid TSTree without a language in this generic test
 * and calling methods on a NULL tree pointer crashes, we'll focus on the classes
 * we can safely instantiate and close.
 */
class TSAutoCloseableTest {

    @Test
    void testParserAutoClose() {
        assertDoesNotThrow(() -> {
            try (TSParser parser = new TSParser()) {
                // usage
            }
        });
    }

    @Test
    void testParserDoubleClose() {
        TSParser parser = new TSParser();
        parser.close();
        assertDoesNotThrow(parser::close);
    }

    @Test
    void testQueryCursorAutoClose() {
        assertDoesNotThrow(() -> {
            try (TSQueryCursor cursor = new TSQueryCursor()) {
                // usage
            }
        });
    }

    @Test
    void testQueryCursorDoubleClose() {
        TSQueryCursor cursor = new TSQueryCursor();
        cursor.close();
        assertDoesNotThrow(cursor::close);
    }

    private TSTree createValidTree() {
        TSParser parser = new TSParser();
        parser.setLanguage(new TreeSitterJson());
        TSTree tree = parser.parseString(null, "{}");
        if (tree == null) {
            throw new RuntimeException("Failed to create test tree");
        }
        return tree;
    }

    @Test
    void testTreeUseAfterClose() {
        TSTree tree = createValidTree();
        tree.close();
        assertThrows(IllegalStateException.class, tree::getRootNode);
    }

    @Test
    void testTreeGetChangedRangesAfterClose() {
        TSTree tree1 = createValidTree();
        TSTree tree2 = createValidTree();
        tree1.close();
        assertThrows(IllegalStateException.class, () -> TSTree.getChangedRanges(tree1, tree2));
        assertThrows(IllegalStateException.class, () -> TSTree.getChangedRanges(tree2, tree1));
    }

    @Test
    @SuppressWarnings("NullAway")
    void testTreeGetChangedRangesNull() {
        TSTree tree1 = createValidTree();
        assertThrows(NullPointerException.class, () -> TSTree.getChangedRanges(tree1, null));
    }

    private static class TestLanguageFailCopy extends TSLanguage {
        TestLanguageFailCopy() {
            super(0);
        }

        @Override
        public TSLanguage copy() {
            throw new UnsupportedOperationException();
        }
    }

    @Test
    void testLanguageUseAfterClose() {
        TSLanguage language = new TestLanguageFailCopy();
        language.close();
        assertThrows(IllegalStateException.class, language::symbolCount);
    }
}
