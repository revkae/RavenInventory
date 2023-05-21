package me.raven.Features;

import me.raven.Interfaces.IRvExtra;
import me.raven.RvInventory;
import me.raven.abstracts.ARvManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class RvExtra extends ARvManager implements IRvExtra {

    public RvExtra(RvInventory rvInventory) {
        init(rvInventory);
    }

    @Override
    public RvInventory setTitle(String title) {
        Inventory newInventory = Bukkit.createInventory(Inventory().getHolder(), Inventory().getSize(), title);
        newInventory.setContents(Inventory().getContents());
        Inventory().setInventory(newInventory);
        return Inventory();
    }

    @Override
    public RvInventory fillOut(ItemStack itemStack) {
        for (int i = 0; i < Inventory().getSize(); i++) {
            Inventory().setItem(i, itemStack);
        }
        return Inventory();
    }

    @Override
    public RvInventory fillOut(ItemStack itemStack, int... indexes) {
        for (int index : indexes) {
            Inventory().setItem(index, itemStack);
        }
        return Inventory();
    }

    @Override
    public RvInventory fillEmptySlots(ItemStack itemStack) {
        for (int i = 0; i < Inventory().getContents().length; i++) {
            if (Inventory().getContents()[i] == null || Inventory().getContents()[i].getType() == Material.AIR) {
                Inventory().setItem(i, itemStack);
            }
        }
        return Inventory();
    }

    @Override
    public boolean isEqual(Inventory inventory) {
        return Inventory().getInventory().equals(inventory);
    }

    @Override
    public boolean isEmpty() {
        return Arrays.stream(Inventory().getContents())
                .noneMatch(itemStack -> itemStack.getType() != Material.AIR);
    }

    @Override
    public boolean isSlotEmpty(int slot) {
        return Inventory().getItem(slot).getType() == Material.AIR;
    }

    @Override
    public boolean isFull() {
        return Arrays.stream(Inventory().getContents())
                .noneMatch(itemStack -> itemStack.getType() == Material.AIR);
    }

    @Override
    public boolean isSlotFull(int slot) {
        return Inventory().getItem(slot).getType() != Material.AIR;
    }

    @Override
    public void open(Player player) {
        player.openInventory(Inventory().getInventory());
    }

    @Override
    public void close(Player player) {
        player.closeInventory();
    }

    @Override
    public void update(Player player) {
        player.updateInventory();
    }
}
