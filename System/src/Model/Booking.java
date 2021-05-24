package Model;

import java.time.LocalDate;

public class Booking {

    private int id;
    private static int contId;
    private Room room;
    private Passenger mainPassenger;
    private Passenger optionalPassenger;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amountOfNights;
    private State state;
    private double totalCost;


    public Booking(Room room, Passenger mainPassenger, Passenger optionalPassenger, LocalDate startDate, LocalDate endDate, int amountOfNights, State state, double totalCost) {

        this.id = ++contId;
        this.room = room;
        this.mainPassenger = mainPassenger;
        this.optionalPassenger = optionalPassenger;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountOfNights = amountOfNights;
        this.state = state;
        setTotalCost();
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Passenger getMainPassenger() {
        return mainPassenger;
    }

    public Passenger getOptionalPassenger() {
        return optionalPassenger;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getAmountOfNights() {
        return amountOfNights;
    }

    public State getState() {
        return state;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setMainPassenger(Passenger mainPassenger) {
        this.mainPassenger = mainPassenger;
    }

    public void setOptionalPassenger(Passenger optionalPassenger) {
        this.optionalPassenger = optionalPassenger;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setAmountOfNights(int amountOfNights) {
        this.amountOfNights = amountOfNights;
    }

    public void setState(State state) {
        this.state = state;
    }

    private void setTotalCost() {
        this.totalCost = room.getCategory().getPrice() * getAmountOfNights();
    }
}
