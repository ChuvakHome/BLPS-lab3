package ru.itmo.se.bl.lab3.exception;

import ru.itmo.se.bl.lab3.model.PaymentResult;

public class CardExpireException extends PaymentException {
    public CardExpireException() {
        super(PaymentResult.CARD_EXPIRED);
    }
}
