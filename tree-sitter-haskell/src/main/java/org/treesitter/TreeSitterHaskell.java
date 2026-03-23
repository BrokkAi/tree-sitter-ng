package org.treesitter;

import org.treesitter.utils.NativeUtils;

public class TreeSitterHaskell extends TSLanguage {

    static {
        NativeUtils.loadLib("lib/tree-sitter-haskell");
    }

    private static native long tree_sitter_haskell();

    public TreeSitterHaskell() {
        super(tree_sitter_haskell());
    }

    private TreeSitterHaskell(long ptr) {
        super(ptr);
    }

    @Override
    public TSLanguage copy() {
        return new TreeSitterHaskell(copyPtr());
    }
}
