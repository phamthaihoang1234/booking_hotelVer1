package com.example.bookinghotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchHotelByRegionController {
    @GetMapping("/all-locations")
    String searchHotelByRegion(){
        return "Pages/all-locations";
    }

    @GetMapping("/")
    void returnRegion(){
       String regions = "A,An Giang,B,Bà Rịa - Vũng Tàu,Bạc Liêu,Bắc Giang,Bắc Kạn,Bắc Ninh,Bến Tre,Bình Dương,Bình Định,Bình Phước,Bình Thuận," +
               "C,Cà Mau,Cao Bằng,Cần Thơ,Đ,Đà Nẵng,Đắk Lắk,Đắk Nông,Điện Biên,Đồng Nai,Đồng Tháp,G,Gia Lai," +
               "H,Hà Giang,Hà Nam,Hà Nội,Hà Tĩnh,Hải Dương,Hải Phòng,Hậu Giang,Hòa Bình,Hưng Yên,K,Kiên Giang,Kon Tum,Khánh Hòa" +
               "L,Lai Châu,Lạng Sơn,Lào Cai,Lâm Đồng,Long An,N,Nam Định,Ninh Bình,Ninh Thuận,Nghệ An,P,Phú Thọ,Phú Yên," +
               "Q,Quảng Bình,Quảng Nam,Quảng Ninh,Quảng Ngãi,Quảng Trị,S,Sóc Trăng,Sơn La" ;
    }


    //     {value: 'T'},
    //     {value: 'Tây Ninh'},
    //     {value: 'Tiền Giang'},
    //     {value: 'TP Hồ Chí Minh'},
    //     {value: 'Tuyên Quang'},
    //     {value: 'Thái Bình'},
    //     {value: 'Thái Nguyên'},
    //     {value: 'Thanh Hóa'},
    //     {value: 'Thừa Thiên Huế'},
    //     {value: 'Trà Vinh'},
    //     {value: 'V'},
    //     {value: 'Vĩnh Long'},
    //     {value: 'Vĩnh Phúc'},
    //     {value: 'Y'},
    //     {value: 'Yên Bái'},
    // ];
}
