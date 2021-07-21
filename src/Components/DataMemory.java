package Components;

//Only used in Single Cycle / Pipe architectures
public class DataMemory extends MemoryModule{

    public DataMemory(Double wordsize, int size, long offset){
        super(wordsize, size, offset);
    }
}
