package me.raven.Interfaces;

import me.raven.RvInventory;
import me.raven.events.*;
import org.bukkit.plugin.java.JavaPlugin;

public interface RvListenerRegisterer {

    default void registerAll(RvInventory inventory, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemBlockListener(inventory), plugin);
        plugin.getServer().getPluginManager().registerEvents(new KeyBlockListener(inventory), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ItemAllowListener(inventory), plugin);
        plugin.getServer().getPluginManager().registerEvents(new KeyAllowListener(inventory), plugin);
    }

    default void registerKeyBlocker(RvInventory inventory, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new KeyBlockListener(inventory), plugin);
    }

    default void registerItemBlocker(RvInventory inventory, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemBlockListener(inventory), plugin);
    }

    default void registerItemAllower(RvInventory inventory, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ItemAllowListener(inventory), plugin);
    }

    default void registerKeyAllower(RvInventory inventory, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new KeyAllowListener(inventory), plugin);
    }

    default void registerInventoryOpenerAllower(RvInventory inventory, JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new InventoryOpenerListener(inventory), plugin);
    }
}
