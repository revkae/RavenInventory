package me.raven.Features;

import me.raven.Interfaces.IRvColumn;
import me.raven.Interfaces.IRvRow;
import me.raven.RvColumn;
import me.raven.RvInventory;
import me.raven.RvRow;
import me.raven.abstracts.ARvManager;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RvGrid extends ARvManager implements IRvColumn, IRvRow {

    public RvGrid(RvInventory inventory) {
        init(inventory);
    }

    @Override
    public RvInventory removeColumn(int index) {
        if (ColumnAmount() < index || index < 0) return Inventory();

        int startSlot = index;
        int endSlot = RowAmount() * ColumnAmount();
        int num = 0;
        for (int j = startSlot; j < endSlot; j+=9) {
            Inventory().setItem(j, new ItemStack(Material.AIR));
            num++;
        }
        return Inventory();
    }

    @Override
    public RvInventory removeColumns(int... indexes) {
        for (int index : indexes) {
            removeColumn(index);
        }
        return Inventory();
    }

    @Override
    public RvInventory setColumn(int index, RvColumn column) {
        if (ColumnAmount() < index || index < 0) return Inventory();

        int startSlot = index;
        int endSlot = RowAmount() * ColumnAmount();
        int num = 0;
        for (int j = startSlot; j < endSlot; j+=9) {
            Inventory().setItem(j, column.getItem(num));
            num++;
        }
        return Inventory();
    }

    @Override
    public RvInventory setColumns(RvColumn column, int... indexes) {
        for (int index : indexes) {
            setColumn(index, column);
        }
        return Inventory();
    }

    @Override
    public RvColumn getColumn(int index) {
        if (ColumnAmount() < index || index < 0) return null;
        RvColumn rvColumn = new RvColumn();
        int startSlot = index;
        int endSlot = RowAmount() * ColumnAmount();
        int num = 0;
        for (int j = startSlot; j < endSlot; j+=9) {
            rvColumn.setItem(num, Inventory().getItem(j));
            num++;
        }
        return rvColumn;
    }

    @Override
    public List<RvColumn> getColumns(int... indexes) {
        return Arrays.stream(indexes).mapToObj(this::getColumn).collect(Collectors.toList());
    }

    @Override
    public boolean isColumnEmpty(int index) {
        if (RowAmount() < index || index < 0) return false;

        int startSlot = index;
        int endSlot = RowAmount() * ColumnAmount();

        boolean found = false;
        for (int j = startSlot; j < endSlot; j++) {
            if (Inventory().getItem(j).getType() == Material.AIR) continue;

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
    public RvInventory removeRow(int index) {
        if (index > RowAmount() || index < 0) return Inventory();

        int startSlot = index * ColumnAmount();
        int endSlot = startSlot + ColumnAmount();

        int num = 0;
        for (int j = startSlot; j < endSlot; j++) {
            Inventory().setItem(j, new ItemStack(Material.AIR));
            num++;
        }
        return Inventory();
    }

    @Override
    public RvInventory removeRows(int... indexes) {
        for (int index : indexes) {
            removeRow(index);
        }
        return Inventory();
    }

    @Override
    public RvInventory setRow(int index, RvRow row) {
        if (index > RowAmount() || index < 0) return Inventory();

        int startSlot = index * ColumnAmount();
        int endSlot = startSlot + ColumnAmount();

        int num = 0;
        for (int j = startSlot; j < endSlot; j++) {
            Inventory().setItem(j, row.getItem(num));
            num++;
        }
        return Inventory();
    }

    @Override
    public RvInventory setRows(RvRow row, int... indexes) {
        for (int index : indexes) {
            setRow(index, row);
        }
        return Inventory();
    }

    @Override
    public RvRow getRow(int index) {
        if (RowAmount() < index || index < 0) return null;

        RvRow rvRow = new RvRow();
        int startSlot = index * ColumnAmount();
        int endSlot = startSlot + ColumnAmount();
        int num = 0;
        for (int j = startSlot; j < endSlot; j++) {
            rvRow.setItem(num, Inventory().getItem(j));
            num++;
        }
        return rvRow;
    }

    @Override
    public List<RvRow> getRows(int... indexes) {
        return Arrays.stream(indexes).mapToObj(this::getRow).collect(Collectors.toList());
    }

    @Override
    public boolean isRowEmpty(int index) {
        if (RowAmount() < index || index < 0) return false;

        int startSlot = index * ColumnAmount();
        int endSlot = startSlot + ColumnAmount();

        boolean found = false;
        for (int j = startSlot; j < endSlot; j++) {
            if (Inventory().getItem(j).getType() == Material.AIR) continue;

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
}
