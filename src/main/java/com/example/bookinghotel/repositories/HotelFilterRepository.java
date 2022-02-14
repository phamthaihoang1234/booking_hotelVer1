package com.example.bookinghotel.repositories;

import com.example.bookinghotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelFilterRepository extends JpaRepository<Hotel,Long> {

    //phan dung code
    // tim theo property va standard cua hotel
    @Query(nativeQuery = true,value = "select hotel.* from hotel inner join hotel_property" +
            "on hotel.hotel_property_id = hotel_property.id  " +
            "where hotel.hotel_standard = ?1 and hotel_property.type = ?2")
    public Iterable<Hotel> listHotelByStandardAndProperty(int standard, String property);

    // tim theo property va standard cua hotel va order by standard
    @Query(nativeQuery = true,value = "select hotel.* from hotel inner join hotel_property" +
            "on hotel.hotel_property_id = hotel_property.id  " +
            "where hotel.hotel_standard = ?1 and hotel_property.type = ?2" +
            "order by hotel.hotel_standard ?3")
    public Iterable<Hotel> listHotelByStandardAndPropertyAndOrderByStandard(int standard, String property, String orderBy);

    // phan dung code-end
}
