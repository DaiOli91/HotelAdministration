package controller;

import exception.*;
import model.*;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Hotel {

    private final UserRepository users;
    private final BookingRepository bookings;
    private final RoomRepository rooms;
    private final String name;
    private final String address;
    private final String telephone;


    public Hotel(String name, String address, String telephone) throws IOException {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.users = new UserRepository();
        this.bookings = new BookingRepository();
        this.rooms = new RoomRepository();
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

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void loadData() throws IOException, DateValidationException, BookingNotFoundException {
        users.readGson();
        if (users.getUsers().size() == 0) {
            this.userHC();
        } //to make sure that the list always has data

        bookings.readGson();
        if (this.bookings.getRoomBookings().size() == 0) {
            this.bookingHC();
        }

        rooms.readGson();
        if (this.rooms.getRooms().size() == 0) {
            this.roomHC();
        }
    }

    public void saveData() throws IOException {
        if (this.getUsers().size() > 0) {
            users.writeGson();
        }
        if (this.getBookings().size() > 0) {
            bookings.writeGson();
        }
        if (this.getRooms().size() > 0) {
            rooms.writeGson();
        }
    }

    // ╠═══════════════════════════════ User Methods // 'ABML' order ═══════════════════════════════╣
    public void register(User user) throws IOException, UserAlreadyRegisteredException, DateValidationException, BookingNotFoundException {

        if (users.getUsers().isEmpty()) {

            this.loadData();
        }
        if (this.users.search(user.getDni()) == null) {

            users.add(user);
            this.saveData();

        } else {

            throw new UserAlreadyRegisteredException();
        }

    }

    public void activateAccount(String dni) throws UserActiveDeactiveException, UserNotFoundException, ReceptionistShiftNeedsChange {

        User auxUser = this.users.search(dni);

        if (auxUser != null) {

            if (!auxUser.getActive()) {

                auxUser.setActive();
                users.edit(auxUser);
                if (auxUser instanceof Receptionist) {

                    throw new ReceptionistShiftNeedsChange();
                }
                try {
                    saveData();
                } catch (IOException e) {
                    System.out.println("\n" + e.getMessage() + "\n");
                }

            } else {

                throw new UserActiveDeactiveException("User account is already Active");
            }
        } else {

            throw new UserNotFoundException();
        }
    }

    public void deactivateAccount(String dni) throws ActiveBookingException, UserNotFoundException, UserActiveDeactiveException {

        User auxUser = this.users.search(dni);
        if (auxUser != null) {

            List<Booking> activeBookings = getActiveBookingsByUser(dni);
            if (activeBookings.size() == 0) {

                if (auxUser.getActive()) {

                    if (auxUser instanceof Receptionist) {

                        ((Receptionist) auxUser).setShift(Shift.UNASIGNED);
                    }
                    auxUser.setActive();
                    users.edit(auxUser);
                    try {
                        saveData();
                    } catch (IOException e) {
                        System.out.println("\n" + e.getMessage() + "\n");
                    }

                } else {

                    throw new UserActiveDeactiveException("User account is already Deactive");
                }
            } else {

                throw new ActiveBookingException();
            }
        } else {

            throw new UserNotFoundException();
        }
    }

    public User searchUserById(String idUser) {
        return users.search(idUser);
    }

    public void changeFullName(String dni, String firstName, String lastName) throws UserNotFoundException, InvalidStringException, ActiveBookingException {

        List<Booking> activeBookings = getActiveBookingsByUser(dni);

        if (activeBookings.size() == 0) {

            User auxUser = this.users.search(dni);
            if (auxUser != null) {

                //!(firstName.matches(".*\\d.*") || !(lastName.matches(".*\\d.*")))
                if (!ifStringContainsDigits(firstName, lastName)) {

                    if (!firstName.isEmpty() || firstName.isBlank()) {

                        if (!lastName.isEmpty() || lastName.isBlank()) {

                            auxUser.setFirstName(firstName);
                            auxUser.setLastName(lastName);
                            users.edit(auxUser);

                        } else {

                            throw new InvalidStringException("You do not enter a valid last name. Please try again");
                        }
                    } else {

                        throw new InvalidStringException("You do not enter a valid first name. Please try again");
                    }
                } else {

                    throw new InvalidStringException("Your name can not contains numbers, spaces or special characters. Please try again");
                }
            } else {

                throw new UserNotFoundException();
            }
        } else {

            throw new ActiveBookingException();
        }
    }

    public boolean ifStringContainsDigits(String firstName, String lastName) {

        boolean flag = false;

        char[] charsFirstName = firstName.toCharArray();
        char[] charsLastName = lastName.toCharArray();

        for (int i = 0; i < charsFirstName.length && !flag; i++) {

            if (Character.isDigit(charsFirstName[i])) {

                flag = true;
            }
        }
        for (int i = 0; i < charsLastName.length && !flag; i++) {

            if (Character.isDigit(charsLastName[i])) {

                flag = true;
            }
        }

        return flag;
    }

    public void changeAge(String dni, int age) throws UserNotFoundException, InvalidNumberValidationException {

        String message;
        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (age > 18) {

                if (age <= 100) {

                    auxUser.setAge(age);
                    users.edit(auxUser);

                } else {

                    throw new InvalidNumberValidationException("Sorry but the maximum allowed age is 100");
                }
            } else {
                throw new InvalidNumberValidationException("Sorry but you need to be at least 18 to change your age");
            }
        } else {

            throw new UserNotFoundException();
        }

    }

    public void changeGender(String dni, Gender gender) throws UserNotFoundException {

        String message;
        User auxUser = users.search(dni);

        if (auxUser != null) {

            auxUser.setGender(gender);
            users.edit(auxUser);

        } else {

            throw new UserNotFoundException();
        }
    }

    public void changeAddress(String dni, String address) throws UserNotFoundException {

        User auxUser = users.search(dni);

        if (auxUser != null) {

            auxUser.setAddress(address);
            users.edit(auxUser);

        } else {

            throw new UserNotFoundException();
        }

    }

    public void changeTelephone(String dni, String telephone) throws UserNotFoundException, InvalidStringException {

        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (ifStringContainsLetters(telephone)) {

                throw new InvalidStringException("Not a valid phone number. Please try again");

            } else {

                auxUser.setTelephone(telephone);
                users.edit(auxUser);

            }
        } else {

            throw new UserNotFoundException();
        }

    }

    // TODO Check link Orellano sent.
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

    public void changeEmail(String dni, String email) throws UserNotFoundException, InvalidStringException {

        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (email.contains("@") && email.contains(".com")) {

                auxUser.setEmail(email);
                users.edit(auxUser);

            } else {

                throw new InvalidStringException("Not a valid email. Please try again");
            }
        } else {

            throw new UserNotFoundException();
        }
    }

    public void changePassword(String dni, String password) throws UserNotFoundException, InvalidStringException {

        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (password.length() > 3) {

                auxUser.setPassword(password);
                users.edit(auxUser);

            } else {

                throw new InvalidStringException("Password too short. Please try again");
            }
        } else {

            throw new UserNotFoundException();
        }

    }

    public void changeReceptionistShift(String dni, Shift shift) throws UserNotFoundException {

        User auxRecep = users.search(dni);

        if (auxRecep != null) {

            if (auxRecep instanceof Receptionist) {

                ((Receptionist) auxRecep).setShift(shift);
                users.edit(auxRecep);
            }
        } else {

            throw new UserNotFoundException();
        }

    }

    public List<User> getAllEmployees() {

        return users.getUsers()
                .stream()
                .filter(user -> user instanceof Employee)
                .collect(Collectors.toList());
    }

    public List<User> getActiveManagers() {

        return users.getUsers()
                .stream()
                .filter(user -> user.getActive())
                .filter(user -> user instanceof Manager)
                .collect(Collectors.toList());
    }

    public List<User> getActiveReceptionists() {

        return users.getUsers()
                .stream()
                .filter(receptionist -> receptionist.getActive())
                .filter(user -> user instanceof Receptionist)
                .collect(Collectors
                        .toList());
    }

    public List<User> getFormerEmployees() {

        return users.getUsers()
                .stream()
                .filter(user -> !user.getActive())
                .filter(user -> user instanceof Employee)
                .collect(Collectors.toList());
    }

    public List<User> getPassengers() {

        return users.getUsers()
                .stream()
                .filter(user -> user instanceof Passenger)
                .collect(Collectors.toList());
    }

    public List<User> getActivePassengers() {

        return users.getUsers()
                .stream()
                .filter(user -> user.getActive())
                .filter(user -> user instanceof Passenger)
                .collect(Collectors.toList());
    }

    public List<User> getFormerPassengers() {

        return users.getUsers()
                .stream()
                .filter(user -> !user.getActive())
                .filter(user -> user instanceof Passenger)
                .collect(Collectors.toList());
    }

    public User getUserByID(String idUser) {

        return users.getUsers()
                .stream()
                .filter(user -> user.getDni().equals(idUser))
                .findFirst().orElse(null);
    }

    public User getUserByFullName(String firstName, String lastName) {

        return users.getUsers()
                .stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null);
    }


    // ╠═══════════════════════════════ Booking Methods // 'ABML' order
    public void createBooking(int idRoom, String idMainPassenger, String idOptionalPassenger, LocalDate startDate, LocalDate endDate) throws UnavailableRoomException, DateValidationException {

        if (ChronoUnit.DAYS.between(startDate, endDate) >= 7) {

            int lastBookingId = getLastBookingId();
            Booking booking = new Booking(lastBookingId + 1, idRoom, idMainPassenger, idOptionalPassenger, startDate, endDate);
            List<Booking> checkBookings = this.getActiveBookingsByRoomAndDate(booking.getStartDate(), booking.getEndDate(), booking.getIdRoom());
            if (checkBookings.isEmpty()) {

                Room aux_room = this.rooms.search(booking.getIdRoom());
                booking.setAmountOfNights((int) DAYS.between(booking.getStartDate(), booking.getEndDate()));
                booking.setTotalCost(aux_room.getCategory().getPrice() * booking.getAmountOfNights());
                this.bookings.add(booking);
            } else {
                throw new UnavailableRoomException("The room is not available for those dates. Please choose another room");
            }
        } else {

            throw new DateValidationException("Booking should be at least seven days long. Please, try again.");
        }
    }

    public void cancelBooking(Integer idBooking) throws BookingNotFoundException, DateValidationException {

        // TODO Maybe needs changes.
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {

            if ((int) DAYS.between(LocalDate.now(), aux_booking.getStartDate()) >= 2) {
                // (int) DAYS.between(LocalDate.now(), aux_booking.getStartDate() >= 2)
                // (LocalDate.now().plusDays(2).isEqual(aux_booking.getStartDate())) || ((LocalDate.now().plusDays(2).isBefore(aux_booking.getStartDate())))
                aux_booking.setState(State.CANCELLED);
                this.bookings.edit(aux_booking);
            } else {

                throw new DateValidationException("Error. There should be at least 48hs before booking Start Date. You cannot cancel this booking");
            }
        } else {

            throw new BookingNotFoundException();
        }
    }

    // TODO Need to ask Orellano about this.
    public String deleteBooking(Integer idBooking) {

        String message;
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

    public void checkIn(String dniPassenger, Integer idBooking) throws InvalidStringException, BookingNotFoundException {

        //TODO Maybe needs changes.
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {
            if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {

                changeStateBooking(idBooking, State.CHECKED);
                this.bookings.edit(aux_booking);

                Room aux_room = this.rooms.search(aux_booking.getIdRoom()); //change the availability of the current list of rooms
                changeRoomAvailability(aux_room.getNumber(), Availability.OCCUPIED);

                this.rooms.edit(aux_room);
            } else {
                throw new InvalidStringException("Passenger's ID and Booking's Id do not match");
            }
        } else {

            throw new BookingNotFoundException();
        }
    }

    public void checkOut(String dniPassenger, Integer idBooking) throws InvalidStringException, BookingNotFoundException {

        //TODO Maybe needs changes.
        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {
            if ((aux_booking.getIdMainPassenger().equals(dniPassenger)) || (aux_booking.getIdOptionalPassenger().equals(dniPassenger))) {
                changeStateBooking(idBooking, State.CHECK_OUT);
                this.bookings.edit(aux_booking);

                Room aux_room = this.rooms.search(aux_booking.getIdRoom()); //change the availability of the current list of rooms
                changeRoomAvailability(aux_room.getNumber(), Availability.CLEANING);

                this.rooms.edit(aux_room);
            } else {
                throw new InvalidStringException("Passenger's ID and Booking's Id do not match");
            }
        } else {

            throw new BookingNotFoundException();
        }
    }

    public void changeStateBooking(Integer idBooking, State state) throws BookingNotFoundException {

        Booking auxBooking = this.bookings.search(idBooking);

        if (auxBooking != null) {

            auxBooking.setState(state);
            bookings.edit(auxBooking);
        } else {

            throw new BookingNotFoundException();
        }
    }

    //TODO if implemented, possible success message for the main> "All bookings have been cancelled";
    public void cancelAllBookingsByRoom(Integer idRoom) throws DateValidationException, BookingNotFoundException {

        List<Booking> auxRoomBookings = getActiveBookingsByRoom(idRoom);

        if (auxRoomBookings != null) {

            for (Booking b : auxRoomBookings) {

                cancelBooking(b.getId());
            }
        } else {

            throw new BookingNotFoundException();
        }
    }

    //TODO Get Orellano's approval
    public int getLastBookingId() {
        Optional<Booking> auxBooking = getBookings().stream().max(Comparator.comparing(booking -> booking.getId()));
        if (!auxBooking.isEmpty() && auxBooking != null) {
            return auxBooking.get().getId();
        } else {
            return 0;
        }

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
        return this.getActiveBookingsByDate(startDate, endDate)
                .stream()
                .filter(b -> b.getIdRoom() == idRoom)
                .collect(Collectors.toList());
    }

    /**
     * @return List of all active bookings for specific Room in a specific period of time- to validate
     */
    public List<Booking> getActiveBookingsByDate(LocalDate startPeriod, LocalDate endPeriod) {

        return getBookings()
                .stream()
                .filter(b -> b.getState().equals(State.ACTIVE))
                .filter(b -> !b.getEndDate().isBefore(startPeriod))
                .filter(b -> (!b.getStartDate().isAfter(endPeriod)))
                .collect(Collectors.toList());
    }


    // ╠═══════════════════════════════ Room Methods // 'ABML' order ═══════════════════════════════╣
    public String createRoom(Category category, Availability availability) {
        int lastRoomNumber = getLastRoomNumber();

        rooms.add(new Room(lastRoomNumber + 1, category, availability));

        return "Room created successfully";
    }

    public String activateRoom(int idRoom) throws RoomNotFoundException {

        String message = "";
        Room auxRoom = this.rooms.search(idRoom);

        if (auxRoom != null) {

            if (auxRoom.getAvailability() == Availability.OUT_OF_SERVICE) {

                auxRoom.setAvailability(Availability.CLEANING);
                rooms.edit(auxRoom);

                message = "The room was activated successfully. Needs to be cleaned";
            } else {

                message = "Room is already active";
            }
        } else {

            throw new RoomNotFoundException();
        }

        return message;
    }

    public String deactivateRoom(int idRoom) throws RoomNotFoundException {

        String message;
        Room auxRoom = rooms.search(idRoom);

        if (auxRoom != null) {

            List<Booking> checkActiveBookings = getActiveBookingsByRoom(idRoom);
            if (checkActiveBookings.size() == 0) {

                if (auxRoom.getAvailability() != Availability.OCCUPIED) {

                    if (auxRoom.getAvailability() != Availability.OUT_OF_SERVICE) {

                        changeRoomAvailability(idRoom, Availability.OUT_OF_SERVICE);
                        rooms.edit(auxRoom);

                        message = "Room successfully deactivated";
                    } else {

                        message = "Room already deactivated";
                    }
                } else {

                    message = "Room is occupied. Please, try later";
                }

            } else {

                message = "The room cannot be deactivated because there are current bookings on";
            }
        } else {

            throw new RoomNotFoundException();
        }

        return message;
    }

    public String changeRoomCategory(int idRoom, Category category) throws RoomNotFoundException {

        String message;
        Room auxRoom = rooms.search(idRoom);

        if (auxRoom != null) {

            List<Booking> checkBooking = getActiveBookingsByRoom(idRoom);
            if (checkBooking.size() == 0) {

                if (auxRoom.getAvailability() != Availability.OCCUPIED) {

                    auxRoom.setCategory(category);
                    rooms.edit(auxRoom);

                    message = "Room's category was changed";
                } else {

                    message = "Room is occupied. Please, try later";
                }

            } else {

                message = "The room cannot change Category. There are active booking related to this room.\nPlease, cancel them first";
            }
        } else {

            throw new RoomNotFoundException();
        }

        return message;
    }

    public String changeRoomAvailability(int idRoom, Availability availability) {

        String message;
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

    //TODO Get Orellano's approval
    public int getLastRoomNumber() {
        Optional<Room> auxRoom = getRooms().stream().max(Comparator.comparing(room -> room.getNumber()));

        if (!auxRoom.isEmpty() && auxRoom != null) {
            return auxRoom.get().getNumber();
        } else {
            return 100;
        }

    }

    public List<Room> getAvailableRooms(LocalDate startDate, LocalDate endDate) {

        List<Room> auxRooms = getRooms();
        List<Booking> activeBookings = getActiveBookingsByDate(startDate, endDate);
        for (int r = 0; r < auxRooms.size(); r++) {

            for (int b = 0; b < activeBookings.size(); b++) {

                if (activeBookings.get(b).getIdRoom() == auxRooms.get(r).getNumber()) {

                    auxRooms.remove(r);
                }
            }
        }
        auxRooms = auxRooms.stream().filter(room -> !room.getAvailability().equals(Availability.OUT_OF_SERVICE)).collect(Collectors.toList());

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

    public void userHC() throws IOException {
        try {
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
            try {
                deactivateAccount("14785972");
            } catch (ActiveBookingException e) {
                e.printStackTrace();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            } catch (UserActiveDeactiveException userActiveDeactiveException) {
                userActiveDeactiveException.printStackTrace();
            }

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
            try {
                deactivateAccount("18956323");
            } catch (ActiveBookingException e) {
                e.printStackTrace();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            } catch (UserActiveDeactiveException userActiveDeactiveException) {
                userActiveDeactiveException.printStackTrace();
            }

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

            register(new Manager("35892293"
                    , "Daiana"
                    , "Olivera"
                    , 30
                    , Gender.FEMALE
                    , "San Luis 4052"
                    , "2235506592"
                    , "daiolivera@gmail.com"
                    , "pass123"));

            register(new Manager("39098436"
                    , "Walter"
                    , "Moretti"
                    , 25
                    , Gender.MALE
                    , "Dardo Rocha 70"
                    , "2235235689"
                    , "wally.moretti@gmail.com"
                    , "pass123"));
        } catch (UserAlreadyRegisteredException e) {
            e.printStackTrace();
        } catch (BookingNotFoundException e) {
            e.printStackTrace();
        } catch (DateValidationException e) {
            e.printStackTrace();
        }
    }

    public void roomHC() {
        if (getRooms().isEmpty()) {
            rooms.add(new Room(getLastRoomNumber() + 1, Category.EXECUTIVE, Availability.FREE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.GUEST, Availability.FREE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.JUNIOR, Availability.FREE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.PRESIDENTIAL, Availability.FREE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.GUEST, Availability.CLEANING));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.GUEST, Availability.OCCUPIED));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.GUEST, Availability.IN_DESINFECTION));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.GUEST, Availability.OUT_OF_SERVICE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.EXECUTIVE, Availability.FREE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.GUEST, Availability.FREE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.JUNIOR, Availability.FREE));
            rooms.add(new Room(getLastRoomNumber() + 1, Category.PRESIDENTIAL, Availability.FREE));
        }

    }

    public void bookingHC() throws DateValidationException, BookingNotFoundException {
        if (getBookings().isEmpty()) {
            bookings.add(new Booking(getLastBookingId() + 1, 101, "38530953", "14874804", LocalDate.now(), LocalDate.now().plusDays(7)));
            bookings.add(new Booking(getLastBookingId() + 1, 101, "14589623", "14874804", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1).plusDays(7)));
            bookings.add(new Booking(getLastBookingId() + 1, 102, "14874804", "14589623", LocalDate.now().plusDays(5), LocalDate.now().plusDays(5).plusDays(7)));
            bookings.add(new Booking(getLastBookingId() + 1, 102, "14589623", "14874804", LocalDate.now().plusDays(20), LocalDate.now().plusDays(20).plusDays(7)));
            bookings.add(new Booking(getLastBookingId() + 1, 103, "14874804", "38530953", LocalDate.now(), LocalDate.now().plusDays(7)));
            bookings.add(new Booking(getLastBookingId() + 1, 103, "38530953", "14874804", LocalDate.now().plusDays(10), LocalDate.now().plusDays(10).plusDays(7)));
            cancelBooking(3);
        }
    }
}
