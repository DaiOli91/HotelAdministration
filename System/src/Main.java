import Controller.Hotel;
import Model.*;
import Repository.UserRepository;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Hotel OlivandersHotel = new Hotel();

        UserRepository userRepository = new UserRepository();
        userRepository.add(new Manager("35892293", "Daiana", "Olivera", 30, Gender.FEMALE, "Solis 5526", "2235566592", "oliveratup@gmail.com", "pass456"));
        userRepository.add(new Manager("39098436", "Walter", "Moretti", 25, Gender.MALE, "Dardo Rocha 70", "2235484378", "wally.moretti@gmai.com", "tuvieja213"));

        int z = 0, option = 0;

        String dni, firstName, lastName, address, telephone, email, password, origin;
        int age = 0, genderOption = 0;
        Gender gender = null;

        while (z == 0) {

            System.out.println("Welcome to Olivanders Hotel\n===========================\n");
            System.out.println("1. Register\n2. Log In");
            System.out.println("0. Exit\n");
            System.out.println("Option: ");
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("Please, complete the next form\n");
                    System.out.println("DNI: ");
                    dni = scan.next();
                    System.out.println("First Name: ");
                    firstName = scan.next();
                    System.out.println("Last Name: ");
                    lastName = scan.next();
                    System.out.println("Age: ");
                    age = scan.nextInt();

                    if (age >= 18) {

                        System.out.println("Gender (1. Male, 2. Female, 3. Other, 4. N/A): ");
                        genderOption = scan.nextInt();
                        switch (genderOption) {
                            case 1: {
                                gender = Gender.MALE;
                            }
                            case 2: {
                                gender = Gender.FEMALE;
                                break;
                            }
                            case 3: {
                                gender = Gender.OTHER;
                            }
                            case 4: {

                                gender = Gender.NA;
                            }
                            default: {

                                System.out.println("Please select a valid option number\n");
                                break;
                            }
                        }
                        System.out.println("Address: ");
                        address = scan.nextLine();
                        address = scan.nextLine();
                        System.out.println("Telephone: ");
                        telephone = scan.next();
                        System.out.println("Email: ");
                        email = scan.next();
                        if (email.contains("@")) {

                            System.out.println("Password: ");
                            password = scan.next();
                            System.out.println("Origin (City): ");
                            origin = scan.nextLine();
                            origin = scan.nextLine();


                            User user = new Passenger(dni, firstName, lastName, age, gender, address, telephone, email, password, origin);
                            if (OlivandersHotel.register(user) == true) {

                                System.out.println("User successfully registered\n");
                            } else {

                                System.out.println("User already exist. Please, try to log in\n");
                            }
                        } else {

                            System.out.println("Not a valid email\n");
                        }
                    } else {

                        System.out.println("You need to be at least 18 years old to register\n");
                    }
                    break;
                }
                case 2: {

                    break;
                }
                case 742617000: {

                    System.out.println("1. New Manager\n2. New Receptionist\n");
                    break;
                }
                case 0: {
                    System.out.println("See you soon :)\n");
                    z++;
                    break;
                }
                default: {
                    System.out.println("Please, choose a valid option\n");
                    break;
                }
            }
        }


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
