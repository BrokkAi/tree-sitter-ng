package org.treesitter;

import org.treesitter.utils.NativeUtils;

/**
 * Tree-sitter language binding for {@code vue}.
 * <p>
 * This class provides the native language definition for use with {@link TSParser}.
 */
public class TreeSitterVue extends TSLanguage {

    static {
        NativeUtils.loadLib("lib/tree-sitter-vue");
    }

    private static native long tree_sitter_vue();

    /**
     * Create a new instance of the {@code vue} language.
     */
    public TreeSitterVue() {
        super(tree_sitter_vue());
    }

    /**
     * Create a new instance from an existing native pointer.
     *
     * @param ptr the native pointer to the language
     */
    private TreeSitterVue(long ptr) {
        super(ptr);
    }

    @Override
    public TSLanguage copy() {
        return new TreeSitterVue(copyPtr());
    }
}
