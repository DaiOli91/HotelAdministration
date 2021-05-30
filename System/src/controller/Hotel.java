package controller;

import model.*;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;

import java.time.LocalDate;
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

    public List<Booking> getBookings() {
        return bookings.getRoomBookings();
    }

    public List<Room> getRooms() {
        return rooms.getRooms();
    }

    // ╔═══════════════════════════════ User Methods // 'ABML' order
    public boolean register(User user) {

        if (this.users.search(user.getDni()) == null) {

            return users.add(user);
        } else {

            return false;
        }
    }

    public boolean deleteUser(String dni) {
        return this.users.delete(dni);
    }

    public User changeFirstName(String dni, String firstName) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setFirstName(firstName);
        return this.users.edit(aux_user);
    }

    public User changeLastName(String dni, String lastName) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setLastName(lastName);
        return this.users.edit(aux_user);
    }

    public User changeAge(String dni, int age) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setAge(age);
        return this.users.edit(aux_user);
    }

    public User changeGender(String dni, Gender gender) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setGender(gender);
        return this.users.edit(aux_user);
    }

    public User changeAddress(String dni, String address) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setAddress(address);
        return this.users.edit(aux_user);
    }

    public User changeTelephone(String dni, String telephone) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setTelephone(telephone);
        return this.users.edit(aux_user);
    }

    public User changeEmail(String dni, String email) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setEmail(email);
        return this.users.edit(aux_user);
    }

    public User changePassword(String dni, String password) {
        boolean flag = false;
        User aux_user = this.users.search(dni);
        aux_user.setPassword(password);
        return this.users.edit(aux_user);
    }

    public User deactivateAccount(String dni) {
        boolean flag = false;
        User aux_user = (User) this.users.search(dni);
        if (aux_user != null) {
            aux_user.setActive();
            aux_user = users.edit(aux_user);
        }
        return aux_user;
    }

    public List<User> getUsers() {
        return users.getUsers();
    }

    public List<User> getActiveEmployees() {
        return users.getUsers()
                .stream()
                .filter(user -> user.getActive())
                .filter(user -> user instanceof Employee)
                .collect(Collectors.toList());
    }

    public List<User> getFormerEmployees() {
        return users.getUsers()
                .stream()
                .filter(user -> !user.getActive())
                .filter(user -> user instanceof Employee)
                .collect(Collectors.toList());
    }

    public List<User> getActiveReceptionists() {
        return users.getUsers()
                .stream()
                .filter(user -> user instanceof Receptionist)
                .filter(receptionist -> receptionist
                        .getActive())
                .collect(Collectors
                        .toList());
    }

    public List<User> getPassengers() {
        return users.getUsers()
                .stream()
                .filter(user -> user instanceof Passenger)
                .filter(user -> user.getActive())
                .collect(Collectors
                        .toList());
    }


    // ╠═══════════════════════════════ Booking Methods // 'ABML' order
    public String createBooking(Booking booking) {
        //TODO logic here to find available dates. Also set TotalCost and AmountOfNights in booking
        String message = "So far, booking created successfully";
        Room aux_room = this.rooms.search(booking.getIdRoom());
        booking.setTotalCost(aux_room.getCategory().getPrice() * booking.getAmountOfNights()); //TODO set approppriate price
        this.bookings.add(booking);
        return message;
    }

    public boolean deleteBooking(Integer idBooking) {
        //TODO logic not to cancel booking on the same day- at least 48hs
        return this.bookings.delete(idBooking);
    }

    public String cancelBooking(Integer idBooking) {
        //TODO same logic as deleteBooking method above
        String message = "Booking not found";
        Booking aux_booking = this.bookings.search(idBooking);
        if (aux_booking != null) {
            aux_booking.setState(State.CANCELLED);
            aux_booking = this.bookings.edit(aux_booking);
            message = "Booking cancelled successfully";
        }
        return message;
    }

    public String checkBooking(String dniPassenger, Integer idBooking) { //checkIn
        String message = "Booking not found";
        Booking aux_booking = this.bookings.search(idBooking);
        if (aux_booking != null) {
            if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {
                message = "Booking found. Passenger CheckedIn";
                aux_booking.setState(State.CHECKED);
                aux_booking = this.bookings.edit(aux_booking);
            } else {
                message = "Passenger's ID and Booking's Id do not match";
            }
        }

        return message;
    }

    public String changeRoomBooking(Integer idBooking, String dniPassenger, Integer roomId) {
        String message = "Booking not found";
        Booking aux_booking = this.bookings.search(idBooking);
        if (aux_booking != null) {
            if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {
                message = "Booking found. Room changed successfully.";
                aux_booking.setIdRoom(roomId);
                aux_booking = this.bookings.edit(aux_booking);
            } else {
                message = "Passenger's ID and Booking's Id do not match";
            }
        }

        return message;
    }

    public String changeIdMainPassenger(Integer idBooking, String dniPassenger, String idNewPassenger) {
        String message = "Booking not found";
        Booking aux_booking = this.bookings.search(idBooking);
        User aux_user = this.users.search(idNewPassenger);
        if (aux_user != null) {
            if (aux_booking != null) {
                if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {
                    message = "Booking found. Passenger Changed";
                    aux_booking.setIdMainPassenger(idNewPassenger);
                    aux_booking = this.bookings.edit(aux_booking);
                } else {
                    message = "Passenger's ID and Booking's Id do not match";
                }
            }
        } else {
            message = "User not found. Please, register the new Passenger first";
        }

        return message;
    }

    public String changeIdOptionalPassenger(Integer idBooking, String dniPassenger, String idNewPassenger) {
        String message = "Booking not found";
        Booking aux_booking = this.bookings.search(idBooking);
        User aux_user = this.users.search(idNewPassenger);
        if (aux_user != null) {
            if (aux_booking != null) {
                if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {
                    message = "Booking found. Passenger Changed";
                    aux_booking.setIdOptionalPassenger(idNewPassenger);
                    aux_booking = this.bookings.edit(aux_booking);
                } else {
                    message = "Passenger's ID and Booking's Id do not match";
                }
            }

        } else {
            message = "User not found. Please, register the new Passenger first";
        }
        return message;
    }

    public String changeDates(Booking booking) {
        //TODO edit StartDate or EndDate implies cancelling the actual booking and create a new one
        String message = "Booking not found";
        Booking aux_booking = this.bookings.search(booking.getId());
        if (aux_booking != null) {
            if ((aux_booking.getIdMainPassenger().equals(booking.getIdMainPassenger()))
                    || (aux_booking.getIdOptionalPassenger().equals(booking.getIdOptionalPassenger()))) {
                this.cancelBooking(booking.getId());
                message = this.createBooking(booking);
                this.bookings.edit(aux_booking);
            } else {
                message = "Passenger's ID and Booking's Id do not match";
            }
        }
        return message;

    }


    // ╠═══════════════════════════════ Room Methods // 'ABML' order
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
