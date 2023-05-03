package me.raven;

public interface RvLayout {

    default void init(RvInventory rvInventory) {
        setup(rvInventory);
        setupRows(rvInventory);
        setupColumns(rvInventory);
        setItems(rvInventory);
    }

    void setup(RvInventory rvInventory);
    void setupRows(RvInventory rvInventory);
    void setupColumns(RvInventory rvInventory);
    void setItems(RvInventory rvInventory);
}