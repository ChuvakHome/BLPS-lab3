package ru.itmo.se.bl.lab3.model;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CardInfo {
	@NotEmpty(message = "Expire date is mandatory")
	private Date expireDate;
	
	@NotEmpty(message = "Card Holder is mandatory")
	private String cardHolder;
	
	@NotEmpty(message = "Card Number is mandatory")
	private String cardNumber;
	
	@NotEmpty(message = "CVV is mandatory")
	private int cvv;
}
