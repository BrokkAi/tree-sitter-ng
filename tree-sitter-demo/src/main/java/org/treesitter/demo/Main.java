package org.treesitter.demo;

import org.treesitter.TSNode;
import org.treesitter.TSParser;
import org.treesitter.TSTree;
import org.treesitter.TreeSitterJson;

public class Main {
    public static void main(String[] args) {
        // Example from https://tree-sitter.github.io/tree-sitter/using-parsers
        try (TSParser tsParser = new TSParser()) {
            tsParser.setLanguage(new TreeSitterJson());
            TSTree tree = tsParser.parseString(null, "[1, null]");
            if (tree != null) {
                TSNode rootNode = tree.getRootNode();
                assert rootNode != null;
                TSNode arrayNode = rootNode.getNamedChild(0);
                assert arrayNode != null;
                TSNode numberNode = arrayNode.getNamedChild(0);
                assert rootNode.getType().equals("document");
                assert arrayNode.getType().equals("array");
                assert numberNode.getType().equals("number");
                assert rootNode.getChildCount() == 1;
                assert arrayNode.getChildCount() == 5;
                assert arrayNode.getNamedChildCount() == 2;
                assert numberNode.getChildCount() == 0;
                String string = rootNode.toString();
                System.out.printf("Syntax tree: %s\n%n", string);
            } else {
                System.err.println("Syntax tree is null!");
            }
        }
    }
}
