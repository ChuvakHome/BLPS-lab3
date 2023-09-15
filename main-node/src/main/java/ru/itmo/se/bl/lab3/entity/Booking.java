package ru.itmo.se.bl.lab3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Entity
@Table(name = "bookings")
@Data
@Getter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "booking_date", nullable = false)
	private Date bookingDate;
	
	@Column(nullable = false)
	private int days;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "tourist_info_id", nullable = false)
	private TouristInfo touristInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_booking_id", nullable = false)
	private HotelBooking hotelBooking;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_booking_id", nullable = false)
	private TravelBooking travelBooking;
}
