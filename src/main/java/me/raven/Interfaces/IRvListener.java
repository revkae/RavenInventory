package me.raven.Interfaces;

import org.bukkit.plugin.java.JavaPlugin;

public interface IRvListener {

    void registerAll(JavaPlugin plugin);

    void registerKeyBlocker(JavaPlugin plugin);

    void registerKeyAllower(JavaPlugin plugin);
}
