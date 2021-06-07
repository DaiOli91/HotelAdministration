package model;

public class Room {

    private int number;
    private Category category;
    private Availability availability;

    public Room(int number, Category category, Availability availability) {
        this.number = number;
        this.category = category;
        this.availability = availability;
    }

    public int getNumber() {
        return number;
    }

    public Category getCategory() {
        return category;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "\n╔═  Room Number: " + number + "   ══════════════════════════" + '\n' +
                "╠═ Category:" + category.toString() + '\n' +
                "╠═ Availability:" + availability.getAvailability() + '\n' +
                "╚══════════════════════════════════════\n";
    }
}
