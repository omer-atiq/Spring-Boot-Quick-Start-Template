package com.inovace.spring_aop.service;


import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserById(int id) {
        System.out.println("Fetching user: " + id);
        return "User" + id;
    }

    public void createUser(String name) {
        System.out.println("Creating user: " + name);
    }
}