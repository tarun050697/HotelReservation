package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {


    private static final HotelResource SINGLETON = new HotelResource();
    private HotelResource(){};
    public static HotelResource getSingleton(){
        return SINGLETON;
    }
    // fetch and give the customer with the email
    public Customer getCustomer(String email){
        return CustomerService.getSingleton().getCustomer(email);
    }

    //    create a customer
    public void createCustomer(String firstName, String lastName, String email){
        CustomerService.getSingleton().addCustomer(firstName,lastName,email);
    }


    public IRoom getRoom(String roomNumber){
        return ReservationService.getSingleton().getARoom(roomNumber);
    }


    //    tthis method wiil help to book a new room
    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.getSingleton().reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    // details of  all reservation done by customer
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return ReservationService.getSingleton().getCustomersReservation(getCustomer(customerEmail));
    }


    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.getSingleton().findRooms(checkIn, checkOut);
    }
    public boolean checkingReserve(IRoom room, Date checkIn, Date checkOut){
        return ReservationService.getSingleton().checkingReserve(room,checkIn, checkOut);
    }

    //   all  reservations
    public Collection<Reservation> getAllReservations(){
        return ReservationService.getSingleton().getListingReservations();
    }
    public boolean checkDuplicateNumber(String roomNumber){
        return ReservationService.getSingleton().checkDuplicateNumber(roomNumber);
    }


}