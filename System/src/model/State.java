package model;

public enum State {

    ACTIVE("Active"),
    CANCELLED("Cancelled"),
    CHECKED("Checked"),
    CHECK_OUT("Checked Out");

    private String state;

    private State(String state) {

        this.state = state;
    }

    public String getState() {
        return state;
    }
}
