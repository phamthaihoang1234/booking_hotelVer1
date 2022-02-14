package com.example.bookinghotel.services;



import com.example.bookinghotel.entities.Hotel;

import java.util.ArrayList;
import java.util.Optional;

public interface HotelService {

    Iterable<Hotel> findAll();

    Hotel save(Hotel province);

    void delete(Long id);

    Optional<Hotel> findById(Long id);


    // phan dung code
    ArrayList<Integer> findAllHotel_Standard();
    // phan dung code-end
}
