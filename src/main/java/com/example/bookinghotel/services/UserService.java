package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Iterable<UserInfo> findAll();

    Optional<UserInfo> findById(Long id);

    Optional<UserInfo> findByNameAndPassword(String name, String password);

    Optional<UserInfo> findByEmail(String email);

    UserInfo save(UserInfo user) throws Exception;

    Optional<UserInfo> findByUserName(String username);

    boolean existsByUsername(String username);
}
