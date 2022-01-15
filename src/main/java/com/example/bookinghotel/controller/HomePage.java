package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Hotel_Property;
import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.entities.WebReview;
import com.example.bookinghotel.repositories.Web_ReviewRepository;
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

    @GetMapping("/")
    public String homepage(Model model) {
        // get reviews for review table webpage
        List<WebReview> reviews1 = webReviewRepo.findTop4Comment((Pageable) PageRequest.of(0,2));
        List<WebReview> reviews2 = webReviewRepo.findTop4Comment((Pageable) PageRequest.of(1,2));

        model.addAttribute("reviews1",reviews1);// row 1 in review table
        model.addAttribute("reviews2",reviews2);// row 2 in review table

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
