package org.treesitter;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.treesitter.tests.CorpusTest;

class TreeSitterCTest {
    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitterC(), "c");
    }
}
