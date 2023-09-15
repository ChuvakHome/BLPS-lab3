package ru.itmo.se.bl.lab3.dto;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itmo.se.bl.lab3.entity.Gender;
import ru.itmo.se.bl.lab3.entity.Passport;
import ru.itmo.se.bl.lab3.utils.DateFormatters;

@AllArgsConstructor
@Getter
public class PassportDTO {
	@NotEmpty(message = "Last name is mandatory")
	private String lastName;
	
	@NotEmpty(message = "First name is mandatory")
	private String firstName;
	private String middleName;
	
	@NotNull(message = "Birth date cannot be null")
	private String birthDate;
	
	@NotNull(message = "Issue date cannot be null")
	private String issueDate;
	
	@NotEmpty(message = "Series is mandatory")
	private String series;
	
	@NotEmpty(message = "Number is mandatory")
	private String number;
	
	@NotNull(message = "Gender is mandatory")
	private String gender;
	
	public Passport toEntity() {
		Gender gender;
		
		try {
			gender = Gender.valueOf(this.gender);
		} catch (IllegalArgumentException e) {
			gender = null;
		}
		
		Date birthDate = Date.valueOf(LocalDate.parse(this.birthDate, DateFormatters.STANDARD_DATE_FORMAT));
		Date issueDate = Date.valueOf(LocalDate.parse(this.issueDate, DateFormatters.STANDARD_DATE_FORMAT));
		
		Passport passport = new Passport();
		passport.setId(null);
		passport.setSeries(series);
		passport.setNumber(number);
		passport.setLastName(lastName);
		passport.setFirstName(firstName);
		passport.setMiddleName(middleName);
		passport.setBirthDate(birthDate);
		passport.setIssueDate(issueDate);
		passport.setGender(gender);
		
		return passport;
	}
}
