package menues;

import controller.Hotel;
import model.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuSeeUsers {

    public static void menuSeeUsers(Scanner scan, Hotel OllivandersHotel) {

        int z = 0, option;

        while (z == 0) {

            System.out.println("\nMenu See Users\n==============\n");
            System.out.println("[1]. See All Employees\n[2]. See Active Employees\n[3]. See Former Employees");
            System.out.println("[4]. See All Passenger\n[5]. See Active Passengers\n[6]. See Former Passenger");
            System.out.println("[7]. Search User\n");
            System.out.println("[0]. Back");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\n------------------\nSee All Employees\n------------------\n");

                        List<User> allEmployeesList = OllivandersHotel.getAllEmployees();
                        if (allEmployeesList.size() != 0) {

                            System.out.println(OllivandersHotel.getAllEmployees() + "\n");
                        } else {

                            System.out.println("There is/are no employee/s\n");
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("\n--------------------\nSee Active Employees\n--------------------\n");

                        List<User> activeManagersList = OllivandersHotel.getActiveManagers();
                        List<User> activeReceptionistsList = OllivandersHotel.getActiveReceptionists();
                        if (activeManagersList.size() != 0) {

                            System.out.println(OllivandersHotel.getActiveManagers() + "\n");
                        } else {

                            System.out.println("There is/are no active Manager/s\n");
                        }

                        if (activeReceptionistsList.size() != 0) {

                            System.out.println(OllivandersHotel.getActiveReceptionists() + "\n");
                        } else {

                            System.out.println("There is/are no active Receptionist/s\n");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\n--------------------\nSee Former Employees\n--------------------\n");

                        List<User> formerEmployeesList = OllivandersHotel.getFormerEmployees();
                        if (formerEmployeesList.size() != 0) {

                            System.out.println(OllivandersHotel.getFormerEmployees() + "\n");
                        } else {

                            System.out.println("The is/are no former Employee/s\n");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\n------------------\nSee All Passenger\n------------------\n");

                        List<User> allPassengersList = OllivandersHotel.getPassengers();
                        if (allPassengersList.size() != 0) {

                            System.out.println(OllivandersHotel.getPassengers() + "\n");
                        } else {

                            System.out.println("The is/are no Passenger/s\n");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("\n--------------------\nSee Active Passengers\n--------------------\n");

                        List<User> activePassengersList = OllivandersHotel.getActivePassengers();
                        if (activePassengersList.size() != 0) {

                            System.out.println(OllivandersHotel.getActivePassengers() + "\n");
                        } else {

                            System.out.println("The is/are no active Passenger/s\n");
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("\n--------------------\nSee Former Passenger\n--------------------\n");

                        List<User> formerPassengersList = OllivandersHotel.getFormerPassengers();
                        if (formerPassengersList.size() != 0) {

                            System.out.println(OllivandersHotel.getFormerPassengers() + "\n");
                        } else {

                            System.out.println("The is/are no former Passenger/s\n");
                        }
                        break;
                    }
                    case 7: {
                        System.out.println("\n------------------\nSearch User\n------------------\n");
                        System.out.print("[1]. Search User by ID\n[2]. Search User by Full Name\n\nOption: ");
                        int searchOption = scan.nextInt();

                        if (searchOption == 1) {

                            System.out.print("Enter User ID: ");
                            String dni = scan.next();

                            if (OllivandersHotel.getUserByID(dni) != null) {

                                System.out.println(OllivandersHotel.getUserByID(dni) + "\n");
                            } else {

                                System.out.println("\nUser not found\n");
                            }
                        } else if (searchOption == 2) {

                            System.out.print("Enter First Name: ");
                            String firstName = scan.next();
                            System.out.print("Enter Last Name: ");
                            String lastName = scan.next();

                            if (OllivandersHotel.getUserByFullName(firstName, lastName) != null) {

                                System.out.println(OllivandersHotel.getUserByFullName(firstName, lastName) + "\n");
                            } else {

                                System.out.println("\nUser not found\n");
                            }
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
                        System.out.println("Please, choose a valid option\n");
                        break;
                    }
                }
            } catch (InputMismatchException ime) {

                System.err.println("Validation Error.");
                System.err.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
                scan.next();
            }
        }
    }
}
