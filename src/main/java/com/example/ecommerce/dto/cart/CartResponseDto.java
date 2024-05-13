package com.example.ecommerce.dto.cart;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class CartResponseDto {
    private Long cartId;
    private Integer quantity;
    private Date dateAdded;
    private Long userId;
    private String username;
    private Long productId;
    private String productName;
    private String createdBy;
    private String updatedBy;


}
