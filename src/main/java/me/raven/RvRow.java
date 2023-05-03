package me.raven;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RvRow {

    private List<ItemStack> row;

    public RvRow() {
        row = new ArrayList<>(Collections.nCopies(9, new ItemStack(Material.AIR)));
    }

    public RvRow(ItemStack... items) {
        List<ItemStack> itemStacks = new ArrayList<>(Arrays.asList(items));
        while (itemStacks.size() != 9) {
            itemStacks.add(new ItemStack(Material.AIR));
        }

        row = itemStacks;
    }

    @SafeVarargs
    public RvRow(Pair<Integer, ItemStack>... items) {
        row = new ArrayList<>(Collections.nCopies(9, new ItemStack(Material.AIR)));

        for (Pair<Integer, ItemStack> item : items) {
            row.set(item.getValue0(), item.getValue1());
        }
    }
    public RvRow setItem(int index, ItemStack itemStack) {
        row.set(index, itemStack);
        return this;
    }

    public RvRow setItem(ItemStack itemStack, int... indexes) {
        for (int index : indexes) {
            row.set(index, itemStack);
        }
        return this;
    }

    public RvRow setItems(Pair<Integer, ItemStack>... items) {
        for (Pair<Integer, ItemStack> item : items) {
            row.set(item.getValue0(), item.getValue1());
        }
        return this;
    }

    public ItemStack getItem(int index) {
        return row.get(index);
    }
    public List<ItemStack> getItems() {
        return row;
    }

    public List<ItemStack> getItems(int... index) {
        List<ItemStack> items = Arrays.stream(index)
                .mapToObj(row::get)
                .collect(Collectors.toList());
        return items;
    }

    public boolean contains(ItemStack itemStack) {
        return row.contains(itemStack);
    }

    public boolean isEmpty(int index) {
        return row.get(index).getType() == Material.AIR;
    }

    public boolean isEmpty() {
        List<ItemStack> items = row.stream()
                .filter(item -> item.getType() != Material.AIR)
                .collect(Collectors.toList());
        return items.isEmpty();
    }

    public boolean isFull(int index) {
        return row.get(index).getType() != Material.AIR;
    }

    public boolean isFull() {
        List<ItemStack> items = row.stream()
                .filter(item -> item.getType() == Material.AIR)
                .collect(Collectors.toList());
        return items.isEmpty();
    }

    public RvRow fillOut(ItemStack itemStack) {
        Collections.fill(row, itemStack);
        return this;
    }

    public RvRow setRow(RvRow newRow) {
        this.row = newRow.getItems();
        return this;
    }

    public RvRow setRow(List<ItemStack> newRow) {
        this.row = newRow;
        return this;
    }

    public static RvRow with(Pair<Integer, ItemStack>... items) {
        return new RvRow(items);
    }

    public static RvRow with(ItemStack... items) {
        return new RvRow(items);
    }
}
