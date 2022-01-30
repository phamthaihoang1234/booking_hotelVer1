<script>
    /** Created by Group6 on 15/12/2015. **/
    $(document).ready(function () {
    /* navigation-bar */
    /* viec xay ra cua mouseenter xay ra qua' nhanh => sau 1 hoac 2s thi` moi' kich hoat thanh nav */
    // How to add delay to jquery mouseover? [duplicate]: http://stackoverflow.com/questions/15575993/how-to-add-delay-to-jquery-mouseover
    // cung co 1 phuong thuc delay khac: https://api.jquery.com/delay/    http://www.w3schools.com/jquery/eff_delay.asp
    var timer;
    var delay = 300; //dam bao khi re chuot nhanh van~ chua so? menu ra
    $('.left-nav-city').hover(function () {
    //dat ngoai` function
    var $glyphicon = $(this).find('.tmp-glyphicon');
    //find ID (dug ra la dung` class cho nav_sub, nhu*ng do da cai nhieu` css truoc' do, bi anh huog', va` dung
    //var att = $(this).attr('id'); de xac dinh ten id
    //ban dau dung` ID => co nhieu dk if
    //var $txt = $(this).find('.txt-name-location').text();
    //if ($txt === "HÀ NỘI") {
    //    $nav_sub = $('#left-nav-A');
    //} else if ($txt === "ĐÀ NẴNG") {
    //    $nav_sub = $('#left-nav-B');
    //} else if ($txt === "HỒ CHÍ MINH") {
    //    $nav_sub = $('#left-nav-C');
    //} else {
    //    //'khi hover vao` TẤT CẢ
    //    $nav_sub = '';
    //}
    //dat ngoai function (neu dat trong thi` phai them bien' de? ho~ tro* this)
    $nav_sub = $(this).find('.tmp-left-nav');
    // on mouse in, start a timeout
    timer = setTimeout(function () {
    // do your stuff here
    $nav_sub.fadeIn(10);
    $glyphicon
    //viet gop, dau ; dat o thuoc tinh cuoi cung`, co' the gop css vao` class rotate-up
    .css("color", "grey")
    //position = relative, dung su* kien Fade ko nhanh = remove class'
    .removeClass('glyphicon-menu-down rotate-down')
    .addClass('glyphicon-menu-up rotate-up');
}, delay);
}, function () {
    //phai khai bao' lai bien' $glyphicon, con` $txt thi` ko can
    var $glyphicon = $(this).find('.tmp-glyphicon');
    // on mouse out, cancel the timer
    clearTimeout(timer);
    $nav_sub.fadeOut();
    $glyphicon
    /*lay thong so' tai vi tri span da~ cai` (ko fai eye picker) */
    .css("color", "#E58373")
    .removeClass('glyphicon-menu-up rotate-up')
    .addClass('glyphicon-menu-down rotate-down')
});

    $('#search-locations img').click(function () {
    $('#search-locations input').val('');
    $('#search-locations input').focus();
});

    $('#search-locations').hover(function () {
    if ($('#autocomplete').val() !== '') {
    $('#search-locations img').fadeIn();
}
}, function () {
    $('#search-locations img').fadeOut();
});
    //su* kien change (ko duoc): http://stackoverflow.com/questions/12797700/jquery-detect-change-in-input-field
    $('#autocomplete').on('input', function () {
    //alert('autocomplete changed!');
    if ($(this).val().length !== 0) {
    $('#search-locations img').fadeIn();
} else {
    $('#search-locations img').fadeOut();
}
});

    $('#booking_form_search').click(function () {
    //luu tru~ bien' cho trang tim kiem (khi load trang nay` se~ thay') http://www.w3schools.com/html/html5_webstorage.asp
    save_name_of_location();
});

    function save_name_of_location() {
    if (typeof (Storage) !== "undefined") {
    if (localStorage.index_tmp_name) {
    var $txtLocal = $('#autocomplete').val().toUpperCase();
    localStorage.index_tmp_name = $txtLocal;
    //alert(localStorage.index_tmp_name.toUpperCase());
    if ($txtLocal === 'HÀ NỘI') {
    // luu bien' de ho tro tao ra cac options lien quan den dia diem noi bat tai trang search hotels
    localStorage.ddnoibat = '0';
} else if ($txtLocal === 'ĐÀ NẴNG') {
    localStorage.ddnoibat = '1';
} else if ($txtLocal === 'TP HỒ CHÍ MINH') {
    localStorage.ddnoibat = '2';
}
    localStorage.index_tmp_typeofroom = $('#loaiphong select').val();
    //save Date (checkin, checkout)
    var check_in = new Date(document.getElementById("datepicker1").value);
    var check_out = new Date(document.getElementById("datepicker2").value);
    if ((isNaN(check_in) == false && isNaN(check_out) == false)) {
    //check_in
    var month_check_in = check_in.getMonth() + 1;
    var day_check_in = check_in.getDate();
    if (check_in.getMonth() < 10) month_check_in = "0" + month_check_in;
    if (day_check_in < 10) day_check_in = "0" + day_check_in;
    //check_out
    var month_check_out = check_out.getMonth() + 1;
    var day_check_out = check_out.getDate();
    if (check_out.getMonth() < 10) month_check_out = "0" + month_check_out;
    if (day_check_out < 10) day_check_out = "0" + day_check_out;
    //convert
    var check_in_convert = check_in.getFullYear() + "-" + month_check_in + "-" + day_check_in;
    var check_out_convert = check_out.getFullYear() + "-" + month_check_out + "-" + day_check_out;
    //save
    localStorage.index_tmp_checkin = check_in_convert;
    localStorage.index_tmp_checkout = check_out_convert;
} else {
    localStorage.index_tmp_checkin = "2016-01-20";
    localStorage.index_tmp_checkout = "2016-01-21";
}
} else {
    localStorage.index_tmp_name = "Đà Nẵng";
    localStorage.index_tmp_checkin = "2016-01-20";
    localStorage.index_tmp_checkout = "2016-01-21";
    localStorage.index_tmp_typeofroom = "1 phòng (2 người lớn)";
}
    //bien' nay` se~ duoc chuyen sang trang search-hotels
} else {
    alert("Sorry, your browser does not support web storage...");
}
}

});

    //  link to search-hotels
    $(document).ready(function () {
    //ko duoc $('#left-nav-city').click(function(){
    $('#left-nav li').click(function () {
        //tren thuc te khi click vao` nhom' li thu' 2 cung co' 2 truong hop xay ra:
        //khi click vao nhom dia diem noi tieng => vao trang search-hotels
        //khi click vao cac ks thi` chay thang den' trang room-bookings
        //========
        //xac dinh thuoc tinh id, class: var att = $(this).attr('id');
        //do dag cai li chung chung cho #left-nav nen them dk if de xac dinh dag o nhom' nao`
        //alert($(this).find('.txt-name-location').text());
        //return false;
        var bcheck;
        if ($(this).attr('class') == "left-nav-city") {
            var $txt = $(this).find('.txt-name-location').text();

            if ($txt === 'HÀ NỘI') {
                // code bị lặp lại tại nút submit: đúng ra việc check được thực hiện tại trang search-hotels
                // (co' khac ve` text o? dia diem: TP HO CHI MINH)
                // luu bien' de ho tro tao ra cac options lien quan den dia diem noi bat tai trang search hotels
                localStorage.ddnoibat = '0';
                bcheck = 'true';
            } else if ($txt === 'ĐÀ NẴNG') {
                localStorage.ddnoibat = '1';
                bcheck = 'true';
            } else if ($txt === 'HỒ CHÍ MINH') {
                localStorage.ddnoibat = '2';
                bcheck = 'true';
            } else if ($txt === 'TẤT CẢ') {
                window.location = 'all-locations.html';
                return false;
            }
        } else {
            //alert('bcheck = false 1');
            bcheck = 'false';
            //http://api.jquery.com/parents/
            var $txt = $(this).parents("li").find('.txt-name-location').text();
            var local = $(this).parents('.nav-ddnoibat').find('.nav-title-loaiphong').text();
        }

        if (typeof (Storage) !== "undefined") {
            //cac bien' nay` se~ duoc chuyen sang trang search-hotels
            if (localStorage.index_tmp_name) {
                localStorage.index_tmp_name = $txt;
            } else {
                localStorage.index_tmp_name = "Đà Nẵng";
            }
            //3 bien sau: co' dinh
            localStorage.index_tmp_checkin = "";
            localStorage.index_tmp_checkout = "";
            localStorage.index_tmp_typeofroom = "1 phòng (2 người lớn)";
        } else {
            alert("Sorry, your browser does not support web storage...");
        }

        if (bcheck === 'true') {
            //alert('bcheck = true');
            //hoac window.open("/searchHotel","_self");
            window.location = '/searchHotel';
            return false;
        } else {
            if (local === 'Địa điểm nổi tiếng' || $(this).text() === 'Tất cả ...') {
                window.location = '/searchHotel';
                return false;
            } else {
                //luu tru ten bien' khach san, nhu*ng ko co' dia chi => viec xac dinh Google se~ co the bi. co' dinh
                //alert('bcheck = false 2');
                localStorage.searchhotels_tmp_nameofhotel = $(this).text();
                window.location = '/room_booking';
                return false;
            }
        }
    });

    $('#promotion figure').click(function () {
    var $txt = $(this).find('.txt-name-location').text();
    if (typeof (Storage) !== "undefined") {
    //cac bien' nay` se~ duoc chuyen sang trang search-hotels
    if (localStorage.index_tmp_name) {
    //chua chuyen chu~ hoa thanh chu~ thuong` (neu dung ham thi co phan biet Unicode)
    //C1: .css('text-transform','capitalize')
    //C2: http://www.w3schools.com/jsref/prop_style_texttransform.asp
    localStorage.index_tmp_name = $txt;
} else {
    localStorage.index_tmp_name = "Đà Nẵng";
}
    //3 bien sau: co' dinh
    localStorage.index_tmp_checkin = "";
    localStorage.index_tmp_checkout = "";
    localStorage.index_tmp_typeofroom = "1 phòng (2 người lớn)";
} else {
    alert("Sorry, your browser does not support web storage...");
}
    //hoac window.open("/searchHotel","_self");
    window.location = '/searchHotel';
});

});

    $(document).ready(function () {
    $('#loaiphong select').change(function () {
        if ($(this).val() == "lựa chọn khác ...") {
            $('#loaiphong').fadeOut();
            $('#loaiphong-khac').fadeIn();
            $('#loaiphong-khac input[name=guest]').focus();
        }
    });
});

    $(document).ready(function () {
    // Add smooth scrolling to all links
    // https://css-tricks.com/examples/SmoothPageScroll/
    // http://www.w3schools.com/jquery/css_scrolltop.asp
//              $("a").on('click', function(event) {
//            dung the a khi co' nhieu the a cai lien quan den Scroll + ko co hieu u*ng
    $("#scrolltop").on('click', function (event) {
        // Prevent default anchor click behavior
//                    $(this).css('cursor','none');

        event.preventDefault();
//                    hide cursor do bi. tac dong den img co' hieu u*ng' effect bubba: http://www.w3schools.com/cssref/pr_class_cursor.asp
        $('body').css('cursor', 'none');
        // Store hash
//                var hash = this.hash;

        // Using jQuery's animate() method to add smooth page scroll
        // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
        $('html, body').animate({
//                    scrollTop: $(hash).offset().top
            scrollTop: 0
        }, 900, function () {
            $('body').css('cursor', 'default');
            // Add hash (#) to URL when done scrolling (default click behavior)
//                    window.location.hash = hash;
        });
    });
});




