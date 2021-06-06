package menues;

import controller.Hotel;
import model.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {

    public static void menuManager(Scanner scan, Hotel OllivandersHotel, User loggedUser) {

        int z = 0, option;

        int age, genderOption = 0, employeeOption, shiftOption, activeDeactiveOption, idRoom, categoryOption = 0;
        String dni, firstName, lastName, address, telephone, email, password;
        Gender gender = null;
        Shift shift;
        Category category = null;

        while (z == 0) {

            System.out.println("\nMenu Manager\n==========\n");
            System.out.println("[1]. Add Employee\n[2]. See Users\n[3]. Edit Account\n[4]. Activate/Deactivate User");
            System.out.println("[5]. Add Room\n[6]. See Rooms\n[7]. Edit Room Category\n[8]. Deactivate Room\n");
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
                                scan.nextLine();
                                address = scan.nextLine();
                                System.out.print("Telephone: ");
                                telephone = scan.next();
                                System.out.print("Email: ");
                                email = scan.next();

                                if (email.contains("@")) {

                                    System.out.print("Password: ");
                                    password = scan.next();

                                    System.out.print("New Employee (1. Manager, 2. Receptionist): ");
                                    employeeOption = scan.nextInt();

                                    if (employeeOption == 1) {

                                        User mUser = new Manager(dni, firstName, lastName, age, gender, address, telephone, email, password);
                                        System.out.println(OllivandersHotel.register(mUser));
                                    } else if (employeeOption == 2) {

                                        System.out.print("Shift (1. Morning, 2. Afternoon, 3. Night): ");
                                        shiftOption = scan.nextInt();

                                        if (shiftOption == 1) {

                                            shift = Shift.MORNING;
                                            User rUser = new Receptionist(dni, firstName, lastName, age, gender, address, telephone, email, password, shift);
                                            System.out.println(OllivandersHotel.register(rUser));
                                        } else if (shiftOption == 2) {

                                            shift = Shift.AFTERNOON;
                                            User rUser = new Receptionist(dni, firstName, lastName, age, gender, address, telephone, email, password, shift);
                                            System.out.println(OllivandersHotel.register(rUser));
                                        } else if (shiftOption == 3) {

                                            shift = Shift.NIGHT;
                                            User rUser = new Receptionist(dni, firstName, lastName, age, gender, address, telephone, email, password, shift);
                                            System.out.println(OllivandersHotel.register(rUser));
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
                        } catch (IOException e) {

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

                        System.out.print("1. Edit my Account\n2. Edit Receptionist Account\n3. Edit Passenger Account\n");
                        int editOption = scan.nextInt();

                        if (editOption == 1) {

                            MenuEditAccount.menuEditAccount(scan, OllivandersHotel, loggedUser);
                        } else if (editOption == 2) {


                            System.out.print("Enter the Passenger DNI: ");
                            dni = scan.next();
                            User rUser = new Receptionist(dni, "xxxx", "xxxx", 18, Gender.NA, "xxxx", "1234", "email@gmail.com", "1234", Shift.UNASIGNED);

                            MenuEditAccount.menuEditAccount(scan, OllivandersHotel, rUser);
                        } else if (editOption == 3) {

                            System.out.print("Enter the Passenger DNI: ");
                            dni = scan.next();
                            User pUser = new Passenger(dni, "xxxx", "xxxx", 18, Gender.NA, "xxxx", "1234", "email@gmail.com", "1234", "xxxx");

                            MenuEditAccount.menuEditAccount(scan, OllivandersHotel, pUser);
                        } else {

                            System.out.println("\nPlease, choose a valid option\n");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\nActivate/Deactivate User\n");

                        System.out.print("Enter user's DNI: ");
                        dni = scan.next();
                        System.out.println("\n1. Activate User\n2. Deactivate User");
                        activeDeactiveOption = scan.nextInt();

                        if (activeDeactiveOption == 1) {

                            System.out.println("\n" + OllivandersHotel.activateAccount(dni) + "\n");
                        } else if (activeDeactiveOption == 2) {

                            System.out.println("\n" + OllivandersHotel.deactivateAccount(dni) + "\n");
                        } else {

                            System.out.println("\nNot a valid option\n");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("\nAdd Room\n");

                        while (categoryOption <= 0 || categoryOption > 4) {

                            System.out.print("Category (1. Guest, 2. Junior, 3. Presidential, 4. Executive): ");
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

                        while (categoryOption == 0 || categoryOption > 4) {

                            System.out.print("Category (1. Guest, 2. Junior, 3. Presidential, 4. Executive): ");
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

                        System.out.println("\n" + OllivandersHotel.changeRoomCategory(idRoom, category) + "\n");
                        break;
                    }
                    case 8: {
                        System.out.println("\nDeactivate Room\n");

                        // TODO Need to add an "activateRoom" method or something.
                        System.out.print("Please enter ID Room: ");
                        idRoom = scan.nextInt();

                        System.out.println("\n" + OllivandersHotel.deactivateRoom(idRoom) + "\n");
                        break;
                    }
                    case 0: {
                        System.out.println("\nLogged out successfully\n");
                        /*
                        try {
                            OllivandersHotel.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        */
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
