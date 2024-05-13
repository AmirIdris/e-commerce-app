package com.example.ecommerce.controller;

import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.dto.category.CategoryResponseDto;
import com.example.ecommerce.model.Categories;
import com.example.ecommerce.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> findAll(){
        List<CategoryResponseDto> categories = productCategoryService.allCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id){
        CategoryResponseDto categories = productCategoryService.getCategoryById(id);
        return ResponseEntity.ok(categories);
    }
    @PostMapping("")
    public  ResponseEntity<Categories> createCategory(@RequestBody CategoryDto categoryDto){
        Categories categories = productCategoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categories);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Categories> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        Categories categories = productCategoryService.updateCategories(id, categoryDto);
        return ResponseEntity.ok(categories);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteCategory(@PathVariable Long id){
        productCategoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted");
    }


}
