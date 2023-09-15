package ru.itmo.se.bl.lab3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentInfoDTO {
//	private PassportDTO[] passports;
//
//	private InternationalPassportDTO[] internationalPassports;
	
	@NotNull
	private ContactInfoDTO contactInfo;
	
	@NotNull
	private CardInfoDTO cardInfo;
}
