package menues;

import controller.Hotel;

import java.util.Scanner;

public class MenuSeeUsers {

    public static void menuSeeUsers(Scanner scan, Hotel OllivandersHotel) {

        int z = 0, option = 0;

        while (z == 0) {

            System.out.println("\nMenu See Users\n==============\n");
            System.out.println("1. See All Users");
            System.out.println("2. See All Employees\n3. See Active Employees\n4. See Former Employees");
            System.out.println("5. See All Passenger\n6. See Active Passengers\n7. See Former Passenger");
            System.out.println("0. Back");
            System.out.print("Option: ");
            System.out.flush();
            //TODO Need to implement "InputMismatchException".
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nSee All Users\n");
                    System.out.println("\n" + OllivandersHotel.getUsers() + "\n");
                    break;
                }
                case 2: {
                    System.out.println("\nSee All Employees\n");
                    // TODO Needs a new method.
                    break;
                }
                case 3: {
                    System.out.println("\nSee Active Employees\n");
                    System.out.println("\n" + OllivandersHotel.getActiveEmployees() + "\n");
                    break;
                }
                case 4: {
                    System.out.println("\nSee Former Employees\n");
                    System.out.println("\n" + OllivandersHotel.getFormerEmployees() + "\n");
                    break;
                }
                case 5: {
                    System.out.println("\nSee All Passenger\n");
                    System.out.println("\n" + OllivandersHotel.getPassengers() + "\n");
                    break;
                }
                case 6: {
                    System.out.println("\nSee Active Passengers\n");
                    // TODO Needs a new method.
                    break;
                }
                case 7: {
                    System.out.println("\nSee Former Passenger\n");
                    // TODO Needs a new method.
                    break;
                }
                case 0: {
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
