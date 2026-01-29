package com.example.carVatika.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.carVatika.Repositories.UserRepositories;
import com.example.carVatika.exception.InvalidCredentialsException;
import com.example.carVatika.exception.UserAlreadyExistsException;
import com.example.carVatika.model.User;

@Service
public class Userservice {

    private PasswordEncoder passwordEncoder;
    private UserRepositories userRepositories;

    // contructor injection
    public Userservice(PasswordEncoder passwordEncoder, UserRepositories repositories) {
        this.passwordEncoder = passwordEncoder;
        this.userRepositories = repositories;
    }

    public User registerUser(String email, String password, String userName) {

        Optional<User> existingUser = userRepositories.findByEmail(email);

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists.");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUserName(userName);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("USER");
        // newUser.setRole(role);

        return userRepositories.save(newUser);

    }

    public User loginUser(String email, String password) {
        Optional<User> userPresent = userRepositories.findByEmail(email);
        if (userPresent.isPresent()) {
            User user = userPresent.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new InvalidCredentialsException("Invalid password.");
            }
        } else {
            throw new UserAlreadyExistsException("User with email " + email + " not found.");
        }

    }

    public User getUserById(Long id ) {
        
        return userRepositories.findById(id).orElse(null);
    }
}
