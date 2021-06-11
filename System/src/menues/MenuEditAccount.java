package menues;

import controller.Hotel;
import exception.ActiveBookingException;
import exception.InvalidNumberValidationException;
import exception.InvalidStringException;
import exception.UserNotFoundException;
import model.*;

import java.util.Scanner;

public class MenuEditAccount {

    public static Hotel menuEditAccount(Scanner scan, Hotel OllivandersHotel, User user) {

        int z = 0, option = 0;

        String dni, firstName, lastName, address, telephone, email, password, password2;
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
                            OllivandersHotel.changeFullName(user.getDni(), firstName, lastName);
                            System.out.println("\nChanges successfully made.\n");
                        } catch (UserNotFoundException e) {

                            System.out.println("\n" + e.getMessage() + "\n");
                        } catch (InvalidStringException e) {
                            System.out.println("\n" + e.getMessage() + "\n");
                        } catch (ActiveBookingException e) {
                            System.out.println("\n" + e.getMessage() + "\n");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\nEdit Age\n");

                        System.out.print("Enter new age: ");
                        age = scan.nextInt();

                        OllivandersHotel.changeAge(user.getDni(), age);
                        System.out.println("\nChanges successfully made.\n");
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
                        OllivandersHotel.changeGender(user.getDni(), gender);
                        System.out.println("\nChanges successfully made.\n");
                        break;
                    }
                    case 5: {
                        System.out.println("\nEdit Address\n");

                        System.out.print("Enter new address: ");
                        scan.nextLine();
                        address = scan.nextLine();

                        OllivandersHotel.changeAddress(user.getDni(), address);
                        System.out.println("\nChanges successfully made.\n");
                        break;
                    }
                    case 6: {
                        System.out.println("\nEdit Telephone\n");

                        System.out.print("Enter new telephone: ");
                        telephone = scan.next();

                        OllivandersHotel.changeTelephone(user.getDni(), telephone);
                        System.out.println("\nChanges successfully made.\n");
                        break;
                    }
                    case 7: {
                        System.out.println("\nEdit email\n");

                        System.out.print("Enter new email: ");
                        email = scan.next();

                        OllivandersHotel.changeEmail(user.getDni(), email);
                        System.out.println("\nChanges successfully made.\n");
                        break;
                    }
                    case 8: {
                        System.out.println("\nEdit Password\n");

                        System.out.print("New Password: ");
                        password = scan.next();
                        System.out.print("Enter your password again: ");
                        password2 = scan.next();
                        if (password.equals(password2)) {

                            OllivandersHotel.changePassword(user.getDni(), password);
                            System.out.println("\nChanges successfully made.\n");
                        } else {

                            System.out.println("\nThe passwords do not match\n");
                        }
                        break;
                    }
                    case 9: {
                        if (user instanceof Manager) {

                            System.out.println("\nEdit Receptionist Shift\n");

                            System.out.print("Enter the Receptionist DNI: ");
                            dni = scan.next();
                            User rUser = OllivandersHotel.searchUserById(dni);
                            if (rUser != null && rUser instanceof Receptionist) {

                                while (shiftOption <= 0 || shiftOption > 3) {
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
                                shiftOption = 0;
                                OllivandersHotel.changeReceptionistShift(rUser.getDni(), shift);
                                System.out.println("\nChanges successfully made.\n");
                            } else {
                                throw new UserNotFoundException();
                            }
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
            } catch (InvalidStringException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (UserNotFoundException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (InvalidNumberValidationException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
        }
        return OllivandersHotel;
    }
}
