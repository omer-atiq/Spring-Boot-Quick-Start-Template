package com.inovace.spring_aop.controller;


import com.inovace.spring_aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired UserService userService;

    @RequestMapping("/hello")
    public String getHello(){

        userService.getUserById(1);
        userService.createUser("hello bello");
        return "abcde";



    }
}
