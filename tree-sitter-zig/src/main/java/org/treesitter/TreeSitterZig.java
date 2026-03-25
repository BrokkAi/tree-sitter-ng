
package org.treesitter;

import org.treesitter.utils.NativeUtils;

/**
 * Tree-sitter language binding for {@code zig}.
 * <p>
 * This class provides the native language definition for use with {@link TSParser}.
 */
public class TreeSitterZig extends TSLanguage {

    static {
        NativeUtils.loadLib("lib/tree-sitter-zig");
    }
    private native static long tree_sitter_zig();

    /**
     * Create a new instance of the {@code zig} language.
     */
    public TreeSitterZig() {
        super(tree_sitter_zig());
    }

    /**
     * Create a new instance from an existing native pointer.
     *
     * @param ptr the native pointer to the language
     */
    private TreeSitterZig(long ptr) {
        super(ptr);
    }

    @Override
    public TSLanguage copy() {
        return new TreeSitterZig(copyPtr());
    }
}
