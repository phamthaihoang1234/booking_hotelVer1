package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public Hotel save(Hotel province) {
        return hotelRepository.save(province);
    }

    @Override
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }
}
