package model;

public enum Availability {

    FREE("Free"),
    OCCUPIED("Occupied"),
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
