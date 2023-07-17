package com.example.usersapi.model;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
