import Assembler.Translator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TranslatorTest {
    private Translator translator;

    //TODO: Generalise RF set up so that tests can be performed on each subcase of wordsize, size, offset.
    @BeforeEach
    public void SetUp(){
        translator = new Translator();
    }

    @Test
    @DisplayName("Register parsing check.")
    public void TestRegisterParsing(){
        assertEquals("24",translator.RegisterFinder("R24").GetMessage());
        assertEquals("24",translator.RegisterFinder("r24").GetMessage());
        assertEquals("24",translator.RegisterFinder("T24").GetMessage());
        assertEquals("24",translator.RegisterFinder("24").GetMessage());
        assertEquals(4,Integer.parseInt(translator.RegisterFinder("04").GetMessage()));
        assertEquals("Register limit overflow / underflow.",translator.RegisterFinder("R33").GetMessage());
        assertEquals("Register limit overflow / underflow.",translator.RegisterFinder("R-3").GetMessage());
        assertEquals("Register not found.",translator.RegisterFinder("RXX").GetMessage());
        assertEquals("Register not found.",translator.RegisterFinder("R").GetMessage());
    }

    @Test
    @DisplayName("ADD instruction parsing check.")
    public void TestADDParsing(){
        assertEquals("18423840",translator.GetMachineCode("ADD R8,R17,R18").GetMessage());
    }

    @Test
    @DisplayName("SUB instruction parsing check.")
    public void TestSUBParsing(){
        assertEquals("18423842",translator.GetMachineCode("SUB R8,R17,R18").GetMessage());
    }
}
