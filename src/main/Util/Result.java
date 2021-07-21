package Util;

public class Result {
    private String message;
    private boolean success = false;

    public String GetMessage() {
        return message;
    }

    public void SetMessage(String message) {
        this.message = message;
    }

    public boolean IsSuccessful() {
        return success;
    }

    public void SetSuccess(boolean success) {
        this.success = success;
    }
}
