package org.treesitter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.treesitter.tests.CorpusTest;

class TreeSitterAngularTest {

    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitterAngular(), "angular");
    }

    @Test
    void testParseBasicInterpolation() {
        try (TSParser parser = new TSParser()) {
            TSLanguage angular = new TreeSitterAngular();
            parser.setLanguage(angular);

            String source = "<div>{{ title }}</div>";
            TSTree tree = parser.parseString(null, source);
            assertNotNull(tree);
            TSNode rootNode = tree.getRootNode();

            assertNotNull(rootNode);
            String treeString = rootNode.toString();
            assertEquals(
                    "(document (element (start_tag (tag_name)) (interpolation (expression (identifier))) (end_tag (tag_name))))",
                    treeString);
        }
    }
}
