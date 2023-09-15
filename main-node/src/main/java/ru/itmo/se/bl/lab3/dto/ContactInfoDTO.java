package ru.itmo.se.bl.lab3.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContactInfoDTO {
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "Phone number is mandatory")
	private String phoneNumber; 
}
