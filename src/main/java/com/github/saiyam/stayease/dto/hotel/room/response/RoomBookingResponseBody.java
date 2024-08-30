package com.github.saiyam.stayease.dto.hotel.room.response;

import com.github.saiyam.stayease.entity.RoomBooking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomBookingResponseBody {

    private long bookingId;
    private long userId;
    private long roomId;
    private long hotelId;

    public RoomBookingResponseBody(RoomBooking roomBooking){
        this.bookingId=roomBooking.getId();
        this.userId=roomBooking.getUser().getId();
        this.roomId=roomBooking.getRoom().getId();
        this.hotelId=roomBooking.getRoom().getHotel().getId();
    }
}
