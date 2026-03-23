package org.treesitter;

import org.treesitter.utils.NativeUtils;

public class TreeSitterPython extends TSLanguage {

    static {
        NativeUtils.loadLib("lib/tree-sitter-python");
    }

    private static native long tree_sitter_python();

    public TreeSitterPython() {
        super(tree_sitter_python());
    }

    private TreeSitterPython(long ptr) {
        super(ptr);
    }

    @Override
    public TSLanguage copy() {
        return new TreeSitterPython(copyPtr());
    }
}
