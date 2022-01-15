package com.example.bookinghotel.repositories;


import com.example.bookinghotel.entities.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    @Query(nativeQuery = true,value = "select Hotel.* from Hotel left join Hotel_Property " +
            "on Hotel_Property_id = Hotel_property.id " +
            "where hotel.hotel_address LIKE CONCAT('%',?1,'%') and Hotel_Property.type LIKE CONCAT('%',?2,'%') ")
    Iterable<Hotel> findByHotel_addressContainingAndHotel_property(String contains,String hotelProperty);
}
