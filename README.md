# Tree Sitter NG

Next generation Tree Sitter Java binding. A fork from https://github.com/bonede/tree-sitter-ng.

Start hacking!
```java
// imports are omitted
class Main {
    public static void main(String[] args) {
        TSParser parser = new TSParser();
        // Use `TSLanguage.load` instead if you would like to load parsers as shared object(.so, .dylib, or .dll).
        // TSLanguage.load("path/to/languane/shared/object", "tree_sitter_some_lang");
        TSLanguage json = new TreeSitterJson();
        parser.setLanguage(json);
        TSTree tree = parser.parseString(null, "[1, null]");
        TSNode rootNode = tree.getRootNode();
        TSNode arrayNode = rootNode.getNamedChild(0);
        TSNode numberNode = arrayNode.getNamedChild(0);
    }
}
```

# Features
- 100% [Tree Sitter API](https://github.com/tree-sitter/tree-sitter/blob/master/lib/include/tree_sitter/api.h) coverage.
- Easy to bootstrap cross compiling environments powered by [Zig](https://ziglang.org/).
- Built-in official parsers.
- Load parsers as [shared object](https://tree-sitter.github.io/tree-sitter/cli/build.html) from disk.

# Supported CPUs and OSes
- x86_64-windows
- x86_64-macos
- aarch64-macos
- x86_64-linux
- aarch64-linux

# Developers: How to Add a Parser

To add a new language parser to this project, we provide a code generation task that handles most of the boilerplate. This is also how you can add an "unofficial" or community parser.

1. **Generate the subproject:**
   Run the `gen` task, providing the language name (in this example, Kotlin), its version, and the URL to its source code zip file.
   ```bash
   ./gradlew gen --parser-name=kotlin --parser-version=0.3.8 --parser-zip=https://github.com/fwcd/tree-sitter-kotlin/archive/refs/tags/0.3.8.zip
   ```
   This will create a new directory `tree-sitter-kotlin` with the correct `build.gradle`, `gradle.properties`, JNI bindings, and Java class extending `TSLanguage`. Finally, an entry of `include 'tree-sitter-kotlin'` will be inserted into `settings.gradle`.

2. **Build native modules and test:**
   Our build system automatically uses Zig to cross-compile the native shared libraries for the new parser. You can trigger the download, native compilation, and tests:
   ```bash
   ./gradlew :tree-sitter-kotlin:buildNative
   ./gradlew :tree-sitter-kotlin:test
   ```

# Built-in Parsers
| Name                            | Grammar Version | Source                                                                                           |
|---------------------------------|-----------------|--------------------------------------------------------------------------------------------------|
| `tree-sitter-agda`              | `1.3.3`         | [official](https://github.com/tree-sitter/tree-sitter-agda/archive/refs/tags/v1.3.3.zip)         |
| `tree-sitter-bash`              | `0.25.1`        | [official](https://github.com/tree-sitter/tree-sitter-bash/archive/refs/tags/v0.25.1.zip)        |
| `tree-sitter-c`                 | `0.24.1`        | [official](https://github.com/tree-sitter/tree-sitter-c/archive/refs/tags/v0.24.1.zip)           |
| `tree-sitter-c-sharp`           | `0.23.1`        | [official](https://github.com/tree-sitter/tree-sitter-c-sharp/archive/refs/tags/v0.23.1.zip)     |
| `tree-sitter-cpp`               | `0.23.4`        | [official](https://github.com/tree-sitter/tree-sitter-cpp/archive/refs/tags/v0.23.4.zip)         |
| `tree-sitter-css`               | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-css/archive/refs/tags/v0.25.0.zip)         |
| `tree-sitter-embedded-template` | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-embedded-template/archive/refs/tags/v0.25.0.zip) |
| `tree-sitter-go`                | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-go/archive/refs/tags/v0.25.0.zip)          |
| `tree-sitter-haskell`           | `0.23.1`        | [official](https://github.com/tree-sitter/tree-sitter-haskell/archive/refs/tags/v0.23.1.zip)     |
| `tree-sitter-html`              | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-html/archive/refs/tags/v0.23.2.zip)        |
| `tree-sitter-java`              | `0.23.5`        | [official](https://github.com/tree-sitter/tree-sitter-java/archive/refs/tags/v0.23.5.zip)        |
| `tree-sitter-javascript`        | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-javascript/archive/refs/tags/v0.25.0.zip)  |
| `tree-sitter-json`              | `0.24.8`        | [official](https://github.com/tree-sitter/tree-sitter-json/archive/refs/tags/v0.24.8.zip)        |
| `tree-sitter-julia`             | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-julia/archive/refs/tags/v0.25.0.zip)       |
| `tree-sitter-kotlin`            | `0.3.8`         | [community](https://github.com/fwcd/tree-sitter-kotlin/archive/refs/tags/0.3.8.zip)              |
| `tree-sitter-ocaml`             | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-ocaml/archive/refs/tags/v0.23.2.zip)       |
| `tree-sitter-php`               | `0.24.2`        | [official](https://github.com/tree-sitter/tree-sitter-php/archive/refs/tags/v0.24.2.zip)         |
| `tree-sitter-python`            | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-python/archive/refs/tags/v0.25.0.zip)      |
| `tree-sitter-regex`             | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-regex/archive/refs/tags/v0.25.0.zip)       |
| `tree-sitter-ruby`              | `0.23.1`        | [official](https://github.com/tree-sitter/tree-sitter-ruby/archive/refs/tags/v0.23.1.zip)        |
| `tree-sitter-rust`              | `0.24.0`        | [official](https://github.com/tree-sitter/tree-sitter-rust/archive/refs/tags/v0.24.0.zip)        |
| `tree-sitter-scala`             | `0.24.0`        | [official](https://github.com/tree-sitter/tree-sitter-scala/archive/refs/tags/v0.24.0.zip)       |
| `tree-sitter-tsx`               | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-typescript/archive/refs/tags/v0.23.2.zip)  |
| `tree-sitter-typescript`        | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-typescript/archive/refs/tags/v0.23.2.zip)  |
| `tree-sitter-verilog`           | `1.0.3`         | [official](https://github.com/tree-sitter/tree-sitter-verilog/archive/refs/tags/v1.0.3.zip)     |

# API Tour
```java
class Main {
    public static void main(String[] args) throws Exception {
        String jsonSource = "[1, null]";
        TSParser parser = new TSParser();
        TSLanguage json = new TreeSitterJson();

        // Set language parser
        parser.setLanguage(json);

        // Parse with string input
        TSTree tree = parser.parseString(null, jsonSource);
        assert tree != null;

        parser.reset();
        // Or parse with encoding
        parser.parseStringEncoding(null, jsonSource, TSInputEncoding.TSInputEncodingUTF8);

        parser.reset();
        // Or parse with custom reader
        byte[] buffer = new byte[1024];
        TSReader reader = (buf, offset, position) -> {
            byte[] sourceBytes = jsonSource.getBytes(StandardCharsets.UTF_8);
            if (offset >= sourceBytes.length) {
                return 0;
            }
            ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
            byteBuffer.put(sourceBytes);
            return sourceBytes.length;
        };
        tree = parser.parse(buffer, null, reader, TSInputEncoding.TSInputEncodingUTF8);
        assert tree != null;

        // Traverse the AST tree with DOM-like APIs
        TSNode rootNode = tree.getRootNode();
        TSNode arrayNode = rootNode.getNamedChild(0);

        // Or traverse the AST with cursor
        TSTreeCursor rootCursor = new TSTreeCursor(rootNode);
        rootCursor.gotoFirstChild();

        // Or query the AST with S-expression
        TSQuery query = new TSQuery(json, "((document) @root)");
        TSQueryCursor cursor = new TSQueryCursor();
        cursor.exec(query, rootNode);
        TSQueryMatch match = new TSQueryMatch();
        while (cursor.nextMatch(match)) {
            // do something with the match
        }

        // Debug the parser with a logger
        TSLogger logger = (type, message) -> {
            System.out.println(message);
        };
        parser.setLogger(logger);

        // Or output the AST tree as DOT graph
        File dotFile = File.createTempFile("json", ".dot");
        parser.printDotGraphs(dotFile);
    }
}
```
