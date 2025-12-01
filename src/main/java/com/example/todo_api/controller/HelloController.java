package com.example.todo_api.controller;

//@RestController
public class HelloController {

//    @GetMapping("/hello")
    public String hello(){
        return "HELLO Learner! Welcome to Spring Boot!";
    }

//    @GetMapping("/status")
    public String status(){             // Bonus endpoint
        return "{\"status\": \"running\", \"timestamp\": \"2025\"}";      // Dummy Json Object
    }
}
