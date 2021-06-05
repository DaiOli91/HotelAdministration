package menues;

import controller.Hotel;

import java.util.Scanner;

public class MenuSeeUsers {

    public static void menuSeeUsers(Scanner scan, Hotel OllivandersHotel) {

        int z = 0, option = 0;

        while (z == 0) {

            System.out.println("\nMenu See Users\n==============\n");
            System.out.println("1. See All Employees\n2. See Active Employees\n3. See Former Employees");
            System.out.println("4. See All Passenger\n5. See Active Passengers\n6. See Former Passenger");
            System.out.println("7. Search User by Full Name\n");
            System.out.println("0. Back");
            System.out.print("Option: ");
            System.out.flush();
            //TODO Need to implement "InputMismatchException".
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nSee All Employees\n");
                    System.out.println("\n" + OllivandersHotel.getAllEmployees() + "\n");
                    break;
                }
                case 2: {
                    System.out.println("\nSee Active Employees\n");
                    System.out.println("\n" + OllivandersHotel.getActiveManagers() + "\n");
                    System.out.println("\n" + OllivandersHotel.getActiveReceptionists() + "\n");
                    break;
                }
                case 3: {
                    System.out.println("\nSee Former Employees\n");
                    System.out.println("\n" + OllivandersHotel.getFormerEmployees() + "\n");
                    break;
                }
                case 4: {
                    System.out.println("\nSee All Passenger\n");
                    System.out.println("\n" + OllivandersHotel.getPassengers() + "\n");
                    break;
                }
                case 5: {
                    System.out.println("\nSee Active Passengers\n");
                    System.out.println("\n" + OllivandersHotel.getActivePassengers() + "\n");
                    break;
                }
                case 6: {
                    System.out.println("\nSee Former Passenger\n");
                    System.out.println("\n" + OllivandersHotel.getFormerPassengers() + "\n");
                    break;
                }
                case 7: {
                    System.out.println("\nSearch User by Full Name\n");
                    System.out.print("1. Search User by ID, 2. Search User by Full Name: ");
                    int searchOption = scan.nextInt();

                    if (searchOption == 1) {

                        System.out.print("Enter User ID: ");
                        String dni = scan.next();

                        System.out.println("\n" + OllivandersHotel.getUserByID(dni) + "\n");
                    } else if (searchOption == 2) {

                        System.out.print("Enter First Name: ");
                        String firstName = scan.next();
                        System.out.print("Enter Last Name: ");
                        String lastName = scan.next();

                        System.out.println("\n" + OllivandersHotel.getUserByFullName(firstName, lastName) + "\n");
                    } else {

                        System.out.println("\nNot a valid option\n");
                    }
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
