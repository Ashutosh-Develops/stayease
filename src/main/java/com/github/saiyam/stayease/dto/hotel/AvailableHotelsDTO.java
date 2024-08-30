package com.github.saiyam.stayease.dto.hotel;

import com.github.saiyam.stayease.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableHotelsDTO {

    private long hotelId;
    private String hotelName;
    private String hotelLocation;
    private String hotelDescription;
    private List<Room> availableRooms;


    public static AvailableHotelsDTO createAvailableHotelsDtoInstance(long hotelId,
                                                                      String hotelName,
                                                                      String hotelLocation,
                                                                      String hotelDescription,
                                                                      List<Room> rooms){

        return new AvailableHotelsDTO(hotelId,hotelName,hotelLocation,hotelDescription,rooms);
    }
}
