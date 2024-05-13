package com.example.ecommerce.service;

import com.example.ecommerce.dto.cart.CartRequestDto;
import com.example.ecommerce.dto.cart.CartResponseDto;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart create(CartRequestDto input){
        Cart cart = new Cart();
//
        cart.setQuantity(input.getQuantity());
        cart.setDateAdded(input.getDateAdded());
        if (input.getProductId() != null) {

            Product product = productRepository.findById(input.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + input.getProductId()));
            cart.setProduct(product);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        cart.setCreatedBy(currentUser);
        cart.setUser(currentUser);
        return cartRepository.save(cart);
    }
    public Map<String, Object> findAll(int page, int size){
        List<CartResponseDto> carts = new ArrayList<>();
        CartResponseDto cartResponseDto = new CartResponseDto();
        Pageable paging = PageRequest.of(page, size);
        Page<Cart> pageCart = cartRepository.findAll(paging);
        pageCart.forEach((pageData)->{
            cartResponseDto.setCartId(pageData.getCartId());
            cartResponseDto.setUserId(pageData.getUser().getUserId());
            cartResponseDto.setQuantity(pageData.getQuantity());
            cartResponseDto.setProductName(pageData.getProduct().getName());
            cartResponseDto.setDateAdded(pageData.getDateAdded());
            cartResponseDto.setProductId(pageData.getProduct().getProductId());
            carts.add(cartResponseDto);
//            cartResponseDto.setUpdatedBy(pageData.getUpdatedBy().getUsername());
        });
        System.out.println(carts);
        Map<String, Object> response = new HashMap<>();
        response.put("carts", carts);
        response.put("currentPage", pageCart.getNumber());
        response.put("totalItems", pageCart.getTotalElements());
        response.put("totalPages", pageCart.getTotalPages());
        return response;
    }
    public CartResponseDto findOne(Long id){
        CartResponseDto cartResponseDto= new CartResponseDto();
        Cart cart = cartRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        cartResponseDto.setCartId(cart.getCartId());
        cartResponseDto.setProductId(cart.getProduct().getProductId());
        cartResponseDto.setUsername(cart.getUser().getUsername());
        cartResponseDto.setUserId(cart.getUser().getUserId());
        cartResponseDto.setQuantity(cart.getQuantity());
        cartResponseDto.setProductName(cart.getProduct().getName());
        cartResponseDto.setDateAdded(cart.getDateAdded());
        cartResponseDto.setCreatedBy(cart.getUser().getUsername());

        return cartResponseDto;
    }
    public Cart update(Long id, CartRequestDto input){
        Cart cart = new Cart();
        if (input.getQuantity() !=null){
            cart.setQuantity(input.getQuantity());

        }
        if (input.getProductId() !=null){
            Product product = productRepository.findById(input.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + input.getProductId()));
            cart.setProduct(product);
        }


        return cartRepository.save(cart);
    }
    public void delete(Long id){
        cartRepository.deleteById(id);
    }

}
