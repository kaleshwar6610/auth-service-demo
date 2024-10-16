package com.example.service;

import com.example.entity.User;

import java.util.List;


public interface UserService {
    String createUser(User user);

    User getUserById(String username);

    String loginUser(String username, String password);

    List<User> getAllUsers();
}
