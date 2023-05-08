package me.raven.Layouts;

import de.tr7zw.nbtapi.NBTItem;
import me.raven.RvInventory;
import me.raven.RvLayout;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RvPageLayout implements RvLayout {

    @Override
    public String title() {
        return "Page";
    }

    @Override
    public void setup(RvInventory rvInventory) {
        NBTItem next = new NBTItem(new ItemStack(Material.ARROW), true);
        next.setString("NEXT", "nothing");

        NBTItem previous = new NBTItem(new ItemStack(Material.ARROW), true);
        previous.setString("previous", "nothing");

        rvInventory.setItem(rvInventory.getRowAmount() - 1, rvInventory.getColumnAmount() - 1, next.getItem());
        rvInventory.setItem(0, rvInventory.getColumnAmount() - 1, next.getItem());
    }
}
