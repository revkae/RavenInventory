package me.raven.Interfaces;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface RvGetItem {

    ItemStack getItem(int row, int column);

    List<ItemStack> getItems(int... indexes);
}
