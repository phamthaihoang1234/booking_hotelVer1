package com.example.bookinghotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HotelReportController {

    @GetMapping("/report")
    public String hotelReport(){
        return "Room/hotel_report.html";
    }

}
