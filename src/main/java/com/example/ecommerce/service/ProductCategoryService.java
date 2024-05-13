package com.example.ecommerce.service;

import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.dto.category.CategoryResponseDto;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.model.Categories;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.ProductCategoryRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    public Categories createCategory(CategoryDto input){
        Categories categories = new Categories();
        categories.setName(input.getName());
        categories.setDescription(input.getDescription());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        categories.setCreatedBy(currentUser);
        return productCategoryRepository.save(categories);
    }
    public List<CategoryResponseDto> allCategories(){
        List<CategoryResponseDto> categories = new ArrayList<>();
        CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
        productCategoryRepository.findAll().forEach((categories1)-> {
            categoryResponseDto.setDescription(categories1.getName());
            categoryResponseDto.setName(categories1.getName());
            categoryResponseDto.setProducts(categories1.getProducts());
            categoryResponseDto.setCategoryId(categories1.getCategoryId());
            categories.add(categoryResponseDto);

        });
        return categories;
    }
    public CategoryResponseDto getCategoryById(Long id){
        CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
        Categories categories= productCategoryRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        categoryResponseDto.setProducts(categories.getProducts());
        categoryResponseDto.setCategoryId(categories.getCategoryId());
        categoryResponseDto.setDescription(categories.getDescription());
        categoryResponseDto.setName(categories.getName());
        categoryResponseDto.setCreatedBy(categories.getCreatedBy().getUsername());

        return categoryResponseDto;
    }
    public Categories updateCategories(Long id, CategoryDto input){
        Optional<Categories> categoriesOptional = productCategoryRepository.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
    if (categoriesOptional.isPresent()){
       Categories categories=categoriesOptional.get();
       categories.setCategoryId(id);
        System.out.println(input + "Category Request");
       if (input.getName() != null)
        categories.setName(input.getName());
       if(input.getDescription() !=null)
           categories.setDescription(input.getDescription());
       categories.setUpdatedBy(currentUser);
       return productCategoryRepository.save(categories);
    }
    return null;
    }
    public void deleteCategory(Long id){
        productCategoryRepository.deleteById(id);
    }

}
