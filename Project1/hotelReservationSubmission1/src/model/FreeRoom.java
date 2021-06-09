package model;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, double price, RoomType roomType) {
        super(roomNumber, 0.00, roomType);
    }

    @Override
    public double getRoomPrice() {
        return super.getRoomPrice();
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public String toString() {
        return "\r\n---- FreeRoom ----" +
               "\r\nroomType: " + roomType +
               "\r\nisFree: " + isFree +
               "\r\n------------------";
    }

}
