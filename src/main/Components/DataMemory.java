package Components;

import java.util.ArrayList;

public class DataMemory {
    private static DataMemory instance;
    private ArrayList<CellAbstractModel> cells;

    public static DataMemory getInstance() {
        if (instance == null)
            instance = new DataMemory();
        return instance;
    }

    public DataMemory(){
        cells = new ArrayList<>();
    }

    public void setCells(ArrayList<CellAbstractModel> cells) {
        this.cells = cells;
    }

    public ArrayList<CellAbstractModel> getCells() {
        return cells;
    }
}
