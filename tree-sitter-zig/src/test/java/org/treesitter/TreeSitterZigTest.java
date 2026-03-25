package org.treesitter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class TreeSitterZigTest {

    @Test
    void testPassingExamples() throws IOException {
        Path path = Paths.get("src", "test", "resources", "valid");
        try (TSParser parser = new TSParser()) {
            parser.setLanguage(new TreeSitterZig());
            try (Stream<Path> paths = Files.walk(path)) {
                paths.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".zig"))
                        .forEach(p -> {
                            try {
                                String content = Files.readString(p);
                                TSTree tree = parser.parseString(null, content);
                                assertNotNull(tree, "Tree should not be null for: " + p);
                                TSNode root = tree.getRootNode();
                                assertNotNull(root, "Root node should not be null for: " + p);
                                assertFalse(
                                        root.hasError(),
                                        "Tree should not have error for passing file: " + p + "\n" + root.toString());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }
    }

    @Test
    void testInvalidExamples() throws IOException {
        Path path = Paths.get("src", "test", "resources", "invalid");
        try (TSParser parser = new TSParser()) {
            parser.setLanguage(new TreeSitterZig());
            try (Stream<Path> paths = Files.walk(path)) {
                paths.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".zig"))
                        .forEach(p -> {
                            try {
                                String content = Files.readString(p);
                                TSTree tree = parser.parseString(null, content);
                                assertNotNull(tree, "Tree should not be null for: " + p);
                                TSNode root = tree.getRootNode();
                                assertNotNull(root, "Root node should not be null for: " + p);
                                assertTrue(root.hasError(), "Tree should have error for invalid file: " + p);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }
    }
}
