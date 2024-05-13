package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ShippingDto {
    private Long shippingId;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @NotNull(message = "Shipping date is required")
    private Date shippingDate;

    @NotBlank(message = "Tracking number is required")
    private String trackingNumber;

    @NotNull(message = "Order ID is required")
    private Long orderId;
}