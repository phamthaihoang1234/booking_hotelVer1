package com.example.bookinghotel.controller;


import com.example.bookinghotel.repositories.BookingRepository;
import com.example.bookinghotel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;

//    @GetMapping("/saveBooking")
//    public String saveBooking(@RequestParam("checkin") String checkin,
//                              @RequestParam("checkout") String checkout,
//                              @RequestParam("numberOfRoom") String numberOfRoom,
//                              @RequestParam("info-name") String name,
//                              @RequestParam("info-phone") String phone,
//                              @RequestParam("info-email") String email
//                              ){
//        System.out.println("vao controller");
//        System.out.println(checkin);
//        System.out.println(checkout);
//        System.out.println(numberOfRoom);
//        System.out.println(name);
//        System.out.println(phone);
//        System.out.println(email);
//        return "redirect:/";
//    }


    @GetMapping("/listBooking")
    public String listBooking(Model model){

        model.addAttribute("bookingList",bookingRepository.findAll());

        return "/Pages/Bookings/booking_list";
    }
}
