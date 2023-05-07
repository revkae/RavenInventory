package me.raven.Test;

import me.raven.Custom.RvAllowItemEvent;
import me.raven.Custom.RvBlockItemEvent;
import me.raven.Custom.RvInventoryEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DoSomething implements Listener {

    @EventHandler
    public void onAllow(RvAllowItemEvent event) {
        InventoryClickEvent clickEvent = event.getEvent();
        Inventory inventory = event.getInventory();
        ItemStack trigger = event.getTrigger();
        Player player = event.getPlayer();

        // DO WHAT YOU WANT
    }

    @EventHandler
    public void onBlock(RvBlockItemEvent event) {
        InventoryClickEvent clickEvent = event.getEvent();
        Inventory inventory = event.getInventory();
        ItemStack trigger = event.getTrigger();
        Player player = event.getPlayer();

        // DO WHAT YOU WANT
    }

    @EventHandler
    public void onInventory(RvInventoryEvent event) {
        InventoryClickEvent clickEvent = event.getEvent();
        Inventory oldInventory = event.getOldInventory();
        Inventory newInventory = event.getNewInventory();
        ItemStack trigger = event.getTrigger();
        Player player = event.getPlayer();

        // DO WHAT YOU WANT
    }
}
