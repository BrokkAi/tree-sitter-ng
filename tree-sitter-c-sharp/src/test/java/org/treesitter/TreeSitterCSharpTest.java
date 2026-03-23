package org.treesitter;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.treesitter.tests.CorpusTest;

class TreeSitterCSharpTest {
    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitterCSharp(), "c-sharp");
    }
}
