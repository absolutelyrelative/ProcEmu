package Components;

public class InstructionMemory extends MemoryModule {
    private ProgramCounter pc;

    public InstructionMemory(Double wordsize, int size, long offset) {
        super(wordsize, size, offset, true);
        this.pc = new ProgramCounter(wordsize);
    }

    public ProgramCounter GetPC() {
        return pc;
    }

}
