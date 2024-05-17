package com.example.config;

import lombok.Data;

@Data
public class UserContext {
    private Long userId;
    private String accountName;
    private String role;
}
