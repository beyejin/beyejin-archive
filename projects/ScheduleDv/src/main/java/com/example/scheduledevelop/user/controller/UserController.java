package com.example.scheduledevelop.user.controller;

import com.example.scheduledevelop.basic.SessionValue;
import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    //생성
    @PostMapping
    public ResponseEntity<UserCreateResponse> save(@Valid @RequestBody UserCreateRequest request) {
        UserCreateResponse result = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 다 건 조회
    @GetMapping
    public ResponseEntity<List<UserGetResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    // 단 건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserGetResponse> findById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    // 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> updateById(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequest request,
                                                         @SessionAttribute(name = "sessionId", required = false) SessionValue sessionValue) {

        if (sessionValue == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 작업입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateById(userId, request, sessionValue));
    }

    //삭제

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletById(@PathVariable Long userId,
                                          @SessionAttribute(name = "sessionId", required = false) SessionValue sessionValue,
                                          HttpSession session) {
        if (sessionValue == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 작업입니다.");
        }
        userService.delete(userId, sessionValue);
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
