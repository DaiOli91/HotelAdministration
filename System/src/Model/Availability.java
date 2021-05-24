package Model;

public enum Availability {

    FREE("Free"),
    CLEANING("Cleaning"),
    IN_DESINFECTION("In Desinfection"),
    UNDER_REPAIR("Under Repair");

    private String availability;

    private Availability(String availability) {

        this.availability = availability;
    }

    public String getAvailability() {
        return availability;
    }
}
