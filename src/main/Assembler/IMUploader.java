package Assembler;

import Components.InstructionMemory;
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

    //Given a list of instructions, the instructions are instantiated as an InstructionList object, and then translated
    // and added to memory.
    public void UploadInstructions(ArrayList<String> instructionlist, InstructionMemory im, Format format) {
        for (String s : instructionlist) {
            il.AddInstruction(s);
        }
        il.TranslateToMachineCode(format);
        ArrayList<Result> translationresult = il.GetTranslationResults();
        if (translationresult.isEmpty()) { //No errors
            im.SetInstructions(instructionlist); //If successful, also add the list to avoid having to decode.
            im.SetNextInstruction(instructionlist.get(0)); //If successful, set the next instruction accordingly.
            for (String s : il.GetMachineCode()) {
                im.AddElement(s); //Adds element to the virtual memory
            }
        } else { //TODO: Show errors here
            System.out.println("Errors.");
        }
    }

}
