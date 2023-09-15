package ru.itmo.se.bl.lab3.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itmo.se.bl.lab3.model.CardInfo;
import ru.itmo.se.bl.lab3.utils.DateFormatters;

@AllArgsConstructor
@Getter
public class CardInfoDTO {
	@NotEmpty(message = "Expire date is mandatory")
	private String expireDate;
	
	@NotEmpty(message = "Card Holder is mandatory")
	private String cardHolder;
	
	@NotEmpty(message = "Card Number is mandatory")
	private String cardNumber;
	
	@NotEmpty(message = "CVV is mandatory")
	private int cvv;
	
	public CardInfo toEntity() {
		Date expireDate = Date.valueOf(DateFormatters.parseCardExpireDate(this.expireDate));
		
		return new CardInfo(expireDate, cardHolder, cardNumber, cvv);
	}
}
