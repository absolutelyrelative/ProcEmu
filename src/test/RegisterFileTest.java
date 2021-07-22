import Components.InstructionMemory;
import Components.RegisterFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterFileTest {
    private RegisterFile rf;

    //TODO: Generalise RF set up so that tests can be performed on each subcase of wordsize, size, offset.
    @BeforeEach
    public void SetUp(){
        rf = new RegisterFile(16.0,4);
    }

    @Test
    @DisplayName("Making sure the virtual memory cell does not contain larger data than it should.")
    public void TestMemoryCellSize(){
        assertTrue(rf.AddElement("az").IsSuccessful(), "Wordsize is 16bits, 2 bytes for each char are exactly 16bits (Provided it's ASCII).");
        assertTrue(rf.AddElement(65535).IsSuccessful());
        assertFalse(rf.AddElement(65536).IsSuccessful(), "0x10000 is out of bounds for 16 bits.");
        assertFalse(rf.AddElement("azzzzzzz").IsSuccessful(), "Wordsize is 16bits, 2 bytes for each char are above 16 bits regardless of standard of encoding.");
    }

    @Test
    @DisplayName("Making sure rf capacity (register count) are respected.")
    public void TestRegisterFileCapacity(){
        assertTrue(rf.AddElement(0xFF).IsSuccessful());
        assertTrue(rf.AddElement(0xFF).IsSuccessful());
        assertTrue(rf.AddElement(0xFF).IsSuccessful());
        assertFalse(rf.AddElement("azzzzzzz").IsSuccessful(), "This should be an illegal wordsize, therefore vector size should not be incremented.");
        assertTrue(rf.AddElement("aa").IsSuccessful());
        assertFalse(rf.AddElement(0xFF).IsSuccessful(), "The function to add integers should fail, as the initial capacity of 4 should be breached.");
        assertFalse(rf.AddElement("azzzzzzz").IsSuccessful(), "The function to add strings should fail, as the initial capacity of 4 should be breached.");
        assertFalse(rf.AddElement("aa").IsSuccessful(), "The function to ad strings should fail, as the initial capacity of 4 should be breached.");
    }
}
