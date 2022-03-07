package com.example.bookinghotel.controller;




import com.example.bookinghotel.repositories.RoomImageRepository;
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
    private RoomService roomService;

    @Autowired
    private RoomImageRepository roomImageRepository;


    @GetMapping("/roomDetails{id}")
    public String roomDetails(@PathVariable Long id, Model model){

        //lay ra thong tin room
        model.addAttribute("roomDetail",roomService.findById(id).get());
        //lay ra image theo id room
        model.addAttribute("roomImage",roomImageRepository.listRoomImage(id));

        //lay ra link iframe gg map
        model.addAttribute("iframe", roomService.findById(id).get().getHotel().getIframe());



        return "Room/room-details";
    }

}
