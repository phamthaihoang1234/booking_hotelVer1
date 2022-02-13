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

        model = getTopReview(model);
        model = getHotelProperty(model,"Hà Nội","hanoi");// hanoi hotel - all property
        model = getHotelProperty(model,"Đà Nẵng","danang");// danang hotel - all property
        model = getHotelProperty(model,"Hồ Chí Minh","hochiminh");// tphochiminh hotel - all property
        return "Pages/homepage";

    }

    private Model getTopReview(Model model){
        // get reviews for review table webpage
        List<WebReview> reviews1 = webReviewRepo.findTop4Comment((Pageable) PageRequest.of(0,2));
        List<WebReview> reviews2 = webReviewRepo.findTop4Comment((Pageable) PageRequest.of(1,2));
        model.addAttribute("reviews1",reviews1);// row 1 in review table
        model.addAttribute("reviews2",reviews2);// row 2 in review table
        return model;
    }

    private Model getHotelProperty(Model model,String location,String pattern){
        // get hotel type for nav bar
        String type1 = "International hotel";
        String type2 = "Business hotel";
        String type3 = "Hotel for lovers";
        String type4 = "Hotels near the airport";
        String type5 = "Hotel near train station";
        String type6 = "Nightlife hotel";

        model.addAttribute(pattern+"Type1",type1);
        model.addAttribute(pattern+"Type2",type2);
        model.addAttribute(pattern+"Type3",type3);
        model.addAttribute(pattern+"Type4",type4);
        model.addAttribute(pattern+"Type5",type5);
        model.addAttribute(pattern+"Type6",type6);


        model.addAttribute(pattern+"hotelType1",
                hotelRepository.findByHotel_addressContainingAndHotel_property(location,type1));
        model.addAttribute(pattern+"hotelType2",
                hotelRepository.findByHotel_addressContainingAndHotel_property(location,type2));
        model.addAttribute(pattern+"hotelType3",
                hotelRepository.findByHotel_addressContainingAndHotel_property(location,type3));
        model.addAttribute(pattern+"hotelType4",
                hotelRepository.findByHotel_addressContainingAndHotel_property(location,type4));
        model.addAttribute(pattern+"hotelType5",
                hotelRepository.findByHotel_addressContainingAndHotel_property(location,type5));
        model.addAttribute(pattern+"hotelType6",
                hotelRepository.findByHotel_addressContainingAndHotel_property(location,type6));
        return model;
    }


    @GetMapping("/download-apps")
    public String getDownloadApp(){
        return "Pages/download-apps";
    }
    @GetMapping("/user-24h")
    public String getSupport(){
        return "Pages/modal-user/user-24h";
    }
    @GetMapping("/header")
    public String getHeader(){
        return "Pages/Common-pages/part-headers";

    }

    @GetMapping(path = "/searchHotel")
    public String getSearchHotel(){
        return "Pages/search-hotels";
    }
    @GetMapping("/footer")
    public String getFooter(){

        return "Pages/Common-pages/part-footer";

    }



}
