package model;

public class Tester {
    public static void main(String[] args) {

        // Test working codes
        Customer customer = new Customer("first", "second", "j@domain.com");
        System.out.println(customer);

        // Test illegal exceptions
        // Customer customer2 = new Customer("first", "second", "email");

    }
}
