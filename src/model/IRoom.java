package model;

public interface IRoom {
    // this is interface and we use below methods in other classes
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
}
