package Components.Controllers;

public class SingleCycleController {
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
}
