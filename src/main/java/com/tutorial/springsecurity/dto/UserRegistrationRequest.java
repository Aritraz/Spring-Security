package com.tutorial.springsecurity.dto;

public class UserRegistrationRequest {

    private String userid;
    private String username;
    private String password;
    private String role;

    public UserRegistrationRequest(String userId ,String username, String password, String role) {
        this.userid=userId;
        this.username = username;
        this.password = password;
        this.role = role;

    }

    public UserRegistrationRequest()
    {

    }
    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
