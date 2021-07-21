package Components;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class MemoryModule {
    private long offset = 0;
    private Double wordsize = 1.0;
    //I will use vectors for now, a self-implemented double linked list didn't make sense.
    private Vector<Object> MemoryContents;

    public MemoryModule(Double wordsize, int size, long offset){
        this.MemoryContents = new Vector<>(size);
        this.offset = offset;
        this.wordsize = wordsize;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    @Deprecated
    public Boolean AddElement(Object o){
        if( Double.parseDouble(this.toHex(o.toString())) > Math.pow(2,this.wordsize) ) {
            System.out.println("Double: " + Double.parseDouble(this.toHex(o.toString())));
            System.out.println("Double: " + Math.pow(2,this.wordsize));
            return false;
        }
        else
            this.MemoryContents.add(o);
        return true;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    public Boolean AddElement(long l){
        if( l > (Math.pow(2,this.wordsize) - 1) ) //In an 8-bit architecture, the maximum value is [(2^8) - 1]
            return false;
        else
            this.MemoryContents.add(l);
        return true;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    public Boolean AddElement(int i){
        return this.AddElement((long) i);
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    //TODO: Think about how to represent / distinguish in memory.
    public Boolean AddElement(float f){
        return false;
    }

    public Boolean RemoveElement(int location){
        if(this.MemoryContents.remove(location) == null){
            return false;
        }
        return true;
    }

    //TODO: Optimise with recursion
    public void PrintMemory(){
        long ctr = 0;
        for(Object o : MemoryContents){
            System.out.println(offset + ctr + ": " + o);
            ctr += this.wordsize;
        }
    }

    public String toHex(String argument){
        return String.format("%d", new BigInteger(1, argument.getBytes()));
    }
}
