package org.treesitter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.nio.charset.StandardCharsets.UTF_8;

class TreeSitterCssTest {
    private TSParser parser;

    @BeforeEach
    public void setup() {
        TreeSitterCss lang = new TreeSitterCss();
        parser = new TSParser();
        parser.setLanguage(lang);
    }

    @Test
    void examples() throws IOException {
        String ext = ".css";
        String examplesPath = "src/test/resources/examples";
        Path dir = Paths.get(examplesPath);
        try (var walk = Files.walk(dir)) {
            walk.filter(path -> path.toString().endsWith(ext)).forEach(this::parse);
        }
    }

    private void parse(Path file) {
        try {
            String source = Files.readString(file);
            parser.reset();
            parser.parseString(null, source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
