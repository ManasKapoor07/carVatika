package com.example.carVatika.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/open")
    public String testEndpoint() {
        return "Test endpoint is working!";
    }
    @GetMapping("/close")
    public String securedEndpoint() {
        return "Secured endpoint is working!";
    }
}
