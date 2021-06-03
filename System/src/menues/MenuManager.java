package menues;

import controller.Hotel;
import model.User;

import javax.naming.event.ObjectChangeListener;
import java.util.Scanner;

public class MenuManager {

    public static void menuManager(Scanner scan, Hotel OllivandersHotel, User loggedUser) {

        int z = 0, option = 0;

        while (z == 0) {

            System.out.println("\nMenu Manager\n==========\n");
            System.out.println("1. Add Employee\n2. See Users\n3. Edit Account\n4. Deactivate User");
            System.out.println("5. See Rooms\n6. Edit Room\n7 . Deactivate Room");
            System.out.println("0. Log Out");
            System.out.print("Option: ");
            System.out.flush();
            //TODO Need to implement "InputMismatchException".
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nAdd Employee\n");
                    break;
                }
                case 2: {
                    System.out.println("\nSee Users\n");
                    break;
                }
                case 3: {
                    System.out.println("\nEdit Account\n");
                    break;
                }
                case 4: {
                    System.out.println("\nDeactivate User\n");
                    break;
                }
                case 5: {
                    System.out.println("\nSee Rooms\n");

                    MenuSeeRooms.menuSeeRooms(scan, OllivandersHotel);
                    break;
                }
                case 6: {
                    System.out.println("\nEdit Room\n");
                    break;
                }
                case 7: {
                    System.out.println("\nDeactivate Room\n");
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
