package com.example.scheduledevelop.auth.controller;

import com.example.scheduledevelop.auth.dto.LoginRequest;
import com.example.scheduledevelop.auth.dto.LoginResponse;
import com.example.scheduledevelop.auth.service.AuthService;
import com.example.scheduledevelop.basic.SessionValue;
import com.example.scheduledevelop.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpSession session) {

        User loginUser = authService.login(request);
        SessionValue sessionValue = new SessionValue(loginUser.getId(), loginUser.getUsername());
        session.setAttribute("sessionId", sessionValue);// 키 벨류 세팅 세션을?
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.from(loginUser));

    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "sessionId", required = false) SessionValue sessionValue,
            HttpSession session) {
        if (sessionValue == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
