package me.raven;

import me.raven.abstracts.RvArrangement;
import org.bukkit.inventory.ItemStack;

public class RvRow extends RvArrangement {

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
