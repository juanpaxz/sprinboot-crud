package com.juan.curso.springboot.app.sprinbootcrud.services;

import com.juan.curso.springboot.app.sprinbootcrud.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> deleteById(Long id);
    Optional<Product>delete(Product product);
    Optional<Product> update(Long id,Product product);

}
