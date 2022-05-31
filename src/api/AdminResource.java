package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.HashSet;

public class AdminResource {
    private static final AdminResource SINGLETON = new AdminResource();
    private AdminResource(){};
    public static AdminResource getSingleton(){
        return SINGLETON;
    }
    public Customer getCustomer(String email){
        return CustomerService.getSingleton().getCustomer(email);
    }
    //  this will add new room
    public  void addRoom(Collection<IRoom> rooms){

        try {
            for (IRoom iroom : rooms) {
                ReservationService.getSingleton().addRoom(iroom.getRoomNumber(), iroom.getRoomPrice(), iroom.getRoomType()); // taken the reference of this code from udacity mentors
            }
        }catch(Exception e ){
            System.out.println(e.getMessage());

        }
    }


    //  this will return all rooms
    public HashSet<IRoom> getAllRooms(){
        return ReservationService.getSingleton().getAllRooms();
    }

    //    this will return all customers
    public Collection<Customer> getAllCustomers(){
        return CustomerService.getSingleton().getAllCustomers();
    }

    //    this for displaying all reservations
    public void displayAllReservations(){
        ReservationService.getSingleton().printAllReservations();
    }


}



