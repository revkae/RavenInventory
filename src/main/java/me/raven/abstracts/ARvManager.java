package me.raven.abstracts;

import me.raven.RvInventory;

public abstract class ARvManager {

    private RvInventory inventory;
    private int rowAmount;
    private int columnAmount;

    public void init(RvInventory inventory) {
        this.inventory = inventory;
        this.columnAmount = 9;
        this.rowAmount = inventory.getSize() / columnAmount;
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
