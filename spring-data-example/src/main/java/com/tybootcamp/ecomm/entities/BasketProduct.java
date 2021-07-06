package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * An entity that is a middle ground between Product and Basket which holds the contents of the baskets.
 * Columns include a unique id, basket id, customer id and quantity.
 */
@Entity
public class BasketProduct {
    // Id is not really necessary, we can also set map both foreign keys as primary.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    // Bi-directional communication. I did this to remove an extra table and have basket id in basket product table.
    @ManyToOne(cascade = CascadeType.ALL)
    private Basket basket;

    @NotNull
    private int quantity;

    public BasketProduct(){

    }

    // Can use getter and setters
    public BasketProduct(Product product, int quantity, Basket basket) {
        this.product = product;
        this.quantity = quantity;
        this.basket = basket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
