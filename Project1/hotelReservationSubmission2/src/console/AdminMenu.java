package console;

import api.AdminResource;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    // Use getInstance to call singleton class objects
    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void adminDisplayMenu() {
        System.out.println("\r\nAdmin Menu");
        System.out.println("-----------------------------------------------------");
        System.out.println("1 - See all Customers");
        System.out.println("2 - See all Rooms");
        System.out.println("3 - See all Reservations");
        System.out.println("4 - Add a Room");
        System.out.println("5 - Back to Main Menu");
        System.out.println("-----------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }

    private static void seeAllCustomers() {
        Collection<Customer> allCustomers = adminResource.getAllCustomers();
        if(allCustomers.size() == 0) {
            System.out.println("\r\n[INFO] NO CUSTOMERS!");
            return;
        }
        for (Customer allCustomer : allCustomers){
            System.out.println(allCustomer);
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        if(allRooms.size() == 0) {
            System.out.println("\r\n[INFO] NO ROOMS!");
            return;
        }
        for (IRoom allRoom : allRooms){
            System.out.println(allRoom);
        }
    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addARoom() {

        Scanner addRoomInput = new Scanner(System.in);
        String yn;
        List<IRoom> rooms = new ArrayList<>();

        System.out.println("\r\nWould you like to add a room? y/n");
        yn = addRoomInput.nextLine();
        while(yn.matches("y")) {
            System.out.println("Enter room number:");
            String roomNumber = addRoomInput.next();

            System.out.println("Enter price per night:");
            Double price = addRoomInput.nextDouble();

            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            RoomType roomType = (addRoomInput.nextInt()) == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
            IRoom room = new Room(roomNumber, price, roomType);
            rooms.add(room);

            System.out.println("\r\nWould you like to add a room again? y/n");
            yn = addRoomInput.next();
        };

        if(rooms.size() == 0) {
            System.out.println("No rooms added, returning back to menu...");
            return;
        }

        System.out.println("\r\n[INFO] Adding rooms:");
        adminResource.addRoom(rooms);
    }

    public static void adminMenuOptions() {
        adminDisplayMenu();
        Scanner input = new Scanner(System.in);
        int adminSelection = input.nextInt();
        while(adminSelection != 5) {
            switch(adminSelection) {
                case 1:
                    System.out.println("[1] 'See all Customers' selected");
                    seeAllCustomers();
                    break;
                case 2:
                    System.out.println("[2] 'See all Rooms' selected");
                    seeAllRooms();
                    break;
                case 3:
                    System.out.println("[3] 'See all Reservations' selected");
                    seeAllReservations();
                    break;
                case 4:
                    System.out.println("[4] 'Add a Room' selected");
                    addARoom();
                    break;
                case 5:
                    System.out.println("[5] 'Back to Main Menu' selected");
                    return;
                default:
                    System.out.println("[ERROR] Only options 1-5 available");
                    break;
            }
            adminDisplayMenu();
            adminSelection = input.nextInt();
        }
    }

}
