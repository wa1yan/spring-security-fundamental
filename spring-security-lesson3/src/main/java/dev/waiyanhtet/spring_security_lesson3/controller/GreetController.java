package dev.waiyanhtet.spring_security_lesson3.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping("/greet")
    public String greet() {
        return "Hello from backend service.";
    }

    @PostMapping("/hello")
    public String hello(@RequestBody String data) {
        return "Hello from backend service with post request.";
    }
}
