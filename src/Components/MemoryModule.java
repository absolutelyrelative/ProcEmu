package Components;

import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class MemoryModule {
    private int offset = 0;
    private int wordsize = 1;
    //I will use vectors for now, a self-implemented double linked list didn't make sense.
    private Vector<Object> MemoryContents;

    public MemoryModule(int wordsize, int size, int offset){
        this.MemoryContents = new Vector<>(size);
        this.offset = offset;
        this.wordsize = wordsize;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    public Boolean AddElement(Object o){
        //Using ASCII instead of UTF_8 to avoid multiple byte variables (UTF_8 varies in size)
        //TODO: US_ASCII is 7 bit for some reason, make sure this doesn't impact size check (it shouldn't)
        if( o.toString().getBytes(StandardCharsets.US_ASCII).length > this.wordsize ) //Size > 32bit
            return false;
        else
            this.MemoryContents.add(o);
        return true;
    }

    public Boolean RemoveElement(int location){
        if(this.MemoryContents.remove(location) == null){
            return false;
        }
        return true;
    }

    //TODO: Optimise with recursion
    public void PrintMemory(){
        int ctr = 0;
        for(Object o : MemoryContents){
            System.out.println(offset + ctr + ": " + o);
            ctr += this.wordsize;
        }
    }
}
