package com.tybootcamp.ecomm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="basket")

public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
    private Set<BasketProduct> basketProducts = new HashSet<>();

    public Basket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BasketProduct> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(Set<BasketProduct> basketProducts) {
        this.basketProducts = basketProducts;
    }
}
