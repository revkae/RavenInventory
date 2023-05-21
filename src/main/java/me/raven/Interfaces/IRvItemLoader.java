package me.raven.Interfaces;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public interface IRvItemLoader {

    ItemStack loadItem(boolean replace);
}
