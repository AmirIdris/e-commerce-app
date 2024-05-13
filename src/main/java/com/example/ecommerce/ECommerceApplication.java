package com.example.ecommerce;

import com.example.ecommerce.util.FileUpload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication {
    FileUpload fileUpload;
    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    public void run(String arg) throws Exception {
//    storageService.deleteAll();
        fileUpload.init();
    }
}
