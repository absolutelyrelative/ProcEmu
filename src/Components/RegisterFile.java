package Components;

public class RegisterFile extends MemoryModule{
    public RegisterFile(int wordsize, int registeramount){
        super(wordsize, registeramount, 0); //Registers have no offset.
    }
}
