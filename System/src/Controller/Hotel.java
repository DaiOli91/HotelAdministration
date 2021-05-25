package Controller;

import Model.*;
import Repository.BookingRepository;
import Repository.RoomRepository;
import Repository.UserRepository;
//import Model.Booking;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private UserRepository users;
    private BookingRepository bookings;
    private RoomRepository rooms;



    public Hotel() {
        this.users = new UserRepository();
        this.bookings = new BookingRepository();
        this.rooms = new RoomRepository();
        this.users.userHC();

    }

    public List<User> getUsers() {
        return users.getUsers();
    }
    public String getUsersData() {
        return users.getUsersData();
    }

    public List<Booking> getBookings() {
        return bookings.getRoomBookings();
    }

    public String getBookingsData() {
        return bookings.getRoomBookingsData();
    }

    public List<Room> getRooms() {
        return rooms.getRooms();
    }

    public String getRoomsData() {
        return rooms.getRoomsData();
    }

    public String logInHotel(String DNI, String pass){
        String message = "User not found. Please, try again or register.";
        User aux_user = new Passenger(DNI, pass);
        aux_user = this.users.logIn(aux_user);
        if (aux_user != null) {
            message = "Welcome, " + aux_user.getFirstName();
            if (aux_user instanceof Manager) {
                message.concat(this.menuManager());
            } else if (aux_user instanceof Receptionist) {
                message.concat(this.menuReceptionist());
            } else message.concat(this.menuPassenger());
        }
        return message;
    }

    public String register(User user){
        return this.users.add(user);
    }



    // ╠═══════════════════════════════ Administrative Methods
    // ╚═══════════════════════════════ Management Methods

    //TODO menuManager
    public String menuManager() {
        //insert code here
        return "Manager Menu";
    }

    //TODO menuReceptionist
    public String menuReceptionist() {
        //insert code here
        return "Receptionist Menu";
    }

    //TODO menuPassenger
    public String menuPassenger() {
        //insert code here
        return "Passenger Menu";
    }

}
