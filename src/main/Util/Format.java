package Util;

//Utility enum intended to represent different data visualization format.
//I gather I will be using this quite frequently.
public enum Format {
    HEX(3),
    DECIMAL(2),
    BINARY(1);

    private final int formatnumber;

    private Format(int formatnumber) {
        this.formatnumber = formatnumber;
    }

    public int GetFormatNumber() {
        return this.formatnumber;
    }
}
