package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
    public ModelAndView getRooms_hotel_detail(@RequestParam("start_date")int start_date,
                                              @RequestParam("end_date")int end_date,
                                              @RequestParam("number_of_people")int number_of_people,
                                              @RequestParam("hotel_name")String hotel_name
    ){
        Optional<Hotel> find_hotel = null;
        if(hotel_name != "")
        find_hotel = hotelService.findHotelByName(hotel_name);
        Hotel hotel;
        ModelAndView mv = new ModelAndView("hotel_details/rooms_hotel_detail");
        if(find_hotel.isPresent()){
            hotel = find_hotel.get();
            List<Room> rooms = hotel.getRooms();
            if(rooms!=null)
            mv.addObject("rooms",rooms);



        }



        return mv;

    }
    public List<Room> filterByDateBooking(List<Room> roomList, String start_date, String end_date){

        return roomList;
    }
}
