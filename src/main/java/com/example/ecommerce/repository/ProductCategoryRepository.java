package com.example.ecommerce.repository;

import com.example.ecommerce.model.Categories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<Categories, Long> {

}
