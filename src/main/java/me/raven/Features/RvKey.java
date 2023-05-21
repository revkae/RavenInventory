package me.raven.Features;

import de.tr7zw.nbtapi.NBTItem;
import me.raven.Interfaces.IRvKey;
import me.raven.RvInventory;
import me.raven.abstracts.ARvStorage;
import org.bukkit.inventory.ItemStack;

public class RvKey extends ARvStorage implements IRvKey {

    public RvKey(RvInventory rvInventory) {
        init(rvInventory);
    }

    @Override
    public void addBlockedKey(String key) {
        blockedKeys.add(key);
    }

    @Override
    public void removeBlockedKey(String key) {
        blockedKeys.remove(key);
    }

    @Override
    public boolean hasBlockedKey(ItemStack itemStack) {
        return new NBTItem(itemStack).getKeys().stream().anyMatch(blockedKeys::contains);
    }

    @Override
    public String getBlockedKey(ItemStack itemStack) {
        for (String key : new NBTItem(itemStack).getKeys()) {
            if (blockedKeys.contains(key)) {
                return key;
            }
        }
        return null;
    }

    @Override
    public void addAllowedKey(String key) {
        allowedKeys.add(key);
    }

    @Override
    public void removeAllowedKey(String key) {
        allowedKeys.remove(key);
    }

    @Override
    public boolean hasAllowedKey(ItemStack itemStack) {
        return new NBTItem(itemStack).getKeys().stream().anyMatch(allowedKeys::contains);
    }

    @Override
    public String getAllowedKey(ItemStack itemStack) {
        for (String key : new NBTItem(itemStack).getKeys()) {
            if (allowedKeys.contains(key)) {
                return key;
            }
        }
        return null;
    }
}
