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
public class TermsOfUseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private UserInfo userInfo;
    private UserInfo oldUserInfo;

    @GetMapping("/TermOfUser")
    public String goToTermOfUser(HttpServletRequest request, Model model) {
        // id=5
        model.addAttribute("TermOfUser", userService.findByUserName(this.getPrincipal()));
        return "Pages/modal-user/TermsOfUse";

    }

    @PostMapping("/saveTermOfUser")
    public String updateTermOfUser(@ModelAttribute UserInfo userInfo) {
        UserInfo oldUserInfo = userService.findByUserName(this.getPrincipal());
        oldUserInfo.setName(userInfo.getName());
        oldUserInfo.setEmail(userInfo.getEmail());
        oldUserInfo.setPhoneNumber(userInfo.getPhoneNumber());
        oldUserInfo.setAddress(userInfo.getAddress());
        oldUserInfo.setGender(userInfo.getGender());
        System.out.println("Class: saveTermOfUser | Method: saveTermOfUser |  " + oldUserInfo.getName());
        try {
            userService.updateInfor(oldUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Pages/modal-user/profile2";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
