package com.example.ecommerce.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUpload {
    private final Path root = Paths.get("uploads");
    public void init(){
        try {
            Files.createDirectories(root);

        }catch (IOException e){
            throw new RuntimeException("Could not initialize folder for upload!");
        }

    }
    public void save(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        }catch(IOException exception){
            if (exception instanceof FileAlreadyExistsException)
                throw new RuntimeException("A file of that name already exists.");
            throw new RuntimeException(exception.getMessage());
        }

    }
    public Resource load(String filename){
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
    }
