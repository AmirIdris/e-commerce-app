package com.example.ecommerce.repository;

import com.example.ecommerce.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {

}
