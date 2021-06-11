package controller;

import exception.*;
import model.*;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public void loadData() throws IOException, DateValidationException, BookingNotFoundException, UnavailableRoomException {
        users.readGson();
        if (users.getUsers().size() == 0) {
            this.userHC();
        } //to make sure that the list always has data

        rooms.readGson();
        if (this.rooms.getRooms().size() == 0) {
            this.roomHC();
        }

        bookings.readGson();
        if (this.bookings.getRoomBookings().size() == 0) {
            this.bookingHC();
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
    public void register(User user) throws UserAlreadyRegisteredException {
        if (this.users.search(user.getDni()) == null) {
            this.users.add(user);
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

        User auxUser = users.search(dni);

        if (auxUser != null) {

            if (age > 18) {

                if (age <= 99) {

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

            if (email.contains("@") && email.contains(".co")) {

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

        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {

            if ((int) DAYS.between(LocalDate.now(), aux_booking.getStartDate()) >= 2) {

                aux_booking.setState(State.CANCELLED);
                this.bookings.edit(aux_booking);
            } else {

                throw new DateValidationException("Error. There should be at least 48hs before booking Start Date. You cannot cancel this booking");
            }
        } else {

            throw new BookingNotFoundException();
        }
    }

    public void checkIn(String dniPassenger, Integer idBooking) throws InvalidStringException, BookingNotFoundException, DateValidationException, BookingStateException, RoomNotFoundException {

        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {
            if (aux_booking.getIdMainPassenger().equals(dniPassenger)) {

                if ((int) DAYS.between(LocalDate.now(), aux_booking.getStartDate()) >= 0 && (int) DAYS.between(LocalDate.now(), aux_booking.getStartDate()) <= 2) {

                    if (aux_booking.getState().equals(State.ACTIVE)) {

                        changeStateBooking(idBooking, State.CHECKED);
                        this.bookings.edit(aux_booking);

                        Room aux_room = this.rooms.search(aux_booking.getIdRoom()); //change the availability of the current list of rooms
                        changeRoomAvailability(aux_room.getNumber(), Availability.OCCUPIED);

                        this.rooms.edit(aux_room);
                    } else {

                        throw new BookingStateException("The State of the Booking is not active");
                    }
                } else {

                    throw new DateValidationException("Check In should be done within the first 48hr of the Booking");
                }
            } else {
                throw new InvalidStringException("Passenger's ID and Booking's Id do not match");
            }
        } else {

            throw new BookingNotFoundException();
        }
    }

    public void checkOut(String dniPassenger, Integer idBooking) throws InvalidStringException, BookingNotFoundException, BookingStateException, RoomNotFoundException {

        Booking aux_booking = this.bookings.search(idBooking);

        if (aux_booking != null) {
            if (aux_booking.getIdMainPassenger().equals(dniPassenger)) {

                if (aux_booking.getState().equals(State.CHECKED)) {

                    changeStateBooking(idBooking, State.CHECK_OUT);
                    this.bookings.edit(aux_booking);

                    Room aux_room = this.rooms.search(aux_booking.getIdRoom()); //change the availability of the current list of rooms
                    changeRoomAvailability(aux_room.getNumber(), Availability.CLEANING);

                    this.rooms.edit(aux_room);
                } else {

                    throw new BookingStateException("The passenger never Checked In");
                }
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

    public int getLastBookingId() {
        Optional<Booking> auxBooking = getBookings().stream().max(Comparator.comparing(booking -> booking.getId()));
        if (auxBooking != null && !auxBooking.isEmpty()) {
            return auxBooking.get().getId();
        } else {
            return 0;
        }
    }

    public List<Booking> getCheckedBookings() {

        return getBookings().stream().filter(booking -> booking.getState().equals(State.CHECKED)).collect(Collectors.toList());
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

    public void activateRoom(int idRoom) throws RoomNotFoundException, ActiveRoomException {

        Room auxRoom = this.rooms.search(idRoom);

        if (auxRoom != null) {

            if (auxRoom.getAvailability() == Availability.OUT_OF_SERVICE) {

                auxRoom.setAvailability(Availability.CLEANING);
                rooms.edit(auxRoom);

            } else {

                throw new ActiveRoomException("Room is already active");
            }
        } else {

            throw new RoomNotFoundException();
        }

    }

    public void deactivateRoom(int idRoom) throws RoomNotFoundException, ActiveRoomException, ActiveBookingException {

        Room auxRoom = rooms.search(idRoom);

        if (auxRoom != null) {

            List<Booking> checkActiveBookings = getActiveBookingsByRoom(idRoom);
            if (checkActiveBookings.size() == 0) {

                if (auxRoom.getAvailability() != Availability.OCCUPIED) {

                    if (auxRoom.getAvailability() != Availability.OUT_OF_SERVICE) {

                        changeRoomAvailability(idRoom, Availability.OUT_OF_SERVICE);
                        rooms.edit(auxRoom);

                    } else {

                        throw new ActiveRoomException("Room already deactivated");
                    }
                } else {

                    throw new ActiveRoomException("Room is occupied. Please, try later");
                }

            } else {

               throw new ActiveBookingException();
            }
        } else {

            throw new RoomNotFoundException();
        }
    }

    public void changeRoomCategory(int idRoom, Category category) throws RoomNotFoundException, ActiveRoomException, ActiveBookingException {

        Room auxRoom = rooms.search(idRoom);

        if (auxRoom != null) {

            List<Booking> checkBooking = getActiveBookingsByRoom(idRoom);
            if (checkBooking.size() == 0) {

                if (auxRoom.getAvailability() != Availability.OCCUPIED) {

                    auxRoom.setCategory(category);
                    rooms.edit(auxRoom);

                } else {

                    throw new ActiveRoomException("Room is occupied. Please, try later");
                }

            } else {

                throw new ActiveBookingException();
            }
        } else {

            throw new RoomNotFoundException();
        }

    }

    public void changeRoomAvailability(int idRoom, Availability availability) throws RoomNotFoundException {
        Room auxRoom = rooms.search(idRoom);

        if (auxRoom != null) {

            auxRoom.setAvailability(availability);
            rooms.edit(auxRoom);
        } else {

            throw new RoomNotFoundException();
        }
    }

    public int getLastRoomNumber() {
        Optional<Room> auxRoom = getRooms().stream().max(Comparator.comparing(room -> room.getNumber()));

        if (!auxRoom.isEmpty() && auxRoom != null) {
            return auxRoom.get().getNumber();
        } else {
            return 100;
        }

    }

    public List<Room> getAvailableRooms(LocalDate startDate, LocalDate endDate) {

        List<Room> auxRooms = new ArrayList<>();
        auxRooms.addAll(getRooms());
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

    public List<Room> getAllFreeRooms() {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.FREE)).collect(Collectors.toList());
    }

    public List<Room> getAllOccupiedRooms() {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.OCCUPIED)).collect(Collectors.toList());
    }

    public List<Room> getAllCleaningRooms() {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.CLEANING)).collect(Collectors.toList());
    }

    public List<Room> getAllInDesinfectionRooms() {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.IN_DESINFECTION)).collect(Collectors.toList());
    }

    public List<Room> getAllUnderRepairRooms() {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.UNDER_REPAIR)).collect(Collectors.toList());
    }

    public List<Room> getAllOutOfServiceRooms() {

        return getRooms().stream().filter(room -> room.getAvailability().equals(Availability.OUT_OF_SERVICE)).collect(Collectors.toList());
    }

    public List<Room> getAllGuestRooms() {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.GUEST)).collect(Collectors.toList());
    }

    public List<Room> getAllJuniorRooms() {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.JUNIOR)).collect(Collectors.toList());
    }

    public List<Room> getAllPresidentialRooms() {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.PRESIDENTIAL)).collect(Collectors.toList());
    }

    public List<Room> getAllExecutiveRooms() {

        return getRooms().stream().filter(room -> room.getCategory().equals(Category.EXECUTIVE)).collect(Collectors.toList());
    }


    // ╚═══════════════════════════════ HC Methods

    public void userHC() throws IOException {
        try {
            register(new Passenger("14785972"
                    , "Juan Pablo"
                    , "Castellares"
                    , 60
                    , Gender.MALE
                    , "Juramento 5646"
                    , "4585838"
                    , "something@something.com"
                    , "pass123"
                    , "Mar del Plata"));

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
        }
    }

    public void roomHC() {
        createRoom(Category.EXECUTIVE, Availability.FREE);
        createRoom(Category.PRESIDENTIAL, Availability.FREE);
        createRoom(Category.JUNIOR, Availability.FREE);
        createRoom(Category.GUEST, Availability.FREE);
        createRoom(Category.EXECUTIVE, Availability.IN_DESINFECTION);
        createRoom(Category.PRESIDENTIAL, Availability.OCCUPIED);
        createRoom(Category.JUNIOR, Availability.CLEANING);
        createRoom(Category.GUEST, Availability.CLEANING);
        createRoom(Category.EXECUTIVE, Availability.CLEANING);
        createRoom(Category.PRESIDENTIAL, Availability.CLEANING);
        createRoom(Category.JUNIOR, Availability.CLEANING);
        createRoom(Category.GUEST, Availability.CLEANING);
        createRoom(Category.EXECUTIVE, Availability.FREE);
        createRoom(Category.PRESIDENTIAL, Availability.FREE);
        createRoom(Category.JUNIOR, Availability.FREE);
        createRoom(Category.GUEST, Availability.FREE);
        createRoom(Category.GUEST, Availability.FREE);
    }

    public void bookingHC() throws DateValidationException, BookingNotFoundException, UnavailableRoomException {

        createBooking(101, "38530953", "14874804", LocalDate.of(2021, 6, 11), LocalDate.of(2021, 6, 19));
        createBooking(101, "14589623", "14874804", LocalDate.of(2021, 7, 3), LocalDate.of(2021, 7, 13));
        createBooking(101, "14589623", "14874804", LocalDate.of(2021, 8, 3), LocalDate.of(2021, 8, 13));
        createBooking(102, "14874804", "14589623", LocalDate.of(2021, 6, 14), LocalDate.of(2021, 6, 22));
        createBooking(110, "14589623", "14874804", LocalDate.of(2021, 6, 17), LocalDate.of(2021, 6, 30));
        createBooking(103, "14874804", "38530953", LocalDate.of(2021, 6, 14), LocalDate.of(2021, 6, 25));
        createBooking(112, "38530953", "14874804", LocalDate.of(2021, 6, 17), LocalDate.of(2021, 6, 28));
        cancelBooking(3);
    }
}
