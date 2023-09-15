package ru.itmo.se.bl.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab3.entity.HotelBooking;
import ru.itmo.se.bl.lab3.entity.TouristInfo;
import ru.itmo.se.bl.lab3.repository.HotelBookingRepository;

@Service
public class HotelBookingService {
    private final HotelBookingRepository repo;

    @Autowired
    public HotelBookingService(HotelBookingRepository repo) {
        this.repo = repo;
    }

    public HotelBooking getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public HotelBooking getByTouristInfo(TouristInfo touristInfo) {
        return this.repo.findByTouristInfo(touristInfo);
    }

    public void saveHotelBooking(HotelBooking hotelBooking) {
        repo.save(hotelBooking);
    }
}
