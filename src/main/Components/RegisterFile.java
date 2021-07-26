package Components;

import java.util.Vector;

public class RegisterFile extends MemoryModule {
    private static String prefix = "R";

    //TODO: Currently only 32 bits are supported in translator. Change this to allow n registers.
    public RegisterFile(Double wordsize, int registeramount) {
        super(wordsize, registeramount, 0, false); //Registers have no offset and should not be expanded.
    }

    public String GetPrefix(){
        return prefix;
    }

    //TODO: Optimise with recursion
    public void PrintInstructionMemory() {
        //Avoid multiple calls
        Vector<String> tempmem = super.GetMemory();
        //For registers it's 0 but let's call it anyway
        //TODO: Remove if not necessary in the future
        long offset = super.GetOffset();
        Double wordsize = super.GetWordSize();

        long ctr = 0;
        for (String s : tempmem) {
            System.out.println(prefix + offset + ctr + ": " + s);
            ctr += wordsize;
        }
    }
}
