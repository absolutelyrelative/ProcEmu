package Assembler;

import Components.CellAbstractModel;
import Util.Format;
import Util.Result;

import java.util.ArrayList;

//This takes an InstructionList and uploads it to a given Instruction Memory
//I could generalise this and utilise morphism to add any variable to any virtual memory
//but for fidelity to the low level model, I am distinguishing them.
//instructionlist -> machinecode -> instructionmemory
public class IMUploader {

    private static IMUploader instance;
    private ArrayList<String> machinecode;
    private InstructionList il = new InstructionList(); //Consider moving instantiation in a method in case morphism is required.

    public static IMUploader getInstance() {
        if (instance == null)
            instance = new IMUploader();
        return instance;
    }

    public void UploadInstructions(ArrayList<String> instructionlist, ArrayList<CellAbstractModel> instructionmemory, Format format, int startingaddress) {
        Encoder ec = new Encoder();
        Decoder dc = new Decoder();
        for(String s : instructionlist){
            if(ec.GetMachineCode(s).IsSuccessful()){
                if(dc.GetInstructionFromMachineCode(ec.GetMachineCode(s).GetMessage()).IsSuccessful()){
                    if(instructionmemory.isEmpty()){
                        CellAbstractModel newcell = new CellAbstractModel((long)0,Long.parseUnsignedLong(ec.GetMachineCode(s).GetMessage()),dc.GetInstructionFromMachineCode(ec.GetMachineCode(s).GetMessage()).GetMessage());
                        instructionmemory.add(newcell);
                    } else {
                        CellAbstractModel newcell = new CellAbstractModel(
                                ((long)instructionmemory.size()) * instructionmemory.get(0).getIncrement() + startingaddress,
                                Long.parseUnsignedLong(ec.GetMachineCode(s).GetMessage()), dc.GetInstructionFromMachineCode(ec.GetMachineCode(s).GetMessage()).GetMessage());
                        instructionmemory.add(newcell);
                    }
                }
            }
        }
    }

}
