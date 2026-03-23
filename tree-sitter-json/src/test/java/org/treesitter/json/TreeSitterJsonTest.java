package org.treesitter.json;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.treesitter.TreeSitterJson;
import org.treesitter.tests.CorpusTest;

class TreeSitterJsonTest {
    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitterJson(), "json");
    }
}
