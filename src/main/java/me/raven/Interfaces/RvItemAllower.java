package me.raven.Interfaces;

import org.bukkit.inventory.ItemStack;

public interface RvItemAllower {

    void addAllowedItem(ItemStack itemStack);

    void removeAllowedItem(ItemStack itemStack);

    boolean hasAllowedItem(ItemStack itemStack);
}
