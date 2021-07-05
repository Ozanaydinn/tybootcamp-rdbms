package com.tybootcamp.ecomm.repositories;

import com.tybootcamp.ecomm.entities.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {

}
