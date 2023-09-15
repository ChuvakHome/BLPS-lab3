package ru.itmo.se.bl.lab3.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;

public class DateFormatters {
	public static final DateTimeFormatter STANDARD_DATE_FORMAT = new DateTimeFormatterBuilder()
				.appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NEVER)
				.appendLiteral('.')
				.appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NEVER)
				.appendLiteral('.')
				.appendValue(ChronoField.YEAR, 4, 10, SignStyle.NEVER)
				.toFormatter();
	
	public static final DateTimeFormatter AMERICAN_DATE_FORMAT = new DateTimeFormatterBuilder()
			.appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NEVER)
			.appendLiteral('.')
			.appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NEVER)
			.appendLiteral('.')
			.appendValue(ChronoField.YEAR, 4, 10, SignStyle.NEVER)
			.toFormatter();
	
	private DateFormatters() {}
	
	public static LocalDate parseCardExpireDate(String s) {
		if (s.contains("/")) {
			String[] parts = s.split("/", 2);
			
			if (parts.length == 2 && 
				!parts[0].contains("/") &&
				parts[0].length() >= 1 && parts[0].length() <= 2 && 
				!parts[1].contains("/") &&
				parts[1].length() >= 1 && parts[1].length() <= 2) {
				String monthStr = parts[0];
				int month = Integer.parseInt(monthStr);
				
				String yearStr = parts[1];
				int year = Integer.parseInt(yearStr);
				
				int currentYear = LocalDate.now().getYear();
				int estimatedYear = currentYear / 100 * 100 + year;
				
				if (month <= 0 || month > 12)
					throw new DateTimeException("Invalid month: " + month);
				else if (year <= 0 || year >= 100)
					throw new DateTimeException("Invalid year: " + year);
				
				return LocalDate.parse(
							String.format("01.%d.%d", month, estimatedYear),
							STANDARD_DATE_FORMAT
						);
			}
		}
			
		throw new DateTimeParseException(String.format("Text '%s' could not be parsed", s), s, 0);
	}
}
