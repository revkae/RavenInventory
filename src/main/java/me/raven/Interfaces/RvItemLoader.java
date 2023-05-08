package me.raven.Interfaces;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public interface RvItemLoader {

    default ItemStack loadItem(ConfigurationSection section, String path, boolean replace) {
        String name = section.getString(path + ".name");
        if (replace)
            name = section.getString(path + ".name").replace("&", "§");

        Material material = Material.getMaterial(section.getString(path + ".material"));
        int data = section.getInt(path + ".data");
        String lore = section.getString(path + ".lore");
        if (replace)
            lore = section.getString(path + ".lore").replace("&", "§");

        List<String> loreList = section.getStringList(path + ".lore");
        if (loreList != null && replace) {
            loreList.replaceAll(s -> s.replace("&", "§"));
        }

        ItemStack output = new ItemStack(material);
        if (data != 0)
            output = new ItemStack(material, 1, (short) data);

        ItemMeta outputMeta = output.getItemMeta();
        if (loreList != null) {
            outputMeta.setDisplayName(name);
            outputMeta.setLore(loreList);
            output.setItemMeta(outputMeta);
            return output;
        }

        outputMeta.setDisplayName(name);
        outputMeta.setLore(Collections.singletonList(lore));
        output.setItemMeta(outputMeta);

        return output;
    }
}
