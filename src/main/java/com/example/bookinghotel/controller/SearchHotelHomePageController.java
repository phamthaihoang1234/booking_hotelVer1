
package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// chi viet code cho homepage vao day
@Controller
public class SearchHotelHomePageController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/search_hotel_by_name")
    String getRoomBooking() {
        return "Pages/search-hotelbynameD";
    }

    @GetMapping("/getAllHotelNames")
    void getNamesOfHotel(HttpServletResponse response) {
        String ans = "";
        Iterable<Hotel> hotels = hotelService.findAll();
        //lay toan bo ten cua hotel de ho tro tim kiem bang js
        for (Hotel hotel : hotels) {
            ans+=hotel.getNameOfHotel()+",";
        }
        if(ans.isEmpty()){
            // thong bao neu loi xay ra khong lay duoc thong tin cua hotel
            ans="cant get hotel names";
        }else{
            // loai bo dau "," cuoi cung
            ans = ans.substring(0,ans.length()-2);
        }

        try (PrintWriter out = response.getWriter()) {
            out.write(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
