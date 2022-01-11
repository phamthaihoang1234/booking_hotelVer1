package com.example.bookinghotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomePage {



    @GetMapping("/")
    public String homepage(){

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
