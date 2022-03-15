package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Booking;
import com.example.bookinghotel.repositories.BookingRepository;
import com.example.bookinghotel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public String listBooking(Model model,
                              @RequestParam(name = "start_date",required = false) String start_date,
                              @RequestParam(name = "end_date",required = false) String end_date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("ngay bat dau: "+ start_date);
        System.out.println("ngay ket thuc: "+ end_date);
        Iterable<Booking> bookingList = null;
        if(StringUtils.hasText(start_date) && StringUtils.hasText(end_date)){
            bookingList = bookingRepository.findByStartDateAndEndDate(LocalDate.parse(start_date,formatter),LocalDate.parse(end_date,formatter));
        }else if(StringUtils.hasText(start_date)){
            bookingList = bookingRepository.findByStartDate(LocalDate.parse(start_date,formatter));
        }else if(StringUtils.hasText(end_date)){
            bookingList = bookingRepository.findByEndDate(LocalDate.parse(end_date,formatter));
        }else{
            bookingList = bookingRepository.findAll();
        }
        model.addAttribute("bookingList",bookingList);

        return "/Pages/Bookings/booking_list";
    }


    @GetMapping("/deleteBooking")
    public String deleteBooking(Long id){
        bookingService.delete(id);
        return "redirect:/listBooking";
    }


    @GetMapping("/addBooking")
    public String addBooking(){
        return "/Pages/Bookings/add_booking_list";
    }


    @GetMapping("/editBooking")
    public String editBooking(){
        return "/Pages/Bookings/edit_booking_list";
    }
}
