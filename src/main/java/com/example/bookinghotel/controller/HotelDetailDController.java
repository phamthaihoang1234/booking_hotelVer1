package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.*;
import com.example.bookinghotel.repositories.PropertyTypeRepository;
import com.example.bookinghotel.services.HomeService;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class HotelDetailDController {
    @Autowired
    HotelService hotelService;
    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    @Autowired
    HomeService homeService;





    Long idHotel;
    @GetMapping("/hotel_detail{id}")
    public String hotelDetail(@PathVariable("id") Long id, Model model) {
        Optional<Hotel> hotel = hotelService.findById(id);
        idHotel = id;
        System.out.println("idHotel là:"+ idHotel);
        if (hotel.isPresent()) {
            model.addAttribute("background_image", hotel.get().getImages().get(0).getImage());
            model.addAttribute("hotel", hotel.get());
            return "hotel_details/index";
        }
        return "redirect:/";
    }

    @GetMapping("/saveBooking")
    public String saveBooking(@RequestParam("checkin") String checkin,
                              @RequestParam("checkout") String checkout,
                              @RequestParam("numberOfRoom") String numberOfRoom,
                              @RequestParam("info-name") String name,
                              @RequestParam("info-phone") String phone,
                              @RequestParam("info-email") String email
    ){
        System.out.println("vao controller");
        System.out.println(checkin);
        System.out.println(checkout);
        System.out.println(numberOfRoom);
        System.out.println(name);
        System.out.println(phone);
        System.out.println(email);
        return "redirect:/";
    }

    @PostMapping("/getRoomsHotelDetail")
    public ModelAndView getRooms_hotel_detail(@RequestParam("start_date") String start_date,
                                              @RequestParam("end_date") String end_date,
                                              @RequestParam("number_of_people") int number_of_people,
                                              @RequestParam("hotel_name") String hotel_name
    ) {
        Optional<Hotel> find_hotel = null;
        if (hotel_name != "")
            find_hotel = hotelService.findHotelByName(hotel_name);
        Hotel hotel;
        ModelAndView mv = new ModelAndView("hotel_details/rooms_hotel_detail2");
        if (find_hotel.isPresent()) {
            hotel = find_hotel.get();
            List<Room> rooms = hotel.getRooms();
            if(rooms!=null){
                List<RoomGroup> roomGroups = getAllFilteredRoomGroup(hotel.getId()
                        ,start_date,end_date,number_of_people);
                mv.addObject("roomGroups", roomGroups);

            }


        }


        return mv;

    }

    @PostMapping("/checkAvailableHotelByDate")
    public void checkAvailableHotelByDate(@RequestParam("room_id")String room_id,
                                    @RequestParam("start_date")String start_date,
                                    @RequestParam("end_date")String end_date,
                                          HttpServletResponse response){
        System.out.println("id cua room la" + room_id);
        System.out.println("start date " + start_date);
        System.out.println("end date" + end_date);
        String ans = "";
        Room room = homeService.findById(Long.valueOf(room_id)).get();
       RoomGroup roomGroup = getFilteredRoomGroup(room.getHotel().getId(),start_date,end_date,1,room.getPropertyType());
       if(roomGroup != null){
           ans = String.valueOf(roomGroup.getEmpty_rooms().size());
       }

        try (PrintWriter out = response.getWriter()) {
            out.write(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // co the return null neu hotel_id sai
    public List<RoomGroup> getAllFilteredRoomGroup(Long hotel_id, String start_date, String end_date, int number_of_people) {
        List<RoomGroup> roomGroups = new ArrayList<>();
        RoomGroup roomGroup = null;
        Iterable<PropertyType> types = propertyTypeRepository.findAll();
        for (PropertyType propertyType : types) {
            roomGroup = getFilteredRoomGroup(hotel_id, start_date, end_date, number_of_people, propertyType);
            if (roomGroup != null) {
                roomGroups.add(roomGroup);
            }


        }
        if (roomGroups.isEmpty()) return null;
        return roomGroups;
    }

    // co the return null neu khach san khong ton tai hoac khong co phong thich hop
    public RoomGroup getFilteredRoomGroup(Long hotel_id, String start_date, String end_date, int number_of_people, PropertyType type) {
        RoomGroup rg = null;

        Optional<Hotel> hotel = hotelService.findById(hotel_id);

        if (hotel.isPresent()) {
            List<Room> rooms = hotel.get().getRooms();
            if (rooms!=null&&!rooms.isEmpty()) {
                rooms = filterByDateBookingAndNumberOfPeople(rooms, start_date, end_date, number_of_people);
                rooms = filterByRoomType(rooms, type);
                if (rooms!=null&&!rooms.isEmpty()) {
                    rg = new RoomGroup(rooms, rooms.get(0));
                }
            }
        }

        return rg;

    }

    public List<Room> filterByRoomType(List<Room> roomList, PropertyType type) {
        List<Room> filteredRooms = new ArrayList<>();
        for (Room room : roomList) {

            if (room.getPropertyType().getId() == type.getId()) {
                filteredRooms.add(room);
            }
        }
        if (filteredRooms.isEmpty()) return null;
        return filteredRooms;
    }

    public List<Room> filterByDateBookingAndNumberOfPeople(List<Room> roomList, String start_date, String end_date, int number_of_people) {
        if (!start_date.equals("no-date")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dtf = dtf.withLocale(Locale.getDefault());
            LocalDate start_dateD = LocalDate.now();
            LocalDate end_dateD = LocalDate.now();


            for (int i = roomList.size() - 1; i >= 0; i--) {
                if (roomList.get(i).getTotalOfBedroom() < number_of_people) {
                    roomList.remove(i);
                }
            }
            try {
                start_dateD = LocalDate.parse(start_date);
                end_dateD = LocalDate.parse(end_date);
                for (int i = roomList.size() - 1; i >= 0; i--) {
                    List<Booking> bookings = roomList.get(i).getBookings();
                    boolean isValid = true;
                    if (bookings!=null&&!bookings.isEmpty()) {
                        for (int j = 0; j < bookings.size(); j++) {
                            if (bookings.get(j).getStartDate().isAfter(end_dateD) || bookings.get(j).getEndDate().isBefore(start_dateD)) {
                                // thoa man khong lam gi
                            } else {
                                isValid = false;
                                break;
                            }
                        }

                    }
                    if (isValid == false) {
                        roomList.remove(i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return roomList;
    }

    private List<Room> sortByPrice(List<Room> roomList, String order) {
        final int orderN;
        if (order.equals("DESC"))
            orderN = -1;
        else orderN = 1;
        Collections.sort(roomList, new Comparator<Room>() {

            @Override
            public int compare(Room r1, Room r2) {
                if (r1.getPricePerNight() > r2.getPricePerNight())
                    return orderN;
                return -orderN;
            }
        });
        return roomList;

    }
}
