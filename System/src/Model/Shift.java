package Model;

public enum Shift {

    MORNING("Morning", "05:00am - 13:00pm"),
    AFTERNOON("Afternoon", "13:00pm - 21:00pm"),
    NIGHT("Night", "21:00pm - 05:00am");

    private String shift;
    private String description;

    private Shift(String shift, String description) {

        this.shift = shift;
        this.description = description;
    }

    public String getShift() {
        return shift;
    }

    public String getDescription() {
        return description;
    }
}
