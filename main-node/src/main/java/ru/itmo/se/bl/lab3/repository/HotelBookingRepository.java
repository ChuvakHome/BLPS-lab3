package ru.itmo.se.bl.lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.se.bl.lab3.entity.HotelBooking;
import ru.itmo.se.bl.lab3.entity.TouristInfo;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {
    HotelBooking findByTouristInfo(TouristInfo touristInfo);
}
