package menues;

import controller.Hotel;
import model.Gender;
import model.Manager;
import model.User;

import java.util.Scanner;

public class MenuEditAccount {

    public static void menuEditAccount(Scanner scan, Hotel OlivandersHotel, User user) {

        int z = 0, option = 0;

        String dni, firstName, lastName, address, telephone, email, password, origin;
        int age = 0, genderOption = 0;
        Gender gender = null;

        while (z == 0) {

            System.out.println("\nMenu Edit Account\n==============\n");
            System.out.println("1. Edit Full Name\n2. Edit Age\n3. Edit Gender");
            System.out.println("4. Edit Address\n5. Edit Telephone\n6. Edit email");
            System.out.println("7. Edit Password");
            if (user instanceof Manager) {

                System.out.println("8. Edit Receptionist Shift");
            }
            System.out.println("\n0. Back");
            System.out.print("Option: ");
            System.out.flush();
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nEdit Full Name\n");
                    System.out.print("Enter your first name: ");
                    firstName = scan.next();
                    System.out.print("Enter your last name: ");
                    lastName = scan.next();

                    // TODO We need to create a new method. Change methods maybe wrong.

                    break;
                }
                case 2: {
                    System.out.println("\nEdit Age\n");
                    System.out.print("Enter your age: ");
                    age = scan.nextInt();

                    // TODO We need to create a new method. Change methods maybe wrong.

                    break;
                }
                case 3: {
                    System.out.println("\nEdit Gender\n");
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

                    // TODO We need to create a new method. Change methods maybe wrong.

                    break;
                }
                case 4: {
                    System.out.println("\nEdit Address\n");
                    System.out.print("Enter new address: ");
                    address = scan.nextLine();
                    address = scan.nextLine();

                    // TODO We need to create a new method. Change methods maybe wrong.

                    break;
                }
                case 5: {
                    System.out.println("\nEdit Telephone\n");
                    System.out.print("Enter new telephone: ");
                    telephone = scan.next();

                    // TODO We need to create a new method. Change methods maybe wrong.

                }
                case 6: {
                    System.out.println("\nEdit email\n");
                    System.out.print("Enter new email: ");
                    email = scan.next();

                    // TODO We need to create a new method. Change methods maybe wrong.

                    break;
                }
                case 7: {
                    System.out.println("\nEdit Password\n");
                    System.out.print("New Password: ");

                    // TODO We need to create a new method. Change methods maybe wrong.

                    break;
                }
                case 8: {
                    if (user instanceof Manager) {

                        System.out.println("\nEdit Receptionist Shift\n");

                        // TODO Dont know is there is a method for this already.

                    } else {

                        System.out.println("\nPlease, choose a valid option\n");
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
