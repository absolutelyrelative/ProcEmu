import Components.InstructionMemory;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Test");
        InstructionMemory IM = new InstructionMemory(8,1,0); //2^8 = 32 bit
        int a = 1;
        String b = "aaa";
        String c = "bbbbbbbb";
        String d = "cccccccc "; //This should return false and not be added, and it does
        IM.AddElement(a);
        IM.AddElement(b);
        System.out.println(IM.AddElement(c));
        System.out.println(IM.AddElement(d));
        IM.PrintMemory();
    }
}
