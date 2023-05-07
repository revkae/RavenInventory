package me.raven;

import de.tr7zw.nbtapi.NBTItem;
import me.raven.Interfaces.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
        RvItemBlocker,
        RvKeyBlocker,
        RvItemAllower,
        RvKeyAllower,
        RvListenerRegisterer,
        RvListenerHelper,
        RvRowHelper,
        RvColumnHelper,
        RvUsefullFeatures,
        RvLayoutMethods,
        RvSetItem,
        RvGetItem,
        RvGetSlot,
        RvItemNBT
{

    private Inventory inventory;
    private final int rowAmount;
    private final int columnAmount = 9;
    private final List<ItemStack> blockedItems = new ArrayList<>();
    private final List<String> blockedKeys = new ArrayList<>();

    private final List<ItemStack> allowedItems = new ArrayList<>();
    private final List<String> allowedKeys = new ArrayList<>();

    private final Map<ItemStack, Inventory> inventoryPerItem = new HashMap<>();

    public RvInventory(Inventory inventory) {
        this.inventory = inventory;
        this.rowAmount = inventory.getSize() / columnAmount;
    }

    public RvInventory(InventoryHolder holder, InventoryType type) {
        this.inventory = Bukkit.createInventory(holder, type);
        this.rowAmount = inventory.getSize() / columnAmount;
    }

    public RvInventory(InventoryHolder holder, InventoryType type, String title) {
        this.inventory = Bukkit.createInventory(holder, type, title);
        this.rowAmount = inventory.getSize() / columnAmount;
    }

    public RvInventory(InventoryHolder holder, int size) {
        this.inventory = Bukkit.createInventory(holder, size);
        this.rowAmount = inventory.getSize() / columnAmount;
    }

    public RvInventory(InventoryHolder holder, int size, String title) {
        this.inventory = Bukkit.createInventory(holder, size, title);
        this.rowAmount = inventory.getSize() / columnAmount;
    }

    @Override
    public void addBlockedItem(ItemStack itemStack) {
        if (blockedItems.contains(itemStack)) return;

        blockedItems.add(itemStack);
    }

    @Override
    public void removeBlockedItem(ItemStack itemStack) {
        if (!blockedItems.contains(itemStack)) return;

        blockedItems.remove(itemStack);
    }

    @Override
    public void addBlockedKey(String key) {
        if (blockedKeys.contains(key)) return;

        blockedKeys.add(key);
    }

    @Override
    public void removeBlockedKey(String key) {
        if (!blockedKeys.contains(key)) return;

        blockedKeys.remove(key);
    }

    @Override
    public boolean hasBlockedKey(ItemStack itemStack) {
        return new NBTItem(itemStack).getKeys().stream().anyMatch(blockedKeys::contains);
    }

    @Override
    public boolean hasBlockedItem(ItemStack itemStack) {
        return blockedItems.contains(itemStack);
    }

    @Override
    public void addAllowedItem(ItemStack itemStack) {
        if (allowedItems.contains(itemStack)) return;

        allowedItems.add(itemStack);
    }

    @Override
    public void removeAllowedItem(ItemStack itemStack) {
        if (!allowedItems.contains(itemStack)) return;

        allowedItems.remove(itemStack);
    }

    @Override
    public boolean hasAllowedItem(ItemStack itemStack) {
        return allowedItems.contains(itemStack);
    }

    @Override
    public void addAllowedKey(String key) {
        if (allowedKeys.contains(key)) return;

        allowedKeys.add(key);
    }

    @Override
    public void removeAllowedKey(String key) {
        if (!allowedKeys.contains(key)) return;

        allowedKeys.remove(key);
    }

    @Override
    public boolean hasAllowedKey(ItemStack itemStack) {
        return new NBTItem(itemStack).getKeys().stream().anyMatch(allowedKeys::contains);
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
    public RvInventory removeRow(int index) {
        if (index > rowAmount || index < 0) return this;

        int startSlot = index * columnAmount;
        int endSlot = startSlot + columnAmount;

        int num = 0;
        for (int j = startSlot; j < endSlot; j++) {
            inventory.setItem(j, new ItemStack(Material.AIR));
            num++;
        }
        return this;
    }

    @Override
    public RvInventory removeRows(int... indexes) {
        for (int index : indexes) {
            removeRow(index);
        }
        return this;
    }

    @Override
    public RvInventory setRow(int index, RvRow row) {
        if (index > rowAmount || index < 0) return this;

        int startSlot = index * columnAmount;
        int endSlot = startSlot + columnAmount;

        int num = 0;
        for (int j = startSlot; j < endSlot; j++) {
            inventory.setItem(j, row.getItem(num));
            num++;
        }
        return this;
    }

    @Override
    public RvInventory setRows(RvRow row, int... indexes) {
        for (int index : indexes) {
            setRow(index, row);
        }
        return this;
    }

    @Override
    public RvInventory removeColumn(int index) {
        if (columnAmount < index || index < 0) return this;

        int startSlot = index;
        int endSlot = rowAmount * columnAmount;
        int num = 0;
        for (int j = startSlot; j < endSlot; j+=9) {
            inventory.setItem(j, new ItemStack(Material.AIR));
            num++;
        }
        return this;
    }

    @Override
    public RvInventory removeColumns(int... indexes) {
        for (int index : indexes) {
            removeColumn(index);
        }
        return this;
    }

    @Override
    public RvInventory setColumn(int index, RvColumn column) {
        if (columnAmount < index || index < 0) return this;

        int startSlot = index;
        int endSlot = rowAmount * columnAmount;
        int num = 0;
        for (int j = startSlot; j < endSlot; j+=9) {
            inventory.setItem(j, column.getItem(num));
            num++;
        }
        return this;
    }

    @Override
    public RvInventory setColumns(RvColumn column, int... indexes) {
        for (int index : indexes) {
            setColumn(index, column);
        }
        return this;
    }

    @Override
    public RvRow getRow(int index) {
        if (rowAmount < index || index < 0) return null;
        RvRow rvRow = new RvRow();
        int startSlot = index * columnAmount;
        int endSlot = startSlot + columnAmount;
        int num = 0;
        for (int j = startSlot; j < endSlot; j++) {
            rvRow.setItem(num, inventory.getItem(j));
            num++;
        }
        return rvRow;
    }

    @Override
    public List<RvRow> getRows(int... indexes) {
        return Arrays.stream(indexes).mapToObj(this::getRow).collect(Collectors.toList());
    }

    @Override
    public RvColumn getColumn(int index) {
        if (columnAmount < index || index < 0) return null;
        RvColumn rvColumn = new RvColumn();
        int startSlot = index;
        int endSlot = rowAmount * columnAmount;
        int num = 0;
        for (int j = startSlot; j < endSlot; j+=9) {
            rvColumn.setItem(num, inventory.getItem(j));
            num++;
        }
        return rvColumn;
    }

    @Override
    public List<RvColumn> getColumns(int... indexes) {
        return Arrays.stream(indexes).mapToObj(this::getColumn).collect(Collectors.toList());
    }

    @Override
    public boolean isRowEmpty(int index) {
        if (rowAmount < index || index < 0) return false;

        int startSlot = index * columnAmount;
        int endSlot = startSlot + columnAmount;

        boolean found = false;
        for (int j = startSlot; j < endSlot; j++) {
            if (inventory.getItem(j).getType() == Material.AIR) continue;

            found = true;
        }

        return !found;
    }

    @Override
    public boolean isRowsEmpty(int... indexes) {
        for (int index : indexes) {
            if (!isRowEmpty(index)) return false;
        }
        return true;
    }

    @Override
    public boolean isColumnEmpty(int index) {
        if (rowAmount < index || index < 0) return false;

        int startSlot = index;
        int endSlot = rowAmount * columnAmount;

        boolean found = false;
        for (int j = startSlot; j < endSlot; j++) {
            if (inventory.getItem(j).getType() == Material.AIR) continue;

            found = true;
        }

        return !found;
    }

    @Override
    public boolean isColumnsEmpty(int... indexes) {
        for (int index : indexes) {
            if (!isColumnEmpty(index)) return false;
        }
        return true;
    }


    @Override
    public RvInventory setTitle(String title) {
        Inventory newInventory = Bukkit.createInventory(inventory.getHolder(), inventory.getSize(), title);
        newInventory.setContents(newInventory.getContents());
        inventory = newInventory;
        return this;
    }

    @Override
    public RvInventory fillOut(ItemStack itemStack) {
        List<ItemStack> content = new ArrayList<>(Arrays.asList(getContents()));
        Collections.fill(content, itemStack);
        setContents((ItemStack[]) content.toArray());
        return this;
    }

    @Override
    public RvInventory fillOut(ItemStack itemStack, int... indexes) {
        List<ItemStack> content = new ArrayList<>(Arrays.asList(getContents()));
        Collections.fill(content, itemStack);
        setContents((ItemStack[]) content.toArray());
        for (int index : indexes) {
            setItem(index, new ItemStack(Material.AIR));
        }
        return this;
    }

    @Override
    public RvInventory fillEmptySlots(ItemStack itemStack) {
        for (int i = 0; i < getContents().length; i++) {
            if (getContents()[i] == null || getContents()[i].getType() == Material.AIR) {
                setItem(i, itemStack);
            }
        }
        return this;
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
    public NBTItem getItemNBT(int row, int column) {
        return new NBTItem(getItem(row, column));
    }

    @Override
    public NBTItem getItemNBT(int index) {
        return new NBTItem(getItem(index));
    }

    @Override
    public List<NBTItem> getItemNBTs(int... indexes) {
        return Arrays.stream(indexes).mapToObj(this::getItemNBT).collect(Collectors.toList());
    }

    @Override
    public int getSlot(ItemStack itemStack) {
        return IntStream.range(0, inventory.getSize())
                .filter(i -> inventory.getItem(i) != null && inventory.getItem(i).isSimilar(itemStack))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public RvInventory setLayout(RvLayout layout) {
        inventory.clear();
        layout.init(this);
        return this;
    }

    @Override
    public RvInventory setLayouts(RvLayout... layouts) {
        for (RvLayout layout : layouts) {
            layout.init(this);
        }
        return this;
    }

    @Override
    public boolean isEqual(Inventory inventory) {
        return this.inventory.equals(inventory);
    }

    @Override
    public boolean isEmpty() {
        return Arrays.stream(getContents())
                .noneMatch(itemStack -> itemStack.getType() != Material.AIR);
    }

    @Override
    public boolean isSlotEmpty(int slot) {
        return inventory.getItem(slot).getType() == Material.AIR;
    }

    @Override
    public boolean isFull() {
        return Arrays.stream(getContents())
                .noneMatch(itemStack -> itemStack.getType() == Material.AIR);
    }

    @Override
    public boolean isSlotFull(int slot) {
        return inventory.getItem(slot).getType() != Material.AIR;
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public void close(Player player) {
        player.closeInventory();
    }

    @Override
    public void update(Player player) {
        player.updateInventory();
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
