package Components;

import java.util.Vector;

public class RegisterFile extends MemoryModule {
    private static String prefix = "R";

    public RegisterFile(Double wordsize, int registeramount) {
        super(wordsize, registeramount, 0, false); //Registers have no offset and should not be expanded.
    }

    public String GetPrefix(){
        return prefix;
    }

    //TODO: Optimise with recursion
    public void PrintMemory() {
        //Avoid multiple calls
        Vector<Object> tempmem = super.GetMemory();
        //For registers it's 0 but let's call it anyway
        //TODO: Remove if not necessary in the future
        long offset = super.GetOffset();
        Double wordsize = super.GetWordSize();

        long ctr = 0;
        for (Object o : tempmem) {
            System.out.println(prefix + offset + ctr + ": " + o);
            ctr += wordsize;
        }
    }
}
