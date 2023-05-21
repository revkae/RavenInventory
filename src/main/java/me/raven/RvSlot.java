package me.raven;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class RvSlot {

    private int slot;
    private ItemStack itemStack;

    public RvSlot(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }
}
