package Components;

public class InstructionMemory extends MemoryModule{
    private ProgramCounter pc;

    public InstructionMemory(Double wordsize, int size, long offset){
        super(wordsize, size, offset);
        this.pc = new ProgramCounter(wordsize);
    }
}
