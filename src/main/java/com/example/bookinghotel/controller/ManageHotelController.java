package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ManageHotelController {

    @Autowired
    private HotelService hotelService;


//    @GetMapping("/manageHotels")
//    public String manageHotel(Model model){
//        model.addAttribute("hotels", hotelService.findAll());
//        return "Pages/hotelManage/createHotel";
//    }

    @GetMapping("/manageHotels")
    public String manageHotel(Model model){
        //model.addAttribute("hotels", hotelService.findAll());
        return "Pages/index";
    }

    @GetMapping("/homepageHotel")
    public String homepageHotel(Model model){
        model.addAttribute("hotels", hotelService.findAll());
        return "Pages/hotelManage/all-hotel";
    }


    @GetMapping("/showFormCreHotel")
    public String showFormCreateHotel(Model model){
        model.addAttribute("hotel", new Hotel());
       return "Pages/hotelManage/form-add-hotel";
    }


    @PostMapping("/createHotel")
    public String showform(Model model, @ModelAttribute Hotel hotel){
        System.out.println(hotel.getId());
        System.out.println(hotel.getNameOfHotel());
        System.out.println(hotel.getAddressOfHotel());
        hotelService.save(hotel);
//        model.addAttribute("listHotel", hotelService.findAll());



        return "redirect:/homepageHotel";
    }



    @GetMapping("/findOne/{id}")
    public String findHotelById(@PathVariable("id") long id, Model model){

        model.addAttribute("hotel",hotelService.findById(id).get());
        return "Pages/hotelManage/form-edit-hotel";
    }

    @PostMapping("/saveEdit")
    public String updateHotel(@ModelAttribute Hotel hotel){
        Optional<Hotel> oldHotel = hotelService.findById(hotel.getId());
        System.out.println("vao saveedit: " + hotel.getAddressOfHotel());
        System.out.println(oldHotel.get().getAddressOfHotel());
        oldHotel.get().setAddressOfHotel(hotel.getAddressOfHotel());
        oldHotel.get().setNameOfHotel(hotel.getNameOfHotel());
        oldHotel.get().setStatus(hotel.getStatus());
        oldHotel.get().setId(hotel.getId());
        hotelService.save(hotel);

        return "redirect:/homepageHotel";
    }

    @GetMapping("/delete")
    public String deleteHotel(long id){
        hotelService.delete(id);
        return "redirect:/homepageHotel";
    }


}
