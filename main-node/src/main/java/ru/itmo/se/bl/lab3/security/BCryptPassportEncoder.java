package ru.itmo.se.bl.lab3.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class BCryptPassportEncoder extends BCryptPasswordEncoder implements PassportEncoder {
    public BCryptPassportEncoder(int strength) {
        super(strength, null);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     */
    public BCryptPassportEncoder(BCryptVersion version) {
        super(version, null);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param random the secure random instance to use
     */
    public BCryptPassportEncoder(BCryptVersion version, SecureRandom random) {
        super(version, -1, random);
    }

    /**
     * @param strength the log rounds to use, between 4 and 31
     * @param random the secure random instance to use
     */
    public BCryptPassportEncoder(int strength, SecureRandom random) {
        super(BCryptVersion.$2A, strength, random);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param strength the log rounds to use, between 4 and 31
     */
    public BCryptPassportEncoder(BCryptVersion version, int strength) {
        super(version, strength, null);
    }

    /**
     * @param version the version of bcrypt, can be 2a,2b,2y
     * @param strength the log rounds to use, between 4 and 31
     * @param random the secure random instance to use
     */
    public BCryptPassportEncoder(BCryptVersion version, int strength, SecureRandom random) {
        super(version, strength, random);
    }
}
