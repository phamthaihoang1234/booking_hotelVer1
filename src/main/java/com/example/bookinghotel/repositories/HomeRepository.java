package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HomeRepository extends CrudRepository<Room, Long> {

    @Query("SELECT h FROM Room h")
    Iterable<Room> findAllCustomQuery();


    @Query(value = "SELECT * FROM rooms where hotel_id = ?1", nativeQuery = true)
    Iterable<Room> findAllRoomByHotelId(long id);


    @Query(value = "SELECT * FROM rooms where address like ?1", nativeQuery = true)
    Iterable<Room> findAllRoomByAddress(String add);

     @Query(value = "SELECT * FROM rooms WHERE id = ?1", nativeQuery = true)
    Optional<Room> findByHomeId(Long id);

    @Query(nativeQuery = true,value = "call GetLastRoom")
    Room GetLastRoom();
}

