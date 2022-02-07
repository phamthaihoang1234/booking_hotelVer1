package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.bookinghotel.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public void handleLogin(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("savelogin") Boolean savelogin, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (PrintWriter out = response.getWriter()) {


            Optional<UserInfo> user = userService.findByUserName(username);
            if (user.isPresent()) {
                UserInfo userInfo = user.get();
                // xử lý cả 2 trường hợp password bị mã hóa trên cookie và password chưa encrypt trong so sánh psw
                if (userInfo.getPassword().equals(password) || UserInfo.getPasswordEncoder().matches(password, userInfo.getPassword())) {

                    // xử lý lưu cookie
                    if (savelogin == true) {
                        // kiểm tra cookie về tài khoản yoy tồn tại chưa
                        Cookie cookieU = findCookie(request, "yoyuser");
                        Cookie cookieP = findCookie(request, "yoypsw");
                        if (cookieU != null) {
                            cookieU.setValue(userInfo.getUsername());
                            cookieU.setMaxAge(31536000);
                            response.addCookie(cookieU);
                        } else {
                            Cookie newCookie1 = new Cookie("yoyuser", userInfo.getUsername());
                            newCookie1.setMaxAge(31536000); // lưu cookie 1 năm
                            response.addCookie(newCookie1);
                        }

                        if (cookieP != null) {
                            cookieP.setValue(userInfo.getPassword());
                            cookieP.setMaxAge(31536000);
                            response.addCookie(cookieP);
                        } else {
                            Cookie newCookie2 = new Cookie("yoypsw", userInfo.getPassword());
                            newCookie2.setMaxAge(31536000);
                            response.addCookie(newCookie2);
                        }


                    } else {
                        //xử lý cookie đã lưu nhưng k muốn lưu nữa(bằng cách đăng nhập mà k chọn save login
                        Cookie cookieU = findCookie(request, "yoyuser");
                        Cookie cookieP = findCookie(request, "yoypsw");
                        if (cookieU != null) {
                            cookieU.setMaxAge(0);
                            response.addCookie(cookieU);
                        }
                        if (cookieP != null) {
                            cookieP.setMaxAge(0);
                            response.addCookie(cookieP);
                        }
                    }

                    out.write("Success");
                } else {
                    out.write("Wrong password");
                }


            } else {
                out.write("this user doesnt exist");
            }


        }


    }

    // tim cookie theo ten
    private Cookie findCookie(HttpServletRequest request, String cookie_name) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(cookie_name)) {
                return cookies[i];
            }

        }
        return null;
    }
}
