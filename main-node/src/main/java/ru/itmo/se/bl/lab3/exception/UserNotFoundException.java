package ru.itmo.se.bl.lab3.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(int id) {
        super("User with id: " + id + " cannot be found");
    }

    public UserNotFoundException(String login) {
        super(String.format("User with login %s cannot be found", login));
    }
}
