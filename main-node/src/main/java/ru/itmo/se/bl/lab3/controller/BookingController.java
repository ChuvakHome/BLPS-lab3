package ru.itmo.se.bl.lab3.controller;

import static ru.itmo.se.bl.lab3.utils.ValidationUtils.nullOrEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab3.dto.*;
import ru.itmo.se.bl.lab3.entity.Booking;
import ru.itmo.se.bl.lab3.entity.Hotel;
import ru.itmo.se.bl.lab3.entity.InternationalPassport;
import ru.itmo.se.bl.lab3.entity.Passport;
import ru.itmo.se.bl.lab3.entity.TouristInfo;
import ru.itmo.se.bl.lab3.entity.Travel;
import ru.itmo.se.bl.lab3.exception.*;
import ru.itmo.se.bl.lab3.model.BookingRequest;
import ru.itmo.se.bl.lab3.model.BookingResult;
import ru.itmo.se.bl.lab3.model.CardInfo;
import ru.itmo.se.bl.lab3.model.PaymentRequest;
import ru.itmo.se.bl.lab3.service.*;
import ru.itmo.se.bl.lab3.utils.ValidationUtils;
import ru.itmo.se.bl.lab3.response.BookingResponse;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private InternationalPassportService internationalPassportService;
	
	@Autowired
	private TouristInfoService touristInfoService;

	@Autowired
	private TravelService travelService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private BookingService bookingService;

	@PostMapping("/book")
	public BookingResponse doBook(@RequestBody BookingDTO bookingDTO) {
		TouristInfoDTO[] touristInfosDTO = bookingDTO.getTouristInfos();
		PaymentInfoDTO paymentInfoDTO = bookingDTO.getPaymentInfo();

		List<TouristInfo> touristInfoList = new ArrayList<>();

		for (TouristInfoDTO infoDTO: touristInfosDTO) {
			PassportDTO passportDTO = infoDTO.getPassport();
			InternationalPassportDTO internationalPassportDTO = infoDTO.getInternationalPassport();

			if (passportDTO == null && internationalPassportDTO == null)
				return new BookingResponse(false, "EMPTY_TOURIST_INFO", "Tourists information is missed");

			TouristInfo touristInfo = null;

			Passport passport;
			Passport passportEntity = null;

			if (passportDTO != null) {
				if (!ValidationUtils.validatePassport(passportDTO))
					return new BookingResponse(false, "INVALID_PASSPORT", "Invalid passport(s)");

				passport = passportDTO.toEntity();
				passportEntity = passportService.getBySeriesAndNumber(passport.getSeries(), passport.getNumber());

				if (passportEntity == null)
					passportEntity = passportService.save(passport);

				touristInfo = touristInfoService.getByPassportId(passportEntity.getId());

				if (touristInfo == null)
					touristInfo = touristInfoService.save(new TouristInfo(null, passportEntity, null, infoDTO.getEmail()));
				else if (touristInfo.getEmail() == null || !touristInfo.getEmail().equals(infoDTO.getEmail())) {
					touristInfo.setEmail(infoDTO.getEmail());
					touristInfo = touristInfoService.save(touristInfo);
				}

			}

			InternationalPassport internationalPassport;
			InternationalPassport internationalPassportEntity;

			if (internationalPassportDTO != null) {
				if (!ValidationUtils.validateInternationPassport(internationalPassportDTO))
					return new BookingResponse(false, "INVALID_INTERNATIONAL_PASSPORT", "Invalid internation passport(s)");

				internationalPassport = internationalPassportDTO.toEntity();
				internationalPassportEntity = internationalPassportService.getBySeriesAndNumber(internationalPassport.getSeries(), internationalPassport.getNumber());

				if (internationalPassportEntity == null)
					internationalPassportEntity = internationalPassportService.save(internationalPassport);

				if (touristInfo != null) {
					if (!internationalPassportEntity.getId().equals(touristInfo.getInternationalPassport().getId()))
						touristInfo.setInternationalPassport(internationalPassportEntity);
				} else {
					touristInfo = touristInfoService.getByInternationalPassportId(internationalPassportEntity.getId());

					if (touristInfo != null) {
						if (passportEntity != null && !passportEntity.getId().equals(touristInfo.getPassport().getId()))
							touristInfo.setPassport(passportEntity);

						if (touristInfo.getEmail() == null || !touristInfo.getEmail().equals(infoDTO.getEmail())) {
							touristInfo.setEmail(infoDTO.getEmail());
							touristInfo = touristInfoService.save(touristInfo);
						}
					} else
						touristInfo = touristInfoService.save(new TouristInfo(null, passportEntity, internationalPassportEntity, infoDTO.getEmail()));
				}
			}

			touristInfoList.add(touristInfo);
		}

		BookingRequest bookingRequest = bookingDTO.toRequest();
		bookingRequest.setTouristInfoList(touristInfoList);

		CardInfo cardInfo = paymentInfoDTO.getCardInfo().toEntity();

		try {
			Travel travel = new Travel();
			travelService.getById(bookingRequest.getTravelId());

			Hotel hotel = new Hotel();
			hotelService.getById(bookingRequest.getHotelId());

			int charge = travel.getCost() + hotel.getNightCost() * touristInfoList.size() * bookingRequest.getDays();

			bookingService.addBooking(bookingRequest, new PaymentRequest(cardInfo, charge));
		} catch (TravelNotFoundException e) {
			return new BookingResponse(BookingResult.INVALID_TRAVEL);
		} catch (TravelBookException e) {
			return new BookingResponse(BookingResult.TRAVEL_BOOK_FAILURE);
		} catch (HotelNotFoundException e) {
			return new BookingResponse(BookingResult.INVALID_HOTEL);
		} catch (HotelBookException e) {
			return new BookingResponse(BookingResult.HOTEL_BOOK_FAILURE);
		} catch (PaymentException e) {
			return new BookingResponse(e.getPaymentResult());
		}

		return new BookingResponse(BookingResult.SUCCESSFUL_BOOKING);
	}
}
