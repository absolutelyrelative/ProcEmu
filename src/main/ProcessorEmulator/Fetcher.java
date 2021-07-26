package ProcessorEmulator;

//Inverse operation to assembling. Fetches machine language
//I could have implemented decoding here without any cluttering of scope
//but in order to try to emulate even at this level how a processor works
//I decided to go for redundancy and favour fidelity to the model.
//It should read from memory instruction at a time, given an address
@Deprecated
public class Fetcher {

    private static Fetcher instance;

    public static Fetcher getInstance() {
        if (instance == null)
            instance = new Fetcher();
        return instance;
    }

}
