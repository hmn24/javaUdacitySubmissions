package model;

import java.util.Objects;

public class Room implements IRoom {

    private final String roomNumber;
    private double price;
    RoomType roomType;
    boolean isFree;
    private int hashVariable;

    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.hashVariable = Objects.hash(roomNumber, price, roomType);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Reservation)) return false;
        if (obj == this)  return true;
        return (this.hashCode() == ((Room) obj).hashCode());
    }

    @Override
    public int hashCode() {
        return this.hashVariable;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    };

    @Override
    public double getRoomPrice() {
        return price;
    };

    @Override
    public RoomType getRoomType() {
        return roomType;
    };

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "\r\n---- Room ----" +
               "\r\nRoomNumber: " + roomNumber +
               "\r\nRoomPrice: " + price +
               "\r\nRoomType: " + roomType +
               "\r\n--------------";
    }
}
