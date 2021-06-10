package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private int hashVariable;

    private final String emailRegex = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email) {
        checkEmailPattern(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashVariable = Objects.hash(firstName, lastName, email);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.hashVariable = Objects.hash(firstName, lastName, email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.hashVariable = Objects.hash(firstName, lastName, email);
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        checkEmailPattern(email);
        this.email = email;
        this.hashVariable = Objects.hash(firstName, lastName, email);
    }

    public String getEmail() {
        return email;
    }

    private void checkEmailPattern(String email) {
        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, Invalid email");
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Reservation)) return false;
        if (obj == this)  return true;
        return (this.hashCode() == ((Customer) obj).hashCode());
    }

    @Override
    public int hashCode() {
        return this.hashVariable;
    }

    @Override
    public String toString() {
        return "\r\n---- Customer ----" +
               "\r\nfirstName: " + firstName +
               "\r\nlastName: " + lastName +
               "\r\nemail: " + email +
               "\r\n------------------";
    }
}
