package Components;

public class ProgramCounter {

    private int wordlocation = 0;
    private int instructionmemorylocation = 0;
    private int maxvalue = 0; //TODO: Unused (?)
    private int wordsize = 0;

    public ProgramCounter(int wordsize){
        this.wordsize = wordsize;
    }

    public void IncrementPC(){
        this.wordlocation += this.wordsize;
        this.instructionmemorylocation += 1;
    }

    public void SetPC(int instructionmemorylocation){
        this.instructionmemorylocation = instructionmemorylocation;
        this.wordlocation = this.instructionmemorylocation * this.wordsize;
    }
}
