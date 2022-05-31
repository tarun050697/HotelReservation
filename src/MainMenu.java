import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import api.HotelResource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import api.AdminResource;



public class MainMenu{
    private final Scanner scanner = new Scanner(System.in);



    public  void letsGetStarted() {
        boolean running= true;

/////// reference ---https://knowledge.udacity.com/questions/649254

        while (running) {
           // try {
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                System.out.println("------------------------------------------");
                System.out.println("Please enter the number according to task u want");
                // Scanner scanner = new Scanner(System.in);
                int typed = scanner.nextInt();
                switch (typed) {
                    case 1:
                        findAndReserveRoom();
                        break;

                    case 2:
                        seeMyReservation();




                        break;

                    case 3:
                         createAccount();



                        break;

                    case 4:

                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.admin();
                        break;

                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("select choice as per list given");
                        System.out.println("--------------------------------");
                    //}
                    // catch (Exception ex) {
                    // System.out.println("\nInvalid input. Please try again.\n");
                    // }
                }
        }

    }
    public  void findAndReserveRoom(){

//      first check customer account
       // String ser= scanner.nextLine();
        System.out.println("enter mail id");
        String ser= scanner.nextLine();
        String email = scanner.nextLine();
        Customer customer = AdminResource.getSingleton().getCustomer(email);
        if(customer == null){
            System.out.println("no account with this mail.so create account first");
            createAccount();
            return;
        }
        else {
//       checkin checkout if user exist
            System.out.println(customer.getFirstName());
            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            boolean endSession = false;
            Date checkInDate;
            Date checkOutDate;

// reference https://www.geeksforgeeks.org/calendar-class-in-java-with-examples/
            for (; ; ) {
                try {
                    System.out.println(" check in date (dd/mm/yyyy)");
                    String date = scanner.nextLine();
                    //checkInDate = getInputDate(scanner);
                    System.out.println(date);


                    checkInDate = dateFormat.parse(date);
                    System.out.println(checkInDate);
                    break;
                } catch (Exception e) {
                    System.out.println("format is wrong please see the above pattern");
                }
            }

            for (; ; ) {
                try {
                    System.out.println(" check out date (dd/mm/yyyy)");
                    String date = scanner.nextLine();

                    checkOutDate = dateFormat.parse(date);
                    break;
                } catch (Exception e) {
                    System.out.println("format is wrong see the above pattern");
                }
            }
//      find for rooms available on the date
            Collection<IRoom> avrooms = HotelResource.getSingleton().findARoom(checkInDate, checkOutDate);


//       show the rooms with the date
            if (!avrooms.isEmpty()) {

                for (IRoom room : avrooms) {
                    System.out.println(room);
                }
//        if no rooms then search for room present after 7 dayds

            } else if (avrooms.isEmpty()) {

//            https://www.geeksforgeeks.org/calendar-class-in-java-with-examples/
                // stackoverflow
                Calendar calendarInput = Calendar.getInstance();
                Calendar calendarOutput = Calendar.getInstance();

                calendarInput.setTime(checkInDate);
                calendarInput.add(Calendar.DAY_OF_MONTH, 7);
                checkInDate = calendarInput.getTime();


                calendarOutput.setTime(checkOutDate);
                calendarOutput.add(Calendar.DAY_OF_MONTH, 7);
                checkOutDate = calendarOutput.getTime();

                Collection<IRoom> roomsAvailability = HotelResource.getSingleton().findARoom(checkInDate, checkOutDate);
                if (!roomsAvailability.isEmpty()) {
                    System.out.println("sorry no room is available for the date");

                    for (IRoom room : roomsAvailability) {
                        System.out.println("below rooms are available after 7 days");
                        System.out.println(room);
                    }
                } else {
                    System.out.println("sorry all rooms are booked");
                }

            }

//

            System.out.println("book  a room from given list");
            String roomNumber = scanner.nextLine();

            Collection<Reservation> listingReservations = HotelResource.getSingleton().getAllReservations();

//
            for (Reservation reservation : listingReservations) {
                if (reservation.getRoom().getRoomNumber().equals(roomNumber) && HotelResource.getSingleton().checkingReserve(reservation.getRoom(), checkInDate, checkOutDate)) {
                    System.out.println("sorry we cant book this room choose other room.this room is already booked");
                    return;
                }
            }
            try {
                boolean existRoom = false;
                for (IRoom roomAavail : AdminResource.getSingleton().getAllRooms()) {
                    if (roomAavail.getRoomNumber().equals(roomNumber)) {
                        existRoom = true;
                        break;
                    }
                }
                if (existRoom == true) {
                    IRoom room = HotelResource.getSingleton().getRoom(roomNumber);
                    Reservation reservation = HotelResource.getSingleton().bookARoom(customer, room, checkInDate, checkOutDate);
                    System.out.println("your reservation" +
                            roomNumber + checkInDate + checkOutDate);

                } else {
                    System.out.println("rooom not exist");
                    letsGetStarted();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Cant book");
                letsGetStarted();
            }

//        then we will find this room and create new reservation for this customer
            // IRoom room = HotelResource.getSingleton().getRoom(roomNumber);

            //Reservation reservation = HotelResource.getSingleton().bookARoom(customer, room, checkInDate, checkOutDate);

            //System.out.println("your reservation" +
            // roomNumber +  checkInDate +  checkOutDate);
        }
    }
    // this method create account
    public void createAccount(){
        String ser=scanner.nextLine();
        System.out.println("type first name");

        String firstName = scanner.nextLine();
        System.out.println("type mail id");
        String email = scanner.nextLine();


        System.out.println("type last name");
        String lastName = scanner.nextLine();

        HotelResource.getSingleton().createCustomer(firstName, lastName, email);
        System.out.println("account created");
        System.out.println("___________________________________");


    }
    // this method will show all the reservations
    public void seeMyReservation(){
        String ser= scanner.nextLine();
        System.out.println("enter email");

       // String ser= scanner.nextLine();
        String email = scanner.nextLine();
        Collection<Reservation> seeMyReservations = HotelResource.getSingleton().getCustomersReservations(email);
        if (seeMyReservations.isEmpty()) {
            System.out.println("no reservation with that email id");
        } else {
            for (Reservation reservation : seeMyReservations) {
                System.out.println(reservation);

            }
        }

    }






    }






