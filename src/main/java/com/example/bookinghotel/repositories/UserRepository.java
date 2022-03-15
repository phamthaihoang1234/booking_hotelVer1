package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

    Optional<UserInfo> findByNameAndPassword(String name, String password);

    UserInfo findByEmail(String email);

    UserInfo findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<UserInfo> findById(Long id);

    Iterable<UserInfo> findAll();


    @Query(value = "SELECT * FROM booking_hotelver10.users Where users.name like concat('%',?1,'%')", nativeQuery = true)
    Iterable<UserInfo> findByNameContaining(String name);

    @Query(value = "SELECT * FROM booking_hotelver10.users where username=?1", nativeQuery = true)// and password=?2;
    Optional<UserInfo> existsByUsernameAndPassword(String email,String password);
}
