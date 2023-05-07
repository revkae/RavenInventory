package me.raven.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RvBlockItemEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final ItemStack trigger;
    private final Inventory inventory;
    private final InventoryClickEvent event;

    public RvBlockItemEvent(Player player, ItemStack trigger, Inventory inventory, InventoryClickEvent event) {
        this.player = player;
        this.trigger = trigger;
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

    public ItemStack getTrigger() {
        return trigger;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
