package Controller;

import Model.*;
//import Model.Booking;

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
    //TODO Password should be 10 characters long
    public String register(User user) {
        String message = "User has been added successfully";
        if (this.users.isEmpty()) {
            this.addSuperAdmin();
            this.users.add(user);
        } else if (this.users.contains(user)) {
            message = "User already exists";
        } else this.users.add(user);

        return message;
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

    public String logIn(User user) {
        //TODO fix log in
        String message = "User not found. Please, try again or register.";
        if (!this.users.isEmpty()) {
            for (User aux_user : users
            ) {
                if ((aux_user.getDni().equals(user.getDni()))
                        && (aux_user.getPassword().equals(user.getPassword()))) {
                    message = "Welcome, " + user.getFirstName();
                    if (user instanceof Manager) {
                        message.concat(this.menuManager());
                    } else if (user instanceof Receptionist) {
                        message.concat(this.menuReceptionist());
                    } else message.concat(this.menuPassenger());
                }

            }

        }
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
}
