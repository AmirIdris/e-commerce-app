package com.example.ecommerce.dto.category;

import com.example.ecommerce.model.Product;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CategoryResponseDto {
    private Long categoryId;
    private String name;
    private String description;
    private String createdBy;
    private Set<Product> products;

}
