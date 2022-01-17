package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Role;
import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.services.RoleService;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String showForm(Model model){
        System.out.println("vao sign up");
        model.addAttribute("user", new UserInfo());
//        return "/greeting";
       return "/Pages/modal-user/user-signup";
    }

    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute("user") UserInfo user , BindingResult result , RedirectAttributes redirect) throws Exception {




        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.save(roleUser);
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);

        user.setRoles(roles);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setToken("user");
        user.setActive(true);
        user.setName(user.getUsername());

        System.out.println(user.getUsername());
        System.out.println(user.getActive());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getRoles());
        System.out.println(user.getPhoneNumber());
        System.out.println(user.getGender());


        if (result.hasErrors()) {
            System.out.println("vao loi nhe");
            return "/Pages/modal-user/user-signup";
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            redirect.addFlashAttribute("globalMessage", "Register successfully.");
                userService.save(user);
                return "redirect:/signup";
//                  return "redirect:/";
            }


    }

//    @GetMapping("/register2")
//    public String registerUser()  {
//
//        return "/Pages/Common-pages/part-header";
//    }




}
