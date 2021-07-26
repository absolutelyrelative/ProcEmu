package Components;

import java.util.ArrayList;

public class InstructionMemory extends MemoryModule {
    private ProgramCounter pc;
    private ArrayList<String> instructions;
    private String nextinstruction; //I could use the ProgramCounter object, but it would be a lot more hassle for a bit more of fidelity.

    public InstructionMemory(Double wordsize, int size, long offset) {
        super(wordsize, size, offset, true);
        this.pc = new ProgramCounter(wordsize);
    }

    public ProgramCounter GetPC() {
        return pc;
    }

    public ArrayList<String> GetInstructions() {
        return instructions;
    }

    public void SetInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public String GetNextInstruction() {
        return nextinstruction;
    }

    public void SetNextInstruction(String nextinstruction) {
        this.nextinstruction = nextinstruction;
    }
}
