package me.raven;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RvColumn {

    private List<ItemStack> column;

    public RvColumn() {
        column = new ArrayList<>(Collections.nCopies(9, new ItemStack(Material.AIR)));
    }

    public RvColumn(ItemStack... items) {
        List<ItemStack> itemStacks = new ArrayList<>(Arrays.asList(items));
        while (itemStacks.size() != 9) {
            itemStacks.add(new ItemStack(Material.AIR));
        }

        column = itemStacks;
    }

    @SafeVarargs
    public RvColumn(Pair<Integer, ItemStack>... items) {
        column = new ArrayList<>(Collections.nCopies(9, new ItemStack(Material.AIR)));

        for (Pair<Integer, ItemStack> item : items) {
            column.set(item.getValue0(), item.getValue1());
        }
    }

    public RvColumn setItem(int index, ItemStack itemStack) {
        column.set(index, itemStack);
        return this;
    }

    public RvColumn setItem(ItemStack itemStack, int... indexes) {
        for (int index : indexes) {
            column.set(index, itemStack);
        }
        return this;
    }

    public RvColumn setItems(Pair<Integer, ItemStack>... items) {
        for (Pair<Integer, ItemStack> item : items) {
            column.set(item.getValue0(), item.getValue1());
        }
        return this;
    }

    public ItemStack getItem(int index) {
        return column.get(index);
    }

    public List<ItemStack> getItems() {
        return column;
    }

    public List<ItemStack> getItems(int... index) {
        List<ItemStack> items = Arrays.stream(index)
                .mapToObj(column::get)
                .collect(Collectors.toList());
        return items;
    }

    public boolean contains(ItemStack itemStack) {
        return column.contains(itemStack);
    }

    public boolean isEmpty(int index) {
        return column.get(index).getType() == Material.AIR;
    }

    public boolean isEmpty() {
        List<ItemStack> items = column.stream()
                .filter(item -> item.getType() != Material.AIR)
                .collect(Collectors.toList());
        return items.isEmpty();
    }

    public boolean isFull(int index) {
        return column.get(index).getType() != Material.AIR;
    }

    public boolean isFull() {
        List<ItemStack> items = column.stream()
                .filter(item -> item.getType() == Material.AIR)
                .collect(Collectors.toList());
        return items.isEmpty();
    }

    public RvColumn fillOut(ItemStack itemStack) {
        Collections.fill(column, itemStack);
        return this;
    }

    public RvColumn setRow(RvColumn newRow) {
        this.column = newRow.getItems();
        return this;
    }

    public RvColumn setRow(List<ItemStack> newRow) {
        this.column = newRow;
        return this;
    }

    public static RvColumn with(ItemStack... items) {
        return new RvColumn(items);
    }

    public static RvColumn with(Pair<Integer, ItemStack>... items) {
        return new RvColumn(items);
    }
}
