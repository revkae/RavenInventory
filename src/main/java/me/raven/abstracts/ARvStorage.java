package me.raven.abstracts;

import com.google.common.collect.Sets;
import me.raven.RvInventory;

import java.util.Set;

public abstract class ARvStorage {

    private RvInventory inventory;
    public Set<String> blockedKeys;
    public Set<String> allowedKeys;
    private int rowAmount;
    private int columnAmount;

    public void init(RvInventory inventory) {
        this.inventory = inventory;
        this.columnAmount = 9;
        this.rowAmount = inventory.getSize() / columnAmount;
        this.blockedKeys = Sets.newHashSet();
        this.allowedKeys = Sets.newHashSet();
    }

    public RvInventory Inventory() {
        return inventory;
    }

    public int RowAmount() {
        return rowAmount;
    }

    public int ColumnAmount() {
        return columnAmount;
    }
}
