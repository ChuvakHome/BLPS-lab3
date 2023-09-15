package ru.itmo.se.bl.lab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.se.bl.lab3.dto.UserDTO;
import ru.itmo.se.bl.lab3.entity.User;
import ru.itmo.se.bl.lab3.entity.UserRole;
import ru.itmo.se.bl.lab3.exception.UserAlreadyRegisteredException;
import ru.itmo.se.bl.lab3.exception.UserNotFoundException;
import ru.itmo.se.bl.lab3.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public boolean doUserRegister(@RequestBody UserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.USER);

        try {
            service.registerUser(user);

            return true;
        } catch (UserAlreadyRegisteredException e) {
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<User> doUserLogin(@RequestBody UserDTO dto) {
        try {
            return ResponseEntity.ok(service.getByLoginAndPassword(dto.getLogin(), dto.getPassword()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
