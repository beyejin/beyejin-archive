package org.example.nbcam_addvanced_1.user.repository;


import java.util.Optional;
import org.example.nbcam_addvanced_1.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
