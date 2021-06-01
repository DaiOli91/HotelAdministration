package menues;

import controller.Hotel;
import model.Availability;
import model.Booking;
import model.Gender;
import model.User;

import java.util.List;
import java.util.Scanner;

public class MenuReceptionist {

    public static void menuReceptionist(Scanner scan, Hotel OlivandersHotel, User loggedUser) {

        int z = 0, option = 0;

        int idRoom = 0, availabilityOption = 0;
        String dni;
        Availability availability = null;

        while (z == 0) {

            System.out.println("\nMenu Receptionist\n==============\n");
            System.out.println("1. Check In\n2. Check Out");
            System.out.println("3. See Bookings\n4. Edit Booking\n5. Cancel Booking\n6. See Booking by User ID");
            System.out.println("7. See Rooms\n8. Edit Room Availability");
            System.out.println("9. Edit Account\n");
            System.out.println("0. Log Out");
            System.out.print("Option: ");
            System.out.flush();
            //TODO Need to implement "InputMismatchException".
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nCheck In\n");
                    break;
                }
                case 2: {
                    System.out.println("\nCheck Out\n");
                    break;
                }
                case 3: {
                    System.out.println("\nSee Bookings\n");
                    break;
                }
                case 4: {
                    System.out.println("\n4Edit Booking\n");
                    break;
                }
                case 5: {
                    System.out.println("\nCancel Booking\n");
                    break;
                }
                case 6: {
                    System.out.println("\nSee Booking by User ID\n");

                    List<Booking> userBookings = null;

                    System.out.println("1. All Bookings by User\n2. Active Bookings by User\n");
                    System.out.print("Option: ");
                    int bookingOption = scan.nextInt();
                    //TODO Need to implement "InputMismatchException".

                    switch (bookingOption) {
                        case 1: {
                            System.out.println("\nAll Bookings by User\n");

                            System.out.print("Enter the Passenger DNI: ");
                            dni = scan.next();
                            userBookings = OlivandersHotel.getBookingsByUser(dni);

                            if (userBookings.size() == 0) {

                                System.out.println("\n" + OlivandersHotel.getBookingsByUser(dni) + "\n");
                            } else {

                                System.out.println("\nThere is no bookings\n");
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("\nActive Bookings by User\n");

                            System.out.print("Enter the Passenger DNI: ");
                            dni = scan.next();
                            userBookings = OlivandersHotel.getActiveBookingsByUser(dni);

                            if (userBookings.size() == 0) {

                                System.out.println("\n" + OlivandersHotel.getActiveBookingsByUser(dni) + "\n");
                            } else {

                                System.out.println("\nThere is no bookings\n");
                            }
                            break;
                        }
                        default: {

                            System.out.println("\nPlease select a valid option number\n");
                            break;
                        }
                    }
                    break;
                }
                case 7: {
                    System.out.println("\nSee Rooms\n");
                    break;
                }
                case 8: {
                    System.out.println("\nEdit Room Availability\n");

                    System.out.print("Enter ID Room: ");
                    idRoom = scan.nextInt();

                    while (availabilityOption == 0 || availabilityOption > 4) {
                        System.out.print("Availability (1. Free, 2. Cleaning, 3. In Desinfection, 4. Under repair): ");
                        availabilityOption = scan.nextInt();

                        //TODO Need to implement "InputMismatchException".

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

                                System.out.println("\nPlease select a valid option number\n");
                                break;
                            }
                        }
                    }

                    // TODO Maybe needs more validations.
                    System.out.println("\n" + OlivandersHotel.changeRoomAvailability(idRoom, availability) + "\n");
                    break;
                }
                case 9: {
                    System.out.println("\nEdit Account\n");

                    // TODO This is a test. If works, we need to add it.
                    MenuEditAccount.menuEditAccount(scan, OlivandersHotel, loggedUser);
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
