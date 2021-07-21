import Components.InstructionMemory;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("");
        InstructionMemory IM = new InstructionMemory(16.0,1,0); //2^8 = 8 bit -> max. value 256
        //int a = 257;
        //int b = 2147483647;
        //String c = "bbbbbbbb";
        //String d = "cccccccc "; //This should return false and not be added, and it does
        //IM.AddElement(a);
        //IM.AddElement(b);
        //System.out.println(IM.AddElement(c));
        //System.out.println(IM.AddElement(d));
        //System.out.println(IM.toHex("abcdefg"));
        //System.out.println(IM.toHex("abcdefgeeeee"));
        //System.out.println(IM.toHex(""));
        System.out.println(IM.AddElement("az"));
        System.out.println(IM.AddElement(256));
        System.out.println(IM.AddElement(257));
        System.out.println("------------------");
        IM.PrintMemory();
    }
}
