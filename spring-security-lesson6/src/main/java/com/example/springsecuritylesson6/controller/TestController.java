package com.example.springsecuritylesson6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        return "Test 1 form backend service";
    }

    @GetMapping("/test2")
    public String test2() {
        return "Test 2 form backend service";
    }

    @PostMapping("/test3")
    public String test3() {
        return "Test 3 form backend service";
    }
}
