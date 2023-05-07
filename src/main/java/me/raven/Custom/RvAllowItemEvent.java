package me.raven.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;

public class RvAllowItemEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Inventory inventory;
    private final InventoryClickEvent event;

    public RvAllowItemEvent(Player player, Inventory inventory, InventoryClickEvent event) {
        this.player = player;
        this.inventory = inventory;
        this.event = event;
    }

    public InventoryClickEvent getEvent() {
        return event;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Player getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
