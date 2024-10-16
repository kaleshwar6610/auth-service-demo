package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user/{userName}")
    private User getUserById(@PathVariable("userName") String username){
        return userService.getUserById(username);
    }

    @GetMapping("/user")
    private List<User> getUserById(){
        return userService.getAllUsers();
    }

    //check the user entered details matches or not
    @PostMapping("/login")
    public String loginUser(@RequestParam("userName") String username, @RequestParam("password") String password){
        return userService.loginUser(username, password);
    }
}
