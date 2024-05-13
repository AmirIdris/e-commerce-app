package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewDto {
    private Long reviewId;

    @NotBlank(message = "Comment is required")
    private String comment;

    @NotNull(message = "Date created is required")
    private Date dateCreated;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Product ID is required")
    private Long productId;
}
