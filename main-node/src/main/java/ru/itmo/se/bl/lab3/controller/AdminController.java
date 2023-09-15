package ru.itmo.se.bl.lab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.se.bl.lab3.exception.UserNotFoundException;
import ru.itmo.se.bl.lab3.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(name = "/")
    public String adminTest() {
        return "Admin test successfully passed";
    }

    @PostMapping("/promote")
    public boolean promoteUser(@RequestBody String login) {
        try {
            return userService.promoteUser(login);
        } catch (UserNotFoundException e) { return false; }
    }
 }
