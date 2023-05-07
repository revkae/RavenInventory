package me.raven.Interfaces;

import me.raven.RvInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface RvInventoryOpener {

    RvInventory addItemForInventory(ItemStack itemStack, Inventory inventory);
    RvInventory removeItemForInventory(ItemStack itemStack, Inventory inventory);
    Inventory getItemForInventory(ItemStack itemStack);
    boolean containsItemForInventory(ItemStack itemStack);
}
