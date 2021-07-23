import Assembler.Translator;
import Components.InstructionMemory;

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        String instruction = "ADD,-R1,.R2,     R3";

        String[] output = instruction.split("[ ,]+");
        for(String s : output){
            System.out.println(s);
        }
    }
}