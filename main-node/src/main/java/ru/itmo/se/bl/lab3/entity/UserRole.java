package ru.itmo.se.bl.lab3.entity;

public enum UserRole {
    USER,
    ADMIN
    ;

    public String getAsAuthority() {
        return "ROLE_" + name();
    }
}
