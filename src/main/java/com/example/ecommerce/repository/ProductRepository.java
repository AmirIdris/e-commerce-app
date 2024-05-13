package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.categories WHERE p.productId = :id")
    Product findByIdWithCategories(@Param("id") Long id);
}
