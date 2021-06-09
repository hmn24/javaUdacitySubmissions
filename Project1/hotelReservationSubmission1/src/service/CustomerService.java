package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService customerService;
    private Collection<Customer> customers = new ArrayList<>();
    private Map<String, Customer> customersMap = new HashMap<>();

    // Dummy Singleton Constructor
    private CustomerService() {}

    // Static reference for CustomerService class
    public static CustomerService getInstance(){
        if (customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(firstName, lastName, email);
        this.customers.add(newCustomer);
        this.customersMap.put(newCustomer.getEmail(), newCustomer);
    };

    public Customer getCustomer(String customerEmail) {
        return customersMap.get(customerEmail.toLowerCase());
    };

    public Collection<Customer> getAllCustomers() {
        return customers;
    }

}
