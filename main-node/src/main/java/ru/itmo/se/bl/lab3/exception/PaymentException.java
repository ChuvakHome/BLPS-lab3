package ru.itmo.se.bl.lab3.exception;

import ru.itmo.se.bl.lab3.model.PaymentResult;

public class PaymentException extends Exception {
    private final PaymentResult paymentResult;

    public PaymentException(PaymentResult result) {
        super(result.getMessage());
        this.paymentResult = result;
    }

    public PaymentResult getPaymentResult() {
        return paymentResult;
    }
}
