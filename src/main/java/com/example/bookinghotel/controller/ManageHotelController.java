package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ManageHotelController {

    @Autowired
    private HotelService hotelService;

//
//    @GetMapping("/manageHotels")
//    public String manageHotel(Model model){
//        model.addAttribute("hotels", hotelService.findAll());
//        return "Pages/hotelManage/homepageHotel";
//    }

    @GetMapping("/manageHotels")
    public String manageHotel(Model model){
        model.addAttribute("hotels", hotelService.findAll());
        return "Pages/hotelManage/createHotel";
    }

    @PostMapping("/createHotel")
    public String showform(Model model, @ModelAttribute Hotel hotel){
        System.out.println(hotel.getId());
        System.out.println(hotel.getNameOfHotel());
        System.out.println(hotel.getAddressOfHotel());
        hotelService.save(hotel);
//        model.addAttribute("listHotel", hotelService.findAll());



        return "redirect:/manageHotels";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Hotel findHotelById(long id){
        System.out.println("id la :"+id);
        return hotelService.findById(id).get();
    }

    @GetMapping("/delete")
    public String deleteById(long id){
        hotelService.delete(id);
        return "redirect:/manageHotels";
    }



}
