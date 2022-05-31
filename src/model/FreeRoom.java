package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber,RoomType roomType) {

        // set the value of price to 0
        super(roomNumber,0.0,roomType);

    }

    public String toString(){
        // this will return the room number and room type

        return this.getRoomNumber() + this.getRoomType() + " is a available room";
    }
}
