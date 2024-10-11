package com.revature.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
