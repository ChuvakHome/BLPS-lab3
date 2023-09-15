package ru.itmo.se.bl.lab3.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.stereotype.Service;

import ru.itmo.se.bl.lab3.exception.*;
import ru.itmo.se.bl.lab3.model.CardInfo;
import ru.itmo.se.bl.lab3.model.PaymentRequest;
import ru.itmo.se.bl.lab3.model.PaymentResult;

@Service
public class PaymentService {
	public void doPayment(PaymentRequest req) throws PaymentException {
		doPayment(req.getCardInfo(), req.getCharge());
	}
	public void doPayment(CardInfo cardInfo, int charge) throws PaymentException {
		PaymentResult paymentResult;

		for (int i = 0; i < 5; ++i) {
			if ((paymentResult = pay(cardInfo, charge)).isSuccessful())
				return;
			else {
				switch (paymentResult) {
					case NOT_ENOUGH_MONEY -> throw new NotEnoughMoneyException();
					case CARD_BLOCKED -> throw new CardBlockedException();
					case CARD_EXPIRED -> throw new CardExpireException();
				}
			}
		}

		throw new ConnectionUnestablishedException();
	}

	public PaymentResult pay(PaymentRequest req) {
		return pay(req.getCardInfo(), req.getCharge());
	}

	public PaymentResult pay(CardInfo cardInfo, int charge) {
		Date now = Date.valueOf(LocalDate.now());
		Random random = new Random();
		
		if (cardInfo.getExpireDate().after(now)) {
			PaymentResult[] paymentResults = PaymentResult.values();
			
			return random.nextInt(10) > 2 ? PaymentResult.PAYMENT_SUCCESSFUL : paymentResults[random.nextInt(1, paymentResults.length - 1)];
		}
		else
			return PaymentResult.CARD_EXPIRED;
	}
}
