package Model;

public enum Shift {

    MORNING("Morning"),
    AFTERNOON("Afternoon"),
    NIGHT("Night");

    private String shift;

    private Shift(String shift) {

        this.shift = shift;
    }
}
