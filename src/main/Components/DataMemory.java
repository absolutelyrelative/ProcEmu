package Components;

import Util.AbstractCellTable;

import java.util.ArrayList;

@Deprecated
public class DataMemory {
    private static DataMemory instance;
    private ArrayList<CellAbstractModel> cells;
    private AbstractCellTable tablemodel;

    public static DataMemory getInstance() {
        if (instance == null)
            instance = new DataMemory();
        return instance;
    }

    public DataMemory() {
        cells = new ArrayList<>();
    }

    public void setCells(ArrayList<CellAbstractModel> cells) {
        this.cells = cells;
    }

    public ArrayList<CellAbstractModel> getCells() {
        return cells;
    }

    public AbstractCellTable getTablemodel() {
        return tablemodel;
    }

    public void setTablemodel(AbstractCellTable tablemodel) {
        this.tablemodel = tablemodel;
    }
}
