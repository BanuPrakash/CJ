package com.cisco.orderapp.security.repo;


import com.cisco.orderapp.security.entity.User;
import com.cisco.orderapp.security.service.UserDetailsServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
