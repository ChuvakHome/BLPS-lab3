package ru.itmo.se.bl.lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.se.bl.lab3.entity.TouristInfo;
import ru.itmo.se.bl.lab3.entity.TravelBooking;

@Repository
public interface TravelBookingRepository extends JpaRepository<TravelBooking, Integer> {
    TravelBooking findByTouristInfo(TouristInfo touristInfo);
}
