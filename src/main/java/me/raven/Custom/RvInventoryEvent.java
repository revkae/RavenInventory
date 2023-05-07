package me.raven.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RvInventoryEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final ItemStack trigger;
    private final Inventory oldInventory;
    private final Inventory newInventory;
    private final InventoryClickEvent event;

    public RvInventoryEvent(Player player, ItemStack trigger, Inventory oldInventory, Inventory newInventory, InventoryClickEvent event) {
        this.player = player;
        this.trigger = trigger;
        this.oldInventory = oldInventory;
        this.newInventory = newInventory;
        this.event = event;
    }

    public InventoryClickEvent getEvent() {
        return event;
    }

    public Inventory getOldInventory() {
        return oldInventory;
    }

    public Player getPlayer() {
        return player;
    }

    public Inventory getNewInventory() {
        return newInventory;
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
