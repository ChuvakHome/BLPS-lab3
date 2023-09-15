package ru.itmo.se.bl.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.se.bl.lab3.controller.CityController;
import ru.itmo.se.bl.lab3.controller.CountryController;
import ru.itmo.se.bl.lab3.controller.HotelController;
import ru.itmo.se.bl.lab3.controller.TourController;

@SpringBootApplication
public class BLLab3 {
	public static void main(String[] args) {
		SpringApplication.run(new Class[] {
				BLLab3.class,
				CountryController.class,
				CityController.class,
				HotelController.class,
				TourController.class,
			}, args);
	}
}
