package me.raven.Features;

import me.raven.Interfaces.IRvListener;
import me.raven.RvInventory;
import me.raven.abstracts.ARvManager;
import me.raven.Events.*;
import org.bukkit.plugin.java.JavaPlugin;

public class RvListener extends ARvManager implements IRvListener {

    public RvListener(RvInventory rvInventory) {
        init(rvInventory);
    }

    @Override
    public void registerAll(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new KeyBlockListener(Inventory()), plugin);
        plugin.getServer().getPluginManager().registerEvents(new KeyAllowListener(Inventory()), plugin);
    }

    @Override
    public void registerKeyBlocker(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new KeyBlockListener(Inventory()), plugin);
    }

    @Override
    public void registerKeyAllower(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new KeyAllowListener(Inventory()), plugin);
    }
}
