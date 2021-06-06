package menues;

import controller.Hotel;
import model.Gender;
import model.Manager;
import model.Shift;
import model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuEditAccount {

    public static void menuEditAccount(Scanner scan, Hotel OlivandersHotel, User user) {

        int z = 0, option = 0;

        String dni, firstName, lastName, address, telephone, email, password, origin;
        int age = 0, genderOption = 0, shiftOption = 0;
        Gender gender = null;
        Shift shift = null;

        while (z == 0) {

            System.out.println("\nMenu Edit Account\n==============\n");
            System.out.println("1. See my Info");
            System.out.println("2. Edit Full Name\n3. Edit Age\n4. Edit Gender");
            System.out.println("5. Edit Address\n6. Edit Telephone");
            System.out.println("7. Edit email\n8. Edit Password");
            if (user instanceof Manager) {

                System.out.println("9. Edit Receptionist Shift");
            }
            System.out.println("\n0. Back");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\nSee my Info\n");

                        System.out.println("\n" + user.toString() + "\n");
                        break;
                    }
                    case 2: {
                        System.out.println("\nEdit Full Name\n");

                        System.out.print("Enter new first name: ");
                        firstName = scan.next();
                        System.out.print("Enter new last name: ");
                        lastName = scan.next();

                        System.out.println("\n" + OlivandersHotel.changeFullName(user.getDni(), firstName, lastName + "\n"));
                        break;
                    }
                    case 3: {
                        System.out.println("\nEdit Age\n");

                        System.out.print("Enter new age: ");
                        age = scan.nextInt();

                        System.out.println("\n" + OlivandersHotel.changeAge(user.getDni(), age) + "\n");
                        break;
                    }
                    case 4: {
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

                        System.out.println("\n" + OlivandersHotel.changeGender(user.getDni(), gender) + "\n");
                        break;
                    }
                    case 5: {
                        System.out.println("\nEdit Address\n");

                        System.out.print("Enter new address: ");
                        address = scan.nextLine();
                        address = scan.nextLine();

                        System.out.println("\n" + OlivandersHotel.changeAddress(user.getDni(), address + "\n"));
                        break;
                    }
                    case 6: {
                        System.out.println("\nEdit Telephone\n");

                        System.out.print("Enter new telephone: ");
                        telephone = scan.next();

                        System.out.println("\n" + OlivandersHotel.changeTelephone(user.getDni(), telephone + "\n"));
                        break;
                    }
                    case 7: {
                        System.out.println("\nEdit email\n");

                        System.out.print("Enter new email: ");
                        email = scan.next();

                        System.out.println("\n" + OlivandersHotel.changeEmail(user.getDni(), email) + "\n");
                        break;
                    }
                    case 8: {
                        System.out.println("\nEdit Password\n");

                        System.out.print("New Password: ");
                        password = scan.next();

                        System.out.println("\n" + OlivandersHotel.changePassword(user.getDni(), password) + "\n");
                        break;
                    }
                    case 9: {
                        if (user instanceof Manager) {

                            System.out.println("\nEdit Receptionist Shift\n");

                            while (shiftOption == 0 || shiftOption > 3) {
                                System.out.print("Shift (1. Morning, 2. Afternoon, 3. Night): ");
                                shiftOption = scan.nextInt();
                                switch (shiftOption) {
                                    case 1: {
                                        shift = Shift.MORNING;
                                        break;
                                    }
                                    case 2: {
                                        shift = Shift.AFTERNOON;
                                        break;
                                    }
                                    case 3: {
                                        shift = Shift.NIGHT;
                                        break;
                                    }
                                    default: {
                                        System.out.println("\nPlease select a valid option number\n");
                                        break;
                                    }
                                }
                            }

                            System.out.println("\n" + OlivandersHotel.changeReceptionistShift(user.getDni(), shift) + "\n");
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
            } catch (InputMismatchException ime) {

                System.err.println("Please, enter only numbers.");
                System.err.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
                scan.next();
            }
        }
    }
}
