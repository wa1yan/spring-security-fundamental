package dev.waiyanhtet.spring_security_lesson2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping("/greet")
    public String greet() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(System.out::println);
        return "Hello from backend service.";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from backend service with post request.";
    }
}
