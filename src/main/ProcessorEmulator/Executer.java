package ProcessorEmulator;

import Components.DataMemory;
import Components.InstructionMemory;
import Components.RegisterFile;

public class Executer {
    private static Executer instance;

    public static Executer getInstance() {
        if (instance == null)
            instance = new Executer();
        return instance;
    }

    //This function is fed the instruction to execute as a single String, which then makes the changes the instruction
    // specifies, and updates the next instruction to execute accordingly.
    public void ExecuteInstruction(DataMemory datamemory, InstructionMemory instructionmemory, RegisterFile registerfile, String instruction){

    }
}
