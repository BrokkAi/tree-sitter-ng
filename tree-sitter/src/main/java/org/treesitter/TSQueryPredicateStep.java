package org.treesitter;

import org.jspecify.annotations.Nullable;

public class TSQueryPredicateStep {
    private @Nullable TSQueryPredicateStepType type;
    private int valueId;

    public @Nullable TSQueryPredicateStepType getType() {
        return type;
    }

    public int getValueId() {
        return valueId;
    }
}
