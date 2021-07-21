package Components;

public class ProgramCounter {

    private long wordlocation = 0;
    private long instructionmemorylocation = 0;
    private long maxvalue = 0; //TODO: Unused (?)
    private Double wordsize = 1.0;

    public ProgramCounter(Double wordsize) {
        this.wordsize = wordsize;
    }

    public void IncrementPC() {
        this.wordlocation += this.wordsize;
        this.instructionmemorylocation += 1;
    }

    public void SetPC(int instructionmemorylocation) {
        this.instructionmemorylocation = instructionmemorylocation;
        this.wordlocation = this.instructionmemorylocation * Math.round(this.wordsize);
    }
}
