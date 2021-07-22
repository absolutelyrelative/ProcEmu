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
    private int offset;

    private static Translator instance;

    public static Translator getInstance() {
        if (instance == null)
            instance = new Translator();
        return instance;
    }

    public Result GetMachineCode(String instruction){
        Result r = new Result();
        Result rdresult;
        Result rtresult;
        Result rsresult;
        Result roresult;
        //String example = "ADD R18,R17,R18";
        /*Pattern opcodepattern = Pattern.compile("^[a-z]* .", Pattern.CASE_INSENSITIVE);
        Matcher matcher = opcodepattern.matcher(instruction);
        boolean matchfound = matcher.find();
        if(matchfound){
            //found
        } else {
            //not found
        }
        String OP = "";*/
        String[] output = instruction.split("[\\p{Punct}\\s]+");
        //ADD R1 R2 R3
        long machinecode = 0xFFFFFFFF;
        switch(output[0].toUpperCase()){
            case "ADD":
                //Parsing registers
                rdresult = RegisterFinder(output[1]);
                if(rdresult.IsSuccessful()){
                    rd = Integer.parseInt(rdresult.GetMessage());
                } else {
                    r.SetSuccess(false);
                    r.SetMessage("Register not found.");
                }
                rsresult = RegisterFinder(output[2]);
                if(rsresult.IsSuccessful()){
                    rs = Integer.parseInt(rsresult.GetMessage());
                } else {
                    r.SetSuccess(false);
                    r.SetMessage("Register not found.");
                }
                rtresult = RegisterFinder(output[3]);
                if(rtresult.IsSuccessful()){
                    rt = Integer.parseInt(rtresult.GetMessage());
                } else {
                    r.SetSuccess(false);
                    r.SetMessage("Register not found.");
                }

                //Building instruction
                machinecode &= (32 + ((long) rs << 20) + ((long) rt << 15) + ((long) rd << 10));
                r.SetSuccess(true);
                r.SetMessage(Long.toString(machinecode));
                break;
            case "SUB":
                //Parsing registers
                rdresult = RegisterFinder(output[1]);
                if(rdresult.IsSuccessful()){
                    rd = Integer.parseInt(rdresult.GetMessage());
                } else {
                    r.SetSuccess(false);
                    r.SetMessage("Register not found.");
                }
                rsresult = RegisterFinder(output[2]);
                if(rsresult.IsSuccessful()){
                    rs = Integer.parseInt(rsresult.GetMessage());
                } else {
                    r.SetSuccess(false);
                    r.SetMessage("Register not found.");
                }
                rtresult = RegisterFinder(output[3]);
                if(rtresult.IsSuccessful()){
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
            case "LW":
                break;
            case "SW":
                break;
            case "BEQ":
                break;
            case "JMP":
                break;
            default:
                r.SetSuccess(false);
                r.SetMessage("Instruction not found.");
                break;
        }
        return r;
    }

    public Result RegisterFinder(String input){
        Result r = new Result();
        Pattern regpattern = Pattern.compile("[^a-zA-Z]+"); //This will also match Ra00044C
        Matcher regmatcher = regpattern.matcher(input);
        if(regmatcher.find() == true){ //Code should be as readable as possible ;)
            //32 registers maximum
            //TODO: Make register amount variable?
            if(Integer.parseInt(regmatcher.group(0)) > 32 || Integer.parseInt(regmatcher.group(0)) < 0){
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

}
