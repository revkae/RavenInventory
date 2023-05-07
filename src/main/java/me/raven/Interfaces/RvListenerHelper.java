package me.raven.Interfaces;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface RvListenerHelper {

    boolean isInventoryNull();

    boolean isSameInventory(Inventory inventory);

    boolean isItemNull(ItemStack itemStack);
}
