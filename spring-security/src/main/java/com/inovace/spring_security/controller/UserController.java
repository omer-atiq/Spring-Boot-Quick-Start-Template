package com.inovace.spring_security.controller;


import com.inovace.spring_security.entities.User;
import com.inovace.spring_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getPassword(),user.getRoles(),user.isActive());
    }
}