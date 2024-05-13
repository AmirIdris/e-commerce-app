package com.example.ecommerce.controller;

import com.example.ecommerce.dto.order.OrderDto;
import com.example.ecommerce.model.Orders;
import com.example.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }
    @PostMapping("")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDto orderDto){
        Orders orders = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }
    @GetMapping("")
    public ResponseEntity<List<Orders>> findAll(){
        List<Orders> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findAll(@PathVariable Long id){
        Orders order = orderService.findOne(id);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Orders> updateProduct(@PathVariable Long id, @RequestBody OrderDto orderDto){
        Orders order = orderService.update(id, orderDto);
        return ResponseEntity.ok(order);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteCategory(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

}
