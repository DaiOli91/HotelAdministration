package menues;

import controller.Hotel;
import exception.ActiveBookingException;
import exception.InvalidNumberValidationException;
import exception.InvalidStringException;
import exception.UserNotFoundException;
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
            System.out.println("1. See Info");
            System.out.println("2. Edit Full Name\n3. Edit Age\n4. Edit Gender");
            System.out.println("5. Edit Address\n6. Edit Telephone");
            System.out.println("7. Edit email\n8. Edit Password");
            if (user instanceof Manager) {

                System.out.println("9. For Receptionist: Edit Shift");
            }
            System.out.println("\n0. Back");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\nSee Info\n");

                        System.out.println(user.toString() + "\n");
                        break;
                    }
                    case 2: {
                        System.out.println("\nEdit Full Name\n");

                        System.out.println("Please do not enter numbers, spaces or special character");
                        System.out.print("Enter new first name: ");
                        firstName = scan.next();
                        System.out.print("Enter new last name: ");
                        lastName = scan.next();

                        try {
                            OlivandersHotel.changeFullName(user.getDni(), firstName, lastName);
                            //TODO possibly add something like @you cannot change your name@ before active booking exception message
                        } catch (UserNotFoundException e) {

                            System.out.println("\n" + e.getMessage() + "\n");
                        } catch (InvalidStringException e) {
                            e.printStackTrace();
                        } catch (ActiveBookingException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\nEdit Age\n");

                        System.out.print("Enter new age: ");
                        age = scan.nextInt();

                        OlivandersHotel.changeAge(user.getDni(), age);
                        //TODO probably show a message if everything is ok?
                        break;
                    }
                    case 4: {
                        System.out.println("\nEdit Gender\n");

                        while (genderOption <= 0 || genderOption > 4) {
                            System.out.print("Gender\n¯¯¯¯¯¯\n[1]. Male\n[2]. Female\n[3]. Other\n[4]. N/A\n\nOption: ");
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
                        genderOption = 0;
                        OlivandersHotel.changeGender(user.getDni(), gender);
                        //TODO possibly print a message if everything went Ok?
                        break;
                    }
                    case 5: {
                        System.out.println("\nEdit Address\n");

                        System.out.print("Enter new address: ");
                        scan.nextLine();
                        address = scan.nextLine();

                        OlivandersHotel.changeAddress(user.getDni(), address);
                        //TODO possibly show an OK message
                        break;
                    }
                    case 6: {
                        System.out.println("\nEdit Telephone\n");

                        System.out.print("Enter new telephone: ");
                        telephone = scan.next();

                        OlivandersHotel.changeTelephone(user.getDni(), telephone);
                        //TODO success message
                        break;
                    }
                    case 7: {
                        System.out.println("\nEdit email\n");

                        System.out.print("Enter new email: ");
                        email = scan.next();

                        OlivandersHotel.changeEmail(user.getDni(), email);
                        //TODO success message
                        break;
                    }
                    case 8: {
                        System.out.println("\nEdit Password\n");

                        System.out.print("New Password: ");
                        password = scan.next();

                        OlivandersHotel.changePassword(user.getDni(), password);
                        //TODO success message
                        break;
                    }
                    case 9: {
                        if (user instanceof Manager) {

                            System.out.println("\nEdit Receptionist Shift\n");

                            while (shiftOption == 0 || shiftOption > 3) {
                                System.out.println("If you are not a receptionist, this change will not apply");
                                System.out.print("Shift\n¯¯¯¯¯¯\n[1]. Morning\n[2]. Afternoon\n[3]. Night\n\nOption: ");
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

                           OlivandersHotel.changeReceptionistShift(user.getDni(), shift);
                            //TODO Success message
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
            } catch (InputMismatchException | UserNotFoundException | InvalidNumberValidationException | InvalidStringException ime) {

                System.err.println("Validation Error.");
                System.err.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
                scan.next();
            }
        }
    }
}
