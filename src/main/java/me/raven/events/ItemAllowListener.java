package me.raven.events;

import me.raven.Custom.RvCustomEvent;
import me.raven.RvInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemAllowListener implements Listener {

    private final RvInventory rvInventory;

    public ItemAllowListener(RvInventory rvInventory) {
        this.rvInventory = rvInventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        RvInventory clickedInv = new RvInventory(event.getClickedInventory());
        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (!clickedInv.isInventoryNull()) return;

        if (!rvInventory.isSameInventory(clickedInv.build())) return;

        if (!rvInventory.isItemNull(clickedItem)) return;

        if (!rvInventory.hasAllowedItem(clickedItem)) return;

        Bukkit.getServer().getPluginManager().callEvent(new RvCustomEvent(player, event.getClickedInventory(), event));
    }
}
