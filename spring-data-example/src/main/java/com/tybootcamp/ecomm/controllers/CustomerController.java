package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Basket;
import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.Profile;
import com.tybootcamp.ecomm.entities.Seller;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(path = "/")
    public ResponseEntity<?> getCustomerById(@RequestParam(value = "id") Long id) {
        try {
            Customer customer =  customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            System.out.println("The customer with id " + id + " is: " + customer.getCustomerName());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("There isn't any customer with this id!",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody Customer customer)
    {
        Customer customerEntity = customer;
        Profile profile = new Profile(customerEntity, customer.getProfile().getFirstName(), customer.getProfile().getLastName(), customer.getProfile().getGender());
        Basket basket = new Basket();
        customerEntity.setProfile(profile);
        customerEntity.setBasket(basket);
        customerEntity.getProfile().setWebsite(customer.getProfile().getWebsite());
        customerEntity.getProfile().setAddress(customer.getProfile().getAddress());
        customerEntity.getProfile().setEmailAddress(customer.getProfile().getEmailAddress());
        customerEntity.getProfile().setBirthday(customer.getProfile().getBirthday());
        customerEntity = customerRepository.save(customerEntity);
        return new ResponseEntity<>(customerEntity, HttpStatus.OK);
    }
}
