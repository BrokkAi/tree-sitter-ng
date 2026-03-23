package org.treesitter;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.treesitter.tests.CorpusTest;

class TreeSitterAgdaTest {
    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitterAgda(), "agda");
    }
}
