package ru.itmo.se.bl.lab3.exception;

import ru.itmo.se.bl.lab3.model.PaymentResult;

public class NotEnoughMoneyException extends PaymentException {
    public NotEnoughMoneyException() {
        super(PaymentResult.NOT_ENOUGH_MONEY);
    }
}
