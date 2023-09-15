package ru.itmo.se.bl.lab3.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

import ru.itmo.se.bl.lab3.dto.InternationalPassportDTO;
import ru.itmo.se.bl.lab3.dto.PassportDTO;
import ru.itmo.se.bl.lab3.entity.InternationalPassport;

public class ValidationUtils {
	public static boolean validateInternationPassport(InternationalPassportDTO internationalPassportDTO) {
		InternationalPassport internationalPassport = internationalPassportDTO.toEntity();
		Date now = Date.valueOf(LocalDate.now());
		
		return internationalPassport.getExpireDate().after(internationalPassport.getBirthDate()) && internationalPassport.getExpireDate().after(now);
	}
	
	public static boolean validatePassport(PassportDTO passportDTO) {
		return true;
	}
	
	public static<T> boolean nullOrEmpty(T[] array) {
		return array == null || array.length == 0;
	}
	
	public static boolean requireAllNonNull(Object... objects) {
		return Stream.of(objects).allMatch(Objects::nonNull);
	}
}
