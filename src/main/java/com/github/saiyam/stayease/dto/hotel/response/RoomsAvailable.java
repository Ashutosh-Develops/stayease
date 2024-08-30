package com.github.saiyam.stayease.dto.hotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomsAvailable {

    private long roomId;
    private int roomCapacity;
}
