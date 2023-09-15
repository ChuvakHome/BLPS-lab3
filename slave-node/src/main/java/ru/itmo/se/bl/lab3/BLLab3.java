package ru.itmo.se.bl.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import ru.itmo.se.bl.lab3.controller.NotificationController;
import ru.itmo.se.bl.lab3.service.EmailService;

@SpringBootApplication
public class BLLab3 {
	public static void main(String[] args) {
		SpringApplication.run(new Class[] {
				BLLab3.class,
				NotificationController.class,
				EmailService.class,
			}, args);
	}
}
