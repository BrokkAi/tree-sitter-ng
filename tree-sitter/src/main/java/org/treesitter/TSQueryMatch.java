package org.treesitter;

import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

public class TSQueryMatch {
    private int id;
    private int patternIndex;
    private int captureIndex;
    private TSQueryCapture[] captures = new TSQueryCapture[0];
    /**
     * Metadata associated with the match, typically populated by {@code #set!} predicates.
     * <p>
     * <b>Note:</b> {@code TSQueryMatch} objects are mutable and reused by {@link TSQueryCursor}.
     * If you need to persist metadata across iterations, you must create a copy of the map.
     */
    private @Nullable Map<String, String> metadata;

    public int getId() {
        return id;
    }

    public int getPatternIndex() {
        return patternIndex;
    }

    public int getCaptureIndex() {
        return captureIndex;
    }

    public TSQueryCapture[] getCaptures() {
        return captures;
    }

    /**
     * Get the metadata for this match.
     *
     * @return A map of metadata. The map is lazily initialized and might be empty.
     */
    public Map<String, String> getMetadata() {
        if (metadata == null) {
            metadata = new HashMap<>(8);
        }
        return metadata;
    }

    /**
     * Clear the metadata for this match.
     * This allows for memory reclamation and avoids eager initialization during cursor iteration.
     */
    public void clearMetadata() {
        metadata = null;
    }
}
