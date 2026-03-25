

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.treesitter.build.Utils

class DownloadSourceTask extends DefaultTask{
    @Input
    String url = ""

    @Internal
    Directory getDownloadDir(){
        return project.rootProject.layout.projectDirectory.dir("tools/parsers/" + project.name)
    }

    @Input
    String getLibVersion(){
        if(project.hasProperty("libVersion")){
            return project.property("libVersion")
        }
        return project.property("version")
    }

    @Input
    String getUpstreamVersion(){
        if(project.hasProperty("upstreamVersion")){
            return project.property("upstreamVersion")
        }
        return getLibVersion()
    }

    @Input
    String getLibName(){
        return project.name
    }

    @OutputFile
    RegularFile getZipFile(){
        return downloadDir.file("$libName-v${upstreamVersion}.zip")
    }

    @OutputDirectory
    Directory getSrcDir(){
        def srcDirName = "$libName-$upstreamVersion"
        return downloadDir.dir(srcDirName)
    }

    DownloadSourceTask(){
        this.description = "Download parser source"
        this.group = "download"
    }

    @Internal
    String getDefaultUrl(){
        return "https://github.com/tree-sitter/${libName}/archive/refs/tags/v${upstreamVersion}.zip"
    }

    @TaskAction
    def downloadSource(){
        if (srcDir.asFile.exists() && srcDir.asFile.list().length > 0) {
            return
        }
        if(!downloadDir.asFile.exists()){
            downloadDir.asFile.mkdirs()
        }
        def url = this.url == "" ? defaultUrl : url
        Utils.downloadFile(url, zipFile.asFile)
        Utils.unzipFile(zipFile.asFile, downloadDir.asFile)
    }
}
