package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.UserInfo;
import com.example.bookinghotel.repositories.UserRepository;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


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
    public String user_list(Model model,
                            @RequestParam(name = "name", required = false) String name,
                            @RequestParam(name = "phone",required = false) String phone,
                            @RequestParam(name ="email", required = false) String email){

        Iterable<UserInfo> userList = null;
        if(StringUtils.hasText(name)){
            userList = userRepository.findByNameContaining(name);
        }else{
            userList = userRepository.findAll();
        }
        model.addAttribute("userList",userList);
        return "/Pages/Admin/user_list";
    }


    @GetMapping("/owner_list")
    public String owner_list(){
        return "/Pages/Admin/owner_list";
    }

}
