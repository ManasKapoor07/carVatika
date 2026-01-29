package com.example.carVatika.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carVatika.dto.ApiResponse;
import com.example.carVatika.dto.LoginResponse;
import com.example.carVatika.dto.RegisterRequest;
import com.example.carVatika.dto.RequestResponse;
import com.example.carVatika.model.User;
import com.example.carVatika.service.Userservice;
import com.example.carVatika.utility.Jwtutility;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

        private final Userservice userservice;
        private final Jwtutility jwtutility;

        public UserAuthController(Userservice userservice, Jwtutility jwtutility) {
                this.userservice = userservice;
                this.jwtutility = jwtutility;
        }

        // ================= REGISTER =================
        @PostMapping("/register")
        public ResponseEntity<ApiResponse<RequestResponse>> register(
                        @RequestBody RegisterRequest request) {

                User user = userservice.registerUser(
                                request.getEmail(),
                                request.getPassword(),
                                request.getUserName());

                RequestResponse responseData = new RequestResponse(user.getid(), user.getUserName(), user.getEmail());

                ApiResponse<RequestResponse> response = new ApiResponse<>(true, "User registered successfully",
                                responseData);

                return ResponseEntity.status(201).body(response);
        }

        // ================= LOGIN =================
        @PostMapping("/login")
        public ResponseEntity<ApiResponse<LoginResponse>> login(
                        @RequestBody RegisterRequest request) {

                User user = userservice.loginUser(
                                request.getEmail(),
                                request.getPassword());

                String token = jwtutility.generateToken(
                                user.getEmail(),
                                user.getRole(),
                                user.getid());

                LoginResponse loginResponse = new LoginResponse(
                                token,
                                jwtutility.getExpirationTime(),
                                user.getid(),
                                user.getRole(),
                                user.getEmail());

                ApiResponse<LoginResponse> response = new ApiResponse<>(true, "Login successful", loginResponse);

                return ResponseEntity.ok(response);
        }
}
