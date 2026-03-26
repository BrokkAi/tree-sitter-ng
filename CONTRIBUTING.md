# Developers: How to Add a Parser

To add a new language parser to this project, we provide a code generation task that handles most of the boilerplate.
This is also how you can add an "unofficial" or community parser.

1. **Generate the subproject:**
   Run the `gen` task, providing the language name (in this example, Kotlin), its version, and the URL to its source
   code zip file.
   ```bash
   ./gradlew gen --parser-name=kotlin --parser-version=0.3.8 --parser-zip=https://github.com/fwcd/tree-sitter-kotlin/archive/refs/tags/0.3.8.zip
   ```
   This will create a new directory `tree-sitter-kotlin` with the correct `build.gradle`, `gradle.properties`, JNI
   bindings, and Java class extending `TSLanguage`. Finally, an entry of `include 'tree-sitter-kotlin'` will be inserted
   into `settings.gradle`.

2. **Build native modules and test:**
   Our build system automatically uses Zig to cross-compile the native shared libraries for the new parser as part of
   the compilation process. You can trigger the download, native compilation, and tests just by running the tests:
   ```bash
   ./gradlew :tree-sitter-kotlin:test
   ```
