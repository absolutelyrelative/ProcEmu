import Components.InstructionMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InstructionMemoryTest {
    private InstructionMemory im;

    //TODO: Generalise IM set up so that tests can be performed on each subcase of wordsize, size, offset.
    @BeforeEach
    public void SetUp(){
        im = new InstructionMemory(16.0,4,0);
    }

    @Test
    @DisplayName("Making sure the virtual memory cell does not contain larger data than it should.")
    public void TestMemoryCellSize(){
        assertTrue(im.AddElement("az").IsSuccessful(), "Wordsize is 16bits, 2 bytes for each char are exactly 16bits (Provided it's ASCII).");
        assertTrue(im.AddElement(256).IsSuccessful());
        assertTrue(im.AddElement(257).IsSuccessful());
        assertTrue(im.AddElement(65535).IsSuccessful());
        assertFalse(im.AddElement(65536).IsSuccessful(), "0x10000 is out of bounds for 16 bits.");
        assertFalse(im.AddElement("azzzzzzz").IsSuccessful(), "Wordsize is 16bits, 2 bytes for each char are above 16 bits regardless of standard of encoding.");


    }

}
