package menues;

import controller.Hotel;
import model.Room;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuSeeRooms {

    public static void menuSeeRooms(Scanner scan, Hotel OllivandersHotel) {

        int z = 0, option;

        List<Room> roomList;

        while (z == 0) {

            System.out.println("\nMenu See Rooms\n==============\n");
            System.out.println("[1]. All Rooms");
            System.out.println("[2]. Free Rooms\n[3]. Occupied Rooms\n[4]. Cleaning Rooms");
            System.out.println("[5]. In Desinfection Rooms\n[6]. Under Repair Rooms\n[7]. Out of Services Rooms");
            System.out.println("[8]. Guest Rooms\n[9]. Junior Rooms\n[10]. Presidential Rooms\n[11]. Executive Rooms\n");
            System.out.println("[0]. Back");
            System.out.print("Option: ");
            System.out.flush();
            try {

                option = scan.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("\nAll Rooms\n");

                        System.out.println("\n" + OllivandersHotel.getRooms().toString() + "\n");
                        break;
                    }
                    case 2: {
                        System.out.println("\nFree Rooms\n");

                        roomList = OllivandersHotel.getAllFreeRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no free rooms\n");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\nOccupied Rooms\n");

                        roomList = OllivandersHotel.getAllOccupiedRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no occupied rooms\n");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\nCleaning Rooms\n");

                        roomList = OllivandersHotel.getAllCleaningRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no rooms being cleaned\n");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("\nIn Desinfection Rooms\n");

                        roomList = OllivandersHotel.getAllInDesinfectionRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no rooms in desinfection\n");
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("\nUnder Repair Rooms\n");

                        roomList = OllivandersHotel.getAllUnderRepairRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no rooms under repair\n");
                        }
                        break;
                    }
                    case 7: {
                        System.out.println("\nOut of Services Rooms\n");

                        roomList = OllivandersHotel.getAllOutOfServiceRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no rooms out of service\n");
                        }
                        break;
                    }
                    case 8: {
                        System.out.println("\nGuest Rooms\n");

                        roomList = OllivandersHotel.getAllGuestRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no Guest rooms\n");
                        }
                        break;
                    }
                    case 9: {
                        System.out.println("\nJunior Rooms\n");

                        roomList = OllivandersHotel.getAllJuniorRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no Junior rooms\n");
                        }
                        break;
                    }
                    case 10: {
                        System.out.println("\nPresidential Rooms\n");

                        roomList = OllivandersHotel.getAllPresidentialRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no Presidential rooms\n");
                        }
                        break;
                    }
                    case 11: {
                        System.out.println("\nExecutive Rooms\n");

                        roomList = OllivandersHotel.getAllExecutiveRooms();

                        if (roomList.size() != 0) {

                            System.out.println("\n" + roomList.toString() + "\n\nThere is/are " + roomList.size() + " room/s\n");
                        } else {

                            System.out.println("\nThere are no Executive rooms\n");
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

                System.out.println("\n" + ime.getMessage() + "\n");
            }
        }
    }
}
