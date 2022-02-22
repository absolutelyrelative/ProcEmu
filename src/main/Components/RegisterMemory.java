package Components;

import java.util.ArrayList;

@Deprecated
public class RegisterMemory {
    private static RegisterMemory instance;
    private ArrayList<CellAbstractModel> cells;

    public static RegisterMemory getInstance() {
        if (instance == null)
            instance = new RegisterMemory();
        return instance;
    }

    public RegisterMemory(){
        cells = new ArrayList<>();
    }

    public void setCells(ArrayList<CellAbstractModel> cells) {
        this.cells = cells;
    }

    public ArrayList<CellAbstractModel> getCells() {
        return cells;
    }
}
