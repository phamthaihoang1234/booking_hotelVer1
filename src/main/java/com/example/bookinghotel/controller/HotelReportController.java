package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Report;
import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.repositories.HotelReportRepository;
import com.example.bookinghotel.services.ReportService;
import com.example.bookinghotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HotelReportController {

    @Autowired
    private HotelReportRepository hotelReportRepository;
    @Autowired
    private ReportService reportService;
    @Autowired
    private RoomService roomService;


    public Long room_id;

    @GetMapping("/report/{id}")
    public String hotelReport(@PathVariable Long id,Model model){
        model.addAttribute("report",new Report());
        model.addAttribute("roomInfo",roomService.findById(id).get());
        room_id = id;
        return "Room/hotel_report";
    }

//    @GetMapping("/report")
//    public String hotelReport(){
//        return "/Room/hotel_report.html";
//    }

    @PostMapping("/saveReport")
    public String saveHotelReport(@ModelAttribute("report") Report report, RedirectAttributes redirect, Model model){

        report.setEmail(report.getEmail());
        report.setMessage(report.getMessage());
        report.setName(report.getName());
        report.setHotel(roomService.findById(room_id).get().getHotel());
        report.setRoom(roomService.findById(room_id).get());

        reportService.save(report);

        model.addAttribute("roomInfo", roomService.findById(room_id).get());
        return "Room/hotel_report";
    }

}
