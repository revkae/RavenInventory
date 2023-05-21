package me.raven.Features;

import de.tr7zw.nbtapi.NBTItem;
import me.raven.Interfaces.IRvNBT;
import me.raven.RvInventory;
import me.raven.abstracts.ARvManager;

import java.util.List;
import java.util.stream.Collectors;

public class RvNBT extends ARvManager implements IRvNBT {

    public RvNBT(RvInventory rvInventory) {
        init(rvInventory);
    }

    @Override
    public NBTItem getItemNBT(int row, int column) {
        return new NBTItem(Inventory().getItem(row, column));
    }

    @Override
    public NBTItem getItemNBT(int index) {
        return new NBTItem(Inventory().getItem(index));
    }

    @Override
    public List<NBTItem> getItemNBTs(int... indexes) {
        return Inventory().getItems(indexes)
                .stream()
                .map(NBTItem::new)
                .collect(Collectors.toList());
    }
}
