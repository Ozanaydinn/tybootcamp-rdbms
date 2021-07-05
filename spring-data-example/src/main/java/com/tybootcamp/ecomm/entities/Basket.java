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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "basket", orphanRemoval = true)
    private Customer customer;

    @ManyToMany(mappedBy = "basketItems", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public Basket() {

    }

    public Basket(Long id){ this.id = id; }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
