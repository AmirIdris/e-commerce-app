package com.example.ecommerce.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Set;

@Data
public class ProductDto {
    private Long productId;
    @NotBlank(message = "Name cannot be Blank")
    private String name;
    @NotNull
    @Min(value = 1, message = "You cannot Provide less tha 1")
    private Integer quantity;
    @NotNull
    private Double price;
    private String description;
    @NotNull
    private Long categoryIds;
}
