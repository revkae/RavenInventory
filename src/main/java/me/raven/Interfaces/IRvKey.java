package me.raven.Interfaces;


import org.bukkit.inventory.ItemStack;

public interface IRvKey {

    void addBlockedKey(String key);

    void removeBlockedKey(String key);

    boolean hasBlockedKey(ItemStack itemStack);

    String getBlockedKey(ItemStack itemStack);

    void addAllowedKey(String key);

    void removeAllowedKey(String key);

    boolean hasAllowedKey(ItemStack itemStack);

    String getAllowedKey(ItemStack itemStack);
}
