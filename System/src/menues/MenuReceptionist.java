package menues;

import controller.Hotel;
import model.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuReceptionist {

    public static void menuReceptionist(Scanner scan, Hotel OllivandersHotel, User loggedUser) {

        int z = 0, option;

        int idBooking, idRoom, availabilityOption = 0;
        String dni;
        Availability availability = null;

        while (z == 0) {

            System.out.println("\nMenu Receptionist\n==============\n");
            System.out.println("[1]. Check In\n[2]. Check Out");
            System.out.println("[3]. See Bookings\n[4]. See Booking by User ID\n[5]. Cancel Booking");
            System.out.println("[6]. See Rooms\n[7]. Edit Room Availability");
            System.out.println("[8]. Edit Account\n");
            System.out.println("[0]. Log Out");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\nCheck In\n");
                        System.out.print("Enter the Passenger DNI: ");
                        dni = scan.next();
                        System.out.print("Enter the Booking ID: ");
                        idBooking = scan.nextInt();

                        if (idBooking != -1) {

                            System.out.println("\n" + OllivandersHotel.checkIn(dni, idBooking) + "\n");
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("\nCheck Out\n");
                        System.out.print("Enter the Passenger DNI: ");
                        dni = scan.next();
                        System.out.print("Enter the Booking ID: ");
                        idBooking = scan.nextInt();

                        // TODO Check wtf I tried to do here. Dai said that I need to put "idBooking > 0" because I am checking that the user put a valid ID Booking number.
                        // TODO I say that I don't know wtf I did here :)
                        if (idBooking != -1) {

                            System.out.println("\n" + OllivandersHotel.checkOut(dni, idBooking) + "\n");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\nSee Bookings\n");

                        List<Booking> bookingsByState;
                        State state;

                        System.out.println("1. All Bookings\n2. Active Bookings\n3. Cancelled Bookings\n4. Checked Bookings\n5. Check Out Bookings\n");
                        System.out.print("Option: ");
                        int bookingOption = scan.nextInt();
                        switch (bookingOption) {
                            case 1: {
                                System.out.println("\nAll Bookings\n");
                                // TODO Check if we need to add toString or not.
                                System.out.println("\n" + OllivandersHotel.getBookings().toString() + "\n");
                                break;
                            }
                            case 2: {
                                System.out.println("\nActive Bookings\n");
                                state = State.ACTIVE;
                                bookingsByState = OllivandersHotel.getBookingsByState(state);

                                if (bookingsByState.size() != 0) {

                                    System.out.println("\n" + OllivandersHotel.getBookingsByState(state) + "\n");
                                } else {

                                    System.err.println("\nThere are no bookings with this State at the moment\n");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println("\nCancelled Bookings\n");
                                state = State.CANCELLED;
                                bookingsByState = OllivandersHotel.getBookingsByState(state);

                                if (bookingsByState.size() != 0) {

                                    System.out.println("\n" + OllivandersHotel.getBookingsByState(state) + "\n");
                                } else {

                                    System.err.println("\nThere are no bookings with this State at the moment\n");
                                }
                                break;
                            }
                            case 4: {
                                System.out.println("\nChecked Bookings\n");
                                state = State.CHECKED;
                                bookingsByState = OllivandersHotel.getBookingsByState(state);

                                if (bookingsByState.size() != 0) {

                                    System.out.println("\n" + OllivandersHotel.getBookingsByState(state) + "\n");
                                } else {

                                    System.err.println("\nThere are no bookings with this State at the moment\n");
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("\nCheck Out Bookings\n");
                                state = State.CHECK_OUT;
                                bookingsByState = OllivandersHotel.getBookingsByState(state);

                                if (bookingsByState.size() != 0) {

                                    System.out.println("\n" + OllivandersHotel.getBookingsByState(state) + "\n");
                                } else {

                                    System.err.println("\nThere are no bookings with this State at the moment\n");
                                }
                                break;
                            }
                            default: {
                                System.err.println("\nPlease, choose a valid option\n");
                                break;
                            }
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\nSee Booking by User ID\n");

                        List<Booking> userBookings;

                        System.out.println("1. All Bookings by User\n2. Active Bookings by User\n");
                        System.out.print("Option: ");
                        int bookingOption = scan.nextInt();
                        switch (bookingOption) {
                            case 1: {
                                System.out.println("\nAll Bookings by User\n");

                                System.out.print("Enter the Passenger DNI: ");
                                dni = scan.next();
                                userBookings = OllivandersHotel.getBookingsByUser(dni);

                                if (userBookings.size() != 0) {

                                    System.out.println("\n" + OllivandersHotel.getBookingsByUser(dni) + "\n");
                                } else {

                                    System.err.println("\nThere are no bookings\n");
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("\nActive Bookings by User\n");

                                System.out.print("Enter the Passenger DNI: ");
                                dni = scan.next();
                                userBookings = OllivandersHotel.getActiveBookingsByUser(dni);

                                if (userBookings.size() != 0) {

                                    System.out.println("\n" + OllivandersHotel.getActiveBookingsByUser(dni) + "\n");
                                } else {

                                    System.err.println("\nThere are no bookings\n");
                                }
                                break;
                            }
                            default: {

                                System.err.println("\nPlease select a valid option number\n");
                                break;
                            }
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("\nCancel Booking\n");

                        System.out.print("Enter the Booking ID: ");
                        idBooking = scan.nextInt();

                        System.out.println("\n" + OllivandersHotel.cancelBooking(idBooking) + "\n");
                        break;
                    }
                    case 6: {
                        System.out.println("\nSee Rooms\n");
                        MenuSeeRooms.menuSeeRooms(scan, OllivandersHotel);
                        break;
                    }
                    case 7: {
                        System.out.println("\nEdit Room Availability\n");

                        System.out.print("Enter ID Room: ");
                        idRoom = scan.nextInt();

                        while (availabilityOption == 0 || availabilityOption > 4) {
                            System.out.print("Availability (1. Free, 2. Cleaning, 3. In Desinfection, 4. Under repair): ");
                            availabilityOption = scan.nextInt();
                            switch (availabilityOption) {
                                case 1: {
                                    availability = Availability.FREE;
                                    break;
                                }
                                case 2: {
                                    availability = Availability.CLEANING;
                                    break;
                                }
                                case 3: {
                                    availability = Availability.IN_DESINFECTION;
                                    break;
                                }
                                case 4: {

                                    availability = Availability.UNDER_REPAIR;
                                    break;
                                }
                                default: {

                                    System.err.println("\nPlease select a valid option number\n");
                                    break;
                                }
                            }
                        }

                        // TODO Maybe needs more validations.
                        System.out.println("\n" + OllivandersHotel.changeRoomAvailability(idRoom, availability) + "\n");
                        break;
                    }
                    case 8: {
                        System.out.println("\nEdit Account\n");

                        // TODO Maybe needs changes.

                        System.out.print("1. Edit my Account\n2. Edit Passenger Account\n");
                        int editOption = scan.nextInt();
                        if (editOption == 1) {

                            MenuEditAccount.menuEditAccount(scan, OllivandersHotel, loggedUser);
                        } else if (editOption == 2) {

                            System.out.print("Enter the Passenger DNI: ");
                            dni = scan.next();
                            User pUser = new Passenger(dni, "xxxx", "xxxx", 18, Gender.NA, "xxxx", "1234", "email@gmail.com", "1234", "xxxx");

                            MenuEditAccount.menuEditAccount(scan, OllivandersHotel, pUser);
                        } else {

                            System.err.println("\nPlease, choose a valid option\n");
                        }

                        break;
                    }
                    case 0: {
                        System.out.println("\nLogged out successfully\n");
                        /*
                        try {
                            OllivandersHotel.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        */
                        z++;
                        break;
                    }
                    default: {
                        System.err.println("\nPlease, choose a valid option\n");
                        break;
                    }
                }
            } catch (InputMismatchException ime) {

                System.err.println("Please, enter only numbers.");
                System.err.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
                scan.next();
            }
        }
    }
}
