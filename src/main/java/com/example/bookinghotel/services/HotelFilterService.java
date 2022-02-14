package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelFilterService extends CrudRepository<Hotel,Long> {
    public Iterable<Hotel> listHotelByStandardAndPropertyAndOrderByStandard(int standard, String property,String orderby);
}
