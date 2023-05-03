package me.raven;

import org.bukkit.inventory.ItemStack;

public class RvSlot {

    private int slot;

    private ItemStack itemStack;

    public RvSlot(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }
}
