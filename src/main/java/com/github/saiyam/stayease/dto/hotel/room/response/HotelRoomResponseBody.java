package com.github.saiyam.stayease.dto.hotel.room.response;

import com.github.saiyam.stayease.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelRoomResponseBody {

    private long roomId;
    private long hotelId;
    private String roomStatus;
    private int numOfGuestsCapacity;


    public static HotelRoomResponseBody getResponseBodyFromRoom(Room room){
        return new HotelRoomResponseBody(room.getId(), room.getHotel().getId(),room.getHotelRoomStatus().toString(),room.getGuestCapacity());
    }
}
