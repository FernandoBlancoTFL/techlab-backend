package com.techlab.ecommerce.service;

import com.techlab.ecommerce.entity.Order;
import com.techlab.ecommerce.dto.CreateOrderRequest;
import com.techlab.ecommerce.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    Order createOrder(CreateOrderRequest request);

    void deleteById(Long id);

    void cancelOrder(Long id);

    List<OrderResponse> getOrdersByUserId(Long userId);
}
