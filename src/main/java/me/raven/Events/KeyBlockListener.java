package me.raven.Events;

import me.raven.Custom.RvBlockItemEvent;
import me.raven.RvInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KeyBlockListener implements Listener {

    private final RvInventory rvInventory;

    public KeyBlockListener(RvInventory rvInventory) {
        this.rvInventory = rvInventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInv = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedInv == null) return;

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        if (!clickedInv.equals(rvInventory.build())) return;

        if (!rvInventory.getKey().hasBlockedKey(clickedItem)) return;

        Bukkit.getServer().getPluginManager().callEvent(new RvBlockItemEvent(player, clickedItem, event.getClickedInventory(), event));
    }

}
