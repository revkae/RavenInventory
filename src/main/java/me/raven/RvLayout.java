package me.raven;

import com.google.common.collect.Sets;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public interface RvLayout {

    default void Init(RvInventory rvInventory) {
        if (!Title().isEmpty())
            rvInventory.setTitle(Title());

        BlockedKeys().forEach(rvInventory::addBlockedKey);
        AllowedKeys().forEach(rvInventory::addAllowedKey);
        BlockedItems().forEach(rvInventory::addBlockedItem);
        AllowedItems().forEach(rvInventory::addAllowedItem);

        Setup(rvInventory);
    }

    void Setup(RvInventory rvInventory);

    default String Title() {
        return "";
    }

    default Set<String> BlockedKeys() {
        return Sets.newHashSet();
    }

    default Set<String> AllowedKeys() {
        return Sets.newHashSet();
    }

    default Set<ItemStack> BlockedItems() {
        return Sets.newHashSet();
    }

    default Set<ItemStack> AllowedItems() {
        return Sets.newHashSet();
    }
}