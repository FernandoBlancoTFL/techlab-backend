package com.techlab.ecommerce.config;

import com.techlab.ecommerce.entity.*;
import com.techlab.ecommerce.enums.OrderStatus;
import com.techlab.ecommerce.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public DataInitializer(
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            OrderRepository orderRepository) {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {

        if (
            categoryRepository.count() > 0 ||
            productRepository.count() > 0 ||
            userRepository.count() > 0
        ) {
            System.out.println("Database already initialized.");
            return;
        }

        loadCategories();

        loadProducts();

        loadUsers();

        loadOrders();

        System.out.println("=====================================");
        System.out.println("Initial data loaded successfully.");
        System.out.println("=====================================");
    }

    private void loadCategories() {

        categoryRepository.save(new Category(null,"Laptops"));
        categoryRepository.save(new Category(null,"Smartphones"));
        categoryRepository.save(new Category(null,"Accessories"));

    }

    private void loadProducts() {

        Category laptops = categoryRepository.findById(1L).get();
        Category smartphones = categoryRepository.findById(2L).get();
        Category accessories = categoryRepository.findById(3L).get();

        productRepository.save(
                new Product(
                        null,
                        "MacBook Air M3",
                        "Apple laptop",
                        1500.0,
                        8,
                        "https://...",
                        laptops));

        productRepository.save(
                new Product(
                        null,
                        "Samsung Galaxy S25",
                        "Android smartphone",
                        1200.0,
                        15,
                        "https://...",
                        smartphones));

        productRepository.save(
                new Product(
                        null,
                        "Mechanical Keyboard",
                        "RGB Keyboard",
                        90.0,
                        30,
                        "https://...",
                        accessories));

    }

    private void loadUsers() {

        userRepository.save(
                new User(
                        null,
                        "Fernando",
                        "Blanco",
                        "fernando@test.com",
                        "123456",
                        true));

        userRepository.save(
                new User(
                        null,
                        "Juan",
                        "Perez",
                        "juan@test.com",
                        "123456",
                        true));

    }

    private void loadOrders() {

        User user = userRepository.findById(1L).get();

        Order order = new Order();

        order.setUser(user);

        order.setOrderDate(LocalDateTime.now());

        order.setStatus(OrderStatus.DELIVERED);

        order.setTotal(1590.0);

        orderRepository.save(order);

    }

}
