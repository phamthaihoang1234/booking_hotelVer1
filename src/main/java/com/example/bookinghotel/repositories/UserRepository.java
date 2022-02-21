package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Booking;
import com.example.bookinghotel.entities.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

    Optional<UserInfo> findByNameAndPassword(String name, String password);

    UserInfo findByEmail(String email);

    UserInfo findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM booking_hotelver10.users where username=?1", nativeQuery = true)// and password=?2;
    Optional<UserInfo> existsByUsernameAndPassword(String email,String password);
}
