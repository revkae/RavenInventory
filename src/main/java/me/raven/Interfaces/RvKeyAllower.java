package me.raven.Interfaces;


import org.bukkit.inventory.ItemStack;

public interface RvKeyAllower {

    void addAllowedKey(String key);

    void removeAllowedKey(String key);

    boolean hasAllowedKey(ItemStack itemStack);
}
