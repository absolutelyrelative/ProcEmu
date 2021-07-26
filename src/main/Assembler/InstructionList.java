package Assembler;

import Util.Format;
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
        if (!instructions.isEmpty()) {
            for (String s : instructions) {
                instructions.remove(s);
            }
        }
    }

    public void EmptyMachineCodeList() {
        if (!machinecode.isEmpty()) {
            for (String s : machinecode) {
                machinecode.remove(s);
            }
        }
    }

    public void EmptyResults() {
        if (!translationresults.isEmpty()) {
            for (Result r : translationresults) {
                translationresults.remove(r);
            }
        }
    }

    public void TranslateToMachineCode(Format format) {

        if (!instructions.isEmpty()) {
            for (String s : instructions) {
                //Separating
                Result translationresult = tr.GetMachineCode(s);
                if (translationresult.IsSuccessful()) {
                    switch (format) {
                        case HEX: {
                            machinecode.add(String.format("0x%08X", Integer.parseUnsignedInt(translationresult.GetMessage())));
                            break;
                        }
                        case DECIMAL: {
                            machinecode.add(translationresult.GetMessage());
                            break;
                        }
                        case BINARY: {
                            machinecode.add(String.format("%32s", Integer.toBinaryString(Integer.parseUnsignedInt(translationresult.GetMessage()))));
                            break;
                        }
                        default: {
                            machinecode.add(translationresult.GetMessage()); //This should never happen anyway.
                            break;
                        }
                    }
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
    }

    public void PrintMachineCode(Format format) {
        if (!machinecode.isEmpty()) {
            for (String s : machinecode) {

                switch (format) {
                    case HEX: {
                        System.out.println(String.format("0x%08X", Integer.parseUnsignedInt(s)));
                        break;
                    }
                    case BINARY: {
                        System.out.println(String.format("%32s", Integer.toBinaryString(Integer.parseUnsignedInt(s))));
                        break;
                    }
                    case DECIMAL: {
                        //System.out.println(String.valueOf(Integer.parseUnsignedInt(s)));
                        System.out.println(s); //No way to do this with String.format without losing 1 bit at least (2^31 - 1)
                        break;
                    }
                    default: {
                        System.out.println(s); //This should never happen anyway
                        break;
                    }
                }
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

    public void PrintResults() {
        if (!translationresults.isEmpty()) {
            for (Result r : translationresults) {
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
