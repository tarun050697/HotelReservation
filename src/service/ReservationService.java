package service;

import model.*;

import java.util.Collection;
import java.util.Date;
import java.util.Calendar;
import java.util.HashSet;


public class ReservationService {
    //static reference dec
    private static final ReservationService SINGLETON = new ReservationService();
     private ReservationService(){};
    public static ReservationService getSingleton() {
        return SINGLETON;
    }




    private static HashSet<Reservation> listingReservations = new HashSet<>();  // set for storing all reservations
    static HashSet<IRoom> roomDescription = new HashSet<>(); // set for storing room description and made it default



    public void addRoom(String roomNumber, Double price, RoomType enumeration){
//      create and add new room
        Room room = new Room(roomNumber, price, enumeration);
        roomDescription.add(room);
    }



    public IRoom getARoom(String roomID){
        for (IRoom iroom: roomDescription) {
            // if the roomId matches within the set with room number then it will return the iroom object
            if(roomID.equals(iroom.getRoomNumber())){
                return iroom;
            }
        }
        // if not found it will return empty object
        return null;
    }
// search for the rooms with the checkin and checkout date
    // REFERENCE --https://knowledge.udacity.com/questions/655317
    public Collection<IRoom>findRooms(Date checkInDate, Date checkOutDate){
        // created a hashset to store the available rooms
        HashSet<IRoom> roomsNotBooked = new HashSet<>();
        for(IRoom room : roomDescription){

            // if there are rooms for the specific date we can add them
            boolean result = checkingReserve(room, checkInDate, checkOutDate);
            if(!result){
                roomsNotBooked.add(room);
            }

        }

        return roomsNotBooked;



    }
    // reserve room for te customer and add to listing reservations
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        System.out.println(checkInDate);
        System.out.println(checkOutDate);

        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        listingReservations.add(reservation);
        return reservation;

    }


     public Collection<Reservation> getCustomersReservation(Customer customer){
        HashSet<Reservation> allCustomerReservation = new HashSet<>(); // created a hashset to store all reservations by customer
        for(Reservation reservation : listingReservations){ // iterate in set of reservations by customer
            if(reservation.getCustomer().equals(customer)){ //if it matches with  the customer then it will be added to set addCustomerReservation
                allCustomerReservation.add(reservation);
            }
        }
        return allCustomerReservation;
    }
    public void printAllReservations(){
        if(listingReservations.isEmpty()){
            System.out.println("no reservations available");
        }
        for(Reservation reservation: listingReservations){
            // print all customer reservation
            System.out.println(reservation);
        }

    }

    public HashSet<Reservation> getListingReservations(){
        return listingReservations;
    }

    public HashSet<IRoom> getAllRooms(){
        // give all the data of room means its price,room number,single or double
        return roomDescription;
    }
    public boolean checkingReserve(IRoom room, Date checKInDate, Date checkOutDate){
//        if the set is empty then all of the rooms are available
        if(listingReservations.isEmpty()){
            return false;
        }
//        checkin and checkout date we wiil see
        for (Reservation reservation : listingReservations){
//
            if(reservation.getRoom().getRoomNumber().equals(room.getRoomNumber())){
                boolean overlapping = (checKInDate.after(reservation.getCheckOutDate()) || checkOutDate.before(reservation.getCheckInDate()));
                if(!overlapping){
                    return true;
                }
            }
        }
        return false;
    }
     public boolean checkDuplicateNumber(String roomNumber){
        for(IRoom room : roomDescription) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return true;
            }
        }
        return false;
    }





}
