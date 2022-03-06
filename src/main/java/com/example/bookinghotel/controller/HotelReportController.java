package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Report;
import com.example.bookinghotel.repositories.HotelReportRepository;
import com.example.bookinghotel.services.ReportService;
import com.example.bookinghotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HotelReportController {

    @Autowired
    private HotelReportRepository hotelReportRepository;
    @Autowired
    private ReportService reportService;
    @Autowired
    private RoomService roomService;

    @GetMapping("/report/{id}")
    public String hotelReport(@PathVariable Long id,Model model){
        model.addAttribute("report",new Report());
        model.addAttribute("roomInfo",roomService.findById(id).get());
        return "Room/hotel_report";
    }

//    @GetMapping("/report")
//    public String hotelReport(){
//        return "/Room/hotel_report.html";
//    }

//    @PostMapping("/saveReport{id}")
//    public String saveHotelReport(){
//
//        return "hom";
//    }

}
