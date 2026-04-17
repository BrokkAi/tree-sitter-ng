package org.treesitter.build

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import static org.junit.jupiter.api.Assertions.*

class GenTaskTest {

    @TempDir
    File tempDir

    @Test
    void "should generate java file with TSLanguage extension and copy method"() {
        // Arrange
        String libName = "json"
        
        // Act
        GenTask.genJavaFile(tempDir, libName)
        
        // Assert
        File javaFile = new File(tempDir, "src/main/java/org/treesitter/TreeSitterJson.java")
        assertTrue(javaFile.exists(), "Java file should be generated")
        
        String content = javaFile.text
        assertTrue(content.contains("public class TreeSitterJson extends TSLanguage"), "Should extend TSLanguage")
        assertTrue(content.contains("NativeUtils.loadLib(\"lib/tree-sitter-json\")"), "Should load correct library")
        assertTrue(content.contains("@Override"), "Should have Override annotation")
        assertTrue(content.contains("public TSLanguage copy()"), "Should implement copy method")
        assertTrue(content.contains("return new TreeSitterJson(copyPtr())"), "Should call copyPtr")
    }

    @Test
    void "should generate java test file with CorpusTest call"() {
        // Arrange
        String libName = "json"
        
        // Act
        GenTask.genJavaTestFile(tempDir, libName)
        
        // Assert
        File testFile = new File(tempDir, "src/test/java/org/treesitter/TreeSitterJsonTest.java")
        assertTrue(testFile.exists(), "Test file should be generated")
        
        String content = testFile.text
        assertTrue(content.contains("import org.treesitter.tests.CorpusTest;"), "Should import CorpusTest")
        assertTrue(content.contains("CorpusTest.runAllTestsInDefaultFolder(new TreeSitterJson(), \"json\");"), "Should call runAllTestsInDefaultFolder")
    }

    @Test
    void "should generate build gradle file with downloadSource task"() {
        // Arrange
        String url = "https://example.com/tree-sitter-json.zip"
        
        // Act
        GenTask.genBuildGradle(tempDir, url)
        
        // Assert
        File gradleFile = new File(tempDir, "build.gradle")
        assertTrue(gradleFile.exists(), "build.gradle should be generated")
        
        String content = gradleFile.text
        assertTrue(content.contains("tasks.named('downloadSource')"), "Should configure downloadSource task")
        assertTrue(content.contains("url = \"$url\""), "Should contain the correct URL")
    }

    @Test
    void "should generate properties file with version"() {
        // Arrange
        String version = "0.20.0"
        
        // Act
        GenTask.genProperties(tempDir, version)
        
        // Assert
        File propsFile = new File(tempDir, "gradle.properties")
        assertTrue(propsFile.exists(), "gradle.properties should be generated")
        assertEquals("upstreamVersion=0.20.0", propsFile.text.trim())
    }

    @Test
    void "should generate JNI C file with correct method mapping"() {
        // Arrange
        String libName = "html" // 'html' -> 'tree_sitter_html'
        
        // Act
        GenTask.genJniCFile(tempDir, libName)
        
        // Assert
        File cFile = new File(tempDir, "src/main/c/org_treesitter_TreeSitterHtml.c")
        assertTrue(cFile.exists(), "C file should be generated")
        
        String content = cFile.text
        assertTrue(content.contains("Java_org_treesitter_TreeSitterHtml_tree_1sitter_1html"), "Should handle underscore escaping for JNI")
        assertTrue(content.contains("return (jlong) tree_sitter_html();"), "Should call native symbol")
    }

