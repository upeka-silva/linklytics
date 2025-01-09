package com.url.linklytics_.shortening.security.jwt;




public class JwtAuthenticationResponse {
    private String token;


    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
