package Util;

import Components.CellAbstractModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AbstractCellTable extends AbstractTableModel {
    ArrayList<CellAbstractModel> data;
    String colNames[] = { "Address", "Data", "Instruction" };
    Class<?> colClasses[] = { Long.class, Long.class, String.class };

    public AbstractCellTable(ArrayList<CellAbstractModel> data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public ArrayList<CellAbstractModel> getdata(){
        return data;
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

    public void addelement(CellAbstractModel value){
        data.add(value);
        fireTableRowsInserted(data.size()-1, data.size()-1);
    }

    public void clearelements(){
        data.clear();
    }


}
