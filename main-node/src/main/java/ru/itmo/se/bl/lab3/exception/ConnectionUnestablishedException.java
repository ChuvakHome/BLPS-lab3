package ru.itmo.se.bl.lab3.exception;

import ru.itmo.se.bl.lab3.model.PaymentResult;

public class ConnectionUnestablishedException extends PaymentException {
    public ConnectionUnestablishedException() {
        super(PaymentResult.CONNECTION_UNESTABLISHED_PROBLEM);
    }
}
