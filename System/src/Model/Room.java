package Model;

public class Room {

    private int number;
    private static int contnumber = 100;
    private Category category;
    private Availability availability;

    public Room(Category category, Availability availability) {

        this.number = ++contnumber;
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
        return "╔═  Room Data   ══════════════════════════" + '\n' +
                "╠═ Number=" + number + '\n' +
                "╠═ Category=" + category + '\n' +
                "╠═ Availability=" + availability + '\n' +
                "╚══════════════════════════════════════";
    }
}
