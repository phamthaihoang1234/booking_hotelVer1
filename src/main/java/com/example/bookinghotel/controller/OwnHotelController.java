package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnHotelController {

    @GetMapping("/signupOwn")
    public String showFormRegisOfOwner(Model model) {
        model.addAttribute("owner", new UserInfo());
        return "Pages/owner/formOwnRegister";

    }

}
