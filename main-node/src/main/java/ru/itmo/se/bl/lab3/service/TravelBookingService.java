package ru.itmo.se.bl.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab3.entity.TouristInfo;
import ru.itmo.se.bl.lab3.entity.TravelBooking;
import ru.itmo.se.bl.lab3.repository.TravelBookingRepository;

@Service
public class TravelBookingService {
    private final TravelBookingRepository repo;

    @Autowired
    public TravelBookingService(TravelBookingRepository repo) {
        this.repo = repo;
    }

    public TravelBooking getById(Integer id) {
        return this.repo.findById(id).orElse(null);
    }

    public TravelBooking getByTouristInfo(TouristInfo touristInfo) {
        return this.repo.findByTouristInfo(touristInfo);
    }

    public void saveTravelBooking(TravelBooking travelBooking) {
        repo.save(travelBooking);
    }
}
