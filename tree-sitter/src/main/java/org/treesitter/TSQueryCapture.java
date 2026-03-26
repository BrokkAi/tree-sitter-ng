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

    /**
     * Create a copy of this capture.
     *
     * @return A new capture instance.
     */
    public TSQueryCapture copy() {
        TSQueryCapture copy = new TSQueryCapture();
        copy.node = this.node;
        copy.index = this.index;
        return copy;
    }
}
