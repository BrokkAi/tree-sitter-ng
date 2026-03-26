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
