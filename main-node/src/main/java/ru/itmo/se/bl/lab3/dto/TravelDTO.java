package ru.itmo.se.bl.lab3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab3.entity.Travel;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
public class TravelDTO implements Serializable {
    private int id;
    private Date travelDate;
    private int cost;
    private int startCityId;
    private int endCityId;
    private long travelDuration;

    public static TravelDTO fromEntity(Travel travel) {
        TravelDTO dto = new TravelDTO();

        dto.setId(travel.getId());
        dto.setTravelDate(travel.getTravelDate());
        dto.setCost(travel.getCost());
        dto.setStartCityId(travel.getStartCity().getId());
        dto.setEndCityId(travel.getEndCity().getId());
        dto.setTravelDuration(travel.getTravelDuration());

        return dto;
    }
}
