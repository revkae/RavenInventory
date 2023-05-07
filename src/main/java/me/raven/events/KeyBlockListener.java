package me.raven.events;

import de.tr7zw.nbtapi.NBTItem;
import me.raven.Custom.RvAllowItemEvent;
import me.raven.Custom.RvBlockItemEvent;
import me.raven.RvInventory;
import org.bukkit.Bukkit;
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
        RvInventory clickedInv = new RvInventory(event.getClickedInventory());
        ItemStack clickedItem = event.getCurrentItem();

        if (!rvInventory.isInventoryNull()) return;

        if (!rvInventory.isSameInventory(clickedInv.build())) return;

        if (!rvInventory.isItemNull(clickedItem)) return;

        if (!rvInventory.hasBlockedKey(clickedItem)) return;

        Bukkit.getServer().getPluginManager().callEvent(new RvBlockItemEvent(player, clickedItem, event.getClickedInventory(), event));
    }

}
