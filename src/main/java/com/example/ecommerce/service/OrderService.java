package com.example.ecommerce.service;

import com.example.ecommerce.dto.order.OrderDto;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.model.OrderItems;
import com.example.ecommerce.model.Orders;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderItemsRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemsRepository orderItemsRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        OrderItemsRepository orderItemsRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemsRepository = orderItemsRepository;
    }
    public Orders createOrder(OrderDto input){
        Orders orders = new Orders();
        orders.setOrderDate(input.getOrderDate());
        orders.setTotalAmount(input.getTotalAmount());
        orders.setStatus(input.getStatus());
        List<Product> products=new ArrayList<>();
        List<OrderItems> orderItems1=new ArrayList<>();
        for (Long item_id: input.getProductIds()){
            OrderItems orderItems= new OrderItems();
            Product product= productRepository.findById(item_id).orElseThrow(()->new NotFoundException(item_id));
            orderItems.setProduct(product);
            orderItems1.add(orderItems);
        }



        orders.setOrderItems(orderItems1);

        return orderRepository.save(orders);
    }
    public List<Orders>findAll(){
        List<Orders> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }
    public Orders findOne(Long id){
        return orderRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public Orders update(Long id, OrderDto input){
        Optional<Orders> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()){
            Orders order=orderOptional.get();
            order.setOrderId(id);
            if (input.getOrderDate() != null)
                order.setOrderDate(input.getOrderDate());
            if(input.getTotalAmount() !=null)
                order.setTotalAmount(input.getTotalAmount());
            return orderRepository.save(order);
        }
        return null;
    }
    public void delete(Long id){
        orderRepository.deleteById(id);
    }



}
