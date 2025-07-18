package com.cisco.asyncdemo.async;

// Immutable objects
public record Post(int id, String title, String body, int userId) {
}


/*
    public class Post {
        int id, String title, String body, int userId;
        constructors
        getters
        // setters
     }

 */