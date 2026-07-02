package com.techlab.ecommerce.service;

import com.techlab.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    Order save(Order order);

    Order update(Long id, Order order);

    void deleteById(Long id);

}
