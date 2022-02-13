package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.Hotel_Property;
import com.example.bookinghotel.repositories.Hotel_PropertyRepositoryD;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.regex.Pattern;

@Controller
public class HotelFilterControllerD {
    // FILE NAY TAO RA CHI VOI MUC DICH LOC KHACH SAN THEO NHIEU CACH
    // AI CODE 1 FILE TUONG TU CO THE COPY VA TAO 1 FILE MOI DE TRANH
    // CONFLICT KHI MERGE CODE
    // phan dung code
    @Autowired
    HotelService hotelService;
    @Autowired
    Hotel_PropertyRepositoryD hotel_propertyRepositoryD;
    @GetMapping("/search-hotels")
    String HotelFiler() {
        Iterable<Hotel_Property> hotel_types = hotel_propertyRepositoryD.findAll();
        return "Pages/hotelFilter";
    }

    @PostMapping("/filterProcess")
    public ModelAndView filterProcess(HttpServletResponse response, @RequestParam("location") String location,@RequestParam(value = "hotel_type[]",required = false) String[] hotel_type) throws UnsupportedEncodingException {
//        , @RequestParam("price") String price
//            , @RequestParam("hotel_standard") String standard, @RequestParam("hotel_property") String[] property
//            , @RequestParam("orderByPrice") String orderByPrice, @RequestParam("orderByStandard") String orderByStandard
//        ,@RequestParam("hotel_type[]", required=false) String[] hotel_type
//        @RequestParam(value = "hotel_type[]",required = false) String[] hotel_type
        String location_processed = "";
        // xu ly cac thong tin dau vao
        if (location != null) {

            System.out.println("location truoc process:" + location);
            // loai bo dau tieng viet
            String nfdNormalizedString = Normalizer.normalize(location, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            location_processed = pattern.matcher(nfdNormalizedString).replaceAll("");
            location_processed = UnExpectedVietnameseChar(location_processed);
            System.out.println("location sau process:" + location_processed);
        } else {
//             check thong tin co duoc gui toan ven khong
            System.out.println("Thieu thong tin");
        }
        if(hotel_type!=null&&hotel_type.length>0){
            for(int i = 0 ; i<hotel_type.length; i++){
                System.out.println(hotel_type[i]);
            }
        }
        ModelAndView mv = new ModelAndView("Pages/Hotel_ListDForFilter");

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            out.write(location_processed );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return mv;

    }

    private String UnExpectedVietnameseChar(String str){

        str = str.replace('Đ','D');
        str = str.replace('đ','d');
        return str;
    }

    @PostMapping("/testModelAndView2")
    public ModelAndView testReturnModelAndView() {
        ModelAndView mv = new ModelAndView("Pages/Hotel_ListDForFilter");

        return mv;
    }
    // phan dung code-end
}
