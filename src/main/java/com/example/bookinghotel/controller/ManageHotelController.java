package com.example.bookinghotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageHotelController {

//    @GetMapping("/manageHotels")
//    public String manageHotel(){
//        return "Pages/hotelManage/homepageHotel";
//    }

    @GetMapping("/manageHotels")
    public String manageHotel(){
        return "Pages/hotelManage/createHotel";
    }


}
