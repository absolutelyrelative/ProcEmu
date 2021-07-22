package Components;

import Util.Result;

import java.math.BigInteger;
import java.util.Vector;

public class MemoryModule {
    private long offset = 0;
    private Double wordsize = 1.0;
    //I will use vectors for now, a self-implemented double linked list didn't make sense.
    private Vector<Object> MemoryContents;
    private Boolean allowexpansion = true;
    private int originalsize;

    public MemoryModule(Double wordsize, int size, long offset, Boolean allowexpansion) {
        this.MemoryContents = new Vector<>(size);
        this.offset = offset;
        this.wordsize = wordsize;
        this.allowexpansion = allowexpansion;
        this.originalsize = size;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    @Deprecated
    public Result AddElement(Object o) {
        Result r = new Result();
        if (!allowexpansion) {
            if (MemoryContents.size() >= originalsize) {
                r.SetSuccess(false);
                return r;
            }
        }
        if (Double.parseDouble(this.toHex(o.toString())) > Math.pow(2, this.wordsize)) {
            r.SetSuccess(false);
        } else {
            this.MemoryContents.add(o);
            r.SetSuccess(true);
        }

        return r;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    public Result AddElement(String s) {
        Result r = new Result();
        if (!allowexpansion) {
            if (MemoryContents.size() >= originalsize) {
                r.SetSuccess(false);
                return r;
            }
        }
        if (Double.parseDouble(this.toHex(s)) > Math.pow(2, this.wordsize)) {
            r.SetSuccess(false);
        } else {
            this.MemoryContents.add(this.toHex(s));
            r.SetSuccess(true);
        }
        return r;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    public Result AddElement(long l) {
        Result r = new Result();
        if (!allowexpansion) {
            if (MemoryContents.size() >= originalsize) {
                r.SetSuccess(false);
                return r;
            }
        }
        if (l > (Math.pow(2, this.wordsize) - 1)) { //In an 8-bit architecture, the maximum value is [(2^8) - 1]
            r.SetSuccess(false);
        } else {
            this.MemoryContents.add(l);
            r.SetSuccess(true);
        }
        return r;
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    public Result AddElement(int i) {
        return this.AddElement((long) i);
    }

    //TODO: Add warning for out of bound memory instead of automatically adding more
    //TODO: Think about how to represent / distinguish in memory.
    public Result AddElement(float f) {
        return null;
    }

    public Result RemoveElement(int location) {
        Result r = new Result();
        if (this.MemoryContents.remove(location) == null) {
            r.SetSuccess(false);
        } else {
            r.SetSuccess(true);
        }
        return r;
    }

    //TODO: Optimise with recursion
    public void PrintMemory() {
        long ctr = 0;
        for (Object o : MemoryContents) {
            System.out.println(offset + ctr + ": " + o);
            ctr += this.wordsize;
        }
    }

    public String toHex(String argument) {
        return String.format("%d", new BigInteger(1, argument.getBytes()));
    }

    public Object GetElementAt(int i) {
        return MemoryContents.elementAt(i);
    }

    public Vector<Object> GetMemory() {
        return MemoryContents;
    }

    public Double GetWordSize() {
        return wordsize;
    }

    public long GetOffset() {
        return offset;
    }

}
