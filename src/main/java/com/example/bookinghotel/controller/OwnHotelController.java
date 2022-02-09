package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.*;
import com.example.bookinghotel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
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


    @GetMapping("/signupOwn")
    public String showFormRegisOfOwner(Model model) {
        model.addAttribute("owner", new UserInfo());
        return "Pages/owner/formOwnRegister";

    }

    @Value("E:/ListRoom/file/")
    private String fileUpload;

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
//    public Long idHotel ;
//
//    @GetMapping("/createRoom/{id}")
//    public String showFormCreateRoom(@PathVariable("id") Long id , Model model){
//        model.addAttribute("listProperty", typeService.getAll());
//        Room room = new Room();
//        room.setHotel(hotelService.findById(id).get());
//        idHotel = id;
//        model.addAttribute("room", room);
//        System.out.println("name of hote : "+hotelService.findById(id).get().getAddressOfHotel());
//        return "Room/create";
//    }
//
//    @PostMapping("/saveRoom")
//    public String saveRoom(Model model, @ModelAttribute("room") Room room, @RequestParam("p") Long id,RedirectAttributes redirect){
//        System.out.println(idHotel);
//        room.setHotel(hotelService.findById(idHotel).get());
//        System.out.println("name of hote : "+room.getHotel().getNameOfHotel());
//        System.out.println("Tên cua type la: "+typeService.getOne(id).get().getName());
//
//        room.setPropertyType(typeService.getOne(id).get());
//        MultipartFile multipartFile = room.getImage();
//        String fileName = multipartFile.getOriginalFilename();
//
//        try {
//            FileCopyUtils.copy(room.getImage().getBytes(), new File(fileUpload + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        room.setImgSrc3(fileName);
//        room.setPricePerNight(2.5);
//        room.setTotalOfBathroom(11);
//        room.setTotalOfBedroom(11);
//        room.setBookings(null);
//        room.setDiscount(discountService.findById(1L).get());
//        room.setUser(userService.findById(1L).get());
//        homeService.save(room);
//        model.addAttribute("rooms",roomService.findAll());
//
//
//        return "Room/ListRoom";
//    }
//





}
