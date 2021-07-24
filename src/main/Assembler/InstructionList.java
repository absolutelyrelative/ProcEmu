package Assembler;

import Util.Result;

import java.util.ArrayList;

public class InstructionList {
    private ArrayList<String> instructions;
    private ArrayList<String> machinecode; //necessary to separate to check Result object
    private ArrayList<Result> translationresults;
    private Translator tr;

    public InstructionList() {
        instructions = new ArrayList<>();
        machinecode = new ArrayList<>();
        translationresults = new ArrayList<>();
        tr = new Translator();
    }

    //Is Result usage redundant?
    public Result AddInstruction(String instruction) {
        Result r = new Result();

        if (instructions.add(instruction)) {
            r.SetSuccess(true);
        } else {
            r.SetSuccess(false);
            r.SetMessage("Could not add instruction.");
        }

        return r;
    }

    public void RemoveInstructionAt(int index) {
        instructions.remove(index);
    }

    public void EmptyInstructionList() {
        if(!instructions.isEmpty()){
            for (String s : instructions) {
                instructions.remove(s);
            }
        }
    }

    public void EmptyMachineCodeList() {
        if(!machinecode.isEmpty()) {
            for (String s : machinecode) {
                machinecode.remove(s);
            }
        }
    }

    public void EmptyResults(){
        if(!translationresults.isEmpty()){
            for (Result r : translationresults){
                translationresults.remove(r);
            }
        }
    }

    public ArrayList<Result> TranslateToMachineCode() {

        if(!instructions.isEmpty()) {
            for (String s : instructions) {
                //Separating
                Result translationresult = tr.GetMachineCode(s);
                if (translationresult.IsSuccessful()) {
                    machinecode.add(translationresult.GetMessage());
                } else {
                    Result temp = new Result();
                    temp.SetSuccess(false);
                    temp.SetMessage("Error parsing instruction '" + s + "': " + translationresult.GetMessage());
                    translationresults.add(temp);
                }
            }
        } else {
            Result temp = new Result();
            temp.SetSuccess(false);
            temp.SetMessage("No instructions specified.");
            translationresults.add(temp);
        }

        return translationresults;
    }

    public void PrintMachineCode() {
        if(!machinecode.isEmpty()) {
            for (String s : machinecode) {
                System.out.println(s);
            }
        }
    }

    public void PrintInstructions() {
        if (!instructions.isEmpty()) {
            for (String s : instructions) {
                System.out.println(s);
            }
        }
    }

    public void PrintResults(){
        if(!translationresults.isEmpty()){
            for (Result r : translationresults){
                System.out.println(r.GetMessage());
            }
        }
    }

    public ArrayList<String> GetInstructions() {
        return instructions;
    }

    public ArrayList<String> GetMachineCode() {
        return machinecode;
    }

    public ArrayList<Result> GetTranslationResults() {
        return translationresults;
    }
}
