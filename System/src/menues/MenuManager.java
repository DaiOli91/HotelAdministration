package menues;

import controller.Hotel;
import exception.*;
import model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {

    public static Hotel menuManager(Scanner scan, Hotel OllivandersHotel, User loggedUser) {

        int z = 0, option;

        int age, genderOption = 0, employeeOption, shiftOption, activeDeactiveOption, idRoom, categoryOption = 0;
        String dni, firstName, lastName, address, telephone, email, password;
        Gender gender = null;
        Shift shift;
        Category category = null;

        while (z == 0) {

            System.out.println("\nMenu Manager\n==========\n");
            System.out.println("[1]. Add Employee\n[2]. See Users\n[3]. Edit Account\n[4]. Activate/Deactivate User");
            System.out.println("[5]. Add Room\n[6]. See Rooms\n[7]. Edit Room Category\n[8]. Activate/Deactivate Room\n");
            System.out.println("[0]. Log Out");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\nAdd Employee\n");

                        try {

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

                                while (genderOption <= 0 || genderOption > 4) {
                                    System.out.print("Gender\n¯¯¯¯¯¯\n[1]. Male\n[2]. Female\n[3]. Other\n[4.] N/A\n\nOption: ");
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

                                    System.out.print("New Employee\n¯¯¯¯¯¯¯¯¯¯\n[1]. Manager\n[2]. Receptionist\n\nOption: ");
                                    employeeOption = scan.nextInt();

                                    if (employeeOption == 1) {

                                        User mUser = new Manager(dni, firstName, lastName, age, gender, address, telephone, email, password);
                                        OllivandersHotel.register(mUser);
                                        System.out.println("\nUser added successfully\n");
                                    } else if (employeeOption == 2) {

                                        System.out.print("Shift\n¯¯¯¯¯¯\n[1]. Morning\n[2]. Afternoon\n[3]. Night\n\nOption: ");
                                        shiftOption = scan.nextInt();

                                        if (shiftOption == 1) {

                                            shift = Shift.MORNING;
                                            User rUser = new Receptionist(dni, firstName, lastName, age, gender, address, telephone, email, password, shift);
                                            OllivandersHotel.register(rUser);
                                            System.out.println("\nUser added successfully\n");
                                        } else if (shiftOption == 2) {

                                            shift = Shift.AFTERNOON;
                                            User rUser = new Receptionist(dni, firstName, lastName, age, gender, address, telephone, email, password, shift);
                                            OllivandersHotel.register(rUser);
                                            System.out.println("\nUser added successfully\n");
                                        } else if (shiftOption == 3) {

                                            shift = Shift.NIGHT;
                                            User rUser = new Receptionist(dni, firstName, lastName, age, gender, address, telephone, email, password, shift);
                                            OllivandersHotel.register(rUser);
                                            System.out.println("\nUser added successfully\n");
                                        } else {

                                            System.out.println("\nNot a valid option. Please, try again later\n");
                                        }
                                    } else {

                                        System.out.println("\nNot a valid option. Please, try again later\n");
                                    }
                                } else {

                                    System.out.println("\nNot a valid email\n");
                                }
                            } else {

                                System.out.println("\nNeeds to be at least 18 years old to add a new employee\n");
                            }
                        } catch (UserAlreadyRegisteredException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("\nSee Users\n");
                        MenuSeeUsers.menuSeeUsers(scan, OllivandersHotel);
                        break;
                    }
                    case 3: {
                        System.out.println("\nEdit Account\n");

                        System.out.print("[1]. Edit my Account or Receptionist Shift\n[2]. Edit User Account\n\nOption: ");
                        int editOption = scan.nextInt();

                        if (editOption == 1) {

                            OllivandersHotel = MenuEditAccount.menuEditAccount(scan, OllivandersHotel, loggedUser);
                        } else if (editOption == 2) {

                            System.out.print("Enter the Passenger DNI: ");
                            dni = scan.next();
                            User pUser = OllivandersHotel.searchUserById(dni);
                            if (pUser != null) {

                                OllivandersHotel = MenuEditAccount.menuEditAccount(scan, OllivandersHotel, pUser);
                            } else {

                                try {
                                    throw new UserNotFoundException();
                                } catch (UserNotFoundException e) {

                                    System.out.println("\n" + e.getMessage() + "\n");
                                }
                            }
                        } else {

                            System.out.println("\nPlease, choose a valid option\n");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\nActivate/Deactivate User\n");

                        System.out.print("Enter user's DNI: ");
                        dni = scan.next();
                        System.out.print("\n[1]. Activate User\n[2]. Deactivate User\n\nOption: ");
                        activeDeactiveOption = scan.nextInt();

                        if (activeDeactiveOption == 1) {

                            OllivandersHotel.activateAccount(dni);
                            User auxUser = OllivandersHotel.searchUserById(dni);
                            if (auxUser.getActive()) {

                                System.out.println("\nThe account has been activated\n");
                            }
                        } else if (activeDeactiveOption == 2) {

                            try {
                                OllivandersHotel.deactivateAccount(dni);
                                User auxUser = OllivandersHotel.searchUserById(dni);
                                if (!auxUser.getActive()) {

                                    System.out.println("\nThe account has been deactivated\n");
                                }
                            } catch (ActiveBookingException e) {

                                System.out.println("\nThis user cannot be deactivated. " + e.getMessage() + "\n");
                            }
                        } else {

                            System.out.println("\nNot a valid option\n");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("\nAdd Room\n");

                        while (categoryOption <= 0 || categoryOption > 4) {

                            System.out.print("Category\n¯¯¯¯¯¯¯¯¯\n[1]. Guest\n[2]. Junior\n[3]. Presidential\n[4]. Executive\n\nOption: ");
                            categoryOption = scan.nextInt();
                            switch (categoryOption) {
                                case 1: {
                                    category = Category.GUEST;
                                    break;
                                }
                                case 2: {
                                    category = Category.JUNIOR;
                                    break;
                                }
                                case 3: {
                                    category = Category.PRESIDENTIAL;
                                    break;
                                }
                                case 4: {
                                    category = Category.EXECUTIVE;
                                    break;
                                }
                                default: {

                                    System.out.println("\nPlease select a valid option number\n");
                                    break;
                                }
                            }
                        }
                        categoryOption = 0;
                        System.out.println("\n" + OllivandersHotel.createRoom(category, Availability.FREE) + "\n");
                        break;
                    }
                    case 6: {
                        System.out.println("\nSee Rooms\n");
                        MenuSeeRooms.menuSeeRooms(scan, OllivandersHotel);
                        break;
                    }
                    case 7: {
                        System.out.println("\nEdit Room Category\n");

                        System.out.print("Enter ID Room: ");
                        idRoom = scan.nextInt();

                        while (categoryOption <= 0 || categoryOption > 4) {

                            System.out.print("Category\n¯¯¯¯¯¯¯¯¯\n[1]. Guest\n[2]. Junior\n[3]. Presidential\n[4]. Executive\n\nOption: ");
                            categoryOption = scan.nextInt();
                            switch (categoryOption) {
                                case 1: {
                                    category = Category.GUEST;
                                    break;
                                }
                                case 2: {
                                    category = Category.JUNIOR;
                                    break;
                                }
                                case 3: {
                                    category = Category.PRESIDENTIAL;
                                    break;
                                }
                                case 4: {
                                    category = Category.EXECUTIVE;
                                    break;
                                }
                                default: {

                                    System.out.println("\nPlease select a valid option number\n");
                                    break;
                                }
                            }
                        }
                        categoryOption = 0;
                        try {
                            OllivandersHotel.changeRoomCategory(idRoom, category);
                            System.out.println("\nThe changes were made successfully\n");

                        } catch (RoomNotFoundException e) {
                            System.out.println("\n" + e.getMessage() + "\n");
                        }
                        break;
                    }
                    case 8: {
                        System.out.println("\nActivate/Deactivate Room\n");

                        System.out.print("Please enter ID Room: ");
                        idRoom = scan.nextInt();
                        System.out.print("\n[1]. Activate Room\n[2]. Deactivate Room\n\nOption: ");
                        activeDeactiveOption = scan.nextInt();

                        if (activeDeactiveOption == 1) {

                            OllivandersHotel.activateRoom(idRoom);
                            System.out.println("\nRoom activated\n");

                        } else if (activeDeactiveOption == 2) {
                            OllivandersHotel.deactivateRoom(idRoom);
                            System.out.println("\nRoom deactivated\n");
                        }
                        break;
                    }
                    case 0: {
                        System.out.println("\nLogged out successfully\n");
                        z++;
                        break;
                    }
                    default: {
                        System.out.println("\nPlease, choose a valid option\n");
                        break;
                    }
                }
            } catch (InputMismatchException ime) {

                System.out.println("\n" + ime.getMessage() + "\n");
            } catch (ReceptionistShiftNeedsChange e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (UserActiveDeactiveException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (UserNotFoundException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (RoomNotFoundException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (ActiveRoomException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (ActiveBookingException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
        }
        return OllivandersHotel;
    }
}
