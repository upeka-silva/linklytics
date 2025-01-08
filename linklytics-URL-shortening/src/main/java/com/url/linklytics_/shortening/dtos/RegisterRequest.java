package com.url.linklytics_.shortening.dtos;
import lombok.*;

import java.util.Set;



public class RegisterRequest {

    private String userName;
    private String email;
    private Set<String> roles;
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String userName, String email, Set<String> roles, String password) {
        this.userName = userName;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
