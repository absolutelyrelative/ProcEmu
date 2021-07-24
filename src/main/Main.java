import Assembler.InstructionList;
import Assembler.Translator;
import Components.InstructionMemory;
import Util.Result;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
       String s1 = "LW R0,100(R1)";
       String s2 = "ADD R1,R2,R3";
       String s3 = "JMP 500";
       String s4 = "BEQ";

        InstructionList il = new InstructionList();
        il.AddInstruction(s1);
        il.AddInstruction(s2);
        il.AddInstruction(s3);
        il.AddInstruction(s4);
        il.PrintInstructions();
        ArrayList<Result> r = il.TranslateToMachineCode();
        if(r.isEmpty()){
            il.PrintMachineCode();
        } else {
            for(Result c : r){
                System.out.println("Error translating." + c.GetMessage());
            }
        }
    }
}