package com.example.ecommerce.controller;

import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductResponseDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping(value = "",consumes = { "multipart/form-data" })
    public  ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto,@RequestParam("file") MultipartFile file){
        Product product = productService.createProduct(productDto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @GetMapping("")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findAll(@PathVariable Long id){
        ProductResponseDto product = productService.findOne(id);
        return ResponseEntity.ok(product);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        Product product = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteCategory(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted");
    }

}
