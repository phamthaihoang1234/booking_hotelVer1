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
    ArrayList<Integer> findAllHotel_StandardByLocation(String location);
    ArrayList<Integer> findAllHotel_StandardByAllInputType(String location,String start_date, String end_date,int number_of_people);
    ArrayList<Integer> findAllHotel_PropertyByLocation(String location);
    ArrayList<Integer> findAllHotel_PropertyByAllInputType(String location,String start_date, String end_date,int number_of_people);
    // phan dung code-end
}
