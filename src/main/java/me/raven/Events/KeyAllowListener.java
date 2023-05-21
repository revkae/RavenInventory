package me.raven.Events;

import me.raven.Custom.RvAllowItemEvent;
import me.raven.RvInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KeyAllowListener implements Listener {

    private final RvInventory rvInventory;

    public KeyAllowListener(RvInventory rvInventory) {
        this.rvInventory = rvInventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory clickedInv = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (clickedInv == null) return;

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        if (!clickedInv.equals(rvInventory.build())) return;

        if (!rvInventory.getKey().hasAllowedKey(clickedItem)) return;

        String key = rvInventory.getKey().getAllowedKey(clickedItem);

        Bukkit.getServer().getPluginManager().callEvent(new RvAllowItemEvent(player, clickedItem, slot, key, event.getClickedInventory(), event));
    }

}
