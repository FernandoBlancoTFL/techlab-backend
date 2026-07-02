package com.techlab.ecommerce.service.impl;

import com.techlab.ecommerce.entity.Order;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.repository.OrderRepository;
import com.techlab.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, Order order) {

        Order existingOrder = findById(id);

        if (existingOrder == null) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }

        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setTotal(order.getTotal());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setUser(order.getUser());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}