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
        this.userHC();

    }

    public List<User> getUsers() {
        return users.getUsers();
    }
    public String getUsersData() {
        return users.getUsersData();
    }

    public UserRepository getUserRepo(){
        return users;
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
/*
    public String logInHotel(String DNI, String pass){
        String message = "User not found. Please, try again or register.";

        return message;
    }
*/
    public String register(User user){
        return this.users.add(user);
    }

    public void addSuperAdmin() {
        User superAdmin = new Manager("11111111"
                , "Super"
                , "Admin"
                , 100
                , Gender.FEMALE
                , "Something 123"
                , "111111111"
                , "superadmin@mail.com"
                , "superadmin123");

        this.users.add(superAdmin);
    }

    public void userHC() {
        addSuperAdmin();
        this.users.add(new Passenger("14785969"
                , "Andrea"
                , "Carrizo"
                , 60
                , Gender.FEMALE
                , "Gaboto 5646"
                , "4585858"
                , "something@asomething.com"
                , "pass123"
                , "Mar del Plata"));

        this.users.add(new Passenger("14874623"
                , "Felipe"
                , "Graziano"
                , 60
                , Gender.MALE
                , "Mexico 3536"
                , "4651236"
                , "something@something.com"
                , "pass123"
                , "San Clemente"));

        this.users.add(new Receptionist("18956565"
                , "Sofia"
                , "Caceres"
                , 58
                , Gender.FEMALE
                , "Bahia Blanca 123"
                , "2235686968"
                , "soficaceres@gmail.com"
                , "sofi1966"
                , Shift.MORNING));

        this.users.add(new Receptionist("35236598"
                , "Santiago"
                , "Gonzalez"
                , 30
                , Gender.MALE
                , "Rio Negro 456"
                , "2235456985"
                , "santig@gmail.com"
                , "santi30"
                , Shift.NIGHT));

        this.users.add(new Manager("13525252"
                , "Calos"
                , "Patriarcado"
                , 61
                , Gender.MALE
                , "Patagones 6000"
                , "2235505065"
                , "carlos.patriarcado@gmail.com"
                , "patriarcado123"));

        this.users.add(new Manager("25525252"
                , "Natalia"
                , "Bossy"
                , 40
                , Gender.FEMALE
                , "San Luis 4052"
                , "2235235689"
                , "natybossy@gmail.com"
                , "bossy123"));

    }

    public String add(User user) {
        String message = "User has been added successfully";
        if (this.users.getUsers().isEmpty()) {
            this.addSuperAdmin();
            this.users.add(user);
        } else if (this.users.getUsers().contains(user)) {
            message = "User already exists";
        } else this.users.add(user);

        return message;
    }

    public String addEmployee(Employee employee){
        String message = "User has been added successfully";
        if (this.users.getUsers().isEmpty()) {
            this.addSuperAdmin();
            this.users.add(employee);
        } else if (this.users.getUsers().contains(employee)) {
            message = "Employee already exists";
        } else this.users.add(employee);

        return message;
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
