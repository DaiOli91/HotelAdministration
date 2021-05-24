package Model;

public enum State {

    ACTIVE("Active"),
    CANCELLED("Cancelled"),
    CHECKED("Checked"),
    ON_HOLD("On Hold");

    private String state;

    private State(String state) {

        this.state = state;
    }

    public String getState() {
        return state;
    }
}
