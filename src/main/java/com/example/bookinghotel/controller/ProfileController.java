package com.example.bookinghotel.controller;

import com.example.bookinghotel.repositories.UserRepository;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

//    @PutMapping("/updateProfile/{id}")
//    public ResponseEntity<ResponseObject> updateProfile(@RequestBody UserInfo userInfo, @PathVariable Long id) {
//        UserInfo optionalUserInfo = userRepository.findById(id).map(user -> {
//            user.setEmail(userInfo.getEmail());
//            user.setGender(userInfo.getGender());
//            user.setName(userInfo.getName());
//            user.setPhoneNumber(userInfo.getPhoneNumber());
//            user.setAddress(userInfo.getAddress());
//            user.setEmail(userInfo.getEmail());
//            return userRepository.save(user);
//        }).orElseGet(() -> {
//            userInfo.setId(id);
//            return userRepository.save(userInfo);
//        });
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("Ok", "Update profile successfull", userInfo);
//        );
//    }

    @GetMapping("/GetDetailsProfile")
    public String goToProfile(HttpServletRequest request, Model model){
//        Cookie[] cookies= request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            System.out.println("ClasssssTesssss: "+cookies[i].getValue());
//        }
        // id=5

//        model.addAttribute("hotel",user.findById(id).get());
        return "Pages/profile";

    }


}