</script>
<script>



    //http://www.w3schools.com/jquery/css_scrolltop.asp
    // http://stackoverflow.com/questions/28452235/make-a-nav-bar-stick-to-the-top-when-scrolling-with-css
    // phai them 1 class de chinh? lai margin left (neu' div nam` ben phai')
    $(window).scroll(function () {
//  if you hard code, then use console, .log to determine when you want the nav bar to stick.
//  mo phan kiem tra phan tu? se~ thay' tab nay`', khi man hinh bi thu nho => scroll vi tri ko chinh xac
//  console.log($(window).scrollTop());
//  alert($(window).scrollTop());
    var $target_1 = $('#right-aboutHotel');
    if ($(window).scrollTop() > 0) {
    $target_1.addClass('navbar-fixed-top');
    $target_1.addClass('right-aboutHotel-margin-left');
}
    if ($(window).scrollTop() < 65) {
    $target_1.removeClass('navbar-fixed-top');
    $target_1.removeClass('right-aboutHotel-margin-left');
}
//  1088, dung` chuot keo' chinh' xac hon scroll giua~.
    if ($(window).scrollTop() > 1100) {
    $target_1.removeClass('navbar-fixed-top');
    $target_1.removeClass('right-aboutHotel-margin-left');

}
//  tai trang chinh thi ho van~ thay' duoc nut' book-now khi vi tri dang o? footer
});
    //##########################
    $(document).ready(function () {
    $('#slt-rooms').change(function () {
//          hoac co the dung vong lap duyet tu` Room6 ve` Room2
        var amount = $(this).val();
//phai dao nguoc amount lon' nhat' xay ra truoc' roi` thoat' dk return false, cac
        if (amount >= 1) {
            fadeInRoom(1);
        } else {
            resetRoom('#room-row1');
        }
        if (amount >= 2) {
            fadeInRoom(2);
        } else {
            resetRoom('#room-row2');
        }
        if (amount >= 3) {
            fadeInRoom(3);
        } else {
            resetRoom('#room-row3');
        }
        if (amount >= 4) {
            fadeInRoom(4);
        } else {
            resetRoom('#room-row4');
        }
        if (amount >= 5) {
            fadeInRoom(5);
        } else {
            resetRoom('#room-row5');
        }
        if (amount == 6) {
            fadeInRoom(6);
        } else {
            resetRoom('#room-row6');
        }
        if (amount == '6+') {
//              do cai' cac cai' else o? tren => room 2 den' 6 deu refresh
//                alert('Để đặt số lượng lớn, vui lòng để lại số điện thoại của bạn. ' +
//                'Chúng tôi sẽ gọi cho bạn trong thời gian sớm nhất.');
//                alert: khi viet xuog dong thi` tu* dong them +
            $('#extra-room').fadeIn();
            //$('#book-now').val("YÊU CẦU GỌI LẠI");
            $('#book-now').fadeOut(1);
            $('#mytable-th').fadeOut(1);
            $('#table-result').fadeOut(1);
//              change noi dung cua nut' book-now
        } else {
//          tinh tien phong
//              table-result-1, table-result-2
//              Tổng số 1 Phòng, 1 Khách & 1 Đêm
            var iRoom = amount;
            var iGuest = 0;
            for (var i = 1; i <= amount; i++) {
                iGuest = iGuest + parseInt($('#room-row' + i).find('span').text());
            }

//          session 15.4.3 Date	Object
            function GetDays() {
                var check_in = new Date(document.getElementById("date1").value);
                var check_out = new Date(document.getElementById("date2").value);

                if ((isNaN(check_in) == true || isNaN(check_out) == true)) {
                    //alert('Nhận phòng & Trả phòng không hợp lệ');
                    //ko can show tooltip, da cai day du tai nut DAT PHONG
                    return 1;
                } else if (check_out < check_in) {
                    //xay ra sau cung` (ko nhap chung voi If thu' nhat')
                    $('.validation-advice').fadeIn();
                    return 1;
                } else {
                    return parseInt((check_out - check_in) / (24 * 3600 * 1000));
                }
            }

//                chi thay doi so' cua cac ID chu' ko thay doi toan` bo (tranh' bi xoa' ID), ko the dung` rieng cho nut minus, plus
//                dung` val() ko dung khi thay doi so' luong room' (chi dung' cho nut' minus, plus)
//                $('#table-result-1').html("Tổng số " + iRoom + " Phòng, " + iGuest + " Khách & " + GetDays() + " Đêm");
            $('#table-result-11').html(iRoom);
            $('#table-result-12').html(iGuest);
            $('#table-result-13').html(GetDays());
            //ho tro khi click DAT PHONG
            $('#table-result-21').html(iRoom);
            $('#table-result-22').html(iGuest);

            var iTotal = 0;
            for (var j = 1; j <= amount; j++) {
//                  http://stackoverflow.com/questions/16970237/jquery-replace-g-do-not-work-for-me-but-others
                var replaceDot = $('#room-row' + j).find('.row-total').text().replace(/\./g, "");
                iTotal = iTotal + parseInt(replaceDot);
            }

            iTotal *= GetDays();

//          https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Number/toLocaleString
            $('#table-result-2').html(iTotal.toLocaleString('vn-VN', {style: 'currency', currency: 'VND'}));
            $('#tien-thanhtoan').html(iTotal.toLocaleString('vn-VN', {style: 'currency', currency: 'VND'}));
        }
    });

//        phai tinh tu` 1 vi khi chon 6+ thi` room1 se~ bi an?
    var fadeInRoom = function (target) {
//      var fadeIn_loop_from1to = function(target){
//      ko can dung vong lap do dk >= do co' xay ra o moi dk if
    $('#mytable-th').fadeIn();
    $('#table-result').fadeIn();
    $('#room-row' + target).fadeIn();
    var replaceDot = $('#room-row' + target).find('.row-total').text().replace(/\./g, "");
    $('#room-row' + target).find('.row-total').html(parseInt(replaceDot).toLocaleString('vn-VN'));
    //
    $('#extra-room').fadeOut(10);
    $('#book-now').val("ĐẶT PHÒNG");
};
//        refresh = reset
    var resetRoom = function (iRoom) {
//          cho fadeOut nhanh thi` moi ko thay' qua trinh` reset'
    $(iRoom).fadeOut(10);
    // return = 1 sau khi an? xong
    $(iRoom).find('span').html(1);
    $(iRoom).find('.minus').removeClass('img-allowed');
    $(iRoom).find('.minus').addClass('img-not-allowed');
    $(iRoom).find('.plus').removeClass('img-not-allowed');
    $(iRoom).find('.plus').addClass('img-allowed');
};
});

    var onclick_guest = function () {
    $(document).on("click", '.minus', function () {
        var guest = parseInt($(this).parent().find('span').text());
        var guestOutput = parseInt($('#table-result-12').text());
        var pass_val = function (object) {
//                ko cai this truc tiep o day
            object.parent().find('span').html(guest - 1);
            $('#table-result-12').html(guestOutput - 1);
            $('#table-result-22').html(guestOutput - 1);

        };
        if (guest == 1) {
            return false;
        } else if (guest == 2) {
            pass_val($(this));
            $(this).removeClass('img-allowed');
            $(this).addClass('img-not-allowed');
        } else {
            pass_val($(this));
            $(this).parent().find('.plus').removeClass('img-not-allowed');
            $(this).parent().find('.plus').addClass('img-allowed');
        }
    });

    $(document).on("click", '.plus', function () {
    var guest = parseInt($(this).parent().find('span').text());
    var guestOutput = parseInt($('#table-result-12').text());
    var pass_val = function (object) {
//                ko cai this truc tiep o day
    object.parent().find('span').html(guest + 1);
    $('#table-result-12').html(guestOutput + 1);
    $('#table-result-22').html(guestOutput + 1);
};
    if (guest == 1) {
    pass_val($(this));
    $(this).parent().find('.minus').removeClass('img-not-allowed');
    $(this).parent().find('.minus').addClass('img-allowed');
} else if (guest == 2) {
    pass_val($(this));
    $(this).removeClass('img-allowed');
    $(this).addClass('img-not-allowed');
} else {
    return false;
}
});
};

    $(document).ready(onclick_guest);

    $(document).ready(function () {
    $('.validation-advice').click(function () {
        $(this).fadeOut();
    });
});

    $(document).ready(function () {
    $('input[type=date]').change(function () {
//            se xay ra khi gia tri dien day du? dd/mm/yyyy
//            alert('changed');
        function GetDays2() {
            var check_in = new Date(document.getElementById("date1").value);
            var check_out = new Date(document.getElementById("date2").value);

            if ((isNaN(check_in) == true || isNaN(check_out) == true)) {
                return 1;
            } else if (check_out < check_in) {
                $('.validation-advice').fadeIn();
                return 1;
            } else {
                //neu lan truoc do' co fadeIn thi bay h se fadeOut
                $('.validation-advice').fadeOut();
                return parseInt((check_out - check_in) / (24 * 3600 * 1000));
            }
        }

        //nhom nay` co viet o su* kien change Room
        $('#table-result-13').html(GetDays2());

        var amount = $('#slt-rooms').val();
        var iTotal = 0;

        for (var j = 1; j <= amount; j++) {
//              http://stackoverflow.com/questions/16970237/jquery-replace-g-do-not-work-for-me-but-others
            var replaceDot = $('#room-row' + j).find('.row-total').text().replace(/\./g, "");
            iTotal = iTotal + parseInt(replaceDot);
        }

        iTotal *= GetDays2();

//          https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Number/toLocaleString
        $('#table-result-2').html(iTotal.toLocaleString('vn-VN', {style: 'currency', currency: 'VND'}));
        $('#tien-thanhtoan').html(iTotal.toLocaleString('vn-VN', {style: 'currency', currency: 'VND'}));
    });
});

    $(document).ready(function () {
//  group 1
    $('#book-now').click(function () {
//  co cai o* su* kien change Room (khac nhau o return + dkien else)
//        if ($(this).val() == 'YÊU CẦU GỌI LẠI'){
//            if ($('#extraroom-tooltip input').val() == ''){
//                $('#extraroom-tooltip').addClass('extraroom-phone');
//                //$('.extraroom-phone').fadeOut(4000); ko the dung fadeOut (bi an? luon Input)
//                var timer;
//                var delay = 3000;
//                timer = setTimeout(function() {
//                    $('#extraroom-tooltip')
//                        //neu viet gop: thi dau ; dat o thuoc tinh cuoi cung`
//                        .removeClass('extraroom-phone')
//                }, delay);
//                return false;
//            } else {
//            }
//        }

        var check_in = new Date(document.getElementById("date1").value);
        var check_out = new Date(document.getElementById("date2").value);

        if ((isNaN(check_in) == true && isNaN(check_out) == true)) {
            //alert('Nhận phòng trả phòng không hợp lệ');
            $('.validation-advice').fadeIn();
            //tu dong an?
            $('.validation-advice').fadeOut(7000);
            return false;
        } else if (isNaN(check_in)) {
            $('#tooltip1').fadeIn();
            $('#tooltip1').fadeOut(4000);
            return false;
        } else if (isNaN(check_out)) {
            $('#tooltip2').fadeIn();
            $('#tooltip2').fadeOut(4000);
            return false;
        } else if (check_out < check_in) {
            //xay ra sau cung` (ko nhap chung voi If thu' nhat')
            $('.validation-advice').fadeIn();
            $('.validation-advice').fadeIn(7000)
            return false;
        } else {
            //return parseInt((check_out - check_in) / (24 * 3600 * 1000));
            $('#right-aboutHotel-1').fadeOut();
            $('#right-aboutHotel-2').fadeIn();
            //update checkin, checkout: http://www.w3schools.com/jsref/jsref_obj_date.asp
            $('#right-aboutHotel-2-checkin').html(check_in.toLocaleDateString());
            $('#right-aboutHotel-2-checkout').html(check_out.toLocaleDateString());
            // lam` mo` div, se fadeOut tai #book-now-2 click
            $('.tmp-left-aboutHotel').fadeIn();
        }
    });

    var changeRoom = function () {

    $('#bt-thanhtoan').fadeIn(10);
    $('#verify-code').fadeOut(10);
    $('#right-aboutHotel-2').fadeOut(10);
    $('.tmp-left-aboutHotel').fadeOut();
    $('#thanhtoan input[name=info-name]').val('');
    $('#thanhtoan input[name=info-phone]').val('');
    $('#thanhtoan input[name=info-email]').val('');
    $('#verify-code input[name=enter-code]').val('');
    //
    $('#right-aboutHotel-1').fadeIn();
};

    $('#book-now-2').click(function () {
    changeRoom();
});
//  group 2
    $('#bt-thanhtoan1').click(function () {
    var $name = $('#thanhtoan input[name=info-name]').val();
    //viet ten chuan thi dung` dau' _ duoi'
    var $phone = $('#thanhtoan input[name=info-phone]').val();
    if (($name !== '') && ($phone !== '')) {
    // kiem tra so' dien thoai: http://www.w3resource.com/javascript/form/javascript-validation-download.php
    // thay vi dung pattern: pattern="[0-9]{6,}" title="Tối thiểu 6 số" tai input
    var numbers = /^[0-9]+$/;
    if ($phone.match(numbers)) {
    //alert('Your Registration number has accepted....');
} else {
    alert('Số điện thoại ko hợp lệ !');
    $('#verify-code input[name=enter-code]').focus();
    return false;
}
    //
    $('#bt-thanhtoan').fadeOut();
    $('#verify-code').fadeIn();
    //neu dung` type = button thi binh thuong nhu*ng ko check duoc required
    //neu ko co' return thi trang giong nhu* bi. refresh
    $('#verify-code input[name=enter-code]').focus();
    return false;
} else {

    //da~ cai them thuoc tinh' required'
}
});

    // verify-code
    $('#verify-code input[type=submit]').click(function () {
    //alert($code);
    var $code = $('#verify-code input[name=enter-code]').val();
    if ($code !== '') {
    //alert('XÁC NHẬN THÀNH CÔNG !');
    $('.ios-overlay').fadeIn(10);

    var timer;
    var delay = 3000;
    timer = setTimeout(function () {
    changeRoom();
}, delay);
    ////de? cho tu* dong return = false <=> refresh trang
    return false;
} else {
    //da~ cai them thuoc tinh' required'
}
});

    $('#verify-code-resendcode').click(function () {
    alert('Đã gửi lại mã');
});

    $('#verify-code-cancel').click(function () {
    $('#bt-thanhtoan').fadeIn();
    $('#verify-code').fadeOut();
    $('#verify-code input[name=enter-code]').val('');
});
});

    $(document).ready(function () {
// http://www.w3schools.com/jquery/jquery_slide.asp
// https://api.jquery.com/mouseleave/
// ban dau dung` hover
    $('#paywith').click(function () {
        $('#paywith div').slideDown();
    });
    $('#paywith div').mouseleave(function () {
    $(this).fadeOut(100);
});

    $('#paywith td').click(function () {
    alert('< Chưa tạo trang thanh toán bằng thẻ />');
});
});

