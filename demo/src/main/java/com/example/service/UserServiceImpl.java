package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "User is created";
    }

    @Override
    public User getUserById(String username) {
        return userRepository.findByUserNameIgnoreCase(username);
    }

    @Override
    public String loginUser(String username, String password) {
        User user = userRepository.findByUserNameIgnoreCase(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatch = encoder.matches(password, user.getPassword());

        if (passwordMatch){
            return "User has access to login";
        }else {
            return "User has no access to login";
        }

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
