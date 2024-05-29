package com.app.FormValidation.model;

public class LoginData {

    private String Username;
    private String email;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "LoginData{" +
                "Username='" + Username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
