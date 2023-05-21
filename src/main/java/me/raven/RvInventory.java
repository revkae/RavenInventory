package me.raven;

import de.tr7zw.nbtapi.NBTItem;
import me.raven.Features.*;
import me.raven.Interfaces.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RvInventory implements
        RvListenerHelper,
        RvLayoutMethods,
        RvSetItem,
        RvGetItem,
        RvGetSlot,
        Cloneable
{

    private Inventory inventory;
    private RvGrid grid = new RvGrid(this);
    private RvListener listener = new RvListener(this);
    private RvKey key = new RvKey(this);
    private RvNBT nbt = new RvNBT(this);
    private RvExtra extra = new RvExtra(this);

    public RvInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public RvInventory(InventoryHolder holder, InventoryType type) {
        this.inventory = Bukkit.createInventory(holder, type);
    }

    public RvInventory(InventoryHolder holder, InventoryType type, String title) {
        this.inventory = Bukkit.createInventory(holder, type, title);
    }

    public RvInventory(InventoryHolder holder, int size) {
        this.inventory = Bukkit.createInventory(holder, size);
    }

    public RvInventory(InventoryHolder holder, int size, String title) {
        this.inventory = Bukkit.createInventory(holder, size, title);
    }

    public RvInventory setInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public RvGrid getGrid() {
        return grid;
    }

    public RvListener getListener() {
        return listener;
    }

    public RvKey getKey() {
        return key;
    }

    public RvNBT getNBT() {
        return nbt;
    }

    public RvExtra getExtra() {
        return extra;
    }

    public RvItemLoader getItemLoader(ConfigurationSection section,
                                      String name,
                                      String material,
                                      String data,
                                      String lore) {
        return new RvItemLoader(this, section, name, material, lore, data);
    }

    @Override
    public boolean isInventoryNull() {
        return inventory == null;
    }

    @Override
    public boolean isSameInventory(Inventory inventory) {
        return this.inventory.equals(inventory);
    }

    @Override
    public boolean isItemNull(ItemStack itemStack) {
        return !(itemStack == null || itemStack.getType() == Material.AIR);
    }

    @Override
    public RvInventory setItem(ItemStack itemStack, int... indexes) {
        for (int index : indexes) {
            setItem(index, itemStack);
        }
        return this;
    }

    @Override
    public RvInventory setItem(int row, int column, ItemStack itemStack) {
        setItem((row * 9) + column, itemStack);
        return this;
    }

    @Override
    public ItemStack getItem(int row, int column) {
        return getItem((row * 9) + column);
    }

    @Override
    public List<ItemStack> getItems(int... indexes) {
        return Arrays.stream(indexes).mapToObj(this::getItem).collect(Collectors.toList());
    }

    @Override
    public int getSlot(ItemStack itemStack) {
        return IntStream.range(0, inventory.getSize())
                .filter(i -> inventory.getItem(i) != null && inventory.getItem(i).isSimilar(itemStack))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public int getSlot(String key) {
        return IntStream.range(0, inventory.getSize())
                .filter(i -> inventory.getItem(i) != null && inventory.getItem(i).getType() != Material.AIR)
                .filter(i -> new NBTItem(inventory.getItem(i)).hasKey(key))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public RvInventory setLayout(RvLayout layout) {
        inventory.clear();
        layout.Init(this);
        return this;
    }

    @Override
    public RvInventory setLayouts(RvLayout... layouts) {
        for (RvLayout layout : layouts) {
            layout.Init(this);
        }
        return this;
    }

    public RvInventory clone() {
        RvInventory clone;
        try {
            clone = (RvInventory) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    public Inventory build() {
        return inventory;
    }

    // DEFAULT INVENTORY FEATURES

    public int getSize() {
        return inventory.getSize();
    }

    public int getMaxStackSize() {
        return inventory.getMaxStackSize();
    }

    public void setMaxStackSize(int size) {
        inventory.setMaxStackSize(size);
    }

    public String getName() {
        return inventory.getName();
    }

    public ItemStack getItem(int index) {
        return inventory.getItem(index);
    }

    public void setItem(int index, ItemStack item) {
        inventory.setItem(index, item);
    }

    public RvInventory addItems(List<ItemStack> itemStacks) {
        inventory.addItem((ItemStack[]) itemStacks.toArray());
        return this;
    }

    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        return inventory.addItem(items);
    }

    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        return inventory.removeItem(items);
    }

    public ItemStack[] getContents() {
        return inventory.getContents();
    }

    public List<ItemStack> getContentList() {
        return Arrays.asList(inventory.getContents());
    }

    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        inventory.setContents(items);
    }

    public boolean contains(int materialId) {
        return inventory.contains(materialId);
    }

    public boolean contains(Material material) throws IllegalArgumentException {
        return inventory.contains(material);
    }

    public boolean contains(ItemStack item) {
        return false;
    }

    public boolean contains(int materialId, int amount) {
        return inventory.contains(materialId, amount);
    }

    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        return inventory.contains(material, amount);
    }

    public boolean contains(ItemStack item, int amount) {
        return inventory.contains(item, amount);
    }

    public boolean containsAtLeast(ItemStack item, int amount) {
        return inventory.containsAtLeast(item, amount);
    }

    public HashMap<Integer, ? extends ItemStack> all(int materialId) {
        return inventory.all(materialId);
    }

    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return inventory.all(material);
    }

    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        return inventory.all(item);
    }

    public int first(int materialId) {
        return inventory.first(materialId);
    }

    public int first(Material material) throws IllegalArgumentException {
        return inventory.first(material);
    }

    public int first(ItemStack item) {
        return inventory.first(item);
    }

    public int firstEmpty() {
        return inventory.firstEmpty();
    }

    public void remove(int materialId) {
        inventory.remove(materialId);
    }

    public void remove(Material material) throws IllegalArgumentException {
        inventory.remove(material);
    }

    public void remove(ItemStack item) {
        inventory.remove(item);
    }

    public void clear(int index) {
        inventory.clear(index);
    }

    public void clear() {
        inventory.clear();
    }

    public List<HumanEntity> getViewers() {
        return inventory.getViewers();
    }

    public String getTitle() {
        return inventory.getTitle();
    }

    public InventoryType getType() {
        return inventory.getType();
    }

    public InventoryHolder getHolder() {
        return inventory.getHolder();
    }

    public ListIterator<ItemStack> iterator() {
        return inventory.iterator();
    }

    public ListIterator<ItemStack> iterator(int index) {
        return inventory.iterator(index);
    }

}
