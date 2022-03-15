package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    @Autowired
    private UserService userService;


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


    @GetMapping("/admin_manageHomepage")
    public String adminManageHomepage(Model model){
        model.addAttribute("admin",userService.findByUserName(this.getPrincipal()));
        return "/Pages/Admin/admin_manageHomepage";
    }


    @GetMapping("/user_list")
    public String user_list(Model model){
        model.addAttribute("userList",userService.findAll());
        return "/Pages/Admin/user_list";
    }


    @GetMapping("/owner_list")
    public String owner_list(){
        return "/Pages/Admin/owner_list";
    }

}
