package com.example.ecommerce.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id){
        super("Not Found ID: " + id);
    }
}
