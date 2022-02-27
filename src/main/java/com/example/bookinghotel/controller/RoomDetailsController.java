package com.example.bookinghotel.controller;



import com.example.bookinghotel.repositories.RoomRepository;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomDetailsController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;

    @GetMapping("/roomDetails/{id}")
    public String roomDetails(@PathVariable Long id, Model model){

        model.addAttribute("hotelInfo",hotelService.findById(id));
//        model.addAttribute("roomDetail",roomService.findByHotelId(id));

        return "Room/room-details";
    }

}
