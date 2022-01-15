package com.example.bookinghotel.repositories;

import com.example.bookinghotel.entities.Hotel_Property;
import org.springframework.data.repository.CrudRepository;

public interface HotelPropertyType extends CrudRepository<Hotel_Property,Long> {

}
