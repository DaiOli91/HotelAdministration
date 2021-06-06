import controller.Hotel;
import model.*;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
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
            newHotel = new Hotel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Testing.roomHC(roomRepo.getRooms());
        // Testing.userHC(userRepo.getUsers());
        // Testing.bookingHC(bookingRepo.getRoomBookings());
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
*/

        try {
            newHotel.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println(newHotel.getAvailableRooms(LocalDate.of(2021, 06, 17), LocalDate.of(2021, 07, 03)));
        System.out.println(newHotel.getBookings().toString());
        System.err.println("AVAILABLE: \n" + getActiveBookingsInPeriod(LocalDate.of(2021, 06, 17), LocalDate.of(2021, 07, 3), newHotel.getBookings()));
        //System.out.println(ChronoUnit.DAYS.between(LocalDate.of(2021, 06, 21), LocalDate.of(2021, 06, 21)));
    }

    public static void roomHC(List<Room> rooms) {
        rooms.add(new Room(Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(Category.GUEST, Availability.FREE));
        rooms.add(new Room(Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(Category.PRESIDENTIAL, Availability.FREE));
        rooms.add(new Room(Category.GUEST, Availability.CLEANING));
        rooms.add(new Room(Category.GUEST, Availability.OCCUPIED));
        rooms.add(new Room(Category.GUEST, Availability.IN_DESINFECTION));
        rooms.add(new Room(Category.GUEST, Availability.OUT_OF_SERVICE));
        rooms.add(new Room(Category.EXECUTIVE, Availability.FREE));
        rooms.add(new Room(Category.GUEST, Availability.FREE));
        rooms.add(new Room(Category.JUNIOR, Availability.FREE));
        rooms.add(new Room(Category.PRESIDENTIAL, Availability.FREE));
    }

    public static void userHC(List<User> users) {
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

        users.add(new Manager("13525252"
                , "Calos"
                , "Patriarcado"
                , 61
                , Gender.MALE
                , "Patagones 6000"
                , "2235505065"
                , "carlos.patriarcado@gmail.com"
                , "patriarcado123"));

        users.add(new Manager("25525252"
                , "Natalia"
                , "Bossy"
                , 40
                , Gender.FEMALE
                , "San Luis 4052"
                , "2235235689"
                , "natybossy@gmail.com"
                , "bossy123"));
    }

    public static void bookingHC(List<Booking> bookings) {
        // LocalDate startDate = LocalDate.now();
        //  LocalDate endDate = LocalDate.now().plusDays(7);
        DateTimeFormatter format = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        bookings.add(new Booking(101, "38530953", "14874804", LocalDate.now(), LocalDate.now().plusDays(7)));
        bookings.add(new Booking(101, "14589623", "14874804", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1).plusDays(7)));
        bookings.add(new Booking(102, "14874804", "14589623", LocalDate.now().plusDays(5), LocalDate.now().plusDays(5).plusDays(7)));
        bookings.add(new Booking(102, "14589623", "14874804", LocalDate.now().plusDays(20), LocalDate.now().plusDays(20).plusDays(7)));
        bookings.add(new Booking(103, "14874804", "38530953", LocalDate.now(), LocalDate.now().plusDays(7)));
        bookings.add(new Booking(103, "38530953", "14874804", LocalDate.now().plusDays(10), LocalDate.now().plusDays(10).plusDays(7)));
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
