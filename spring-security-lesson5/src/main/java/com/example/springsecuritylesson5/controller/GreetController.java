package com.example.springsecuritylesson5.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping("/greet")
    public String greet() {
        return "Greet from backend service.";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from backend service.";
    }
}
