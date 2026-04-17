
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.treesitter.build.GenTask

/**
 * Generates org.treesitter.<Lang>NodeTypes from upstream node-types.json (if present).
 */
class GenerateNodeTypesTask extends DefaultTask {

    @OutputDirectory
    final DirectoryProperty outputDir = project.objects.directoryProperty().convention(
            project.layout.projectDirectory.dir("src/main/java")
    )

    @InputDirectory
    final DirectoryProperty upstreamRootDir = project.objects.directoryProperty()

    @Internal
    String getLibShortName() {
        def n = project.name
        return n.startsWith("tree-sitter-") ? n.substring("tree-sitter-".length()) : n
    }

    GenerateNodeTypesTask() {
        description = "Generate NodeTypes constants from node-types.json"
        group = "build setup"
    }

    @TaskAction
    void generate() {
        def root = upstreamRootDir.get().asFile
        def json = GenTask.findNodeTypesJson(root, libShortName)
        if (json == null) {
            logger.lifecycle("No node-types.json found under ${root.absolutePath}; skipping NodeTypes generation for ${project.name}.")
            return
        }

        def nodes = GenTask.parseNodeTypes(json)
        def out = outputDir.get().asFile
        GenTask.writeNodeTypesClass(libShortName, out, nodes)
    }
}
