# Tree Sitter NG

[![CI](https://github.com/BrokkAi/tree-sitter-ng/actions/workflows/ci.yml/badge.svg)](https://github.com/BrokkAi/tree-sitter-ng/actions)
[![Latest Release](https://img.shields.io/github/v/release/BrokkAi/tree-sitter-ng)](https://github.com/BrokkAi/tree-sitter-ng/releases)

Next generation Tree Sitter Java binding. A "Java-first" fork optimized for modern developer experience, safety, and ecosystem breadth.

## Why this fork?

*   **Ecosystem Breadth**: Support for the full modern stack (Kotlin, Groovy, Zig, Angular, Vue.js) which the upstream project refuses to maintain.
*   **Modern Java Ergonomics**: Moving away from C-style wrappers toward a library that feels native to Java 21+.
    *   **Strict Null Safety**: Integration with **JSpecify** and **Error Prone** for compile-time safety at the JNI boundary.
    *   **Idiomatic Patterns**: Lazy collection patterns (e.g., `getNamedChildren()`) and strict handling (e.g., `parseStringOrThrow()`).
    *   **Resource Management**: Automated native memory management using the **Cleaner API** with `AutoCloseable` support.

### Start hacking!

```java
try (TSParser parser = new TSParser();
     TSLanguage json = new TreeSitterJson()) {

    parser.setLanguage(json);
    
    // Use parseStringOrThrow for strict null handling
    try (TSTree tree = parser.parseStringOrThrow(null, "[1, null]")) {
        TSNode rootNode = tree.getRootNode();
        
        // Access children via index
        TSNode arrayNode = rootNode.getNamedChild(0);

        // Or use the new lazy list pattern for easier iteration
        for (TSNode child : arrayNode.getNamedChildren()) {
            System.out.println(child.getType());
        }
    }
}
```

## Supported Grammars

We maintain both official and high-demand community grammars.

| Language | Status | Implementation |
| :--- | :--- | :--- |
| Java, Python, C++, Go | **Official** | Upstream maintained |
| Kotlin, Groovy | **Community** | First-class support in this fork |
| Vue, Angular, Zig | **Community** | Extended ecosystem support |

## Technical Design

### JNI Safety & Memory Management
We bridge the gap between Java's GC and C's manual memory management using a dual-layered approach:
1.  **AutoCloseable**: Primary resources (Parsers, Trees, Cursors) implement `AutoCloseable` for deterministic cleanup via `try-with-resources`.
2.  **Cleaner API**: A `Cleaner` fallback ensures that if a Java object is garbage collected without being closed, the underlying native memory is still freed, preventing leaks in long-running processes.

### Type Safety
By utilizing **JSpecify** annotations and **Error Prone** static analysis, we enforce null-safety across the JNI boundary. This ensures that the "C-heavy" nature of Tree-Sitter doesn't lead to `NullPointerException` or JVM crashes in your Java application.

### Zig Cross-Compilation
We use **Zig** as our C/C++ compiler toolchain. This allows us to produce perfectly matched native binaries for Linux, macOS, and Windows (x86_64 and aarch64) from a single CI environment without complex cross-compilation headers.

# Commands

```bash
# Compile Java and native modules
./gradlew compile

# Build and test all subprojects
./gradlew build
```

# Releases

The project distinguishes between the **Java library version** (`libVersion`) and the **upstream grammar version** (
`upstreamVersion`). We **are not** currently working on Maven Central publishing. For now, we provide a pre-bundled ZIP
to ensure all native binaries are perfectly matched to the library version.

## Lockstep Versioning

We use a **lockstep versioning** strategy for releases. This means that every module in the repository shares the exact
same `libVersion` (e.g., `0.1.0`).
When a new release is cut, all modules are published with this new version number, regardless of whether their specific
parser or upstream grammar changed.

This provides a simple and predictable experience: you only ever need to specify one version number for all
`ai.brokk:tree-sitter-*` dependencies in your build file, and they are guaranteed to be perfectly compatible with each
other.

When building or publishing a new release of the Java bindings, specify the `libVersion`:

```bash
# Build with version
./gradlew build -PlibVersion=0.1.0

# Publish with version
./gradlew publish -PlibVersion=0.1.0
```

The `upstreamVersion` is managed in each subproject's `gradle.properties` and controls which version of the native
tree-sitter C code is downloaded and compiled.

> **Note**: Native binaries are generated into `src/main/resources/lib` during the build process and are ignored by Git.
> They are built automatically in CI and do not need to be committed to the repository.

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

# Integrating Tree-Sitter-NG into Gradle

Because these libraries contain native components and are hosted as a bundled ZIP release on GitHub, the integration
requires a two-step process:

1. **Automated Retrieval**: A Gradle task to download and extract the JARs.
2. **Repository Configuration**: A `flatDir` repository that points to the extracted artifacts.

## 1. Define Versions in `libs.versions.toml`

First, add the coordinates to your Version Catalog. We use a custom namespace (`ai.brokk`) to avoid collisions with
upstream libraries.

```toml
[versions]
treesitter = "0.2.0"

[libraries]
# Core Runtime
treesitter-api = { group = "ai.brokk", name = "tree-sitter", version.ref = "treesitter" }

# Language Modules
treesitter-java = { group = "ai.brokk", name = "tree-sitter-java", version.ref = "treesitter" }
treesitter-python = { group = "ai.brokk", name = "tree-sitter-python", version.ref = "treesitter" }
# Add other languages as needed...
```

## 2. Configure the Download Task

In your **root** `build.gradle.kts`, create a task to handle the lifecycle of the native ZIP. This task is incremental,
meaning it only runs when the version changes.

```kotlin
tasks.register("downloadTreeSitterNg") {
    val version = libs.versions.treesitter.get()
    val downloadUrl = "https://github.com/BrokkAi/tree-sitter-ng/releases/download/v$version/tree-sitter-ng-jar.zip"
    val cacheDir = file(".gradle/tree-sitter-ng/v$version")
    val zipFile = file(".gradle/tree-sitter-ng/tree-sitter-ng.zip")

    inputs.property("version", version)
    outputs.dir(cacheDir)

    doLast {
        if (!zipFile.exists()) {
            zipFile.parentFile.mkdirs()
            java.net.URI(downloadUrl).toURL().openStream().use { it.copyTo(zipFile.outputStream()) }
        }

        copy {
            from(zipTree(zipFile))
            into(cacheDir)
            // Extract only the language directories
            include("tree-sitter*/**")
            includeEmptyDirs = false
        }
    }
}
```

## 3. Set Up the Local Repository

In the `allprojects` or `subprojects` block of your root build script, configure Gradle to look into the extracted
folders for dependencies.

> **Note:** `flatDir` does not support recursive searching, so you must point it directly to the subdirectories
> containing the `.jar` files.

```kotlin
allprojects {
    repositories {
        val tsVersion = "0.2.0"
        flatDir {
            dirs(
                rootProject.file(".gradle/tree-sitter-ng/v$tsVersion/tree-sitter"),
                rootProject.file(".gradle/tree-sitter-ng/v$tsVersion/tree-sitter-java"),
                rootProject.file(".gradle/tree-sitter-ng/v$tsVersion/tree-sitter-python")
            )
        }
        mavenCentral()
    }
}
```

## 4. Usage in Subprojects

In your application or library module (e.g., `app/build.gradle.kts`), declare the dependencies and ensure the download
task runs before compilation.

```kotlin
dependencies {
    implementation(libs.treesitter.api)
    implementation(libs.treesitter.java)
    implementation(libs.treesitter.python)
}

// Ensure JARs are extracted before the IDE or compiler tries to find them
tasks.withType<JavaCompile> {
    dependsOn(":downloadTreeSitterNg")
}
```

## Important Considerations

### Why Use `flatDir`?

Since these JARs are extracted from a ZIP and do not include Maven `pom.xml` metadata, `flatDir` is the simplest way to
treat a local directory as a repository. It matches the filename (e.g., `tree-sitter-java.jar`) to the dependency name.

### Native Access

When running your application with these libraries, remember that Tree-sitter uses JNI (Java Native Interface). If you
are using JDK 21+, you may need to pass the following JVM argument to allow native access:
`--enable-native-access=ALL-UNNAMED`

### Cleaning

The artifacts are stored in the `.gradle` folder. Running `./gradlew clean` typically does not remove this folder. If
you need to force a re-download, delete the `.gradle/tree-sitter-ng` directory manually.

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

# Built-in Parsers

| Name                            | Grammar Version | Source                                                                                                                    |
|---------------------------------|-----------------|---------------------------------------------------------------------------------------------------------------------------|
| `tree-sitter-agda`              | `1.3.3`         | [official](https://github.com/tree-sitter/tree-sitter-agda/archive/refs/tags/v1.3.3.zip)                                  |
| `tree-sitter-angular`           | `0.8.3`         | [community](https://github.com/dlvandenberg/tree-sitter-angular/archive/refs/tags/v0.8.3.zip)                             |
| `tree-sitter-bash`              | `0.25.1`        | [official](https://github.com/tree-sitter/tree-sitter-bash/archive/refs/tags/v0.25.1.zip)                                 |
| `tree-sitter-c`                 | `0.24.1`        | [official](https://github.com/tree-sitter/tree-sitter-c/archive/refs/tags/v0.24.1.zip)                                    |
| `tree-sitter-c-sharp`           | `0.23.1`        | [official](https://github.com/tree-sitter/tree-sitter-c-sharp/archive/refs/tags/v0.23.1.zip)                              |
| `tree-sitter-cpp`               | `0.23.4`        | [official](https://github.com/tree-sitter/tree-sitter-cpp/archive/refs/tags/v0.23.4.zip)                                  |
| `tree-sitter-css`               | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-css/archive/refs/tags/v0.25.0.zip)                                  |
| `tree-sitter-embedded-template` | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-embedded-template/archive/refs/tags/v0.25.0.zip)                    |
| `tree-sitter-go`                | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-go/archive/refs/tags/v0.25.0.zip)                                   |
| `tree-sitter-haskell`           | `0.23.1`        | [official](https://github.com/tree-sitter/tree-sitter-haskell/archive/refs/tags/v0.23.1.zip)                              |
| `tree-sitter-html`              | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-html/archive/refs/tags/v0.23.2.zip)                                 |
| `tree-sitter-java`              | `0.23.5`        | [official](https://github.com/tree-sitter/tree-sitter-java/archive/refs/tags/v0.23.5.zip)                                 |
| `tree-sitter-javascript`        | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-javascript/archive/refs/tags/v0.25.0.zip)                           |
| `tree-sitter-json`              | `0.24.8`        | [official](https://github.com/tree-sitter/tree-sitter-json/archive/refs/tags/v0.24.8.zip)                                 |
| `tree-sitter-julia`             | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-julia/archive/refs/tags/v0.25.0.zip)                                |
| `tree-sitter-kotlin`            | `0.3.8`         | [community](https://github.com/fwcd/tree-sitter-kotlin/archive/refs/tags/0.3.8.zip)                                       |
| `tree-sitter-ocaml`             | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-ocaml/archive/refs/tags/v0.23.2.zip)                                |
| `tree-sitter-php`               | `0.24.2`        | [official](https://github.com/tree-sitter/tree-sitter-php/archive/refs/tags/v0.24.2.zip)                                  |
| `tree-sitter-python`            | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-python/archive/refs/tags/v0.25.0.zip)                               |
| `tree-sitter-regex`             | `0.25.0`        | [official](https://github.com/tree-sitter/tree-sitter-regex/archive/refs/tags/v0.25.0.zip)                                |
| `tree-sitter-ruby`              | `0.23.1`        | [official](https://github.com/tree-sitter/tree-sitter-ruby/archive/refs/tags/v0.23.1.zip)                                 |
| `tree-sitter-rust`              | `0.24.0`        | [official](https://github.com/tree-sitter/tree-sitter-rust/archive/refs/tags/v0.24.0.zip)                                 |
| `tree-sitter-scala`             | `0.24.0`        | [official](https://github.com/tree-sitter/tree-sitter-scala/archive/refs/tags/v0.24.0.zip)                                |
| `tree-sitter-tsx`               | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-typescript/archive/refs/tags/v0.23.2.zip)                           |
| `tree-sitter-typescript`        | `0.23.2`        | [official](https://github.com/tree-sitter/tree-sitter-typescript/archive/refs/tags/v0.23.2.zip)                           |
| `tree-sitter-verilog`           | `1.0.3`         | [official](https://github.com/tree-sitter/tree-sitter-verilog/archive/refs/tags/v1.0.3.zip)                               |
| `tree-sitter-zig`               | `6479aa13`      | [community](https://github.com/tree-sitter-grammars/tree-sitter-zig/archive/6479aa13f32f701c383083d8b28360ebd682fb7d.zip) |

# API Tour

```java
class Main {
    public static void main(String[] args) throws Exception {
        String jsonSource = "[1, null]";

        // TSParser, TSLanguage, TSTree, TSQuery, TSQueryCursor, TSTreeCursor implement AutoCloseable.
        // They are also registered in the Cleaner, but explicit closing via try-with-resources is recommended.
        try (TSParser parser = new TSParser();
             TSLanguage json = new TreeSitterJson()) {

            // Set language parser
            parser.setLanguage(json);

            // Parse with string input
            try (TSTree tree = parser.parseString(null, jsonSource)) {
                assert tree != null;

                parser.reset();
                // Or parse with encoding
                try (TSTree tree2 = parser.parseStringEncoding(null, jsonSource, TSInputEncoding.TSInputEncodingUTF8)) {
                    // ...
                }

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
                try (TSTree tree3 = parser.parse(buffer, null, reader, TSInputEncoding.TSInputEncodingUTF8)) {
                    assert tree3 != null;
                }

                // Traverse the AST tree with DOM-like APIs
                TSNode rootNode = tree.getRootNode();

                // Access children as a standard Java List
                List<TSNode> children = rootNode.getChildren();
                TSNode arrayNode = rootNode.getNamedChild(0);

                // Or traverse the AST with cursor
                try (TSTreeCursor rootCursor = new TSTreeCursor(rootNode)) {
                    rootCursor.gotoFirstChild();
                }

                // Or query the AST with S-expression
                try (TSQuery query = new TSQuery(json, "((document) @root)");
                     TSQueryCursor cursor = new TSQueryCursor()) {
                    cursor.exec(query, rootNode);
                    TSQueryMatch match = new TSQueryMatch();
                    while (cursor.nextMatch(match)) {
                        // do something with the match
                    }
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
    }
}
```
