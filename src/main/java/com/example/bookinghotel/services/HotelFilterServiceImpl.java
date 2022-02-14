package com.example.bookinghotel.services;

import com.example.bookinghotel.entities.Hotel;
import com.example.bookinghotel.repositories.HotelFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class HotelFilterServiceImpl implements HotelFilterService{
    //phan dung code
    @Autowired
    HotelFilterRepository hotelFilterRepo;
    @Override
    public <S extends Hotel> S save(S entity) {
        return hotelFilterRepo.save(entity);
    }

    @Override
    public <S extends Hotel> Iterable<S> saveAll(Iterable<S> entities) {
        return hotelFilterRepo.saveAll(entities);
    }

    @Override
    public Optional<Hotel> findById(Long aLong) {
        return hotelFilterRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return hotelFilterRepo.existsById(aLong);
    }

    @Override
    public Iterable<Hotel> findAll() {
        return hotelFilterRepo.findAll();
    }

    @Override
    public Iterable<Hotel> findAllById(Iterable<Long> longs) {
        return hotelFilterRepo.findAllById(longs);
    }

    @Override
    public long count() {
        return hotelFilterRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void delete(Hotel entity) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void deleteAll(Iterable<? extends Hotel> entities) {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public void deleteAll() {
        // khong phuc vu
        System.out.println("no permission to delete");
    }

    @Override
    public Iterable<Hotel> listHotelByStandardAndPropertyAndOrderByStandard(int standard, String property,String orderBy) {
        if(orderBy!=null&&!orderBy.isEmpty())
        return hotelFilterRepo.listHotelByStandardAndProperty(standard,property);
        return hotelFilterRepo.listHotelByStandardAndPropertyAndOrderByStandard(standard,property,orderBy);
    }
    // phan dung code-end
}
