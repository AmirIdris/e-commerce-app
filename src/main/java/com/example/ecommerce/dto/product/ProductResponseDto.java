package com.example.ecommerce.dto.product;

import lombok.Data;

import java.util.Date;
@Data
public class ProductResponseDto {
    private Long productId;
    private String name;
    private Integer quantity;
    private Double price;
    private String description;
    private Long categoryId;
    private String createdBy;
    private String updatedBy;
    private Long userId;
}
