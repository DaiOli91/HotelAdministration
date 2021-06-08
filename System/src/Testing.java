import controller.Hotel;
import exception.DateValidationException;
import exception.UnavailableRoomException;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class Testing {

    public static void main(String[] args) {

        //   User auxPassenger = new Passenger("35892293", "pass123");
        //   System.out.println("User.class: " + User.class + "\nPassenger.class: "
        //           + Passenger.class + "\nauxPassenger.getClass(): " + auxPassenger.getClass());

        //   RoomRepository roomRepo = new RoomRepository();
        //   UserRepository userRepo = new UserRepository();
        //   BookingRepository bookingRepo = new BookingRepository();

        Hotel newHotel = null;
        try {
            newHotel = new Hotel("Ollivanders", "Belgrano 3998", "4758996");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Testing.roomHC(newHotel);
       //  Testing.userHC(newHotel);
       // Testing.bookingHC(newHotel);
        try {
            newHotel.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        try {
            roomRepo.readGson();
         //   System.out.println(roomRepo.getRooms().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            userRepo.readGson();
           // System.out.println(userRepo.getUsers().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bookingRepo.readGson();
            System.out.println(bookingRepo.getRoomBookings().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            newHotel.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        //System.err.println(newHotel.getUsers().size());
      //  System.err.println(newHotel.getAvailableRooms(LocalDate.of(2021, 06, 17), LocalDate.of(2021, 07, 03)));
     //   System.out.println(newHotel.getBookings().toString());
     //   System.err.println("AVAILABLE: \n" + getActiveBookingsInPeriod(LocalDate.of(2021, 06, 17), LocalDate.of(2021, 07, 3), newHotel.getBookings()));
       // System.err.println(ChronoUnit.DAYS.between(LocalDate.of(2021, 06, 21), LocalDate.of(2021, 05, 21)));
        System.out.println("Ultimo ID: " + newHotel.getLastRoomNumber());
        try {
            newHotel.createBooking(135, "38530953", "", LocalDate.of(2021, 7, 15), LocalDate.of(2021, 8,1));
        } catch (UnavailableRoomException | DateValidationException e) {
            e.printStackTrace();
        }
        System.out.println(newHotel.getBookings().toString());
        try {
            newHotel.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void roomHC(Hotel hotel) {
        List<Room> rooms = hotel.getRooms();
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.PRESIDENTIAL, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.CLEANING));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.OCCUPIED));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.IN_DESINFECTION));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.OUT_OF_SERVICE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.PRESIDENTIAL, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.PRESIDENTIAL, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.CLEANING));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.OCCUPIED));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.IN_DESINFECTION));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.OUT_OF_SERVICE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.PRESIDENTIAL, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.PRESIDENTIAL, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.CLEANING));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.OCCUPIED));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.IN_DESINFECTION));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.OUT_OF_SERVICE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.GUEST, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(hotel.getLastRoomNumber()+1, Category.PRESIDENTIAL, Availability.FREE));
    }

    public static void userHC(Hotel hotel) {
        List<User> users = hotel.getUsers();
        users.add(new Passenger("14874804"
                , "Andrea"
                , "Carrizo"
                , 60
                , Gender.FEMALE
                , "Gaboto 5646"
                , "4585858"
                , "something@asomething.com"
                , "pass123"
                , "Mar del Plata"));

        users.add(new Passenger("14589623"
                , "Miguel"
                , "Toyota"
                , 60
                , Gender.FEMALE
                , "Gaboto 5646"
                , "4585858"
                , "something@asomething.com"
                , "pass123"
                , "Mar del Plata"));

        users.add(new Passenger("38530953"
                , "Felipe"
                , "Graziano"
                , 25
                , Gender.MALE
                , "Mexico 3536"
                , "4651236"
                , "something@something.com"
                , "pass123"
                , "San Clemente"));

        users.add(new Receptionist("18956565"
                , "Sofia"
                , "Caceres"
                , 58
                , Gender.FEMALE
                , "Bahia Blanca 123"
                , "2235686968"
                , "soficaceres@gmail.com"
                , "sofi1966"
                , Shift.MORNING));

        users.add(new Receptionist("18956323"
                , "Matias"
                , "Palacios"
                , 58
                , Gender.MALE
                , "Chubut 123"
                , "2235686323"
                , "matias@gmail.com"
                , "mati1966"
                , Shift.AFTERNOON));

        users.add(new Receptionist("35236598"
                , "Santiago"
                , "Gonzalez"
                , 30
                , Gender.MALE
                , "Rio Negro 456"
                , "2235456985"
                , "santig@gmail.com"
                , "santi30"
                , Shift.NIGHT));

        users.add(new Manager("35892293"
                , "Daiana"
                , "Olivera"
                , 30
                , Gender.FEMALE
                , "San Luis 4052"
                , "2235506592"
                , "daiolivera@gmail.com"
                , "pass123"));

        users.add(new Manager("39098436"
                , "Walter"
                , "Moretti"
                , 25
                , Gender.MALE
                , "Dardo Rocha 70"
                , "2235235689"
                , "wally.moretti@gmail.com"
                , "pass123"));
    }

    public static void bookingHC(Hotel hotel) {
        List<Booking> bookings = hotel.getBookings();
        //DateTimeFormatter format = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        bookings.add(new Booking(hotel.getLastBookingId()+1, 101, "38530953", "14874804", LocalDate.now(), LocalDate.now().plusDays(7)));
        bookings.add(new Booking(hotel.getLastBookingId()+1, 101, "14589623", "14874804", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1).plusDays(7)));
        bookings.add(new Booking(hotel.getLastBookingId()+1, 102, "14874804", "14589623", LocalDate.now().plusDays(5), LocalDate.now().plusDays(5).plusDays(7)));
        bookings.add(new Booking(hotel.getLastBookingId()+1, 102, "14589623", "14874804", LocalDate.now().plusDays(20), LocalDate.now().plusDays(20).plusDays(7)));
        bookings.add(new Booking(hotel.getLastBookingId()+1, 103, "14874804", "38530953", LocalDate.now(), LocalDate.now().plusDays(7)));
        bookings.add(new Booking(hotel.getLastBookingId()+1, 103, "38530953", "14874804", LocalDate.now().plusDays(10), LocalDate.now().plusDays(10).plusDays(7)));
        //cancelBooking(3);

    }
/*

        public static List<Booking> getActiveBookingsInPeriod(LocalDate startPeriod, LocalDate endPeriod, List<Booking> bookings) {
            return bookings.stream().filter(b -> !dateIsBetweenPeriod(b.getStartDate(), startPeriod, endPeriod))
                    .filter(b->(!dateIsBetweenPeriod(b.getEndDate(), startPeriod, endPeriod)))
                    .collect(Collectors.toList());

        }
    */
    public static boolean dateIsBetweenPeriod(LocalDate date, LocalDate startPeriod, LocalDate endPeriod) {
        boolean flag = false;
        if ((date.isAfter(startPeriod) || date.isEqual(startPeriod)) && (date.isBefore(endPeriod) || date.isEqual(endPeriod))) {
            flag = true;
        }
        return flag;
    }

    public static List<Booking> getActiveBookingsInPeriod(LocalDate startPeriod, LocalDate endPeriod, List<Booking> bookings) {
        return bookings
                .stream()
                .filter(b -> !b.getEndDate().isBefore(startPeriod))
                .filter(b -> (!b.getStartDate().isAfter(endPeriod)))
                .collect(Collectors.toList());

    }
}
