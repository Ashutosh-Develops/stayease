package com.github.saiyam.stayease.service.hotel;

import com.github.saiyam.stayease.dto.hotel.AvailableHotelsDTO;
import com.github.saiyam.stayease.dto.hotel.request.HotelRequestBody;
import com.github.saiyam.stayease.dto.hotel.room.request.RoomRequestBody;
import com.github.saiyam.stayease.entity.Hotel;
import com.github.saiyam.stayease.entity.Room;

import java.util.List;

public interface IHotelService {


   public Hotel addHotel(HotelRequestBody hotelRequestBody);

   public Hotel deleteHotel(String hotelId);

   public Room addRoom(RoomRequestBody roomRequestBody);

   public List<AvailableHotelsDTO> getAllAvailableHotels();
}
