package menues;

import controller.Hotel;
import model.*;

import java.util.Scanner;

public class MenuManager {

    public static void menuManager(Scanner scan, Hotel OllivandersHotel, User loggedUser) {

        int z = 0, option = 0;

        int idRoom = 0, employeeOption = 0, categoryOption = 0;
        String dni;
        Category category = null;

        while (z == 0) {

            System.out.println("\nMenu Manager\n==========\n");
            System.out.println("1. Add Employee\n2. See Users\n3. Edit Account\n4. Deactivate User");
            System.out.println("5. Add Room\n6. See Rooms\n7. Edit Room Category\n8 . Deactivate Room");
            System.out.println("0. Log Out");
            System.out.print("Option: ");
            System.out.flush();
            //TODO Need to implement "InputMismatchException".
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nAdd Employee\n");

                    System.out.print("New Employee (1. Manager, 2. Receptionist): ");
                    employeeOption = scan.nextInt();

                    if (employeeOption == 1) {

                        // TODO Don't know yet if this will be in this menu or in the Main.
                    } else if (employeeOption == 2) {

                    } else {

                        System.out.println("\nPlease, choose a valid option\n");
                    }
                    break;
                }
                case 2: {
                    System.out.println("\nSee Users\n");
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
                    System.out.println("\nDeactivate User\n");

                    System.out.print("Enter user's DNI: ");
                    dni = scan.next();

                    System.out.println("\n" + OllivandersHotel.deactivateAccount(dni) + "\n");
                    break;
                }
                case 5: {
                    System.out.println("\nAdd Room\n");

                    while (categoryOption <= 0 || categoryOption > 4) {

                        System.out.print("Category (1. Guest, 2. Junior, 3. Presidential, 4. Executive): ");
                        //TODO Need to implement "InputMismatchException".
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
                        //TODO Need to implement "InputMismatchException".
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

                    System.out.print("Please enter ID Room: ");
                    idRoom = scan.nextInt();

                    System.out.println("\n" + OllivandersHotel.deactivateRoom(idRoom) + "\n");
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
        }
    }
}
