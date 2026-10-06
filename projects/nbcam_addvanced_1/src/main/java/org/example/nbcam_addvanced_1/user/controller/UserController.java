package org.example.nbcam_addvanced_1.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbcam_addvanced_1.common.utils.JwtUtil;
import org.example.nbcam_addvanced_1.user.model.request.LoginRequestDto;
import org.example.nbcam_addvanced_1.user.model.request.UpdateUserEmailDto;
import org.example.nbcam_addvanced_1.user.model.response.LoginResponseDto;
import org.example.nbcam_addvanced_1.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @GetMapping("/get")
    @PreAuthorize("hasRole('NORMAL')")
    public String getUserInfo(@AuthenticationPrincipal User user) {
        log.info(user.getUsername());
        return "유저 컨트롤러에 접근 하였습니다.";
    }

    @PutMapping("/{username}/email")
    public String editEmail(@PathVariable String username, @RequestBody UpdateUserEmailDto dto) {
        userService.updateEmail(username,dto.getEmail());
        return "수정 완료!";

    }

    // 토큰 검증 테스트
    @GetMapping("/validate")
    public ResponseEntity<Boolean> checkValidate(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        String jwt = authorizationHeader.substring(7);

        Boolean validate = jwtUtil.validateToken(jwt);

        return ResponseEntity.ok(validate);
    }

    // 토큰 복호화 테스트
    @GetMapping("/username")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        String jwt = authorizationHeader.substring(7);

        String username = jwtUtil.extractUsername(jwt);

        return ResponseEntity.ok(username);
    }

}
