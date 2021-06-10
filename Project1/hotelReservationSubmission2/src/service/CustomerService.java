package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService customerService;
    public Set<Customer> customers = new HashSet<>();
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
