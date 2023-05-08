package me.raven.events;

import me.raven.Custom.RvInventoryEvent;
import me.raven.RvPageInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class InventoryPageListener implements Listener {

    private RvPageInventory rvPageInventory;

    public InventoryPageListener(RvPageInventory rvPageInventory) {
        this.rvPageInventory = rvPageInventory;
    }

    @EventHandler
    public void onClick(RvInventoryEvent event) {
        Player player = event.getPlayer();
        Inventory newInventory = event.getNewInventory();

        player.openInventory(newInventory);
    }
}
