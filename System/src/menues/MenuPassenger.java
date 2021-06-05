package menues;

import controller.Hotel;
import model.Room;
import model.User;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuPassenger {

    public static void menuPassenger(Scanner scan, Hotel OllivandersHotel, User loggedUser) {

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
            //TODO Need to implement "InputMismatchException".
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nSee available rooms\n");
                    System.out.println("Loading Start Date\n-------------------\n");
                    System.out.print("Enter Day: ");
                    day = scan.nextInt();
                    System.out.print("Enter Month: ");
                    month = scan.nextInt();
                    System.out.print("Enter Year: ");
                    year = scan.nextInt();

                    try {

                        startDate = LocalDate.of(year, month, day);

                        if (startDate.isAfter(LocalDate.now())) {

                            System.out.println("\nLoading End Date\n-----------------\n");
                            System.out.print("Enter Day: ");
                            day = scan.nextInt();
                            System.out.print("Enter Month: ");
                            month = scan.nextInt();
                            System.out.print("Enter Year: ");
                            year = scan.nextInt();

                            try {

                                endDate = LocalDate.of(year, month, day);

                                if (endDate.isAfter(LocalDate.now())) {

                                    System.out.println("\n" + OllivandersHotel.getAvailableRooms(startDate, endDate).toString() + "\n");
                                } else {

                                    System.out.println("\nPlease enter a valid date\n");
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

                    // TODO Fix room state. Not changing after booked
                    // TODO Fix new booking when you select the exact same days and try to create another booking.

                    System.out.println("\nNew booking\n");
                    System.out.println("Loading Start Date\n-------------------\n");
                    System.out.print("Enter Day: ");
                    day = scan.nextInt();
                    System.out.print("Enter Month: ");
                    month = scan.nextInt();
                    System.out.print("Enter Year: ");
                    year = scan.nextInt();

                    try {

                        startDate = LocalDate.of(year, month, day);

                        if (startDate.isAfter(LocalDate.now())) {

                            System.out.println("\nLoading End Date\n-----------------\n");
                            System.out.print("Enter Day: ");
                            day = scan.nextInt();
                            System.out.print("Enter Month: ");
                            month = scan.nextInt();
                            System.out.print("Enter Year: ");
                            year = scan.nextInt();

                            try {

                                endDate = LocalDate.of(year, month, day);

                                if (endDate.isAfter(LocalDate.now())) {

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

                                            //TODO Check if there are 7 days between startDate and EndDate.
                                            System.out.println("\n" + OllivandersHotel.createBooking(numberRoom, loggedUser.getDni(), dniOptionalPassenger, startDate, endDate) + "\n");
                                        } else if (answer.equalsIgnoreCase("no")) {

                                            System.out.println("\n" + OllivandersHotel.createBooking(numberRoom, loggedUser.getDni(), null, startDate, endDate) + "\n");
                                        } else {

                                            System.out.println("\nInvalid answer. Please try again\n");
                                        }
                                    } else {

                                        System.out.println("\nInvalid room number\n");
                                    }
                                } else {

                                    System.out.println("\nPlease enter a valid date\n");
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
                    MenuEditAccount.menuEditAccount(scan, OllivandersHotel, loggedUser);
                    break;
                }
                case 5: {
                    System.out.println("\nDeactivate Account\n");
                    System.out.println(OllivandersHotel.deactivateAccount(loggedUser.getDni()) + "\n");
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
        }
    }
}
