package menues;

import controller.Hotel;
import model.Availability;
import model.Category;
import model.Room;

import java.util.List;
import java.util.Scanner;

public class MenuSeeRooms {

    public static void menuSeeRooms(Scanner scan, Hotel OllivandersHotel) {

        int z = 0, option = 0;

        Availability availability = null;
        Category category = null;
        List<Room> roomList = null;

        while (z == 0) {

            //TODO Maybe we can simplify the getRooms like this: "getRoomsByAvailability" and "getRoomsByCategory".
            System.out.println("\nMenu See Rooms\n==============\n");
            System.out.println("1. All Rooms");
            System.out.println("2. Free Rooms\n3. Occupied Rooms\n4. Cleaning Rooms");
            System.out.println("5. In Desinfection Rooms\n6. Under Repair Rooms\n7. Out of Services Rooms");
            System.out.println("8. Guest Rooms\n9. Junior Rooms\n10. Presidential Rooms\n11. Executive Rooms\n");
            System.out.print("Option: ");
            System.out.flush();
            //TODO Need to implement "InputMismatchException".
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("\nAll Rooms\n");

                    // TODO Check if we need to add toString or not.
                    System.out.println("\n" + OllivandersHotel.getRooms().toString() + "\n");
                    break;
                }
                case 2: {
                    System.out.println("\nFree Rooms\n");

                    availability = Availability.FREE;
                    roomList = OllivandersHotel.getAllFreeRooms(availability);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllFreeRooms(availability) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + availability.getAvailability() + " rooms\n");
                    }
                    break;
                }
                case 3: {
                    System.out.println("\nOccupied Rooms\n");

                    availability = Availability.OCCUPIED;
                    roomList = OllivandersHotel.getAllOccupiedRooms(availability);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllOccupiedRooms(availability) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + availability.getAvailability() + " rooms\n");
                    }
                    break;
                }
                case 4: {
                    System.out.println("\nCleaning Rooms\n");

                    availability = Availability.CLEANING;
                    roomList = OllivandersHotel.getAllCleaningRooms(availability);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllCleaningRooms(availability) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + availability.getAvailability() + " rooms\n");
                    }
                    break;
                }
                case 5: {
                    System.out.println("\nIn Desinfection Rooms\n");

                    availability = Availability.IN_DESINFECTION;
                    roomList = OllivandersHotel.getAllInDesinfectionRooms(availability);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllInDesinfectionRooms(availability) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + availability.getAvailability() + " rooms\n");
                    }
                    break;
                }
                case 6: {
                    System.out.println("\nUnder Repair Rooms\n");

                    availability = Availability.UNDER_REPAIR;
                    roomList = OllivandersHotel.getAllUnderRepairRooms(availability);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllUnderRepairRooms(availability) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + availability.getAvailability() + " rooms\n");
                    }
                    break;
                }
                case 7: {
                    System.out.println("\nOut of Services Rooms\n");

                    availability = Availability.OUT_OF_SERVICE;
                    roomList = OllivandersHotel.getAllOutOfServiceRooms(availability);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllOutOfServiceRooms(availability) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + availability.getAvailability() + " rooms\n");
                    }
                    break;
                }
                case 8: {
                    System.out.println("\nGuest Rooms\n");

                    category = Category.GUEST;
                    roomList = OllivandersHotel.getAllGuestRooms(category);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllGuestRooms(category) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + category.getName() + " rooms\n");
                    }
                    break;
                }
                case 9: {
                    System.out.println("\nJunior Rooms\n");

                    category = Category.JUNIOR;
                    roomList = OllivandersHotel.getAllJuniorRooms(category);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllJuniorRooms(category) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + category.getName() + " rooms\n");
                    }
                    break;
                }
                case 10: {
                    System.out.println("\nPresidential Rooms\n");

                    category = Category.PRESIDENTIAL;
                    roomList = OllivandersHotel.getAllPresidentialRooms(category);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllPresidentialRooms(category) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + category.getName() + " rooms\n");
                    }
                    break;
                }
                case 11: {
                    System.out.println("\nExecutive Rooms\n");

                    category = Category.EXECUTIVE;
                    roomList = OllivandersHotel.getAllExecutiveRooms(category);

                    if (roomList.size() != 0) {

                        System.out.println("\n" + OllivandersHotel.getAllExecutiveRooms(category) + "\n\nThere is/are " + roomList.size() + " room/s\n");
                    } else {

                        System.out.println("\nThere is no " + category.getName() + " rooms\n");
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
