package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private UserService userService;

    @GetMapping("/admin_manageHomepage")
    public String adminManageHomepage(){
        return "/Pages/Admin/admin_manageHomepage";
    }

    @GetMapping("/user_list")
    public String user_list(Model model){
        model.addAttribute("userList",userService.findAll())
        return "/Pages/Admin/user_list";
    }


    @GetMapping("/owner_list")
    public String owner_list(){
        return "/Pages/Admin/owner_list";
    }
}
