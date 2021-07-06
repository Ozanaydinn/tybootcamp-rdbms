package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Basket;
import com.tybootcamp.ecomm.entities.BasketProduct;
import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.repositories.BasketProductRepository;
import com.tybootcamp.ecomm.repositories.BasketRepository;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@RestController
@RequestMapping(path = "/basket")
public class BasketController {
    private final BasketProductRepository basketProductRepository;
    private final BasketRepository basketRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public BasketController(BasketRepository basketRepository, BasketProductRepository basketProductRepository, CustomerRepository customerRepository){
      this.basketProductRepository = basketProductRepository;
      this.basketRepository = basketRepository;
      this.customerRepository = customerRepository;
    }

    // Finding baskets by user id seemed the most likely use case, can add findById as well.
    @GetMapping(path = "/")
    public ResponseEntity<?> getBasketByUserId(@RequestParam(value = "id") Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Basket basket = customer.getBasket();
            System.out.println("The basket id for the customer with id " + id + " is: " + basket.getId());
            return new ResponseEntity<>(basket, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("There aren't any users with the given ID!", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(path = "/")
    public ResponseEntity<Basket> addProductToBasket(@RequestBody Set<BasketProduct> basketProducts, Long basketId) {
        Basket basket = basketRepository.findById(basketId).orElseThrow(EntityNotFoundException::new);
        basket.setBasketProducts(basketProducts);
        basket = basketRepository.save(basket);
        return new ResponseEntity<>(basket, HttpStatus.OK);
    }
}
