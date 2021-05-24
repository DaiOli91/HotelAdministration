package Controller;

import Model.Booking;
import Model.User;
//import Model.Booking;
import Model.Room;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<User> users;
    private List<Booking> roomBookings;
    private List<Room> rooms;

    public Hotel() {
        this.users = new ArrayList<>();
        this.roomBookings = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Booking> getRoomBookings() {
        return roomBookings;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getUsersData() {
        return users.toString();
    }

    public String getRoomBookingsData() {
        return roomBookings.toString();
    }

    public String getRoomsData() {
        return rooms.toString();
    }
    // ╔═══════════════════════════════ End User Methods
    // ╠═══════════════════════════════ Administrative Methods
    // ╚═══════════════════════════════ Management Methods
}
