package me.raven;

import me.raven.Abstracts.RvArrangement;
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
