package com.samet.erdem.tracker.model;

import java.sql.Timestamp;

/**
 * @class User
 * @brief Model class representing a user in the system.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private double height;
    private double weight;
    private Timestamp createdAt;
    
    // Constructor
    public User(String username, String password, double height, double weight) {
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }
    
    // Constructor with ID
    public User(int id, String username, String password, double height, double weight, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
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
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    // BMI Hesaplama
    public double calculateBMI() {
        // Boy metre cinsinden olmalı
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", createdAt=" + createdAt +
                '}';
    }
} 