package Assembler;

import Components.CellAbstractModel;
import Components.DataMemory;
import Util.AbstractCellTable;
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

    @Deprecated
    public void UploadInstructions(ArrayList<String> instructionlist, DataMemory memory, Format format, int startingaddress) {
        Encoder ec = new Encoder();
        Decoder dc = new Decoder();
        for(String s : instructionlist){
            if(ec.GetMachineCode(s).IsSuccessful()){
                if(dc.GetInstructionFromMachineCode(ec.GetMachineCode(s).GetMessage()).IsSuccessful()){
                    if(memory.getCells().isEmpty()){
                        CellAbstractModel newcell = new CellAbstractModel((long)0,Long.parseUnsignedLong(ec.GetMachineCode(s).GetMessage()),dc.GetInstructionFromMachineCode(ec.GetMachineCode(s).GetMessage()).GetMessage());
                        memory.getCells().add(newcell);
                    } else {
                        CellAbstractModel newcell = new CellAbstractModel(
                                ((long)memory.getCells().size()) * memory.getCells().get(0).getIncrement() + startingaddress,
                                Long.parseUnsignedLong(ec.GetMachineCode(s).GetMessage()), dc.GetInstructionFromMachineCode(ec.GetMachineCode(s).GetMessage()).GetMessage());
                        memory.getCells().add(newcell);
                    }
                }
            }
        }

    }

    //Takes a list of instructions 'instructionlist', our virtual data memory 'memorymodel', Format for conversion, and uploads instructions to the memory
    //If reset is true, the memory is reset before this operation.
    public void UploadInstructions(ArrayList<String> instructionlist, AbstractCellTable memorymodel, Format format, int startingaddress, boolean reset){
        if(reset){
            memorymodel.clearelements();
        }

        Encoder ec = new Encoder();
        Decoder dc = new Decoder();
        int increment;
        if(memorymodel.getdata().isEmpty()){
            increment = 32; //TODO: Enforce increment
        } else {
            increment = memorymodel.getdata().get(0).getIncrement();
        }

        for(String s : instructionlist){
            Result encoderresult = ec.GetMachineCode(s);
            if(encoderresult.IsSuccessful()){
                Result decoderresult = dc.GetInstructionFromMachineCode(encoderresult.GetMessage());
                if(decoderresult.IsSuccessful()){
                    if(memorymodel.getdata().isEmpty()){
                        CellAbstractModel newcell = new CellAbstractModel((long)0,Long.parseUnsignedLong(encoderresult.GetMessage()), decoderresult.GetMessage());
                        memorymodel.addelement(newcell);
                    } else {
                        CellAbstractModel newcell = new CellAbstractModel((long)memorymodel.getdata().size() * increment + startingaddress, Long.parseUnsignedLong(encoderresult.GetMessage()), decoderresult.GetMessage());
                        memorymodel.addelement(newcell);
                    }
                }
            }
        }
    }

    //This method is used to first decode the instructions rather than to receive them decoded
    public void DecodeAndUploadInstructions(ArrayList<String> instructionlist, AbstractCellTable memorymodel, Format format, int startingaddress, boolean reset){
        if(reset){
            memorymodel.clearelements();
        }

        Encoder ec = new Encoder();
        Decoder dc = new Decoder();
        int increment;
        if(memorymodel.getdata().isEmpty()){
            increment = 32; //TODO: Enforce increment
        } else {
            increment = memorymodel.getdata().get(0).getIncrement();
        }

        for(String s : instructionlist){
            Result decoderresult = dc.GetInstructionFromMachineCode(s);
            if(decoderresult.IsSuccessful()){
                if(memorymodel.getdata().isEmpty()){
                    CellAbstractModel newcell = new CellAbstractModel((long)0,Long.parseUnsignedLong(s), decoderresult.GetMessage());
                    memorymodel.addelement(newcell);
                } else {
                    CellAbstractModel newcell = new CellAbstractModel((long)memorymodel.getdata().size() * increment + startingaddress, Long.parseUnsignedLong(s), decoderresult.GetMessage());
                    memorymodel.addelement(newcell);
                }
            }
        }
    }

}
