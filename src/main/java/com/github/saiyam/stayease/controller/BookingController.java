package com.github.saiyam.stayease.controller;

import com.github.saiyam.stayease.dto.hotel.room.response.RoomBookingResponseBody;
import com.github.saiyam.stayease.entity.RoomBooking;
import com.github.saiyam.stayease.service.hotel.roombooking.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stayease/v1/bookings")
public class BookingController {

    @Autowired
    private IRoomBookingService roomBookingService;

    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<RoomBookingResponseBody> cancelBooking(@PathVariable String bookingId){
        RoomBooking canceledBooking=roomBookingService.cancelBooking(bookingId);
        return new ResponseEntity<RoomBookingResponseBody>(new RoomBookingResponseBody(canceledBooking), HttpStatus.OK);

    }
}
