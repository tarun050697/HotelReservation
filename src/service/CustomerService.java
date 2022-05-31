package service;




import model.Customer;

import java.util.Collection;
import java.util.HashSet;




public class CustomerService {
    //
  //  provide static refernce for only one instance of class
    private static final CustomerService SINGLETON = new CustomerService();
    private CustomerService(){};
    public static CustomerService getSingleton() {
        return SINGLETON;
    }


    Collection<Customer> storingCustomersData = new HashSet<>();// created the hashset for storing the customers first,last and email


    // adding  new customers to set
    public void addCustomer(String firstName, String lastName, String email){
        Customer customer = new Customer(firstName, lastName, email);
        storingCustomersData.add(customer);

    }
// search customer with email
    public Customer getCustomer(String customerEmail){
        //iterating over the hashset and hast set do not contain null values
        for (Customer customer: storingCustomersData) {

            if(customer.getEmail().equals(customerEmail)){
                return customer;
            }
        }
        // if no customer is present with that email id than
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        // this will return the whole set of customers

        return storingCustomersData;
    }
}
