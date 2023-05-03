package me.raven.events;

import me.raven.RvInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemBlockListener implements Listener {

    private final RvInventory rvInventory;

    public ItemBlockListener(RvInventory rvInventory) {
        this.rvInventory = rvInventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory clickedInv = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if (!rvInventory.isInventoryNull(clickedInv)) return;

        if (!rvInventory.isSameInventory(clickedInv)) return;

        if (!rvInventory.isItemNull(clickedItem)) return;

        if (!rvInventory.hasBlockedItem(clickedItem)) return;

        event.setCancelled(true);
    }

}
