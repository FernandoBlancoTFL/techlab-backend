package com.techlab.ecommerce.service;

import com.techlab.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    void deleteById(Long id);

}
