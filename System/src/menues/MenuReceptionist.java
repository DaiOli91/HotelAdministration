package menues;

import controller.Hotel;
import model.User;

import java.util.Scanner;

public class MenuReceptionist {

    public static void menuReceptionist(Scanner scan, Hotel OlivandersHotel, User loggedUser) {

        int z = 0, option = 0;

        while (z == 0) {

            System.out.println("\nMenu Receptionist\n==============\n");
            System.out.println("1. Check In\n2. Check Out");
            System.out.println("3. See Bookings\n4. Edit Booking\n5. Cancel Booking\n6. See Booking by User ID");
            System.out.println("7. See Rooms");
            System.out.println("8. Edit Account\n");
            System.out.println("0. Log Out");
            System.out.print("Option: ");
            System.out.flush();
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
                    break;
                }
                case 7: {
                    System.out.println("\nSee Rooms\n");
                    break;
                }
                case 8: {
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
