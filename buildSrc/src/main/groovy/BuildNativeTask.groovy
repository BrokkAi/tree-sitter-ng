

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.Directory
import org.gradle.api.file.FileCollection
import org.gradle.api.file.RegularFile
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction
import org.treesitter.build.Utils

class BuildNativeTask extends DefaultTask{

    static String libExt(String target){
        if(target.contains("windows")){
            return "dll"
        }else if(target.contains("linux")){
            return "so"
        }else if(target.contains("macos")){
            return "dylib"
        }else{
            throw new GradleException("Does not support $target")
        }
    }

    @InputFiles
    FileCollection additionalCFiles = project.files()

    @InputFiles
    FileCollection additionalIncludeDirs = project.files()

    @InputFile
    RegularFile zigExe

    @Input
    List<String> getTargets() {
        def props = (String) project.rootProject.properties.get("treeSitterTargets")
        if(props == null){
            throw new GradleException("Can't find `treeSitterTargets` in gradle.properties")
        }
        props.split(",").collect() { target ->
            target.trim()
        }
    }

    @Input
    String getLibVersion(){
        if (project.hasProperty("libVersion")) {
            return project.property("libVersion")
        }
        return project.version == "unspecified" ? "0.0.0" : project.version.toString()
    }

    @Internal
    Directory getSrcDir(){
        def version = getLibVersion()
        def srcDirName = (version == "0.0.0" || version == "unspecified") ? libName : "$libName-$version"
        return downloadDir.dir(srcDirName)
    }

    @Internal
    Directory getDownloadDir(){
        return project.rootProject.layout.projectDirectory.dir("tools/parsers/" + project.name)
    }

    @Input
    String getLibName() {
        return project.name
    }

    @Internal
    Directory getJniOutDir() {
        return project.layout.buildDirectory.dir("jni-libs").get()
    }

    @OutputFiles
    FileCollection getJniLibFiles(){
        def files = targets.collect() { target ->
            Utils.jniOutFile(project, target, libName)
        }
        return project.files(files)
    }


    @Internal
    Directory getJniCDir(){
        project.layout.projectDirectory.dir("src/main/c")
    }

    @InputFiles
    FileCollection getJniSourceFiles(){
        return jniCDir.asFileTree.matching {
            include("*.c")
            include("*.h")
        }
    }

    @InputFiles
    FileCollection getJniCFiles() {
        return jniCDir.asFileTree.matching {
            include("*.c")
        }
    }

    @InputFiles
    FileCollection getParserSourceFiles() {
        def dir = srcDir.dir("src")
        if (!dir.asFile.exists()) return project.files()
        dir.asFileTree.matching {
            include("**/*.c")
            include("**/*.h")
            include("**/*.cpp")
        }
    }

    @InputFiles
    FileCollection getParserCFiles() {
        def dir = srcDir.dir("src")
        if (!dir.asFile.exists()) return project.files()
        dir.asFileTree.matching {
            include("**/*.c")
            include("**/*.cpp")
        }
    }

    @Internal
    Directory getJniIncludeDir(){
        return project.rootProject.layout.projectDirectory.dir("include/jni")
    }


    Directory getJniMdInclude(String target){
        Directory jniInclude = jniIncludeDir
        if(target.contains("windows")){
            return jniInclude.dir("win32")
        }else if(target.contains("linux")){
            return jniInclude.dir("linux")
        }else if(target.contains("macos")){
            return jniInclude.dir("darwin")
        }else{
            throw new GradleException("Does not support $target")
        }
    }

    RegularFile jniOutFile(String target, String name){
        String ext = libExt(target)
        return jniOutDir.file("$target-$name.$ext")
    }

    BuildNativeTask(){
        description = "Build parser native modules"
        group = "build"
    }

    @TaskAction
    def buildNative() {
        if (!srcDir.asFile.exists() && jniCFiles.isEmpty()) {
            logger.lifecycle("No source found for native build in project ${project.name}, skipping.")
            return
        }
        jniOutDir.dir("lib").asFile.mkdirs()
        targets.each {target ->
            def jniMdIncludeDir = getJniMdInclude(target)
            def jniOutFile = Utils.jniOutFile(project, target, libName)

            def cmd = [
                    zigExe, "c++",
                    "-g0",
                    "-fno-sanitize=undefined",
                    "-shared",
                    "-target", target,
                    "-I", srcDir,
                    "-I", srcDir.dir("lib/include"),
                    "-I", jniIncludeDir,
                    "-I", jniMdIncludeDir,
                    "-o", jniOutFile
            ]
            additionalIncludeDirs.each { f ->
                cmd.add("-I")
                cmd.add(f.absolutePath)
            }
            cmd.addAll(jniCFiles)
            cmd.addAll(parserCFiles)
            cmd.addAll(additionalCFiles)
            project.exec{
                workingDir jniCDir
                commandLine(cmd)
            }
        }
        this.removeWindowsDebugFiles()
    }

    private void removeWindowsDebugFiles(){
        def files = jniOutDir.asFileTree.matching {
            include("**/*.pdb")
            include("**/*.lib")
        }
        project.delete(files)
    }
}
