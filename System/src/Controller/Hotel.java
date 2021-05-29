package Controller;

import Model.*;
import Repository.BookingRepository;
import Repository.RoomRepository;
import Repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hotel {
    private final UserRepository users;
    private final BookingRepository bookings;
    private final RoomRepository rooms;



    public Hotel() {
        this.users = new UserRepository();
        this.bookings = new BookingRepository();
        this.rooms = new RoomRepository();
        this.userHC();

    }

    public List<User> getUsers() {
        return users.getUsers();
    }

    public List<Booking> getBookings() {
        return bookings.getRoomBookings();
    }

    public List<Room> getRooms() {
        return rooms.getRooms();
    }



    public List<User> getActiveEmployees(){
        return users.getUsers()
                .stream()
                .filter(user -> user.getActive())
                .filter(user -> user instanceof Employee)
                .collect(Collectors.toList());
    }

    public List<User> getFormerEmployees(){
        return users.getUsers()
                .stream()
                .filter(user -> !user.getActive())
                .filter(user -> user instanceof Employee)
                .collect(Collectors.toList());
    }

    public List<User> getActiveReceptionists(){
        return users.getUsers()
                .stream()
                .filter(user -> user instanceof Receptionist)
                .filter(receptionist -> receptionist
                        .getActive())
                .collect(Collectors
                        .toList());
    }
    public List<User> getPassengers(){
        return users.getUsers()
                .stream()
                .filter(user -> user instanceof Passenger)
                .filter(user -> user.getActive())
                .collect(Collectors
                        .toList());
    }

    // ╔═══════════════════════════════ User Methods
    public void register(User user){
        if(this.users.search(user.getDni()) == null){
            this.users.add(user);
        }
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

    public void deactivateAccount(String dni){
        User aux_user = (User) this.users.getUsers().stream().filter(user -> user.getDni().equals(dni));
        if(aux_user != null){
            aux_user.setActive();
            users.edit(aux_user);
        }
    }

    // ╠═══════════════════════════════ Booking Methods
    // ╠═══════════════════════════════ Room Methods
    // ╚═══════════════════════════════ HC Methods
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


}
