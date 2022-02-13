package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.repositories.UserRepository;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private UserInfo userInfo;
    private UserInfo oldUserInfo;

    @GetMapping("/GetDetailsProfile")
    public String goToProfile(HttpServletRequest request, Model model){
        // id=5
        model.addAttribute("userInfo",userService.findByUserName(this.getPrincipal()));
        return "Pages/profile2";

    }

    @PostMapping("/saveEditProfile")
    public String updateProfile(@ModelAttribute UserInfo userInfo){
        UserInfo oldUserInfo = userService.findByUserName(this.getPrincipal());

        oldUserInfo.setName(userInfo.getName());
        oldUserInfo.setEmail(userInfo.getEmail());
        oldUserInfo.setPhoneNumber(userInfo.getPhoneNumber());
        oldUserInfo.setAddress(userInfo.getAddress());
        System.out.println("Class: ProfileControler | Method: updateProfile |  " + oldUserInfo.getName());
        try {
            userService.save(oldUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/homepageHotel";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
