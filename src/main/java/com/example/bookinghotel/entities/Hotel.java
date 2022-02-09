package com.example.bookinghotel.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status = true;
    private String nameOfHotel ;
    private String hotel_address;
    private int hotel_standard;
    private String phone;
    private double location_lat;
    private double location_lng;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(double location_lat) {
        this.location_lat = location_lat;
    }

    public double getLocation_lng() {
        return location_lng;
    }

    public void setLocation_lng(double location_lng) {
        this.location_lng = location_lng;
    }

    public List<Hotel_Preview_Image> getImages() {
        return images;
    }

    public void setImages(List<Hotel_Preview_Image> images) {
        this.images = images;
    }

    public Hotel() {
    }

    public int getHotel_standard() {
        return hotel_standard;
    }

    public void setHotel_standard(int hotel_standard) {
        this.hotel_standard = hotel_standard;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public Hotel(Long id, Boolean status) {
        this.id = id;
        this.status = status;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_property_id")
    private Hotel_Property hotel_property;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel")
    private List<Hotel_Preview_Image> images;

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
