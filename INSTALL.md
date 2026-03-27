# Integrating Tree-Sitter-NG into Gradle (Multi-Project)

Because these libraries contain native components and are hosted as a bundled ZIP release on GitHub, they lack standard Maven metadata. The most robust way to integrate them is using a dedicated "provider" submodule.

## 1. Register the Submodule

In your `settings.gradle.kts`, include the new module:

```kotlin
include("treesitter-provider")
```

## 2. Define Version in `libs.versions.toml`

Keep your version centralized in the catalog:

```toml
[versions]
treesitter = "0.2.4"
```

## 3. Create the `treesitter-provider` Module

Create `treesitter-provider/build.gradle.kts`. This module handles the download, SHA-256 verification, and extraction. It exports the resulting JARs to other modules.

```kotlin
import java.net.URI
import java.security.MessageDigest

plugins {
    `java-library`
}

val treeSitterNgVersion = libs.versions.treesitter.get()
val jarsDir = layout.buildDirectory.dir("jars").get().asFile

val downloadTreeSitterNg = tasks.register("downloadTreeSitterNg") {
    description = "Downloads and extracts tree-sitter-ng native libraries"
    group = "build setup"

    val version = treeSitterNgVersion
    val downloadUrl = "https://github.com/BrokkAi/tree-sitter-ng/releases/download/v$version/tree-sitter-ng-jar.zip"
    val checksumsUrl = "https://github.com/BrokkAi/tree-sitter-ng/releases/download/v$version/checksums.txt"
    
    val cacheDir = layout.buildDirectory.dir("cache/v$version").get().asFile
    val zipFile = layout.buildDirectory.file("cache/tree-sitter-ng-$version.zip").get().asFile
    val checksumsFile = cacheDir.resolve("checksums.txt")

    inputs.property("version", version)
    outputs.dir(cacheDir)
    outputs.file(zipFile)
    outputs.dir(jarsDir)

    doLast {
        if (!cacheDir.exists()) cacheDir.mkdirs()

        // 1. Download checksums.txt
        if (!checksumsFile.exists()) {
            URI(checksumsUrl).toURL().openStream().use { input ->
                checksumsFile.outputStream().use { output -> input.copyTo(output) }
            }
        }

        // 2. Download ZIP
        if (!zipFile.exists()) {
            zipFile.parentFile.mkdirs()
            URI(downloadUrl).toURL().openStream().use { input ->
                zipFile.outputStream().use { output -> input.copyTo(output) }
            }
        }

        // 3. Verify Checksum
        val expectedFileName = "tree-sitter-ng-jar.zip"
        val expectedHash = checksumsFile.useLines { lines ->
            lines.map { it.split(Regex("\\s+")) }
                .find { it.size >= 2 && it[1] == expectedFileName }
                ?.get(0)
        } ?: throw GradleException("No checksum entry found for $expectedFileName")

        val digest = MessageDigest.getInstance("SHA-256")
        val actualHash = zipFile.inputStream().use { input ->
            val buffer = ByteArray(8192)
            var read = input.read(buffer)
            while (read != -1) {
                digest.update(buffer, 0, read)
                read = input.read(buffer)
            }
            digest.digest().joinToString("") { b -> "%02x".format(b) }
        }

        if (!actualHash.equals(expectedHash, ignoreCase = true)) {
            zipFile.delete()
            throw GradleException("SHA-256 mismatch for $zipFile")
        }

        // 4. Extract
        if (!jarsDir.exists()) jarsDir.mkdirs()
        copy {
            from(zipTree(zipFile))
            into(jarsDir)
            include("**/*.jar")
            eachFile {
                path = name // Flatten
                path = path.replace("tree-sitter-ng", "tree-sitter") // Normalize names
            }
            includeEmptyDirs = false
        }
    }
}

dependencies {
    // Export the directory as a library. 'builtBy' ensures the task runs first.
    api(fileTree(jarsDir) {
        include("*.jar")
        builtBy(downloadTreeSitterNg)
    })
}
```

## 4. Usage in Other Subprojects

In any module that needs Tree-sitter (e.g., `app/build.gradle.kts`), simply add the project dependency. Gradle will automatically ensure the download task in `:treesitter-provider` completes before compiling `:app`.

```kotlin
dependencies {
    implementation(project(":treesitter-provider"))
}
```

## Important Considerations

### Why this approach?
Unlike `flatDir`, which is a repository type that can be "empty" during configuration if the files aren't there yet, a `project` dependency with `builtBy` creates a hard link in the Gradle task graph. This eliminates race conditions where the compiler starts before the JARs are downloaded.

### Native Access
When running your application, Tree-sitter uses JNI. For JDK 21+, you must allow native access:
`--enable-native-access=ALL-UNNAMED`

### IDE Sync
Because the JARs are only available after the download task runs, you may need to run `./gradlew :treesitter-provider:downloadTreeSitterNg` once before your IDE (IntelliJ/Eclipse) can fully resolve the symbols.
