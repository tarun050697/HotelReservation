import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.*;

public class AdminMenu {
   // public static int value= 5;
   Scanner scanner= new Scanner(System.in);
    public  void admin(){
        // https://knowledge.udacity.com/questions/649381
        boolean running= true;
       // Scanner scanner= new Scanner(System.in);



        while (running) {
            try {

                System.out.println("1. See all Customers");
                System.out.println("2. See all Rooms");
                System.out.println("3. see all Reservations");
                System.out.println("4. Add a Room");
                System.out.println("5. Back to Main Menu");
                System.out.println("please select the number of your choice");

                int type = scanner.nextInt();
                switch (type) {
                    case 1:
                        seeAllcustomer();

                        break;

                    case 2:
                        seeAllRooms();

                        break;

                    case 3:
                        seeAllReservations();

                        break;

                    case 4:
// reference https://knowledge.udacity.com/questions/649254
                        //Room(String roomNumber, Double price, RoomType enumeration)
                        // Scanner scannerroom = new Scanner(System.in);
                        addRoom();


                        break;

                    case 5:


                        running = false;
                    default:
                        System.out.println("enter choice per list");
                        System.out.println("________________________________");
                }
            }
        catch (Exception ex) {
                System.out.println("\nInvalid input. Please try again.\n");
            }


        }
    }
    public void seeAllReservations(){
        AdminResource.getSingleton().displayAllReservations();

    }
    public void seeAllRooms(){
        Collection<IRoom> allrooms = AdminResource.getSingleton().getAllRooms();
        if (allrooms.isEmpty()) {
            System.out.println("no room is available");
        } else {
            for (IRoom room : allrooms
            ) {
                System.out.println(room);
            }
        }

    }
    public void seeAllcustomer(){
        Collection<Customer> c = AdminResource.getSingleton().getAllCustomers();
        if (c.isEmpty()) {
            System.out.println("no customers are there");
        } else {
            for (Customer customer : c) {
                System.out.println(customer);
            }
        }

    }
    public void addRoom(){
        String ser=scanner.nextLine();
        System.out.println("Enter room number: ");
        String roomNumber = scanner.nextLine();
        if(HotelResource.getSingleton().checkDuplicateNumber(roomNumber)){
            System.out.println("room with same room number already exists");
            System.out.println("-------------------------------------------");
            return;
        }
        System.out.println("Enter room price: ");
        Double price = scanner.nextDouble();
        boolean out = false;
        RoomType roomType = null;
        // Double price = scanner.nextDouble();
        //using  while loop for selecting room type
        do {
            System.out.println("Which type of room you want add");
            System.out.println(" 1. Single\n 2. Double ");

            int option = scanner.nextInt();

            if (option == 1){
                roomType = RoomType.SINGLE;
                out= true;
                break;
            }else if(option == 2) {
                roomType = RoomType.DOUBLE;
                out = true;
                break;
            }else {
                System.out.println("Please select option from menu");
            }

        }while (!out);
        IRoom room;
        // if price 0 add to free room else add to room
        if (price != 0.0) {
            room = new Room(roomNumber, price, roomType);


        } else {
            room = new FreeRoom(roomNumber, roomType);

        }
        Collection<IRoom> displayRoom = new HashSet<>();
        displayRoom.add(room);
        AdminResource.getSingleton().addRoom(displayRoom);

    }

}
