package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {

    private static ReservationService reservationService;
    private Collection<IRoom> rooms = new ArrayList<>();
    public Collection<Reservation> reservations = new ArrayList<>();

    // Dummy Singleton Constructor
    private ReservationService() {}

    // Static reference for Reservation class
    public static ReservationService getInstance(){
        if (reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room) {
        for (IRoom rm : rooms) {
            if(room.getRoomNumber().equals(rm.getRoomNumber())) {
                System.out.println("[ERROR] Room already exists: " + room);
                return;
            }
        }
        this.rooms.add(room);
        System.out.println("\r\n[INFO] Your room has been created: " + room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if(roomId.equals(room.getRoomNumber())) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservedRoom = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservedRoom);
        return reservedRoom;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        if(reservations.size() == 0) {
            return rooms;
        }

        if(checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("[ERROR] Invalid checkInDate/checkOutDate");
        }

        Collection<String> notAvailableRoomNumbers = new ArrayList<>();
        Collection<IRoom> availableRooms = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if(!(checkInDate.after(reservation.getCheckOutDate()))) {
                notAvailableRoomNumbers.add(reservation.getRoom().getRoomNumber());
            }
        }

        if(notAvailableRoomNumbers.size() == 0) {
            return rooms;
        }

        for (IRoom room : rooms) {
            for (String notAvailableRoomNumber : notAvailableRoomNumbers) {
                if(!(room.getRoomNumber().equals(notAvailableRoomNumber))) {
                    availableRooms.add(room);
                }
            }
        }

        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {

        Collection<Reservation> cusReservations = new ArrayList<>();
        for (Reservation reservation : reservations){
            if(customer.equals(reservation.getCustomer())) {
                cusReservations.add(reservation);
            }
        }

        return cusReservations;
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public void printAllReservation() {
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }
}

