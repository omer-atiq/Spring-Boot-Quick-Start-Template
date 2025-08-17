package com.example.spring.Spring_Batch.Models;


//@Entity
//public class User {
//
//    private Long id;
//    private String name;
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//}


public class User {
    private String id;
    private String name;

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}