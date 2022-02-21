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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
public class ChangePasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private UserInfo userInfo;
    private UserInfo oldUserInfo;

    @PostMapping("/saveChangePasword")
    public String saveChangePasword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("newPassword2") String newPassword2, Model model, @ModelAttribute UserInfo userInfo) {
        UserInfo user = null;
        try{
            user = userService.existsByUsernameAndPassword(this.getPrincipal(), oldPassword);
            if (user != null) {
                user.setPassword(newPassword);
                userService.save(user);
                model.addAttribute("statusChangePassWord", "Thay đổi mật khẩu thành công !!!");
            }else{
                model.addAttribute("statusChangePassWord", "Mật khẩu cũ không đúng !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("statusChangePassWord", "e.printStackTrace()");
        }
        model.addAttribute("userInfo", userService.findByUserName(this.getPrincipal()));
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
