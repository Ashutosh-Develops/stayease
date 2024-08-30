package com.github.saiyam.stayease.dto.hotel.room.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomRequestBody {

    private String hotelId;
    private String status;
}
