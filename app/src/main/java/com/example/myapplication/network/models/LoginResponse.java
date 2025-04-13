package com.example.myapplication.network.models;

public class LoginResponse {
    private String token;
    private User user;

    // Getters
    public String getToken() { return token; }
    public User getUser() { return user; }

    public static class User {
        private String name;
        private String email;
        // Getters
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
