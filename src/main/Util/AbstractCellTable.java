package Util;

import Components.CellAbstractModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AbstractCellTable extends AbstractTableModel {
    ArrayList<CellAbstractModel> data;
    String colNames[] = {"Address", "Data", "Instruction"};
    Class<?> colClasses[] = {Long.class, Long.class, String.class};
    int currentcell = 0;

    public AbstractCellTable(ArrayList<CellAbstractModel> data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public ArrayList<CellAbstractModel> getdata() {
        return data;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            if(data.size() > rowIndex)
                return data.get(rowIndex).getAddress();
            else
                return null;
        }
        if (columnIndex == 1) {
            if(data.get(rowIndex) != null)
                return data.get(rowIndex).getData();
            else
                return null;
        }
        if (columnIndex == 2) {
            if(data.get(rowIndex) != null)
                return data.get(rowIndex).getInstruction();
            else
                return null;
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

    public void addelement(CellAbstractModel value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void clearelements() {
        data.clear();
    }

    //TODO: This needs to be reworked to include the actual executor logic
    public CellAbstractModel getnext() {
        if (!data.isEmpty()) {
            if (currentcell < data.size()) {
                currentcell++;
            } else {
                currentcell = 0;
            }
            return data.get(currentcell);
        } else {
            return null;
        }
    }


}
