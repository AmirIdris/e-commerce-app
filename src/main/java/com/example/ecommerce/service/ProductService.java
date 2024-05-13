package com.example.ecommerce.service;

import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductResponseDto;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.model.Categories;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductCategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.util.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final FileUpload fileUpload;

    public ProductService(ProductRepository productRepository,
                          ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;


        fileUpload = new FileUpload();
    }
    public Product createProduct(ProductDto input, MultipartFile file){
        Product product = new Product();
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());
        fileUpload.save(file);

        if (input.getCategoryIds() != null) {
            Set<Categories> categories = new HashSet<>();
            Categories category = productCategoryRepository.findById(input.getCategoryIds())
                    .orElseThrow(() -> new NotFoundException(input.getCategoryIds()));
            categories.add(category);

            product.setCategories(categories);


        }
        System.out.println(product +"Product Being Created");

        return productRepository.save(product);
    }

    public List<Product> getAll(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }
    public ProductResponseDto findOne(Long id){
        ProductResponseDto productResponseDto=new ProductResponseDto();
        Product product=productRepository.findById(id).orElseThrow(()->new NotFoundException(id));

        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setProductId(product.getProductId());
        productResponseDto.setQuantity(product.getQuantity());


        if (product.getCreatedBy() !=null)
            productResponseDto.setCreatedBy(product.getCreatedBy().getUsername());
        if (product.getUpdatedBy() !=null)
            productResponseDto.setUpdatedBy(product.getUpdatedBy().getUsername());
        if (product.getUser() !=null)
            productResponseDto.setUserId(product.getUser().getUserId());

        return productResponseDto;

    }
    public Product updateProduct(Long id, ProductDto input){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product product=productOptional.get();
            product.setProductId(id);
            if (input.getName() != null)
                product.setName(input.getName());
            if(input.getDescription() !=null)
                product.setDescription(input.getDescription());
            return productRepository.save(product);
        }
        return null;
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
