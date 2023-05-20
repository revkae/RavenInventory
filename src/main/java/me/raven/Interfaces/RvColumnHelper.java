package me.raven.Interfaces;


import me.raven.RvColumn;
import me.raven.RvInventory;

import java.util.List;

public interface RvColumnHelper {

    RvInventory removeColumn(int index);
    RvInventory removeColumns(int... indexes);
    RvInventory setColumn(int index, RvColumn column);
    RvInventory setColumns(RvColumn column, int... indexes);
    RvColumn getColumn(int index);
    List<RvColumn> getColumns(int... indexes);
    boolean isColumnEmpty(int index);
    boolean isColumnsEmpty(int... indexes);
}
