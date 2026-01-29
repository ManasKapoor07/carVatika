package com.example.carVatika.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carVatika.model.User;
import com.example.carVatika.service.Userservice;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Userservice userservice;

    public UserController(Userservice userservice) {
        this.userservice = userservice;
    }

    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return userservice.getUserById(userId);
    }
}
