package me.raven.Interfaces;

import me.raven.RvInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IRvExtra {

    RvInventory setTitle(String title);
    RvInventory fillOut(ItemStack itemStack);
    RvInventory fillOut(ItemStack itemStack, int... indexes);
    RvInventory fillEmptySlots(ItemStack itemStack);

    boolean isEqual(Inventory inventory);
    boolean isEmpty();
    boolean isSlotEmpty(int slot);
    boolean isFull();
    boolean isSlotFull(int slot);

    void open(Player player);
    void close(Player player);
    void update(Player player);
}
