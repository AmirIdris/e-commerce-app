package com.example.ecommerce.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data

public class CartRequestDto {
    private Long cartId;
    @NotNull
    @Positive(message = "Quantity Must be Positive Integer")
    private Integer quantity;
    @NotNull(message = "User Id is required")
    private Long userId;
    @NotNull(message = "Added Date I required")
    private Date dateAdded;
    @NotNull(message = "Product Id is required")
    private Long productId;

}
