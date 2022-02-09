package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.RoleService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomePage {
    @GetMapping("/")
    public String homepage() {


        return "Pages/homepage";

    }



    @GetMapping("/header")
    public String getHeader(){

        return "Pages/Common-pages/part-headers";

    }

    @GetMapping("/footer")
    public String getFooter(){

        return "Pages/Common-pages/part-footer";

    }


}
