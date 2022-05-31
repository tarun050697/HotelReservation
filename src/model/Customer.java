package model;
import java.util.Objects;
import java.util.regex.Pattern;
public class Customer {

        private String firstName;
        private String lastName;
        private String email;
    private final String emailRegex = "^(.+)@(.+).com$";  // this string is final and can not be changed
    private final Pattern pattern = Pattern.compile(emailRegex);

        public Customer( String firstName, String lastName,  String email){



            if(!pattern.matcher(email).matches()){
                // if the email do not match with following pattern it will throw error
                throw new IllegalArgumentException("not a valid email");
            }

            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;


        }
    public String getFirstName(){
        return firstName;
    }
    public String getEmail(){
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    //public void setEmail(String email) {
       // this.email = email;
   // }

   // public void setFirstName(String firstName) {
       // this.firstName = firstName;
    //}

   // public void setLastName(String lastName) {
      //  this.lastName = lastName;
    //}

    public String toString() {
            // return the first,last and email of the customer
        return "First Name: " + this.firstName
                + " Last Name: " + this.lastName
                + " Email: " + this.email;
    }

 // REFERENCE---https://knowledge.udacity.com/questions/536745
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(this.getFirstName(), customer.getFirstName()) &&
                Objects.equals(this.getLastName(), customer.getLastName()) &&
                Objects.equals(this.getEmail(), customer.getEmail());
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }


}
