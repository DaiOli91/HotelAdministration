package menues;

import controller.Hotel;
import exception.*;
import model.Room;
import model.User;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPassenger {

    public static Hotel menuPassenger(Scanner scan, Hotel OllivandersHotel, User loggedUser) {

        int z = 0, option;

        int day, month, year, numberRoom;
        LocalDate startDate;
        LocalDate endDate;


        while (z == 0) {

            System.out.println("\nMenu Passenger\n=================\n");
            System.out.println("[1]. See available rooms\n[2]. New Booking\n[3]. My Bookings");
            System.out.println("[4]. Edit Account\n[5]. Deactivate Account\n");
            System.out.println("[0]. Log Out");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\nSee available rooms\n");
                        System.out.println("Loading Start Date\n-------------------");
                        System.out.print("Enter Day: ");
                        day = scan.nextInt();
                        System.out.print("Enter Month: ");
                        month = scan.nextInt();
                        System.out.print("Enter Year: ");
                        year = scan.nextInt();

                        try {

                            startDate = LocalDate.of(year, month, day);
                            if (startDate.isAfter(LocalDate.now())) {

                                System.out.println("\nLoading End Date\n-----------------");
                                System.out.print("Enter Day: ");
                                day = scan.nextInt();
                                System.out.print("Enter Month: ");
                                month = scan.nextInt();
                                System.out.print("Enter Year: ");
                                year = scan.nextInt();

                                try {

                                    endDate = LocalDate.of(year, month, day);
                                    if (ChronoUnit.DAYS.between(startDate, endDate) >= 7) {

                                        System.out.println("\n" + OllivandersHotel.getAvailableRooms(startDate, endDate).toString() + "\n");
                                    } else {

                                        System.out.println("\nBooking should be at least seven days long. Please, try again.\n");
                                    }

                                } catch (DateTimeException dte) {

                                    System.out.println("\n" + dte.getMessage() + ", please try again\n");
                                }
                            } else {

                                System.out.println("\nPlease enter a valid date\n");
                            }
                        } catch (DateTimeException dte) {

                            System.out.println("\n" + dte.getMessage() + ", please try again\n");
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("\nNew booking\n");

                        System.out.println("Loading Start Date\n-------------------");
                        System.out.print("Enter Day: ");
                        day = scan.nextInt();
                        System.out.print("Enter Month: ");
                        month = scan.nextInt();
                        System.out.print("Enter Year: ");
                        year = scan.nextInt();

                        try {

                            startDate = LocalDate.of(year, month, day);
                            if (startDate.isAfter(LocalDate.now())) {

                                System.out.println("\nLoading End Date\n-----------------");
                                System.out.print("Enter Day: ");
                                day = scan.nextInt();
                                System.out.print("Enter Month: ");
                                month = scan.nextInt();
                                System.out.print("Enter Year: ");
                                year = scan.nextInt();

                                try {

                                    endDate = LocalDate.of(year, month, day);
                                    if (ChronoUnit.DAYS.between(startDate, endDate) >= 7) {

                                        List<Room> availableRooms = OllivandersHotel.getAvailableRooms(startDate, endDate);
                                        System.out.println("\n" + availableRooms.toString() + "\n");
                                        System.out.print("Please enter the number of the room: ");
                                        numberRoom = scan.nextInt();
                                        if (OllivandersHotel.isRoomPresent(availableRooms, numberRoom)) {

                                            System.out.println("Would you like to add another passenger?");
                                            System.out.print("Yes/No: ");
                                            String answer = scan.next();

                                            if (answer.equalsIgnoreCase("yes")) {

                                                System.out.print("Enter the DNI of the added passenger: ");
                                                String dniOptionalPassenger = scan.next();

                                                OllivandersHotel.createBooking(numberRoom, loggedUser.getDni(), dniOptionalPassenger, startDate, endDate);
                                                System.out.println("\nBooking created successfully\n");
                                            } else if (answer.equalsIgnoreCase("no")) {

                                                OllivandersHotel.createBooking(numberRoom, loggedUser.getDni(), null, startDate, endDate);
                                                System.out.println("\nBooking created successfully\n");
                                            } else {

                                                System.out.println("\nInvalid answer. Please try again\n");
                                            }
                                        } else {

                                            System.out.println("\nInvalid room number\n");
                                        }
                                    } else {

                                        System.out.println("\nBooking should be at least seven days long. Please, try again.\n");
                                    }
                                } catch (DateTimeException dte) {

                                    System.out.println("\n" + dte.getMessage() + ", please try again\n");
                                } catch (UnavailableRoomException e) {
                                    System.out.println("\n" + e.getMessage() + "\n");
                                } catch (DateValidationException e) {
                                    System.out.println("\n" + e.getMessage() + "\n");
                                }
                            } else {

                                System.out.println("\nPlease enter a valid date\n");
                            }
                        } catch (DateTimeException dte) {

                            System.out.println("\n" + dte.getMessage() + ", please try again\n");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\nMy bookings\n");

                        if (OllivandersHotel.getBookingsByUser(loggedUser.getDni()).size() != 0) {

                            System.out.println("\n" + OllivandersHotel.getBookingsByUser(loggedUser.getDni()) + "\n");
                        } else {

                            System.out.println("\nIt seems that you do not have any booking yet\n");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\nEdit Account\n");
                        OllivandersHotel = MenuEditAccount.menuEditAccount(scan, OllivandersHotel, loggedUser);
                        break;
                    }
                    case 5: {
                        System.out.println("\nDeactivate Account\n");
                        try {
                            OllivandersHotel.deactivateAccount(loggedUser.getDni());
                            System.out.println("\nThe account has been deactivated. To activate it again, please reach one of our managers\n");
                        } catch (ActiveBookingException e) {

                            System.out.println("\n" + e.getMessage() + "\n");
                        } catch (UserNotFoundException e) {
                            System.out.println("\n" + e.getMessage() + "\n");
                        } catch (UserActiveDeactiveException e) {
                            System.out.println("\n" + e.getMessage() + "\n");
                        }
                        z++;
                        break;
                    }
                    case 0: {
                        System.out.println("\nLogged out successfully\n");

                        z++;
                        break;
                    }
                    default: {
                        System.out.println("\nPlease, choose a valid option\n");
                        break;
                    }
                }
            } catch (InputMismatchException ime) {

                System.out.println("\n" + ime.getMessage() + "\n");
            }
        }
        return OllivandersHotel;
    }
}
