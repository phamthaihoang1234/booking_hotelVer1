package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Hotel_Property;
import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.entities.WebReview;
import com.example.bookinghotel.repositories.Web_ReviewRepository;
import com.example.bookinghotel.repositories.HotelRepository;
import com.example.bookinghotel.services.RoleService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomePage {
    @Autowired
    private Web_ReviewRepository webReviewRepo;
    @Autowired
    private HotelRepository hotelRepository;
    @GetMapping("/")
    public String homepage(Model model) {
        // get reviews for review table webpage
        List<WebReview> reviews1 = webReviewRepo.findTop4Comment((Pageable) PageRequest.of(0,2));
        List<WebReview> reviews2 = webReviewRepo.findTop4Comment((Pageable) PageRequest.of(1,2));


        model.addAttribute("reviews1",reviews1);// row 1 in review table
        model.addAttribute("reviews2",reviews2);// row 2 in review table
        // get hotel type for nav bar
        String type1 = "Khách sạn quốc tế";
        String type2 = "Hạng Thương Gia";
        String type3 = "Dành Cho Cặp Đôi";
        String type4 = "Gần Sân Bay";
        String type5 = "Gần Ga Xe Lửa";
        String type6 = "Cuộc Sống Về Đêm";

        model.addAttribute("hanoiType1",type1);
        model.addAttribute("hanoiType2",type2);
        model.addAttribute("hanoiType3",type3);
        model.addAttribute("hanoiType4",type4);
        model.addAttribute("hanoiType5",type5);
        model.addAttribute("hanoiType6",type6);


        model.addAttribute("hanoihotelType1",
                hotelRepository.findByHotel_addressContainingAndHotel_property("Hà Nội",type1));
        model.addAttribute("hanoihotelType2",
                hotelRepository.findByHotel_addressContainingAndHotel_property("Hà Nội",type2));
        model.addAttribute("hanoihotelType3",
                hotelRepository.findByHotel_addressContainingAndHotel_property("Hà Nội",type3));
        model.addAttribute("hanoihotelType4",
                hotelRepository.findByHotel_addressContainingAndHotel_property("Hà Nội",type4));
        model.addAttribute("hanoihotelType5",
                hotelRepository.findByHotel_addressContainingAndHotel_property("Hà Nội",type5));
        model.addAttribute("hanoihotelType6",
                hotelRepository.findByHotel_addressContainingAndHotel_property("Hà Nội",type6));
        return "Pages/homepage";

    }



    @GetMapping("/header")
    public String getHeader(){
        return "Pages/Common-pages/part-header";

    }

    @GetMapping("/footer")
    public String getFooter(){

        return "Pages/Common-pages/part-footer";

    }


}
