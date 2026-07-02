package com.techlab.ecommerce.service;

import com.techlab.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(Long id, User user);

    void deleteById(Long id);

}
