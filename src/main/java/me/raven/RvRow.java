package me.raven;

import me.raven.abstracts.ARvGrid;
import org.bukkit.inventory.ItemStack;

public class RvRow extends ARvGrid<RvSlot> {

    public RvRow() {
        super();
    }

    public RvRow(ItemStack... items) {
        super(items);
    }

    public RvRow(RvSlot... slots) {
        super(slots);
    }
}
