package com.example.bookinghotel.controller;

import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.entities.Hotel_Property;
import com.example.bookinghotel.repositories.HotelFilterRepository;
import com.example.bookinghotel.repositories.HotelRepository;
import com.example.bookinghotel.repositories.Hotel_PropertyRepositoryD;
import com.example.bookinghotel.services.HotelFilterService;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Optional;
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
    @Autowired
    HotelFilterRepository hotelFilterRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelFilterService hotelFilterService;

    @RequestMapping(value={"search-hotels","search-hotels/{location}"},method = { RequestMethod.GET, RequestMethod.POST })

    String HotelFiler(Model model,
                      @RequestParam(value = "location",required = false) String location,
                      @RequestParam(value = "start_date",required = false) String start_date,
                      @RequestParam(value = "end_date",required = false) String end_date,
                      @RequestParam(value = "number_of_people",required = false) Integer number_of_people,
                      @PathVariable Optional<String> location2) {

        String unique_location = "";
        ArrayList<Hotel_Property> hotel_properties ;
        ArrayList<Integer> hotel_standards ;

        if(location2.isPresent()){
            hotel_properties = hotel_propertyRepositoryD.findAll();
            hotel_standards = hotelService.findAllHotel_StandardByLocation(location2.get());
        }
        else {
            hotel_properties = hotel_propertyRepositoryD.findAll();
            hotel_standards = hotelService.findAllHotel_StandardByLocation(unique_location);
        }



        // so sao cua 1 standard - stars_per_standard
        // 1 obj = 1 sao
        ArrayList<Object> stars_per_standard = new ArrayList<>();
        ArrayList<ArrayList<Object>> standards = new ArrayList<>();

        // them cac standard vao model
        for(int i = 0 ; i < hotel_standards.size();i++){
            for(int j = 0 ; j< hotel_standards.get(i) ; j++)
                stars_per_standard.add(new Object());
            standards.add(stars_per_standard);
            stars_per_standard = new ArrayList<>();
        }

        // chia cac  hotel property vao tung attr theo form cua hotelFilter.html
        ArrayList<Hotel_Property> hotel_properties1 = new ArrayList<>();
        ArrayList<Hotel_Property> hotel_properties2 = new ArrayList<>();
        for(int i = 0 ;i<4; i++){
            hotel_properties1.add(hotel_properties.get(i));

        }
        for(int i = 4 ; i< hotel_properties.size();i++){
            hotel_properties2.add(hotel_properties.get(i));
        }

        model.addAttribute("hotel_types1",hotel_properties1);
        model.addAttribute("hotel_types2",hotel_properties2);
        model.addAttribute("standards",standards);
        return "Pages/hotelFilter";
    }



    @PostMapping("/filterProcess")
    public ModelAndView filterProcess(@RequestParam("location") String location,@RequestParam(value = "hotel_type[]",required = false) String[] hotel_type
            ,@RequestParam(value = "hotel_standard",required=false) Integer standard,
            @RequestParam(value = "page_number",required=false) int page) throws UnsupportedEncodingException {
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
        int page_number = 0;// for check page number use want
        if(location==null) location = "";
        if(page != 0) page_number = page;
        ArrayList<Hotel> hotels = new ArrayList<>();
        if(hotel_type==null){
            hotel_type = new String[1];
            hotel_type[0] = "";
        }
        if(standard==null){
            standard = 0 ;
        }
        int number_of_hotel = 0;
        if((hotel_type[0].equals("all")||hotel_type[0].isEmpty())&&standard==0){
            hotels = hotelFilterService.listHotelByLocation(location, PageRequest.of(page_number,6));
            number_of_hotel = hotelFilterService.listHotelByLocationAndNoPageable(location).size();
        }
        else if((hotel_type[0].isEmpty()||hotel_type[0].equals("all"))&&standard!=0){
            hotels = hotelFilterService.listHotelByStandard(location,standard,PageRequest.of(page_number,6));
            number_of_hotel = hotelFilterService.listHotelByStandardAndNoPageable(location,standard).size();
        }else if(!hotel_type[0].equals("all")&&standard==0){
            hotels = hotelFilterService.listHotelByPropery(location,hotel_type[0],PageRequest.of(page_number,6));
            number_of_hotel = hotelFilterService.listHotelByProperyAndNoPageable(location,hotel_type[0]).size();
        }
        else{
            hotels = hotelFilterService.listHotelByStandardAndPropertyAndOrderByStandard(standard,hotel_type[0],
                    location,null,PageRequest.of(page_number,6));
            number_of_hotel = hotelFilterService.listHotelByStandardAndPropertyAndOrderByStandardAndNoPageable(standard,hotel_type[0],
                    location,null).size();
        }

        int number_of_page = (int)number_of_hotel/6;
        if(number_of_hotel%6!=0){
            number_of_page+=1;
        }
//        System.out.println(number_of_hotel);


        ArrayList<ArrayList<Hotel>> list_of_row = new ArrayList<>();
        ArrayList<Hotel> hotel_in_row = new ArrayList<>();
        // xu ly hotel chia theo template
        for(int i = 0 ; i<hotels.size();i++){
            if(i%2==1){
                hotel_in_row.add(hotels.get(i));
                list_of_row.add(hotel_in_row);
                hotel_in_row = new ArrayList<>();

            }else{
                hotel_in_row.add(hotels.get(i));
            }
        }
        if(list_of_row.isEmpty()){
            list_of_row.add(hotel_in_row);
        }

        ModelAndView mv = new ModelAndView("Pages/Hotel_ListDForFilter");
        mv.addObject("list_of_row",list_of_row);
        mv.addObject("number_of_page",number_of_page);
        mv.addObject("active_page",page_number);
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
