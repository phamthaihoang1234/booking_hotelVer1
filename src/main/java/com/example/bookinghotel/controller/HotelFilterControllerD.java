package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelFilterControllerD {
    @GetMapping("/search-hotels")
    String HotelFiler(){
        return "Pages/search-hotelsD";
    }
}
