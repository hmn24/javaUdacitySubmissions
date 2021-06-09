package model;

public class Room implements IRoom {

    private final String roomNumber;
    private double price;
    RoomType roomType;
    boolean isFree;

    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
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
