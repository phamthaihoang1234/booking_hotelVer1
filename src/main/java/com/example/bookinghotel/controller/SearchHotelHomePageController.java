
package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// chi viet code cho homepage vao day
@Controller
public class SearchHotelHomePageController {
    @GetMapping("/search_hotel_by_name")
    String getRoomBooking() {
        return "Pages/search-hotelbynameD";
    }
}
