import Assembler.Decoder;
import Assembler.IMUploader;
import Assembler.InstructionList;
import Assembler.Translator;
import Components.InstructionMemory;
import Util.Format;
import Util.Result;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        /*ArrayList<String> instructions = new ArrayList<>();
        instructions.add("LW R0,100(r1)");
        instructions.add("JMP 500");
        instructions.add("BEQ R1,R2,20");
        instructions.add("JMP 40");
        InstructionMemory im = new InstructionMemory((double)32,10,900);
        IMUploader.getInstance().UploadInstructions(instructions, im, Format.HEX);
        im.PrintMemory();*/
        Decoder dc = new Decoder();
        dc.Splitter("LW R1,200(R2)");
    }
}