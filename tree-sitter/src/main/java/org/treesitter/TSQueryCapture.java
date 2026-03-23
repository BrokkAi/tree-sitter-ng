package org.treesitter;

import org.jspecify.annotations.Nullable;

public class TSQueryCapture {
    private @Nullable TSNode node;
    private int index;

    public @Nullable TSNode getNode() {
        return node;
    }

    public int getIndex() {
        return index;
    }
}
