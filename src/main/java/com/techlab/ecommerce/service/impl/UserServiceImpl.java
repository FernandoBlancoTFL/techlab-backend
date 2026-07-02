package com.techlab.ecommerce.service.impl;

import com.techlab.ecommerce.entity.User;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.repository.UserRepository;
import com.techlab.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {

        User existingUser = findById(id);

        if (existingUser == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setActive(user.getActive());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
