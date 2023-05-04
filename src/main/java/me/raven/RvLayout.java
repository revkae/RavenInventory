package me.raven;

public interface RvLayout {

    default void init(RvInventory rvInventory) {
        if (!title().isEmpty())
            rvInventory.setTitle(title());

        setup(rvInventory);
    }

    String title();
    void setup(RvInventory rvInventory);
}