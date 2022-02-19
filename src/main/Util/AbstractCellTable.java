package Util;

import Components.CellAbstractModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AbstractCellTable extends AbstractTableModel {
    List<CellAbstractModel> data;
    String colNames[] = { "Address", "Data", "Instruction" };
    Class<?> colClasses[] = { Long.class, Long.class, String.class };

    public AbstractCellTable(ArrayList<CellAbstractModel> data) {
        this.data = data;

        /*data.add(new CellAbstractModel(1,1,"no"));
        data.add(new CellAbstractModel(1,2,"no"));
        data.add(new CellAbstractModel(1,3,"no"));*/
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return data.get(rowIndex).getAddress();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getData();
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getInstruction();
        }
        return null;
    }

    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            data.get(rowIndex).setAddress((Long) aValue);
        }
        if (columnIndex == 1) {
            data.get(rowIndex).setData((Long) aValue);
        }
        if (columnIndex == 2) {
            data.get(rowIndex).setInstruction((String) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
