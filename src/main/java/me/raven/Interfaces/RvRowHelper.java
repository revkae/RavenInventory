package me.raven.Interfaces;



import me.raven.RvInventory;
import me.raven.RvRow;

import java.util.List;

public interface RvRowHelper {

    RvInventory removeRow(int index);

    RvInventory removeRows(int... indexes);
    RvInventory setRow(int index, RvRow row);

    RvInventory setRows(RvRow row, int... indexes);

    RvRow getRow(int index);

    List<RvRow> getRows(int... indexes);

    boolean isRowEmpty(int index);
    boolean isRowsEmpty(int... indexes);
}
