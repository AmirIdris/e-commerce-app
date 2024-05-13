package com.example.ecommerce.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;

    @NotNull(message = "Order date is required")
    private Date orderDate;

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be a positive number")
    private Double totalAmount;

    @NotNull(message = "Status is required")
    private String status;

    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Product ID is required")
    private List<Long> productIds; // Assuming you want to transfer product IDs only
    // If you want to transfer product names, you can change the type to List<String>


}
