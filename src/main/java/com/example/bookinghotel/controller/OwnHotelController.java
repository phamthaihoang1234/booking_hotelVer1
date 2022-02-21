package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.*;
import com.example.bookinghotel.repositories.UserRepository;
import com.example.bookinghotel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class OwnHotelController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HomeService homeService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PropertyTypeService typeService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private HomeService roomService;
    @Autowired
    private UserRepository userRepository;





    @GetMapping("/signupOwn")
    public String showFormRegisOfOwner(Model model) {
        model.addAttribute("owner", new UserInfo());
        return "Pages/owner/formOwnRegister";

    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @Value("E:/ListRoom/file/")
    private String fileUpload;

    @PostMapping("/saveOwner")
    public String saveInforOfOwner(@Validated @ModelAttribute("owner") UserInfo user , BindingResult result , RedirectAttributes redirect, Model model) throws Exception {

        Role roleOwner = new Role();
        roleOwner.setName("ROLE_OWNER");
//        Role roleUser = new Role();
//        roleUser.setName("ROLE_USER");
        roleService.save(roleOwner);
//        roleService.save(roleUser);
        Set<Role> roles = new HashSet<>();
        roles.add(roleOwner);
//        roles.add(roleUser);

        user.setRoles(roles);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setToken("user");
        user.setActive(true);
        user.setName(user.getUsername());

        if (result.hasErrors()) {
            if(userService.findByUserName(user.getUsername()) != null){
                model.addAttribute("errolUsername", "Username was existed");
            }
            if(userService.findByEmail(user.getEmail()) != null) {
                model.addAttribute("errolEmail", "Email was existed");
            }
            return "/Pages/owner/formOwnRegister";
        }
        else if(userService.findByUserName(user.getUsername()) != null){
            model.addAttribute("errolUsername", "Username was existed");
            if(userService.findByEmail(user.getEmail()) != null) {
                model.addAttribute("errolEmail", "Email was existed");
            }
            return "/Pages/owner/formOwnRegister";
        }
        else if(userService.findByEmail(user.getEmail()) != null){
            model.addAttribute("errolEmail", "Email was existed");
            return "/Pages/owner/formOwnRegister";
        }
        else {
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            redirect.addFlashAttribute("globalMessage", "Register successfully.");
            userService.save(user);
            return "redirect:/signupOwn";

        }

    }

//    @GetMapping("/manageHotel")
//    public String manageHotel(){
//        return "/Pages/owner/manageHotel";
//    }
//
//
//    @GetMapping("/hotel")
//    public String showform(Model model){
//        model.addAttribute("hotel", new Hotel());
//        return "/greeting";
//    }
//
//    @PostMapping("/createHotel")
//    public String showform(Model model, @ModelAttribute Hotel hotel){
//        hotelService.save(hotel);
//        model.addAttribute("listHotel", hotelService.findAll());
//
//
//
//        return "/result";
//    }
//
    public Long idHotel;


    @GetMapping("/manageRoom/{id}")
    public String homepageRoom(@PathVariable("id") Long id , Model model){
        idHotel = id;
//        model.addAttribute("rooms",roomService.findAllByHotelId(id));
        return "redirect:/roomHomepage";
    }

    @GetMapping("/roomHomepage")
    public String homepageRoom(Model model){
        model.addAttribute("rooms",roomService.findAllByHotelId(idHotel));
        return "Pages/roomManage/all-room";
    }

    @GetMapping("/createRoom")
    public String showFormCreateRoom( Model model){
        model.addAttribute("listProperty", typeService.getAll());
        Room room = new Room();
        room.setHotel(hotelService.findById(idHotel).get());
        model.addAttribute("room", room);
        System.out.println("name of hote : "+hotelService.findById(idHotel).get().getAddressOfHotel());
        return "Pages/roomManage/add-room";
    }

    @PostMapping("/saveRoom")
    public String saveRoom(Model model, @ModelAttribute("room") Room room,@RequestParam("pr") Long id,RedirectAttributes redirect){
        System.out.println("vao save room");
        System.out.println("id property"+id);
        System.out.println("id saveroom hotel la: " +idHotel);
        room.setHotel(hotelService.findById(idHotel).get());
        System.out.println("name of hote : "+room.getHotel().getNameOfHotel());
       System.out.println("Tên cua type la: "+ typeService.getOne(id).get().getName());
        System.out.println("Tên cua type la: "+ room.getStatus());

        room.setPropertyType(typeService.getOne(id).get());
        MultipartFile multipartFile = room.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(room.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        room.setImgSrc3(fileName);

        room.setBookings(null);
        room.setDiscount(discountService.findById(1L).get());
        room.setUser(userService.findByUserName(getPrincipal()));
        homeService.save(room);
        model.addAttribute("rooms",roomService.findAllByHotelId(idHotel));


        return "Pages/roomManage/all-room";
    }


    @GetMapping("/hotelOwnerProfile")
    public String editHotelOwnerProfile(Model model){
        model.addAttribute("hotelOwnerProfile",userService.findByUserName(this.getPrincipal()));
        if(userService.findByUserName(this.getPrincipal()).getRoles().equals("ROLE_USER")){
            return "Pages/modal-user/profile2";
        }
        return "Pages/owner/hotelOwnerProfile";
    }

    @PostMapping("/hotelOwnerProfile/save")
    public String saveEditHotelOwnerProfile(@ModelAttribute UserInfo hotelOwnerProfile) {

        UserInfo oldHotelOwnerProfile = userService.findByUserName(this.getPrincipal());
        oldHotelOwnerProfile.setName(hotelOwnerProfile.getName());
        oldHotelOwnerProfile.setEmail(hotelOwnerProfile.getEmail());
        oldHotelOwnerProfile.setPhoneNumber(hotelOwnerProfile.getPhoneNumber());
        oldHotelOwnerProfile.setAddress(hotelOwnerProfile.getAddress());
        try{
            userService.save(oldHotelOwnerProfile);
        }catch (Exception e){
            e.printStackTrace();
        }
//        redirect.addFlashAttribute("success", "Saved HotelOwner Profile successfully!");
        return "redirect:/hotelOwnerProfile";
    }

    @PostMapping("/saveHotelOwnerNewPasword")
    public String saveHotelOwnerNewPasword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("newPassword2") String newPasswordAgain, HttpServletResponse response, @ModelAttribute UserInfo userInfo, Model model) {
        UserInfo hotelOwner = null;
        try {
            hotelOwner = userService.existsByUsernameAndPassword(this.getPrincipal(), oldPassword);
            if (hotelOwner !=  null) {
                hotelOwner.setPassword(newPassword);
                userService.save(hotelOwner);
                model.addAttribute("statusChangePassWord", "Thay đổi mật khẩu thành công !!!");
            } else
                model.addAttribute("statusChangePassWord", "Mật khẩu cũ không đúng !!!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("statusChangePassWord", "e.printStackTrace()");
        }
        model.addAttribute("hotelOwnerProfile",userService.findByUserName(this.getPrincipal()));
        return "Pages/owner/hotelOwnerProfile";
    }


}
