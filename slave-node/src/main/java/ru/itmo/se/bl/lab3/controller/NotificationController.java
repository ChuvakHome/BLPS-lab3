package ru.itmo.se.bl.lab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.se.bl.lab3.service.EmailService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/check-notify")
    public void checkAndNotify() {
        emailService.sendNotificationEmails();
    }
}
