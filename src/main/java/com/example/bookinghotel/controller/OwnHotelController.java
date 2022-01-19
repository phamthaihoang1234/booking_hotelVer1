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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
public class OwnHotelController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signupOwn")
    public String showFormRegisOfOwner(Model model) {
        model.addAttribute("owner", new UserInfo());
        return "Pages/owner/formOwnRegister";

    }

    @PostMapping("/saveOwner")
    public String saveInforOfOwner(@Validated @ModelAttribute("owner") UserInfo user , BindingResult result , RedirectAttributes redirect) throws Exception {

        Role roleOwner = new Role();
        roleOwner.setName("ROLE_OWNER");
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.save(roleOwner);
        roleService.save(roleUser);
        Set<Role> roles = new HashSet<>();
        roles.add(roleOwner);
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
            return "/Pages/owner/formOwnRegister";
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            redirect.addFlashAttribute("globalMessage", "Register successfully.");
            userService.save(user);
            return "redirect:/signupOwn";

        }

    }

    @GetMapping("/manageHotel")
    public String manageHotel(){
        return "/Pages/owner/manageHotel";
    }



}
