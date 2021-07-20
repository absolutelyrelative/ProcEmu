package Components;

public class InstructionMemory extends MemoryModule{
    private ProgramCounter pc;

    public InstructionMemory(int wordsize, int size, int offset){
        super(wordsize, size, offset);
        this.pc = new ProgramCounter(wordsize);
    }
}
