package model;

public enum Category {

    GUEST("Guest", "Twin", 1, false, false, false, 1200),
    JUNIOR("Junior", "Twin", 2, false, false, true, 2800),
    PRESIDENTIAL("Presidential", "Queen", 1, true, false, true, 3500),
    EXECUTIVE("Executive", "King", 1, true, true, true, 4800);

    private String name;
    private String bedSize;
    private int numberOfBeds;
    private boolean hasPrivBathroom;
    private boolean hasFridge;
    private boolean hasTV;
    private int price;

    private Category(String name, String bedSize, int numberOfBeds, boolean hasPrivBathroom, boolean hasFridge, boolean hasTV, int price) {

        this.name = name;
        this.bedSize = bedSize;
        this.numberOfBeds = numberOfBeds;
        this.hasPrivBathroom = hasPrivBathroom;
        this.hasFridge = hasFridge;
        this.hasTV = hasTV;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBedSize() {
        return bedSize;
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

    public void setPrice(int price) {
        this.price = price;
    }
}
