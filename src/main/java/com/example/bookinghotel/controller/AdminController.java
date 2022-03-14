package com.example.bookinghotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin_manageHomepage")
    public String adminManageHomepage(){
        return "/Pages/Admin/admin_manageHomepage";
    }

    @GetMapping("/user_list")
    public String user_list(){
        return "/Pages/Admin/user_list";
    }


    @GetMapping("/owner_list")
    public String owner_list(){
        return "/Pages/Admin/owner_list";
    }
}
