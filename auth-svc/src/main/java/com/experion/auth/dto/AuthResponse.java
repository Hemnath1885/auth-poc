package com.experion.auth.dto;

public class AuthResponse {
    public AuthResponse(String token,String message) {
        this.token = token;
        this.message = message;
    }

    public AuthResponse(String message) {
        this.message = message;
    }

    private String token;
    private final String message;

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
