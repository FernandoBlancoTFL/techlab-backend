package com.techlab.ecommerce.service.impl;

import com.techlab.ecommerce.dto.CreateOrderItemRequest;
import com.techlab.ecommerce.dto.CreateOrderRequest;
import com.techlab.ecommerce.dto.OrderItemResponse;
import com.techlab.ecommerce.dto.OrderResponse;
import com.techlab.ecommerce.entity.*;
import com.techlab.ecommerce.enums.OrderStatus;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.exception.StockInsufficientException;
import com.techlab.ecommerce.repository.*;
import com.techlab.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            ProductRepository productRepository,
                            OrderItemRepository orderItemRepository) {

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setTotal(0.0);

        order = orderRepository.save(order);

        double total = 0.0;
        List<OrderItem> items = new ArrayList<>();

        for (CreateOrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            if (product.getStock() < itemRequest.getQuantity()) {
                throw new StockInsufficientException("Not enough stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - itemRequest.getQuantity());
            productRepository.save(product);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(product.getPrice());

            orderItemRepository.save(item);

            items.add(item);

            total += product.getPrice() * itemRequest.getQuantity();
        }

        order.setTotal(total);
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Order> orders = orderRepository.findAll()
                .stream()
                .filter(o -> o.getUser().getId().equals(userId))
                .toList();

        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : orders) {

            OrderResponse response = new OrderResponse();
            response.setId(order.getId());
            response.setUserId(userId);
            response.setOrderDate(order.getOrderDate());
            response.setTotal(order.getTotal());
            response.setStatus(order.getStatus());

            List<OrderItemResponse> items = new ArrayList<>();

            for (OrderItem item : order.getItems()) {

                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProductName(item.getProduct().getName());
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setUnitPrice(item.getUnitPrice());
                itemResponse.setSubtotal(item.getQuantity() * item.getUnitPrice());

                items.add(itemResponse);
            }

            response.setItems(items);
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public void cancelOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);
    }
}