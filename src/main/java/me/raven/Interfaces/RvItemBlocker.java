package me.raven.Interfaces;

import org.bukkit.inventory.ItemStack;

public interface RvItemBlocker {

    void addBlockedItem(ItemStack itemStack);

    void removeBlockedItem(ItemStack itemStack);

    boolean hasBlockedItem(ItemStack itemStack);
}
