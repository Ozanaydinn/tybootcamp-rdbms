package com.tybootcamp.ecomm.repositories;

import com.tybootcamp.ecomm.entities.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperUserRepository extends JpaRepository<SuperUser, Long> {
}