    @Test
    void "should generate NodeTypes class with named constants and subtype sets"() {
        // Arrange
        def jsonFile = new File(tempDir, "upstream/src/node-types.json")
        jsonFile.parentFile.mkdirs()
        jsonFile.text = """
[
  { "type": "abstract_class_declaration", "named": true },
  { "type": "function_declaration", "named": true },
  {
    "type": "declaration",
    "named": true,
    "subtypes": [
      { "type": "abstract_class_declaration", "named": true },
      { "type": "function_declaration", "named": true },
      { "type": "missing", "named": false }
    ]
  }
]
""".trim()

        // Act
        def parsed = GenTask.parseNodeTypes(jsonFile)
        def outDir = new File(tempDir, "gen")
        GenTask.writeNodeTypesClass("tsx", outDir, parsed)

        // Assert
        def outFile = new File(outDir, "org/treesitter/TsxNodeType.java")
        assertTrue(outFile.exists(), "NodeType enum should be generated")
        def content = outFile.text
        assertTrue(content.contains("public enum TsxNodeType"), "Should generate a TsxNodeType enum")
        assertTrue(content.contains("__NULL__(null)"), "Should generate a null sentinel enum constant")
        assertTrue(content.contains("ABSTRACT_CLASS_DECLARATION(\"abstract_class_declaration\")"))
        assertTrue(content.contains("FUNCTION_DECLARATION(\"function_declaration\")"))
        assertTrue(content.contains("public static final Set<TsxNodeType> DECLARATION_SET = Set.of(ABSTRACT_CLASS_DECLARATION, FUNCTION_DECLARATION);"),
                "Should generate a declaration set containing named subtype constants")
    }

    @Test
    void "should generate NodeField and NodeSchema from fields and children metadata"() {
        // Arrange
        def jsonFile = new File(tempDir, "upstream/src/node-types.json")
        jsonFile.parentFile.mkdirs()
        jsonFile.text = """
[
  {
    "type": "function_declaration",
    "named": true,
    "fields": {
      "name": {
        "required": true,
        "multiple": false,
        "types": [{ "type": "identifier", "named": true }]
      },
      "body": {
        "required": true,
        "multiple": false,
        "types": [{ "type": "statement_block", "named": true }]
      }
    },
    "children": {
      "required": false,
      "multiple": true,
      "types": [{ "type": "comment", "named": true }, { "type": "missing", "named": false }]
    }
  },
  { "type": "identifier", "named": true },
  { "type": "statement_block", "named": true },
  { "type": "comment", "named": true }
]
""".trim()

        // Act
        def parsed = GenTask.parseNodeTypes(jsonFile)
        def outDir = new File(tempDir, "gen")
        GenTask.writeNodeTypesClass("tsx", outDir, parsed)
        GenTask.writeNodeFieldsClass("tsx", outDir, parsed)
        GenTask.writeNodeSchemaClass("tsx", outDir, parsed)

        // Assert
        def fieldFile = new File(outDir, "org/treesitter/TsxNodeField.java")
        assertTrue(fieldFile.exists(), "NodeField enum should be generated")
        def fieldContent = fieldFile.text
        assertTrue(fieldContent.contains("public enum TsxNodeField"), "Should generate a TsxNodeField enum")
        assertTrue(fieldContent.contains("NAME(\"name\")"), "Should generate a NAME field constant")
        assertTrue(fieldContent.contains("BODY(\"body\")"), "Should generate a BODY field constant")
        assertTrue(fieldContent.contains("public static TsxNodeField fromName"), "Should generate a lookup helper")

        def schemaFile = new File(outDir, "org/treesitter/TsxNodeSchema.java")
        assertTrue(schemaFile.exists(), "NodeSchema helper should be generated")
        def schemaContent = schemaFile.text
        assertTrue(schemaContent.contains("public final class TsxNodeSchema"), "Should generate a TsxNodeSchema helper")
        assertTrue(schemaContent.contains("static Set<TsxNodeField> fields"), "Should expose fields(owner)")
        assertTrue(schemaContent.contains("static Set<TsxNodeType> allowedTypes"), "Should expose allowedTypes(owner, field)")
        assertTrue(schemaContent.contains("static Set<TsxNodeType> allowedChildTypes"), "Should expose allowedChildTypes(owner)")
        assertTrue(schemaContent.contains("FUNCTION_DECLARATION"), "Should include owner node type constants")
    }
}
