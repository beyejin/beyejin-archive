package com.example.scheduledevelop.auth.dto;

import com.example.scheduledevelop.user.entity.User;
import lombok.Getter;

@Getter
public class LoginResponse {

    private Long userId;
    private String username;
    private String email;
    private String message;

    private LoginResponse(Long userId, String username, String email, String message) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.message = message;
    }

    public static LoginResponse from(User user) {
        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                "로그인 성공"
        );
    }
}
