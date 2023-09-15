package ru.itmo.se.bl.lab3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab3.entity.Tour;

@Data
@NoArgsConstructor
public class TourDTO {
    private int id;
    private String tourName;
    private int tourDays;
    private int tourTravelCost;
    private int hotelId;

    public static TourDTO fromEntity(Tour tour) {
        TourDTO dto = new TourDTO();
        dto.setId(tour.getId());
        dto.setTourName(tour.getTourName());
        dto.setTourDays(tour.getTourDays());
        dto.setTourTravelCost(tour.getTourTravelCost());
        dto.setHotelId(tour.getTourHotel().getId());

        return dto;
    }
}
