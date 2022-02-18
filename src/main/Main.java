import Assembler.Decoder;
import Assembler.Encoder;

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
        Encoder ec = new Encoder();
        Decoder dc = new Decoder();

        String input = ec.GetMachineCode("SUB R1,R2,R3").GetMessage();
        System.out.println(input);
        System.out.println(dc.GetInstructionFromMachineCode(input).GetMessage());
    }
}