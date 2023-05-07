package me.raven.Interfaces;

import me.raven.RvInventory;
import org.bukkit.inventory.ItemStack;

public interface RvSetItem {

    RvInventory setItem(ItemStack itemStack, int... indexes);
    RvInventory setItem(int row, int column, ItemStack itemStack);
}
