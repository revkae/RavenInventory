package me.raven;

import me.raven.Abstracts.RvArrangement;
import org.bukkit.inventory.ItemStack;

public class RvColumn extends RvArrangement {

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
