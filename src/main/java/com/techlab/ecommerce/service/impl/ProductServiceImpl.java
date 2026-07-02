package com.techlab.ecommerce.service.impl;

import com.techlab.ecommerce.entity.Product;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.repository.ProductRepository;
import com.techlab.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {

        Product existingProduct = findById(id);

        if (existingProduct == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
