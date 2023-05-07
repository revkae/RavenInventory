package me.raven.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class RvBlockItemEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Inventory inventory;
    private InventoryClickEvent event;

    public RvBlockItemEvent(Player player, Inventory inventory, InventoryClickEvent event) {
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
