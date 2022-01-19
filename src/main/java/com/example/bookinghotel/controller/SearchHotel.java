package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchHotel {
    @GetMapping("room_booking")
    String getRoomBooking(){
        return "Pages/room-bookings";
    }
}
