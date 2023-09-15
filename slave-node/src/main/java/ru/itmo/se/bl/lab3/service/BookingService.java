package ru.itmo.se.bl.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab3.entity.Booking;
import ru.itmo.se.bl.lab3.repository.BookingRepository;

import java.sql.Date;
import java.util.List;

@Service
public class BookingService {
	private final BookingRepository bookingRepository;

	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public List<Booking> getBookingsByDayDifference(Date date, int days) {
		return bookingRepository.findBookingsByDayDifference(date, days);
	}
}
