package com.example.carVatika.dto;

public class RequestResponse {
    private Long id;
    private String userName;
    private String email;

    public RequestResponse(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Long getid() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getuserName(){
        return userName;
    }
}
