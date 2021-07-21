package Components.Controllers;

import java.util.Vector;

public class SingleCycleController {


    private static SingleCycleController instance;

    public static SingleCycleController getInstance(){
        if(instance == null)
            instance = new SingleCycleController();
        return instance;
    }

    //I could probably do this with a vector, but for now this is cleaner.
    public Signal registerdestination;
    public Signal jump;
    public Signal branch;
    public Signal memoryread;
    public Signal memorytoregister;
    public Signal ALUoperation;
    public Signal memorywrite;
    public Signal ALUsource;
    public Signal registerwrite;

    public SingleCycleController(){
        registerdestination = new Signal("RegisterDestination");
        jump = new Signal("Jump");
        branch = new Signal("Branch");
        memoryread = new Signal("MemoryRead");
        memorytoregister = new Signal("MemoryToRegister");
        ALUoperation = new Signal("ALUOperation");
        memorywrite = new Signal("MemoryWrite");
        ALUsource = new Signal("ALUSource");
        registerwrite = new Signal("RegisterWrite");
    }

}
