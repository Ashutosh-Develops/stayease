package com.github.saiyam.stayease.dto.hotel.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelRequestBody {

    private String hotelName;
    private String location;
    private String hotelDescription;
    private int numberOfRooms;
}
