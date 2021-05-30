package menues;

import controller.Hotel;
import model.User;

import java.util.Scanner;

public class MenuPassenger {

    public static void menuPassenger(Scanner scan, Hotel OlivandersHotel, User loggedUser) {

        int z = 0, option = 0;

        while (z == 0) {

            System.out.println("\nMenu Passenger\n=================\n");
            System.out.println("1. See available rooms\n2. New booking\n3. My Bookings");
            System.out.println("4. Edit Account\n5. Deactivate Account\n");
            System.out.println("0. Log Out");
            System.out.print("Option: ");
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\n See availability\n");
                    break;
                }
                case 2: {
                    System.out.println("\nNew booking\n");
                    break;
                }
                case 3: {
                    System.out.println("\nMy bookings\n");
                    break;
                }
                case 4: {
                    System.out.println("\nEdit Account\n");
                    break;
                }
                case 5: {
                    System.out.println("\nDeactivate Account\n");
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
