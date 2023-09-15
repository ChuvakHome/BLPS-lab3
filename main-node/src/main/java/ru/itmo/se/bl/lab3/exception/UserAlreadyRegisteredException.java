package ru.itmo.se.bl.lab3.exception;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException(String login) {
        super(String.format("User with login %s has already registered", login));
    }
}
