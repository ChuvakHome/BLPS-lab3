package ru.itmo.se.bl.lab3.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class CustomBCryptPasswordEncoder extends BCryptPasswordEncoder {
    public CustomBCryptPasswordEncoder(int strength) {
        super(strength, null);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     */
    public CustomBCryptPasswordEncoder(BCryptVersion version) {
        super(version, null);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param random the secure random instance to use
     */
    public CustomBCryptPasswordEncoder(BCryptVersion version, SecureRandom random) {
        super(version, -1, random);
    }

    /**
     * @param strength the log rounds to use, between 4 and 31
     * @param random the secure random instance to use
     */
    public CustomBCryptPasswordEncoder(int strength, SecureRandom random) {
        super(BCryptVersion.$2A, strength, random);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param strength the log rounds to use, between 4 and 31
     */
    public CustomBCryptPasswordEncoder(BCryptVersion version, int strength) {
        super(version, strength, null);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param strength the log rounds to use, between 4 and 31
     * @param random the secure random instance to use
     */
    public CustomBCryptPasswordEncoder(BCryptVersion version, int strength, SecureRandom random) {
        super(version, strength, random);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return "{bcrypt}" + super.encode(rawPassword);
    }
}
