package com.techlab.ecommerce.entity;

import com.techlab.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Order() {
    }

    public Order(Long id,
                 LocalDateTime orderDate,
                 Double total,
                 OrderStatus status,
                 User user) {

        this.id = id;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
