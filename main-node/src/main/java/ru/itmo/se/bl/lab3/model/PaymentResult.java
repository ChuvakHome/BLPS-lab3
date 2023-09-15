package ru.itmo.se.bl.lab3.model;

public enum PaymentResult {
	PAYMENT_SUCCESSFUL(true, "Payment was successful"),
	CONNECTION_UNESTABLISHED_PROBLEM(false, "Unable to establish connection with the bank"),
	CARD_BLOCKED(false, "The card is blocked"),
	NOT_ENOUGH_MONEY(false, "Not enough money on the card"),
	CARD_EXPIRED(false, "The card has expired"),
	;
	
	private final boolean successful;
	private final String message;
	
	private PaymentResult(boolean successful, String message) {
		this.successful = successful;
		this.message = message;
	}
	
	public boolean isSuccessful() {
		return successful;
	}
	
	public String getMessage() {
		return message;
	}
}
