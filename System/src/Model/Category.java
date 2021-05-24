package Model;

public enum Category {

    GUEST("Guest", "Twin", 1, false, false, false, 1200),
    Junior("Junior", "Twin", 2, false, false, true, 2800),
    Presidential("Presidential", "Queen", 1, true, false, true, 3500),
    Executive("Executive", "King", 1, true, true, true, 4800);

    private String name;
    private String bedType;
    private int numberOfBeds;
    private boolean hasPrivBathroom;
    private boolean hasFridge;
    private boolean hasTV;
    private int price;

    private Category(String name, String bedType, int numberOfBeds, boolean hasPrivBathroom, boolean hasFridge, boolean hasTV, int price) {

        this.name = name;
        this.bedType = bedType;
        this.numberOfBeds = numberOfBeds;
        this.hasPrivBathroom = hasPrivBathroom;
        this.hasFridge = hasFridge;
        this.hasTV = hasTV;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBedType() {
        return bedType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public boolean isHasPrivBathroom() {
        return hasPrivBathroom;
    }

    public boolean isHasFridge() {
        return hasFridge;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public int getPrice() {
        return price;
    }
}
