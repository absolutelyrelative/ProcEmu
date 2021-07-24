import Assembler.Translator;
import Components.InstructionMemory;

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        String instruction = "LW R1,100(R2)";

        String[] output = instruction.split("[ ,()]+");
        for(String s : output){
            System.out.println(s);
        }
    }
}