package ru.itmo.se.bl.lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itmo.se.bl.lab3.entity.Booking;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(
            value = "SELECT * FROM bookings WHERE EXTRACT(DAY FROM booking_date - CAST(:date AS timestamp)) = :days",
            nativeQuery = true)
    List<Booking> findBookingsByDayDifference(@Param("date") Date date, @Param("days") int days);
}
