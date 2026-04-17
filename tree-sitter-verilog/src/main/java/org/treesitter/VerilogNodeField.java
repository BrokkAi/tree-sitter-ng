package org.treesitter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.Nullable;

/**
 * Node field names for {@code verilog} from tree-sitter {@code node-types.json}.
 */
public enum VerilogNodeField {
    /** Represents a null field reference or a null field name. */
    __NULL__(null);

    private final @Nullable String name;

    VerilogNodeField(@Nullable String name) {
        this.name = name;
    }

    public @Nullable String getName() {
        return name;
    }

    public static VerilogNodeField fromName(@Nullable String name) {
        if (name == null) return __NULL__;
        VerilogNodeField f = LOOKUP.get(name);
        return f == null ? __NULL__ : f;
    }

    private static final Map<String, VerilogNodeField> LOOKUP = initLookup();

    private static Map<String, VerilogNodeField> initLookup() {
        HashMap<String, VerilogNodeField> m = new HashMap<>();
        for (VerilogNodeField f : values()) {
            if (f.name != null) m.put(f.name, f);
        }
        return Collections.unmodifiableMap(m);
    }
}
