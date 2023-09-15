package ru.itmo.se.bl.lab3.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itmo.se.bl.lab3.model.BookingResult;
import ru.itmo.se.bl.lab3.model.PaymentResult;

@AllArgsConstructor
@Getter
public class BookingResponse {
	private boolean success;
	private String result;
	private String message;

	public BookingResponse(BookingResult bookingResult) {
		this(bookingResult.isSuccessful(), bookingResult.name(), bookingResult.getMessage());
	}
	public BookingResponse(PaymentResult paymentResult) {
		this(paymentResult.isSuccessful(), paymentResult.name(), paymentResult.getMessage());
	}
}
