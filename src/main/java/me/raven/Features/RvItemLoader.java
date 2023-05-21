package me.raven.Features;

import me.raven.Interfaces.IRvItemLoader;
import me.raven.RvInventory;
import me.raven.abstracts.ARvManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class RvItemLoader extends ARvManager implements IRvItemLoader {

    private ConfigurationSection section;
    private String name;
    private String material;
    private String lore;
    private String data;

    public RvItemLoader(RvInventory rvInventory, ConfigurationSection section, String name, String material, String lore, String data) {
        init(rvInventory);

        this.section = section;
        this.name = name;
        this.material = material;
        this.lore = lore;
        this.data = data;
    }

    @Override
    public ItemStack loadItem(boolean replace) {
        String name = section.getString(this.name);
        if (replace)
            name = section.getString(this.name).replace("&", "§");

        Material material = Material.getMaterial(section.getString(this.material));
        int data = section.getInt(this.data);
        String lore = section.getString(this.lore);
        if (replace)
            lore = section.getString(this.lore).replace("&", "§");

        List<String> loreList = section.getStringList(this.lore);
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