</script>
<script>
    /**
    * Created by Group6 on 03/01/2016.
    */
    var refreshPage = function () {
    $('#page-1').fadeOut();
    $('#page-1').fadeIn();

    $('#left-center-result').fadeOut();
    $('#left-center-result').fadeIn();
};
    // các input & button của nav
    $(document).ready(function () {
    //nhom' #search-locations
    $('#search-locations').hover(function () {
        //var inputval = $('#autocomplete').val();
        if ($('#autocomplete').val() !== '') {
            $('#search-locations img').fadeIn();
        }
    }, function () {
        $('#search-locations img').fadeOut();
    });
//su* kien change (ko duoc): http://stackoverflow.com/questions/12797700/jquery-detect-change-in-input-field
    $('#autocomplete').on('input', function () {
    // alert('autocomplete changed!');
    if ($(this).val().length !== 0) {
    $('#search-locations img').fadeIn();
} else {
    $('#search-locations img').fadeOut();
}
});
    $('#search-locations img').click(function () {
    $('#search-locations input').val('');
    $('#search-locations input').focus();
});
    //nhom' #loaiphong
    $('#loaiphong select').change(function () {
    if ($(this).val() == "lựa chọn khác ...") {
    $('#loaiphong').fadeOut();
    $('#loaiphong-khac').fadeIn();
    $('#loaiphong-khac input[name=guest]').focus();
}
});
    //nhom' button[type=submit]
    <!--button lien quan den' cac' code modal da~ khai bao' nen khi click thi` co' dau hieu. refresh page => them return false-->
    $("button[type=submit]").click(function () {
    var get_location = function () {
    $('.name-of-location').text($('#autocomplete').val());
};
    var check_in = new Date(document.getElementById("s-datePicker1").value);
    var check_out = new Date(document.getElementById("s-datePicker2").value);
    if ((isNaN(check_in) == false && isNaN(check_out) == false)) {
    if (check_in > check_out) {
    alert("Ngày nhận phòng & trả phòng ko hợp lệ !");
    //return false se~ xay tra truoc dk required cua input co' id= autocomplete'
    // => dat. trong ham` If de ko anh huong den' dk required
    return false;
} else {
    get_location();
    refreshPage();
    return false;
}
} else if (isNaN(check_in) || isNaN(check_out)) {
    get_location();
    refreshPage();
    return false;
}
});
});

    var main = function () {
//    them su* kien nay` de biet duoc dang load thong tin hoac dung` progress
//    phai cai dat truc tiep' tai tung su* kien de ho tro su* kien click vao li'
//    $('#center input').click(function(){
//        refreshPage();
//    });
//    voi dk cac input phai dung dk change chu' ko phai click'
    var boolean;
    //ko the kiem tra tai li chua' checkbox'
    $("#left-center ul").click(function (event) {

    var target = $(event.target);
    // set boolean = 'true' cho nhieu truong hop
    boolean = 'true';

    if (target.is("li")) {
    //alert('click to li');
    //if ($(this).find('input[type=checkbox]').is(':checked')) {
    if (target.find('input[type=checkbox]').is(':checked')) {
    //alert('li 1');
    target.find('input').prop("checked", false).trigger("change");
    boolean = 'false';
    return false;
} else {
    //alert('li 2');
    target.find('input').prop("checked", true).trigger("change");
    boolean = 'false';
}
} else if (target.is("span")) {
    //alert('click to span');
    if (target.parent().find('input[type=radio]').is(':checked')) {
    //alert('span 1');
    target.find('input[type=radio]').prop("checked", false).trigger("change");
    boolean = 'false';
    return false;
} else {
    //alert('span 2');
    target.parent().find('input[type=radio]').prop("checked", true).trigger("change");
    boolean = 'false';
}
} else {
    //alert('click to radio or checkbox, Thêm ...');
    //them dk kiem tra all checkbox = empty sau khi trigger xong tai tu*ng` nhom' checkbox' + delay
    target.trigger("change");
    boolean = 'false';
    //return false;
}
});

    $('input[name=dd-noibat]').change(function () {
    // se bi refresh 2 lan neu click truc tiep' vao day' (disable input thi ko hop ly)
    // => phai them 1 bien de kiem tra la` duoc kich' hoat tu` li, vi` li bao phu luon input
    // neu click truc tiep' vao input thi co the bi tinh den 2 lan do bi bao phu boi? li
    if (boolean === 'true') {
//      cho show lai neu' da~ bi. fadeOut
    $('#opts-ddnoitieng').fadeIn();
    var toAdd = $("input[name=dd-noibat]:checked").val();
    $('#opts-ddnoitieng').html(
    '<a href="#">' +
    '<div class="options-x">' + '&times;' + '</div>' + toAdd +
    '</a>');

    add_HoverClick();
    refreshPage();
}
});

    // 1 nut
    //$("#range-price").change(function() {
    //    $('#opts-price').fadeIn();
    //    var toAdd = (parseInt($(this).val())).toLocaleString('vn-VN', { style: 'currency', currency: 'VND' });
    //    $('#opts-price').html(
    //        '<a href="#">' +
    //        '<div class="options-x">' + '&times;' + '</div>' + toAdd +
    //        '</a>');
    //
    //    add_HoverClick();
    //});

    // 2 nut http://jqueryui.com/slider/#range
    $(function () {
    $("#slider-range").slider({
    range: true,
    min: 168600,
    max: 25952800,
    values: [168600, 25952800],
    slide: function (event, ui) {
    //stop: function( event, ui ) {
    // ko co' parseInt
    var gia1 = ((ui.values[0]).toLocaleString('vn-VN', {style: 'currency', currency: 'VND'}));
    var gia2 = ((ui.values[1]).toLocaleString('vn-VN', {style: 'currency', currency: 'VND'}));
    var gia = 0;
    $("#amount-1").text(gia1);
    $("#amount-2").text(gia2);

    $('#opts-price').fadeIn();

    if ((ui.values[0]) == (ui.values[1])) {
    gia = (gia1);
} else {
    gia = ("> " + gia1 + " - < " + gia2);
}
    $('#opts-price').html(
    '<a href="#">' +
    '<div class="options-x">' + '&times;' + '</div>' + gia +
    '</a>');

    add_HoverClick();
    //khi keo' nhieu thi` bi. nhay? nhieu => chua hop ly' (them delay)
    //http://www.tutorialspoint.com/jqueryui/jqueryui_slider.htm
    //thay the' thuoc tinh slide thanh stop & tach' rieng
    //refreshPage();
}
});
    //ket hop 2 su* kien cung luc thi moi hop ly'
    $("#slider-range").slider({
    range: true,
    //min: 168600,
    //max: 25952800,
    //values: [ 168600, 25952800 ],
    //slide: function( event, ui ) {
    stop: function (event, ui) {
    //...
    refreshPage();
}
});

//      ban dau
    $("#amount-1").text(($("#slider-range").slider("values", 0)).toLocaleString('vn-VN', {
    style: 'currency',
    currency: 'VND'
}));
    $("#amount-2").text(($("#slider-range").slider("values", 1)).toLocaleString('vn-VN', {
    style: 'currency',
    currency: 'VND'
}));
});

    $('input[name=status]').change(function () {
    refreshPage();
});

    $('input[name=khoangcach]').change(function () {
    if (boolean === 'true') {
    $('#opts-km').fadeIn();
    var toAdd = $("input[name=khoangcach]:checked").val();
    if (toAdd == "Bất kỳ") {
    $('#opts-km').fadeOut();
} else {
    $('#opts-km').html(
    '<a href="#">' +
    '<div class="options-x">' + '&times;' + '</div>' + toAdd +
    '</a>');

    add_HoverClick();
}
    refreshPage();
}
});

    $('input[name=hang-khachsang]').change(function () {
    if (boolean === 'true') {
    $('#opts-hangks').fadeIn();
    var toAdd = $("input[name=hang-khachsang]:checked").val();
    var star = '<span class="glyphicon glyphicon-star" style="color: rgb(255,192,0); font-size: 10px">' + '</span>';

    if (toAdd == "*****") {
    toAdd = star + star + star + star + star;
} else if (toAdd == "****") {
    toAdd = star + star + star + star;
} else if (toAdd == "***") {
    toAdd = star + star + star;
} else if (toAdd == "**") {
    toAdd = star + star;
} else {
//                      neu ko an? thi dung` su* kien check click lan` 2 de? bo? input radio + ko can input bat ky` nay`
    $('#opts-hangks').fadeOut();
}

    $('#opts-hangks').html(
    '<a href="#">' +
    '<div class="options-x">' + '&times;' + '</div>' + toAdd +
    '</a>');

//      $('#opts-hangks').css('backgroundColor','#ffc000');
    add_HoverClick();
    refreshPage();
}
});

    $('#loai input[type=checkbox]').change(function () {
    if (boolean === 'true') {
    $('#opts-loaiks').fadeIn();
    //toAdd = array  http://stackoverflow.com/questions/11945802/how-to-get-multiple-checkbox-value-using-jquery
    var toAdd = $('#loai input[type=checkbox]:checked').map(function () {
    return " " + this.value;
}).get();
    $('#opts-loaiks').html(
    '<a href="#">' +
    '<div class="options-x">' + '&times;' + '</div>' + toAdd +
    '</a>');

    add_HoverClick();

    refreshPage();

    if (toAdd.length == 0) {
    //alert('if' + toAdd.length);
    // ko co delay thi ko the an? (bi choi~ voi' 1 thanh phan nao do' dag chay fia sau)
    var timer;
    var delay = 500;
    timer = setTimeout(function () {
    $('#opts-loaiks').fadeOut();
}, delay);
    //return false;
}
}
});

    $('#tiennghi input[type=checkbox]').change(function () {
    if (boolean === 'true') {
    $('#opts-tiennghi').fadeIn();
    var toAdd = $('#tiennghi input[type=checkbox]:checked').map(function () {
    return " " + this.value;
}).get();
    $('#opts-tiennghi').html(
    '<a href="#">' +
    '<div class="options-x">' + '&times;' + '</div>' + toAdd +
    '</a>');

    add_HoverClick();

    refreshPage();

    if (toAdd.length == 0) {
    //alert('if' + toAdd.length);
    // ko co delay thi ko the an? (bi choi~ voi' 1 thanh phan nao do' dag chay fia sau)
    var timer;
    var delay = 500;
    timer = setTimeout(function () {
    $('#opts-tiennghi').fadeOut();
}, delay);
    //return false;
}
}
});

};

    var add_HoverClick = function () {
//                    su* kien hover & click duoc ap dung cho tat ca cac tuy` chon (co' cai` lan dau tien khi load page)
    $('.options a').hover(
        function () {
            $(this).find('div').fadeIn();
        },
        function () {
            $(this).find('div').fadeOut();
        }
    );
    $('.options').click(function () {
//                        se~ fadeIn lai, khi add html
    $(this).fadeOut();

});
};

    $(document).ready(main);

    //nhóm Lựa chọn của bạn:
    $(document).ready(function () {
    $('.options a').hover(
        function () {
//  http://stackoverflow.com/questions/306583/this-selector-and-children
            $(this).find('div').fadeIn();
        },
        function () {
            $(this).find('div').fadeOut();
        }
    );
    $('.options').click(function () {
    $(this).fadeOut();
    refreshPage();
});

});

    // nhóm SẮP XẾP THEO
    $(document).ready(function () {
    $('#sort a').click(function () {
        $('#sort a').css('border-bottom', 'none');
        $(this).css('textDecoration', 'none');
//                        http://stackoverflow.com/questions/447197/how-to-define-multiple-css-attributes-in-jquery
        $(this).css({
            'border-bottom': '2px solid rgb(220,30,40)'
        });
//                        refreshPage();, chi refresh page (ko phai ket qua)
        $('#page-1').fadeOut();
        $('#page-1').fadeIn();
    });

    $('#sort-price').click(function () {
//                        http://stackoverflow.com/questions/12665107/how-to-get-class-value-of-a-span
    var spanClass = $('#sort-price').find('span').attr('class');
    if (spanClass == 'glyphicon glyphicon-menu-up') {
    $('#sort-price span')
    .removeClass('glyphicon glyphicon-menu-up')
    .addClass('glyphicon glyphicon-menu-down');
} else {
    $('#sort-price span')
    .removeClass('glyphicon glyphicon-menu-down')
    .addClass('glyphicon glyphicon-menu-up');
}

});
});
    // nhóm WOWSLIDER
    $(document).ready(function () {
//  chua the loai bo the? a ben slideshow bo bot the a de khoi nham
//    $('.ws_images').click(function(){
    $('.ws_images').click(function () {
        // riêng Google map vẫn chưa cập nhật được
        //    neu xay ra loi thi thoat trinh duyet
        //    alert('click');
        if (typeof (Storage) !== "undefined") {
            //luu tru~ bien' cho trang room-bookings (khi load trang nay` se~ thay') http://www.w3schools.com/html/html5_webstorage.asp
            //get_name_of_location();
            //dung` ham thi phai khai bao' them bien' de? ho tro $(this)
            if (localStorage.index_tmp_name) {
                //1 so' localStorage giong trang homepage: de? ho tro ghi de`
                localStorage.index_tmp_name = $('#autocomplete').val();
                localStorage.searchhotels_tmp_nameofhotel = $(this).parent().find('.name-of-hotel').text();
                localStorage.searchhotels_tmp_addofhotel = $(this).parent().find('.address-of-hotel').text();
                localStorage.searchhotels_tmp_addofhotel_2 = $(this).parent().find('.address-of-hotel-sub').text() + ' Đà Nẵng';
                //save Date (checkin, checkout)
                //viec khai bao' trung id o? cac file co' bi. anh huong? cai` trung id thi` ko chay. duoc ham` nay`
                var check_in = new Date(document.getElementById("s-datePicker1").value);
                var check_out = new Date(document.getElementById("s-datePicker2").value);
                if ((isNaN(check_in) == false && isNaN(check_out) == false)) {
                    //check_in
                    var month_check_in = check_in.getMonth() + 1;
                    var day_check_in = check_in.getDate();
                    if (check_in.getMonth() < 10) month_check_in = "0" + month_check_in;
                    if (day_check_in < 10) day_check_in = "0" + day_check_in;
                    //check_out
                    var month_check_out = check_out.getMonth() + 1;
                    var day_check_out = check_out.getDate();
                    if (check_out.getMonth() < 10) month_check_out = "0" + month_check_out;
                    if (day_check_out < 10) day_check_out = "0" + day_check_out;
                    //convert
                    var check_in_convert = check_in.getFullYear() + "-" + month_check_in + "-" + day_check_in;
                    var check_out_convert = check_out.getFullYear() + "-" + month_check_out + "-" + day_check_out;
                    //save
                    localStorage.searchhotels_tmp_checkin = check_in_convert;
                    localStorage.searchhotels_tmp_checkout = check_out_convert;
                } else {
                    localStorage.searchhotels_tmp_checkin = "2016-01-20";
                    localStorage.searchhotels_tmp_checkout = "2016-01-21";
                }
            } else {
                localStorage.searchhotels_tmp_name = "Đà Nẵng";
                //localStorage.searchhotels_tmp_nameofhotel = $(this).find('.name-of-hotel').text();
                //localStorage.searchhotels_tmp_addofhotel = $(this).find('.address-of-hotel').text();
                localStorage.searchhotels_tmp_checkin = "2016-01-20";
                localStorage.searchhotels_tmp_checkout = "2016-01-21";
            }
            //bien' nay` se~ duoc chuyen sang trang search-hotels
        } else {
            alert("Sorry, your browser does not support web storage...");
        }
        //hoac window.open("/searchHotel","_self");
        //window.location = '/room_booking';
        //return false;
    });

    //function get_name_of_location() {
    //}
});

    // nhóm BS Pagination (code thuc te' se link den' tung` page rieng biet)
    $(document).ready(function () {
    $("#pagi-1").click(function () {
        // parents co' them s neu xac dinh selector cha cu. the
        // xoa' sach. cac li co' chua' class = active & chi add cho cai' dang click
        $(this).parents("ul").find("li").removeClass('active');
        $(this).parent().addClass('active');
        //
        $('#page-1').fadeIn();
        $('#page-2').fadeOut();
    });
    $("#pagi-2").click(function () {
    $(this).parents("ul").find("li").removeClass('active');
    $(this).parent().addClass('active');
    $('#page-1').fadeOut();
    $('#page-2').fadeIn();
});
});






</script>