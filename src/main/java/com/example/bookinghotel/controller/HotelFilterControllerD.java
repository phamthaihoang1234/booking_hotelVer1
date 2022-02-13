package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.Normalizer;

@Controller
public class HotelFilterControllerD {
    // FILE NAY TAO RA CHI VOI MUC DICH LOC KHACH SAN THEO NHIEU CACH
    // AI CODE 1 FILE TUONG TU CO THE COPY VA TAO 1 FILE MOI DE TRANH
    // CONFLICT KHI MERGE CODE
    // phan dung code
    @GetMapping("/search-hotels")
    String HotelFiler(){
        return "Pages/search-hotelsD";
    }

    @PostMapping("/testModelAndView")
    public void filterProcess(){
//        String location_processed = "";
//        // xu ly cac thong tin dau vao
//        if(location!=null){
//            location = Normalizer.normalize(location, Normalizer.Form.NFD);
//            location = location.replaceAll("[^\\p{ASCII}]", "");
//            location_processed = location.replaceAll("\\p{M}", "");
//            System.out.println("location sau process:"+location_processed);
//        }else{
            // check thong tin co duoc gui toan ven khong

//        }
        System.out.println("Thieu thong tin");


    }
    @PostMapping("/testModelAndView2")
    public ModelAndView testReturnModelAndView(){
        ModelAndView mv = new ModelAndView("Pages/Hotel_ListDForFilter");

        return mv;
    }
    // phan dung code-end
}
