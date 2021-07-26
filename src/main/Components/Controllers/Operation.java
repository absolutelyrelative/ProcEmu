package Components.Controllers;

//This might come in handy later on
public enum Operation {
    ADD(0),
    SUB(1),
    LW(2),
    SW(3),
    BEW(4),
    JMP(5);

    private final int operation;

    private Operation(int operation) {
        this.operation = operation;
    }

    public int GetOperation() {
        return this.operation;
    }
}
