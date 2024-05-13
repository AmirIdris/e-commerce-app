package com.example.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {
    private Long categoryId;
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
}
