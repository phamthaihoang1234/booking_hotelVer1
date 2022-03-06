package com.example.bookinghotel.repositories;

import com.example.bookinghotel.entities.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HotelReportRepository extends CrudRepository<Report,Long> {

    Optional<Report> findById(Long id);


    Iterable<Report> findAll();

}
