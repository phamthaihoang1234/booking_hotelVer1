package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
