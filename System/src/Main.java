import controller.Hotel;
import menues.MenuManager;
import menues.MenuReceptionist;
import model.*;
import util.Log;
import menues.MenuPassenger;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Hotel OlivandersHotel = new Hotel();

        OlivandersHotel.register(new Manager("35892293", "Daiana", "Olivera", 30, Gender.FEMALE, "Solis 5526", "2235566592", "oliveratup@gmail.com", "pass456"));
        OlivandersHotel.register(new Manager("39098436", "Walter", "Moretti", 25, Gender.MALE, "Dardo Rocha 70", "2235484378", "wally.moretti@gmai.com", "tuvieja123"));

        int z = 0, option = 0;

        String dni, firstName, lastName, address, telephone, email, password, origin;
        int age = 0, genderOption = 0;
        Gender gender = null;

        while (z == 0) {

            System.out.println("\nWelcome to Olivanders Hotel\n===========================\n");
            System.out.println("1. Register\n2. Log In");
            System.out.println("0. Exit\n");
            System.out.print("Option: ");
            System.out.flush();
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

                    if (age >= 18) {

                        while (genderOption == 0 || genderOption > 4) {
                            System.out.print("Gender (1. Male, 2. Female, 3. Other, 4. N/A): ");
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
                        address = scan.nextLine();
                        address = scan.nextLine();
                        System.out.print("Telephone: ");
                        telephone = scan.next();
                        System.out.print("Email: ");
                        email = scan.next();
                        if (email.contains("@")) {

                            System.out.print("Password: ");
                            password = scan.next();
                            System.out.print("Origin (City): ");
                            origin = scan.nextLine();
                            origin = scan.nextLine();


                            User user = new Passenger(dni, firstName, lastName, age, gender, address, telephone, email, password, origin);

                            if (OlivandersHotel.register(user) == true) {

                                System.out.println("\nUser successfully registered\n");
                            } else {

                                System.out.println("\nUser already exist. Please, try to log in\n");
                            }
                        } else {

                            System.out.println("\nNot a valid email\n");
                        }
                    } else {

                        System.out.println("\nYou need to be at least 18 years old to register\n");
                    }
                    break;
                }
                case 2: {

                    System.out.println("\nPlease, enter your DNI and Password\n");
                    System.out.print("DNI: ");
                    dni = scan.next();
                    System.out.print("Password: ");
                    password = scan.next();

                    User loggedUser = Log.LogIn(dni, password, OlivandersHotel.getUsers());
                    if (loggedUser != null) {

                        if (loggedUser instanceof Passenger) {

                            System.out.println("\nPassenger found. Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName() + " to Hotel Olivanders!\n");
                            MenuPassenger.menuPassenger(scan, OlivandersHotel, loggedUser);
                        } else if (loggedUser instanceof Receptionist) {

                            System.out.println("User found. Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName() + "\n");
                            MenuReceptionist.menuReceptionist(scan, OlivandersHotel, loggedUser);
                        } else {

                            System.out.println("User found. Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName() + "\n");
                            MenuManager.menuManager(scan, OlivandersHotel, loggedUser);
                        }
                    } else {

                        System.out.println("\nUser not found. Please register or try to log in again\n");
                    }
                    break;
                }
                case 742617000: {

                    System.out.println("1. New Manager\n2. New Receptionist\n");
                    break;
                }
                case 0: {
                    System.out.println("\nSee you soon :)\n");
                    z++;
                    break;
                }
                default: {
                    System.out.println("\nPlease, choose a valid option\n");
                    break;
                }
            }
        }
        scan.close();

        /*User user1 = new Manager("35892293"
                , "Daiana"
                , "Olivera"
                , 30
                , Gender.FEMALE
                , "Solis 5526"
                , "2235566592"
                , "oliveratup@gmail.com"
                , "pass456");

        user1.setActive();
        hotel.register(user1);

        User user2 = Log.LogIn("35892293", "pass456", hotel.getUsers());
        if (user2 != null) {
            System.out.println("User found: " + user2.toString());

        }

        //  System.out.println(hotel1.logInHotel(user1.getDni(), user1.getPassword()));
        //   System.out.println(hotel1.register(user1));
        //  System.out.println(hotel1.getUsersData());
        //  System.out.println(hotel1.logInHotel(user1.getDni(), user1.getPassword()));

        System.out.println("Former employee: " + hotel.getFormerEmployees().toString());*/
    }
}
