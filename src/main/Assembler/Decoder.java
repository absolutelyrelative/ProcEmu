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

    //TODO: Potentially improve performance by investigating the Big-O asymptote of ArrayList vs String[].
    public ArrayList<String> Splitter(String instruction){
        ArrayList<String> output = new ArrayList<>();
        String[] regexoutput = instruction.split("[ ,()]+");
        //System.out.println(output.length);
        switch(regexoutput[0].toUpperCase()){
            case "ADD":{
                if(regexoutput.length != 4){
                    return null; //Fail
                } else {

                }
                output.add(regexoutput[0].toUpperCase());
                break;
            }
            case "SUB":{break;}
            case "LW":{break;}
            case "SW":{break;}
            case "BEQ":{break;}
            case "JMP":{break;}
            default:{break;}
        }
        return null;
    }

    public Result RegisterFinder(String input) {
        Result r = new Result();
        Pattern regpattern = Pattern.compile("[^a-zA-Z]+"); //This will also match Ra00044C
        Matcher regmatcher = regpattern.matcher(input);
        if (regmatcher.find()) {
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
}
