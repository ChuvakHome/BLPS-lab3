package ru.itmo.se.bl.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.itmo.se.bl.lab3.entity.*;
import ru.itmo.se.bl.lab3.exception.*;
import ru.itmo.se.bl.lab3.model.BookingRequest;
import ru.itmo.se.bl.lab3.model.EmailNotification;
import ru.itmo.se.bl.lab3.model.PaymentRequest;
import ru.itmo.se.bl.lab3.repository.BookingRepository;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
	private final BookingRepository bookingRepository;
	private final TravelService travelService;
	private final TravelBookingService travelBookingService;
	private final HotelService hotelService;
	private final HotelBookingService hotelBookingService;
	private final PaymentService paymentService;
	private final MessageService messageService;
	private final PlatformTransactionManager txManager;

	@Autowired
	public BookingService(TravelService travelService, TravelBookingService travelBookingService, HotelService hotelService, HotelBookingService hotelBookingService, BookingRepository bookingRepository, PaymentService paymentService, MessageService messageService, PlatformTransactionManager transactionManager) {
		this.travelService = travelService;
		this.travelBookingService = travelBookingService;
		this.hotelService = hotelService;
		this.hotelBookingService = hotelBookingService;
		this.bookingRepository = bookingRepository;
		this.paymentService = paymentService;
		this.messageService = messageService;
		this.txManager = transactionManager;
	}

	public void addBooking(BookingRequest bookingRequest, PaymentRequest req) throws HotelBookException, TravelNotFoundException, TravelBookException, HotelNotFoundException, PaymentException {
		List<EmailNotification> notificationList = new ArrayList<>();

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);

		TransactionStatus status = txManager.getTransaction(def);

		try {
			int travelId = bookingRequest.getTravelId();
			Travel travel = travelService.getById(travelId);

			int hotelId = bookingRequest.getHotelId();
			Hotel hotel = hotelService.getById(hotelId);

			for (TouristInfo info: bookingRequest.getTouristInfoList()) {
				TravelBooking travelBooking = new TravelBooking();
				travelBooking.setId(null);
				travelBooking.setTravel(travel);
				travelBooking.setTouristInfo(info);

				travelBookingService.saveTravelBooking(travelBooking);

				HotelBooking hotelBooking = new HotelBooking();
				hotelBooking.setId(null);
				hotelBooking.setHotel(hotel);
				hotelBooking.setTouristInfo(info);

				hotelBookingService.saveHotelBooking(hotelBooking);

				Booking booking = new Booking();
				booking.setBookingDate(bookingRequest.getBookingDate());
				booking.setDays(bookingRequest.getDays());
				booking.setTouristInfo(info);
				booking.setTravelBooking(travelBooking);
				booking.setHotelBooking(hotelBooking);

				bookingRepository.save(booking);

				EmailNotification emailNotification = new EmailNotification();

				if (info.getPassport() != null) {
					emailNotification.setFirstName(info.getPassport().getFirstName());
					emailNotification.setLastName(info.getPassport().getLastName());
					emailNotification.setMiddleName(info.getPassport().getMiddleName());
				}
				else {
					emailNotification.setFirstName(info.getInternationalPassport().getFirstName());
					emailNotification.setLastName(info.getInternationalPassport().getLastName());
					emailNotification.setMiddleName(null);
				}

				paymentService.doPayment(req);

				emailNotification.setEmail(info.getEmail());
				emailNotification.setBookingDate(bookingRequest.getBookingDate());

				notificationList.add(emailNotification);
			}

			txManager.commit(status);

			for (EmailNotification t: notificationList)
				messageService.sendObjectMessage(t);
		} catch (TravelNotFoundException e) {
			txManager.rollback(status);

			throw e;
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
