package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.basic.SessionValue;
import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    // 저장 회원가입
    @Transactional
    public UserCreateResponse save(UserCreateRequest request) {

        //로그인상태이면 유저 생성 안될 수 있도록

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        User saveUser = userRepository.save(user);
        return UserCreateResponse.from(saveUser);
    }

    // 다 건 조회
    @Transactional(readOnly = true)
    public List<UserGetResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserGetResponse::from).toList();
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public UserGetResponse findById(Long userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        return UserGetResponse.from(savedUser);
    }

    // 수정
    @Transactional
    public UserUpdateResponse updateById(Long userId, UserUpdateRequest request, SessionValue sessionValue) {

        User updatedUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );

        if (!sessionValue.getUserId().equals(userId)) {
            throw new IllegalStateException("본인 정보만 수정 가능합니다.");
        }
        if (request.getPassword().length() < 8) {
            throw new IllegalStateException("비밀번호는 8글자 이상이어야 합니다");
        }
        updatedUser.update(request);
        return UserUpdateResponse.from(updatedUser);
    }

    // 삭제
    @Transactional
    public void delete(Long userId, SessionValue sessionValue) {

        User deletedUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        if (!sessionValue.getUserId().equals(userId)) {
            throw new IllegalStateException("본인만 삭제 가능합니다.");
        }

        //로그아웃

        userRepository.delete(deletedUser);

    }

}
