package ru.itmo.se.bl.lab3.dto;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itmo.se.bl.lab3.entity.Gender;
import ru.itmo.se.bl.lab3.entity.InternationalPassport;
import ru.itmo.se.bl.lab3.utils.DateFormatters;

@AllArgsConstructor
@Getter
public class InternationalPassportDTO {
	@NotEmpty(message = "Last name is mandatory")
	private String lastName;
	
	@NotEmpty(message = "First name is mandatory")
	private String firstName;
	
	@NotEmpty(message = "Series is mandatory")
	private String series;
	
	@NotEmpty(message = "Number is mandatory")
	private String number;
	
	@NotNull(message = "Birth date cannot be null")
	private String birthDate;
	
	@NotNull(message = "Expire date cannot be null")
	private String expireDate;
	
	@NotNull(message = "Gender is mandatory")
	private String gender;
	
	@NotEmpty(message = "Citizenship is mandatory")
	private String citizenship;
	
	public InternationalPassport toEntity() {
		Gender gender;
		
		try {
			gender = Gender.valueOf(this.gender);
		} catch (IllegalArgumentException e) {
			gender = null;
		}
		
		Date birthDate = Date.valueOf(LocalDate.parse(this.birthDate, DateFormatters.STANDARD_DATE_FORMAT));
		Date expireDate = Date.valueOf(LocalDate.parse(this.expireDate, DateFormatters.STANDARD_DATE_FORMAT));
		
		InternationalPassport internationalPassport = new InternationalPassport();
		internationalPassport.setId(null);
		internationalPassport.setLastName(lastName);
		internationalPassport.setFirstName(firstName);
		internationalPassport.setSeries(series);
		internationalPassport.setNumber(number);
		internationalPassport.setBirthDate(birthDate);
		internationalPassport.setExpireDate(expireDate);
		internationalPassport.setCitizenship(citizenship);
		internationalPassport.setGender(gender);
		
		return internationalPassport;
	}
}
