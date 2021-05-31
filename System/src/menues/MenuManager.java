package menues;

import controller.Hotel;
import model.User;

import java.util.Scanner;

public class MenuManager {

    public static void menuManager(Scanner scan, Hotel OlivandersHotel, User loggedUser) {

        int z = 0, option = 0;

        while (z == 0) {

            System.out.println("\nMenu Manager\n==========\n");
            System.out.println("1. \n2. \n3. ");
            System.out.println("4. Test - Edit Account");
            System.out.println("0. Log Out");
            System.out.print("Option: ");
            System.out.flush();
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nInsert text here\n");
                    break;
                }
                case 2: {
                    System.out.println("\nInsert text 2 here\n");
                    break;
                }
                case 3: {
                    System.out.println("\nInsert text 3 here\n");
                    break;
                }
                case 4: {
                    System.out.println("\nThis is a test\n");
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
