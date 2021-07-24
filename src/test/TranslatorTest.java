import Assembler.Translator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TranslatorTest {
    private Translator translator;

    //TODO: Generalise RF set up so that tests can be performed on each sub-case of wordsize, size, offset.
    @BeforeEach
    public void SetUp() {
        translator = new Translator();
    }

    @Test
    @DisplayName("Register parsing check.")
    public void TestRegisterParsing() {
        assertEquals("24", translator.RegisterFinder("R24").GetMessage());
        assertEquals("24", translator.RegisterFinder("r24").GetMessage());
        assertEquals("24", translator.RegisterFinder("T24").GetMessage());
        assertEquals("24", translator.RegisterFinder("24").GetMessage());
        assertEquals(4, Integer.parseInt(translator.RegisterFinder("04").GetMessage()));
        assertEquals("Register limit overflow / underflow.", translator.RegisterFinder("R32").GetMessage());
        assertEquals("Register limit overflow / underflow.", translator.RegisterFinder("R33").GetMessage());
        assertEquals("Register limit overflow / underflow.", translator.RegisterFinder("R-3").GetMessage());
        assertEquals("Register not found.", translator.RegisterFinder("RXX").GetMessage());
        assertEquals("Register not found.", translator.RegisterFinder("R").GetMessage());
    }

    @Test
    @DisplayName("ADD instruction parsing check.")
    public void TestADDParsing() {
        assertEquals("18423840", translator.GetMachineCode("ADD R8,R17,R18").GetMessage());
    }

    @Test
    @DisplayName("SUB instruction parsing check.")
    public void TestSUBParsing() {
        assertEquals("18423842", translator.GetMachineCode("SUB R8,R17,R18").GetMessage());
    }

    @Test
    @DisplayName("Offset parsing check.")
    public void TestOffsetParsing() {
        //BEQ
        assertEquals("32767", translator.OffsetFinder("32767", 0).GetMessage());
        assertEquals("-32767", translator.OffsetFinder("-32767", 0).GetMessage());
        assertEquals("0", translator.OffsetFinder("0", 0).GetMessage());
        assertEquals("Offset Underflow / Overflow.", translator.OffsetFinder("32768", 0).GetMessage());
        assertEquals("Offset Underflow / Overflow.", translator.OffsetFinder("-32768", 0).GetMessage());
        assertEquals("Register not found.", translator.OffsetFinder("xXXXà", 0).GetMessage());

        //JMP
        assertEquals("33554431", translator.OffsetFinder("33554431", 1).GetMessage());
        assertEquals("-33554431", translator.OffsetFinder("-33554431", 1).GetMessage());
        assertEquals("0", translator.OffsetFinder("0", 1).GetMessage());
        assertEquals("Offset Underflow / Overflow.", translator.OffsetFinder("33554432", 1).GetMessage());
        assertEquals("Offset Underflow / Overflow.", translator.OffsetFinder("-33554432", 1).GetMessage());
        assertEquals("Register not found.", translator.OffsetFinder("xXXXà", 1).GetMessage());
    }

    @Test
    @DisplayName("JMP instruction parsing check.")
    public void TestJMPParsing() {
        assertEquals("134217728", translator.GetMachineCode("JMP 0").GetMessage());
        assertEquals("134217729", translator.GetMachineCode("JMP 1").GetMessage());
        assertEquals("167772161", translator.GetMachineCode("JMP -1").GetMessage());
        assertEquals("167772159", translator.GetMachineCode("JMP 33554431").GetMessage());
        assertEquals("201326591", translator.GetMachineCode("JMP -33554431").GetMessage());
    }

    @Test
    @DisplayName("BEQ instruction parsing check.")
    public void TestBEQParsing() {
        assertEquals("270663880", translator.GetMachineCode("BEQ R1,R2,200").GetMessage());
        assertEquals("270696648", translator.GetMachineCode("BEQ R1,R2,-200").GetMessage());
        assertEquals("335478785", translator.GetMachineCode("BEQ R31,R31,1").GetMessage());
        assertTrue(translator.GetMachineCode("BEQ R0,R31,1").IsSuccessful());
        assertTrue(translator.GetMachineCode("BEQ R1,R31,32767").IsSuccessful());
        assertTrue(translator.GetMachineCode("BEQ R1,R31,-32767").IsSuccessful());
        assertFalse(translator.GetMachineCode("BEQ R1,R31,32768").IsSuccessful());
        assertFalse(translator.GetMachineCode("BEQ R1,R31, -32768").IsSuccessful());
        assertFalse(translator.GetMachineCode("BEQ R1,R32,1").IsSuccessful());
    }

    @Test
    @DisplayName("LW instruction parsing check.")
    public void TestLWParsing() {
        assertEquals("2350972929", translator.GetMachineCode("LW R1,1(R1)").GetMessage());
        assertEquals("2353070180", translator.GetMachineCode("LW R1,100(R2)").GetMessage());
        assertEquals("2413821952", translator.GetMachineCode("LW R0,0(R31)").GetMessage());
        assertEquals("2350841856", translator.GetMachineCode("LW R31,0(R0)").GetMessage());
        assertEquals("2415853568", translator.GetMachineCode("LW R31,0(R31)").GetMessage());
        assertEquals("2415886335", translator.GetMachineCode("LW R31,32767(R31)").GetMessage());
        assertEquals("2415919103", translator.GetMachineCode("LW R31,-32767(R31)").GetMessage());
        assertFalse(translator.GetMachineCode("LW R-1,0(R1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("LW R0,0(R-2)").IsSuccessful());
        assertFalse(translator.GetMachineCode("LW -R1,0(r1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("LW R1,0(-r1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("LW R1,32768(r1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("LW R1,-32768(r1)").IsSuccessful());
    }

    @Test
    @DisplayName("SW instruction parsing check.")
    public void TestSWParsing() {
        assertEquals("2887843841", translator.GetMachineCode("SW R1,1(R1)").GetMessage());
        assertEquals("2889941092", translator.GetMachineCode("SW R1,100(R2)").GetMessage());
        assertEquals("2950692864", translator.GetMachineCode("SW R0,0(R31)").GetMessage());
        assertEquals("2887712768", translator.GetMachineCode("SW R31,0(R0)").GetMessage());
        assertEquals("2952724480", translator.GetMachineCode("SW R31,0(R31)").GetMessage());
        assertEquals("2952757247", translator.GetMachineCode("SW R31,32767(R31)").GetMessage());
        assertEquals("2952790015", translator.GetMachineCode("SW R31,-32767(R31)").GetMessage());
        assertFalse(translator.GetMachineCode("SW R-1,0(R1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("SW R0,0(R-2)").IsSuccessful());
        assertFalse(translator.GetMachineCode("SW -R1,0(r1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("SW R1,0(-r1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("SW R1,32768(r1)").IsSuccessful());
        assertFalse(translator.GetMachineCode("SW R1,-32768(r1)").IsSuccessful());
    }

}
