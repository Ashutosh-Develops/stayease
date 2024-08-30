package com.github.saiyam.stayease.service.hotel.roombooking;

import com.github.saiyam.stayease.dto.hotel.room.request.RoomBookingRequestBody;
import com.github.saiyam.stayease.entity.RoomBooking;

public interface IRoomBookingService {

    public RoomBooking bookRoom(String hotelId, RoomBookingRequestBody roomBookingRequestBody);

    public RoomBooking cancelBooking(String bookingId);
}
