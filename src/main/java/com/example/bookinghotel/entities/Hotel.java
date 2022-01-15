package com.example.bookinghotel.entities;

import javax.persistence.*;

@Entity
@Table (name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status = true;
    private String nameOfHotel ;


    public Hotel() {
    }

    public Hotel(Long id, Boolean status) {
        this.id = id;
        this.status = status;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_property_id")
    private Hotel_Property hotel_property;

    public Hotel_Property getHotel_property() {
        return hotel_property;
    }

    public void setHotel_property(Hotel_Property hotel_property) {
        this.hotel_property = hotel_property;
    }

    public String getNameOfHotel() {
        return nameOfHotel;
    }

    public void setNameOfHotel(String nameOfHotel) {
        this.nameOfHotel = nameOfHotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
