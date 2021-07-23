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
        Result rdresult, rtresult, rsresult, roresult;
        Result offsetresult;
        String[] output = instruction.split("[ ,]+"); //TODO: Find better Regex, ex. [\p{Punct}\s]+

        long machinecode = 0xFFFFFFFF;
        switch (output[0].toUpperCase()) {
            case "ADD": {
                //Parsing registers
                rdresult = RegisterFinder(output[1]);
                if (rdresult.IsSuccessful()) {
                    rd = Integer.parseInt(rdresult.GetMessage());
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
                machinecode &= (32 + ((long) rs << 20) + ((long) rt << 15) + ((long) rd << 10));
                r.SetSuccess(true);
                r.SetMessage(Long.toString(machinecode));
                break;
            }
            case "SUB": {
                //Parsing registers
                rdresult = RegisterFinder(output[1]);
                if (rdresult.IsSuccessful()) {
                    rd = Integer.parseInt(rdresult.GetMessage());
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
                machinecode &= (34 + ((long) rs << 20) + ((long) rt << 15) + ((long) rd << 10));
                r.SetSuccess(true);
                r.SetMessage(Long.toString(machinecode));
                break;
            }
            case "LW":
                break;
            case "SW":
                break;
            case "BEQ":
                break;
            case "JMP": {
                //Parsing offset
                offsetresult = OffsetFinder(output[1], 1);
                if (offsetresult.IsSuccessful()) {
                    offset = Long.parseLong(offsetresult.GetMessage());
                    //TODO: JMP specifies number of WORDS to jump, this is fine, but implement word jumping later on.
                    //offset = (long)Math.floor((double)offset); //JMP cannot be done byte by byte
                } else {
                    r.SetSuccess(false);
                    r.SetMessage("Couldn't parse offset.");
                    break;
                }

                //Building instruction
                if(offset < 0){ //Negative signed binary algebra
                    offset = Math.abs(offset);
                    offset = offset + (1 << 25); //negative bit
                }
                machinecode &= (((long) 2 << 26) + offset);
                r.SetSuccess(true);
                r.SetMessage(Long.toString(machinecode));
                break;
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
            //TODO: Make register amount variable?
            if (Integer.parseInt(regmatcher.group(0)) > 32 || Integer.parseInt(regmatcher.group(0)) < 0) {
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

    //TODO: Merge RegisterFinder & OffsetFinder
    //0 - BEQ
    //1 - JMP
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
