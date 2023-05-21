package me.raven.Interfaces;

import de.tr7zw.nbtapi.NBTItem;

import java.util.List;

public interface IRvNBT {

    NBTItem getItemNBT(int row, int column);

    NBTItem getItemNBT(int index);

    List<NBTItem> getItemNBTs(int... indexes);
}
