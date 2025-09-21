package com.inovace.spring_security.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    public User() {
    }

    public User(String username, String password,String roles,Boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private  String password;
    private  boolean active;
    private  String roles;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
