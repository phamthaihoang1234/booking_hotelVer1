package com.example.bookinghotel.repositories;

import com.example.bookinghotel.entities.Blog;

import com.example.bookinghotel.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {

//    @Query(value = "SELECT * FROM blog where hotel_id = ?1", nativeQuery = true)
//    Iterable<Room> findAllRoomByUserId(long id);
}
