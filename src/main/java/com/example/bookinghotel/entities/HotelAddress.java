package com.example.bookinghotel.entities;


import javax.persistence.*;

@Entity
@Table
public class HotelAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressOfHotel;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hotel_id",nullable = false,updatable = true)
//    private Hotel hotel;


    public HotelAddress() {
    }

    public HotelAddress(Long id, String addressOfHotel, Hotel hotel) {
        this.id = id;
        this.addressOfHotel = addressOfHotel;
//        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressOfHotel() {
        return addressOfHotel;
    }

    public void setAddressOfHotel(String addressOfHotel) {
        this.addressOfHotel = addressOfHotel;
    }

//    public Hotel getHotel() {
//        return hotel;
//    }
//
//    public void setHotel(Hotel hotel) {
//        this.hotel = hotel;
//    }
}
