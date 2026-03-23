package org.treesitter.utils;

import java.io.*;
import java.util.Locale;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

public abstract class NativeUtils {
    private static final Set<String> loadedLibs = new HashSet<>();

    private static String getFullLibName(String libName) {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String archName = System.getProperty("os.arch").toLowerCase(Locale.ROOT);
        String ext;
        String os;
        String arch;
        if (osName.contains("windows")) {
            ext = "dll";
            os = "windows";
        } else if (osName.contains("linux")) {
            ext = "so";
            os = "linux-gnu";
        } else if (osName.contains("mac")) {
            ext = "dylib";
            os = "macos";
        } else {
            throw new RuntimeException(String.format("Does not support OS: %s", osName));
        }
        if (archName.contains("amd64") || archName.contains("x86_64")) {
            arch = "x86_64";
        } else if (archName.contains("aarch64")) {
            arch = "aarch64";
        } else {
            throw new RuntimeException(String.format("Does not support arch: %s", archName));
        }

        Path path = Path.of(libName);
        String fileName = path.getFileName().toString();
        String fullFileName = String.format("%s-%s-%s.%s", arch, os, fileName, ext);

        Path parent = path.getParent();
        if (parent == null) {
            return fullFileName;
        }
        // Native libraries in JARs always use '/' as separator
        return parent.resolve(fullFileName).toString().replace(File.separatorChar, '/');
    }

    /**
     * Get the path of the native lib. If the lib is in the classpath, it will be
     * extracted to a temporary directory.
     *
     * @param libName Canonical name of the library. e.g. 'lib/foo', 'bar'
     * @return The path of the native lib.
     */
    public static synchronized Path libFile(String libName) {
        String fullLibName = getFullLibName(libName);

        // Check for user-defined library path
        String userDefinedPath = System.getProperty("tree-sitter-lib");
        if (userDefinedPath != null) {
            Path customPath = Path.of(userDefinedPath).resolve(fullLibName);
            if (Files.exists(customPath)) {
                return customPath;
            }
        }

        String prefix = fullLibName.replace("/", "_").replace("\\", "_");
        String suffix = fullLibName.contains(".") ? fullLibName.substring(fullLibName.lastIndexOf(".")) : ".tmp";

        try (InputStream inputStream = NativeUtils.class.getClassLoader().getResourceAsStream(fullLibName)) {
            if (inputStream == null) {
                throw new RuntimeException(String.format("Can't open %s", fullLibName));
            }
            Path tempFile = Files.createTempFile("tree-sitter-ng-" + prefix, suffix);
            tempFile.toFile().deleteOnExit();
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract native library: " + fullLibName, e);
        }
    }

    /**
     * Load native lib from class path by name convention. <br>
     *
     * This action is process safe and thread safe.
     *
     * <p>Name convention: <code>arch-os-name.ext</code>
     * <p><code>arch</code>
     * <ol>
     *     <li>x64: <code>x86_64</code></li>
     *     <li>arm64: <code>aarch64</code></li>
     * </ol>
     * @param libName Canonical name of the library. e.g. 'lib/foo', 'bar'
     */
    public static synchronized void loadLib(String libName) {
        if (loadedLibs.contains(libName)) {
            return;
        }
        Path path = libFile(libName);
        System.load(path.toAbsolutePath().toString());
        loadedLibs.add(libName);
    }
}
