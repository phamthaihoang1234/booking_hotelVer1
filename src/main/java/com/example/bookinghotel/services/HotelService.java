package com.example.bookinghotel.services;



import com.example.bookinghotel.entities.Hotel;

import java.util.Optional;

public interface HotelService {

    Iterable<Hotel> findAll();

    Hotel save(Hotel province);

    void delete(Long id);

    Optional<Hotel> findById(Long id);
}
