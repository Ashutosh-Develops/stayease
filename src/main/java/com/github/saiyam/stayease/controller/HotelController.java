package com.github.saiyam.stayease.controller;

import com.github.saiyam.stayease.dto.hotel.AvailableHotelsDTO;
import com.github.saiyam.stayease.dto.hotel.request.HotelRequestBody;
import com.github.saiyam.stayease.dto.hotel.response.AvailableHotelsResponseBody;
import com.github.saiyam.stayease.dto.hotel.room.request.RoomBookingRequestBody;
import com.github.saiyam.stayease.dto.hotel.room.request.RoomRequestBody;
import com.github.saiyam.stayease.dto.hotel.room.response.HotelRoomResponseBody;
import com.github.saiyam.stayease.dto.hotel.room.response.RoomBookingResponseBody;
import com.github.saiyam.stayease.entity.Hotel;
import com.github.saiyam.stayease.entity.Room;
import com.github.saiyam.stayease.entity.RoomBooking;
import com.github.saiyam.stayease.service.hotel.IHotelService;
import com.github.saiyam.stayease.service.hotel.roombooking.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stayease/v1/hotels")
public class HotelController {

    @Autowired
    private IHotelService iHotelService;
    @Autowired
    private IRoomBookingService roomBookingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody HotelRequestBody hotelRequestBody){
        Hotel hotel=iHotelService.addHotel(hotelRequestBody);
        return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{hotelId}/rooms")
    public ResponseEntity<HotelRoomResponseBody> addRoom(@RequestBody RoomRequestBody roomRequestBody){
        Room room = iHotelService.addRoom(roomRequestBody);
        return new ResponseEntity<HotelRoomResponseBody>(HotelRoomResponseBody.getResponseBodyFromRoom(room),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER','HOTEL_MANAGER','ADMIN')")
    @PostMapping("/{hotelId}/book")
    public ResponseEntity<RoomBookingResponseBody> bookRoom(@PathVariable String hotelId, @RequestBody RoomBookingRequestBody roomBookingRequestBody){
        RoomBooking bookedRoom=roomBookingService.bookRoom(hotelId,roomBookingRequestBody);
        return new ResponseEntity<RoomBookingResponseBody>(new RoomBookingResponseBody(bookedRoom),HttpStatus.OK);
    }

    // public endpoint
    @GetMapping
    public ResponseEntity<List<AvailableHotelsResponseBody>> getHotel(){
          List<AvailableHotelsDTO> availableHotelsDTOList=iHotelService.getAllAvailableHotels();
          List<AvailableHotelsResponseBody> response=AvailableHotelsResponseBody.getResponseBodyFromDTO(availableHotelsDTOList);

          return new ResponseEntity<List<AvailableHotelsResponseBody>>(response,HttpStatus.OK);
    }



}
