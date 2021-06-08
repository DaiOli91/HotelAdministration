import controller.Hotel;
import exception.BookingNotFoundException;
import exception.DateValidationException;
import exception.UserAlreadyRegisteredException;
import menues.MenuManager;
import menues.MenuReceptionist;
import model.*;
import util.Log;
import menues.MenuPassenger;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Hotel OllivandersHotel = null;
        try {

            OllivandersHotel = new Hotel("Ollivanders", "Belgrano 3998", "4758996");
        } catch (IOException e) {

            System.out.println("\n" + e.getMessage() + "\n");
        }

        int z = 0, option;

        String dni, firstName, lastName, address, telephone, email, password, origin;
        int age, genderOption = 0;
        Gender gender = null;

        while (z == 0) {

            System.out.println("\nWelcome to hotel Ollivanders Hotel\n===========================\n");
            System.out.println("[1]. Register\n[2]. Log In\n");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\nPlease, complete the next form\n");
                        System.out.print("DNI: ");
                        dni = scan.next();
                        System.out.print("First Name: ");
                        firstName = scan.next();
                        System.out.print("Last Name: ");
                        lastName = scan.next();
                        System.out.print("Age: ");
                        age = scan.nextInt();

                        if (age >= 18 && age < 100) {

                            while (genderOption <= 0 || genderOption > 4) {
                                System.out.print("Gender\n¯¯¯¯¯¯¯\n[1]. Male\n[2]. Female\n[3]. Other\n[4]. N/A\n\nOption: ");
                                genderOption = scan.nextInt();
                                switch (genderOption) {
                                    case 1: {
                                        gender = Gender.MALE;
                                        break;
                                    }
                                    case 2: {
                                        gender = Gender.FEMALE;
                                        break;
                                    }
                                    case 3: {
                                        gender = Gender.OTHER;
                                        break;
                                    }
                                    case 4: {

                                        gender = Gender.NA;
                                        break;
                                    }
                                    default: {

                                        System.out.println("\nPlease select a valid option number\n");
                                        break;
                                    }
                                }
                            }
                            System.out.print("Address: ");
                            scan.nextLine();
                            address = scan.nextLine();
                            System.out.print("Telephone: ");
                            telephone = scan.next();
                            System.out.print("Email: ");
                            email = scan.next();
                            if (email.contains("@")) {

                                System.out.print("Password: ");
                                password = scan.next();
                                System.out.print("Origin (City): ");
                                scan.nextLine();
                                origin = scan.nextLine();


                                User user = new Passenger(dni, firstName, lastName, age, gender, address, telephone, email, password, origin);

                                try {

                                    OllivandersHotel.register(user);
                                    //TODO Register now is void....only prints message if an exception is thrown... would you like to show a message in case that the user is added successfully?

                                } catch (IOException e) {

                                    e.printStackTrace();
                                } catch (UserAlreadyRegisteredException e) {
                                    e.printStackTrace();
                                } catch (BookingNotFoundException e) {
                                    e.printStackTrace();
                                } catch (DateValidationException e) {
                                    e.printStackTrace();
                                }
                            } else {

                                System.out.println("\nNot a valid email\n");
                            }
                        } else {

                            System.out.println("\nYou need to be between 18 and 99 years old\n");
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("\nPlease, enter your DNI and Password\n");
                        System.out.print("DNI: ");
                        dni = scan.next();
                        System.out.print("Password: ");
                        password = scan.next();

                        User loggedUser = null;
                        try {
                            loggedUser = Log.logIn(dni, password, OllivandersHotel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (loggedUser != null) {

                            if (loggedUser instanceof Passenger) {

                                System.out.println("\nPassenger found. Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName() + " to Hotel Ollivanders!\n");
                                MenuPassenger.menuPassenger(scan, OllivandersHotel, loggedUser);
                            } else if (loggedUser instanceof Receptionist) {

                                System.out.println("User found. Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName() + "\n");
                                MenuReceptionist.menuReceptionist(scan, OllivandersHotel, loggedUser);
                            } else {

                                System.out.println("User found. Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName() + "\n");
                                MenuManager.menuManager(scan, OllivandersHotel, loggedUser);
                            }
                        } else {

                            System.out.println("\nUser not found or user deactivated. Please register or try to log in again.");
                            System.out.println("If your account was deactivated, please reach one of our managers\n");
                        }
                        break;
                    }
                    case 0: {
                        System.out.println("\nSee you soon :)\n");
                        try {
                            OllivandersHotel.saveData();
                        } catch (IOException e) {
                            System.out.println("\n" + e.getMessage() + "\n");
                        }
                        z++;
                        break;
                    }
                    default: {
                        System.out.println("\nPlease, choose a valid option\n");
                        break;
                    }
                }
            } catch (InputMismatchException ime) {

                System.err.println("Validation Error.");
                System.err.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
                scan.next();
            }
        }
        scan.close();
    }
}
