package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelDetailDController {
    @GetMapping("/room-bookings")
    public String hotelDetail(){
        return "Pages/room-bookings";
    }
}
