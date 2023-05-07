package me.raven.Interfaces;


import org.bukkit.inventory.ItemStack;

public interface RvKeyBlocker {

    void addBlockedKey(String key);

    void removeBlockedKey(String key);

    boolean hasBlockedKey(ItemStack itemStack);
}
