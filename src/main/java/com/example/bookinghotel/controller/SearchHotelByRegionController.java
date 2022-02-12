package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchHotelByRegionController {
    @GetMapping("/all-locations")
    String searchHotelByRegion(){
        return "Pages/all-locations";
    }

}
