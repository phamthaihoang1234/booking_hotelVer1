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


    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public void handleLogin(@RequestParam("username")String username , @RequestParam("password") String password, @RequestParam("savelogin") Boolean savelogin, HttpServletRequest request, HttpServletResponse response ) throws IOException {

        try (PrintWriter out = response.getWriter()) {


        Optional<UserInfo> user =  userService.findByUserName(username);
        if(user.isPresent()){
            UserInfo userInfo = user.get();
            // xử lý cả 2 trường hợp password bị mã hóa trên cookie và password chưa encrypt trong so sánh psw
            if(userInfo.getPassword().equals(password)||UserInfo.getPasswordEncoder().matches(password,userInfo.getPassword())){
                // kiểm tra cookie về tài khoản yoy tồn tại chưa
                boolean isDuplicated = false;
                if(savelogin==true){
                    Cookie[] cookies = request.getCookies();
                    for(int i = 0 ;i<cookies.length;i++){
                        if(cookies[i].getName().equals("yoyuser")) {
                            cookies[i].setValue(userInfo.getUsername());
                            cookies[i].setMaxAge(31536000);
                            isDuplicated = true;
                            response.addCookie(cookies[i]);
                        }
                        if(cookies[i].getName().equals("yoypsw")){
                            cookies[i].setValue(userInfo.getPassword());
                            cookies[i].setMaxAge(31536000);
                            response.addCookie(cookies[i]);
                        }
                    }
                    if(isDuplicated==false){
                        Cookie newCookie1 = new Cookie("yoyuser",userInfo.getUsername());
                        Cookie newCookie2 = new Cookie("yoypsw",userInfo.getPassword());
                        newCookie1.setMaxAge(31536000); // lưu cookie 1 năm
                        newCookie2.setMaxAge(31536000);
                        response.addCookie(newCookie1);
                        response.addCookie(newCookie2);
                    }
                }
                out.write("Success");
            }else{
                out.write("Wrong password");
            }


        }else{
            out.write("this user doesnt exist");
        }


        }




    }
}
