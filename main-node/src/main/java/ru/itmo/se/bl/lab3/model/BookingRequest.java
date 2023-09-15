package ru.itmo.se.bl.lab3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.se.bl.lab3.entity.TouristInfo;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class BookingRequest {
    private Date bookingDate;
    private int days;
    private int travelId;
    private int hotelId;

    private List<TouristInfo> touristInfoList;
}
