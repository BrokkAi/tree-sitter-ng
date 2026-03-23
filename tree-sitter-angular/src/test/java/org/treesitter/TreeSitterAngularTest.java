
package org.treesitter;

import org.junit.jupiter.api.Test;
import org.treesitter.tests.CorpusTest;

import java.io.IOException;

class TreeSitterAngularTest {
    @Test
    void corpusTest() throws IOException {
        CorpusTest.runAllTestsInDefaultFolder(new TreeSitterAngular(), "angular");
    }
}
