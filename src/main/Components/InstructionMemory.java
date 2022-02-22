package Components;

import java.util.ArrayList;

@Deprecated
public class InstructionMemory {
    private static InstructionMemory instance;
    private ArrayList<CellAbstractModel> cells;

    public static InstructionMemory getInstance() {
        if (instance == null)
            instance = new InstructionMemory();
        return instance;
    }

    public InstructionMemory(){
        cells = new ArrayList<>();
    }

    public void setCells(ArrayList<CellAbstractModel> cells) {
        this.cells = cells;
    }

    public ArrayList<CellAbstractModel> getCells() {
        return cells;
    }
}
