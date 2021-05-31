package model;

import java.time.LocalDate;

public class Booking {

    private int id;
    private static int contId;
    private int idRoom;
    private String idMainPassenger;
    private String idOptionalPassenger;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amountOfNights;
    private State state;
    private double totalCost;


    public Booking(int idRoom, String idMainPassenger, String idOptionalPassenger, LocalDate startDate, LocalDate endDate) {

        this.id = ++contId;
        this.idRoom = idRoom;
        this.idMainPassenger = idMainPassenger;
        this.idOptionalPassenger = idOptionalPassenger;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountOfNights = 0;
        this.state = State.ACTIVE;
        this.totalCost = 0;
    }

    public int getId() {
        return id;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public String getIdMainPassenger() {
        return idMainPassenger;
    }

    public String getIdOptionalPassenger() {
        return idOptionalPassenger;
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

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public void setIdMainPassenger(String idMainPassenger) {
        this.idMainPassenger = idMainPassenger;
    }

    public void setIdOptionalPassenger(String idOptionalPassenger) {
        this.idOptionalPassenger = idOptionalPassenger;
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

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "\n╔═  Booking ID: " + id + "   ══════════════════════════" + '\n' +
                "╠═ Room:" + idRoom + '\n' +
                "╠═ Main Passenger:" + idMainPassenger + '\n' +
                "╠═ Optional Passenger:" + idOptionalPassenger + '\n' +
                "╠═ Start Date:" + startDate + '\n' +
                "╠═ End Date:" + endDate + '\n' +
                "╠═ Amount Of Nights:" + amountOfNights + '\n' +
                "╠═ State:" + state + '\n' +
                "╠═ Total Cost:" + totalCost + '\n' +
                "╚══════════════════════════════════════\n";
    }
}
