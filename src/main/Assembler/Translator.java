package Assembler;

import Util.Result;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This class translate single instruction strings into single machine code strings
//Example: This class receives 'ADD R18,R17,R18' and should translate
//          it to 0, 17, 18, 8, 0, 32 in binary:
//          000000  6 bit
//          10001   5 bit
//          10010   5 bit
//          01000   5 bit
//          00000   5 bit
//          100000  6 bit
//

public class Translator {
    private int opcode;
    private int ro;
    private int rs;
    private int rd;
    private int rt;
    private int shamt;
    private int funct;
    private long offset;

    private static Translator instance;

    public static Translator getInstance() {
        if (instance == null)
            instance = new Translator();
        return instance;
    }

    public Result GetMachineCode(String instruction) {
        Result r = new Result();
        Result rsresult, rtresult, rdresult, roresult;
        Result offsetresult;
        String[] output = instruction.split("[ ,()]+"); //TODO: Find better Regex, ex. [\p{Punct}\s]+

        //As of Java 8, this should be 32-bit with unsigned option
        Integer machinecode = 0xFFFFFFFF;
        switch (output[0].toUpperCase()) {
            case "ADD": {
                if (output.length != 4){
                    r.SetSuccess(false);
                    r.SetMessage("Wrong instruction format for " + instruction);
                    break;
                } else {
                    //Parsing registers
                    rsresult = RegisterFinder(output[1]);
                    if (rsresult.IsSuccessful()) {
                        rd = Integer.parseInt(rsresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                        break;
                    }
                    rsresult = RegisterFinder(output[2]);
                    if (rsresult.IsSuccessful()) {
                        rs = Integer.parseInt(rsresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                        break;
                    }
                    rtresult = RegisterFinder(output[3]);
                    if (rtresult.IsSuccessful()) {
                        rt = Integer.parseInt(rtresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                        break;
                    }

                    //Building instruction
                    machinecode &= (int) (32 + ((long) rs << 20) + ((long) rt << 15) + ((long) rd << 10));
                    r.SetSuccess(true);
                    r.SetMessage(Integer.toUnsignedString(machinecode)); //New in Java 8
                    break;
                }
            }
            case "SUB": {
                if (output.length != 4){
                    r.SetSuccess(false);
                    r.SetMessage("Wrong instruction format for " + instruction);
                    break;
                } else {
                    //Parsing registers
                    rsresult = RegisterFinder(output[1]);
                    if (rsresult.IsSuccessful()) {
                        rd = Integer.parseInt(rsresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                    }
                    rsresult = RegisterFinder(output[2]);
                    if (rsresult.IsSuccessful()) {
                        rs = Integer.parseInt(rsresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                    }
                    rtresult = RegisterFinder(output[3]);
                    if (rtresult.IsSuccessful()) {
                        rt = Integer.parseInt(rtresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                    }

                    //Building instruction
                    machinecode &= (int) (34 + ((long) rs << 20) + ((long) rt << 15) + ((long) rd << 10));
                    r.SetSuccess(true);
                    r.SetMessage(Integer.toUnsignedString(machinecode)); //New in Java 8
                    break;
                }
            }
            case "LW": { //TODO: Reading from a non-multiple of (wordsize) should be possible.
                if (output.length != 4){
                    r.SetSuccess(false);
                    r.SetMessage("Wrong instruction format for " + instruction);
                    break;
                } else {
                    //Parsing registers RO, RD, OFFSET
                    rsresult = RegisterFinder(output[1]);
                    offsetresult = OffsetFinder(output[2], 0);
                    roresult = RegisterFinder(output[3]);
                    if (rsresult.IsSuccessful() && roresult.IsSuccessful() && offsetresult.IsSuccessful()) {
                        rd = Integer.parseInt(rsresult.GetMessage());
                        ro = Integer.parseInt(roresult.GetMessage());
                        offset = Long.parseLong(offsetresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Registers / offset not found.");
                        break;
                    }

                    //Building instruction
                    if (offset < 0) { //Negative signed binary algebra
                        offset = Math.abs(offset);
                        offset = offset + (1 << 15); //negative bit
                    }
                    machinecode &= (int) ((35 << 26) + ((long) ro << 21) + ((long) rd << 16) + offset);
                    r.SetSuccess(true);
                    r.SetMessage(Integer.toUnsignedString(machinecode)); //New in Java 8
                    break;
                }
            }
            case "SW": {
                if (output.length != 4){
                    r.SetSuccess(false);
                    r.SetMessage("Wrong instruction format for " + instruction);
                    break;
                } else {
                    //Parsing registers RO, RS, OFFSET
                    rsresult = RegisterFinder(output[1]);
                    offsetresult = OffsetFinder(output[2], 0);
                    roresult = RegisterFinder(output[3]);
                    if (rsresult.IsSuccessful() && roresult.IsSuccessful() && offsetresult.IsSuccessful()) {
                        rd = Integer.parseInt(rsresult.GetMessage());
                        ro = Integer.parseInt(roresult.GetMessage());
                        offset = Long.parseLong(offsetresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Registers / offset not found.");
                        break;
                    }

                    //Building instruction
                    if (offset < 0) { //Negative signed binary algebra
                        offset = Math.abs(offset);
                        offset = offset + (1 << 15); //negative bit
                    }
                    machinecode &= (int) ((43 << 26) + ((long) ro << 21) + ((long) rd << 16) + offset);
                    r.SetSuccess(true);
                    r.SetMessage(Integer.toUnsignedString(machinecode)); //New in Java 8
                    break;
                }
            }
            case "BEQ":{
                if (output.length != 4){
                    r.SetSuccess(false);
                    r.SetMessage("Wrong instruction format for " + instruction);
                    break;
                } else {
                    //Parsing registers
                    rsresult = RegisterFinder(output[1]);
                    if (rsresult.IsSuccessful()) {
                        rs = Integer.parseInt(rsresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                        break;
                    }
                    rtresult = RegisterFinder(output[2]);
                    if (rtresult.IsSuccessful()) {
                        rt = Integer.parseInt(rtresult.GetMessage());
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Register not found.");
                        break;
                    }

                    //Parsing offset
                    offsetresult = OffsetFinder(output[3], 0);
                    if (offsetresult.IsSuccessful()) {
                        offset = Long.parseLong(offsetresult.GetMessage());
                        //TODO: BEQ specifies number of WORDS to jump, this is fine, but implement word jumping later on if necessary.
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Couldn't parse offset.");
                        break;
                    }

                    //Building instruction
                    if (offset < 0) { //Negative signed binary algebra
                        offset = Math.abs(offset);
                        offset = offset + (1 << 15); //negative bit
                    }
                    machinecode &= (int) (((long) 4 << 26) + offset + ((long) rs << 21) + ((long) rt << 16));
                    r.SetSuccess(true);
                    r.SetMessage(Integer.toUnsignedString(machinecode)); //New in Java 8
                    break;
                }
            }
            case "JMP": {
                if (output.length != 2){
                    r.SetSuccess(false);
                    r.SetMessage("Wrong instruction format for " + instruction);
                    break;
                } else {
                    //Parsing offset
                    offsetresult = OffsetFinder(output[1], 1);
                    if (offsetresult.IsSuccessful()) {
                        offset = Long.parseLong(offsetresult.GetMessage());
                        //TODO: JMP specifies number of WORDS to jump, this is fine, but implement word jumping later on if necessary.
                        //offset = (long)Math.floor((double)offset); //JMP cannot be done byte by byte
                    } else {
                        r.SetSuccess(false);
                        r.SetMessage("Couldn't parse offset.");
                        break;
                    }

                    //Building instruction
                    if (offset < 0) { //Negative signed binary algebra
                        offset = Math.abs(offset);
                        offset = offset + (1 << 25); //negative bit
                    }
                    machinecode &= (int) (((long) 2 << 26) + offset);
                    r.SetSuccess(true);
                    r.SetMessage(Integer.toUnsignedString(machinecode)); //New in Java 8
                    break;
                }
            }
            default: {
                r.SetSuccess(false);
                r.SetMessage("Instruction not found.");
                break;
            }
        }
        return r;
    }

    public Result RegisterFinder(String input) {
        Result r = new Result();
        Pattern regpattern = Pattern.compile("[^a-zA-Z]+"); //This will also match Ra00044C
        Matcher regmatcher = regpattern.matcher(input);
        if (regmatcher.find() == true) { //Code should be as readable as possible ;)
            //32 registers maximum
            //TODO: Make register amount variable? Registers are 32 and go from 0 to 31 at the moment.
            if (Integer.parseInt(regmatcher.group(0)) > 31 || Integer.parseInt(regmatcher.group(0)) < 0) {
                r.SetSuccess(false);
                r.SetMessage("Register limit overflow / underflow.");
            } else {
                r.SetSuccess(true);
                r.SetMessage(regmatcher.group(0));
            }
        } else {
            //Register not found
            r.SetSuccess(false);
            r.SetMessage("Register not found.");
        }
        return r;
    }

    //TODO: Merge RegisterFinder & OffsetFinder.
    //TODO: Use mode to dynamically calculate offsets if needed.
    //0 - BEQ, LW, SW (16-bit OFFSET)
    //1 - JMP (26-bit OFFSET)
    public Result OffsetFinder(String input, int mode) {
        Result r = new Result();
        Pattern regpattern = Pattern.compile("^(\\+|\\-)?(\\d)+"); //This will also match Ra00044C
        Matcher regmatcher = regpattern.matcher(input);
        if (regmatcher.find() == true) {
            switch (mode) {
                case 0: { // BEQ
                    if (Math.abs(Long.parseLong(regmatcher.group(0))) > (0x7FFF)) { //(2^15 - 1) OFFSET, 1 bit for sign
                        r.SetSuccess(false);
                        r.SetMessage("Offset Underflow / Overflow.");
                    } else {
                        r.SetSuccess(true);
                        r.SetMessage(regmatcher.group(0));
                    }
                    break;
                }
                case 1: { // JMP
                    if (Math.abs(Long.parseLong(regmatcher.group(0))) > (0x1FFFFFF)) { // 2^25 - 1
                        r.SetSuccess(false);
                        r.SetMessage("Offset Underflow / Overflow.");
                    } else {
                        r.SetSuccess(true);
                        r.SetMessage(regmatcher.group(0));
                    }
                    break;
                }
                default: {
                    r.SetSuccess(false);
                    r.SetMessage("Unexpected OP.");
                    break;
                }
            }
        } else {
            //Register not found
            r.SetSuccess(false);
            r.SetMessage("Register not found.");
        }
        return r;
    }

}
