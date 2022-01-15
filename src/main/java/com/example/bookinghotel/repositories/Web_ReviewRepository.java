package com.example.bookinghotel.repositories;

import com.example.bookinghotel.entities.WebReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface Web_ReviewRepository extends CrudRepository<WebReview,Long> {
    @Override
    Iterable<WebReview> findAll();
    //find top 4 comment with highest rate
    @Query(value = "select wr from WebReview wr order by wr.rate desc ")//limit 4
    List<WebReview> findTop4Comment(Pageable pageable);
    //findTop4Comment(PageRequest.of(0,4));

}
