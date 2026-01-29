package com.example.carVatika.dto;

public class RegisterRequest {

    private String email;
    private String password;
    // private String role;
    private String userName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public String getRole() {
    //     return role;
    // }

    // public void setRole(String role) {
    //     this.role = role;
    // }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
