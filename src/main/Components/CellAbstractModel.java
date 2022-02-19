package Components;

//TODO: Refactor all Memory Components to utilise this Cell Model
public class CellAbstractModel {
    int increment = 32; //Word
    Long address = (long)0;
    Long data = (long)0;
    String instruction = "";

    public CellAbstractModel(Long address, Long data, String instruction) {
        this.address = address;
        this.data = data;
        this.instruction = instruction;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public Long getData() {
        return data;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
