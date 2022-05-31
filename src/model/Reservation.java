package model;
import java.util.Date;
import java.util.Objects;

public class Reservation {
    //
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
       // System.out.println(checkInDate);
        this.checkOutDate = checkOutDate;
       // System.out.println(checkOutDate);
    }

    public Customer getCustomer() {

        return this.customer;
    }





    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }





    public String toString() {
        // this will return the details of the customer with details of his booking

          return  "customer: " + this.customer.getFirstName()+this.customer.getLastName()+
                "Room: " + this.room.getRoomNumber()  +" "+
                "Check In Date:" + this.checkInDate +" "+
                "Check Out Date: " + this.checkOutDate;
    }
    //REFERENCE---https://knowledge.udacity.com/questions/536745
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation reservation = (Reservation) o;
        return Objects.equals(this.getCustomer(), reservation.getCustomer()) &&
                Objects.equals(this.getRoom(), reservation.getRoom()) &&
                Objects.equals(this.getCheckInDate(), reservation.getCheckInDate())&&
                Objects.equals(this.getCheckOutDate(), reservation.getCheckOutDate());
    }
    @Override
    public int hashCode() {
        return Objects.hash(customer,room,checkInDate,checkOutDate);
    }


}
