package controller;

import model.*;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Hotel {

    private final UserRepository users;
    private final BookingRepository bookings;
    private final RoomRepository rooms;


    public Hotel() {
        this.users = new UserRepository();
        this.bookings = new BookingRepository();
        this.rooms = new RoomRepository();
        this.userHC();
        this.roomHC();
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

    public String changeFullName(String dni, String firstName, String lastName) {

        String message = "";
        List<Booking> activeBookings = getActiveBookingsByUser(dni);

        if (activeBookings.size() == 0) {


            User auxUser = this.users.search(dni);

            if (auxUser != null) {

                // TODO Check if this validation works. If not, try this ones and maybe they need modifications.
                // !ifNameContainsNumbers(firstName) || !ifNameContainsNumbers(lastName)
                // !firstName.matches("^[0-9]+$") || !lastName.matches("^[0-9]+$"
                if (!(firstName.matches(".*\\d.*") || !(lastName.matches(".*\\d.*")))) {

                    if (!firstName.isEmpty() || firstName.isBlank()) {

                        if (!lastName.isEmpty() || lastName.isBlank()) {

                            auxUser.setFirstName(firstName);
                            auxUser.setLastName(lastName);
                            auxUser = users.edit(auxUser);

                            message = "The changes were made successfully";
                        } else {

                            message = "You do not enter a valid last name. Please try again";
                        }
                    } else {

                        message = "You do not enter a valid first name. Please try again";
                    }
                } else {

                    message = "Your name can not contains numbers. Please try again";
                }
            } else {

                message = "User not found";
            }
        } else {

            message = "You can not change your name. There are active bookings";
        }

        return message;
    }

    // TODO Probably delete "ifNameContainsNumbers" method.
    public boolean ifStringContainsNumbers(String name) {

        if (name.contains("0") || name.contains("1") || name.contains("2")
                || name.contains("3") || name.contains("4") || name.contains("5")
                || name.contains("6") || name.contains("7") || name.contains("8")
                || name.contains("9")) {

            return true;
        } else {

            return false;
        }
    }

    public String changeAge(String dni, int age) {

        String message = "";
        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (age > 18) {

                auxUser.setAge(age);
                auxUser = users.edit(auxUser);

                message = "The changes were made successfully";

            } else {

                message = "Sorry but you need to be at least 18 to change your age";
            }
        } else {

            message = "User not found";
        }

        return message;
    }

    public String changeGender(String dni, Gender gender) {

        String message = "";
        User auxUser = users.search(dni);

        if (auxUser != null) {

            auxUser.setGender(gender);
            auxUser = users.edit(auxUser);

            message = "The changes were made successfully";
        } else {

            message = "User not found";
        }

        return message;
    }

    public String changeAddress(String dni, String address) {

        String message = "";
        User auxUser = users.search(dni);

        if (auxUser != null) {

            auxUser.setAddress(address);
            auxUser = users.edit(auxUser);

            message = "The changes were made successfully";
        } else {

            message = "User not found";
        }

        return message;
    }

    public String changeTelephone(String dni, String telephone) {

        String message = "";
        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (ifStringContainsLetters(telephone)) {

                message = "Not a valid phone number. Please try again";

            } else {

                auxUser.setTelephone(telephone);
                auxUser = users.edit(auxUser);

                message = "The changes were made successfully";
            }
        } else {

            message = "User not found";
        }

        return message;
    }

    public boolean ifStringContainsLetters(String telephone) {

        boolean flag = false;

        char[] chars = telephone.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            if (Character.isLetter(chars[i])) {

                flag = true;
            }
        }

        return flag;
    }

    public String changeEmail(String dni, String email) {

        String message = "";
        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (email.contains("@") && email.contains(".com")) {

                auxUser.setEmail(email);
                auxUser = users.edit(auxUser);

                message = "The changes were made successfully";

            } else {

                message = "Not a valid email. Please try again";
            }
        } else {

            message = "User not found";
        }

        return message;
    }

    public String changePassword(String dni, String password) {

        String message = "";
        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (password.length() > 3) {

                auxUser.setPassword(password);
                auxUser = users.edit(auxUser);

                message = "The changes were made successfully";
            } else {

                message = "Password too short. Please try again";
            }
        } else {

            message = "User not found";
        }

        return message;
    }

    public String deactivateAccount(String dni) {

        String message = "";
        List<Booking> activeBookings = getActiveBookingsByUser(dni);

        if (activeBookings.size() == 0) {


            User auxUser = this.users.search(dni);

            if (auxUser != null) {

                auxUser.setActive();
                auxUser = users.edit(auxUser);

                message = "Your account has been deactivated. To activate it again, please reach to one of our managers";
            } else {

                message = "User not found";
            }
        } else {

            message = "You can not deactivate your account. There are active bookings";
        }

        return message;
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
                .filter(receptionist -> receptionist.getActive())
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
    public String createBooking(int idRoom, String idMainPassenger, String idOptionalPassenger, LocalDate startDate, LocalDate endDate) {

        Booking booking = new Booking(idRoom, idMainPassenger, idOptionalPassenger, startDate, endDate);
        String message = "Booking created successfully";
        List<Booking> checkBookings = this.getActiveBookingsByRoomAndDate(booking.getStartDate(), booking.getEndDate(), booking.getIdRoom());
        if (checkBookings.isEmpty()) {
            Room aux_room = this.rooms.search(booking.getIdRoom());
            booking.setAmountOfNights((int) DAYS.between(booking.getStartDate(), booking.getEndDate()));
            booking.setTotalCost(aux_room.getCategory().getPrice() * booking.getAmountOfNights());
            this.bookings.add(booking);
        } else {
            message = "The room is not available for those dates. Please choose another room"; //TODO return message in Exception
        }
        return message;
    }

    public String deleteBooking(Integer idBooking) {

        String message = "";
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {
            if ((LocalDate.now().plusDays(2).isEqual(aux_booking.getStartDate()))
                    || ((LocalDate.now().plusDays(2).isBefore(aux_booking.getStartDate())))) {
                //also: if((int) DAYS.between(LocalDate.now(), aux_booking.getStartDate() <= 2)
                this.bookings.delete(idBooking);
                message = "Booking successfully deleted";

            } else {
                message = "Error. There should be at least 48hs before booking Start Date. You cannot delete this booking";
            }
        } else {
            message = "Booking not found";
        }
        return message;
    }

    public String cancelBooking(Integer idBooking) {

        // TODO Maybe needs changes.
        String message = "";
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {

            if ((LocalDate.now().plusDays(2).isEqual(aux_booking.getStartDate()))
                    || ((LocalDate.now().plusDays(2).isBefore(aux_booking.getStartDate())))) {
                //also: if((int) DAYS.between(LocalDate.now(), aux_booking.getStartDate() <= 2)
                aux_booking.setState(State.CANCELLED);
                aux_booking = this.bookings.edit(aux_booking);
                message = "Booking cancelled";
            } else {

                message = "Error. There should be at least 48hs before booking Start Date. You cannot cancel this booking";
            }
        } else {

            message = "Booking not found";
        }

        return message;
    }

    public String checkIn(String dniPassenger, Integer idBooking) {

        //TODO Maybe needs changes.
        String message = "";
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {
            if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {

                changeStateBooking(idBooking, State.CHECKED);
                aux_booking = this.bookings.edit(aux_booking);

                message = "Booking found. Passenger Checked In";

                Room aux_room = this.rooms.search(aux_booking.getIdRoom()); //change the availability of the current list of rooms
                changeRoomAvailability(aux_room.getNumber(), Availability.OCCUPIED);

                this.rooms.edit(aux_room);
            } else {
                message = "Passenger's ID and Booking's Id do not match";
            }
        } else {

            message = "Booking not found";
        }

        return message;
    }

    public String checkOut(String dniPassenger, Integer idBooking) {

        //TODO Maybe needs changes.
        String message = "";
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {
            if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {

                message = "Booking found. Passenger Checked Out";
                changeStateBooking(idBooking, State.CHECK_OUT);
                aux_booking = this.bookings.edit(aux_booking);

                Room aux_room = this.rooms.search(aux_booking.getIdRoom()); //change the availability of the current list of rooms
                changeRoomAvailability(aux_room.getNumber(), Availability.CLEANING);

                this.rooms.edit(aux_room);
            } else {
                message = "Passenger's ID and Booking's Id do not match";
            }
        } else {

            message = "Booking not found";
        }

        return message;
    }

    public String changeStateBooking(Integer idBooking, State state) {

        String message = "";
        Booking auxBooking = this.bookings.search(idBooking);

        if (auxBooking != null) {

            auxBooking.setState(state);
            bookings.edit(auxBooking);

            message = "Booking's state successfully changed";
        } else {

            message = "Booking not found";
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

    /***
     * StartDate or EndDate implies cancelling the actual booking and create a new one
     * @param idBooking
     * @param dni
     * @param newStartDate
     * @param newEndDate
     * @return
     */
    public String changeDates(Integer idBooking, String dni, LocalDate newStartDate, LocalDate newEndDate) {

        String message = "";
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {

            if (aux_booking.getIdMainPassenger().equals(dni)) {

                this.cancelBooking(idBooking);
                message = this.createBooking(aux_booking.getIdRoom(), aux_booking.getIdMainPassenger(), aux_booking.getIdOptionalPassenger(), newStartDate, newEndDate);
                this.bookings.edit(aux_booking);
            } else {

                message = "Passenger's ID and Booking's Id do not match";
            }
        } else {

            message = "Booking not found";
        }

        return message;
    }

    public String cancelAllBookingsByRoom(Integer idRoom) {

        String message = "";
        List<Booking> auxRoomBookings = getActiveBookingsByRoom(idRoom);

        if (auxRoomBookings != null) {

            for (Booking b : auxRoomBookings) {

                cancelBooking(b.getId());
            }

            message = "All bookings have been cancelled";
        } else {

            message = "Bookings not found";
        }

        return message;
    }

    public List<Booking> getCheckedBookings() {

        return getBookings().stream().filter(booking -> booking.getState().equals(State.CHECKED)).collect(Collectors.toList());
    }


    /***
     *
     * @param idRoom to filter
     * @return a list with all bookings with checked state for a room- double purpose, to verify that the system is working fine
     */
    public List<Booking> getCheckedBookingsByRoom(Integer idRoom) {

        return getCheckedBookings().stream().filter(booking -> booking.getIdRoom() == idRoom).collect(Collectors.toList());
    }


    /***
     * Checks only MainPassengerId
     * @param userDni to filter
     * @return list of all bookings that contains that DNI as main passenger ID
     */
    public List<Booking> getBookingsByUser(String userDni) {

        return getBookings()
                .stream()
                .filter(b -> b.getIdMainPassenger().equals(userDni))
                .collect(Collectors.toList());
    }

    /***
     * Checks only MainPassengerId and filters by state
     * @param userDni to filter
     * @return list of all bookings that contains that DNI as main passenger ID
     */
    public List<Booking> getActiveBookingsByUser(String userDni) {

        return getBookings()
                .stream()
                .filter(b -> b.getIdMainPassenger().equals(userDni))
                .filter(b -> b.getState().equals(State.ACTIVE))
                .collect(Collectors.toList());
    }

    public List<Booking> getBookingsByState(State state) {

        return getBookings()
                .stream()
                .filter(b -> b.getState().equals(state))
                .collect(Collectors.toList());
    }

    public List<Booking> getActiveBookingByPassenger(String dni) {

        return getBookings().stream()
                .filter(booking -> booking.getIdMainPassenger().equals(dni))
                .filter(booking -> booking.getState().equals(State.ACTIVE))
                .collect(Collectors.toList());
    }


    /**
     * @return List of all active bookings for a Specific room- to validate
     */
    public List<Booking> getActiveBookingsByRoom(Integer roomId) {

        return getBookings()
                .stream()
                .filter(b -> b.getIdRoom() == roomId)
                .filter(b -> (b.getState().equals(State.ACTIVE)))
                .collect(Collectors.toList());
    }

    /**
     * @return List of all active bookings for specific Room in a specific period of time- to validate
     */
    public List<Booking> getActiveBookingsByRoomAndDate(LocalDate startDate, LocalDate endDate, Integer idRoom) {

        // TODO Maybe need changes.
        return this.getActiveBookingsByRoom(idRoom)
                .stream()
                .filter(b -> ((b.getStartDate().isAfter(startDate)) && (b.getStartDate().isBefore(endDate))
                        || ((b.getEndDate().isAfter(startDate)) && (b.getEndDate().isBefore(endDate))
                        || (b.getStartDate().isEqual(startDate)) && (b.getEndDate().isEqual(endDate)))))
                .collect(Collectors.toList());
    }

    /**
     * @return List of all active bookings for specific Room in a specific period of time- to validate
     */
    public List<Booking> getActiveBookingsByDate(LocalDate startDate, LocalDate endDate) {

        return getBookings()
                .stream()
                .filter(b -> ((b.getStartDate().isAfter(startDate)) && (b.getEndDate().isBefore(endDate))))
                .filter(b -> (b.getState().equals(State.ACTIVE)))
                .collect(Collectors.toList());
    }


    // ╠═══════════════════════════════ Room Methods // 'ABML' order
    public String createRoom(Category category, Availability availability) {

        rooms.add(new Room(category, availability));

        return "Room created successfully";
    }

    public String deactivateRoom(int idRoom) {

        String message = "";
        List<Booking> checkBooking = getActiveBookingsByRoom(idRoom);

        if (checkBooking == null) {

            Room auxRoom = rooms.search(idRoom);
            if (auxRoom != null) {

                if (auxRoom.getAvailability() != Availability.OCCUPIED) {

                    changeRoomAvailability(idRoom, Availability.OUT_OF_SERVICE);
                    rooms.edit(auxRoom);

                    message = "Room successfully deactivated";
                } else {

                    message = "Room is occupied. Please, try later";
                }

            } else {

                message = "Room not found";
            }
        } else {

            message = "The room cannot be deactivated because there are current bookings on";
        }

        return message;
    }

    public String deleteRoom(int idRoom) {

        String message = "";
        List<Booking> checkBooking = getActiveBookingsByRoom(idRoom);

        if (checkBooking == null) {

            Room auxRoom = rooms.search(idRoom);
            if (auxRoom != null) {

                if (auxRoom.getAvailability() != Availability.OCCUPIED) {

                    rooms.delete(idRoom);

                    message = "Room successfully deleted";
                } else {

                    message = "Room is occupied. Please, try later";
                }

            } else {

                message = "Room not found";
            }
        } else {

            message = "The room cannot be deleted because there are current bookings on";
        }

        return message;
    }

    public String changeRoomCategory(int idRoom, Category category) {

        String message = "";
        List<Booking> checkBooking = getActiveBookingsByRoom(idRoom);

        if (checkBooking == null) {

            Room auxRoom = rooms.search(idRoom);
            if (auxRoom != null) {

                if (auxRoom.getAvailability() != Availability.OCCUPIED) {

                    auxRoom.setCategory(category);
                    rooms.edit(auxRoom);

                    message = "Room's category was changed";
                } else {

                    message = "Room is occupied. Please, try later";
                }

            } else {

                message = "Room not found";
            }
        } else {

            message = "The room cannot change Category. There are active booking related to this room.\nPlease, cancel them first";
        }

        return message;
    }

    public String changeRoomAvailability(int idRoom, Availability availability) {

        String message = "";
        Room auxRoom = rooms.search(idRoom);

        if (auxRoom != null) {

            auxRoom.setAvailability(availability);
            rooms.edit(auxRoom);

            message = "Room's availability was changed";
        } else {

            message = "Room not found";
        }

        return message;
    }

    public List<Room> getAvailableRooms(LocalDate startDate, LocalDate endDate) {

        List<Room> auxRooms = getRooms();
        List<Booking> activeBookings = getActiveBookingsByDate(startDate, endDate);

        for (Room r : getRooms()) {

            for (Booking b : activeBookings) {

                if (b.getIdRoom() == r.getNumber()) {

                    auxRooms.remove(r);
                }
            }
        }
        auxRooms = auxRooms.stream().filter(room -> room.getAvailability().equals(Availability.FREE)).collect(Collectors.toList());

        return auxRooms;
    }

    public boolean isRoomPresent(List<Room> roomList, int idRoom) {

        Room room = roomList.stream().filter(room1 -> room1.getNumber() == idRoom).findFirst().orElse(null);
        if (room != null) {

            return true;
        } else {

            return false;
        }
    }

    public List<Room> getAllFreeRooms(Availability availability) {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.FREE)).collect(Collectors.toList());
    }

    public List<Room> getAllOccupiedRooms(Availability availability) {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.OCCUPIED)).collect(Collectors.toList());
    }

    public List<Room> getAllCleaningRooms(Availability availability) {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.CLEANING)).collect(Collectors.toList());
    }

    public List<Room> getAllInDesinfectionRooms(Availability availability) {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.IN_DESINFECTION)).collect(Collectors.toList());
    }

    public List<Room> getAllUnderRepairRooms(Availability availability) {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.UNDER_REPAIR)).collect(Collectors.toList());
    }

    public List<Room> getAllOutOfServiceRooms(Availability availability) {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.OUT_OF_SERVICE)).collect(Collectors.toList());
    }

    public List<Room> getAllGuestRooms(Category category) {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.GUEST)).collect(Collectors.toList());
    }

    public List<Room> getAllJuniorRooms(Category category) {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.JUNIOR)).collect(Collectors.toList());
    }

    public List<Room> getAllPresidentialRooms(Category category) {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.PRESIDENTIAL)).collect(Collectors.toList());
    }

    public List<Room> getAllExecutiveRooms(Category category) {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.EXECUTIVE)).collect(Collectors.toList());
    }


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
        register(new Passenger("14874804"
                , "Andrea"
                , "Carrizo"
                , 60
                , Gender.FEMALE
                , "Gaboto 5646"
                , "4585858"
                , "something@asomething.com"
                , "pass123"
                , "Mar del Plata"));

        register(new Passenger("14589623"
                , "Miguel"
                , "Toyota"
                , 60
                , Gender.FEMALE
                , "Gaboto 5646"
                , "4585858"
                , "something@asomething.com"
                , "pass123"
                , "Mar del Plata"));
        deactivateAccount("14785972");

        register(new Passenger("38530953"
                , "Felipe"
                , "Graziano"
                , 25
                , Gender.MALE
                , "Mexico 3536"
                , "4651236"
                , "something@something.com"
                , "pass123"
                , "San Clemente"));

        register(new Receptionist("18956565"
                , "Sofia"
                , "Caceres"
                , 58
                , Gender.FEMALE
                , "Bahia Blanca 123"
                , "2235686968"
                , "soficaceres@gmail.com"
                , "sofi1966"
                , Shift.MORNING));

        register(new Receptionist("18956323"
                , "Matias"
                , "Palacios"
                , 58
                , Gender.MALE
                , "Chubut 123"
                , "2235686323"
                , "matias@gmail.com"
                , "mati1966"
                , Shift.AFTERNOON));
        deactivateAccount("18956323");

        register(new Receptionist("35236598"
                , "Santiago"
                , "Gonzalez"
                , 30
                , Gender.MALE
                , "Rio Negro 456"
                , "2235456985"
                , "santig@gmail.com"
                , "santi30"
                , Shift.NIGHT));

        register(new Manager("13525252"
                , "Calos"
                , "Patriarcado"
                , 61
                , Gender.MALE
                , "Patagones 6000"
                , "2235505065"
                , "carlos.patriarcado@gmail.com"
                , "patriarcado123"));
        deactivateAccount("13525252");

        register(new Manager("25525252"
                , "Natalia"
                , "Bossy"
                , 40
                , Gender.FEMALE
                , "San Luis 4052"
                , "2235235689"
                , "natybossy@gmail.com"
                , "bossy123"));
    }

    public void roomHC() {
        if (getRooms().isEmpty()) {
            createRoom(Category.EXECUTIVE, Availability.FREE);
            createRoom(Category.GUEST, Availability.FREE);
            createRoom(Category.JUNIOR, Availability.FREE);
            createRoom(Category.PRESIDENTIAL, Availability.FREE);
            createRoom(Category.GUEST, Availability.CLEANING);
            createRoom(Category.GUEST, Availability.OCCUPIED);
            createRoom(Category.GUEST, Availability.IN_DESINFECTION);
            createRoom(Category.GUEST, Availability.OUT_OF_SERVICE);
            createRoom(Category.EXECUTIVE, Availability.FREE);
            createRoom(Category.GUEST, Availability.FREE);
            createRoom(Category.JUNIOR, Availability.FREE);
            createRoom(Category.PRESIDENTIAL, Availability.FREE);
            createRoom(Category.JUNIOR, Availability.CLEANING);
            createRoom(Category.JUNIOR, Availability.OCCUPIED);
            createRoom(Category.JUNIOR, Availability.IN_DESINFECTION);
            createRoom(Category.JUNIOR, Availability.OUT_OF_SERVICE);
            createRoom(Category.EXECUTIVE, Availability.FREE);
            createRoom(Category.GUEST, Availability.FREE);
            createRoom(Category.JUNIOR, Availability.FREE);
            createRoom(Category.PRESIDENTIAL, Availability.FREE);
            createRoom(Category.PRESIDENTIAL, Availability.CLEANING);
            createRoom(Category.PRESIDENTIAL, Availability.OCCUPIED);
            createRoom(Category.PRESIDENTIAL, Availability.IN_DESINFECTION);
            createRoom(Category.PRESIDENTIAL, Availability.OUT_OF_SERVICE);
            createRoom(Category.EXECUTIVE, Availability.FREE);
            createRoom(Category.GUEST, Availability.FREE);
            createRoom(Category.JUNIOR, Availability.FREE);
            createRoom(Category.PRESIDENTIAL, Availability.FREE);
            createRoom(Category.EXECUTIVE, Availability.CLEANING);
            createRoom(Category.EXECUTIVE, Availability.OCCUPIED);
            createRoom(Category.EXECUTIVE, Availability.IN_DESINFECTION);
            createRoom(Category.EXECUTIVE, Availability.OUT_OF_SERVICE);
            createRoom(Category.EXECUTIVE, Availability.FREE);
            createRoom(Category.GUEST, Availability.FREE);
            createRoom(Category.JUNIOR, Availability.FREE);
            createRoom(Category.PRESIDENTIAL, Availability.FREE);
            createRoom(Category.EXECUTIVE, Availability.FREE);
            createRoom(Category.GUEST, Availability.FREE);
            createRoom(Category.JUNIOR, Availability.FREE);
            createRoom(Category.PRESIDENTIAL, Availability.FREE);
        }

    }

    public void bookingHC() {
        if (getBookings().isEmpty()) {
            createBooking(101, "38530953", "14874804", LocalDate.now(), LocalDate.now().plusDays(7));
            createBooking(101, "14589623", "14874804", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1).plusDays(7));
            createBooking(102, "14874804", "14589623", LocalDate.now().plusDays(5), LocalDate.now().plusDays(5).plusDays(7));
            createBooking(102, "14589623", "14874804", LocalDate.now().plusDays(20), LocalDate.now().plusDays(20).plusDays(7));
            createBooking(103, "14874804", "38530953", LocalDate.now(), LocalDate.now().plusDays(7));
            createBooking(103, "38530953", "14874804", LocalDate.now().plusDays(10), LocalDate.now().plusDays(10).plusDays(7));
            cancelBooking(3);
        }
    }


}
