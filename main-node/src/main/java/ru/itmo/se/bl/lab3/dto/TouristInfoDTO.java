package ru.itmo.se.bl.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TouristInfoDTO {
    PassportDTO passport;
    InternationalPassportDTO internationalPassport;
    String email;
}
