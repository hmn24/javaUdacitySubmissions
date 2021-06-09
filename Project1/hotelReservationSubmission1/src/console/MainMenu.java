package console;

import api.HotelResource;
import model.IRoom;
import model.Reservation;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    // Use getInstance to call singleton class objects
    private static final HotelResource hotelResource = HotelResource.getInstance();

    // For stdIn and parsing of stdIn
    private static Scanner stdIn;
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void displayMenu() {
        System.out.println("\r\nWelcome to Hotels Reservation Application");
        System.out.println("-----------------------------------------------------");
        System.out.println("1 - Find and reserve a room");
        System.out.println("2 - See my reservations");
        System.out.println("3 - Create an account");
        System.out.println("4 - Admin");
        System.out.println("5 - Exit");
        System.out.println("-----------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }

    private static void findAndReserveARoom() {

        Date checkInDate = new Date();
        Date checkOutDate = new Date();
        String yn;

        Scanner input = new Scanner(System.in);

        System.out.println("Enter CheckIn Date mm/dd/yyyy (Example 02/01/2020):");
        String checkInDateString = input.next();
        try {
            checkInDate = dateFormatter.parse(checkInDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Enter CheckOut Date mm/dd/yyyy (Example 02/01/2020):");
        String checkOutDateString = input.next();
        try {
            checkOutDate = dateFormatter.parse(checkOutDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Collection<IRoom> availableRooms;
        try {
            availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        if(availableRooms.size() == 0) {
            System.out.println("[ERROR] No Rooms Available for Booking!");
            return;
        }

        System.out.println("Available Rooms for Booking:");
        for (IRoom room : availableRooms){
            System.out.println(room);
        }

        System.out.println("Would you like to book a room? y/n");
        yn = input.next();
        if(!yn.matches("y")) return;

        System.out.println("Enter Email Format: name@domain.com");
        String email = input.next();

        System.out.println("What room would you like to book?");
        int bookedRoomNumber = input.nextInt();
        IRoom bookedRoom = hotelResource.getRoom(Integer.toString(bookedRoomNumber));

        if(null == bookedRoom) {
            System.out.println("[ERROR] Room does not exist");
            return;
        }

        Reservation HRBookedRoom = hotelResource.bookARoom(email, bookedRoom, checkInDate, checkOutDate);
        if(HRBookedRoom == null) {
            System.out.println("\r\n[ERROR] Issues booking a room. PLEASE try again!");
        } else {
            System.out.println("\r\n[INFO] Booked room successfully");
            System.out.println(HRBookedRoom);
        }
    }

    private static void seeMyReservations() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Email Format: name@domain.com");
        String email = input.next();

        Collection<Reservation> myReservations = hotelResource.getCustomersReservations(email);

        if(myReservations == null) return;

        for (Reservation myReservation : myReservations) {
            System.out.println(myReservation);
        }
    }

    private static void createAnAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Email Format: name@domain.com");
        String email = input.next();

        System.out.println("Enter First Name:");
        String firstName = input.next();

        System.out.println("Enter Last Name:");
        String lastName = input.next();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
        } catch (Exception e) {
            System.out.println("[ERROR] Creating an account: " + e.toString());
        }
    }

    public static void mainMenuOptions() {
        displayMenu();
        stdIn = new Scanner(System.in);
        int selection = stdIn.nextInt();
        while(selection != 5) {
            switch(selection) {
                case 1:
                    System.out.println("[1] Find and reserve a room");
                    findAndReserveARoom();
                    break;
                case 2:
                    System.out.println("[2] 'See my reservations' selected");
                    seeMyReservations();
                    break;
                case 3:
                    System.out.println("[3] 'Create an account' selected");
                    createAnAccount();
                    break;
                case 4:
                    System.out.println("[4] 'Admin' selected");
                    AdminMenu.adminMenuOptions();
                    break;
                case 5:
                    stdIn.close();
                    System.exit(0);
                default:
                    System.out.println("[ERROR] Only options 1-5 available");
                    break;
            }
            displayMenu();
            selection = stdIn.nextInt();
        }
    }

}
