package model;
import java.util.Objects;

public class Room implements IRoom{
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price   =   price;
        this.enumeration = enumeration;

    }


// to access the method in other class and to get rid from encapsulation getter and setter method is used

    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public void setRoomPrice(Double price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public void setRoomType(RoomType enumeration) {
        this.enumeration = enumeration;
    }


    public boolean isFree() {
        //set its value to false
        return false;
    }
    public String toString()
    {

        return "Room number: " + this.roomNumber + this.enumeration + "Room price: $" + this.price;

    }
    // REFERENCE---https://knowledge.udacity.com/questions/536745
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(this.getRoomNumber(), room.getRoomNumber()) &&
                Objects.equals(this.getRoomPrice(), room.getRoomPrice()) &&
                Objects.equals(this.getRoomType(), room.getRoomType());
    }
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, enumeration);
    }
}





