package com.example.scheduledevelop.user.entity;

import com.example.scheduledevelop.basic.entity.Base;
import com.example.scheduledevelop.user.dto.UserUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 4)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void update(UserUpdateRequest request) {
        this.username = request.getUsername();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }
}
