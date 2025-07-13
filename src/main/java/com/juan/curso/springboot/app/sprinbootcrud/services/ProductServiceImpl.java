package com.juan.curso.springboot.app.sprinbootcrud.services;

import com.juan.curso.springboot.app.sprinbootcrud.model.Product;
import com.juan.curso.springboot.app.sprinbootcrud.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    @Override
    public Optional<Product> deleteById(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return product;
                });
    }


    @Transactional
    @Override
    public Optional<Product> delete(Product product) {
        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        deletedProduct.ifPresent(productRepository::delete);
        return deletedProduct;
    }

}
