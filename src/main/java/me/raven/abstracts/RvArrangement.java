package me.raven.abstracts;

import me.raven.RvSlot;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RvArrangement {

    public List<ItemStack> arrangement;

    public RvArrangement() {
        arrangement = new ArrayList<>();
    }

    public RvArrangement(ItemStack... items) {
        List<ItemStack> itemStacks = new ArrayList<>(Arrays.asList(items));
        while (itemStacks.size() != 9) {
            itemStacks.add(new ItemStack(Material.AIR));
        }

        arrangement = itemStacks;
    }

    public RvArrangement(RvSlot... items) {
        arrangement = new ArrayList<>(Collections.nCopies(9, new ItemStack(Material.AIR)));

        for (RvSlot item : items) {
            arrangement.set(item.getSlot(), item.getItemStack());
        }
    }

    public void setItem(int index, ItemStack item) {
        arrangement.set(index, item);
    }

    public void setItem(RvSlot item) {
        arrangement.set(item.getSlot(), item.getItemStack());
    }

    public void setItem(ItemStack items, int... indexes) {
        for (int index : indexes) {
            arrangement.set(index, items);
        }
    }

    public void setItems(RvSlot... items) {
        for (RvSlot item : items) {
            arrangement.set(item.getSlot(), item.getItemStack());
        }
    }

    public ItemStack getItem(int index) {
        return arrangement.get(index);
    }

    public RvSlot getItemSlot(int index) {
        return new RvSlot(index, arrangement.get(index));
    }

    public List<ItemStack> getItems(int... indexes) {
        return Arrays.stream(indexes)
                .mapToObj(arrangement::get)
                .collect(Collectors.toList());
    }

    public List<RvSlot> getItemSlots(int... indexes) {
        return Arrays.stream(indexes)
                .mapToObj(index -> new RvSlot(index, arrangement.get(index)))
                .collect(Collectors.toList());
    }

    public boolean containsItem(ItemStack item) {
        return arrangement.contains(item);
    }

    public boolean isEmpty(int index) {
        return arrangement.get(index).getType() == Material.AIR;
    }

    public boolean isWholeEmpty() {
        List<ItemStack> items = arrangement.stream()
                .filter(item -> item.getType() != Material.AIR)
                .collect(Collectors.toList());
        return items.isEmpty();
    }

    public boolean isFull(int index) {
        return arrangement.get(index).getType() != Material.AIR;
    }

    public boolean isWholeFull() {
        List<ItemStack> items = arrangement.stream()
                .filter(item -> item.getType() == Material.AIR)
                .collect(Collectors.toList());
        return items.isEmpty();
    }

    public void fillOut(ItemStack item) {
        arrangement.replaceAll(ignored -> item);
    }
}
