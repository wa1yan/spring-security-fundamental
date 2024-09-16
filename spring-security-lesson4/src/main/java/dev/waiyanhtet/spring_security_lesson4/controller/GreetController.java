package dev.waiyanhtet.spring_security_lesson4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreetController {

    @GetMapping("/greet")
    public String greet() {
        return "Hello from backend service.";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from backend service with post request.";
    }

}
