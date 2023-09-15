package ru.itmo.se.bl.lab3.exception;

import ru.itmo.se.bl.lab3.model.PaymentResult;

public class CardBlockedException extends PaymentException {
    public CardBlockedException() {
        super(PaymentResult.CARD_BLOCKED);
    }
}
