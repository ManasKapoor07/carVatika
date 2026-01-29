package com.example.carVatika.dto;

public class LoginResponse {
 
    
    private String token;
    private String tokenType = "Bearer";
    private long expirationTime ;
    private Long userId;
    private String role;
    private String email;

    public LoginResponse(String token, long expirationTime , Long userId, String role, String email) {
        this.token = token;
        this.expirationTime = expirationTime;
        this.userId = userId;
        this.role = role;
        this.email = email;

    }
    public String getToken() {
        return token;
    }
    public String getTokenType() {
        return tokenType;
    }
    public long getExpirationTime() {
        return expirationTime;
    }

    public Long getUserId() {
        return userId;
    }
    public String getRole(){
        return role;
    }
    public String getEmail(){
        return email;
    }
    

}
