package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Report;
import com.example.bookinghotel.repositories.HotelReportRepository;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService{

    private HotelReportRepository hotelReportRepository;


    @Override
    public Report save(Report report) {
        return hotelReportRepository.save(report);
    }

}
