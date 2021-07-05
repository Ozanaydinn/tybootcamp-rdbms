package com.tybootcamp.ecomm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer extends SuperUser{

    @OneToOne
    @JoinColumn(nullable = false)
    @MapsId
    @JsonIgnore
    private Basket basket;

    @NotNull
    private String customerName;

    public Customer(){

    }
    public Customer(String customerName){
        this.customerName = customerName;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
