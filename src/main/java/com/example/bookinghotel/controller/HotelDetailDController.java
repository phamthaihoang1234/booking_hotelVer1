package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HotelDetailDController {
    @Autowired
    HotelService hotelService;
    @GetMapping("/hotel_detail{id}")
    public String hotelDetail(@PathVariable("id") Long id, Model model){
        Optional<Hotel> hotel = hotelService.findById(id);
        if(hotel.isPresent()){
            model.addAttribute("background_image",hotel.get().getImages().get(0).getImage());
            model.addAttribute("hotel",hotel.get());
            return "hotel_details/index";
        }
        return "redirect:/";
    }

    @PostMapping("/getRoomsHotelDetail")
    public ModelAndView getRooms_hotel_detail(){
        ModelAndView mv = new ModelAndView("hotel_details/rooms_hotel_detail");

        return mv;

    }
}
