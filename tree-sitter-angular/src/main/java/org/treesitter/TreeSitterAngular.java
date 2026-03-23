
package org.treesitter;

import org.treesitter.utils.NativeUtils;

/**
 * Tree-sitter language binding for angular.
 */
public class TreeSitterAngular extends TSLanguage {

    static {
        NativeUtils.loadLib("lib/tree-sitter-angular");
    }
    private native static long tree_sitter_angular();

    /**
     * Create a new instance of the angular language.
     */
    public TreeSitterAngular() {
        super(tree_sitter_angular());
    }

    private TreeSitterAngular(long ptr) {
        super(ptr);
    }

    @Override
    public TSLanguage copy() {
        return new TreeSitterAngular(copyPtr());
    }
}
