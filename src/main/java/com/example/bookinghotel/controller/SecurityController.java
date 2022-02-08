package com.example.bookinghotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    @GetMapping("/login")
    public String login(){
        System.out.println("vaologin");
        return "/Pages/booker/login";
    }

    @GetMapping("/logout")
    public String logout(){return "/";}

}
