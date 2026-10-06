package org.example.nbcam_addvanced_1;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.example.nbcam_addvanced_1.common.entity.User;
import org.example.nbcam_addvanced_1.common.enums.UserRoleEnum;
import org.example.nbcam_addvanced_1.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitData {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostConstruct
    @Transactional
    public void init() {
        List<User> productList =
            List.of(
                new User("아이브",passwordEncoder.encode("1234"),"user1@sparta.com", UserRoleEnum.NORMAL),
                new User("BTS",passwordEncoder.encode("1234"),"user2@sparta.com",UserRoleEnum.ADMIN)
            );
        for (User product : productList) {
            userService.save(product);
        }
    }
}
