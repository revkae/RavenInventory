package me.raven;

import me.raven.abstracts.ARvGrid;
import org.bukkit.inventory.ItemStack;

public class RvColumn extends ARvGrid {

    public RvColumn() {
        super();
    }

    public RvColumn(ItemStack... items) {
        super(items);
    }

    public RvColumn(RvSlot... slots) {
        super(slots);
    }
}
