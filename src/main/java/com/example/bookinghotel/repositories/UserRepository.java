package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

    Optional<UserInfo> findByNameAndPassword(String name, String password);

    UserInfo findByEmail(String email);



    UserInfo findByUsername(String username);

    boolean existsByUsername(String username);

}
