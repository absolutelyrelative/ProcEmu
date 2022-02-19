import Assembler.Encoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class EncoderTest {
    private Encoder encoder;

    //TODO: Generalise RF set up so that tests can be performed on each sub-case of wordsize, size, offset.
    @BeforeEach
    public void SetUp() {
        encoder = new Encoder();
    }

    @Test
    @DisplayName("Register parsing check.")
    public void TestRegisterParsing() {
        assertEquals("24", encoder.RegisterFinder("R24").GetMessage());
        assertEquals("24", encoder.RegisterFinder("r24").GetMessage());
        assertEquals("24", encoder.RegisterFinder("T24").GetMessage());
        assertEquals("24", encoder.RegisterFinder("24").GetMessage());
        assertEquals(4, Integer.parseInt(encoder.RegisterFinder("04").GetMessage()));
        assertEquals("Register limit overflow / underflow.", encoder.RegisterFinder("R32").GetMessage());
        assertEquals("Register limit overflow / underflow.", encoder.RegisterFinder("R33").GetMessage());
        assertEquals("Register limit overflow / underflow.", encoder.RegisterFinder("R-3").GetMessage());
        assertEquals("Register not found.", encoder.RegisterFinder("RXX").GetMessage());
        assertEquals("Register not found.", encoder.RegisterFinder("R").GetMessage());
    }

    @Test
    @DisplayName("ADD instruction parsing check.")
    public void TestADDParsing() {
        assertEquals("36847648", encoder.GetMachineCode("ADD R8,R17,R18").GetMessage());
    }

    @Test
    @DisplayName("SUB instruction parsing check.")
    public void TestSUBParsing() {
        assertEquals("36847650", encoder.GetMachineCode("SUB R8,R17,R18").GetMessage());
    }

    @Test
    @DisplayName("Offset parsing check.")
    public void TestOffsetParsing() {
        //BEQ
        assertEquals("32767", encoder.OffsetFinder("32767", 0).GetMessage());
        assertEquals("-32767", encoder.OffsetFinder("-32767", 0).GetMessage());
        assertEquals("0", encoder.OffsetFinder("0", 0).GetMessage());
        assertEquals("Offset Underflow / Overflow.", encoder.OffsetFinder("32768", 0).GetMessage());
        assertEquals("Offset Underflow / Overflow.", encoder.OffsetFinder("-32768", 0).GetMessage());
        assertEquals("Register not found.", encoder.OffsetFinder("xXXXà", 0).GetMessage());

        //JMP
        assertEquals("33554431", encoder.OffsetFinder("33554431", 1).GetMessage());
        assertEquals("-33554431", encoder.OffsetFinder("-33554431", 1).GetMessage());
        assertEquals("0", encoder.OffsetFinder("0", 1).GetMessage());
        assertEquals("Offset Underflow / Overflow.", encoder.OffsetFinder("33554432", 1).GetMessage());
        assertEquals("Offset Underflow / Overflow.", encoder.OffsetFinder("-33554432", 1).GetMessage());
        assertEquals("Register not found.", encoder.OffsetFinder("xXXXà", 1).GetMessage());
    }

    @Test
    @DisplayName("JMP instruction parsing check.")
    public void TestJMPParsing() {
        assertEquals("134217728", encoder.GetMachineCode("JMP 0").GetMessage());
        assertEquals("134217729", encoder.GetMachineCode("JMP 1").GetMessage());
        assertEquals("167772161", encoder.GetMachineCode("JMP -1").GetMessage());
        assertEquals("167772159", encoder.GetMachineCode("JMP 33554431").GetMessage());
        assertEquals("201326591", encoder.GetMachineCode("JMP -33554431").GetMessage());
    }

    @Test
    @DisplayName("BEQ instruction parsing check.")
    public void TestBEQParsing() {
        assertEquals("270663880", encoder.GetMachineCode("BEQ R1,R2,200").GetMessage());
        assertEquals("270696648", encoder.GetMachineCode("BEQ R1,R2,-200").GetMessage());
        assertEquals("335478785", encoder.GetMachineCode("BEQ R31,R31,1").GetMessage());
        assertTrue(encoder.GetMachineCode("BEQ R0,R31,1").IsSuccessful());
        assertTrue(encoder.GetMachineCode("BEQ R1,R31,32767").IsSuccessful());
        assertTrue(encoder.GetMachineCode("BEQ R1,R31,-32767").IsSuccessful());
        assertFalse(encoder.GetMachineCode("BEQ R1,R31,32768").IsSuccessful());
        assertFalse(encoder.GetMachineCode("BEQ R1,R31, -32768").IsSuccessful());
        assertFalse(encoder.GetMachineCode("BEQ R1,R32,1").IsSuccessful());
    }

    @Test
    @DisplayName("LW instruction parsing check.")
    public void TestLWParsing() {
        assertEquals("2350972929", encoder.GetMachineCode("LW R1,1(R1)").GetMessage());
        assertEquals("2353070180", encoder.GetMachineCode("LW R1,100(R2)").GetMessage());
        assertEquals("2413821952", encoder.GetMachineCode("LW R0,0(R31)").GetMessage());
        assertEquals("2350841856", encoder.GetMachineCode("LW R31,0(R0)").GetMessage());
        assertEquals("2415853568", encoder.GetMachineCode("LW R31,0(R31)").GetMessage());
        assertEquals("2415886335", encoder.GetMachineCode("LW R31,32767(R31)").GetMessage());
        assertEquals("2415919103", encoder.GetMachineCode("LW R31,-32767(R31)").GetMessage());
        assertFalse(encoder.GetMachineCode("LW R-1,0(R1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("LW R0,0(R-2)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("LW -R1,0(r1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("LW R1,0(-r1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("LW R1,32768(r1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("LW R1,-32768(r1)").IsSuccessful());
    }

    @Test
    @DisplayName("SW instruction parsing check.")
    public void TestSWParsing() {
        assertEquals("2887843841", encoder.GetMachineCode("SW R1,1(R1)").GetMessage());
        assertEquals("2889941092", encoder.GetMachineCode("SW R1,100(R2)").GetMessage());
        assertEquals("2950692864", encoder.GetMachineCode("SW R0,0(R31)").GetMessage());
        assertEquals("2887712768", encoder.GetMachineCode("SW R31,0(R0)").GetMessage());
        assertEquals("2952724480", encoder.GetMachineCode("SW R31,0(R31)").GetMessage());
        assertEquals("2952757247", encoder.GetMachineCode("SW R31,32767(R31)").GetMessage());
        assertEquals("2952790015", encoder.GetMachineCode("SW R31,-32767(R31)").GetMessage());
        assertFalse(encoder.GetMachineCode("SW R-1,0(R1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("SW R0,0(R-2)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("SW -R1,0(r1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("SW R1,0(-r1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("SW R1,32768(r1)").IsSuccessful());
        assertFalse(encoder.GetMachineCode("SW R1,-32768(r1)").IsSuccessful());
    }

}
