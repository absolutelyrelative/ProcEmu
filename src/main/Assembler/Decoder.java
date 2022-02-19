package Assembler;

import Util.Result;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: Implement other methods to move some coherency checks here and de-clutter the Translator class.
public class Decoder {

    private static Decoder instance;

    public static Decoder getInstance() {
        if (instance == null)
            instance = new Decoder();
        return instance;
    }

    //TODO: Utilize this to execute instructions on virtual memory / registers?
    public Result GetInstructionFromMachineCode(String machinecode) { //Machine Code is saved as Integer.toUnsignedString(machinecode)
        Result instruction = new Result();
        //As of Java 8, this should be 32-bit with unsigned option with Integer.parseUnsignedInt() & Integer.toUnsignedLong()
        Long code = Long.parseUnsignedLong(machinecode);

        switch ((int) (code >> 26)) {
            case 0: {
                //ADD & SUB
                String funct;
                long rd, rt, rs;
                if ((code & 0x3F) == 32) {
                    funct = "ADD";
                } else if ((code & 0x3F) == 34) {
                    funct = "SUB";
                } else {
                    instruction.SetSuccess(false);
                    return instruction;
                }
                rd = (code & 0xF800) >> 11;
                rt = (code & 0x1F_0000) >> 16;
                rs = (code & 0x3E0_0000) >> 21;

                instruction.SetSuccess(true);
                instruction.SetMessage(funct + ' ' + rd + ',' + rs + ',' + rt);
                break;
            }
            case 35: {
                //LW
                long ro, rd, offset;
                ro = (code & 0x3E0_0000) >> 21;
                rd = (code & 0x1F_0000) >> 16;
                offset = code & 0xFFFF;

                instruction.SetSuccess(true);
                instruction.SetMessage("LW " + rd + ',' + offset + ',' + ro);
                break;
            }
            case 43: {
                //SW
                long ro, rs, offset;
                ro = (code & 0x3E0_0000) >> 21;
                rs = (code & 0x1F_0000) >> 16;
                offset = code & 0xFFFF;

                instruction.SetSuccess(true);
                instruction.SetMessage("SW " + rs + ',' + offset + ',' + ro);
                break;
            }
            case 2: {
                //JMP
                long offset = code & 0x3FF_FFFF;

                instruction.SetSuccess(true);
                instruction.SetMessage("JMP " + offset);
                break;
            }
            case 4: {
                //BEQ
                long offset, rs, rt;
                offset = code & 0xFFFF;
                rs = (code & 0x3E0_0000) >> 21;
                rt = (code & 0x1F_0000) >> 16;

                instruction.SetSuccess(true);
                instruction.SetMessage("BEQ " + rs + ',' + rt + ',' + offset);
            }
            default: {
                instruction.SetSuccess(false);
            }

        }

        return instruction;
    }

}
