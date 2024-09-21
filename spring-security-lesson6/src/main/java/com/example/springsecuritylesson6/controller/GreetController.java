package com.example.springsecuritylesson6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetController {

    @GetMapping("/greet1")
    public String greet1() {
        return "Greet 1 form backend service";
    }

    @GetMapping("/greet2")
    public String greet2() {
        return "Greet 2 form backend service";
    }

    @PostMapping("/greet3")
    public String greet3() {
        return "Greet 3 form backend service";
    }
}
