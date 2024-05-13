package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cart;

import com.example.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Page<Cart> findAll(Pageable pageable);
    Page<Cart> findByUser(User user, Pageable pageable);
}
