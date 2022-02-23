package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Booking;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public ModelAndView getRooms_hotel_detail(@RequestParam("start_date")String start_date,
                                              @RequestParam("end_date")String end_date,
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
            if(rooms!=null) {
                rooms = sortByPrice(rooms,"ASC");
                if(start_date.equals("no-date"))
                mv.addObject("rooms", rooms);
                else{
                    rooms= filterByDateBookingAndNumberOfPeople(rooms,start_date,end_date,number_of_people);
                    mv.addObject("rooms", rooms);
                }
            }



        }



        return mv;

    }
    public List<Room> filterByDateBookingAndNumberOfPeople(List<Room> roomList, String start_date, String end_date,int number_of_people){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dtf = dtf.withLocale(Locale.getDefault());
        LocalDate start_dateD = LocalDate.now();
        LocalDate end_dateD = LocalDate.now();


        for (int i = roomList.size() - 1 ; i>=0 ; i--) {
            if (roomList.get(i).getTotalOfBedroom() < number_of_people) {
                roomList.remove(i);
            }
        }
        try {
            start_dateD = LocalDate.parse(start_date);
            end_dateD = LocalDate.parse(end_date);
        for(int i = roomList.size() -1 ; i >=0 ; i--){
            List<Booking> bookings = roomList.get(i).getBookings();
            boolean isValid = true;
            if(!bookings.isEmpty()){
                for (int j = 0 ; j<bookings.size();j++){
                    if(bookings.get(j).getStartDate().isAfter(end_dateD)||bookings.get(j).getEndDate().isBefore(start_dateD)){
                       // thoa man khong lam gi
                    }else{
                        isValid = false;
                        break;
                    }
                }

            }
            if(isValid == false){
                roomList.remove(i);
            }
        }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("loi ngay thang nhap vao");
        }
        return roomList;
    }
    private List<Room> sortByPrice(List<Room> roomList, String order){
        final int orderN ;
        if(order.equals("DESC"))
            orderN = -1;
        else orderN = 1;
        Collections.sort(roomList,new Comparator<Room>(){

            @Override
            public int compare(Room r1, Room r2) {
                if(r1.getPricePerNight()>r2.getPricePerNight())
                return orderN;
                return -orderN;
            }
        });
        return roomList;

    }
}
