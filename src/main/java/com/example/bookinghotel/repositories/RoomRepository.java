package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findById(Long id);

}
