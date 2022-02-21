package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Iterable<UserInfo> findAll();

    Optional<UserInfo> findById(Long id);

    UserInfo findByEmail(String email);

    UserInfo save(UserInfo user) throws Exception;

    UserInfo findByUserName(String username);

    boolean existsByUsername(String username);

    UserInfo existsByUsernameAndPassword(String email,String password) throws Exception;
}
