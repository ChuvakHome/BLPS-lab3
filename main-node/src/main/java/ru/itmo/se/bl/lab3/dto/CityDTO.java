package ru.itmo.se.bl.lab3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab3.entity.City;

@NoArgsConstructor
@Data
public class CityDTO {
    private int id;
    private String name;
    private String localName;
    private int countryId;

    public static CityDTO fromEntity(City city) {
        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setLocalName(city.getLocalName());
        dto.setCountryId(city.getCountry().getId());

        return dto;
    }
}
