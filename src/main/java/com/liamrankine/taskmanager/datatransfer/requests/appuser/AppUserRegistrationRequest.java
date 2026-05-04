package com.liamrankine.taskmanager.datatransfer.requests.appuser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AppUserRegistrationRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;

    //Constructor
    public AppUserRegistrationRequest() {}
    public AppUserRegistrationRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }

    //Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}
