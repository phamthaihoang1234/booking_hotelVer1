package com.example.bookinghotel.services;


import com.example.bookinghotel.entities.Booking;
import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.Hotel_Property;
import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.repositories.HotelFilterRepository;
import com.example.bookinghotel.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public Hotel save(Hotel province) {
        return hotelRepository.save(province);
    }

    @Override
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }
    //phan dung code

    @Autowired
    HotelFilterRepository hotelFilterRepository;
    public ArrayList<Integer> findAllHotel_StandardByLocation(String location){
        Iterable<Hotel> hotels = hotelFilterRepository.listHotelByLocationAndNoPageable(location);
        ArrayList<Integer> standards = new ArrayList<>();
        hotels.forEach(hotel -> {
            if(!standards.contains(hotel.getHotel_standard())){
                standards.add(hotel.getHotel_standard());
            }
        });
        // sort vi tri cac standard => tu cao xuong thap
        Collections.sort(standards);
        Collections.reverse(standards);

        return standards;
    }

    @Override
    public ArrayList<Integer> findAllHotel_StandardByAllInputType(String location, String start_date, String end_date, int number_of_people) {
        ArrayList<Hotel> hotels = hotelFilterRepository.getHotelsByNameAndNumberOfPeopleBookedList(location,number_of_people);
        hotels = BookingDateFilter(hotels,start_date,end_date);
        hotels.addAll(hotelFilterRepository.getHotelsByNameAndNumberOfPeopleNoBookedList(location,number_of_people));
        ArrayList<Integer> standards = new ArrayList<>();
        hotels.forEach(hotel -> {
            if(!standards.contains(hotel.getHotel_standard())){
                standards.add(hotel.getHotel_standard());
            }
        });
        // sort vi tri cac standard => tu cao xuong thap
        Collections.sort(standards);
        Collections.reverse(standards);

        return standards;
    }


    @Override
    public ArrayList<Hotel_Property> findAllHotel_PropertyByLocation(String location) {
        ArrayList<Hotel> hotels = hotelFilterRepository.listHotelByLocationAndNoPageable(location);
        ArrayList<Hotel_Property> hotel_Property = new ArrayList<>();
        hotels.forEach(hotel -> {
            if(!hotel_Property.contains(hotel.getHotel_standard())){
                hotel_Property.add(hotel.getHotel_property());
            }
        });

        return hotel_Property;

    }

    @Override
    public ArrayList<Hotel_Property> findAllHotel_PropertyByAllInputType(String location, String start_date, String end_date, int number_of_people) {
        ArrayList<Hotel> hotels = hotelFilterRepository.getHotelsByNameAndNumberOfPeopleBookedList(location,number_of_people);
        hotels = BookingDateFilter(hotels,start_date,end_date);
        hotels.addAll(hotelFilterRepository.getHotelsByNameAndNumberOfPeopleNoBookedList(location,number_of_people));
        ArrayList<Hotel_Property> hotel_Property = new ArrayList<>();
        hotels.forEach(hotel -> {
            if(!hotel_Property.contains(hotel.getHotel_property())){
                hotel_Property.add(hotel.getHotel_property());
            }
        });



        return hotel_Property;
    }

    private ArrayList<Hotel> BookingDateFilter(ArrayList<Hotel> hotels,String start_date,String end_date){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        dtf = dtf.withLocale(Locale.getDefault());
        LocalDate  start_dateD;
        LocalDate  end_dateD;
        try {
            start_dateD = LocalDate.parse(start_date);
            end_dateD = LocalDate.parse(end_date);
//            String newDateString = df.format(end_date);
//            System.out.println(newDateString);
            for(int i = hotels.size() - 1  ; i >=0 ;i--){
                boolean findEmptyRoom = false;// coi nhu chua tim duoc phong trong khi chua quet
                ArrayList<Room> rooms = (ArrayList<Room>) hotels.get(i).getRooms();

                for(int j = 0 ; j<rooms.size();j++){
                    boolean isValid = true;// coi nhu phong dang trong , neu quet booking thay trung ngay se set ve false

                    ArrayList<Booking> bookings = (ArrayList<Booking>) rooms.get(j).getBookings();
                    for (int k = 0 ; k < bookings.size(); k++){
                        if(bookings.get(k).getStartDate().isAfter(end_dateD)||bookings.get(k).getEndDate().isBefore(start_dateD)){
                            // booking hien tai dap ung yeu cau khong thay doi gia tri valid
                        }else{
                            isValid = false;//khong dap ung yeu cau
                        }
                    }
                    if(isValid==true){
                        findEmptyRoom = true;// tim duoc phong trong trong khac san.
                        break;
                    }
                }
                if(findEmptyRoom==false){
                    hotels.remove(i);
                }
            }
        } catch (Exception e) {
            for(int i = 0 ; i< 10 ; i++)
            System.out.println("NGAY NHAP VAO BI LOI");
        }
        return hotels;
    }

    // phan dung code -end
}
