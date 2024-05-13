package com.example.ecommerce.dto.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;
@Data
public class PaymentDto {
    private Long paymentId;

    private String paymentMethod;

    @NotNull(message = "Amount paid is required")
    @Positive(message = "Amount paid must be a positive number")
    private Double amountPaid;

    @NotNull(message = "Payment date is required")
    private Date paymentDate;

    @NotNull(message = "Order ID is required")
    private Long orderId;
}
