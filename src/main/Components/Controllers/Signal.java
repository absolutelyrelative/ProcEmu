package Components.Controllers;

public class Signal {

    private String name;
    private Boolean status = false;

    public Signal(String name) {
        this.name = name;
    }

    public void TurnOn() {
        this.status = true;
    }

    public void TurnOff() {
        this.status = false;
    }
}
