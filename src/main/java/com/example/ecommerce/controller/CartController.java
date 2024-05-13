package com.example.ecommerce.controller;

import com.example.ecommerce.dto.cart.CartRequestDto;
import com.example.ecommerce.dto.cart.CartResponseDto;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {


    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    public ResponseEntity<Cart> createCart(@RequestBody CartRequestDto cartDto){
        Cart orders = cartService.create(cartDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> findAll( @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "3") int size){
        Map<String, Object> carts = cartService.findAll(page, size);
        return  ResponseEntity.ok(carts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> findOne(@PathVariable Long id){
        CartResponseDto carts = cartService.findOne(id);
        return  ResponseEntity.ok(carts);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cart> update(@RequestBody CartRequestDto cartDto, @PathVariable Long id){
        Cart carts = cartService.update(id, cartDto);
        return  ResponseEntity.ok(carts);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable  Long id){
        cartService.delete(id);
        return ResponseEntity.ok("deleted");
    }



}
