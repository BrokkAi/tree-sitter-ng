package org.treesitter;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.treesitter.tests.CorpusTest;

class TreeSitterPythonTest {
    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitterPython(), "python");
    }
}
